package ifpb.edu.br.fernandavieira.service;

import ifpb.edu.br.fernandavieira.dao.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicInteger;

public class UseThreadService {
    private static final AtomicInteger id = new AtomicInteger(0);
    private static final Integer MAX = 100;

    private static final String scriptInserir = "INSERT INTO Usuario(id, Nome) VALUES (?,?);";
    private static final String scriptAtualizar = "UPDATE Usuario SET updated = TRUE WHERE id = ?";
    private static final String scriptDeletar = "UPDATE Usuario SET deleted = TRUE WHERE id = ?";

    private Connection connection;
    private Database db = new Database();

    public UseThreadService() throws SQLException {
        this.connection = db.getConnection();
    }

    public void comThreads() {
        PreparedStatement psInserir = null;
        try {
            psInserir = connection.prepareStatement(scriptInserir);

            PreparedStatement psAtualizar = connection.prepareStatement(scriptAtualizar);
            PreparedStatement psDeletar = connection.prepareStatement(scriptDeletar);

            PreparedStatement fimPreparedStatement = psInserir;

            Runnable inserir = () -> {
                try {
                    fimPreparedStatement.setInt(1, id.getAndIncrement());
                    fimPreparedStatement.setString(2, String.format("Nome%d", id.getAndIncrement()));
                    fimPreparedStatement.executeUpdate();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    return;
                }
            };

            Runnable deletar = () -> {
                try {
                    psDeletar.setInt(1, id.getAndIncrement());
                    psDeletar.executeUpdate();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    return;
                }
            };


            Runnable atualizar = () -> {
                try {
                    psAtualizar.setInt(1, id.getAndIncrement());
                    psAtualizar.executeUpdate();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    return;
                }
            };


            Long tempoInicio = System.currentTimeMillis();

            int count = 0;
            while (MAX > count) {
                new Thread(inserir).start();
                new Thread(atualizar).start();
                new Thread(deletar).start();
                count++;
            }

            Long tempoFinal = System.currentTimeMillis();


            System.out.println("UseThreadService: " + (tempoFinal - tempoInicio) + "ms");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
