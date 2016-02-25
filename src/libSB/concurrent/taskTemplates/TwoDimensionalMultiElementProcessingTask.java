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
package libSB.concurrent.taskTemplates;

import java.util.concurrent.RecursiveAction;
import java.util.function.BiConsumer;
import java.util.stream.StreamSupport;
import libSB.streamAPI.CurringUtil;

/**
 *
 * @author Simon Berndt
 */
public final class TwoDimensionalMultiElementProcessingTask<T, A> extends RecursiveAction {

    private static final long serialVersionUID = 6865759796158647737L;

    private final Iterable<T> elements;
    private final Iterable<A> arguments;
    transient private final BiConsumer<? super T, ? super A> elementProcess;

    public TwoDimensionalMultiElementProcessingTask(Iterable<T> elements, Iterable<A> arguments, BiConsumer<? super T, ? super A> elementProcess) {
	this.elements = elements;
	this.arguments = arguments;
	this.elementProcess = elementProcess;
    }

    @Override
    protected void compute() {
        StreamSupport.stream(elements.spliterator(), true).map(CurringUtil.curryBiConsumer(this.elementProcess)::apply).forEach(this.arguments::forEach);
    }
}
