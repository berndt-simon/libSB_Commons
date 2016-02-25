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

import java.util.function.DoubleSupplier;
import java.util.function.DoubleUnaryOperator;

/**
 *
 * @author Simon Berndt
 */
public final class ReRange {

    private ReRange() {
    }

    /**
     * Creates a DoubleUnaryOperator which maps a given Value from an Input-Domain to an Output-Range specified by this Function-Call.
     */
    public static DoubleUnaryOperator staticReRange(double inputDomain0, double inputDomain1, double outputRange0, double outputRange1) {
        return new ReRange.Static(inputDomain0, inputDomain1, outputRange0, outputRange1);
    }

    /**
     * Creates a DoubleUnaryOperator which maps a given Value from an Input-Domain to an Output-Range specified by this Function-Call.
     * <p> 
     * For each Call the Limits of the Input-Domain and the Output-Range are fetched from the given Suppliers.
     */
    public static DoubleUnaryOperator dynamicReRange(DoubleSupplier inputDomain0, DoubleSupplier inputDomain1, DoubleSupplier outputRange0, DoubleSupplier outputRange1) {
        return new ReRange.Dynamic(inputDomain0, inputDomain1, outputRange0, outputRange1);
    }

    /**
     * Interpolates between an Input-Domain given by inputDomain0 and inputDomain1 to an Output-Range given by outputRange0 and outputRange1
     */
    public static float interpolate(float inputDomain0, float inputDomain1, float outputRange0, float outputRange1, float value) {
        return (((outputRange1 - outputRange0) * (value - inputDomain0)) / (inputDomain1 - inputDomain0)) + outputRange0;
    }

    /**
     * Interpolates between an Input-Domain given by inputDomain0 and inputDomain1 to an Output-Range given by outputRange0 and outputRange1
     */
    public static double interpolate(double inputDomain0, double inputDomain1, double outputRange0, double outputRange1, double value) {
        return (((outputRange1 - outputRange0) * (value - inputDomain0)) / (inputDomain1 - inputDomain0)) + outputRange0;
    }

    /**
     * Interpolates between an Input-Domain of 0.0f and 1.0f to an Output-Range given by outputRange0 and outputRange1
     */
    public static float interpolate(float outputRange0, float outputRange1, float value) {
        return (((outputRange1 - outputRange0) * value)) + outputRange0;
    }

    /**
     * Interpolates between an Input-Domain of 0.0 and 1.0 to an Output-Range given by outputRange0 and outputRange1
     */
    public static double interpolate(double outputRange0, double outputRange1, double value) {
        return (((outputRange1 - outputRange0) * value)) + outputRange0;
    }

    public ReRange(double baseInsertStart, double baseInsertEnd, double insertStart, double insertEnd) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static class Static implements DoubleUnaryOperator {

        private final double inputDomain0;
        private final double outputRange0;

        private final double inputDomain1;
        private final double outputRange1;

        public Static(double inputDomain0, double inputDomain1, double outputRange0, double outputRange1) {
            this.inputDomain0 = inputDomain0;
            this.outputRange0 = outputRange0;
            this.inputDomain1 = inputDomain1;
            this.outputRange1 = outputRange1;
        }

        @Override
        public double applyAsDouble(double t) {
            return interpolate(inputDomain0, inputDomain1, outputRange0, outputRange1, t);
        }
    }

    private static class Dynamic implements DoubleUnaryOperator {

        private final DoubleSupplier inputDomain0;
        private final DoubleSupplier outputRange0;

        private final DoubleSupplier inputDomain1;
        private final DoubleSupplier outputRange1;

        public Dynamic(DoubleSupplier inputDomain0, DoubleSupplier inputDomain1, DoubleSupplier outputRange0, DoubleSupplier outputRange1) {
            this.inputDomain0 = inputDomain0;
            this.outputRange0 = outputRange0;
            this.inputDomain1 = inputDomain1;
            this.outputRange1 = outputRange1;
        }

        @Override
        public double applyAsDouble(double t) {
            return interpolate(inputDomain0.getAsDouble(), inputDomain1.getAsDouble(), outputRange0.getAsDouble(), outputRange1.getAsDouble(), t);
        }
    }

}
