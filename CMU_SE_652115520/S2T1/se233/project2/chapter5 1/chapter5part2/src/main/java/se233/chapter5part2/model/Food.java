package se233.chapter5part2.model;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import se233.chapter5part2.view.GameStage;

import java.util.Random;

public class Food {
    private Point2D position;
    private boolean isSpecialFood; // 标记是否为特殊食物
    private Random rn;

    public Food(Point2D position) {
        this.rn = new Random();
        this.position = position;
        this.isSpecialFood = rn.nextBoolean(); // 随机决定是否为特殊食物
    }

    public Food() {
        this.rn = new Random();
        this.position = new Point2D(rn.nextInt(GameStage.WIDTH), rn.nextInt(GameStage.HEIGHT));
        this.isSpecialFood = rn.nextBoolean(); // 随机决定是否为特殊食物
    }

    public void respawn() {
        Point2D prev_position = this.position;
        do {
            this.position = new Point2D(rn.nextInt(GameStage.WIDTH), rn.nextInt(GameStage.HEIGHT));
        } while (prev_position.equals(this.position));
        this.isSpecialFood = rn.nextBoolean(); // 重新生成时随机决定是否为特殊食物
    }

    public boolean isSpecialFood() {
        return this.isSpecialFood;
    }

    public Point2D getPosition() {
        return position;
    }
}
