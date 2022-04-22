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

 Date: 22/04/2022 10:17:50
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for dictionary
-- ----------------------------
DROP TABLE IF EXISTS `dictionary`;
CREATE TABLE `dictionary`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'code码',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '名称',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '类型',
  `p_id` int(11) DEFAULT NULL COMMENT '父id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 106 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '下拉框字典表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dictionary
-- ----------------------------
INSERT INTO `dictionary` VALUES (1, NULL, '语言类型', 'languageType', NULL);
INSERT INTO `dictionary` VALUES (2, 'zh-CN', '中文', 'languageType', 1);
INSERT INTO `dictionary` VALUES (3, 'en-US', '英文', 'languageType', 1);
INSERT INTO `dictionary` VALUES (4, 'ru-RU', '俄文', 'languageType', 1);
INSERT INTO `dictionary` VALUES (5, NULL, '稿件类型', 'manuscriptType', NULL);
INSERT INTO `dictionary` VALUES (6, '0', '中文原稿', 'manuscriptType', 5);
INSERT INTO `dictionary` VALUES (7, '1', '中文译稿', 'manuscriptType', 5);
INSERT INTO `dictionary` VALUES (8, '2', '俄文原稿', 'manuscriptType', 5);
INSERT INTO `dictionary` VALUES (9, '3', '俄文译稿', 'manuscriptType', 5);
INSERT INTO `dictionary` VALUES (10, '4', '英文原稿', 'manuscriptType', 5);
INSERT INTO `dictionary` VALUES (11, '5', '英文译稿', 'manuscriptType', 5);
INSERT INTO `dictionary` VALUES (12, NULL, '稿件状态', 'manuscriptStatus', NULL);
INSERT INTO `dictionary` VALUES (13, '0', '未上架', 'manuscriptStatus', 12);
INSERT INTO `dictionary` VALUES (14, '1', '下架', 'manuscriptStatus', 12);
INSERT INTO `dictionary` VALUES (15, '2', '已上架', 'manuscriptStatus', 12);
INSERT INTO `dictionary` VALUES (16, NULL, '国别', 'differentCountries', NULL);
INSERT INTO `dictionary` VALUES (17, '0', '印度', 'differentCountries', 16);
INSERT INTO `dictionary` VALUES (18, '1', '哈萨克斯坦', 'differentCountries', 16);
INSERT INTO `dictionary` VALUES (19, '2', '中国', 'differentCountries', 16);
INSERT INTO `dictionary` VALUES (20, '3', '吉尔吉斯斯坦', 'differentCountries', 16);
INSERT INTO `dictionary` VALUES (21, '4', '巴基斯坦', 'differentCountries', 16);
INSERT INTO `dictionary` VALUES (22, '5', '俄罗斯', 'differentCountries', 16);
INSERT INTO `dictionary` VALUES (23, '6', '塔吉克斯坦', 'differentCountries', 16);
INSERT INTO `dictionary` VALUES (24, '7', '乌兹别克斯坦', 'differentCountries', 16);
INSERT INTO `dictionary` VALUES (25, NULL, '职能机构', 'functionalOrgan', NULL);
INSERT INTO `dictionary` VALUES (26, '0', '委员会', 'functionalOrgan', 25);
INSERT INTO `dictionary` VALUES (27, '1', '秘书处', 'functionalOrgan', 25);
INSERT INTO `dictionary` VALUES (28, '2', '西安中心', 'functionalOrgan', 25);
INSERT INTO `dictionary` VALUES (29, '3', '交流合作基地', 'functionalOrgan', 25);
INSERT INTO `dictionary` VALUES (30, NULL, '操作类型', 'operateType', NULL);
INSERT INTO `dictionary` VALUES (31, '1', '登录', 'operateType', 30);
INSERT INTO `dictionary` VALUES (32, '2', '登出', 'operateType', 30);
INSERT INTO `dictionary` VALUES (33, '3', '添加', 'operateType', 30);
INSERT INTO `dictionary` VALUES (34, '4', '编辑', 'operateType', 30);
INSERT INTO `dictionary` VALUES (35, '5', '删除', 'operateType', 30);
INSERT INTO `dictionary` VALUES (36, '6', '上架', 'operateType', 30);
INSERT INTO `dictionary` VALUES (37, '7', '下架', 'operateType', 30);
INSERT INTO `dictionary` VALUES (38, '10', '预览', 'operateType', 30);
INSERT INTO `dictionary` VALUES (39, '9', '图片预览', 'operateType', 30);
INSERT INTO `dictionary` VALUES (40, '10', '添加原稿', 'operateType', 30);
INSERT INTO `dictionary` VALUES (41, '11', '添加译稿', 'operateType', 30);
INSERT INTO `dictionary` VALUES (42, '12', '刷新', 'operateType', 30);
INSERT INTO `dictionary` VALUES (43, '13', '导出', 'operateType', 30);
INSERT INTO `dictionary` VALUES (44, '14', '目录导出', 'operateType', 30);
INSERT INTO `dictionary` VALUES (45, '15', '分配权限', 'operateType', 30);
INSERT INTO `dictionary` VALUES (46, '16', '前台隐藏', 'operateType', 30);
INSERT INTO `dictionary` VALUES (47, '17', '批量下架', 'operateType', 30);
INSERT INTO `dictionary` VALUES (48, NULL, '栏目层级', 'columnLevel', NULL);
INSERT INTO `dictionary` VALUES (49, '0', '一级', 'columnLevel', 48);
INSERT INTO `dictionary` VALUES (50, '1', '二级', 'columnLevel', 48);
INSERT INTO `dictionary` VALUES (52, NULL, '模块类型', 'moduleType', NULL);
INSERT INTO `dictionary` VALUES (54, '0', '用户登录', 'moduleType', 52);
INSERT INTO `dictionary` VALUES (55, '1', '用户登出', 'moduleType', 52);
INSERT INTO `dictionary` VALUES (56, '2', '导航栏', 'moduleType', 52);
INSERT INTO `dictionary` VALUES (57, '3', '友情链接', 'moduleType', 52);
INSERT INTO `dictionary` VALUES (58, '4', '国家介绍', 'moduleType', 52);
INSERT INTO `dictionary` VALUES (59, '5', '职能机构', 'moduleType', 52);
INSERT INTO `dictionary` VALUES (61, '6', '中国法规', 'moduleType', 52);
INSERT INTO `dictionary` VALUES (62, '7', '外国法规', 'moduleType', 52);
INSERT INTO `dictionary` VALUES (63, '8', '国际条约', 'moduleType', 52);
INSERT INTO `dictionary` VALUES (64, '9', '经典案例', 'moduleType', 52);
INSERT INTO `dictionary` VALUES (65, '10', '法律服务机构', 'moduleType', 52);
INSERT INTO `dictionary` VALUES (66, '11', '咨询专家', 'moduleType', 52);
INSERT INTO `dictionary` VALUES (67, '', '权限类型', 'PermissionType', NULL);
INSERT INTO `dictionary` VALUES (69, '0', '菜单', 'PermissionType', 67);
INSERT INTO `dictionary` VALUES (70, '1', '按钮', 'PermissionType', 67);
INSERT INTO `dictionary` VALUES (71, NULL, '是否前台隐藏', 'foregroundHidden', NULL);
INSERT INTO `dictionary` VALUES (72, '0', '否', 'foregroundHidden', 71);
INSERT INTO `dictionary` VALUES (73, '1', '是', 'foregroundHidden', 71);
INSERT INTO `dictionary` VALUES (74, NULL, '前台索引库', 'libraryType', NULL);
INSERT INTO `dictionary` VALUES (75, 'ywsk_law_zh-cn', '中外法规(中)', 'libraryType', 74);
INSERT INTO `dictionary` VALUES (76, 'ywsk_law_ru-ru', '中外法规(俄)', 'libraryType', 74);
INSERT INTO `dictionary` VALUES (77, 'ywsk_law_en-us', '中外法规(英)', 'libraryType', 74);
INSERT INTO `dictionary` VALUES (78, 'ywsk_pfnl_zh-cn', '经典案例(中)', 'libraryType', 74);
INSERT INTO `dictionary` VALUES (79, 'ywsk_pfnl_ru-ru', '经典案例(俄)', 'libraryType', 74);
INSERT INTO `dictionary` VALUES (80, 'ywsk_pfnl_en-us', '经典案例(英)', 'libraryType', 74);
INSERT INTO `dictionary` VALUES (81, 'ywsk_fo_zh-cn', '职能机构(俄)', 'libraryType', 74);
INSERT INTO `dictionary` VALUES (82, 'ywsk_fo_ru-ru', '职能机构(俄)', 'libraryType', 74);
INSERT INTO `dictionary` VALUES (83, 'ywsk_fo_en-us', '职能机构(英)', 'libraryType', 74);
INSERT INTO `dictionary` VALUES (84, 'ywsk_eagn_zh-cn', '国际条约(中)', 'libraryType', 74);
INSERT INTO `dictionary` VALUES (85, 'ywsk_eagn_ru-ru', '国际条约(俄)', 'libraryType', 74);
INSERT INTO `dictionary` VALUES (86, 'ywsk_eagn_en-us', '国际条约(英)', 'libraryType', 74);
INSERT INTO `dictionary` VALUES (87, 'ywsk_home_zh-cn', '首页(中)', 'libraryType', 74);
INSERT INTO `dictionary` VALUES (88, 'ywsk_home_ru-ru', '首页(俄)', 'libraryType', 74);
INSERT INTO `dictionary` VALUES (89, 'ywsk_home_en-us', '首页(英)', 'libraryType', 74);
INSERT INTO `dictionary` VALUES (90, NULL, ' 性别', 'gender', NULL);
INSERT INTO `dictionary` VALUES (91, '0', '男', 'gender', 90);
INSERT INTO `dictionary` VALUES (92, '1', '女', 'gender', 90);
INSERT INTO `dictionary` VALUES (93, NULL, '上一层级', 'upperLevelId', NULL);
INSERT INTO `dictionary` VALUES (94, '01', '首页', 'upperLevelId', 93);
INSERT INTO `dictionary` VALUES (95, '02', '职能机构', 'upperLevelId', 93);
INSERT INTO `dictionary` VALUES (96, '03', '法律法规', 'upperLevelId', 93);
INSERT INTO `dictionary` VALUES (97, '04', '案例数据', 'upperLevelId', 93);
INSERT INTO `dictionary` VALUES (98, '05', '法律服务机构', 'upperLevelId', 93);
INSERT INTO `dictionary` VALUES (99, '06', '专家数据', 'upperLevelId', 93);
INSERT INTO `dictionary` VALUES (100, '07', '中外法规', 'upperLevelId', 93);
INSERT INTO `dictionary` VALUES (101, '08', '国际条约', 'upperLevelId', 93);
INSERT INTO `dictionary` VALUES (102, '12', ' 用户管理', 'moduleType', 52);
INSERT INTO `dictionary` VALUES (103, '13', '角色管理', 'moduleType', 52);
INSERT INTO `dictionary` VALUES (104, '14', '菜单管理', 'moduleType', 52);
INSERT INTO `dictionary` VALUES (105, '15', '聚类管理', 'moduleType', 52);

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission`  (
  `id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `api_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '后台地址',
  `http_method` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'http请求方式',
  `sort_number` double(11, 0) DEFAULT NULL,
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
  `operate_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '操作类型，对应字典里的OperateType',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES ('1', '北大英华', NULL, 'POST', NULL, '', 0, '', '2022-04-15 13:05:36', '2022-04-15 13:05:41', NULL, NULL, 0, 'aggregation', '', NULL);
