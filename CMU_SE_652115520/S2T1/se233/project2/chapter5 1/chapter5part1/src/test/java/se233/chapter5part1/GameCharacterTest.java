package se233.chapter5part1;

import javafx.scene.input.KeyCode;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se233.chapter5part1.model.GameCharacter;
import se233.chapter5part1.view.GameStage;


import static org.junit.jupiter.api.Assertions.*;

public class GameCharacterTest {
    private GameCharacter gameCharacter;

    @BeforeAll
    public static void initJfxRuntime() {
        javafx.application.Platform.startup(() -> {});
    }

    @BeforeEach
    public void setup() {
        gameCharacter = new GameCharacter(0, 30, 30, "assets/Character1.png", 4, 3, 2, 111, 97, KeyCode.A, KeyCode.D, KeyCode.W);
    }

    @Test
    public void respawn_givenNewGameCharacter_thenCoordinatesAre30_30() {
        gameCharacter.respawn();
        assertEquals(30, gameCharacter.getX(), "Initial x should be 30");
        assertEquals(30, gameCharacter.getY(), "Initial y should be 30");
    }

    @Test
    public void respawn_givenNewGameCharacter_thenScoreIs0() {
        gameCharacter.respawn();
        assertEquals(0, gameCharacter.getScore(), "Initial score should be 0");
    }

    @Test
    public void moveX_givenMoveRightOnce_thenXCoordinateIncreasedByXVelocity() {
        gameCharacter.respawn();
        gameCharacter.moveRight();
        gameCharacter.moveX();
        int expectedX = 30 + gameCharacter.getxVelocity(); // 使用 getter 方法
        assertEquals(expectedX, gameCharacter.getX(), "X coordinate should increase by xVelocity");
    }

    @Test
    public void moveY_givenTwoConsecutiveCalls_thenYVelocityIncreases() {
        gameCharacter.respawn();
        gameCharacter.moveY();
        int yVelocity1 = gameCharacter.getyVelocity(); // 使用 getter 方法
        gameCharacter.moveY();
        int yVelocity2 = gameCharacter.getyVelocity(); // 使用 getter 方法
        assertTrue(yVelocity2 > yVelocity1, "Velocity should be increasing");
    }

    @Test
    public void moveY_givenTwoConsecutiveCalls_thenYAccelerationUnchanged() {
        gameCharacter.respawn();
        gameCharacter.moveY();
        int yAcceleration1 = gameCharacter.getyAcceleration(); // 使用 getter 方法
        gameCharacter.moveY();
        int yAcceleration2 = gameCharacter.getyAcceleration(); // 使用 getter 方法
        assertEquals(yAcceleration1, yAcceleration2, "Acceleration should not change");
    }

    // Part 1: Testing checkReachGameWall method
    @Test
    public void checkReachGameWall_givenCharacterMovesLeft_thenShouldStopAtLeftWall() {
        gameCharacter.setPosition(0, 50); // Near the left border
        gameCharacter.moveLeft();
        gameCharacter.checkReachGameWall();
        assertEquals(0, gameCharacter.getX(), "Character should stop at the left wall");
    }

    @Test
    public void checkReachGameWall_givenCharacterMovesRight_thenShouldStopAtRightWall() {
        gameCharacter.setPosition(GameStage.WIDTH - gameCharacter.getCharacterWidth(), 50); // Near the right border
        gameCharacter.moveRight();
        gameCharacter.checkReachGameWall();
        assertEquals(GameStage.WIDTH - gameCharacter.getCharacterWidth(), gameCharacter.getX(), "Character should stop at the right wall");
    }

    // Part 2: Testing jump method
    @Test
    public void jump_givenCharacterCanJump_thenShouldStartJumping() {
        gameCharacter.setJumpAbility(true); // Allow jumping
        gameCharacter.jump();
        assertTrue(gameCharacter.isJumping(), "Character should start jumping");
        assertFalse(gameCharacter.isFalling(), "Character should not be falling while jumping");
    }

    @Test
    public void jump_givenCharacterCannotJump_thenShouldNotStartJumping() {
        gameCharacter.setJumpAbility(false); // Disallow jumping
        gameCharacter.jump();
        assertFalse(gameCharacter.isJumping(), "Character should not start jumping");
    }

    // Part 3: Testing collided method
    @Test
    public void collided_givenHorizontalCollision_thenShouldDetectCollision() {
        GameCharacter otherCharacter = new GameCharacter(1, 100, 50, "assets/Character2.png", 4, 3, 2, 111, 97, KeyCode.A, KeyCode.D, KeyCode.W);
        gameCharacter.setPosition(90, 50); // Position close to otherCharacter on x-axis
        assertTrue(gameCharacter.collided(otherCharacter), "Characters should collide horizontally");
    }

    @Test
    public void collided_givenVerticalCollision_thenShouldDetectCollision() {
        GameCharacter otherCharacter = new GameCharacter(1, 100, 100, "assets/Character2.png", 4, 3, 2, 111, 97, KeyCode.A, KeyCode.D, KeyCode.W);
        gameCharacter.setPosition(100, 90); // Position close to otherCharacter on y-axis
        assertTrue(gameCharacter.collided(otherCharacter), "Characters should collide vertically");
    }
}
