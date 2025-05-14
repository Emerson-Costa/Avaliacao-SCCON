<h1 align="center">Avaliação Java: SCCON</h1>

## Sobre o Projeto

Este projeto consiste em uma avaliação prática de Java para a SCCON. O objetivo é demonstrar conhecimentos em desenvolvimento backend utilizando Java, seguindo boas práticas de programação, organização de código e modelagem de dados.


## End-Points

   *  **1- GET /person**
      * Retorna uma lista de todas as pessoas cadastradas, ordenadas alfabeticamente pelo nome.

   * **2- GET /person/{id}**
      * Busca uma pessoa pelo seu ID.
   
   * **3- POST /person**
      * Cria uma nova pessoa a partir dos dados enviados.

   * **4- DELETE /person/{id}**
      * Remove uma pessoa pelo ID.

   * **5- PUT /person/{id}**
      * Atualiza todos os dados de uma pessoa pelo ID.

   * **6- PATCH /person/{id}**
      * Atualiza parcialmente os dados de uma pessoa pelo ID (apenas os atributos enviados).

   * **7- GET /person/{id}/age?output={output}**
      * Calcula a idade da pessoa em dias, meses ou anos.

   * **8- GET /person/{id}/age?output={output}**
      * Calcula o salário atual da pessoa, podendo retornar o valor total ou em múltiplos de salário mínimo.

## Como executar?

   * Utilize a IDE de sua preferência para executar o projeto.
   * A Base de dados utiliada neste projeto é o "H2 Console".
     * A configuração do mesmo se encontra em aberto sem autenticação.
   * A porta que roda o projeto é a '8081'.
     * https://localhost:8081
   * Utilize para realizar os teste o POSTMAM ou INSOMNIA.

# Considerações finais.

Foi muito gratificante realizar este desafio, pois fazia algum tempo que eu não resolvia problemas como esse. Apesar de serem simples, levei aproximadamente 2 dias para resolvê-los. A dificuldade que tive durante o desenvolvimento deste projeto foram os últimos end-points, pois a contagem dos dias e os cálculos referentes ao salário mínimo exigiram um pouco mais de reflexão antes de colocar a mão na massa. Mas consegui resolver com precisão, sem ajuda de IA, pois aceitei este projeto para colocar em prática minhas habilidades como desenvolvedor de software.