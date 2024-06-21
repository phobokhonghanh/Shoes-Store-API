/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 100428 (10.4.28-MariaDB)
 Source Host           : localhost:3306
 Source Schema         : shoes_store_db

 Target Server Type    : MySQL
 Target Server Version : 100428 (10.4.28-MariaDB)
 File Encoding         : 65001

 Date: 21/06/2024 12:28:49
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for brand
-- ----------------------------
DROP TABLE IF EXISTS `brand`;
CREATE TABLE `brand`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime NULL DEFAULT NULL,
  `updated_at` datetime NULL DEFAULT NULL,
  `created_by` bigint NULL DEFAULT NULL,
  `updated_by` bigint NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `code` varchar(35) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `support_status` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `code`(`code` ASC) USING BTREE,
  INDEX `fk_support_status_on_brand`(`support_status` ASC) USING BTREE,
  CONSTRAINT `fk_support_status_on_brand` FOREIGN KEY (`support_status`) REFERENCES `support` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of brand
-- ----------------------------
INSERT INTO `brand` VALUES (1, '2024-06-09 10:57:56', NULL, NULL, NULL, 'JORDAN Company', 'JORDAN', 'Nhà phân phối', 'S1');
INSERT INTO `brand` VALUES (3, '2024-06-09 13:42:21', '2024-06-09 13:42:21', NULL, NULL, 'NIKE COMAPANY', 'NIKE', 'Nhà phân phối lớn nhất Châu Á', 'S1');

-- ----------------------------
-- Table structure for image
-- ----------------------------
DROP TABLE IF EXISTS `image`;
CREATE TABLE `image`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime NULL DEFAULT NULL,
  `updated_at` datetime NULL DEFAULT NULL,
  `created_by` bigint NULL DEFAULT NULL,
  `updated_by` bigint NULL DEFAULT NULL,
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `is_thumbnail` bit(1) NOT NULL,
  `product_id` bigint NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_product_on_image`(`product_id` ASC) USING BTREE,
  CONSTRAINT `fk_product_on_image` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of image
-- ----------------------------
INSERT INTO `image` VALUES (1, '2024-06-09 14:46:46', '2024-06-09 17:40:17', NULL, NULL, '/img/jordan-black.png', b'0', 1);
INSERT INTO `image` VALUES (2, '2024-06-09 14:46:46', '2024-06-09 17:40:17', NULL, NULL, '/img/jordan-black-thumnail.png', b'0', 1);
INSERT INTO `image` VALUES (3, '2024-06-09 14:57:57', '2024-06-09 14:57:57', NULL, NULL, '/img/JORDAN.png', b'0', 2);
INSERT INTO `image` VALUES (4, '2024-06-09 14:57:57', '2024-06-09 14:57:57', NULL, NULL, '/img/JORDAN-gray.png', b'0', 2);
INSERT INTO `image` VALUES (5, '2024-06-09 15:04:33', '2024-06-09 15:04:33', NULL, NULL, '/img/NIKE.png', b'0', 3);
INSERT INTO `image` VALUES (6, '2024-06-09 15:04:33', '2024-06-09 17:13:24', NULL, NULL, '/img/NIKE-black-thumnail.png', b'0', 3);
INSERT INTO `image` VALUES (7, '2024-06-09 17:13:24', NULL, NULL, NULL, '/img/NIKE-black.png', b'0', 3);

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime NULL DEFAULT NULL,
  `updated_at` datetime NULL DEFAULT NULL,
  `created_by` bigint NULL DEFAULT NULL,
  `updated_by` bigint NULL DEFAULT NULL,
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `to_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `to_phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `to_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `to_ward_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `to_district_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `to_province_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `note` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `total_amount` decimal(15, 5) NOT NULL,
  `tax` decimal(15, 5) NOT NULL,
  `shipping_cost` decimal(15, 5) NOT NULL,
  `total_pay` decimal(15, 5) NOT NULL,
  `payment_method_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `user_id` bigint NOT NULL,
  `payment_method_order_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `support_status` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `code`(`code` ASC) USING BTREE,
  INDEX `fk_support_status_on_order`(`support_status` ASC) USING BTREE,
  INDEX `fk_user_on_order`(`user_id` ASC) USING BTREE,
  CONSTRAINT `fk_support_status_on_order` FOREIGN KEY (`support_status`) REFERENCES `support` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_user_on_order` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order
