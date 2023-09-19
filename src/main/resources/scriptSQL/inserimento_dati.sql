INSERT INTO AZIENDA (Nome_Azienda, Indirizzo, Citta, Provincia, CAP, Paese, Settore, Numero_di_Telefono, Email, Partita_IVA, Conto_Azienda) VALUES 
('MA_Technology', 'Via Giuseppe Garibaldi 22', 'Ostiglia', 'Mantova', 43567 , 'Italia', 'IT', 3245677719 , 'matechnology@gmail.com', 'matech123', 'Aziendale')
INSERT INTO AZIENDA (Nome_Azienda, Indirizzo, Citta, Provincia, CAP, Paese, Settore, Numero_di_Telefono, Email, Partita_IVA, Conto_Azienda) VALUES 
('Apple', 'Silicon Valley', 'Silicon Valley', 'Assente', 00000 , 'America', 'IT', 3567889910 , 'apple@apple.com', 'apple111', 'Aziendale')
INSERT INTO AZIENDA (Nome_Azienda, Indirizzo, Citta, Provincia, CAP, Paese, Settore, Numero_di_Telefono, Email, Partita_IVA, Conto_Azienda) VALUES 
('Samsung', 'Via Milano 99', 'Milano', 'Milano', 49019 , 'Italia', 'IT', 399011345 , 'samsung@outlook.it', 'samsung234', 'Aziendale')
INSERT INTO AZIENDA (Nome_Azienda, Indirizzo, Citta, Provincia, CAP, Paese, Settore, Numero_di_Telefono, Email, Partita_IVA, Conto_Azienda) VALUES 
('Mizuno', 'Via Salvatore Quasimodo', 'Cesena', 'Forli-Cesena',  46035, 'Italia', 'Sport', 3556789901 , 'mizuno@gmail.com', 'mizuno999', 'Aziendale')

INSERT INTO CLIENTE (Nome, Cognome, Data_Nascita, Indirizzo_Civico, Citta, Provincia, CAP, Paese, Email, Numero_di_Telefono, Professione, Partita_IVA) VALUES 
('Mario', 'Rossi', '1990-05-15', 'Via Roma 123', 'Milano', 'MI', 20100, 'Italia', 'mario.rossi@email.com', 1234567890, 'Ingegnere', '12345678901');

INSERT INTO CLIENTE (Nome, Cognome, Data_Nascita, Indirizzo_Civico, Citta, Provincia, CAP, Paese, Email, Numero_di_Telefono, Professione, Partita_IVA) VALUES 
('Anna', 'Bianchi', '1985-08-20', 'Via Verdi 456', 'Roma', 'RM', 00100, 'Italia', 'anna.bianchi@email.com', 9876543210, 'Avvocato', '23456789012');

INSERT INTO CLIENTE (Nome, Cognome, Data_Nascita, Indirizzo_Civico, Citta, Provincia, CAP, Paese, Email, Numero_di_Telefono, Professione, Partita_IVA) VALUES 
('Luigi', 'Verdi', '1982-03-10', 'Via Napoli 789', 'Napoli', 'NA', 80100, 'Italia', 'luigi.verdi@email.com', 5555555555, 'Medico', '34567890123');

INSERT INTO CLIENTE (Nome, Cognome, Data_Nascita, Indirizzo_Civico, Citta, Provincia, CAP, Paese, Email, Numero_di_Telefono, Professione, Partita_IVA) VALUES 
('Giovanna', 'Ferrari', '1995-12-05', 'Via Firenze 101', 'Firenze', 'FI', 50100, 'Italia', 'giovanna.ferrari@email.com', 9999999999, 'Architetto', '45678901234');

INSERT INTO CLIENTE (Nome, Cognome, Data_Nascita, Indirizzo_Civico, Citta, Provincia, CAP, Paese, Email, Numero_di_Telefono, Professione, Partita_IVA) VALUES 
('Marco', 'Russo', '1988-07-25', 'Via Venezia 222', 'Venezia', 'VE', 30100, 'Italia', 'marco.russo@email.com', 7777777777, 'Insegnante', '56789012345');


