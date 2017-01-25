
INSERT INTO public.Location (Address, Country, ZIP, City) VALUES ('Alte Poststraße 122/1', 'Austria', 8020, 'Graz');

INSERT INTO public.Location (Address, Country, ZIP, City) VALUES ('Lugner City 1', 'Austria', 1010, 'Wien');

INSERT INTO public.Location (Address, Country, ZIP, City) VALUES ('Melbourne Road 88', 'Australia', 9020, 'Sidney');

INSERT INTO public.Location (Address, Country, ZIP, City) VALUES ('Chapel Hill 12', 'UK', 1234, 'London');

INSERT INTO public.User (User_Id, Name, Email, Password, Dtype) VALUES (nextval ('public.user_sequence'), 'Administrator', 'Admin@edu.fh-joanneum.at', '1234567', 'User');

INSERT INTO public.Company (Company_Name, FK_Address, Branch) VALUES ('Stahl Incorporation', (SELECT Address from public.location WHERE Address='Alte Poststraße 122/1'), 'Stahlbau');

INSERT INTO public.Company (Company_Name, FK_Address, Branch) VALUES ('Orange GmbH',  (SELECT Address from public.location WHERE Address='Lugner City 1'), 'Bergbau');

INSERT INTO public.Company (Company_Name, FK_Address, Branch) VALUES ('Diamond Mine Coorporation GmbH',  (SELECT Address from public.location WHERE Address='Melbourne Road 88'), 'Bergbau');

INSERT INTO public.User (User_Id, Name, Email, Password, Dtype) VALUES (nextval ('public.user_sequence'), 'Somebody', 'Somebody@edu.fh-joanneum.at', '1234567', 'Freelancer');
INSERT INTO public.Freelancer (User_Id, Profession, Availability, Fk_Address, Hourly_Wage, Education) VALUES ((SELECT User_Id from public.User WHERE User_Id = 2) , 'Software Engineer Freelancer', 'available', (SELECT Address from public.location WHERE Address='Chapel Hill 12'), 13, 'FH Joanneum');

INSERT INTO public.User (User_Id, Name, Email, Password, Dtype) VALUES (nextval ('public.user_sequence'), 'Someone', 'Someone@edu.fh-joanneum.at', '1234567', 'Projectmanager');
INSERT INTO public.Projectmanager (User_Id, Involved_In, Fk_Company_Name, Function) VALUES ((SELECT User_Id from public.User WHERE User_Id = 3 ), 1, (SELECT Company_Name from public.Company WHERE Company_Name='Stahl Incorporation'), 'Projectmanager');

INSERT INTO public.User (User_Id, Name, Email, Password, Dtype) VALUES (nextval ('public.user_sequence'), 'Everyone', 'everyone@edu.fh-joanneum.at', '1234567', 'Projectmanager');
INSERT INTO public.Projectmanager (User_Id, Involved_In, Fk_Company_Name, Function) VALUES ((SELECT User_Id from public.User WHERE User_Id = 4 ), 1, (SELECT Company_Name from public.Company WHERE Company_Name='Diamond Mine Coorporation GmbH'), 'Personal');

INSERT INTO public.User (User_Id, Name, Email, Password, Dtype) VALUES (nextval ('public.user_sequence'), 'Anyone', 'anyone@edu.fh-joanneum.at', '1234567', 'Projectmanager');
INSERT INTO public.Projectmanager (User_Id, Involved_In, Fk_Company_Name, Function) VALUES ((SELECT User_Id from public.User WHERE User_Id = 5 ), 1, (SELECT Company_Name from public.Company WHERE Company_Name='Stahl Incorporation'), 'Personal');

INSERT INTO public.Project (Project_Id, Capital, Creation_Date, Task, Fk_Company_Name ) VALUES (nextval ('public.project_sequence'), 1000, current_timestamp, 'Turbinenherstellung',(SELECT company_name from public.Company WHERE company_name = 'Stahl Incorporation') );

INSERT INTO public.Project (Project_Id, Capital, Creation_Date, Task, Fk_Company_Name ) VALUES (nextval ('public.project_sequence'), 2000, current_timestamp, 'Turbinenherstellung',(SELECT company_name from public.Company WHERE company_name = 'Stahl Incorporation') );

INSERT INTO public.Project (Project_Id, Capital, Creation_Date, Task, Fk_Company_Name ) VALUES (nextval ('public.project_sequence'), 5000, current_timestamp, 'Turbinenherstellung',(SELECT company_name from public.Company WHERE company_name = 'Stahl Incorporation') );

INSERT INTO public.Offer (Offer_Id, Price, Creation_Date, Fk_Project_Id, Fk_User_Id ) VALUES (nextval ('public.offer_sequence'), 100, current_timestamp, (SELECT Project_Id from public.Project WHERE Project_Id = 1 ),(SELECT User_Id from public.User WHERE User_Id = 2) );

INSERT INTO public.Participation (Fk_User_Id, Fk_Project_Id) VALUES ((SELECT User_Id from public.User WHERE User_Id = 3),(SELECT Project_Id from public.Project WHERE Project_Id = 1));