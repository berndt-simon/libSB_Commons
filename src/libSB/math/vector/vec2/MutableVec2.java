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

import libSB.math.vector.vec3.MutableVec3;

/**
 *
 * @author Simon Berndt
 */
public final class MutableVec2 extends AbstractReadOnlyVec2<MutableVec2, MutableVec3> implements Vec2.Mutable {

    private volatile double x;
    private volatile double y;

    public MutableVec2() {
        this(0.0, 0.0);
    }

    public MutableVec2(Vec2 vec) {
        this(vec.getX(), vec.getY());
    }

    public MutableVec2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    //<editor-fold defaultstate="collapsed" desc="Inplace">
    @Override
    public Vec2.Mutable inplaceAdd(double x, double y) {
        this.x += x;
        this.y += y;
        return this;
    }

    @Override
    public Vec2.Mutable inplaceAdd(Vec2 vec) {
        return inplaceAdd(vec.getX(), vec.getY());
    }

    @Override
    public Vec2.Mutable inplaceSubtract(double x, double y) {
        this.x -= x;
        this.y -= y;
        return this;
    }

    @Override
    public Vec2.Mutable inplaceSubtract(Vec2 vec) {
        return inplaceSubtract(vec.getX(), vec.getY());
    }

    @Override
    public Vec2.Mutable inplaceMultiply(double factor) {
        this.x *= factor;
        this.y *= factor;
        return this;
    }

    @Override
    public Vec2.Mutable inplaceDivide(double dividend) {
        if (dividend == 0.0) {
            this.x = Double.NaN;
            this.y = Double.NaN;
        } else {
            this.x /= dividend;
            this.y /= dividend;
        }
        return this;
    }

    @Override
    public Vec2.Mutable inplaceNormalize() {
        final double mag = magnitude();
        if (mag != 0.0) {
            x /= mag;
            y /= mag;
        }
        return this;
    }

    @Override
    public Mutable inplaceInverse() {
        this.x = -x;
        this.y = -y;
        return this;
    }
//</editor-fold>

    @Override
    protected MutableVec2 createVec2(double x, double y) {
        return new MutableVec2(x, y);
    }

    @Override
    protected MutableVec3 createVec3(double x, double y, double z) {
        return new MutableVec3(x, y, z);
    }
    
    public static Builder builder() {
        return new Builder();
    }
    
    //<editor-fold defaultstate="collapsed" desc="Getter/Setter">

    @Override
    public void setAxis(int axis, double value) {
        switch (axis) {
            case 0:
                this.x = value;
                break;
            case 1:
                this.y = value;
                break;
        }
        throw new IllegalArgumentException("Invalid Axis-Index");
    }
    
    @Override
    public void set(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void setPolar(double r, double phi) {
        this.x = r * Math.cos(phi);
        this.y = r * Math.sin(phi);
    }

    @Override
    public void set(Vec2 vec) {
        set(vec.getX(), vec.getY());
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public void setX(double x) {
        this.x = x;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public void setY(double y) {
        this.y = y;
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Object-Stuff">
    @Override
    public int hashCode() {
        long bits = 7L;
        bits = 31L * bits + Double.doubleToLongBits(x);
        bits = 31L * bits + Double.doubleToLongBits(y);
        return (int) (bits ^ (bits >> 32));
    }
    //</editor-fold>

    public static class Builder extends AbstractVec2Builder<MutableVec2> {

        @Override
        protected MutableVec2 createVec2(double x, double y) {
            return new MutableVec2(x, y);
        }

    }

}
