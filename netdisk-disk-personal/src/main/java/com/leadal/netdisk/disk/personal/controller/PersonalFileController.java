package com.leadal.netdisk.disk.personal.controller;


import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.leadal.netdisk.common.exception.InvalidExtensionException;
import com.leadal.netdisk.common.model.Result;
import com.leadal.netdisk.common.util.file.FileUploadUtils;
import com.leadal.netdisk.disk.mapping.FileMapping;
import com.leadal.netdisk.disk.model.File;
import com.leadal.netdisk.disk.service.IFileService;
import com.leadal.netdisk.disk.view.FileVO;
import com.leadal.netdisk.resource.service.IResourceService;
import com.leadal.netdisk.resource.view.ResourceVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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

    @Resource
    private IResourceService resourceService;


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
        boolean result = fileService.saveBatch(files);
        if(!result) { return Result.error("复制文件失败！"); }
        return Result.OK();
    }

    /**
     * 上传文件
     *
     * @param vo
        diskId 网盘空间ID
        files 上传文件集合 支持多文件
        folderIds 文件夹ID集合 支持多文件夹
     * @return
     */
    @ApiOperation(value="上传文件")
    @PostMapping(value = "/upload")
    public Result<?> upload(ResourceVO vo) {
        List<MultipartFile> files = null;
        try {
            files = vo.getFiles();
            for (MultipartFile file : files) {
                String id = IdUtil.simpleUUID();
                boolean result = resourceService.transactionalSave(file, id, vo);
                // 判断文件是否需要上传服务器
                if(result) {
                    FileUploadUtils.upload(file, id);
                }
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
            if(null == files) {
                return Result.error("请选择一个文件！");
            }
        } catch (InvalidExtensionException e) {
            e.printStackTrace();
            return Result.error("不支持." + e.getExtension() + "类型的文件上传！");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Result.OK();
    }

    /**
     * 下载文件
     *
     [
        {
            "realFileName": "文件名 例如 新建文本文档2.txt 记得要带后缀",
            "resouseId": "服务器资源ID"
        }
     ]
     * @param response
     * @return
     */
    @ApiOperation(value="下载文件")
    @GetMapping(value = "/download/{resouseId}/{realFileName}")
    public void download(@PathVariable String resouseId,
                         @PathVariable String realFileName,
                         HttpServletResponse response) {
        resourceService.download(resouseId, realFileName, response);
    }

    /**
     * 删除文件
     *
     * @param vo
    {
    "diskId": "网盘空间ID",
    "fileIds": [
    "文件ID集合"
    ]
    }
     * @return
     */
    @ApiOperation(value="逻辑删除文件")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestBody ResourceVO vo) {
        List<String> fileIds = vo.getFileIds();
        QueryWrapper<File> fileQueryWrapper = new QueryWrapper<>();
        fileQueryWrapper
                .in("id", fileIds)
                .eq("del_flag", "0");
        File file = new File("1");
        boolean result = fileService.update(file, fileQueryWrapper);
        if(!result) { return Result.error("复制文件失败！"); }
        return Result.OK();
    }





}
