CREATE TABLE public.User(
User_Id SERIAL PRIMARY KEY,
Name VARCHAR NOT NULL,
Email VARCHAR NOT NULL,
Password VARCHAR NOT NULL);

CREATE TABLE public.Freelancer(
Profession VARCHAR NOT NULL,
Availability VARCHAR NOT NULL,
Fk_Adress VARCHAR,
Hourly_Wage INT,
Education VARCHAR
) INHERITS (public.User);

CREATE TABLE public.Projectmanager(
Involved_In INT NOT NULL,
Fk_Company_Name VARCHAR,
Function VARCHAR
) INHERITS (public.User);

CREATE TABLE public.Company(
Company_Name VARCHAR PRIMARY KEY,
Fk_Adress VARCHAR,
Branch VARCHAR);

CREATE TABLE public.Location(
Adress VARCHAR PRIMARY KEY,
Country VARCHAR NOT NULL,
ZIP INT NOT NULL,
City VARCHAR NOT NULL);

CREATE TABLE public.Offer(
Offer_Id SERIAL PRIMARY KEY,
Price INT NOT NULL,
Creation_Date DATE NOT NULL,
Fk_Project_Id INT,
Fk_User_Id INT);

CREATE TABLE public.Project(
Project_Id SERIAL PRIMARY KEY,
Capital INT,
Creation_Date DATE NOT NULL,
Task VARCHAR NOT NULL,
Fk_Adress VARCHAR);

CREATE TABLE public.Responsibility(
Fk_Projectmanager_Id INT,
Fk_Project_Id INT);

ALTER TABLE public.Responsibility
ADD CONSTRAINT Responsibility_User
FOREIGN KEY (Fk_Projectmanager_Id)
REFERENCES public.User(User_Id);

ALTER TABLE public.Responsibility
ADD CONSTRAINT Responsibility_Project
FOREIGN KEY (Fk_Project_Id)
REFERENCES public.Project(Project_Id);

ALTER TABLE public.Projectmanager
ADD CONSTRAINT Projectmanager_Company
FOREIGN KEY (Fk_Company_Name)
REFERENCES public.Company(Company_Name);

ALTER TABLE public.Offer
ADD CONSTRAINT Offer_Project
FOREIGN KEY (Fk_Project_Id)
REFERENCES Project(Project_Id);

ALTER TABLE public.Offer
ADD CONSTRAINT Offer_User
FOREIGN KEY (Fk_User_Id)
REFERENCES public.User(User_Id);

ALTER TABLE public.Freelancer
ADD CONSTRAINT Freelancer_Location
FOREIGN KEY (Fk_Adress)
REFERENCES public.Location(Adress);

ALTER TABLE public.Company
ADD CONSTRAINT Company_Location
FOREIGN KEY (Fk_Adress)
REFERENCES public.Location(Adress);