package ifpb.edu.br.fernandavieira.service;

import ifpb.edu.br.fernandavieira.dao.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectService {
    private Connection connection;
    private Database db = new Database();

    public SelectService() throws SQLException {
        this.connection = db.getConnection();
    }

    public void buscar() {


        try {
            PreparedStatement preparedStatement = null;

            preparedStatement = connection.prepareStatement("SELECT * FROM Controle");

            long tempoInicio = System.currentTimeMillis();

            ResultSet resultSet = preparedStatement.executeQuery();

            long tempoFinal = System.currentTimeMillis();

            System.out.println("SelectService: " + (tempoFinal - tempoInicio) + "ms");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