INSERT INTO COMMESSA (ID_Commessa, Descrizione, Data_Inizio, Data_Fine, Stato_Commessa, Documenti_Associati, Progetto, Nome_Azienda, Partita_IVA, Ric_Partita_IVA) VALUES 
('C001', 'Descrizione Commessa 1', '2023-09-01', '2023-09-15', 'In Corso', 'Doc001', 'Progetto 1', 'MA_Technology', 'matech123', '12345678901');

INSERT INTO COMMESSA (ID_Commessa, Descrizione, Data_Inizio, Data_Fine, Stato_Commessa, Documenti_Associati, Progetto, Nome_Azienda, Partita_IVA, Ric_Partita_IVA) VALUES 
('C002', 'Descrizione Commessa 2', '2023-08-25', '2023-09-10', 'In Corso', 'Doc002', 'Progetto 2', 'MA_Technology', 'matech123', '12345678901');

INSERT INTO COMMESSA (ID_Commessa, Descrizione, Data_Inizio, Data_Fine, Stato_Commessa, Documenti_Associati, Progetto, Nome_Azienda, Partita_IVA, Ric_Partita_IVA) VALUES 
('C003', 'Descrizione Commessa 3', '2023-09-03', '2023-09-18', 'In Corso', 'Doc003', 'Progetto 3', 'Apple', 'apple111', '34567890123');

INSERT INTO COMMESSA (ID_Commessa, Descrizione, Data_Inizio, Data_Fine, Stato_Commessa, Documenti_Associati, Progetto, Nome_Azienda, Partita_IVA, Ric_Partita_IVA) VALUES 
('C004', 'Descrizione Commessa 4', '2023-08-30', '2023-09-12', 'In Corso', 'Doc004', 'Progetto 4', 'Samsung', 'samsung234', '45678901234');

INSERT INTO COMMESSA (ID_Commessa, Descrizione, Data_Inizio, Data_Fine, Stato_Commessa, Documenti_Associati, Progetto, Nome_Azienda, Partita_IVA, Ric_Partita_IVA) VALUES 
('C005', 'Descrizione Commessa 5', '2023-09-05', '2023-09-20', 'In Corso', 'Doc005', 'Progetto 5', 'Mizuno', 'mizuno999', '56789012345');


INSERT INTO CONSEGNA_CLIENTE (ID_Consegna, Data_Spedizione, Data_Arrivo, Indirizzo_spedizione, Citta_spedizione, CAP_spedizione, Dipendente_Responsabile, Peso, Documenti_Associati, Mezzo_Trasporto) VALUES 
('CON001', '2023-09-01', '2023-09-10', 'Via Roma 123', 'Milano', 20100, 'Mario Rossi', 500, 'Doc123', 'Autotrasporto');

INSERT INTO CONSEGNA_CLIENTE (ID_Consegna, Data_Spedizione, Data_Arrivo, Indirizzo_spedizione, Citta_spedizione, CAP_spedizione, Dipendente_Responsabile, Peso, Documenti_Associati, Mezzo_Trasporto) VALUES 
('CON002', '2023-08-28', '2023-09-05', 'Via Verdi 456', 'Roma', 00100, 'Anna Bianchi', 700, 'Doc456', 'Trasporto Aereo');

INSERT INTO CONSEGNA_CLIENTE (ID_Consegna, Data_Spedizione, Data_Arrivo, Indirizzo_spedizione, Citta_spedizione, CAP_spedizione, Dipendente_Responsabile, Peso, Documenti_Associati, Mezzo_Trasporto) VALUES 
('CON003', '2023-09-03', '2023-09-12', 'Via Napoli 789', 'Napoli', 80100, 'Luigi Verdi', 300, 'Doc789', 'Trasporto Ferroviario');

