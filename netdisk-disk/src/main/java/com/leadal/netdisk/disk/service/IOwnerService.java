package com.leadal.netdisk.disk.service;

import cn.hutool.json.JSONObject;
import com.leadal.netdisk.disk.model.Owner;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 拥有者表 服务类
 * </p>
 *
 * @author yf
 * @since 2022-04-25
 */
public interface IOwnerService extends IService<Owner> {

    JSONObject getUser();
}
