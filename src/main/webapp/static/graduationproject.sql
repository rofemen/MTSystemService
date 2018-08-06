/*
Navicat MySQL Data Transfer
Source Host     : localhost:3306
Source Database : graduationproject
Target Host     : localhost:3306
Target Database : graduationproject
Date: 2018-01-08 23:43:07
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for authinfo
-- ----------------------------
DROP TABLE IF EXISTS `authinfo`;
CREATE TABLE `authinfo` (
  `auth_id` int(11) NOT NULL,
  `auth_name` varchar(50) DEFAULT NULL,
  `auth_permission` varchar(50) DEFAULT NULL COMMENT 'shiro格式的权限字符串',
  `auth_extra_1` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`auth_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of authinfo
-- ----------------------------

-- ----------------------------
-- Table structure for classinfo
-- ----------------------------
DROP TABLE IF EXISTS `classinfo`;
CREATE TABLE `classinfo` (
  `cl_code` bigint(11) NOT NULL COMMENT '班级编号',
  `cl_dpId` bigint(11) NOT NULL DEFAULT '1' COMMENT '系别编号',
  `cl_grade` int(11) DEFAULT NULL,
  `cl_name` varchar(50) DEFAULT NULL,
  `cl_count` int(11) DEFAULT NULL COMMENT '班级人数',
  `cl_rowname` varchar(50) DEFAULT NULL COMMENT '排名称',
  `cl_instor` bigint(20) DEFAULT NULL COMMENT '辅导员',
  `cl_army_instor` bigint(20) DEFAULT NULL COMMENT '教官',
  `cl_tutor` bigint(20) DEFAULT NULL COMMENT '导师',
  `cl_extra_1` varchar(20) DEFAULT NULL,
  `cl_extra_2` varchar(20) DEFAULT NULL,
  `cl_extra_3` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`cl_code`),
  KEY `cl_instor` (`cl_instor`),
  KEY `cl_army_instor` (`cl_army_instor`),
  KEY `cl_tutor` (`cl_tutor`),
  KEY `cl_dpId` (`cl_dpId`),
  CONSTRAINT `classinfo_ibfk_2` FOREIGN KEY (`cl_instor`) REFERENCES `teacherinfo` (`tc_userId`),
  CONSTRAINT `classinfo_ibfk_3` FOREIGN KEY (`cl_army_instor`) REFERENCES `teacherinfo` (`tc_userId`),
  CONSTRAINT `classinfo_ibfk_4` FOREIGN KEY (`cl_tutor`) REFERENCES `teacherinfo` (`tc_userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of classinfo
-- ----------------------------
INSERT INTO `classinfo` VALUES ('101', '1', '2014', '软件工程（1）班 ', '60', '一排', '10001', '10002', '10003', null, null, null);

-- ----------------------------
-- Table structure for courseinfo
-- ----------------------------
DROP TABLE IF EXISTS `courseinfo`;
CREATE TABLE `courseinfo` (
  `ci_code` bigint(20) NOT NULL,
  `ci_name` varchar(50) DEFAULT NULL,
  `ci_des` text COMMENT '课程描述',
  `ci_extra_1` varchar(20) DEFAULT NULL,
  `ci_extra_2` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`ci_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of courseinfo
-- ----------------------------
INSERT INTO `courseinfo` VALUES ('1122', '软件工程', '关于一个软件开发周期的系统性学科', null, null);

-- ----------------------------
-- Table structure for coursescore
-- ----------------------------
DROP TABLE IF EXISTS `coursescore`;
CREATE TABLE `coursescore` (
  `cs_id` bigint(20) NOT NULL DEFAULT '0',
  `cs_ci_code` bigint(20) NOT NULL,
  `cs_stu_userId` bigint(20) NOT NULL,
  `cs_score` int(11) NOT NULL DEFAULT '0' COMMENT '成绩',
  `cs_extra_1` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`cs_id`),
  KEY `cs_stu_userId` (`cs_stu_userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of coursescore
-- ----------------------------
INSERT INTO `coursescore` VALUES ('0', '1122', '1', '92', null);

-- ----------------------------
-- Table structure for departmentinfo
-- ----------------------------
DROP TABLE IF EXISTS `departmentinfo`;
CREATE TABLE `departmentinfo` (
  `di_id` bigint(11) NOT NULL,
  `di_name` varchar(50) DEFAULT NULL,
  `company` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`di_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of departmentinfo
-- ----------------------------
INSERT INTO `departmentinfo` VALUES ('1', '软件系', '一连');
INSERT INTO `departmentinfo` VALUES ('2', '管理系', '二连');
INSERT INTO `departmentinfo` VALUES ('3', '网络系', '三连');
INSERT INTO `departmentinfo` VALUES ('4', '电子系', '四连');
INSERT INTO `departmentinfo` VALUES ('5', '计算机系', '五连');
INSERT INTO `departmentinfo` VALUES ('6', '数码媒体系', '六连');
INSERT INTO `departmentinfo` VALUES ('7', '游戏系', '七连');
INSERT INTO `departmentinfo` VALUES ('8', '国际经贸系', '八连');
INSERT INTO `departmentinfo` VALUES ('9', '财会系', '九连');
INSERT INTO `departmentinfo` VALUES ('10', '外语系 ', '十连');

-- ----------------------------
-- Table structure for evaluationinfo
-- ----------------------------
DROP TABLE IF EXISTS `evaluationinfo`;
CREATE TABLE `evaluationinfo` (
  `ei_id` bigint(20) NOT NULL,
  `ei_publisher` bigint(20) NOT NULL COMMENT '发布者',
  `ei_target` bigint(20) NOT NULL,
  `ei_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `ei_content` text,
  `ei_score` int(11) DEFAULT '0',
  `ei_extra_1` varchar(20) DEFAULT NULL,
  `ei_extra_2` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`ei_id`),
  KEY `ei_publisher` (`ei_publisher`),
  KEY `ei_target` (`ei_target`),
  CONSTRAINT `evaluationinfo_ibfk_1` FOREIGN KEY (`ei_publisher`) REFERENCES `studentinfo` (`stu_userId`),
  CONSTRAINT `evaluationinfo_ibfk_2` FOREIGN KEY (`ei_target`) REFERENCES `teacherinfo` (`tc_userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of evaluationinfo
-- ----------------------------

-- ----------------------------
-- Table structure for roleinfo
-- ----------------------------
DROP TABLE IF EXISTS `roleinfo`;
CREATE TABLE `roleinfo` (
  `role_id` int(11) NOT NULL,
  `role_authority` varchar(100) DEFAULT NULL COMMENT '角色权限字符',
  `role_name` varchar(50) DEFAULT NULL COMMENT '角色名称',
  `role_extra_1` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of roleinfo
-- ----------------------------
INSERT INTO `roleinfo` VALUES ('0', 'all', '辅导员', null);
INSERT INTO `roleinfo` VALUES ('1', 'part', '教官', null);
INSERT INTO `roleinfo` VALUES ('2', 'part', '导师', null);

-- ----------------------------
-- Table structure for scheduleinfo
-- ----------------------------
DROP TABLE IF EXISTS `scheduleinfo`;
CREATE TABLE `scheduleinfo` (
  `si_id` bigint(20) NOT NULL,
  `si_cl_code` bigint(11) NOT NULL COMMENT '班级编号',
  `si_ci_code` bigint(20) NOT NULL COMMENT '课程编号',
  `si_tc_num` bigint(20) DEFAULT NULL COMMENT '教官工号',
  `si_time` timestamp NULL DEFAULT NULL COMMENT '课程时间',
  `si_extra_1` varchar(20) DEFAULT NULL,
  `si_extra_2` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`si_id`),
  KEY `si_ci_code` (`si_ci_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of scheduleinfo
-- ----------------------------

-- ----------------------------
-- Table structure for shareinfo
-- ----------------------------
DROP TABLE IF EXISTS `shareinfo`;
CREATE TABLE `shareinfo` (
  `share_id` bigint(20) NOT NULL,
  `share_publisher` bigint(20) NOT NULL COMMENT '发布者',
  `share_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `share_content` text,
  `share_pic1` varchar(100) DEFAULT NULL,
  `share_pic2` varchar(100) DEFAULT NULL,
  `share_pic3` varchar(100) DEFAULT NULL,
  `share_good` int(11) DEFAULT '0' COMMENT '点赞数量',
  `share_extra_1` varchar(20) DEFAULT NULL,
  `share_extra_2` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`share_id`),
  KEY `share_publisher` (`share_publisher`),
  CONSTRAINT `shareinfo_ibfk_1` FOREIGN KEY (`share_publisher`) REFERENCES `studentinfo` (`stu_userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of shareinfo
-- ----------------------------

-- ----------------------------
-- Table structure for studentinfo
-- ----------------------------
DROP TABLE IF EXISTS `studentinfo`;
CREATE TABLE `studentinfo` (
  `stu_userId` bigint(20) NOT NULL AUTO_INCREMENT,
  `stu_username` varchar(50) DEFAULT NULL,
  `stu_password` varchar(100) DEFAULT NULL,
  `stu_pre_password` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `stu_num` bigint(20) DEFAULT NULL COMMENT '学号',
  `stu_name` varchar(50) DEFAULT NULL,
  `stu_grade` int(11) DEFAULT '0',
  `stu_class` bigint(11) DEFAULT '0',
  `stu_tel` varchar(50) DEFAULT NULL,
  `stu_birthday` timestamp NULL DEFAULT NULL,
  `stu_sex` tinyint(4) DEFAULT NULL COMMENT '0：男，1：女',
  `stu_lock` int(11) DEFAULT '0',
  `stu_firstLogin` tinyint(4) DEFAULT '0' COMMENT '0：第一次登陆，1：非第一次登陆',
  `stu_extra_1` varchar(20) DEFAULT NULL,
  `stu_extra_2` varchar(20) DEFAULT NULL,
  `stu_extra_3` varchar(20) DEFAULT NULL,
  `stu_extra_4` varchar(20) DEFAULT NULL,
  `stu_extra_5` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`stu_userId`),
  KEY `stu_class` (`stu_class`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of studentinfo
-- ----------------------------
INSERT INTO `studentinfo` VALUES ('1', 'superstu', 'b142aeb5c4870c9c39e6ae3cdffbe295', 'super', '1440125136', '郑日枋', '2014', '101', '15622174974', '1996-01-02 00:00:00', '0', '0', '0', null, null, null, null, null);
INSERT INTO `studentinfo` VALUES ('2', null, null, null, '1440125137', null, '0', '0', null, null, null, '0', '0', null, null, null, null, null);

-- ----------------------------
-- Table structure for teacherinfo
-- ----------------------------
DROP TABLE IF EXISTS `teacherinfo`;
CREATE TABLE `teacherinfo` (
  `tc_userId` bigint(20) NOT NULL AUTO_INCREMENT,
  `tc_username` varchar(50) DEFAULT NULL,
  `tc_password` varchar(100) DEFAULT NULL,
  `tc_pre_password` varchar(100) DEFAULT NULL,
  `tc_num` bigint(20) DEFAULT NULL COMMENT '工号',
  `tc_name` varchar(50) DEFAULT NULL,
  `tc_department` bigint(11) DEFAULT NULL COMMENT '系别',
  `tc_tel` varchar(50) DEFAULT NULL,
  `tc_birthday` timestamp NULL DEFAULT NULL,
  `tc_sex` tinyint(4) DEFAULT NULL COMMENT '0：男，1：女',
  `tc_email` varchar(50) DEFAULT NULL,
  `tc_role` tinyint(4) DEFAULT NULL COMMENT '0：辅导员，1：教官，2：导师',
  `tc_lock` int(11) DEFAULT '0',
  `tc_firstLogin` tinyint(4) DEFAULT '0' COMMENT '0：第一次登陆，1：非第一次登陆',
  `tc_extra_1` varchar(20) DEFAULT NULL,
  `tc_extra_2` varchar(20) DEFAULT NULL,
  `tc_extra_3` varchar(20) DEFAULT NULL,
  `tc_extra_4` varchar(20) DEFAULT NULL,
  `tc_extra_5` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`tc_userId`),
  KEY `tc_department` (`tc_department`)
) ENGINE=InnoDB AUTO_INCREMENT=10021 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of teacherinfo
-- ----------------------------
INSERT INTO `teacherinfo` VALUES ('10001', 'admin', '3ef7164d1f6167cb9f2658c07d3c2f0a', '7a661aa2e5ac7e0bdb301d812857113e', '1001', 'rofe', '1', '15622174974', '1996-01-02 00:00:00', '1', '815360296@qq.com', '0', '0', '0', null, null, null, null, null);
INSERT INTO `teacherinfo` VALUES ('10002', 'army_zheng', 'army', 'army', '1002', '陈教官', '1', '15622174973', '1989-01-02 00:00:00', '0', '976749752@qq.com', '1', '0', '0', null, null, null, null, null);
INSERT INTO `teacherinfo` VALUES ('10003', 'teacher_huang', 'teacher', 'teacher', '1003', '黄老师', '1', '15622174972', '1987-02-01 00:00:00', '1', '679511863@qq.com', '2', '0', '0', null, null, null, null, null);
INSERT INTO `teacherinfo` VALUES ('10004', null, null, null, '1440125138', null, null, null, null, null, null, null, '0', '0', null, null, null, null, null);
INSERT INTO `teacherinfo` VALUES ('10005', null, null, null, '1440125138', null, null, null, null, null, null, null, '0', '0', null, null, null, null, null);
INSERT INTO `teacherinfo` VALUES ('10006', null, null, null, '1440125138', null, null, null, null, null, null, null, '0', '0', null, null, null, null, null);
INSERT INTO `teacherinfo` VALUES ('10007', null, null, null, '1440125138', null, null, null, null, null, null, null, '0', '0', null, null, null, null, null);
INSERT INTO `teacherinfo` VALUES ('10008', null, null, null, '1440125138', null, null, null, null, null, null, null, '0', '0', null, null, null, null, null);
INSERT INTO `teacherinfo` VALUES ('10009', null, null, null, '1440125138', null, null, null, null, null, null, null, '0', '0', null, null, null, null, null);
INSERT INTO `teacherinfo` VALUES ('10010', null, null, null, '1440125138', null, null, null, null, null, null, null, '0', '0', null, null, null, null, null);
INSERT INTO `teacherinfo` VALUES ('10011', null, null, null, '1440125138', null, null, null, null, null, null, null, '0', '0', null, null, null, null, null);
INSERT INTO `teacherinfo` VALUES ('10012', null, null, null, '1440125138', null, null, null, null, null, null, null, '0', '0', null, null, null, null, null);
INSERT INTO `teacherinfo` VALUES ('10013', null, null, null, '1440125138', null, null, null, null, null, null, null, '0', '0', null, null, null, null, null);
INSERT INTO `teacherinfo` VALUES ('10014', null, null, null, '1440125138', null, null, null, null, null, null, null, '0', '0', null, null, null, null, null);
INSERT INTO `teacherinfo` VALUES ('10015', null, null, null, '1440125138', null, null, null, null, null, null, null, '0', '0', null, null, null, null, null);
INSERT INTO `teacherinfo` VALUES ('10016', null, null, null, '1440125138', null, null, null, null, null, null, null, '0', '0', null, null, null, null, null);
INSERT INTO `teacherinfo` VALUES ('10017', null, null, null, '1440125138', null, null, null, null, null, null, null, '0', '0', null, null, null, null, null);
INSERT INTO `teacherinfo` VALUES ('10018', null, null, null, '1440125138', null, null, null, null, null, null, null, '0', '0', null, null, null, null, null);
INSERT INTO `teacherinfo` VALUES ('10019', null, null, null, '1440125138', null, null, null, null, null, null, null, '0', '0', null, null, null, null, null);
INSERT INTO `teacherinfo` VALUES ('10020', null, null, null, '1440125138', null, null, null, null, null, null, null, '0', '0', null, null, null, null, null);
