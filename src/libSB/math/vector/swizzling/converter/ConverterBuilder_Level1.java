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

/**
 *
 * @author Simon Berndt
 */
public final class ConverterBuilder_Level1 {

    private ConverterBuilder_Level1() {

    }

    public static final class V2 {

        private final ToDoubleFunction<? super Vec.V2> xGetter;

        V2(ToDoubleFunction<? super Vec.V2> xGetter) {
            this.xGetter = xGetter;
        }
        
        public ConverterBuilder_Level2.V2 x() {
            return new ConverterBuilder_Level2.V2(xGetter, Vec.V2::getX);
        }
        
        public ConverterBuilder_Level2.V2 y() {
            return new ConverterBuilder_Level2.V2(xGetter, Vec.V2::getY);
        }
        
        public ConverterBuilder_Level2.V3 z() {
            return new ConverterBuilder_Level2.V3(xGetter, Vec.V3::getZ);
        }
        
        public ConverterBuilder_Level2.V4 w() {
            return new ConverterBuilder_Level2.V4(xGetter, Vec.V4::getW);
        }

        public ToDoubleFunction<? super Vec.V2> build() {
            return xGetter;
        }

    }

    public static final class V3 {

        private final ToDoubleFunction<? super Vec.V3> xGetter;

        V3(ToDoubleFunction<? super Vec.V3> xGetter) {
            this.xGetter = xGetter;
        }
        
        public ConverterBuilder_Level2.V3 x() {
            return new ConverterBuilder_Level2.V3(xGetter, Vec.V3::getX);
        }
        
        public ConverterBuilder_Level2.V3 y() {
            return new ConverterBuilder_Level2.V3(xGetter, Vec.V3::getY);
        }
        
        public ConverterBuilder_Level2.V3 z() {
            return new ConverterBuilder_Level2.V3(xGetter, Vec.V3::getZ);
        }
        
        public ConverterBuilder_Level2.V4 w() {
            return new ConverterBuilder_Level2.V4(xGetter, Vec.V4::getW);
        }

        public ToDoubleFunction<? super Vec.V3> build() {
            return xGetter;
        }

    }

    public static final class V4 {

        private final ToDoubleFunction<? super Vec.V4> xGetter;

        V4(ToDoubleFunction<? super Vec.V4> xGetter) {
            this.xGetter = xGetter;
        }
        
        public ConverterBuilder_Level2.V4 x() {
            return new ConverterBuilder_Level2.V4(xGetter, Vec.V4::getX);
        }
        
        public ConverterBuilder_Level2.V4 y() {
            return new ConverterBuilder_Level2.V4(xGetter, Vec.V4::getY);
        }
        
        public ConverterBuilder_Level2.V4 z() {
            return new ConverterBuilder_Level2.V4(xGetter, Vec.V4::getZ);
        }
        
        public ConverterBuilder_Level2.V4 w() {
            return new ConverterBuilder_Level2.V4(xGetter, Vec.V4::getW);
        }

        public ToDoubleFunction<? super Vec.V4> build() {
            return xGetter;
        }

    }
}
