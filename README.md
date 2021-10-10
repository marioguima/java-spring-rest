# Conceitos REST

## REpresentational State Transfer

### REST não é uma tecnologia é um modelo arquitetural
Ele é uma especificação que define a forma de comunicação entre componentes web

Em outras palavras, ele é um modelo arquitetural para implementação de web services

Roy Fielding foi quem definiu essas especificações

Porq eu REST?

. Separação entre cliente e servidor
. Escalabilidade
. Independência de linguagem
. Demanda de mercado

Ele especifica algumas Constraints

- Cliente-servidor

	- Significa que precisamos de um cliente, que pode ser uma aplicação frontend ou até mesmo uma outra aplicação servidora
	- A aplicação cliente e/ou servidora deve poder evoluir de forma independente

- Stateless
	- A aplicação não deve conter estado, ou seja, a requisição enviada ao servidor deve conter tudo que ela precisa para ser processada
	- Entre uma requisição e outra não existe sessão, o servidor não precisa lembrar do cliente, cada requisição é única

- Cache
	- Possibilidade de guardar a resposta em cache
	- Podemos ter prox entre o cliente e o servidor para melhorar a resposta da api

- Interface uniforme
	- Um conjunto de operações bem definidas do sistema
	- Permite desacoplar
	- Devemos usar o padrão dos verbos do HTTP
	- As respostas devem ser padronizadas

- Sistema em camadas
	- Fala sobre a possibilidade de entre o cliente, que consome a api, e o servidor que hospeda a api, ter outros servidores entre eles, que podem ser de sergurança, balanceamento de carga, cache...
	- Essas camadas não devem afetar nem a chamada nem a resposta do servidor
	- Nem mesmo precisam conhecer quantas camadas existem entre eles
	
- Código sob demanda
	- É opcional e muito pouco usada em api, já que não se aplica na maioria dos casos
	- Ela diz que o servidor pode enviar como resposta, algum código que pode ser executado pelo cliente
	
	ex. Pode ser um javascript que é responsável por montar um gráfico, para o cliente executar esse javascript em uma aplicação web e mostrar para o usuário

-----------
Diferença entre REST vs RESTful

REST é o estilo arquitetural que possue as constraints que servem de guia, ou padrão, de implementação

RESTful ou REST API é uma API desenvolvida em conformidade com as constraints REST


-----------
Existem dois tipos de desenvolvedores de REST API

***Puristas e Pragmáticos***

Os puristas defendem que a implementação deve seguir fielmente os princípios REST sem exceções

Se Roy Fielding disse que tem que ser assim, vai ser assim, custe o que custar

Os pragmáticos eles desenvolve a REST API seguindo as constraints também, porém, estão abertos a exceções

Se percebem que seguir uma constraint vai complicar demais, então não vão seguir a risca em favor do desenvolvimento ou uso da API de forma mais simples

------------
Para desenvolver uma REST API é necessário um protocolo de comunicação, porém, não estamos amarrados a nenhum tipo de protocolo, mas precisamos de um

O mais comum é utilizar o HTTP

Que é composto por duas partes, a Requisição e a Resposta

> Composição da requisição
```
[MÉTODO] [URI] PROTOCOLO[http]/[Versão]    POST  /produtos  HTTP/1.1
                                           
[Cabeçalhos]                               Content-Type: application/json

[CORPO/PAYLOAD]                            Accept: application/json

                                           {
                                               "nome": "Notebook i7",
                                               "preco": 2100.0
                                           }
```
-------------

> Composição da resposta

```
PROTOCOLO[http]/[Versão] [STATUS]      HTTP/1.1 201 Created
[Cabeçalhos]                           Content-Type: application/json

[CORPO/PAYLOAD]                        {
                                            "codigo": 331,
                                            "nome": "Notebook i7",
                                            "preco": 2100.0
                                       }
```
-------------
Exercitando o uso do protocolo HTTP

GET / HTTP/1.1
Host: www.uol.com.br
Accept: text/html

- No comando acima temos uma requisição utilizando o protocolo HTTP através do verbo GET
- Acessando o endpoint www.uol.com.br
- Que espera um terno no formato texto formatado em html text/html

Para usar precisamos primeiro nos conectar ao servido especificando o endpoint e a porta

telnet www.uol.com.br 80

Depois digitar os comandos para consumir o serviço
```
GET / HTTP/1.1
Host: www.uol.com.br
Accept: text/html
```
-------------
Consumindo a API do git
```
gnutls-cli api.github.com
```
```
GET /users/marioguima/repos HTTP/1.1
Host: api.github.com
Accept: application/json
User-Agent: ubuntu
```
Ps. User-Agent pode ser qualquer valor, coloquei ubuntu que é por onde fiz a requisição, o ubuntu no wsl2


-------------
## URI

Uniform Resource Identifier (Identificador de Recurso Uniforme)

Um conjunto de caracteres que identifica o identificador como único

### URL e URI

**URL**

O objetivo é associar um endereço remoto com um nome de recurso na Internet.

**URI**

É o identificador do recurso. Pode ser uma imagem, uma página, etc, pois tudo o que está disponível na internet precisa de um identificador único para que não seja confundido.

Ex.
Coleção de recurso
```
URL www.marioguimaraes.com.br
URI /listarProdutos
```

Nesse caso, não é o ideal usar verbos apenas substantivos

Por convenção se usa um substantivo no plural

O ideal aqui seria ter a URI como /produtos

------------
Convensões na construção de REST API

. Usario adjetivos no plural para nomes de URI/Endpoint

Ex. /cozinhas

------------
Principais métodos/verbos HTTP
```
Method      Safe Method     Idempotente
GET             Sim             Sim
POST            Não             Não
PUT             Não             Sim
PATCH           Não             Sim
DELETE          Não             Sim
HEAD            Sim             Sim
OPTIONS         Sim             Sim
```

### Idempotente

Significa que independente de quantas vezes algo aconteça o resultado não muda

Ex.
Imagine que você tem uma maça verde nas mãos e eu pergunte 10 vezes seguidas qual a cor da maça que você tem em suas mãos.
As 10 vezes você vai responder que a mãça é verde.
Não importa quantas vezes eu te perguntar ela vai continuar verde.
Isso é ser idempotente, isso é idempotência.

Agora, se você morder a maçã uma vez, outra vez e outra vez.
Cada vez que você morde a maçã ela muda.
Essa ação não é idempotente, porque ela muda o estado da maçã.

Em relação aos verbos do HTTP dizemos que ele é Safe Method quando ele é idempotente, ou seja, quando ele não modifica o resultado por ser executado, não importando quantas vezes execute.

. GET

O verbo GET deve ser utilizado quando se deseja objter a representação de um recurso seja ele Single Resource ou uma Collection Resource

. POST

O verbo POST é utilizado para criar um novo recurso

. PUT

É normalmente utilizado para atualizar um recurso

Ele não pode atualizar parte do recurso, ele precisa enviador todos os dados do recurso para que seja atualizado

. PATCH

É utilizado para atualizar parte do recurso sem a necessidade de enviar todos os dados

. DELETE

Utilizado para excluir um recurso

. HEAD

Utilizado para retornar apenas o cabeçalho

. OPTIONS

Utilizado para obter a lista de métodos/verbos que o recurso disponibiliza
