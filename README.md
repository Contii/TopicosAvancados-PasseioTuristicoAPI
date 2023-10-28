# API de Gerenciamento do Sistema Turístico
A API de Gerenciamento do Sistema Turístico é uma aplicação Java em  Spring Boot que desempenha um papel crucial no sistema de gerenciamento de pacotes turísticos. Ela foi projetada para servir como um microsserviço que lida com diversas operações relacionadas a pessoas, passeios, pacotes, pagamentos e outras entidades.

## Visão Geral
A API é uma parte integrante do ecossistema de microsserviços composto por três componentes principais:

- **API Java:** Responsável por gerenciar informações e operações relacionadas a pessoas, passeios, pacotes, pagamentos e outras entidades no sistema.
- **API Node:** Responsável por registrar as avaliações de clientes sobre os passeios.
- **API React:** Responsável por exibir ao usuário as informações fornecidas pelas APIs Node e Java, criando uma integração harmoniosa de microsserviços.

## Tecnologias Utilizadas

A API de Gerenciamento do Sistema Turístico foi desenvolvida com o objetivo de criar uma aplicação eficaz e eficiente para o gerenciamento de diversas funcionalidades, incluindo pessoas, passeios, pagamentos e outras entidades do sistema. O uso de tecnologias específicas foi uma escolha estratégica para alcançar esses objetivos:

- **Java 17:** A linguagem de programação principal utilizada para desenvolver a aplicação.
- **Gradle:** Sistema de automação de compilação e gerenciamento de dependências.
- **Spring Boot:** Framework que simplifica o desenvolvimento de APIs REST, permitindo um desenvolvimento rápido e confiável.
- **PostgreSQL:** Banco de dados relacional usado para armazenar informações sobre pessoas, passeios, pagamentos e outras entidades.
- **Jakarta EE:** Conjunto de especificações para desenvolvimento de aplicativos empresariais em Java.
- **Lombok:** Biblioteca que simplifica a criação de classes Java, reduzindo a necessidade de escrever código boilerplate, tornando o código mais limpo e eficiente.
- **Swagger:** Ferramenta de documentação e teste de APIs que permite criar documentação interativa dos endpoints da API.

Essas tecnologias foram selecionadas com o intuito de fornecer uma base sólida para o desenvolvimento do sistema turístico, garantindo desempenho, segurança e escalabilidade. O Java 17, aliado ao Spring Boot, proporciona um ambiente confiável para o gerenciamento de informações relacionadas as classes, enquanto o PostgreSQL assegura a integridade dos dados. O uso do Swagger simplifica a documentação e teste dos endpoints da API, melhorando a experiência do usuário e a interação harmoniosa com os microsserviços do ecossistema.

## Como Iniciar

Siga as etapas a seguir para iniciar o servidor da API localmente:

1. Clone este repositório em sua máquina:
```bash
git clone https://github.com/contii/TopicosAvancados-PasseioTuristicoAPI-Node.git
```
2. Navegue até o diretório do projeto:
```bash
cd TopicosAvancados-PasseioTuristicoAPI-Node
```
3. Compile e execute o aplicativo usando Gradle:
```bash
./gradlew bootRun
```
A API estará disponível em http://localhost:8080.

## Endpoints

A API de Gerenciamento do Sistema Turístico oferece os seguintes endpoints para manipulação de informações relacionadas a pessoas, passeios, pacotes, pagamentos e outras entidades:

### Pessoas:

**GET** /person: Recupera as informações de todas as pessoas.

**GET** /person/: id: Recupera informações de uma pessoa específica por ID.

**POST** /person: Insere informações de uma nova pessoa.

**PUT** /person/ i d: Atualiza informações de uma pessoa existente por ID.

**DELETE** /person/: id: Exclui as informações de uma pessoa por ID.

Os end-points de outras classes estão presentes na documentação swagger.

---

© 2023 **Joao Conti** | Este projeto faz parte das implementações da disciplina de Tópicos Avançados em Computação do curso de Ciência da Computação da **UTFPR-MD**, ministrada pelo **Prof. Dr. Ricardo Sobjak.**
