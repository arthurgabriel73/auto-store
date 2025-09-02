![auto-store](docs/auto-store-api.png)

## Introdução

A **AutoStoreApi** é uma aplicação backend desenvolvida aplicando: Arquitetura de Microsserviços, Saga Orquestrado.
O objetivo é gerir o estoque de uma loja de automóveis, ordens de compra e vendas, e controle de clientes.
O ponto-chave da arquitetura proposta é a resiliência e consistência dos dados, mesmo em cenários de falhas,
que conseguimos graças ao controle de transações distribuídas utilizando o padrão Saga Orquestrado.

### Video Apresentação

*In Progress...*

[![Watch the video](https://img.youtube.com/vi/0b1g2a3c4d5e/maxresdefault.jpg)](https://www.youtube.com/watch?v=1234567890)

## Summary

* [Technology Stack](#technology-stack)
* [Architecture](#architecture)
* [Code Design](#code-design)
* [CI/CD](#cicd)
* [Executing the project](#executing-the-project)
* [API Documentation](#api-documentation)
* [Testing](#testing)

## Technology Stack

* **Java 17**
* **Spring Boot 3**
* **Apache Kafka**
* **API REST**
* **PostgreSQL**
* **MongoDB**
* **Docker**
* **docker-compose**
* **Redpanda Console**

## Architecture

![SD](docs/SD.png)

## Code Design

O código foi implementado utilizando Arquitetura Hexagonal (Ports and Adapters), seguindo os princípios do SOLID e boas
práticas de Clean Code.

### Camadas da Arquitetura

* **Domain**: Contém as entidades, agregados, repositórios e serviços de domínio. Esta camada é independente de
  qualquer tecnologia ou ‘framework’. Nesse projeto, a única dependência externa é a biblioteca Lombok, utilizada
  para reduzir o boilerplate code, que pode ser facilmente removida posteriormente sem impactar no comportamento da
  aplicação.

####

* **Application**: Contém as ports (‘interfaces’) e casos de uso (‘use cases’) da aplicação. Essa camada orquestra a
  lógica de
  aplicação, coordenando as operações entre a camada de domínio e as camadas externas.

####

* **Adapters**: Contém as implementações concretas das Ports definidas na camada de aplicação, tanto as
  implementações REST (Controllers) e as implementações de Eventos (Kafka Consumers), para os Driven Adapters.
  Quanto as implementações dos repositórios que interagem com o banco de dados e os Event Producers (Kafka Producers)
  sendo os Driven Adapters.

####

* **Configuration**: Contém as classes de configuração da aplicação, como configuração de exceções, segurança,
  banco de dados, Kafka, etc.

## CI/CD

O projeto utiliza GitHub Actions para integração contínua (CI). O pipeline de testes é
acionado em cada push ou pull request para a branch main, porém a branch main está protegida para evitar
commits diretos, garantindo que todas as alterações passem por revisão de código os testes automatizados.

## Executing the project

### Execution Steps

### Accessing the API

[Go to page beginning](#introdução)

### Testing Best Practices
