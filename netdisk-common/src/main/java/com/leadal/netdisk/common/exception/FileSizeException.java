package com.leadal.netdisk.common.exception;


import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.apache.tomcat.util.http.fileupload.impl.SizeLimitExceededException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;

import java.util.HashMap;
import java.util.Map;


@Slf4j
@RestControllerAdvice
public class FileSizeException {

    @Value("${spring.servlet.multipart.max-file-size}")
    private String maxFileSize;

    @Value("${spring.servlet.multipart.max-request-size}")
    private String maxRequestSize;

    @ExceptionHandler(MultipartException.class)
    public String  exception(MaxUploadSizeExceededException e){
        String msg = null;
        Map<String,Object> result = new HashMap<>();
        result.put("success", false);
        result.put("code", 500);

        if (e.getCause().getCause() instanceof FileSizeLimitExceededException){
            msg = "上传文件过大[单个文件大小不得超过" + maxFileSize + "]";
            result.put("message", msg);
            return result.toString();
        } else if (e.getCause().getCause() instanceof SizeLimitExceededException){
            msg = "上传文件过大[总上传大小不得超过" + maxRequestSize + "]";
            result.put("message", msg);
            return result.toString();
        } else {
            msg = "上传文件异常";
            result.put("message", msg);
            return result.toString();
        }

    }



}
