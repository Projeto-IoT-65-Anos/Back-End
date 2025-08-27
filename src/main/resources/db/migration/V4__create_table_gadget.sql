CREATE TABLE gadget (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    token VARCHAR(64) NOT NULL,
    name VARCHAR(50) NOT NULL,
    description TEXT,
    location VARCHAR(64) NOT NULL,
    status_id BIGINT NOT NULL DEFAULT 1,
    FOREIGN KEY (status_id) REFERENCES status(id)
)