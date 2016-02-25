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
package libSB.tuple;

import java.util.Objects;

/**
 * Tuple-Class for Multiple-Return Value Szenarios
 * @author Simon Berndt
 */
public final class Tuple<A, B> {

	private final A alpha;
	private final B beta;

	public Tuple(A alpha, B beta) {
		this.alpha = alpha;
		this.beta = beta;
	}

	public A getAlpha() {
		return this.alpha;
	}

	public B getBeta() {
		return this.beta;
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.alpha, this.beta);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Tuple<?, ?> other = (Tuple<?, ?>) obj;
		if (!Objects.equals(this.alpha, other.alpha)) {
			return false;
		}
		return Objects.equals(this.beta, other.beta);
	}

}
