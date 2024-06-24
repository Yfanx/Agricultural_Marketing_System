DROP TABLE IF EXISTS `User`;
#用户信息表
CREATE TABLE User (
                      UserID INT PRIMARY KEY AUTO_INCREMENT,
                      Username VARCHAR(50) UNIQUE NOT NULL,
                      PasswordHash VARCHAR(255) NOT NULL,
                      Role VARCHAR(20),
                      CreatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

