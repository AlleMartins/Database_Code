package db.tables;

import db.Table;
import model.Fornitori_Associati;
import model.Prodotti;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FornitoriAssociatiTable implements Table<Fornitori_Associati, String> {
    public static final String TABLE_NAME = "FORNITORI_ASSOCIATI";
    private final Connection connection;

    public FornitoriAssociatiTable(final Connection connection) {
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
    public Optional<Fornitori_Associati> findByPrimaryKey(final String primaryKey) {
        final String query = "SELECT * FROM " + TABLE_NAME + " WHERE ID_Fornitore = ?";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, primaryKey);
            final ResultSet resultSet = statement.executeQuery();
            return readFornitoriFromResultSet(resultSet).stream().findFirst();
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    private List<Fornitori_Associati> readFornitoriFromResultSet(final ResultSet resultSet) {
        final List<Fornitori_Associati> fornitori = new ArrayList<>();
        try {
            while (resultSet.next()) {
                final String idFornitore = resultSet.getString("ID_Fornitore");
                final String name = resultSet.getString("Nome_Fornitore");
                final String address = resultSet.getString("Indirizzo");
                final String city = resultSet.getString("Citta");
                final String province = resultSet.getString("Provincia");
                final int cap = resultSet.getInt("CAP");
                final String nation = resultSet.getString("Paese");
                final String type = resultSet.getString("Tipo_fornitura");
                final int telephone = resultSet.getInt("Numero_di_Telefono");
                final String email = resultSet.getString("Email");
                final int timeForniture = resultSet.getInt("Tempo_Fornitura");
                final Fornitori_Associati fornitore = new Fornitori_Associati(idFornitore, name, address, city, province, cap, nation, type, telephone, email, timeForniture);
                fornitori.add(fornitore);
            }
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
        return fornitori;
    }

    @Override
    public List<Fornitori_Associati> findAll() {
        try (final Statement statement = this.connection.createStatement()) {
            final ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE_NAME);
            return readFornitoriFromResultSet(resultSet);
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public boolean save(final Fornitori_Associati value) {
        final String query = "INSERT INTO " + TABLE_NAME + "(ID_Fornitore, Nome_Fornitore, Indirizzo, Citta, Provincia, CAP, Paese, Tipo_Fornitura, Numero_di_Telefono, Email, Tempo_Fornitura) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, value.getId());
            statement.setString(2, value.getName());
            statement.setString(3, value.getAddress());
            statement.setString(4, value.getCity());
            statement.setString(5, value.getProvince());
            statement.setInt(6, value.getCap());
            statement.setString(7, value.getNation());
            statement.setString(8, value.getForniture());
            statement.setInt(9, value.getTelephone());
            statement.setString(10, value.getEmail());
            statement.setInt(11, value.getTimeForniture());
            statement.executeUpdate();
            return true;
        } catch (final SQLIntegrityConstraintViolationException e) {
            return false;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    private boolean fornitoriExists(final String id) {
        final FornitoriAssociatiTable forniture = new FornitoriAssociatiTable(connection);
        return forniture.findByPrimaryKey(id).isPresent();
    }

    @Override
    public boolean update(final Fornitori_Associati value) {
        final String query = "UPDATE " + TABLE_NAME + " SET " +
                "ID_Fornitore = ?," +
                "Nome_Fornitore = ?," +
                "Indirizzo = ?," +
                "Citta = ?," +
                "Provincia = ?," +
                "CAP = ?," +
                "Paese = ?," +
                "Tipo_Fornitura = ?," +
                "Numero_di_Telefono = ?," +
                "Email = ?," +
                "Tempo_Fornitura = ?" +
                " WHERE ID_Fornitore = ?";
        if (!this.fornitoriExists(value.getId())) {
            return false;
        }
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, value.getId());
            statement.setString(2, value.getName());
            statement.setString(3, value.getAddress());
            statement.setString(4, value.getCity());
            statement.setString(5, value.getProvince());
            statement.setInt(6, value.getCap());
            statement.setString(7, value.getNation());
            statement.setString(8, value.getForniture());
            statement.setInt(9, value.getTelephone());
            statement.setString(10, value.getEmail());
            statement.setInt(11, value.getTimeForniture());
            statement.setString(12, value.getId());
            return statement.executeUpdate() > 0;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public boolean delete(final String primaryKey) {
        final String query = "DELETE FROM " + TABLE_NAME + " WHERE ID_Fornitore = ?";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, primaryKey);
            return statement.executeUpdate() > 0;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public List<Prodotti> fornitureProducts (final String idFornitore) {
        final String query = "SELECT F.ID_Fornitore, F.*, P.ID_Prodotto, P.*\n" +
                "FROM " + TABLE_NAME + " F\n" +
                "LEFT JOIN PRODOTTI P ON F.ID_Fornitore = P.ID_Fornitore\n" +
                "WHERE F.ID_Fornitore = ?";

        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, idFornitore);
            ResultSet resultSet = statement.executeQuery();
            return readProdottiFromResultSet(resultSet);
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    private List<Prodotti> readProdottiFromResultSet (final ResultSet resultSet) {
        final List<Prodotti> prodotti = new ArrayList<>();
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
                prodotti.add(product);
            }
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
        return prodotti;
    }
}
