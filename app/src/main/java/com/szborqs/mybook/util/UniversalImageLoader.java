package com.szborqs.mybook.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.utils.StorageUtils;
import com.szborqs.mybook.config.Constant;
import com.szborqs.mybook.system.MyApplication;

import java.io.File;

/**
 * 图片下载
 *
 * @author qiaocbao
 * @time 2014-11-12 下午6:21:43
 */
public class UniversalImageLoader {

    private ImageLoader imageLoader = ImageLoader.getInstance();

    private File cacheDir = StorageUtils.getOwnCacheDirectory(
            MyApplication.getInstance(), Constant.IMAGE_LOADER_DIR);
    private ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
            MyApplication.getInstance())
            .memoryCacheExtraOptions(1080, 1920)
            // max width, max height，即保存的每个缓存文件的最大长宽
            // .discCacheExtraOptions(480, 800, CompressFormat.JPEG, 75, null)
            // // Can slow ImageLoader, use it carefully (Better don't use
            // it)/设置缓存的详细信息，最好不要设置这个
            .threadPoolSize(2)
            // 线程池内加载的数量
            .threadPriority(Thread.NORM_PRIORITY - 3)
            .denyCacheImageMultipleSizesInMemory()
            .memoryCache(new WeakMemoryCache())
            // implementation/你可以通过自己的内存缓存实现
            .memoryCacheSize(2 * 1024 * 1024).diskCacheSize(50 * 1024 * 1024)
            .diskCacheFileNameGenerator(new Md5FileNameGenerator())
            // 将保存的时候的URI名称用MD5 加密
            .tasksProcessingOrder(QueueProcessingType.LIFO)
            .diskCacheFileCount(100) // 缓存的文件数量
            .diskCache(new UnlimitedDiscCache(cacheDir))// 自定义缓存路径
            .defaultDisplayImageOptions(DisplayImageOptions.createSimple())
            .imageDownloader(
                    new BaseImageDownloader(MyApplication.getInstance(),
                            10 * 1000, 30 * 1000)) // connectTimeout (5 s),
            // readTimeout (30 s)超时时间
            // .writeDebugLogs() //
            // Remove for release app
            .build();// 开始构建

    public UniversalImageLoader(Context context) {
        imageLoader.init(config);
    }

    /**
     * 加载listivew头像图片（如头像等小图片）
     *
     * @param uri       图片uri地址
     * @param imageView
     * @param dw        默认图片
     * @param isFillet  是否圆角
     */
    public void imgLoader(String uri, ImageView imageView, int dw,
                          boolean isFillet) {
        DisplayImageOptions.Builder builder = new DisplayImageOptions.Builder();
        builder.showImageOnLoading(null); // 加载图片时的图片
        builder.showImageOnFail(dw);// 设置图片加载或解码过程中发生错误显示的图片
        builder.showImageForEmptyUri(dw);// 设置图片Uri为空或是错误的时候显示的图片
        builder.cacheInMemory(false);// 设置下载的图片是否缓存在内存中
        builder.cacheOnDisk(true);// 启用外存缓存
        builder.imageScaleType(ImageScaleType.IN_SAMPLE_INT);
        // if (isFillet) {// 是否圆角
        //
        // builder.displayer(new RoundedBitmapDisplayer(5));
        // }
        builder.bitmapConfig(Bitmap.Config.RGB_565);// 设置图片的解码类型
        DisplayImageOptions listOptions = builder.build();

        try {
            imageLoader.displayImage(uri, imageView, listOptions);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 加载考勤列表的地图图片，不缩小
     *
     * @param uri       图片uri地址
     * @param imageView
     * @param dw        默认图片
     * @param isFillet  是否圆角
     */
    public void imgLoaderNoScale(String uri, ImageView imageView, int dw,
                                 boolean isFillet) {
        DisplayImageOptions.Builder builder = new DisplayImageOptions.Builder();
        builder.showImageOnLoading(null); // 加载图片时的图片
        builder.showImageOnFail(dw);// 设置图片加载或解码过程中发生错误显示的图片
        builder.showImageForEmptyUri(dw);// 设置图片Uri为空或是错误的时候显示的图片
        builder.cacheInMemory(false);// 设置下载的图片是否缓存在内存中
        builder.cacheOnDisk(true);// 启用外存缓存
        builder.imageScaleType(ImageScaleType.NONE);
        // if (isFillet) {// 是否圆角
        //
        // builder.displayer(new RoundedBitmapDisplayer(5));
        // }
        builder.bitmapConfig(Bitmap.Config.RGB_565);// 设置图片的解码类型
        DisplayImageOptions listOptions = builder.build();

        try {
            imageLoader.displayImage(uri, imageView, listOptions);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 加载listivew头像图片（如头像等小图片）
     *
     * @param uri       图片uri地址
     * @param imageView
     * @param dw        默认图片
     * @param isFillet  是否圆角
     */
    public void imgLoaderNoRepeat(String uri, ImageView imageView, int dw,
                                  boolean isFillet) {
        if (!uri.equals(imageView.getTag())) {

            DisplayImageOptions.Builder builder = new DisplayImageOptions.Builder();
            builder.showImageOnLoading(dw); // 加载图片时的图片
            builder.showImageOnFail(dw);// 设置图片加载或解码过程中发生错误显示的图片
            builder.showImageForEmptyUri(dw);// 设置图片Uri为空或是错误的时候显示的图片
            builder.cacheInMemory(false);// 设置下载的图片是否缓存在内存中
            builder.cacheOnDisk(true);// 启用外存缓存
            builder.imageScaleType(ImageScaleType.IN_SAMPLE_INT);
        /* if (isFillet) {// 是否圆角
		
		 builder.displayer(new RoundedBitmapDisplayer(5));
		 }*/
            builder.bitmapConfig(Bitmap.Config.RGB_565);// 设置图片的解码类型
            DisplayImageOptions listOptions = builder.build();

            try {
                imageLoader.displayImage(uri, imageView, listOptions);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        imageView.setTag(uri);
    }

    /**
     * 加载listivew头像图片（如头像等小图片）
     *
     * @param uri       图片uri地址
     * @param imageView
     * @param dw        默认图片
     * @param ew        错误图片
     * @param listener  图片加载监听
     */
    public void imgLoaderNoRepeat(String uri, ImageView imageView, int dw,
                                  int ew, ImageLoadingListener listener) {
        if (!uri.equals(imageView.getTag())) {

            DisplayImageOptions.Builder builder = new DisplayImageOptions.Builder();
//		builder.showImageOnLoading(dw); // 加载图片时的图片
            builder.showImageOnFail(ew);// 设置图片加载或解码过程中发生错误显示的图片
            builder.showImageForEmptyUri(ew);// 设置图片Uri为空或是错误的时候显示的图片
            builder.cacheInMemory(false);// 设置下载的图片是否缓存在内存中
            builder.cacheOnDisk(true);// 启用外存缓存
            builder.imageScaleType(ImageScaleType.IN_SAMPLE_INT);
            builder.bitmapConfig(Bitmap.Config.RGB_565);// 设置图片的解码类型
            DisplayImageOptions listOptions = builder.build();

            try {
                if (listener != null) {
                    imageLoader.displayImage(uri, imageView, listOptions, listener);
                } else {
                    imageLoader.displayImage(uri, imageView, listOptions);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        imageView.setTag(uri);
    }

    /**
     * 清除缓存
     */
    public void clearCache() {
        try {
            // 清除内存缓存
            imageLoader.clearMemoryCache();
            // 清除SD卡中的缓存
            imageLoader.clearDiskCache();
        } catch (Exception e) {
            BookLog.e("clearCache:" + e.getMessage());
        }
    }
}
