USE restaurantbuddy;

create or replace table ITEM
(
    ITEM_ID          bigint auto_increment primary key,
    ITEM_COST        double       not null,
    ITEM_DESCRIPTION varchar(255) null,
    ITEM_NAME        varchar(255) not null
);

create or replace table LOCATION
(
    LOCATION_ID      bigint auto_increment primary key,
    LOCATION_ADDRESS varchar(255) not null,
    LOCATION_CITY    varchar(255) not null,
    LOCATION_NAME    varchar(255) not null,
    LOCATION_STATE   varchar(255) not null,
    LOCATION_ZIP     varchar(255) not null
);

create or replace table LOGIN
(
    LOGIN_ID            bigint auto_increment primary key,
    LOGIN_PASSWORD_HASH varchar(255) not null,
    LOGIN_USERNAME      varchar(255) not null,
    constraint UK_5ja62iqb6cfvgis013kgqq80s unique (LOGIN_USERNAME)
);

create or replace table MENU
(
    MENU_ID   bigint auto_increment primary key,
    MENU_NAME varchar(255) not null
);

create or replace table MENU_ITEM
(
    MENU_ID bigint not null,
    ITEM_ID bigint not null,
    constraint FKb1agtkkbyf4lmiai10w7o4t7o foreign key (ITEM_ID) references ITEM (ITEM_ID),
    constraint FKe5679m3bs0nmy540fh3t279gj foreign key (MENU_ID) references MENU (MENU_ID)
);

create or replace table USER
(
    USER_ID         bigint auto_increment primary key,
    USER_ADDRESS    varchar(255) not null,
    USER_CITY       varchar(255) not null,
    USER_EMAIL      varchar(255) not null,
    USER_FIRST_NAME varchar(255) not null,
    USER_LAST_NAME  varchar(255) not null,
    USER_PHONE      varchar(255) not null,
    USER_STATE      varchar(255) not null,
    USER_ZIP        varchar(255) not null,
    LOGIN_ID        bigint       not null,
    constraint UK_f1k8o23frox2w153vgjg4vhv3 unique (LOGIN_ID),
    constraint FK9s1n9o2mfgofjt7gfek0etdmn foreign key (LOGIN_ID) references LOGIN (LOGIN_ID)
);

create or replace table CUSTOMER
(
    CUSTOMER_ID bigint auto_increment primary key,
    USER_ID     bigint not null,
    constraint UK_pfmbgp1qy1etgdmnfbkivgcet unique (USER_ID),
    constraint FK94tdx19fgpjddng1qydgqdpq5 foreign key (USER_ID) references USER (USER_ID)
);

create or replace table EMPLOYEE
(
    EMPLOYEE_ID     bigint auto_increment primary key,
    EMPLOYEE_SALARY double not null,
    USER_ID         bigint not null,
    constraint UK_pahqxqqbjnk7q2759k4ogtusb unique (USER_ID),
    constraint FKqfyh4bav9e7p5dqbjklfqtswm foreign key (USER_ID) references USER (USER_ID)
);

create or replace table OWNER
(
    OWNER_ID bigint auto_increment primary key,
    USER_ID  bigint not null,
    constraint UK_fo83xr511ewwq0rek96b32dyh unique (USER_ID),
    constraint FK1cya32sm22xwhjw5kbebbgumb foreign key (USER_ID) references USER (USER_ID)
);

create or replace table PURCHASE
(
    PURCHASE_ID              bigint auto_increment primary key,
    PURCHASE_COMPLETION_TIME datetime(6) null,
    PURCHASE_PLACEMENT_TIME  datetime(6) not null,
    CUSTOMER_ID              bigint      not null,
    constraint FKb6440hx23obi21h0yjb2d8no7 foreign key (CUSTOMER_ID) references CUSTOMER (CUSTOMER_ID)
);

create or replace table PURCHASE_ITEM
(
    PURCHASE_ID bigint not null,
    ITEM_ID     bigint not null,
    constraint FK5j6q4nwb27e0f2rkbcyxx2nou foreign key (ITEM_ID) references ITEM (ITEM_ID),
    constraint FKqfxpkhrwp1vl503flqvn5brc9 foreign key (PURCHASE_ID) references PURCHASE (PURCHASE_ID)
);
