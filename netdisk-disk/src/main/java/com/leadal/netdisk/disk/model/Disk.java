package com.leadal.netdisk.disk.model;


import com.leadal.netdisk.common.model.BaseModel;
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
@ApiModel(value = "Disk对象", description = "网盘空间")
public class Disk extends BaseModel {

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
