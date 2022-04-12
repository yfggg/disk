package com.leadal.netdisk.common.util.file;

/**
 * 媒体类型工具类
 * 
 * @author yf
 */
public class MimeTypeUtils {

    public static final String[] IMAGE_EXTENSION = { "bmp", "gif", "jpg", "jpeg", "png" };

    public static final String[] FLASH_EXTENSION = { "swf", "flv" };

    public static final String[] MEDIA_EXTENSION = { "swf", "flv", "mp3", "wav", "wma", "wmv", "mid", "avi", "mpg",
            "asf", "rm", "rmvb" };

    public static final String[] VIDEO_EXTENSION = { "mp4", "avi", "rmvb" };

    public static final String[] DEFAULT_ALLOWED_EXTENSION = {
            // 图片
            "bmp", "gif", "jpg", "jpeg", "png", "tif",
            // 文档
            "doc", "docx", "xls", "xlsx", "ppt", "pptx", "txt", "pdf",

            // 视频
            "mp4", "avi", "rmvb", "mov", "wmv", "flv",
            // 音频
            "wma", "wav", "mp3",
            // 压缩文件
            "rar", "zip", "gz", "bz2",
    };



}
