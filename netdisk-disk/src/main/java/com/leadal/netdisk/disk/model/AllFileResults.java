package com.leadal.netdisk.disk.model;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "AllFileResults对象")
public class AllFileResults implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty("级联路径")
    private List<Folder> path;

    @ApiModelProperty("文件夹列表")
    private FileResults fileResults;

}
