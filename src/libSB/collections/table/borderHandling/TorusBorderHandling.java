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

import java.util.function.IntSupplier;

/**
 *
 * @author Simon Berndt
 */
final public class TorusBorderHandling implements BorderHandling {

    private final IntSupplier minIndexX;
    private final IntSupplier minIndexY;
    private final IntSupplier maxIndexX;
    private final IntSupplier maxIndexY;

    public TorusBorderHandling(IntSupplier minIndexX, IntSupplier minIndexY, IntSupplier maxIndexX, IntSupplier maxIndexY) {
	this.minIndexX = minIndexX;
	this.minIndexY = minIndexY;
	this.maxIndexX = maxIndexX;
	this.maxIndexY = maxIndexY;
    }

    private static int getLength(int min, int max) {
	return max - min + 1;
    }

    @Override
    public int getRowIndex(int orgRowIndex) {
	final int minY = this.minIndexY.getAsInt();
	final int maxY = this.maxIndexY.getAsInt();
	final int length = getLength(minY, maxY);
	while (orgRowIndex < minY) {
	    orgRowIndex += length;
	}
	while (orgRowIndex > maxY) {
	    orgRowIndex -= length;
	}
	return orgRowIndex;
    }

    @Override
    public int getColumnIndex(int orgColumnIndex) {
	final int minX = this.minIndexX.getAsInt();
	final int maxX = this.maxIndexX.getAsInt();
	final int length = getLength(minX, maxX);
	while (orgColumnIndex < minX) {
	    orgColumnIndex += length;
	}
	while (orgColumnIndex > maxX) {
	    orgColumnIndex -= length;
	}
	return orgColumnIndex;
    }

}
