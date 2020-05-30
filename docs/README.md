# LPOO_T4G4 - SNAKE #

## Descrição ##
O programa irá seguir a ideia base do jogo conhecido como "Snake", em que o jogador controla uma cobra posicionada numa arena que contém maçãs. Á medida que a cobra come as maçãs cresce em tamanho. O jogador perde se a cobra tocar numa parede ou no seu próprio corpo. O objetivo é aguentar na arena o máximo de tempo possível sem tocar em nada.
Na nossa versão do jogo vamos ter uma versão single player descrita acima com algumas funcionalidades extra, bem como uma versão multiplayer onde poderam existir mais cobras no mesmo ecrã.

Este projeto foi desenvolvido por por Flávia Carvalhido (up201806857@fe.up.pt) e Mariana Ramos (up201806869@fe.up.pt) no âmbito da cadeira Laboratório de Programação Orientada por Objectos no ano letivo 19/20.

## Funcionalidades Implementadas ##
**Single Player**
- **Virar**: A cobra está sempre em movimento, o jogador usa as teclas para mudar a direção em que a cobra vai;
- **Score**: A score é calculada pelo número de maçãs comidas;
- **TopScore**: Top score é sempre guardado;
- **Níveis**: Existem quatro níveis distintos: Fácil, Médio, Difícil e Ultra Difícil;
- **Obstáculos**: Existem obstáculos no meio da arena que vão aparecendo ao longo do tempo em locais aleatórios;
- **Mapas**: Mapas diferentes para cada nível de dificuldade;
- **Velocidade** e **tamanho dos obstáculos** mudam ao longo do nível e de nível para nível;
- **Maçãs**: As maçãs são colocadas em posições aleatórias na arena à medida que a cobra as vai comendo;
- **Maças Especiais**: Maçãs especiais dão poderes à cobra: ficar mais curta e rápida durante um intervalo de tempo;
- **Maçãs Envenenadas**: Maçãs envenenadas fazem a cobra diminuir de tamanho e perder 5 pontos na score;
- **Maçãs Fantasma**: Maçãs fantasma quando comidas fazem com que a cobra consiga passar por cima dos obstáculos;

**Multi Player**
- Estão duas cobras na mesma arena, a ser controladas por partes do teclado diferentes;
- **Ataque**: Se uma cobra tocar no corpo da outra perde, isto dá possibilidade de uma cobra "matar" outra colocando o seu corpo à frente da cabeça da mesma;
- **Rondas**: Esta versão é constituida por diferentes rondas, quando um dos players morre o outro ganha um ponto. O primeiro a chegar aos 3 pontos ganha o jogo;


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
- No menu principal irá existir uma funcionalidade que permitirá aos jogadores construir os seus próprios mapas. Estes poderão ter uma serie de obstáculos previamente selecionados e poderão ser usados nas versões multiplayer.

## Architetural Pattern ##

O nosso programa tem o padrão arquitetural MVC (Model-View-Controller). 
Tal como o nome indica, este padrão divide a estrutura do nosso programa em três partes interconectadas:
- Model (package **model**): Contém todos os elementos do jogo: Snake, ArenaModel, diferentes modelos de maçãs que implementam 
a interface AppleInterface,  Walls e Obstaculos (constituidos por Walls) sendo que ambos estendem a classe Element, modelos dos 
diferentes menus que estendem a classe MenuModel e por último modelo da Top Score.
- View (package **view**): Representa a visualização de todos os dados contidos no model.
- Controller (package **controller**): Existe entre a *view* e o *model*. O controller responde aos eventos enviados 
por *view* através de Commands e executa a ação apropriada a esses eventos. Na maioria dos casos, essa ação muda o *model* que será
visualizado por view.


Implementamos este modelo logo desde início dadas as vantagens que este design apresenta, entre elas a organização do nosso código, o que facilitou o uso e paralelismo entre classes.

Para além dos packages já mencionados decidimos adicionar:
- **files**: Este package contém todos os ficheiros necessários para o funcionamento do jogo. Entre eles 3 mapas para 3 níveis diferentes (fácil, médio e díficil) e um ficheiro que guarda a atual top score do jogo.
- **fileReaders**: Este package  contém uma classe mapReader que tal como o nome indica lê os ficheiros dos mapas e encarrega-se de colocar as paredes da arena nas posições indicadas no ficheiro.
- **commands**: Este package contém duas interfaces CommandArena e CommandMenu, e uma série de classes que implementam ou uma ou as duas interfaces.

