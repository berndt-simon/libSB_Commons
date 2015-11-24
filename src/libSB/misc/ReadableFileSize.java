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
package libSB.misc;

/**
 *
 * @author Simon Berndt
 */
public final class ReadableFileSize {

    private ReadableFileSize() {
    }

    public static String humanReadableByteCount(long bytes, Base base) {
        if (bytes < base.asNumber) {
            return bytes + " B";
        }
        final int exp = (int) (Math.log(bytes) / Math.log(base.asNumber));
        final String pre = base.prefixes.charAt(exp - 1) + base.postfix;
        return String.format("%.1f %sB", bytes / Math.pow(base.asNumber, exp), pre);
    }

    public static enum Base {

        TEN(1000, "kMGTPE", ""),
        TWO(1024, "KMGTPE", "i");

        private final String prefixes;
        private final String postfix;
        private final int asNumber;

        private Base(int asNumber, String prefixes, String postfix) {
            this.prefixes = prefixes;
            this.postfix = postfix;
            this.asNumber = asNumber;
        }

    }
}
