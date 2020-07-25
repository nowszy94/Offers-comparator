insert INTO users (user_id, login, password, email, token,active,points,last_update, role)
values (nextval('user_seq'),'jan','$2a$12$4kqOmPr2uOBrqVAETBb0BOPejzEHinA5itgsDZyI39yKrNPiPLwri','jk@test.pl','testtokentesttokentesttoken1',false, 0,current_date,'user');
insert INTO users (user_id, login, password, email, token,active,points,last_update, role)
values (nextval('user_seq'),'marek','$2a$12$.mmDJgNVKpog5tA9NgLYSOgEafJ7Bv83rtkK7BI/vh4tdyvObGmH2','mj@test.pl','testtokentesttokentesttoken2',true, 5,current_date ,'user');
insert INTO users (user_id, login, password, email, token,active,points,last_update, role)
values (nextval('user_seq'),'anna','$2a$12$W2EhU1dx/Q34PjtBtsjVq.UUVu1E4kyYmffd77u0pp2hivdSRt3Q6','am@test.pl','testtokentesttokentesttoken3',false, 0,current_date ,'admin');

commit ;