package se233.chapter5part2.model;

import javafx.geometry.Point2D;
import se233.chapter5part2.view.GameStage;

import java.util.ArrayList;
import java.util.List;

public class Snake {
    private Direction direction;
    private Point2D head;
    private Point2D prev_tail;
    private List<Point2D> body;
    private int score = 0;

    public Snake(Point2D position) {
        direction = Direction.DOWN;
        body = new ArrayList<>();
        this.head = position;
        this.body.add(this.head);
    }
    public void move() {
        head = head.add(direction.current);
        prev_tail = body.remove(body.size() - 1);
        body.add(0, head);
    }
    public Direction getDirection() {
        return direction;
    }
    public void setDirection(Direction direction) {
        this.direction = direction;
    }
    public Point2D getHead() {
        return head;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    // Introduce a scoring mechanism - ??
    public boolean collided(Food food) {
        if (head.equals(food.getPosition())) {
            setScore(score + 1);
            return true;
        }
        return false;
    }
    public boolean collided(SpecialFood food) {
        if (food != null && head.equals(food.getPosition())) {
            setScore(score+5);
            return true;
        }
        return false;
    }
    public void grow() {
        body.add(prev_tail);
    }
    public int getLength() { return body.size(); }
    public List<Point2D> getBody() { return body; }
    public boolean checkDead() {
        boolean isOutOfBound = head.getX() < 0 || head.getY() < 0 || head.getX() > GameStage.WIDTH || head.getY() > GameStage.HEIGHT;
        boolean isHitBody = body.lastIndexOf(head) > 0;
        return isOutOfBound || isHitBody;
    }
}
