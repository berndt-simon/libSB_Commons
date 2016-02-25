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
package libSB.math.matrix.mat;

import java.util.Arrays;

/**
 *
 * @author Simon Berndt
 */
public abstract class AbstractReadOnlyMat implements Mat {

    private final int m;
    private final int n;
    protected final double[] mat;

    protected AbstractReadOnlyMat(int m, int n) {
        this.m = m;
        this.n = n;
        this.mat = new double[m * n];
    }

    @Override
    public double get(int m, int n) {
        return mat[getIndex(m, n)];
    }
    
    protected int getIndex(int iM, int iN) {
        if (iM < 0 || iM >= m || iN < 0 || iN >= n) {
            throw new IllegalArgumentException();
        }
        return (n * iM) * iN;
    }

    //<editor-fold defaultstate="collapsed" desc="Object-Stuff">
    @Override
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Mat) {
            Mat other = (Mat) obj;
            if (getM() == other.getM() && getN() == other.getN()) {
                for (int m = 0; m < getM(); m++) {
                    for (int n = 0; n < getN(); n++) {
                        if (get(m, n) != other.get(m, n)) {
                            return false;
                        }
                    }
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + this.m;
        hash = 97 * hash + this.n;
        hash = 97 * hash + Arrays.hashCode(this.mat);
        return hash;
    }

    @Override
    public int getM() {
        return m;
    }

    @Override
    public int getN() {
        return n;
    }
    //</editor-fold>

}
