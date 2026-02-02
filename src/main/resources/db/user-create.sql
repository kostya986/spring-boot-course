create schema ad;

create sequence ad.address_id_seq;
create table ad.address(
    id integer unique not null default nextval('ad.address_id_seq'),
    country varchar,
    city varchar,
    street varchar,
    home varchar,
    primary key(id)
);

create sequence ad.user_id_seq;
create table ad.users(
    id integer unique not null default nextval('ad.user_id_seq'),
    name varchar not null,
    email varchar,
    address_id integer,
    primary key(id),
    foreign key (address_id) references ad.address(id)
);