INSERT INTO `sys_permission` VALUES ('13', '首页友情链接', '', NULL, 2, '/sjgl/rotatList', 0, '5', '2022-04-16 23:18:46', '2022-04-16 23:18:46', 'admin', 'admin', 0, 'aggregation', '/sjgl/rotat/rotatList', NULL);
INSERT INTO `sys_permission` VALUES ('14', '新增', '', NULL, 100, '', 1, '13', '2022-04-16 23:12:37', '2022-04-16 23:12:37', 'admin', 'admin', 0, 'aggregation', NULL, '3');
INSERT INTO `sys_permission` VALUES ('15', '国家介绍', '', NULL, 3, '/sjgl/countryList', 0, '5', '2022-04-16 23:18:46', '2022-04-16 23:18:46', 'admin', 'admin', 0, 'aggregation', '/sjgl/country/countryList', NULL);
INSERT INTO `sys_permission` VALUES ('16', '职能机构', '', NULL, 4, '/sjgl/organizeList', 0, '5', '2022-04-16 23:18:46', '2022-04-16 23:18:46', 'admin', 'admin', 0, 'aggregation', '/sjgl/organize/organizeList', NULL);
INSERT INTO `sys_permission` VALUES ('17', '中国法规', '', NULL, 5, '/sjgl/chinese', 0, '5', '2022-04-16 23:18:46', '2022-04-16 23:18:46', 'admin', 'admin', 0, 'aggregation', '/sjgl/chinese/chineseList', NULL);
INSERT INTO `sys_permission` VALUES ('18', '聚类管理', '', NULL, 100, '/xtsz/clusterList', 0, '6', '2022-04-16 23:18:46', '2022-04-16 23:18:46', 'admin', 'admin', 0, 'aggregation', '/xtsz/cluster/clusterList', NULL);
INSERT INTO `sys_permission` VALUES ('19', '角色管理', '/role/query**', NULL, 100, '/xtsz/roleList', 0, '6', '2022-04-16 23:18:46', '2022-04-16 23:18:46', 'admin', 'admin', 0, 'aggregation', '/xtsz/sys/roleList', NULL);
INSERT INTO `sys_permission` VALUES ('2', '导航栏管理', '', 'GET', 1, '/sjgl/navList', 0, '5', '2022-04-15 13:05:36', '2022-04-15 13:05:41', NULL, NULL, 0, 'aggregation', '/sjgl/nav/navList', NULL);
INSERT INTO `sys_permission` VALUES ('20', '用户管理', '/user/list**', NULL, 100, '/sys/userList', 0, '6', '2022-04-16 23:18:46', '2022-04-16 23:18:46', 'admin', 'admin', 0, 'aggregation', '/xtsz/sys/userList', NULL);
INSERT INTO `sys_permission` VALUES ('21', '菜单管理', '/permission/list**', 'GET', 100, '/sys/menu', 0, '6', '2022-04-16 23:18:46', '2022-04-16 23:18:46', 'admin', 'admin', 0, 'aggregation', '/xtsz/sys/menu', NULL);
INSERT INTO `sys_permission` VALUES ('22', '操作日志', '/operateLog/**', NULL, 100, '/operationList', 0, '6', '2022-04-16 23:18:46', '2022-04-16 23:18:46', 'admin', 'admin', 0, 'aggregation', '/xtsz/operation/operationList', NULL);
INSERT INTO `sys_permission` VALUES ('23', '添加', '/user/add**', 'POST', 100, '', 1, '20', '2022-04-16 23:18:46', '2022-04-16 23:18:46', 'admin', 'admin', 0, 'aggregation', '', '3');
INSERT INTO `sys_permission` VALUES ('24', '编辑', '/user/update/**', 'PUT', 100, '', 1, '20', '2022-04-16 23:18:46', '2022-04-16 23:18:46', 'admin', 'admin', 0, 'aggregation', '', '4');
INSERT INTO `sys_permission` VALUES ('25', '删除', '/user/del/**', 'PUT', 100, '', 1, '20', '2022-04-16 23:18:46', '2022-04-16 23:18:46', 'admin', 'admin', 0, 'aggregation', '', '4');
INSERT INTO `sys_permission` VALUES ('26', '批量删除', '/user/bulk-del**', 'POST', 100, '', 1, '20', '2022-04-16 23:18:46', '2022-04-16 23:18:46', 'admin', 'admin', 0, 'aggregation', '', '4');
INSERT INTO `sys_permission` VALUES ('27', '重置密码', '/user/bulk-reset-password**', 'PUT', 100, '', 1, '20', '2022-04-16 23:18:46', '2022-04-16 23:18:46', 'admin', 'admin', 0, 'aggregation', '', '4');
INSERT INTO `sys_permission` VALUES ('28', '编辑', '/role/update/**', 'PUT', 100, '', 1, '19', '2022-04-16 23:18:46', '2022-04-16 23:18:46', 'admin', 'admin', 0, 'aggregation', '', '4');
INSERT INTO `sys_permission` VALUES ('29', '删除', '/role/del/**', 'PUT', 100, '', 1, '19', '2022-04-16 23:18:46', '2022-04-16 23:18:46', 'admin', 'admin', 0, 'aggregation', '', '4');
INSERT INTO `sys_permission` VALUES ('30', '批量删除', '/role/bulk-del**', 'POST', 100, '', 1, '19', '2022-04-16 23:18:46', '2022-04-16 23:18:46', 'admin', 'admin', 0, 'aggregation', '', '4');
INSERT INTO `sys_permission` VALUES ('31', '添加', '/role/add**', 'POST', 100, '', 1, '19', '2022-04-16 23:18:46', '2022-04-16 23:18:46', 'admin', 'admin', 0, 'aggregation', '', '3');
INSERT INTO `sys_permission` VALUES ('32', '外国法规', '', 'GET', 6, '/sjgl/foreignReg', 0, '5', '2022-04-16 23:18:46', '2022-04-16 23:18:46', 'admin', 'admin', 0, 'aggregation', '/sjgl/chinese/chineseList', NULL);
INSERT INTO `sys_permission` VALUES ('33', '国际条约', '', 'GET', 7, '/sjgl/interList', 0, '5', '2022-04-16 23:18:46', '2022-04-16 23:18:46', 'admin', 'admin', 0, 'aggregation', '/sjgl/chinese/chineseList', NULL);
INSERT INTO `sys_permission` VALUES ('34', '经典案例', '', 'GET', 8, '/sjgl/caseList', 0, '5', '2022-04-19 09:45:40', '2022-04-19 09:45:40', 'admin', 'admin', 0, 'manage', NULL, NULL);
INSERT INTO `sys_permission` VALUES ('35', '法律服务机构', '', 'GET', 9, '/sjgl/service', 0, '5', '2022-04-16 23:18:46', '2022-04-16 23:18:46', 'admin', 'admin', 0, 'aggregation', '/sjgl/chinese/chineseList', NULL);
INSERT INTO `sys_permission` VALUES ('36', '咨询专家', '', 'GET', 10, '/sjgl/expertList', 0, '5', '2022-04-16 23:18:46', '2022-04-16 23:18:46', 'admin', 'admin', 0, 'aggregation', '/sjgl/chinese/chineseList', NULL);
INSERT INTO `sys_permission` VALUES ('5', '数据管理', NULL, 'GET', NULL, '', 0, '1', '2022-04-15 13:05:36', '2022-04-15 13:05:41', NULL, NULL, 0, 'aggregation', NULL, NULL);
INSERT INTO `sys_permission` VALUES ('6', '系统管理', NULL, 'GET', NULL, '', 0, '1', '2022-04-15 13:05:36', '2022-04-15 13:05:41', NULL, NULL, 0, 'aggregation', NULL, NULL);
INSERT INTO `sys_permission` VALUES ('d2e9dffaafb482defd413c87ac321b92', 'name', NULL, 'GET', 100, '/xaa', 0, '22', '2022-04-19 20:19:23', '2022-04-19 20:19:23', 'admin', 'admin', 0, 'bianji', NULL, NULL);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
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
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '管理员1', NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_role` VALUES ('1506558565100863489', '测试', NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_role` VALUES ('1510072193993146370', '运维', NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_role` VALUES ('483733d7c7ae06f113a8a8d356c0a0de', '张三11', '2022-04-19 10:56:29', '2022-04-19 10:56:29', 'admin', 'admin', 0);
INSERT INTO `sys_role` VALUES ('526293298436902912', 'name', '2022-04-12 21:14:01', '2022-04-12 21:14:01', 'admin', 'admin', 0);
INSERT INTO `sys_role` VALUES ('526293604298133504', 'name2', '2022-04-12 21:15:14', '2022-04-12 21:15:14', 'admin', 'admin', 0);
INSERT INTO `sys_role` VALUES ('526293825358925824', 'name1', '2022-04-12 21:16:07', '2022-04-12 21:16:07', 'admin', 'admin', 0);
INSERT INTO `sys_role` VALUES ('526294138765709312', '1', '2022-04-12 21:17:22', '2022-04-12 21:17:22', 'admin', 'admin', 0);
INSERT INTO `sys_role` VALUES ('526294338074841088', '2', '2022-04-12 21:18:09', '2022-04-12 21:18:09', 'admin', 'admin', 0);
INSERT INTO `sys_role` VALUES ('526294668867014656', '3', '2022-04-12 21:19:28', '2022-04-12 21:19:28', 'admin', 'admin', 0);
INSERT INTO `sys_role` VALUES ('526294947737899008', '4', '2022-04-12 21:20:35', '2022-04-12 21:20:35', 'admin', 'admin', 0);
INSERT INTO `sys_role` VALUES ('a2358c4ab72c555584ffeaf62e7c02ea', '小花', '2022-04-19 09:51:53', '2022-04-19 09:51:53', 'admin', 'admin', 0);
INSERT INTO `sys_role` VALUES ('c00ec79c19b40674214f04bc584ca564', '小花管理员016', '2022-04-19 10:00:05', '2022-04-19 10:00:05', 'admin', 'admin', 0);
INSERT INTO `sys_role` VALUES ('c2c68251eb65cf2e95d81d3acf9c1577', '小花管理员', '2022-04-19 09:53:07', '2022-04-19 09:53:07', 'admin', 'admin', 0);
INSERT INTO `sys_role` VALUES ('f55c00faa7379be90eca3f381d80f621', '测试001', '2022-04-19 09:35:04', '2022-04-19 09:35:04', 'admin', 'admin', 0);

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission`  (
  `id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `role_id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `permission_id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `selected` bit(1) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO `sys_role_permission` VALUES ('10', '1', '1', b'1');
INSERT INTO `sys_role_permission` VALUES ('11', '1', '24', b'0');
INSERT INTO `sys_role_permission` VALUES ('12', '1', '25', b'0');
INSERT INTO `sys_role_permission` VALUES ('13', '1', '26', b'0');
INSERT INTO `sys_role_permission` VALUES ('14', '1', '27', b'0');
INSERT INTO `sys_role_permission` VALUES ('15', '1', '28', b'0');
INSERT INTO `sys_role_permission` VALUES ('1506897376846630913', '1', '2', b'0');
INSERT INTO `sys_role_permission` VALUES ('1506897376855019521', '1', '3', b'0');
INSERT INTO `sys_role_permission` VALUES ('1506897376867602433', '1', '4', b'0');
INSERT INTO `sys_role_permission` VALUES ('1506897376875991042', '1', '5', b'0');
INSERT INTO `sys_role_permission` VALUES ('1514057482044018690', '1', '11', b'0');
INSERT INTO `sys_role_permission` VALUES ('1514057482056601602', '1', '12', b'0');
INSERT INTO `sys_role_permission` VALUES ('1514057482077573121', '1', '13', b'0');
INSERT INTO `sys_role_permission` VALUES ('1514057482081767425', '1', '14', b'0');
INSERT INTO `sys_role_permission` VALUES ('1514057482098544641', '1', '15', b'0');
INSERT INTO `sys_role_permission` VALUES ('1514884706477289474', '1', '16', b'0');
INSERT INTO `sys_role_permission` VALUES ('1515346543174053889', '1', '17', b'0');
INSERT INTO `sys_role_permission` VALUES ('1515346831633117186', '1', '18', b'0');
INSERT INTO `sys_role_permission` VALUES ('1515347060306571265', '1', '19', b'0');
INSERT INTO `sys_role_permission` VALUES ('1515348393243799553', '1', '20', b'0');
INSERT INTO `sys_role_permission` VALUES ('1515348981973086209', '1', '22', b'0');
INSERT INTO `sys_role_permission` VALUES ('1516231524637212673', '1', '60b319580942a3869e849e472940a95d', b'0');
INSERT INTO `sys_role_permission` VALUES ('1516391001743290370', '1', 'd2e9dffaafb482defd413c87ac321b92', b'0');
INSERT INTO `sys_role_permission` VALUES ('16', '1', '29', b'0');
INSERT INTO `sys_role_permission` VALUES ('17', '1', '30', b'0');
INSERT INTO `sys_role_permission` VALUES ('18', '1', '31', b'0');
INSERT INTO `sys_role_permission` VALUES ('19', '1', '32', b'0');
INSERT INTO `sys_role_permission` VALUES ('20', '1', '33', b'0');
INSERT INTO `sys_role_permission` VALUES ('21', '1', '34', b'0');
INSERT INTO `sys_role_permission` VALUES ('22', '1', '35', b'0');
INSERT INTO `sys_role_permission` VALUES ('23', '1', '36', b'0');
INSERT INTO `sys_role_permission` VALUES ('3', '1', '6', b'0');
INSERT INTO `sys_role_permission` VALUES ('4', '1', '7', b'0');
INSERT INTO `sys_role_permission` VALUES ('5', '1', '8', b'0');
INSERT INTO `sys_role_permission` VALUES ('6', '1', '9', b'0');
INSERT INTO `sys_role_permission` VALUES ('7', '1', '10', b'0');
INSERT INTO `sys_role_permission` VALUES ('8', '1', '23', b'0');
INSERT INTO `sys_role_permission` VALUES ('9', '1', '24', b'0');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
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
  `organization` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `job` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `gender` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'JxAl4jrBu3ApOPe/aWxvL7B2+GeKx/zT1vNt6GMzXbkEkU8dNV7IhC0rwG3PlL2GtahrumYfCFNHvwsQV0c19N91vcXckJrGNLuOFxbazkBJZFxxlsCziCfRJwOT4W3Tuqk0gYx2n5tKhErYXVxn6/dqPb4qIJuVuMNZp2Tx1mE=', '{bcrypt}$2a$10$fDcoNgjTd/elRr5yMdZsn.NP1/IxMjZdD2NJnQ0QBzKFJofnIt9Xe', 'admin@sina.com', '13521972990', 'dmgQ23eKiaiBzwhtFyrMJM56sgh72rERjDAkHcgaZNDhB5hoZsrhEOaEiiQSi/DCjKWQfElkJjd/OWMQSCe5rOhGm9jHKS6ylAd+S+R+ValFEol97IVOoZzsCpHFpgAx6NWK0LmeZB878ItI4c+w6H9LXmBOOnp5JXWQWoSkKZo=', '2022-04-21 10:28:07', '2022-04-21 10:28:07', 'system', 'system', 0, 0, 'L8U/BX8qT7AUH4DG+V8Y5fFXClfegVWVfpMSh3nwi7SHzVEGGC+8a7GW20zrJDFM0S7w9gWLQ7yBLQWA/cYQy6IB+H4wxFPpYSmTxCh4G8ER1G2xdQMwpd2h5bcQHfEMZ6uYMV2cjS2VbjQUWFe+GJd4ywRWbwymerLUGJF5BIs=', 'Os8CkWLcLc5aqpb3r1k9h4oZGw9xfUtejOTTFsJ+eVF1yXEeXnh+lmTHw/YaUanVuezAvilhMSi522ujPcEOVVS03xEw7cL7XwWxbVXrW46EfLxRCJLYKB7rw21gD1KzrWSq6safGLu+t7dnxvs90cI5UbYoI0sY/itk65qXr+o=', 1);
INSERT INTO `sys_user` VALUES ('8b5b41ed5e26140e8438ad3aa585bea2', 'KaMYQZFpoQviFWkarZeT3rT99b7Rdv/glPMvCj1qQqNEqCs/53OH/kWapatjKCfgT+o97mQJ41NdmTvstAEMThCMSDhS6YZav8QJyCKc2UvfnBLndbjeCUG1zS4GrFjREM2pFzn4ddAxtfYxHFfPrr2G7uTN++sfcG6+1FnfQmI=', '{bcrypt}$2a$10$Lk9ApijGvgMo/5VWcTNvjezH0K7k96xbbUTo2wSbfO5M.bof/Ws.G', 'admin1@sina.com', '13521972991', 'ZqRe+/kyv+MLYgdjidVhvaF9rGAOLloy14z8T591LaOMUBV7m2RfXmphXl3fIQCT/f+1fCMcHjnx4xp41ilu6aWKxdTgwbFZRi1xbiQKw83BREf6UV2Nw9jTgjKhIQ3ESieMGq83YiiM+PNDLWejU8wqohBZqBNg399RpMj5phY=', '2022-04-21 14:29:35', '2022-04-21 17:25:40', 'admin', 'admin', 0, 0, 'I/QE2JiZj7ms6YJpwR8tW5ZhYJvo+szInH+azlo+BJa578EmcK7VYTHnvcsNuETxdcFCUThKysC0qEg+D+f0qoU9ic+Qu0GE+zbLqVuUqPolmyb2EViA20geYqCEqwrgv3cu4K+qdgDiydUSrKCxybxGW1LpVa3p17CgVp5srXE=', 'egFDUNdNrg0sDBqi9LEq2B5Ttic1c/NH8y1IAxTnj+R5piUBtPnh/9Tho9bA0JlUo/33YciE744APhrlPFTc07MU/ZMjIRQzxoqVaLeJ+ZLsZHGqP9hK6xGYHfWEnZ2B4/7X03YqIWRnCzmC38vu5uCE8FZbTnZjUAKiziMwpfs=', 1);
INSERT INTO `sys_user` VALUES ('d081923c8b0c653dbc953a73538d20fc', 'cUeBcoY4pzGRs7nmUP4bD61nXsH+cNfpLwzYl4end1hHX0Wk/f0rAo1Fznv6fXc/YeuMDSeqTPAeVYl6kuj1GFbeDoTf8Nr9xMSInBtBEkjuPQyigSHzQ/KQxdR8F30hmJSMN/Fk9lD476MZK2AB/UG+zvaAJ/bZj8+eYtfBd9E=', '{bcrypt}$2a$10$cHdrjqqX5I2a0MNi2e0.W.k2ncLpi6mtZy6n63XSkmetctki64KyO', 'admin2@sina.com', '13521972992', 'hXfAT9WQqwa8QEZSbSeCWKNyPV9mZPQFxTFnZvTVua95kUKkfGxLyA+hNTXt9HRksP9rKBX97SX6DlsEymnt2gvykC2m9BcOxEHMiBx8w4jvQDtOeNNUGaCuf1PPl6AiL6Ap6vJCb49bNt3kcTAiz7lWJ91y8nD0BfUTlSZ3/6g=', '2022-04-21 16:32:15', '2022-04-21 16:32:15', 'admin', 'admin', 0, 0, 'b5QPLnfEM7qhIhDh8gcXjPw65OK3g+XDAVdl1Ijt8T8L4uJsqCQE5UGg8bpC5L6/j+zzWvz56nTwofOooNm1Y8FhAKTE1goL3NLoHkgaGGYDJoNavRzS8zox3lA8wNtZKzIehgZVNjw+wHsiyJDHske1BeoUA99GOc4Hcr+B8KM=', 'E3rnUhSnSK36JuY1Xe3EYPYp2nh+R0s4Uy35IgNKSkvsOEE5YU5VwZz7sXhTgYkZaJHHKY1SbK/p6MZpfaV2mXt0fzcjliTQ3ii1h5fAEdC/xKaP5AhXLdCjzLGheAwJi0mgiw97EjSNXWxtgUM7fjm5INgCEsCw/jy6q+e+4JI=', 1);
INSERT INTO `sys_user` VALUES ('d20a9e5242a603d0d630d240f8f41728', 'GTYAiEWLUwz85l2MGbazqyB6Rkra2k2VSlyqXDrOPKmBrnv12sQZbXKZf/tvLgKxGgr5LDpEv2l9BCL7eAHOk12Gy9T+hqSpC5n96rhn0EHz8O5AMxNGCKaTZHOCqkskqcUId4TMbC2tqnaXzKFes/NoFqFb0ttzd1c4RWW8dtM=', '{bcrypt}$2a$10$0VxZ.r7P6eBIvv8rInUYWuYOLreD7uvaTWCSIjO7dG6FrJwHWXiq.', 'admin3@sina.com', '13521972993', 'OCyN+r9HXkhiQT8yXUJzLhKQ3FIqItRozJ0FOwXpRvqqKBDJoopHV4MX6Jtwjhu4VM1KehCGf1B2A2yRsFl1pR1aG7t5gbdAhRCOzoHhscY3AsMTgpWa0kzASffhlRkI3v0k2L1eBeUxefwS96eEthlJ7e4awXiS2ZlYZclg33A=', '2022-04-21 16:36:45', '2022-04-21 17:13:29', 'admin', 'admin', 0, 0, 'TELYHAVq1w1O5JSYBjnKziCgVG1yNUjt2DYlWLs+LKWTSXgB46h9JropGkgpapmN57bedajJcO7ASGcp4cDT3rOQ0KTUYh6y2tEWgB2wRUzyWVpzNIbw+v01lbaEw6R2L6qpZ4Y69lpCzXs3hnpZa8g/ejLz6E5yt9OsTLEAKvk=', 'UJvimLPX5gdoY8Sq7GvjrOU5gvWCF32qwilCmD346tBq1MVdw8lcI7iaixSE1fSmF4ujXsVVKLTkrXSTSxEMr12trbTw0hhdGOrnv+GY3ql0901QjUC7kx0MVF1Cl+03gVi0Nmv8n5l4gpIm9/oIbWGXDcMpOYWkxuJ7X/Y8zvc=', 1);
INSERT INTO `sys_user` VALUES ('f2fb03363c4e6fc66b210fff3452c007', 'Uta8rXkrju6O5hoIT5maTwcH/vKpOA6OS11CxQYw9JTbb+dCnmeemp4glv3ysfDwk+vbxXzF9nTJH6AVtOAn3Sx3DLNWonPkICsEujsGuzcSOOJNR6lGW8wvvtu2Qv/mlZnkTrQOsix/kKGs9gxadKiHkezSsyXrq7jF+dDkPMo=', '{bcrypt}$2a$10$KkPafFuHJcbDYKWijlUCBe4YNqJM.XoYM7.byx9DALgKYnXEDaSr6', 'admin7@sina.com', '13521972997', 'jmt8BQR58DzwOEnlqMTfTDqc2Zn22uqFq+zw3ggBQkQsYn07/oo+JzgdzB+UpOtK3AO9CMkRcqQA62i5+UPljVjs1ddkwhyO8kVthhdTDqYSfpX7/ZUUX8LwBrNU075CdaGm9cKZYRnmjPr5WkqV1Q6zWWnLOomhzPsBl7lqB4c=', '2022-04-21 10:22:32', '2022-04-21 10:22:32', 'system', 'system', 0, 0, 'Md4u+CB+oVV+6S+OZQ2cvLMl7GFKeHb48xyVRfbA/6LLJB6Q+4NYHLTx1KQCzTsXZjzPD0X/iCfTCKQsFFGU9KSB4DqpPbJI2wGCFoHCPZ8Byy92fNBukYgDGVkPXtMRnX4LNqRJ26UcnJN3kEkRyQfqC4emo9MFXOWhe/Uj6Q8=', 'RwFxlhaj+GLsmlXNX6p1GuLQrGPdQ8FzGY3/YqlcjaCNAds3xNINz7noMSar1QXP/PHrQDVBSlD+LTnnANZeBDu/zB5jITr43Ydp3q7tchMkHH8WGnPtPOnvfObJB8clABaNCEjJSlcGIigyU6j0KXqcGFg0EnScO1fmPD8KUxc=', 1);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `id` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `role_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '1', '1');
INSERT INTO `sys_user_role` VALUES ('1511529518075604994', '1511529436223762433', '1');
INSERT INTO `sys_user_role` VALUES ('1511529524627107841', '1511529436223762433', '1506558565100863489');
INSERT INTO `sys_user_role` VALUES ('1513475199771717634', '9012f39e28fc1029375b50a1df4d7728', '1510072193993146370');
INSERT INTO `sys_user_role` VALUES ('1513699479184830465', 'ab9a48d4f49d93237f7090d340d9fa07', '1');
INSERT INTO `sys_user_role` VALUES ('1516245280113160194', '387cf509e94d72048f1fe4e0461ae992', '1');
INSERT INTO `sys_user_role` VALUES ('1516245280243183618', '387cf509e94d72048f1fe4e0461ae992', '526294947737899008');

SET FOREIGN_KEY_CHECKS = 1;
