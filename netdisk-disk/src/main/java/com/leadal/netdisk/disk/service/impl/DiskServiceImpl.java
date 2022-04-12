package com.leadal.netdisk.disk.service.impl;

import com.leadal.netdisk.disk.model.Disk;
import com.leadal.netdisk.disk.dao.DiskMapper;
import com.leadal.netdisk.disk.service.IDiskService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 网盘空间表 服务实现类
 * </p>
 *
 * @author yf
 * @since 2022-04-07
 */
@Service
public class DiskServiceImpl extends ServiceImpl<DiskMapper, Disk> implements IDiskService {

}
