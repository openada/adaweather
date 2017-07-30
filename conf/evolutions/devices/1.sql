# adaweather SCHEMA

# --- !Ups

CREATE TABLE devices (
  ID        BIGINT       NOT NULL AUTO_INCREMENT,
  NAME      VARCHAR(255) NOT NULL,
  LATITUDE  FLOAT        NOT NULL,
  LONGITUDE FLOAT        NOT NULL,
  ALTITUDE  FLOAT        NOT NULL,
  PRIMARY KEY (ID)
);

CREATE TABLE sensors (
  ID   BIGINT       NOT NULL AUTO_INCREMENT,
  NAME VARCHAR(255) NOT NULL,
  PRIMARY KEY (ID)
);

CREATE TABLE devices_sensors (
  DEVICE_ID BIGINT NOT NULL,
  SENSOR_ID BIGINT NOT NULL,
  FOREIGN KEY (DEVICE_ID) REFERENCES devices (ID),
  FOREIGN KEY (SENSOR_ID) REFERENCES sensors (ID)
);

INSERT INTO sensors VALUES (0, 'Luz');
INSERT INTO sensors VALUES (1, 'Humedaz y ole');

INSERT INTO devices VALUES (0, 'enca Dani', 51.461999, 0.125753, 0);
INSERT INTO devices VALUES (1, 'enca Adri', 51.462485, 0.126115, 0);
INSERT INTO devices VALUES (2, 'enca Angel', 40.733362, -3.946101, 0);
INSERT INTO devices VALUES (3, 'Test', 40.733362, 0, 0);

INSERT INTO devices_sensors VALUES (0, 0);
INSERT INTO devices_sensors VALUES (0, 1);
INSERT INTO devices_sensors VALUES (1, 0);
INSERT INTO devices_sensors VALUES (1, 1);
INSERT INTO devices_sensors VALUES (2, 0);
INSERT INTO devices_sensors VALUES (2, 1);
INSERT INTO devices_sensors VALUES (3, 0);
INSERT INTO devices_sensors VALUES (3, 1);

# --- !Downs

DROP TABLE devices_sensors;
DROP TABLE devices;
DROP TABLE sensors;
