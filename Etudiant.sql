-- Active: 1738839130594@@127.0.0.1@3306@etudiant_cafe
create  database etudiant_cafe;
use etudiant_cafe;
CREATE TABLE ETUDIANTS (ID INTEGER NOT NULL,
NOM VARCHAR(32) NOT NULL,
PRENOM VARCHAR(32) NOT NULL,
CHAMBRE INTEGER, PRIMARY KEY (ID));

CREATE TABLE CONSOS_CAFE (
NO_SEMAINE INTEGER NOT NULL,
ETUDIANT INTEGER NOT NULL,
NB_TASSES INT NOT NULL,
PRIMARY KEY (NO_SEMAINE, ETUDIANT),Foreign Key (ETUDIANT)  REFERENCES ETUDIANTS(ID));

insert into etudiants VALUES(1,"Ibrahima","Diallo",12);
insert into etudiants VALUES(2,"Salif","Doukouré",2);
insert into etudiants VALUES(3,"Mamadou","Sané",4);
insert into etudiants VALUES(4,"Ndiaye","LAMINE",5);

INSERT INTO consos_cafe VALUES(1,1,23);
INSERT INTO consos_cafe VALUES(2,2,3);
INSERT INTO consos_cafe VALUES(4,4,53);
select * from consos_cafe ;




# URL de la base de données  
db.url=jdbc:mysql://localhost:3306/votre_base_de_données  

# Nom d'utilisateur de la base de données  
db.user=your_username  

# Mot de passe de la base de données  
db.password=your_password  

# Driver de la base de données  
db.driver=com.mysql.cj.jdbc.Driver  




