package db.tables;

import db.Table;
import model.Cliente;
import model.Commessa;
import model.Prodotti;
import utils.Utils;

import java.sql.*;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClienteTable implements Table<Cliente, String> {
    public static final String TABLE_NAME = "CLIENTE";
    private final Connection connection;

    public ClienteTable(Connection connection) {
        this.connection = connection;
    }

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    @Override
    public boolean dropTable() {
        try (final Statement statement = this.connection.createStatement()) {
            statement.executeQuery("DROP TABLE " + TABLE_NAME);
            return true;
        } catch (final SQLException e) {
            return false;
        }
    }

    @Override
    public Optional<Cliente> findByPrimaryKey(final String id) {
        final String query = "SELECT * FROM " + TABLE_NAME + " WHERE Partita_IVA = ?";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, id);
            final ResultSet resultSet = statement.executeQuery();
            return readClientiFromResultSet(resultSet).stream().findFirst();
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    private List<Cliente> readClientiFromResultSet(final ResultSet resultSet) {
        final List<Cliente> clienti = new ArrayList<>();
        try {
            while (resultSet.next()) {
                final String name = resultSet.getString("Nome");
                final String surname = resultSet.getString("Cognome");
                final Date birthday = Utils.sqlDateToDate(resultSet.getDate("Data_Nascita"));
                final String address = resultSet.getString("Indirizzo_Civico");
                final String city = resultSet.getString("Citta");
                final String province = resultSet.getString("Provincia");
                final int cap = resultSet.getInt("CAP");
                final String nation = resultSet.getString("Paese");
                final String email = resultSet.getString("Email");
                final int telephone = resultSet.getInt("Numero_di_Telefono");
                final String job = resultSet.getString("Professione");
                final String partitaIva =  resultSet.getString("Partita_IVA");

                final Cliente cliente = new Cliente(name, surname, birthday, address, city, province, cap, nation, email, telephone, job, partitaIva);
                clienti.add(cliente);
            }
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
        return clienti;
    }

    @Override
    public List<Cliente> findAll() {
        try (final Statement statement = this.connection.createStatement()) {
            final ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE_NAME);
            return readClientiFromResultSet(resultSet);
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public boolean save(final Cliente value) {
        final String query = "INSERT INTO " + TABLE_NAME + "(Nome, Cognome, Data_Nascita, Indirizzo_Civico, Citta, Provincia, CAP, Paese, Email, Numero_di_Telefono, Professione, Partita_IVA) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, value.getName());
            statement.setString(2, value.getSurname());
            statement.setDate(3, Utils.dateToSqlDate(value.getBirthday()));
            statement.setString(4, value.getAddress());
            statement.setString(5, value.getCity());
            statement.setString(6, value.getProvince());
            statement.setInt(7, value.getCap());
            statement.setString(8, value.getNation());
            statement.setString(9, value.getEmail());
            statement.setInt(10, value.getTelephone());
            statement.setString(11, value.getJobSector());
            statement.setString(12, value.getPartitaIva());
            statement.executeUpdate();
            return true;
        } catch (final SQLIntegrityConstraintViolationException e) {
            return false;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    private boolean clienteExists (final String partitaIva) {
        final ClienteTable cliente = new ClienteTable(connection);
        return cliente.findByPrimaryKey(partitaIva).isPresent();
    }

    @Override
    public boolean update(final Cliente updatedValue) {
        final String query = "UPDATE " + TABLE_NAME + " SET " +
                "Nome = ?," +
                "Cognome = ?," +
                "Data_Nascita = ?," +
                "Indirizzo_Civico = ?," +
                "Citta = ?," +
                "Provincia = ?," +
                "CAP = ?," +
                "Paese = ?," +
                "Email = ?," +
                "Numero_di_Telefono = ?," +
                "Professione = ?," +
                "Partita_IVA = ?" +
                "WHERE Partita_IVA = ?";
        if (!this.clienteExists(updatedValue.getPartitaIva())) {
            return false;
        }
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, updatedValue.getName());
            statement.setString(2, updatedValue.getSurname());
            statement.setDate(3, Utils.dateToSqlDate(updatedValue.getBirthday()));
            statement.setString(4, updatedValue.getAddress());
            statement.setString(5, updatedValue.getCity());
            statement.setString(6, updatedValue.getProvince());
            statement.setInt(7, updatedValue.getCap());
            statement.setString(8, updatedValue.getNation());
            statement.setString(9, updatedValue.getEmail());
            statement.setInt(10, updatedValue.getTelephone());
            statement.setString(11, updatedValue.getJobSector());
            statement.setString(12, updatedValue.getPartitaIva());
            statement.setString(13, updatedValue.getPartitaIva());
            return statement.executeUpdate() > 0;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public boolean delete(final String primaryKey) {
        final String query = "DELETE FROM " + TABLE_NAME + " WHERE Partita_IVA = ?";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, primaryKey);
            return statement.executeUpdate() > 0;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }


    public List<Commessa> commesseClients (final String partitaIva) {
        final String query = "SELECT C.*\n" +
                "FROM CLIENTE CL\n" +
                "JOIN COMMESSA C ON CL.Partita_IVA = C.Ric_Partita_IVA\n" +
                "WHERE CL.Partita_IVA = ?;";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, partitaIva);
            ResultSet resultSet = statement.executeQuery();
            return readCommesseFromResultSet(resultSet);
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    private List<Commessa> readCommesseFromResultSet (final ResultSet resultSet) {
        final List<Commessa> commesse = new ArrayList<>();
        try {
            while (resultSet.next()) {
                final String idCommessa = resultSet.getString("ID_Commessa");
                final String description = resultSet.getString("Descrizione");
                final Date start = Utils.sqlDateToDate(resultSet.getDate("Data_Inizio"));
                final Date end = Utils.sqlDateToDate(resultSet.getDate("Data_Fine"));
                final String state = resultSet.getString("Stato_Commessa");
                final String documents = resultSet.getString("Documenti_Associati");
                final String project = resultSet.getString("Progetto");
                final String companyName = resultSet.getString("Nome_Azienda");
                final String partitaIVA = resultSet.getString("Partita_IVA");
                final String ricPartitaIVA = resultSet.getString("Ric_Partita_IVA");
                final Commessa commessa = new Commessa(idCommessa, description, start, end, state, documents, project, companyName, partitaIVA, ricPartitaIVA);
                commesse.add(commessa);
            }
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
        return commesse;
    }
}