INSERT INTO CONSEGNA_CLIENTE (ID_Consegna, Data_Spedizione, Data_Arrivo, Indirizzo_spedizione, Citta_spedizione, CAP_spedizione, Dipendente_Responsabile, Peso, Documenti_Associati, Mezzo_Trasporto) VALUES 
('CON004', '2023-09-02', '2023-09-09', 'Via Firenze 101', 'Firenze', 50100, 'Giovanna Ferrari', 600, 'Doc101', 'Trasporto Stradale');

INSERT INTO CONSEGNA_CLIENTE (ID_Consegna, Data_Spedizione, Data_Arrivo, Indirizzo_spedizione, Citta_spedizione, CAP_spedizione, Dipendente_Responsabile, Peso, Documenti_Associati, Mezzo_Trasporto) VALUES 
('CON005', '2023-08-30', '2023-09-08', 'Via Venezia 222', 'Venezia', 30100, 'Marco Russo', 400, 'Doc222', 'Trasporto Marittimo');


INSERT INTO DIPENDENTI (ID_Dipendente, Nome, Data_Nascita, Indirizzo, Citta, Provincia, CAP, Paese, Numero_di_Telefono, Email, Ruolo, Dipartimento, Data_Assunzione, Stipendio, Certificazioni) VALUES 
('D001', 'Mario Rossi', '1990-05-15', 'Via Roma 123', 'Milano', 'MI', 20100, 'Italia', 1234567890, 'mario.rossi@email.com', 'Manager', 'Amministrazione', '2022-01-10', 60000, 'Cert1, Cert2');

INSERT INTO DIPENDENTI (ID_Dipendente, Nome, Data_Nascita, Indirizzo, Citta, Provincia, CAP, Paese, Numero_di_Telefono, Email, Ruolo, Dipartimento, Data_Assunzione, Stipendio, Certificazioni) VALUES 
('D002', 'Anna Bianchi', '1985-08-20', 'Via Verdi 456', 'Roma', 'RM', 00100, 'Italia', 9876543210, 'anna.bianchi@email.com', 'Analista', 'Tecnologia', '2022-02-15', 50000, 'Cert3');

INSERT INTO DIPENDENTI (ID_Dipendente, Nome, Data_Nascita, Indirizzo, Citta, Provincia, CAP, Paese, Numero_di_Telefono, Email, Ruolo, Dipartimento, Data_Assunzione, Stipendio, Certificazioni) VALUES 
('D003', 'Luigi Verdi', '1982-03-10', 'Via Napoli 789', 'Napoli', 'NA', 80100, 'Italia', 5555555555, 'luigi.verdi@email.com', 'Sviluppatore', 'Tecnologia', '2022-03-20', 55000, 'Cert4');

INSERT INTO DIPENDENTI (ID_Dipendente, Nome, Data_Nascita, Indirizzo, Citta, Provincia, CAP, Paese, Numero_di_Telefono, Email, Ruolo, Dipartimento, Data_Assunzione, Stipendio, Certificazioni) VALUES 
('D004', 'Giovanna Ferrari', '1995-12-05', 'Via Firenze 101', 'Firenze', 'FI', 50100, 'Italia', 9999999999, 'giovanna.ferrari@email.com', 'Project Manager', 'Progetti', '2022-04-25', 62000, 'Cert5, Cert6');

INSERT INTO DIPENDENTI (ID_Dipendente, Nome, Data_Nascita, Indirizzo, Citta, Provincia, CAP, Paese, Numero_di_Telefono, Email, Ruolo, Dipartimento, Data_Assunzione, Stipendio, Certificazioni) VALUES 
('D005', 'Marco Russo', '1988-07-25', 'Via Venezia 222', 'Venezia', 'VE', 30100, 'Italia', 7777777777, 'marco.russo@email.com', 'Amministratore di Sistema', 'Tecnologia', '2022-05-12', 58000, 'Cert7, Cert8');


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
('F001', 'Fornitore A', 'Via Roma 123', 'Milano', 'MI', 20100, 'Italia', 'Materiali Edili', 1234567890, 'fornitoreA@email.com', 30);

