-- *********************************************
-- * SQL MySQL generation                      
-- *--------------------------------------------
-- * DB-MAIN version: 11.0.2              
-- * Generator date: Sep 14 2021              
-- * Generation date: Mon Aug 21 18:13:03 2023 
-- * LUN file: C:\Users\WINDOWS\Downloads\schema_modificato_tolto_banca.lun 
-- * Schema: Progettazione_Concettuale_nuova_prog_conc/1-1-1 
-- ********************************************* 


-- Database Section
-- ________________ 

create database Progettazione_Concettuale_nuova_prog_conc;
use Progettazione_Concettuale_nuova_prog_conc;


-- Tables Section
-- _____________ 

create table AZIENDA (
     Nome_Azienda varchar(100) not null,
     Indirizzo varchar(100) not null,
     Citta char(100) not null,
     Provincia char(100) not null,
     CAP int not null,
     Paese char(10) not null,
     Settore char(200) not null,
     Numero_di_Telefono int not null,
     Email varchar(100) not null,
     Partita_IVA varchar(11) not null,
     Conto_Azienda varchar(20) not null,
     constraint ID_AZIENDA_ID primary key (Nome_Azienda, Partita_IVA));

create table CLIENTE (
     Nome char(20) not null,
     Cognome char(20) not null,
     Data_Nascita date not null,
     Indirizzo_Civico varchar(40) not null,
     Citta char(20) not null,
     Provincia char(20) not null,
     CAP int not null,
     Paese char(20) not null,
     Email varchar(50) not null,
     Numero_di_Telefono int not null,
     Professione char(30) not null,
     Partita_IVA varchar(11) not null,
     constraint ID_CLIENTE_ID primary key (Partita_IVA));

create table COMMESSA (
     ID_Commessa varchar(10) not null,
     Descrizione char(200) not null,
     Data_Inizio date not null,
     Data_Fine date not null,
     Stato_Commessa char(20) not null,
     Documenti_Associati char(200) not null,
     Progetto char(50) not null,
     Nome_Azienda varchar(100) not null,
     Partita_IVA varchar(11) not null,
     Ric_Partita_IVA varchar(11) not null,
     constraint ID_COMMESSA_ID primary key (ID_Commessa));

create table CONSEGNA_CLIENTE (
     ID_Consegna varchar(10) not null,
     Data_Spedizione date not null,
     Data_Arrivo date not null,
     Indirizzo_spedizione char(100) not null,
     Citta_spedizione char(100) not null,
     CAP_spedizione int not null,
     Dipendente_Responsabile char(40) not null,
     Peso int not null,
     Documenti_Associati varchar(40) not null,
     Mezzo_Trasporto char(20) not null,
     constraint ID_CONSEGNA_CLIENTE_ID primary key (ID_Consegna));

create table contatta (
     Nome_Azienda varchar(100) not null,
     Partita_IVA varchar(11) not null,
     ID_Fornitore varchar(10) not null,
     constraint ID_contatta_ID primary key (ID_Fornitore, Nome_Azienda, Partita_IVA));

create table destinazione (
     ID_Discarica varchar(10) not null,
     ID_Trasporto varchar(10) not null,
     constraint ID_destinazione_ID primary key (ID_Discarica, ID_Trasporto));

create table DIPENDENTI (
     ID_Dipendente varchar(10) not null,
     Nome char(40) not null,
     Data_Nascita date not null,
     Indirizzo varchar(50) not null,
     Citta char(50) not null,
     Provincia char(50) not null,
     CAP int not null,
     Paese char(50) not null,
     Numero_di_Telefono int not null,
     Email varchar(30) not null,
     Ruolo char(40) not null,
     Dipartimento varchar(50) not null,
     Data_Assunzione date not null,
     Stipendio int not null,
     Certificazioni char(100) not null,
     constraint ID_DIPENDENTI_ID primary key (ID_Dipendente));

create table DISCARICA (
     ID_Discarica varchar(10) not null,
     Nome_Discarica char(100) not null,
     Indirizzo varchar(100) not null,
     Citta char(100) not null,
     Provincia char(50) not null,
     CAP int not null,
     Paese char(100) not null,
     Tipo_Rifiuti char(100) not null,
     Data_Apertura date not null,
     Data_Chiusura date not null,
     Gestore char(100) not null,
     constraint ID_DISCARICA_ID primary key (ID_Discarica));

create table FATTURA (
     Numero_Fattura int not null,
     Data_Emissione date not null,
     Descrizione char(200) not null,
     Totale int not null,
     Metodo_Pagamento varchar(20) not null,
     Stato_Pagamento char(20) not null,
     Data_Scadenza date not null,
     Indirizzo_Fatturazione varchar(40) not null,
     Nome_Azienda varchar(100) not null,
     Partita_IVA varchar(11) not null,
     constraint ID_FATTURA_ID primary key (Numero_Fattura));

create table FORNITORI_ASSOCIATI (
     ID_Fornitore varchar(10) not null,
     Nome_Fornitore varchar(100) not null,
     Indirizzo char(100) not null,
     Citta char(100) not null,
     Provincia char(100) not null,
     CAP int not null,
     Paese char(40) not null,
     Tipo_Fornitura varchar(60) not null,
     Numero_di_Telefono int not null,
     Email varchar(60) not null,
     Tempo_Fornitura bigint not null,
     constraint ID_FORNITORI_ASSOCIATI_ID primary key (ID_Fornitore));

create table MACCHINARI (
     ID_Macchina varchar(10) not null,
     Nome_Macchina char(40) not null,
     Tipo_Macchina varchar(30) not null,
     Descrizione char(200) not null,
     Marca varchar(100) not null,
     Modello varchar(30) not null,
     Capacita_Lavorativa int not null,
     constraint ID_MACCHINARI_ID primary key (ID_Macchina));

create table necessita (
     ID_Macchina varchar(10) not null,
     ID_Processo varchar(10) not null,
     constraint ID_necessita_ID primary key (ID_Macchina, ID_Processo));

create table pagata (
     Partita_IVA varchar(11) not null,
     Numero_Fattura int not null,
     constraint ID_pagata_ID primary key (Partita_IVA, Numero_Fattura));

create table PROCESSO_PRODUTTIVO (
     ID_Processo varchar(10) not null,
     Nome_processo char(100) not null,
     Data_inizio date not null,
     Data_fine date not null,
     Nome_Azienda varchar(100) not null,
     Partita_IVA varchar(11) not null,
     constraint ID_PROCESSO_PRODUTTIVO_ID primary key (ID_Processo));

create table PRODOTTI (
     ID_Prodotto varchar(10) not null,
     Nome_Prodotto char(60) not null,
     Descrizione char(100) not null,
     Categoria_Prodotto char(100) not null,
     Prezzo int not null,
     Disponibilita int not null,
     Documentazione char(100) not null,
     Licenze_Prodotto char(100) not null,
     ID_Fornitore varchar(10) not null,
     constraint ID_PRODOTTI_ID primary key (ID_Prodotto));

create table PRODOTTO_FINITO (
     ID_Prodotto varchar(10) not null,
     Quantita_Disponibile int not null,
     Descrizione char(200) not null,
     Documentazione char(50) not null,
     ID_Processo varchar(10) not null,
     ID_Consegna varchar(10) not null,
     constraint ID_PRODOTTO_FINITO_ID primary key (ID_Prodotto));

create table produce (
     Data date not null,
     ID_Macchina varchar(10) not null,
     ID_Dipendente varchar(10) not null,
     Ora varchar(40) not null,
     ID_Scarto varchar(10) not null,
     constraint ID_produce_ID primary key (ID_Scarto, Data, ID_Macchina, ID_Dipendente, Ora));

