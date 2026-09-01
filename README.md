# Library Management Software

Sistema de gerenciamento de biblioteca desenvolvido em Java. O projeto foca na consolidação de conceitos de arquitetura em camadas, qualidade de código e evolução gradual de um sistema legado CLI para uma arquitetura de API RESTful.

Atualmente, o backend opera com **Spring Boot**, expondo endpoints HTTP para autenticação e operações do sistema, com tratamento centralizado de exceções e persistência de dados.

## 🛠️ Tecnologias Utilizadas
* **Java 17/21/25** (Lógica de negócio e regras de domínio)
* **Spring Boot (Spring Web)** (Estruturação da API REST e injeção de dependências)
* **PostgreSQL / JDBC** (Persistência e comunicação com banco de dados relacional)
* **Maven** (Gerenciamento de dependências e automação de build)
* **Git/GitHub** (Versionamento e fluxo de commits)

## ⚙️ Funcionalidades Atuais
* **Endpoints REST (Controllers):** Rotas HTTP para autenticação/login e gerenciamento de recursos.
* **Validações e Integridade de Dados:** Regras estritas aplicadas na camada de negócio (Regex para CPF, nomes, contato e integridade de dados).
* **Tratamento de Exceções Customizado:** Mapeamento de erros de domínio para respostas HTTP padronizadas.
* **Arquitetura em Camadas:** Separação clara de responsabilidades (`Controller` -> `Service` -> `Repository`).

## 🗺️ Roadmap de Evolução
- [x] **Fase 1:** Regras de negócio, validações (Regex) e interface CLI.
- [x] **Fase 2:** Integração com Banco de Dados SQL utilizando JDBC puro.
- [x] **Fase 3:** Refatoração e migração da arquitetura para Spring Boot (API REST).
- [ ] **Fase 4:** Desenvolvimento e integração com interface Web (Front-end).
- [ ] **Fase 5:** Migração da persistência para Spring Data JPA / Hibernate.

## 🚀 Como Executar Localmente

### Pré-requisitos
* Java JDK instalado (versão 17 ou superior)
* PostgreSQL configurado e em execução
* Bash para envio e recebimento de JSON ou Postman/Insomnia

### Passos

1. Clone o repositório:
   ```bash
   git clone https://github.com/MatheusTudisco/library-management-software.git
   cd library-management-software
   ```
2. Execute a aplicação via Maven Wrapper ou IDE da sua preferência:
   ```bash
   Bash
   ./mvnw spring-boot:run
   
   DOS
   .\mvnw.cmd spring-boot:run
   ```
3. A API estará acessível em:
   ```bash
   http://localhost:8080
   ```