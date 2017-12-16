CREATE TABLE account (
  `id`        BIGINT UNSIGNED NOT NULL AUTO_INCREMENT
  COMMENT 'Unique identifier for Account',
  `username`  VARCHAR(40) UNIQUE,
  `password`  VARCHAR(60) NOT NULL,
  `firstname` VARCHAR(50) NOT NULL,
  `lastname`  VARCHAR(60) NOT NULL,
  CONSTRAINT pk_account PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 COMMENT='Stores Service and Component Mapping Data.';