INSERT INTO FORNITORI_ASSOCIATI (ID_Fornitore, Nome_Fornitore, Indirizzo, Citta, Provincia, CAP, Paese, Tipo_Fornitura, Numero_di_Telefono, Email, Tempo_Fornitura) VALUES 
('F002', 'Fornitore B', 'Via Verdi 456', 'Roma', 'RM', 00100, 'Italia', 'Prodotti Elettronici', 9876543210, 'fornitoreB@email.com', 45);

INSERT INTO FORNITORI_ASSOCIATI (ID_Fornitore, Nome_Fornitore, Indirizzo, Citta, Provincia, CAP, Paese, Tipo_Fornitura, Numero_di_Telefono, Email, Tempo_Fornitura) VALUES 
('F003', 'Fornitore C', 'Via Napoli 789', 'Napoli', 'NA', 80100, 'Italia', 'Componenti Meccanici', 5555555555, 'fornitoreC@email.com', 60);

INSERT INTO FORNITORI_ASSOCIATI (ID_Fornitore, Nome_Fornitore, Indirizzo, Citta, Provincia, CAP, Paese, Tipo_Fornitura, Numero_di_Telefono, Email, Tempo_Fornitura) VALUES 
('F004', 'Fornitore D', 'Via Firenze 101', 'Firenze', 'FI', 50100, 'Italia', 'Prodotti Alimentari', 9999999999, 'fornitoreD@email.com', 90);

INSERT INTO FORNITORI_ASSOCIATI (ID_Fornitore, Nome_Fornitore, Indirizzo, Citta, Provincia, CAP, Paese, Tipo_Fornitura, Numero_di_Telefono, Email, Tempo_Fornitura) VALUES 
('F005', 'Fornitore E', 'Via Venezia 222', 'Venezia', 'VE', 30100, 'Italia', 'Prodotti Chimici', 7777777777, 'fornitoreE@email.com', 120);

INSERT INTO FORNITORI_ASSOCIATI (ID_Fornitore, Nome_Fornitore, Indirizzo, Citta, Provincia, CAP, Paese, Tipo_Fornitura, Numero_di_Telefono, Email, Tempo_Fornitura) VALUES 
('F006', 'Fornitore F', 'Via Milano 333', 'Milano', 'MI', 20100, 'Italia', 'Materiali Edili', 8888888888, 'fornitoreF@email.com', 60);


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
('P001', 'Processo di Assemblaggio', '2023-09-01', '2023-09-15', 'Azienda 1', '12345678901');

INSERT INTO PROCESSO_PRODUTTIVO (ID_Processo, Nome_processo, Data_inizio, Data_fine, Nome_Azienda, Partita_IVA) VALUES 
('P002', 'Processo di Produzione', '2023-09-05', '2023-09-20', 'Azienda 2', '23456789012');

INSERT INTO PROCESSO_PRODUTTIVO (ID_Processo, Nome_processo, Data_inizio, Data_fine, Nome_Azienda, Partita_IVA) VALUES 
('P003', 'Processo di Confezionamento', '2023-09-10', '2023-09-25', 'Azienda 3', '34567890123');

INSERT INTO PROCESSO_PRODUTTIVO (ID_Processo, Nome_processo, Data_inizio, Data_fine, Nome_Azienda, Partita_IVA) VALUES 
('P004', 'Processo di Controllo Qualità', '2023-09-15', '2023-09-30', 'Azienda 4', '45678901234');

