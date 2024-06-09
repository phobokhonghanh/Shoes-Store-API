

DROP TABLE IF EXISTS
	`order`,
		size,
	payment_method,
	user,
	image,
	product,
	type,
	brand,
	support

	;
	CREATE TABLE support
(
    id            VARCHAR(36)						NOT NULL,
    created_at    datetime              NULL,
    updated_at    datetime              NULL,
    created_by    BIGINT                NULL,
    updated_by    BIGINT                NULL,
    value          VARCHAR(255)          NOT NULL,
    infor          VARCHAR(35)           NOT NULL,
    `description` VARCHAR(255)          NULL,
    CONSTRAINT pk_support PRIMARY KEY (id)
);

CREATE TABLE brand
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    created_at    datetime              NULL,
    updated_at    datetime              NULL,
    created_by    BIGINT                NULL,
    updated_by    BIGINT                NULL,
    name          VARCHAR(255)          NOT NULL,
    code          VARCHAR(35)           NOT NULL UNIQUE,
    `description` VARCHAR(255)          NULL,
    support_status    VARCHAR(36)	          NOT NULL,
    CONSTRAINT pk_brand PRIMARY KEY (id),
		CONSTRAINT fk_support_status_on_brand FOREIGN KEY (support_status) REFERENCES support (id)

);

CREATE TABLE type
(
    id                BIGINT AUTO_INCREMENT NOT NULL,
    created_at        datetime              NULL,
    updated_at        datetime              NULL,
    created_by        BIGINT                NULL,
    updated_by        BIGINT                NULL,
    name              VARCHAR(255)          NOT NULL,
    CONSTRAINT pk_type PRIMARY KEY (id)
);

CREATE TABLE product
(
    id                BIGINT AUTO_INCREMENT NOT NULL,
    created_at        datetime              NULL,
    updated_at        datetime              NULL,
    created_by        BIGINT                NULL,
    updated_by        BIGINT                NULL,
    name              VARCHAR(255)          NOT NULL,
    code              VARCHAR(255)          NOT NULL UNIQUE,
    short_description VARCHAR(255)          NULL,
    `description`     TEXT                  NULL,
		 support_sex    VARCHAR(36)	          NOT NULL,
    support_status    VARCHAR(36)	          NOT NULL,
    type_id					  BIGINT                NULL,
    brand_id          BIGINT                NULL,
    CONSTRAINT pk_product PRIMARY KEY (id),
		CONSTRAINT fk_brand_on_product FOREIGN KEY (brand_id) REFERENCES brand (id),
		CONSTRAINT fk_support_status_on_product FOREIGN KEY (support_status) REFERENCES support (id),
		CONSTRAINT fk_support_sex_on_product FOREIGN KEY (support_sex) REFERENCES support (id),
		CONSTRAINT fk_type_on_product FOREIGN KEY (type_id) REFERENCES type (id)
);

CREATE TABLE size
(
    id            BIGINT AUTO_INCREMENT 		NOT NULL,
    product_id        BIGINT 								NULL,
    created_at        datetime              NULL,
    updated_at        datetime               NULL,
    created_by        BIGINT                NULL,
    updated_by        BIGINT                NULL,
    name              VARCHAR(255)          NOT NULL,
    quantity          VARCHAR(255)          NOT NULL,
    price							VARCHAR(255)          NOT NULL,
		sale_percent			VARCHAR(255)          NULL,
		`description`     TEXT                  NULL,
				CONSTRAINT pk_size PRIMARY KEY (id),
		CONSTRAINT fk_product_on_size FOREIGN KEY (product_id) REFERENCES product (id)
);

CREATE TABLE image
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    created_at    datetime              NULL,
    updated_at    datetime               NULL,
    created_by    BIGINT                NULL,
    updated_by    BIGINT                NULL,
    `path`        VARCHAR(255)          NOT NULL,
    is_thumbnail  BIT(1)                NOT NULL,
    product_id    BIGINT                NULL,
    CONSTRAINT pk_image PRIMARY KEY (id),
		CONSTRAINT fk_product_on_image FOREIGN KEY (product_id) REFERENCES product (id)
);


CREATE TABLE user
(
    id                   BIGINT AUTO_INCREMENT NOT NULL,
    created_at           datetime              NULL,
    updated_at           datetime               NULL,
    created_by           BIGINT                NULL,
    updated_by           BIGINT                NULL,
    username             VARCHAR(255)          NOT NULL,
    password             VARCHAR(255)          NOT NULL,
    fullname             VARCHAR(255)          NOT NULL,
    email                VARCHAR(255)          NOT NULL,
    phone                VARCHAR(255)          NOT NULL,
    gender               CHAR                  NOT NULL,
		token 							 VARCHAR(255)          NULL,
    avatar               VARCHAR(255)          NULL,
    support_status    	 VARCHAR(36)	         NOT NULL,
		support_role    	 		VARCHAR(36)	         NOT NULL,
    CONSTRAINT pk_user PRIMARY KEY (id),
		CONSTRAINT fk_support_status_on_user FOREIGN KEY (support_status) REFERENCES support (id),
		CONSTRAINT fk_support_role_on_user FOREIGN KEY (support_role) REFERENCES support (id)
);

CREATE TABLE payment_method
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    created_at datetime               NULL,
    updated_at datetime               NULL,
    created_by BIGINT                NULL,
    updated_by BIGINT                NULL,
    name       VARCHAR(255)          NOT NULL,
    code       VARCHAR(255)          NOT NULL UNIQUE,
     support_status    	 VARCHAR(36)	         NOT NULL,
    CONSTRAINT pk_payment_method PRIMARY KEY (id),
		CONSTRAINT fk_support_status_on_payment_method FOREIGN KEY (support_status) REFERENCES support (id)
	
);

CREATE TABLE `order`
(
    id                           BIGINT AUTO_INCREMENT NOT NULL,
    created_at                   datetime               NULL,
    updated_at                   datetime               NULL,
    created_by                   BIGINT                NULL,
    updated_by                   BIGINT                NULL,
    code                         VARCHAR(255)          NOT NULL UNIQUE,
    to_name                      VARCHAR(255)          NULL,
    to_phone                     VARCHAR(255)          NULL,
    to_address                   VARCHAR(255)          NULL,
    to_ward_name                 VARCHAR(255)          NULL,
    to_district_name             VARCHAR(255)          NULL,
    to_province_name             VARCHAR(255)          NULL,
    note                         VARCHAR(255)          NULL,
		size_id                   		BIGINT                NOT NULL,
		quantity                   		BIGINT                NOT NULL,
		price                   		VARCHAR(255)                 NOT NULL,
    total_amount                 DECIMAL(15, 5)        NOT NULL,
    tax                          DECIMAL(15, 5)        NOT NULL,
    shipping_cost                DECIMAL(15, 5)        NOT NULL,
    total_pay                    DECIMAL(15, 5)        NOT NULL,
    payment_method_type          VARCHAR(255)          NULL,
		user_id                      BIGINT                NOT NULL,
    payment_method_order_id      VARCHAR(255)          NULL,
		support_status    	 				 VARCHAR(36)	         NOT NULL,
    CONSTRAINT pk_order PRIMARY KEY (id),
		CONSTRAINT fk_support_status_on_order FOREIGN KEY (support_status) REFERENCES support (id),
		CONSTRAINT fk_user_on_order FOREIGN KEY (user_id) REFERENCES user (id),
		CONSTRAINT fk_size_on_order FOREIGN KEY (size_id) REFERENCES size (id)
);