-- ----------------------------
INSERT INTO `order` VALUES (19, '2024-06-20 08:44:40', '2024-06-21 04:30:36', NULL, NULL, 'R925Z6Kp', 'John Doe', '0912345678', '123 Le Loi, District 1, HCM', 'Ben Nghe', 'District 1', 'HCM', 'Ghi chú giao hàng', 100000.00000, 30000.00000, 20000.00000, 150000.00000, 'PAYPAL', 6, '36V61572FD8187702', 'O2');
INSERT INTO `order` VALUES (20, '2024-06-21 03:06:50', '2024-06-21 03:07:49', NULL, NULL, 'xl iOAya', 'John Doe', '0912345678', '123 Le Loi, District 1, HCM', 'Ben Nghe', 'District 1', 'HCM', 'Ghi chú giao hàng', 100000.00000, 30000.00000, 20000.00000, 150000.00000, 'PAYPAL', 6, 'true', 'O1');

-- ----------------------------
-- Table structure for order_detail
-- ----------------------------
DROP TABLE IF EXISTS `order_detail`;
CREATE TABLE `order_detail`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `size_id` bigint NOT NULL,
  `quantity` bigint NOT NULL,
  `price` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `id_order_detail` bigint NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_size_on_order_detail`(`size_id` ASC) USING BTREE,
  INDEX `fk_order_on_order_detail`(`id_order_detail` ASC) USING BTREE,
  CONSTRAINT `fk_order_on_order_detail` FOREIGN KEY (`id_order_detail`) REFERENCES `order` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_size_on_order_detail` FOREIGN KEY (`size_id`) REFERENCES `size` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order_detail
-- ----------------------------
INSERT INTO `order_detail` VALUES (14, 3, 1, '100000', 19);
INSERT INTO `order_detail` VALUES (15, 3, 1, '100000', 20);

-- ----------------------------
-- Table structure for payment_method
-- ----------------------------
DROP TABLE IF EXISTS `payment_method`;
CREATE TABLE `payment_method`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime NULL DEFAULT NULL,
  `updated_at` datetime NULL DEFAULT NULL,
  `created_by` bigint NULL DEFAULT NULL,
  `updated_by` bigint NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `support_status` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `code`(`code` ASC) USING BTREE,
  INDEX `fk_support_status_on_payment_method`(`support_status` ASC) USING BTREE,
  CONSTRAINT `fk_support_status_on_payment_method` FOREIGN KEY (`support_status`) REFERENCES `support` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of payment_method
