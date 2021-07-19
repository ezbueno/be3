<p align="center">🚀 API RESTful para cadastro de pacientes e convênios</p>

## Tecnologias:
![JAVA](https://img.shields.io/static/v1?label=JAVA&message=BACKEND&color=0091EA&style=flat&logo=JAVA)
![SPRING](https://img.shields.io/static/v1?label=Spring&message=FRAMEWORK&color=0091EA&style=flat&logo=Spring)

## Ferramentas utilizadas na construção do projeto:
* [**Git**](https://git-scm.com/)
* [**Java 11**](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
* [**Spring Tool Suite**](https://spring.io/tools)
* [**Dbeaver**](https://dbeaver.io/)

## Funcionalidades da aplicação:
* Cadastro de pacientes e seus respectivos convênios.
* Alterar dados do paciente / convênio existente na base de dados.
* Buscar todos os pacientes / convênios.
* Buscar um determinado paciente / convênio por ID.
* O usuário não tem permissão para realizar a exclusão do cadastro.
* Convênio vinculado à um paciente também não pode ser deletado.
* A aplicação não permite cadastros duplicados utilizando o CPF.
* Erros de validação são mostrados com mensagens customizadas ao usuário.

## Resumo:
* A aplicação foi projetada para realizar cadastro de pacientes, não sendo possível cadastrar dados duplicados utilizando o CPF. Para cadastrar um convênio com seus respectivos dados é necessário informar o ID do paciente, não sendo possível cadastrar um convênio se não houver um paciente cadastrado.

## Importação do projeto:
* O projeto deve ser importado como sendo um projeto Maven.
* Necessário ter o Java 11 configurado.

## Como gravar as informações na base de dados:
* Com a aplicação em execução, os dados podem ser persistidos tanto com o H2 DataBase quanto com o SQL Server.
* Para persistir os dados utilizando o H2, abra o arquivo application.properties e deixe essa propriedade "spring.profiles.active" como test. Ex: (spring.profiles.active=test). Para visualizar os dados persistidos no banco de dados, cole essa URL no navegador. (http://localhost:8080/h2-console)
* Para persistir os dados utilizando o SQL Server, abra o arquivo application.properties e deixe essa propriedade "spring.profiles.active" como dev. Ex: (spring.profiles.active=dev).

## Como executar os testes das requisições:
* Importante: A aplicação deve estar em execução no ambiente local.
* Se os testes forem realizados via OpenAPi, basta colar essa URL no navegador. (http://localhost:8080/cadastro/swagger-ui.html).
* Se os testes forem realizados via Postman, abra o arquivo application.properties e deixe comentado essa configuração: server.servlet.context-path=/cadastro. Dessa forma: #server.servlet.context-path=/cadastro. Em seguida, basta seguir o passo a passo mostrado abaixo:

## Cadastrar, listar e editar dados do paciente:
* GET: http://localhost:8080/pacientes . É realizado a busca de todos os pacientes cadastrados.

* GET: http://localhost:8080/pacientes/1 . É realizado a busca de um determinado paciente informando o seu Id. Neste caso, foi informado o Id: 1 como exemplo.

* POST: http://localhost:8080/pacientes. É realizado o cadastro do paciente. Os dados devem ser enviados no formato JSON. Os campos são:
{
    "nome": "",
    "sobrenome": "",
    "dataNascimento": "",
    "genero": "",
    "cpf": "",
    "rg": "",
    "uf": "",
    "email": "",
    "celular": "",
    "telefone": ""
}

* PUT: http://localhost:8080/pacientes/1. É realizado a atualização dos dados do paciente, onde é necessário informar o seu Id. Neste caso, foi informado o Id: 1, como exemplo. Os dados devem ser enviados no formato JSON. Os campos são:
{
    "nome": "",
    "sobrenome": "",
    "dataNascimento": "",
    "genero": "",
    "rg": "",
    "uf": "",
    "email": "",
    "celular": "",
    "telefone": ""
}

* DELETE: http://localhost:8080/pacientes/1. Se o usuário tentar deletar um determinado paciente, será exibido uma mensagem customizada, pois o mesmo não tem permissão para excluir um cadastro. No exemplo, o usuário tentou deletar o paciente com o Id: 1.


## Cadastrar, listar e editar dados do convênio:
* GET: http://localhost:8080/convenios . É realizado a busca de todos os convênios cadastrados.

* GET: http://localhost:8080/convenios/1 . É realizado a busca de um determinado convênio informando o seu Id. Neste caso, foi usado o Id: 1 como exemplo.

* POST: http://localhost:8080/convenios?paciente=1. É realizado o cadastro do convênio. Para a realização do cadastro, é necessário informar o Id do paciente. Neste caso, foi informado o Id: 1, como exemplo. Os dados devem ser enviados no formato JSON. Os campos são:
{
    "numCarteirinhaConvenio": ,
    "validadeCarteirinha": ""
}

* PUT: http://localhost:8080/convenios/1. É realizado a atualização dos dados do paciente, onde é necessário informar o seu Id. Neste caso, foi informado o Id: 1 como exemplo. Os dados devem ser enviados no formato JSON. Os campos são:
{
    "numCarteirinhaConvenio": ,
    "validadeCarteirinha": ""
}

* DELETE: http://localhost:8080/convenios/1. Se o usuário tentar deletar um determinado convênio, será exibido uma mensagem customizada, pois não é possível deletar um convênio que esteja associado à um paciente. No exemplo, o usuário tentou deletar o convênio com o Id: 1.


# Observação:
* Foi utilizado a ferramenta DBeaver para gerenciamento do banco de dados SQL Server. As configurações para conexão com este banco de dados foi feito no arquivo "application-dev.properties".
