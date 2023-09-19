package db.tables;

import db.Table;
import model.Macchinari;
import model.Prodotti;
import model.Revisione;
import utils.Utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class MacchinariTable implements Table<Macchinari, String> {
    public static final String TABLE_NAME = "MACCHINARI";
    private final Connection connection;

    public MacchinariTable(final Connection connection) {
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
    public Optional<Macchinari> findByPrimaryKey(final String primaryKey) {
        final String query = "SELECT * FROM " + TABLE_NAME + " WHERE ID_Macchina = ?";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, primaryKey);
            final ResultSet resultSet = statement.executeQuery();
            return readMacchineFromResultSet(resultSet).stream().findFirst();
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    private List<Macchinari> readMacchineFromResultSet(final ResultSet resultSet) {
        final List<Macchinari> machines = new ArrayList<>();
        try {
            while (resultSet.next()) {
                final String idMachine = resultSet.getString("ID_Macchina");
                final String name = resultSet.getString("Nome_Macchina");
                final String type = resultSet.getString("Tipo_Macchina");
                final String description = resultSet.getString("Descrizione");
                final String brand = resultSet.getString("Marca");
                final String modello = resultSet.getString("Modello");
                final int capacity = resultSet.getInt("Capacita_Lavorativa");
                final Macchinari machine = new Macchinari(idMachine, name, type, description, brand, modello, capacity);
                machines.add(machine);
            }
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
        return machines;
    }

    @Override
    public List<Macchinari> findAll() {
        try (final Statement statement = this.connection.createStatement()) {
            final ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE_NAME);
            return readMacchineFromResultSet(resultSet);
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public boolean save(final Macchinari value) {
        final String query = "INSERT INTO " + TABLE_NAME + "() VALUES (?,?,?,?,?,?,?)";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, value.getId());
            statement.setString(2, value.getNameMachine());
            statement.setString(3, value.getTypeMachine());
            statement.setString(4, value.getDescription());
            statement.setString(5, value.getBrand());
            statement.setString(6, value.getModelMachine());
            statement.setInt(7, value.getCapacity());
            statement.executeUpdate();
            return true;
        } catch (final SQLIntegrityConstraintViolationException e) {
            return false;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    private boolean macchinarioExists(final String id) {
        final MacchinariTable machine = new MacchinariTable(connection);
        return machine.findByPrimaryKey(id).isPresent();
    }

    @Override
    public boolean update(final Macchinari value) {
        final String query = "UPDATE " + TABLE_NAME + " SET " +
                "ID_Macchina = ?," +
                "Nome_Macchina = ?," +
                "Tipo_Macchina = ?," +
                "Descrizione = ?," +
                "Marca = ?," +
                "Modello = ?," +
                "Capacita_Lavorativa = ?" +
                "WHERE ID_Macchina = ?";
        if (!this.macchinarioExists(value.getId())) {
            return false;
        }
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, value.getId());
            statement.setString(2, value.getNameMachine());
            statement.setString(3, value.getTypeMachine());
            statement.setString(4, value.getDescription());
            statement.setString(5, value.getBrand());
            statement.setString(6, value.getModelMachine());
            statement.setInt(7, value.getCapacity());
            statement.setString(8, value.getId());
            return statement.executeUpdate() > 0;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public boolean delete(final String primaryKey) {
        final String query = "DELETE FROM " + TABLE_NAME + " WHERE ID_Macchina = ?";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, primaryKey);
            return statement.executeUpdate() > 0;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public List<Revisione> machineRevisione (final String idMacchina) {
        final String query = "SELECT R.*\n" +
                "FROM MACCHINARI M\n" +
                "JOIN REVISIONE R ON M.ID_Macchina = R.ID_Macchina\n" +
                "WHERE M.ID_Macchina = ?\n";

        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, idMacchina);
            ResultSet resultSet = statement.executeQuery();
            return readRevisioniFromResultSet(resultSet);
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    private List<Revisione> readRevisioniFromResultSet (final ResultSet resultSet) {
        final List<Revisione> reviews = new ArrayList<>();
        try {
            while (resultSet.next()) {
                final String idReview = resultSet.getString("ID_Revisione");
                final java.util.Date start = Utils.sqlDateToDate(resultSet.getDate("Data_inizio_revisione"));
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
}
