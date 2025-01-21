
create table topicos(
    id bigint not null auto_increment,
    mensaje varchar(100) not null unique,
    curso varchar(100) not null,
    titulo varchar(100) not null unique,
    fecha datetime not null,

    primary key(id)

)