# LPOO_T4G4 - SNAKE

## Descrição
O jogo irá seguir a ideia base do jogo conhecido como "Snake", em que o jogador controla uma cobra posicionada numa arena que contém maçãs. Á medida que a cobra come as maçãs cresce em tamanho. O jogador perde se a cobra tocar numa parede ou no seu próprio corpo. O objetivo é aguentar na arena o máximo de tempo possível sem tocar em nada.
Na nossa versão do jogo vamos ter uma versão single player descrita acima com algumas funcionalidades extra, bem como uma versão multiplayer onde poderam existir mais cobras no mesmo ecrã.

Este projeto foi desenvolvido por por Flávia Carvalhido (up201806857@fe.up.pt) e Mariana Ramos (up201806869@fe.up.pt) no âmbito da cadeira Laboratório de Programação Orientada por Objectos no ano letivo 19/20.

## Funcionalidades Implementadas

## Funcionalidades Planeadas

**Dois modos de jogo:** Single Player e Multi Player

**Single Player**
- **Virar**: A cobra está sempre em movimento, o jogador usa as teclas para mudar a direção em que a cobra vai;
- **Score**: A score é calculada pelo número de maçãs comidas;
- **Níveis**: Existem três níveis distintos: Fácil, Médio e Difícil;
- **Obstáculos**: Existem obstáculos no meio da arena que vão aparecendo ao longo do tempo;
- **Maçãs**: As maçãs são colocadas em posições aleatórias na arena à medida que a cobra as vai comedo;
- **Maçãs envenenadas**: Maçãs envenenadas fazem a cobra diminuir de tamanho;
- **Maças Especiais**: Maçãs especiais dão poderes há cobra: ficar mais curta e rápida durante um intervalo de tempo OU conseguir passar por entre obstáculos;
- **Velocidade** e **tamanho dos obstáculos** mudam ao longo do nível e de nível para nível
- **TopScore**: Top score é sempre guardado;

**Multi Player**
- Estão duas cobras na mesma arena, a ser controladas por partes do teclado diferentes;
- **Ataque**: Se uma cobra tocar no corpo da outra perde, isto dá possibilidade de uma cobra "matar" outra colocando o seu corpo à frente da cabeça da mesma;
- **Rondas*: Esta versão é constituida por três rondas, quando um dos players morre o outro ganha um ponto;

**Construção de mapas**
- No menu principal irá existir uma funcionalidade que permitirá aos jogadores construir os seus próprios mapas. Estes poderão ter uma serie de obstáculos previamente selecionados e podeão ser usados nas versões multiplayer.

## Design

## Code Smells and Refactoring Technics

## Self Evaluation
