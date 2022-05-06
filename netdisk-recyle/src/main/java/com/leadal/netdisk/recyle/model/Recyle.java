package com.leadal.netdisk.recyle.model;

import com.leadal.netdisk.common.model.BaseModel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * <p>
 * 回收站表
 * </p>
 *
 * @author yf
 * @since 2022-05-05
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@TableName("ndk_recyle")
@ApiModel(value = "Recyle对象", description = "回收站表")
public class Recyle extends BaseModel {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("ID")
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    @ApiModelProperty("网盘空间ID")
    private String diskId;

    @ApiModelProperty("文件ID")
    private String fileId;

    @ApiModelProperty("创建者ID")
    private String createId;


    public Recyle(String diskId, String fileId) {
        this.diskId = diskId;
        this.fileId = fileId;
    }


    public Recyle(String delFlag) {
        setDelFlag(delFlag);
    }

    public String getDelFlag() {
        return this.delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

}
