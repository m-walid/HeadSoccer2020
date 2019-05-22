package HeadSoccer;

import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class FinalMessage extends StackPane {
    MediaPlayer mp ;
    Image im;
    public FinalMessage(String string)
    {
       // Image im2 = new Image (getClass().getResource("/Resources/Texts/YouWin.png").toString());
       // ImageView imv2 = new ImageView(im2);
        Media mf = new Media(getClass().getResource("/Resources/Audios/Fireworkk.mp3").toString());
        mp = new MediaPlayer(mf);
        mp.setAutoPlay(true);
        mp.setCycleCount(1);
        Image im1 = new Image (getClass().getResource("/Resources/GIFs/Fireworks.gif").toString());
        ImageView imv1 = new ImageView(im1);
        switch (string){
            case "P1":
                 im = new Image(getClass().getResource("/Resources/Texts/Player1Win.png").toString());
                break;
            case "P2":
                 im = new Image(getClass().getResource("/Resources/Texts/Player2Win.png").toString());
                break;
            case "P1S":
                 im = new Image(getClass().getResource("/Resources/Texts/YouWin.png").toString());
                break;
            case "COM":
                 im = new Image(getClass().getResource("/Resources/Texts/YouLose.png").toString());
                 break;
        }

        ImageView imv = new ImageView(im);
        imv.setX(5);
        imv.setY(5);
        Duration dx = Duration.seconds(1.5);
        ScaleTransition sct = new ScaleTransition(dx, imv);
        sct.setByX(1);
        sct.setByY(1);
       sct.setAutoReverse(true);

        sct.setCycleCount(4);
        sct.play();

        this.getChildren().addAll(imv1,imv);
        //this.setPadding(new Insets(0,0,0,100));
    }
}
