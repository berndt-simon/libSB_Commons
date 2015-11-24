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
package libSB.collections.table.basic;

import java.util.stream.IntStream;
import java.util.stream.Stream;
import javafx.beans.value.WritableValue;

public interface StaticTable<T> {

    WritableValue<T> getCell(int x, int y);

    T getCellValue(int x, int y);

    void setCellValue(int x, int y, T value);

    int getRowCount();

    int getColumnCount();

    default int getCellCount() {
	return getRowCount() * getColumnCount();
    }

    Stream<WritableValue<T>> cells();

    Stream<T> cellValues();

    default boolean isEmpty() {
	return getCellCount() < 1;
    }

    public static <T> String toString(StaticTable<T> table) {
	final StringBuilder str = new StringBuilder();
	IntStream.range(0, table.getRowCount()).forEach((int y) -> {
	    IntStream.range(0, table.getColumnCount()).forEach((int x) -> {
		str.append(table.getCell(x, y).toString());
		str.append(' ');
	    });
	    str.append(System.lineSeparator());
	});
	return str.toString();
    }

}
