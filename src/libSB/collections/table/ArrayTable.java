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
package libSB.collections.table;

import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import javafx.beans.value.WritableValue;
import libSB.collections.table.basic.StaticTable;

/**
 * @author Simon Berndt
 */
public final class ArrayTable<T> implements StaticTable<T> {

    private final Object[] cells;

    private final int width;
    private final int height;

    public ArrayTable(int width, int height) {
	this.width = width;
	this.height = height;
	this.cells = new Object[getCellCount()];
    }
    
    public ArrayTable(int width, int height, Supplier<T> initialSupplier) {
	this(width, height);
        for (int i = 0; i < cells.length; i++) {
            cells[i] = initialSupplier.get();
        }
    }

    public ArrayTable(StaticTable<T> sourceTbl) {
	this.width = sourceTbl.getColumnCount();
	this.height = sourceTbl.getRowCount();

	// init cells;
	this.cells = new Object[getCellCount()];
	IntStream.range(0, getRowCount()).forEach((int y) -> {
	    IntStream.range(0, getColumnCount()).forEach((int x) -> {
		setCellValue(x, y, sourceTbl.getCellValue(x, y));
	    });
	});
    }

    private static int linearize(int x, int y, int width) {
	final int offset = width * y;
	return offset + x;
    }

    @Override
    public int getCellCount() {
	return getColumnCount() * getRowCount();
    }

    @Override
    public WritableValue<T> getCell(int x, int y) {
	return new CellAdapter(x, y);
    }

    @Override
    @SuppressWarnings("unchecked")
    public T getCellValue(int x, int y) {
	return (T) this.cells[(linearize(x, y, getColumnCount()))];
    }

    @Override
    public void setCellValue(int x, int y, T value) {
	this.cells[(linearize(x, y, getColumnCount()))] = value;
    }

    @Override
    public int getRowCount() {
	return this.height;
    }

    @Override
    public int getColumnCount() {
	return this.width;
    }

    @Override
    public Stream<T> cellValues() {
	return IntStream.range(0, getRowCount()).boxed().flatMap(this::streamRowValues);
    }

    @Override
    public Stream<WritableValue<T>> cells() {
	return IntStream.range(0, getRowCount()).boxed().flatMap(this::streamRowCells);
    }

    private Stream<T> streamRowValues(int rowIndex) {
	return IntStream.range(0, getColumnCount()).mapToObj((int columnIndex) -> getCellValue(columnIndex, rowIndex));
    }

    private Stream<WritableValue<T>> streamRowCells(int rowIndex) {
	return IntStream.range(0, getColumnCount()).mapToObj((int columnIndex) -> new CellAdapter(columnIndex, rowIndex));
    }

    private final class CellAdapter implements WritableValue<T> {

	private final int x, y;

	private CellAdapter(int x, int y) {
	    this.x = x;
	    this.y = y;
	}

	@Override
	public T getValue() {
	    return getCellValue(this.x, this.y);
	}

	@Override
	public void setValue(T value) {
	    setCellValue(this.x, this.y, value);
	}

    }

}
