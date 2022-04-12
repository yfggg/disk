package com.leadal.netdisk.disk.service.impl;

import com.leadal.netdisk.disk.model.File;
import com.leadal.netdisk.disk.dao.FileMapper;
import com.leadal.netdisk.disk.service.IFileService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 文件表 服务实现类
 * </p>
 *
 * @author yf
 * @since 2022-04-07
 */
@Service
public class FileServiceImpl extends ServiceImpl<FileMapper, File> implements IFileService {

}
