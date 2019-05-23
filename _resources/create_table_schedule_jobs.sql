CREATE TABLE
IF NOT EXISTS schedule_jobs
(
    id INT AUTO_INCREMENT,
    message_text TEXT,
    time DATE,
    channel VARCHAR
(255),
    status TINYINT NOT NULL,
    PRIMARY KEY
(id)
)  ENGINE=INNODB;

SET GLOBAL time_zone = '+1:00';