package com.leadal.netdisk.resource.controller;


import com.leadal.netdisk.common.model.Result;
import com.leadal.netdisk.common.util.file.FileUploadUtils;
import com.leadal.netdisk.resource.service.IResourceService;
import com.leadal.netdisk.resource.view.ResourceVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * <p>
 * 服务器资源表 前端控制器
 * </p>
 *
 * @author yf
 * @since 2022-04-12
 */
@Api(tags="服务器-资源-控制器")
@Slf4j
@RestController
@RequestMapping("/resource")
public class ResourceController {

    @javax.annotation.Resource
    private IResourceService resourceService;

    /**
     * 上传文件
     * @param vo
        diskId 网盘空间ID
        files 支持多文件
     * @return
     */
    @ApiOperation(value="上传文件")
    @PostMapping(value = "/upload")
    public Result<?> upload(ResourceVO vo) {
        String pathFileName = null;
        List<MultipartFile> files = vo.getFiles();
        try {
            for (MultipartFile file : files) {
                try {
                    resourceService.save(file);
                    // todo 如果表中不存在 就进行上传服务器
                    pathFileName = FileUploadUtils.upload(file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (NullPointerException e) {
            return Result.error("请选择一个文件！");
        }
        return Result.OK(pathFileName);
    }

    /**
     * 下载文件
     *
     * @param vo
    {
        "diskId": "网盘空间ID",
        "fileIds": [
            "文件ID集合"
        ]
    }
     * @param response
     * @return
     */
    @ApiOperation(value="下载文件")
    @PostMapping(value = "/download")
    public Result<?> download(@RequestBody ResourceVO vo, HttpServletResponse response) {

        return Result.OK();
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
    @ApiOperation(value="删除文件")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestBody ResourceVO vo) {
        // TODO 根据 fileIds 更新 resource 表中 del_flag 的数据
        return Result.OK();
    }


}
