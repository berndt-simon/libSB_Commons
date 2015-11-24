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

import java.util.Locale;

/**
 *
 * @author Simon Berndt
 */
public class MutableVec2 implements Vec2.Mutable {

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
    public Vec2.Mutable add(double x, double y) {
        return new MutableVec2(this.x + x, this.y + y);
    }

    @Override
    public Vec2.Mutable add(Vec2 vec) {
        return add(vec.getX(), vec.getY());
    }

    @Override
    public Vec2.Mutable subtract(double x, double y) {
        return new MutableVec2(this.x - x, this.y - y);
    }

    @Override
    public Vec2.Mutable subtract(Vec2 vec) {
        return subtract(vec.getX(), vec.getY());
    }

    @Override
    public Vec2.Mutable divide(double divisor) {
        return new MutableVec2(this.x / divisor, this.y / divisor);
    }

    @Override
    public Vec2.Mutable multiply(double factor) {
        return new MutableVec2(this.x * factor, this.y * factor);
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
    public Mutable inverse() {
        return new MutableVec2(-x, -y);
    }

    @Override
    public Vec2.Mutable normalize() {
        final double mag = magnitude();
        if (mag == 0.0) {
            return new MutableVec2(0.0, 0.0);
        }
        return new MutableVec2(x / mag, y / mag);
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

    //<editor-fold defaultstate="collapsed" desc="Getter/Setter">
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
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof MutableVec2) {
            MutableVec2 other = (MutableVec2) obj;
            return this.x == other.x && this.y == other.y;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        long bits = 7L;
        bits = 31L * bits + Double.doubleToLongBits(x);
        bits = 31L * bits + Double.doubleToLongBits(y);
        return (int) (bits ^ (bits >> 32));
    }
    
    @Override
    public String toString() {
        return String.format(Locale.ROOT, "[%f; %f]", x, y);
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
        
        public Vec2.Mutable build() {
            return new MutableVec2(bX, bY);
        }
        
    }


}
