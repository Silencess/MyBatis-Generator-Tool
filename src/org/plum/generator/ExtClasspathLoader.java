package org.plum.generator;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * 根据properties中配置的路径把jar和配置文件加载到classpath中。
 * @author jnbzwm
 *
 */
public final class ExtClasspathLoader {
    /** URLClassLoader的addURL方法 */
    private static Method addURL = initAddMethod();
 
    private static URLClassLoader classloader = (URLClassLoader) ClassLoader.getSystemClassLoader();
 
    /**
     * 初始化addUrl 方法.
     * @return 可访问addUrl方法的Method对象
     */
    private static Method initAddMethod() {
        try {
            Method add = URLClassLoader.class.getDeclaredMethod("addURL", new Class[] { URL.class });
            add.setAccessible(true);
            return add;
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
 
    /**
     * 通过filepath加载文件到classpath。
     * @param filePath 文件路径
     * @return URL
     * @throws Exception 异常
     */
    public static void add2Path(File file) {
        try {
            addURL.invoke(classloader, new Object[] { file.toURI().toURL() });
        }
        catch (Exception e) {
        	System.out.println(e.getMessage());
        }
    }
}
