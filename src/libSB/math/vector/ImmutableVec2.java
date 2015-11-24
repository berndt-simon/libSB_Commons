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
public final class ImmutableVec2 implements Vec2 {

    public static final Vec2 ORIGIN = new ImmutableVec2();
    
    private final double x;
    private final double y;
    private final int hash;

    public ImmutableVec2() {
        this(0.0, 0.0);
    }
    
    public ImmutableVec2(Vec2 vec) {
        this(vec.getX(), vec.getY());
    }

    public ImmutableVec2(double x, double y) {
        this.x = x;
        this.y = y;
        this.hash = calcHash();
    }

    @Override
    public double distance(double x, double y) {
        double a = this.x - x;
        double b = this.y - y;
        return Math.sqrt(a * a + b * b);
    }

    @Override
    public double distance(Vec2 vec) {
        return distance(vec.getX(), vec.getY());
    }

    @Override
    public Vec2 add(double x, double y) {
        return new ImmutableVec2(this.x + x, this.y + y);
    }

    @Override
    public Vec2 add(Vec2 vec) {
        return add(vec.getX(), vec.getY());
    }

    @Override
    public Vec2 subtract(double x, double y) {
        return new ImmutableVec2(this.x - x, this.y - y);
    }

    @Override
    public Vec2 subtract(Vec2 vec) {
        return subtract(vec.getX(), vec.getY());
    }

    @Override
    public Vec2 divide(double divisor) {
        return new ImmutableVec2(this.x / divisor, this.y / divisor);
    }

    @Override
    public Vec2 multiply(double factor) {
        return new ImmutableVec2(this.x * factor, this.y * factor);
    }

    @Override
    public double magnitude() {
        return Math.hypot(x, y);
    }

    @Override
    public double phi() {
        return Math.atan2(y, x);
    }

    @Override
    public double dotProduct(double x, double y) {
        return this.x * x + this.y * y;
    }

    @Override
    public double dotProduct(Vec2 vec) {
        return dotProduct(vec.getX(), vec.getY());
    }

    @Override
    public MutableVec3 crossProduct(double x, double y) {
        return new MutableVec3(0, 0, this.x * y - this.y * x);
    }

    @Override
    public MutableVec3 crossProduct(Vec2 vec) {
        return crossProduct(vec.getX(), vec.getY());
    }

    @Override
    public Vec2 inverse() {
        return new ImmutableVec2(-x, -y);
    }

    @Override
    public Vec2 normalize() {
        final double mag = magnitude();
        if (mag == 0.0) {
            return new ImmutableVec2(0.0, 0.0);
        }
        return new ImmutableVec2(x / mag, y / mag);
    }
    
    public static Builder builder() {
        return new Builder();
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
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Object-Stuff">
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ImmutableVec2) {
            ImmutableVec2 other = (ImmutableVec2) obj;
            return this.x == other.x && this.y == other.y;
        } else {
            return false;
        }
    }

    private int calcHash() {
        long bits = 7L;
        bits = 31L * bits + Double.doubleToLongBits(x);
        bits = 31L * bits + Double.doubleToLongBits(y);
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

        private Builder() {
            this.bX = 0.0;
            this.bY = 0.0;
        }
        
        public Builder x(double x) {
            this.bX = x;
            return this;
        }
        
        public Builder y(double y) {
            this.bY = y;
            return this;
        }
        
        public Vec2 build() {
            return new ImmutableVec2(bX, bY);
        }
        
    }
    
}
