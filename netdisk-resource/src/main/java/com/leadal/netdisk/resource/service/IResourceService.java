package com.leadal.netdisk.resource.service;

import com.leadal.netdisk.common.exception.InvalidExtensionException;
import com.leadal.netdisk.disk.model.File;
import com.leadal.netdisk.resource.model.Resource;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * <p>
 * 服务器资源表 服务类
 * </p>
 *
 * @author yf
 * @since 2022-04-12
 */
public interface IResourceService extends IService<Resource> {

    boolean tSave(String diskId, MultipartFile mFile, String[] folderIds, String resouseId)
            throws IOException, InvalidExtensionException;

    void download(String resouseId, String realFileName, HttpServletResponse response);

    List<File> addUrl(List<File> files);


}
