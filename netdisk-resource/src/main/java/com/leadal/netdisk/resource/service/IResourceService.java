package com.leadal.netdisk.resource.service;

import com.leadal.netdisk.common.exception.InvalidExtensionException;
import com.leadal.netdisk.resource.model.Resource;
import com.baomidou.mybatisplus.extension.service.IService;
import com.leadal.netdisk.resource.view.ResourceVO;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
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

    boolean transactionalSave(MultipartFile mFile, String id, ResourceVO vo) throws IOException, InvalidExtensionException;

    void download(String resouseId, String realFileName, HttpServletResponse response);
}
