[![Build Status](https://travis-ci.org/amitchell94/impact-tracker.svg?branch=master)](https://travis-ci.org/amitchell94/impact-tracker)
# Impact Tracker

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

### Installing
To get the development environment up and running, you will first need to create the database.

This can be achieved by either importing the database schema, or manually using the terminal.
#### Setting up database by importing schema
Download the schema [impacttracker.sql](/database/impacttracker.sql)

Import the schema into your MySQL server.

#### Setting up database using terminal

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

```

A reductions table

```
CREATE TABLE `reductions` (
  `r_id` int(11) NOT NULL AUTO_INCREMENT,
  `r_reduction` varchar(45) NOT NULL,
  PRIMARY KEY (`r_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

```
An impacts table

```
CREATE TABLE `impacts` (
  `i_id` int(11) NOT NULL AUTO_INCREMENT,
  `i_r_id` int(11) NOT NULL,
  `i_impact_per_unit` int(11) NOT NULL,
  `i_impact_type` int(11) NOT NULL,
  `i_unit` varchar(45) NOT NULL,
  PRIMARY KEY (`i_id`),
  KEY `impacts_r_fk` (`i_r_id`),
  CONSTRAINT `impacts_r_fk` FOREIGN KEY (`i_r_id`) REFERENCES `reductions` (`r_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

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
