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
package libSB.pluginSupport;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Locale;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import javax.tools.JavaFileObject;
import libSB.streamAPI.StreamUtils;

/**
 *
 * @author Simon Berndt
 */
public final class PluginLoadingUtil {

    private static final Logger LOG = Logger.getLogger(PluginLoadingUtil.class.getName());

    private static final String CLASS_FILE_ENDING = JavaFileObject.Kind.CLASS.extension.toLowerCase(Locale.ROOT);

    private PluginLoadingUtil() {
    }

    /**
     * Checks if a given Path looks like it represents a Class-File
     */
    public static boolean looksLikeClassFile(Path path) {
        String fileName = path.getFileName().toString();
        return fileName.toLowerCase(Locale.ROOT).endsWith(CLASS_FILE_ENDING);
    }

    /**
     * Extracts the actual Class-Name from the given Path which should be a Path to a Class-File
     * <p>
     * If the given Path does not discribe a Class-File the result may be completly unusable
     */
    public static String extractClassName(Path path) {
        String className = path.toString();
        return className.substring(1, className.length() - CLASS_FILE_ENDING.length()).replace('/', '.');
    }

    /**
     * Returns an Optional Instance of the given Class. To obtain a Instance the No-Args Constructor is called if this kind of Constuctor exists.
     */
    public static <T> Optional<T> newInstanceOf(Class<T> aClass) {
        return noArgConstructorOf(aClass).flatMap(PluginLoadingUtil::newParameterlessInstanceOf);
    }

    /**
     * Returns an Optional Constructor-Instance of the given Class which takes no Paramters. If no such Constructor exists an empty Optional is returned.
     */
    public static <T> Optional<Constructor<T>> noArgConstructorOf(Class<T> aClass) {
        try {
            return Optional.of(aClass.getConstructor());
        } catch (NoSuchMethodException | SecurityException ex) {
            return Optional.empty();
        }
    }

    /**
     * Returns an Optional Instance of the given Class. If an empty Optional is returned.
     */
    public static <T> Optional<T> newParameterlessInstanceOf(Constructor<T> aConstructor) {
        try {
            return Optional.of(aConstructor.newInstance());
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            return Optional.empty();
        }
    }

    public static <T> void loadFromFileSystem(FileSystem jarFileSystem, ClassLoader jarClassLoader, Class<T> pluginMasterClass, Collection<Class<? extends T>> target) {
        StreamSupport.stream(jarFileSystem.getRootDirectories().spliterator(), false).forEach((Path rootDirectory) -> {
            try (Stream<Path> fileTreeStream = Files.walk(rootDirectory)) {
                fileTreeStream
                        .filter(Files::isReadable)
                        .filter(Files::isRegularFile)
                        .filter(PluginLoadingUtil::looksLikeClassFile)
                        .forEach(StreamUtils.silentConsumer((Path path) -> {
                            LOG.log(Level.FINER, "Jar-Contend: {0}", path.toString());
                            String className = PluginLoadingUtil.extractClassName(path);
                            Class<?> loadedClass = jarClassLoader.loadClass(className);
                            LOG.log(Level.FINER, "Loaded Class: {0}", loadedClass.getName());
                            if (pluginMasterClass.isAssignableFrom(loadedClass)) {
                                LOG.log(Level.FINE, "{0} is a Plugin-Class", loadedClass.getName());
                                target.add(loadedClass.asSubclass(pluginMasterClass));
                            } else {
                                LOG.log(Level.FINER, "{0} is not a Plugin-Class", loadedClass.getName());
                            }
                        }));
            } catch (IOException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        });
    }

}
