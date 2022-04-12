package com.leadal.netdisk.disk.personal.controller;


import cn.hutool.crypto.digest.DigestUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.leadal.netdisk.common.model.Result;
import com.leadal.netdisk.common.util.file.FileUploadUtils;
import com.leadal.netdisk.common.util.file.FileUtils;
import com.leadal.netdisk.disk.mapping.FileMapping;
import com.leadal.netdisk.disk.model.File;
import com.leadal.netdisk.disk.service.IFileService;
import com.leadal.netdisk.disk.view.FileVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * <p>
 * 个人网盘空间表 前端控制器
 * </p>
 *
 * @author yf
 * @since 2022-04-07
 */
@Api(tags="个人网盘-文件-控制器")
@Slf4j
@RestController
@RequestMapping("/perdisk/files")
public class PersonalFileController {

    @Resource
    private IFileService fileService;


    /**
     * 移动文件
     *
     * @param vo
    {
    "diskId": "网盘空间ID",
    "fileIds": [
    "传输文件id集合"
    ],
    "folderId": "文件夹id"
    }
     * @return
     */
    @ApiOperation(value="移动文件")
    @PutMapping(value = "/move")
    public Result<?> move(@RequestBody FileVO vo) {
        String diskId = vo.getDiskId();
        List<String> ids = vo.getFileIds();
        QueryWrapper<File> fileQueryWrapper = new QueryWrapper<>();
        fileQueryWrapper
                .eq("disk_id", diskId)
                .in("id", ids);
        File file = FileMapping.INSTANCE.toModel(vo);
        boolean result = fileService.update(file, fileQueryWrapper);
        if(!result) { return Result.error("移动文件失败！"); }
        return Result.OK();
    }

    /**
     * 复制文件
     *
     * @param files
    [
    {
    "diskId": "网盘空间ID",
    "folderId": "文件夹id",
    "kind": "文件类别（0文档 1图片 2视频 3音频 4压缩文件）",
    "name": "文件名称",
    "size": 文件大小,
    "type": "文件类型（.xls .ppt...）",
    }
    ]
     * @return
     */
    @ApiOperation(value="复制文件")
    @PostMapping(value = "/copy")
    public Result<?> copy(@RequestBody List<File> files) {
        fileService.saveBatch(files);
        return Result.OK();
    }







}
