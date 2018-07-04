/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50710
Source Host           : localhost:3306
Source Database       : test_user_performance

Target Server Type    : MYSQL
Target Server Version : 50710
File Encoding         : 65001

Date: 2018-06-27 22:24:25
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_app
-- ----------------------------
DROP TABLE IF EXISTS `sys_app`;
CREATE TABLE `sys_app` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) NOT NULL COMMENT '名称',
  `sort` int(11) NOT NULL COMMENT '排序',
  `createTime` datetime NOT NULL COMMENT '创建时间',
  `isEnable` int(1) NOT NULL COMMENT '是否启用',
  `code` varchar(16) NOT NULL COMMENT '编码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=82 DEFAULT CHARSET=utf8 COMMENT='应用表';

-- ----------------------------
-- Records of sys_app
-- ----------------------------
INSERT INTO `sys_app` VALUES ('1', '单点登录权限管理系统', '20', '2015-06-02 11:31:44', '1', 'smart-sso-server');
INSERT INTO `sys_app` VALUES ('81', 'Demo管理系统', '15', '2015-11-08 17:16:39', '1', 'smart-sso-demo');

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `appId` int(11) NOT NULL COMMENT '应用ID',
  `parentId` int(11) DEFAULT NULL COMMENT '父ID',
  `name` varchar(50) NOT NULL COMMENT '名称',
  `url` varchar(255) NOT NULL COMMENT '权限URL',
  `sort` int(11) NOT NULL COMMENT '排序',
  `isMenu` int(1) NOT NULL COMMENT '是否菜单',
  `isEnable` int(1) NOT NULL COMMENT '是否启用',
  `icon` varchar(100) DEFAULT NULL COMMENT '图标',
  PRIMARY KEY (`id`),
  KEY `FK_SYS_PERM_REFERENCE_SYS_APP` (`appId`),
  CONSTRAINT `FK_SYS_PERM_REFERENCE_SYS_APP` FOREIGN KEY (`appId`) REFERENCES `sys_app` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8 COMMENT='权限表';

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES ('2', '1', null, '应用', '/admin/app', '39', '1', '1', 'fa-plus-circle blue');
INSERT INTO `sys_permission` VALUES ('3', '1', null, '用户', '/admin/user', '79', '1', '1', 'fa-user');
INSERT INTO `sys_permission` VALUES ('4', '1', null, '角色', '/admin/role', '59', '1', '1', 'fa-users');
INSERT INTO `sys_permission` VALUES ('5', '1', null, '权限', '/admin/permission', '29', '1', '1', 'fa-key');
INSERT INTO `sys_permission` VALUES ('6', '1', '2', '应用新增', '/admin/app/edit', '4', '0', '1', 'fa-plus-circle blue');
INSERT INTO `sys_permission` VALUES ('7', '1', '2', '应用禁用', '/admin/app/enable', '3', '0', '1', 'fa-lock orange');
INSERT INTO `sys_permission` VALUES ('8', '1', '2', '应用启用', '/admin/app/enable', '2', '0', '1', 'fa-unlock green');
INSERT INTO `sys_permission` VALUES ('9', '1', '2', '应用删除', '/admin/app/delete', '1', '0', '1', 'fa-trash-o red');
INSERT INTO `sys_permission` VALUES ('10', '1', '3', '用户新增', '/admin/user/edit', '6', '0', '1', 'fa-plus-circle blue');
INSERT INTO `sys_permission` VALUES ('11', '1', '3', '用户禁用', '/admin/user/enable', '5', '0', '1', 'fa-lock orange');
INSERT INTO `sys_permission` VALUES ('12', '1', '3', '用户启用', '/admin/user/enable', '4', '0', '1', 'fa-unlock green');
INSERT INTO `sys_permission` VALUES ('13', '1', '3', '用户删除', '/admin/user/delete', '3', '0', '1', 'fa-trash-o red');
INSERT INTO `sys_permission` VALUES ('14', '1', '3', '重置密码', '/admin/user/resetPassword', '2', '0', '1', 'fa-key grey');
INSERT INTO `sys_permission` VALUES ('16', '1', '4', '角色新增', '/admin/role/edit', '5', '0', '1', 'fa-plus-circle blue');
INSERT INTO `sys_permission` VALUES ('17', '1', '4', '角色禁用', '/admin/role/enable', '4', '0', '1', 'fa-lock orange');
INSERT INTO `sys_permission` VALUES ('18', '1', '4', '角色启用', '/admin/role/enable', '3', '0', '1', 'fa-unlock green');
INSERT INTO `sys_permission` VALUES ('19', '1', '4', '角色删除', '/admin/role/delete', '2', '0', '1', 'fa-trash-o red');
INSERT INTO `sys_permission` VALUES ('20', '1', '4', '角色授权', '/admin/role/allocate', '1', '0', '1', 'fa-cog grey');
INSERT INTO `sys_permission` VALUES ('22', '1', '2', '应用列表', '/admin/app/list', '5', '0', '1', '');
INSERT INTO `sys_permission` VALUES ('23', '1', '3', '用户列表', '/admin/user/list', '7', '0', '1', '');
INSERT INTO `sys_permission` VALUES ('24', '1', '4', '角色列表', '/admin/role/list', '6', '0', '1', '');
INSERT INTO `sys_permission` VALUES ('25', '1', '5', '权限树列表', '/admin/permission/nodes', '1', '0', '1', '');
INSERT INTO `sys_permission` VALUES ('26', '1', '2', '应用保存', '/admin/app/save', '1', '0', '1', '');
INSERT INTO `sys_permission` VALUES ('27', '1', '3', '用户保存', '/admin/user/save', '1', '0', '1', '');
INSERT INTO `sys_permission` VALUES ('28', '1', '4', '角色保存', '/admin/role/save', '1', '0', '1', '');
INSERT INTO `sys_permission` VALUES ('29', '1', '5', '权限保存', '/admin/permission/save', '1', '0', '1', '');
INSERT INTO `sys_permission` VALUES ('30', '1', '5', '权限删除', '/admin/permission/delete', '1', '0', '1', '');
INSERT INTO `sys_permission` VALUES ('33', '81', null, '菜单1', '/admin/men1', '100', '1', '1', 'fa-user');
INSERT INTO `sys_permission` VALUES ('35', '81', '33', '菜单1新增', '/admin/menu1/edit', '1', '0', '1', '');
INSERT INTO `sys_permission` VALUES ('36', '81', '33', '菜单1删除', '/admin/menu1/delete', '1', '0', '1', '');
INSERT INTO `sys_permission` VALUES ('39', '1', null, '导航栏', '/admin/admin/menu', '99', '0', '1', '');
INSERT INTO `sys_permission` VALUES ('41', '1', null, '个人中心', '/admin/profile', '89', '1', '1', 'fa fa-desktop');
INSERT INTO `sys_permission` VALUES ('42', '1', '41', '修改密码', '/admin/profile/savePassword', '1', '0', '1', '');
INSERT INTO `sys_permission` VALUES ('59', '81', null, '菜单2', '/admin/menu2', '90', '1', '1', '');