create table REVISIONE (
     ID_Revisione varchar(10) not null,
     Data_inizio_revisione date not null,
     Data_fine_revisione date not null,
     Responsabile char(100) not null,
     Risultati varchar(100) not null,
     Azioni_Correttive varchar(100) not null,
     Costo int not null,
     Stato char(50) not null,
     Note_aggiuntive char(200) not null,
     ID_Macchina varchar(10) not null,
     constraint ID_REVISIONE_ID primary key (ID_Revisione));

create table riceve (
     Partita_IVA varchar(11) not null,
     ID_Consegna varchar(10) not null,
     constraint ID_riceve_ID primary key (Partita_IVA, ID_Consegna));

create table ricicla (
     Data date not null,
     ID_Macchina varchar(10) not null,
     ID_Dipendente varchar(10) not null,
     Ora varchar(40) not null,
     ID_Scarto varchar(10) not null,
     constraint ID_ricicla_ID primary key (Data, ID_Macchina, ID_Dipendente, Ora, ID_Scarto));

create table SCARTI_NON_RIUTILIZZABILI (
     ID_Scarto varchar(10) not null,
     Descrizione char(100) not null,
     Data_Rilevamento date not null,
     Quantita int not null,
     Documentazione char(70) not null,
     Motivo_Scarto char(50) not null,
     constraint ID_SCARTI_NON_RIUTILIZZABILI_ID primary key (ID_Scarto));

create table SCARTI_RIUTILIZZABILI (
     ID_Scarto varchar(10) not null,
     Descrizione char(100) not null,
     Data_Rilevamento date not null,
     Quantita int not null,
     Documentazione char(70) not null,
     constraint ID_SCARTI_RIUTILIZZABILI_ID primary key (ID_Scarto));

create table trasporto (
     ID_Scarto varchar(10) not null,
     ID_Trasporto varchar(10) not null,
     constraint ID_trasporto_ID primary key (ID_Scarto, ID_Trasporto));

create table TRASPORTO_DISCARICA (
     ID_Trasporto varchar(10) not null,
     Data_Spedizione date not null,
     Data_Arrivo date not null,
     Mezzo_di_Trasporto char(100) not null,
     Dipendente_Responsabile char(100) not null,
     Quantita int not null,
     Peso int not null,
     Documenti_Associati char(100) not null,
     constraint ID_TRASPORTO_DISCARICA_ID primary key (ID_Trasporto));

create table usato (
     ID_Processo varchar(10) not null,
     ID_Prodotto varchar(10) not null,
     constraint ID_usato_ID primary key (ID_Prodotto, ID_Processo));

create table USO_MACCHINARIO (
     ID_Macchina varchar(10) not null,
     ID_Dipendente varchar(10) not null,
     Data date not null,
     Ora varchar(40) not null,
     constraint ID_USO_MACCHINARIO_ID primary key (Data, ID_Macchina, ID_Dipendente, Ora));


-- Constraints Section
-- ___________________ 

-- Not implemented
-- alter table AZIENDA add constraint ID_AZIENDA_CHK
--     check(exists(select * from PROCESSO_PRODUTTIVO
--                  where PROCESSO_PRODUTTIVO.Nome_Azienda = Nome_Azienda and PROCESSO_PRODUTTIVO.Partita_IVA = Partita_IVA)); 

-- Not implemented
-- alter table AZIENDA add constraint ID_AZIENDA_CHK
--     check(exists(select * from contatta
--                  where contatta.Nome_Azienda = Nome_Azienda and contatta.Partita_IVA = Partita_IVA)); 

-- Not implemented
-- alter table AZIENDA add constraint ID_AZIENDA_CHK
--     check(exists(select * from FATTURA
--                  where FATTURA.Nome_Azienda = Nome_Azienda and FATTURA.Partita_IVA = Partita_IVA)); 

-- Not implemented
-- alter table AZIENDA add constraint ID_AZIENDA_CHK
--     check(exists(select * from COMMESSA
--                  where COMMESSA.Nome_Azienda = Nome_Azienda and COMMESSA.Partita_IVA = Partita_IVA)); 

-- Not implemented
-- alter table CLIENTE add constraint ID_CLIENTE_CHK
--     check(exists(select * from pagata
--                  where pagata.Partita_IVA = Partita_IVA)); 

alter table COMMESSA add constraint FKsvolta_FK
     foreign key (Nome_Azienda, Partita_IVA)
     references AZIENDA (Nome_Azienda, Partita_IVA);

alter table COMMESSA add constraint FKrichiesta_FK
     foreign key (Ric_Partita_IVA)
     references CLIENTE (Partita_IVA);

-- Not implemented
-- alter table CONSEGNA_CLIENTE add constraint ID_CONSEGNA_CLIENTE_CHK
--     check(exists(select * from PRODOTTO_FINITO
--                  where PRODOTTO_FINITO.ID_Consegna = ID_Consegna)); 

-- Not implemented
-- alter table CONSEGNA_CLIENTE add constraint ID_CONSEGNA_CLIENTE_CHK
--     check(exists(select * from riceve
--                  where riceve.ID_Consegna = ID_Consegna)); 

alter table contatta add constraint FKcon_FOR
     foreign key (ID_Fornitore)
     references FORNITORI_ASSOCIATI (ID_Fornitore);

alter table contatta add constraint FKcon_AZI_FK
     foreign key (Nome_Azienda, Partita_IVA)
     references AZIENDA (Nome_Azienda, Partita_IVA);

alter table destinazione add constraint FKdes_TRA_FK
     foreign key (ID_Trasporto)
     references TRASPORTO_DISCARICA (ID_Trasporto);

alter table destinazione add constraint FKdes_DIS
     foreign key (ID_Discarica)
     references DISCARICA (ID_Discarica);

-- Not implemented
-- alter table DIPENDENTI add constraint ID_DIPENDENTI_CHK
--     check(exists(select * from USO_MACCHINARIO
--                  where USO_MACCHINARIO.ID_Dipendente = ID_Dipendente)); 

-- Not implemented
-- alter table DISCARICA add constraint ID_DISCARICA_CHK
--     check(exists(select * from destinazione
--                  where destinazione.ID_Discarica = ID_Discarica)); 

-- Not implemented
-- alter table FATTURA add constraint ID_FATTURA_CHK
--     check(exists(select * from pagata
--                  where pagata.Numero_Fattura = Numero_Fattura)); 

alter table FATTURA add constraint FKemessa_FK
     foreign key (Nome_Azienda, Partita_IVA)
     references AZIENDA (Nome_Azienda, Partita_IVA);

-- Not implemented
-- alter table FORNITORI_ASSOCIATI add constraint ID_FORNITORI_ASSOCIATI_CHK
--     check(exists(select * from contatta
--                  where contatta.ID_Fornitore = ID_Fornitore)); 

-- Not implemented
-- alter table FORNITORI_ASSOCIATI add constraint ID_FORNITORI_ASSOCIATI_CHK
--     check(exists(select * from PRODOTTI
--                  where PRODOTTI.ID_Fornitore = ID_Fornitore)); 

-- Not implemented
-- alter table MACCHINARI add constraint ID_MACCHINARI_CHK
--     check(exists(select * from REVISIONE
--                  where REVISIONE.ID_Macchina = ID_Macchina)); 

-- Not implemented
-- alter table MACCHINARI add constraint ID_MACCHINARI_CHK
--     check(exists(select * from USO_MACCHINARIO
--                  where USO_MACCHINARIO.ID_Macchina = ID_Macchina)); 

