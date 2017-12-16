CREATE TABLE follower (
  `id`        BIGINT UNSIGNED NOT NULL AUTO_INCREMENT
  COMMENT 'Unique identifier for Follower',
  `user_id` BIGINT UNSIGNED NOT NULL,
  `follower_id` BIGINT UNSIGNED NOT NULL,
  CONSTRAINT pk_account PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 COMMENT='Stores follower Data.';
