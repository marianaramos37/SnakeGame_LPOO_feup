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

(falta screenshots)

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

## Design
 - **MVC**: O nosso programa tem o design arquitetural MVC dado que é um jogo (foi implementado logo desde início dadas as vantagens que este design apresenta)
 - **Factory Method**: Classe abstrata Element que engloba todos os elementos de jogo (classe Snake, classe Apple, classe Wall). Os Elements concretos são definidos e usados pela classe ArenaModel.
 - **Command Pattern**: Classe Apple, todas as maças fazem getChar(). Vantagens: acrescentar mais maças diferentes é fácil porque não temos de modificar outras classes, basta acrescentar outra clase que implemente a interface Apple; ArenaModel pode ter uma lista da interface Apple em vez que ter uma lista diferente para cada classe que implementa a interface; podemos usar o método getChar num elemento da interface Apple que o mesmo vai devolver o valor correto da instância concreta da Apple que o chama.
 - **Observer Pattern**: 
 - **Strategy Method**:
 
 
## Code Smells and Refactoring Technics
A velocidade default da snake é 150 (de momento, mais tarde este valor será variável). É o que se chama um Magic Number e deve ser substituído através da utilizacão de uma Symbolic Constant para uma melhor organização e compreeensão do código.

A classe Snake tem um public field boolean isShrink que devia ser encapsulado através da criação de métodos get e set para esta variável, uma vez que não é boa prática ter campos públicos numa classe.

As classes SinglePlayerTopScoreFileReader e SinglePlayerTopScoreFileWritter tem construtores que além de atribuírem valores aos campos das classes, chamam outros métodos desta mesma classe para ler ou escrever nos ficheiros, respetivamente. Logo estes construtores fazem mais do que deveriam fazer e devem ser substituídos/simplificados por um Factory Method.

O método movement da ArenaController pode ser simplificado removendo o código duplicado para um método diferente, tornando o método movement muito mais legível e pequeno, uma vez que as if-statements do mesmo são longas e complexas.

## Self Evaluation
