DROP TABLE IF EXISTS job_demand;
DROP TABLE IF EXISTS student;
DROP TABLE IF EXISTS speciality;


CREATE TABLE speciality (
    name VARCHAR(255) PRIMARY KEY,
    category VARCHAR(255) NOT NULL
);

CREATE TABLE student (
    email VARCHAR(255) PRIMARY KEY,
    firstname VARCHAR(255),
    lastname VARCHAR(255),
    speciality VARCHAR(255),
    studentNumber VARCHAR(255),
    lastLoginTimestamp timestamp,
    FOREIGN KEY (speciality) REFERENCES speciality(name)
);

CREATE TABLE job_demand (
    id SERIAL PRIMARY KEY,
    speciality VARCHAR(255),
    requestor VARCHAR(255),
    title VARCHAR(255),
    startDate VARCHAR(255),
    endDate VARCHAR(255),
    startDayTime VARCHAR(255),
    endDayTime VARCHAR(255),
    creationDatetime VARCHAR(255),
    expirationDays VARCHAR(255),
    creationTimestamp VARCHAR(255),
    FOREIGN KEY (speciality) REFERENCES speciality(name),
    FOREIGN KEY (requestor) REFERENCES student(email)
);