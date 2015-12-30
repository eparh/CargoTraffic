# --- Testing data

# --- !Ups


INSERT INTO `cargo_traffic`.`company` (name) VALUES
  ("TradeCorp"),
  ("TravelInc"),
  ("SomeInc");

INSERT INTO `address` (country, city, street, house) VALUES
  ("Belarus", "Minsk", "blabla", "12");


INSERT INTO `user` (username, password, name, surname, patronymic, email, birthday, company_id, address_id) VALUES
  ("admin", "admin", "tom", "brown", "васильевич", "test@mail.ru", "1994-1-6", 1, 1);

INSERT INTO `role` (name) VALUES
  ("ADMIN"), ("USER");

INSERT INTO `user_role` (user_id, role_id) VALUES
  (1, 1);

# --- !Downs



