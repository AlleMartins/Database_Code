package db.tables;

import db.Table;
import model.Revisione;
import utils.Utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class RevisioneTable implements Table<Revisione, String> {

    public static final String TABLE_NAME = "REVISIONE";
    private final Connection connection;

    public RevisioneTable(final Connection connection) {
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
    public Optional<Revisione> findByPrimaryKey(final String primaryKey) {
        final String query = "SELECT * FROM " + TABLE_NAME + " WHERE ID_Revisione = ?";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, primaryKey);
            final ResultSet resultSet = statement.executeQuery();
            return readRevisioneFromResultSet(resultSet).stream().findFirst();
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    private List<Revisione> readRevisioneFromResultSet(final ResultSet resultSet) {
        final List<Revisione> reviews = new ArrayList<>();
        try {
            while (resultSet.next()) {
                final String idReview = resultSet.getString("ID_Revisione");
                final Date start = Utils.sqlDateToDate(resultSet.getDate("Data_inizio_revisione"));
                final Date end = Utils.sqlDateToDate(resultSet.getDate("Data_fine_revisione"));
                final String responsabile = resultSet.getString("Responsabile");
                final String results = resultSet.getString("Risultati");
                final String action = resultSet.getString("Azioni_Correttive");
                final int cost = resultSet.getInt("Costo");
                final String state = resultSet.getString("Stato");
                final String notes = resultSet.getString("Note_Aggiuntive");
                final String idMachine = resultSet.getString("ID_Macchina");
                final Revisione review = new Revisione(idReview, start, end, responsabile, results, action, cost, state, notes, idMachine);
                reviews.add(review);
            }
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
        return reviews;
    }

    @Override
    public List<Revisione> findAll() {
        try (final Statement statement = this.connection.createStatement()) {
            final ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE_NAME);
            return readRevisioneFromResultSet(resultSet);
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public boolean save(final Revisione value) {
        final String query = "INSERT INTO " + TABLE_NAME + "(ID_Revisione, Data_inizio_revisione, Data_fine_revisione, Responsabile, Risultati, Azioni_Correttive, Costo, Stato, Note_aggiuntive, ID_Macchina) VALUES (?,?,?,?,?,?,?,?,?,?)";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, value.getId());
            statement.setDate(2, Utils.dateToSqlDate(value.getStart()));
            statement.setDate(3, Utils.dateToSqlDate(value.getEnd()));
            statement.setString(4, value.getResponsible());
            statement.setString(5, value.getResult());
            statement.setString(6, value.getCorrectiveActions());
            statement.setInt(7, value.getCost());
            statement.setString(8, value.getState());
            statement.setString(9, value.getNotes());
            statement.setString(10, value.getIdMachine());
            statement.executeUpdate();
            return true;
        } catch (final SQLIntegrityConstraintViolationException e) {
            return false;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    private boolean reviewExists(final String id) {
        final RevisioneTable review = new RevisioneTable(connection);
        return review.findByPrimaryKey(id).isPresent();
    }

    @Override
    public boolean update(final Revisione value) {
        final String query = "UPDATE " + TABLE_NAME + " SET " +
                "ID_Revisione = ?," +
                "Data_inizio_revisione = ?," +
                "Data_fine_revisione = ?," +
                "Responsabile = ?," +
                "Risultati = ?," +
                "Azioni_Correttive = ?," +
                "Costo = ?," +
                "Stato = ?," +
                "Note_aggiuntive = ?," +
                "ID_Macchina = ?" +
                "WHERE ID_Revisione = ?";
        if (!this.reviewExists(value.getId())) {
            return false;
        }
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, value.getId());
            statement.setDate(2, Utils.dateToSqlDate(value.getStart()));
            statement.setDate(3, Utils.dateToSqlDate(value.getEnd()));
            statement.setString(4, value.getResponsible());
            statement.setString(5, value.getResult());
            statement.setString(6, value.getCorrectiveActions());
            statement.setInt(7, value.getCost());
            statement.setString(8, value.getState());
            statement.setString(9, value.getNotes());
            statement.setString(10, value.getIdMachine());
            statement.setString(11, value.getId());
            return statement.executeUpdate() > 0;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public boolean delete(final String primaryKey) {
        final String query = "DELETE FROM " + TABLE_NAME + " WHERE ID_Revisione = ?";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, primaryKey);
            return statement.executeUpdate() > 0;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
