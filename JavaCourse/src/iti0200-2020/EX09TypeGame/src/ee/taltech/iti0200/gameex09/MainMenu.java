import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.stage.Stage;

import java.util.LinkedList;
import java.util.List;

public class MainMenu {

    public static final int GAMEWIDTH = 960;
    public static final int GAMEHEIGHT = 640;
    public static final int TIMELENGTH = 30;
    public static final int FONTSIZE16 = 16;
    public static final int FONTSIZE20 = 20;
    public static final int SCORECOLORRED = 88;
    public static final int SCORECOLORGREEN = 119;
    public static final int SCORECOLORBLUE = 196;
    public static final int LABELCOLORRED = 133;
    public static final int LABELCOLORGREEN = 37;
    public static final int LABELCOLORBLUE = 141;
    public static final int TEXTFIELDSIZE = 12;
    public static final double GLOW = 0.7;
    public static final int STARTBUTTONCOLORRED = 2;
    public static final int STARTBUTTONCOLORGREEN = 170;
    public static final int STARTBUTTONCOLORBLUE = 85;
    public static final int EXITBUTTONCOLORRED = 204;
    public static final int SCOREHEADINGBUTTONCOLORRED = 206;
    public static final int SCOREHEADINGCOLORGREEN = 10;
    public static final int SCOREHEADINGCOLORBLUE = 13;

    String scoreInt = "0";
    int time = TIMELENGTH;
    int lettersChangeTime = 5;
    Stage stage;
    boolean scoretable = false;
    CharSequence name;
    List<Label> scores = new LinkedList<>();

    Background background;
    Image imageMenuBackground = new Image("gamefiles/startLogo.png",
            GAMEWIDTH, GAMEHEIGHT, false, false);

    public MainMenu(Stage stage) {
        this.stage = stage;
        start(stage);
    }

    public MainMenu(Stage stage, CharSequence name, String scoreInt, List<Label> scores) {
        this.scores = scores;
        this.stage = stage;
        Label score = new Label(name + " - " + scoreInt);
        score.setFont(Font.font("Verdana", FontPosture.REGULAR, FONTSIZE16));
        score.setTextFill(Color.rgb(SCORECOLORRED, SCORECOLORGREEN, SCORECOLORBLUE));
        scores.add(0, score);
        this.scoreInt = scoreInt;
        scoretable = true;
        this.name = name;
        start(stage);
    }

    public void start(Stage stage) {
        BackgroundImage backgroundimage = new BackgroundImage(imageMenuBackground,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);

        Label label = new Label("Enter your name: ");
        label.setFont(Font.font("Verdana", FontPosture.ITALIC, FONTSIZE20));
        label.setTextFill(Color.rgb(LABELCOLORRED, LABELCOLORGREEN, LABELCOLORBLUE));

        TextField textfield = new TextField();
        textfield.setPrefColumnCount(TEXTFIELDSIZE);
        textfield.setFont(new Font("Verdana", FONTSIZE20));
        Glow glow = new Glow();
        glow.setLevel(GLOW);
        textfield.setEffect(glow);
        textfield.setStyle("-fx-background-color: #86e0eb");

        Button button = new Button("Start");
        button.setTextFill(Color.rgb(STARTBUTTONCOLORRED, STARTBUTTONCOLORGREEN, STARTBUTTONCOLORBLUE));
        button.setStyle("-fx-font: 20 verdana; -fx-base: #21d8af;");

        Button buttonExit = new Button("Exit");
        buttonExit.setTextFill(Color.rgb(EXITBUTTONCOLORRED, 0, 0));
        buttonExit.setStyle("-fx-font: 20 verdana; -fx-base: #F55E47;");

        HBox hbox = new HBox(label, textfield, button, buttonExit);
        hbox.setSpacing(10);
        hbox.setAlignment(Pos.CENTER);
        background = new Background(backgroundimage);

        Label startEvent = new Label();
        EventHandler<ActionEvent> eventToStart = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                if (!textfield.getCharacters().toString().isBlank()) {
                    new Game(stage, textfield.getCharacters(), time, lettersChangeTime, scores);
                } else if (scoretable) {
                    new Game(stage, name, time, lettersChangeTime, scores);
                }
            }
        };

        Label exitEvent = new Label();
        EventHandler<ActionEvent> eventToExit = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                stage.close();
            }
        };

        buttonExit.setOnAction(eventToExit);
        textfield.setOnAction(eventToStart);
        button.setOnAction(eventToStart);
        hbox.getChildren().add(exitEvent);
        hbox.getChildren().add(startEvent);
        BorderPane mainMenuPane = new BorderPane();
        mainMenuPane.setCenter(hbox);
        mainMenuPane.setBackground(background);

        if (scoretable) {
            VBox scoreTable = new VBox();
            Label heading = new Label("SCORE TABLE ");
            heading.setFont(Font.font("Verdana", FONTSIZE20));
            heading.setTextFill(Color.rgb(SCOREHEADINGBUTTONCOLORRED, SCOREHEADINGCOLORGREEN, SCOREHEADINGCOLORBLUE));
            scoreTable.getChildren().add(heading);
            int five = 1;
            for (Label score : scores) {
                scoreTable.getChildren().add(score);
                if (five == 5) {
                    break;
                }
                five++;
            }
            mainMenuPane.setRight(scoreTable);
        }

        Scene scene = new Scene(mainMenuPane, GAMEWIDTH, GAMEHEIGHT);
        stage.setScene(scene);
        stage.show();
    }
}
