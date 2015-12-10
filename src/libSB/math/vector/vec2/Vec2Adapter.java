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
package libSB.math.vector.vec2;

import java.util.Objects;
import java.util.function.DoubleSupplier;
import libSB.math.vector.vec3.ImmutableVec3;

/**
 *
 * @author Simon Berndt
 */
public class Vec2Adapter extends AbstractReadOnlyVec2<ImmutableVec2, ImmutableVec3> {
    
    private final DoubleSupplier xGetter;
    private final DoubleSupplier yGetter;

    public Vec2Adapter(DoubleSupplier xGetter, DoubleSupplier yGetter) {
        this.xGetter = Objects.requireNonNull(xGetter);
        this.yGetter = Objects.requireNonNull(yGetter);
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
    protected ImmutableVec2 createVec2(double x, double y) {
        return new ImmutableVec2(x, y);
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
        return (int) (bits ^ (bits >> 32));
    }

}