-- Not implemented
-- alter table MACCHINARI add constraint ID_MACCHINARI_CHK
--     check(exists(select * from necessita
--                  where necessita.ID_Macchina = ID_Macchina)); 

alter table necessita add constraint FKnec_PRO_FK
     foreign key (ID_Processo)
     references PROCESSO_PRODUTTIVO (ID_Processo);

alter table necessita add constraint FKnec_MAC
     foreign key (ID_Macchina)
     references MACCHINARI (ID_Macchina);

alter table pagata add constraint FKpag_FAT_FK
     foreign key (Numero_Fattura)
     references FATTURA (Numero_Fattura);

alter table pagata add constraint FKpag_CLI
     foreign key (Partita_IVA)
     references CLIENTE (Partita_IVA);

-- Not implemented
-- alter table PROCESSO_PRODUTTIVO add constraint ID_PROCESSO_PRODUTTIVO_CHK
--     check(exists(select * from necessita
--                  where necessita.ID_Processo = ID_Processo)); 

-- Not implemented
-- alter table PROCESSO_PRODUTTIVO add constraint ID_PROCESSO_PRODUTTIVO_CHK
--     check(exists(select * from usato
--                  where usato.ID_Processo = ID_Processo)); 

alter table PROCESSO_PRODUTTIVO add constraint FKattua_FK
     foreign key (Nome_Azienda, Partita_IVA)
     references AZIENDA (Nome_Azienda, Partita_IVA);

-- Not implemented
-- alter table PRODOTTI add constraint ID_PRODOTTI_CHK
--     check(exists(select * from usato
--                  where usato.ID_Prodotto = ID_Prodotto)); 

alter table PRODOTTI add constraint FKfornisce_FK
     foreign key (ID_Fornitore)
     references FORNITORI_ASSOCIATI (ID_Fornitore);

alter table PRODOTTO_FINITO add constraint FKgenera_FK
     foreign key (ID_Processo)
     references PROCESSO_PRODUTTIVO (ID_Processo);

alter table PRODOTTO_FINITO add constraint FKassegnata_FK
     foreign key (ID_Consegna)
     references CONSEGNA_CLIENTE (ID_Consegna);

alter table produce add constraint FKpro_SCA
     foreign key (ID_Scarto)
     references SCARTI_NON_RIUTILIZZABILI (ID_Scarto);

alter table produce add constraint FKpro_USO_FK
     foreign key (Data, ID_Macchina, ID_Dipendente, Ora)
     references USO_MACCHINARIO (Data, ID_Macchina, ID_Dipendente, Ora);

alter table REVISIONE add constraint FKcontrolla_FK
     foreign key (ID_Macchina)
     references MACCHINARI (ID_Macchina);

alter table riceve add constraint FKric_CON_FK
     foreign key (ID_Consegna)
     references CONSEGNA_CLIENTE (ID_Consegna);

alter table riceve add constraint FKric_CLI
     foreign key (Partita_IVA)
     references CLIENTE (Partita_IVA);

alter table ricicla add constraint FKric_SCA_FK
     foreign key (ID_Scarto)
     references SCARTI_RIUTILIZZABILI (ID_Scarto);

alter table ricicla add constraint FKric_USO
     foreign key (Data, ID_Macchina, ID_Dipendente, Ora)
     references USO_MACCHINARIO (Data, ID_Macchina, ID_Dipendente, Ora);

-- Not implemented
-- alter table SCARTI_NON_RIUTILIZZABILI add constraint ID_SCARTI_NON_RIUTILIZZABILI_CHK
--     check(exists(select * from trasporto
--                  where trasporto.ID_Scarto = ID_Scarto)); 

alter table trasporto add constraint FKtra_TRA_FK
     foreign key (ID_Trasporto)
     references TRASPORTO_DISCARICA (ID_Trasporto);

alter table trasporto add constraint FKtra_SCA
     foreign key (ID_Scarto)
     references SCARTI_NON_RIUTILIZZABILI (ID_Scarto);

-- Not implemented
-- alter table TRASPORTO_DISCARICA add constraint ID_TRASPORTO_DISCARICA_CHK
--     check(exists(select * from destinazione
--                  where destinazione.ID_Trasporto = ID_Trasporto)); 

-- Not implemented
-- alter table TRASPORTO_DISCARICA add constraint ID_TRASPORTO_DISCARICA_CHK
--     check(exists(select * from trasporto
--                  where trasporto.ID_Trasporto = ID_Trasporto)); 

alter table usato add constraint FKusa_PRO_1
     foreign key (ID_Prodotto)
     references PRODOTTI (ID_Prodotto);

alter table usato add constraint FKusa_PRO_FK
     foreign key (ID_Processo)
     references PROCESSO_PRODUTTIVO (ID_Processo);

-- Not implemented
-- alter table USO_MACCHINARIO add constraint ID_USO_MACCHINARIO_CHK
--     check(exists(select * from produce
--                  where produce.Data = Data and produce.ID_Macchina = ID_Macchina and produce.ID_Dipendente = ID_Dipendente and produce.Ora = Ora)); 

-- Not implemented
-- alter table USO_MACCHINARIO add constraint ID_USO_MACCHINARIO_CHK
--     check(exists(select * from ricicla
--                  where ricicla.Data = Data and ricicla.ID_Macchina = ID_Macchina and ricicla.ID_Dipendente = ID_Dipendente and ricicla.Ora = Ora)); 

alter table USO_MACCHINARIO add constraint FKoccupato_FK
     foreign key (ID_Dipendente)
     references DIPENDENTI (ID_Dipendente);

alter table USO_MACCHINARIO add constraint FKesegue_FK
     foreign key (ID_Macchina)
     references MACCHINARI (ID_Macchina);


-- Index Section
-- _____________ 

create unique index ID_AZIENDA_IND
     on AZIENDA (Nome_Azienda, Partita_IVA);

create unique index ID_CLIENTE_IND
     on CLIENTE (Partita_IVA);

create unique index ID_COMMESSA_IND
     on COMMESSA (ID_Commessa);

create index FKsvolta_IND
     on COMMESSA (Nome_Azienda, Partita_IVA);

create index FKrichiesta_IND
     on COMMESSA (Ric_Partita_IVA);

create unique index ID_CONSEGNA_CLIENTE_IND
     on CONSEGNA_CLIENTE (ID_Consegna);

create unique index ID_contatta_IND
     on contatta (ID_Fornitore, Nome_Azienda, Partita_IVA);

create index FKcon_AZI_IND
     on contatta (Nome_Azienda, Partita_IVA);

create unique index ID_destinazione_IND
     on destinazione (ID_Discarica, ID_Trasporto);

create index FKdes_TRA_IND
     on destinazione (ID_Trasporto);

create unique index ID_DIPENDENTI_IND
     on DIPENDENTI (ID_Dipendente);

create unique index ID_DISCARICA_IND
     on DISCARICA (ID_Discarica);

create unique index ID_FATTURA_IND
     on FATTURA (Numero_Fattura);

create index FKemessa_IND
     on FATTURA (Nome_Azienda, Partita_IVA);

create unique index ID_FORNITORI_ASSOCIATI_IND
     on FORNITORI_ASSOCIATI (ID_Fornitore);

create unique index ID_MACCHINARI_IND
     on MACCHINARI (ID_Macchina);

create unique index ID_necessita_IND
     on necessita (ID_Macchina, ID_Processo);

create index FKnec_PRO_IND
     on necessita (ID_Processo);

create unique index ID_pagata_IND
     on pagata (Partita_IVA, Numero_Fattura);

create index FKpag_FAT_IND
     on pagata (Numero_Fattura);

