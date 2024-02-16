create table user
(
    id varchar(36) not null,
    username varchar(255) not null,
    password varchar(255) not null,
    name varchar(255) not null,
    created_at timestamp default current_timestamp not null,
    updated_at timestamp null,
    role_id varchar(36) not null,
    constraint user_pk primary key (id),
    constraint user_username_un unique (username),
    constraint user_role_fk foreign key (role_id)
        references role (id)
);

insert into user (id, username, password, name, role_id)
values ('6e947d08-26c6-485c-95ae-0f28f2f45a58', 'admin', '$2a$10$bWZ0UIaQcxY.buyh66Sy2OyP7/tA5qkgAMs40JCznZPI8o0tJN33C', 'Administrator', '4a294ea9-086f-4c14-8c13-23caa87f6f66'),
       ('b1437291-9bbc-47c4-b0b7-331d3ce3f4bb', 'simple_user_a', '$2a$10$xv4W4uC9W0qEKpauSB1m3uI1JKASqtqRL1RaoeQp6I8QL3BGk7b8S', 'Simple User A', 'c03f9071-e544-42c0-857e-ffb7d2a2a3dd'),
       ('8f7e147a-dd08-4e47-b5c1-adc147e40c21', 'simple_user_b', '$2a$10$oZ26.lNlkJQbyZGrgD17Me9MUqpO3cy1Fg3/Ny66z23HSZgmlAv4C', 'Simple User B', 'c03f9071-e544-42c0-857e-ffb7d2a2a3dd'),
       ('a4dba7c9-b5ab-42c1-80dc-1883e4ac9313', 'simple_user_c', '$2a$10$J2PFgRWXvjTvfUzGzrBSBeHykipe/vkaKtU26ZeFgO3y55hw4sV1i', 'Simple User C', 'c03f9071-e544-42c0-857e-ffb7d2a2a3dd')



