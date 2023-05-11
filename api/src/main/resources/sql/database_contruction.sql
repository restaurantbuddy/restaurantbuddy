DROP
    DATABASE IF EXISTS restaurantbuddy;
CREATE
    DATABASE restaurantbuddy;

DROP
    USER IF EXISTS 'restaurantbuddy'@'localhost';
CREATE
    USER IF NOT EXISTS 'restaurantbuddy'@'localhost' IDENTIFIED BY 'developer.cert';
GRANT ALL PRIVILEGES ON restaurantbuddy.* TO 'restaurantbuddy'@'localhost' IDENTIFIED BY 'developer.cert';

USE restaurantbuddy;

CREATE TABLE ITEM
(
    ITEM_ID          INT PRIMARY KEY NOT NULL,
    ITEM_NAME        VARCHAR(225)    NOT NULL,
    ITEM_COST        DOUBLE          NOT NULL,
    ITEM_DESCRIPTION VARCHAR(225)
);

CREATE TABLE MENU
(
    MENU_ID   INT PRIMARY KEY NOT NULL,
    MENU_NAME VARCHAR(50)     NOT NULL
);

CREATE TABLE MENU_ITEM
(
    MENU_ID INT NOT NULL REFERENCES MENU (MENU_ID),
    ITEM_ID INT NOT NULL REFERENCES ITEM (ITEM_ID),
    PRIMARY KEY (MENU_ID, ITEM_ID)
);

CREATE TABLE LOGIN
(
    LOGIN_ID            INT PRIMARY KEY NOT NULL,
    LOGIN_USERNAME      VARCHAR(50)     NOT NULL,
    LOGIN_PASSWORD_HASH CHAR(60)        NOT NULL
);

CREATE TABLE USER
(
    USER_ID         INT PRIMARY KEY NOT NULL,
    USER_FIRST_NAME VARCHAR(225)    NOT NULL,
    USER_LAST_NAME  VARCHAR(225)    NOT NULL,
    USER_EMAIL      VARCHAR(225)    NOT NULL,
    USER_PHONE      VARCHAR(225)    NOT NULL,
    USER_ADDRESS    VARCHAR(225)    NOT NULL,
    USER_CITY       VARCHAR(225)    NOT NULL,
    USER_STATE      VARCHAR(225)    NOT NULL,
    USER_ZIP        VARCHAR(225)    NOT NULL,
    LOGIN_ID        INT             NOT NULL UNIQUE REFERENCES LOGIN (LOGIN_ID)
);

CREATE TABLE OWNER
(
    OWNER_ID INT PRIMARY KEY NOT NULL,
    USER_ID  INT             NOT NULL UNIQUE REFERENCES USER (USER_ID)
);

CREATE TABLE EMPLOYEE
(
    EMPLOYEE_ID     INT PRIMARY KEY NOT NULL,
    EMPLOYEE_SALARY DOUBLE,
    USER_ID         INT             NOT NULL UNIQUE REFERENCES USER (USER_ID)
);

CREATE TABLE CUSTOMER
(
    CUSTOMER_ID INT PRIMARY KEY NOT NULL,
    USER_ID     INT             NOT NULL UNIQUE REFERENCES USER (USER_ID)
);

CREATE TABLE PURCHASE
(
    PURCHASE_ID        INT PRIMARY KEY NOT NULL,
    CUSTOMER_ID        INT             NOT NULL REFERENCES CUSTOMER (CUSTOMER_ID),
    PURCHASE_TIMESTAMP DATETIME        NOT NULL
);

CREATE TABLE PURCHASE_ITEM
(
    PURCHASE_ID          INT REFERENCES PURCHASE (PURCHASE_ID),
    ITEM_ID              INT REFERENCES ITEM (ITEM_ID),
    PURCHASE_ITEM_AMOUNT DOUBLE NOT NULL,
    PURCHASE_ITEM_TAX    DOUBLE NOT NULL
);
