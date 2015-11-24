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
package libSB.collections.liteEnumMap;

import libSB.collections.keyed.Keyed;

/**
 *
 * @author Simon Berndt
 */
final public class LiteEnumMap<K extends Enum<K>, V> implements Keyed.Writable<K, V> {

    private final Object[] vals;

    public LiteEnumMap(Class<K> keyClass) {
        this.vals = new Object[keyClass.getEnumConstants().length];
    }

    @Override
    public void setValue(K key, V value) {
        this.vals[key.ordinal()] = value;
    }

    @Override
    @SuppressWarnings("unchecked")
    public V getValue(K key) {
	return (V) this.vals[key.ordinal()];
    }

}
