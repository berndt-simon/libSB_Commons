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

import java.util.Objects;
import java.util.function.DoubleSupplier;

/**
 *
 * @author Simon Berndt
 */
public class Vec3Adapter extends AbstractReadOnlyVec3<ImmutableVec3> {

    private final DoubleSupplier xGetter;
    private final DoubleSupplier yGetter;
    private final DoubleSupplier zGetter;

    public Vec3Adapter(DoubleSupplier xGetter, DoubleSupplier yGetter, DoubleSupplier zGetter) {
        this.xGetter = Objects.requireNonNull(xGetter);
        this.yGetter = Objects.requireNonNull(yGetter);
        this.zGetter = Objects.requireNonNull(zGetter);
    }

    @Override
    public double getX() {
        return xGetter.getAsDouble();
    }

    @Override
    public double getY() {
        return yGetter.getAsDouble();
    }

    @Override
    public double getZ() {
        return zGetter.getAsDouble();
    }

    @Override
    protected ImmutableVec3 createVec3(double x, double y, double z) {
        return new ImmutableVec3(x, y, z);
    }

    @Override
    public int hashCode() {
        long bits = 7L;
        bits = 31L * bits + Double.doubleToLongBits(getX());
        bits = 31L * bits + Double.doubleToLongBits(getY());
        bits = 31L * bits + Double.doubleToLongBits(getZ());
        return (int) (bits ^ (bits >> 32));
    }

}
