Implementação desenvolvida para carater avaliativo para vaga de desenvolvedor Back-End

Porta a qual a aplicação esta disponível 8085

* Configurações necessárias para o funcionamento da aplicação.
Editar o arquivo config.properties em .\src\main\resources
configurar os seguintes parametro conforme a necessidade:
- tamanhoFila: campo que determina a quantidade de mensagens na fila;
- quantidadeProdutorChat: campo contendo a quantidade de threads do produtor de mensagens do tipo chat;
- tempoExecucaoProdutorChat: campo com o tempo em que o produtor de mensagens do tipo chat vai executar enquanto a fila não estiver cheia (tempo em segundos);
- quantidadeProdutorEmail: campo contendo a quantidade de threads do produtor de mensagens do tipo email;
- tempoExecucaoProdutorEmail: campo com o tempo em que o produtor de mensagens do tipo email vai executar enquanto a fila não estiver cheia (tempo em segundos);
- quantidadeProdutorVoz: campo contendo a quantidade de threads do produtor de mensagens do tipo voz;
- tempoExecucaoProdutorVoz: campo com o tempo em que o produtor de mensagens do tipo voz vai executar enquanto a fila não estiver cheia (tempo em segundos);
- quantidadeConsumidor: campo contendo a quantidade de threads do consumidor;

Todos os produtores irão executar durante o tempo informado nos seus repectivos campos vai parar e aguardar que a fila esteja vazia,
que todas as mensagens tenhão sida consumidas, e reiniciará a produção.

Para consultar os endpoints foi implementado a API de documentação Swagger, disponivel em http://localhost:8085/swagger-ui/index.html