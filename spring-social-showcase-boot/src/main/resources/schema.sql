create table Account (id identity,
						username varchar unique,
						firstName varchar not null, 
						lastName varchar not null,
						primary key (id));

-- had to put this here because we've overridden the default config
create table UserConnection (userId varchar(255) not null,
	providerId varchar(255) not null,
	providerUserId varchar(255),
	rank int not null,
	displayName varchar(255),
	profileUrl varchar(512),
	imageUrl varchar(512),
	accessToken varchar(255) not null,
	secret varchar(255),
	refreshToken varchar(255),
	expireTime bigint,
	primary key (userId, providerId, providerUserId));
create unique index UserConnectionRank on UserConnection(userId, providerId, rank);