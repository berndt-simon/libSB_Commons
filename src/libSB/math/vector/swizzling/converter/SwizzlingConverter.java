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
import libSB.math.vector.vec2.ImmutableVec2;
import libSB.math.vector.vec2.Vec2;
import libSB.math.vector.vec3.ImmutableVec3;
import libSB.math.vector.vec3.Vec3;
import libSB.math.vector.vec4.ImmutableVec4;
import libSB.math.vector.vec4.Vec4;

/**
 *
 * @author Simon Berndt
 */
public final class SwizzlingConverter {

    private SwizzlingConverter() {
    }

    public static <VI extends Vector.V2> Converter<VI, Vec2> converterWith(
            ToDoubleFunction<VI> xGetter, 
            ToDoubleFunction<VI> yGetter) {
        return (VI vec) -> {
            double x = xGetter.applyAsDouble(vec);
            double y = yGetter.applyAsDouble(vec);
            return new ImmutableVec2(x, y);
        };
    }
    
    public static <VI extends Vector.V2> Converter<VI, Vec3> converterWith(
            ToDoubleFunction<VI> xGetter, 
            ToDoubleFunction<VI> yGetter, 
            ToDoubleFunction<VI> zGetter) {
        return (VI vec) -> {
            double x = xGetter.applyAsDouble(vec);
            double y = yGetter.applyAsDouble(vec);
            double z = zGetter.applyAsDouble(vec);
            return new ImmutableVec3(x, y, z);
        };
    }
    
    public static <VI extends Vector.V2> Converter<VI, Vec4> converterWith(
            ToDoubleFunction<VI> xGetter, 
            ToDoubleFunction<VI> yGetter, 
            ToDoubleFunction<VI> zGetter, 
            ToDoubleFunction<VI> wGetter) {
        return (VI vec) -> {
            double x = xGetter.applyAsDouble(vec);
            double y = yGetter.applyAsDouble(vec);
            double z = zGetter.applyAsDouble(vec);
            double w = wGetter.applyAsDouble(vec);
            return new ImmutableVec4(x, y, z, w);
        };
    }

    public static ConverterBuilder_Level1.V2 x() {
        return new ConverterBuilder_Level1.V2(Vector.V2::getX);
    }

    public static ConverterBuilder_Level1.V2 y() {
        return new ConverterBuilder_Level1.V2(Vector.V2::getY);
    }

    public static ConverterBuilder_Level1.V3 z() {
        return new ConverterBuilder_Level1.V3(Vector.V3::getZ);
    }

    public static ConverterBuilder_Level1.V4 w() {
        return new ConverterBuilder_Level1.V4(Vector.V4::getW);
    }

}
