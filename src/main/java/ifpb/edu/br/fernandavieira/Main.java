package ifpb.edu.br.fernandavieira;

import ifpb.edu.br.fernandavieira.service.*;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException, InterruptedException {

        //Instanciando serviços

        SelectService selectService = new SelectService();
        SimpleService simpleService = new SimpleService();
        BlockingQueuePutService blockingQueuePutService = new BlockingQueuePutService();
        UseThreadService useThreadService = new UseThreadService();
        TruncateService truncateService = new TruncateService();

        //Realizando uma busca simples
        selectService.buscar();

        //Limpando tabela Usuario do banco de dados
        truncateService.truncade();

        //Realizando as operaçoes: INSERIR, ATUALIZAR E DELETAR com o Serviço Simples
        simpleService.inserirAtualizarDeletar();

        //Limpando tabela Usuario do banco de dados
        truncateService.truncade();

        //limpando tabela Usuario do banco de dados
        //truncateService.truncade();

        //Utilizando fila bloqueante realizando PUT
        blockingQueuePutService.blockingQueuePut();

        //Limpando tabela Usuario do banco de dados
        truncateService.truncade();

        //Utilizando serviço de THREADS
        useThreadService.comThreads();

        //Limpando tabela Usuario do banco de dados
        truncateService.truncade();

    }
}
