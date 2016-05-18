
# --- !Ups
CREATE SEQUENCE contact_id_seq;
CREATE TABLE contact(
  id integer NOT NULL DEFAULT nextval('contact_id_seq'),
  name varchar(255),
  emailAddress varchar(255),
  phoneNumber varchar(20),
  homeAddress varchar(500)
);

# --- !Downs
DROP TABLE contact;
DROP SEQUENCE contact_id_seq;
