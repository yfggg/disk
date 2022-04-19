package com.leadal.netdisk.disk.personal.controller;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.leadal.netdisk.common.model.Result;
import com.leadal.netdisk.common.util.file.DateUtils;
import com.leadal.netdisk.disk.enums.TableKind;
import com.leadal.netdisk.disk.model.File;
import com.leadal.netdisk.disk.service.IFileService;
import com.leadal.netdisk.disk.view.FolderVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.websocket.server.PathParam;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


/**
 * <p>
 * 个人网盘空间表 前端控制器
 * </p>
 *
 * @author yf
 * @since 2022-04-07
 */
@Api(tags="个人网盘-文件夹-控制器")
@Slf4j
@RestController
@RequestMapping("/perdisk/folder")
public class PersonalFolderContoller {

    private static final String DISK_ID = "1";

    @Resource
    private IFileService fileService;

    /**
     * 文件夹列表
     *
     * @param id "当前-文件夹ID"
     * @return
     */
    @ApiOperation(value="文件夹列表")
    @GetMapping(value = "/query")
    public Result<?> query(String id) {

        QueryWrapper<File> wrapper = new QueryWrapper<>();
        wrapper
                .eq("folder_parent_id", id)
                .eq("table_kind", TableKind.FOLDER)
                .eq("disk_id", DISK_ID)
                .eq("del_flag", "0");
        List<File> list = fileService.list(wrapper);

        return Result.OK(list);
    }

    /**
     * 新建文件夹
     *
     * {
     *   "id": "当前-文件夹ID",
     *   "folderParentIds": "当前-上级文件夹ID集",
     *   "folderName": "文件夹名称"
     * }
     * @param vo
     * @return
     */
    @ApiOperation(value="新建文件夹")
    @PostMapping(value = "/create")
    public Result<?> create(@RequestBody FolderVO vo) {

        String folderParentId = vo.getId();
        String folderParentIds = vo.getFolderParentIds();
        String folderName = vo.getFolderName();

        // 文件夹重名
        QueryWrapper<File> wrapper = new QueryWrapper<>();
        wrapper
                .eq("folder_name", folderName)
                .eq("table_kind", TableKind.FOLDER)
                .eq("disk_id", DISK_ID)
                .eq("del_flag", "0");
        int same = fileService.count(wrapper);
        if(0 < same) {
            folderName = new StringBuffer()
                    .append(folderName)
                    .append(DateUtils.dateTimePath())
                    .toString();
        }

        // 拼接级联路径
        StringBuffer buffer = new StringBuffer();
        String parentIdsBuffer;
        if(StringUtils.isBlank(folderParentIds)) {
            parentIdsBuffer = buffer
                    .append(folderParentId)
                    .toString();
        }  else {
            parentIdsBuffer = buffer
                    .append(folderParentIds)
                    .append(",")
                    .append(folderParentId)
                    .toString();
        }

        // 插入
        File folder = new File(
                DISK_ID,
                folderName,
                folderParentId,
                parentIdsBuffer,
                TableKind.FOLDER
        );
        fileService.save(folder);

        return Result.OK();
    }

    /**
     * 重命名文件夹
     *
     * {
     *   "id": "当前-文件夹ID",
     *   "folderName": "文件夹名称"
     * }
     * @param vo
     * @return
     */
    @ApiOperation(value="重命名文件夹")
    @PutMapping(value = "/rename")
    public Result<?> rename(@RequestBody FolderVO vo) {

        String id = vo.getId();
        String folderName = vo.getFolderName();

        // 文件夹重名
        QueryWrapper<File> wrapper = new QueryWrapper<>();
        wrapper
                .eq("id", id)
                .eq("folder_name", folderName)
                .eq("table_kind", TableKind.FOLDER)
                .eq("disk_id", DISK_ID)
                .eq("del_flag", "0");

        int same = fileService.count(wrapper);
        if(0 < same) {
            return Result.error("操作失败: 文件夹已经存在! ");
        }

        // 更新
        fileService.update(wrapper);

        return Result.OK();
    }






}
