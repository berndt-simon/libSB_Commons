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
package libSB.math.vector.vec2;

import java.util.Locale;
import libSB.math.vector.vec3.Vec3;

/**
 *
 * @author Simon Berndt
 */
abstract class AbstractReadOnlyVec2<V2 extends Vec2, V3 extends Vec3> implements Vec2 {

    @Override
    public final double distance(double x, double y) {
        return Math.hypot(this.getX() - x, this.getY() - y);
    }

    @Override
    public final double distance(Vec2 vec) {
        return distance(vec.getX(), vec.getY());
    }

    @Override
    public final V2 add(double x, double y) {
        return createVec2(this.getX() + x, this.getY() + y);
    }

    @Override
    public final V2 add(Vec2 vec) {
        return add(vec.getX(), vec.getY());
    }

    @Override
    public final V2 subtract(double x, double y) {
        return createVec2(this.getX() - x, this.getY() - y);
    }

    @Override
    public final V2 subtract(Vec2 vec) {
        return subtract(vec.getX(), vec.getY());
    }

    @Override
    public final V2 divide(double divisor) {
        return createVec2(this.getX() / divisor, this.getY() / divisor);
    }

    @Override
    public final V2 multiply(double factor) {
        return createVec2(this.getX() * factor, this.getY() * factor);
    }

    @Override
    public final double magnitude() {
        return Math.hypot(getX(), getY());
    }

    @Override
    public final double phi() {
        return Math.atan2(getY(), getX());
    }

    @Override
    public final double dotProduct(double x, double y) {
        return this.getX() * x + this.getY() * y;
    }

    @Override
    public final double dotProduct(Vec2 vec) {
        return dotProduct(vec.getX(), vec.getY());
    }

    @Override
    public final V3 crossProduct(double x, double y) {
        return createVec3(0, 0, this.getX() * y - this.getY() * x);
    }

    @Override
    public final V3 crossProduct(Vec2 vec) {
        return crossProduct(vec.getX(), vec.getY());
    }

    @Override
    public final V2 inverse() {
        return createVec2(-getX(), -getY());
    }

    @Override
    public final V2 normalize() {
        final double mag = magnitude();
        if (mag == 0.0) {
            return createVec2(0.0, 0.0);
        }
        return createVec2(getX() / mag, getY() / mag);
    }

    @Override
    public double get(int axisIndex) {
        switch (axisIndex) {
            case 0:
                return getX();
            case 1:
                return getY();
        }
        throw new IllegalArgumentException("Invalid Axis-Index");
    }

    protected abstract V2 createVec2(double x, double y);

    protected abstract V3 createVec3(double x, double y, double z);

    //<editor-fold defaultstate="collapsed" desc="Object-Stuff">
    @Override
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Vec2) {
            Vec2 other = (Vec2) obj;
            return this.getX() == other.getX() && this.getY() == other.getY();
        } else {
            return false;
        }
    }

    @Override
    public abstract int hashCode();

    @Override
    public String toString() {
        return String.format(Locale.ROOT, "[%f; %f]", getX(), getY());
    }
    //</editor-fold>

}