INSERT INTO PROCESSO_PRODUTTIVO (ID_Processo, Nome_processo, Data_inizio, Data_fine, Nome_Azienda, Partita_IVA) VALUES 
('P005', 'Processo di Imballaggio', '2023-09-20', '2023-10-05', 'Azienda 5', '56789012345');

INSERT INTO PROCESSO_PRODUTTIVO (ID_Processo, Nome_processo, Data_inizio, Data_fine, Nome_Azienda, Partita_IVA) VALUES 
('P006', 'Processo di Magazzinaggio', '2023-09-25', '2023-10-10', 'Azienda 6', '67890123456');


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
('PF001', 100, 'Prodotto Finito A', 'Documentazione_A.pdf', 'P001', 'C001');

INSERT INTO PRODOTTO_FINITO (ID_Prodotto, Quantita_Disponibile, Descrizione, Documentazione, ID_Processo, ID_Consegna) VALUES 
('PF002', 200, 'Prodotto Finito B', 'Documentazione_B.pdf', 'P002', 'C002');

INSERT INTO PRODOTTO_FINITO (ID_Prodotto, Quantita_Disponibile, Descrizione, Documentazione, ID_Processo, ID_Consegna) VALUES 
('PF003', 300, 'Prodotto Finito C', 'Documentazione_C.pdf', 'P003', 'C003');

INSERT INTO PRODOTTO_FINITO (ID_Prodotto, Quantita_Disponibile, Descrizione, Documentazione, ID_Processo, ID_Consegna) VALUES 
('PF004', 150, 'Prodotto Finito D', 'Documentazione_D.pdf', 'P004', 'C004');

INSERT INTO PRODOTTO_FINITO (ID_Prodotto, Quantita_Disponibile, Descrizione, Documentazione, ID_Processo, ID_Consegna) VALUES 
('PF005', 75, 'Prodotto Finito E', 'Documentazione_E.pdf', 'P005', 'C005');

INSERT INTO PRODOTTO_FINITO (ID_Prodotto, Quantita_Disponibile, Descrizione, Documentazione, ID_Processo, ID_Consegna) VALUES 
('PF006', 50, 'Prodotto Finito F', 'Documentazione_F.pdf', 'P006', 'C006');


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
('SNR001', 'Scarto A', '2023-09-02', 10, 'Documentazione_A.pdf', 'Materiale danneggiato');

INSERT INTO SCARTI_NON_RIUTILIZZABILI (ID_Scarto, Descrizione, Data_Rilevamento, Quantita, Documentazione, Motivo_Scarto) VALUES 
('SNR002', 'Scarto B', '2023-08-28', 5, 'Documentazione_B.pdf', 'Errore di produzione');

INSERT INTO SCARTI_NON_RIUTILIZZABILI (ID_Scarto, Descrizione, Data_Rilevamento, Quantita, Documentazione, Motivo_Scarto) VALUES 
('SNR003', 'Scarto C', '2023-09-11', 8, 'Documentazione_C.pdf', 'Spreco di materiale');

INSERT INTO SCARTI_NON_RIUTILIZZABILI (ID_Scarto, Descrizione, Data_Rilevamento, Quantita, Documentazione, Motivo_Scarto) VALUES 
('SNR004', 'Scarto D', '2023-08-23', 12, 'Documentazione_D.pdf', 'Materiale non conforme');

INSERT INTO SCARTI_NON_RIUTILIZZABILI (ID_Scarto, Descrizione, Data_Rilevamento, Quantita, Documentazione, Motivo_Scarto) VALUES 
('SNR005', 'Scarto E', '2023-09-06', 7, 'Documentazione_E.pdf', 'Errore di produzione');

INSERT INTO SCARTI_NON_RIUTILIZZABILI (ID_Scarto, Descrizione, Data_Rilevamento, Quantita, Documentazione, Motivo_Scarto) VALUES 
('SNR006', 'Scarto F', '2023-08-18', 9, 'Documentazione_F.pdf', 'Spreco di materiale');


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