create unique index ID_PROCESSO_PRODUTTIVO_IND
     on PROCESSO_PRODUTTIVO (ID_Processo);

create index FKattua_IND
     on PROCESSO_PRODUTTIVO (Nome_Azienda, Partita_IVA);

create unique index ID_PRODOTTI_IND
     on PRODOTTI (ID_Prodotto);

create index FKfornisce_IND
     on PRODOTTI (ID_Fornitore);

create unique index ID_PRODOTTO_FINITO_IND
     on PRODOTTO_FINITO (ID_Prodotto);

create index FKgenera_IND
     on PRODOTTO_FINITO (ID_Processo);

create index FKassegnata_IND
     on PRODOTTO_FINITO (ID_Consegna);

create unique index ID_produce_IND
     on produce (ID_Scarto, Data, ID_Macchina, ID_Dipendente, Ora);

create index FKpro_USO_IND
     on produce (Data, ID_Macchina, ID_Dipendente, Ora);

create unique index ID_REVISIONE_IND
     on REVISIONE (ID_Revisione);

create index FKcontrolla_IND
     on REVISIONE (ID_Macchina);

create unique index ID_riceve_IND
     on riceve (Partita_IVA, ID_Consegna);

create index FKric_CON_IND
     on riceve (ID_Consegna);

create unique index ID_ricicla_IND
     on ricicla (Data, ID_Macchina, ID_Dipendente, Ora, ID_Scarto);

create index FKric_SCA_IND
     on ricicla (ID_Scarto);

create unique index ID_SCARTI_NON_RIUTILIZZABILI_IND
     on SCARTI_NON_RIUTILIZZABILI (ID_Scarto);

create unique index ID_SCARTI_RIUTILIZZABILI_IND
     on SCARTI_RIUTILIZZABILI (ID_Scarto);

create unique index ID_trasporto_IND
     on trasporto (ID_Scarto, ID_Trasporto);

create index FKtra_TRA_IND
     on trasporto (ID_Trasporto);

create unique index ID_TRASPORTO_DISCARICA_IND
     on TRASPORTO_DISCARICA (ID_Trasporto);

create unique index ID_usato_IND
     on usato (ID_Prodotto, ID_Processo);

create index FKusa_PRO_IND
     on usato (ID_Processo);

create unique index ID_USO_MACCHINARIO_IND
     on USO_MACCHINARIO (Data, ID_Macchina, ID_Dipendente, Ora);

create index FKoccupato_IND
     on USO_MACCHINARIO (ID_Dipendente);

create index FKesegue_IND
     on USO_MACCHINARIO (ID_Macchina);


INSERT INTO AZIENDA (Nome_Azienda, Indirizzo, Citta, Provincia, CAP, Paese, Settore, Numero_di_Telefono, Email, Partita_IVA, Conto_Azienda)VALUES ('MA_Technology', 'Via Giuseppe Garibaldi 22', 'Ostiglia', 'Mantova', 43567, 'Italia', 'IT', 32456777, 'matechnology@gmail.com', 'matech123', 'Aziendale');
INSERT INTO AZIENDA (Nome_Azienda, Indirizzo, Citta, Provincia, CAP, Paese, Settore, Numero_di_Telefono, Email, Partita_IVA, Conto_Azienda)VALUES ('Apple', 'Silicon Valley', 'Silicon Valley', 'Assente', 00000 , 'America', 'IT', 35678899 , 'apple@apple.com', 'apple111', 'Aziendale');
INSERT INTO AZIENDA (Nome_Azienda, Indirizzo, Citta, Provincia, CAP, Paese, Settore, Numero_di_Telefono, Email, Partita_IVA, Conto_Azienda)VALUES ('Samsung', 'Via Milano 99', 'Milano', 'Milano', 49019 , 'Italia', 'IT', 3990113 , 'samsung@outlook.it', 'samsung234', 'Aziendale');
INSERT INTO AZIENDA (Nome_Azienda, Indirizzo, Citta, Provincia, CAP, Paese, Settore, Numero_di_Telefono, Email, Partita_IVA, Conto_Azienda)VALUES ('Mizuno', 'Via Salvatore Quasimodo', 'Cesena', 'Forli-Cesena',  46035, 'Italia', 'Sport', 35567901 , 'mizuno@gmail.com', 'mizuno999', 'Aziendale');

INSERT INTO CLIENTE (Nome, Cognome, Data_Nascita, Indirizzo_Civico, Citta, Provincia, CAP, Paese, Email, Numero_di_Telefono, Professione, Partita_IVA)VALUES ('Mario', 'Rossi', '1990-05-15', 'Via Roma 123', 'Milano', 'MI', 20100, 'Italia', 'mario.rossi@email.com', 12567890, 'Ingegnere', '12345678901');

INSERT INTO CLIENTE (Nome, Cognome, Data_Nascita, Indirizzo_Civico, Citta, Provincia, CAP, Paese, Email, Numero_di_Telefono, Professione, Partita_IVA)VALUES ('Anna', 'Bianchi', '1985-08-20', 'Via Verdi 456', 'Roma', 'RM', 00100, 'Italia', 'anna.bianchi@email.com', 98763210, 'Avvocato', '23456789012');

INSERT INTO CLIENTE (Nome, Cognome, Data_Nascita, Indirizzo_Civico, Citta, Provincia, CAP, Paese, Email, Numero_di_Telefono, Professione, Partita_IVA)VALUES ('Luigi', 'Verdi', '1982-03-10', 'Via Napoli 789', 'Napoli', 'NA', 80100, 'Italia', 'luigi.verdi@email.com', 55555555, 'Medico', '34567890123');

INSERT INTO CLIENTE (Nome, Cognome, Data_Nascita, Indirizzo_Civico, Citta, Provincia, CAP, Paese, Email, Numero_di_Telefono, Professione, Partita_IVA)VALUES ('Giovanna', 'Ferrari', '1995-12-05', 'Via Firenze 101', 'Firenze', 'FI', 50100, 'Italia', 'giovanna.ferrari@email.com', 99999999, 'Architetto', '45678901234');

INSERT INTO CLIENTE (Nome, Cognome, Data_Nascita, Indirizzo_Civico, Citta, Provincia, CAP, Paese, Email, Numero_di_Telefono, Professione, Partita_IVA)VALUES ('Marco', 'Russo', '1988-07-25', 'Via Venezia 222', 'Venezia', 'VE', 30100, 'Italia', 'marco.russo@email.com', 77777777, 'Insegnante', '56789012345');


INSERT INTO COMMESSA (ID_Commessa, Descrizione, Data_Inizio, Data_Fine, Stato_Commessa, Documenti_Associati, Progetto, Nome_Azienda, Partita_IVA, Ric_Partita_IVA)VALUES 
('C001', 'Descrizione Commessa 1', '2023-09-01', '2023-09-15', 'In Corso', 'Doc001', 'Progetto 1', 'MA_Technology', 'matech123', '12345678901');

INSERT INTO COMMESSA (ID_Commessa, Descrizione, Data_Inizio, Data_Fine, Stato_Commessa, Documenti_Associati, Progetto, Nome_Azienda, Partita_IVA, Ric_Partita_IVA)VALUES 
('C002', 'Descrizione Commessa 2', '2023-08-25', '2023-09-10', 'In Corso', 'Doc002', 'Progetto 2', 'MA_Technology', 'matech123', '12345678901');

