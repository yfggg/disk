package com.leadal.netdisk.disk.model;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.leadal.netdisk.common.enums.FileKind;
import com.leadal.netdisk.common.model.BaseModel;
import com.baomidou.mybatisplus.annotation.TableName;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.Date;

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


    public File(String id, String diskId, String folderId, String name, Long size, String type, FileKind kind, String resourceId) {
        super();
        this.id = id;
        this.diskId = diskId;
        this.folderId = folderId;
        this.name = name;
        this.size = size;
        this.type = type;
        this.kind = kind;
        this.resourceId = resourceId;
    }

    public File(String delFlag) {
        super();
        this.delFlag = delFlag;
    }



}
