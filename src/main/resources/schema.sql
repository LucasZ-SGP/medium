CREATE TABLE public.users (
	bio varchar(255) NOT NULL,
	email varchar(255) PRIMARY KEY,
	image varchar(255) NOT NULL,
	"token" varchar(255) NOT NULL,
	username varchar(255) NOT NULL
);