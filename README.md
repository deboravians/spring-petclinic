# Refatoração com Padrões de Projeto: Spring PetClinic

Trabalho 1 da disciplina **QXD0068 – Reuso de Software** (UFC Quixadá), prof. Francisco Victor da Silva Pinheiro.

Fork do [Spring PetClinic](https://github.com/spring-projects/spring-petclinic), sistema de gestão de uma clínica veterinária (donos, pets, visitas e veterinários), refatorado com quatro padrões de projeto GoF.

## Integrantes

- Debora Silva Viana (557337)
- Anaildo do Nascimento Silva (552836)

## Versões

| Versão | Onde está |
|---|---|
| Original | tag [`v1.0-original`](../../tree/v1.0-original) |
| Refatorada | tag [`v2.0-refatorada`](../../tree/v2.0-refatorada) (branch `main`) |

Cada padrão foi aplicado em um commit separado:

| Padrão | Commit | Principais classes |
|---|---|---|
| Strategy | `a881fdf` | `PetValidationRule`, `PetValidationRules`, `PetValidator` |
| Template Method | `7fa98e5` | `PetFormProcessor`, `CreatePetFormProcessor`, `UpdatePetFormProcessor` |
| Builder | `a2083d5` | `OwnerBuilder`, `PetBuilder`, `VisitBuilder` (em `src/test`) |
| Facade | `9bcdc21` | `ClinicFacade` |

Para ver todas as mudanças: `git diff v1.0-original v2.0-refatorada`.

## Relatório

O relatório técnico está em [`docs/Relatório PetClinic Final.pdf`](docs/Relat%C3%B3rio%20PetClinic%20Final.pdf).

## Como executar

Pré-requisitos: Git e Java 17 ou superior.

```bash
git clone https://github.com/deboravians/spring-petclinic.git
cd spring-petclinic

./mvnw spring-javaformat:apply   # ajusta a formatação exigida pelo build
./mvnw test                      # roda os testes
./mvnw spring-boot:run           # sobe a aplicação
```

A aplicação fica disponível em http://localhost:8080. O banco padrão é o H2 em memória, então não é preciso configurar nada. No Windows, use `mvnw.cmd` no lugar de `./mvnw`.

Para abrir no IntelliJ: *File → Open*, selecione o `pom.xml` e escolha *Open as Project*.

Os testes de integração com MySQL e PostgreSQL são ignorados quando o Docker não está disponível.