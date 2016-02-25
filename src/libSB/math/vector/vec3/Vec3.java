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
package libSB.math.vector.vec3;

import libSB.math.vector.swizzling.accessor.AccessorBuilder_Level1;
import libSB.math.vector.swizzling.accessor.SwizzlingAccessor;
import libSB.math.vector.vec.Vec;

/**
 *
 * @author Simon Berndt
 */
public interface Vec3 extends Vec.V3 {

    Vec3 add(double x, double y, double z);

    Vec3 add(Vec3 vec);

    Vec3 subtract(double x, double y, double z);

    Vec3 subtract(Vec3 vec);

    Vec3 multiply(double factor);

    Vec3 divide(double divisor);

    double dotProduct(double x, double y, double z);

    double dotProduct(Vec3 vec);

    Vec3 crossProduct(double x, double y, double z);

    Vec3 crossProduct(Vec3 vec);

    Vec3 inverse();

    double distance(double x, double y, double z);

    double distance(Vec3 vec);

    double theta();

    double phi();

    Vec3 normalize();

    double magnitude();

    default AccessorBuilder_Level1.V3 swizzle() {
        return SwizzlingAccessor.of(this);
    }

    interface Mutable extends Vec3, Vec.Mutable {

        @Override
        Vec3.Mutable add(double x, double y, double z);

        @Override
        Vec3.Mutable add(Vec3 vec);

        @Override
        Vec3.Mutable subtract(double x, double y, double z);

        @Override
        Vec3.Mutable subtract(Vec3 vec);

        @Override
        Vec3.Mutable multiply(double factor);

        @Override
        Vec3.Mutable divide(double divisor);

        @Override
        Vec3.Mutable crossProduct(double x, double y, double z);

        @Override
        Vec3.Mutable crossProduct(Vec3 vec);

        @Override
        Vec3.Mutable inverse();

        Vec3.Mutable inplaceAdd(double x, double y, double z);

        Vec3.Mutable inplaceAdd(Vec3 vec);

        Vec3.Mutable inplaceSubtract(double x, double y, double z);

        Vec3.Mutable inplaceSubtract(Vec3 vec);

        Vec3.Mutable inplaceMultiply(double factor);

        Vec3.Mutable inplaceDivide(double dividend);

        Vec3.Mutable inplaceNormalize();

        Vec3.Mutable inplaceInverse();

        void set(double x, double y, double z);

        void setPolar(double r, double theta, double phi);

        void set(Vec3 vec);

        void setX(double x);

        void setY(double y);

        void setZ(double z);

    }

}
