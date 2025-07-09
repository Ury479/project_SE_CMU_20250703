package se233.chapter5part2.controller;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyCode;
import se233.chapter5part2.model.Direction;
import se233.chapter5part2.model.Food;
import se233.chapter5part2.model.Snake;
import se233.chapter5part2.model.SpecialFood;
import se233.chapter5part2.view.GameStage;
import se233.chapter5part2.view.Score;

public class GameLoop implements Runnable {
    private GameStage gameStage;
    private Snake snake;
    private Food food;
    private SpecialFood specialFood;
    private float interval = 1000.0f / 10;
    private boolean running;

    public GameLoop(GameStage gameStage, Snake snake, Food food) {
        this.snake = snake;
        this.gameStage = gameStage;
        this.food = food;
        running = true;
    }

    public GameLoop(GameStage gameStage, Snake snake, Food food, SpecialFood specialFood) {
        this.snake = snake;
        this.gameStage = gameStage;
        this.food = food;
        this.specialFood = specialFood;
        running = true;
    }

    public void keyProcess() {
        KeyCode curKey = gameStage.getKey();
        Direction curDirection = snake.getDirection();
        if (curKey == KeyCode.UP && curDirection != Direction.DOWN)
            snake.setDirection(Direction.UP);
        else if (curKey == KeyCode.DOWN && curDirection != Direction.UP)
            snake.setDirection(Direction.DOWN);
        else if (curKey == KeyCode.LEFT && curDirection != Direction.RIGHT)
            snake.setDirection(Direction.LEFT);
        else if (curKey == KeyCode.RIGHT && curDirection != Direction.LEFT)
            snake.setDirection(Direction.RIGHT);
        snake.move();
    }

    public void checkCollision() {
        if (snake.collided(food)) {
            snake.grow();
            food.respawn();
        }
        if (snake.collided(specialFood)) {
            snake.grow();
            specialFood.respawn();
        }
        if (snake.checkDead()) {
            running = false;
            gameStage.showDeathMessage();
        }
    }

    public void redraw() {
        gameStage.render(snake, food);
        if (specialFood != null) {
            gameStage.renderSpecialFood(specialFood);
        }
    }


    private void updateScore(Score score,Snake snake) {
        Platform.runLater(() -> {
            score.setScore(snake.getScore());
        });
    }

    @Override
    public void run() {
        while (running) {
            keyProcess();
            checkCollision();
            redraw();
            updateScore(gameStage.getScore(), snake);
            try {
                Thread.sleep((long) interval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
