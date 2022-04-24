package com.leadal.netdisk.resource.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.leadal.netdisk.common.exception.InvalidExtensionException;
import com.leadal.netdisk.common.util.file.FileUploadUtils;
import com.leadal.netdisk.common.util.file.FileUtils;
import com.leadal.netdisk.common.util.file.MimeTypeUtils;
import com.leadal.netdisk.disk.enums.FileKind;
import com.leadal.netdisk.disk.enums.TableKind;
import com.leadal.netdisk.disk.model.File;
import com.leadal.netdisk.disk.service.IFileService;
import com.leadal.netdisk.resource.model.Resource;
import com.leadal.netdisk.resource.dao.ResourceMapper;
import com.leadal.netdisk.resource.service.IResourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


/**
 * <p>
 * 服务器资源表 服务实现类
 * </p>
 *
 * @author yf
 * @since 2022-04-12
 */
@Service
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements IResourceService {

    @javax.annotation.Resource
    private IFileService fileService;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean tSave(String diskId, MultipartFile mFile, String[] folderIds, String resouseId)
            throws IOException, InvalidExtensionException {

        String md5 = FileUploadUtils.generateMd5(mFile);
        Long fiSize = mFile.getSize();
        String ofiName = mFile.getOriginalFilename();
        String fiName = ofiName.substring(0, ofiName.lastIndexOf("."));
        String fiType = ofiName.substring(ofiName.lastIndexOf(".")+1).toLowerCase();
        FileKind fiKind = getKindByType(fiType);

        // 后缀名不合法
        FileUploadUtils.assertAllowed(mFile, MimeTypeUtils.DEFAULT_ALLOWED_EXTENSION);

        // 文件是否存在于服务器？
        QueryWrapper<Resource> rWrapper = new QueryWrapper<>();
        rWrapper.eq("md5", md5);
        int rCount = this.baseMapper.selectCount(rWrapper);

        // 不存在？ 插入 并进行上传
        if(0 >= rCount) {
            Resource resource = new Resource(resouseId, md5, fiSize, fiType);
            this.baseMapper.insert(resource);

            // 多个文件夹
            List<File> files = getFoldersById(folderIds);
            files.stream().forEach(file ->
                    fileSave(file, fiName, fiType, fiSize, fiKind, resouseId)
            );

            return true;
        }

        // 存在？ 名字和类型不相同并且已经逻辑删除 插入
        if(0 < rCount) {

            boolean r = checkSameFileByKind(fiName, fiType);
            if(!r) {

                QueryWrapper<Resource> wrapper = new QueryWrapper<>();
                wrapper.eq("md5", md5);
                String rId = this.baseMapper.selectOne(wrapper).getId();

                // 多个文件夹
                List<File> files = getFoldersById(folderIds);
                files.stream().forEach(file ->
                        fileSave(file, fiName, fiType, fiSize, fiKind, rId)
                );
            }
        }

        return false;
    }

    private boolean checkSameFileByKind(String fiName, String fiType) {

        QueryWrapper<File> fWrapper = new QueryWrapper<>();
        fWrapper
                .eq("table_kind", TableKind.FILE)
                .eq("del_flag", "0"); // 删除的就不用进行检测了
        List<File> files = fileService.list(fWrapper);

        for(File file : files) {
            String a = fiName + "." + fiType;
            String b = file.getFileName() + "." + file.getFileType();
            if(a.equals(b)){
                return true;
            }
        }

        return false;
    }

    private List<File> getFoldersById(String... folderIds) {

        QueryWrapper<File> fWrapper = new QueryWrapper<>();
        fWrapper.in("id", folderIds);
        List<File> files = fileService.list(fWrapper);

        return files;
    }

    private boolean fileSave(File file, String fiName, String fiType, Long fiSize, FileKind fiKind, String resouseId) {

        file.setId(IdUtil.simpleUUID());
        file.setFileName(fiName);
        file.setFileType(fiType);
        file.setFileSize(fiSize);
        file.setFileKind(fiKind);
        file.setResourceId(resouseId);
        file.setTableKind(TableKind.FILE);
        boolean result = fileService.save(file);

        return result;
    }

    private FileKind getKindByType(String type) {

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

    @Override
    public void download(String resouseId, String realFileName, HttpServletResponse response) {

        Resource resource = rSelectOne(resouseId);

        String url = getURL(resource);

        try {
            response.setContentLengthLong(resource.getSize());
            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
            FileUtils.setAttachmentResponseHeader(response, realFileName);
            FileUtils.writeBytes(url, response.getOutputStream());
        }
        catch (Exception e) {
            log.error("下载文件失败", e);
        }
    }

    private Resource rSelectOne(String resouseId) {

        QueryWrapper<Resource> resourceQueryWrapper = new QueryWrapper<>();
        resourceQueryWrapper
                .eq("id", resouseId)
                .eq("del_flag", "0");
        Resource resource = this.baseMapper.selectOne(resourceQueryWrapper);

        return resource;
    }

    private String getURL(Resource resource) {

        String createTime = DateUtil.formatDateTime(resource.getCreateTime());
        String date = createTime.substring(0, createTime.lastIndexOf(" "));
        String year = date.substring(0, 4);
        String mDay = date.substring(5);
        String time = createTime.substring(createTime.lastIndexOf(" ")+1);
        String hour = time.substring(0, 2);
        String baseDir = FileUtils.getDefaultBaseDir();
        String dateTime = year + "/" + mDay + "/" + hour;
        String fileName = resource.getId() + "." + resource.getType();
        String url = baseDir + "/" + dateTime + "/" + fileName;

        return url;
    }

    @Override
    public List<File> addUrl(List<File> files) {

        // 加入文件预览地址
        return files.stream()
                .map(file -> {
                    if(TableKind.FILE.equals(file.getTableKind())) {
                        String resourceId = file.getResourceId();
                        Resource resource = rSelectOne(resourceId);
                        String url = getURL(resource);
                        file.setUrl(url);
                    }
                    return file;
                }).collect(Collectors.toList());
    }





}
