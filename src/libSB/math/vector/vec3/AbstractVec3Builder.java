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

/**
 *
 * @author Simon Berndt
 */
abstract class AbstractVec3Builder<V3 extends Vec3> {
    
    protected double bX;
    protected double bY;
    protected double bZ;

    AbstractVec3Builder() {
        this.bX = 0.0;
        this.bY = 0.0;
        this.bZ = 0.0;
    }

    public final AbstractVec3Builder x(double x) {
        this.bX = x;
        return this;
    }

    public final AbstractVec3Builder y(double y) {
        this.bY = y;
        return this;
    }
    
    public final AbstractVec3Builder z(double z) {
        this.bZ = z;
        return this;
    }

    public V3 build() {
        return createVec3(bX, bY, bZ);
    }

    protected abstract V3 createVec3(double x, double y, double z);
    
}
