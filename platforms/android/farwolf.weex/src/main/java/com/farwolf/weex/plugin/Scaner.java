package com.farwolf.weex.plugin;


/**
 * Created by zhengjiangrong on 2018/7/24.
 */
public class Scaner {

//
//    private static Field dexField;
//
//    static {
//        try {
//            dexField = PathClassLoader.class.getDeclaredField("mDexs");
//            dexField.setAccessible(true);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void scan() {
//        try {
//            dexField = PathClassLoader.class.getDeclaredField("mDexs");
//            dexField.setAccessible(true);
//            PathClassLoader classLoader = (PathClassLoader) Thread.currentThread().getContextClassLoader();
//
//            DexFile[] dexs = (DexFile[]) dexField.get(classLoader);
//            for (DexFile dex : dexs) {
//                Enumeration<String> entries = dex.entries();
//                while (entries.hasMoreElements()) {
//                    String entry = entries.nextElement();
//
//                    Class<?> entryClass = dex.loadClass(entry, classLoader);
//                    if (entryClass != null) {
//
//                            WeexModule annotation = entryClass.getAnnotation(WeexModule.class);
//                            if (annotation != null) {
//                                System.out.println("name=" + annotation.name() + "; class=" + entryClass.getName());
//                                registModule(annotation.name(),(Class<? extends WXModule>)entryClass);
//                            }
//                            WeexComponent annotationc = entryClass.getAnnotation(WeexComponent.class);
//                        if (annotationc != null) {
//
//                            registComponent(annotationc.names()[0],(Class<? extends WXComponent>)entryClass);
//                        }
//
//                    }
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    public static void registModule(String name,Class<? extends WXModule> cls)
//    {
//        try {
//            WXSDKEngine.registerModule(name,cls);
//        } catch (WXException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void registComponent(String name,Class<? extends WXComponent> cls)
//    {
//        try {
//            WXSDKEngine.registerComponent(name,cls);
//        } catch (WXException e) {
//            e.printStackTrace();
//        }
//    }
}
