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
public final class ImmutableVec3 implements Vec3 {
    
    
    public static final Vec3 ORIGIN = new ImmutableVec3();

    private final double x;
    private final double y;
    private final double z;
    private final int hash;

    public ImmutableVec3() {
        this(0.0, 0.0, 0.0);
    }
    
    public ImmutableVec3(Vec3 vec) {
        this(vec.getX(), vec.getY(), vec.getZ());
    }

    public ImmutableVec3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.hash = calcHash();
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
    public Vec3 add(double x, double y, double z) {
        return new ImmutableVec3(this.x + x, this.y + y, this.z + z);
    }

    @Override
    public Vec3 add(Vec3 vec) {
        return add(vec.getX(), vec.getY(), vec.getZ());
    }

    @Override
    public Vec3 subtract(double x, double y, double z) {
        return new ImmutableVec3(this.x - x, this.y - y, this.z - z);
    }

    @Override
    public Vec3 subtract(Vec3 vec) {
        return subtract(vec.getX(), vec.getY(), vec.getZ());
    }

    @Override
    public Vec3 divide(double divisor) {
        if (divisor == 0.0) {
            return new ImmutableVec3(Double.NaN, Double.NaN, Double.NaN);
        }
        return new ImmutableVec3(this.x / divisor, this.y / divisor, this.z / divisor);
    }

    @Override
    public Vec3 multiply(double factor) {
        return new ImmutableVec3(this.x * factor, this.y * factor, this.z * factor);
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
    public Vec3 crossProduct(double x, double y, double z) {
        return new ImmutableVec3(this.y * z - this.z * y, this.z * x - this.x * z, this.x * y - this.y * x);
    }

    @Override
    public Vec3 crossProduct(Vec3 vec) {
        return crossProduct(vec.getX(), vec.getY(), vec.getZ());
    }

    @Override
    public Vec3 normalize() {
        final double mag = magnitude();
        if (mag == 0.0) {
            return new ImmutableVec3(0.0, 0.0, 0.0);
        }
        return new ImmutableVec3(x / mag, y / mag, z / mag);
    }

    @Override
    public Vec3 inverse() {
        return new ImmutableVec3(-x, -y, -z);
    }

    //<editor-fold defaultstate="collapsed" desc="Getter">

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public double getZ() {
        return z;
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Object-Stuff">
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ImmutableVec3) {
            ImmutableVec3 other = (ImmutableVec3) obj;
            return this.x == other.x && this.y == other.y && this.z == other.z;
        } else {
            return false;
        }
    }

    private int calcHash() {
        long bits = 7L;
        bits = 31L * bits + Double.doubleToLongBits(x);
        bits = 31L * bits + Double.doubleToLongBits(y);
        bits = 31L * bits + Double.doubleToLongBits(z);
        return (int) (bits ^ (bits >> 32));
    }
    
    @Override
    public int hashCode() {
        return hash;
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
        
        public Vec3 build() {
            return new ImmutableVec3(bX, bY, bZ);
        }
        
    }

}