-- ----------------------------
-- Table structure for sys_re_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_re_role_permission`;
CREATE TABLE `sys_re_role_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `roleId` int(11) NOT NULL COMMENT '角色ID',
  `permissionId` int(11) NOT NULL COMMENT '权限ID',
  `appId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_SYS_RE_R_REFERENCE_SYS_PERM` (`permissionId`),
  KEY `FK_SYS_RE_R_REFERENCE_SYS_ROLE` (`roleId`),
  CONSTRAINT `FK_SYS_RE_R_REFERENCE_SYS_PERM` FOREIGN KEY (`permissionId`) REFERENCES `sys_permission` (`id`),
  CONSTRAINT `FK_SYS_RE_R_REFERENCE_SYS_ROLE` FOREIGN KEY (`roleId`) REFERENCES `sys_role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=349 DEFAULT CHARSET=utf8 COMMENT='角色权限表';

-- ----------------------------
-- Records of sys_re_role_permission
-- ----------------------------
INSERT INTO `sys_re_role_permission` VALUES ('291', '1', '39', '1');
INSERT INTO `sys_re_role_permission` VALUES ('292', '1', '41', '1');
INSERT INTO `sys_re_role_permission` VALUES ('293', '1', '42', '1');
INSERT INTO `sys_re_role_permission` VALUES ('294', '1', '3', '1');
INSERT INTO `sys_re_role_permission` VALUES ('295', '1', '23', '1');
INSERT INTO `sys_re_role_permission` VALUES ('296', '1', '10', '1');
INSERT INTO `sys_re_role_permission` VALUES ('297', '1', '11', '1');
INSERT INTO `sys_re_role_permission` VALUES ('298', '1', '12', '1');
INSERT INTO `sys_re_role_permission` VALUES ('299', '1', '13', '1');
INSERT INTO `sys_re_role_permission` VALUES ('300', '1', '14', '1');
INSERT INTO `sys_re_role_permission` VALUES ('302', '1', '27', '1');
INSERT INTO `sys_re_role_permission` VALUES ('303', '1', '2', '1');
INSERT INTO `sys_re_role_permission` VALUES ('304', '1', '22', '1');
INSERT INTO `sys_re_role_permission` VALUES ('305', '1', '6', '1');
INSERT INTO `sys_re_role_permission` VALUES ('306', '1', '7', '1');
INSERT INTO `sys_re_role_permission` VALUES ('307', '1', '8', '1');
INSERT INTO `sys_re_role_permission` VALUES ('308', '1', '9', '1');
INSERT INTO `sys_re_role_permission` VALUES ('309', '1', '26', '1');
INSERT INTO `sys_re_role_permission` VALUES ('310', '1', '4', '1');
INSERT INTO `sys_re_role_permission` VALUES ('311', '1', '24', '1');
INSERT INTO `sys_re_role_permission` VALUES ('312', '1', '16', '1');
INSERT INTO `sys_re_role_permission` VALUES ('313', '1', '17', '1');
INSERT INTO `sys_re_role_permission` VALUES ('314', '1', '18', '1');
INSERT INTO `sys_re_role_permission` VALUES ('315', '1', '19', '1');
INSERT INTO `sys_re_role_permission` VALUES ('316', '1', '20', '1');
INSERT INTO `sys_re_role_permission` VALUES ('317', '1', '28', '1');
INSERT INTO `sys_re_role_permission` VALUES ('318', '1', '5', '1');
INSERT INTO `sys_re_role_permission` VALUES ('319', '1', '25', '1');
INSERT INTO `sys_re_role_permission` VALUES ('320', '1', '29', '1');
INSERT INTO `sys_re_role_permission` VALUES ('321', '1', '30', '1');
INSERT INTO `sys_re_role_permission` VALUES ('345', '1', '33', '81');
INSERT INTO `sys_re_role_permission` VALUES ('346', '1', '35', '81');
INSERT INTO `sys_re_role_permission` VALUES ('347', '1', '36', '81');
INSERT INTO `sys_re_role_permission` VALUES ('348', '1', '59', '81');

-- ----------------------------
-- Table structure for sys_re_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_re_user_role`;
CREATE TABLE `sys_re_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL COMMENT '用户ID ',
  `roleId` int(11) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`),
  KEY `FK_SYS_RE_U_REFERENCE_SYS_USER` (`userId`),
  KEY `FK_SYS_RE_U_REFERENCE_SYS_ROLE` (`roleId`),
  CONSTRAINT `FK_SYS_RE_U_REFERENCE_SYS_ROLE` FOREIGN KEY (`roleId`) REFERENCES `sys_role` (`id`),
  CONSTRAINT `FK_SYS_RE_U_REFERENCE_SYS_USER` FOREIGN KEY (`userId`) REFERENCES `sys_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COMMENT='用户角色表';

-- ----------------------------
-- Records of sys_re_user_role
-- ----------------------------
INSERT INTO `sys_re_user_role` VALUES ('16', '2', '1');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL COMMENT '名称',
  `sort` int(11) NOT NULL COMMENT '排序',
  `description` varchar(200) DEFAULT NULL COMMENT '描述',
  `isEnable` int(1) NOT NULL COMMENT '是否启用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '系统管理员', '999', '系统管理员', '1');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account` varchar(50) NOT NULL COMMENT '登录名',
  `password` varchar(100) NOT NULL COMMENT '密码(加密)',
  `lastLoginIp` varchar(20) DEFAULT NULL COMMENT '最后登录IP',
  `lastLoginTime` datetime DEFAULT NULL COMMENT '最后登录时间',
  `loginCount` int(11) NOT NULL COMMENT '登录总次数',
  `createTime` datetime NOT NULL COMMENT '创建时间',
  `isEnable` int(1) NOT NULL COMMENT '是否启用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('2', 'admin', '26524bdf4ea266f131566a89e8f4972c', '127.0.0.1', '2018-04-09 16:20:23', '213', '2015-06-02 11:31:56', '1');

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `user_info_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `login_id` bigint(20) NOT NULL,
  `id_card` varchar(50) COLLATE utf8_latvian_ci NOT NULL,
  `user_name` varchar(25) COLLATE utf8_latvian_ci NOT NULL,
  `birthday` varchar(50) COLLATE utf8_latvian_ci NOT NULL,
  `sex` int(11) NOT NULL,
  `phone` varchar(50) COLLATE utf8_latvian_ci DEFAULT NULL,
  `is_deleted` int(11) NOT NULL,
  `pid` bigint(20) NOT NULL COMMENT '父ID 为0时，是最大职级',
  `dispostion` int(11) NOT NULL,
  `created_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `modified_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `created_user_info_id` bigint(20) NOT NULL,
  `modified_user_info_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_info_id`)
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8 COLLATE=utf8_latvian_ci COMMENT='用戶信息';

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES ('1', '1', '410882199009013439', 'admin', '20190105', '1', '18832058799', '0', '0', '99', '2018-05-29 17:49:46', '2018-05-29 17:49:46', '0', '0');
INSERT INTO `user_info` VALUES ('2', '2', '410882199009013439', 'department1', '20190105', '1', '18832058799', '0', '1', '4', '2018-05-30 14:11:32', '2018-05-30 14:11:32', '1', '1');
INSERT INTO `user_info` VALUES ('3', '3', '410882199009013439', 'department2', '20190105', '1', '18832058799', '0', '1', '4', '2018-05-30 14:12:00', '2018-05-30 14:12:00', '1', '1');
INSERT INTO `user_info` VALUES ('4', '4', '410882199009013439', 'department3', '20190105', '1', '18832058799', '0', '1', '4', '2018-05-30 14:12:04', '2018-05-30 14:12:04', '1', '1');
INSERT INTO `user_info` VALUES ('5', '5', '410882199009013439', 'center5', '20190105', '1', '18832058799', '0', '2', '3', '2018-05-30 14:32:49', '2018-05-30 14:32:49', '2', '2');
INSERT INTO `user_info` VALUES ('6', '6', '410882199009013439', 'center738', '20190105', '1', '18832058799', '0', '2', '3', '2018-05-30 14:19:03', '2018-05-30 14:19:03', '2', '2');
INSERT INTO `user_info` VALUES ('7', '7', '410882199009013439', 'center954', '20190105', '1', '18832058799', '0', '2', '3', '2018-05-30 14:20:51', '2018-05-30 14:20:51', '2', '2');
INSERT INTO `user_info` VALUES ('8', '8', '410882199009013439', 'center483', '20190105', '1', '18832058799', '0', '2', '3', '2018-05-30 14:21:38', '2018-05-30 14:21:38', '2', '2');
INSERT INTO `user_info` VALUES ('9', '9', '410882199009013439', 'center951', '20190105', '1', '18832058799', '0', '2', '3', '2018-05-30 14:21:38', '2018-05-30 14:21:38', '2', '2');
INSERT INTO `user_info` VALUES ('10', '10', '410882199009013439', 'center421', '20190105', '1', '18832058799', '0', '2', '3', '2018-05-30 14:21:38', '2018-05-30 14:21:38', '2', '2');
INSERT INTO `user_info` VALUES ('11', '11', '410882199009013439', 'center11', '20190105', '1', '18832058799', '0', '3', '3', '2018-05-30 14:32:54', '2018-05-30 14:32:54', '3', '3');
INSERT INTO `user_info` VALUES ('12', '12', '410882199009013439', 'center632', '20190105', '1', '18832058799', '0', '3', '3', '2018-05-30 14:26:48', '2018-05-30 14:26:48', '3', '3');
INSERT INTO `user_info` VALUES ('13', '13', '410882199009013439', 'center162', '20190105', '1', '18832058799', '0', '3', '3', '2018-05-30 14:26:48', '2018-05-30 14:26:48', '3', '3');
INSERT INTO `user_info` VALUES ('14', '14', '410882199009013439', 'center746', '20190105', '1', '18832058799', '0', '3', '3', '2018-05-30 14:26:48', '2018-05-30 14:26:48', '3', '3');
INSERT INTO `user_info` VALUES ('15', '15', '410882199009013439', 'center998', '20190105', '1', '18832058799', '0', '3', '3', '2018-05-30 14:26:48', '2018-05-30 14:26:48', '3', '3');
INSERT INTO `user_info` VALUES ('16', '16', '410882199009013439', 'center16', '20190105', '1', '18832058799', '0', '3', '3', '2018-05-30 14:32:59', '2018-05-30 14:32:59', '3', '3');
INSERT INTO `user_info` VALUES ('17', '17', '410882199009013439', 'center275', '20190105', '1', '18832058799', '0', '4', '3', '2018-05-30 15:06:03', '2018-05-30 15:06:03', '4', '4');
INSERT INTO `user_info` VALUES ('18', '18', '410882199009013439', 'center162', '20190105', '1', '18832058799', '0', '4', '3', '2018-05-30 15:06:00', '2018-05-30 15:06:00', '4', '4');
INSERT INTO `user_info` VALUES ('19', '19', '410882199009013439', 'center448', '20190105', '1', '18832058799', '0', '4', '3', '2018-05-30 15:05:58', '2018-05-30 15:05:58', '4', '4');
INSERT INTO `user_info` VALUES ('20', '20', '410882199009013439', 'center301', '20190105', '1', '18832058799', '0', '4', '3', '2018-05-30 15:05:55', '2018-05-30 15:05:55', '4', '4');
INSERT INTO `user_info` VALUES ('21', '21', '410882199009013439', 'center554', '20190105', '1', '18832058799', '0', '4', '3', '2018-05-30 15:05:53', '2018-05-30 15:05:53', '4', '4');
INSERT INTO `user_info` VALUES ('22', '22', '410882199009013439', 'leader22', '20190105', '1', '18832058799', '0', '5', '2', '2018-05-30 14:41:14', '2018-05-30 14:41:14', '5', '5');
INSERT INTO `user_info` VALUES ('23', '23', '410882199009013439', 'leader23', '20190105', '1', '18832058799', '0', '5', '2', '2018-05-30 14:52:03', '2018-05-30 14:52:03', '5', '5');
INSERT INTO `user_info` VALUES ('24', '24', '410882199009013439', 'leader185', '20190105', '1', '18832058799', '0', '5', '2', '2018-05-30 14:35:54', '2018-05-30 14:35:54', '5', '5');
INSERT INTO `user_info` VALUES ('25', '25', '410882199009013439', 'leader867', '20190105', '1', '18832058799', '0', '5', '2', '2018-05-30 14:35:54', '2018-05-30 14:35:54', '5', '5');
INSERT INTO `user_info` VALUES ('26', '26', '410882199009013439', 'leader462', '20190105', '1', '18832058799', '0', '5', '2', '2018-05-30 14:35:54', '2018-05-30 14:35:54', '5', '5');
INSERT INTO `user_info` VALUES ('27', '27', '410882199009013439', 'leader27', '20190105', '1', '18832058799', '0', '11', '2', '2018-05-30 14:41:25', '2018-05-30 14:41:25', '11', '11');
INSERT INTO `user_info` VALUES ('28', '28', '410882199009013439', 'leader28', '20190105', '1', '18832058799', '0', '11', '2', '2018-05-30 14:52:07', '2018-05-30 14:52:07', '11', '11');
INSERT INTO `user_info` VALUES ('29', '29', '410882199009013439', 'leader618', '20190105', '1', '18832058799', '0', '11', '2', '2018-05-30 14:37:10', '2018-05-30 14:37:10', '11', '11');
INSERT INTO `user_info` VALUES ('30', '30', '410882199009013439', 'leader102', '20190105', '1', '18832058799', '0', '11', '2', '2018-05-30 14:37:10', '2018-05-30 14:37:10', '11', '11');
INSERT INTO `user_info` VALUES ('31', '31', '410882199009013439', 'leader522', '20190105', '1', '18832058799', '0', '11', '2', '2018-05-30 14:37:10', '2018-05-30 14:37:10', '11', '11');
INSERT INTO `user_info` VALUES ('32', '32', '410882199009013439', 'leader32', '20190105', '1', '18832058799', '0', '16', '2', '2018-05-30 14:41:34', '2018-05-30 14:41:34', '16', '16');
INSERT INTO `user_info` VALUES ('33', '33', '410882199009013439', 'leader33', '20190105', '1', '18832058799', '0', '16', '2', '2018-05-30 14:52:10', '2018-05-30 14:52:10', '16', '16');
INSERT INTO `user_info` VALUES ('34', '34', '410882199009013439', 'leader113', '20190105', '1', '18832058799', '0', '16', '2', '2018-05-30 14:38:34', '2018-05-30 14:38:34', '16', '16');
INSERT INTO `user_info` VALUES ('35', '35', '410882199009013439', 'leader932', '20190105', '1', '18832058799', '0', '16', '2', '2018-05-30 14:38:34', '2018-05-30 14:38:34', '16', '16');
INSERT INTO `user_info` VALUES ('36', '36', '410882199009013439', 'leader653', '20190105', '1', '18832058799', '0', '16', '2', '2018-05-30 14:38:34', '2018-05-30 14:38:34', '16', '16');
INSERT INTO `user_info` VALUES ('37', '37', '410882199009013439', 'common310', '20190105', '1', '18832058799', '0', '22', '1', '2018-05-30 14:44:43', '2018-05-30 14:44:43', '22', '22');
INSERT INTO `user_info` VALUES ('38', '38', '410882199009013439', 'common628', '20190105', '1', '18832058799', '0', '22', '1', '2018-05-30 14:44:43', '2018-05-30 14:44:43', '22', '22');
INSERT INTO `user_info` VALUES ('39', '39', '410882199009013439', 'common6', '20190105', '1', '18832058799', '0', '22', '1', '2018-05-30 14:44:43', '2018-05-30 14:44:43', '22', '22');
INSERT INTO `user_info` VALUES ('40', '40', '410882199009013439', 'common384', '20190105', '1', '18832058799', '0', '22', '1', '2018-05-30 14:44:43', '2018-05-30 14:44:43', '22', '22');
INSERT INTO `user_info` VALUES ('41', '41', '410882199009013439', 'common137', '20190105', '1', '18832058799', '0', '22', '1', '2018-05-30 14:44:43', '2018-05-30 14:44:43', '22', '22');
INSERT INTO `user_info` VALUES ('42', '42', '410882199009013439', 'common725', '20190105', '1', '18832058799', '0', '27', '1', '2018-05-30 14:45:31', '2018-05-30 14:45:31', '27', '27');
INSERT INTO `user_info` VALUES ('43', '43', '410882199009013439', 'common379', '20190105', '1', '18832058799', '0', '27', '1', '2018-05-30 14:45:31', '2018-05-30 14:45:31', '27', '27');
INSERT INTO `user_info` VALUES ('44', '44', '410882199009013439', 'common219', '20190105', '1', '18832058799', '0', '27', '1', '2018-05-30 14:45:31', '2018-05-30 14:45:31', '27', '27');
INSERT INTO `user_info` VALUES ('45', '45', '410882199009013439', 'common173', '20190105', '1', '18832058799', '0', '27', '1', '2018-05-30 14:45:31', '2018-05-30 14:45:31', '27', '27');
INSERT INTO `user_info` VALUES ('46', '46', '410882199009013439', 'common24', '20190105', '1', '18832058799', '0', '27', '1', '2018-05-30 14:45:31', '2018-05-30 14:45:31', '27', '27');
INSERT INTO `user_info` VALUES ('47', '47', '410882199009013439', 'common130', '20190105', '1', '18832058799', '0', '32', '1', '2018-05-30 14:46:33', '2018-05-30 14:46:33', '32', '32');
INSERT INTO `user_info` VALUES ('48', '48', '410882199009013439', 'common630', '20190105', '1', '18832058799', '0', '32', '1', '2018-05-30 14:46:33', '2018-05-30 14:46:33', '32', '32');
INSERT INTO `user_info` VALUES ('49', '49', '410882199009013439', 'common897', '20190105', '1', '18832058799', '0', '32', '1', '2018-05-30 14:46:33', '2018-05-30 14:46:33', '32', '32');
INSERT INTO `user_info` VALUES ('50', '50', '410882199009013439', 'common563', '20190105', '1', '18832058799', '0', '32', '1', '2018-05-30 14:46:33', '2018-05-30 14:46:33', '32', '32');
INSERT INTO `user_info` VALUES ('51', '51', '410882199009013439', 'common318', '20190105', '1', '18832058799', '0', '32', '1', '2018-05-30 14:46:33', '2018-05-30 14:46:33', '32', '32');
INSERT INTO `user_info` VALUES ('52', '52', '410882199009013439', 'common368', '20190105', '1', '18832058799', '0', '23', '1', '2018-05-30 14:48:54', '2018-05-30 14:48:54', '23', '23');
INSERT INTO `user_info` VALUES ('53', '53', '410882199009013439', 'common809', '20190105', '1', '18832058799', '0', '23', '1', '2018-05-30 14:48:54', '2018-05-30 14:48:54', '23', '23');
INSERT INTO `user_info` VALUES ('54', '54', '410882199009013439', 'common999', '20190105', '1', '18832058799', '0', '23', '1', '2018-05-30 14:48:54', '2018-05-30 14:48:54', '23', '23');
INSERT INTO `user_info` VALUES ('55', '55', '410882199009013439', 'common91', '20190105', '1', '18832058799', '0', '23', '1', '2018-05-30 14:48:54', '2018-05-30 14:48:54', '23', '23');
INSERT INTO `user_info` VALUES ('56', '56', '410882199009013439', 'common593', '20190105', '1', '18832058799', '0', '23', '1', '2018-05-30 14:48:54', '2018-05-30 14:48:54', '23', '23');
INSERT INTO `user_info` VALUES ('57', '57', '410882199009013439', 'common607', '20190105', '1', '18832058799', '0', '28', '1', '2018-05-30 14:49:42', '2018-05-30 14:49:42', '28', '28');
INSERT INTO `user_info` VALUES ('58', '58', '410882199009013439', 'common996', '20190105', '1', '18832058799', '0', '28', '1', '2018-05-30 14:49:42', '2018-05-30 14:49:42', '28', '28');
INSERT INTO `user_info` VALUES ('59', '59', '410882199009013439', 'common52', '20190105', '1', '18832058799', '0', '28', '1', '2018-05-30 14:49:42', '2018-05-30 14:49:42', '28', '28');
INSERT INTO `user_info` VALUES ('60', '60', '410882199009013439', 'common898', '20190105', '1', '18832058799', '0', '28', '1', '2018-05-30 14:49:42', '2018-05-30 14:49:42', '28', '28');
INSERT INTO `user_info` VALUES ('61', '61', '410882199009013439', 'common772', '20190105', '1', '18832058799', '0', '28', '1', '2018-05-30 14:49:42', '2018-05-30 14:49:42', '28', '28');
INSERT INTO `user_info` VALUES ('62', '62', '410882199009013439', 'common529', '20190105', '1', '18832058799', '0', '33', '1', '2018-05-30 14:50:26', '2018-05-30 14:50:26', '33', '33');
INSERT INTO `user_info` VALUES ('63', '63', '410882199009013439', 'common645', '20190105', '1', '18832058799', '0', '33', '1', '2018-05-30 14:50:26', '2018-05-30 14:50:26', '33', '33');
INSERT INTO `user_info` VALUES ('64', '64', '410882199009013439', 'common199', '20190105', '1', '18832058799', '0', '33', '1', '2018-05-30 14:50:26', '2018-05-30 14:50:26', '33', '33');
INSERT INTO `user_info` VALUES ('65', '65', '410882199009013439', 'common913', '20190105', '1', '18832058799', '0', '33', '1', '2018-05-30 14:50:26', '2018-05-30 14:50:26', '33', '33');
INSERT INTO `user_info` VALUES ('66', '66', '410882199009013439', 'common281', '20190105', '1', '18832058799', '0', '33', '1', '2018-05-30 14:50:26', '2018-05-30 14:50:26', '33', '33');

-- ----------------------------
-- Table structure for user_login
-- ----------------------------
DROP TABLE IF EXISTS `user_login`;
CREATE TABLE `user_login` (
  `login_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '登录信息表ID',
  `user_info_id` bigint(20) DEFAULT NULL,
  `is_deleted` int(11) NOT NULL,
  `login_name` varchar(50) COLLATE utf8_latvian_ci NOT NULL,
  `password` varchar(50) COLLATE utf8_latvian_ci NOT NULL,
  `dispostion` int(11) NOT NULL,
  `created_time` timestamp NULL DEFAULT '0000-00-00 00:00:00',
  `modified_time` timestamp NULL DEFAULT '0000-00-00 00:00:00',
  `created_user` varchar(50) COLLATE utf8_latvian_ci NOT NULL,
  `modified_user` varchar(50) COLLATE utf8_latvian_ci NOT NULL,
  `created_user_id` bigint(20) NOT NULL,
  `modified_user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`login_id`)
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8 COLLATE=utf8_latvian_ci;

-- ----------------------------
-- Records of user_login
-- ----------------------------
INSERT INTO `user_login` VALUES ('1', '1', '0', 'admin', 'admin', '99', '2018-05-31 16:21:02', '2018-05-31 16:21:02', 'admin', 'admin', '0', '0');
INSERT INTO `user_login` VALUES ('2', '2', '0', 'department1', 'department1', '4', '2018-05-30 13:54:45', '2018-05-30 13:54:45', 'admin', 'admin', '1', '1');
INSERT INTO `user_login` VALUES ('3', '3', '0', 'department2', 'department681', '4', '2018-05-30 14:12:09', '2018-05-30 14:12:09', 'admin', 'admin', '1', '1');
INSERT INTO `user_login` VALUES ('4', '4', '0', 'department3', 'dipartment770', '4', '2018-05-30 14:12:11', '2018-05-30 14:12:11', 'admin', 'admin', '1', '1');
INSERT INTO `user_login` VALUES ('5', '5', '0', 'center5', 'center5', '3', '2018-05-30 14:33:24', '2018-05-30 14:33:24', 'department1', 'department1', '2', '2');
INSERT INTO `user_login` VALUES ('6', '6', '0', 'center738', 'center738', '3', '2018-05-30 11:54:12', '2018-05-30 11:54:12', 'department1', 'department1', '2', '2');
INSERT INTO `user_login` VALUES ('7', '7', '0', 'center954', 'center954', '3', '2018-05-30 11:54:12', '2018-05-30 11:54:12', 'department1', 'department1', '2', '2');
INSERT INTO `user_login` VALUES ('8', '8', '0', 'center483', 'center483', '3', '2018-05-30 11:54:12', '2018-05-30 11:54:12', 'department1', 'department1', '2', '2');
INSERT INTO `user_login` VALUES ('9', '9', '0', 'center951', 'center951', '3', '2018-05-30 11:54:12', '2018-05-30 11:54:12', 'department1', 'department1', '2', '2');
INSERT INTO `user_login` VALUES ('10', '10', '0', 'center_test4', 'center_test4', '3', '2018-06-26 12:46:35', '2018-06-26 12:48:26', 'department1', 'department1', '2', '2');
INSERT INTO `user_login` VALUES ('11', '11', '0', 'center11', 'center11', '3', '2018-05-30 14:33:34', '2018-05-30 14:33:34', 'department3', 'department2', '3', '3');
INSERT INTO `user_login` VALUES ('12', '12', '0', 'center632', 'center632', '3', '2018-05-30 14:28:15', '2018-05-30 14:28:15', 'department3', 'department2', '3', '3');
INSERT INTO `user_login` VALUES ('13', '13', '0', 'center162', 'center162', '3', '2018-05-30 14:28:18', '2018-05-30 14:28:18', 'department3', 'department2', '3', '3');
INSERT INTO `user_login` VALUES ('14', '14', '0', 'center746', 'center746', '3', '2018-05-30 14:28:20', '2018-05-30 14:28:20', 'department3', 'department2', '3', '3');
INSERT INTO `user_login` VALUES ('15', '15', '0', 'center998', 'center998', '3', '2018-05-30 14:28:22', '2018-05-30 14:28:22', 'department3', 'department2', '3', '3');
INSERT INTO `user_login` VALUES ('16', '16', '0', 'center16', 'center16', '3', '2018-05-30 14:33:44', '2018-05-30 14:33:44', 'department3', 'department2', '3', '3');
INSERT INTO `user_login` VALUES ('17', '17', '0', 'center275', 'center275', '3', '2018-05-30 14:39:39', '2018-05-30 14:39:39', 'department3', 'department3', '4', '4');
INSERT INTO `user_login` VALUES ('18', '18', '0', 'center162', 'center162', '3', '2018-05-30 14:39:42', '2018-05-30 14:39:42', 'department3', 'department3', '4', '4');
INSERT INTO `user_login` VALUES ('19', '19', '0', 'center448', 'center448', '3', '2018-05-30 14:39:45', '2018-05-30 14:39:45', 'department3', 'department3', '4', '4');
INSERT INTO `user_login` VALUES ('20', '20', '0', 'center301', 'center301', '3', '2018-05-30 14:39:48', '2018-05-30 14:39:48', 'department3', 'department3', '4', '4');
INSERT INTO `user_login` VALUES ('21', '21', '0', 'center554', 'center554', '3', '2018-05-30 14:39:50', '2018-05-30 14:39:50', 'department3', 'department3', '4', '4');
INSERT INTO `user_login` VALUES ('22', '22', '0', 'leader22', 'leader22', '2', '2018-05-30 14:42:11', '2018-05-30 14:42:11', 'center3', 'center3', '5', '5');
INSERT INTO `user_login` VALUES ('23', '23', '0', 'leader23', 'leader23', '2', '2018-05-30 14:51:20', '2018-05-30 14:51:20', 'center3', 'center3', '5', '5');
INSERT INTO `user_login` VALUES ('24', '24', '0', 'leader185', 'leader185', '2', '2018-05-30 11:54:12', '2018-05-30 11:54:12', 'center3', 'center3', '5', '5');
INSERT INTO `user_login` VALUES ('25', '25', '0', 'leader867', 'leader867', '2', '2018-05-30 11:54:12', '2018-05-30 11:54:12', 'center3', 'center3', '5', '5');
INSERT INTO `user_login` VALUES ('26', '26', '0', 'leader462', 'leader462', '2', '2018-05-30 11:54:12', '2018-05-30 11:54:12', 'center3', 'center3', '5', '5');
INSERT INTO `user_login` VALUES ('27', '27', '0', 'leader27', 'leader27', '2', '2018-05-30 14:42:15', '2018-05-30 14:42:15', 'center11', 'center11', '11', '11');
INSERT INTO `user_login` VALUES ('28', '28', '0', 'leader28', 'leader28', '2', '2018-05-30 14:51:28', '2018-05-30 14:51:28', 'center11', 'center11', '11', '11');
INSERT INTO `user_login` VALUES ('29', '29', '0', 'leader618', 'leader618', '2', '2018-05-30 11:54:12', '2018-05-30 11:54:12', 'center11', 'center11', '11', '11');
INSERT INTO `user_login` VALUES ('30', '30', '0', 'leader102', 'leader102', '2', '2018-05-30 11:54:12', '2018-05-30 11:54:12', 'center11', 'center11', '11', '11');
INSERT INTO `user_login` VALUES ('31', '31', '0', 'leader522', 'leader522', '2', '2018-05-30 11:54:12', '2018-05-30 11:54:12', 'center11', 'center11', '11', '11');
INSERT INTO `user_login` VALUES ('32', '32', '0', 'leader32', 'leader32', '2', '2018-05-30 14:42:00', '2018-05-30 14:42:00', 'center16', 'center16', '16', '16');
INSERT INTO `user_login` VALUES ('33', '33', '0', 'leader33', 'leader33', '2', '2018-05-30 14:51:35', '2018-05-30 14:51:35', 'center16', 'center16', '16', '16');
INSERT INTO `user_login` VALUES ('34', '34', '0', 'leader113', 'leader113', '2', '2018-05-30 11:54:12', '2018-05-30 11:54:12', 'center16', 'center16', '16', '16');
INSERT INTO `user_login` VALUES ('35', '35', '0', 'leader932', 'leader932', '2', '2018-05-30 11:54:12', '2018-05-30 11:54:12', 'center16', 'center16', '16', '16');
INSERT INTO `user_login` VALUES ('36', '36', '0', 'leader653', 'leader653', '2', '2018-05-30 11:54:12', '2018-05-30 11:54:12', 'center16', 'center16', '16', '16');
INSERT INTO `user_login` VALUES ('37', '37', '0', 'common310', 'common310', '1', '2018-05-30 11:54:12', '2018-05-30 11:54:12', 'leader22', 'leader22', '22', '22');
INSERT INTO `user_login` VALUES ('38', '38', '0', 'common628', 'common628', '1', '2018-05-30 11:54:12', '2018-05-30 11:54:12', 'leader22', 'leader22', '22', '22');
INSERT INTO `user_login` VALUES ('39', '39', '0', 'common6', 'common6', '1', '2018-05-30 11:54:12', '2018-05-30 11:54:12', 'leader22', 'leader22', '22', '22');
INSERT INTO `user_login` VALUES ('40', '40', '0', 'common384', 'common384', '1', '2018-05-30 11:54:12', '2018-05-30 11:54:12', 'leader22', 'leader22', '22', '22');
INSERT INTO `user_login` VALUES ('41', '41', '0', 'common137', 'common137', '1', '2018-05-30 11:54:12', '2018-05-30 11:54:12', 'leader22', 'leader22', '22', '22');
INSERT INTO `user_login` VALUES ('42', '42', '0', 'common725', 'common725', '1', '2018-05-30 11:54:12', '2018-05-30 11:54:12', 'leader27', 'leader27', '27', '27');
INSERT INTO `user_login` VALUES ('43', '43', '0', 'common379', 'common379', '1', '2018-05-30 11:54:12', '2018-05-30 11:54:12', 'leader27', 'leader27', '27', '27');
INSERT INTO `user_login` VALUES ('44', '44', '0', 'common219', 'common219', '1', '2018-05-30 11:54:12', '2018-05-30 11:54:12', 'leader27', 'leader27', '27', '27');
INSERT INTO `user_login` VALUES ('45', '45', '0', 'common173', 'common173', '1', '2018-05-30 11:54:12', '2018-05-30 11:54:12', 'leader27', 'leader27', '27', '27');
INSERT INTO `user_login` VALUES ('46', '46', '0', 'common24', 'common24', '1', '2018-05-30 11:54:12', '2018-05-30 11:54:12', 'leader27', 'leader27', '27', '27');
INSERT INTO `user_login` VALUES ('47', '47', '0', 'common130', 'common130', '1', '2018-05-30 11:54:12', '2018-05-30 11:54:12', 'leader32', 'leader32', '32', '32');
INSERT INTO `user_login` VALUES ('48', '48', '0', 'common630', 'common630', '1', '2018-05-30 11:54:12', '2018-05-30 11:54:12', 'leader32', 'leader32', '32', '32');
INSERT INTO `user_login` VALUES ('49', '49', '0', 'common897', 'common897', '1', '2018-05-30 11:54:12', '2018-05-30 11:54:12', 'leader32', 'leader32', '32', '32');
INSERT INTO `user_login` VALUES ('50', '50', '0', 'common563', 'common563', '1', '2018-05-30 11:54:12', '2018-05-30 11:54:12', 'leader32', 'leader32', '32', '32');
INSERT INTO `user_login` VALUES ('51', '51', '0', 'common318', 'common318', '1', '2018-05-30 11:54:12', '2018-05-30 11:54:12', 'leader32', 'leader32', '32', '32');
INSERT INTO `user_login` VALUES ('52', '52', '0', 'common368', 'common368', '1', '2018-05-30 11:54:12', '2018-05-30 11:54:12', 'leader23', 'leader23', '23', '23');
INSERT INTO `user_login` VALUES ('53', '53', '0', 'common809', 'common809', '1', '2018-05-30 11:54:12', '2018-05-30 11:54:12', 'leader23', 'leader23', '23', '23');
INSERT INTO `user_login` VALUES ('54', '54', '0', 'common999', 'common999', '1', '2018-05-30 11:54:12', '2018-05-30 11:54:12', 'leader23', 'leader23', '23', '23');
INSERT INTO `user_login` VALUES ('55', '55', '0', 'common91', 'common91', '1', '2018-05-30 11:54:12', '2018-05-30 11:54:12', 'leader23', 'leader23', '23', '23');
INSERT INTO `user_login` VALUES ('56', '56', '0', 'common593', 'common593', '1', '2018-05-30 11:54:12', '2018-05-30 11:54:12', 'leader23', 'leader23', '23', '23');
INSERT INTO `user_login` VALUES ('57', '57', '0', 'common607', 'common607', '1', '2018-05-30 11:54:12', '2018-05-30 11:54:12', 'leader28', 'leader28', '28', '28');
INSERT INTO `user_login` VALUES ('58', '58', '0', 'common996', 'common996', '1', '2018-05-30 11:54:12', '2018-05-30 11:54:12', 'leader28', 'leader28', '28', '28');
INSERT INTO `user_login` VALUES ('59', '59', '0', 'common52', 'common52', '1', '2018-05-30 11:54:12', '2018-05-30 11:54:12', 'leader28', 'leader28', '28', '28');
INSERT INTO `user_login` VALUES ('60', '60', '0', 'common898', 'common898', '1', '2018-05-30 11:54:12', '2018-05-30 11:54:12', 'leader28', 'leader28', '28', '28');
INSERT INTO `user_login` VALUES ('61', '61', '0', 'common772', 'common772', '1', '2018-05-30 11:54:12', '2018-05-30 11:54:12', 'leader28', 'leader28', '28', '28');
INSERT INTO `user_login` VALUES ('62', '62', '0', 'common529', 'common529', '1', '2018-05-30 11:54:12', '2018-05-30 11:54:12', 'leader33', 'leader33', '33', '33');
INSERT INTO `user_login` VALUES ('63', '63', '0', 'common645', 'common645', '1', '2018-05-30 11:54:12', '2018-05-30 11:54:12', 'leader33', 'leader33', '33', '33');
INSERT INTO `user_login` VALUES ('64', '64', '0', 'common199', 'common199', '1', '2018-05-30 11:54:12', '2018-05-30 11:54:12', 'leader33', 'leader33', '33', '33');
INSERT INTO `user_login` VALUES ('65', '65', '0', 'common913', 'common913', '1', '2018-05-30 11:54:12', '2018-05-30 11:54:12', 'leader33', 'leader33', '33', '33');
INSERT INTO `user_login` VALUES ('66', '66', '0', 'common281', 'common281', '1', '2018-05-30 11:54:12', '2018-05-30 11:54:12', 'leader33', 'leader33', '33', '33');

-- ----------------------------
-- Table structure for user_performance
-- ----------------------------
DROP TABLE IF EXISTS `user_performance`;
CREATE TABLE `user_performance` (
  `performance_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_info_id` bigint(20) NOT NULL,
  `is_deleted` int(11) NOT NULL,
  `performance_content` longtext COLLATE utf8_latvian_ci,
  `performance_score` int(11) DEFAULT NULL,
  `operate_user_info_id` bigint(20) DEFAULT NULL,
  `created_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `modified_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `is_locked` int(11) NOT NULL,
  `operate_disposition` int(11) DEFAULT NULL COMMENT '''operate_dispostion'' ',
  `performance_time` varchar(50) COLLATE utf8_latvian_ci NOT NULL,
  PRIMARY KEY (`performance_id`)
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=utf8 COLLATE=utf8_latvian_ci;

-- ----------------------------
-- Records of user_performance
-- ----------------------------
INSERT INTO `user_performance` VALUES ('1', '1', '0', '哈哈哈，真厉害！', '100', null, '2018-06-01 00:06:08', '2018-06-01 00:06:08', '0', null, '201806');
INSERT INTO `user_performance` VALUES ('2', '2', '0', '哈哈哈，真厉害！', '12', '1', '2018-06-04 06:45:26', '2018-06-04 06:45:26', '0', '4', '201806');
INSERT INTO `user_performance` VALUES ('3', '3', '0', '哈哈哈，真厉害！', '12', '1', '2018-06-04 06:45:26', '2018-06-04 06:45:26', '0', '4', '201806');
INSERT INTO `user_performance` VALUES ('4', '4', '0', '123123', '23', '1', '2018-06-04 06:45:26', '2018-06-04 06:45:26', '0', '4', '201806');
INSERT INTO `user_performance` VALUES ('5', '5', '0', '你好', '13', '1', '2018-06-04 06:45:26', '2018-06-04 06:45:26', '1', '4', '201806');
INSERT INTO `user_performance` VALUES ('6', '6', '0', '你好', '22', '1', '2018-06-04 06:45:26', '2018-06-04 06:45:26', '1', '4', '201806');
INSERT INTO `user_performance` VALUES ('7', '7', '0', '哈哈哈，真厉害！', '100', '2', '2018-06-04 06:40:25', '2018-06-04 06:40:25', '1', '4', '201806');
INSERT INTO `user_performance` VALUES ('8', '8', '0', '哈哈哈，真厉害！', '100', '2', '2018-06-04 06:40:25', '2018-06-04 06:40:25', '1', '4', '201806');
INSERT INTO `user_performance` VALUES ('9', '9', '0', '哈哈哈，真厉害！', '100', '2', '2018-06-04 06:40:25', '2018-06-04 06:40:25', '1', '4', '201806');
INSERT INTO `user_performance` VALUES ('10', '10', '0', '哈哈哈，真厉害！', '100', '2', '2018-06-04 06:40:25', '2018-06-04 06:40:25', '1', '4', '201806');
INSERT INTO `user_performance` VALUES ('11', '11', '0', '哈哈哈，真厉害！', '32', '3', '2018-06-04 00:24:35', '2018-06-04 00:24:35', '0', '4', '201806');
INSERT INTO `user_performance` VALUES ('12', '12', '0', '哈哈哈，真厉害！', '33', '3', '2018-06-04 01:08:43', '2018-06-04 01:08:43', '0', '4', '201806');
INSERT INTO `user_performance` VALUES ('13', '13', '0', '12312', '23', '3', '2018-06-04 00:24:46', '2018-06-04 00:24:46', '0', '4', '201806');
INSERT INTO `user_performance` VALUES ('14', '14', '0', '哈哈哈，真厉害！', '100', '3', '2018-06-01 00:06:08', '2018-06-01 00:06:08', '0', '4', '201806');
INSERT INTO `user_performance` VALUES ('15', '15', '0', '哈哈哈，真厉害！', '100', '3', '2018-06-01 00:06:08', '2018-06-01 00:06:08', '0', '4', '201806');
INSERT INTO `user_performance` VALUES ('16', '16', '0', '你是？', '12', '3', '2018-06-04 07:07:32', '2018-06-04 07:07:32', '0', '4', '201806');
INSERT INTO `user_performance` VALUES ('17', '17', '0', '哈哈哈，真厉害！', '100', '4', '2018-06-03 22:24:14', '2018-06-03 22:24:14', '0', '4', '201806');
INSERT INTO `user_performance` VALUES ('18', '18', '0', '哈哈哈，真厉害！', '100', '4', '2018-06-03 22:24:12', '2018-06-03 22:24:12', '0', '4', '201806');
INSERT INTO `user_performance` VALUES ('19', '19', '0', '哈哈哈，真厉害！', '100', '4', '2018-06-03 22:24:09', '2018-06-03 22:24:09', '0', '4', '201806');
INSERT INTO `user_performance` VALUES ('20', '20', '0', '哈哈哈，真厉害！', '100', '4', '2018-06-03 22:24:05', '2018-06-03 22:24:05', '0', '4', '201806');
INSERT INTO `user_performance` VALUES ('21', '21', '0', '哈哈哈，真厉害！', '100', '4', '2018-06-03 22:23:59', '2018-06-03 22:23:59', '0', '4', '201806');
INSERT INTO `user_performance` VALUES ('22', '22', '0', '哈哈哈，真厉害！', '100', '1', '2018-06-04 06:45:21', '2018-06-04 06:45:21', '1', '4', '201806');
INSERT INTO `user_performance` VALUES ('23', '23', '0', '哈哈哈，真厉害！', '22', '1', '2018-06-04 06:45:21', '2018-06-04 06:45:21', '1', '4', '201806');
INSERT INTO `user_performance` VALUES ('24', '24', '0', '哈哈哈，真厉害！', '12', '2', '2018-06-04 06:47:00', '2018-06-04 06:47:00', '1', '4', '201806');
INSERT INTO `user_performance` VALUES ('25', '25', '0', '测试信息', '12', '1', '2018-06-04 06:45:21', '2018-06-04 06:45:21', '1', '4', '201806');
INSERT INTO `user_performance` VALUES ('26', '26', '0', '哈哈哈，真厉害！', '100', '5', '2018-06-04 06:46:56', '2018-06-04 06:46:56', '1', '3', '201806');
INSERT INTO `user_performance` VALUES ('27', '27', '1', '哈哈哈，真厉害！', '100', '11', '2018-06-04 01:20:15', '2018-06-04 01:20:15', '0', '3', '201806');
INSERT INTO `user_performance` VALUES ('28', '28', '0', '哈哈哈，真厉害！', '100', '11', '2018-06-01 00:06:08', '2018-06-01 00:06:08', '0', '3', '201806');
INSERT INTO `user_performance` VALUES ('29', '29', '0', '哈哈哈，真厉害！', '100', '11', '2018-06-01 00:06:08', '2018-06-01 00:06:08', '0', '3', '201806');
INSERT INTO `user_performance` VALUES ('30', '30', '0', '哈哈哈，真厉害！', '100', '11', '2018-06-01 00:06:08', '2018-06-01 00:06:08', '0', '3', '201806');
INSERT INTO `user_performance` VALUES ('31', '31', '0', '哈哈哈，真厉害！', '100', '11', '2018-06-01 00:06:08', '2018-06-01 00:06:08', '0', '3', '201806');
INSERT INTO `user_performance` VALUES ('32', '32', '0', '哈哈哈，真厉害！', '100', '16', '2018-06-01 00:06:08', '2018-06-01 00:06:08', '0', '3', '201806');
INSERT INTO `user_performance` VALUES ('33', '33', '0', '哈哈哈，真厉害！', '100', '16', '2018-06-01 00:06:08', '2018-06-01 00:06:08', '0', '3', '201806');
INSERT INTO `user_performance` VALUES ('34', '34', '0', '哈哈哈，真厉害！', '100', '16', '2018-06-01 00:06:08', '2018-06-01 00:06:08', '0', '3', '201806');
INSERT INTO `user_performance` VALUES ('35', '35', '0', '哈哈哈，真厉害！', '100', '16', '2018-06-01 00:06:08', '2018-06-01 00:06:08', '0', '3', '201806');
INSERT INTO `user_performance` VALUES ('36', '36', '0', '哈哈哈，真厉害！', '100', '16', '2018-06-01 00:06:08', '2018-06-01 00:06:08', '0', '3', '201806');
INSERT INTO `user_performance` VALUES ('37', '37', '0', '哈哈哈，真厉害！', '100', '22', '2018-06-04 06:40:25', '2018-06-04 06:40:25', '1', '2', '201806');
INSERT INTO `user_performance` VALUES ('38', '38', '0', '哈哈哈，真厉害！', '100', '22', '2018-06-04 06:40:25', '2018-06-04 06:40:25', '1', '2', '201806');
INSERT INTO `user_performance` VALUES ('39', '39', '0', '哈哈哈，真厉害！', '100', '22', '2018-06-04 06:40:25', '2018-06-04 06:40:25', '1', '2', '201806');
INSERT INTO `user_performance` VALUES ('40', '40', '0', '哈哈哈，真厉害！', '100', '22', '2018-06-04 06:40:25', '2018-06-04 06:40:25', '1', '2', '201806');
INSERT INTO `user_performance` VALUES ('41', '41', '0', '哈哈哈，真厉害！', '100', '22', '2018-06-04 06:40:25', '2018-06-04 06:40:25', '1', '2', '201806');
INSERT INTO `user_performance` VALUES ('42', '42', '0', '哈哈哈，真厉害！', '100', '27', '2018-06-01 00:06:08', '2018-06-01 00:06:08', '0', '2', '201806');
INSERT INTO `user_performance` VALUES ('43', '43', '0', '哈哈哈，真厉害！', '100', '27', '2018-06-01 00:06:08', '2018-06-01 00:06:08', '0', '2', '201806');
INSERT INTO `user_performance` VALUES ('44', '44', '0', '哈哈哈，真厉害！', '100', '27', '2018-06-01 00:06:08', '2018-06-01 00:06:08', '0', '2', '201806');
INSERT INTO `user_performance` VALUES ('45', '45', '0', '哈哈哈，真厉害！', '100', '27', '2018-06-01 00:06:08', '2018-06-01 00:06:08', '0', '2', '201806');
INSERT INTO `user_performance` VALUES ('46', '46', '0', '哈哈哈，真厉害！', '100', '27', '2018-06-01 00:06:08', '2018-06-01 00:06:08', '0', '2', '201806');
INSERT INTO `user_performance` VALUES ('47', '47', '0', '哈哈哈，真厉害！', '100', '32', '2018-06-01 00:06:08', '2018-06-01 00:06:08', '0', '2', '201806');
INSERT INTO `user_performance` VALUES ('48', '48', '0', '哈哈哈，真厉害！', '100', '32', '2018-06-01 00:06:08', '2018-06-01 00:06:08', '0', '2', '201806');
INSERT INTO `user_performance` VALUES ('49', '49', '0', '哈哈哈，真厉害！', '100', '32', '2018-06-01 00:06:08', '2018-06-01 00:06:08', '0', '2', '201806');
INSERT INTO `user_performance` VALUES ('50', '50', '0', '哈哈哈，真厉害！', '100', '32', '2018-06-01 00:06:08', '2018-06-01 00:06:08', '0', '2', '201806');
INSERT INTO `user_performance` VALUES ('51', '51', '0', '哈哈哈，真厉害！', '100', '32', '2018-06-01 00:06:08', '2018-06-01 00:06:08', '0', '2', '201806');
INSERT INTO `user_performance` VALUES ('52', '52', '0', '哈哈哈，真厉害！', '100', '23', '2018-06-04 06:40:25', '2018-06-04 06:40:25', '1', '2', '201806');
INSERT INTO `user_performance` VALUES ('53', '53', '0', '哈哈哈，真厉害！', '100', '23', '2018-06-04 06:40:25', '2018-06-04 06:40:25', '1', '2', '201806');
INSERT INTO `user_performance` VALUES ('54', '54', '0', '哈哈哈，真厉害！', '100', '23', '2018-06-04 06:40:25', '2018-06-04 06:40:25', '1', '2', '201806');
INSERT INTO `user_performance` VALUES ('55', '55', '0', '哈哈哈，真厉害！', '100', '23', '2018-06-04 06:40:25', '2018-06-04 06:40:25', '1', '2', '201806');
INSERT INTO `user_performance` VALUES ('56', '56', '0', '哈哈哈，真厉害！', '100', '23', '2018-06-04 06:40:25', '2018-06-04 06:40:25', '1', '2', '201806');
INSERT INTO `user_performance` VALUES ('57', '57', '0', '哈哈哈，真厉害！', '100', '28', '2018-06-01 00:06:08', '2018-06-01 00:06:08', '0', '2', '201806');
INSERT INTO `user_performance` VALUES ('58', '58', '0', '哈哈哈，真厉害！', '100', '28', '2018-06-01 00:06:08', '2018-06-01 00:06:08', '0', '2', '201806');
INSERT INTO `user_performance` VALUES ('59', '59', '0', '哈哈哈，真厉害！', '100', '28', '2018-06-01 00:06:08', '2018-06-01 00:06:08', '0', '2', '201806');
INSERT INTO `user_performance` VALUES ('60', '60', '0', '哈哈哈，真厉害！', '100', '28', '2018-06-01 00:06:08', '2018-06-01 00:06:08', '0', '2', '201806');
INSERT INTO `user_performance` VALUES ('61', '61', '0', '哈哈哈，真厉害！', '100', '28', '2018-06-01 00:06:08', '2018-06-01 00:06:08', '0', '2', '201806');
INSERT INTO `user_performance` VALUES ('62', '62', '0', '哈哈哈，真厉害！', '100', '33', '2018-06-01 00:06:08', '2018-06-01 00:06:08', '0', '2', '201806');
INSERT INTO `user_performance` VALUES ('63', '63', '0', '哈哈哈，真厉害！', '100', '33', '2018-06-01 00:06:08', '2018-06-01 00:06:08', '0', '2', '201806');
INSERT INTO `user_performance` VALUES ('64', '64', '0', '哈哈哈，真厉害！', '100', '33', '2018-06-01 00:06:08', '2018-06-01 00:06:08', '0', '2', '201806');
INSERT INTO `user_performance` VALUES ('65', '65', '0', '哈哈哈，真厉害！', '100', '33', '2018-06-01 00:06:08', '2018-06-01 00:06:08', '0', '2', '201806');
INSERT INTO `user_performance` VALUES ('66', '66', '0', '哈哈哈，真厉害！', '100', '33', '2018-06-01 00:06:08', '2018-06-01 00:06:08', '0', '2', '201806');
INSERT INTO `user_performance` VALUES ('68', '27', '0', null, '12', '3', '2018-06-04 01:19:31', '2018-06-04 01:19:31', '0', '4', '201806');