-- ----------------------------
INSERT INTO `payment_method` VALUES (1, '2024-06-21 05:20:35', NULL, NULL, NULL, 'Tiền mặt', 'CASH', 'S1');
INSERT INTO `payment_method` VALUES (2, '2024-06-21 05:20:35', NULL, NULL, NULL, 'Cổng thanh toán PayPal', 'PAYPAL', 'S1');
INSERT INTO `payment_method` VALUES (3, '2024-06-21 05:20:35', '2024-06-21 05:22:11', NULL, NULL, 'Cổng thanh toán Momo', 'MOMO', 'S2');

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime NULL DEFAULT NULL,
  `updated_at` datetime NULL DEFAULT NULL,
  `created_by` bigint NULL DEFAULT NULL,
  `updated_by` bigint NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `short_description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `support_sex` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `support_status` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `type_id` bigint NULL DEFAULT NULL,
  `brand_id` bigint NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `code`(`code` ASC) USING BTREE,
  INDEX `fk_brand_on_product`(`brand_id` ASC) USING BTREE,
  INDEX `fk_support_status_on_product`(`support_status` ASC) USING BTREE,
  INDEX `fk_support_sex_on_product`(`support_sex` ASC) USING BTREE,
  INDEX `fk_type_on_product`(`type_id` ASC) USING BTREE,
  CONSTRAINT `fk_brand_on_product` FOREIGN KEY (`brand_id`) REFERENCES `brand` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_support_sex_on_product` FOREIGN KEY (`support_sex`) REFERENCES `support` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_support_status_on_product` FOREIGN KEY (`support_status`) REFERENCES `support` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_type_on_product` FOREIGN KEY (`type_id`) REFERENCES `type` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES (1, '2024-06-09 14:46:46', '2024-06-09 17:40:17', NULL, NULL, 'JORDAN Đen', 'JORDAN-black', 'Đổi trả 1 - 1 trong 30 ngày nếu có lỗi', 'Mô tả dài dằng dặc', 'G1', 'S1', 1, 1);
INSERT INTO `product` VALUES (2, '2024-06-09 14:57:57', NULL, NULL, NULL, 'JORDAN Xám', 'JORDAN-gray', 'Đổi trả 1 - 2 trong 30 ngày nếu có lỗi', 'Mô tả dài dằng dặc', 'G1', 'S1', 1, 1);
INSERT INTO `product` VALUES (3, '2024-06-09 15:04:33', '2024-06-09 17:13:24', NULL, NULL, 'NIKE Đen', 'NIKE-black', 'Đổi trả 1 - 2 trong 30 ngày nếu có lỗi', 'Mô tả dài dằng dặc', 'G1', 'S1', 1, 3);

