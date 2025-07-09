package se233.chapter5part1.controller;

import se233.chapter5part1.model.GameCharacter;
import se233.chapter5part1.view.GameStage;
import se233.chapter5part1.view.Score;

import java.util.List;

public class GameLoop implements Runnable {
    private GameStage gameStage;
    private int frameRate;
    private float interval;
    private boolean running;
    public GameLoop(GameStage gameStage) {
        this.gameStage = gameStage;
        frameRate = 10;
        interval = 1000.0f / frameRate;
        running = true;
    }
    private void update(List<GameCharacter> gameCharacterList) {

        for (GameCharacter gameCharacter : gameCharacterList) {
            boolean leftPressed = gameStage.getKeys().isPressed(gameCharacter.getLeftKey());
            boolean rightPressed = gameStage.getKeys().isPressed(gameCharacter.getRightKey());
            boolean upPressed = gameStage.getKeys().isPressed(gameCharacter.getUpKey());

            if (leftPressed && rightPressed) {
                gameCharacter.stop();
            } else if (leftPressed) {
                gameCharacter.getImageView().tick();
                gameCharacter.moveLeft();
            } else if (rightPressed) {
                gameCharacter.getImageView().tick();
                gameCharacter.moveRight();
            } else {
                gameCharacter.stop();
            }

            if (upPressed) {
                gameCharacter.jump();
            }
        }
    }
    private void updateScore(List<Score> scoreList, List<GameCharacter>
            gameCharacterList) {
        javafx.application.Platform.runLater(()-> {
            for (int i=0 ; i<scoreList.size() ; i++) {
                scoreList.get(i).setPoint(gameCharacterList.get(i).getScore());
            }
        });
    }
    @Override
    public void run() {
        while (running) {
            float time = System.currentTimeMillis();
            update(gameStage.getGameCharacterList());
            updateScore(gameStage.getScoreList(), gameStage.getGameCharacterList());
            time = System.currentTimeMillis() - time;
            if (time < interval) {
                try {
                    Thread.sleep((long) (interval - time));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    Thread.sleep((long) (interval - (interval % time)));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // In GameLoop.java

    public class GameLoop implements Runnable {
        private GameStage gameStage;
        private Snake snake;
        private Food food;
        private float interval = 1000.0f / 10;
        private boolean running;
        private int score = 0; // Added score variable

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
                updateScore(food); // Update score when food is consumed
                food.respawn();
            }
            if (snake.checkDead()) {
                running = false;
                showGameOverPopup(); // Show Game Over message
            }
        }

        private void updateScore(Food food) {
            if (food.isSpecialFood()) {
                score += 5; // Special food gives 5 points
            } else {
                score += 1; // Regular food gives 1 point
            }
            System.out.println("Score: " + score); // Display the updated score
        }

        private void showGameOverPopup() {
            javafx.application.Platform.runLater(() -> {
                javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
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

}