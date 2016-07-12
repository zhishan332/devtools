/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 50631
 Source Host           : localhost
 Source Database       : devtool

 Target Server Type    : MySQL
 Target Server Version : 50631
 File Encoding         : utf-8

 Date: 07/12/2016 10:35:48 AM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `mybatis_db`
-- ----------------------------
DROP TABLE IF EXISTS `mybatis_db`;
CREATE TABLE `mybatis_db` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `dbtype` varchar(30) DEFAULT NULL,
  `jdbcurl` varchar(128) DEFAULT NULL,
  `username` varchar(32) DEFAULT NULL,
  `password` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
