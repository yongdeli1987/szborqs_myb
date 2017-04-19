package com.szborqs.mybook.config;

/**
 * description
 *
 * @Author Administrator
 * @Time 2016/11/4 16:51
 */

public class Constant {
    /**
     * 本地缓存根文件夹
     */
    public static final String FILE_BASE = "/szborqs_book/";
    /**
     * imageload图片框架的缓存
     */
    public static final String IMAGE_LOADER_DIR = FILE_BASE + "caches/";
    /**
     * 图片
     */
    public static final String IMG_DIR = "images/";
    /**
     * 视频
     */
    public static final String VIDEO_DIR = "videos/";
    /**
     * 语音
     */
    public static final String VOICE_DIR = "voices/";
    /**
     * apk
     */
    public static final String APK_DIR = "apk/";
    /**
     * 启动图片
     */
    public static final String START_IMG_DIR = "startpic/";





    /**
     * 面向手机用户和设备的管理主题名格式
     */
    public static final String TOPIC_ALL = "ADMIN/all";

    /**
     * 面向手机用户的管理主题名格式
     */
    public static final String TOPIC_USER = "ADMIN/USER/broadcast";

    /**
     * 拍照或者视频完成
     */
    public static final String ACTION_OPERATE_COMPLETE = "com.szocean.mobilephone.action.complete";
    /**
     * 重连mqtt
     */
    public static final String ACTION_RECONNECT_MQTT = "com.szocean.mobilephone.action.reconnect.mqtt";
    /**
     * 重连mqtt
     */
    public static final String ACTION_DELETE_MORE_ALARM = "com.szocean.mobilephone.action.delete.more.alarm";
    /**
     * 重连mqtt
     */
    public static final String ACTION_PICKUP_FRIEND_LOCATION = "com.szocean.mobilephone.action.pickup.friend.location";

}
