CREATE TABLE Spiller
(
    brukerID INTEGER AUTO_INCREMENT NOT NULL,
    brukernavn VARCHAR (255) NOT NULL,
    telefonnr VARCHAR (50) NOT NULL,
    land VARCHAR (255) NOT NULL,
    by VARCHAR (255) NOT NULL,
    terningKast INTEGER NOT NULL,
    PRIMARY KEY (brukerID)
);

CREATE TABLE LandOgBy
(
    id INTEGER AUTO_INCREMENT NOT NULL,
    land VARCHAR (255) NOT NULL,
    by VARCHAR (255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE Login
(
    brukernavn VARCHAR (30) NOT NULL,
    passord VARCHAR (255) NOT NULL,
    PRIMARY KEY (brukernavn)
);