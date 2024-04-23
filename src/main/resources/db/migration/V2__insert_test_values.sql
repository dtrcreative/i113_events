SET search_path TO event;

insert into users(user_id)
values ('05811434-2cd4-4d0c-ac8d-9b5273a380dc');

insert into birthdays(user_id, first_name, last_name, date, description)
values (1,'Geralt', 'Rivia', current_timestamp, 'description');

insert into events(user_id, event_name, date, description)
values (1, 'Wedding', current_timestamp, 'description');




