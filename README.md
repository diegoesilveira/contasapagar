# Contas a Pagar

Este é um sistema de gestão de contas a pagar, desenvolvido em Java 17 usando Spring Boot 3, PostgreSQL, Docker e Docker Compose.

## Funcionalidades

- CRUD de contas a pagar
- Filtragem de contas por data de vencimento e descrição
- Importação de contas a partir de um arquivo CSV
- Autenticação e autorização
- Relatórios de valores pagos em um período

## Tecnologias Utilizadas

- Java 17
- Spring Boot 3
- PostgreSQL
- Docker
- Docker Compose
- Flyway

## Estrutura do Projeto

- `src/main/java`: Código-fonte principal
- `src/main/resources`: Arquivos de configuração e scripts de migração
- `Dockerfile`: Para criar a imagem Docker da aplicação
- `docker-compose.yml`: Para orquestrar os contêineres Docker

## Configuração

### Pré-requisitos

- Docker
- Docker Compose

### Passos para Rodar a Aplicação

1. Clone o repositório:
    ```sh
    git clone https://github.com/seu-usuario/contasapagar.git
    cd contasapagar
    ```

2. Compile o projeto e construa o jar:
    ```sh
    mvn clean package -DskipTests
    ```

3. Construa e inicie os contêineres Docker:
    ```sh
    docker-compose up --build
    ```

4. Acesse a aplicação em [http://localhost:8080](http://localhost:8080).

### Variáveis de Ambiente

- `SPRING_DATASOURCE_URL`: URL de conexão com o banco de dados
- `SPRING_DATASOURCE_USERNAME`: Nome de usuário do banco de dados
- `SPRING_DATASOURCE_PASSWORD`: Senha do banco de dados

## Endpoints da API

### Contas

- `POST /contas`: Cria uma nova conta
- `PUT /contas/{id}`: Atualiza uma conta existente
- `GET /contas/{id}`: Obtém uma conta por ID
- `GET /contas`: Lista contas com filtro
- `PATCH /contas/{id}/situacao`: Altera a situação de uma conta
- `POST /contas/import`: Importa contas a partir de um CSV
- `GET /contas/valorTotal`: Obtém o valor total pago em um período

## Autenticação

O sistema utiliza autenticação JWT. 

### Endpoints de Autenticação

- `POST /auth/register`: Registra um novo usuário
- `POST /auth/login`: Realiza login e retorna um token JWT