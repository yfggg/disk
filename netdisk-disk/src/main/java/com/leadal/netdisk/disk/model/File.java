package com.leadal.netdisk.disk.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.leadal.netdisk.common.model.BaseModel;
import com.baomidou.mybatisplus.annotation.TableName;

import com.leadal.netdisk.disk.enums.FileKind;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * <p>
 * 文件表
 * </p>
 *
 * @author yf
 * @since 2022-04-07
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@TableName("ndk_file")
@ApiModel(value = "File对象", description = "文件表")
public class File extends BaseModel {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    protected String id;

    @ApiModelProperty("网盘空间ID")
    private String diskId;

    @ApiModelProperty("文件夹ID")
    private String folderId;

    @ApiModelProperty("服务器资源ID")
    private String resourceId;

    @ApiModelProperty("文件名称")
    private String name;

    @ApiModelProperty("文件大小")
    private Long size;

    @ApiModelProperty("文件类型（.xls .ppt...）")
    private String type;

    @ApiModelProperty("文件类别（0文档 1图片 2视频 3音频 4压缩文件）")
    private FileKind kind;









}
