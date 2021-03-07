import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundSize;

import java.util.List;
import java.util.Set;
import java.util.Arrays;
import java.util.Random;
import java.util.HashSet;
import java.util.ArrayList;

public class Game {

    public static final double PERCENT4 = 0.04;
    public static final double PERCENT17 = 0.172;
    public static final int FONTSIZE22 = 22;
    public static final int FONTSIZE24 = 24;
    public static final int LETTEFSIZE = 40;
    public static final int SCORECOLORRED = 62;
    public static final int SCORECOLORGREEN = 83;
    public static final int SCORECOLORBLUE = 206;
    public static final int TIMELEFTCOLORRED = 41;
    public static final int TIMELEFTCOLORGREEN = 127;
    public static final int TIMELEFTCOLORBLUE = 206;
    public static final int LETTER1RED = 43;
    public static final int LETTER1GREEN = 177;
    public static final int LETTER1BLUE = 106;
    public static final int LETTER2RED = 225;
    public static final int LETTER2GREEN = 161;
    public static final int LETTER2BLUE = 8;
    public static final int LETTER3RED = 193;
    public static final int LETTER3GREEN = 157;
    public static final int LETTER3BLUE = 255;
    public static final double BUTTONANIMATIONLENGTH = 0.3;
    public static final double BUTTONANIMATIONWORKTIME = 0.6;

    Stage stage;
    CharSequence name;
    String scoreInt = "0";
    int time;
    int lettersChangeTime;
    int gameWidth = MainMenu.GAMEWIDTH;
    int gameHeight = MainMenu.GAMEHEIGHT;
    List<Label> scores;

    int minXLetter1 = (int) (-(gameWidth - PERCENT4 * gameWidth) / 2);
    int maxXLetter1 = (int) ((gameWidth - PERCENT4 * gameWidth) / 2);
    int minYLetter1 = (int) (-(gameHeight - PERCENT17 * gameHeight) / 2);
    int maxYLetter1 = (int) ((gameHeight - PERCENT17 * gameHeight) / 2);
    int letter1Time = 0;
    boolean letter1Cliked = false;

    int minXLetter2 = (int) (-gameWidth + PERCENT4 * gameWidth);
    int maxXLetter2 = 0;
    int minYLetter2 = 0;
    int maxYLetter2 = (int) (gameHeight - PERCENT17 * gameHeight);
    int letter2Time = 0;
    boolean letter2Cliked = false;

    int minXLetter3 = 0;
    int maxXLetter3 = (int) ((gameWidth - PERCENT4 * gameWidth));
    int minYLetter3 = 0;
    int maxYLetter3 = (int) (gameHeight - PERCENT17 * gameHeight);
    int letter3Time = 0;
    boolean letter3Cliked = false;

