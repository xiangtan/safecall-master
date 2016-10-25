CREATE DATABASE  `safecall` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

USE `safecall`;

DROP TABLE IF EXISTS `t_userinfo`;

CREATE TABLE `t_userinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `IMEI` varchar(32) DEFAULT NULL,
  `username` varchar(32) DEFAULT NULL,
  `nickname` varchar(32) DEFAULT NULL,
  `pwd` varchar(32) DEFAULT NULL,
  `mobile` varchar(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_Unique` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `t_userloginfo`;
CREATE TABLE `t_userloginfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `imei` varchar(32) DEFAULT NULL,
  `username` varchar(32) DEFAULT NULL,
  `mobile` varchar(11) DEFAULT NULL,
  `brand` varchar(32) DEFAULT NULL,
  `brand_version` varchar(16) DEFAULT NULL,
  `os` varchar(16) DEFAULT NULL,
  `os_version` varchar(16) DEFAULT NULL,
  `model` varchar(32) DEFAULT NULL,
  `language` varchar(16) DEFAULT NULL,
  `resolution` varchar(32) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `imei_Unique` (`imei`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
