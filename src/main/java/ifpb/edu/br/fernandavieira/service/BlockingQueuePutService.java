package ifpb.edu.br.fernandavieira.service;

import ifpb.edu.br.fernandavieira.dao.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueuePutService {
    private static Long tempoInicio;

    private static Integer id = new Integer(1);
    private static final Integer MAX = 100;

    private static final String scriptInserr = "INSERT INTO Usuario(id, Nome) VALUES (?,?);";
    private static final String scriptAtualizar = "UPDATE Usuario SET updated = TRUE WHERE id = ?";
    private static final String scriptDeletar = "UPDATE Usuario SET deleted = TRUE WHERE id = ?";


    private Connection connection;
    private Database db = new Database();

    public BlockingQueuePutService() throws SQLException {
        this.connection = db.getConnection();
    }

    public void blockingQueuePut() throws InterruptedException {
        BlockingQueue<Integer> queueInserir = new ArrayBlockingQueue<>(10);
        BlockingQueue<Integer> queueAtualizar = new ArrayBlockingQueue<>(10);
        BlockingQueue<Integer> queueDeletar = new ArrayBlockingQueue<>( 10);

        Runnable inserir = () -> {
            try {
                int indice = Integer.valueOf(queueInserir.take());
                PreparedStatement stmtInserir = connection.prepareStatement(scriptInserr);

                stmtInserir.setInt(1, indice);
                stmtInserir.setString(2, String.format("Nome%d", indice));
                stmtInserir.executeUpdate();

                queueAtualizar.put(indice);

            } catch (Exception ex) {
                ex.printStackTrace();
                return;
            }
        };

        Runnable deletar = () -> {
            try {
                Integer indice = new Integer(queueDeletar.take());
                PreparedStatement stmtDelete = connection.prepareStatement(scriptDeletar);

                stmtDelete.setInt(1, indice);
                stmtDelete.executeUpdate();

                if (indice.equals(MAX)) {
                    Long tempoFinal = System.currentTimeMillis();
                    System.out.printf("BlockingQueuePutService: " + (tempoFinal - tempoInicio) + "ms\n\n");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                return;
            }
        };

        Runnable atualizar = () -> {
            try {
                Integer indice = new Integer(queueAtualizar.take());
                PreparedStatement stmtUpdate = connection.prepareStatement(scriptAtualizar);

                stmtUpdate.setInt(1, indice);
                stmtUpdate.executeUpdate();

                queueDeletar.put(indice);
            } catch (Exception ex) {
                ex.printStackTrace();
                return;
            }
        };

        tempoInicio = System.currentTimeMillis();

        while (MAX >= id) {

            queueInserir.put(id++);

            new Thread(inserir).start();
            new Thread(atualizar).start();
            new Thread(deletar).start();

        }
    }


}
