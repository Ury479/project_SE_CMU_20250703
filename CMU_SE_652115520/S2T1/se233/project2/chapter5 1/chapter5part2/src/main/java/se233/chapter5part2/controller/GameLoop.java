package se233.chapter5part2.controller;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import se233.chapter5part2.model.Direction;
import se233.chapter5part2.model.Food;
import se233.chapter5part2.model.Snake;
import se233.chapter5part2.view.GameStage;

public class GameLoop implements Runnable {
    private GameStage gameStage;
    private Snake snake;
    private Food food;
    private float interval = 1000.0f / 10;
    private boolean running;
    private int score = 0; // 添加得分变量

    public GameLoop(GameStage gameStage, Snake snake, Food food) {
        this.snake = snake;
        this.food = food;
        this.gameStage = gameStage;
        running = true;
    }

    private void keyProcess() {
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

    private void checkCollision() {
        if (snake.collided(food)) {
            snake.grow();
            updateScore(food); // 更新得分
            food.respawn();
        }
        if (snake.checkDead()) {
            running = false;
            showGameOverPopup(); // 显示 Game Over 信息
        }
    }

    private void updateScore(Food food) {
        if (food.isSpecialFood()) {
            score += 5; // 特殊食物加5分
        } else {
            score += 1; // 普通食物加1分
        }
        System.out.println("Score: " + score); // 输出当前得分
    }

    private void showGameOverPopup() {
        Platform.runLater(() -> {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Game Over");
            alert.setHeaderText(null);
            alert.setContentText("The snake has collided with the border! Game Over.");
            alert.showAndWait();
        });
    }

    private void redraw() {
        gameStage.render(snake, food);
    }

    @Override
    public void run() {
        while (running) {
            keyProcess();
            checkCollision();
            redraw();
            try {
                Thread.sleep((long) interval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
