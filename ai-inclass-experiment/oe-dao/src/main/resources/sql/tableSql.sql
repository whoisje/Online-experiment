
create table user
(
  id       int auto_increment primary key,
  username varchar(30),
  password varchar(128),
  level    int(1)
)