-- ----------------------------
-- Table structure for size
-- ----------------------------
DROP TABLE IF EXISTS `size`;
CREATE TABLE `size`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `product_id` bigint NULL DEFAULT NULL,
  `created_at` datetime NULL DEFAULT NULL,
  `updated_at` datetime NULL DEFAULT NULL,
  `created_by` bigint NULL DEFAULT NULL,
  `updated_by` bigint NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `quantity` bigint NOT NULL,
  `price` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `sale_percent` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_product_on_size`(`product_id` ASC) USING BTREE,
  CONSTRAINT `fk_product_on_size` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of size
-- ----------------------------
INSERT INTO `size` VALUES (1, 1, '2024-06-09 14:46:46', '2024-06-20 06:51:32', NULL, NULL, '36', 5, '120000', '10', 'Mô tả dài dằng dặc về size');
INSERT INTO `size` VALUES (2, 1, '2024-06-09 14:46:46', '2024-06-20 08:25:48', NULL, NULL, '37', 10, '130000', '20', 'Mô tả dài dằng dặc về size 2');
INSERT INTO `size` VALUES (3, 2, '2024-06-09 14:57:57', '2024-06-21 03:06:51', NULL, NULL, '40', 8, '160000', '10', 'Mô tả dài dằng dặc về size');
INSERT INTO `size` VALUES (4, 2, '2024-06-09 14:57:57', '2024-06-09 14:57:57', NULL, NULL, '42', 12, '190000', '20', 'Mô tả dài dằng dặc về size 2');
INSERT INTO `size` VALUES (5, 3, '2024-06-09 15:04:33', '2024-06-20 08:25:48', NULL, NULL, '29', 9, '160000', '20', 'Mô tả dài dằng dặc về size');
INSERT INTO `size` VALUES (6, 3, '2024-06-09 15:04:33', '2024-06-09 15:04:33', NULL, NULL, '43', 12, '190000', '20', 'Mô tả dài dằng dặc về size 2');
INSERT INTO `size` VALUES (7, 3, '2024-06-09 17:13:24', NULL, NULL, NULL, '33', 10, '200000', '20', 'Mô tả dài dằng dặc về size 2');

-- ----------------------------
-- Table structure for support
-- ----------------------------
DROP TABLE IF EXISTS `support`;
CREATE TABLE `support`  (
  `id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `created_at` datetime NULL DEFAULT NULL,
  `updated_at` datetime NULL DEFAULT NULL,
  `created_by` bigint NULL DEFAULT NULL,
  `updated_by` bigint NULL DEFAULT NULL,
  `value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `infor` varchar(35) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of support
-- ----------------------------
INSERT INTO `support` VALUES ('G1', NULL, NULL, NULL, NULL, 'MALE', 'GENDER', NULL);
INSERT INTO `support` VALUES ('G2', NULL, NULL, NULL, NULL, 'FEMALE', 'GENDER', NULL);
INSERT INTO `support` VALUES ('O1', NULL, NULL, NULL, NULL, 'PROCESSING', 'ORDER', NULL);
INSERT INTO `support` VALUES ('O2', NULL, NULL, NULL, NULL, 'SHIPPING', 'ORDER', NULL);
INSERT INTO `support` VALUES ('O3', NULL, NULL, NULL, NULL, 'DELIVERED', 'ORDER', NULL);
INSERT INTO `support` VALUES ('O4', NULL, NULL, NULL, NULL, 'CANCEL', 'ORDER', NULL);
INSERT INTO `support` VALUES ('R1', NULL, NULL, NULL, NULL, 'ADMIN', 'ROLE', NULL);
INSERT INTO `support` VALUES ('R2', NULL, NULL, NULL, NULL, 'CLIENT', 'ROLE', NULL);
INSERT INTO `support` VALUES ('S1', NULL, NULL, NULL, NULL, 'OPEN', 'STATUS', NULL);
INSERT INTO `support` VALUES ('S2', NULL, NULL, NULL, NULL, 'LOCK', 'STATUS', NULL);
INSERT INTO `support` VALUES ('S3', NULL, NULL, NULL, NULL, 'VERIFY', 'STATUS', NULL);

-- ----------------------------
-- Table structure for type
-- ----------------------------
DROP TABLE IF EXISTS `type`;
CREATE TABLE `type`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime NULL DEFAULT NULL,
  `updated_at` datetime NULL DEFAULT NULL,
  `created_by` bigint NULL DEFAULT NULL,
  `updated_by` bigint NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of type
-- ----------------------------
INSERT INTO `type` VALUES (1, '2024-06-09 20:48:04', NULL, NULL, NULL, 'Thể thao');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime NULL DEFAULT NULL,
  `updated_at` datetime NULL DEFAULT NULL,
  `created_by` bigint NULL DEFAULT NULL,
  `updated_by` bigint NULL DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `fullname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `gender` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `token` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `support_status` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `support_role` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_support_status_on_user`(`support_status` ASC) USING BTREE,
  INDEX `fk_support_role_on_user`(`support_role` ASC) USING BTREE,
  CONSTRAINT `fk_support_role_on_user` FOREIGN KEY (`support_role`) REFERENCES `support` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_support_status_on_user` FOREIGN KEY (`support_status`) REFERENCES `support` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (5, '2024-06-16 06:59:00', '2024-06-18 04:10:13', NULL, NULL, 'test', '$2a$10$pogsybOGHG2ZbHUgku3TfuH.XISp443.wHLN6tXm5.lcw/W5odnrC', 'John Doe', 'tester@gmail.com', '1234567890', '0', '798125', NULL, 'S3', 'R1');
INSERT INTO `user` VALUES (6, '2024-06-16 09:16:00', '2024-06-16 09:21:16', NULL, NULL, 'johndoe', '$2a$10$B.tbDkNPHpABZfTxeDrjQO3/FwFLEZH0ce40358S7WpvT.4MJtv5K', 'John Doe', 'johndoe@example.com', '1234567890', '1', NULL, NULL, 'S1', 'R2');

SET FOREIGN_KEY_CHECKS = 1;
