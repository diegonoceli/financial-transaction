# financial-transaction

## Descrição
Este é um projeto que demonstra como construir e executar um sistema usando Java com Spring Boot e Docker Compose. Ele fornece uma API REST para operações bancárias, como transferência entre contas.

## Pré-requisitos
- Docker
- Docker Compose
- cURL (opcional, apenas para fazer chamadas de teste ao endpoint localmente)

## Como usar

### 1. Clonar o repositório
git clone https://github.com/seu-usuario/meu-projeto.git
cd meu-projeto

### 2. Executar o Docker Compose
docker-compose up

Isso criará e iniciará os containers Docker para o aplicativo e quaisquer serviços necessários.

### 3. Fazer uma chamada de teste ao endpoint

Você pode usar cURL ou qualquer ferramenta de cliente HTTP para fazer chamadas de teste ao endpoint localmente. Abaixo está um exemplo de como fazer isso usando cURL:

curl --location 'http://localhost:8080/transfer' \
--header 'Content-Type: application/json' \
--data '{
"accountId": "123",
"transactionAmount": 30.0
}'

Isso enviará uma solicitação POST para http://localhost:8080/transfer com um corpo JSON contendo os detalhes da transferência.
