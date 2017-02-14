CREATE TABLE ACCOUNTS
(
  ACCOUNT_ID INTEGER  AUTO_INCREMENT PRIMARY KEY NOT NULL,
  CLIENT_ID INTEGER NOT NULL,
  DEPOSIT DOUBLE NOT NULL,
  ACCOUNT_TYPE VARCHAR(250) NOT NULL,
  OVERDRAFT INTEGER
);
CREATE TABLE CITIES
(
  CITY_ID INTEGER  AUTO_INCREMENT PRIMARY KEY NOT NULL,
  CITY_NAME VARCHAR(2147483647)
);
CREATE TABLE CLIENTS
(
  CLIENT_ID INTEGER  AUTO_INCREMENT PRIMARY KEY NOT NULL,
  NAME VARCHAR(2147483647) NOT NULL,
  EMAIL VARCHAR(2147483647) NOT NULL,
  PHONE VARCHAR(2147483647),
  CITY_ID INTEGER,
  GENDER VARCHAR(2147483647) DEFAULT '('MALE', 'FEMALE')' NOT NULL,
  ACCOUNT_ID INTEGER
);
ALTER TABLE ACCOUNTS ADD FOREIGN KEY (CLIENT_ID) REFERENCES CLIENTS (CLIENT_ID) ON DELETE CASCADE;
ALTER TABLE CLIENTS ADD FOREIGN KEY (CITY_ID) REFERENCES CITIES (CITY_ID);
CREATE UNIQUE INDEX "CLIENTS_NAME_uindex" ON CLIENTS (NAME);