# LPOO_T4G4 - SNAKE #

## Descrição ##
O programa irá seguir a ideia base do jogo conhecido como "Snake", em que o jogador controla uma cobra posicionada numa 
arena que contém maçãs. Á medida que a cobra come as maçãs cresce em tamanho. O jogador perde se a cobra tocar numa parede 
ou no seu próprio corpo. O objetivo é aguentar na arena o máximo de tempo possível sem tocar em nada.
Na nossa versão do jogo vamos ter uma versão single player descrita acima com algumas funcionalidades extra, bem como uma 
versão multiplayer onde poderam existir mais cobras no mesmo ecrã.

Este projeto foi desenvolvido por por Flávia Carvalhido (up201806857@fe.up.pt) e Mariana Ramos (up201806869@fe.up.pt) no 
âmbito da cadeira Laboratório de Programação Orientada por Objectos no ano letivo 19/20.

## Funcionalidades Implementadas ##
**Single Player**
- **Virar**: A cobra está sempre em movimento, o jogador usa as teclas (Setas ou A,W,D,S) para mudar a direção em que a cobra vai;
- **Score**: A score é calculada pelo número de maçãs comidas;
- **TopScore**: Top score é sempre guardado;
- **Níveis**: Existem quatro níveis distintos: Fácil, Médio, Difícil e Ultra Difícil;
- **Obstáculos**: Existem obstáculos no meio da arena que vão aparecendo ao longo do tempo em locais aleatórios;
- **Mapas**: Mapas diferentes para cada nível de dificuldade;
- **Velocidade** muda ao longo do nível;
- **Maçãs**: As maçãs são colocadas em posições aleatórias na arena à medida que a cobra as vai comendo;
- **Maças Especiais**: Maçãs especiais dão poderes à cobra: ficar mais curta e rápida durante um intervalo de tempo;
- **Maçãs Envenenadas**: Maçãs envenenadas fazem a cobra diminuir de tamanho e perder 5 pontos na score;
- **Maçãs Fantasma**: Maçãs fantasma quando comidas fazem com que a cobra consiga passar por cima dos obstáculos;

**Multi Player**
- Estão duas cobras na mesma arena, a ser controladas por partes do teclado diferentes;
- **Ataque**: Se uma cobra tocar no corpo da outra perde, isto dá possibilidade de uma cobra "matar" outra colocando o 
seu corpo à frente da cabeça da mesma;
- **Rondas**: Esta versão é constituida por diferentes rondas, quando um dos players morre o outro ganha um ponto. 
O primeiro a chegar aos 3 pontos ganha o jogo;


**Menus**
- **Menu Principal** - Permite escolher entre SinglePlayer, MultiPlayer, Instruções do Jogo e Sair;
- **Menu de Escolha de nível** - Permite escolher entre os quatro níveis de dificuldade do modo SinglePlayer;
- **Menu GameOver** - Permite retornar ao Menu Principal ou Sair.


![MainMenu](/docs/images/MainMenu.PNG)
![SinglePlayer_UltraHardLevel](/docs/images/SinglePlayer.PNG)
![MultiPlayer](/docs/images/MultiPlayer.PNG)
![GameOver](/docs/images/GameOver.PNG)

## Funcionalidades Planeadas ##

**Terceiro modo de jogo:** Construção de Mapas
- No menu principal irá existir uma funcionalidade que permitirá aos jogadores construir os seus próprios mapas. Estes 
poderão ter uma serie de obstáculos previamente selecionados e poderão ser usados nas versões multiplayer.

## Architetural Pattern ##

O nosso programa tem o padrão arquitetural MVC (Model-View-Controller). 
Tal como o nome indica, este padrão divide a estrutura do nosso programa em três partes interconectadas:
- Model (package **com.g44.com.g44.model**): Contém todos os elementos do jogo: Snake, ArenaModel, diferentes modelos de maçãs que implementam 
a interface AppleInterface,  Walls e Obstaculos (constituidos por Walls) sendo que ambos estendem a classe Element, modelos dos 
diferentes menus que estendem a classe MenuModel e por último modelo da TopScore.
- View (package **com.g44.view**): Representa a visualização de todos os dados contidos no com.g44.com.g44.model.
- Controller (package **com.g44.controller**): Existe entre a *com.g44.view* e o *com.g44.com.g44.model*. O com.g44.controller responde aos eventos enviados 
por *com.g44.view* através de Commands e executa a ação apropriada a esses eventos. Na maioria dos casos, essa ação muda o *com.g44.com.g44.model* que será
visualizado por com.g44.view.


Implementamos este modelo logo desde início dadas as vantagens que este design apresenta, entre elas a 
organização do nosso código, o que facilitou o uso e paralelismo entre classes.

