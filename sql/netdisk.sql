
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

/*------------------------------------- 文件和文件夹集合表 -----------------------------------------*/

DROP TABLE IF EXISTS ndk_file;
CREATE TABLE `ndk_file` (
`id` varchar(32) NOT NULL COMMENT 'ID',
`disk_id` varchar(32) NOT NULL COMMENT '网盘空间ID',

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

/*--------------------------------- 文件夹 ---------------------------------------------*/

/* 新建文件夹 输入: folder_name, folder_parent_id */
INSERT into ndk_file
(id, disk_id, table_kind, folder_name, folder_parent_id, folder_parent_ids, create_time)
VALUES
(replace(uuid(),'-',''), '1', '0', 'x军', '806334271bd18e08caeaa0005299a7ab', '806334271bd18e08caeaa0005299a7ab', NOW())

/* 重命名文件夹 输入: id */
UPDATE ndk_file SET folder_name = 'x军改' WHERE id = '6d2bfa74bebb11ec852c000ec64a7891' and disk_id = '1' and table_kind = 0

/* 列表文件夹 输入: folder_parent_id */
SELECT * FROM ndk_file WHERE folder_parent_id = '6d2bfa74bebb11ec852c000ec64a7891' and disk_id = '1' and table_kind = 0

/*------------------------------------ 文件 ------------------------------------------*/

/*上传文件 输入: folder_parent_id */
insert into ndk_resource
(id, size, type,  md5, create_time)
VALUES
(replace(uuid(),'-',''), 167, 'txt', 'd9d2586f9bfa91828046ae24344b4d5b', NOW())

INSERT INTO ndk_file (id, disk_id, table_kind,
folder_name, folder_parent_id, folder_parent_ids,
resource_id, file_name, file_type, file_size, file_kind, create_time)
VALUES
(replace(uuid(),'-',''), '1', '1',
'根目录', NULL, NULL,
'3638e656bec711ec852c000ec64a7891', '测试文件', 'txt', 127, 0, NOW())

/*逻辑删除文件 输入: ids */
UPDATE ndk_file SET del_flag = '1' WHERE id in( '806334271bd18e08caeaa0005299a7ab','fd6445a2bec911ec852c000ec64a7891')
and disk_id = '1' and table_kind = 1

/*移动文件 输入: ids, folder_name, folder_parent_id, folder_parent_ids */
UPDATE ndk_file SET folder_name = 'x军', folder_parent_id = '806334271bd18e08caeaa0005299a7ab', folder_parent_ids = '806334271bd18e08caeaa0005299a7ab'
WHERE id in( '806334271bd18e08caeaa0005299a7ab','fd6445a2bec911ec852c000ec64a7891') and disk_id = '1' and table_kind = 1

/*复制文件 输入: ids, folder_name, folder_parent_id, folder_parent_ids,
resource_id, file_name, file_type, file_size, file_kind, */
INSERT INTO ndk_file (id, disk_id, table_kind,
folder_name, folder_parent_id, folder_parent_ids,
resource_id, file_name, file_type, file_size, file_kind, create_time)
VALUES
(replace(uuid(),'-',''), '1', '1',
'根目录', NULL, NULL,
'3638e656bec711ec852c000ec64a7891', '测试文件', 'txt', 127, 0, NOW())

/*------------------------------------ 列表 ------------------------------------------*/

/*文件列表(进入文件夹) 输入: folder_parent_id */
SELECT * FROM ndk_file
WHERE folder_parent_id = '806334271bd18e08caeaa0005299a7ab' and
del_flag = '0' AND disk_id = '1' AND table_kind in('0', '1')

/*关键字搜索 输入: keyword */
SELECT * FROM ndk_file
WHERE (folder_name like '%根目录%' or file_name like '%根目录%')
and del_flag = '0' AND disk_id = '1' AND table_kind in('0', '1')
