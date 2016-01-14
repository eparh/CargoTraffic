# --- Adding address table

# --- !Ups

CREATE TABLE IF NOT EXISTS `cargo_traffic`.`address` (
  `id`      INTEGER(11)  UNSIGNED NOT NULL AUTO_INCREMENT UNIQUE,
  `country` VARCHAR(250),
  `city`    VARCHAR(250),
  `street`  VARCHAR(250),
  `house`   VARCHAR(250),
  `deleted` BIT(1)                         DEFAULT FALSE,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;


# --- !Downs

DROP TABLE IF EXISTS `cargo_traffic`.`address`;