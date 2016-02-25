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
package libSB.collections.liteEnumMap;

import java.util.Arrays;
import libSB.collections.keyed.KeyedLong;

/**
 *
 * @author Simon Berndt
 */
final public class LiteEnumLongMap<K extends Enum<K>> implements KeyedLong.Writable<K> {

    private final long[] vals;

    public LiteEnumLongMap(Class<K> keyClass) {
        this.vals = new long[keyClass.getEnumConstants().length];
    }

    @Override
    public void setValue(K key, Long value) {
        this.vals[key.ordinal()] = value;
    }

    @Override
    public void set(K key, long value) {
        this.vals[key.ordinal()] = value;
    }

    public void setAll(long value) {
	Arrays.fill(this.vals, value);
    }

    @Override
    public Long getValue(K key) {
	return this.vals[key.ordinal()];
    }

    @Override
    public long get(K key) {
	return this.vals[key.ordinal()];
    }

}
