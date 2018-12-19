[![Build Status](https://travis-ci.org/amitchell94/impact-tracker.svg?branch=master)](https://travis-ci.org/amitchell94/impact-tracker)
# Impact Tracker

A Java application which allows you to enter positive impact commitments and keep track of the total estimated impact you are having on the world!

The application is accessable through a web page and all user info is stored in a MySQL database.

URL: http://18.220.98.185

## Schemas
There are two schemas included in this project:
* [Use Cases](diagrams/UseCase/ImpactTrackerUserCaseDiagram.png)
* [Data Model](diagrams/data_model/impact_tracker_schema.png)

These schemas were created using data diagram tool available at [draw.io](http://www.draw.io/).

## Getting Started

You can get started by cloning the project to your local machine:
```
$ git clone https://github.com/amitchell94/impact-tracker.git
```

### Prerequisites

In order to execute this program you will need to install the following:
* Java JDK
* MySQL
* Gradle

### Installing
To get the development environment up and running, you will first need to create the database.

This can be achieved by either importing the database schema, or manually using the terminal.
#### Setting up database by importing schema
Download the schema [2018-11-28_impact_tracker_initial.sql](/database/2018-11-28_impact_tracker_initial.sql)

Import the schema into your MySQL server.

#### Setting up database using terminal
//are all the scripts in here updated with all the latest changes in the SQL files?
 (Thats why i wouldnt put here all the scripts of the sql, just link them and the users will run them)
In your terminal login to your MySQL as a user with the appropriate permissions.

Then enter the following command to create your database.
```
CREATE DATABASE impacttracker;
```
Then use this new database
```
USE impacttracker;
```

You will need to create the following tables in this database:

A user table

```
 CREATE TABLE `user` (
  `u_id` int(11) NOT NULL AUTO_INCREMENT,
  `u_name` varchar(45) NOT NULL,
  `u_password` varchar(45) NOT NULL,
  PRIMARY KEY (`u_id`)
);

```

A reductions table

```
CREATE TABLE `reductions` (
  `r_id` int(11) NOT NULL AUTO_INCREMENT,
  `r_reduction` varchar(45) NOT NULL,
  `r_average_per_day` decimal(10,7) NOT NULL,
  PRIMARY KEY (`r_id`)
);


```
An impacts table

```
CREATE TABLE `impacts` (
  `i_id` int(11) NOT NULL AUTO_INCREMENT,
  `i_r_id` int(11) NOT NULL,
  `i_impact_per_unit` decimal(10,4) NOT NULL,
  `i_impact_unit` varchar(45) NOT NULL,
  `i_impact_type` varchar(45) NOT NULL,
  `i_reduction_unit` varchar(45) NOT NULL,
  PRIMARY KEY (`i_id`),
  KEY `impacts_r_fk` (`i_r_id`),
  CONSTRAINT `impacts_r_fk` FOREIGN KEY (`i_r_id`) REFERENCES `reductions` (`r_id`)
);


```
And finally a commitments table

```
CREATE TABLE `commitments` (
  `c_id` int(11) NOT NULL AUTO_INCREMENT,
  `c_u_id` int(11) NOT NULL,
  `c_r_id` int(11) NOT NULL,
  `c_start_date` date NOT NULL,
  `c_end_date` date NOT NULL,
  `c_amount` int(11) DEFAULT NULL,
  PRIMARY KEY (`c_id`),
  KEY `commitments_u_fk` (`c_u_id`),
  KEY `commitments_r_fk` (`c_r_id`),
  CONSTRAINT `commitments_r_fk` FOREIGN KEY (`c_r_id`) REFERENCES `reductions` (`r_id`),
  CONSTRAINT `commitments_u_fk` FOREIGN KEY (`c_u_id`) REFERENCES `user` (`u_id`)
);

```
Now we need to populate our impacts and reductions tables with data.

Starting with the impacts table
```
INSERT INTO impacts (i_id, i_r_id, i_impact_per_unit, i_impact_unit, i_impact_type, i_reduction_unit) VALUES (1, 5, 2000.0000, 'gallon', 'water use', 'pound');
INSERT INTO impacts (i_id, i_r_id, i_impact_per_unit, i_impact_unit, i_impact_type, i_reduction_unit) VALUES (2, 3, 0.0004, 'ton', 'co2 emissions', 'mile');
INSERT INTO impacts (i_id, i_r_id, i_impact_per_unit, i_impact_unit, i_impact_type, i_reduction_unit) VALUES (3, 4, 2.3000, 'ton', 'co2 emissions', 'ton');
INSERT INTO impacts (i_id, i_r_id, i_impact_per_unit, i_impact_unit, i_impact_type, i_reduction_unit) VALUES (5, 5, 0.0110, 'ton', 'co2 emissions', 'pound');
INSERT INTO impacts (i_id, i_r_id, i_impact_per_unit, i_impact_unit, i_impact_type, i_reduction_unit) VALUES (6, 1, 0.0030, 'ton', 'co2 emissions', 'day');
INSERT INTO impacts (i_id, i_r_id, i_impact_per_unit, i_impact_unit, i_impact_type, i_reduction_unit) VALUES (7, 1, 600.0000, 'gallon', 'water use', 'day');
INSERT INTO impacts (i_id, i_r_id, i_impact_per_unit, i_impact_unit, i_impact_type, i_reduction_unit) VALUES (8, 2, 550.0000, 'gallon', 'water use', 'day');
INSERT INTO impacts (i_id, i_r_id, i_impact_per_unit, i_impact_unit, i_impact_type, i_reduction_unit) VALUES (9, 2, 0.0020, 'ton', 'co2 emissions', 'day');

```
Followed by the reductions table

```
INSERT INTO reductions (r_id, r_reduction, r_average_per_day) VALUES (1, 'vegan', 1.0000000);
INSERT INTO reductions (r_id, r_reduction, r_average_per_day) VALUES (2, 'vegetarianism', 1.0000000);
INSERT INTO reductions (r_id, r_reduction, r_average_per_day) VALUES (3, 'driving', 29.2000000);
INSERT INTO reductions (r_id, r_reduction, r_average_per_day) VALUES (4, 'palm oil consumption', 0.0000027);
INSERT INTO reductions (r_id, r_reduction, r_average_per_day) VALUES (5, 'beef', 222.2000000);
```
## Built With

* [Gradle](https://gradle.org/) - Dependency Management

## Authors

* **Andy Mitchell** - *Initial work* - [GitHub](https://github.com/amitchell94)
* **Andrea Howes** - *Initial work* - [GitHub](https://github.com/ashcreek)
* **Mike Ramey** - *Initial work* - [GitHub](https://github.com/mikram5)

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments

* CodingNomads for teaching us Java!
