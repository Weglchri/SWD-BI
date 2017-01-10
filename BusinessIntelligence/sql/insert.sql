INSERT INTO public.Location (Address, Country, ZIP, City) VALUES ('Alte Poststraße 122/1', 'Austria', 8020, 'Graz');

INSERT INTO public.Location (Address, Country, ZIP, City) VALUES ('Alte Poststraße 122/2', 'Austria', 8020, 'Graz');

INSERT INTO public.Location (Address, Country, ZIP, City) VALUES ('Melbourne Road 88', 'Australia', 9020, 'Sidney');

INSERT INTO public.User (User_Id, Name, Email, Password, Dtype) VALUES (nextval ('public.user_sequence'), 'Administrator', 'Admin@edu.fh-joanneum.at', '1234567', 'User');

INSERT INTO public.Company (Company_Name, FK_Address, Branch) VALUES ('Stahl Incorporation', (SELECT Address from public.location WHERE Address='Alte Poststraße 122/1'), 'Stahlbau');

INSERT INTO public.Company (Company_Name, FK_Address, Branch) VALUES ('Orange GmbH',  (SELECT Address from public.location WHERE Address='Alte Poststraße 122/2'), 'Bergbau');

INSERT INTO public.Company (Company_Name, FK_Address, Branch) VALUES ('Diamond Mine Coorporation GmbH',  (SELECT Address from public.location WHERE Address='Alte Poststraße 122/3'), 'Bergbau');

INSERT INTO public.User (User_Id, Name, Email, Password, Dtype) VALUES (nextval ('public.user_sequence'), 'Somebody', 'Somebody@edu.fh-joanneum.at', '1234567', 'Freelancer');

INSERT INTO public.Freelancer (User_Id, Profession, Availability, Fk_Address, Hourly_Wage, Education) VALUES ((SELECT user_id from public.User WHERE user_id = 2) , 'FH Joanneum', 'available', (SELECT Address from public.location WHERE Address='Alte Poststraße 122/1'), 13, 'Software Engineer Freelancer');

INSERT INTO public.User (User_Id, Name, Email, Password, Dtype) VALUES (nextval ('public.user_sequence'), 'Someone', 'Someone@edu.fh-joanneum.at', '1234567', 'Projectmanager');

INSERT INTO public.Projectmanager (User_Id, Involved_In, Fk_Company_Name, Function) VALUES ((SELECT user_id from public.User WHERE user_id = 3 ), 1, (SELECT Company_Name from public.Company WHERE Company_Name='Stahl Incorporation'), 'Personal');

INSERT INTO public.Project (Project_Id, Capital, Creation_Date, Task, Fk_Company_Name ) VALUES (nextval ('public.project_sequence'), 1000, current_timestamp, 'Turbinenherstellung',(SELECT company_name from public.Company WHERE company_name = 'Stahl Incorporation') );
