CREATE TABLE impacttracker.commitments
(
    c_id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    c_u_id int(11) NOT NULL,
    c_r_id int(11) NOT NULL,
    c_start_date date NOT NULL,
    c_end_date date NOT NULL,
    c_amount int(11),
    CONSTRAINT commitments_u_fk FOREIGN KEY (c_u_id) REFERENCES impacttracker.user (u_id),
    CONSTRAINT commitments_r_fk FOREIGN KEY (c_r_id) REFERENCES impacttracker.reductions (r_id)
);
CREATE INDEX commitments_u_fk ON impacttracker.commitments (c_u_id);
CREATE INDEX commitments_r_fk ON impacttracker.commitments (c_r_id);
CREATE TABLE impacttracker.impacts
(
    i_id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    i_r_id int(11) NOT NULL,
    i_impact_per_unit int(11) NOT NULL,
    i_impact_type int(11) NOT NULL,
    i_unit varchar(45) NOT NULL,
    CONSTRAINT impacts_r_fk FOREIGN KEY (i_r_id) REFERENCES impacttracker.reductions (r_id)
);
CREATE INDEX impacts_r_fk ON impacttracker.impacts (i_r_id);
CREATE TABLE impacttracker.reductions
(
    r_id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    r_reduction varchar(45) NOT NULL
);
CREATE TABLE impacttracker.user
(
    u_id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    u_name varchar(45) NOT NULL,
    u_password varchar(45) NOT NULL
);