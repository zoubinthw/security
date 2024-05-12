create database security_demo;

create table `user`
(
    `id`       INT     NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `username` VARCHAR(50)  DEFAULT NULL,
    `password` VARCHAR(500) DEFAULT NULL,
    `enabled`  BOOLEAN NOT NULL
);

create unique index `user_name_un_index` on `user`(`username`);

# $2a$10$WkQ37j/AstVVj/sVw.G6qeJax0zkRn31IDwjBLLPsznNul7CE4IDi 123456
# $2y$10$qLV.NY7ZPwoRXUxzqqyZ3.EmN1l7BJT37p9Joj0LjdHWU1sPuyZsO 123456
# jim 123

insert into `user` (`username`, `password`, `enabled`)
values ('admin', '{bcrypt}$2y$10$qLV.NY7ZPwoRXUxzqqyZ3.EmN1l7BJT37p9Joj0LjdHWU1sPuyZsO', TRUE),
       ('Helen', '{bcrypt}$2y$10$qLV.NY7ZPwoRXUxzqqyZ3.EmN1l7BJT37p9Joj0LjdHWU1sPuyZsO', TRUE),
       ('Tom', '{bcrypt}$2y$10$qLV.NY7ZPwoRXUxzqqyZ3.EmN1l7BJT37p9Joj0LjdHWU1sPuyZsO', TRUE);