package com.leadal.netdisk.common.util.file;

import com.leadal.netdisk.common.enums.FileKind;

import java.util.Arrays;

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

    public static final FileKind getKindByType(String type) {
        long imageCount = Arrays.stream(MimeTypeUtils.IMAGE_EXTENSION)
                .filter(imageType -> imageType.equals(type)).count();
        long documentCount = Arrays.stream(MimeTypeUtils.DOCUMENT_EXTENSION)
                .filter(documentType -> documentType.equals(type)).count();
        long videoCount = Arrays.stream(MimeTypeUtils.VIDEO_EXTENSION)
                .filter(videoType -> videoType.equals(type)).count();
        long audioCount = Arrays.stream(MimeTypeUtils.AUDIO_EXTENSION)
                .filter(audioType -> audioType.equals(type)).count();
        long compressionCount = Arrays.stream(MimeTypeUtils.COMPRESSION_EXTENSION)
                .filter(compressionType -> compressionType.equals(type)).count();
        if(1 <= imageCount) {
            return FileKind.IMAGE;
        } else if(1 <= documentCount) {
            return FileKind.DOCUMENT;
        } else if(1 <= videoCount) {
            return FileKind.VIDEO;
        } else if(1 <= audioCount) {
            return FileKind.AUDIO;
        } else if(1 <= compressionCount) {
            return FileKind.COMPRESSION;
        }
        return null;
    }

}
