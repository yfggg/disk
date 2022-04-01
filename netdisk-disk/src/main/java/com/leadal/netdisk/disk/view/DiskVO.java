package com.leadal.netdisk.disk.view;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * <p>
 * 网盘表
 * </p>
 *
 * @author yf
 * @since 2022-04-01
 */
@Getter
@Setter
@ApiModel(value = "DiskVO对象", description = "网盘表")
public class DiskVO {

    @ApiModelProperty("名称")
    private String name;









}
