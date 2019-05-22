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

class thirdscene {

    public static Scene thirdpage() {
        final MediaPlayer[] cheerplayer = new MediaPlayer[1];
        final MediaPlayer[] cheerplayer1 = new MediaPlayer[1];
        final MediaPlayer[] cheerplayer2 = new MediaPlayer[1];
        BorderPane p = new BorderPane();
        Image bg = new Image(SecondScene.class.getResource("/Resources/Background/bg.jpg").toString());
        ImageView bgv = new ImageView(bg);
        p.getChildren().add(bgv);

        Image border = new Image(SecondScene.class.getResource("/Resources/Texts/18.png").toString());

        ImageView borderview = new ImageView(border);
        p.setTop(borderview);

        GridPane gbb1 = new GridPane();
        gbb1.setVgap(20);
        gbb1.setPadding(new Insets(80, 0, 0, 400));

        Image easybutton = new Image(thirdscene.class.getResource("/Resources/Buttons/easy.png").toString());
        Image clickedeasybutton = new Image(thirdscene.class.getResource("/Resources/Buttons/clickedeasy.png").toString());
        ImageView easybuttonview = new ImageView(easybutton);
        GridPane.setRowIndex(easybuttonview, 0);
        gbb1.getChildren().add(easybuttonview);


        Image ballphoto = new Image(thirdscene.class.getResource("/Resources/Ball/ballNew.png").toString());
        ImageView ballphotoview = new ImageView(ballphoto);
        ballphotoview.setFitWidth(40);
        ballphotoview.setFitHeight(40);
        Duration d = Duration.seconds(0.25);
        TranslateTransition t = new TranslateTransition(d, ballphotoview);
        t.setByY(-15);
        t.setAutoReverse(true);
        t.setCycleCount(PathTransition.INDEFINITE);

        Media ballsound = new Media(thirdscene.class.getResource("/Resources/Audios/caughtball.mp3").toString());
        MediaPlayer ballsoundplayer = new MediaPlayer(ballsound);
        ballsoundplayer.setCycleCount(100);

        ballsoundplayer.setVolume(1);

        easybuttonview.setOnMouseEntered(
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e1) {
                        ballsoundplayer.play();
                        easybuttonview.setCursor(Cursor.HAND);

                        ballphotoview.setVisible(true);

                        t.play();
                        GridPane.setColumnIndex(ballphotoview, 1);
                        GridPane.setRowIndex(ballphotoview, 0);
                        gbb1.getChildren().add(ballphotoview);
                    }
                }
        );
        easybuttonview.setOnMouseExited(
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e1) {
                        ballsoundplayer.pause();
                        gbb1.getChildren().remove(ballphotoview);
                        t.pause();

                    }
                }
        );

        easybuttonview.setOnMouseClicked(
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e1) {
                        ImageView clickedeasybuttonview = new ImageView(clickedeasybutton);
                        GridPane.setRowIndex(clickedeasybuttonview, 0);
                        GridPane.setColumnIndex(clickedeasybuttonview, 0);
                        gbb1.getChildren().add(clickedeasybuttonview);
                        Media m1 = new Media(thirdscene.class.getResource("/Resources/Audios/Ball+Hit+Cheer.mp3").toString());
                        cheerplayer[0] = new MediaPlayer(m1);
                        cheerplayer[0].setAutoPlay(true);
                        cheerplayer[0].setVolume(1);
                        ballsoundplayer.pause();
                        clickedeasybuttonview.setCursor(Cursor.NONE);
                        Timeline t = new Timeline(new KeyFrame(Duration.millis(1300),
                                e
                                        -> {

                                    Main.bgsongplayer.pause();
                                    Scene sc = null;
                                    try {
                                        sc = new StartGame().singlePlayer("EASY");
                                    } catch (Exception ex) {
                                        ex.printStackTrace();
                                    }
                                    Window.setScene(sc);

                                }));

                        t.play();


                    }

                }
        );


        Image mediumbutton = new Image(thirdscene.class.getResource("/Resources/Buttons/medium.png").toString());
        Image clickedmediumbutton = new Image(thirdscene.class.getResource("/Resources/Buttons/clickedmedium.png").toString());
        ImageView mediumbuttonview = new ImageView(mediumbutton);
        GridPane.setRowIndex(mediumbuttonview, 1);
        Duration d2 = Duration.seconds(0.25);

        gbb1.getChildren().add(mediumbuttonview);

        mediumbuttonview.setOnMouseEntered(
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e1) {
                        ballsoundplayer.play();
                        mediumbuttonview.setCursor(Cursor.HAND);

                        ballphotoview.setVisible(true);
                        t.play();
                        GridPane.setColumnIndex(ballphotoview, 1);
                        GridPane.setRowIndex(ballphotoview, 1);
                        gbb1.getChildren().add(ballphotoview);
                    }
                }
        );
        mediumbuttonview.setOnMouseExited(
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e1) {
                        ballsoundplayer.pause();
                        gbb1.getChildren().remove(ballphotoview);
                        t.pause();

                    }
                }
        );

        mediumbuttonview.setOnMouseClicked(
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e1) {
                        ImageView clickedmediumbuttonview = new ImageView(clickedmediumbutton);
                        GridPane.setRowIndex(clickedmediumbuttonview, 1);
                        GridPane.setColumnIndex(clickedmediumbuttonview, 0);
                        gbb1.getChildren().add(clickedmediumbuttonview);
                        Media m1 = new Media(thirdscene.class.getResource("/Resources/Audios/Ball+Hit+Cheer.mp3").toString());
                        cheerplayer1[0] = new MediaPlayer(m1);
                        cheerplayer1[0].setAutoPlay(true);
                        cheerplayer1[0].setVolume(1);
                        ballsoundplayer.pause();
                        clickedmediumbuttonview.setCursor(Cursor.NONE);
                        Timeline t = new Timeline(new KeyFrame(Duration.millis(1300),
                                e
                                        -> {
                                    Main.bgsongplayer.pause();
                                    Scene sc = null;
                                    try {
                                        sc = new StartGame().singlePlayer("MID");
                                    } catch (Exception ex) {
                                        ex.printStackTrace();
                                    }
                                    Window.setScene(sc);

                                }));

                        t.play();
                    }

                }
        );

        Image hardbutton = new Image(thirdscene.class.getResource("/Resources/Buttons/hard.png").toString());
        Image clickedhardbutton = new Image(thirdscene.class.getResource("/Resources/Buttons/clickedhard.png").toString());
        ImageView hardbuttonview = new ImageView(hardbutton);
        GridPane.setRowIndex(hardbuttonview, 2);
        gbb1.getChildren().add(hardbuttonview);

        hardbuttonview.setOnMouseEntered(
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e1) {
                        ballsoundplayer.play();
                        hardbuttonview.setCursor(Cursor.HAND);

                        ballphotoview.setVisible(true);
                        t.play();
                        GridPane.setColumnIndex(ballphotoview, 1);
                        GridPane.setRowIndex(ballphotoview, 2);
                        gbb1.getChildren().add(ballphotoview);

                    }
                }
        );
        hardbuttonview.setOnMouseExited(
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e1) {
                        ballsoundplayer.pause();
                        gbb1.getChildren().remove(ballphotoview);
                        t.pause();

                    }
                }
        );

        hardbuttonview.setOnMouseClicked(
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e1) {
                        ImageView m15 = new ImageView(clickedhardbutton);
                        GridPane.setRowIndex(m15, 2);
                        GridPane.setColumnIndex(m15, 0);
                        gbb1.getChildren().add(m15);
                        Media m1 = new Media(thirdscene.class.getResource("/Resources/Audios/Ball+Hit+Cheer.mp3").toString());
                        cheerplayer1[0] = new MediaPlayer(m1);
                        cheerplayer1[0].setAutoPlay(true);
                        cheerplayer1[0].setVolume(1);
                        ballsoundplayer.pause();
                        m15.setCursor(Cursor.NONE);
                        Timeline t = new Timeline(new KeyFrame(Duration.millis(1300),
                                e
                                        -> {
                                    Main.bgsongplayer.pause();
                                    Scene sc = null;
                                    try {
                                        sc = new StartGame().singlePlayer("HARD");
                                    } catch (Exception ex) {
                                        ex.printStackTrace();
                                    }
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
                        Scene sc = SecondScene.SecondPage();
                        Window.setScene(sc);

                    }

                }
        );
        p.setBottom(gp);

        Scene sc2 = new Scene(p, 1000, 600);

        return sc2;
    }

}