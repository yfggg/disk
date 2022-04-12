package com.leadal.netdisk.common.exception;

/**
 * 文件大小限制异常类
 * 
 * @author yf
 */
public class FileSizeLimitExceededException extends BaseException {
    private static final long serialVersionUID = 1L;

    public FileSizeLimitExceededException(long defaultMaxSize)
    {
        super("upload.exceed.maxSize", new Object[] { defaultMaxSize });
    }
}
