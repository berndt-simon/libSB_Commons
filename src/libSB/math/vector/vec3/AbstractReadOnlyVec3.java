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
package libSB.math.vector.vec3;

import java.util.Locale;

/**
 *
 * @author Simon Berndt
 */
abstract class AbstractReadOnlyVec3<V3 extends Vec3> implements Vec3 {

    @Override
    public final double distance(double x, double y, double z) {
        double a = this.getX() - x;
        double b = this.getY() - y;
        double c = this.getZ() - z;
        return Math.sqrt(a * a + b * b + c * c);
    }

    @Override
    public final double distance(Vec3 vec) {
        return distance(vec.getX(), vec.getY(), vec.getZ());
    }

    @Override
    public final V3 add(double x, double y, double z) {
        return createVec3(this.getX() + x, this.getY() + y, this.getZ() + z);
    }

    @Override
    public final V3 add(Vec3 vec) {
        return add(vec.getX(), vec.getY(), vec.getZ());
    }

    @Override
    public final V3 subtract(double x, double y, double z) {
        return createVec3(this.getX() - x, this.getY() - y, this.getZ() - z);
    }

    @Override
    public final V3 subtract(Vec3 vec) {
        return subtract(vec.getX(), vec.getY(), vec.getZ());
    }

    @Override
    public final V3 divide(double divisor) {
        if (divisor == 0.0) {
            return createVec3(Double.NaN, Double.NaN, Double.NaN);
        }
        return createVec3(this.getX() / divisor, this.getY() / divisor, this.getZ() / divisor);
    }

    @Override
    public final V3 multiply(double factor) {
        return createVec3(this.getX() * factor, this.getY() * factor, this.getZ() * factor);
    }

    @Override
    public final double magnitude() {
        return Math.sqrt(getX() * getX() + getY() * getY() + getZ() * getZ());
    }

    @Override
    public final double theta() {
        return (Math.PI / 2) - (Math.atan(getZ() / Math.hypot(getX(), getY())));
    }

    @Override
    public final double phi() {
        return Math.atan2(getY(), getX());
    }

    @Override
    public final double dotProduct(double x, double y, double z) {
        return this.getX() * x + this.getY() * y + this.getZ() * z;
    }

    @Override
    public final double dotProduct(Vec3 vec) {
        return dotProduct(vec.getX(), vec.getY(), vec.getZ());
    }

    @Override
    public final V3 crossProduct(double x, double y, double z) {
        return createVec3(this.getY() * z - this.getZ() * y, this.getZ() * x - this.getX() * z, this.getX() * y - this.getY() * x);
    }

    @Override
    public final V3 crossProduct(Vec3 vec) {
        return crossProduct(vec.getX(), vec.getY(), vec.getZ());
    }

    @Override
    public final V3 normalize() {
        final double mag = magnitude();
        if (mag == 0.0) {
            return createVec3(0.0, 0.0, 0.0);
        }
        return createVec3(getX() / mag, getY() / mag, getZ() / mag);
    }

    @Override
    public final V3 inverse() {
        return createVec3(-getX(), -getY(), -getZ());
    }

    @Override
    public double get(int axisIndex) {
        switch (axisIndex) {
            case 0:
                return getX();
            case 1:
                return getY();
            case 2:
                return getZ();
        }
        throw new IllegalArgumentException("Invalid Axis-Index");
    }

    protected abstract V3 createVec3(double x, double y, double z);

    //<editor-fold defaultstate="collapsed" desc="Object-Stuff">
    @Override
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Vec3) {
            Vec3 other = (Vec3) obj;
            return this.getX() == other.getX() && this.getY() == other.getY() && this.getZ() == other.getZ();
        } else {
            return false;
        }
    }

    @Override
    public abstract int hashCode();

    @Override
    public String toString() {
        return String.format(Locale.ROOT, "[%f; %f; %f]", getX(), getY(), getZ());
    }
//</editor-fold>

}
