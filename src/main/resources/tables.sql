-- tables
CREATE TABLE Car (
    plate varchar(10)  NOT NULL,
    model varchar(20)  NULL,
    seats int  NULL,
    Driver_Person_email varchar(80)  NOT NULL,
    CONSTRAINT Car_pk PRIMARY KEY (plate)
);

CREATE TABLE Driver (
    Person_email varchar(80)  NOT NULL,
    CONSTRAINT Driver_pk PRIMARY KEY (Person_email)
);

CREATE TABLE Person (
    email varchar(80)  NOT NULL,
    name varchar(50)  NULL,
    password varchar(150)  NULL,
    CONSTRAINT Person_pk PRIMARY KEY (email)
);

CREATE TABLE "User" (
    Person_email varchar(80)  NOT NULL,
    CONSTRAINT User_pk PRIMARY KEY (Person_email)
);

-- foreign keys
ALTER TABLE Car ADD CONSTRAINT Car_Driver
    FOREIGN KEY (Driver_Person_email)
    REFERENCES Driver (Person_email)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

ALTER TABLE Driver ADD CONSTRAINT Driver_Person
    FOREIGN KEY (Person_email)
    REFERENCES Person (email)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

ALTER TABLE "User" ADD CONSTRAINT User_Person
    FOREIGN KEY (Person_email)
    REFERENCES Person (email)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;
