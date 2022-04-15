package com.leadal.netdisk.common.util.file;

import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.leadal.netdisk.common.exception.InvalidExtensionException;
import com.leadal.netdisk.common.model.FileURL;
import lombok.Getter;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * 文件上传工具类
 *
 * @author yf
 */
@Component
public class FileUploadUtils {

    /**
     * 默认上传的地址
     */
    @Getter
    private static String defaultBaseDir;
    @Value("${spring.servlet.multipart.location}")
    public void setLocation(String location) { defaultBaseDir = location; }

    // 返回文件的md5 用于判断文件内容的唯一性
    public static final String generateMd5(MultipartFile mFile) throws IOException {
        File tmpFile = new File(mFile.getOriginalFilename());
        FileUtils.copyInputStreamToFile(mFile.getInputStream(), tmpFile);
        String md5 = DigestUtil.md5Hex(tmpFile);
        // 会在本地产生临时文件，用完后需要删除
        if (tmpFile.exists()) {
            tmpFile.delete();
        }
        return md5;
    }

    /**
     * 以默认配置进行文件上传
     *
     * @param fileURL 上传的文件
     * @return 文件名称
     * @throws Exception
     */
    public static final String upload(FileURL fileURL) throws IOException, InvalidExtensionException {
        return upload(defaultBaseDir, fileURL, MimeTypeUtils.DEFAULT_ALLOWED_EXTENSION);
    }

    /**
     * 文件上传
     *
     * @param baseDir 相对应用的基目录
     * @param fileURL 上传的文件
     * @param allowedExtension 上传文件类型
     * @return 返回上传成功的文件名
     * @throws IOException 比如读写文件出错时
     * @throws InvalidExtensionException 文件校验异常
     */
    public static final String upload(String baseDir, FileURL fileURL, String[] allowedExtension) throws IOException, InvalidExtensionException {
        MultipartFile file = fileURL.getMFile();
        String idPath = fileURL.getIdUrl();
//        Date creteTimePath = fileURL.getCreateTimeUrl();
        assertAllowed(file, allowedExtension);
        String fileName = extractFilename(file, idPath);
        File desc = getAbsoluteFile(baseDir, fileName);
        file.transferTo(desc);
        String pathFileName = getPathFileName(baseDir, fileName);
        return pathFileName;
    }

    /**
     * 编码文件名（路径）
     *
     * @param file
     * @return
     */
    public static final String extractFilename(MultipartFile file, String idPath) {
        String extension = getExtension(file);
//        Date creteTime = null != creteTimePath ? creteTimePath : new Date();
        String dateTimePath = DateUtils.dateTimePath();
        String uuid = null != idPath ? idPath : IdUtil.simpleUUID();
        String fileName = dateTimePath + "/" + uuid + "." + extension;
        return fileName;
    }

    /**
     * 获取返回路径
     *
     * @param uploadDir
     * @param fileName
     * @return
     * @throws IOException
     */
    public static final String getPathFileName(String uploadDir, String fileName) {
        int dirLastIndex = defaultBaseDir.length() + 1;
        String currentDir = StringUtils.substring(uploadDir, dirLastIndex);
        String pathFileName = currentDir + "/" + fileName;
        return pathFileName;
    }

    /**
     * 上传前检测目标目录是否存在
     *
     * @param uploadDir
     * @param fileName
     * @return
     * @throws IOException
     */
    public static final File getAbsoluteFile(String uploadDir, String fileName) {
        File file = new File(uploadDir + File.separator + fileName);
        if (!file.exists()) {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
        }
        return file;
    }

    /**
     * 验证文件后缀合法性
     *
     * @param file 上传的文件
     * @return
     * @throws InvalidExtensionException
     */
    public static final void assertAllowed(MultipartFile file, String[] allowedExtension) throws InvalidExtensionException {
        String fileName = file.getOriginalFilename();
        String extension = getExtension(file);
        if (allowedExtension != null && !isAllowedExtension(extension, allowedExtension)) {
            throw new InvalidExtensionException(allowedExtension, extension, fileName);
        }
    }

    /**
     * 判断MIME类型是否是允许的MIME类型
     *
     * @param extension
     * @param allowedExtension
     * @return
     */
    public static final boolean isAllowedExtension(String extension, String[] allowedExtension) {
        for (String str : allowedExtension) {
            if (str.equalsIgnoreCase(extension)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取文件名的后缀
     *
     * @param file 表单文件
     * @return 后缀名
     */
    public static final String getExtension(MultipartFile file) {
        return FilenameUtils.getExtension(file.getOriginalFilename());
    }





}
