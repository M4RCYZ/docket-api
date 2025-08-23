# Docket API - Backend com Spring Boot

![Status do Projeto](https://img.shields.io/badge/status-em%20desenvolvimento-yellow)

API RESTful completa para um gerenciador de tarefas, construída com Java e Spring Boot. Este projeto serve como a base de back-end para uma aplicação de lista de tarefas (To-Do List), permitindo o cadastro de usuários e o gerenciamento de tarefas de forma segura e individual.

---

## 🚀 Features

-   **Gerenciamento de Usuários:**
    -   Cadastro de novos usuários.
    -   Segurança de senhas com criptografia **BCrypt**.
-   **Autenticação e Autorização:**
    -   Sistema de autenticação `stateless` via **HTTP Basic Auth**.
    -   Rotas protegidas com **Spring Security**, garantindo que um usuário só possa acessar e manipular suas próprias tarefas.
-   **Gerenciamento de Tarefas (CRUD Completo):**
    -   Criação, listagem, atualização e exclusão de tarefas.
    -   As tarefas são automaticamente associadas ao usuário autenticado.
-   **Validação de Dados:**
    -   Regras de negócio e validação de dados de entrada na criação de tarefas (ex: datas, tamanho de campos).
-   **Design de API:**
    -   Uso do padrão **DTO (Data Transfer Object)** para desacoplar a API da camada de persistência e garantir respostas limpas.
-   **Documentação:**
    -   Documentação interativa da API gerada com **Swagger (OpenAPI 3)**.
-   **Banco de Dados:**
    -   Persistência de dados utilizando um banco de dados **PostgreSQL**.

---

## 🛠️ Tecnologias Utilizadas

-   **Linguagem:** Java 17+
-   **Framework:** Spring Boot 3
-   **Módulos Spring:** Spring Web, Spring Security, Spring Data JPA
-   **Banco de Dados:** PostgreSQL
-   **Persistência:** Hibernate
-   **Build & Dependências:** Maven
-   **Segurança:** BCrypt Password Encoding
-   **Documentação:** Springdoc OpenAPI (Swagger)
-   **Testes:** Postman

---

## ⚙️ Como Rodar o Projeto Localmente

### Pré-requisitos

-   [Java 17+](https://www.oracle.com/java/technologies/downloads/)
-   [Maven 3.8+](https://maven.apache.org/download.cgi)
-   [PostgreSQL](https://www.postgresql.org/download/)
-   [Git](https://git-scm.com/downloads)

### Passo a Passo

1.  **Clone o repositório:**
    ```bash
    git clone [git@github.com:M4RCYZ/docket-api.git](https://github.com/M4RCYZ/todolist-springboot-api.git)
    cd todolist-springboot-api
    ```

2.  **Configure o Banco de Dados:**
    -   Certifique-se de que o PostgreSQL está rodando.
    -   Crie um novo banco de dados chamado `todolist`.

3.  **Configure as Variáveis de Ambiente:**
    -   Na pasta `src/main/resources`, renomeie o arquivo `application.yml.example` para `application.yml`.
    -   Abra o arquivo `application.yml` e preencha o campo `password` com a senha do seu usuário `postgres`.

4.  **Execute a aplicação:**
    ```bash
    ./mvnw spring-boot:run
    ```

5.  **Acesse a aplicação:**
    -   A API estará rodando em `http://localhost:8080`.
    -   A documentação do Swagger pode ser acessada em `http://localhost:8080/swagger-ui.html`.