INSERT INTO COMMESSA (ID_Commessa, Descrizione, Data_Inizio, Data_Fine, Stato_Commessa, Documenti_Associati, Progetto, Nome_Azienda, Partita_IVA, Ric_Partita_IVA)VALUES 
('C003', 'Descrizione Commessa 3', '2023-09-03', '2023-09-18', 'In Corso', 'Doc003', 'Progetto 3', 'Apple', 'apple111', '34567890123');

INSERT INTO COMMESSA (ID_Commessa, Descrizione, Data_Inizio, Data_Fine, Stato_Commessa, Documenti_Associati, Progetto, Nome_Azienda, Partita_IVA, Ric_Partita_IVA)VALUES 
('C004', 'Descrizione Commessa 4', '2023-08-30', '2023-09-12', 'In Corso', 'Doc004', 'Progetto 4', 'Samsung', 'samsung234', '45678901234');

INSERT INTO COMMESSA (ID_Commessa, Descrizione, Data_Inizio, Data_Fine, Stato_Commessa, Documenti_Associati, Progetto, Nome_Azienda, Partita_IVA, Ric_Partita_IVA)VALUES 
('C005', 'Descrizione Commessa 5', '2023-09-05', '2023-09-20', 'In Corso', 'Doc005', 'Progetto 5', 'Mizuno', 'mizuno999', '56789012345');


INSERT INTO CONSEGNA_CLIENTE (ID_Consegna, Data_Spedizione, Data_Arrivo, Indirizzo_spedizione, Citta_spedizione, CAP_spedizione, Dipendente_Responsabile, Peso, Documenti_Associati, Mezzo_Trasporto)VALUES 
('CON001', '2023-09-01', '2023-09-10', 'Via Roma 123', 'Milano', 20100, 'Mario Rossi', 500, 'Doc123', 'Autotrasporto');

INSERT INTO CONSEGNA_CLIENTE (ID_Consegna, Data_Spedizione, Data_Arrivo, Indirizzo_spedizione, Citta_spedizione, CAP_spedizione, Dipendente_Responsabile, Peso, Documenti_Associati, Mezzo_Trasporto)VALUES 
('CON002', '2023-08-28', '2023-09-05', 'Via Verdi 456', 'Roma', 00100, 'Anna Bianchi', 700, 'Doc456', 'Aereo');

INSERT INTO CONSEGNA_CLIENTE (ID_Consegna, Data_Spedizione, Data_Arrivo, Indirizzo_spedizione, Citta_spedizione, CAP_spedizione, Dipendente_Responsabile, Peso, Documenti_Associati, Mezzo_Trasporto)VALUES 
('CON003', '2023-09-03', '2023-09-12', 'Via Napoli 789', 'Napoli', 80100, 'Luigi Verdi', 300, 'Doc789', 'Ferroviario');

INSERT INTO CONSEGNA_CLIENTE (ID_Consegna, Data_Spedizione, Data_Arrivo, Indirizzo_spedizione, Citta_spedizione, CAP_spedizione, Dipendente_Responsabile, Peso, Documenti_Associati, Mezzo_Trasporto)VALUES 
('CON004', '2023-09-02', '2023-09-09', 'Via Firenze 101', 'Firenze', 50100, 'Giovanna Ferrari', 600, 'Doc101', 'Stradale');

INSERT INTO CONSEGNA_CLIENTE (ID_Consegna, Data_Spedizione, Data_Arrivo, Indirizzo_spedizione, Citta_spedizione, CAP_spedizione, Dipendente_Responsabile, Peso, Documenti_Associati, Mezzo_Trasporto)VALUES 
('CON005', '2023-08-30', '2023-09-08', 'Via Venezia 222', 'Venezia', 30100, 'Marco Russo', 400, 'Doc222', 'Marittimo');


INSERT INTO DIPENDENTI (ID_Dipendente, Nome, Data_Nascita, Indirizzo, Citta, Provincia, CAP, Paese, Numero_di_Telefono, Email, Ruolo, Dipartimento, Data_Assunzione, Stipendio, Certificazioni) VALUES 
('D001', 'Mario Rossi', '1990-05-15', 'Via Roma 123', 'Milano', 'MI', 20100, 'Italia', 12345890, 'mario.rossi@email.com', 'Manager', 'Amministrazione', '2022-01-10', 60000, 'Cert1, Cert2');

INSERT INTO DIPENDENTI (ID_Dipendente, Nome, Data_Nascita, Indirizzo, Citta, Provincia, CAP, Paese, Numero_di_Telefono, Email, Ruolo, Dipartimento, Data_Assunzione, Stipendio, Certificazioni) VALUES 
('D002', 'Anna Bianchi', '1985-08-20', 'Via Verdi 456', 'Roma', 'RM', 00100, 'Italia', 98765210, 'anna.bianchi@email.com', 'Analista', 'Tecnologia', '2022-02-15', 50000, 'Cert3');

INSERT INTO DIPENDENTI (ID_Dipendente, Nome, Data_Nascita, Indirizzo, Citta, Provincia, CAP, Paese, Numero_di_Telefono, Email, Ruolo, Dipartimento, Data_Assunzione, Stipendio, Certificazioni) VALUES 
('D003', 'Luigi Verdi', '1982-03-10', 'Via Napoli 789', 'Napoli', 'NA', 80100, 'Italia', 55555555, 'luigi.verdi@email.com', 'Sviluppatore', 'Tecnologia', '2022-03-20', 55000, 'Cert4');

INSERT INTO DIPENDENTI (ID_Dipendente, Nome, Data_Nascita, Indirizzo, Citta, Provincia, CAP, Paese, Numero_di_Telefono, Email, Ruolo, Dipartimento, Data_Assunzione, Stipendio, Certificazioni) VALUES 
('D004', 'Giovanna Ferrari', '1995-12-05', 'Via Firenze 101', 'Firenze', 'FI', 50100, 'Italia', 99999999, 'giovanna.ferrari@email.com', 'Project Manager', 'Progetti', '2022-04-25', 62000, 'Cert5, Cert6');

INSERT INTO DIPENDENTI (ID_Dipendente, Nome, Data_Nascita, Indirizzo, Citta, Provincia, CAP, Paese, Numero_di_Telefono, Email, Ruolo, Dipartimento, Data_Assunzione, Stipendio, Certificazioni) VALUES 
('D005', 'Marco Russo', '1988-07-25', 'Via Venezia 222', 'Venezia', 'VE', 30100, 'Italia', 77777777, 'marco.russo@email.com', 'Amministratore', 'Tecnologia', '2022-05-12', 58000, 'Cert7, Cert8');


INSERT INTO DISCARICA (ID_Discarica, Nome_Discarica, Indirizzo, Citta, Provincia, CAP, Paese, Tipo_Rifiuti, Data_Apertura, Data_Chiusura, Gestore) VALUES 
('D001', 'Discarica A', 'Via Roma 123', 'Milano', 'MI', 20100, 'Italia', 'Rifiuti Solidi', '2023-01-15', '2023-12-31', 'Gestore A');

INSERT INTO DISCARICA (ID_Discarica, Nome_Discarica, Indirizzo, Citta, Provincia, CAP, Paese, Tipo_Rifiuti, Data_Apertura, Data_Chiusura, Gestore) VALUES 
('D002', 'Discarica B', 'Via Verdi 456', 'Roma', 'RM', 00100, 'Italia', 'Rifiuti Liquidi', '2023-02-20', '2023-12-31', 'Gestore B');

INSERT INTO DISCARICA (ID_Discarica, Nome_Discarica, Indirizzo, Citta, Provincia, CAP, Paese, Tipo_Rifiuti, Data_Apertura, Data_Chiusura, Gestore) VALUES 
('D003', 'Discarica C', 'Via Napoli 789', 'Napoli', 'NA', 80100, 'Italia', 'Rifiuti Elettronici', '2023-03-10', '2023-12-31', 'Gestore C');

