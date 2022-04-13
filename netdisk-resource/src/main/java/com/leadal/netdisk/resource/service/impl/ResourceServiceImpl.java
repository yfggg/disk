package com.leadal.netdisk.resource.service.impl;

import cn.hutool.crypto.digest.DigestUtil;
import com.leadal.netdisk.common.model.Result;
import com.leadal.netdisk.common.util.file.FileUploadUtils;
import com.leadal.netdisk.disk.service.IFileService;
import com.leadal.netdisk.resource.model.Resource;
import com.leadal.netdisk.resource.dao.ResourceMapper;
import com.leadal.netdisk.resource.service.IResourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.leadal.netdisk.resource.view.ResourceVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
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
    public String upload(ResourceVO vo) {
        List<MultipartFile> files = vo.getFiles();
        for (MultipartFile file : files) {
            try {
                this.save(file);
                // todo 如果表中不存在 就进行上传服务器
                return FileUploadUtils.upload(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean save(MultipartFile file) throws IOException {
        // md5判断文件的唯一性，防止重复上传
        InputStream iStream = file.getInputStream();
        String md5 = DigestUtil.md5Hex(iStream);
        Long size = file.getSize();
        String name = file.getOriginalFilename();
        String type = name.substring(name.lastIndexOf(".")+1);
        Resource resource = new Resource();

        // TODO 先保存到 ndk_resource 表中
        // todo 再保存到 ndk_file 表中 disk_id
        int result = this.baseMapper.insert(resource);
        return false;
    }





}
