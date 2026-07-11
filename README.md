# Library Management Software

Sistema de gerenciamento de biblioteca desenvolvido em Java. Este projeto está sendo construído em etapas, com o objetivo de consolidar conceitos de arquitetura, qualidade de código e evolução de software.

Atualmente, o sistema roda via terminal (CLI) com foco estrito na estruturação das regras de negócio, integridade dos dados e tratamento de erros.

## 🛠️ Tecnologias Utilizadas
* **Java** (Lógica de negócio e validações)
* **Maven** (Gerenciamento de dependências e build do projeto)
* **Git/GitHub** (Versionamento de código)

## ⚙️ Funcionalidades Atuais
* **Cadastro de Usuários e Livros:** Interface interativa via terminal.
* **Validações com Regex:** Filtros rigorosos para blindar a entrada de dados (ex: o campo "Nome" aceita apenas letras e espaços; "Celular" e "CPF" exigem tamanhos exatos e apenas números).
* **Tratamento de Erros Customizado:** Implementação de exceções próprias (como `CpfInvalidoException` e `NomeInvalidoException`) aliadas a blocos `try-catch` para impedir a interrupção abrupta da aplicação.
* **Isolamento de Responsabilidades:** Regras de negócio centralizadas na camada de serviço (ex: `UserService`).

## 🗺️ Roadmap (Próximos Passos)
O projeto seguirá uma evolução arquitetural dividida nas seguintes fases:

- [x] **Fase 1:** Regras de negócio, validações (Regex) e CLI.
- [ ] **Fase 2:** Integração com Banco de Dados SQL utilizando JDBC puro.
- [ ] **Fase 3:** Refatoração e migração da aplicação para o ecossistema Spring Boot.
- [ ] **Fase 4:** Desenvolvimento e integração de uma interface Web (Front-end).

## 🚀 Como Executar

1. Clone este repositório:
   ```bash
   git clone https://github.com/MatheusTudisco/library-management-software.git
2. Navegue até o diretório do projeto:
    ```bash
   cd library-management-software
3. Compile o projeto utilizando o Maven:
    ```bash
   mvn clean compile
4. Execute a aplicação através da sua IDE de preferência ou via terminal apontando para a classe principal.