INSERT INTO DISCARICA (ID_Discarica, Nome_Discarica, Indirizzo, Citta, Provincia, CAP, Paese, Tipo_Rifiuti, Data_Apertura, Data_Chiusura, Gestore) VALUES 
('D004', 'Discarica D', 'Via Firenze 101', 'Firenze', 'FI', 50100, 'Italia', 'Rifiuti Organici', '2023-04-25', '2023-12-31', 'Gestore D');

INSERT INTO DISCARICA (ID_Discarica, Nome_Discarica, Indirizzo, Citta, Provincia, CAP, Paese, Tipo_Rifiuti, Data_Apertura, Data_Chiusura, Gestore) VALUES 
('D005', 'Discarica E', 'Via Venezia 222', 'Venezia', 'VE', 30100, 'Italia', 'Rifiuti Plastici', '2023-05-12', '2023-12-31', 'Gestore E');


INSERT INTO FATTURA (Numero_Fattura, Data_Emissione, Descrizione, Totale, Metodo_Pagamento, Stato_Pagamento, Data_Scadenza, Indirizzo_Fatturazione, Nome_Azienda, Partita_IVA) VALUES 
(1, '2023-09-01', 'Fattura per servizi di consulenza', 5000, 'Carta di Credito', 'Non Pagata', '2023-09-15', 'Via Roma 123', 'Apple', 'apple111');

INSERT INTO FATTURA (Numero_Fattura, Data_Emissione, Descrizione, Totale, Metodo_Pagamento, Stato_Pagamento, Data_Scadenza, Indirizzo_Fatturazione, Nome_Azienda, Partita_IVA) VALUES 
(2, '2023-09-05', 'Fattura per servizi di manutenzione', 7000, 'Bonifico Bancario', 'Pagata', '2023-09-20', 'Via Verdi 456', 'Apple', 'apple111');

INSERT INTO FATTURA (Numero_Fattura, Data_Emissione, Descrizione, Totale, Metodo_Pagamento, Stato_Pagamento, Data_Scadenza, Indirizzo_Fatturazione, Nome_Azienda, Partita_IVA) VALUES 
(3, '2023-09-10', 'Fattura per forniture materiali', 3000, 'Assegno', 'Non Pagata', '2023-09-25', 'Via Napoli 789', 'Mizuno', 'mizuno999');

INSERT INTO FATTURA (Numero_Fattura, Data_Emissione, Descrizione, Totale, Metodo_Pagamento, Stato_Pagamento, Data_Scadenza, Indirizzo_Fatturazione, Nome_Azienda, Partita_IVA) VALUES 
(4, '2023-09-15', 'Fattura per servizi di trasporto', 6000, 'PayPal', 'Pagata', '2023-09-30', 'Via Firenze 101', 'Samsung', 'samsung234');

INSERT INTO FATTURA (Numero_Fattura, Data_Emissione, Descrizione, Totale, Metodo_Pagamento, Stato_Pagamento, Data_Scadenza, Indirizzo_Fatturazione, Nome_Azienda, Partita_IVA) VALUES 
(5, '2023-09-20', 'Fattura per servizi di marketing', 8000, 'Carta di Credito', 'Non Pagata', '2023-10-05', 'Via Venezia 222', 'MA_Technology', 'matech123');

INSERT INTO FATTURA (Numero_Fattura, Data_Emissione, Descrizione, Totale, Metodo_Pagamento, Stato_Pagamento, Data_Scadenza, Indirizzo_Fatturazione, Nome_Azienda, Partita_IVA) VALUES 
(6, '2023-09-25', 'Fattura per servizi di consulenza', 5500, 'Bonifico Bancario', 'Non Pagata', '2023-10-10', 'Via Milano 333', 'MA_Technology', 'matech123');


INSERT INTO FORNITORI_ASSOCIATI (ID_Fornitore, Nome_Fornitore, Indirizzo, Citta, Provincia, CAP, Paese, Tipo_Fornitura, Numero_di_Telefono, Email, Tempo_Fornitura) VALUES 
('F001', 'Fornitore A', 'Via Roma 123', 'Milano', 'MI', 20100, 'Italia', 'Materiali Edili', 12345890, 'fornitoreA@email.com', 30);

INSERT INTO FORNITORI_ASSOCIATI (ID_Fornitore, Nome_Fornitore, Indirizzo, Citta, Provincia, CAP, Paese, Tipo_Fornitura, Numero_di_Telefono, Email, Tempo_Fornitura) VALUES 
('F002', 'Fornitore B', 'Via Verdi 456', 'Roma', 'RM', 00100, 'Italia', 'Prodotti Elettronici', 98763210, 'fornitoreB@email.com', 45);

INSERT INTO FORNITORI_ASSOCIATI (ID_Fornitore, Nome_Fornitore, Indirizzo, Citta, Provincia, CAP, Paese, Tipo_Fornitura, Numero_di_Telefono, Email, Tempo_Fornitura) VALUES 
('F003', 'Fornitore C', 'Via Napoli 789', 'Napoli', 'NA', 80100, 'Italia', 'Componenti Meccanici', 55555555, 'fornitoreC@email.com', 60);

INSERT INTO FORNITORI_ASSOCIATI (ID_Fornitore, Nome_Fornitore, Indirizzo, Citta, Provincia, CAP, Paese, Tipo_Fornitura, Numero_di_Telefono, Email, Tempo_Fornitura) VALUES 
('F004', 'Fornitore D', 'Via Firenze 101', 'Firenze', 'FI', 50100, 'Italia', 'Prodotti Alimentari', 99999999, 'fornitoreD@email.com', 90);

INSERT INTO FORNITORI_ASSOCIATI (ID_Fornitore, Nome_Fornitore, Indirizzo, Citta, Provincia, CAP, Paese, Tipo_Fornitura, Numero_di_Telefono, Email, Tempo_Fornitura) VALUES 
('F005', 'Fornitore E', 'Via Venezia 222', 'Venezia', 'VE', 30100, 'Italia', 'Prodotti Chimici', 77777777, 'fornitoreE@email.com', 120);

INSERT INTO FORNITORI_ASSOCIATI (ID_Fornitore, Nome_Fornitore, Indirizzo, Citta, Provincia, CAP, Paese, Tipo_Fornitura, Numero_di_Telefono, Email, Tempo_Fornitura) VALUES 
('F006', 'Fornitore F', 'Via Milano 333', 'Milano', 'MI', 20100, 'Italia', 'Materiali Edili', 88888888, 'fornitoreF@email.com', 60);


INSERT INTO MACCHINARI (ID_Macchina, Nome_Macchina, Tipo_Macchina, Descrizione, Marca, Modello, Capacita_Lavorativa) VALUES 
('M001', 'Macchina A', 'Stampante 3D', 'Stampante 3D ad alta precisione', 'MakerBot', 'Modello XYZ', 500);

INSERT INTO MACCHINARI (ID_Macchina, Nome_Macchina, Tipo_Macchina, Descrizione, Marca, Modello, Capacita_Lavorativa) VALUES 
('M002', 'Macchina B', 'Fresatrice CNC', 'Fresatrice CNC per lavorazioni metalliche', 'Haas', 'VF-2', 1000);

INSERT INTO MACCHINARI (ID_Macchina, Nome_Macchina, Tipo_Macchina, Descrizione, Marca, Modello, Capacita_Lavorativa) VALUES 
('M003', 'Macchina C', 'Tornio a controllo numerico', 'Tornio CNC per la lavorazione di materiali metallici', 'DMG MORI', 'NLX 2500', 800);

