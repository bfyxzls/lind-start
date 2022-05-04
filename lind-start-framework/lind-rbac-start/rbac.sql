/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 80018
 Source Host           : 127.0.0.1:3306
 Source Schema         : rbac

 Target Server Type    : MySQL
 Target Server Version : 80018
 File Encoding         : 65001

 Date: 01/05/2022 22:41:27
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
  `front_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '前台的code码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 105 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '下拉框字典表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dictionary
-- ----------------------------
INSERT INTO `dictionary` VALUES (1, NULL, '语言类型', 'languageType', NULL, NULL);
INSERT INTO `dictionary` VALUES (2, 'zh-CN', '中文', 'languageType', 1, 'zh-CN');
INSERT INTO `dictionary` VALUES (3, 'en-US', '英文', 'languageType', 1, 'en-US');
INSERT INTO `dictionary` VALUES (4, 'ru-RU', '俄文', 'languageType', 1, 'ru-RU');
INSERT INTO `dictionary` VALUES (5, NULL, '稿件类型', 'manuscriptType', NULL, NULL);
INSERT INTO `dictionary` VALUES (6, '0', '中文原稿', 'manuscriptType', 5, '0');
INSERT INTO `dictionary` VALUES (7, '1', '中文译稿', 'manuscriptType', 5, '1');
INSERT INTO `dictionary` VALUES (8, '2', '俄文原稿', 'manuscriptType', 5, '2');
INSERT INTO `dictionary` VALUES (9, '3', '俄文译稿', 'manuscriptType', 5, '3');
INSERT INTO `dictionary` VALUES (10, '4', '英文原稿', 'manuscriptType', 5, '4');
INSERT INTO `dictionary` VALUES (11, '5', '英文译稿', 'manuscriptType', 5, '5');
INSERT INTO `dictionary` VALUES (12, NULL, '稿件状态', 'manuscriptStatus', NULL, NULL);
INSERT INTO `dictionary` VALUES (13, '1', '未上架', 'manuscriptStatus', 12, '2');
INSERT INTO `dictionary` VALUES (14, '0', '下架', 'manuscriptStatus', 12, '0');
INSERT INTO `dictionary` VALUES (15, '2', '已上架', 'manuscriptStatus', 12, '1');
INSERT INTO `dictionary` VALUES (16, NULL, '国别', 'differentCountries', NULL, NULL);
INSERT INTO `dictionary` VALUES (17, '0', '印度', 'differentCountries', 16, '0');
INSERT INTO `dictionary` VALUES (18, '1', '哈萨克斯坦', 'differentCountries', 16, '1');
INSERT INTO `dictionary` VALUES (19, '2', '中国', 'differentCountries', 16, '2');
INSERT INTO `dictionary` VALUES (20, '3', '吉尔吉斯斯坦', 'differentCountries', 16, '3');
INSERT INTO `dictionary` VALUES (21, '4', '巴基斯坦', 'differentCountries', 16, '4');
INSERT INTO `dictionary` VALUES (22, '5', '俄罗斯', 'differentCountries', 16, '5');
INSERT INTO `dictionary` VALUES (23, '6', '塔吉克斯坦', 'differentCountries', 16, '6');
INSERT INTO `dictionary` VALUES (24, '7', '乌兹别克斯坦', 'differentCountries', 16, '7');
INSERT INTO `dictionary` VALUES (25, NULL, '职能机构', 'functionalOrgan', NULL, NULL);
INSERT INTO `dictionary` VALUES (26, '0', '委员会', 'functionalOrgan', 25, '0');
INSERT INTO `dictionary` VALUES (27, '1', '秘书处', 'functionalOrgan', 25, '1');
INSERT INTO `dictionary` VALUES (28, '2', '西安中心', 'functionalOrgan', 25, '2');
INSERT INTO `dictionary` VALUES (29, '3', '交流合作基地', 'functionalOrgan', 25, '3');
INSERT INTO `dictionary` VALUES (30, NULL, '操作类型', 'operateType', NULL, NULL);
INSERT INTO `dictionary` VALUES (31, '1', '登录', 'operateType', 30, '1');
INSERT INTO `dictionary` VALUES (32, '2', '登出', 'operateType', 30, '2');
INSERT INTO `dictionary` VALUES (33, '3', '添加', 'operateType', 30, '3');
INSERT INTO `dictionary` VALUES (34, '4', '编辑', 'operateType', 30, '4');
INSERT INTO `dictionary` VALUES (35, '5', '删除', 'operateType', 30, '5');
INSERT INTO `dictionary` VALUES (36, '6', '上架', 'operateType', 30, '6');
INSERT INTO `dictionary` VALUES (37, '7', '下架', 'operateType', 30, '7');
INSERT INTO `dictionary` VALUES (38, '10', '预览', 'operateType', 30, '10');
INSERT INTO `dictionary` VALUES (39, '9', '图片预览', 'operateType', 30, '9');
INSERT INTO `dictionary` VALUES (40, '10', '添加原稿', 'operateType', 30, '10');
INSERT INTO `dictionary` VALUES (41, '11', '添加译稿', 'operateType', 30, '11');
INSERT INTO `dictionary` VALUES (42, '12', '刷新', 'operateType', 30, '12');
INSERT INTO `dictionary` VALUES (43, '13', '导出', 'operateType', 30, '13');
INSERT INTO `dictionary` VALUES (44, '14', '目录导出', 'operateType', 30, '14');
INSERT INTO `dictionary` VALUES (45, '15', '分配权限', 'operateType', 30, '15');
INSERT INTO `dictionary` VALUES (46, '16', '前台隐藏', 'operateType', 30, '16');
INSERT INTO `dictionary` VALUES (47, '17', '批量下架', 'operateType', 30, '17');
INSERT INTO `dictionary` VALUES (48, NULL, '栏目层级', 'columnLevel', NULL, NULL);
INSERT INTO `dictionary` VALUES (49, '0', '一级', 'columnLevel', 48, '0');
INSERT INTO `dictionary` VALUES (50, '1', '二级', 'columnLevel', 48, '1');
INSERT INTO `dictionary` VALUES (52, NULL, '模块类型', 'moduleType', NULL, NULL);
INSERT INTO `dictionary` VALUES (54, '1', '用户登录', 'moduleType', 52, '1');
INSERT INTO `dictionary` VALUES (55, '2', '用户登出', 'moduleType', 52, '2');
INSERT INTO `dictionary` VALUES (56, '7', '导航栏', 'moduleType', 52, 'homepage');
INSERT INTO `dictionary` VALUES (57, '8', '友情链接', 'moduleType', 52, '8');
INSERT INTO `dictionary` VALUES (58, '9', '国家介绍', 'moduleType', 52, '9');
INSERT INTO `dictionary` VALUES (59, '10', '职能机构', 'moduleType', 52, 'functionalorganization');
INSERT INTO `dictionary` VALUES (61, '11', '中国法规', 'moduleType', 52, 'lawrule');
INSERT INTO `dictionary` VALUES (62, '12', '外国法规', 'moduleType', 52, 'lawrule');
INSERT INTO `dictionary` VALUES (63, '13', '国际条约', 'moduleType', 52, 'internationaltreaty');
INSERT INTO `dictionary` VALUES (64, '14', '经典案例', 'moduleType', 52, 'classiccase');
INSERT INTO `dictionary` VALUES (65, '15', '法律服务机构', 'moduleType', 52, 'lawoffices');
INSERT INTO `dictionary` VALUES (66, '16', '咨询专家', 'moduleType', 52, 'consultantexpert');
INSERT INTO `dictionary` VALUES (67, '', '权限类型', 'PermissionType', NULL, '');
INSERT INTO `dictionary` VALUES (69, '0', '菜单', 'PermissionType', 67, '0');
INSERT INTO `dictionary` VALUES (70, '1', '按钮', 'PermissionType', 67, '1');
INSERT INTO `dictionary` VALUES (71, NULL, '是否前台隐藏', 'foregroundHidden', NULL, NULL);
INSERT INTO `dictionary` VALUES (72, '0', '否', 'foregroundHidden', 71, '0');
INSERT INTO `dictionary` VALUES (73, '1', '是', 'foregroundHidden', 71, '1');
INSERT INTO `dictionary` VALUES (74, NULL, '前台索引库', 'libraryType', NULL, NULL);
INSERT INTO `dictionary` VALUES (75, 'ywsk_law_zh-cn', '中外法规(中)', 'libraryType', 74, 'ywsk_law_zh-cn');
INSERT INTO `dictionary` VALUES (76, 'ywsk_law_ru-ru', '中外法规(俄)', 'libraryType', 74, 'ywsk_law_ru-ru');
INSERT INTO `dictionary` VALUES (77, 'ywsk_law_en-us', '中外法规(英)', 'libraryType', 74, 'ywsk_law_en-us');
INSERT INTO `dictionary` VALUES (78, 'ywsk_pfnl_zh-cn', '经典案例(中)', 'libraryType', 74, 'ywsk_pfnl_zh-cn');
INSERT INTO `dictionary` VALUES (79, 'ywsk_pfnl_ru-ru', '经典案例(俄)', 'libraryType', 74, 'ywsk_pfnl_ru-ru');
INSERT INTO `dictionary` VALUES (80, 'ywsk_pfnl_en-us', '经典案例(英)', 'libraryType', 74, 'ywsk_pfnl_en-us');
INSERT INTO `dictionary` VALUES (81, 'ywsk_fo_zh-cn', '职能机构(俄)', 'libraryType', 74, 'ywsk_fo_zh-cn');
INSERT INTO `dictionary` VALUES (82, 'ywsk_fo_ru-ru', '职能机构(俄)', 'libraryType', 74, 'ywsk_fo_ru-ru');
INSERT INTO `dictionary` VALUES (83, 'ywsk_fo_en-us', '职能机构(英)', 'libraryType', 74, 'ywsk_fo_en-us');
INSERT INTO `dictionary` VALUES (84, 'ywsk_eagn_zh-cn', '国际条约(中)', 'libraryType', 74, 'ywsk_eagn_zh-cn');
INSERT INTO `dictionary` VALUES (85, 'ywsk_eagn_ru-ru', '国际条约(俄)', 'libraryType', 74, 'ywsk_eagn_ru-ru');
INSERT INTO `dictionary` VALUES (86, 'ywsk_eagn_en-us', '国际条约(英)', 'libraryType', 74, 'ywsk_eagn_en-us');
INSERT INTO `dictionary` VALUES (87, 'ywsk_home_zh-cn', '首页(中)', 'libraryType', 74, 'ywsk_home_zh-cn');
INSERT INTO `dictionary` VALUES (88, 'ywsk_home_ru-ru', '首页(俄)', 'libraryType', 74, 'ywsk_home_ru-ru');
INSERT INTO `dictionary` VALUES (89, 'ywsk_home_en-us', '首页(英)', 'libraryType', 74, 'ywsk_home_en-us');
INSERT INTO `dictionary` VALUES (90, NULL, ' 性别', 'gender', NULL, NULL);
INSERT INTO `dictionary` VALUES (91, '0', '男', 'gender', 90, '0');
INSERT INTO `dictionary` VALUES (92, '1', '女', 'gender', 90, '1');
INSERT INTO `dictionary` VALUES (93, NULL, '上一层级', 'upperLevelId', NULL, NULL);
INSERT INTO `dictionary` VALUES (94, '01', '首页', 'upperLevelId', 93, '01');
INSERT INTO `dictionary` VALUES (95, '02', '职能机构', 'upperLevelId', 93, '02');
INSERT INTO `dictionary` VALUES (96, '03', '法律法规', 'upperLevelId', 93, '03');
INSERT INTO `dictionary` VALUES (97, '04', '案例数据', 'upperLevelId', 93, '04');
INSERT INTO `dictionary` VALUES (98, '05', '法律服务机构', 'upperLevelId', 93, '05');
INSERT INTO `dictionary` VALUES (99, '06', '专家数据', 'upperLevelId', 93, '06');
INSERT INTO `dictionary` VALUES (100, '07', '中外法规', 'upperLevelId', 93, '07');
INSERT INTO `dictionary` VALUES (101, '08', '国际条约', 'upperLevelId', 93, '08');
INSERT INTO `dictionary` VALUES (102, '3', ' 用户管理', 'moduleType', 52, '3');
INSERT INTO `dictionary` VALUES (103, '4', '角色管理', 'moduleType', 52, '4');
INSERT INTO `dictionary` VALUES (104, '5', '菜单管理', 'moduleType', 52, '5');
INSERT INTO `dictionary` VALUES (105, '6', '聚类管理', 'moduleType', 52, '6');

-- ----------------------------
-- Table structure for operate_log
-- ----------------------------
DROP TABLE IF EXISTS `operate_log`;
CREATE TABLE `operate_log`  (
  `id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `data_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `module_type` int(11) DEFAULT NULL,
  `content` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `operate_type` int(11) DEFAULT NULL,
  `operator` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `operator_ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `operate_time` datetime(0) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission`  (
  `id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `api_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '后台地址',
  `path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'url路径',
  `file_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'vue文件路径',
  `http_method` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'http请求方式',
  `sort_number` double(11, 0) NOT NULL DEFAULT 100,
  `type` int(11) DEFAULT NULL,
  `parent_id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `create_time` datetime(0) DEFAULT NULL,
  `update_time` datetime(0) DEFAULT NULL,
  `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `del_flag` int(11) DEFAULT NULL,
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '图标',
  `operate_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '操作类型，对应字典里的OperateType',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES ('1', '北大英华', NULL, '', '', 'POST', 100, 0, '', '2022-04-15 13:05:36', '2022-04-15 13:05:41', NULL, NULL, 0, 'aggregation', NULL);
