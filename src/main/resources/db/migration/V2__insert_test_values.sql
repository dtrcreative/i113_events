SET search_path TO event;

insert into users(user_id)
values ('1d5fcfd1-afc2-483d-8bd9-0c218e774bca');

insert into birthdays(user_id, first_name, last_name, date, description)
values (1,'Geralt', 'Rivia', current_timestamp, 'description');

insert into events(user_id, event_name, date, description)
values (1, 'Wedding', current_timestamp, 'description');




