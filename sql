create database ofr;

use ofr;

CREATE TABLE User ( 
	userId              INT  auto_increment,
	userName            VARCHAR(40) not null,
	email               VARCHAR(40) not null unique,
	password            VARCHAR(20) not null,
	nationalId          VARCHAR(40) not null,
	presentAddress      VARCHAR(100) not null,
	permanentAddress    VARCHAR(100) not null,
	phoneNumber         VARCHAR(20) not null,
	role                VARCHAR(10) not null default "user",
	PRIMARY KEY (userId)
);

rename table User to UserInfo;


CREATE TABLE Issue ( 
	issueId             INT auto_increment,
	title               VARCHAR(20),
	helpFor             VARCHAR(40),
	description         VARCHAR(300),
	status              VARCHAR(10) default "pending",
	requiredAmount      INT,
	collectedAmount     INT default 0,
	createdDate	        DATE,
	userId              INT,
	PRIMARY KEY (issueId),
	FOREIGN KEY (userId) REFERENCES User(userId)
);

CREATE TABLE Donation (
    donationId          INT auto_increment,
    amount              INT,
    userId              INT,
    issueId             INT,
    issueTitle          VARCHAR(20),
    donationDate	    DATE,
    PRIMARY KEY (donationId),
    FOREIGN KEY (userId) REFERENCES User(userId),
    FOREIGN KEY (issueId) REFERENCES Issue(issueId)
);

Insert into User values(11,"z", "z", "z", "z", "z", "z", "z","admin");