INSERT INTO MACCHINARI (ID_Macchina, Nome_Macchina, Tipo_Macchina, Descrizione, Marca, Modello, Capacita_Lavorativa) VALUES 
('M004', 'Macchina D', 'Taglio al laser', 'Macchina per il taglio al laser di materiali plastici', 'Trumpf', 'TruLaser 3030', 1500);

INSERT INTO MACCHINARI (ID_Macchina, Nome_Macchina, Tipo_Macchina, Descrizione, Marca, Modello, Capacita_Lavorativa) VALUES 
('M005', 'Macchina E', 'Pressa idraulica', 'Pressa idraulica per lavorazioni su lamiera', 'Amada', 'HDS-8025NT', 2000);

INSERT INTO MACCHINARI (ID_Macchina, Nome_Macchina, Tipo_Macchina, Descrizione, Marca, Modello, Capacita_Lavorativa) VALUES 
('M006', 'Macchina F', 'Stampatrice offset', 'Stampatrice offset per la stampa su carta', 'Heidelberg', 'Speedmaster XL 106', 1200);


INSERT INTO PROCESSO_PRODUTTIVO (ID_Processo, Nome_processo, Data_inizio, Data_fine, Nome_Azienda, Partita_IVA) VALUES 
('P001', 'Processo di Assemblaggio', '2023-09-01', '2023-09-15', 'Apple', 'apple111');

INSERT INTO PROCESSO_PRODUTTIVO (ID_Processo, Nome_processo, Data_inizio, Data_fine, Nome_Azienda, Partita_IVA) VALUES 
('P002', 'Processo di Produzione', '2023-09-05', '2023-09-20', 'Apple', 'apple111');

INSERT INTO PROCESSO_PRODUTTIVO (ID_Processo, Nome_processo, Data_inizio, Data_fine, Nome_Azienda, Partita_IVA) VALUES 
('P003', 'Processo di Confezionamento', '2023-09-10', '2023-09-25', 'MA_Technology', 'matech123');

INSERT INTO PROCESSO_PRODUTTIVO (ID_Processo, Nome_processo, Data_inizio, Data_fine, Nome_Azienda, Partita_IVA) VALUES 
('P004', 'Processo di Controllo Qualità', '2023-09-15', '2023-09-30', 'MA_Technology', 'matech123');

INSERT INTO PROCESSO_PRODUTTIVO (ID_Processo, Nome_processo, Data_inizio, Data_fine, Nome_Azienda, Partita_IVA) VALUES 
('P005', 'Processo di Imballaggio', '2023-09-20', '2023-10-05', 'Mizuno', 'mizuno999');

INSERT INTO PROCESSO_PRODUTTIVO (ID_Processo, Nome_processo, Data_inizio, Data_fine, Nome_Azienda, Partita_IVA) VALUES 
('P006', 'Processo di Magazzinaggio', '2023-09-25', '2023-10-10', 'Samsung', 'samsung234');


INSERT INTO PRODOTTI (ID_Prodotto, Nome_Prodotto, Descrizione, Categoria_Prodotto, Prezzo, Disponibilita, Documentazione, Licenze_Prodotto, ID_Fornitore) VALUES 
('PRD001', 'Prodotto A', 'Descrizione del Prodotto A', 'Elettronica', 500, 100, 'Documentazione_A.pdf', 'Licenza_A', 'F001');

INSERT INTO PRODOTTI (ID_Prodotto, Nome_Prodotto, Descrizione, Categoria_Prodotto, Prezzo, Disponibilita, Documentazione, Licenze_Prodotto, ID_Fornitore) VALUES 
('PRD002', 'Prodotto B', 'Descrizione del Prodotto B', 'Abbigliamento', 50, 200, 'Documentazione_B.pdf', 'Licenza_B', 'F001');

INSERT INTO PRODOTTI (ID_Prodotto, Nome_Prodotto, Descrizione, Categoria_Prodotto, Prezzo, Disponibilita, Documentazione, Licenze_Prodotto, ID_Fornitore) VALUES 
('PRD003', 'Prodotto C', 'Descrizione del Prodotto C', 'Alimentari', 10, 300, 'Documentazione_C.pdf', 'Licenza_C', 'F003');

INSERT INTO PRODOTTI (ID_Prodotto, Nome_Prodotto, Descrizione, Categoria_Prodotto, Prezzo, Disponibilita, Documentazione, Licenze_Prodotto, ID_Fornitore) VALUES 
('PRD004', 'Prodotto D', 'Descrizione del Prodotto D', 'Elettronica', 800, 50, 'Documentazione_D.pdf', 'Licenza_D', 'F004');

INSERT INTO PRODOTTI (ID_Prodotto, Nome_Prodotto, Descrizione, Categoria_Prodotto, Prezzo, Disponibilita, Documentazione, Licenze_Prodotto, ID_Fornitore) VALUES 
('PRD005', 'Prodotto E', 'Descrizione del Prodotto E', 'Casa e Giardino', 150, 150, 'Documentazione_E.pdf', 'Licenza_E', 'F005');

INSERT INTO PRODOTTI (ID_Prodotto, Nome_Prodotto, Descrizione, Categoria_Prodotto, Prezzo, Disponibilita, Documentazione, Licenze_Prodotto, ID_Fornitore) VALUES 
('PRD006', 'Prodotto F', 'Descrizione del Prodotto F', 'Elettronica', 300, 75, 'Documentazione_F.pdf', 'Licenza_F', 'F006');


INSERT INTO PRODOTTO_FINITO (ID_Prodotto, Quantita_Disponibile, Descrizione, Documentazione, ID_Processo, ID_Consegna) VALUES 
('PF001', 100, 'Prodotto Finito A', 'Documentazione_A.pdf', 'P001', 'CON001');

INSERT INTO PRODOTTO_FINITO (ID_Prodotto, Quantita_Disponibile, Descrizione, Documentazione, ID_Processo, ID_Consegna) VALUES 
('PF002', 200, 'Prodotto Finito B', 'Documentazione_B.pdf', 'P002', 'CON002');

INSERT INTO PRODOTTO_FINITO (ID_Prodotto, Quantita_Disponibile, Descrizione, Documentazione, ID_Processo, ID_Consegna) VALUES 
('PF003', 300, 'Prodotto Finito C', 'Documentazione_C.pdf', 'P003', 'CON003');

INSERT INTO PRODOTTO_FINITO (ID_Prodotto, Quantita_Disponibile, Descrizione, Documentazione, ID_Processo, ID_Consegna) VALUES 
('PF004', 150, 'Prodotto Finito D', 'Documentazione_D.pdf', 'P004', 'CON004');

INSERT INTO PRODOTTO_FINITO (ID_Prodotto, Quantita_Disponibile, Descrizione, Documentazione, ID_Processo, ID_Consegna) VALUES 
('PF005', 75, 'Prodotto Finito E', 'Documentazione_E.pdf', 'P005', 'CON005');

INSERT INTO PRODOTTO_FINITO (ID_Prodotto, Quantita_Disponibile, Descrizione, Documentazione, ID_Processo, ID_Consegna) VALUES 
('PF006', 50, 'Prodotto Finito F', 'Documentazione_F.pdf', 'P006', 'CON005');


INSERT INTO REVISIONE (ID_Revisione, Data_inizio_revisione, Data_fine_revisione, Responsabile, Risultati, Azioni_Correttive, Costo, Stato, Note_aggiuntive, ID_Macchina) VALUES 
('REV001', '2023-09-01', '2023-09-03', 'Responsabile A', 'Buono', 'Nessuna azione', 500, 'Completata', 'Nessuna nota', 'M001');

