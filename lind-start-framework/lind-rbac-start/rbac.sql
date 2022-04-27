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

 Date: 27/04/2022 19:56:37
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
) ENGINE = InnoDB AUTO_INCREMENT = 106 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '下拉框字典表' ROW_FORMAT = Dynamic;

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
-- Records of operate_log
-- ----------------------------
INSERT INTO `operate_log` VALUES ('1517372705458917378', NULL, NULL, 1, '用户“admin”在“2022-04-22 13:20:19”登录了', 1, 'admin', '192.168.60.250', '2022-04-22 13:20:19');
INSERT INTO `operate_log` VALUES ('1517380554641674241', NULL, NULL, 1, '用户“admin”在“2022-04-22 13:51:30”登录了', 1, 'admin', '192.168.60.250', '2022-04-22 13:51:30');
INSERT INTO `operate_log` VALUES ('1517394976519585793', '8b5b41ed5e26140e8438ad3aa585bea2', '', 3, '用户“admin”在“2022-04-22 14:48:48”，“编辑”了“用户管理”模块的ID“8b5b41ed5e26140e8438ad3aa585bea2”，“”', 4, 'admin', '127.0.0.1', '2022-04-22 14:48:49');
INSERT INTO `operate_log` VALUES ('1517395299426467841', '', 'admin4', 3, '用户“admin”在“2022-04-22 14:50:05”，“添加”了“用户管理”模块的ID“”，“admin4”', 3, 'admin', '127.0.0.1', '2022-04-22 14:50:06');
INSERT INTO `operate_log` VALUES ('1517405795932200961', '', 'admin4', 3, '用户“admin”在“2022-04-22 15:31:48”，“添加”了“用户管理”模块的ID“”，“admin4”', 3, 'admin', '127.0.0.1', '2022-04-22 15:31:48');
INSERT INTO `operate_log` VALUES ('1517405892707377154', 'b60bda3919c8d0acc26e16af856f9c71', 'admin5', 3, '用户“admin”在“2022-04-22 15:32:11”，“添加”了“用户管理”模块的ID“b60bda3919c8d0acc26e16af856f9c71”，“admin5”', 3, 'admin', '127.0.0.1', '2022-04-22 15:32:12');
INSERT INTO `operate_log` VALUES ('1517405999720849410', '8b5b41ed5e26140e8438ad3aa585bea2', 'admin1', 3, '用户“admin”在“2022-04-22 15:32:37”，“编辑”了“用户管理”模块的ID“8b5b41ed5e26140e8438ad3aa585bea2”，“admin1”', 4, 'admin', '127.0.0.1', '2022-04-22 15:32:37');
INSERT INTO `operate_log` VALUES ('1517416986307493889', NULL, NULL, 1, '用户“admin”在“2022-04-22 16:16:16”登录了', 1, 'admin', '127.0.0.1', '2022-04-22 16:16:16');
INSERT INTO `operate_log` VALUES ('1517421214233047042', NULL, NULL, 1, '用户“admin”在“2022-04-22 16:33:04”登录了', 1, 'admin', '127.0.0.1', '2022-04-22 16:33:04');
INSERT INTO `operate_log` VALUES ('1517423497796771842', NULL, NULL, 1, '用户“admin”在“2022-04-22 16:42:08”登录了', 1, 'admin', '127.0.0.1', '2022-04-22 16:42:09');
INSERT INTO `operate_log` VALUES ('1517423991357231106', NULL, NULL, 1, '用户“admin”在“2022-04-22 16:44:06”登录了', 1, 'admin', '127.0.0.1', '2022-04-22 16:44:07');
INSERT INTO `operate_log` VALUES ('1517425146934087682', NULL, NULL, 1, '用户“admin”在“2022-04-22 16:48:42”登录了', 1, 'admin', '127.0.0.1', '2022-04-22 16:48:42');
INSERT INTO `operate_log` VALUES ('1517426093383077890', NULL, NULL, 1, '用户“admin”在“2022-04-22 16:52:27”登录了', 1, 'admin', '127.0.0.1', '2022-04-22 16:52:28');
INSERT INTO `operate_log` VALUES ('1517428350329593857', NULL, NULL, 1, '用户“admin”在“2022-04-22 17:01:25”登录了', 1, 'admin', '127.0.0.1', '2022-04-22 17:01:26');
INSERT INTO `operate_log` VALUES ('1517433815352520706', NULL, NULL, 1, '用户“admin”在“2022-04-22 17:23:08”登录了', 1, 'admin', '192.168.60.250', '2022-04-22 17:23:09');
INSERT INTO `operate_log` VALUES ('1517435604755853313', NULL, NULL, 1, '用户“admin”在“2022-04-22 17:30:15”登录了', 1, 'admin', '192.168.60.250', '2022-04-22 17:30:15');
INSERT INTO `operate_log` VALUES ('1517436063612710913', NULL, NULL, 1, '用户“admin”在“2022-04-22 17:32:04”登录了', 1, 'admin', '192.168.60.250', '2022-04-22 17:32:05');
INSERT INTO `operate_log` VALUES ('1517440194893058049', NULL, NULL, 1, '用户“admin”在“2022-04-22 17:48:29”登录了', 1, 'admin', '127.0.0.1', '2022-04-22 17:48:30');
INSERT INTO `operate_log` VALUES ('1517440858335502337', NULL, NULL, 1, '用户“admin”在“2022-04-22 17:51:07”登录了', 1, 'admin', '127.0.0.1', '2022-04-22 17:51:08');
INSERT INTO `operate_log` VALUES ('1517658702641934338', NULL, NULL, 1, '用户“admin”在“2022-04-23 08:16:46”登录了', 1, 'admin', '192.168.60.250', '2022-04-23 08:16:46');
INSERT INTO `operate_log` VALUES ('1517661228015595522', NULL, NULL, 1, '用户“admin”在“2022-04-23 08:26:48”登录了', 1, 'admin', '192.168.60.250', '2022-04-23 08:26:48');
INSERT INTO `operate_log` VALUES ('1517663675513614338', NULL, NULL, 1, '用户“admin”在“2022-04-23 08:36:31”登录了', 1, 'admin', '192.168.60.250', '2022-04-23 08:36:32');
INSERT INTO `operate_log` VALUES ('1517703017749590018', NULL, NULL, 1, '用户“admin”在“2022-04-23 11:12:51”登录了', 1, 'admin', '192.168.60.250', '2022-04-23 11:12:52');
INSERT INTO `operate_log` VALUES ('1517780088966393857', NULL, NULL, 1, '用户“admin”在“2022-04-23 16:19:06”登录了', 1, 'admin', '192.168.60.250', '2022-04-23 16:19:07');
INSERT INTO `operate_log` VALUES ('1517807178591846402', NULL, NULL, 1, '用户“admin”在“2022-04-23 18:06:45”登录了', 1, 'admin', '127.0.0.1', '2022-04-23 18:06:46');
INSERT INTO `operate_log` VALUES ('1517810230849744898', NULL, NULL, 1, '用户“admin”在“2022-04-23 18:18:53”登录了', 1, 'admin', '192.168.60.250', '2022-04-23 18:18:53');
INSERT INTO `operate_log` VALUES ('1517842703155507202', NULL, NULL, 1, '用户“admin”在“2022-04-23 20:27:55”登录了', 1, 'admin', '192.168.60.250', '2022-04-23 20:27:55');
INSERT INTO `operate_log` VALUES ('1517844169396105218', NULL, NULL, 1, '用户“admin”在“2022-04-23 20:33:44”登录了', 1, 'admin', '192.168.60.250', '2022-04-23 20:33:45');
INSERT INTO `operate_log` VALUES ('1517844434606141441', NULL, NULL, 1, '用户“admin”在“2022-04-23 20:34:48”登录了', 1, 'admin', '192.168.60.250', '2022-04-23 20:34:48');
INSERT INTO `operate_log` VALUES ('1517846028835926018', NULL, NULL, 1, '用户“admin”在“2022-04-23 20:41:08”登录了', 1, 'admin', '192.168.60.250', '2022-04-23 20:41:08');
INSERT INTO `operate_log` VALUES ('1517847182068199425', NULL, NULL, 1, '用户“admin”在“2022-04-23 20:45:43”登录了', 1, 'admin', '192.168.60.250', '2022-04-23 20:45:43');
INSERT INTO `operate_log` VALUES ('1517847362360356865', NULL, NULL, 1, '用户“admin”在“2022-04-23 20:46:26”登录了', 1, 'admin', '192.168.60.250', '2022-04-23 20:46:26');
INSERT INTO `operate_log` VALUES ('1517847484452352001', NULL, NULL, 1, '用户“admin”在“2022-04-23 20:46:55”登录了', 1, 'admin', '192.168.60.250', '2022-04-23 20:46:55');
INSERT INTO `operate_log` VALUES ('1517847591201583105', NULL, NULL, 1, '用户“admin”在“2022-04-23 20:47:20”登录了', 1, 'admin', '192.168.60.250', '2022-04-23 20:47:21');
INSERT INTO `operate_log` VALUES ('1517874226185416706', NULL, NULL, 1, '用户“admin”在“2022-04-23 22:33:10”登录了', 1, 'admin', '192.168.60.250', '2022-04-23 22:33:11');
INSERT INTO `operate_log` VALUES ('1517874373158023169', NULL, NULL, 1, '用户“admin”在“2022-04-23 22:33:45”登录了', 1, 'admin', '192.168.60.250', '2022-04-23 22:33:46');
INSERT INTO `operate_log` VALUES ('1517893305675853825', NULL, NULL, 1, '用户“admin”在“2022-04-23 23:48:59”登录了', 1, 'admin', '192.168.60.250', '2022-04-23 23:49:00');
INSERT INTO `operate_log` VALUES ('1518039971276034049', NULL, NULL, 1, '用户“admin”在“2022-04-24 09:31:47”登录了', 1, 'admin', '127.0.0.1', '2022-04-24 09:31:48');
INSERT INTO `operate_log` VALUES ('1518055726616956930', NULL, NULL, 1, '用户“admin”在“2022-04-24 10:34:23”登录了', 1, 'admin', '192.168.60.250', '2022-04-24 10:34:24');
INSERT INTO `operate_log` VALUES ('1518056675066531841', '', '', 7, '用户“admin”在“2022-04-24 10:38:10”，“添加”了“导航栏”模块的ID“”，“”', 3, 'admin', '192.168.60.250', '2022-04-24 10:38:10');
INSERT INTO `operate_log` VALUES ('1518056705299075074', '7e37864f67490bc2be98027b82fa95e7', '', 7, '用户“admin”在“2022-04-24 10:38:17”，“预览”了“导航栏”模块的ID“7e37864f67490bc2be98027b82fa95e7”，“”', 8, 'admin', '192.168.60.250', '2022-04-24 10:38:17');
INSERT INTO `operate_log` VALUES ('1518056787545182209', '7e37864f67490bc2be98027b82fa95e7', '', 7, '用户“admin”在“2022-04-24 10:38:36”，“编辑”了“导航栏”模块的ID“7e37864f67490bc2be98027b82fa95e7”，“”', 4, 'admin', '192.168.60.250', '2022-04-24 10:38:37');
INSERT INTO `operate_log` VALUES ('1518056916503252994', '7e37864f67490bc2be98027b82fa95e7', '', 7, '用户“admin”在“2022-04-24 10:39:07”，“预览”了“导航栏”模块的ID“7e37864f67490bc2be98027b82fa95e7”，“”', 8, 'admin', '192.168.60.250', '2022-04-24 10:39:08');
INSERT INTO `operate_log` VALUES ('1518057127778734081', '7e37864f67490bc2be98027b82fa95e7', '', 7, '用户“admin”在“2022-04-24 10:39:58”，“预览”了“导航栏”模块的ID“7e37864f67490bc2be98027b82fa95e7”，“”', 8, 'admin', '192.168.60.250', '2022-04-24 10:39:58');
INSERT INTO `operate_log` VALUES ('1518057551139196930', '7e37864f67490bc2be98027b82fa95e7', '', 7, '用户“admin”在“2022-04-24 10:41:38”，“预览”了“导航栏”模块的ID“7e37864f67490bc2be98027b82fa95e7”，“”', 8, 'admin', '192.168.60.250', '2022-04-24 10:41:39');
INSERT INTO `operate_log` VALUES ('1518057586702712834', NULL, NULL, 1, '用户“admin”在“2022-04-24 10:41:47”登录了', 1, 'admin', '127.0.0.1', '2022-04-24 10:41:47');
INSERT INTO `operate_log` VALUES ('1518057604348137473', '7e37864f67490bc2be98027b82fa95e7', '', 7, '用户“admin”在“2022-04-24 10:41:51”，“预览”了“导航栏”模块的ID“7e37864f67490bc2be98027b82fa95e7”，“”', 8, 'admin', '192.168.60.250', '2022-04-24 10:41:52');
INSERT INTO `operate_log` VALUES ('1518060376552394754', '', '', 7, '用户“admin”在“2022-04-24 10:52:52”，“添加”了“导航栏”模块的ID“”，“”', 3, 'admin', '192.168.60.250', '2022-04-24 10:52:53');
INSERT INTO `operate_log` VALUES ('1518060408546545665', 'ffb0c05608562f49003e83103b5418cf', '', 7, '用户“admin”在“2022-04-24 10:53:00”，“预览”了“导航栏”模块的ID“ffb0c05608562f49003e83103b5418cf”，“”', 8, 'admin', '192.168.60.250', '2022-04-24 10:53:00');
INSERT INTO `operate_log` VALUES ('1518060808368574465', NULL, NULL, 1, '用户“admin”在“2022-04-24 10:54:35”登录了', 1, 'admin', '127.0.0.1', '2022-04-24 10:54:36');
INSERT INTO `operate_log` VALUES ('1518061019677609985', 'ffb0c05608562f49003e83103b5418cf', '', 7, '用户“admin”在“2022-04-24 10:55:25”，“预览”了“导航栏”模块的ID“ffb0c05608562f49003e83103b5418cf”，“”', 8, 'admin', '192.168.60.250', '2022-04-24 10:55:26');
INSERT INTO `operate_log` VALUES ('1518061025398640641', 'ffb0c05608562f49003e83103b5418cf', '', 7, '用户“admin”在“2022-04-24 10:55:27”，“预览”了“导航栏”模块的ID“ffb0c05608562f49003e83103b5418cf”，“”', 8, 'admin', '192.168.60.250', '2022-04-24 10:55:27');
INSERT INTO `operate_log` VALUES ('1518061074878844929', 'ffb0c05608562f49003e83103b5418cf', '', 7, '用户“admin”在“2022-04-24 10:55:39”，“添加”了“导航栏”模块的ID“ffb0c05608562f49003e83103b5418cf”，“”', 3, 'admin', '192.168.60.250', '2022-04-24 10:55:39');
INSERT INTO `operate_log` VALUES ('1518061148144947202', 'ffb0c05608562f49003e83103b5418cf', '', 7, '用户“admin”在“2022-04-24 10:55:56”，“预览”了“导航栏”模块的ID“ffb0c05608562f49003e83103b5418cf”，“”', 8, 'admin', '192.168.60.250', '2022-04-24 10:55:57');
INSERT INTO `operate_log` VALUES ('1518061435773640705', NULL, NULL, 1, '用户“admin”在“2022-04-24 10:57:05”登录了', 1, 'admin', '127.0.0.1', '2022-04-24 10:57:05');
INSERT INTO `operate_log` VALUES ('1518061493063536641', 'a906517e7729044fee8bcbbf80c88aea', '', 7, '用户“admin”在“2022-04-24 10:57:18”，“预览”了“导航栏”模块的ID“a906517e7729044fee8bcbbf80c88aea”，“”', 8, 'admin', '192.168.60.250', '2022-04-24 10:57:19');
INSERT INTO `operate_log` VALUES ('1518061509580705794', 'a906517e7729044fee8bcbbf80c88aea', '', 7, '用户“admin”在“2022-04-24 10:57:22”，“预览”了“导航栏”模块的ID“a906517e7729044fee8bcbbf80c88aea”，“”', 8, 'admin', '192.168.60.250', '2022-04-24 10:57:23');
INSERT INTO `operate_log` VALUES ('1518061550580027394', 'a906517e7729044fee8bcbbf80c88aea', '', 7, '用户“admin”在“2022-04-24 10:57:32”，“预览”了“导航栏”模块的ID“a906517e7729044fee8bcbbf80c88aea”，“”', 8, 'admin', '192.168.60.250', '2022-04-24 10:57:33');
INSERT INTO `operate_log` VALUES ('1518063857103306753', NULL, NULL, 1, '用户“admin”在“2022-04-24 11:06:42”登录了', 1, 'admin', '127.0.0.1', '2022-04-24 11:06:42');
INSERT INTO `operate_log` VALUES ('1518064089774026753', NULL, NULL, 1, '用户“admin”在“2022-04-24 11:07:37”登录了', 1, 'admin', '192.168.60.250', '2022-04-24 11:07:38');
INSERT INTO `operate_log` VALUES ('1518064503999295490', 'a906517e7729044fee8bcbbf80c88aea', '', 7, '用户“admin”在“2022-04-24 11:09:16”，“预览”了“导航栏”模块的ID“a906517e7729044fee8bcbbf80c88aea”，“”', 8, 'admin', '192.168.60.250', '2022-04-24 11:09:17');
INSERT INTO `operate_log` VALUES ('1518065281740697601', NULL, NULL, 1, '用户“admin”在“2022-04-24 11:12:22”登录了', 1, 'admin', '192.168.60.250', '2022-04-24 11:12:22');
INSERT INTO `operate_log` VALUES ('1518065345460563969', 'a906517e7729044fee8bcbbf80c88aea', '', 7, '用户“admin”在“2022-04-24 11:12:37”，“预览”了“导航栏”模块的ID“a906517e7729044fee8bcbbf80c88aea”，“”', 8, 'admin', '192.168.60.250', '2022-04-24 11:12:37');
INSERT INTO `operate_log` VALUES ('1518065922256977922', NULL, NULL, 1, '用户“admin”在“2022-04-24 11:14:54”登录了', 1, 'admin', '127.0.0.1', '2022-04-24 11:14:55');
INSERT INTO `operate_log` VALUES ('1518066390815338497', 'a906517e7729044fee8bcbbf80c88aea', '', 7, '用户“admin”在“2022-04-24 11:16:46”，“预览”了“导航栏”模块的ID“a906517e7729044fee8bcbbf80c88aea”，“”', 8, 'admin', '192.168.60.250', '2022-04-24 11:16:47');
INSERT INTO `operate_log` VALUES ('1518066583531024386', 'a906517e7729044fee8bcbbf80c88aea', '', 7, '用户“admin”在“2022-04-24 11:17:32”，“预览”了“导航栏”模块的ID“a906517e7729044fee8bcbbf80c88aea”，“”', 8, 'admin', '192.168.60.250', '2022-04-24 11:17:32');
INSERT INTO `operate_log` VALUES ('1518067690743283713', NULL, NULL, 1, '用户“admin”在“2022-04-24 11:21:56”登录了', 1, 'admin', '127.0.0.1', '2022-04-24 11:21:56');
INSERT INTO `operate_log` VALUES ('1518070453384523777', NULL, NULL, 1, '用户“admin”在“2022-04-24 11:32:55”登录了', 1, 'admin', '127.0.0.1', '2022-04-24 11:32:55');
INSERT INTO `operate_log` VALUES ('1518094665599995906', NULL, NULL, 1, '用户“admin”在“2022-04-24 13:09:07”登录了', 1, 'admin', '127.0.0.1', '2022-04-24 13:09:08');
INSERT INTO `operate_log` VALUES ('1518096209905049602', NULL, NULL, 1, '用户“admin”在“2022-04-24 13:15:15”登录了', 1, 'admin', '127.0.0.1', '2022-04-24 13:15:16');
INSERT INTO `operate_log` VALUES ('1518101159246630914', '7e37864f67490bc2be98027b82fa95e7', '', 7, '用户“admin”在“2022-04-24 13:34:55”，“删除”了“导航栏”模块的ID“7e37864f67490bc2be98027b82fa95e7”，“”', 5, 'admin', '192.168.60.250', '2022-04-24 13:34:56');
INSERT INTO `operate_log` VALUES ('1518101257678512129', NULL, NULL, 1, '用户“admin”在“2022-04-24 13:35:19”登录了', 1, 'admin', '127.0.0.1', '2022-04-24 13:35:19');
INSERT INTO `operate_log` VALUES ('1518101711145734145', '', '', 7, '用户“admin”在“2022-04-24 13:37:07”，“添加”了“导航栏”模块的ID“”，“”', 3, 'admin', '192.168.60.250', '2022-04-24 13:37:08');
INSERT INTO `operate_log` VALUES ('1518101885951741954', '', '', 7, '用户“admin”在“2022-04-24 13:37:49”，“添加”了“导航栏”模块的ID“”，“”', 3, 'admin', '192.168.60.250', '2022-04-24 13:37:49');
INSERT INTO `operate_log` VALUES ('1518102279834636290', NULL, NULL, 1, '用户“admin”在“2022-04-24 13:39:23”登录了', 1, 'admin', '127.0.0.1', '2022-04-24 13:39:23');
INSERT INTO `operate_log` VALUES ('1518104180227571713', '', '', 7, '用户“admin”在“2022-04-24 13:46:56”，“添加”了“导航栏”模块的ID“”，“”', 3, 'admin', '192.168.60.250', '2022-04-24 13:46:56');
INSERT INTO `operate_log` VALUES ('1518104633594085377', NULL, NULL, 1, '用户“admin”在“2022-04-24 13:48:44”登录了', 1, 'admin', '192.168.60.250', '2022-04-24 13:48:44');
INSERT INTO `operate_log` VALUES ('1518105609424146433', NULL, NULL, 1, '用户“admin”在“2022-04-24 13:52:36”登录了', 1, 'admin', '127.0.0.1', '2022-04-24 13:52:37');
INSERT INTO `operate_log` VALUES ('1518128122984144897', NULL, NULL, 1, '用户“admin”在“2022-04-24 15:22:04”登录了', 1, 'admin', '192.168.60.250', '2022-04-24 15:22:05');
INSERT INTO `operate_log` VALUES ('1518128161118756866', NULL, NULL, 1, '用户“admin”在“2022-04-24 15:22:13”登录了', 1, 'admin', '192.168.60.250', '2022-04-24 15:22:14');
INSERT INTO `operate_log` VALUES ('1518133989179662338', NULL, NULL, 1, '用户“admin”在“2022-04-24 15:45:23”登录了', 1, 'admin', '192.168.60.250', '2022-04-24 15:45:23');
INSERT INTO `operate_log` VALUES ('1518136148575133698', '', '', 7, '用户“admin”在“2022-04-24 15:53:58”，“添加”了“导航栏”模块的ID“”，“”', 3, 'admin', '192.168.60.250', '2022-04-24 15:53:58');
INSERT INTO `operate_log` VALUES ('1518137026598150146', '', '', 7, '用户“admin”在“2022-04-24 15:57:27”，“添加”了“导航栏”模块的ID“”，“”', 3, 'admin', '192.168.60.250', '2022-04-24 15:57:27');
INSERT INTO `operate_log` VALUES ('1518140551625515009', NULL, NULL, 1, '用户“admin”在“2022-04-24 16:11:27”登录了', 1, 'admin', '127.0.0.1', '2022-04-24 16:11:28');
INSERT INTO `operate_log` VALUES ('1518143112269029377', NULL, NULL, 1, '用户“admin”在“2022-04-24 16:21:38”登录了', 1, 'admin', '192.168.60.250', '2022-04-24 16:21:38');
INSERT INTO `operate_log` VALUES ('1518144761209315329', NULL, NULL, 1, '用户“admin”在“2022-04-24 16:28:11”登录了', 1, 'admin', '192.168.60.250', '2022-04-24 16:28:11');
INSERT INTO `operate_log` VALUES ('1518146022746906626', NULL, NULL, 1, '用户“admin”在“2022-04-24 16:33:12”登录了', 1, 'admin', '127.0.0.1', '2022-04-24 16:33:12');
INSERT INTO `operate_log` VALUES ('1518148928611106817', NULL, NULL, 1, '用户“admin”在“2022-04-24 16:44:45”登录了', 1, 'admin', '192.168.60.250', '2022-04-24 16:44:45');
INSERT INTO `operate_log` VALUES ('1518149024182517762', NULL, NULL, 1, '用户“admin”在“2022-04-24 16:45:07”登录了', 1, 'admin', '127.0.0.1', '2022-04-24 16:45:08');
INSERT INTO `operate_log` VALUES ('1518151746447863809', NULL, NULL, 1, '用户“admin”在“2022-04-24 16:55:56”登录了', 1, 'admin', '127.0.0.1', '2022-04-24 16:55:57');
INSERT INTO `operate_log` VALUES ('1518154839656316930', '', '', 7, '用户“admin”在“2022-04-24 17:08:14”，“预览”了“导航栏”模块的ID“”，“”', 8, 'admin', '192.168.60.250', '2022-04-24 17:08:14');
INSERT INTO `operate_log` VALUES ('1518154859969331201', '', '', 7, '用户“admin”在“2022-04-24 17:08:19”，“预览”了“导航栏”模块的ID“”，“”', 8, 'admin', '192.168.60.250', '2022-04-24 17:08:19');
INSERT INTO `operate_log` VALUES ('1518154906228310017', '', '', 7, '用户“admin”在“2022-04-24 17:08:30”，“预览”了“导航栏”模块的ID“”，“”', 8, 'admin', '192.168.60.250', '2022-04-24 17:08:30');
INSERT INTO `operate_log` VALUES ('1518154941812785153', '', '', 7, '用户“admin”在“2022-04-24 17:08:38”，“预览”了“导航栏”模块的ID“”，“”', 8, 'admin', '192.168.60.250', '2022-04-24 17:08:39');
INSERT INTO `operate_log` VALUES ('1518155251931234306', 'ca7a5d1be10bfedbdba505f4f55e5926', '', 7, '用户“admin”在“2022-04-24 17:09:52”，“编辑”了“导航栏”模块的ID“ca7a5d1be10bfedbdba505f4f55e5926”，“”', 4, 'admin', '192.168.60.250', '2022-04-24 17:09:53');
INSERT INTO `operate_log` VALUES ('1518156530610929665', '', '', 7, '用户“admin”在“2022-04-24 17:14:57”，“添加”了“导航栏”模块的ID“”，“”', 3, 'admin', '192.168.60.250', '2022-04-24 17:14:58');
INSERT INTO `operate_log` VALUES ('1518156534830399490', NULL, NULL, 1, '用户“admin”在“2022-04-24 17:14:58”登录了', 1, 'admin', '192.168.60.250', '2022-04-24 17:14:59');
INSERT INTO `operate_log` VALUES ('1518156628375941122', NULL, NULL, 1, '用户“admin”在“2022-04-24 17:15:20”登录了', 1, 'admin', '127.0.0.1', '2022-04-24 17:15:21');
INSERT INTO `operate_log` VALUES ('1518156793988055041', '', '', 7, '用户“admin”在“2022-04-24 17:16:00”，“添加”了“导航栏”模块的ID“”，“”', 3, 'admin', '192.168.60.250', '2022-04-24 17:16:00');
INSERT INTO `operate_log` VALUES ('1518157107516473345', NULL, NULL, 1, '用户“admin”在“2022-04-24 17:17:15”登录了', 1, 'admin', '127.0.0.1', '2022-04-24 17:17:15');
INSERT INTO `operate_log` VALUES ('1518160116556288001', 'b8a025854266f9dc70fdfe8079749137', '', 7, '用户“admin”在“2022-04-24 17:29:12”，“添加”了“导航栏”模块的ID“b8a025854266f9dc70fdfe8079749137”，“”', 3, 'admin', '192.168.60.250', '2022-04-24 17:29:12');
INSERT INTO `operate_log` VALUES ('1518160641330827265', NULL, NULL, 1, '用户“admin”在“2022-04-24 17:31:17”登录了', 1, 'admin', '192.168.60.250', '2022-04-24 17:31:18');
INSERT INTO `operate_log` VALUES ('1518160708800401410', NULL, NULL, 1, '用户“admin”在“2022-04-24 17:31:33”登录了', 1, 'admin', '192.168.60.250', '2022-04-24 17:31:34');
INSERT INTO `operate_log` VALUES ('1518160833266372609', 'f3c6825e225da165119778b273757b66', '', 7, '用户“admin”在“2022-04-24 17:32:03”，“删除”了“导航栏”模块的ID“f3c6825e225da165119778b273757b66”，“”', 5, 'admin', '192.168.60.250', '2022-04-24 17:32:03');
INSERT INTO `operate_log` VALUES ('1518161844915720194', NULL, NULL, 1, '用户“admin”在“2022-04-24 17:36:04”登录了', 1, 'admin', '127.0.0.1', '2022-04-24 17:36:05');
INSERT INTO `operate_log` VALUES ('1518163849121890306', NULL, NULL, 1, '用户“admin”在“2022-04-24 17:44:02”登录了', 1, 'admin', '127.0.0.1', '2022-04-24 17:44:02');
INSERT INTO `operate_log` VALUES ('1518164147504701441', NULL, NULL, 1, '用户“admin”在“2022-04-24 17:45:13”登录了', 1, 'admin', '127.0.0.1', '2022-04-24 17:45:14');
INSERT INTO `operate_log` VALUES ('1518165268172423170', NULL, NULL, 1, '用户“admin”在“2022-04-24 17:49:40”登录了', 1, 'admin', '127.0.0.1', '2022-04-24 17:49:41');
INSERT INTO `operate_log` VALUES ('1518165384199458817', NULL, NULL, 1, '用户“admin”在“2022-04-24 17:50:08”登录了', 1, 'admin', '127.0.0.1', '2022-04-24 17:50:08');
INSERT INTO `operate_log` VALUES ('1518166090486714369', NULL, NULL, 1, '用户“admin”在“2022-04-24 17:52:56”登录了', 1, 'admin', '127.0.0.1', '2022-04-24 17:52:57');
INSERT INTO `operate_log` VALUES ('1518171414530629633', NULL, NULL, 1, '用户“admin”在“2022-04-24 18:14:06”登录了', 1, 'admin', '127.0.0.1', '2022-04-24 18:14:06');
INSERT INTO `operate_log` VALUES ('1518171895931899905', NULL, NULL, 1, '用户“admin”在“2022-04-24 18:16:00”登录了', 1, 'admin', '127.0.0.1', '2022-04-24 18:16:01');
INSERT INTO `operate_log` VALUES ('1518173742327414786', NULL, NULL, 1, '用户“admin”在“2022-04-24 18:23:21”登录了', 1, 'admin', '127.0.0.1', '2022-04-24 18:23:21');
INSERT INTO `operate_log` VALUES ('1518174241315377153', NULL, NULL, 1, '用户“admin”在“2022-04-24 18:25:20”登录了', 1, 'admin', '127.0.0.1', '2022-04-24 18:25:20');
INSERT INTO `operate_log` VALUES ('1518189292801826818', '', '', 7, '用户“admin”在“2022-04-24 19:25:08”，“预览”了“导航栏”模块的ID“”，“”', 8, 'admin', '127.0.0.1', '2022-04-24 19:25:09');
INSERT INTO `operate_log` VALUES ('1518190171030364161', 'b8a025854266f9dc70fdfe8079749137', '', 7, '用户“admin”在“2022-04-24 19:28:38”，“编辑”了“导航栏”模块的ID“b8a025854266f9dc70fdfe8079749137”，“”', 4, 'admin', '127.0.0.1', '2022-04-24 19:28:38');
INSERT INTO `operate_log` VALUES ('1518193954829443074', NULL, NULL, 1, '用户“admin”在“2022-04-24 19:43:40”登录了', 1, 'admin', '127.0.0.1', '2022-04-24 19:43:40');
INSERT INTO `operate_log` VALUES ('1518193960235900930', NULL, NULL, 1, '用户“admin”在“2022-04-24 19:43:41”登录了', 1, 'admin', '127.0.0.1', '2022-04-24 19:43:41');
INSERT INTO `operate_log` VALUES ('1518198347209449473', NULL, NULL, 1, '用户“admin”在“2022-04-24 20:01:07”登录了', 1, 'admin', '127.0.0.1', '2022-04-24 20:01:07');
INSERT INTO `operate_log` VALUES ('1518198378431848450', NULL, NULL, 1, '用户“admin”在“2022-04-24 20:01:14”登录了', 1, 'admin', '127.0.0.1', '2022-04-24 20:01:15');
INSERT INTO `operate_log` VALUES ('1518201876519133185', NULL, NULL, 1, '用户“admin”在“2022-04-24 20:15:08”登录了', 1, 'admin', '127.0.0.1', '2022-04-24 20:15:09');
INSERT INTO `operate_log` VALUES ('1518201883708170242', NULL, NULL, 1, '用户“admin”在“2022-04-24 20:15:10”登录了', 1, 'admin', '127.0.0.1', '2022-04-24 20:15:11');
INSERT INTO `operate_log` VALUES ('1518387189275185154', NULL, NULL, 1, '用户“admin”在“2022-04-25 08:31:30”登录了', 1, 'admin', '192.168.60.250', '2022-04-25 08:31:31');
INSERT INTO `operate_log` VALUES ('1518390244938895361', NULL, NULL, 1, '用户“admin”在“2022-04-25 08:43:39”登录了', 1, 'admin', '192.168.60.250', '2022-04-25 08:43:39');
INSERT INTO `operate_log` VALUES ('1518390415567376385', NULL, NULL, 1, '用户“admin”在“2022-04-25 08:44:20”登录了', 1, 'admin', '192.168.60.250', '2022-04-25 08:44:20');
INSERT INTO `operate_log` VALUES ('1518420011100704769', NULL, NULL, 1, '用户“admin”在“2022-04-25 10:41:56”登录了', 1, 'admin', '127.0.0.1', '2022-04-25 10:41:56');
INSERT INTO `operate_log` VALUES ('1518430968387063810', '[1, 2, 3]', '', 3, '用户“admin”在“2022-04-25 11:25:28”，“编辑”了“用户管理”模块的ID“[1, 2, 3]”，“”', 4, 'admin', '127.0.0.1', '2022-04-25 11:25:29');
INSERT INTO `operate_log` VALUES ('1518431743154704386', '[2, 3]', '', 3, '用户“admin”在“2022-04-25 11:28:33”，“编辑”了“用户管理”模块的ID“[2, 3]”，“”', 4, 'admin', '127.0.0.1', '2022-04-25 11:28:33');
INSERT INTO `operate_log` VALUES ('1518432051368939521', NULL, NULL, 1, '用户“admin”在“2022-04-25 11:29:46”登录了', 1, 'admin', '127.0.0.1', '2022-04-25 11:29:47');
INSERT INTO `operate_log` VALUES ('1518432408225992705', NULL, NULL, 1, '用户“admin”在“2022-04-25 11:31:11”登录了', 1, 'admin', '192.168.60.250', '2022-04-25 11:31:12');
INSERT INTO `operate_log` VALUES ('1518434006696247297', NULL, NULL, 1, '用户“admin”在“2022-04-25 11:37:32”登录了', 1, 'admin', '192.168.60.250', '2022-04-25 11:37:33');
INSERT INTO `operate_log` VALUES ('1518434616199921666', '[41733d099f99629639ab5d4dc1938fca, d20a9e5242a603d0d630d240f8f41728]', '', 3, '用户“admin”在“2022-04-25 11:39:58”，“删除”了“用户管理”模块的ID“[41733d099f99629639ab5d4dc1938fca, d20a9e5242a603d0d630d240f8f41728]”，“”', 5, 'admin', '192.168.60.250', '2022-04-25 11:39:58');
INSERT INTO `operate_log` VALUES ('1518458971545976833', NULL, NULL, 1, '用户“admin”在“2022-04-25 13:16:45”登录了', 1, 'admin', '192.168.60.250', '2022-04-25 13:16:45');
INSERT INTO `operate_log` VALUES ('1518462166435475458', NULL, NULL, 1, '用户“admin”在“2022-04-25 13:29:26”登录了', 1, 'admin', '127.0.0.1', '2022-04-25 13:29:27');
INSERT INTO `operate_log` VALUES ('1518463234825306114', NULL, NULL, 1, '用户“admin”在“2022-04-25 13:33:41”登录了', 1, 'admin', '192.168.60.250', '2022-04-25 13:33:42');
INSERT INTO `operate_log` VALUES ('1518463353540886529', NULL, NULL, 1, '用户“admin”在“2022-04-25 13:34:09”登录了', 1, 'admin', '127.0.0.1', '2022-04-25 13:34:10');
INSERT INTO `operate_log` VALUES ('1518463402555523074', NULL, NULL, 1, '用户“admin”在“2022-04-25 13:34:21”登录了', 1, 'admin', '127.0.0.1', '2022-04-25 13:34:22');
INSERT INTO `operate_log` VALUES ('1518463433442377730', NULL, NULL, 1, '用户“admin”在“2022-04-25 13:34:28”登录了', 1, 'admin', '127.0.0.1', '2022-04-25 13:34:29');
INSERT INTO `operate_log` VALUES ('1518464349247684610', NULL, NULL, 1, '用户“admin”在“2022-04-25 13:38:07”登录了', 1, 'admin', '192.168.60.250', '2022-04-25 13:38:07');
INSERT INTO `operate_log` VALUES ('1518465895145009154', NULL, NULL, 1, '用户“admin”在“2022-04-25 13:44:15”登录了', 1, 'admin', '127.0.0.1', '2022-04-25 13:44:16');
INSERT INTO `operate_log` VALUES ('1518466143021539329', NULL, NULL, 1, '用户“admin”在“2022-04-25 13:45:14”登录了', 1, 'admin', '192.168.60.250', '2022-04-25 13:45:15');
INSERT INTO `operate_log` VALUES ('1518466509423370241', NULL, NULL, 1, '用户“admin”在“2022-04-25 13:46:42”登录了', 1, 'admin', '192.168.60.250', '2022-04-25 13:46:42');
INSERT INTO `operate_log` VALUES ('1518466521779789826', NULL, NULL, 1, '用户“admin”在“2022-04-25 13:46:45”登录了', 1, 'admin', '192.168.60.250', '2022-04-25 13:46:45');
INSERT INTO `operate_log` VALUES ('1518469549844865026', '0b5b9fa41769e2dc517bd1bb51cc3afe', '1', 3, '用户“admin”在“2022-04-25 13:58:47”，“添加”了“用户管理”模块的ID“0b5b9fa41769e2dc517bd1bb51cc3afe”，“1”', 3, 'admin', '192.168.60.250', '2022-04-25 13:58:47');
INSERT INTO `operate_log` VALUES ('1518472523504234497', '[1]', '', 3, '用户“admin”在“2022-04-25 14:10:36”，“编辑”了“用户管理”模块的ID“[1]”，“”', 4, 'admin', '127.0.0.1', '2022-04-25 14:10:36');
INSERT INTO `operate_log` VALUES ('1518472893638979585', NULL, NULL, 1, '用户“admin”在“2022-04-25 14:12:04”登录了', 1, 'admin', '127.0.0.1', '2022-04-25 14:12:04');
INSERT INTO `operate_log` VALUES ('1518472914473623553', NULL, NULL, 1, '用户“admin”在“2022-04-25 14:12:09”登录了', 1, 'admin', '127.0.0.1', '2022-04-25 14:12:09');
INSERT INTO `operate_log` VALUES ('1518472983648743426', NULL, NULL, 1, '用户“admin”在“2022-04-25 14:12:25”登录了', 1, 'admin', '192.168.4.229', '2022-04-25 14:12:26');
INSERT INTO `operate_log` VALUES ('1518475770991751170', NULL, NULL, 1, '用户“admin”在“2022-04-25 14:23:30”登录了', 1, 'admin', '127.0.0.1', '2022-04-25 14:23:30');
INSERT INTO `operate_log` VALUES ('1518476265221898241', NULL, NULL, 1, '用户“admin”在“2022-04-25 14:25:28”登录了', 1, 'admin', '192.168.4.229', '2022-04-25 14:25:28');
INSERT INTO `operate_log` VALUES ('1518476743986413569', NULL, NULL, 1, '用户“admin”在“2022-04-25 14:27:22”登录了', 1, 'admin', '127.0.0.1', '2022-04-25 14:27:22');
INSERT INTO `operate_log` VALUES ('1518478621709201410', NULL, NULL, 1, '用户“admin”在“2022-04-25 14:34:50”登录了', 1, 'admin', '192.168.60.250', '2022-04-25 14:34:50');
INSERT INTO `operate_log` VALUES ('1518478987007913986', NULL, NULL, 1, '用户“admin”在“2022-04-25 14:36:17”登录了', 1, 'admin', '192.168.60.250', '2022-04-25 14:36:17');
INSERT INTO `operate_log` VALUES ('1518481950224986114', NULL, NULL, 1, '用户“admin”在“2022-04-25 14:48:03”登录了', 1, 'admin', '127.0.0.1', '2022-04-25 14:48:04');
INSERT INTO `operate_log` VALUES ('1518482197567262722', '[f2fb03363c4e6fc66b210fff3452c007]', '', 3, '用户“admin”在“2022-04-25 14:49:02”，“删除”了“用户管理”模块的ID“[f2fb03363c4e6fc66b210fff3452c007]”，“”', 5, 'admin', '127.0.0.1', '2022-04-25 14:49:03');
INSERT INTO `operate_log` VALUES ('1518482298918449153', NULL, NULL, 1, '用户“admin”在“2022-04-25 14:49:26”登录了', 1, 'admin', '127.0.0.1', '2022-04-25 14:49:27');
INSERT INTO `operate_log` VALUES ('1518483182754766849', NULL, NULL, 1, '用户“admin”在“2022-04-25 14:52:57”登录了', 1, 'admin', '192.168.60.250', '2022-04-25 14:52:57');
INSERT INTO `operate_log` VALUES ('1518484653424541698', NULL, NULL, 1, '用户“admin”在“2022-04-25 14:58:48”登录了', 1, 'admin', '192.168.60.250', '2022-04-25 14:58:48');
INSERT INTO `operate_log` VALUES ('1518485206238003201', NULL, NULL, 1, '用户“admin”在“2022-04-25 15:00:59”登录了', 1, 'admin', '192.168.60.250', '2022-04-25 15:01:00');
INSERT INTO `operate_log` VALUES ('1518486695429836802', NULL, NULL, 1, '用户“admin”在“2022-04-25 15:06:54”登录了', 1, 'admin', '127.0.0.1', '2022-04-25 15:06:55');
INSERT INTO `operate_log` VALUES ('1518489608004845570', NULL, NULL, 1, '用户“admin”在“2022-04-25 15:18:29”登录了', 1, 'admin', '127.0.0.1', '2022-04-25 15:18:29');
INSERT INTO `operate_log` VALUES ('1518495658279976961', NULL, NULL, 1, '用户“admin”在“2022-04-25 15:42:29”登录了', 1, 'admin', '127.0.0.1', '2022-04-25 15:42:32');
INSERT INTO `operate_log` VALUES ('1518499748041023489', NULL, NULL, 1, '用户“admin”在“2022-04-25 15:58:46”登录了', 1, 'admin', '192.168.60.250', '2022-04-25 15:58:47');
INSERT INTO `operate_log` VALUES ('1518500042573389826', NULL, NULL, 1, '用户“admin”在“2022-04-25 15:59:56”登录了', 1, 'admin', '127.0.0.1', '2022-04-25 15:59:57');
INSERT INTO `operate_log` VALUES ('1518500606296285185', NULL, NULL, 1, '用户“admin”在“2022-04-25 16:02:11”登录了', 1, 'admin', '192.168.60.250', '2022-04-25 16:02:12');
INSERT INTO `operate_log` VALUES ('1518510846605570049', NULL, NULL, 1, '用户“admin”在“2022-04-25 16:42:52”登录了', 1, 'admin', '127.0.0.1', '2022-04-25 16:42:53');
INSERT INTO `operate_log` VALUES ('1518511973694750721', NULL, NULL, 1, '用户“admin”在“2022-04-25 16:47:21”登录了', 1, 'admin', '127.0.0.1', '2022-04-25 16:47:22');
INSERT INTO `operate_log` VALUES ('1518512809529954305', NULL, NULL, 1, '用户“admin”在“2022-04-25 16:50:41”登录了', 1, 'admin', '127.0.0.1', '2022-04-25 16:50:41');
INSERT INTO `operate_log` VALUES ('1518512946236358658', NULL, NULL, 1, '用户“admin”在“2022-04-25 16:51:12”登录了', 1, 'admin', '127.0.0.1', '2022-04-25 16:51:13');
INSERT INTO `operate_log` VALUES ('1518515143787429890', NULL, NULL, 1, '用户“admin”在“2022-04-25 16:59:56”登录了', 1, 'admin', '127.0.0.1', '2022-04-25 16:59:57');
INSERT INTO `operate_log` VALUES ('1518519999558377474', NULL, NULL, 1, '用户“admin”在“2022-04-25 17:19:15”登录了', 1, 'admin', '192.168.60.250', '2022-04-25 17:19:15');
INSERT INTO `operate_log` VALUES ('1518524813423996930', NULL, NULL, 1, '用户“admin”在“2022-04-25 17:38:19”登录了', 1, 'admin', '127.0.0.1', '2022-04-25 17:38:19');
INSERT INTO `operate_log` VALUES ('1518525045188653057', NULL, NULL, 1, '用户“admin”在“2022-04-25 17:39:18”登录了', 1, 'admin', '127.0.0.1', '2022-04-25 17:39:18');
INSERT INTO `operate_log` VALUES ('1518525179314102273', NULL, NULL, 1, '用户“admin”在“2022-04-25 17:39:50”登录了', 1, 'admin', '192.168.60.250', '2022-04-25 17:39:50');
INSERT INTO `operate_log` VALUES ('1518525666562232322', NULL, NULL, 1, '用户“admin”在“2022-04-25 17:41:46”登录了', 1, 'admin', '127.0.0.1', '2022-04-25 17:41:46');
INSERT INTO `operate_log` VALUES ('1518526173561327618', NULL, NULL, 1, '用户“admin”在“2022-04-25 17:43:47”登录了', 1, 'admin', '127.0.0.1', '2022-04-25 17:43:47');
INSERT INTO `operate_log` VALUES ('1518527934132944898', NULL, NULL, 1, '用户“admin”在“2022-04-25 17:50:47”登录了', 1, 'admin', '127.0.0.1', '2022-04-25 17:50:47');
INSERT INTO `operate_log` VALUES ('1518529880621375489', NULL, NULL, 1, '用户“admin”在“2022-04-25 17:58:31”登录了', 1, 'admin', '127.0.0.1', '2022-04-25 17:58:31');
INSERT INTO `operate_log` VALUES ('1518531260425715714', NULL, NULL, 1, '用户“admin”在“2022-04-25 18:04:00”登录了', 1, 'admin', '192.168.60.250', '2022-04-25 18:04:00');
INSERT INTO `operate_log` VALUES ('1518546273915912194', NULL, NULL, 1, '用户“admin”在“2022-04-25 19:03:39”登录了', 1, 'admin', '192.168.60.250', '2022-04-25 19:03:40');
INSERT INTO `operate_log` VALUES ('1518759941505077249', NULL, NULL, 1, '用户“admin”在“2022-04-26 09:12:41”登录了', 1, 'admin', '127.0.0.1', '2022-04-26 09:12:42');
INSERT INTO `operate_log` VALUES ('1518761578017669121', NULL, NULL, 1, '用户“admin”在“2022-04-26 09:19:12”登录了', 1, 'admin', '127.0.0.1', '2022-04-26 09:19:12');
INSERT INTO `operate_log` VALUES ('1518761741637423105', NULL, NULL, 1, '用户“admin”在“2022-04-26 09:19:51”登录了', 1, 'admin', '127.0.0.1', '2022-04-26 09:19:51');
INSERT INTO `operate_log` VALUES ('1518763251825954818', NULL, NULL, 1, '用户“admin”在“2022-04-26 09:25:51”登录了', 1, 'admin', '127.0.0.1', '2022-04-26 09:25:51');
INSERT INTO `operate_log` VALUES ('1518763772670406658', NULL, NULL, 1, '用户“admin”在“2022-04-26 09:27:55”登录了', 1, 'admin', '127.0.0.1', '2022-04-26 09:27:55');
INSERT INTO `operate_log` VALUES ('1518780101884407809', NULL, NULL, 1, '用户“admin”在“2022-04-26 10:32:48”登录了', 1, 'admin', '127.0.0.1', '2022-04-26 10:32:49');
INSERT INTO `operate_log` VALUES ('1518784025899057153', NULL, NULL, 1, '用户“admin”在“2022-04-26 10:48:24”登录了', 1, 'admin', '192.168.60.250', '2022-04-26 10:48:24');
INSERT INTO `operate_log` VALUES ('1518843932719464449', NULL, NULL, 1, '用户“admin”在“2022-04-26 14:46:26”登录了', 1, 'admin', '192.168.60.250', '2022-04-26 14:46:27');
INSERT INTO `operate_log` VALUES ('1518867226017501185', NULL, NULL, 1, '用户“admin”在“2022-04-26 16:19:00”登录了', 1, 'admin', '192.168.60.250', '2022-04-26 16:19:01');
INSERT INTO `operate_log` VALUES ('1518870298026414082', '', '', 7, '用户“admin”在“2022-04-26 16:31:12”，“预览”了“导航栏”模块的ID“”，“”', 8, 'admin', '192.168.60.250', '2022-04-26 16:31:13');
INSERT INTO `operate_log` VALUES ('1518870613278691330', NULL, NULL, 1, '用户“admin”在“2022-04-26 16:32:28”登录了', 1, 'admin', '192.168.60.250', '2022-04-26 16:32:28');
INSERT INTO `operate_log` VALUES ('1518892849737846785', NULL, NULL, 1, '用户“admin”在“2022-04-26 18:00:49”登录了', 1, 'admin', '127.0.0.1', '2022-04-26 18:00:50');
INSERT INTO `operate_log` VALUES ('1518893584764452866', NULL, NULL, 1, '用户“admin”在“2022-04-26 18:03:44”登录了', 1, 'admin', '127.0.0.1', '2022-04-26 18:03:45');
INSERT INTO `operate_log` VALUES ('1518894190329655298', NULL, NULL, 1, '用户“admin”在“2022-04-26 18:06:09”登录了', 1, 'admin', '127.0.0.1', '2022-04-26 18:06:09');
INSERT INTO `operate_log` VALUES ('1518930067642748929', NULL, NULL, 1, '用户“admin”在“2022-04-26 20:28:43”登录了', 1, 'admin', '192.168.60.250', '2022-04-26 20:28:43');
INSERT INTO `operate_log` VALUES ('1518953219705901057', NULL, NULL, 1, '用户“admin”在“2022-04-26 22:00:43”登录了', 1, 'admin', '192.168.60.250', '2022-04-26 22:00:43');
INSERT INTO `operate_log` VALUES ('1518962336239554561', NULL, NULL, 1, '用户“admin”在“2022-04-26 22:36:56”登录了', 1, 'admin', '192.168.60.250', '2022-04-26 22:36:57');
INSERT INTO `operate_log` VALUES ('1519113583584169985', NULL, NULL, 1, '用户“admin”在“2022-04-27 08:37:56”登录了', 1, 'admin', '127.0.0.1', '2022-04-27 08:37:57');
INSERT INTO `operate_log` VALUES ('1519114993855946754', NULL, NULL, 1, '用户“admin”在“2022-04-27 08:43:32”登录了', 1, 'admin', '127.0.0.1', '2022-04-27 08:43:33');
INSERT INTO `operate_log` VALUES ('1519117714206023681', NULL, NULL, 1, '用户“admin”在“2022-04-27 08:54:21”登录了', 1, 'admin', '127.0.0.1', '2022-04-27 08:54:22');
INSERT INTO `operate_log` VALUES ('1519119910507188225', NULL, NULL, 1, '用户“admin”在“2022-04-27 09:03:05”登录了', 1, 'admin', '127.0.0.1', '2022-04-27 09:03:05');
INSERT INTO `operate_log` VALUES ('1519120438595227650', NULL, NULL, 1, '用户“admin”在“2022-04-27 09:05:11”登录了', 1, 'admin', '127.0.0.1', '2022-04-27 09:05:11');
INSERT INTO `operate_log` VALUES ('1519124492624924673', NULL, NULL, 1, '用户“admin”在“2022-04-27 09:21:17”登录了', 1, 'admin', '127.0.0.1', '2022-04-27 09:21:18');
INSERT INTO `operate_log` VALUES ('1519126206237179906', NULL, NULL, 1, '用户“admin”在“2022-04-27 09:28:06”登录了', 1, 'admin', '127.0.0.1', '2022-04-27 09:28:06');
INSERT INTO `operate_log` VALUES ('1519186451831558145', NULL, NULL, 1, '用户“admin”在“2022-04-27 13:27:29”登录了', 1, 'admin', '192.168.60.250', '2022-04-27 13:27:30');
INSERT INTO `operate_log` VALUES ('1519186508081369089', NULL, NULL, 1, '用户“admin”在“2022-04-27 13:27:43”登录了', 1, 'admin', '192.168.60.250', '2022-04-27 13:27:43');
INSERT INTO `operate_log` VALUES ('1519186771173281794', NULL, NULL, 1, '用户“admin”在“2022-04-27 13:28:46”登录了', 1, 'admin', '192.168.60.250', '2022-04-27 13:28:46');
INSERT INTO `operate_log` VALUES ('1519191039125028865', NULL, NULL, 1, '用户“admin”在“2022-04-27 13:45:43”登录了', 1, 'admin', '127.0.0.1', '2022-04-27 13:45:44');
INSERT INTO `operate_log` VALUES ('1519191473357164546', NULL, NULL, 1, '用户“admin”在“2022-04-27 13:47:27”登录了', 1, 'admin', '192.168.60.250', '2022-04-27 13:47:27');
INSERT INTO `operate_log` VALUES ('1519192704062427138', NULL, NULL, 1, '用户“admin”在“2022-04-27 13:52:20”登录了', 1, 'admin', '192.168.60.250', '2022-04-27 13:52:21');
INSERT INTO `operate_log` VALUES ('1519210619457294338', NULL, NULL, 1, '用户“admin”在“2022-04-27 15:03:31”登录了', 1, 'admin', '127.0.0.1', '2022-04-27 15:03:32');
INSERT INTO `operate_log` VALUES ('1519211398620618754', NULL, NULL, 1, '用户“admin”在“2022-04-27 15:06:37”登录了', 1, 'admin', '127.0.0.1', '2022-04-27 15:06:38');
INSERT INTO `operate_log` VALUES ('1519212438958993410', NULL, NULL, 1, '用户“admin”在“2022-04-27 15:10:45”登录了', 1, 'admin', '127.0.0.1', '2022-04-27 15:10:46');
INSERT INTO `operate_log` VALUES ('1519213661028196353', NULL, NULL, 1, '用户“admin”在“2022-04-27 15:15:37”登录了', 1, 'admin', '127.0.0.1', '2022-04-27 15:15:37');
INSERT INTO `operate_log` VALUES ('1519215654991863809', NULL, NULL, 1, '用户“admin”在“2022-04-27 15:23:32”登录了', 1, 'admin', '127.0.0.1', '2022-04-27 15:23:32');
INSERT INTO `operate_log` VALUES ('1519216520905969665', NULL, NULL, 1, '用户“admin”在“2022-04-27 15:26:58”登录了', 1, 'admin', '127.0.0.1', '2022-04-27 15:26:59');
INSERT INTO `operate_log` VALUES ('1519216721519521794', NULL, NULL, 1, '用户“admin”在“2022-04-27 15:27:46”登录了', 1, 'admin', '127.0.0.1', '2022-04-27 15:27:47');
INSERT INTO `operate_log` VALUES ('1519217012268683265', NULL, NULL, 1, '用户“admin”在“2022-04-27 15:28:56”登录了', 1, 'admin', '127.0.0.1', '2022-04-27 15:28:56');
INSERT INTO `operate_log` VALUES ('1519217418923257857', NULL, NULL, 1, '用户“admin”在“2022-04-27 15:30:33”登录了', 1, 'admin', '127.0.0.1', '2022-04-27 15:30:33');
INSERT INTO `operate_log` VALUES ('1519238594986807298', NULL, NULL, 1, '用户“admin”在“2022-04-27 16:54:41”登录了', 1, 'admin', '192.168.60.250', '2022-04-27 16:54:42');
INSERT INTO `operate_log` VALUES ('1519240766201843714', NULL, NULL, 1, '用户“admin”在“2022-04-27 17:03:19”登录了', 1, 'admin', '127.0.0.1', '2022-04-27 17:03:19');
INSERT INTO `operate_log` VALUES ('1519244760190050306', NULL, NULL, 1, '用户“admin”在“2022-04-27 17:19:11”登录了', 1, 'admin', '192.168.60.250', '2022-04-27 17:19:12');
INSERT INTO `operate_log` VALUES ('1519247644159926274', NULL, NULL, 1, '用户“admin”在“2022-04-27 17:30:39”登录了', 1, 'admin', '192.168.60.250', '2022-04-27 17:30:39');
INSERT INTO `operate_log` VALUES ('1519247818848493569', NULL, NULL, 1, '用户“admin”在“2022-04-27 17:31:20”登录了', 1, 'admin', '192.168.60.250', '2022-04-27 17:31:21');

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission`  (
  `id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `api_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '后台地址',
  `http_method` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'http请求方式',
  `sort_number` double(11, 0) NOT NULL DEFAULT 100,
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
INSERT INTO `sys_permission` VALUES ('066a10b8bb511ce2742f6f22b2dba959', '新增', NULL, NULL, 0, '', 1, 'bded1434f63d43b6e71319e7ef7e8dfc', '2022-04-23 08:48:50', '2022-04-23 08:48:50', 'admin', 'admin', 0, '', NULL, NULL);
INSERT INTO `sys_permission` VALUES ('1', '北大英华', NULL, 'POST', 100, '', 0, '', '2022-04-15 13:05:36', '2022-04-15 13:05:41', NULL, NULL, 0, 'aggregation', '', NULL);
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
INSERT INTO `sys_permission` VALUES ('22', '操作日志', '/operateLog/**', 'GET', 100, '/operationList', 0, '6', '2022-04-16 23:18:46', '2022-04-16 23:18:46', 'admin', 'admin', 0, 'aggregation', '/xtsz/operation/operationList', NULL);
INSERT INTO `sys_permission` VALUES ('23', '添加用户', '/user/add**', 'POST', 2, '', 1, '20', '2022-04-16 23:18:46', '2022-04-16 23:18:46', 'admin', 'admin', 0, 'aggregation', '', '3');
INSERT INTO `sys_permission` VALUES ('24', '编辑用户', '/user/update/**', 'PUT', 1, '', 1, '20', '2022-04-16 23:18:46', '2022-04-16 23:18:46', 'admin', 'admin', 0, 'aggregation', '', '4');
INSERT INTO `sys_permission` VALUES ('25', '删除用户', '/user/del/**', 'DELETE', 4, '', 1, '20', '2022-04-16 23:18:46', '2022-04-16 23:18:46', 'admin', 'admin', 0, 'aggregation', '', '4');
INSERT INTO `sys_permission` VALUES ('26', '批量删除用户', '/user/bulk-del**', 'POST', 3, '', 1, '20', '2022-04-16 23:18:46', '2022-04-16 23:18:46', 'admin', 'admin', 0, 'aggregation', '', '4');
INSERT INTO `sys_permission` VALUES ('27', '重置密码', '/user/bulk-reset-password**', 'PUT', 100, '', 1, '20', '2022-04-16 23:18:46', '2022-04-16 23:18:46', 'admin', 'admin', 0, 'aggregation', '', '4');
INSERT INTO `sys_permission` VALUES ('28', '编辑角色', '/role/update/**', 'PUT', 100, '', 1, '19', '2022-04-16 23:18:46', '2022-04-16 23:18:46', 'admin', 'admin', 0, 'aggregation', '', '4');
INSERT INTO `sys_permission` VALUES ('29', '删除角色', '/api/role/del/**', 'DELETE', 100, '', 1, '19', '2022-04-16 23:18:46', '2022-04-16 23:18:46', 'admin', 'admin', 0, 'aggregation', '', '4');
INSERT INTO `sys_permission` VALUES ('30', '批量删除角色', '/role/bulk-del**', 'POST', 100, '', 1, '19', '2022-04-16 23:18:46', '2022-04-16 23:18:46', 'admin', 'admin', 0, 'aggregation', '', '4');
INSERT INTO `sys_permission` VALUES ('31', '添加角色', '/role/add**', 'POST', 100, '', 1, '19', '2022-04-16 23:18:46', '2022-04-16 23:18:46', 'admin', 'admin', 0, 'aggregation', '', '3');
INSERT INTO `sys_permission` VALUES ('32', '外国法规', '', 'GET', 6, '/sjgl/foreignReg', 0, '5', '2022-04-16 23:18:46', '2022-04-16 23:18:46', 'admin', 'admin', 0, 'aggregation', '/sjgl/chinese/chineseList', NULL);
INSERT INTO `sys_permission` VALUES ('33', '国际条约', '', 'GET', 7, '/sjgl/interList', 0, '5', '2022-04-16 23:18:46', '2022-04-16 23:18:46', 'admin', 'admin', 0, 'aggregation', '/sjgl/chinese/chineseList', NULL);
INSERT INTO `sys_permission` VALUES ('34', '经典案例', '', 'GET', 8, '/sjgl/caseList', 0, '5', '2022-04-19 09:45:40', '2022-04-19 09:45:40', 'admin', 'admin', 0, 'manage', NULL, NULL);
INSERT INTO `sys_permission` VALUES ('35', '法律服务机构', '', 'GET', 9, '/sjgl/service', 0, '5', '2022-04-16 23:18:46', '2022-04-16 23:18:46', 'admin', 'admin', 0, 'aggregation', '/sjgl/chinese/chineseList', NULL);
INSERT INTO `sys_permission` VALUES ('36', '咨询专家', '', 'GET', 10, '/sjgl/expertList', 0, '5', '2022-04-16 23:18:46', '2022-04-16 23:18:46', 'admin', 'admin', 0, 'aggregation', '/sjgl/chinese/chineseList', NULL);
INSERT INTO `sys_permission` VALUES ('37', '删除菜单', '/permission/del/**', 'DELETE', 100, '/sys/menu', 0, '6', '2022-04-16 23:18:46', '2022-04-16 23:18:46', 'admin', 'admin', 0, 'aggregation', '/xtsz/sys/menu', NULL);
INSERT INTO `sys_permission` VALUES ('5', '数据管理', NULL, 'GET', 1, '', 0, '1', '2022-04-15 13:05:36', '2022-04-15 13:05:41', NULL, NULL, 0, 'aggregation', NULL, NULL);
INSERT INTO `sys_permission` VALUES ('6', '系统管理', NULL, 'GET', 2, '', 0, '1', '2022-04-15 13:05:36', '2022-04-15 13:05:41', NULL, NULL, 0, 'aggregation', NULL, NULL);
INSERT INTO `sys_permission` VALUES ('73f6391e9197fb9e45429513e7e4268f', '要在', NULL, NULL, 0, 'test', 0, '6', '2022-04-22 17:27:20', '2022-04-22 17:27:20', 'admin', 'admin', 0, 'job', NULL, NULL);
INSERT INTO `sys_permission` VALUES ('87fadeba27fa7cc4f8aeadfea45edceb', '信息管理', NULL, NULL, 0, '/xigl', 0, '1', '2022-04-23 08:50:52', '2022-04-23 08:50:52', 'admin', 'admin', 0, 'admin', NULL, NULL);
INSERT INTO `sys_permission` VALUES ('bded1434f63d43b6e71319e7ef7e8dfc', '123', NULL, NULL, 0, '123/21', 0, '6', '2022-04-23 08:37:02', '2022-04-23 08:37:02', 'admin', 'admin', 0, 'sousuo', NULL, NULL);
INSERT INTO `sys_permission` VALUES ('d2276ee35791cf5454078ed2f74b5ed1', '测试', NULL, NULL, 100, 'test', 1, '22', '2022-04-22 17:24:23', '2022-04-22 17:24:23', 'admin', 'admin', 0, 'admin', NULL, NULL);
INSERT INTO `sys_permission` VALUES ('d2e9dffaafb482defd413c87ac321b92', 'name', NULL, 'GET', 100, '/xaa', 1, '22', '2022-04-19 20:19:23', '2022-04-19 20:19:23', 'admin', 'admin', 0, 'bianji', NULL, NULL);

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
