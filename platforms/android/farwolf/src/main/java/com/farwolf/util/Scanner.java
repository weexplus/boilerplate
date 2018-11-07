package com.farwolf.util;

import android.content.Context;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import dalvik.system.DexFile;
import dalvik.system.PathClassLoader;

public class Scanner {
    private static Field dexField;

    static {
        try {
            dexField = PathClassLoader.class.getDeclaredField("mDexs");
            dexField.setAccessible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
