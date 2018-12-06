/*
Navicat MySQL Data Transfer

Source Server         : 本机
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : framework

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-12-06 18:01:14
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for my_demo
-- ----------------------------
DROP TABLE IF EXISTS `my_demo`;
CREATE TABLE `my_demo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(20) NOT NULL COMMENT '姓名',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='demo表';

-- ----------------------------
-- Records of my_demo
-- ----------------------------

-- ----------------------------
-- Table structure for pf_notice
-- ----------------------------
DROP TABLE IF EXISTS `pf_notice`;
CREATE TABLE `pf_notice` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `title` varchar(50) NOT NULL COMMENT '标题',
  `type` int(11) DEFAULT NULL COMMENT '类型',
  `content` varchar(500) NOT NULL COMMENT '内容',
  `publish_time` datetime NOT NULL COMMENT '发布时间',
  `creater` bigint(20) NOT NULL COMMENT '创建人ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='通知表';

-- ----------------------------
-- Records of pf_notice
-- ----------------------------

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `parent_id` bigint(20) NOT NULL COMMENT '上级部门ID，一级部门为0',
  `name` varchar(30) NOT NULL COMMENT '部门名称',
  `order_num` int(11) NOT NULL DEFAULT '1' COMMENT '排序',
  `del_flag` int(11) DEFAULT NULL COMMENT '逻辑删除-1已删除，0正常',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='部门表';

-- ----------------------------
-- Records of sys_dept
-- ----------------------------

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '日志ID',
  `username` varchar(30) NOT NULL COMMENT '账户',
  `operation` varchar(10) NOT NULL COMMENT '操作人',
  `method` varchar(100) NOT NULL COMMENT '方法',
  `params` varchar(1000) DEFAULT NULL COMMENT '参数',
  `time` bigint(20) DEFAULT NULL COMMENT '执行时长',
  `ip` varchar(20) DEFAULT NULL COMMENT 'ip地址',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='日志表';

-- ----------------------------
-- Records of sys_log
-- ----------------------------

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) unsigned DEFAULT NULL,
  `name` varchar(10) NOT NULL,
  `url` varchar(200) DEFAULT NULL,
  `perms` varchar(200) DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
  `type` int(1) NOT NULL COMMENT '类型',
  `icon` varchar(200) NOT NULL DEFAULT '' COMMENT '菜单图标',
  `order_num` int(11) NOT NULL DEFAULT '1' COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='菜单表';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', null, '系统管理', 'string', 'string', '0', 'el-icon-setting', '0');
INSERT INTO `sys_menu` VALUES ('2', null, '会员管理', null, null, '0', 'icon-ali-fenlei', '1');
INSERT INTO `sys_menu` VALUES ('3', '1', '用户管理', null, null, '0', 'icon-ali-jiaoseguanli', '1');
INSERT INTO `sys_menu` VALUES ('4', '1', '菜单管理', null, null, '0', 'el-icon-menu', '2');
INSERT INTO `sys_menu` VALUES ('5', '2', '超级会员', null, null, '0', '', '1');
INSERT INTO `sys_menu` VALUES ('6', '2', '普通会员', null, null, '0', '', '1');
INSERT INTO `sys_menu` VALUES ('7', '5', '大会员', null, null, '1', '', '1');
INSERT INTO `sys_menu` VALUES ('8', null, '水电费', null, null, '0', 'icon-ali-xiaoxi1', '0');
INSERT INTO `sys_menu` VALUES ('11', '6', '地方', null, null, '1', '', '0');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(20) NOT NULL COMMENT '角色名称',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `dept_id` bigint(20) DEFAULT NULL COMMENT '部门ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('11', '管理员', '管理员', null, '2018-12-06 14:49:34');
INSERT INTO `sys_role` VALUES ('12', 'sdfsa', '11', null, '2018-12-06 17:11:14');

-- ----------------------------
-- Table structure for sys_role_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_dept`;
CREATE TABLE `sys_role_dept` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `dept_id` bigint(20) NOT NULL COMMENT '部门ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='角色部门关联表';

-- ----------------------------
-- Records of sys_role_dept
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `menu_id` bigint(20) NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `UK_SYS_ROLE_MENU_ROLE_ID_MENU_ID` (`role_id`,`menu_id`) COMMENT '联合唯一索引',
  KEY `FK_SYS_ROLE_MENU_MENU_ID` (`menu_id`),
  CONSTRAINT `FK_SYS_ROLE_MENU_MENU_ID` FOREIGN KEY (`menu_id`) REFERENCES `sys_menu` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `FK_SYS_ROLE_MENU_ROLE_ID` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='角色菜单关联表';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('7', '12', '4');
INSERT INTO `sys_role_menu` VALUES ('8', '12', '6');
INSERT INTO `sys_role_menu` VALUES ('9', '12', '11');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `name` varchar(30) DEFAULT NULL COMMENT '姓名',
  `username` varchar(30) NOT NULL COMMENT '账户',
  `password` varchar(64) NOT NULL COMMENT '密码',
  `salt` varchar(4) NOT NULL COMMENT '盐',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(20) DEFAULT NULL COMMENT '移动电话',
  `avatar` varchar(200) DEFAULT NULL COMMENT '头像图片',
  `status` int(2) DEFAULT NULL COMMENT '状态  0：禁用   1：正常',
  `dept_id` bigint(20) DEFAULT NULL COMMENT '部门id',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `U_SYS_USER_USERNAME` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', '超级管理员', 'admin', '20a239be517652a4920366a93cca08a4754313b64ec3e67723941d4428b422d7', 'D5Kt', 'wenlongfei_person@163.com', '18877779999', 'https://raw.githubusercontent.com/taylorchen709/markdown-images/master/vueadmin/user.png', '1', null, '2018-10-15 14:22:43');
INSERT INTO `sys_user` VALUES ('6', '张三', 'zhangsan', 'f6062e283d73f2729121229cfc0c0fedca0bbe0c4558dc9859a59595ebaf479a', '7rAF', 'a@b.c', '17589652354', null, '1', null, '2018-11-02 20:46:35');
INSERT INTO `sys_user` VALUES ('11', '温龙飞', 'wenlf', '2104d23aa72e48249da6c065af89d63c19f0aa555af4e007fa952d21ea583403', 'ljD0', null, '17600287268', null, '1', null, '2018-12-04 17:14:26');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `FK_SYS_USER_ROLE_USER_ID` (`user_id`),
  KEY `FK_SYS_USER_ROLE_ROLE_ID` (`role_id`),
  CONSTRAINT `FK_SYS_USER_ROLE_ROLE_ID` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`) ON DELETE CASCADE,
  CONSTRAINT `FK_SYS_USER_ROLE_USER_ID` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户角色关联表';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user_token
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_token`;
CREATE TABLE `sys_user_token` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `token` varchar(64) NOT NULL COMMENT 'token',
  `expire_time` datetime NOT NULL COMMENT '过期时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `U_SYS_USER_TOKEN_USER_ID` (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sys_user_token
-- ----------------------------
INSERT INTO `sys_user_token` VALUES ('1', '3', 'bc9c98e6ac11f093cafb96c79e34f4ba', '2018-10-16 17:13:27', '2018-10-15 17:13:44');
INSERT INTO `sys_user_token` VALUES ('2', '1', '33a651c371f89d5dcbf96223691c7588', '2018-12-07 14:25:59', '2018-12-06 14:25:59');
INSERT INTO `sys_user_token` VALUES ('3', '6', 'c07b0fc3a62e504339c8cb3206aa023e', '2018-11-12 12:35:13', '2018-11-11 12:35:13');
