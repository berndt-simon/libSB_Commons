/* 
 * The MIT License
 *
 * Copyright 2015 Simon Berndt.
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
package libSB.logUtils;

import java.util.logging.ConsoleHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Simon Berndt
 */
public final class LoggerFormatUtil {

    public static final Logger GLOBAL_LOGGER = Logger.getLogger("");
    public static final Logger CSS_LOGGER = Logger.getLogger("css");

    private LoggerFormatUtil() {
    }

    /**
     * Remove all default Logger-Handlers from the Global-Loggen and adds a new Instance of the ConsoleHandler with the MessageFormaterLite as the specified Formatter.
     */
    public static void setupLoggingStyle() {
	    final Formatter formatter = new MessageFormatterLite();
	    final ConsoleHandler consoleHandler = new ConsoleHandler();
	consoleHandler.setFormatter(formatter);
	consoleHandler.setLevel(Level.FINE);

	for (final Handler handler : GLOBAL_LOGGER.getHandlers()) {
	    GLOBAL_LOGGER.removeHandler(handler);
	}
	GLOBAL_LOGGER.addHandler(consoleHandler);
    }

}
