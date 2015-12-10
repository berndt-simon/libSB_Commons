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
package libSB.math.vector.swizzling.accessor;

import java.util.function.DoubleSupplier;
import libSB.math.vector.vec4.Vec4;
import libSB.math.vector.vec4.Vec4Adapter;

/**
 *
 * @author Simon Berndt
 */
public class AccessorBuilder_Level5 {

    protected final DoubleSupplier xGetter;
    protected final DoubleSupplier yGetter;
    protected final DoubleSupplier zGetter;
    protected final DoubleSupplier wGetter;

    protected AccessorBuilder_Level5(DoubleSupplier xGetter, DoubleSupplier yGetter, DoubleSupplier zGetter, DoubleSupplier wGetter) {
        this.xGetter = xGetter;
        this.yGetter = yGetter;
        this.zGetter = zGetter;
        this.wGetter = wGetter;
    }

    public Vec4 build() {
        return new Vec4Adapter(xGetter, yGetter, zGetter, wGetter);
    }

    public static class VN extends AccessorBuilder_Level5 {

        VN(DoubleSupplier xGetter, DoubleSupplier yGetter, DoubleSupplier zGetter, DoubleSupplier wGetter) {
            super(xGetter, yGetter, zGetter, wGetter);
        }

    }

}
