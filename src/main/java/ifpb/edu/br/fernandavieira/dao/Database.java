package ifpb.edu.br.fernandavieira.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    private static final String url = "jdbc:postgresql://localhost:5432/atv-sd";
    private static final String user = "postgres";
    private static final String password = "12345678";

    public static Connection getConnection  () throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}
