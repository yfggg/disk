package com.leadal.netdisk.disk.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.leadal.netdisk.common.model.BaseModel;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * <p>
 * 文件夹表
 * </p>
 *
 * @author yf
 * @since 2022-04-07
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@TableName("ndk_folder")
@ApiModel(value = "Folder对象", description = "文件夹表")
public class Folder extends BaseModel {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    protected String id;

    @ApiModelProperty("上级文件夹ID")
    private String parentId;

    @ApiModelProperty("网盘空间ID")
    private String diskId;

    @ApiModelProperty("文件夹名称")
    private String name;

    @ApiModelProperty("排列序号（间距10）")
    private Long sequence;









}
