INSERT INTO `impacttracker`.`role` (`r_name`) VALUES ('USER');
INSERT INTO `impacttracker`.`role` (`r_name`) VALUES ('ADMIN');
ALTER TABLE `impacttracker`.`user` 
CHANGE COLUMN `u_password` `u_password` VARCHAR(70) NOT NULL;

