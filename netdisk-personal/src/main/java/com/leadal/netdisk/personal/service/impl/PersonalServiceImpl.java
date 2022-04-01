package com.leadal.netdisk.personal.service.impl;

import com.leadal.netdisk.personal.model.entity.Personal;
import com.leadal.netdisk.personal.dao.PersonalMapper;
import com.leadal.netdisk.personal.service.IPersonalService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author yf
 * @since 2022-04-01
 */
@Service
public class PersonalServiceImpl extends ServiceImpl<PersonalMapper, Personal> implements IPersonalService {

}
