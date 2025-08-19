CREATE TABLE access_levels (
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
    access_level VARCHAR(15) NOT NULL
);

CREATE TABLE users(
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    password_hash VARCHAR(64) NOT NULL,
    access_level_id BIGINT NOT NULL,
    FOREIGN KEY (access_level_id) REFERENCES access_levels(id)
);

CREATE TABLE status (
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
    status VARCHAR(15) NOT NULL
);

CREATE TABLE devices (
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(64) NOT NULL,
    description LONGTEXT,
    location VARCHAR(64) NOT NULL,
    status_id BIGINT NOT NULL,
    register_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    owner_id BIGINT NOT NULL,
    FOREIGN KEY (status_id) REFERENCES status(id),
    FOREIGN KEY (owner_id) REFERENCES users(id)
);

CREATE TABLE device_access (
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
    device_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    FOREIGN KEY (device_id) REFERENCES devices(id),
    FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE registers (
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
    device_id BIGINT NOT NULL,
    date DATETIME NOT NULL,
    temperature FLOAT,
    umidity FLOAT,
    external_temperature FLOAT,
    external_umidity FLOAT,
    FOREIGN KEY (device_id) REFERENCES devices(id)
);