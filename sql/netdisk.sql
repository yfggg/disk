

DROP TABLE IF EXISTS ndk_owner;
CREATE TABLE `ndk_owner` (
`id` varchar(32) NOT NULL COMMENT 'ID',
`user_name` varchar(150) DEFAULT NULL COMMENT '拥有者名称',
`status` char(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
`del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
`create_by` varchar(64) DEFAULT '' COMMENT '创建者',
`create_time` datetime DEFAULT NULL COMMENT '创建时间',
`update_by` varchar(64) DEFAULT '' COMMENT '更新者',
`update_time` datetime DEFAULT NULL COMMENT '更新时间',
`remark` varchar(500) DEFAULT NULL COMMENT '备注',
PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='拥有者表';

DROP TABLE IF EXISTS ndk_file;
CREATE TABLE `ndk_file` (
`id` varchar(32) NOT NULL COMMENT 'ID',
`disk_id` varchar(32) NOT NULL COMMENT '网盘空间ID',
`owner_id` varchar(32) DEFAULT NULL COMMENT '拥有者ID',

`table_kind` char(1) NOT NULL COMMENT '数据表类别（0文件夹 1文件）',

`folder_name` varchar(150) DEFAULT NULL COMMENT '文件夹名称',
`folder_parent_id` varchar(32) DEFAULT NULL COMMENT '上级文件夹ID',
`folder_parent_ids` varchar(500) DEFAULT NULL COMMENT '上级文件夹ID集',

`resource_id` varchar(32) DEFAULT NULL COMMENT '服务器资源ID',
`file_name` varchar(150) DEFAULT NULL COMMENT '文件名称',
`file_size` BIGINT DEFAULT NULL COMMENT '文件大小',
`file_type` varchar(50) DEFAULT NULL COMMENT '文件类型（.xls .ppt...）',
`file_kind` char(1) DEFAULT NULL COMMENT '文件类别（0文档 1图片 2视频 3音频 4压缩文件）',

`status` char(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
`del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
`create_by` varchar(64) DEFAULT '' COMMENT '创建者',
`create_time` datetime DEFAULT NULL COMMENT '创建时间',
`update_by` varchar(64) DEFAULT '' COMMENT '更新者',
`update_time` datetime DEFAULT NULL COMMENT '更新时间',
`remark` varchar(500) DEFAULT NULL COMMENT '备注',
PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文件表';


/**
 * com.leadal.netdisk.disk.personal
 */
DROP TABLE IF EXISTS ndk_personal_disk;
CREATE TABLE `ndk_personal_disk` (
`id` varchar(32) NOT NULL COMMENT 'ID',
`owner_id` varchar(32) DEFAULT NULL COMMENT '拥有者ID',
`name` varchar(150) DEFAULT NULL COMMENT '空间名称',
`allocate_space` BIGINT DEFAULT NULL COMMENT '分配空间大小',
`used_space` BIGINT DEFAULT NULL COMMENT '已使用空间大小',
`remaie_space` BIGINT DEFAULT NULL COMMENT '剩余空间大小',
`sequence` BIGINT DEFAULT 10 COMMENT '排列序号（间距10）',
`status` char(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
`del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
`create_by` varchar(64) DEFAULT '' COMMENT '创建者',
`create_time` datetime DEFAULT NULL COMMENT '创建时间',
`update_by` varchar(64) DEFAULT '' COMMENT '更新者',
`update_time` datetime DEFAULT NULL COMMENT '更新时间',
`remark` varchar(500) DEFAULT NULL COMMENT '备注',
PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='个人网盘空间表';

/**
 * com.leadal.netdisk.resource
 */
DROP TABLE IF EXISTS ndk_resource;
CREATE TABLE `ndk_resource` (
`id` varchar(32) NOT NULL COMMENT 'ID',
`file_id` varchar(32) DEFAULT NULL COMMENT '文件ID',
`size` BIGINT DEFAULT NULL COMMENT '文件大小',
`type` varchar(50) DEFAULT NULL COMMENT '文件类型（.xls .ppt...）',
`md5` varchar(150) DEFAULT NULL COMMENT 'md5摘要',
`status` char(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
`del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
`create_by` varchar(64) DEFAULT '' COMMENT '创建者',
`create_time` datetime DEFAULT NULL COMMENT '创建时间',
`update_by` varchar(64) DEFAULT '' COMMENT '更新者',
`update_time` datetime DEFAULT NULL COMMENT '更新时间',
`remark` varchar(500) DEFAULT NULL COMMENT '备注',
PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='服务器资源表';

DROP TABLE IF EXISTS ndk_recyle;
CREATE TABLE `ndk_recyle` (
`id` varchar(32) NOT NULL COMMENT 'ID',
`disk_id` varchar(32) NOT NULL COMMENT '网盘空间ID',
`file_id` varchar(32) DEFAULT NULL COMMENT '文件ID',
`create_id` varchar(32) DEFAULT NULL COMMENT '创建者ID',
`status` char(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
`del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
`create_by` varchar(64) DEFAULT '' COMMENT '创建者',
`create_time` datetime DEFAULT NULL COMMENT '创建时间',
`update_by` varchar(64) DEFAULT '' COMMENT '更新者',
`update_time` datetime DEFAULT NULL COMMENT '更新时间',
`remark` varchar(500) DEFAULT NULL COMMENT '备注',
PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='回收站表';
