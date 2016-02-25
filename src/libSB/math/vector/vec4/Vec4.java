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

import libSB.math.vector.swizzling.accessor.AccessorBuilder_Level1;
import libSB.math.vector.swizzling.accessor.SwizzlingAccessor;
import libSB.math.vector.vec.Vec;

/**
 *
 * @author Simon Berndt
 */
public interface Vec4 extends Vec.V4 {

    Vec4 add(double x, double y, double z, double w);

    Vec4 add(Vec4 vec);

    Vec4 subtract(double x, double y, double z, double w);

    Vec4 subtract(Vec4 vec);

    Vec4 multiply(double factor);

    Vec4 divide(double divisor);

    double dotProduct(double x, double y, double z, double w);

    double dotProduct(Vec4 vec);

    Vec4 inverse();

    double distance(double x, double y, double z, double w);

    double distance(Vec4 vec);

    Vec4 normalize();

    double magnitude();

    default AccessorBuilder_Level1.V4 swizzle() {
        return SwizzlingAccessor.of(this);
    }

    interface Mutable extends Vec4, Vec.Mutable {

        @Override
        Vec4.Mutable add(double x, double y, double z, double w);

        @Override
        Vec4.Mutable add(Vec4 vec);

        @Override
        Vec4.Mutable subtract(double x, double y, double z, double w);

        @Override
        Vec4.Mutable subtract(Vec4 vec);

        @Override
        Vec4.Mutable multiply(double factor);

        @Override
        Vec4.Mutable divide(double divisor);

        @Override
        Vec4.Mutable inverse();

        Vec4.Mutable inplaceAdd(double x, double y, double z, double w);

        Vec4.Mutable inplaceAdd(Vec4 vec);

        Vec4.Mutable inplaceSubtract(double x, double y, double z, double w);

        Vec4.Mutable inplaceSubtract(Vec4 vec);

        Vec4.Mutable inplaceMultiply(double factor);

        Vec4.Mutable inplaceDivide(double dividend);

        Vec4.Mutable inplaceNormalize();

        Vec4.Mutable inplaceInverse();

        void set(double x, double y, double z, double w);

        void set(Vec4 vec);

        void setX(double x);

        void setY(double y);

        void setZ(double z);

        void setW(double w);

    }

}
