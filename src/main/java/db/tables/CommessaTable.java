package db.tables;

import db.Table;
import model.Commessa;
import utils.Utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class CommessaTable implements Table<Commessa, String> {
    public static final String TABLE_NAME = "COMMESSA";
    private final Connection connection;

    public CommessaTable(final Connection connection) {
        this.connection = connection;
    }

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    @Override
    public boolean dropTable() {
        try (final Statement statement = this.connection.createStatement()) {
            statement.executeUpdate("DROP TABLE " + TABLE_NAME);
            return true;
        } catch (final SQLException e) {
            return false;
        }
    }

    @Override
    public Optional<Commessa> findByPrimaryKey(final String primaryKey) {
        final String query = "SELECT * FROM " + TABLE_NAME + " WHERE ID_Commessa = ?";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, primaryKey);
            final ResultSet resultSet = statement.executeQuery();
            return readCommesseFromResultSet(resultSet).stream().findFirst();
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    private List<Commessa> readCommesseFromResultSet(final ResultSet resultSet) {
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

    @Override
    public List<Commessa> findAll() {
        try (final Statement statement = this.connection.createStatement()) {
            final ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE_NAME);
            return readCommesseFromResultSet(resultSet);
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public boolean save(final Commessa value) {
        final String query = "INSERT INTO " + TABLE_NAME + "(ID_Commessa, Descrizione, Data_Inizio, Data_Fine, Stato_Commessa, Documenti_Associati, Progetto, Nome_Azienda, Partita_IVA, Ric_Partita_IVA) VALUES (?,?,?,?,?,?,?,?,?,?)";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, value.getId());
            statement.setString(2, value.getDescription());
            statement.setDate(3, Utils.dateToSqlDate(value.getStart()));
            statement.setDate(4, Utils.dateToSqlDate(value.getEnd()));
            statement.setString(5, value.getState());
            statement.setString(6, value.getDocuments());
            statement.setString(7, value.getProject());
            statement.setString(8, value.getCompanyName());
            statement.setString(9, value.getPartitaIva());
            statement.setString(10, value.getRicPartitaIva());
            statement.executeUpdate();
            return true;
        } catch (final SQLIntegrityConstraintViolationException e) {
            return false;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    private boolean commessaExists(final String id) {
        final CommessaTable commessa = new CommessaTable(connection);
        return commessa.findByPrimaryKey(id).isPresent();
    }

    @Override
    public boolean update(final Commessa updatedValue) {
        final String query = "UPDATE " + TABLE_NAME + " SET " +
                "ID_Commessa = ?," +
                "Descrizione = ?," +
                "Data_Inizio = ?," +
                "Data_Fine = ?," +
                "Stato_Commessa = ?," +
                "Documenti_Associati = ?," +
                "Progetto = ?," +
                "Nome_Azienda = ?," +
                "Partita_IVA = ?," +
                "Ric_Partita_IVA = ?" +
                "WHERE ID_Commessa = ?";
        if (!this.commessaExists(updatedValue.getPartitaIva())) {
            return false;
        }
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, updatedValue.getId());
            statement.setString(2, updatedValue.getDescription());
            statement.setDate(3, Utils.dateToSqlDate(updatedValue.getStart()));
            statement.setDate(4, Utils.dateToSqlDate(updatedValue.getEnd()));
            statement.setString(5, updatedValue.getState());
            statement.setString(6, updatedValue.getDocuments());
            statement.setString(7, updatedValue.getProject());
            statement.setString(8, updatedValue.getCompanyName());
            statement.setString(9, updatedValue.getPartitaIva());
            statement.setString(10, updatedValue.getRicPartitaIva());
            statement.setString(11, updatedValue.getId());
            return statement.executeUpdate() > 0;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public boolean delete(final String primaryKey) {
        final String query = "DELETE FROM " + TABLE_NAME + " WHERE ID_Commessa = ?";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, primaryKey);
            return statement.executeUpdate() > 0;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
