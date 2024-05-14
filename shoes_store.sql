/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 100428 (10.4.28-MariaDB)
 Source Host           : localhost:3306
 Source Schema         : shoes_store

 Target Server Type    : MySQL
 Target Server Version : 100428 (10.4.28-MariaDB)
 File Encoding         : 65001

 Date: 14/05/2024 18:17:06
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for brands
-- ----------------------------
DROP TABLE IF EXISTS `brands`;
CREATE TABLE `brands`  (
  `id_brand` int NOT NULL AUTO_INCREMENT,
  `name_brand` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id_brand`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of brands
-- ----------------------------
INSERT INTO `brands` VALUES (1, 'ADIDAS');
INSERT INTO `brands` VALUES (2, 'test brand');

-- ----------------------------
-- Table structure for image_products
-- ----------------------------
DROP TABLE IF EXISTS `image_products`;
CREATE TABLE `image_products`  (
  `id_image` bigint NOT NULL AUTO_INCREMENT,
  `path` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `id_product` bigint NOT NULL,
  `create_at` timestamp NOT NULL DEFAULT current_timestamp,
  PRIMARY KEY (`id_image`) USING BTREE,
  INDEX `IX_Image_Products_id_product`(`id_product` ASC) USING BTREE,
  CONSTRAINT `FK_Image_Products_Products_id_product` FOREIGN KEY (`id_product`) REFERENCES `products` (`id_product`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 37507 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of image_products
-- ----------------------------

-- ----------------------------
-- Table structure for logs
-- ----------------------------
DROP TABLE IF EXISTS `logs`;
CREATE TABLE `logs`  (
  `id_log` int NOT NULL AUTO_INCREMENT,
  `id_user` int NOT NULL,
  `src` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `id_address` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `web_browser` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `create_at` datetime(6) NOT NULL DEFAULT current_timestamp(6) ON UPDATE CURRENT_TIMESTAMP(6),
  `status` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL,
  `support_level` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id_log`) USING BTREE,
  INDEX `support_level`(`support_level` ASC) USING BTREE,
  CONSTRAINT `logs_ibfk_1` FOREIGN KEY (`support_level`) REFERENCES `support` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of logs
-- ----------------------------

-- ----------------------------
-- Table structure for order_details
-- ----------------------------
DROP TABLE IF EXISTS `order_details`;
CREATE TABLE `order_details`  (
  `id_order_detail` bigint NOT NULL AUTO_INCREMENT,
  `id_order` bigint NOT NULL,
  `id_product` bigint NOT NULL,
  `name_size` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL,
  `quantity` int NOT NULL,
  `price` decimal(65, 30) NOT NULL,
  PRIMARY KEY (`id_order_detail`) USING BTREE,
  INDEX `IX_Order_Details_id_order`(`id_order` ASC) USING BTREE,
  INDEX `IX_Order_Details_id_product`(`id_product` ASC) USING BTREE,
  CONSTRAINT `FK_Order_Details_Orders_id_order` FOREIGN KEY (`id_order`) REFERENCES `orders` (`id_order`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `FK_Order_Details_Products_id_product` FOREIGN KEY (`id_product`) REFERENCES `products` (`id_product`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of order_details
-- ----------------------------

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `id_order` bigint NOT NULL AUTO_INCREMENT,
  `email_customer` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL,
  `to_name` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL,
  `to_phone` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL,
  `to_address` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL,
  `to_ward_name` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL,
  `to_district_name` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL,
  `to_province_name` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL,
  `to_ward_id` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL,
  `to_district_id` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL,
  `to_province_id` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL,
  `note` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL,
  `ship_price` decimal(65, 30) NULL DEFAULT NULL,
  `order_value` decimal(65, 30) NULL DEFAULT NULL,
  `total_price` decimal(65, 30) NULL DEFAULT NULL,
  `create_at` timestamp NOT NULL DEFAULT current_timestamp,
  `update_at` timestamp NULL DEFAULT NULL,
  `support_status` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id_order`) USING BTREE,
  INDEX `support_status`(`support_status` ASC) USING BTREE,
  CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`support_status`) REFERENCES `support` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of orders
-- ----------------------------

-- ----------------------------
-- Table structure for products
-- ----------------------------
DROP TABLE IF EXISTS `products`;
CREATE TABLE `products`  (
  `id_product` bigint NOT NULL AUTO_INCREMENT,
  `name_product` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `star_review` int NOT NULL,
  `support_status` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `price` decimal(65, 30) NOT NULL,
  `discount_percent` decimal(65, 30) NOT NULL,
  `id_brand` int NOT NULL DEFAULT 0,
  `id_type_product` int NOT NULL DEFAULT 0,
  `support_sex` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `create_at` timestamp NOT NULL DEFAULT current_timestamp,
  PRIMARY KEY (`id_product`) USING BTREE,
  INDEX `IX_Products_id_brand`(`id_brand` ASC) USING BTREE,
  INDEX `IX_Products_id_type_product`(`id_type_product` ASC) USING BTREE,
  INDEX `support_sex`(`support_sex` ASC) USING BTREE,
  INDEX `support_status`(`support_status` ASC) USING BTREE,
  CONSTRAINT `FK_Products_Brands_id_brand` FOREIGN KEY (`id_brand`) REFERENCES `brands` (`id_brand`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `FK_Products_Type_Products_id_type_product` FOREIGN KEY (`id_type_product`) REFERENCES `type_products` (`id_type`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `products_ibfk_1` FOREIGN KEY (`support_sex`) REFERENCES `support` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `products_ibfk_2` FOREIGN KEY (`support_status`) REFERENCES `support` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 10503 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of products
-- ----------------------------

-- ----------------------------
-- Table structure for size_products
-- ----------------------------
DROP TABLE IF EXISTS `size_products`;
CREATE TABLE `size_products`  (
  `id_product` bigint NOT NULL,
  `name_size` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `quantity_available` int NOT NULL,
  PRIMARY KEY (`id_product`, `name_size`) USING BTREE,
  INDEX `IX_Size_Products_name_size`(`name_size` ASC) USING BTREE,
  CONSTRAINT `FK_Size_Products_Products_id_product` FOREIGN KEY (`id_product`) REFERENCES `products` (`id_product`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of size_products
-- ----------------------------

-- ----------------------------
-- Table structure for support
-- ----------------------------
DROP TABLE IF EXISTS `support`;
CREATE TABLE `support`  (
  `id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `support_value` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL,
  `support_info` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL,
  `create_at` timestamp NOT NULL DEFAULT current_timestamp,
  `update_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of support
-- ----------------------------

-- ----------------------------
-- Table structure for type_products
-- ----------------------------
DROP TABLE IF EXISTS `type_products`;
CREATE TABLE `type_products`  (
  `id_type` int NOT NULL AUTO_INCREMENT,
  `name_type` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id_type`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of type_products
-- ----------------------------

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `id_user` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `fullname` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL,
  `phone` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL,
  `path_img_avatar` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL,
  `otp` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `support_role` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `support_status` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `create_at` timestamp NOT NULL DEFAULT current_timestamp,
  `update_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id_user`) USING BTREE,
  UNIQUE INDEX `IX_Users_email`(`email` ASC) USING BTREE,
  INDEX `support_role`(`support_role` ASC) USING BTREE,
  INDEX `support_status`(`support_status` ASC) USING BTREE,
  CONSTRAINT `users_ibfk_1` FOREIGN KEY (`support_role`) REFERENCES `support` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `users_ibfk_2` FOREIGN KEY (`support_status`) REFERENCES `support` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of users
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
