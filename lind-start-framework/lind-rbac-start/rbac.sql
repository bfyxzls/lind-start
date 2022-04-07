/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50727
 Source Host           : localhost:3306
 Source Schema         : rbac

 Target Server Type    : MySQL
 Target Server Version : 50727
 File Encoding         : 65001

 Date: 07/04/2022 10:23:24
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission`  (
  `id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `parent_id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `bulk_button` int(11) DEFAULT NULL COMMENT '批量按钮集合',
  `row_button` int(11) DEFAULT NULL COMMENT '行上的按钮集合',
  `create_time` datetime(0) DEFAULT NULL,
  `update_time` datetime(0) DEFAULT NULL,
  `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `del_flag` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES ('1', '北大英华', '', 0, NULL, 7, 5, NULL, NULL, NULL, NULL, 0);
INSERT INTO `permission` VALUES ('1510188865418190849', '', '', 0, '', 0, 0, NULL, NULL, NULL, NULL, 0);
INSERT INTO `permission` VALUES ('1510529717273387009', '测试3', '/', 0, '1', NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `permission` VALUES ('2', '文章管理', '/article/index', 0, '1', 48, 7, NULL, NULL, NULL, NULL, 0);
INSERT INTO `permission` VALUES ('3', '添加文章', '/article/add', 1, '2', 512, 1029, NULL, NULL, NULL, NULL, 0);
INSERT INTO `permission` VALUES ('4', '删除文章', '/article/del', 1, '2', 512, 4096, NULL, NULL, NULL, NULL, 0);
INSERT INTO `permission` VALUES ('5', 'test管理', '/test/**', 0, '1', 512, 4096, NULL, NULL, NULL, NULL, 0);

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '编号',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '姓名',
  `button_grant` int(11) DEFAULT 0 COMMENT '按钮权限',
  `create_time` datetime(0) DEFAULT NULL,
  `update_time` datetime(0) DEFAULT NULL,
  `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `del_flag` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', '管理员1', 32767, NULL, NULL, NULL, NULL, 0);
INSERT INTO `role` VALUES ('1506558565100863489', '测试', 7, NULL, NULL, NULL, NULL, 0);
INSERT INTO `role` VALUES ('1510072193993146370', '运维', 32767, NULL, NULL, NULL, NULL, 0);

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission`  (
  `id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `role_id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `permission_id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `selected` bit(1) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_permission
-- ----------------------------
INSERT INTO `role_permission` VALUES ('1', '1506558565100863489', '1', b'0');
INSERT INTO `role_permission` VALUES ('1506897376838242306', '1', '1', b'0');
INSERT INTO `role_permission` VALUES ('1506897376846630913', '1', '2', b'0');
INSERT INTO `role_permission` VALUES ('1506897376855019521', '1', '3', b'0');
INSERT INTO `role_permission` VALUES ('1506897376867602433', '1', '4', b'0');
INSERT INTO `role_permission` VALUES ('1506897376875991042', '1', '5', b'0');
INSERT INTO `role_permission` VALUES ('2', '1506558565100863489', '2', b'0');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `phone` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `real_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `create_time` datetime(0) DEFAULT NULL,
  `update_time` datetime(0) DEFAULT NULL,
  `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `del_flag` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', '{bcrypt}$2a$10$Lxu43yea5WC1am3knYnegOtHeg/Z/Hqy2U3aiyvo1KCq8KuZ7PhVC', 'admin@sina.com', '1352198281', ' 占占', '2022-03-05 00:00:00', '2022-03-05 00:00:00', NULL, NULL, 0);
INSERT INTO `user` VALUES ('1510130388400971777', 'test', '{bcrypt}$2a$10$bC0pH8jy18eCYRJLH/Y.auVmEXJTylwTzwNSqEhSJ6brkYrOYYY3S', 'test', NULL, NULL, '2022-03-05 00:00:00', '2022-03-05 00:00:00', NULL, NULL, 0);
INSERT INTO `user` VALUES ('1510528511339720705', 'test1', '{bcrypt}$2a$10$k3pYVY8SeI96eD5222HyS.hBX1UD3b1lrE3TtKu9DhFDpG2VTLjJ.', 'test1', NULL, NULL, '2022-03-05 00:00:00', '2022-03-05 00:00:00', NULL, NULL, 0);
INSERT INTO `user` VALUES ('1510834221344702466', 'test11', '{bcrypt}$2a$10$P3NtOiamly5C3JPqYQid1u1PlVpBDtOw1kYY4tLkm5qwX4QJdVaTa', 'test1@sina.com', '13521972991', '张三', '2022-03-05 00:00:00', '2022-03-05 00:00:00', NULL, NULL, 0);
INSERT INTO `user` VALUES ('1511529256413949953', 'dudu', '{bcrypt}$2a$10$p44IgTIERlwXVK5bljj8WegdkXquEsJIhT11krDj30Ja1x8be.fhG', 'dudu@sina.com', '13521972991', 'lisi', '2022-03-05 00:00:00', '2022-04-07 00:00:00', NULL, NULL, 0);
INSERT INTO `user` VALUES ('1511529436223762433', 'dudu2', '{bcrypt}$2a$10$fuAbZbl/KS8XuAiiG6D2ZeWc0uhLgebtNMhpRY3YHuYYevACdrMfW', 'dudu@sina.com', '13521972991', 'lisi', '2022-03-05 00:00:00', '2022-03-05 00:00:00', NULL, NULL, 0);
INSERT INTO `user` VALUES ('1511887713067343874', 'test112', '{bcrypt}$2a$10$7.6sDad.U6wz.X7cuGWjnuPH8XxNe98B40M7RBbbrQQwFReqcY./C', 'test1@sina.com', '13521972991', '张三', '2022-04-07 10:04:55', '2022-04-07 10:04:55', NULL, NULL, 0);
INSERT INTO `user` VALUES ('3', 'zhz', '{bcrypt}$2a$10$Lxu43yea5WC1am3knYnegOtHeg/Z/Hqy2U3aiyvo1KCq8KuZ7PhVC', NULL, NULL, NULL, '2022-03-05 00:00:00', '2022-03-05 00:00:00', NULL, NULL, 0);
INSERT INTO `user` VALUES ('4', 'china', '{bcrypt}$2a$10$Lxu43yea5WC1am3knYnegOtHeg/Z/Hqy2U3aiyvo1KCq8KuZ7PhVC', 'china@sina.com', NULL, NULL, '2022-03-05 00:00:00', '2022-03-05 00:00:00', NULL, NULL, 0);

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `role_id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('1', '1', '1');
INSERT INTO `user_role` VALUES ('1511529518075604994', '1511529436223762433', '1');
INSERT INTO `user_role` VALUES ('1511529524627107841', '1511529436223762433', '1506558565100863489');

SET FOREIGN_KEY_CHECKS = 1;
