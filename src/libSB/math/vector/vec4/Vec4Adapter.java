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
package libSB.math.vector.vec4;

import java.util.Objects;
import java.util.function.DoubleSupplier;

/**
 *
 * @author Simon Berndt
 */
public class Vec4Adapter extends AbstractReadOnlyVec4<ImmutableVec4> {

    private final DoubleSupplier xGetter;
    private final DoubleSupplier yGetter;
    private final DoubleSupplier zGetter;
    private final DoubleSupplier wGetter;

    public Vec4Adapter(DoubleSupplier xGetter, DoubleSupplier yGetter, DoubleSupplier zGetter, DoubleSupplier wGetter) {
        this.xGetter = Objects.requireNonNull(xGetter);
        this.yGetter = Objects.requireNonNull(yGetter);
        this.zGetter = Objects.requireNonNull(zGetter);
        this.wGetter = Objects.requireNonNull(wGetter);
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
    public double getW() {
        return wGetter.getAsDouble();
    }

    @Override
    protected ImmutableVec4 createVec4(double x, double y, double z, double w) {
        return new ImmutableVec4(x, y, z, w);
    }

    @Override
    public int hashCode() {
        long bits = 3;
        bits = 97L * bits + Double.doubleToLongBits(getX());
        bits = 97L * bits + Double.doubleToLongBits(getY());
        bits = 97L * bits + Double.doubleToLongBits(getZ());
        bits = 97L * bits + Double.doubleToLongBits(getW());
        return (int) (bits ^ (bits >> 32));
    }

}
