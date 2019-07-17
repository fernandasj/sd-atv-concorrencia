# Threads e Concorrência

### Atividade implementada para a disciplina de Sistemas Distribuídos, do curso: Análise e Desenvolvimento de Sistemas - IFPB Campus Cajazeiras

Para resolver a atividade, foram implementados vários que iram utilizar Threads para inserir dados em um banco de dados.

**Tabela:** USUARIO

**Campos:** ID e NOME

# Resultados obtidos:
* Número de Threads utilizado: 100

* Tempo de resposta:

```
- SelectService: 16ms

- SimpleService: 3420ms

- UseThreadService: 70ms

- BlockingQueuePutService: 4233ms
```
