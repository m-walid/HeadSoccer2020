package HeadSoccer;


import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.util.Random;

public class Ball {
    private ImageView ball;
    private double speedX = 1;
    private double speedY = 0.1;
    private double rotate = 1;
    private double gravity = 0.12;
    private boolean alive = true;
    private boolean rand;
    private boolean flag;

    public double getSpeedX() {
        return speedX;
    }

    public boolean isAlive() {
        return alive;
    }

    public boolean isDead() {
        return !alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    private void setBall() {
        this.ball = new ImageView(new Image(getClass().getResource("/Resources/Ball/ballNew.png").toString()));
       // this.ball.setTranslateY(-30);
       // this.ball.setTranslateX(100);
        Random random = new Random();
        rand = random.nextBoolean();
        if(rand){
            this.ball.setTranslateY(-30);
            this.ball.setTranslateX(80);

        }
        else{
            this.ball.setTranslateY(-30);
            this.ball.setTranslateX(680);
        }
        flag=!rand;
    }

    public ImageView getBall() {
        return ball;
    }

    private double generateDouble(double min, double max) {
        double x = (Math.random() * ((max - min) + 1)) + min;
        return x;

    }

    public void ballMovement() {

                ball.setTranslateX(ball.getTranslateX() + speedX);
                ball.setTranslateY(ball.getTranslateY() + speedY);
                speedY += gravity;
                ball.setRotate(ball.getRotate() + rotate);

                //System.out.println(speedY);
    }


    public Ball() {

        setBall();
        ballMovement();

    }



    public void collision(Player p1, Player p2) {

        Rectangle ballRec = new Rectangle(ball.getTranslateX(), ball.getTranslateY(), 70, 68);
        Rectangle p1Rec = new Rectangle(p1.getPlayer().getTranslateX(), p1.getPlayer().getTranslateY(), 94, 199);
        Rectangle p2Rec = new Rectangle(p2.getPlayer().getTranslateX(), p2.getPlayer().getTranslateY(), 91, 199);

        Shape intersect = Shape.intersect(ballRec, p1Rec);
        if (intersect.getBoundsInLocal().getWidth() != -1 && !flag) {
            Media m22 = new Media(getClass().getResource("/Resources/Audios/He2.m4a").toString());  //change to caughtball.mp3 law 3ayez eli maw kan 3amelha
            MediaPlayer mm2 = new MediaPlayer(m22);
            mm2.setVolume(1);
            mm2.setAutoPlay(true);
            //    double rand =(Math.random()*((1000-p1Rec.getTranslateX())/860)+2);
            double rand;
            if (p1Rec.getX() > 0 && p1Rec.getX() < 300) {
                rand = generateDouble(3.5, 3.7);
            } else if (p1Rec.getX() > 300 && p1Rec.getX() < 400) {
                rand = generateDouble(2, 2.2);
            } else {
                rand = generateDouble(2, 2.1 );
            }
            speedX = rand;
            //System.out.println(speedX);
            rotate = 2;
            speedY = -1 * 8;


            p1.jump();
            flag = true;

        }

        intersect = Shape.intersect(ballRec, p2Rec);
        if (intersect.getBoundsInLocal().getWidth() != -1 && flag) {
            Media m22 = new Media(getClass().getResource("/Resources/Audios/Heya2.m4a").toString());  //same here
            MediaPlayer mm2 = new MediaPlayer(m22);
            mm2.setVolume(1);
            mm2.setAutoPlay(true);

            double rand;
            if (p2Rec.getX() > 700 && p2Rec.getX() < 1000) {
                rand = generateDouble(3.5, 3.7);
                //System.out.println(">700 <1000");
            } else if (p2Rec.getX() > 600 && p2Rec.getX() < 700) {
                rand = generateDouble(2, 2.2);
                //System.out.println(">600 <700");
            } else {
                rand = generateDouble(2, 2.1);
                //System.out.println("else");
            }
            //    double rand =(Math.random()*((1000-p2Rec.getTranslateX())/860)+2);
            // double rand = generateDouble(2.5,3);
            speedX = -1 * rand;
            //System.out.println(speedX);
            rotate = -2;
            speedY = -1 * 8;
            //System.out.println("Player 2 at collision is "+ p2Rec.getX()+" And rand is "+ rand);
            p2.jump();
            flag = false;
        }


    }


}
