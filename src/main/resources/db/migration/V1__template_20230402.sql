CREATE TABLE `template_random` (
 `template_random_id` int NOT NULL AUTO_INCREMENT,
 `template_random` int NOT NULL,
 PRIMARY KEY (`template_random_id`),
 UNIQUE KEY `unique_template_random` (`template_random`)
);