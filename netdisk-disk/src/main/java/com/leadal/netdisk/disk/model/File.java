package com.leadal.netdisk.disk.model;

import com.leadal.netdisk.disk.enums.FileKind;
import com.leadal.netdisk.common.model.BaseModel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.leadal.netdisk.disk.enums.TableKind;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * <p>
 * 文件表
 * </p>
 *
 * @author yf
 * @since 2022-04-18
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@TableName("ndk_file")
@ApiModel(value = "File对象", description = "文件表")
public class File extends BaseModel {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("ID")
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    @ApiModelProperty("网盘空间ID")
    private String diskId;

    @ApiModelProperty("数据表类别（0文件夹 1文件）")
    private TableKind tableKind;

    @ApiModelProperty("文件夹名称")
    private String folderName;

    @ApiModelProperty("上级文件夹ID")
    private String folderParentId;

    @ApiModelProperty("上级文件夹ID集")
    private String folderParentIds;

    @ApiModelProperty("服务器资源ID")
    private String resourceId;

    @ApiModelProperty("文件名称")
    private String fileName;

    @ApiModelProperty("文件大小")
    private Long fileSize;

    @ApiModelProperty("文件类型（.xls .ppt...）")
    private String fileType;

    @ApiModelProperty("文件类别（0文档 1图片 2视频 3音频 4压缩文件）")
    private FileKind fileKind;

    public File(String diskId, String folderName, String folderParentId, String folderParentIds, TableKind tableKind) {
        this.diskId = diskId;
        this.folderName = folderName;
        this.folderParentId = folderParentId;
        this.folderParentIds = folderParentIds;
        this.tableKind = tableKind;
    }

    public String getDelFlag() {
        return this.delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }



}
