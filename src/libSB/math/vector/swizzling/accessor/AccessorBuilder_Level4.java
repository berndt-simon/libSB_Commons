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
package libSB.math.vector.swizzling.accessor;

import java.util.function.DoubleSupplier;
import libSB.math.vector.vec2.Vec2;
import libSB.math.vector.vec3.Vec3;
import libSB.math.vector.vec3.Vec3Adapter;
import libSB.math.vector.vec4.Vec4;

/**
 *
 * @author Simon Berndt
 */
public class AccessorBuilder_Level4 {

    protected final DoubleSupplier xGetter;
    protected final DoubleSupplier yGetter;
    protected final DoubleSupplier zGetter;

    protected AccessorBuilder_Level4(DoubleSupplier xGetter, DoubleSupplier yGetter, DoubleSupplier zGetter) {
        this.xGetter = xGetter;
        this.yGetter = yGetter;
        this.zGetter = zGetter;
    }

    public Vec3 build() {
        return new Vec3Adapter(xGetter, yGetter, zGetter);
    }

    public static final class V2 extends AccessorBuilder_Level4 {

        private final Vec2 vec;

        V2(Vec2 vec, DoubleSupplier xGetter, DoubleSupplier yGetter, DoubleSupplier zGetter) {
            super(xGetter, yGetter, zGetter);
            this.vec = vec;
        }

        public AccessorBuilder_Level5.VN x() {
            return new AccessorBuilder_Level5.VN(xGetter, yGetter, zGetter, vec::getX);
        }

        public AccessorBuilder_Level5.VN y() {
            return new AccessorBuilder_Level5.VN(xGetter, yGetter, zGetter, vec::getY);
        }

    }

    public static final class V3 extends AccessorBuilder_Level4 {

        private final Vec3 vec;

        public V3(Vec3 vec, DoubleSupplier xGetter, DoubleSupplier yGetter, DoubleSupplier zGetter) {
            super(xGetter, yGetter, zGetter);
            this.vec = vec;
        }

        public AccessorBuilder_Level5.VN x() {
            return new AccessorBuilder_Level5.VN(xGetter, yGetter, zGetter, vec::getX);
        }

        public AccessorBuilder_Level5.VN y() {
            return new AccessorBuilder_Level5.VN(xGetter, yGetter, zGetter, vec::getY);
        }

        public AccessorBuilder_Level5.VN z() {
            return new AccessorBuilder_Level5.VN(xGetter, yGetter, zGetter, vec::getZ);
        }

    }

    public static final class V4 extends AccessorBuilder_Level4 {

        private final Vec4 vec;

        public V4(Vec4 vec, DoubleSupplier xGetter, DoubleSupplier yGetter, DoubleSupplier zGetter) {
            super(xGetter, yGetter, zGetter);
            this.vec = vec;
        }

        public AccessorBuilder_Level5.VN x() {
            return new AccessorBuilder_Level5.VN(xGetter, yGetter, zGetter, vec::getX);
        }

        public AccessorBuilder_Level5.VN y() {
            return new AccessorBuilder_Level5.VN(xGetter, yGetter, zGetter, vec::getY);
        }

        public AccessorBuilder_Level5.VN z() {
            return new AccessorBuilder_Level5.VN(xGetter, yGetter, zGetter, vec::getZ);
        }

        public AccessorBuilder_Level5.VN w() {
            return new AccessorBuilder_Level5.VN(xGetter, yGetter, zGetter, vec::getW);
        }

    }

}
