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
import libSB.math.vector.vec2.Vec2;
import libSB.math.vector.vec3.Vec3;
import libSB.math.vector.vec4.Vec4;

/**
 *
 * @author Simon Berndt
 */
public class AccessorBuilder_Level2 {

    protected final DoubleSupplier xGetter;

    protected AccessorBuilder_Level2(DoubleSupplier xGetter) {
        this.xGetter = xGetter;
    }

    public DoubleSupplier build() {
        return xGetter;
    }

    public static final class V2 extends AccessorBuilder_Level2 {

        private final Vec2 vec;

        V2(Vec2 vec, DoubleSupplier xGetter) {
            super(xGetter);
            this.vec = vec;
        }

        public AccessorBuilder_Level3.V2 x() {
            return new AccessorBuilder_Level3.V2(vec, xGetter, vec::getX);
        }

        public AccessorBuilder_Level3.V2 y() {
            return new AccessorBuilder_Level3.V2(vec, xGetter, vec::getY);
        }

    }

    public static final class V3 extends AccessorBuilder_Level2 {

        private final Vec3 vec;

        V3(Vec3 vec, DoubleSupplier xGetter) {
            super(xGetter);
            this.vec = vec;
        }

        public AccessorBuilder_Level3.V3 x() {
            return new AccessorBuilder_Level3.V3(vec, xGetter, vec::getX);
        }

        public AccessorBuilder_Level3.V3 y() {
            return new AccessorBuilder_Level3.V3(vec, xGetter, vec::getY);
        }

        public AccessorBuilder_Level3.V3 z() {
            return new AccessorBuilder_Level3.V3(vec, xGetter, vec::getZ);
        }

    }

    public static final class V4 extends AccessorBuilder_Level2 {

        private final Vec4 vec;

        V4(Vec4 vec, DoubleSupplier xGetter) {
            super(xGetter);
            this.vec = vec;
        }

        public AccessorBuilder_Level3.V4 x() {
            return new AccessorBuilder_Level3.V4(vec, xGetter, vec::getX);
        }

        public AccessorBuilder_Level3.V4 y() {
            return new AccessorBuilder_Level3.V4(vec, xGetter, vec::getY);
        }

        public AccessorBuilder_Level3.V4 z() {
            return new AccessorBuilder_Level3.V4(vec, xGetter, vec::getZ);
        }

        public AccessorBuilder_Level3.V4 w() {
            return new AccessorBuilder_Level3.V4(vec, xGetter, vec::getW);
        }

    }

}
