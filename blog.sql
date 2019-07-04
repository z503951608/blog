/*
Navicat MySQL Data Transfer

Source Server         : 192.168.0.162
Source Server Version : 50726
Source Host           : 192.168.0.162:3306
Source Database       : blog

Target Server Type    : MYSQL
Target Server Version : 50726
File Encoding         : 65001

Date: 2019-07-04 08:41:07
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for cms_article
-- ----------------------------
DROP TABLE IF EXISTS `cms_article`;
CREATE TABLE `cms_article` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '文章标题',
  `public_time` datetime DEFAULT NULL COMMENT '发布时间',
  `content` longtext COLLATE utf8mb4_bin COMMENT '文章类容',
  `navigation_id` int(11) DEFAULT NULL COMMENT '标题ID对应cms_navigation表ID',
  `is_show` int(1) DEFAULT NULL COMMENT '是否显示',
  `image` varchar(1000) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '标题图像',
  `views` int(11) DEFAULT '0' COMMENT '浏览量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='文章';

-- ----------------------------
-- Table structure for cms_article_image
-- ----------------------------
DROP TABLE IF EXISTS `cms_article_image`;
CREATE TABLE `cms_article_image` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `article_id` int(11) DEFAULT NULL COMMENT '文章ID',
  `image_url` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '文章中图片url',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='附件';

-- ----------------------------
-- Table structure for cms_classify
-- ----------------------------
DROP TABLE IF EXISTS `cms_classify`;
CREATE TABLE `cms_classify` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parent_id` int(11) DEFAULT NULL COMMENT '父ID',
  `class_name` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '类别名称',
  `is_order` int(11) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='分类';

-- ----------------------------
-- Table structure for cms_comment
-- ----------------------------
DROP TABLE IF EXISTS `cms_comment`;
CREATE TABLE `cms_comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `article_id` int(11) DEFAULT NULL COMMENT '文章ID',
  `comment` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '评价类容',
  `add_time` datetime DEFAULT NULL COMMENT '评价时间',
  `is_show` int(1) DEFAULT NULL COMMENT '是否显示',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='评价表';

-- ----------------------------
-- Table structure for cms_navigation
-- ----------------------------
DROP TABLE IF EXISTS `cms_navigation`;
CREATE TABLE `cms_navigation` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '名称',
  `is_show` char(1) CHARACTER SET utf8 DEFAULT NULL COMMENT '是否显示',
  `is_order` int(2) DEFAULT NULL COMMENT '排序',
  `url` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '菜单路径',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='导航';

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
  `name` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(200) DEFAULT NULL COMMENT '菜单URL',
  `perms` varchar(500) DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
  `type` int(11) DEFAULT NULL COMMENT '类型   0：目录   1：菜单   2：按钮  3: 模块  4 :页面',
  `icon` varchar(50) DEFAULT NULL COMMENT '菜单图标',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  `title` varchar(50) DEFAULT NULL COMMENT '标题',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  `isDelete` tinyint(2) DEFAULT NULL COMMENT '是否删除',
  `status` tinyint(2) DEFAULT NULL COMMENT '状态',
  `descr` varchar(255) DEFAULT NULL,
  `requestMethod` varchar(16) DEFAULT NULL COMMENT '请求方式 0 get,1 post,2put 3 delete',
  `res_url` varchar(500) DEFAULT NULL,
  `component` varchar(200) DEFAULT NULL COMMENT '组件路径',
  `alias` varchar(64) DEFAULT NULL COMMENT '别名',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10508 DEFAULT CHARSET=utf8 COMMENT='菜单管理';

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(100) DEFAULT NULL COMMENT '角色名称',
  `role_sign` varchar(100) DEFAULT NULL COMMENT '角色标识',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `user_id_create` bigint(255) DEFAULT NULL COMMENT '创建用户id',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '创建时间',
  `is_delete` tinyint(2) DEFAULT NULL COMMENT '是否删除',
  `status` tinyint(2) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='角色';

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `menu_id` bigint(20) DEFAULT NULL COMMENT '菜单ID',
  `menu_type` tinyint(2) DEFAULT '0' COMMENT '是否全选 0是 1否',
  PRIMARY KEY (`id`),
  KEY `FK_main_role_id` (`role_id`) USING BTREE,
  CONSTRAINT `sys_role_menu_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`role_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色与菜单对应关系';

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `name` varchar(100) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL COMMENT '密码',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(100) DEFAULT NULL COMMENT '手机号',
  `status` tinyint(255) DEFAULT NULL COMMENT '状态 0:禁用，1:正常',
  `user_id_create` bigint(255) DEFAULT NULL COMMENT '创建用户id',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  `sex` bigint(32) DEFAULT NULL COMMENT '性别',
  `birth` datetime DEFAULT NULL COMMENT '阳历生日出身日期',
  `pic_id` bigint(32) DEFAULT NULL,
  `live_address` varchar(500) DEFAULT NULL COMMENT '现居住地',
  `hobby` varchar(255) DEFAULT NULL COMMENT '爱好',
  `province` varchar(255) DEFAULT NULL COMMENT '省份',
  `city` varchar(255) DEFAULT NULL COMMENT '所在城市',
  `district` varchar(255) DEFAULT NULL COMMENT '所在地区',
  `isDelete` tinyint(2) DEFAULT NULL,
  `accountNum` varchar(255) DEFAULT NULL COMMENT '账号',
  `weiXinNum` varchar(255) DEFAULT NULL COMMENT '微信号',
  `qqNum` varchar(255) DEFAULT NULL COMMENT 'qq号',
  `identity` varchar(32) DEFAULT NULL COMMENT '身份证号',
  `lunarCalendar` datetime DEFAULT NULL COMMENT '农历生日',
  `mark` varchar(255) DEFAULT NULL COMMENT '备注',
  `namePinYin` varchar(255) DEFAULT NULL COMMENT '姓名拼音',
  `pid` int(11) DEFAULT NULL COMMENT '父账号id',
  `avatar` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  KEY `username_idx` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `is_delete` tinyint(2) DEFAULT NULL COMMENT '是否删除',
  `status` tinyint(2) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户与角色对应关系';
