create sequence hibernate_sequence start 1 increment 1;

create table comment (
  id int8 not null,
  filename varchar(255),
  tag varchar(255),
  text varchar(2048) not null,
  user_id int8,
  book_id int8,
  primary key (id)
);

create table user_role (
  user_id int8 not null,
  roles varchar(255)
);

create table usr (
  id int8 not null,
  activation_code varchar(255),
  active boolean not null,
  email varchar(255),
  password varchar(255) not null,
  username varchar(255) not null,
  primary key (id)
);

alter table
  if exists comment
add
  constraint comment_user_fk foreign key (user_id) references usr;

alter table
  if exists user_role
add
  constraint user_role_fk foreign key (user_id) references usr;

create table book (
     id int8 not null,
     filename varchar(255),
     caption varchar(255) not null,
     description varchar(2048) not null,
     author_id int8,
     content varchar(4096) not null,
     published boolean,
     primary key (id)
);

create table book_genre (
    book_id int8 not null,
    genres varchar(255)
);

create table author (
     id int8 not null,
     alias varchar(60) not null,
     biography varchar(2048) not null,
     user_id int8,
     primary key (id)
);

alter table
    if exists author
    add
        constraint author_user_fk foreign key (user_id) references usr;

alter table
    if exists book
    add
        constraint book_author_fk foreign key (author_id) references author;

alter table
    if exists book_genre
    add
        constraint book_genre_fk foreign key (book_id) references book;

create table user_subscriptions (
        channel_id int8 not null references usr,
        subscriber_id int8 not null references usr,
        primary key (channel_id, subscriber_id)
)
