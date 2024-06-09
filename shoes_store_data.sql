-- support
INSERT INTO `shoes_store_db`.`support` (`id`, `created_at`, `updated_at`, `created_by`, `updated_by`, `value`, `infor`, `description`) VALUES ('S1', NULL, NULL, NULL, NULL, 'OPEN', 'STATUS', NULL);
INSERT INTO `shoes_store_db`.`support` (`id`, `created_at`, `updated_at`, `created_by`, `updated_by`, `value`, `infor`, `description`) VALUES ('G1', NULL, NULL, NULL, NULL, 'MALE', 'GENDER', NULL);

-- brand
INSERT INTO `shoes_store_db`.`brand` (`id`, `created_at`, `updated_at`, `created_by`, `updated_by`, `name`, `code`, `description`, `support_status`) VALUES (1, '2024-06-09 10:57:56', NULL, NULL, NULL, 'JORDAN Company', 'JORDAN', 'Nhà phân phối', 'S1');
INSERT INTO `shoes_store_db`.`brand` (`id`, `created_at`, `updated_at`, `created_by`, `updated_by`, `name`, `code`, `description`, `support_status`) VALUES (3, '2024-06-09 13:42:21', '2024-06-09 13:42:21', NULL, NULL, 'NIKE COMAPANY', 'NIKE', 'Nhà phân phối lớn nhất Châu Á', 'S1');


-- type
INSERT INTO `shoes_store_db`.`type` (`id`, `created_at`, `updated_at`, `created_by`, `updated_by`, `name`) VALUES (1, '2024-06-09 20:48:04', NULL, NULL, NULL, 'Thể thao');


-- product
INSERT INTO `shoes_store_db`.`product` (`id`, `created_at`, `updated_at`, `created_by`, `updated_by`, `name`, `code`, `short_description`, `description`, `support_sex`, `support_status`, `type_id`, `brand_id`) VALUES (1, '2024-06-09 14:46:46', '2024-06-09 17:40:17', NULL, NULL, 'JORDAN Đen', 'JORDAN-black', 'Đổi trả 1 - 1 trong 30 ngày nếu có lỗi', 'Mô tả dài dằng dặc', 'G1', 'S1', 1, 1);
INSERT INTO `shoes_store_db`.`product` (`id`, `created_at`, `updated_at`, `created_by`, `updated_by`, `name`, `code`, `short_description`, `description`, `support_sex`, `support_status`, `type_id`, `brand_id`) VALUES (2, '2024-06-09 14:57:57', NULL, NULL, NULL, 'JORDAN Xám', 'JORDAN-gray', 'Đổi trả 1 - 2 trong 30 ngày nếu có lỗi', 'Mô tả dài dằng dặc', 'G1', 'S1', 1, 1);
INSERT INTO `shoes_store_db`.`product` (`id`, `created_at`, `updated_at`, `created_by`, `updated_by`, `name`, `code`, `short_description`, `description`, `support_sex`, `support_status`, `type_id`, `brand_id`) VALUES (3, '2024-06-09 15:04:33', '2024-06-09 17:13:24', NULL, NULL, 'NIKE Đen', 'NIKE-black', 'Đổi trả 1 - 2 trong 30 ngày nếu có lỗi', 'Mô tả dài dằng dặc', 'G1', 'S1', 1, 3);

