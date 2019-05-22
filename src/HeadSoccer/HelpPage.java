package HeadSoccer;

import javafx.animation.PathTransition;
import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;

class HelpPage {

     public static Scene helpscene()
     {
         BorderPane p = new BorderPane();
         Image bg = new Image(HelpPage.class.getResource("/Resources/Background/bg.jpg").toString());
         ImageView bgv = new ImageView(bg);
         p.getChildren().add(bgv);

         Image helpphoto = new Image(HelpPage.class.getResource("/Resources/Scenes/helpscene.png").toString());
         ImageView helpphotoview = new ImageView(helpphoto);
         p.getChildren().add(helpphotoview);

         GridPane gp = new GridPane();
         gp.setPadding(new Insets(0, 0, 20, 20));
         Image backbutton = new Image(SecondScene.class.getResource("/Resources/Buttons/back.png").toString());
         ImageView backbuttonview = new ImageView(backbutton);
         gp.getChildren().add(backbuttonview);

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
         Scene sc2 = new Scene(p, 1000, 600);
         return sc2;
     }
}
