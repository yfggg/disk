
DROP TABLE IF EXISTS disk;
CREATE TABLE `disk` (
`id` varchar(32) NOT NULL COMMENT 'ID',
`name` varchar(32) NOT NULL COMMENT '名称',
`status` char(1) DEFAULT '0' COMMENT '帐号状态（0正常 1停用）',
`del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
`create_by` varchar(64) DEFAULT '' COMMENT '创建者',
`create_time` datetime DEFAULT NULL COMMENT '创建时间',
`update_by` varchar(64) DEFAULT '' COMMENT '更新者',
`update_time` datetime DEFAULT NULL COMMENT '更新时间',
`remark` varchar(500) DEFAULT NULL COMMENT '备注',
PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='网盘表';

DROP TABLE IF EXISTS personal;
CREATE TABLE `personal` (
`id` varchar(32) NOT NULL COMMENT 'ID',
`name` varchar(32) NOT NULL COMMENT '姓名',
`status` char(1) DEFAULT '0' COMMENT '帐号状态（0正常 1停用）',
`del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
`create_by` varchar(64) DEFAULT '' COMMENT '创建者',
`create_time` datetime DEFAULT NULL COMMENT '创建时间',
`update_by` varchar(64) DEFAULT '' COMMENT '更新者',
`update_time` datetime DEFAULT NULL COMMENT '更新时间',
`remark` varchar(500) DEFAULT NULL COMMENT '备注',
PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';