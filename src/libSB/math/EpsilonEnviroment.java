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
package libSB.math;

import libSB.functionalInterface.DoubleBiPredicate;

/**
 * An Epsilon-Enviroment in which Floating point Values can be tested against equality
 * 
 * @author Simon Berndt
 */
public final class EpsilonEnviroment implements DoubleBiPredicate {

    private final double epsilon;

    public EpsilonEnviroment(double epsilon) {
	this.epsilon = epsilon;
    }

    /**
     * Test if d1 is equal to d2 within the limits of this Epsilon-Enviroment.
     */
    @Override
    public boolean test(double d1, double d2) {
	final double diff = d1 - d2;
	return Math.abs(diff) <= this.epsilon;
    }

}
