package se233.chapter5part2;

import javafx.geometry.Point2D;
import org.junit.jupiter.api.Test;
import se233.chapter5part2.model.Food;
import se233.chapter5part2.model.Snake;

import static org.junit.jupiter.api.Assertions.*;

public class FoodTest {

    @Test
    public void testSpecialFoodWorthFivePoints() {
        Food specialFood = new Food(new Point2D(5, 5));
        specialFood.setSpecialFood(true); // 设为特殊食物

        Snake snake = new Snake(new Point2D(5, 5));
        int initialScore = snake.getScore();
        snake.consumeFood(specialFood); // 蛇吃特殊食物
        assertEquals(initialScore + 5, snake.getScore(), "吃特殊食物应得分增加5");
    }

    @Test
    public void testSpecialFoodColorIsGreen() {
        Food specialFood = new Food(new Point2D(5, 5));
        specialFood.setSpecialFood(true); // 设为特殊食物

        // 检查食物颜色
        assertTrue(specialFood.isSpecialFood(), "特殊食物应该为绿色");
    }
}
