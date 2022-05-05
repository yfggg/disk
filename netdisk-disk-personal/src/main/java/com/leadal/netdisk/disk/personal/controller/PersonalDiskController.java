package com.leadal.netdisk.disk.personal.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.leadal.doconvert.client.Doconvert;
import com.leadal.doconvert.client.callback.ConvertorCallback;
import com.leadal.netdisk.common.model.Result;
import com.leadal.netdisk.disk.enums.FileKind;
import com.leadal.netdisk.disk.enums.TableKind;
import com.leadal.netdisk.disk.model.File;
import com.leadal.netdisk.disk.model.FileResult;
import com.leadal.netdisk.disk.service.IFileService;
import com.leadal.netdisk.resource.service.IResourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;


/**
 * <p>
 * 个人网盘空间表 前端控制器
 * </p>
 *
 * @author yf
 * @since 2022-04-07
 */
@Api(tags="个人网盘-控制器")
@Slf4j
@RestController
@RequestMapping("/perdisk/disk")
public class PersonalDiskController {

    private static final String DISK_ID = "1";

    @Resource
    private IFileService fileService;

    @Resource
    private IResourceService resourceService;

    /**
     * 搜索 同时搜索出文件和文件夹 只从全部文件开始搜索
     *
     * @param keyword 关键字
     * @return
     */
    @ApiOperation(value="关键字搜索")
    @GetMapping(value = "/queryKeyword")
    public Result<?> keywordQuery(String keyword) {

        QueryWrapper<File> fWrapper = new QueryWrapper<>();
        fWrapper
                .like("folder_name", keyword)
                .or()
                .like("file_name", keyword)
                .eq("disk_id", DISK_ID)
                .eq("del_flag", "0");
        List<File> files = fileService.list(fWrapper);

        // 数量
        int total = files.size();

        return Result.OK(total, files);
    }

    /**
     * 文件列表(进入文件夹)
     *
     * @param id 文件夹ID
     * @param pageNo   当前页
     * @param pageSize 每页展示的数量
     * @return
     */
    @ApiOperation(value="文件列表(进入文件夹)")
    @GetMapping(value = "/query")
    public Result<?> query(String id,
                           @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                           @RequestParam(name="pageSize", defaultValue="10") Integer pageSize) {

        // 文件和文件夹(分页)
        QueryWrapper<File> eqWrapper = new QueryWrapper<>();
        eqWrapper
                .and(wrapper ->
                    wrapper
                        .eq("table_kind", TableKind.FOLDER)
                        .eq("folder_parent_id", id)
                )
                .or(wrapper ->
                    wrapper
                        .eq("table_kind", TableKind.FILE)
                        .likeLeft("folder_parent_ids", id)
                )
                .eq("disk_id", DISK_ID)
                .eq("del_flag", "0")
                .orderByAsc("create_time");
        Page<File> page = new Page<>(pageNo, pageSize);
        IPage<File> pageFiles = fileService.page(page, eqWrapper);

        // 加入文件预览地址
        List<File> files = resourceService.addUrl(pageFiles.getRecords());
        pageFiles.setRecords(files);

        // 级联路径
        QueryWrapper<File> likeWrapper = new QueryWrapper<>();
        likeWrapper
                .likeLeft("folder_parent_ids", id)
                .eq("disk_id", DISK_ID)
                .eq("table_kind", TableKind.FOLDER)
                .eq("del_flag", "0");
        File file = fileService.getOne(likeWrapper);
        String[] folderParentIds = file.getFolderParentIds().split(",");
        QueryWrapper<File> inWrapper = new QueryWrapper<>();
        inWrapper
                .in("id", folderParentIds)
                .eq("disk_id", DISK_ID)
                .eq("table_kind", TableKind.FOLDER)
                .eq("del_flag", "0")
                .orderByAsc("create_time");
        List<File> pathTree = fileService.list(inWrapper);

        FileResult fileResult = new FileResult(pageFiles, pathTree);

        return Result.OK(fileResult);
    }

    @ApiOperation(value="文档列表")
    @GetMapping(value = "/documentQuery")
    public Result<?> documentQuery(String code,
                           @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                           @RequestParam(name="pageSize", defaultValue="10") Integer pageSize) {

        QueryWrapper<File> wrapper = new QueryWrapper<>();

        wrapper
                .eq("table_kind", TableKind.FILE)
                .eq("file_kind", FileKind.DOCUMENT)
                .eq("disk_id", DISK_ID)
                .eq("del_flag", "0")
                .orderByAsc("create_time");
        Page<File> page = new Page<>(pageNo, pageSize);
        IPage<File> pageFiles = fileService.page(page, wrapper);

        // 加入文件预览地址
        List<File> files = resourceService.addUrl(pageFiles.getRecords());
        pageFiles.setRecords(files);

        return Result.OK(pageFiles);
    }

//    /**
//     * 各类型列表
//     *
//     * @param code 例如输入"0"表示查询文档列表（0文档 1图片 2视频 3音频 4压缩文件）
//     * @param pageNo   当前页
//     * @param pageSize 每页展示的数量
//     * @return
//     */
//    @ApiOperation(value="各类型列表")
//    @GetMapping(value = "/kindQuery")
//    public Result<?> documentQuery(String code,
//                           @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
//                           @RequestParam(name="pageSize", defaultValue="10") Integer pageSize) {
//
//        QueryWrapper<File> eqWrapper = new QueryWrapper<>();
//
//        FileKind kind = null;
//        switch (code) {
//            case "0":
//                kind = FileKind.DOCUMENT;
//                break;
//            case "1":
//                kind = FileKind.IMAGE;
//                break;
//            case "2":
//                kind = FileKind.VIDEO;
//                break;
//            case "3":
//                kind = FileKind.AUDIO;
//                break;
//            case "4":
//                kind = FileKind.COMPRESSION;
//                break;
//        }
//
//        eqWrapper
//                .eq("table_kind", TableKind.FILE)
//                .eq("file_kind", kind)
//                .eq("disk_id", DISK_ID)
//                .eq("del_flag", "0")
//                .orderByAsc("create_time");
//        Page<File> page = new Page<>(pageNo, pageSize);
//        IPage<File> pageFiles = fileService.page(page, eqWrapper);
//
//        // 加入文件预览地址
//        List<File> files = resourceService.addUrl(pageFiles.getRecords());
//        pageFiles.setRecords(files);
//
//        return Result.OK(pageFiles);
//    }

//    public static void main(String[] args) {
//        //源文件路径及名称
//        String src = "D:\\tmp\\test2.jpg";
//        //目标文件路径及名称
//        String desc = "D:\\tmp\\test2998.pdf";
//        Doconvert.create().setUrl("11.1.0.99:8086").setSrc(src).setDesc(desc).execute(new ConvertorCallback() {
//            @Override
//            public void execute(java.io.File file) {
//                //file为转换后的文件
//                System.out.println("转换完成");
//            }
//        });
//    }



}
