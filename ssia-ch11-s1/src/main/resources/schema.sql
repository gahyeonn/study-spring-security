CREATE TABLE IF NOT EXISTS security11.user (
    username varchar(45) not null,
    password text null,
    primary key(username)
);

CREATE TABLE IF NOT EXISTS security11.otp (
    username varchar(45) not null,
    code varchar(45) null,
    primary key(username)
);