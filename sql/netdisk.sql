
/**
 * com.leadal.netdisk.disk
 */
DROP TABLE IF EXISTS ndk_disk;
CREATE TABLE `ndk_disk` (
`id` varchar(32) NOT NULL COMMENT 'ID',
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='网盘空间表';

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

DROP TABLE IF EXISTS ndk_folder;
CREATE TABLE `ndk_folder` (
`id` varchar(32) NOT NULL COMMENT 'ID',
`disk_id` varchar(32) DEFAULT NULL COMMENT '网盘空间ID',
`name` varchar(150) DEFAULT NULL COMMENT '文件夹名称',
`size` BIGINT DEFAULT NULL COMMENT '文件夹大小',
`sequence` BIGINT DEFAULT 10 COMMENT '排列序号（间距10）',
`status` char(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
`del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
`create_by` varchar(64) DEFAULT '' COMMENT '创建者',
`create_time` datetime DEFAULT NULL COMMENT '创建时间',
`update_by` varchar(64) DEFAULT '' COMMENT '更新者',
`update_time` datetime DEFAULT NULL COMMENT '更新时间',
`remark` varchar(500) DEFAULT NULL COMMENT '备注',
PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文件夹表';

DROP TABLE IF EXISTS ndk_file;
CREATE TABLE `ndk_file` (
`id` varchar(32) NOT NULL COMMENT 'ID',
`disk_id` varchar(32) DEFAULT NULL COMMENT '网盘空间ID',
`folder_id` varchar(32) DEFAULT NULL COMMENT '文件夹ID',
`name` varchar(150) DEFAULT NULL COMMENT '文件名称',
`size` BIGINT DEFAULT NULL COMMENT '文件大小',
`type` varchar(50) DEFAULT NULL COMMENT '文件类型（.xls .ppt...）',
`kind` char(1) DEFAULT NULL COMMENT '文件类别（1文档 2图片 3视频 4音频）',
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
`disk_id` varchar(32) DEFAULT NULL COMMENT '网盘空间ID',
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