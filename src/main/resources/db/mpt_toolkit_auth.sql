/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : localhost:3306
 Source Schema         : mpt_toolkit_auth

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 04/09/2019 17:06:21
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for auth_class
-- ----------------------------
DROP TABLE IF EXISTS `auth_class`;
CREATE TABLE `auth_class` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键 id',
  `auth_service_name` varchar(64) NOT NULL COMMENT '权限所属应用',
  `auth_type` int(1) NOT NULL COMMENT '权限类型 1: function  2: data',
  `auth_range` varchar(64) NOT NULL COMMENT '权限作用范围',
  `auth_ch_name` varchar(64) NOT NULL COMMENT '权限中文名称',
  `auth_table_name` varchar(64) NOT NULL COMMENT '权限点表名',
  `is_deleted` int(1) NOT NULL DEFAULT '0' COMMENT '是否删除 1: 删除 0: 未删除',
  `version` int(11) NOT NULL DEFAULT '1' COMMENT '权限版本',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `created_by` varchar(64) CHARACTER SET utf8 DEFAULT 'default',
  `updated_at` datetime DEFAULT NULL,
  `updated_by` varchar(64) CHARACTER SET utf8 DEFAULT 'default',
  `deleted_at` datetime DEFAULT NULL,
  `deleted_by` varchar(64) CHARACTER SET utf8 DEFAULT 'default',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;
