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

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import javafx.beans.value.WritableValue;
import libSB.collections.table.basic.ModifiableTable;
import libSB.collections.table.basic.StaticTable;

/**
 *
 * @author Simon Berndt
 */
final public class ArrayListTable<T> implements ModifiableTable<T> {

    private final ArrayList<WritableValue<T>> cells;

    private int width;
    private int height;

    public ArrayListTable(int width, int height) {
	this.width = width;
	this.height = height;

	// init cells;
	this.cells = new ArrayList<>(getCellCount());
	IntStream.range(0, getCellCount()).forEach(i -> this.cells.add(new Cell<>()));
    }

    public ArrayListTable(StaticTable<T> sourceTbl) {
	this.width = sourceTbl.getColumnCount();
	this.height = sourceTbl.getRowCount();

	// init cells;
	this.cells = new ArrayList<>(sourceTbl.getCellCount());
	sourceTbl.cells().forEachOrdered(this.cells::add);
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
	return this.cells.get(linearize(x, y, getColumnCount()));
    }

    @Override
    public T getCellValue(int x, int y) {
	return this.cells.get(linearize(x, y, getColumnCount())).getValue();
    }

    @Override
    public void setCellValue(int x, int y, T value) {
	this.cells.get(linearize(x, y, getColumnCount())).setValue(value);
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
    public Stream<WritableValue<T>> cells() {
	return this.cells.stream();
    }

    @Override
    public Stream<T> cellValues() {
	return this.cells.stream().map(WritableValue::getValue);
    }

    @Override
    public void addRow() {
	addRows(1);
    }

    private void addRows(int rowCount) {
	this.height += rowCount;
	this.cells.ensureCapacity(getCellCount());
	IntStream.range(0, this.width * rowCount).forEach(i -> this.cells.add(new Cell<>()));
    }

    @Override
    public void removeRow() {
	removeRows(1);
    }

    private void removeRows(int rowCount) {
	if (this.height > rowCount) {
	    this.height -= rowCount;
	    removeLastCells(getColumnCount() * rowCount);
	}
    }

    @Override
    public void addColumn() {
	addColumns(1);
    }

    private void addColumns(int columnCount) {
	final int oldWidth = this.width;
	this.width += columnCount;
	this.cells.ensureCapacity(getCellCount());
	IntStream.range(0, this.height * columnCount).forEach(i -> this.cells.add(new Cell<>()));
	for (int y = getRowCount() - 1; y >= 0; y--) {
	    for (int x = oldWidth - 1; x >= 0; x--) {
		final WritableValue<T> oldCell = this.cells.get(linearize(x, y, oldWidth));
		final WritableValue<T> newCell = this.cells.get(linearize(x, y, this.width));
		newCell.setValue(oldCell.getValue());
	    }
	}
	for (int y = 0; y < getRowCount(); y++) {
	    for (int x = oldWidth; x < this.width; x++) {
		getCell(x, y).setValue(null);
	    }
	}
    }

    @Override
    public void removeColumn() {
	removeColumns(-1);
    }

    private void removeColumns(int columnCount) {
	if (this.width > columnCount) {
	    final int oldWidth = this.width;
	    this.width -= columnCount;
	    for (int y = 0; y < getRowCount(); y++) {
		for (int x = 0; x < getColumnCount(); x++) {
		    final WritableValue<T> oldCell = this.cells.get(linearize(x, y, oldWidth));
		    final WritableValue<T> newCell = this.cells.get(linearize(x, y, this.width));
		    newCell.setValue(oldCell.getValue());
		}
	    }
	    removeLastCells(getRowCount() * columnCount);
	}
    }

    private void removeLastCells(int count) {
	final ListIterator<WritableValue<T>> listIterator = this.cells.listIterator(this.cells.size());
	IntStream.range(0, count).forEach(i -> {
	    listIterator.previous();
	    listIterator.remove();
	});
    }

    @Override
    public void setRows(int rowCount) {
	if (getRowCount() < rowCount) {
	    addRows(rowCount - getRowCount());
	}
	if (getRowCount() > rowCount) {
	    removeRows(getRowCount() - rowCount);
	}
    }

    @Override
    public void setColumns(int columnCount) {
	if (getColumnCount() < columnCount) {
	    addColumns(columnCount - getColumnCount());
	}
	if (getColumnCount() > columnCount) {
	    removeColumns(getColumnCount() - columnCount);
	}
    }

    @Override
    public void setDimension(int rowCount, int columnClount) {
	setColumns(columnClount);
	setRows(rowCount);
    }

    private static class Cell<T> implements WritableValue<T> {

	private T value;

	@Override
	public T getValue() {
	    return value;
	}

	@Override
	public void setValue(T value) {
	    this.value = value;
	}

    }

}
