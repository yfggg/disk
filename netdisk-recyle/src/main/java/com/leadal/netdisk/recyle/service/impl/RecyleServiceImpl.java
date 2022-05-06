package com.leadal.netdisk.recyle.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.leadal.netdisk.disk.enums.TableKind;
import com.leadal.netdisk.disk.model.File;
import com.leadal.netdisk.disk.service.IFileService;
import com.leadal.netdisk.recyle.model.Recyle;
import com.leadal.netdisk.recyle.dao.RecyleMapper;
import com.leadal.netdisk.recyle.service.IRecyleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.leadal.netdisk.resource.service.IResourceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 回收站表 服务实现类
 * </p>
 *
 * @author yf
 * @since 2022-05-05
 */
@Service
public class RecyleServiceImpl extends ServiceImpl<RecyleMapper, Recyle> implements IRecyleService {

    @Resource
    private IFileService fileService;

    @Resource
    private IResourceService resourceService;


    @Override
    public IPage<File> selectPage(Page page, Wrapper<Recyle> queryWrapper) {
        return this.baseMapper.selectPageList(page, queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean create(List<String> fileIds, String diskId) {

        QueryWrapper<File> wrapper = new QueryWrapper<>();
        wrapper
                .in("id", fileIds)
                .eq("disk_id", diskId)
                .eq("table_kind", TableKind.FILE)
                .eq("del_flag", "0");
        boolean result = fileService.update(new File("1"), wrapper);

        if(result) {
            for(String fileId : fileIds) {
                Recyle recyle = new Recyle(diskId, fileId);
                save(recyle);
            }
            return true;
        }

        return false;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean recover(List<String> fileIds, String diskId) {

        QueryWrapper<File> wrapper = new QueryWrapper<>();
        wrapper
                .in("id", fileIds)
                .eq("disk_id", diskId)
                .eq("table_kind", TableKind.FILE)
                .eq("del_flag", "1");
        boolean result = fileService.update(new File("0"), wrapper);

        if(result) {
            QueryWrapper<Recyle> rWrapper = new QueryWrapper<>();
            rWrapper
                    .in("file_id", fileIds)
                    .eq("disk_id", diskId)
                    .eq("del_flag", "0");
            return update(new Recyle("1") ,rWrapper);
        }

        return false;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Long delete(List<String> fileIds, String diskId) {

        QueryWrapper<Recyle> wrapper = new QueryWrapper<>();
        wrapper
                .in("file_id", fileIds)
                .eq("disk_id", diskId)
                .eq("del_flag", "0");
        boolean result = update(new Recyle("1"), wrapper);

        Long sum = 0L;

        if(result) {

            QueryWrapper<File> fWrapper = new QueryWrapper<>();
            fWrapper
                    .in("id", fileIds)
                    .eq("disk_id", diskId)
                    .eq("del_flag", "1");
            List<File> files = fileService.list(fWrapper);

            List<String> resourceIds = files.stream().map(file -> file.getResourceId())
                    .distinct().collect(Collectors.toList());
            for(String resourceId : resourceIds) {

                QueryWrapper<File> fWrapper1 = new QueryWrapper<>();
                fWrapper1.eq("resource_id", resourceId);
                List<File> files1 = fileService.list(fWrapper1);

                if(1 == files1.size()) {
                    boolean result1 = resourceService.updateById(
                            new com.leadal.netdisk.resource.model.Resource(resourceId, "1"));
                    if(result1) {
                        sum += files1.get(0).getFileSize();
                    }
                }

            }
            return sum;
        }
        return sum;
    }


}
