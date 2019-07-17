package ifpb.edu.br.fernandavieira.service;

import ifpb.edu.br.fernandavieira.dao.Database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TruncateService {
    private Connection connection;
    private Database db = new Database();

    public TruncateService() throws SQLException {
        this.connection = db.getConnection();
    }

    public void truncade() {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("TRUNCATE usuario;");
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