Para além dos packages já mencionados decidimos adicionar:
- **com.g44.files**: Este package contém todos os ficheiros necessários para o funcionamento do jogo. Entre eles 3 mapas para 3 níveis diferentes (fácil, médio e díficil) e um ficheiro que guarda a atual top score do jogo.
- **fileReaders**: Este package  contém uma classe mapReader que tal como o nome indica lê os ficheiros dos mapas e encarrega-se de colocar as paredes da arena nas posições indicadas no ficheiro.
- **com.g44.commands**: Este package contém duas interfaces CommandArena e CommandMenu, e uma série de classes que implementam ou uma ou as duas interfaces.

## Design ##


### Parametrizar comandos e reutilizalos em vários estados do jogo ###
- **Contexto do problema**

Aquando da criação dos diferentes Controllers (com papel de States) reparamos que a interação com o utilizador através da View envolvia 
muitas verificações sobre que teclas estavam a ser precionadas (através de swich cases que continham um obvio Code Smell). 
Para além disso cada tecla produzia mudanças nos Models muito semelhantes mas que estavam a ser repetidas sempre para cada Controller.

Dentro do mesmo problema deparamo-nos com o facto de as mesmas teclas provocarem ações diferentes consoante o estado do jogo.
Caso estivesse-mos num Menu a tecla ArrowLeft iria mudar a opção selecionada. Mas caso estivessemos na Arena a mesma tecla iria 
alterar a posição de uma das snakes.

- **O Padrão**

Para resolver este problema recorremos ao **Command Pattern**.
Este padrão permite-nos encapsular um pedido (no nosso caso a chamada de funções que alteram os modelos) num objeto de forma
 a que este possa ser parametrizavel. Desta forma em cada Controller apenas precisamos de chamar o objeto Command através 
 da View com getCommand() e de seguida chamar a função execute() desse objeto, evitando assim um grande Swich Statment.
 No nosso caso optamos por colocar duas funções execute (executeArena() e executeMenu()) de forma a resolver 
 o segundo problema mencionado. Na interface ambos os métodos têm uma implementação default que não altera os modelos.
 
- **Implementação**

A figura seguinte mostra como os papeis do padrão foram aplicados nas nossas classes:

![Diagrama_Command](/docs/images/UMLCommand.png)

