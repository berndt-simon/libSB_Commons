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

import java.util.BitSet;
import libSB.collections.keyed.KeyedBoolean;

/**
 *
 * @author Simon Berndt
 */
final public class LiteEnumBooleanMap<K extends Enum<K>> implements KeyedBoolean.Writable<K> {

    private final BitSet vals;
    private final int length;

    public LiteEnumBooleanMap(Class<K> keyClass) {
	this.length = keyClass.getEnumConstants().length;
        this.vals = new BitSet(this.length);
    }

    @Override
    public void setValue(K key, Boolean value) {
        this.vals.set(key.ordinal(), value);
    }

    @Override
    public void set(K key, boolean value) {
        this.vals.set(key.ordinal(), value);
    }

    public void setAll(boolean value) {
        this.vals.set(0, this.length - 1, value);
    }

    @Override
    public Boolean getValue(K key) {
	return this.vals.get(key.ordinal());
    }

    @Override
    public boolean get(K key) {
	return this.vals.get(key.ordinal());
    }

}
