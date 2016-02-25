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

/**
 *
 * @author Simon Berndt
 */
public final class ImmutableVec3 extends AbstractReadOnlyVec3 {

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
    protected Vec3 createVec3(double x, double y, double z) {
        return new ImmutableVec3(x, y, z);
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
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Object-Stuff">
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

    public static class Builder extends AbstractVec3Builder<ImmutableVec3> {

        @Override
        protected ImmutableVec3 createVec3(double x, double y, double z) {
            return new ImmutableVec3(x, y, z);
        }

    }

}
