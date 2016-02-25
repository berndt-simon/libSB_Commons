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
package libSB.streamAPI;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * Util-Class for Curring
 *
 * @author Simon Berndt
 */
public final class CurringUtil {

    private CurringUtil() {
    }

    public static <I, O> Supplier<O> captureArgument(Function<I, O> function, I argument) {
        return () -> {
            return function.apply(argument);
        };
    }

    public static <I1, I2> void run(Stream<I1> items, Stream<I2> arguments, Function<I1, Consumer<I2>> curryFunction) {
        items.map(curryFunction).forEach(arguments::forEach);
    }
    
    public static <T> Consumer<T> justCall(Runnable r) {
        return (T t) -> {
            r.run();
        };
    }

    /**
     * Transforms a BiFunction to another BiFunction with reveresed Parameters. To be used in Combination with curryBiFunction.
     */
    public static <I1, I2, O> BiFunction<I2, I1, O> reverseBiFunction(BiFunction<I1, I2, O> biFunction) {
        return (I2 b, I1 a) -> biFunction.apply(a, b);
    }

    /**
     * Transforms a BiFunction to another BiConsumer with reveresed Parameters. To be used in Combination with curryBiConsumer.
     */
    public static <I1, I2> BiConsumer<I2, I1> reverseBiConsumer(BiConsumer<I1, I2> biConsumer) {
        return (I2 b, I1 a) -> biConsumer.accept(a, b);
    }

    /**
     * Transforms a BiFunction into a Function which takes the first Parameter and returns a Funtion which takes the second Parameter and returns the final
     * Result.
     */
    public static <I1, I2, O> Function<I1, Function<I2, O>> curryBiFunction(BiFunction<I1, I2, O> function) {
        return (I1 a) -> (I2 b) -> function.apply(a, b);
    }

    /**
     * Transforms a BiConsumer into a Consumer which takes the first Parameter and returns a Consumer which takes the second Parameter.
     */
    public static <I1, I2> Function<I1, Consumer<I2>> curryBiConsumer(BiConsumer<I1, I2> consumer) {
        return (I1 a) -> (I2 b) -> consumer.accept(a, b);
    }

    public static <I1, I2, O> BiFunction<I1, I2, O> uncurryBiFunction(Function<I1, Function<I2, O>> curryFunction) {
        return (I1 a, I2 b) -> curryFunction.apply(a).apply(b);
    }

    public static <I1, I2> BiConsumer<I1, I2> uncurryBiConsumer(Function<I1, Consumer<I2>> curryConsumer) {
        return (I1 a, I2 b) -> curryConsumer.apply(a).accept(b);
    }
}
