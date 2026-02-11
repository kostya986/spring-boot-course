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

create schema dict;

create sequence dict.currency_id_seq;
create table dict.currency(
    id integer unique not null default nextval('ad.address_id_seq'),
    name varchar not null,
    primary key (id)
);

create schema bank;

create sequence bank.bank_book_id_seq;
create table bank.bank_book(
    id integer unique not null default nextval('bank.bank_book_id_seq'),
    user_id integer not null,
    number varchar not null,
    amount numeric not null,
    currency_id integer not null,
    primary key (id),
    foreign key (user_id) references ad.users(id),
    foreign key (currency_id) references dict.currency(id)
);

create sequence ad.group_id_seq;
create table ad.groups(
    id integer unique not null default nextval('ad.group_id_seq'),
    name varchar not null,
    primary key (id)
);

create table ad.users_groups(
    user_id integer not null,
    group_id integer not null,
    primary key (user_id, group_id),
    foreign key (user_id) references ad.users(id),
    foreign key (group_id) references ad.groups(id)
);