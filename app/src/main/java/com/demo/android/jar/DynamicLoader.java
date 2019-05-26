package com.demo.android.jar;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.leeks.loader.ILoader;

import java.io.File;

import dalvik.system.DexClassLoader;

/**
 * Created by herr.wang on 2017/5/18.
 */

public class DynamicLoader {
    public static final String TAG = "DynamicLoader";
    public void load(Context context){
        String jarPath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "file_util_loader.jar";
        File jarFile = new File(jarPath);
        if(!jarFile.exists()){
            Log.e(TAG, "jar is not exits");
            return;
        }
        File dexFile = context.getDir("dex", 0);
        DexClassLoader loader = new DexClassLoader(jarFile.getAbsolutePath(),dexFile.getAbsolutePath(), null, context.getClassLoader());
        try {
            Class clazz = loader.loadClass("com.leeks.fileutil.DynamicLoader");
            ILoader loaderImpl = (ILoader) clazz.newInstance();
            loaderImpl.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
