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
package libSB.math.vector.swizzling.converter;

import java.util.function.ToDoubleFunction;
import libSB.math.vector.vec.Vec;
import libSB.math.vector.vec3.ImmutableVec3;
import libSB.math.vector.vec3.Vec3;

/**
 *
 * @author Simon Berndt
 */
public final class ConverterBuilder_Level3 {

    private ConverterBuilder_Level3() {
    }

    public static final class V2 {

        private final ToDoubleFunction<? super Vec.V2> xGetter;
        private final ToDoubleFunction<? super Vec.V2> yGetter;
        private final ToDoubleFunction<? super Vec.V2> zGetter;

        V2(ToDoubleFunction<? super Vec.V2> xGetter, ToDoubleFunction<? super Vec.V2> yGetter, ToDoubleFunction<? super Vec.V2> zGetter) {
            this.xGetter = xGetter;
            this.yGetter = yGetter;
            this.zGetter = zGetter;
        }

        public ConverterBuilder_Level4.V2 x() {
            return new ConverterBuilder_Level4.V2(xGetter, yGetter, zGetter, Vec.V2::getX);
        }

        public ConverterBuilder_Level4.V2 y() {
            return new ConverterBuilder_Level4.V2(xGetter, yGetter, zGetter, Vec.V2::getY);
        }

        public ConverterBuilder_Level4.V3 z() {
            return new ConverterBuilder_Level4.V3(xGetter, yGetter, zGetter, Vec.V3::getZ);
        }

        public ConverterBuilder_Level4.V4 w() {
            return new ConverterBuilder_Level4.V4(xGetter, yGetter, zGetter, Vec.V4::getW);
        }

        public Converter<Vec.V2, Vec3> build() {
            return (Vec.V2 vec) -> {
                double x = xGetter.applyAsDouble(vec);
                double y = yGetter.applyAsDouble(vec);
                double z = zGetter.applyAsDouble(vec);
                return new ImmutableVec3(x, y, z);
            };
        }
    }

    public static final class V3 {

        private final ToDoubleFunction<? super Vec.V3> xGetter;
        private final ToDoubleFunction<? super Vec.V3> yGetter;
        private final ToDoubleFunction<? super Vec.V3> zGetter;

        V3(ToDoubleFunction<? super Vec.V3> xGetter, ToDoubleFunction<? super Vec.V3> yGetter, ToDoubleFunction<? super Vec.V3> zGetter) {
            this.xGetter = xGetter;
            this.yGetter = yGetter;
            this.zGetter = zGetter;
        }

        public ConverterBuilder_Level4.V3 x() {
            return new ConverterBuilder_Level4.V3(xGetter, yGetter, zGetter, Vec.V2::getX);
        }

        public ConverterBuilder_Level4.V3 y() {
            return new ConverterBuilder_Level4.V3(xGetter, yGetter, zGetter, Vec.V2::getY);
        }

        public ConverterBuilder_Level4.V3 z() {
            return new ConverterBuilder_Level4.V3(xGetter, yGetter, zGetter, Vec.V3::getZ);
        }

        public ConverterBuilder_Level4.V4 w() {
            return new ConverterBuilder_Level4.V4(xGetter, yGetter, zGetter, Vec.V4::getW);
        }

        public Converter<Vec.V3, Vec3> build() {
            return (Vec.V3 vec) -> {
                double x = xGetter.applyAsDouble(vec);
                double y = yGetter.applyAsDouble(vec);
                double z = zGetter.applyAsDouble(vec);
                return new ImmutableVec3(x, y, z);
            };
        }
    }

    public static final class V4 {

        private final ToDoubleFunction<? super Vec.V4> xGetter;
        private final ToDoubleFunction<? super Vec.V4> yGetter;
        private final ToDoubleFunction<? super Vec.V4> zGetter;

        V4(ToDoubleFunction<? super Vec.V4> xGetter, ToDoubleFunction<? super Vec.V4> yGetter, ToDoubleFunction<? super Vec.V4> zGetter) {
            this.xGetter = xGetter;
            this.yGetter = yGetter;
            this.zGetter = zGetter;
        }

        public ConverterBuilder_Level4.V4 x() {
            return new ConverterBuilder_Level4.V4(xGetter, yGetter, zGetter, Vec.V2::getX);
        }

        public ConverterBuilder_Level4.V4 y() {
            return new ConverterBuilder_Level4.V4(xGetter, yGetter, zGetter, Vec.V2::getY);
        }

        public ConverterBuilder_Level4.V4 z() {
            return new ConverterBuilder_Level4.V4(xGetter, yGetter, zGetter, Vec.V3::getZ);
        }

        public ConverterBuilder_Level4.V4 w() {
            return new ConverterBuilder_Level4.V4(xGetter, yGetter, zGetter, Vec.V4::getW);
        }

        public Converter<Vec.V4, Vec3> build() {
            return (Vec.V4 vec) -> {
                double x = xGetter.applyAsDouble(vec);
                double y = yGetter.applyAsDouble(vec);
                double z = zGetter.applyAsDouble(vec);
                return new ImmutableVec3(x, y, z);
            };
        }
    }

}
