# Backend desafio Petshop DTI

<p align="center">
  <img src="https://github.com/ViniciusLAAraujo/DTIPetshopFinder/assets/90988825/69c87ef2-e2f7-4805-8bd0-36e2396d6185" alt="logo" width="250" height="250">
</p>

Este é o backend do projeto Petshop, que é responsável por fornecer informações sobre petshops e encontrar o melhor petshop com base em parâmetros de preço e distância para uma aplicação React.

## Tabela de Conteúdos

- [Instruções para Executar o Sistema](#instruções-para-executar-o-sistema)
- [Premissas Assumidas](#premissas-assumidas)
- [Decisões de Projeto](#decisões-de-projeto)
- [Organização de Pastas no Projeto Spring](#organização-de-pastas-no-projeto-spring)
- [API Endpoints](#api-endpoints)
  - [Petshop Endpoints](#petshop-endpoints)
    - [findAll() / Encontra todos](#findall--encontra-todos)
    - [findPetshopById() / Encontra um por id](#findpetshopbyid--encontra-um-por-id)
    - [savePetshop() / Cria ou Atualiza](#savepetshop--cria-ou-atualiza)
    - [deletePetshop() / Deleta](#deletepetshop--deleta)
    - [findBest() / Encontra o melhor](#findbest--encontra-o-melhor)
    - [findBestList() / Encontra lista ordenada dos melhores](#findbestlist--encontra-lista-ordenada-dos-melhores)
  - [Data Transfer Objects (DTOs)](#data-transfer-objects-dtos)
    - [PetshopDTO](#petshopdto)
    - [BestPetShopDTO](#bestpetshopdto)
    - [SearchDTO](#searchdto)
- [Logo](#logo)

## Instruções para Executar o Sistema

Certifique-se de que você possui o ambiente de desenvolvimento Java e o Spring Boot configurados.

1. Abra um terminal na pasta raiz do projeto.

2. Execute o comando para inicializar o servidor Spring Boot.
```bash
mvn spring-boot:run
```
   O servidor estará disponível em `http://localhost:8080` por padrão.

## Premissas Assumidas

1. **Tipos de Banho**: Os petshops oferecem banhos apenas para cães de porte pequeno ou grande.

2. **Variação de Preços**: Os preços dos petshops podem variar entre dias úteis (weekdays) e fins de semana (weekend). Não há consideração para feriados ou fusos horários.

3. **Acréscimo nos Preços**: O percentual de acréscimo nos preços de fim de semana já está incluído nos preços cadastrados. Por exemplo, se o preço de banho é aumentado em 20%, isso significa que o preço unitário para cães pequenos é incrementado em 20%, e o mesmo se aplica aos cães grandes. Esse acréscimo é aplicado individualmente a cada preço, não sobre o preço total. Em situações do mundo real, essa ambiguidade precisaria ser esclarecida antes da construção do modelo.

4. **Autenticação Não Necessária**: Não é necessário autenticação para acessar o sistema. O CORS (Cross-Origin Resource Sharing) também está habilitado para permitir solicitações de diferentes origens.

## Decisões de Projeto

Algumas decisões de projeto importantes incluem:

- Utilização do Java Spring Boot como framework para criação do backend
- Armazenamento dos dados dos petshops em um banco de dados usando o JPA (Java Persistence API).
- Uso do H2 Database devido à sua facilidade de configuração e alta velocidade, tornando-o ideal para desenvolvimento rápido e testes.
- Implementação de endpoints REST para listar todos os petshops, encontrar um petshop por ID, salvar um novo petshop e calcular o melhor petshop com base nos parâmetros de busca.

## Organização de Pastas no Projeto Spring

No diretório `src/main/...`, nossa aplicação Spring segue uma estrutura organizada para manter a clareza e a modularidade do código. Aqui está uma breve descrição de cada pasta:

- **controllers**: controladores, que são responsáveis por lidar com as requisições HTTP, interagem com os serviços por meio de DTOs (Data Transfer Objects).

- **dtos**: contém os Data Transfer Objects, que são utilizados para transferir dados entre o controlador e o serviço.

- **entities**: entidades, representam os modelos de dados do nosso aplicativo. As entidades são mapeadas para tabelas no banco de dados e são acessadas por meio dos repositórios.

- **repositories**: Os repositórios são responsáveis por interagir com o banco de dados.

- **services**: contém os serviços da aplicação, que encapsulam a lógica de negócios. Eles utilizam os repositórios para acessar os dados e podem ser chamados pelos controladores para processar as solicitações dos clientes.

No diretório `src/test/...`, pode ser encontrado a classe de teste do serviço que realiza a lógica principal do desafio.

Testes unitários validados
<p align="center">
  <img src="https://github.com/ViniciusLAAraujo/DTIPetshopFinder/assets/90988825/422f24c2-bcb3-436c-b579-290bfc099be0" alt="tests" width="200" height="200">
</p>

## API Endpoints

### Petshop Endpoints

#### findAll() / Encontra todos

- **URL:** `/petshop`
- **Método:** GET
- **Descrição:** Retorna uma lista de todos os petshops do banco.
- **Resposta:** JSON de [PetshopDTO](#petshopdto).

#### findPetshopById() / Encontra um por id

- **URL:** `/petshop/{petshopId}`
- **Método:** GET
- **Descrição:** Retorna um petshop pelo seu Id.
- **Parameters:** `petshopId` (Long) - Petshop ID buscado.
- **Resposta:** [PetshopDTO](#petshopdto).

#### savePetshop() / Cria ou Atualiza

- **URL:** `/petshop/upsert`
- **Método:** POST
- **Descrição:** Cria ou Atualiza um petshop.
- **Request Body:** [PetshopDTO](#petshopdto) - Atributos do petshop a ser inserido ou modificado
- **Resposta:** [PetshopDTO](#petshopdto).

#### deletePetshop() / Deleta

- **URL:** `/petshop/delete/{petshopId}`
- **Método:** DELETE
- **Descrição:** Deleta o petshop pelo seu Id.
- **Parameters:** `petshopId` (Long) - Petshop ID a ser deletado.
- **Resposta:** HTTP Status 200 (OK) sem body.

#### findBest() / Encontra o melhor

- **URL:** `/petshop/best`
- **Método:** POST
- **Descrição:** Encontra o melhor petshop segundo preço em conta e proximidade para o dia, e numero de cães pedidos.
- **Request Body:** [SearchDTO](#searchdto) - Data-Numero de cães pequenos-Numero de cães grandes.
- **Resposta:** [BestPetShopDTO](#bestpetshopdto).

#### findBestList() / Encontra lista ordenada dos melhores

- **URL:** `/petshop/bestlist`
- **Método:** POST
- **Descrição:** Retorna a lista com todos os petshops ordenados pelos criterios de preço e proximidade.
- **Request Body:** [SearchDTO](#searchdto) - Data-Numero de cães pequenos-Numero de cães grandes.
- **Resposta:** JSON de [BestPetShopDTO](#bestpetshopdto).

### Data Transfer Objects (DTOs)

#### PetshopDTO

- **Attributes:**
  - `id` (Long) - Identificador de petshop.
  - `name` (String) - Nome do petshop.
  - `kmDistance` (Double) - Distancia em km do petshop.
  - `weekdayPriceSmallDog` (BigDecimal) - Perço de cães pequenos em dias de semana.
  - `weekdayPriceBigDog` (BigDecimal) - Perço de cães grandes em dias de semana.
  - `weekendPriceSmallDog` (BigDecimal) - Perço de cães pequenos em dias de final de semana.
  - `weekendPriceBigDog` (BigDecimal) - Perço de cães grandes em dias de final de semana.

#### BestPetShopDTO

- **Attributes:**
  - `id` (Long) - Identificador de petshop.
  - `name` (String) - Nome do petshop.
  - `kmDistance` (Double) - Distancia em km do petshop.
  - `totalAmount` (BigDecimal) - Perço total a se pagar para os parametros enviados.
  - `smallDogAmount` (BigDecimal) - Perço total a se pagar para os parametros enviados apenas para os cães pequenos.
  - `bigDogAmount` (BigDecimal) - Perço total a se pagar para os parametros enviados apenas para os cães grandes.

#### SearchDTO

- **Attributes:**
  - `date` (LocalDate) - Data do banho desejado.
  - `numSmallDog` (Integer) - Numero de cães pequenos desejados.
  - `numBigDog` (Integer) - Numero de cães grandes desejados.

### Logo

- A logo do site foi criada a partir do Bing Image Creator disponivel em : https://www.bing.com/create
