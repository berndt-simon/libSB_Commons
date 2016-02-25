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
import libSB.math.vector.vec4.ImmutableVec4;
import libSB.math.vector.vec4.Vec4;

/**
 *
 * @author Simon Berndt
 */
public final class ConverterBuilder_Level4 {

    private ConverterBuilder_Level4() {
    }

    public static final class V2 {

        private final ToDoubleFunction<? super Vec.V2> xGetter;
        private final ToDoubleFunction<? super Vec.V2> yGetter;
        private final ToDoubleFunction<? super Vec.V2> zGetter;
        private final ToDoubleFunction<? super Vec.V2> wGetter;

        V2(ToDoubleFunction<? super Vec.V2> xGetter, ToDoubleFunction<? super Vec.V2> yGetter, ToDoubleFunction<? super Vec.V2> zGetter, ToDoubleFunction<? super Vec.V2> wGetter) {
            this.xGetter = xGetter;
            this.yGetter = yGetter;
            this.zGetter = zGetter;
            this.wGetter = wGetter;
        }

        public Converter<Vec.V2, Vec4> build() {
            return (Vec.V2 vec) -> {
                double x = xGetter.applyAsDouble(vec);
                double y = yGetter.applyAsDouble(vec);
                double z = zGetter.applyAsDouble(vec);
                double w = wGetter.applyAsDouble(vec);
                return new ImmutableVec4(x, y, z, w);
            };
        }
    }

    public static final class V3 {

        private final ToDoubleFunction<? super Vec.V3> xGetter;
        private final ToDoubleFunction<? super Vec.V3> yGetter;
        private final ToDoubleFunction<? super Vec.V3> zGetter;
        private final ToDoubleFunction<? super Vec.V3> wGetter;

        V3(ToDoubleFunction<? super Vec.V3> xGetter, ToDoubleFunction<? super Vec.V3> yGetter, ToDoubleFunction<? super Vec.V3> zGetter, ToDoubleFunction<? super Vec.V3> wGetter) {
            this.xGetter = xGetter;
            this.yGetter = yGetter;
            this.zGetter = zGetter;
            this.wGetter = wGetter;
        }
        
        public Converter<Vec.V3, Vec4> build() {
            return (Vec.V3 vec) -> {
                double x = xGetter.applyAsDouble(vec);
                double y = yGetter.applyAsDouble(vec);
                double z = zGetter.applyAsDouble(vec);
                double w = wGetter.applyAsDouble(vec);
                return new ImmutableVec4(x, y, z, w);
            };
        }
    }

    public static final class V4 {

        private final ToDoubleFunction<? super Vec.V4> xGetter;
        private final ToDoubleFunction<? super Vec.V4> yGetter;
        private final ToDoubleFunction<? super Vec.V4> zGetter;
        private final ToDoubleFunction<? super Vec.V4> wGetter;

        V4(ToDoubleFunction<? super Vec.V4> xGetter, ToDoubleFunction<? super Vec.V4> yGetter, ToDoubleFunction<? super Vec.V4> zGetter, ToDoubleFunction<? super Vec.V4> wGetter) {
            this.xGetter = xGetter;
            this.yGetter = yGetter;
            this.zGetter = zGetter;
            this.wGetter = wGetter;
        }

        public Converter<Vec.V4, Vec4> build() {
            return (Vec.V4 vec) -> {
                double x = xGetter.applyAsDouble(vec);
                double y = yGetter.applyAsDouble(vec);
                double z = zGetter.applyAsDouble(vec);
                double w = wGetter.applyAsDouble(vec);
                return new ImmutableVec4(x, y, z, w);
            };
        }
    }

}
