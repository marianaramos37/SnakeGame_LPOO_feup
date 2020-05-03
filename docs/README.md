# LPOO_T4G4 - SNAKE

## Descrição
O jogo irá seguir a ideia base do jogo conhecido como "Snake", em que o jogador controla uma cobra posicionada numa arena que contém maçãs. Á medida que a cobra come as maçãs cresce em tamanho. O jogador perde se a cobra tocar numa parede ou no seu próprio corpo. O objetivo é aguentar na arena o máximo de tempo possível sem tocar em nada.
Na nossa versão do jogo vamos ter uma versão single player descrita acima com algumas funcionalidades extra, bem como uma versão multiplayer onde poderam existir mais cobras no mesmo ecrã.

Este projeto foi desenvolvido por por Flávia Carvalhido (up201806857@fe.up.pt) e Mariana Ramos (up201806869@fe.up.pt) no âmbito da cadeira Laboratório de Programação Orientada por Objectos no ano letivo 19/20.

## Funcionalidades Implementadas
**Single Player**
- **Virar**: A cobra está sempre em movimento, o jogador usa as teclas para mudar a direção em que a cobra vai;
- **Score**: A score é calculada pelo número de maçãs comidas;
- **Mapas**: Mapas diferentes para cada nível de dificuldade
- **Maçãs**: As maçãs são colocadas em posições aleatórias na arena à medida que a cobra as vai comendo;
- **Maças Especiais**: Maçãs especiais dão poderes há cobra: ficar mais curta e rápida durante um intervalo de tempo;
- **TopScore**: Top score é sempre guardado;


![screenshot of game](/docs/images/screenshot.png)


## Funcionalidades Planeadas

**Menu**: para selecionar o modo de jogo e o nível de dificuldade do modo de jogo Single Player

**Dois modos de jogo:** Single Player e Multi Player, selecionáveis a partir de um menu

**Single Player**
- **Níveis**: Existem três níveis distintos: Fácil, Médio e Difícil;
- **Obstáculos**: Existem obstáculos no meio da arena que vão aparecendo ao longo do tempo;
- **Maçãs envenenadas**: Maçãs envenenadas fazem a cobra diminuir de tamanho;
- **Velocidade** e **tamanho dos obstáculos** mudam ao longo do nível e de nível para nível

**Multi Player**
- Estão duas cobras na mesma arena, a ser controladas por partes do teclado diferentes;
- **Ataque**: Se uma cobra tocar no corpo da outra perde, isto dá possibilidade de uma cobra "matar" outra colocando o seu corpo à frente da cabeça da mesma;
- **Rondas**: Esta versão é constituida por três rondas, quando um dos players morre o outro ganha um ponto;

**Construção de mapas**
- No menu principal irá existir uma funcionalidade que permitirá aos jogadores construir os seus próprios mapas. Estes poderão ter uma serie de obstáculos previamente selecionados e poderão ser usados nas versões multiplayer.

## Architetural Pattern
O nosso programa tem o padrão arquitetural MVC (Model-View-Controller). 
Tal como o nome indica, este padrão divide a estrutura do nosso programa em três partes interconectadas:
- Model (package **data**): Contém a informação "interna" do jogo (?)
- View (package **gui**): Representa a visualização de todos os dados contidos no model.
- Controller (package **rules**): Existe entre a view e o model. O controller responde aos eventos enviados 
por view e executa a reação apropriada a esses eventos. Na maioria dos casos, essa reação irá mudar o model e será
visualizado por view.

A maior vantagem que encontramos ao implementar este padrão ...
Foi implementado logo desde início dadas as vantagens que este design apresenta.

Para além dos packages já mencionados decidimos adicionar:
- **files**: Este package contém todos os ficheiros necessários para o funcionamento do jogo. Entre eles 3 mapas para 3 níveis diferentes (fácil, médio e díficil) e um ficheiro que guarda a atual top score do jogo.
- **fileReaders**: Este package para já contém uma classe mapReader que tal como o nome indica lê os ficheiros dos mapas e encarrega-se de colocar as paredes da arena nas posições indicadas no ficheiro.

