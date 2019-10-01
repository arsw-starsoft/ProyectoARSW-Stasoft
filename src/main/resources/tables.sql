-- tables
-- Table: Car
CREATE TABLE Car (
    id int NOT NULL,
    plate varchar(10)  NOT NULL,
    model varchar(20)  NULL,
    seats int  NULL,
    Driver_Person_id varchar(80)  NOT NULL,
    Coordintate_latitude real   NULL,
    Coordintate_longitude real   NULL,
    CONSTRAINT Car_pk PRIMARY KEY (id)
);

-- Table: Coordintate
CREATE TABLE Coordintate (
    id serial NOT NULL,
    latitude real  NOT NULL,
    longitude real  NOT NULL,
    CONSTRAINT Coordintate_pk PRIMARY KEY (latitude,longitude)
);

-- Table: Driver
CREATE TABLE Driver (
    id int NOT NULL,
    email varchar(80)  NOT NULL,
    firstName varchar(50)  NULL,
    lastName varchar(50)  NULL,
    userName varchar(50)  NULL,
    cellPhone varchar(50)  NULL,
    password varchar(150)  NULL,
    CONSTRAINT Driver_pk PRIMARY KEY (id)
    
);

-- Table: Person
CREATE TABLE "User" (
    id int NOT NULL,
    email varchar(80)  NOT NULL,
    firstName varchar(50)  NULL,
    lastName varchar(50)  NULL,
    userName varchar(50)  NULL,
    cellPhone varchar(50)  NULL,
    password varchar(150)  NULL,
    CONSTRAINT User_pk PRIMARY KEY (id)
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
    FOREIGN KEY (Driver_Person_id)
    REFERENCES Driver (id)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;


