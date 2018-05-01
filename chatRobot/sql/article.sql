/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : chatrobot

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-12-06 10:29:28
*/
use chatrobot;


SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
  `articleId` int(11) NOT NULL AUTO_INCREMENT,
  `collarTime` char(255) NOT NULL,
  `articleTitle` varchar(255) NOT NULL,
  `articleSubTitle` varchar(255) NOT NULL,
  `articleAuthor` varchar(255) NOT NULL,
  `updateTime` varchar(255) NOT NULL,
  `articleTag` mediumtext NOT NULL,
  `shopBuyLink` varchar(255) NOT NULL,
  `articleContent` mediumtext NOT NULL,
  `articleImage` varchar(255) NOT NULL,
  `articleArea` varchar(255) NOT NULL,
  `articleLanguage` varchar(255) NOT NULL,
  `articleType` int(11) NOT NULL,
  `articleLink` varchar(255) NOT NULL,
  `issueState` varchar(255) NOT NULL,
  `articleLanguageType` int(11) NOT NULL,
  `articleAreaType` int(11) NOT NULL,
  `webSiteType` int(11) NOT NULL,
  PRIMARY KEY (`articleId`)
) ENGINE=InnoDB AUTO_INCREMENT=2208 DEFAULT CHARSET=utf8;

