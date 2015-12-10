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

/**
 *
 * @author Simon Berndt
 */
public final class MutableVec4 extends AbstractReadOnlyVec4<MutableVec4> implements Vec4.Mutable {

    private volatile double x;
    private volatile double y;
    private volatile double z;
    private volatile double w;

    public MutableVec4() {
        this(0.0, 0.0, 0.0, 0.0);
    }
    
    public MutableVec4(Vec4 vec) {
        this(vec.getX(), vec.getY(), vec.getZ(), vec.getW());
    }

    public MutableVec4(double x, double y, double z, double w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    //<editor-fold defaultstate="collapsed" desc="Inplace">
    @Override
    public Vec4.Mutable inplaceAdd(double x, double y, double z, double w) {
        this.x += x;
        this.y += y;
        this.z += z;
        this.w += w;
        return this;
    }

    @Override
    public Vec4.Mutable inplaceAdd(Vec4 vec) {
        return inplaceAdd(vec.getX(), vec.getY(), vec.getZ(), vec.getW());
    }

    @Override
    public Vec4.Mutable inplaceSubtract(double x, double y, double z, double w) {
        this.x -= x;
        this.y -= y;
        this.z -= z;
        this.w -= w;
        return this;
    }

    @Override
    public Vec4.Mutable inplaceSubtract(Vec4 vec) {
        return inplaceSubtract(vec.getX(), vec.getY(), vec.getZ(), vec.getW());
    }

    @Override
    public Vec4.Mutable inplaceMultiply(double factor) {
        this.x *= factor;
        this.y *= factor;
        this.z *= factor;
        this.w *= factor;
        return this;
    }

    @Override
    public Vec4.Mutable inplaceDivide(double dividend) {
        if (dividend == 0.0) {
            this.x = Double.NaN;
            this.y = Double.NaN;
            this.z = Double.NaN;
            this.w = Double.NaN;
        } else {
            this.x /= dividend;
            this.y /= dividend;
            this.z /= dividend;
            this.w /= dividend;
        }
        return this;
    }

    @Override
    public Vec4.Mutable inplaceNormalize() {
        final double mag = magnitude();
        if (mag != 0.0) {
            x /= mag;
            y /= mag;
            z /= mag;
            w /= mag;
        }
        return this;
    }

    @Override
    public Vec4.Mutable inplaceInverse() {
        this.x = -x;
        this.y = -y;
        this.z = -z;
        this.w = -w;
        return this;
    }
//</editor-fold>

    @Override
    protected MutableVec4 createVec4(double x, double y, double z, double w) {
        return new MutableVec4(x, y, z, w);
    }
    
    public static Builder builder() {
        return new Builder();
    }
    
    //<editor-fold defaultstate="collapsed" desc="Getter/Setter">
    @Override
    public void set(double x, double y, double z, double w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    @Override
    public void set(Vec4 vec) {
        set(vec.getX(), vec.getY(), vec.getZ(), vec.getW());
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

    @Override
    public double getZ() {
        return z;
    }

    @Override
    public void setZ(double z) {
        this.z = z;
    }

    @Override
    public double getW() {
        return w;
    }

    @Override
    public void setW(double w) {
        this.w = w;
    }
    
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Object-Stuff">
    
    @Override
    public int hashCode() {
        long bits = 3;
        bits = 97L * bits + Double.doubleToLongBits(getX());
        bits = 97L * bits + Double.doubleToLongBits(getY());
        bits = 97L * bits + Double.doubleToLongBits(getZ());
        bits = 97L * bits + Double.doubleToLongBits(getW());
        return (int) (bits ^ (bits >> 32));
    }
//</editor-fold>

    public static class Builder extends AbstractVec4Builder<MutableVec4> {

        @Override
        protected MutableVec4 createVec4(double x, double y, double z, double w) {
            return new MutableVec4(x, y, z, w);
        }

    }
}
