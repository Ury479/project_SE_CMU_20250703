package se233.chapter5_2;

import javafx.embed.swing.JFXPanel;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import javafx.geometry.Point2D;
import se233.chapter5_2.model.Direction;
import se233.chapter5_2.model.Food;
import se233.chapter5_2.model.Snake;

public class SnakeTest {
    private Snake snake ;

    @Before
    public void setup() {
//        JFXPanel jfxPannel = new JFXPanel();
        snake = new Snake(new Point2D(0,0));
    }

    @Test
    public void snakeShouldBeSpawnAtTheCoordinateItWasCreated(){
        assertEquals(snake.getHead() , new Point2D(0,0));
    }

    @Test
    public void snakeShouldMoveDownIfItHeadingDownward(){
        snake.setCurrentDirection(Direction.DOWN);
        snake.update() ;
        assertEquals(snake.getHead(), new Point2D(0,1));
    }

    @Test
    public void collisionFlagShouldRaiseIfSnakeCollideWithFood(){
        Food food =  new Food(new Point2D(0,0));
        assertTrue(snake.isCollidingWith(food));
    }

    @Test
    public void foodShouldRespawnOnDifferentCoordinates(){
        Food food =  new Food(new Point2D(0,0));
        food.respawn();
        assertNotSame(food.getPosition() , new Point2D(0,0));
    }

    @Test
    public void snakeGrowthShouldAddItsLengthByOne() {
        snake.grow();
        assertEquals(snake.getLength(),2);
    }

    @Test
    public void bodyOfGrownSnakeShouldContainPreviousHead(){
        Point2D cur_head = snake.getHead();
        snake.update();
        snake.grow();
        assertTrue(snake.getBody().contains(cur_head));
    }

    @Test
    public void snakeWillDieIfItGoesTheGameBorder(){
        snake = new Snake(new Point2D(30,30));
        snake.setCurrentDirection(Direction.RIGHT);
        snake.update();
        assertTrue(snake.isDead());
    }

    @Test
    public void snakeWillDieIfItHitItsBody(){
        snake = new Snake(new Point2D(0,0));
        snake.setCurrentDirection(Direction.DOWN);
        snake.update();
        snake.grow();
        snake.setCurrentDirection(Direction.LEFT);
        snake.update();
        snake.grow();
        snake.setCurrentDirection(Direction.UP);
        snake.update();
        snake.grow();
        snake.setCurrentDirection(Direction.RIGHT);
        snake.update();
        snake.grow();
        assertTrue(snake.isDead());
    }

    @Test
    public void foodShouldGiveScore() {
        Food normalFood = new Food();
        assertNotEquals(0,normalFood.getScore(),0);
    }

}