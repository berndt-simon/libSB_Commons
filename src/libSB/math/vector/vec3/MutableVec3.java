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
package libSB.math.vector.vec3;

/**
 *
 * @author Simon Berndt
 */
public final class MutableVec3 extends AbstractReadOnlyVec3<MutableVec3> implements Vec3.Mutable {

    private volatile double x;
    private volatile double y;
    private volatile double z;

    public MutableVec3() {
        this(0.0, 0.0, 0.0);
    }
    
    public MutableVec3(Vec3 vec) {
        this(vec.getX(), vec.getY(), vec.getZ());
    }

    public MutableVec3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    //<editor-fold defaultstate="collapsed" desc="Inplace">
    @Override
    public Vec3.Mutable inplaceAdd(double x, double y, double z) {
        this.x += x;
        this.y += y;
        this.z += z;
        return this;
    }

    @Override
    public Vec3.Mutable inplaceAdd(Vec3 vec) {
        return inplaceAdd(vec.getX(), vec.getY(), vec.getZ());
    }

    @Override
    public Vec3.Mutable inplaceSubtract(double x, double y, double z) {
        this.x -= x;
        this.y -= y;
        this.z -= z;
        return this;
    }

    @Override
    public Vec3.Mutable inplaceSubtract(Vec3 vec) {
        return inplaceSubtract(vec.getX(), vec.getY(), vec.getZ());
    }

    @Override
    public Vec3.Mutable inplaceMultiply(double factor) {
        this.x *= factor;
        this.y *= factor;
        this.z *= factor;
        return this;
    }

    @Override
    public Vec3.Mutable inplaceDivide(double dividend) {
        if (dividend == 0.0) {
            this.x = Double.NaN;
            this.y = Double.NaN;
            this.z = Double.NaN;
        } else {
            this.x /= dividend;
            this.y /= dividend;
            this.z /= dividend;
        }
        return this;
    }

    @Override
    public Vec3.Mutable inplaceNormalize() {
        final double mag = magnitude();
        if (mag != 0.0) {
            x /= mag;
            y /= mag;
            z /= mag;
        }
        return this;
    }

    @Override
    public Vec3.Mutable inplaceInverse() {
        this.x = -x;
        this.y = -y;
        this.z = -z;
        return this;
    }
//</editor-fold>

    @Override
    protected MutableVec3 createVec3(double x, double y, double z) {
        return new MutableVec3(x, y, z);
    }
    
    public static Builder builder() {
        return new Builder();
    }
    
    //<editor-fold defaultstate="collapsed" desc="Getter/Setter">
    @Override
    public void set(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public void setPolar(double r, double theta, double phi) {
        this.x = r * Math.sin(theta) * Math.cos(phi);
        this.y = r * Math.sin(theta) * Math.sin(phi);
        this.z = r * Math.cos(theta);
    }

    @Override
    public void set(Vec3 vec) {
        set(vec.getX(), vec.getY(), vec.getZ());
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
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Object-Stuff">
    
    @Override
    public int hashCode() {
        long bits = 7L;
        bits = 31L * bits + Double.doubleToLongBits(x);
        bits = 31L * bits + Double.doubleToLongBits(y);
        bits = 31L * bits + Double.doubleToLongBits(z);
        return (int) (bits ^ (bits >> 32));
    }
//</editor-fold>

    public static class Builder extends AbstractVec3Builder<MutableVec3> {

        @Override
        protected MutableVec3 createVec3(double x, double y, double z) {
            return new MutableVec3(x, y, z);
        }

    }
}
