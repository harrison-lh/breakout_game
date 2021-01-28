package breakout;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class Ball extends Sprite{

    public double size;

    public Ball() {
        Circle ball = new Circle();
        size = 10;
        ball.setRadius(size);

        node = ball;
    }


    @Override
    public void update() {
        node.setTranslateX(node.getTranslateX() + xVelocity);
        node.setTranslateY(node.getTranslateY() + yVelocity);
    }

    public Circle getAsCircle() {
        return (Circle) node;
    }

    public void setXVelocity(double v) {
        xVelocity = v;
    }
    public void setYVelocity(double v) {
        yVelocity = v;
    }

    @Override
    public boolean collide(Sprite other) {
        if (other instanceof Paddle) {
            return collide((Paddle)other);
        }
        else if (other instanceof Block) {
            return collide((Block)other);
        }
        return false;
    }

    private boolean collide(Paddle paddle) {
        double xCenter = node.getTranslateX()+size/2;
        double yCenter = node.getTranslateY()+size/2;

        if (paddle.xPos <= xCenter &&
            xCenter <= paddle.xPos + paddle.size &&
            paddle.yPos <= yCenter+size/2 &&
            yCenter-size/2 <= paddle.yPos + paddle.size/10) {
            return true;
        }
        return false;
    }
    public boolean collideXToRight(Paddle paddle) {
        double xCenter = node.getTranslateX()+size/2;
        double yCenter = node.getTranslateY()+size/2;

        double paddleRadius = paddle.size/10;
        double xLeftPaddleFocus = paddle.xPos + paddle.size/12;
        double yLeftPaddleFocus = paddle.yPos + paddle.size/10;
        double xRightPaddleFocus = paddle.xPos + paddle.size*11/12;
        double yRightPaddleFocus = paddle.yPos + paddle.size/10;

        if (Math.sqrt(Math.pow((xCenter - xLeftPaddleFocus),2) +
                        Math.pow((yCenter - yLeftPaddleFocus),2)) >
                (size + paddleRadius) ||
            Math.sqrt(Math.pow((xCenter - xRightPaddleFocus),2) +
                        Math.pow((yCenter - yRightPaddleFocus),2)) <=
                (size + paddleRadius)) {
            return true;
        }
        return false;


    }
    public boolean collide(Block block) {
        if ((withinRange(node.getTranslateX()+size,block.block.getX(),block.block.getX()+size) &&
            withinRange(node.getTranslateY(),block.block.getY(),block.block.getX()+size/2))) {}
        return false;
    }
    private boolean withinRange(double point, double low, double high) {
        return (low <= point && point <= high);
    }
}
