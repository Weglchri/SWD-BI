-- Narrow rights for ALL USERS (PUBLIC) ------------------------------
REVOKE CREATE /* SCHEMA*/ ON DATABASE wegl FROM PUBLIC;
REVOKE CREATE /* OBJECTS*/ ON SCHEMA public FROM PUBLIC;

-- Reader Role -----------------------------------------------------------
CREATE ROLE BI_READER_ROLE;
GRANT SELECT ON ALL TABLES IN SCHEMA public TO BI_READER_ROLE;

-- Writer Role -------------------------------------------------------------
CREATE ROLE BI_WRITER_ROLE;
GRANT BI_READER_ROLE TO BI_WRITER_ROLE;
GRANT INSERT, UPDATE ON ALL TABLES IN SCHEMA public TO BI_WRITER_ROLE;
GRANT SELECT,UPDATE ON ALL SEQUENCES IN SCHEMA public TO BI_WRITER_ROLE;

-- Admin Role -----------------------------------------------------------
CREATE ROLE BI_ADMIN_ROLE;
GRANT BI_WRITER_ROLE TO BI_ADMIN_ROLE;
GRANT CREATE ON SCHEMA public TO BI_ADMIN_ROLE; -- schema privilge
GRANT DELETE ON ALL TABLES IN SCHEMA public TO BI_ADMIN_ROLE;

-- Users -----------------------------------------------------------
CREATE USER homer WITH PASSWORD 'donut';
CREATE USER larry WITH PASSWORD 'donut';
GRANT BI_READER_ROLE TO homer;
GRANT BI_READER_ROLE TO larry;
CREATE USER smithers WITH PASSWORD 'burns';
GRANT BI_WRITER_ROLE TO smithers;
CREATE USER burns WITH PASSWORD 'smithers';
GRANT BI_ADMIN_ROLE TO burns;