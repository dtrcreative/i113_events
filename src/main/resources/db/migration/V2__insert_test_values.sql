SET search_path TO event;

insert into users(user_name)
values ('master');

insert into birthdays(user_id, first_name, last_name, date, description)
values (1,'Geralt', 'Rivia', current_timestamp, 'description');

insert into events(user_id, event_name, date, description)
values (1, 'Wedding', current_timestamp, 'description');




