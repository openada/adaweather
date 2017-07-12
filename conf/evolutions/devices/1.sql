# adaweather SCHEMA

# --- !Ups

CREATE TABLE devices (
  id        BIGINT       NOT NULL AUTO_INCREMENT,
  name      VARCHAR(255) NOT NULL,
  latitude  FLOAT        NOT NULL,
  longitude FLOAT        NOT NULL,
  altitude  FLOAT        NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE sensors (
  id   BIGINT       NOT NULL AUTO_INCREMENT,
  type VARCHAR(255) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE devices_sensors (
  device_id BIGINT NOT NULL,
  sensor_id BIGINT NOT NULL,
  FOREIGN KEY (device_id) REFERENCES devices (id),
  FOREIGN KEY (sensor_id) REFERENCES sensors (id)
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
INSERT INTO devices_sensors VALUES (3, 1);

# --- !Downs

DROP TABLE devices_sensors;
DROP TABLE devices;
DROP TABLE sensors;
