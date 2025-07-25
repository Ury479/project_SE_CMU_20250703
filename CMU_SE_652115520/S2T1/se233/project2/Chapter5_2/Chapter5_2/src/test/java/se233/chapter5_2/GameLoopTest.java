package se233.chapter5_2;

import javafx.embed.swing.JFXPanel;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import org.junit.Before;
import org.junit.Test;
import se233.chapter5_2.controller.GameLoop;
import se233.chapter5_2.model.Food;
import se233.chapter5_2.model.Snake;
import se233.chapter5_2.view.Platform;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import static org.junit.Assert.*;

public class GameLoopTest {
    private GameLoop gameLoopUnderTest ;
    private Method update ;
    private Method collision ;
    private Method redraw ;
    private Method updateScore;

    @Before
    public void init() throws NoSuchMethodException {
        JFXPanel jfxPanel = new JFXPanel();
        gameLoopUnderTest = new GameLoop(new Platform(), new Snake(new Point2D(0,0)), new Food(new Point2D(0,0)));
        update = gameLoopUnderTest.getClass().getDeclaredMethod("update");
        collision = gameLoopUnderTest.getClass().getDeclaredMethod("checkCollision");
        redraw = gameLoopUnderTest.getClass().getDeclaredMethod("redraw");
        updateScore = gameLoopUnderTest.getClass().getDeclaredMethod("updateScore");
        updateScore.setAccessible(true);
        update.setAccessible(true);
        redraw.setAccessible(true);
        collision.setAccessible(true);
    }

    private void clockTickHelper() throws InvocationTargetException, IllegalAccessException {
        update.invoke(gameLoopUnderTest);
        collision.invoke(gameLoopUnderTest);
        redraw.invoke(gameLoopUnderTest);
        updateScore.invoke(gameLoopUnderTest);
    }

    @Test
    public void testClockTick() throws InvocationTargetException, IllegalAccessException {
//        gameLoopUnderTest = new GameLoop(new Platform(), new Snake(new Point2D(0,0)), new Food());
        clockTickHelper();
        assertEquals(this.gameLoopUnderTest.getSnake().getHead(),new Point2D(0,1));
    }

    @Test
    public void testNoBack() throws InvocationTargetException, IllegalAccessException{
        gameLoopUnderTest = new GameLoop(new Platform(), new Snake(new Point2D(0,0)), new Food());
        gameLoopUnderTest.getPlatform().setKey(KeyCode.DOWN);
        clockTickHelper();
        assertEquals(gameLoopUnderTest.getSnake().getHead(),new Point2D(0,1));
        gameLoopUnderTest.getPlatform().setKey(KeyCode.DOWN);
        clockTickHelper();
        assertEquals(gameLoopUnderTest.getSnake().getHead(),new Point2D(0,2));
        gameLoopUnderTest.getPlatform().setKey(KeyCode.UP);
        clockTickHelper();
        assertEquals(gameLoopUnderTest.getSnake().getHead(),new Point2D(0,3));
    }

    @Test
    public void scoreShouldBeIncreasedWhenSnakeEatsFood() throws InvocationTargetException, IllegalAccessException{
        gameLoopUnderTest = new GameLoop(new Platform(), new Snake(new Point2D(0,0)), new Food(new Point2D(0,1)));
        gameLoopUnderTest.getPlatform().setKey(KeyCode.DOWN);
        clockTickHelper();
//        assertEquals(gameLoopUnderTest.getSnake().getHead(),new Point2D(0,1));
        clockTickHelper();
//        System.out.println(gameLoopUnderTest.getSnake().getSnakeScore());
        assertNotEquals(0,gameLoopUnderTest.getSnake().getSnakeScore());
    }

    @Test
    public void scoreShouldBeIncreasedByFiveWhenSnakeEatsFood() throws InvocationTargetException, IllegalAccessException{
        gameLoopUnderTest = new GameLoop(new Platform(), new Snake(new Point2D(0,0)), new Food(new Point2D(0,1),true));
        gameLoopUnderTest.getPlatform().setKey(KeyCode.DOWN);
        clockTickHelper();
//        assertEquals(gameLoopUnderTest.getSnake().getHead(),new Point2D(0,1));
        clockTickHelper();
//        System.out.println(gameLoopUnderTest.getSnake().getSnakeScore());
        assertEquals(5,gameLoopUnderTest.getSnake().getSnakeScore());
    }
}