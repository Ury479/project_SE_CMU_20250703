package se233.chapter5part2;

import javafx.application.Platform;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import se233.chapter5part2.controller.GameLoop;
import se233.chapter5part2.model.Direction;
import se233.chapter5part2.model.Food;
import se233.chapter5part2.model.Snake;
import se233.chapter5part2.view.GameStage;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GameLoopTest {
    private GameStage gameStage;
    private Snake snake;
    private Food food;
    private GameLoop gameLoop;

    @BeforeEach
    public void setUp() {
        gameStage = new GameStage();
        snake = new Snake(new Point2D(0, 0));
        food = new Food(new Point2D(0, 1));
        gameLoop = new GameLoop(gameStage, snake, food);
    }

    private void clockTickHelper() throws Exception {
        ReflectionHelper.invokeMethod(gameLoop, "keyProcess", new Class<?>[0]);
        ReflectionHelper.invokeMethod(gameLoop, "checkCollision", new Class<?>[0]);
        ReflectionHelper.invokeMethod(gameLoop, "redraw", new Class<?>[0]);
    }

    @Test
    public void keyProcess_pressRight_snakeTurnRight() throws Exception {
        ReflectionHelper.setField(gameStage, "key", KeyCode.RIGHT);
        ReflectionHelper.setField(snake, "direction", Direction.DOWN);
        clockTickHelper();
        Direction currentDirection = (Direction) ReflectionHelper.getField(snake, "direction");
        assertEquals(Direction.RIGHT, currentDirection);
    }

    @Test
    public void collided_snakeEatFood_shouldGrow() throws Exception {
        clockTickHelper();
        assertTrue(snake.getLength() > 1);
        clockTickHelper();
        assertNotSame(food.getPosition(), new Point2D(0, 1));
    }

    @Test
    public void collided_snakeHitBorder_shouldDie() throws Exception {
        Snake mockSnake = Mockito.mock(Snake.class);
        Food mockFood = Mockito.mock(Food.class);
        when(mockSnake.checkDead()).thenReturn(true);

        GameStage mockGameStage = Mockito.mock(GameStage.class);
        GameLoop gameLoop = new GameLoop(mockGameStage, mockSnake, mockFood);

        GameLoop spyGameLoop = Mockito.spy(gameLoop);

        spyGameLoop.checkCollision();

        Boolean running = (Boolean) ReflectionHelper.getField(spyGameLoop, "running");
        assertFalse(running, "The game should be stopped when the snake hits the border.");
    }

    @Test
    public void redraw_calledThreeTimes_snakeAndFoodShouldRenderThreeTimes() throws Exception {
        GameStage mockGameStage = Mockito.mock(GameStage.class);
        Snake mockSnake = Mockito.mock(Snake.class);
        Food mockFood = Mockito.mock(Food.class);
        GameLoop gameLoop = new GameLoop(mockGameStage, mockSnake, mockFood);
        ReflectionHelper.invokeMethod(gameLoop, "redraw", new Class<?>[0]);
        ReflectionHelper.invokeMethod(gameLoop, "redraw", new Class<?>[0]);
        ReflectionHelper.invokeMethod(gameLoop, "redraw", new Class<?>[0]);
        verify(mockGameStage, times(3)).render(mockSnake, mockFood);
    }

    @Test
    public void direction_snakeChangeCannotGoOppositeDirection() {
        snake.setDirection(Direction.UP);
        gameStage.setKey(KeyCode.DOWN);

        gameLoop.keyProcess();

        assertFalse(snake.getDirection() == Direction.DOWN);
    }
}