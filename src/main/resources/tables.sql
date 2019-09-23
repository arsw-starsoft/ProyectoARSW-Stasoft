-- tables
-- Table: Car
CREATE TABLE Car (
    plate varchar(10)  NOT NULL,
    model varchar(20)  NULL,
    seats int  NULL,
    Driver_Person_email varchar(80)  NOT NULL,
    Coordintate_latitude real  NOT NULL,
    Coordintate_longitude real  NOT NULL,
    CONSTRAINT Car_pk PRIMARY KEY (plate)
);

-- Table: Coordintate
CREATE TABLE Coordintate (
    latitude real  NOT NULL,
    longitude real  NOT NULL,
    CONSTRAINT Coordintate_pk PRIMARY KEY (latitude,longitude)
);

-- Table: Driver
CREATE TABLE Driver (
    Person_email varchar(80)  NOT NULL,
    CONSTRAINT Driver_pk PRIMARY KEY (Person_email)
);

-- Table: Person
CREATE TABLE Person (
    email varchar(80)  NOT NULL,
    name varchar(50)  NULL,
    password varchar(150)  NULL,
    CONSTRAINT Person_pk PRIMARY KEY (email)
);

-- Table: User
CREATE TABLE "User" (
    Person_email varchar(80)  NOT NULL,
    CONSTRAINT User_pk PRIMARY KEY (Person_email)
);

-- foreign keys
-- Reference: Car_Coordintate (table: Car)
ALTER TABLE Car ADD CONSTRAINT Car_Coordintate
    FOREIGN KEY (Coordintate_latitude, Coordintate_longitude)
    REFERENCES Coordintate (latitude, longitude)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- Reference: Car_Driver (table: Car)
ALTER TABLE Car ADD CONSTRAINT Car_Driver
    FOREIGN KEY (Driver_Person_email)
    REFERENCES Driver (Person_email)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- Reference: Driver_Person (table: Driver)
ALTER TABLE Driver ADD CONSTRAINT Driver_Person
    FOREIGN KEY (Person_email)
    REFERENCES Person (email)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- Reference: User_Person (table: User)
ALTER TABLE "User" ADD CONSTRAINT User_Person
    FOREIGN KEY (Person_email)
    REFERENCES Person (email)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;
