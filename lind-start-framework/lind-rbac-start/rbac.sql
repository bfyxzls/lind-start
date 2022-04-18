/*
 Navicat Premium Data Transfer

 Source Server         : 案件开发环境192.168.60.138
 Source Server Type    : MySQL
 Source Server Version : 50730
 Source Host           : 192.168.60.138:3306
 Source Schema         : soc

 Target Server Type    : MySQL
 Target Server Version : 50730
 File Encoding         : 65001

 Date: 18/04/2022 09:14:05
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
  `path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'url路径',
  `type` int(11) DEFAULT NULL,
  `parent_id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `create_time` datetime(0) DEFAULT NULL,
  `update_time` datetime(0) DEFAULT NULL,
  `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `del_flag` int(11) DEFAULT NULL,
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '图标',
  `file_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'vue文件路径',
  `http_method` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'http请求方式',
  `sort_number` double(11, 0) DEFAULT NULL,
  `flag` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT ' 授权标志',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES ('1', '一级菜单', '', 1, '', '2022-04-15 13:05:36', '2022-04-15 13:05:41', NULL, NULL, 0, '', '', 'POST', NULL, NULL);
INSERT INTO `permission` VALUES ('10', '操作日志', '', 0, '6', '2022-04-15 13:05:36', '2022-04-15 13:05:41', NULL, NULL, 0, NULL, NULL, 'GET', NULL, NULL);
INSERT INTO `permission` VALUES ('11', '批量删除', '/user/bulk-del/**', 1, '7', '2022-04-15 13:05:36', '2022-04-15 13:05:41', NULL, NULL, 0, '', '', 'POST', NULL, NULL);
INSERT INTO `permission` VALUES ('1510529717273387009', '内容管理', '', 0, '1', '2022-04-15 13:05:36', '2022-04-15 13:05:41', NULL, NULL, 0, NULL, NULL, 'GET', NULL, NULL);
INSERT INTO `permission` VALUES ('2', '中国法则', '/article/index', 0, '5', '2022-04-15 13:05:36', '2022-04-15 13:05:41', NULL, NULL, 0, 'FA', 'article', 'GET', NULL, NULL);
INSERT INTO `permission` VALUES ('3', '添加法则', '/article/add', 0, '2', '2022-04-15 13:05:36', '2022-04-15 13:05:41', NULL, NULL, 0, 'FA', 'article', 'GET', NULL, NULL);
INSERT INTO `permission` VALUES ('33c6a1b2f880ee45786b8cce8069078f', 'name4', '1', 0, '5', '2022-04-16 23:18:46', '2022-04-16 23:18:46', 'admin', 'admin', 0, 'xiangqufill', NULL, NULL, 0, '');
INSERT INTO `permission` VALUES ('3a82aaf0c5da091b21e897124b95970a', 'name', 'ccc', 0, '22', '2022-04-16 23:09:04', '2022-04-16 23:09:04', 'admin', 'admin', 0, 'admin', NULL, NULL, 0, '');
INSERT INTO `permission` VALUES ('4', '删除法则', '/article/del', 1, '2', '2022-04-15 13:05:36', '2022-04-15 13:05:41', NULL, NULL, 0, 'FA', 'article', 'GET', NULL, NULL);
INSERT INTO `permission` VALUES ('5', '数据管理', '', 0, '1', '2022-04-15 13:05:36', '2022-04-15 13:05:41', NULL, NULL, 0, NULL, NULL, 'GET', NULL, NULL);
INSERT INTO `permission` VALUES ('6', '系统管理', '', 0, '1', '2022-04-15 13:05:36', '2022-04-15 13:05:41', NULL, NULL, 0, NULL, NULL, 'GET', NULL, NULL);
INSERT INTO `permission` VALUES ('7', '用户管理', '/user', 0, '6', '2022-04-15 13:05:36', '2022-04-15 13:05:41', NULL, NULL, 0, NULL, NULL, '', NULL, NULL);
INSERT INTO `permission` VALUES ('8', '模块管理', '/permission', 0, '6', '2022-04-15 13:05:36', '2022-04-15 13:05:41', NULL, NULL, 0, NULL, NULL, 'GET', NULL, NULL);
INSERT INTO `permission` VALUES ('8274785332b5fb91f158be3ef32010fc', 'name1', '11', 0, '5', '2022-04-16 23:12:37', '2022-04-16 23:12:37', 'admin', 'admin', 0, 'zhedie', NULL, NULL, 0, '');
INSERT INTO `permission` VALUES ('8b3ed3a2a310bf53dd1dc338151ea7b0', '编辑', '/user', 1, '7', '2022-04-15 13:05:36', '2022-04-15 13:05:41', NULL, NULL, 0, '', '', 'PUT', NULL, NULL);
INSERT INTO `permission` VALUES ('9', '角色管理', '/role', 0, '6', '2022-04-15 13:05:36', '2022-04-15 13:05:41', NULL, NULL, 0, NULL, NULL, 'GET', NULL, NULL);
INSERT INTO `permission` VALUES ('a83fdf188db5d40ee185a910556a5d5c', '添加', '/user', 1, '7', '2022-04-15 13:05:36', '2022-04-15 13:05:41', NULL, NULL, 0, '', '', 'POST', NULL, NULL);
INSERT INTO `permission` VALUES ('a83fdf188db5d40ee185a910556a5d5d', '删除', '/user', 1, '7', '2022-04-15 13:05:36', '2022-04-15 13:05:41', NULL, NULL, 0, '', '', 'DELETE', NULL, NULL);
INSERT INTO `permission` VALUES ('b4d791dd7d8c9421bad74a1954e9ebf2', 'fname', '12', 0, '3', '2022-04-16 23:10:13', '2022-04-16 23:10:13', 'admin', 'admin', 0, 'zhedie', NULL, NULL, 0, '');
INSERT INTO `permission` VALUES ('c86c9483684edbb7e099649dfdaab923', 'name3', '111/ccc', 0, '2', '2022-04-16 23:16:25', '2022-04-16 23:16:25', 'admin', 'admin', 0, 'admin', NULL, NULL, 0, '');
INSERT INTO `permission` VALUES ('de507ca61f22c315cf94298e35f0785b', 'name1', 'a', 0, '3', '2022-04-16 23:11:08', '2022-04-16 23:11:08', 'admin', 'admin', 0, 'xiangqufill', NULL, NULL, 0, '');


-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '编号',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '姓名',
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
INSERT INTO `role` VALUES ('1', '管理员1', NULL, NULL, NULL, NULL, 0);
INSERT INTO `role` VALUES ('1506558565100863489', '测试', NULL, NULL, NULL, NULL, 0);
INSERT INTO `role` VALUES ('1510072193993146370', '运维', NULL, NULL, NULL, NULL, 0);
INSERT INTO `role` VALUES ('526293298436902912', 'name', '2022-04-12 21:14:01', '2022-04-12 21:14:01', 'admin', 'admin', 0);
INSERT INTO `role` VALUES ('526293604298133504', 'name2', '2022-04-12 21:15:14', '2022-04-12 21:15:14', 'admin', 'admin', 0);
INSERT INTO `role` VALUES ('526293825358925824', 'name1', '2022-04-12 21:16:07', '2022-04-12 21:16:07', 'admin', 'admin', 0);
INSERT INTO `role` VALUES ('526294138765709312', '1', '2022-04-12 21:17:22', '2022-04-12 21:17:22', 'admin', 'admin', 0);
INSERT INTO `role` VALUES ('526294338074841088', '2', '2022-04-12 21:18:09', '2022-04-12 21:18:09', 'admin', 'admin', 0);
INSERT INTO `role` VALUES ('526294668867014656', '3', '2022-04-12 21:19:28', '2022-04-12 21:19:28', 'admin', 'admin', 0);
INSERT INTO `role` VALUES ('526294947737899008', '4', '2022-04-12 21:20:35', '2022-04-12 21:20:35', 'admin', 'admin', 0);

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
INSERT INTO `role_permission` VALUES ('10', '1', '1', b'1');
INSERT INTO `role_permission` VALUES ('1506897376846630913', '1', '2', b'0');
INSERT INTO `role_permission` VALUES ('1506897376855019521', '1', '3', b'0');
INSERT INTO `role_permission` VALUES ('1506897376867602433', '1', '4', b'0');
INSERT INTO `role_permission` VALUES ('1506897376875991042', '1', '5', b'0');
INSERT INTO `role_permission` VALUES ('1514057482044018690', '1506558565100863489', '1', b'0');
INSERT INTO `role_permission` VALUES ('1514057482056601602', '1506558565100863489', '2', b'0');
INSERT INTO `role_permission` VALUES ('1514057482077573121', '1506558565100863489', '3', b'0');
INSERT INTO `role_permission` VALUES ('1514057482081767425', '1506558565100863489', '4', b'0');
INSERT INTO `role_permission` VALUES ('1514057482098544641', '1506558565100863489', '5', b'0');
INSERT INTO `role_permission` VALUES ('1514884706477289474', '1', 'd96943ac488d74438073d1ba866d413f', b'0');
INSERT INTO `role_permission` VALUES ('1515346543174053889', '1', '3a82aaf0c5da091b21e897124b95970a', b'0');
INSERT INTO `role_permission` VALUES ('1515346831633117186', '1', 'b4d791dd7d8c9421bad74a1954e9ebf2', b'0');
INSERT INTO `role_permission` VALUES ('1515347060306571265', '1', 'de507ca61f22c315cf94298e35f0785b', b'0');
INSERT INTO `role_permission` VALUES ('1515347434232967169', '1', '8274785332b5fb91f158be3ef32010fc', b'0');
INSERT INTO `role_permission` VALUES ('1515348393243799553', '1', 'c86c9483684edbb7e099649dfdaab923', b'0');
INSERT INTO `role_permission` VALUES ('1515348981973086209', '1', '33c6a1b2f880ee45786b8cce8069078f', b'0');
INSERT INTO `role_permission` VALUES ('3', '1', '6', b'0');
INSERT INTO `role_permission` VALUES ('4', '1', '7', b'0');
INSERT INTO `role_permission` VALUES ('5', '1', '8', b'0');
INSERT INTO `role_permission` VALUES ('6', '1', '9', b'0');
INSERT INTO `role_permission` VALUES ('7', '1', '10', b'0');
INSERT INTO `role_permission` VALUES ('8', '1', '8b3ed3a2a310bf53dd1dc338151ea7b0', b'0');
INSERT INTO `role_permission` VALUES ('9', '1', 'a83fdf188db5d40ee185a910556a5d5d', b'0');

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
  `status` int(11) DEFAULT 0 COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', '{bcrypt}$2a$10$ht1q5Ox8g2B4jNHHViAULOjGwOaCCk9UZbeDc7iIbprjpUufrGTie', 'admin@sina.com', '1352198281', '张三1', '2022-03-05 00:00:00', '2022-03-05 00:00:00', 'zzl', 'zzl', 0, 0);
INSERT INTO `user` VALUES ('1510130388400971777', 'test', '{bcrypt}$2a$10$bC0pH8jy18eCYRJLH/Y.auVmEXJTylwTzwNSqEhSJ6brkYrOYYY3S', 'test', NULL, NULL, '2022-03-05 00:00:00', '2022-03-05 00:00:00', NULL, NULL, 0, 0);
INSERT INTO `user` VALUES ('1510528511339720705', 'test1', '{bcrypt}$2a$10$k3pYVY8SeI96eD5222HyS.hBX1UD3b1lrE3TtKu9DhFDpG2VTLjJ.', 'test1', NULL, NULL, '2022-03-05 00:00:00', '2022-03-05 00:00:00', NULL, NULL, 0, 0);
INSERT INTO `user` VALUES ('1510834221344702466', 'test11', '{bcrypt}$2a$10$P3NtOiamly5C3JPqYQid1u1PlVpBDtOw1kYY4tLkm5qwX4QJdVaTa', 'test1@sina.com', '13521972991', '张三', '2022-03-05 00:00:00', '2022-03-05 00:00:00', NULL, NULL, 0, 0);
INSERT INTO `user` VALUES ('1511529256413949953', 'dudu', '{bcrypt}$2a$10$p44IgTIERlwXVK5bljj8WegdkXquEsJIhT11krDj30Ja1x8be.fhG', 'dudu@sina.com', '13521972991', 'lisi', '2022-03-05 00:00:00', '2022-04-07 00:00:00', NULL, NULL, 0, 0);
INSERT INTO `user` VALUES ('1511529436223762433', 'dudu2', '{bcrypt}$2a$10$fuAbZbl/KS8XuAiiG6D2ZeWc0uhLgebtNMhpRY3YHuYYevACdrMfW', 'dudu@sina.com', '13521972991', 'lisi', '2022-03-05 00:00:00', '2022-03-05 00:00:00', NULL, NULL, 0, 0);
INSERT INTO `user` VALUES ('1511887713067343874', 'test112', '{bcrypt}$2a$10$7.6sDad.U6wz.X7cuGWjnuPH8XxNe98B40M7RBbbrQQwFReqcY./C', 'test1@sina.com', '13521972991', '张三', '2022-04-07 10:04:55', '2022-04-07 10:04:55', NULL, NULL, 0, 0);
INSERT INTO `user` VALUES ('3', 'zhz', '{bcrypt}$2a$10$Lxu43yea5WC1am3knYnegOtHeg/Z/Hqy2U3aiyvo1KCq8KuZ7PhVC', NULL, NULL, NULL, '2022-03-05 00:00:00', '2022-03-05 00:00:00', NULL, NULL, 1, 0);
INSERT INTO `user` VALUES ('4', 'china', '{bcrypt}$2a$10$Lxu43yea5WC1am3knYnegOtHeg/Z/Hqy2U3aiyvo1KCq8KuZ7PhVC', 'china@sina.com', NULL, NULL, '2022-03-05 00:00:00', '2022-03-05 00:00:00', NULL, NULL, 1, 0);
INSERT INTO `user` VALUES ('9012f39e28fc1029375b50a1df4d7728', '1', '{bcrypt}$2a$10$7IzgR6BP9xoOgJUcCAMHE.4xVdIKYm9wGXqMo4f8Y29YAGIF7BBtu', '112223@qq.com', '13754911025', NULL, '2022-04-11 19:13:01', '2022-04-11 19:13:01', 'admin', 'admin', 0, 1);
INSERT INTO `user` VALUES ('ab9a48d4f49d93237f7090d340d9fa07', '123', '{bcrypt}$2a$10$9Q0m8plueumCG23s/kThUOhGDITrYAe4SwI1G.4tZtoJOPgRi7mrS', '123@qq.com', '13754911028', NULL, '2022-04-12 10:04:14', '2022-04-12 10:04:14', 'admin', 'admin', 0, 1);
INSERT INTO `user` VALUES ('ac1930cfd142bfe0d7840314dc70bffe', '123', '{bcrypt}$2a$10$dFRAlz6O6q9aBYKq1u9EwOMUnMCe/JjrEFYCowIEtird5qbjTLIty', '568462525@qq.com', '13754911023', NULL, '2022-04-11 19:31:15', '2022-04-11 19:31:15', 'admin', 'admin', 1, 1);

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
INSERT INTO `user_role` VALUES ('1513475199771717634', '9012f39e28fc1029375b50a1df4d7728', '1510072193993146370');
INSERT INTO `user_role` VALUES ('1513699479184830465', 'ab9a48d4f49d93237f7090d340d9fa07', '1');


SET FOREIGN_KEY_CHECKS = 1;
