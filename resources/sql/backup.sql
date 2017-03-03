
CREATE SEQUENCE PUBLIC.SYSTEM_SEQUENCE_0ACB6E31_EC45_4118_B5DD_F916F2DA70BC START WITH 13;
CREATE SEQUENCE PUBLIC.SYSTEM_SEQUENCE_A689859F_EFE8_465A_9321_6FA90268F1C3 START WITH 17;
CREATE SEQUENCE PUBLIC.SYSTEM_SEQUENCE_CEA874A3_0B1C_4E5E_8D1A_60B75675F339 START WITH 8 ;
CREATE CACHED TABLE PUBLIC.ACCOUNTS(
  ACCOUNT_ID INT DEFAULT (NEXT VALUE FOR PUBLIC.SYSTEM_SEQUENCE_0ACB6E31_EC45_4118_B5DD_F916F2DA70BC) NOT NULL NULL_TO_DEFAULT SEQUENCE PUBLIC.SYSTEM_SEQUENCE_0ACB6E31_EC45_4118_B5DD_F916F2DA70BC,
  CLIENT_ID INT NOT NULL,
  DEPOSIT DOUBLE NOT NULL,
  ACCOUNT_TYPE VARCHAR(250) NOT NULL,
  OVERDRAFT INT
);
ALTER TABLE PUBLIC.ACCOUNTS ADD CONSTRAINT PUBLIC.CONSTRAINT_A PRIMARY KEY(ACCOUNT_ID);
-- 11 +/- SELECT COUNT(*) FROM PUBLIC.ACCOUNTS;
INSERT INTO PUBLIC.ACCOUNTS(ACCOUNT_ID, CLIENT_ID, DEPOSIT, ACCOUNT_TYPE, OVERDRAFT) VALUES
  (1, 1, 1000.0, 'SAVING', 0),
  (2, 1, 500.0, 'CHECKING', 100),
  (3, 3, 500.0, 'SAVING', NULL),
  (4, 3, 50000.0, 'SAVING', NULL),
  (5, 4, 1200.0, 'CHECKING', 250),
  (6, 4, -500.0, 'CHECKING', 600),
  (7, 5, 120.0, 'SAVING', NULL),
  (8, 5, 280.0, 'SAVING', NULL),
  (9, 6, 800.0, 'CHECKING', 500),
  (10, 7, 9000.0, 'SAVING', NULL),
  (11, 7, 150.0, 'SAVING', NULL);
CREATE CACHED TABLE PUBLIC.CITIES(
  CITY_ID INT DEFAULT (NEXT VALUE FOR PUBLIC.SYSTEM_SEQUENCE_CEA874A3_0B1C_4E5E_8D1A_60B75675F339) NOT NULL NULL_TO_DEFAULT SEQUENCE PUBLIC.SYSTEM_SEQUENCE_CEA874A3_0B1C_4E5E_8D1A_60B75675F339,
  CITY_NAME CHAR
);
ALTER TABLE PUBLIC.CITIES ADD CONSTRAINT PUBLIC.CONSTRAINT_7 PRIMARY KEY(CITY_ID);
-- 7 +/- SELECT COUNT(*) FROM PUBLIC.CITIES;
INSERT INTO PUBLIC.CITIES(CITY_ID, CITY_NAME) VALUES
  (1, 'Dnepr'),
  (2, 'Kiev'),
  (3, 'Lvov'),
  (4, 'Odessa'),
  (5, 'City'),
  (6, 'Springfield'),
  (7, 'SouthPark');
CREATE CACHED TABLE PUBLIC.CLIENTS(
  CLIENT_ID INT DEFAULT (NEXT VALUE FOR PUBLIC.SYSTEM_SEQUENCE_A689859F_EFE8_465A_9321_6FA90268F1C3) NOT NULL NULL_TO_DEFAULT SEQUENCE PUBLIC.SYSTEM_SEQUENCE_A689859F_EFE8_465A_9321_6FA90268F1C3,
  NAME CHAR NOT NULL,
  EMAIL CHAR NOT NULL,
  PHONE CHAR,
  CITY_ID INT,
  GENDER VARCHAR DEFAULT ('MALE', 'FEMALE') NOT NULL,
  ACCOUNT_ID INT
);
ALTER TABLE PUBLIC.CLIENTS ADD CONSTRAINT PUBLIC.CONSTRAINT_5 PRIMARY KEY(CLIENT_ID);
INSERT INTO PUBLIC.CLIENTS(CLIENT_ID, NAME, EMAIL, PHONE, CITY_ID, GENDER, ACCOUNT_ID) VALUES
  (1, 'Anton', 'anton@gmail.com', '+380661234567', 1, 'MALE', NULL),
  (3, 'Ivan', 'ivan@gmail.com', '+380663216549', 1, 'MALE', NULL),
  (4, 'Vladimir', 'vladimir@gmail.com', '+380668527419', 2, 'MALE', NULL),
  (5, 'Viktoria', 'viktoria@gmail.com', '+380661472583', 2, 'FEMALE', NULL),
  (6, 'Анна', 'anna@gmail.com', '+380661236547', 3, 'FEMALE', NULL),
  (7, 'Gleb', 'gleb@gmail.com', '+380661597532', 4, 'MALE', NULL),
  (11, 'ClientName', 'email@i.ia', '+380505050505', 5, 'MALE', NULL),
  (13, 'Name', 'y@u.ua', '+380661212456', 5, 'MALE', NULL),
  (14, 'NewClient', 'm@m.ua', '+380501414401', 5, 'MALE', NULL),
  (15, 'Homer', 'homer@simpsons.com', '+380501111111', 6, 'MALE', NULL);
CREATE UNIQUE INDEX PUBLIC."CLIENTS_NAME_uindex" ON PUBLIC.CLIENTS(NAME);
ALTER TABLE PUBLIC.ACCOUNTS ADD CONSTRAINT PUBLIC.ACCOUNTS_CLIENTS_CLIENT_ID_FK FOREIGN KEY(CLIENT_ID) REFERENCES PUBLIC.CLIENTS(CLIENT_ID) ON DELETE CASCADE NOCHECK;
ALTER TABLE PUBLIC.CLIENTS ADD CONSTRAINT PUBLIC.CLIENTS_CITIES_CITY_ID_FK FOREIGN KEY(CITY_ID) REFERENCES PUBLIC.CITIES(CITY_ID) NOCHECK;


