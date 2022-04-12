package com.leadal.netdisk.disk.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.leadal.netdisk.common.model.BaseModel;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * <p>
 * 网盘空间表
 * </p>
 *
 * @author yf
 * @since 2022-04-07
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@TableName("ndk_disk")
@ApiModel(value = "Disk对象", description = "网盘空间表")
public class Disk extends BaseModel {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    protected String id;

    @ApiModelProperty("空间名称")
    private String name;

    @ApiModelProperty("分配空间大小")
    private Long allocateSpace;

    @ApiModelProperty("已使用空间大小")
    private Long usedSpace;

    @ApiModelProperty("剩余空间大小")
    private Long remaieSpace;

    @ApiModelProperty("排列序号（间距10）")
    private Long sequence;









}
