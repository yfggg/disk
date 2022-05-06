package com.leadal.netdisk.disk.personal.service;

import com.leadal.netdisk.disk.personal.model.PersonalDisk;
import com.baomidou.mybatisplus.extension.service.IService;
import com.leadal.netdisk.disk.view.FileVO;

/**
 * <p>
 * 个人网盘空间表 服务类
 * </p>
 *
 * @author yf
 * @since 2022-04-07
 */
public interface IPersonalDiskService extends IService<PersonalDisk> {

    boolean plusSpace(String diskId, Long sum);

    boolean minusSpace(String diskId, Long sum);

}
