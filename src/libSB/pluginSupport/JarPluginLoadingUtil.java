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
package libSB.pluginSupport;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Collections;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Util-Class to ease Loading of Plugins in Form of external Jars
 * @author Simon Berndt
 */
public final class JarPluginLoadingUtil {

    private static final Logger LOG = Logger.getLogger(JarPluginLoadingUtil.class.getName());
    private static final ClassLoader SYSTEM_CLASSLOADER = ClassLoader.getSystemClassLoader();

    // Hardcode this for now
    private static final String JAR_FILE_ENDING = ".jar";

    private JarPluginLoadingUtil() {
    }

    /**
     * Checks if a given Path looks like it represents a Jar-File
     */
    public static boolean looksLikeJarFile(Path path) {
	return path.getFileName().toString().toLowerCase(Locale.ROOT).endsWith(JAR_FILE_ENDING);
    }

    
    public static <T> Iterable<Class<? extends T>> loadFromJarFile(Path jarFile, Class<T> pluginMasterClass) {
	LOG.log(Level.FINE, "Look into potential Plugin-Jar: \'{0}\'", jarFile.toString());
	try (URLClassLoader urlClassLoader = new URLClassLoader(asArray(jarFile.toUri().toURL()), SYSTEM_CLASSLOADER)) {
	    try (FileSystem jarFileSystem = FileSystems.newFileSystem(jarFile, null)) {
		return PluginLoadingUtil.loadFromFileSystem(jarFileSystem, urlClassLoader, pluginMasterClass);
	    } catch (IOException e) {
                LOG.log(Level.SEVERE, null, e);
            }
	} catch (IOException ex) {
	    LOG.log(Level.SEVERE, null, ex);
	}
        return Collections.emptyList();
    }

    private static URL[] asArray(URL... urls) {
	return urls;
    }


}
