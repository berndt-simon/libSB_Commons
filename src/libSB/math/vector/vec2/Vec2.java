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
package libSB.math.vector.vec2;

import libSB.math.vector.swizzling.accessor.AccessorBuilder_Level1;
import libSB.math.vector.swizzling.accessor.SwizzlingAccessor;
import libSB.math.vector.vec.Vec;
import libSB.math.vector.vec3.Vec3;

/**
 *
 * @author Simon Berndt
 */
public interface Vec2 extends Vec.V2 {

    Vec2 add(double x, double y);

    Vec2 add(Vec2 vec);

    Vec2 subtract(double x, double y);

    Vec2 subtract(Vec2 vec);

    Vec2 multiply(double factor);

    Vec2 divide(double divisor);

    double dotProduct(double x, double y);

    double dotProduct(Vec2 vec);

    Vec3 crossProduct(double x, double y);

    Vec3 crossProduct(Vec2 vec);

    Vec2 inverse();

    double distance(double x, double y);

    double distance(Vec2 vec);

    double magnitude();

    double phi();

    Vec2 normalize();

    default AccessorBuilder_Level1.V2 swizzle() {
        return SwizzlingAccessor.of(this);
    }

    interface Mutable extends Vec2, Vec.Mutable {

        @Override
        Vec2.Mutable add(double x, double y);

        @Override
        Vec2.Mutable add(Vec2 vec);

        @Override
        Vec2.Mutable subtract(double x, double y);

        @Override
        Vec2.Mutable subtract(Vec2 vec);

        @Override
        Vec2.Mutable multiply(double factor);

        @Override
        Vec2.Mutable divide(double divisor);

        @Override
        Vec3.Mutable crossProduct(double x, double y);

        @Override
        Vec3.Mutable crossProduct(Vec2 vec);

        @Override
        Vec2.Mutable inverse();

        @Override
        Vec2.Mutable normalize();

        Vec2.Mutable inplaceAdd(double x, double y);

        Vec2.Mutable inplaceAdd(Vec2 vec);

        Vec2.Mutable inplaceSubtract(double x, double y);

        Vec2.Mutable inplaceSubtract(Vec2 vec);

        Vec2.Mutable inplaceMultiply(double factor);

        Vec2.Mutable inplaceDivide(double dividend);

        Vec2.Mutable inplaceNormalize();

        Vec2.Mutable inplaceInverse();

        void set(double x, double y);

        void setPolar(double r, double phi);

        void set(Vec2 vec);

        void setX(double x);

        void setY(double y);
    }
    
}
