CREATE TABLE topping
(
    id   INTEGER      NOT NULL AUTO_INCREMENT,
    name VARCHAR(128) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE topping_user
(
    id    INTEGER       NOT NULL AUTO_INCREMENT,
    email VARCHAR(128) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE user_topping_xref
(
    user_id    INTEGER NOT NULL,
    topping_id INTEGER NOT NULL
);