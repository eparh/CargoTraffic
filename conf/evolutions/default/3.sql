# --- Adding user table

# --- !Ups

CREATE TABLE IF NOT EXISTS `cargo_traffic`.`user` (
  `id`         INTEGER(11) UNSIGNED NOT NULL AUTO_INCREMENT UNIQUE,
  `username`   VARCHAR(250)         NOT NULL,
  `password`   VARCHAR(250)         NOT NULL,
  `name`       VARCHAR(250)         ,
  `surname`    VARCHAR(250)         NOT NULL,
  `patronymic` VARCHAR(250)         ,
  `email`      VARCHAR(250)         ,
  `birthday`   DATE                 ,
  `company_id` INTEGER(11) UNSIGNED,
  `address_id` INTEGER(11) UNSIGNED ,
  `deleted`    BIT(1)                        DEFAULT FALSE,
  PRIMARY KEY (`id`),
  INDEX (`company_id` ASC),
  INDEX (`address_id` ASC),
  FOREIGN KEY (`company_id`)
  REFERENCES `cargo_traffic`.`company` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  FOREIGN KEY (`address_id`)
  REFERENCES `cargo_traffic`.`address` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

# --- !Downs

DROP TABLE IF EXISTS `cargo_traffic`.`user`;

