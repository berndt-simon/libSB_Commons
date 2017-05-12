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

import java.util.Objects;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

/**
 * Util-Class for Curring
 *
 * @author Simon Berndt
 */
public final class StreamUtils {

    private static final Logger LOG = Logger.getLogger(StreamUtils.class.getName());

    private StreamUtils() {
    }

    /**
     * creates a Predidate which returns true if for a given Argument the extracted Value equals the specified Matching-Value. 
     * Equality is deterimined by Objects.equals()
     */
    public static <T, N> Predicate<T> matcher(Function<? super T, ? extends N> extractor, N match) {
        Objects.requireNonNull(extractor);
        return (T t) -> {
            return Objects.equals(match, extractor.apply(t));
        };
    }

    /**
     * creates a Predidate which returns true if for a given Argument the extracted Value equals the specified Matching-Value.
     * Equality is determined by '=='-Operator
     */
    public static <T, N> Predicate<T> identityMatcher(Function<? super T, ? extends N> extractor, N identityMatch) {
        Objects.requireNonNull(extractor);
        return (T t) -> {
            return identityMatch == extractor.apply(t);
        };
    }

    /**
     * Rebuilds a Stream into a Stream of the same type to enable a parallel Processing further on.
     * <p>
     * This is a terminal Operation.
     */
    public static <T> Stream<T> rebuildParallel(Stream<T> stream) {
        final Stream.Builder<T> streamBuilder = Stream.builder();
        stream.forEach(streamBuilder);
        return streamBuilder.build().parallel();
    }

    /**
     * Transforms a BiConsumer into a Consumer in which each Argument is given to the original BiConsumer in pair with a Boolean.TRUE
     */
    public static <T> Consumer<T> applyTrue(BiConsumer<T, Boolean> booleanConsumer) {
        return (T t) -> booleanConsumer.accept(t, Boolean.TRUE);
    }

    /**
     * Transforms a BiConsumer into a Consumer in which each Argument is given to the original BiConsumer in pair with a Boolean.FALSE
     */
    public static <T> Consumer<T> applyFalse(BiConsumer<T, Boolean> booleanConsumer) {
        return (T t) -> booleanConsumer.accept(t, Boolean.FALSE);
    }

    public static <T> Predicate<T> not(Predicate<T> predicate) {
        return predicate.negate();
    }

    /**
     * Transforms a CheckedConsumer into a Consumer. If a Exception occurs while executing the CheckedConsumers Code a RuntimeException will be rethrown.
     */
    public static <T> Consumer<T> uncheckedConsumer(CheckedConsumer<T> consumer) {
        return (T t) -> {
            try {
                consumer.accept(t);
            } catch (Throwable ex) {
                throw new RuntimeException(ex);
            }
        };
    }

    /**
     * Transforms a CheckedConsumer into a Consumer. If a Exception occurs while executing the CheckedConsumers Code the Exception will be catched and logged.
     */
    public static <T> Consumer<T> silentConsumer(CheckedConsumer<T> consumer) {
        return (T t) -> {
            try {
                consumer.accept(t);
            } catch (Throwable ex) {
                LOG.log(Level.FINER, null, ex);
            }
        };
    }

    /**
     * Transforms a CheckedFunction into a Function. If a Exception occurs while executing the CheckedFunctions Code a RuntimeException will be rethrown.
     */
    public static <T, R> Function<T, R> uncheckedFunction(CheckedFunction<T, R> function) {
        return (T t) -> {
            try {
                return function.apply(t);
            } catch (Throwable ex) {
                throw new RuntimeException(ex);
            }
        };
    }

    /**
     * Transforms a CheckedFunction into a Function yielding an Optional Result. If a Exception occurs while executing the CheckedFunctions Code an empty
     * Optional will be returned.
     */
    public static <T, R> Function<T, Optional<R>> optionalFunction(CheckedFunction<T, R> function) {
        return (T t) -> {
            try {
                return Optional.ofNullable(function.apply(t));
            } catch (Throwable ex) {
                return Optional.empty();
            }
        };
    }

    /**
     * Transforms a CheckedBiFunction into a BiFunction. If a Exception occurs while executing the CheckedBiFunctions Code a RuntimeException will be rethrown.
     */
    public static <T1, T2, R> BiFunction<T1, T2, R> uncheckedBiFunction(CheckedBiFunction<T1, T2, R> biFunction) {
        return (T1 t1, T2 t2) -> {
            try {
                return biFunction.apply(t1, t2);
            } catch (Throwable ex) {
                throw new RuntimeException(ex);
            }
        };
    }

    /**
     * Transforms a CheckedBiFunction into a BiFunction yielding an Optional Result. If a Exception occurs while executing the CheckedBiFunctions Code an empty
     * Optional will be returned.
     */
    public static <T1, T2, R> BiFunction<T1, T2, Optional<R>> optionalBiFunction(CheckedBiFunction<T1, T2, R> biFunction) {
        return (T1 t1, T2 t2) -> {
            try {
                return Optional.ofNullable(biFunction.apply(t1, t2));
            } catch (Throwable ex) {
                return Optional.empty();
            }
        };
    }

    /**
     * Transforms a CheckedSupplier into a Supplier. If a Exception occurs while executing the CheckedSuppliers Code a RuntimeException will be rethrown.
     */
    public static <T> Supplier<T> uncheckedSupplier(CheckedSupplier<T> supplier) {
        return () -> {
            try {
                return supplier.get();
            } catch (Throwable ex) {
                throw new RuntimeException(ex);
            }
        };
    }

    /**
     * Transforms a CheckedSupplier into a Supplier yielding an Optional Result. If a Exception occurs while executing the CheckedSuppliers Code an empty
     * Optional will be returned.
     */
    public static <T> Supplier<Optional<T>> optionalSupplier(CheckedSupplier<T> supplier) {
        return () -> {
            try {
                return Optional.ofNullable(supplier.get());
            } catch (Throwable ex) {
                return Optional.empty();
            }
        };
    }

    /**
     * Transforms a CheckedRunnable into a Runnable. If a Exception occurs while executing the CheckedRunnables Code a RuntimeException will be rethrown.
     */
    public static Runnable uncheckedRunnable(CheckedRunnable runnable) {
        return () -> {
            try {
                runnable.run();
            } catch (Throwable ex) {
                throw new RuntimeException(ex);
            }
        };
    }

    /**
     * Transforms a CheckedRunnable into a Runnable. If a Exception occurs while executing the CheckedRunnables Code the Exception will be catched and logged.
     */
    public static Runnable silentRunnable(CheckedRunnable runnable) {
        return () -> {
            try {
                runnable.run();
            } catch (Throwable ex) {
                LOG.log(Level.FINER, null, ex);
            }
        };
    }

    @FunctionalInterface
    public static interface CheckedConsumer<T> {

        void accept(T t) throws Throwable;
    }

    @FunctionalInterface
    public static interface CheckedFunction<T, R> {

        R apply(T t) throws Throwable;
    }

    @FunctionalInterface
    public static interface CheckedBiFunction<T1, T2, R> {

        R apply(T1 t1, T2 t2) throws Throwable;
    }

    @FunctionalInterface
    public static interface CheckedSupplier<T> {

        T get() throws Throwable;
    }

    @FunctionalInterface
    public static interface CheckedRunnable {

        void run() throws Throwable;
    }

}