INSERT INTO `sys_permission` VALUES ('19', '角色管理', '/role/query**', '/xtsz/roleList', 'roleList', NULL, 100, 0, '6', '2022-04-16 23:18:46', '2022-04-16 23:18:46', 'admin', 'admin', 0, 'aggregation', NULL);
INSERT INTO `sys_permission` VALUES ('20', '用户管理', '/user/list**', '/sys/userList', 'userList', NULL, 100, 0, '6', '2022-04-16 23:18:46', '2022-04-16 23:18:46', 'admin', 'admin', 0, 'aggregation', NULL);
INSERT INTO `sys_permission` VALUES ('21', '菜单管理', '/permission/list**', '/sys/menu', 'menu', 'GET', 100, 0, '6', '2022-04-16 23:18:46', '2022-04-16 23:18:46', 'admin', 'admin', 0, 'aggregation', NULL);
INSERT INTO `sys_permission` VALUES ('22', '操作日志', '/operateLog/**', '/operationList', '/xtsz/operation/operationList', 'GET', 100, 0, '6', '2022-04-16 23:18:46', '2022-04-16 23:18:46', 'admin', 'admin', 0, 'aggregation', NULL);
INSERT INTO `sys_permission` VALUES ('23', '添加用户', '/user/add**', '', '', 'POST', 2, 1, '20', '2022-04-16 23:18:46', '2022-04-16 23:18:46', 'admin', 'admin', 0, 'aggregation', '3');
INSERT INTO `sys_permission` VALUES ('24', '编辑用户', '/user/update/**', '', '', 'PUT', 1, 1, '20', '2022-04-16 23:18:46', '2022-04-16 23:18:46', 'admin', 'admin', 0, 'aggregation', '4');
INSERT INTO `sys_permission` VALUES ('25', '删除用户', '/user/del/**', '', '', 'DELETE', 4, 1, '20', '2022-04-16 23:18:46', '2022-04-16 23:18:46', 'admin', 'admin', 0, 'aggregation', '4');
INSERT INTO `sys_permission` VALUES ('26', '批量删除用户', '/user/bulk-del**', '', '', 'POST', 3, 1, '20', '2022-04-16 23:18:46', '2022-04-16 23:18:46', 'admin', 'admin', 0, 'aggregation', '4');
INSERT INTO `sys_permission` VALUES ('27', '重置密码', '/user/bulk-reset-password**', '', '', 'PUT', 100, 1, '20', '2022-04-16 23:18:46', '2022-04-16 23:18:46', 'admin', 'admin', 0, 'aggregation', '4');
INSERT INTO `sys_permission` VALUES ('28', '编辑角色', '/role/update/**', '', '', 'PUT', 100, 1, '19', '2022-04-16 23:18:46', '2022-04-16 23:18:46', 'admin', 'admin', 0, 'aggregation', '4');
INSERT INTO `sys_permission` VALUES ('29', '删除角色', '/api/role/del/**', '', '', 'DELETE', 100, 1, '19', '2022-04-16 23:18:46', '2022-04-16 23:18:46', 'admin', 'admin', 0, 'aggregation', '4');
INSERT INTO `sys_permission` VALUES ('30', '批量删除角色', '/role/bulk-del**', '', '', 'POST', 100, 1, '19', '2022-04-16 23:18:46', '2022-04-16 23:18:46', 'admin', 'admin', 0, 'aggregation', '4');
INSERT INTO `sys_permission` VALUES ('31', '添加角色', '/role/add**', '', '', 'POST', 100, 1, '19', '2022-04-16 23:18:46', '2022-04-16 23:18:46', 'admin', 'admin', 0, 'aggregation', '3');
INSERT INTO `sys_permission` VALUES ('37', '删除菜单', '/permission/del/**', '/sys/menu', '/xtsz/sys/menu', 'DELETE', 100, 0, '6', '2022-04-16 23:18:46', '2022-04-16 23:18:46', 'admin', 'admin', 0, 'aggregation', NULL);
INSERT INTO `sys_permission` VALUES ('5', '数据管理', NULL, '', NULL, 'GET', 1, 0, '1', '2022-04-15 13:05:36', '2022-04-15 13:05:41', NULL, NULL, 0, 'aggregation', NULL);
INSERT INTO `sys_permission` VALUES ('6', '系统管理', NULL, '', NULL, 'GET', 2, 0, '1', '2022-04-15 13:05:36', '2022-04-15 13:05:41', NULL, NULL, 0, 'aggregation', NULL);

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
INSERT INTO `sys_role` VALUES ('526293604298133504', 'name2', '2022-04-12 21:15:14', '2022-04-12 21:15:14', 'admin', 'admin', 1);
INSERT INTO `sys_role` VALUES ('526293825358925824', 'name1', '2022-04-12 21:16:07', '2022-04-12 21:16:07', 'admin', 'admin', 0);
INSERT INTO `sys_role` VALUES ('526294138765709312', '1', '2022-04-12 21:17:22', '2022-04-12 21:17:22', 'admin', 'admin', 0);
INSERT INTO `sys_role` VALUES ('526294338074841088', '2', '2022-04-12 21:18:09', '2022-04-12 21:18:09', 'admin', 'admin', 0);
INSERT INTO `sys_role` VALUES ('526294668867014656', '3', '2022-04-12 21:19:28', '2022-04-12 21:19:28', 'admin', 'admin', 0);
INSERT INTO `sys_role` VALUES ('526294947737899008', '4', '2022-04-12 21:20:35', '2022-04-12 21:20:35', 'admin', 'admin', 0);
INSERT INTO `sys_role` VALUES ('5d8dba6462efaeaf9d0437e86563c588', '测试用户', '2022-04-25 13:33:16', '2022-04-25 13:33:16', 'admin', 'admin', 0);
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
INSERT INTO `sys_role_permission` VALUES ('1517434128490868737', '1', 'd2276ee35791cf5454078ed2f74b5ed1', b'0');
INSERT INTO `sys_role_permission` VALUES ('1517434868043132930', '1', '73f6391e9197fb9e45429513e7e4268f', b'0');
INSERT INTO `sys_role_permission` VALUES ('1517663801959297025', '1', 'bded1434f63d43b6e71319e7ef7e8dfc', b'0');
INSERT INTO `sys_role_permission` VALUES ('1517666773258776577', '1', '066a10b8bb511ce2742f6f22b2dba959', b'0');
INSERT INTO `sys_role_permission` VALUES ('1517667281960742914', '1', '87fadeba27fa7cc4f8aeadfea45edceb', b'0');
INSERT INTO `sys_role_permission` VALUES ('16', '1', '29', b'0');
INSERT INTO `sys_role_permission` VALUES ('17', '1', '30', b'0');
INSERT INTO `sys_role_permission` VALUES ('18', '1', '31', b'0');
INSERT INTO `sys_role_permission` VALUES ('19', '1', '32', b'0');
INSERT INTO `sys_role_permission` VALUES ('20', '1', '33', b'0');
INSERT INTO `sys_role_permission` VALUES ('21', '1', '34', b'0');
INSERT INTO `sys_role_permission` VALUES ('22', '1', '35', b'0');
INSERT INTO `sys_role_permission` VALUES ('23', '1', '36', b'0');
INSERT INTO `sys_role_permission` VALUES ('24', '1', '21', b'0');
INSERT INTO `sys_role_permission` VALUES ('25', '1', '37', b'0');
INSERT INTO `sys_role_permission` VALUES ('26', '1', '27', b'0');
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
INSERT INTO `sys_user` VALUES ('1', 'gWIWGBgBiUVHn04uRigyoiFeroQog+M+xdRsTjnhAlsXIPUMmi6iVNmobZL0GqGgv8RS+y3W0ZRl3QMPJrUiqpl/eev1diJ97swcViOQBILGh6ioY+8RgyEXPF9TRXdi4BL2RM1Mg7EgW/Y6Nh1p3WvuV+cCLDAZ3wBq4vnZwF4=', '{bcrypt}$2a$10$vValqlVTQrdUS/UITOUcmuQbHdCZHpJBL9cgKkC6zpbo0YNN6nnQ.', 'admin@sina.com', '13521972990', 'Q9UaX//jLbPrwWV2SPZTu6EtPxQV3fytolaBrn3/vK3LO02xpXGaDSO1VBSpe4Lh4MkM5Sfpfca8ZaT1o9iwkazwAYAJ6CTdxiqQp9X65D6S49/86Bz5Y83GWJqc0nphT9oEGkwzRsLrEK0gjsJDorty5bfTJydQNr1SxP93kq4=', '2022-04-21 10:28:07', '2022-04-25 17:08:07', 'admin', 'system', 0, 0, 'LfrM9wHWO54smwxGvogJskq3DfY1qAQBeHUH68981iH34yCxYyRayr1rSaNYT2pBxouzUCADaLbCbAxxY4QIqNE1wYKvkUx9tN+Iya2T0Idj7LleksJi8OZeXwLpqS4prbCBhVqGHdV/Nc2qpewTN58bpgz0ituGsVORNfduoLk=', 'ShGiqh8uwLVhO2bq59F5GH9b6WC5G0H1OjgI8lhlN35nKnICwRdki526dSQSoz0Cb50CsGcuS16IVslqhvt7w2gygyYeuZs1TIoggvz2OJxC8NZkV/ywQnYyP2+2Cgvl8glL8vYopG+goePLcblUX32M1/GXz5L1AxUKF5HQpfk=', 1);
INSERT INTO `sys_user` VALUES ('41733d099f99629639ab5d4dc1938fca', 'HVBGils52jy/fw/bFXbTPxArBPpC2vX1W+JtmExlAkmRx14IM1ssXHFNIkizVh/QLlti7xUXVSQI5fRyOZW/spD243Jm/eY9B1EW0mDJhGzr31/wRf0ol9Of0PE/GSpg3jvuoNItvmaDkgm2ZMyIUKaLaHgbsBNy11GAJmV0a5E=', '{bcrypt}$2a$10$XmHaDOvwPBoSm6Rc3j5wUuXcIaB7rPE23d7i4QTl6NFeEo2IqQCju', 'admin4@sina.com', '13521972994', 'OJbxxFuH/XK0fJo/cHNF5F88j+0F9rkNc3+wy5HTqOembwi/pYbnA0HxP39+K5rOHg28FlDB+uVWn9TYRJMpLOtRZhkS57CK7v3dQe1ukUfGPtoAeteqzuxOFz2qf86MdnCNVc5bpAph3rjNJ71qqoj204aCav0g9dkX/IEn8rs=', '2022-04-22 14:50:06', '2022-04-22 14:50:06', 'admin', 'admin', 1, 0, 'aP4LcVLnWNJ4i9bCg8ydo2S1PreDUn1CH7sUAQ71o41xlmQ68fYKCjbSej6I44IFZvGPjbctB/cl3+DadeoxG7cspLAF3Wc3dqREM9Z0m92nDlJqKyW76uBcZyVyDWdeWz9VhcoUrMWEx87V7d1xNidMlcGYbm/bBXYrZZFpb3I=', 'kIK8BgjowgqYAV97y1cCVOvAmaxmz/JWIsBKdLdz9+KJtA6+I63OOyg6EAMEo/VuopHmC96gVKIL8f7mlbOXfSCqadOSG0YE526zGYauE8X2DEvt9vc2BZfPC6nOVL9k35Bmzv+LQ0+8G2HxhwE2Upl4EENmmz+Tk5DKHIupE8s=', 1);
INSERT INTO `sys_user` VALUES ('8b5b41ed5e26140e8438ad3aa585bea2', 'A8G5lbOMikpY7Fh6xuU0qd6MLfYMPS2ituZ14Z6cvG7HEtSTmvbnp1gh50XBglUGpgc8G4x3mwU4/ScM4am0x8auiRU5FsJzMNphdKY2/4iN3Iif5/JeuvnrWoFVE5pGURxVN6/aTl95wUA4EQCOkS4b9gVeR1QiVm9KEB+GqX4=', '{bcrypt}$2a$10$Lk9ApijGvgMo/5VWcTNvjezH0K7k96xbbUTo2wSbfO5M.bof/Ws.G', 'admin1@sina.com', '13521972991', 'QTz5iewxC8z+OQfNAcMcZfNywpWTJJG9EDvc17apPTJFpV6H467HKI2BaAimtUHhng1r6jDhAkvo2KO7uhfyGsedFeZ5TXMRkqgRlYZfWihzpkuxnC/Shy2soJ2doMLOKoW3AyZWjxugqKVD4Y6PIb9QfPPggXpzT8MrFd4/ROE=', '2022-04-21 14:29:35', '2022-04-22 15:32:37', 'admin', 'admin', 1, 0, 'NyWW29PRdOLls+Z2D0HNVxq0b3dEXA/V84JG8EC+kfZ0pX9VmCX/C36qQJXsahyoR2IrsRHKvWW5Pj7+L8jwNjApL61g6LslWtOQQacyoHIvF2MylU8cAV+rJGqOzuJnQOACYIt+AMXoRcg4rwo/J54p1tMAMbzquI9NgL1JA4w=', 'WMfNIs7b6bYfkBK9EunIOp2XbPpnVyLB/ysn7vns1oieKKBx9IHkDODQUETozfWK1XxvRfXfTEQvkExk0fJor8Ag8l7vb6b6lX/2sIEhHhfIEPADX0jDJ2k00qK6FdevBySrSEzdsUCBJjEJeW9buTOh9R9Ugqc9RJuuCFOgty8=', 1);
INSERT INTO `sys_user` VALUES ('b60bda3919c8d0acc26e16af856f9c71', 'HYUdsPnbF1bzcrrzU875d77Rosl/8JYyEQ9P8fZHDzMmMicjh5db8QNXCBLuBRUggf21tegBpjg+0/YNVXeCtThYo8/k+RFUzOKa1crISkO2dnMtxy2sTVEQF9Nen2ot6Zs8H7tr6fgngNbtSJZQdFXfhzUdUQUvyy08j5swrSQ=', '{bcrypt}$2a$10$IgmpOKJlVQz9RNb31x8z3Oe9X6TfWfUwivRZRouaqYNjjm6jKzsFK', 'admin5@sina.com', '13521972995', 'kTqDl/N+qYZsv2FEW7SgtBglyiBLuCATECFdrL8U+vgmMZTLx4jxorW/hFnqj5fqictz8YnEYKmaj3aloDyiJs+Fi7QtlRA11SA0c9KdL01PiUWrj26GZvJNcs7nhIxRfnmBzvP/ciEBcoqtnswaWE9qMCBumYoLs1fW1tAqECM=', '2022-04-22 15:32:11', '2022-04-22 15:32:11', 'admin', 'admin', 1, 0, 'UGXjNWkd/1ee8GGngV6bb+bJBpyfnrAoDaY0F3AimLFqU8jPQng/Dn5JQeDaVwCMgpKb2AfShRFXyhy4niyXSN69XRUxe54MnXG3rvBVzuIovYyuRUwxi//UkmdBZVZz3WCF1ZqkpqATiwAwBm+wLN08UgmyctpkVnIfyykZZOk=', 'ifwz/rler4aunb+uGYhr4IEr4fOHYHhvWVhJe8Ndc3e5hDSfzSQ0q2+xX6lxpMfITZCiOPc6Ca9yG5C+06HBjkGLI+n9HAjDsQHHL+JuyWR9l1np+/WKHB0pyFE7lHQhrwUWVzMCTeAb6OV1I2YPUvOMAG2ICk4N7mlBWIQtmw8=', 1);
INSERT INTO `sys_user` VALUES ('d081923c8b0c653dbc953a73538d20fc', 'cUeBcoY4pzGRs7nmUP4bD61nXsH+cNfpLwzYl4end1hHX0Wk/f0rAo1Fznv6fXc/YeuMDSeqTPAeVYl6kuj1GFbeDoTf8Nr9xMSInBtBEkjuPQyigSHzQ/KQxdR8F30hmJSMN/Fk9lD476MZK2AB/UG+zvaAJ/bZj8+eYtfBd9E=', '{bcrypt}$2a$10$cHdrjqqX5I2a0MNi2e0.W.k2ncLpi6mtZy6n63XSkmetctki64KyO', 'admin2@sina.com', '13521972992', 'hXfAT9WQqwa8QEZSbSeCWKNyPV9mZPQFxTFnZvTVua95kUKkfGxLyA+hNTXt9HRksP9rKBX97SX6DlsEymnt2gvykC2m9BcOxEHMiBx8w4jvQDtOeNNUGaCuf1PPl6AiL6Ap6vJCb49bNt3kcTAiz7lWJ91y8nD0BfUTlSZ3/6g=', '2022-04-21 16:32:15', '2022-04-21 16:32:15', 'admin', 'admin', 1, 0, 'b5QPLnfEM7qhIhDh8gcXjPw65OK3g+XDAVdl1Ijt8T8L4uJsqCQE5UGg8bpC5L6/j+zzWvz56nTwofOooNm1Y8FhAKTE1goL3NLoHkgaGGYDJoNavRzS8zox3lA8wNtZKzIehgZVNjw+wHsiyJDHske1BeoUA99GOc4Hcr+B8KM=', 'E3rnUhSnSK36JuY1Xe3EYPYp2nh+R0s4Uy35IgNKSkvsOEE5YU5VwZz7sXhTgYkZaJHHKY1SbK/p6MZpfaV2mXt0fzcjliTQ3ii1h5fAEdC/xKaP5AhXLdCjzLGheAwJi0mgiw97EjSNXWxtgUM7fjm5INgCEsCw/jy6q+e+4JI=', 1);
INSERT INTO `sys_user` VALUES ('d20a9e5242a603d0d630d240f8f41728', 'GTYAiEWLUwz85l2MGbazqyB6Rkra2k2VSlyqXDrOPKmBrnv12sQZbXKZf/tvLgKxGgr5LDpEv2l9BCL7eAHOk12Gy9T+hqSpC5n96rhn0EHz8O5AMxNGCKaTZHOCqkskqcUId4TMbC2tqnaXzKFes/NoFqFb0ttzd1c4RWW8dtM=', '{bcrypt}$2a$10$0VxZ.r7P6eBIvv8rInUYWuYOLreD7uvaTWCSIjO7dG6FrJwHWXiq.', 'admin3@sina.com', '13521972993', 'OCyN+r9HXkhiQT8yXUJzLhKQ3FIqItRozJ0FOwXpRvqqKBDJoopHV4MX6Jtwjhu4VM1KehCGf1B2A2yRsFl1pR1aG7t5gbdAhRCOzoHhscY3AsMTgpWa0kzASffhlRkI3v0k2L1eBeUxefwS96eEthlJ7e4awXiS2ZlYZclg33A=', '2022-04-21 16:36:45', '2022-04-21 17:13:29', 'admin', 'admin', 1, 0, 'TELYHAVq1w1O5JSYBjnKziCgVG1yNUjt2DYlWLs+LKWTSXgB46h9JropGkgpapmN57bedajJcO7ASGcp4cDT3rOQ0KTUYh6y2tEWgB2wRUzyWVpzNIbw+v01lbaEw6R2L6qpZ4Y69lpCzXs3hnpZa8g/ejLz6E5yt9OsTLEAKvk=', 'UJvimLPX5gdoY8Sq7GvjrOU5gvWCF32qwilCmD346tBq1MVdw8lcI7iaixSE1fSmF4ujXsVVKLTkrXSTSxEMr12trbTw0hhdGOrnv+GY3ql0901QjUC7kx0MVF1Cl+03gVi0Nmv8n5l4gpIm9/oIbWGXDcMpOYWkxuJ7X/Y8zvc=', 1);
INSERT INTO `sys_user` VALUES ('f2fb03363c4e6fc66b210fff3452c007', 'Uta8rXkrju6O5hoIT5maTwcH/vKpOA6OS11CxQYw9JTbb+dCnmeemp4glv3ysfDwk+vbxXzF9nTJH6AVtOAn3Sx3DLNWonPkICsEujsGuzcSOOJNR6lGW8wvvtu2Qv/mlZnkTrQOsix/kKGs9gxadKiHkezSsyXrq7jF+dDkPMo=', '{bcrypt}$2a$10$KkPafFuHJcbDYKWijlUCBe4YNqJM.XoYM7.byx9DALgKYnXEDaSr6', 'admin7@sina.com', '13521972997', 'jmt8BQR58DzwOEnlqMTfTDqc2Zn22uqFq+zw3ggBQkQsYn07/oo+JzgdzB+UpOtK3AO9CMkRcqQA62i5+UPljVjs1ddkwhyO8kVthhdTDqYSfpX7/ZUUX8LwBrNU075CdaGm9cKZYRnmjPr5WkqV1Q6zWWnLOomhzPsBl7lqB4c=', '2022-04-21 10:22:32', '2022-04-21 10:22:32', 'system', 'system', 1, 0, 'Md4u+CB+oVV+6S+OZQ2cvLMl7GFKeHb48xyVRfbA/6LLJB6Q+4NYHLTx1KQCzTsXZjzPD0X/iCfTCKQsFFGU9KSB4DqpPbJI2wGCFoHCPZ8Byy92fNBukYgDGVkPXtMRnX4LNqRJ26UcnJN3kEkRyQfqC4emo9MFXOWhe/Uj6Q8=', 'RwFxlhaj+GLsmlXNX6p1GuLQrGPdQ8FzGY3/YqlcjaCNAds3xNINz7noMSar1QXP/PHrQDVBSlD+LTnnANZeBDu/zB5jITr43Ydp3q7tchMkHH8WGnPtPOnvfObJB8clABaNCEjJSlcGIigyU6j0KXqcGFg0EnScO1fmPD8KUxc=', 1);

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
INSERT INTO `sys_user_role` VALUES ('1518469549605789697', '0b5b9fa41769e2dc517bd1bb51cc3afe', '1');

SET FOREIGN_KEY_CHECKS = 1;
