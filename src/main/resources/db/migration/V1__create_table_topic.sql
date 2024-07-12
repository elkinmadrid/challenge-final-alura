-- topic.topic definition

CREATE TABLE `topic` (
                         `id` int NOT NULL AUTO_INCREMENT,
                         `title` varchar(100) NOT NULL,
                         `message` varchar(250) NOT NULL,
                         `status` varchar(100) NOT NULL,
                         `course` varchar(100) NOT NULL,
                         `author` varchar(100) NOT NULL,
                         `created` datetime NOT NULL,
                         PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;