    Background background;
    Image imageGameScreen = new Image("gamefiles/gameScreen.png", gameWidth,
            gameHeight, false, false);
    Random random = new Random();
    Set<String> setOfCharsToUse = new HashSet<>(Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "q",
            "w", "e", "r", "t", "y", "u", "i", "o", "p", "a", "s", "d", "f", "g", "h", "j", "k", "l", "z", "x",
            "c", "v", "b", "n", "m"));
    String chars = "0123456789qwertyuiopasdfghjklzxcvbnm";

    Label letter1 = new Label(getLetter("1")), letter2 = new Label(getLetter("2")),
            letter3 = new Label(getLetter("3"));

    public Game(Stage stage, CharSequence name, int time, int lettersChangeTime, List<Label> scores) {
        this.scores = scores;
        this.stage = stage;
        this.name = name;
        this.time = time;
        this.lettersChangeTime = lettersChangeTime;
        gameScreen();
    }

    public void gameScreen() {
        String nameScore = name + ", your score is: ";
        Label score = new Label(nameScore + scoreInt);
        score.setFont(Font.font("Verdana", FONTSIZE24));
        score.setTextFill(Color.rgb(SCORECOLORRED, SCORECOLORGREEN, SCORECOLORBLUE));
        Label gameTimeLeft = new Label("Time left: " + String.valueOf(time));
        gameTimeLeft.setFont(Font.font("Verdana", FONTSIZE22));
        gameTimeLeft.setTextFill(Color.rgb(TIMELEFTCOLORRED, TIMELEFTCOLORGREEN, TIMELEFTCOLORBLUE));

        letter1.setFont(Font.font("Verdana", LETTEFSIZE));
        letter1.setTextFill(Color.rgb(LETTER1RED, LETTER1GREEN, LETTER1BLUE));
        letter2.setFont(Font.font("Verdana", LETTEFSIZE));
        letter2.setTextFill(Color.rgb(LETTER2RED, LETTER2GREEN, LETTER2BLUE));
        letter3.setFont(Font.font("Verdana", LETTEFSIZE));
        letter3.setTextFill(Color.rgb(LETTER3RED, LETTER3GREEN, LETTER3BLUE));

        BackgroundImage backgroundimage = new BackgroundImage(imageGameScreen, // sets new background
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);
        background = new Background(backgroundimage);

        PauseTransition gameTime = new PauseTransition(Duration.seconds(time));
        gameTime.setOnFinished(event -> { // timetracker, if time ends, you are going to the menu screen
                    try {
                        new MainMenu(stage, name, scoreInt, scores);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
        );
        gameTime.play();

        PauseTransition letter1time = new PauseTransition(Duration.seconds(lettersChangeTime));
        letter1time.setOnFinished(event -> { // timetracker for letter1, if ends, then changes
                    letter1Time = 0;
                    timeEndedChange(letter1, letter1time, minXLetter1, maxXLetter1, minYLetter1, maxYLetter1);
                }
        );
        letter1time.play();

        PauseTransition letter2time = new PauseTransition(Duration.seconds(lettersChangeTime));
        letter2time.setOnFinished(event -> { // timetracker for letter2, if ends, then changes
                    letter2Time = 0;
                    timeEndedChange(letter2, letter2time, minXLetter2, maxXLetter2, minYLetter2, maxYLetter2);
                }
        );
        letter2time.play();

        PauseTransition letter3time = new PauseTransition(Duration.seconds(lettersChangeTime));
        letter3time.setOnFinished(event -> { // timetracker for letter3, if ends, then changes
                    letter3Time = 0;
                    timeEndedChange(letter3, letter3time, minXLetter3, maxXLetter3, minYLetter3, maxYLetter3);
                }
        );
        letter3time.play();

        VBox vBox = new VBox();
        vBox.getChildren().add(score);

        PauseTransition timeLeft = new PauseTransition(Duration.seconds(1));
        timeLeft.setOnFinished(event -> { // timetracker for for showing how much time left, if ends, then changes
                    letter1Time++;
                    letter2Time++;
                    letter3Time++;
                    time--;
                    gameTimeLeft.setText("Time left: " + String.valueOf(time));
                    timeLeft.setDuration(Duration.seconds(1));
                    timeLeft.play();
                }
        );
        timeLeft.play();
        vBox.getChildren().add(gameTimeLeft);

        BorderPane gameScreenPane = new BorderPane();
        BorderPane lettersPane = new BorderPane();
        changeCoordinates(letter1, minXLetter1, maxXLetter1, minYLetter1, maxYLetter1);
        changeCoordinates(letter2, minXLetter2, maxXLetter2, minYLetter2, maxYLetter2);
        changeCoordinates(letter3, minXLetter3, maxXLetter3, minYLetter3, maxYLetter3);

        lettersPane.setCenter(letter1);
        lettersPane.setRight(letter2);
        lettersPane.setLeft(letter3);

        gameScreenPane.setCenter(lettersPane);
        gameScreenPane.setTop(vBox);
        gameScreenPane.setBackground(background);
        Scene scene = new Scene(gameScreenPane, gameWidth, gameHeight);

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getText().equals(letter1.getText()) && !letter1Cliked) {
                    letter1Cliked = true;
                    List<Integer> coordinates = new ArrayList<>(Arrays.asList(minXLetter1,
                            maxXLetter1, minYLetter1, maxYLetter1));
                    rightLetterClicked(letter1Time, letter1, coordinates, letter1time, score, nameScore);
                    letter1Time = 0;
                } else if (keyEvent.getText().equals(letter2.getText()) && !letter2Cliked) {
                    letter2Cliked = true;
                    List<Integer> coordinates = new ArrayList<>(Arrays.asList(minXLetter2,
                            maxXLetter2, minYLetter2, maxYLetter2));
                    rightLetterClicked(letter2Time, letter2, coordinates, letter2time, score, nameScore);
                    letter2Time = 0;
                } else if (keyEvent.getText().equals(letter3.getText()) && !letter3Cliked) {
                    letter3Cliked = true;
                    List<Integer> coordinates = new ArrayList<>(Arrays.asList(minXLetter3,
                            maxXLetter3, minYLetter3, maxYLetter3));
                    rightLetterClicked(letter3Time, letter3, coordinates, letter3time, score, nameScore);
                    letter2Time = 0;
                } else {
                    if (Integer.parseInt(scoreInt) > 0) {
                        scoreInt = Integer.toString(Integer.parseInt(scoreInt) - 1);
                        score.setText(nameScore + scoreInt);
                    }
                }
            }
        });
        stage.setScene(scene);
    }

    // Event to change letters, if they weren't clicked:
    private void timeEndedChange(Label letter, PauseTransition lettertime, int minX, int maxX, int minY, int maxY) {
        letter.setText(getLetter(letter.getText()));
        changeCoordinates(letter, minX, maxX, minY, maxY);
        lettertime.setDuration(Duration.seconds(lettersChangeTime));
        lettertime.play();
    }

    // Events to do when clicked right letter:
    private void rightLetterClicked(int letterTime, Label letter, List<Integer> coordinates,
                                    PauseTransition lettertime, Label score, String nameScore) {
        scoreInt = Integer.toString(Integer.parseInt(scoreInt) + (10 - letterTime));
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(BUTTONANIMATIONLENGTH), letter);
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.0);
        fadeTransition.setCycleCount(Animation.INDEFINITE);
        fadeTransition.play();
        PauseTransition letterChange = new PauseTransition(Duration.seconds(BUTTONANIMATIONWORKTIME));
        letterChange.setOnFinished(event -> { // timetracker for letter changing. it will blink 2 times
                    fadeTransition.pause();
                    letter.setText(getLetter(letter.getText()));
                    changeCoordinates(letter, coordinates.get(0), coordinates.get(1), coordinates.get(2),
                            coordinates.get(3));
                    lettertime.playFrom(Duration.seconds(lettersChangeTime));
                    score.setText(nameScore + scoreInt);
                    letter1Cliked = false;
                    letter2Cliked = false;
                    letter3Cliked = false;
                }
        );
        letterChange.play();
    }

    //Changes letter to another, doesn't repeat.
    public String getLetter(String usedLetter) {
        setOfCharsToUse.add(usedLetter);
        String letter = String.valueOf(chars.charAt(random.nextInt(chars.length())));
        if (setOfCharsToUse.contains(letter)) {
            setOfCharsToUse.remove(letter);
            return letter;
        } else {
            return getLetter(usedLetter);
        }
    }

    //Changes randomly letter coordinates.
    public void changeCoordinates(Label label, int minX, int maxX, int minY, int maxY) {
        label.setTranslateX(random.nextInt(maxX - minX) + minX);
        label.setTranslateY(random.nextInt(maxY - minY) + minY);
    }
}
