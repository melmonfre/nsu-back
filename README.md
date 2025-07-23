# Pré-requisitos

- Java 17 ou superior
- Maven
- Banco de dados configurado

# Como executar o projeto

# Compilar e instalar dependências

mvn clean install

Este comando limpa o diretório de build, compila o projeto e instala as dependências no repositório local.

# Executar a aplicação

mvn spring-boot:run

Este comando inicia o servidor Spring Boot, disponibilizando a aplicação localmente (em http://localhost:8080).

# Executar testes

mvn test

Este comando executa os testes unitários e de integração configurados no projeto.

# Endpoints da API

A API está localizada no pacote com.desafiotecnico.nsu.controller.impl. Os endpoints disponíveis são:

# GET /api/creditos/{numeroNfse}

Retorna uma lista de créditos associados ao numeroNfse informado.

- Exemplo: GET http://localhost:8080/api/creditos/12345
- Resposta: Lista de CreditoResponseDTO.

# GET /api/creditos/credito/{numeroCredito}

Retorna um crédito específico com base no numeroCredito informado.

- Exemplo: GET http://localhost:8080/api/creditos/credito/67890
- Resposta: Objeto CreditoResponseDTO.

## Docker

Para rodar o container dockerizado, execute os comandos:

./mvnw clean package -DskipTests
docker build -t nsu-app .
docker compose up -d
docker run --network=host --name nsu-app nsu-app

(Assegure-se de que o docker está adequadamente instalado)