-- size
INSERT INTO `shoes_store_db`.`size` (`id`, `product_id`, `created_at`, `updated_at`, `created_by`, `updated_by`, `name`, `quantity`, `price`, `sale_percent`, `description`) VALUES (1, 1, '2024-06-09 14:46:46', '2024-06-09 14:46:46', NULL, NULL, '36', '10', '120000', '10', 'Mô tả dài dằng dặc về size');
INSERT INTO `shoes_store_db`.`size` (`id`, `product_id`, `created_at`, `updated_at`, `created_by`, `updated_by`, `name`, `quantity`, `price`, `sale_percent`, `description`) VALUES (2, 1, '2024-06-09 14:46:46', '2024-06-09 14:46:46', NULL, NULL, '37', '12', '130000', '20', 'Mô tả dài dằng dặc về size 2');
INSERT INTO `shoes_store_db`.`size` (`id`, `product_id`, `created_at`, `updated_at`, `created_by`, `updated_by`, `name`, `quantity`, `price`, `sale_percent`, `description`) VALUES (3, 2, '2024-06-09 14:57:57', '2024-06-09 14:57:57', NULL, NULL, '40', '10', '160000', '10', 'Mô tả dài dằng dặc về size');
INSERT INTO `shoes_store_db`.`size` (`id`, `product_id`, `created_at`, `updated_at`, `created_by`, `updated_by`, `name`, `quantity`, `price`, `sale_percent`, `description`) VALUES (4, 2, '2024-06-09 14:57:57', '2024-06-09 14:57:57', NULL, NULL, '42', '12', '190000', '20', 'Mô tả dài dằng dặc về size 2');
INSERT INTO `shoes_store_db`.`size` (`id`, `product_id`, `created_at`, `updated_at`, `created_by`, `updated_by`, `name`, `quantity`, `price`, `sale_percent`, `description`) VALUES (5, 3, '2024-06-09 15:04:33', '2024-06-09 17:13:24', NULL, NULL, '29', '10', '160000', '20', 'Mô tả dài dằng dặc về size');
INSERT INTO `shoes_store_db`.`size` (`id`, `product_id`, `created_at`, `updated_at`, `created_by`, `updated_by`, `name`, `quantity`, `price`, `sale_percent`, `description`) VALUES (6, 3, '2024-06-09 15:04:33', '2024-06-09 15:04:33', NULL, NULL, '43', '12', '190000', '20', 'Mô tả dài dằng dặc về size 2');
INSERT INTO `shoes_store_db`.`size` (`id`, `product_id`, `created_at`, `updated_at`, `created_by`, `updated_by`, `name`, `quantity`, `price`, `sale_percent`, `description`) VALUES (7, 3, '2024-06-09 17:13:24', NULL, NULL, NULL, '33', '10', '200000', '20', 'Mô tả dài dằng dặc về size 2');

-- image
INSERT INTO `shoes_store_db`.`image` (`id`, `created_at`, `updated_at`, `created_by`, `updated_by`, `path`, `is_thumbnail`, `product_id`) VALUES (1, '2024-06-09 14:46:46', '2024-06-09 17:40:17', NULL, NULL, '/img/jordan-black.png', b'0', 1);
INSERT INTO `shoes_store_db`.`image` (`id`, `created_at`, `updated_at`, `created_by`, `updated_by`, `path`, `is_thumbnail`, `product_id`) VALUES (2, '2024-06-09 14:46:46', '2024-06-09 17:40:17', NULL, NULL, '/img/jordan-black-thumnail.png', b'0', 1);
INSERT INTO `shoes_store_db`.`image` (`id`, `created_at`, `updated_at`, `created_by`, `updated_by`, `path`, `is_thumbnail`, `product_id`) VALUES (3, '2024-06-09 14:57:57', '2024-06-09 14:57:57', NULL, NULL, '/img/JORDAN.png', b'0', 2);
INSERT INTO `shoes_store_db`.`image` (`id`, `created_at`, `updated_at`, `created_by`, `updated_by`, `path`, `is_thumbnail`, `product_id`) VALUES (4, '2024-06-09 14:57:57', '2024-06-09 14:57:57', NULL, NULL, '/img/JORDAN-gray.png', b'0', 2);
INSERT INTO `shoes_store_db`.`image` (`id`, `created_at`, `updated_at`, `created_by`, `updated_by`, `path`, `is_thumbnail`, `product_id`) VALUES (5, '2024-06-09 15:04:33', '2024-06-09 15:04:33', NULL, NULL, '/img/NIKE.png', b'0', 3);
INSERT INTO `shoes_store_db`.`image` (`id`, `created_at`, `updated_at`, `created_by`, `updated_by`, `path`, `is_thumbnail`, `product_id`) VALUES (6, '2024-06-09 15:04:33', '2024-06-09 17:13:24', NULL, NULL, '/img/NIKE-black-thumnail.png', b'0', 3);
INSERT INTO `shoes_store_db`.`image` (`id`, `created_at`, `updated_at`, `created_by`, `updated_by`, `path`, `is_thumbnail`, `product_id`) VALUES (7, '2024-06-09 17:13:24', NULL, NULL, NULL, '/img/NIKE-black.png', b'0', 3);
