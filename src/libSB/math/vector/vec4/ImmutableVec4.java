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
public final class ImmutableVec4 extends AbstractReadOnlyVec4 {

    public static final Vec4 ORIGIN = new ImmutableVec4();

    private final double x;
    private final double y;
    private final double z;
    private final double w;
    private final int hash;

    public ImmutableVec4() {
        this(0.0, 0.0, 0.0, 0.0);
    }

    public ImmutableVec4(Vec4 vec) {
        this(vec.getX(), vec.getY(), vec.getZ(), vec.getW());
    }

    public ImmutableVec4(double x, double y, double z, double w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
        this.hash = calcHash();
    }

    @Override
    protected Vec4 createVec4(double x, double y, double z, double w) {
        return new ImmutableVec4(x, y, z, w);
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

    @Override
    public double getZ() {
        return z;
    }

    @Override
    public double getW() {
        return w;
    }

//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Object-Stuff">
    private int calcHash() {
        long bits = 3;
        bits = 97L * bits + Double.doubleToLongBits(getX());
        bits = 97L * bits + Double.doubleToLongBits(getY());
        bits = 97L * bits + Double.doubleToLongBits(getZ());
        bits = 97L * bits + Double.doubleToLongBits(getW());
        return (int) (bits ^ (bits >> 32));
    }

    @Override
    public int hashCode() {
        return hash;
    }
//</editor-fold>

    public static class Builder extends AbstractVec4Builder<ImmutableVec4> {

        @Override
        protected ImmutableVec4 createVec4(double x, double y, double z, double w) {
            return new ImmutableVec4(x, y, z, w);
        }

    }

}
