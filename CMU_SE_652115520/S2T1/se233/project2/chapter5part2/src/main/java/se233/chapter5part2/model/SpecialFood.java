package se233.chapter5part2.model;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

public class SpecialFood extends Food {
    private static final int POINTS = 5;
    private static final Color COLOR = Color.GREEN;

    public SpecialFood(Point2D position) {
        super(position);
    }

    public SpecialFood() {
        super();
    }
}
