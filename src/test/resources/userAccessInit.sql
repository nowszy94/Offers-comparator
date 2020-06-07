insert INTO users (user_id, login, password, email, token,active,points,last_update, role)
values (1L,'Jan','Nowak','jk@test.pl','testtokentesttokentesttoken',false, 0,current_date,'user');
insert INTO users (user_id, login, password, email, token,active,points,last_update, role)
values (2L,'Marek','Jan','mj@test.pl','testtokentesttokentesttoken',true, 5,current_date ,'user');
insert INTO users (user_id, login, password, email, token,active,points,last_update, role)
values (3L,'Anna','Marek','am@test.pl','testtokentesttokentesttoken',false, 0,current_date ,'admin');

commit ;