DROP TABLE IF EXISTS `User`;
#用户信息表
CREATE TABLE User
(
    UserID       INT PRIMARY KEY AUTO_INCREMENT,
    Username     VARCHAR(50) UNIQUE NOT NULL,
    PasswordHash VARCHAR(255)       NOT NULL,
    Role         VARCHAR(20),
    CreatedAt    TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

#创建管理员
INSERT INTO User (Username, PasswordHash, Role)
VALUES ('root', '123', 'admin');
#创建用户
INSERT INTO User (Username, PasswordHash, Role)
VALUES ('userInformation', '123', 'userInformation');

delete
from User
where Username = 'root';

delete
from User
where Role = 'userInformation';

# 用户信息（用户编号，用户名，用户密码，用户角色，注册时间）
# 注册客户（客户编号，客户电话，客户地址，客户名称，用户编号）外键：用户编号

#注册用户信息表
#客户信息表=客户编号+客户身份证+客户名称+客户电话+客户住址
CREATE TABLE Customer_Information
(
    CustomerID      INT PRIMARY KEY AUTO_INCREMENT,
    CustomerIDCard  VARCHAR(18) UNIQUE NOT NULL,
    CustomerName    VARCHAR(50)        NOT NULL,
    CustomerPhone   VARCHAR(11)        NOT NULL,
    CustomerAddress VARCHAR(255)       NOT NULL,
    UserID          INT,
    FOREIGN KEY (UserID) references User (UserID)
);

# 农产品（农产品编号，农产品名称，农产品类型，农产品介绍，价格）
CREATE TABLE Agricultural_Information
(
    AgriculturalID           INT PRIMARY KEY AUTO_INCREMENT,
    AgriculturalName         VARCHAR(50)    NOT NULL,
    AgriculturalType         VARCHAR(50)    NOT NULL,
    AgriculturalIntroduction VARCHAR(255)   NOT NULL,
    Price                    DECIMAL(10, 2) NOT NULL
);
# 供应商（供应商编号，供应商名称，供应商地址，供应商电话）
CREATE TABLE Supplier_Information
(
    SupplierID      INT PRIMARY KEY AUTO_INCREMENT,
    SupplierName    VARCHAR(50)  NOT NULL,
    SupplierAddress VARCHAR(255) NOT NULL,
    SupplierPhone   VARCHAR(11)  NOT NULL
);
# 销售订单（订单编号，所购商品名称，订单创建时间，总价，客户编号）外键：客户编号
CREATE TABLE Sales_Order
(
    OrderID          INT PRIMARY KEY AUTO_INCREMENT,
    AgriculturalName VARCHAR(50)    NOT NULL,
    OrderCreateTime  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    TotalPrice       DECIMAL(10, 2) NOT NULL,
    UserID       INT,
    FOREIGN KEY (UserID) references User (UserID)
);
# 提供（农产品编号，供应商编号）外键：农产品编号，供应商编号
CREATE TABLE Provide
(
    AgriculturalID INT,
    SupplierID     INT,
    FOREIGN KEY (AgriculturalID) references Agricultural_Information (AgriculturalID),
    FOREIGN KEY (SupplierID) references Supplier_Information (SupplierID)
);
# 购买（农产品编号，客户编号）外键：农产品编号，客户编号
CREATE TABLE Purchase
(
    AgriculturalID INT,
    CustomerID     INT,
    FOREIGN KEY (AgriculturalID) references Agricultural_Information (AgriculturalID),
    FOREIGN KEY (CustomerID) references Customer_Information (CustomerID)
);
# 出现（订单编号，农产品编号）外键：订单编号，农产品编号
CREATE TABLE Appear
(
    OrderID        INT,
    AgriculturalID INT,
    FOREIGN KEY (OrderID) references Sales_Order (OrderID),
    FOREIGN KEY (AgriculturalID) references Agricultural_Information (AgriculturalID)
);

#购物车
DROP TABLE IF EXISTS Shopping_Cart;




# 购物车表（购物车编号，客户编号，农产品编号，供应商名称，数量）
CREATE TABLE Shopping_Cart
(
    CartID         INT PRIMARY KEY AUTO_INCREMENT,
    UserID     INT,
    AgriculturalID INT,
    SupplierName   VARCHAR(50),
    Quantity       INT DEFAULT 1,
    FOREIGN KEY (UserID) REFERENCES Customer_Information (UserID),
    FOREIGN KEY (AgriculturalID) REFERENCES Agricultural_Information (AgriculturalID)
);

ALTER TABLE Sales_Order ADD FOREIGN KEY (UserID)REFERENCES User(UserID);

alter table Sales_Order
    add UserID INT references User (UserID);
