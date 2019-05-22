package HeadSoccer;

import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Window;
import javafx.util.Duration;

class FrontPage extends BorderPane {

    public static Stage Window;
    MediaPlayer cheerplayer;


    public FrontPage() {

        Image bg = new Image(getClass().getResource("/Resources/Background/bg.jpg").toString());
        ImageView bgv = new ImageView(bg);
//
        this.getChildren().add(bgv);


        Image headsoccertitle = new Image(getClass().getResource("/Resources/Background/name.png").toString());
        ImageView headsoccertitleview = new ImageView(headsoccertitle);
        headsoccertitleview.setX(200);
        headsoccertitleview.setY(20);
        Duration dx = Duration.seconds(2.5);
        ScaleTransition scaleTransition = new ScaleTransition(dx, headsoccertitleview);
        scaleTransition.setByX(0.5);
        scaleTransition.setByY(0.5);

        ScaleTransition scaleTransition1 = new ScaleTransition(dx, headsoccertitleview);
        scaleTransition1.setByX(-0.5);
        scaleTransition1.setByY(-0.5);

        SequentialTransition sequentialTransition = new SequentialTransition(scaleTransition, scaleTransition1);
        sequentialTransition.setCycleCount(100);
        sequentialTransition.play();

        this.getChildren().add(headsoccertitleview);

        GridPane V = new GridPane();
        V.setVgap(20);
        V.setPadding(new Insets(300, 0, 0, 400));

        Image startbutton = new Image(getClass().getResource("/Resources/Buttons/start.png").toString());
        Image clickedstartbutton = new Image(getClass().getResource("/Resources/Buttons/clicked_start.png").toString());
        ImageView startbuttonview = new ImageView(startbutton);
        GridPane.setColumnIndex(startbuttonview, 0);

        V.getChildren().add(startbuttonview);

        Image ballphoto = new Image(getClass().getResource("/Resources/Ball/ballNew.png").toString());
        ImageView ballphotov = new ImageView(ballphoto);
        ballphotov.setFitWidth(40);
        ballphotov.setFitHeight(40);
        Duration d = Duration.seconds(0.25);
        TranslateTransition t = new TranslateTransition(d, ballphotov);
        t.setByY(-15);
        t.setAutoReverse(true);
        t.setCycleCount(PathTransition.INDEFINITE);

        Media ballsound = new Media(getClass().getResource("/Resources/Audios/caughtball.mp3").toString());
        MediaPlayer ballsoundplayer = new MediaPlayer(ballsound);
        ballsoundplayer.setCycleCount(100);
        ballsoundplayer.setVolume(1);

        Media cheer = new Media(getClass().getResource("/Resources/Audios/Ball+Hit+Cheer.mp3").toString());
        cheerplayer = new MediaPlayer(cheer);
//        cheerplayer.setAutoPlay(true);
        cheerplayer.setVolume(1);

        startbuttonview.setOnMouseEntered(
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e1) {

                        startbuttonview.setCursor(Cursor.HAND);

                        ballsoundplayer.play();
                        ballphotov.setVisible(true);

                        t.play();

                        GridPane.setColumnIndex(ballphotov, 1);
                        GridPane.setRowIndex(ballphotov, 0);
                        V.getChildren().add(ballphotov);

                    }
                }
        );
        startbuttonview.setOnMouseExited(
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e1) {

                        ballsoundplayer.pause();

                        ballphotov.setVisible(false);
                        V.getChildren().remove(ballphotov);
                        t.pause();

                    }
                }
        );

        startbuttonview.setOnMouseClicked(
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e1) {
                        ImageView clickedstartbuttonview = new ImageView(clickedstartbutton);
                        GridPane.setColumnIndex(clickedstartbuttonview, 0);
                        V.getChildren().add(clickedstartbuttonview);
                        ballsoundplayer.pause();
                        cheerplayer.play();
                        clickedstartbuttonview.setCursor(Cursor.NONE);

                        Timeline t = new Timeline(new KeyFrame(Duration.millis(1300),
                                e
                                        -> {
                                    Scene sc = SecondScene.SecondPage();
                                    Window.setScene(sc);
                                }));

                        t.play();

                    }
                }
        );

        Image creditsbutton = new Image(getClass().getResource("/Resources/Buttons/credits.png").toString());
        Image clickedcreditsbutton = new Image(getClass().getResource("/Resources/Buttons/clicked_credits.png").toString());
        ImageView creditsbuttonview = new ImageView(creditsbutton);

        GridPane.setRowIndex(creditsbuttonview, 1);
        V.getChildren().add(creditsbuttonview);


        creditsbuttonview.setOnMouseEntered(
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e1) {
                        ballsoundplayer.play();
                        creditsbuttonview.setCursor(Cursor.HAND);

                        ballphotov.setVisible(true);
                        t.play();
                        GridPane.setColumnIndex(ballphotov, 1);
                        GridPane.setRowIndex(ballphotov, 1);
                        V.getChildren().add(ballphotov);
                    }
                }
        );
        creditsbuttonview.setOnMouseExited(
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e1) {
                        ballsoundplayer.pause();
                        V.getChildren().remove(ballphotov);
                        t.pause();

                    }
                }
        );

        creditsbuttonview.setOnMouseClicked(
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e1) {
                        ImageView clickedcreditsbuttonview = new ImageView(clickedcreditsbutton);
                        GridPane.setRowIndex(clickedcreditsbuttonview, 1);
                        GridPane.setColumnIndex(clickedcreditsbuttonview, 0);
                        V.getChildren().add(clickedcreditsbuttonview);
                        cheerplayer.play();
                        ballsoundplayer.pause();
                        clickedcreditsbuttonview.setCursor(Cursor.NONE);
                        Timeline t = new Timeline(new KeyFrame(Duration.millis(1300),
                                e
                                        -> {
                                    Scene sc = CreditsPage.creditsscene();
                                    Window.setScene(sc);
                                }));

                        t.play();
                    }

                }
        );

        Image exitbutton = new Image(getClass().getResource("/Resources/Buttons/exit.png").toString());
        Image clickedexitbutton = new Image(getClass().getResource("/Resources/Buttons/clicked_exit1.png").toString());
        ImageView exitbuttonview = new ImageView(exitbutton);
        GridPane.setRowIndex(exitbuttonview, 2);
        V.getChildren().add(exitbuttonview);
        exitbuttonview.setOnMouseEntered(
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e1) {
                        ballsoundplayer.play();
                        exitbuttonview.setCursor(Cursor.HAND);

                        ballphotov.setVisible(true);
                        t.play();
                        GridPane.setColumnIndex(ballphotov, 1);
                        GridPane.setRowIndex(ballphotov, 2);
                        V.getChildren().add(ballphotov);
                    }
                }
        );
        exitbuttonview.setOnMouseExited(
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e1) {
                        ballsoundplayer.pause();
                        V.getChildren().remove(ballphotov);
                        t.pause();

                    }
                }
        );

        exitbuttonview.setOnMouseClicked(
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e1) {
                        ImageView clickedexitbuttonview = new ImageView(clickedexitbutton);
                        GridPane.setRowIndex(clickedexitbuttonview, 2);
                        GridPane.setColumnIndex(clickedexitbuttonview, 0);
                        V.getChildren().add(clickedexitbuttonview);
                        cheerplayer.play();
                        clickedexitbuttonview.setCursor(Cursor.NONE);
                        Window.close();

                    }

                }
        );

        this.setCenter(V);

        GridPane gp = new GridPane();

        gp.setPadding(new Insets(0, 0, 100, 20));

        Image helpbutton = new Image(getClass().getResource("/Resources/Buttons/help_button.png").toString());
        Image clickedhelpbutton = new Image(getClass().getResource("/Resources/Buttons/clicked_help_button1.png").toString());
        ImageView helpbuttonview = new ImageView(helpbutton);
        gp.getChildren().add(helpbuttonview);

        helpbuttonview.setOnMouseEntered(
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e1) {
                        ballsoundplayer.play();
                        helpbuttonview.setCursor(Cursor.HAND);

                        ballphotov.setVisible(true);
                        t.play();
                        GridPane.setColumnIndex(ballphotov, 1);
                        GridPane.setRowIndex(ballphotov, 0);
                        gp.getChildren().add(ballphotov);

                    }
                }
        );
        helpbuttonview.setOnMouseExited(
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e1) {
                        ballsoundplayer.pause();
                        gp.getChildren().remove(ballphotov);
                        t.pause();

                    }
                }
        );

        helpbuttonview.setOnMouseClicked(
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e1) {
                        ImageView clickedhelpbuttonview = new ImageView(clickedhelpbutton);
                        GridPane.setRowIndex(clickedhelpbuttonview, 0);
                        GridPane.setColumnIndex(clickedhelpbuttonview, 0);
                        gp.getChildren().add(clickedhelpbuttonview);
                        cheerplayer.play();
                        ballsoundplayer.pause();
                        clickedhelpbuttonview.setCursor(Cursor.NONE);

                        Timeline t = new Timeline(new KeyFrame(Duration.millis(1300),
                                e
                                        -> {
                                    Scene sc = HelpPage.helpscene();
                                    Window.setScene(sc);
                                }));

                        t.play();

                    }

                }
        );
        this.setBottom(gp);

    }

}

public class Main extends Application {

    public static MediaPlayer bgsongplayer;

    @Override
    public void start(Stage primaryStage) {

        FrontPage.Window = primaryStage;
        FrontPage f = new FrontPage();
        primaryStage.setResizable(false);
        primaryStage.sizeToScene();
        Media bgsong = new Media(getClass().getResource("/Resources/Audios/UEFAChampionsLeague201819IntroHD-Arabsong.mp3").toString());
        bgsongplayer = new MediaPlayer(bgsong);
        bgsongplayer.setAutoPlay(true);
        bgsongplayer.setCycleCount(100);
        bgsongplayer.setVolume(0.5);

        Scene scene1 = new Scene(f, 1000, 600);

        primaryStage.setTitle("Head Soccer");
        primaryStage.setScene(scene1);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}


