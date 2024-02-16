create table role
(
    id varchar(36) not null,
    name varchar(255) not null,
    constraint role_pk primary key (id),
    constraint role_name_un unique (name)
);

insert into role (id, name)
values ('4a294ea9-086f-4c14-8c13-23caa87f6f66', 'ADMIN'),
       ('c03f9071-e544-42c0-857e-ffb7d2a2a3dd', 'USER');

