package com.leadal.netdisk.common.util.file;

/**
 * 媒体类型工具类
 * 
 * @author yf
 */
public class MimeTypeUtils {

    public static final String[] IMAGE_EXTENSION = { "bmp", "gif", "jpg", "jpeg", "png", "tif" };

    public static final String[] DOCUMENT_EXTENSION = { "doc", "docx", "xls", "xlsx", "ppt", "pptx", "txt", "pdf" };

    public static final String[] VIDEO_EXTENSION = { "mp4", "avi", "rmvb", "mov", "wmv", "flv", "rm", "swf", "mpg" };

    public static final String[] AUDIO_EXTENSION = { "wma", "wav", "mp3" };

    public static final String[] COMPRESSION_EXTENSION = { "rar", "zip", "gz", "bz2" };

    public static final String[] DEFAULT_ALLOWED_EXTENSION = {
            // 图片
            "bmp", "gif", "jpg", "jpeg", "png", "tif",
            // 文档
            "doc", "docx", "xls", "xlsx", "ppt", "pptx", "txt", "pdf",
            // 视频
            "mp4", "avi", "rmvb", "mov", "wmv", "flv", "rm", "swf", "mpg",
            // 音频
            "wma", "wav", "mp3",
            // 压缩文件
            "rar", "zip", "gz", "bz2",
    };



}
