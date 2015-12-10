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
package libSB.math.vector.vec4;

import java.util.Locale;

/**
 *
 * @author Simon Berndt
 */
abstract class AbstractReadOnlyVec4<V4 extends Vec4> implements Vec4 {
    
    @Override
    public final double distance(double x, double y, double z, double w) {
        double a = this.getX() - x;
        double b = this.getY() - y;
        double c = this.getZ() - z;
        return Math.sqrt(a * a + b * b + c * c);
    }

    @Override
    public final double distance(Vec4 vec) {
        return distance(vec.getX(), vec.getY(), vec.getZ(), vec.getW());
    }

    @Override
    public final V4 add(double x, double y, double z, double w) {
        return createVec4(this.getX() + x, this.getY() + y, this.getZ() + z, this.getW() + w);
    }

    @Override
    public final V4 add(Vec4 vec) {
        return add(vec.getX(), vec.getY(), vec.getZ(), vec.getW());
    }

    @Override
    public final V4 subtract(double x, double y, double z, double w) {
        return createVec4(this.getX() - x, this.getY() - y, this.getZ() - z, this.getW() - w);
    }

    @Override
    public final V4 subtract(Vec4 vec) {
        return subtract(vec.getX(), vec.getY(), vec.getZ(), vec.getW());
    }

    @Override
    public final V4 divide(double divisor) {
        if (divisor == 0.0) {
            return createVec4(Double.NaN, Double.NaN, Double.NaN, Double.NaN);
        }
        return createVec4(this.getX() / divisor, this.getY() / divisor, this.getZ() / divisor, this.getW() / divisor);
    }

    @Override
    public final V4 multiply(double factor) {
        return createVec4(this.getX() * factor, this.getY() * factor, this.getZ() * factor, this.getW() * factor);
    }

    @Override
    public final double magnitude() {
        return Math.sqrt(getX() * getX() + getY() * getY() + getZ() * getZ() + getW() * getW());
    }

    @Override
    public final double dotProduct(double x, double y, double z, double w) {
        return this.getX() * x + this.getY() * y + this.getZ() * z + this.getW() * w;
    }

    @Override
    public final double dotProduct(Vec4 vec) {
        return dotProduct(vec.getX(), vec.getY(), vec.getZ(), vec.getW());
    }

    @Override
    public final V4 normalize() {
        final double mag = magnitude();
        if (mag == 0.0) {
            return createVec4(0.0, 0.0, 0.0, 0.0);
        }
        return createVec4(getX() / mag, getY() / mag, getZ() / mag, getW() / mag);
    }

    @Override
    public final V4 inverse() {
        return createVec4(-getX(), -getY(), -getZ(), -getW());
    }

    protected abstract V4 createVec4(double x, double y, double z, double w);

    //<editor-fold defaultstate="collapsed" desc="Object-Stuff">
    @Override
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Vec4) {
            Vec4 other = (Vec4) obj;
            return this.getX() == other.getX() && this.getY() == other.getY() && this.getZ() == other.getZ() && this.getW() == other.getW();
        } else {
            return false;
        }
    }
    
    @Override
    public abstract int hashCode();
    
    @Override
    public String toString() {
        return String.format(Locale.ROOT, "[%f; %f; %f; %f]", getX(), getY(), getZ(), getW());
    }
//</editor-fold>
    
   
}