INSERT INTO REVISIONE (ID_Revisione, Data_inizio_revisione, Data_fine_revisione, Responsabile, Risultati, Azioni_Correttive, Costo, Stato, Note_aggiuntive, ID_Macchina) VALUES 
('REV002', '2023-08-25', '2023-08-27', 'Responsabile B', 'Sufficiente', 'Aggiornamento software', 750, 'In corso', 'Revisione programmata', 'M002');

INSERT INTO REVISIONE (ID_Revisione, Data_inizio_revisione, Data_fine_revisione, Responsabile, Risultati, Azioni_Correttive, Costo, Stato, Note_aggiuntive, ID_Macchina) VALUES 
('REV003', '2023-09-10', '2023-09-12', 'Responsabile C', 'Eccellente', 'Nessuna azione', 300, 'Completata', 'Nessuna nota', 'M003');

INSERT INTO REVISIONE (ID_Revisione, Data_inizio_revisione, Data_fine_revisione, Responsabile, Risultati, Azioni_Correttive, Costo, Stato, Note_aggiuntive, ID_Macchina) VALUES 
('REV004', '2023-08-20', '2023-08-22', 'Responsabile D', 'Soddisfacente', 'Manutenzione preventiva', 600, 'In corso', 'Revisione programmata', 'M004');

INSERT INTO REVISIONE (ID_Revisione, Data_inizio_revisione, Data_fine_revisione, Responsabile, Risultati, Azioni_Correttive, Costo, Stato, Note_aggiuntive, ID_Macchina) VALUES 
('REV005', '2023-09-05', '2023-09-07', 'Responsabile E', 'Buono', 'Aggiornamento firmware', 400, 'Completata', 'Nessuna nota', 'M005');

INSERT INTO REVISIONE (ID_Revisione, Data_inizio_revisione, Data_fine_revisione, Responsabile, Risultati, Azioni_Correttive, Costo, Stato, Note_aggiuntive, ID_Macchina) VALUES 
('REV006', '2023-08-15', '2023-08-17', 'Responsabile F', 'Eccellente', 'Nessuna azione', 350, 'Completata', 'Nessuna nota', 'M006');


INSERT INTO SCARTI_NON_RIUTILIZZABILI (ID_Scarto, Descrizione, Data_Rilevamento, Quantita, Documentazione, Motivo_Scarto) VALUES 
('SNR001', 'Scarto A', '2023-09-02', 10, 'Documentazione_A.pdf', 'danneggiato');

INSERT INTO SCARTI_NON_RIUTILIZZABILI (ID_Scarto, Descrizione, Data_Rilevamento, Quantita, Documentazione, Motivo_Scarto) VALUES 
('SNR002', 'Scarto B', '2023-08-28', 5, 'Documentazione_B.pdf', 'Erroreproduzione');

INSERT INTO SCARTI_NON_RIUTILIZZABILI (ID_Scarto, Descrizione, Data_Rilevamento, Quantita, Documentazione, Motivo_Scarto) VALUES 
('SNR003', 'Scarto C', '2023-09-11', 8, 'Documentazione_C.pdf', 'Sprecomateriale');

INSERT INTO SCARTI_NON_RIUTILIZZABILI (ID_Scarto, Descrizione, Data_Rilevamento, Quantita, Documentazione, Motivo_Scarto) VALUES 
('SNR004', 'Scarto D', '2023-08-23', 12, 'Documentazione_D.pdf', 'nonconforme');

INSERT INTO SCARTI_NON_RIUTILIZZABILI (ID_Scarto, Descrizione, Data_Rilevamento, Quantita, Documentazione, Motivo_Scarto) VALUES 
('SNR005', 'Scarto E', '2023-09-06', 7, 'Documentazione_E.pdf', 'Errore');

INSERT INTO SCARTI_NON_RIUTILIZZABILI (ID_Scarto, Descrizione, Data_Rilevamento, Quantita, Documentazione, Motivo_Scarto) VALUES 
('SNR006', 'Scarto F', '2023-08-18', 9, 'Documentazione_F.pdf', 'Spreco');


INSERT INTO SCARTI_RIUTILIZZABILI (ID_Scarto, Descrizione, Data_Rilevamento, Quantita, Documentazione) VALUES 
('SRR001', 'Scarto Riutilizzabile A', '2023-09-02', 3, 'Document_A.pdf');

INSERT INTO SCARTI_RIUTILIZZABILI (ID_Scarto, Descrizione, Data_Rilevamento, Quantita, Documentazione) VALUES 
('SRR002', 'Scarto Riutilizzabile B', '2023-08-28', 2, 'Document_B.pdf');

INSERT INTO SCARTI_RIUTILIZZABILI (ID_Scarto, Descrizione, Data_Rilevamento, Quantita, Documentazione) VALUES 
('SRR003', 'Scarto Riutilizzabile C', '2023-09-11', 4, 'Document_C.pdf');

INSERT INTO SCARTI_RIUTILIZZABILI (ID_Scarto, Descrizione, Data_Rilevamento, Quantita, Documentazione) VALUES 
('SRR004', 'Scarto Riutilizzabile D', '2023-08-23', 1, 'Document_D.pdf');

INSERT INTO SCARTI_RIUTILIZZABILI (ID_Scarto, Descrizione, Data_Rilevamento, Quantita, Documentazione) VALUES 
('SRR005', 'Scarto Riutilizzabile E', '2023-09-06', 5, 'Document_E.pdf');

INSERT INTO SCARTI_RIUTILIZZABILI (ID_Scarto, Descrizione, Data_Rilevamento, Quantita, Documentazione) VALUES 
('SRR006', 'Scarto Riutilizzabile F', '2023-08-18', 2, 'Document_F.pdf');


INSERT INTO TRASPORTO_DISCARICA (ID_Trasporto, Data_Spedizione, Data_Arrivo, Mezzo_di_Trasporto, Dipendente_Responsabile, Quantita, Peso, Documenti_Associati) VALUES 
('TD001', '2023-09-05', '2023-09-06', 'Camion A', 'Dipendente A', 10, 500, 'Document_A.pdf');

INSERT INTO TRASPORTO_DISCARICA (ID_Trasporto, Data_Spedizione, Data_Arrivo, Mezzo_di_Trasporto, Dipendente_Responsabile, Quantita, Peso, Documenti_Associati) VALUES 
('TD002', '2023-08-30', '2023-08-31', 'Camion B', 'Dipendente B', 8, 400, 'Document_B.pdf');

INSERT INTO TRASPORTO_DISCARICA (ID_Trasporto, Data_Spedizione, Data_Arrivo, Mezzo_di_Trasporto, Dipendente_Responsabile, Quantita, Peso, Documenti_Associati) VALUES 
('TD003', '2023-09-12', '2023-09-13', 'Camion C', 'Dipendente C', 12, 600, 'Document_C.pdf');


INSERT INTO USO_MACCHINARIO (ID_Macchina, ID_Dipendente, Data, Ora) VALUES ('M001', 'D001', '2023-09-05', '08:00');
INSERT INTO USO_MACCHINARIO (ID_Macchina, ID_Dipendente, Data, Ora) VALUES ('M002', 'D002', '2023-09-06', '09:30');
INSERT INTO USO_MACCHINARIO (ID_Macchina, ID_Dipendente, Data, Ora) VALUES ('M003', 'D003', '2023-09-07', '11:15');
INSERT INTO USO_MACCHINARIO (ID_Macchina, ID_Dipendente, Data, Ora) VALUES ('M004', 'D004', '2023-09-08', '14:45');
