package ifpb.edu.br.fernandavieira.service;

import ifpb.edu.br.fernandavieira.dao.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicInteger;

public class SimpleService {

    private static final AtomicInteger id = new AtomicInteger(0);
    private static final Integer MAX = 100;

    private static final String scriptInserir = "INSERT INTO Usuario(id, Nome) VALUES (?,?);";
    private static final String scripAtualizar = "UPDATE Usuario SET updated = TRUE";
    private static final String scriptDeletar = "UPDATE Usuario SET deleted = TRUE";

    private Connection connection;
    private Database db = new Database();

    public SimpleService() throws SQLException {
        this.connection = db.getConnection();
    }


    public void inserirAtualizarDeletar() {

        try {
            connection = Database.getConnection();

            PreparedStatement inserir = connection.prepareStatement(scriptInserir);
            PreparedStatement atualizar = connection.prepareStatement(scripAtualizar);
            PreparedStatement deletar = connection.prepareStatement(scriptDeletar);

            Long tempoInicio = System.currentTimeMillis();

            while (MAX > id.get()) {

                inserir.setInt(1, id.get());
                inserir.setString(2, String.format("Usuario - %d", id.getAndIncrement()));
                inserir.executeUpdate();

                atualizar.executeUpdate();

                deletar.executeUpdate();
            }

            Long tempoFinal = System.currentTimeMillis();

            System.out.println("SimpleService: " + (tempoFinal - tempoInicio) + "ms");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
