package com.leadal.netdisk.resource.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.leadal.netdisk.common.enums.FileKind;
import com.leadal.netdisk.common.exception.InvalidExtensionException;
import com.leadal.netdisk.common.util.file.FileUploadUtils;
import com.leadal.netdisk.common.util.file.FileUtils;
import com.leadal.netdisk.common.util.file.MimeTypeUtils;
import com.leadal.netdisk.disk.model.File;
import com.leadal.netdisk.disk.service.IFileService;
import com.leadal.netdisk.common.model.FileURL;
import com.leadal.netdisk.resource.model.Resource;
import com.leadal.netdisk.resource.dao.ResourceMapper;
import com.leadal.netdisk.resource.service.IResourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.leadal.netdisk.resource.view.RsDownloadVO;
import com.leadal.netdisk.resource.view.ResourceVO;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.util.Date;
import java.util.List;


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
    public boolean transactionalSave(FileURL fileURL, ResourceVO vo) throws IOException, InvalidExtensionException {
        MultipartFile mFile = fileURL.getMFile();
        String id = fileURL.getIdUrl();
        String md5 = FileUploadUtils.generateMd5(mFile);
        Long size = mFile.getSize();
        String ofName = mFile.getOriginalFilename();
        String name = ofName.substring(0, ofName.lastIndexOf("."));
        String type = ofName.substring(ofName.lastIndexOf(".")+1).toLowerCase();
        String diskId = vo.getDiskId();
        List<String> folderIds = vo.getFolderIds();
        FileKind kind = MimeTypeUtils.getKindByType(type);

        // 后缀名不合法
        FileUploadUtils.assertAllowed(mFile, MimeTypeUtils.DEFAULT_ALLOWED_EXTENSION);

        // 文件是否存在于服务器？
        Resource resource = new Resource(id, md5, size, type);
        int rsResult = this.baseMapper.conditionInsert(resource);

        String resourceId = resource.getId();
//            File file = new File(IdUtil.simpleUUID(), diskId, folderId, name, size, type, kind, resourceId);
        // 存在？ 名字和类型不相同 插入
        if(0 == rsResult) {
            for(String folderId : folderIds) {
                File file = new File(IdUtil.simpleUUID(), diskId, folderId, name, size, type, kind, resourceId);
                fileService.conditionSave(file, md5);
            }
        // 不存在？ 插入 并进行上传
        } else if(1 == rsResult) {
            for(String folderId : folderIds) {
                File file = new File(IdUtil.simpleUUID(), diskId, folderId, name, size, type, kind, resourceId);
                fileService.save(file);
            }
            return true;
        }

        return false;
    }

    @Override
    public void download(String resouseId, String realFileName, HttpServletResponse response) {
        QueryWrapper<Resource> resourceQueryWrapper = new QueryWrapper<>();
        resourceQueryWrapper
                .eq("id", resouseId)
                .eq("del_flag", "0");
        Resource resource = this.baseMapper.selectOne(resourceQueryWrapper);

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

        try {
            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
            FileUtils.setAttachmentResponseHeader(response, realFileName);
            FileUtils.writeBytes(url, response.getOutputStream());
        }
        catch (Exception e) {
            log.error("下载文件失败", e);
        }
    }


}
