-- tables
-- Table: Car
CREATE TABLE Car (
    plate varchar(10)  NOT NULL,
    model varchar(20)  NULL,
    seats int  NULL,
    Driver_Person_email varchar(80)  NOT NULL,
    Coordintate_latitude real   NULL,
    Coordintate_longitude real   NULL,
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
    email varchar(80)  NOT NULL,
    firstName varchar(50)  NULL,
    lastName varchar(50)  NULL,
    userName varchar(50)  NULL,
    cellPhone varchar(50)  NULL,
    password varchar(150)  NULL,
    CONSTRAINT Driver_pk PRIMARY KEY (email)
    
);

-- Table: Person
CREATE TABLE "User" (
    email varchar(80)  NOT NULL,
    firstName varchar(50)  NULL,
    lastName varchar(50)  NULL,
    userName varchar(50)  NULL,
    cellPhone varchar(50)  NULL,
    password varchar(150)  NULL,
    CONSTRAINT User_pk PRIMARY KEY (email)
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
    REFERENCES Driver (email)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;


