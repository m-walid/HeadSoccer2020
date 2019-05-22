package HeadSoccer;

import static HeadSoccer.FrontPage.Window;

import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

class SecondScene {


    public static Scene SecondPage() {
        final MediaPlayer[] cheerplayer = new MediaPlayer[1];
        final MediaPlayer[] cheerplayer1 = new MediaPlayer[1];

        BorderPane p = new BorderPane();
        Image bg = new Image(SecondScene.class.getResource("/Resources/Background/bg.jpg").toString());
        ImageView bgview = new ImageView(bg);
        p.getChildren().add(bgview);


        Image border = new Image(SecondScene.class.getResource("/Resources/Texts/19.png").toString());

        ImageView borderview = new ImageView(border);

        p.setTop(borderview);

        GridPane gbb1 = new GridPane();
        gbb1.setVgap(20);
        gbb1.setPadding(new Insets(100, 0, 0, 400));

        Image singleplayerbutton = new Image(SecondScene.class.getResource("/Resources/Buttons/singleplayer.png").toString());
        Image clickedsingleplayerbutton = new Image(SecondScene.class.getResource("/Resources/Buttons/clickedsingleplayer.png").toString());
        ImageView singleplayerbuttonview = new ImageView(singleplayerbutton);
        GridPane.setRowIndex(singleplayerbuttonview, 0);
        gbb1.getChildren().add(singleplayerbuttonview);


        Image ballphoto = new Image(SecondScene.class.getResource("/Resources/Ball/ballNew.png").toString());
        ImageView ballphotoview = new ImageView(ballphoto);
        ballphotoview.setFitWidth(40);
        ballphotoview.setFitHeight(40);

        Duration d = Duration.seconds(0.25);
        TranslateTransition t = new TranslateTransition(d, ballphotoview);
        t.setByY(-15);
        t.setAutoReverse(true);
        t.setCycleCount(PathTransition.INDEFINITE);

        Media ballsound = new Media(SecondScene.class.getResource("/Resources/Audios/caughtball.mp3").toString());
        MediaPlayer ballsoundplayer = new MediaPlayer(ballsound);
        ballsoundplayer.setCycleCount(100);

        ballsoundplayer.setVolume(1);

        singleplayerbuttonview.setOnMouseEntered(
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e1) {
                        ballsoundplayer.play();
                        singleplayerbuttonview.setCursor(Cursor.HAND);

                        ballphotoview.setVisible(true);
                        t.play();
                        GridPane.setColumnIndex(ballphotoview, 1);
                        GridPane.setRowIndex(ballphotoview, 0);
                        gbb1.getChildren().add(ballphotoview);
                    }
                }
        );
        singleplayerbuttonview.setOnMouseExited(
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e1) {
                        ballsoundplayer.pause();
                        gbb1.getChildren().remove(ballphotoview);
                        t.pause();

                    }
                }
        );

        singleplayerbuttonview.setOnMouseClicked(
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e1) {
                        ImageView m13 = new ImageView(clickedsingleplayerbutton);
                        GridPane.setRowIndex(m13, 0);
                        GridPane.setColumnIndex(m13, 0);
                        gbb1.getChildren().add(m13);
                        Media m1 = new Media(SecondScene.class.getResource("/Resources/Audios/Ball+Hit+Cheer.mp3").toString());
                        cheerplayer[0] = new MediaPlayer(m1);
                        cheerplayer[0].setAutoPlay(true);
                        cheerplayer[0].setVolume(1);
                        cheerplayer[0].play();
                        ballsoundplayer.pause();
                        m13.setCursor(Cursor.NONE);
                        Timeline t = new Timeline(new KeyFrame(Duration.millis(1300),
                                e
                                        -> {
                                    Scene sc = thirdscene.thirdpage();
                                    Window.setScene(sc);

                                }));

                        t.play();
                    }

                }
        );


        Image multiplayerbutton = new Image(SecondScene.class.getResource("/Resources/Buttons/multiplayer.png").toString());
        Image clickedmultiplayerbutton = new Image(SecondScene.class.getResource("/Resources/Buttons/clickedmultiplayer2.png").toString());
        ImageView multiplayerbuttonview = new ImageView(multiplayerbutton);
        GridPane.setRowIndex(multiplayerbuttonview, 1);
        gbb1.getChildren().add(multiplayerbuttonview);

        multiplayerbuttonview.setOnMouseEntered(
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e1) {
                        ballsoundplayer.play();
                        multiplayerbuttonview.setCursor(Cursor.HAND);

                        ballphotoview.setVisible(true);

                        t.play();
                        GridPane.setColumnIndex(ballphotoview, 1);
                        GridPane.setRowIndex(ballphotoview, 1);
                        gbb1.getChildren().add(ballphotoview);
                    }
                }
        );
        multiplayerbuttonview.setOnMouseExited(
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e1) {
                        ballsoundplayer.pause();
                        gbb1.getChildren().remove(ballphotoview);
                        t.pause();

                    }
                }
        );

        multiplayerbuttonview.setOnMouseClicked(
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e1) {
                        ImageView m14 = new ImageView(clickedmultiplayerbutton);
                        GridPane.setRowIndex(m14, 1);
                        GridPane.setColumnIndex(m14, 0);
                        gbb1.getChildren().add(m14);
                        Media m1 = new Media(SecondScene.class.getResource("/Resources/Audios/Ball+Hit+Cheer.mp3").toString());
                        cheerplayer1[0] = new MediaPlayer(m1);
                        cheerplayer1[0].setAutoPlay(true);
                        cheerplayer1[0].setVolume(1);
                        cheerplayer1[0].play();
                        ballsoundplayer.pause();
                        m14.setCursor(Cursor.NONE);
                        Timeline t = new Timeline(new KeyFrame(Duration.millis(1300),
                                e
                                        -> {
                                    Main.bgsongplayer.pause();
                                    Scene sc = new StartGame().multiPlayer();
                                    Window.setScene(sc);

                                }));

                        t.play();


                    }

                }
        );

        p.setCenter(gbb1);

        GridPane gp = new GridPane();
        gp.setPadding(new Insets(0, 0, 20, 20));
        Image backbutton = new Image(SecondScene.class.getResource("/Resources/Buttons/back.png").toString());
        ImageView backbuttonview = new ImageView(backbutton);
        gp.getChildren().add(backbuttonview);

        backbuttonview.setOnMouseEntered(
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e1) {
                        ballsoundplayer.play();
                        backbuttonview.setCursor(Cursor.HAND);

                        ballphotoview.setVisible(true);
                        t.play();
                        GridPane.setColumnIndex(ballphotoview, 1);
                        GridPane.setRowIndex(ballphotoview, 0);
                        gp.getChildren().add(ballphotoview);


                    }
                }
        );
        backbuttonview.setOnMouseExited(
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e1) {
                        ballsoundplayer.pause();
                        gp.getChildren().remove(ballphotoview);
                        t.pause();

                    }
                }
        );

        backbuttonview.setOnMouseClicked(
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e1) {
//
                        FrontPage f = new FrontPage();
//

                        Scene scene1 = new Scene(f, 1000, 600);
                        FrontPage.Window.setScene(scene1);

                    }

                }
        );
        p.setBottom(gp);




        Scene sc1 = new Scene(p, 1000, 600);

        return sc1;
    }
}