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
package libSB.collections.table.borderHandling;

import javafx.beans.value.WritableValue;
import libSB.collections.table.basic.StaticTable;
import libSB.functionalInterface.IntBiFunction;

/**
 *
 * @author Simon Berndt
 */
final public class BorderHandlingTableAccess<T> implements IntBiFunction<WritableValue<T>> {

    private final StaticTable<T> wrappedTable;
    private final BorderHandling borderHandler;

    public BorderHandlingTableAccess(StaticTable<T> wrappedTable, BorderHandling borderHandler) {
	this.wrappedTable = wrappedTable;
	this.borderHandler = borderHandler;
    }

    @Override
    public WritableValue<T> apply(int x, int y) {
	return this.wrappedTable.getCell(this.borderHandler.getColumnIndex(x), this.borderHandler.getRowIndex(y));
    }

}
