package db.tables;

import db.Table;
import model.Prodotto_Finito;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProdottoFinitoTable implements Table<Prodotto_Finito, String> {

    public static final String TABLE_NAME = "PRODOTTO_FINITO";
    private final Connection connection;

    public ProdottoFinitoTable(final Connection connection) {
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
    public Optional<Prodotto_Finito> findByPrimaryKey(final String primaryKey) {
        final String query = "SELECT * FROM " + TABLE_NAME + " WHERE ID_Prodotto = ?";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, primaryKey);
            final ResultSet resultSet = statement.executeQuery();
            return readProdFinFromResultSet(resultSet).stream().findFirst();
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    private List<Prodotto_Finito> readProdFinFromResultSet(final ResultSet resultSet) {
        final List<Prodotto_Finito> finishProd = new ArrayList<>();
        try {
            while (resultSet.next()) {
                final String idProduct = resultSet.getString("ID_Prodotto");
                final int disponibility = resultSet.getInt("Quantita_Disponibile");
                final String description = resultSet.getString("Descrizione");
                final String documents = resultSet.getString("Documentazione");
                final String idProcess = resultSet.getString("ID_Processo");
                final String idConsegna = resultSet.getString("ID_Consegna");
                final Prodotto_Finito prod = new Prodotto_Finito(idProduct, disponibility, description, documents, idProcess, idConsegna);
                finishProd.add(prod);
            }
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
        return finishProd;
    }

    @Override
    public List<Prodotto_Finito> findAll() {
        try (final Statement statement = this.connection.createStatement()) {
            final ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE_NAME);
            return readProdFinFromResultSet(resultSet);
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public boolean save(final Prodotto_Finito value) {
        final String query = "INSERT INTO " + TABLE_NAME + "(ID_Prodotto, Quantita_Disponibile, Descrizione, Documentazione, ID_Processo, ID_Consegna) VALUES (?,?,?,?,?,?)";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, value.getId());
            statement.setInt(2, value.getAvailability());
            statement.setString(3, value.getDescription());
            statement.setString(4, value.getDocumentation());
            statement.setString(5, value.getIdProcess());
            statement.setString(6, value.getIdDelivery());
            statement.executeUpdate();
            return true;
        } catch (final SQLIntegrityConstraintViolationException e) {
            return false;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    private boolean productExists(final String id) {
        final ProdottoFinitoTable prod = new ProdottoFinitoTable(connection);
        return prod.findByPrimaryKey(id).isPresent();
    }

    @Override
    public boolean update(final Prodotto_Finito value) {
        final String query = "UPDATE " + TABLE_NAME + " SET " +
                "ID_Prodotto = ?," +
                "Quantita_Disponibile = ?," +
                "Descrizione = ?," +
                "Documentazione = ?," +
                "ID_Processo = ?," +
                "ID_Consegna = ?" +
                "WHERE ID_Prodotto = ?";
        if (!this.productExists(value.getId())) {
            return false;
        }
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, value.getId());
            statement.setInt(2, value.getAvailability());
            statement.setString(3, value.getDescription());
            statement.setString(4, value.getDocumentation());
            statement.setString(5, value.getIdProcess());
            statement.setString(6, value.getIdDelivery());
            statement.setString(7, value.getId());
            return statement.executeUpdate() > 0;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public boolean delete(final String primaryKey) {
        final String query = "DELETE FROM " + TABLE_NAME + " WHERE ID_Prodotto = ?";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, primaryKey);
            return statement.executeUpdate() > 0;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