## Design

 ###### Common methods to different classes ######
 - **Contexto do problema**
 Tanto a classe Snake como a classe Wall funcionam à base de getters e setters de posições (apesar da classe Snake ter uma lista de posições, é necessário saber a posição da cabeça da Snake a todo o momento). Como era necessário fazer comparações de posições e usar métodos muito comuns às duas classes, decidimos que as duas classes deviam extender a mesma classe Element.
 
 - **Factory Method**
 O Factory Method permite instanciar a superclasse e só depois dizer qual das classes concretas (que extendem essa mesma superclasse) é que queremos usar, além de agrupar classes com propriedades semelhantes numa mesma superclasse. No nosso caso, isto permite avaliar as colisões entre elementos muito mais facilmente.
 
 - **Implementação**
 Classe abstrata Element que engloba vários elementos de jogo (classe Snake, classe Wall).
 
 ![diagrama factory](/docs/images/factoryMethod.png)
 
 - **Consequências**
 Os Elements concretos são definidos e usados pela classe ArenaModel. As colisões e a verificação da posição dos vários elementos é facilitada.
 
 
 ###### Different apples ######
 - **Contexto do problema**
 No nosso jogo é necessário criar maçãs consumíveis que adoptam posições aleatórias depois de consumidas e têm poderes especificos associados, além de terem TextCharacter diferentes atribuídos a cada tipo de maçã. Isso torna imperativo uma interface comum às diferentes classes de maçãs com os métodos comuns às classes de maçãs que a implementam.
 
 - **Abstract-Factory Pattern**
 O Abstract-Factory Pattern permite criar várias instanciações concretas de objetos com propriedades semelhantes através da criação de classes que implementem uma mesma interface abstrata que instancia todas as propriedades comuns a esses objetos.
 
 - **Implementação**
 As classes Apple e SpecialApple ambas implementam a Interface AppleInterface. 
  ![diagrama command](/docs/images/abstractPattern.png)
  
 - **Consequências**
  Nas Classes Apple, todas as maçãs implementam os mesmos métodos. Acrescentar mais maças diferentes é fácil porque não temos de modificar outras classes, basta acrescentar outra clase que implemente a interface AppleInterface. ArenaModel pode ter uma lista de objetos AppleInterface em vez que ter uma lista diferente para cada classe que implementa a interface. Podemos usar o método getChar, ou outro método qualquer instanciado na interface, num elemento da interface Apple que o mesmo vai devolver o valor correto da classe concreta da Apple que o chama.
 Ao implementar este padrão respeitamos o Open-Close Principle.
 
 ######  ######
 - **Contexto do problema**
 
 - ** **
 
 - **Implementação**
 
 - **Consequências**

 
 
## Code Smells and Refactoring Technics
A velocidade default da snake é 150 (de momento, mais tarde este valor será variável). É o que se chama um Magic Number e deve ser substituído através da utilizacão de uma Symbolic Constant para uma melhor organização e compreeensão do código.

A classe Snake tem um public field boolean isShrink que devia ser encapsulado através da criação de métodos get e set para esta variável (Encapsulate Field), uma vez que não é boa prática ter campos públicos numa classe.

O método movement() da ArenaController pode ser simplificado removendo o código duplicado para um método diferente (Extract Method). Tornando o método movement muito mais legível e pequeno, uma vez que as if-statements do mesmo são longas e complexas.

O construtor da Class MapReader chama o método fileReader(), tendo assim mais uma responsabilidade que não deveria estar a seu cargo (Replace Constructor with Factory Method). Devemos chamar o método fileReader() fora do construtor.

O construtor da ArenaView (ArenaView(int width, int height,Screen screen)) recebe os parâmetros Width e Height e não os usa dentro do mesmo. Eliminando esses mesmos parâmetros, uma vez que são obsoletos, removemos o Code Smell (Remove Parameters).


## Testing Results

## Self Evaluation
