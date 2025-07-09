package se233.chapter5part1;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se233.chapter5part1.model.GameCharacter;
import se233.chapter5part1.model.Keys;
import se233.chapter5part1.view.GameStage;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

public class GameCharacterTest {
    private GameCharacter gameCharacter;
    private GameCharacter gameCharacter2;
    Field xVelocityField, yVelocityField, yAccelerationField, yMaxVelocityField;

    @BeforeAll
    public static void initJfxRuntime() {
        javafx.application.Platform.startup(() -> {});
    }

    @BeforeEach
    public void setUp() throws NoSuchFieldException {
        gameCharacter = new GameCharacter(0, 30, 30, "assets/Character1.png", 4, 3, 2, 111, 97, KeyCode.A, KeyCode.D, KeyCode.W);
        xVelocityField = gameCharacter.getClass().getDeclaredField("xVelocity");
        yVelocityField = gameCharacter.getClass().getDeclaredField("yVelocity");
        yMaxVelocityField = gameCharacter.getClass().getDeclaredField("yMaxVelocity");
        yAccelerationField = gameCharacter.getClass().getDeclaredField("yAcceleration");
        xVelocityField.setAccessible(true);
        yVelocityField.setAccessible(true);
        yAccelerationField.setAccessible(true);
    }

    @Test
    public void moveX_givenMoveRightOnce_thenXCoordinateIncreasedByXVelocity() throws IllegalAccessException {
        gameCharacter.respawn();
        gameCharacter.moveRight();
        gameCharacter.moveX();
        assertEquals(30 + xVelocityField.getInt(gameCharacter), gameCharacter.getX(), "Move right x");
    }

    @Test
    public void moveY_givenTwoConsecutiveCalls_thenYVelocityIncreases() throws IllegalAccessException {
        gameCharacter.respawn();
        gameCharacter.moveY();
        int yVelocity1 = yVelocityField.getInt(gameCharacter);
        gameCharacter.moveY();
        int yVelocity2 = yVelocityField.getInt(gameCharacter);
        assertTrue(yVelocity2 > yVelocity1, "Velocity is increasing");
    }

    @Test
    public void moveY_givenTwoConsecutiveCalls_thenYAccelerationUnchanged() throws IllegalAccessException {
        gameCharacter.respawn();
        gameCharacter.moveY();
        int yAcceleration1 = yAccelerationField.getInt(gameCharacter);
        gameCharacter.moveY();
        int yAcceleration2 = yAccelerationField.getInt(gameCharacter);
        assertTrue(yAcceleration1 == yAcceleration2, "Acceleration is not changing");
    }

    @Test
    public void respawn_givenNewGameCharacter_thenCoordinatesAre30_30() {
        gameCharacter.respawn();
        assertEquals(30, gameCharacter.getX(), "Initial x");
        assertEquals(30, gameCharacter.getY(), "Initial y");
    }

    @Test
    public void respawn_givenNewGameCharacter_thenScoreIs0() {
        gameCharacter.respawn();
        assertEquals(0, gameCharacter.getScore(), "Initial score");
    }


    @Test
    public void checkReachGameWall_moveCharacterOutsideLeftBorder_thenXIs0() {
        gameCharacter.setX(-10);
        gameCharacter.checkReachGameWall();
        assertEquals(0, gameCharacter.getX(), "Left wall reach");
    }

    @Test
    public void checkReachGameWall_moveCharacterOutsideRightBorder_thenXIsLessThanGameStageWIDTH() {
        gameCharacter.setX(GameStage.WIDTH + 10);
        gameCharacter.checkReachGameWall();
        assertTrue(gameCharacter.getX() <= GameStage.WIDTH, "Right wall reach");
    }

    @Test
    public void jump_characterCanJump_thenIsJumpingTrue() {
        gameCharacter.setCanJump(true);
        gameCharacter.jump();
        assertTrue(gameCharacter.isJumping(), "Character Jump");
    }

    @Test
    public void jump_characterCannotJump_thenIsJumpingFalse() {
        gameCharacter.setCanJump(false);
        gameCharacter.jump();
        assertFalse(gameCharacter.isJumping(), "Character didn't Jump");
    }

    @Test
    public void collided_newCharacterCollideHorizontally_thenIsCollidedFalse() {
        gameCharacter2 = new GameCharacter(1, 35, 30, "assets/Character2.png", 4, 4 ,1, 129,66, KeyCode.LEFT, KeyCode.RIGHT, KeyCode.UP);
        gameCharacter2.setxVelocity(gameCharacter2.getxMaxVelocity());
        gameCharacter2.moveX();
        assertFalse(gameCharacter.collided(gameCharacter2), "Collided with other character");
    }

    @Test
    public void collided_newCharacterCollideVertically_thenIsCollidedTrue() {
        gameCharacter2 = new GameCharacter(1, 30, 40, "assets/Character2.png", 4, 4 ,1, 129,66, KeyCode.LEFT, KeyCode.RIGHT, KeyCode.UP);
        gameCharacter2.setyVelocity(gameCharacter2.getyMaxVelocity());
        gameCharacter2.setFalling(true);
        gameCharacter2.moveY();
        assertTrue(gameCharacter.collided(gameCharacter2), "Collided with other character");
    }

    @Test
    public void keypress_singlekeypress_thenIsPressedIsTrue() {
        KeyCode key = KeyCode.A;
        Keys keys = new Keys();
        keys.add(key);
        assertTrue(keys.isPressed(key), "Key pressed");
    }

    @Test
    public void keypress_sequencekeypress_thenIsPressedIsTrue() {
        KeyCode key1 = KeyCode.A;
        KeyCode key2 = KeyCode.B;
        Keys keys = new Keys();
        keys.add(key1);
        keys.add(key2);
        assertTrue(keys.isPressed(key1), "Key1 pressed");
        assertTrue(keys.isPressed(key2), "Key2 pressed");
    }

}