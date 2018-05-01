/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : chatrobot

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-01-03 18:38:04
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `username` varchar(255) NOT NULL COMMENT '用户昵称',
  `password` varchar(255) NOT NULL COMMENT '用户密码',
  `email` varchar(255) NOT NULL COMMENT '用户邮箱',
  `role` varchar(255) NOT NULL COMMENT '用户身份',
  `status` int(1) NOT NULL COMMENT '用户状态',
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'game7', '1d37dc8b9c6b056c8cc16266bc4fc129', 'huangcg@game7.cc', '超管', '0');
INSERT INTO `user` VALUES ('2', 'liubobo', '87bd2afb321b707756efbaacfa9c6b08', 'eivis616861@163.com', '会员', '0');
INSERT INTO `user` VALUES ('3', 'nier1992120', '9f4519f38ab28a6c293f8b52bece637f', '1661686216@qq.com', '会员', '0');
INSERT INTO `user` VALUES ('4', 'Fashion', '145c469d323061c06bccac13a082af18', '4577047@qq.com', '会员', '0');
INSERT INTO `user` VALUES ('7', 'huweiwei', '5e79b6960c499c524e308875c60b44e1', '453533580@qq.com', '会员', '0');
INSERT INTO `user` VALUES ('8', 'crackdeal', '4fbe5531bf29afb478ec61ad3180778b', 'rumabiswas2050@gmail.com', '会员', '0');
INSERT INTO `user` VALUES ('9', 'xiesguo1', 'b23f5f0bbac1e29ab80433b8de5990c2', 'xiesguo@126.com', '会员', '0');
INSERT INTO `user` VALUES ('10', 'tom', '202cb962ac59075b964b07152d234b70', '123456@126.com', '会员', '0');
INSERT INTO `user` VALUES ('11', 'man', '145c469d323061c06bccac13a082af18', 'huangcguo@game7.cc', '会员', '0');