Estas classes podem ser encontradas nos seguintes ficheiros:
[Command](https://github.com/FEUP-LPOO/lpoo-2020-g44/blob/3a241ee1079a3fb4ca6b5edfbbbd76892033a5c1/src/main/java/com.g44.commands/Command.java#L6),
[CommandArena](https://github.com/FEUP-LPOO/lpoo-2020-g44/blob/3a241ee1079a3fb4ca6b5edfbbbd76892033a5c1/src/main/java/com.g44.commands/CommandArena.java#L5),
[CommandMenu](https://github.com/FEUP-LPOO/lpoo-2020-g44/blob/3a241ee1079a3fb4ca6b5edfbbbd76892033a5c1/src/main/java/com.g44.commands/CommandMenu.java#L5),
[ArrowLeft](https://github.com/FEUP-LPOO/lpoo-2020-g44/blob/3a241ee1079a3fb4ca6b5edfbbbd76892033a5c1/src/main/java/com.g44.commands/ArrowLeft.java#L6),
[ArrowRight](https://github.com/FEUP-LPOO/lpoo-2020-g44/blob/3a241ee1079a3fb4ca6b5edfbbbd76892033a5c1/src/main/java/com.g44.commands/ArrowRight.java#L6),
[ArrowUp](https://github.com/FEUP-LPOO/lpoo-2020-g44/blob/3a241ee1079a3fb4ca6b5edfbbbd76892033a5c1/src/main/java/com.g44.commands/ArrowUp.java#L5),
[ArrowDown](https://github.com/FEUP-LPOO/lpoo-2020-g44/blob/3a241ee1079a3fb4ca6b5edfbbbd76892033a5c1/src/main/java/com.g44.commands/ArrowDown.java#L5),
[AKey](https://github.com/FEUP-LPOO/lpoo-2020-g44/blob/3a241ee1079a3fb4ca6b5edfbbbd76892033a5c1/src/main/java/com.g44.commands/AKey.java#L5),
[WKey](https://github.com/FEUP-LPOO/lpoo-2020-g44/blob/3a241ee1079a3fb4ca6b5edfbbbd76892033a5c1/src/main/java/com.g44.commands/WKey.java#L5),
[DKey](https://github.com/FEUP-LPOO/lpoo-2020-g44/blob/3a241ee1079a3fb4ca6b5edfbbbd76892033a5c1/src/main/java/com.g44.commands/DKey.java#L5),
[SKey](https://github.com/FEUP-LPOO/lpoo-2020-g44/blob/3a241ee1079a3fb4ca6b5edfbbbd76892033a5c1/src/main/java/com.g44.commands/SKey.java#L5),
[EnterKey](https://github.com/FEUP-LPOO/lpoo-2020-g44/blob/3a241ee1079a3fb4ca6b5edfbbbd76892033a5c1/src/main/java/com.g44.commands/EnterKey.java#L5),
[EscKey](https://github.com/FEUP-LPOO/lpoo-2020-g44/blob/3a241ee1079a3fb4ca6b5edfbbbd76892033a5c1/src/main/java/com.g44.commands/EscKey.java#L5),
[IKey](https://github.com/FEUP-LPOO/lpoo-2020-g44/blob/3a241ee1079a3fb4ca6b5edfbbbd76892033a5c1/src/main/java/com.g44.commands/IKey.java#L5),
[NullCommand](https://github.com/FEUP-LPOO/lpoo-2020-g44/blob/3a241ee1079a3fb4ca6b5edfbbbd76892033a5c1/src/main/java/com.g44.commands/NullCommand.java#L6)

- **Consequências**

Usar o Command Pattern no design do nosso projeto forneceu-nos as seguintes vantagens:
1. Separamos as responsabilidades de saber quando executar um comando (nos Controllers) e como executar o comando 
(nas classes mecionadas a cima). Cumprindo o Single Responsibility Principle;
2. Tornou-se fácil ter comandos compostos. Por exemplo executar o comandos das Arrow e Enter para selecionar opções;
3. Torna-se fácil adicionar novos comandos, não tendo que alterar nenhuma classe para o fazer, apenas acrescentar;
4. Tivemos que adicionar muitas novas classes o que pode ser visto como uma desvantagem. Mas dada a dimensão do projeto não foi muito
problemático.



### Criação de obstáculos ###

- **Contexto do problema**

No nosso jogo criamos obstaculos que funcionam exatamente como elementos (Classe Element) uma vez que têm uma posição.
No entanto um obstaculo é contituido por Walls que por sua vez também são elementos que também contém uma posição. 
Desenhar elementos Walls e Obstaculos que não apresentam diferenças na sua representação seria repetir métodos desnecessários.


- **O Padrão**

Para resolver o problema optamos por usar o **Composite Pattern** (com uma variação em que ordenamos os elemets no Composite). 
Este padrão permite-nos representar hierarquias de objetos parte-todo e fazer com que o cliente (neste caso **ArenaView**) ignore 
a diferença entre a composição de elementos (os Obstaculos) e os elementos em si (as Walls). 
É importante notar que no nosso caso, para o cliente Arena Model interessa ainda distinguir obstaculos de paredes (em especifico no
caso de a Snake ter comido uma GhostApple e conseguir ultrapassar obstaculos mas não paredes). No entanto este padrão é extremamente
útil na Arenaview como explicado mais à frente em "Consequências".

- **Implementação**

A figura seguinte mostra como os papeis do padrão foram aplicados nas nossas classes:

![Diagrama_Composite](/docs/images/UMLComposite.PNG)

Estas classes podem ser encontradas nos seguintes ficheiros:

[Element](https://github.com/FEUP-LPOO/lpoo-2020-g44/blob/c70e6046a344b3570dd8400f064b025b1863836c/src/main/java/com.g44.com.g44.model/Element.java#L3),
[Wall](https://github.com/FEUP-LPOO/lpoo-2020-g44/blob/c70e6046a344b3570dd8400f064b025b1863836c/src/main/java/com.g44.com.g44.model/Wall.java#L3) e
[Obstaculo](https://github.com/FEUP-LPOO/lpoo-2020-g44/blob/c70e6046a344b3570dd8400f064b025b1863836c/src/main/java/com.g44.com.g44.model/Obstaculo.java#L6).


- **Consequências**

Usar o Composite Pattern no nosso design do nosso projeto forneceu-nos as seguintes vantagens:
   1. Simplificamos bastante a nossa Classe View uma vez que desenhar obstaculos não é mais do que desenhar elementos Walls
    sem necessitar de distinguir entre eles.
    
    
### Menus ###
- **Contexto do problema**

Depois de criar a Classe GameController que iria tratar de todo o funcionamento do jogo reparamos que este se iria comportar
de maneiras diferentes consoante o estado de jogo em que estivesse, SinglePlayer, Multiplayer, Menu, etc. Para isso iriamos 
precisar de grandes Conditional Statements e diversas flags num mesmo método o que violava o Single Responsability Principle.

- **O padrão**

Obtamos por escolher o **State Pattern** de modo resolver esse problema. Este padrão permite-nos representar diferentes
estados de jogo com diferentes subclasses. Desta forma podemos aterar o estado de jogo mudando para outra implementação. Resolvemos 
assim o problema uma vez que na nossa Classe GameController apenas temos que inicializar um State usando o seu método "run()" que 
por sua vez irá devolver o próximo State que irá de novo fazer "run()" e devolver o próximo State por aí a fora. Desta forma
o próprio estado define as suas transições.
 
- **Implementação**

As figuras seguintes mostram como os papeis do padrão foram aplicados nas nossas classes e um diagrama de estados do funcionamento 
do objeto GameController:

![Diagrama_State](/docs/images/UMLState.png)
![DiagramaDeEstados](/docs/images/StateDiagram.png)

Estas classes podem ser encontradas nos seguintes ficheiros:

[StateControllers](https://github.com/FEUP-LPOO/lpoo-2020-g44/blob/c70e6046a344b3570dd8400f064b025b1863836c/src/main/java/com.g44.controller/StateControllers.java#L8),
[MainMenuController](https://github.com/FEUP-LPOO/lpoo-2020-g44/blob/c70e6046a344b3570dd8400f064b025b1863836c/src/main/java/com.g44.controller/MainMenuController.java#L23),
[SinglePlayerController](https://github.com/FEUP-LPOO/lpoo-2020-g44/blob/c70e6046a344b3570dd8400f064b025b1863836c/src/main/java/com.g44.controller/SinglePlayerController.java#L9),
[MultiPlayerController](https://github.com/FEUP-LPOO/lpoo-2020-g44/blob/c70e6046a344b3570dd8400f064b025b1863836c/src/main/java/com.g44.controller/MultiPlayerController.java#L16),
[GameOverController](https://github.com/FEUP-LPOO/lpoo-2020-g44/blob/c70e6046a344b3570dd8400f064b025b1863836c/src/main/java/com.g44.controller/GameOverController.java#L13),
[InstructionsController](https://github.com/FEUP-LPOO/lpoo-2020-g44/blob/c70e6046a344b3570dd8400f064b025b1863836c/src/main/java/com.g44.controller/IntructionsController.java#L12) e
[MenuLevelsController](https://github.com/FEUP-LPOO/lpoo-2020-g44/blob/c70e6046a344b3570dd8400f064b025b1863836c/src/main/java/com.g44.controller/MenuLevelsController.java#L12).

- **Consequências**

O uso do State Pattern no design do nosso projeto permitiu-nos:
1. Os diferentes estados que representam o funcionamento geral do jogo tornam-se explicitos no código, em vez de serem apenas uma
    série de flags;
2. Não necessitamos de usar longos Conditional Statements associados aos diferentes estados. Recorrendo ao polimorfismo
    criamos o funcionamento correto;
3. Uma pequena desvantagem foi o número de classes adicionais.



## Code Smells and Refactoring Technics ##
- **Long Method**
No nosso projeto encontramos dois métodos que tem um tamanho um pouco maior do que o desejado:
[run()](https://github.com/FEUP-LPOO/lpoo-2020-g44/blob/318bdf4cd5f6105e1dddf5aa904e03e168845c88/src/main/java/com/g44/controller/SinglePlayerController.java#L35) 
do SinglePlayerController e [run()](https://github.com/FEUP-LPOO/lpoo-2020-g44/blob/318bdf4cd5f6105e1dddf5aa904e03e168845c88/src/main/java/com/g44/controller/MultiPlayerController.java#L27) 
do MultiPlayerController. Este Smell poderia ser resolvido 
recorrendo ao refactoring **Extract Method** onde moveriamos o código para métodos mais pequenos. No entanto,
apesar de os métodos terem um tamanho considerável, achamos que separar o seu conteúdo iria tornar o código
confuso em vez de o simplificar.

-**Data Class**
A classe [Element](https://github.com/FEUP-LPOO/lpoo-2020-g44/blob/318bdf4cd5f6105e1dddf5aa904e03e168845c88/src/main/java/com/g44/model/Element.java#L3) 
é uma Data Classes uma vez que só contem parâmetros e os respetivos getters
e setters sem nenhum comportamento. Isto é um problema porque não damos nenhum poder à classe, ela 
apenas têm dados que podem ser usados por outras. Recorrendo aos refactoring  **Move Method** and **Extract Method**
poderiamos ser capazes de mover alguns dos métodos da classe ArenaModel por exemplo para as classes Element ou Wall
mas no nosso caso em particular traria-nos mais desvantagens do que vantagens e optamos por continuar com o Smell.


## Testing Results
![testing](/docs/images/testing.PNG)

## Self Evaluation
Mariana Ramos: 50%

Flávia Carvalhido: 50%