## Design ##

 ### Maçãs diferentes -> adicionar strategy### 
 
 - **Contexto do problema**
 
 Aquando da criação de diferentes maçãs no jogo deparamo-nos com a repetição de métodos
 No nosso jogo é necessário criar diferentes maçãs consumíveis que adoptam posições aleatórias depois de consumidas e têm 
 poderes especificos associados, além de terem TextCharacter diferentes atribuídos a cada tipo de maçã. Isso torna 
 imperativo uma interface comum às diferentes classes de maçãs com os métodos comuns às classes de maçãs que a implementam.
 
 - **O Padrão**
 
 Nós escolhemos usar o **Factory Method**
 permite criar várias instanciações concretas de objetos com propriedades semelhantes através 
 da criação de classes que implementem uma mesma interface abstrata que instancia todas as propriedades comuns a esses objetos.
 
 - **Implementação**
 
 As classes [Apple](https://github.com/FEUP-LPOO/lpoo-2020-g44/blob/3f8697ca49d4d44437c2285ba599dc59d9dae1f7/src/main/java/data/Apple.java#L3) 
 e [SpecialApple](https://github.com/FEUP-LPOO/lpoo-2020-g44/blob/3f8697ca49d4d44437c2285ba599dc59d9dae1f7/src/main/java/data/SpecialApple.java#L3)
  ambas implementam a Interface [AppleInterface](https://github.com/FEUP-LPOO/lpoo-2020-g44/blob/3f8697ca49d4d44437c2285ba599dc59d9dae1f7/src/main/java/data/AppleInterface.java#L3). 
  
  
 - **Consequências**
 
  Nas Classes Apple, todas as maçãs implementam os mesmos métodos. Acrescentar mais maças diferentes é fácil porque não temos de modificar outras classes, basta acrescentar outra clase que implemente a interface AppleInterface. ArenaModel pode ter uma lista de objetos AppleInterface em vez que ter uma lista diferente para cada classe que implementa a interface. Podemos usar o método getChar, ou outro método qualquer instanciado na interface, num elemento da interface Apple que o mesmo vai devolver o valor correto da classe concreta da Apple que o chama.
 Ao implementar este padrão respeitamos o Open-Close Principle.

## Menus Models
- **Contexto do problema**
- **Factory Method**
- **Implementação**
- **Consequências**

## Criação de obstáculos

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
útil na Arenaview como expliado mais à frente em "Consequências".

- **Implementação**

A figura seguinte mostra como os papeis do padrão foram aplicados nas nossas classes:

![Diagrama_Composite](/docs/images/UMLComposite.PNG)

Estas classes podem ser encontradas nos seguintes ficheiros:

[Element](https://github.com/FEUP-LPOO/lpoo-2020-g44/blob/c70e6046a344b3570dd8400f064b025b1863836c/src/main/java/model/Element.java#L3),
[Wall](https://github.com/FEUP-LPOO/lpoo-2020-g44/blob/c70e6046a344b3570dd8400f064b025b1863836c/src/main/java/model/Wall.java#L3) e
[Obstaculo](https://github.com/FEUP-LPOO/lpoo-2020-g44/blob/c70e6046a344b3570dd8400f064b025b1863836c/src/main/java/model/Obstaculo.java#L6).


- **Consequências**

Usar o Composite Pattern no nosso design do nosso projeto forneceu-nos as seguintes vantagens:
    - Simplificamos bastante a nossa Classe View uma vez que desenhar obstaculos não é mais do que desenhar elementos Walls
    sem necessitar de distinguir entre eles.


## Parametrizar comandos e reutilizalos em vários estados do jogo
- **Contexto do problema**

Aquando da criação dos diferentes Controllers (com papel de States) reparamos que a interação com o utilizador através da View envolvia 
muitas verificações sobre que teclas estavam a ser precionadas (através de swich cases que continham um obvio Code Smell). 
Para além disso cada tecla produzia mudanças nos Models muito semelhantes mas que estavam a ser repetidas sempre para cada Controller.

Dentro do mesmo problema deparamo-nos com o facto de as mesmas teclas provocarem ações diferentes consoante o estado do jogo.
Caso estivesse-mos num Menu a tecla ArrowLeft iria mudar a opção selecionada. Mas caso estivessemos na Arena a mesma tecla iria 
alterar a posição de uma das snakes.

- **O Padrão**

Para resolver este problema recorremos ao **Command Pattern** onde optamos por inserir duas interfaces.
Este padrão permite-nos encapsular um pedido (no nosso caso a chamada de funções que alteram os modelos) num objeto de forma
 a que este possa ser parametrizavel. Desta forma em cada Controller apenas precisamos de chamar o objeto Command através 
 da View com getCommand() e de seguida chamar a função execute() desse objeto, evitando assim um grande Swich Statment.
 De forma a que na Classe View não tenhamos que ter duas funções que devolvam Commands de interfaces diferentes optamos ainda por
 recorrer ao **Adapter Pattern** de modo a podermos trabalhar interfaces não previstas.
 
- **Implementação**

- **Consequências**


## Menus
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

![Diagrama_State](/docs/images/UMLState.PNG)
![DiagramaDeEstados](/docs/images/StateDiagram.PNG)

Estas classes podem ser encontradas nos seguintes ficheiros:

[StateControllers](https://github.com/FEUP-LPOO/lpoo-2020-g44/blob/c70e6046a344b3570dd8400f064b025b1863836c/src/main/java/controller/StateControllers.java#L8),
[MainMenuController](https://github.com/FEUP-LPOO/lpoo-2020-g44/blob/c70e6046a344b3570dd8400f064b025b1863836c/src/main/java/controller/MainMenuController.java#L23),
[SinglePlayerController](https://github.com/FEUP-LPOO/lpoo-2020-g44/blob/c70e6046a344b3570dd8400f064b025b1863836c/src/main/java/controller/SinglePlayerController.java#L9),
[MultiPlayerController](https://github.com/FEUP-LPOO/lpoo-2020-g44/blob/c70e6046a344b3570dd8400f064b025b1863836c/src/main/java/controller/MultiPlayerController.java#L16),
[GameOverController](https://github.com/FEUP-LPOO/lpoo-2020-g44/blob/c70e6046a344b3570dd8400f064b025b1863836c/src/main/java/controller/GameOverController.java#L13),
[InstructionsController](https://github.com/FEUP-LPOO/lpoo-2020-g44/blob/c70e6046a344b3570dd8400f064b025b1863836c/src/main/java/controller/IntructionsController.java#L12) e
[MenuLevelsController](https://github.com/FEUP-LPOO/lpoo-2020-g44/blob/c70e6046a344b3570dd8400f064b025b1863836c/src/main/java/controller/MenuLevelsController.java#L12).

- **Consequências**

O uso do State Pattern no design do nosso projeto permitiu-nos:
    - Os diferentes estados que representam o funcionamento geral do jogo tornam-se explicitos no código, em vez de serem apenas uma
    série de flags;
    - Não necessitamos de usar longos Conditional Statements associados aos diferentes estados. Recorrendo ao polimorfismo
    criamos o funcionamento correto;
    - Uma pequena desvantagem foi o número de classes adicionais.



 
## Code Smells and Refactoring Technics
 - A velocidade default da snake é 150 (de momento, mais tarde este valor será variável). É o que se chama um **Magic Number** e deve ser substituído através da utilizacão de uma **Symbolic Constant** para uma melhor organização e compreeensão do código.

 - O método movement() da ArenaController pode ser simplificado removendo o código duplicado para um método diferente (**Extract Method**). Tornando o método movement muito mais legível e pequeno, uma vez que as if-statements do mesmo são longas e complexas.

 - O construtor da Class MapReader chama o método fileReader(), tendo assim mais uma responsabilidade que não deveria estar a seu cargo (Replace Constructor with Factory Method). Devemos chamar o método fileReader() fora do construtor.

 - O construtor da ArenaView (ArenaView(int width, int height,Screen screen)) recebe os parâmetros Width e Height e não os usa dentro do mesmo. Eliminando esses mesmos parâmetros, uma vez que são obsoletos, removemos o Code Smell (Remove Parameters).


## Testing Results
![testing](/docs/images/testing.PNG)

## Self Evaluation
Mariana Ramos: 50%

Flávia Carvalhido: 50%

