/* 
 * The MIT License
 *
 * Copyright 2016 Simon Berndt.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package libSB.watchdog;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import libSB.concurrent.DaemonThreadFactory;

/**
 * Basic Implementation of the Watchdog-Interface which throws an Error when the Watchdog is triggered
 * @author Simon Berndt
 */
public class SimpleWatchdog implements Watchdog {

    private static final Logger LOG = Logger.getLogger(SimpleWatchdog.class.getName());

    private final AtomicInteger callbackCounter;
    private final ScheduledExecutorService watchdogService;

    private final int errorThreshold;
    private final long tick;
    private final TimeUnit timeUnit;

    private ScheduledFuture<?> currentTask;

    public SimpleWatchdog(int counterThreshold, long tick, TimeUnit tickUnit) {
	this.callbackCounter = new AtomicInteger(0);
	this.watchdogService = Executors.newSingleThreadScheduledExecutor(DaemonThreadFactory::createThread);
	this.currentTask = null;

	this.errorThreshold = counterThreshold;
	this.tick = tick;
	this.timeUnit = tickUnit;
    }

    @Override
    public void reset() {
	callbackCounter.set(0);
    }

    @Override
    public void arm() {
	currentTask = watchdogService.scheduleAtFixedRate(this::checkStatus, tick, tick, timeUnit);
    }

    @Override
    public void disarm() {
	if (currentTask != null) {
	    currentTask.cancel(true);
	    currentTask = null;
	} else {
	    LOG.log(Level.WARNING, "Watchdog wasn't armed in the first place");
	}
    }

    private void checkStatus() {
	int counterValue = callbackCounter.getAndIncrement();
	if (counterValue > 0) {
	    LOG.log(Level.FINE, "Watchdog warning: {0}", counterValue);
	    if (counterValue > errorThreshold) {
		LOG.log(Level.SEVERE, "Watchdog triggered");
		throw new Error("Watchdog triggered");
	    }
	}
    }

}
