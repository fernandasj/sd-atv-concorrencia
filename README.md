# Threads e Concorrência

### Atividade implementada para a disciplina de Sistemas Distribuídos, do curso: Análise e Desenvolvimento de Sistemas - IFPB Campus Cajazeiras

Para resolver a atividade, foram implementados vários que iram utilizar Threads para inserir dados em um banco de dados.

**Tabela:** USUARIO

**Campos:** ID e NOME

# Resultados obtidos:
* Número de Threads: 100

* Tempo de resposta:

```
1° - SelectService: 16ms

2° - UseThreadService: 70ms

3° - SimpleService: 3420ms

4° - BlockingQueuePutService: 4233ms
```
