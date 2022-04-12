package com.leadal.netdisk.resource.service;

import com.leadal.netdisk.resource.model.Resource;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * <p>
 * 服务器资源表 服务类
 * </p>
 *
 * @author yf
 * @since 2022-04-12
 */
public interface IResourceService extends IService<Resource> {

    boolean save(MultipartFile file) throws IOException;
}
