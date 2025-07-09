package se233.chapter5part2;

import javafx.geometry.Point2D;
import org.junit.jupiter.api.Test;
import se233.chapter5part2.model.Direction;
import se233.chapter5part2.model.Food;
import se233.chapter5part2.model.Snake;

import static org.junit.jupiter.api.Assertions.*;

public class SnakeTest {

    @Test
    public void testConsumeRegularFoodIncreasesScore() {
        Snake snake = new Snake(new Point2D(5, 5));
        Food food = new Food(new Point2D(5, 5)); // 将食物放置在蛇头的位置

        int initialScore = snake.getScore();
        snake.consumeFood(food); // 模拟蛇吃食物
        assertEquals(initialScore + 1, snake.getScore(), "吃普通食物应得分增加1");
    }

    @Test
    public void testPreventOppositeDirectionChange() {
        Snake snake = new Snake(new Point2D(5, 5));
        snake.setDirection(Direction.LEFT); // 设置初始方向为左

        snake.setDirection(Direction.RIGHT); // 尝试更改为相反方向
        assertNotEquals(Direction.RIGHT, snake.getDirection(), "蛇不应能够改变到相反的方向");
    }
}
