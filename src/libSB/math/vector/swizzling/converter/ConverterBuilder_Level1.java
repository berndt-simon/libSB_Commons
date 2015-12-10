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
package libSB.math.vector.swizzling.converter;

import java.util.function.ToDoubleFunction;
import libSB.math.vector.Vector;

/**
 *
 * @author Simon Berndt
 */
public final class ConverterBuilder_Level1 {

    private ConverterBuilder_Level1() {

    }

    public static final class V2 {

        private final ToDoubleFunction<? super Vector.V2> xGetter;

        V2(ToDoubleFunction<? super Vector.V2> xGetter) {
            this.xGetter = xGetter;
        }
        
        public ConverterBuilder_Level2.V2 x() {
            return new ConverterBuilder_Level2.V2(xGetter, Vector.V2::getX);
        }
        
        public ConverterBuilder_Level2.V2 y() {
            return new ConverterBuilder_Level2.V2(xGetter, Vector.V2::getY);
        }
        
        public ConverterBuilder_Level2.V3 z() {
            return new ConverterBuilder_Level2.V3(xGetter, Vector.V3::getZ);
        }
        
        public ConverterBuilder_Level2.V4 w() {
            return new ConverterBuilder_Level2.V4(xGetter, Vector.V4::getW);
        }

        public ToDoubleFunction<? super Vector.V2> build() {
            return xGetter;
        }

    }

    public static final class V3 {

        private final ToDoubleFunction<? super Vector.V3> xGetter;

        V3(ToDoubleFunction<? super Vector.V3> xGetter) {
            this.xGetter = xGetter;
        }
        
        public ConverterBuilder_Level2.V3 x() {
            return new ConverterBuilder_Level2.V3(xGetter, Vector.V3::getX);
        }
        
        public ConverterBuilder_Level2.V3 y() {
            return new ConverterBuilder_Level2.V3(xGetter, Vector.V3::getY);
        }
        
        public ConverterBuilder_Level2.V3 z() {
            return new ConverterBuilder_Level2.V3(xGetter, Vector.V3::getZ);
        }
        
        public ConverterBuilder_Level2.V4 w() {
            return new ConverterBuilder_Level2.V4(xGetter, Vector.V4::getW);
        }

        public ToDoubleFunction<? super Vector.V3> build() {
            return xGetter;
        }

    }

    public static final class V4 {

        private final ToDoubleFunction<? super Vector.V4> xGetter;

        V4(ToDoubleFunction<? super Vector.V4> xGetter) {
            this.xGetter = xGetter;
        }
        
        public ConverterBuilder_Level2.V4 x() {
            return new ConverterBuilder_Level2.V4(xGetter, Vector.V4::getX);
        }
        
        public ConverterBuilder_Level2.V4 y() {
            return new ConverterBuilder_Level2.V4(xGetter, Vector.V4::getY);
        }
        
        public ConverterBuilder_Level2.V4 z() {
            return new ConverterBuilder_Level2.V4(xGetter, Vector.V4::getZ);
        }
        
        public ConverterBuilder_Level2.V4 w() {
            return new ConverterBuilder_Level2.V4(xGetter, Vector.V4::getW);
        }

        public ToDoubleFunction<? super Vector.V4> build() {
            return xGetter;
        }

    }
}
