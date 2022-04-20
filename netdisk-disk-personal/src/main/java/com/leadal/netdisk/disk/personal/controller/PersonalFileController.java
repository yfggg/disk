package com.leadal.netdisk.disk.personal.controller;


import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.leadal.netdisk.common.exception.InvalidExtensionException;
import com.leadal.netdisk.common.model.Result;
import com.leadal.netdisk.common.util.file.DateUtils;
import com.leadal.netdisk.common.util.file.FileUploadUtils;
import com.leadal.netdisk.disk.enums.TableKind;
import com.leadal.netdisk.disk.model.File;
import com.leadal.netdisk.disk.service.IFileService;
import com.leadal.netdisk.disk.view.FileVO;
import com.leadal.netdisk.resource.service.IResourceService;
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

    private static final String DISK_ID = "1";

    @Resource
    private IFileService fileService;

    @Resource
    private IResourceService resourceService;

    /**
     * 移动文件
     *
     * {
     *   "fileIds": [
     *     "传输文件id集"
     *   ],
     *   "folderId": "目标文件夹id"
     * }
     * @return
     */
    @ApiOperation(value="移动文件")
    @PutMapping(value = "/move")
    public Result<?> move(@RequestBody FileVO vo) {

        List<File> finas = fileService.moveAndCopyById(DISK_ID, vo, false);
        boolean result = fileService.updateBatchById(finas);

        if(!result) {
            return Result.error("移动文件失败！");
        }

        return Result.OK();
    }

    /**
     * 复制文件
     *
     * {
     *   "fileIds": [
     *     "传输文件id集"
     *   ],
     *   "folderId": "目标文件夹id"
     * }
     * @return
     */
    @ApiOperation(value="复制文件")
    @PostMapping(value = "/copy")
    public Result<?> copy(@RequestBody FileVO vo) {

        List<File> finas = fileService.moveAndCopyById(DISK_ID, vo, true);
        boolean result = fileService.saveBatch(finas);

        if(!result) {
            return Result.error("复制文件失败！");
        }

        return Result.OK();
    }

    /**
     * 上传文件
     *
     * @param mFiles [] 上传文件集
     * @param folderIds [] 目标-文件夹ID集
     * @return
     */
    @ApiOperation(value="上传文件")
    @PostMapping(value = "/upload")
    public Result<?> upload(MultipartFile[] mFiles, String[] folderIds) {

        try {

            for (MultipartFile mFile : mFiles) {
                String resouseId = IdUtil.simpleUUID();
                boolean result = resourceService.tSave(DISK_ID, mFile, folderIds, resouseId);
                if(result) {
                    FileUploadUtils.upload(mFile, resouseId);
                }
            }

        } catch (NullPointerException e) {
            e.printStackTrace();
            if(null == mFiles) {
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
     * @param resouseId 服务器资源ID
     * @param realFileName 文件名 例如 新建文本文档2.txt 记得要带后缀
     * @param response
     */
    @ApiOperation(value="下载文件")
    @GetMapping(value = "/download")
    public void download(String resouseId, String realFileName, HttpServletResponse response) {
        resourceService.download(resouseId, realFileName, response);
    }

    /**
     * 删除文件
     *
     * {
     *   "fileIds": [
     *     "传输文件id集"
     *   ]
     * }
     * @return
     */
    @ApiOperation(value="删除文件")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestBody FileVO vo) {

        List<String> fileIds = vo.getFileIds();

        QueryWrapper<File> fWrapper = new QueryWrapper<>();
        fWrapper
                .in("id", fileIds)
                .eq("disk_id", DISK_ID)
                .eq("table_kind", TableKind.FILE)
                .eq("del_flag", "0");
        File file = new File();
        file.setDelFlag("1");

        boolean result = fileService.update(file, fWrapper);

        if(!result) {
            return Result.error("删除文件失败！");
        }

        return Result.OK();
    }





}
