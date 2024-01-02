DROP TABLE IF EXISTS userr;

CREATE TABLE userr (
                      id int NOT NULL AUTO_INCREMENT,
                      first_name varchar(300),
                      last_name varchar(300) NOT NULL DEFAULT '',
                      email varchar(300) NOT NULL DEFAULT '',
                      date_birth DATE NOT NULL DEFAULT '',
                      age int ,
                      address varchar(300) NOT NULL DEFAULT 'Israel',
                      joined_date DATE NOT NULL DEFAULT CURDATE(),
                      is_registered boolean NOT NULL DEFAULT false,

                      PRIMARY KEY (id)
);
INSERT INTO userr (first_name, last_name, email,date_birth , age, address, joined_date,is_registered )
VALUES
    ('John', 'Doe', 'john.doe@example.com','2023-01-02', 30, '123 Main St', '2023-01-01',false)
 ;