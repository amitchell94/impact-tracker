ALTER TABLE `impacttracker`.`reductions` 
ADD COLUMN `r_unit` VARCHAR(20) NOT NULL AFTER `r_average_per_day`;

UPDATE `impacttracker`.`reductions` SET `r_unit` = 'days' WHERE (`r_id` = '1');
UPDATE `impacttracker`.`reductions` SET `r_unit` = 'days' WHERE (`r_id` = '2');
UPDATE `impacttracker`.`reductions` SET `r_unit` = 'miles' WHERE (`r_id` = '3');
UPDATE `impacttracker`.`reductions` SET `r_unit` = 'tons' WHERE (`r_id` = '4');
UPDATE `impacttracker`.`reductions` SET `r_unit` = 'pounds' WHERE (`r_id` = '5');

ALTER TABLE `impacttracker`.`impacts` 
DROP COLUMN `i_reduction_unit`;
