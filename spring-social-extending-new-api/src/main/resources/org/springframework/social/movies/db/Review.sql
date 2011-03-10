create table Review (id identity,
						movieTitle varchar not null,
						author varchar not null,
						text varchar not null,
						netflixId varchar not null, 
						submitted timestamp not null,
						primary key (id),
						foreign key (author) references Account(username));
