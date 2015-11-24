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
package libSB.math.vector;

/**
 *
 * @author Simon Berndt
 */
public class MutableVec3 implements Vec3.Mutable {

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

    @Override
    public double distance(double x, double y, double z) {
        double a = this.x - x;
        double b = this.y - y;
        double c = this.z - z;
        return Math.sqrt(a * a + b * b + c * c);
    }

    @Override
    public double distance(Vec3 vec) {
        return distance(vec.getX(), vec.getY(), vec.getZ());
    }

    @Override
    public Vec3.Mutable add(double x, double y, double z) {
        return new MutableVec3(this.x + x, this.y + y, this.z + z);
    }

    @Override
    public Vec3.Mutable add(Vec3 vec) {
        return add(vec.getX(), vec.getY(), vec.getZ());
    }

    @Override
    public Vec3.Mutable subtract(double x, double y, double z) {
        return new MutableVec3(this.x - x, this.y - y, this.z - z);
    }

    @Override
    public Vec3.Mutable subtract(Vec3 vec) {
        return subtract(vec.getX(), vec.getY(), vec.getZ());
    }

    @Override
    public Vec3.Mutable divide(double divisor) {
        if (divisor == 0.0) {
            return new MutableVec3(Double.NaN, Double.NaN, Double.NaN);
        }
        return new MutableVec3(this.x / divisor, this.y / divisor, this.z / divisor);
    }

    @Override
    public Vec3.Mutable multiply(double factor) {
        return new MutableVec3(this.x * factor, this.y * factor, this.z * factor);
    }

    @Override
    public double magnitude() {
        return Math.sqrt(x * x + y * y + z * z);
    }

    @Override
    public double theta() {
        return (Math.PI / 2) - (Math.atan(z / Math.hypot(x, y)));
    }

    @Override
    public double phi() {
        return Math.atan2(y, x);
    }

    @Override
    public double dotProduct(double x, double y, double z) {
        return this.x * x + this.y * y + this.z * z;
    }

    @Override
    public double dotProduct(Vec3 vec) {
        return dotProduct(vec.getX(), vec.getY(), vec.getZ());
    }

    @Override
    public Vec3.Mutable crossProduct(double x, double y, double z) {
        return new MutableVec3(this.y * z - this.z * y, this.z * x - this.x * z, this.x * y - this.y * x);
    }

    @Override
    public Vec3.Mutable crossProduct(Vec3 vec) {
        return crossProduct(vec.getX(), vec.getY(), vec.getZ());
    }

    @Override
    public Vec3.Mutable inverse() {
        return new MutableVec3(-x, -y, -z);
    }

    @Override
    public Vec3.Mutable normalize() {
        final double mag = magnitude();
        if (mag == 0.0) {
            return new MutableVec3(0.0, 0.0, 0.0);
        }
        return new MutableVec3(x / mag, y / mag, z / mag);
    }

    //<editor-fold defaultstate="collapsed" desc="Inplace">
    @Override
    public Vec3.Mutable inplaceAdd(double x, double y, double z) {
        this.x += x;
        this.y += y;
        this.z *= z;
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
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof MutableVec3) {
            MutableVec3 other = (MutableVec3) obj;
            return this.x == other.x && this.y == other.y && this.z == other.z;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        long bits = 7L;
        bits = 31L * bits + Double.doubleToLongBits(x);
        bits = 31L * bits + Double.doubleToLongBits(y);
        bits = 31L * bits + Double.doubleToLongBits(z);
        return (int) (bits ^ (bits >> 32));
    }
//</editor-fold>

    public static class Builder {
        private double bX;
        private double bY;
        private double bZ;

        private Builder() {
            this.bX = 0.0;
            this.bY = 0.0;
            this.bZ = 0.0;
        }
        
        public Builder x(double x) {
            this.bX = x;
            return this;
        }
        
        public Builder y(double y) {
            this.bY = y;
            return this;
        }
        
        public Builder z(double z) {
            this.bZ = z;
            return this;
        }
        
        public Vec3.Mutable build() {
            return new MutableVec3(bX, bY, bZ);
        }
        
    }
}
