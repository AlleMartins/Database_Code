package db.tables;

import db.Table;
import model.Scarti_Non_Riutilizzabili;
import utils.Utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class ScartiNonRiutilizzabiliTable implements Table<Scarti_Non_Riutilizzabili, String> {

    public static final String TABLE_NAME = "SCARTI_NON_RIUTILIZZABILI";
    private final Connection connection;

    public ScartiNonRiutilizzabiliTable(final Connection connection) {
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
    public Optional<Scarti_Non_Riutilizzabili> findByPrimaryKey(final String primaryKey) {
        final String query = "SELECT * FROM " + TABLE_NAME + " WHERE ID_Scarto = ?";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, primaryKey);
            final ResultSet resultSet = statement.executeQuery();
            return readWasteFromResultSet(resultSet).stream().findFirst();
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    private List<Scarti_Non_Riutilizzabili> readWasteFromResultSet(final ResultSet resultSet) {
        final List<Scarti_Non_Riutilizzabili> wastes = new ArrayList<>();
        try {
            while (resultSet.next()) {
                final String idWaste = resultSet.getString("ID_Scarto");
                final String description = resultSet.getString("Descrizione");
                final Date dateResearch = Utils.sqlDateToDate(resultSet.getDate("Data_Rilevamento"));
                final int amount = resultSet.getInt("Quantita");
                final String documents = resultSet.getString("Documentazione");
                final String reasonWaste = resultSet.getString("Motivo_Scarto");
                final Scarti_Non_Riutilizzabili waste = new Scarti_Non_Riutilizzabili(idWaste, description, dateResearch, amount, documents, reasonWaste);
                wastes.add(waste);
            }
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
        return wastes;
    }

    @Override
    public List<Scarti_Non_Riutilizzabili> findAll() {
        try (final Statement statement = this.connection.createStatement()) {
            final ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE_NAME);
            return readWasteFromResultSet(resultSet);
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public boolean save(final Scarti_Non_Riutilizzabili value) {
        final String query = "INSERT INTO " + TABLE_NAME + "(ID_Scarto, Descrizione, Data_Rilevamento, Quantita, Documentazione, Motivo_Scarto) VALUES (?,?,?,?,?,?)";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, value.getId());
            statement.setString(2, value.getDescription());
            statement.setDate(3, Utils.dateToSqlDate(value.getDetectionDate()));
            statement.setInt(4, value.getAmount());
            statement.setString(5, value.getDocumentation());
            statement.setString(6, value.getReject());
            statement.executeUpdate();
            return true;
        } catch (final SQLIntegrityConstraintViolationException e) {
            return false;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    private boolean wasteExists(final String id) {
        final ScartiNonRiutilizzabiliTable waste = new ScartiNonRiutilizzabiliTable(connection);
        return waste.findByPrimaryKey(id).isPresent();
    }

    @Override
    public boolean update(final Scarti_Non_Riutilizzabili value) {
        final String query = "UPDATE " + TABLE_NAME + " SET " +
                "ID_Scarto = ?," +
                "Descrizione = ?," +
                "Data_Rilevamento = ?," +
                "Quantita = ?," +
                "Documentazione = ?," +
                "Motivo_Scarto = ?" +
                "WHERE ID_Scarto = ?";
        if (!this.wasteExists(value.getId())) {
            return false;
        }
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, value.getId());
            statement.setString(2, value.getDescription());
            statement.setDate(3, Utils.dateToSqlDate(value.getDetectionDate()));
            statement.setInt(4, value.getAmount());
            statement.setString(5, value.getDocumentation());
            statement.setString(6, value.getReject());
            statement.setString(7, value.getId());
            return statement.executeUpdate() > 0;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public boolean delete(final String primaryKey) {
        final String query = "DELETE FROM " + TABLE_NAME + " WHERE ID_Scarto = ?";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, primaryKey);
            return statement.executeUpdate() > 0;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
