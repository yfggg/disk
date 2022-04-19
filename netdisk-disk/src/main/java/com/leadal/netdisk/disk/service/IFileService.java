package com.leadal.netdisk.disk.service;

import com.leadal.netdisk.disk.model.File;
import com.baomidou.mybatisplus.extension.service.IService;
import com.leadal.netdisk.disk.view.FileVO;

import java.util.List;

/**
 * <p>
 * 文件表 服务类
 * </p>
 *
 * @author yf
 * @since 2022-04-18
 */
public interface IFileService extends IService<File> {

    List<File> moveAndCopyById(String diskId, FileVO vo, boolean needId);
}
