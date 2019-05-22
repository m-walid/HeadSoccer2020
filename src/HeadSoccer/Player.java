package HeadSoccer;


import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import java.util.ArrayList;


public class Player {

    private ImageView player;
    private ArrayList<Image> images;
    private String type;
    private int score = 0;
    private boolean moveLeft = false;
    private boolean moveRight = false;

    public void setType(String type) {
        this.type = type;
    }

    public void setMoveLeft(boolean moveLeft) {
        this.moveLeft = moveLeft;
    }

    public void setMoveRight(boolean moveRight) {
        this.moveRight = moveRight;
    }

    public void checkMovement() {

                if (moveLeft) {
                    player.setTranslateX(player.getTranslateX() - 7);
                   if(!type.equals("com")) player.setImage(images.get(1));
                }
                if (moveRight) {
                    player.setTranslateX(player.getTranslateX() + 7);
                    if(!type.equals("com")) player.setImage(images.get(1));
                }
                if(!moveLeft && !moveRight) player.setImage(images.get(0));
    }



    public void jump() {
        Timeline t = new Timeline(new KeyFrame(Duration.millis(10), e -> player.setImage(images.get(2))),
                new KeyFrame(Duration.millis(30), e -> {
                    player.setImage(images.get(3));
                    player.setTranslateY(player.getTranslateY() - 13);
                    if (this.type.equals("player1")) player.setRotate(10);
                    else player.setRotate(-10);
                }),
                new KeyFrame(Duration.millis(160), e -> {
                    player.setImage(images.get(0));
                    player.setTranslateY(player.getTranslateY() + 13);
                    player.setRotate(0);
                }));
        t.play();

    }


    Player(String type) {
        //checkMovement();
        images = new ArrayList<>();
        this.type = type;
        if (type.equals("player1")) {
            images.add(new Image(getClass().getResource("/Resources/Player1/player1.png").toString()));
            images.add(new Image(getClass().getResource("/Resources/Player1/player12.png").toString()));
            images.add(new Image(getClass().getResource("/Resources/Player1/halfjump.png").toString()));
            images.add(new Image(getClass().getResource("/Resources/Player1/jump1.png").toString()));
            player = new ImageView(images.get(0));
            this.player.setTranslateX(45);


        } else if (type.equals("player2") || type.equals("com")) {
            images.add(new Image(getClass().getResource("/Resources/Player2/player2.png").toString()));
            images.add(new Image(getClass().getResource("/Resources/Player2/player12.png").toString()));
            images.add(new Image(getClass().getResource("/Resources/Player2/halfjump.png").toString()));
            images.add(new Image(getClass().getResource("/Resources/Player2/jump1.png").toString()));
            player = new ImageView(images.get(0));

            this.player.setTranslateX(900);

        }


        this.player.setTranslateY(600 - 250);

    }

    public ImageView getPlayer() {
        return player;
    }

    public int getScore() {
        return score;
    }

    public void goal() {
        score++;
    }

}


