package HeadSoccer;


import javafx.animation.*;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class StartGame {
    private BorderPane root = new BorderPane();
    private Player p1 = new Player("player1");
    private Player p2 = new Player("player2");
    private ImageView bg = new ImageView(new Image(getClass().getResource("/Resources/Background/background1.jpg").toString()));
    private Label score = new Label(p1.getScore() + " - " + p2.getScore());
    private double time1 = 0;
    private double time2 = 0;
    private double rand = Math.random();
    private ArrayList<Ball> balls = new ArrayList<>();
    private boolean pause;
    private StackPane pausePane = new StackPane();
    private  FinalMessage finalMessage;
    private VBox vBox=new VBox();
    private Label goal=new Label("GOAL");
    private HBox hBox=new HBox();

    public StartGame() {
        goal.setFont(Font.font(35));
        goal.setPadding(new Insets(26,0,0,0));
        goal.setOpacity(0);
        score.setFont(Font.font(70));
        score.setTextFill(Color.WHITE);
        hBox.setPadding(new Insets(10, 0, 0, 425));
        hBox.setSpacing(30);
        hBox.getChildren().addAll(score,goal);
        balls.add(new Ball());
        root.getChildren().addAll(bg, p1.getPlayer(), p2.getPlayer(), balls.get(0).getBall());
        root.setTop(hBox);
        pausePane.setPrefSize(1000,600);
        ImageView mainmenuBt =new ImageView(new Image(getClass().getResource("/Resources/Buttons/mainmenuBt.png").toString()));
        ImageView exitBt =new ImageView(new Image(getClass().getResource("/Resources/Buttons/exit.png").toString()));
        exitBt.setOnMouseClicked(e->{
            exitBt.setCursor(Cursor.HAND);
            FrontPage.Window.close();
        });
        exitBt.setOnMouseEntered(e->{
            exitBt.setCursor(Cursor.HAND);

        });

        mainmenuBt.setOnMouseClicked(e->{

            FrontPage f = new FrontPage();
            Media bgsong = new Media(getClass().getResource("/Resources/Audios/UEFAChampionsLeague201819IntroHD-Arabsong.mp3").toString());
            Main.bgsongplayer = new MediaPlayer(bgsong);
            Main.bgsongplayer.setAutoPlay(true);
            Main.bgsongplayer.setCycleCount(100);
            Main.bgsongplayer.setVolume(0.5);

            Scene scene1 = new Scene(f, 1000, 600);
            FrontPage.Window.setScene(scene1);


        });
        mainmenuBt.setOnMouseEntered(
                e->
                {
                    mainmenuBt.setCursor(Cursor.HAND);
                }
        );


        Rectangle rec  =new Rectangle(400,300,Color.BLACK);
        rec.setArcHeight(20);
        rec.setArcWidth(20);
        rec.setOpacity(0.35);

        vBox.setSpacing(25);
        vBox.setPadding(new Insets(160,0,0,410));
        vBox.getChildren().addAll(mainmenuBt,exitBt);
        StackPane.setAlignment(vBox, Pos.CENTER);
        pausePane.getChildren().addAll(rec,vBox);
        root.setCenter(pausePane);
        BorderPane.setAlignment(pausePane,Pos.CENTER);
    }


    public Scene singlePlayer(String mode) throws Exception {
        p2.setType("com");
        double chance;
        if (mode.equals("EASY")) chance = 0.69;
        else if (mode.equals("MID")) chance = 0.84;
        else chance = 0.98;
        Scene scene = new Scene(root, 1000, 600);

      /* AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                updateBalls();
               p1.checkMovement();
               p2.checkMovement();
                if (win()) {
                    this.stop();
                }
                checkBoundaries();
                comMovement(chance);
                keysCheckSingle(scene);
            }
        };
        animationTimer.start();*/


        Timeline t = new Timeline(new KeyFrame(Duration.millis(5.2), e -> {
            if (pause)
                pauseMenu(true);
            else if (!win("single")) {

                pauseMenu(false);

                updateBalls();
                p1.checkMovement();
                p2.checkMovement();

                checkBoundaries();
                comMovement(chance);
                keysCheckSingle(scene);
            }
            else pause=true;

        }));
        t.setCycleCount(Animation.INDEFINITE);
        t.play();
        t.setRate(1);


        return scene;
    }


    public Scene multiPlayer() {
        Scene scene = new Scene(root, 1000, 600);

       /* AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                updateBalls();
                if (win()) {
                    this.stop();
                }
                checkBoundaries();
                keysCheckMulti(scene);
            }
        };
        animationTimer.start();*/


        Timeline t = new Timeline(new KeyFrame(Duration.millis(5.2), e -> {
            if (pause) {
                pauseMenu(true);

            }
            else if (!win("multi")) {
                pauseMenu(false);
                updateBalls();
                p1.checkMovement();
                p2.checkMovement();
                checkBoundaries();
                keysCheckMulti(scene);
            }
            else pause=true;

        }));


        t.setCycleCount(Animation.INDEFINITE);
        t.play();
        t.setRate(1);


        return scene;
    }


    private Boolean win(String mode) {
        String s1=new String();
        String s2=new String();
        if(mode.equals("multi")){
            s1="P1";
            s2="P2";
        }
        else if(mode.equals("single")){
            s1="P1S";
            s2="COM";
        }
        if (p1.getScore() == 7) {
                finalMessage = new FinalMessage(s1);
                root.setTop(finalMessage);
           BorderPane.setAlignment(finalMessage,Pos.TOP_CENTER);
           Timeline t=new Timeline(new KeyFrame(Duration.seconds(5),e->{
               root.getChildren().remove(finalMessage);
               vBox.setPadding(new Insets(220,0,0,410));
           }));
           t.play();
            return true;
        } else if (p2.getScore() == 7) {
                finalMessage = new FinalMessage(s2);
               root.setTop(finalMessage);
                BorderPane.setAlignment(finalMessage,Pos.TOP_CENTER);
            Timeline t=new Timeline(new KeyFrame(Duration.seconds(5),e->{
                root.getChildren().remove(finalMessage);
                vBox.setPadding(new Insets(220,0,0,410));

            }));
            t.play();
            return true;
        }
        else return false;
    }


    private void comMovement(double chance) {
        time2 += 0.0052;

        if (time2 > 2.5) {
            rand = Math.random();
            time2 = 0;
        }
        Ball closest = new Ball();
        if (!balls.isEmpty()) closest = balls.get(0);
        for (Ball ball : balls) {
            if (ball.getBall().getTranslateY() < 400 && ball.getBall().getTranslateY() > 100 && ball.getBall().getTranslateX() > 460 ) {

                if (ball.getBall().getTranslateY() > closest.getBall().getTranslateY()) closest = ball;

            }
        }
        if (!balls.isEmpty() && rand < chance ) {
            if (closest.getBall().getTranslateX() > p2.getPlayer().getTranslateX()) {
                p2.setMoveRight(true);
                p2.setMoveLeft(false);
            } else if (closest.getBall().getTranslateX() < p2.getPlayer().getTranslateX()) {
                p2.setMoveRight(false);
                p2.setMoveLeft(true);
            }
        }

        if (win("single")) {
            p2.setMoveRight(false);
            p2.setMoveLeft(false);
        }

    }


    private void checkBoundaries() {
        if (p1.getPlayer().getTranslateX() < 37) {
            p1.setMoveLeft(false);
            p1.getPlayer().setTranslateX(37);
        }
        if (p1.getPlayer().getTranslateX() > 490 - 140) {
            p1.setMoveRight(false);
            p1.getPlayer().setTranslateX(490 - 140);
        }
        if (p2.getPlayer().getTranslateX() < 490 + 50) {
            p2.setMoveLeft(false);
            p2.getPlayer().setTranslateX(490 + 50);
        }
        if (p2.getPlayer().getTranslateX() > 873) {
            p2.setMoveRight(false);
            p2.getPlayer().setTranslateX(873);
        }

    }

    private void goalAnimation(){
        goal.setOpacity(1);
        ScaleTransition scaleTransition=new ScaleTransition();
        scaleTransition.setAutoReverse(true);
        scaleTransition.setNode(goal);
        scaleTransition.setByY(0.5);
        scaleTransition.setByX(0.5);
        scaleTransition.setDuration(Duration.seconds(0.5));
        scaleTransition.setCycleCount(2);
        scaleTransition.play();
        Timeline t= new Timeline(new KeyFrame(Duration.seconds(1),e->goal.setOpacity(0)));
        t.play();
    }

    private void updateBalls() {
        time1 += 0.0052;


        if (balls.isEmpty() && time1 < 8) time1 = 8;
        if (time1 > 9) {
            Ball ball = new Ball();
            balls.add(ball);
            root.getChildren().add(ball.getBall());
            time1 = 0;
        }


        if (!balls.isEmpty()) {
            for (Ball ball : balls) {
                ball.ballMovement();
                if (ball.getBall().getTranslateY() < 400 && ball.getBall().getTranslateX() > 40){
                    ball.collision(p1, p2);
                }
                else if (ball.isAlive()) {
                    if (ball.getBall().getTranslateX() > 460 /*&& ball.getBall().getTranslateX() < 1000*/) {
                        p1.goal();
                        goalAnimation();

                    } else //if (ball.getBall().getTranslateX() < 1000)
                         {
                        p2.goal();
                        goalAnimation();


                    }
                    root.getChildren().remove(ball.getBall());
                    ball.setAlive(false);

                }
                score.setText(p1.getScore() + " - " + p2.getScore());

            }
        }
        balls.removeIf(Ball::isDead);

    }


    private void keysCheckMulti(Scene scene) {
        if (win("multi")) scene.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case ESCAPE:
                    finalMessage.mp.pause();
                    FrontPage f = new FrontPage();
                    Media bgsong = new Media(getClass().getResource("/Resources/Audios/UEFAChampionsLeague201819IntroHD-Arabsong.mp3").toString());
                    Main.bgsongplayer = new MediaPlayer(bgsong);
                    Main.bgsongplayer.setAutoPlay(true);
                    Main.bgsongplayer.setCycleCount(100);
                    Main.bgsongplayer.setVolume(0.5);

                    Scene scene1 = new Scene(f, 1000, 600);

                    FrontPage.Window.setScene(scene1);
                    break;
        }});
        else {
            scene.setOnKeyPressed(e -> {
                switch (e.getCode()) {
                    case ESCAPE:
                        pause=!pause;
                        break;
                    case A:
                        p1.setMoveLeft(true);
                        break;
                    case D:
                        p1.setMoveRight(true);
                        break;
                    case LEFT:
                        p2.setMoveLeft(true);
                        break;
                    case RIGHT:
                        p2.setMoveRight(true);
                        break;
                    default:
                        break;
                }
            });
        }

        scene.setOnKeyReleased(e -> {
            switch (e.getCode()) {

                case A:
                    p1.setMoveLeft(false);
                    break;
                case D:
                    p1.setMoveRight(false);
                    break;
                case LEFT:
                    p2.setMoveLeft(false);
                    break;
                case RIGHT:
                    p2.setMoveRight(false);
                    break;
                default:
                    break;
            }
        });
    }


    private void keysCheckSingle(Scene scene) {
        if (win("single")) scene.setOnKeyPressed(e -> { switch (e.getCode()) {
            case ESCAPE:
                finalMessage.mp.pause();
                FrontPage f = new FrontPage();
                Media bgsong = new Media(getClass().getResource("/Resources/Audios/UEFAChampionsLeague201819IntroHD-Arabsong.mp3").toString());
                Main.bgsongplayer = new MediaPlayer(bgsong);
                Main.bgsongplayer.setAutoPlay(true);
                Main.bgsongplayer.setCycleCount(100);
                Main.bgsongplayer.setVolume(0.5);

                Scene scene1 = new Scene(f, 1000, 600);

                FrontPage.Window.setScene(scene1);
                break;

        }});
        else {
            scene.setOnKeyPressed(e -> {
                switch (e.getCode()) {
                    case ESCAPE:
                        pause=!pause;
                        break;
                    case A:
                        p1.setMoveLeft(true);
                        break;
                    case D:
                        p1.setMoveRight(true);
                        break;
                    default:
                        break;
                }
            });
        }

        scene.setOnKeyReleased(e -> {
            switch (e.getCode()) {

                case A:
                    p1.setMoveLeft(false);
                    break;
                case D:
                    p1.setMoveRight(false);
                    break;
                default:
                    break;
            }
        });
    }


    private void pauseMenu(Boolean pause){

        if (pause) {
            pausePane.setDisable(false);
            pausePane.setOpacity(1);

        }
        else{
            pausePane.setDisable(true);
            pausePane.setOpacity(0);
        }
    }

}
