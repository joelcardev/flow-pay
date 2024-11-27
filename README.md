Customer Request Management System

Um sistema para gerenciar solicitações de clientes de forma assíncrona, distribuindo-as para agentes disponíveis e processando-as em uma fila com prioridade.

Tecnologias Utilizadas
Java 17
Spring Boot
RabbitMQ (mensageria)
JPA/Hibernate (persistência de dados)
H2 (banco de dados)
Lombok (redução de boilerplate)
Docker (containerização)
Fluxo Resumido

Criação de Solicitação:

O cliente cria uma solicitação com status inicial PENDING, que é colocada na fila: http://localhost:8080/api/v1/support-distribution/request
Processamento Assíncrono:

Um listener verifica a disponibilidade de agentes:

Sem agente: Solicitação permanece PENDING.
Com agente: Solicitação atribuída e status muda para IN_PROGRESS. http://localhost:8080/api/v1/agents/finalize-request/{{requestId}}
Finalização de Solicitação:

A solicitação é finalizada (COMPLETED).
O sistema atribui automaticamente outra solicitação pendente ao mesmo agente, se disponível.

Como Rodar o Projeto
Pré-requisitos
Docker e Docker Compose instalados.
Java 17 instalado.
RabbitMQ e H2 configurados (ou usar Docker Compose abaixo).
