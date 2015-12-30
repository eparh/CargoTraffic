# --- Adding role tables

# --- !Ups

CREATE TABLE IF NOT EXISTS `cargo_traffic`.`role` (
  `id`   INTEGER(11) UNSIGNED NOT NULL AUTO_INCREMENT UNIQUE,
  `name` VARCHAR(250) NOT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;


CREATE TABLE IF NOT EXISTS `cargo_traffic`.`user_role` (
  `user_id` INTEGER(11) UNSIGNED NOT NULL,
  `role_id` INTEGER(11) UNSIGNED NOT NULL,
  PRIMARY KEY (`user_id`, `role_id`),
  INDEX (`role_id`),
  FOREIGN KEY (`user_id`)
  REFERENCES `cargo_traffic`.`user` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  FOREIGN KEY (`role_id`)
  REFERENCES `cargo_traffic`.`role` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;


# --- !Downs

DROP TABLE IF EXISTS `cargo_traffic`.`role`;
DROP TABLE IF EXISTS `cargo_traffic`.`user_role`;

