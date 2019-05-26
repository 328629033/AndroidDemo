package com.leeks.fileutil;


import android.os.Environment;

import com.leeks.loader.ILoader;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * Created by herr.wang on 2017/5/17.
 */

public class DynamicLoader implements ILoader {
    @Override
    public void load() {
        System.out.println("loading...");
        String tempPath = FileUtils.getTempDirectoryPath();
        String userPath = FileUtils.getUserDirectoryPath();
        System.out.println("temp path is " + tempPath + ", user path is " + userPath);
        try {
            FileUtils.forceMkdir(new File(Environment.getExternalStorageDirectory() + File.separator + "android_demo"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
