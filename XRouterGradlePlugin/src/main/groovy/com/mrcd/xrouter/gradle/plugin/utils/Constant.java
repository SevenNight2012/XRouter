package com.mrcd.xrouter.gradle.plugin.utils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Constant {

    /**
     * XRouter名称
     */
    public static final String XROUTER_NAME = "XRouter";

    /**
     * Android content包名
     */
    public static final String CONTEXT_PKG = "android.content";

    /**
     * context类名
     */
    public static final String CONTEXT_NAME = "Context";

    /**
     * xrouter.core包路径
     */
    public static final String LIBRARY_CORE_PKG_NAME = "com.mrcd.xrouter.core";

    /**
     * IntentArgs的类名
     */
    public static final String INTENT_ARG = "IntentArgs";

    /**
     * IntentWrapper类名
     */
    public static final String INTENT_WRAPPER_NAME = "IntentWrapper";

    /**
     * 拦截器类名
     */
    public static final String INTENT_INTERCEPTOR = "IntentInterceptor";

    /**
     * 任务描述
     */
    public static final String MAKE_ROUTERS_TASK_DESCRIPTION = "This task will make the routes";

    /**
     * 扩展名
     */
    public static final String ROUTER_CONFIG = "XRouterConfig";

    /**
     * 在编译过程中生成的缓存文件目录
     */
    public static final String CACHE_DIR_NAME = "ProcessCache";

    /**
     * 编译时收集的路由缓存文件
     */
    public static final String ROUTER_CACHE_NAME = "ClassRouter.json";

    /**
     * 查看编译时异常的关键字
     */
    public static final String EXCEPTION_KEY = "XRouterException";

    /**
     * 路由表Java文件的统一后缀名
     */
    public static final String ROUTER_SUFFIX = "Router";

    /**
     * 路由表Java文件的命名规则
     */
    public static final String ROUTER_NAME_FORMAT = "%s" + ROUTER_SUFFIX;

    public static final Map<String, Class> CLASS_MAP = new HashMap<>();

    /**
     * 库工程的Java目录
     */
    public static File sJavaFileOutPutDir;

    /**
     * 获取缓存目录
     *
     * @param rootDir     主目录
     * @param projectName 工程名
     * @return 缓存目录对象
     */
    public static File getCacheDir(File rootDir, String projectName) {
        return new File(rootDir, File.separator + projectName + File.separator + CACHE_DIR_NAME);
    }

    /**
     * 获取json缓存文件对象
     *
     * @param rootDir     主目录
     * @param projectName 工程名
     * @return 缓存文件对象
     */
    public static File getJsonCacheFile(File rootDir, String projectName) {
        return new File(getCacheDir(rootDir, projectName), ROUTER_CACHE_NAME);
    }

    /**
     * 创建Java文件目录的file对象
     *
     * @param parent      主工程根目录
     * @param projectName 实现了插件的工程的工程名
     */
    public static void generateJavaHomeDir(File parent, String projectName) {
        //注意，此处有坑，如果考虑到渠道分发的构建的话，此处有大坑
        //如果要处理，那么必须在知道当前的构建类型，此处的路径就该定义成"/%s/src/%s/java/"
        String routerDirName = "/%s/src/main/java/";
        String formatName = String.format(routerDirName, projectName);
        sJavaFileOutPutDir = new File(parent + formatName);
    }

    static {
        CLASS_MAP.put("int", int.class);
        CLASS_MAP.put("short", short.class);
        CLASS_MAP.put("long", long.class);
        CLASS_MAP.put("byte", byte.class);
        CLASS_MAP.put("float", float.class);
        CLASS_MAP.put("double", double.class);
        CLASS_MAP.put("char", char.class);
        CLASS_MAP.put("boolean", boolean.class);
    }

}
