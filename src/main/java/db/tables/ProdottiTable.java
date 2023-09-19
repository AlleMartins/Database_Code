package db.tables;

import db.Table;
import model.Prodotti;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProdottiTable implements Table<Prodotti, String> {

    public static final String TABLE_NAME = "PRODOTTI";
    private final Connection connection;

    public ProdottiTable(final Connection connection) {
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
    public Optional<Prodotti> findByPrimaryKey(final String primaryKey) {
        final String query = "SELECT * FROM " + TABLE_NAME + " WHERE ID_Prodotto = ?";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, primaryKey);
            final ResultSet resultSet = statement.executeQuery();
            return readProdFromResultSet(resultSet).stream().findFirst();
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    private List<Prodotti> readProdFromResultSet(final ResultSet resultSet) {
        final List<Prodotti> products = new ArrayList<>();
        try {
            while (resultSet.next()) {
                final String idProd = resultSet.getString("ID_Prodotto");
                final String name = resultSet.getString("Nome_Prodotto");
                final String description = resultSet.getString("Descrizione");
                final String category = resultSet.getString("Categoria_Prodotto");
                final int value = resultSet.getInt("Prezzo");
                final int disponibility = resultSet.getInt("Disponibilita");
                final String documents = resultSet.getString("Documentazione");
                final String license = resultSet.getString("Licenze_Prodotto");
                final String idFornitore = resultSet.getString("ID_Fornitore");
                final Prodotti product = new Prodotti(idProd, name, description, category, value, disponibility, documents, license, idFornitore);
                products.add(product);
            }
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
        return products;
    }

    @Override
    public List<Prodotti> findAll() {
        try (final Statement statement = this.connection.createStatement()) {
            final ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE_NAME);
            return readProdFromResultSet(resultSet);
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public boolean save(final Prodotti value) {
        final String query = "INSERT INTO " + TABLE_NAME + "(ID_Prodotto, Nome_Prodotto, Descrizione, Categoria_Prodotto, Prezzo, Disponibilita, Documentazione, Licenze_Prodotto, ID_Fornitore) VALUES (?,?,?,?,?,?,?,?,?)";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, value.getIdProduct());
            statement.setString(2, value.getProductName());
            statement.setString(3, value.getDescription());
            statement.setString(4, value.getProductCategory());
            statement.setInt(5, value.getPrice());
            statement.setInt(6, value.getAvailability());
            statement.setString(7, value.getDocumentation());
            statement.setString(8, value.getLicense());
            statement.setString(9, value.getIdForniture());
            statement.executeUpdate();
            return true;
        } catch (final SQLIntegrityConstraintViolationException e) {
            return false;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    private boolean productExists(final String id) {
        final ProdottiTable prod = new ProdottiTable(connection);
        return prod.findByPrimaryKey(id).isPresent();
    }

    @Override
    public boolean update(final Prodotti value) {
        final String query = "UPDATE " + TABLE_NAME + " SET " +
                "ID_Prodotto = ?," +
                "Nome_Prodotto = ?," +
                "Descrizione = ?," +
                "Categoria_Prodotto = ?," +
                "Prezzo = ?," +
                "Disponibilita = ?," +
                "Documentazione = ?," +
                "Licenze_Prodotto = ?," +
                "ID_Fornitore = ?" +
                "WHERE ID_Prodotto = ?";
        if (!this.productExists(value.getIdProduct())) {
            return false;
        }
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, value.getIdProduct());
            statement.setString(2, value.getProductName());
            statement.setString(3, value.getDescription());
            statement.setString(4, value.getProductCategory());
            statement.setInt(5, value.getPrice());
            statement.setInt(6, value.getAvailability());
            statement.setString(7, value.getDocumentation());
            statement.setString(8, value.getLicense());
            statement.setString(9, value.getIdForniture());
            statement.setString(10, value.getIdProduct());
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
