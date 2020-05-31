package com.g44.model;

import com.g44.filereaders.MapReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class ArenaModel {
    private int width;
    private int height;
    private Snake snake;
    private Snake snake2;
    private List<Wall> walls = new ArrayList<>();
    private List<Obstaculo> obstaculos = new ArrayList<>();
    private List<AppleInterface> apples = new ArrayList<>();
    private int score;
    private SinglePlayerTopScore topScore = new SinglePlayerTopScore();
    private boolean game_over;

    public ArenaModel(int width, int height) throws IOException {
        this.width = width;
        this.height = height;
        this.snake = new Snake(new Position(width / 2, height / 2));
        this.snake2 = new Snake(new Position(3, 3));
        Apple apple = new Apple(ThreadLocalRandom.current().nextInt(1, width - 1), ThreadLocalRandom.current().nextInt(1, height - 1));
        SpecialApple appleS = new SpecialApple(ThreadLocalRandom.current().nextInt(1, width - 1), ThreadLocalRandom.current().nextInt(1, height - 1));
        PoisonedApple appleP = new PoisonedApple(ThreadLocalRandom.current().nextInt(1, width - 1), ThreadLocalRandom.current().nextInt(1, height - 1));
        GhostApple appleG = new GhostApple(ThreadLocalRandom.current().nextInt(1, width - 1), ThreadLocalRandom.current().nextInt(1, height - 1));
        this.apples.add(apple);
        this.apples.add(appleS);
        this.apples.add(appleP);
        this.apples.add(appleG);
        Obstaculo obstaculo1 = new Obstaculo(new Position(20, 20), 1);
        Obstaculo obstaculo2 = new Obstaculo(new Position(20, 20), 2);
        obstaculos.add(obstaculo1);
        obstaculos.add(obstaculo2);
        this.game_over = false;

    }

    public ArenaModel(int width, int height, String filename) throws IOException {
        this.width = width;
        this.height = height;
        this.snake = new Snake(new Position(50, 17));
        this.snake2 = new Snake(new Position(7, 17));

        Apple apple = new Apple(ThreadLocalRandom.current().nextInt(1, width - 1), ThreadLocalRandom.current().nextInt(1, height - 1));
        SpecialApple appleS = new SpecialApple(ThreadLocalRandom.current().nextInt(1, width - 1), ThreadLocalRandom.current().nextInt(1, height - 1));
        PoisonedApple appleP = new PoisonedApple(ThreadLocalRandom.current().nextInt(1, width - 1), ThreadLocalRandom.current().nextInt(1, height - 1));
        GhostApple appleG = new GhostApple(ThreadLocalRandom.current().nextInt(1, width - 1), ThreadLocalRandom.current().nextInt(1, height - 1));
        this.apples.add(apple);
        this.apples.add(appleS);
        this.apples.add(appleP);
        this.apples.add(appleG);

        MapReader c = new MapReader(filename);
        walls = c.getWallsRead();

        this.game_over = false;
    }


    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getScore() {
        return score;
    }

    public Snake getSnake() {
        return snake;
    }

    public List<Wall> getWalls() {
        return walls;
    }

    public List<Obstaculo> getObstaculos() {
        return this.obstaculos;
    }

    public List<AppleInterface> getApples() {
        return this.apples;
    }

    public void setTopScore(SinglePlayerTopScore topScore) {
        this.topScore = topScore;
    }

    public SinglePlayerTopScore getTopScore() {
        return this.topScore;
    }

    public Snake getSnake2() {
        return snake2;
    }

    public void setSnake2(Snake snake2) {
        this.snake2 = snake2;
    }

    public void setSnake(Snake s) {
        this.snake = s;
    }

    public void setWalls(List<Wall> l) {
        this.walls = l;
    }

    public void setApples(List<AppleInterface> l) {
        this.apples = l;
    }

    public void setScore(int s) {
        this.score = s;
    }


    public boolean getGameOver() {
        return game_over;
    }

    public void restartGame() {
        this.game_over = false;
        this.snake = new Snake(new Position(50, 17));
        this.snake.setLoser(false);
        this.snake2 = new Snake(new Position(7, 17));
        this.snake2.setLoser(false);
    }

    public void endGame() throws IOException {
        this.game_over = true;
    }

    public void buildWalls() {
        for (int i = 0; i < width; i++) {
            this.walls.add(new Wall(i, 0));
            this.walls.add(new Wall(i, this.height - 1));
        }
        for (int i = 0; i < height; i++) {
            this.walls.add(new Wall(0, i));
            this.walls.add(new Wall(this.width - 1, i));
        }
    }

    public void randomWalls() {

        Obstaculo obstaculo;
        obstaculo = new Obstaculo(new Position(0, 0), ThreadLocalRandom.current().nextInt(1, 3));
        Position randomPosition;
        do{
            randomPosition = new Position(ThreadLocalRandom.current().nextInt(1, getWidth() - 1), ThreadLocalRandom.current().nextInt(1, getHeight() - 1));
        }while(getCollidingElement(randomPosition) != null || getCollidingBody(randomPosition) || getCollidingApples(randomPosition) != null);
        obstaculo.setPosition(randomPosition);

        obstaculos.add(obstaculo);

    }

    public AppleInterface getCollidingApples(Position position) {
        for (AppleInterface apple : getApples())
            if (apple.getPosition().equals(position))
                return apple;
        return null;
    }

    public Element getCollidingElement(Position position) {
        for (Element element : this.getWalls())
            if (element.getPosition().equals(position))
                return element;
        for (Obstaculo obstaculo : this.getObstaculos())
            for (Element element : obstaculo.getObstaculo())
                if (element.getPosition().equals(position))
                    if (!snake.isGhost())
                        return element;
        return null;
    }

    public boolean getCollidingBody(Position position) {
        if (!getSnake().isShrink()) {
            for (int i = 2; i < this.getSnake().getPos().size(); i++)
                if (this.getSnake().getPos().get(i).equals(position))
                    return true;
        }
        return false;
    }

    public void checkAttack() throws IOException {
        //if the two heads collide everyone loses
        if (snake.getPosition().equals(snake2.getPosition())) {
            snake.setLoser(true);
            snake2.setLoser(true);
            this.endGame();
            return;
        }
        //check attack for Snake 2
        for (int i = 1; i < snake.getPos().size(); i++) {
            if (snake2.getPosition().equals(snake.getPos().get(i))) {
                snake2.setLoser(true);
                this.endGame();
                return;
            }
        }
        //check attack for Snake 1
        for (int i = 1; i < snake2.getPos().size(); i++) {
            if (snake.getPosition().equals(snake2.getPos().get(i))) {
                snake.setLoser(true);
                this.endGame();
                return;
            }
        }
    }

    public void eatenApple(AppleInterface a) {
        //colocar maca numa nova posicao aleatoria da arena:
        do {
            a.changePosition();
        } while (getCollidingElement(a.getPosition()) != null || getCollidingBody(a.getPosition()));

        //Altera a score:
        score += 1;
        if (a instanceof PoisonedApple) {
            if (getScore() <= 5) {
                setScore(0);
            } else {
                setScore(score - 5);
            }
            return;
        }
        if (score > topScore.getScore()) {
            topScore.incrementScore();
        }
        if (!getSnake().isShrink()) {
            this.updateVelocidadeSnake();
        }
    }

    public void checkCollisions(Snake s) throws IOException {
        AppleInterface eaten = getCollidingApples(s.getPosition());
        Wall hit = (Wall) getCollidingElement(s.getPosition());
        Boolean ownBody = getCollidingBody(s.getPosition());

        if (eaten != null) {
            eaten.affect(s);
            eatenApple(eaten);
        }
        if (hit != null) {
            if (s == this.getSnake()) this.getSnake().setLoser(true);
            else if (s == this.getSnake2()) this.getSnake2().setLoser(true);
            this.endGame();;
        }
        if (ownBody) {
            this.endGame();
        }
    }

    public void updateVelocidadeSnake() {
        if (this.getScore() % 5 == 0 && this.getScore() != 0 && this.snake.getVelocidade() > 90) {
            this.snake.setVelocidade(this.snake.getVelocidade() - 5);
        }
    }

}