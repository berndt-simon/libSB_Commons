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
package libSB.collections.primitiveSet;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 *
 * @author Simon Berndt
 */
final public class ImmutableIntSet<TypeHint> {

    // Sorted Array of elements contained by this set - Immutable
    private final int[] elements;

    public ImmutableIntSet(IntStream elements) {
	this.elements = elements.distinct().sorted().toArray();
    }

    private ImmutableIntSet() {
	this.elements = new int[0];
    }

    private ImmutableIntSet(int i) {
	this.elements = new int[] {i};
    }

    public static <T> ImmutableIntSet<T> empty() {
	return new ImmutableIntSet<>();
    }

    public static <TypeHint> Builder<TypeHint> builder() {
	return new Builder<>();
    }

    public boolean isEmpty() {
	return this.elements.length == 0;
    }

    public int size() {
	return this.elements.length;
    }

    public boolean contains(int i) {
	return Arrays.binarySearch(this.elements, i) >= 0;
    }

    public boolean notContains(int i) {
	return Arrays.binarySearch(this.elements, i) < 0;
    }

    public ImmutableIntSet<TypeHint> union(ImmutableIntSet<? super TypeHint> set) {
	return new ImmutableIntSet<>(IntStream.concat(this.stream(), set.stream()));
    }

    public ImmutableIntSet<TypeHint> intersection(ImmutableIntSet<? super TypeHint> set) {
	return new ImmutableIntSet<>(this.stream().filter(set::contains));
    }

    public ImmutableIntSet<TypeHint> complement(ImmutableIntSet<? super TypeHint> domain) {
	return new ImmutableIntSet<>(domain.stream().filter(this::notContains));
    }

    public IntStream stream() {
	return Arrays.stream(this.elements);
    }

    public static class Builder<TypeHint> {

	private final IntStream.Builder internalBuilder;

	public Builder() {
	    this.internalBuilder = IntStream.builder();
	}

	public void add(IntStream streamOfElements) {
	    streamOfElements.forEach(this.internalBuilder);
	}

	public void add(int element) {
		this.internalBuilder.accept(element);
	}

	public void add(int... elements) {
	    for (final int element : elements) {
		    this.internalBuilder.accept(element);
	    }
	}

	public ImmutableIntSet<TypeHint> build() {
	    return new ImmutableIntSet<>(this.internalBuilder.build());
	}

    }

}
