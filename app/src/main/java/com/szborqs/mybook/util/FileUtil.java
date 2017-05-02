package com.szborqs.mybook.util;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;


import com.szborqs.mybook.config.Constant;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

/**
 * Created by liyongde on 2016/10/10.
 */

public class FileUtil {

    public static boolean isSDcardAvailable() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }

    public static String getCachFilePath() {
        String path=Environment.getExternalStorageDirectory().getPath() + Constant.FILE_BASE;
        try{
            File file=new File(path);
            if(!file.exists()){
                file.mkdir();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return path;
    }


    public static String getApkDir() {
        String path=getCachFilePath() + Constant.APK_DIR;
        try{
            File file=new File(path);
            if(!file.exists()){
                file.mkdir();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return path;
    }



    public static String getApkFilePath() {
        return getApkDir()  + getApkName();
    }

    public static String getApkName() {
        return "mybook.apk";
    }

    public static boolean isApkFileExist() {
        return isFileExist(getApkFilePath());
    }


    public static boolean isFileExist(String filePath) {
        if (!isSDcardAvailable()) {
            return false;
        }
        try {
            File file = new File(filePath);
            return file.exists();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }




    public static void delDirectory(File file){
        if(file.isFile()){
            file.delete();
        }
        if(file.isDirectory()){
            File[] fileList=file.listFiles();
            if(fileList!=null && fileList.length>0){
                for(File dest:fileList){
                    delDirectory(dest);
                }
            }
        }
    }

    public static String getChapterFilePath(String bookId,String chapterId){
        if(SharedMethod.isEmptyString(bookId)||SharedMethod.isEmptyString(chapterId)){
            return "";
        }
        String bookParentDir=getCachFilePath()+Constant.BOOK_DIR;
        String bookDir=bookParentDir+bookId;
        String chapterFilePath=bookDir+"/"+chapterId+".txt";
        try{
            File bookParentDirFile=new File(bookParentDir);
            if(!bookParentDirFile.exists()){
                bookParentDirFile.mkdir();
            }

            File bookDirFile=new File(bookDir);
            if(!bookDirFile.exists()){
                bookDirFile.mkdir();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return chapterFilePath;
    }

    public static boolean isChapterFileExsit(String bookId,String chapterId){
        String chapterFilePath=getChapterFilePath(bookId,chapterId);
        return isFileExist(chapterFilePath);
    }

    public static void saveChapterContent(String bookId,String chapterId,String content){
        String chapterFilePath=getChapterFilePath(bookId,chapterId);
        try{
            File file=new File(chapterFilePath);
            if(file.exists()){
                file.delete();
            }
            file.createNewFile();
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(content.getBytes("gbk"));
            fos.close();
        }catch(Exception e){
            e.printStackTrace();
        }

    }



}
