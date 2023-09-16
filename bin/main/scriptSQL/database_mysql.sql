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
     Motivo_Scarto char(10) not null,
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

