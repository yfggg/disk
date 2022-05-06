package com.leadal.netdisk.disk.personal.service.impl;

import com.leadal.netdisk.disk.model.File;
import com.leadal.netdisk.disk.personal.model.PersonalDisk;
import com.leadal.netdisk.disk.personal.dao.PersonalDiskMapper;
import com.leadal.netdisk.disk.personal.service.IPersonalDiskService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.leadal.netdisk.disk.service.IFileService;
import com.leadal.netdisk.disk.view.FileVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 个人网盘空间表 服务实现类
 * </p>
 *
 * @author yf
 * @since 2022-04-07
 */
@Service
public class PersonalDiskServiceImpl extends ServiceImpl<PersonalDiskMapper, PersonalDisk> implements IPersonalDiskService {

    @Override
    public boolean plusSpace(String diskId, Long sum) {
        PersonalDisk personalDisk = getById(diskId);
        Long all = personalDisk.getAllocateSpace();
        Long used = personalDisk.getUsedSpace();

        used = used + sum;
        personalDisk.setUsedSpace(used);
        personalDisk.setRemaieSpace(all - used);

        boolean result = updateById(personalDisk);

        if(result) {
            return true;
        }

        return false;
    }

    @Override
    public boolean minusSpace(String diskId, Long sum) {
        PersonalDisk personalDisk = getById(diskId);
        Long all = personalDisk.getAllocateSpace();
        Long used = personalDisk.getUsedSpace();

        used = used - sum;
        personalDisk.setUsedSpace(used);
        personalDisk.setRemaieSpace(all - used);

        boolean result = updateById(personalDisk);

        if(result) {
            return true;
        }

        return false;
    }


}
