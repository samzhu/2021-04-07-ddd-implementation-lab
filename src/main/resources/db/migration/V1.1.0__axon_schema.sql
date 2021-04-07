SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for Axon
-- ----------------------------
CREATE TABLE association_value_entry (
  id BIGINT NOT NULL, 
  association_key VARCHAR(255) NOT NULL, 
  association_value VARCHAR(255), 
  saga_id VARCHAR(255) NOT NULL, 
  saga_type VARCHAR(255), 
  PRIMARY KEY (id)
) engine = innodb;
CREATE TABLE hibernate_sequence (next_val BIGINT) engine = innodb;

INSERT INTO hibernate_sequence VALUES (1);

CREATE TABLE saga_entry (
  saga_id VARCHAR(255) NOT NULL, 
  revision VARCHAR(255), 
  saga_type VARCHAR(255), 
  serialized_saga LONGBLOB, 
  PRIMARY KEY (saga_id)
) engine = innodb;

CREATE TABLE token_entry (
  processor_name VARCHAR(255) NOT NULL, 
  segment INTEGER NOT NULL, 
  owner VARCHAR(255), 
  timestamp VARCHAR(255) NOT NULL, 
  token LONGBLOB, 
  token_type VARCHAR(255), 
  PRIMARY KEY (processor_name, segment)
) engine = innodb;

CREATE INDEX idxk45eqnxkgd8hpdn6xixn8sgft ON association_value_entry (
  saga_type, association_key, association_value
);

CREATE INDEX idxgv5k1v2mh6frxuy5c0hgbau94 ON association_value_entry (saga_id, saga_type);

SET FOREIGN_KEY_CHECKS=1;