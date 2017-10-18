package comp1110.ass2.gui;


import comp1110.ass2.StepsGame;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;


public class Board extends Application {

    public static int z = 0;
    private static final int SQUARE_SIZE = 60;
    private static final int BOARD_HEIGHT = 700;
    private static final int BOARD_WIDTH = 933;
    private static final int PIECE_IMAGE_SIZE = (int) ((3 * SQUARE_SIZE) * 1.33);
    private static final String URI_BASE = "assets/";
    private final Group masks = new Group();
    private final Group board = new Group();
    private final Group root = new Group();
    private final Group win = new Group();
    private final Group btm = new Group();
    private final Group square = new Group();
    String[] maskstate = new String[8];
    String Masks = "";
    int[] count = new int[8];
    Scene scene = new Scene(root, BOARD_WIDTH, BOARD_HEIGHT);

    private void makeBoard() {
        for (int i = 0; i < 15; i++) {
            Circle r = new Circle();
            r.setCenterX(SQUARE_SIZE * (i % 5) * 2);
            r.setCenterY(SQUARE_SIZE * (i / 5) * 2);
            r.setRadius(SQUARE_SIZE / 3);
            board.getChildren().add(r);
            r.setFill(Color.GRAY);
        }
        for (int i = 0; i < 10; i++) {
            Circle r = new Circle();
            r.setCenterX(SQUARE_SIZE * (i % 5) * 2 + SQUARE_SIZE);
            r.setCenterY(SQUARE_SIZE * (i / 5) * 2 + SQUARE_SIZE);
            r.setRadius(SQUARE_SIZE / 3);
            board.getChildren().add(r);
            r.setFill(Color.GRAY);
        }
        board.toBack();


    }

    class FXMask extends ImageView {
        char mask;

        FXMask(char mask) {

            setImage(new Image(Board.class.getResource(URI_BASE + mask + 'A' + ".png").toString()));
            this.mask = mask;

        }

    }


    public class DraggableFXMask extends FXMask {
        int homeX, homeY;
        double mouseX, mouseY;

        DraggableFXMask(char mask) {
            super(mask);

            homeX = ((mask - 'A') % 4 * 200 + 100);
            setLayoutX(homeX);
            homeY = ((mask - 'A') / 4 * 500 + 30);
            setLayoutY(homeY);
            setFitHeight(120);
            setFitWidth(120);


            setOnMouseClicked(event -> {
                if (event.getButton() == MouseButton.SECONDARY)

                {
                    flip();
                    event.consume();
                }
            });

            setOnScroll(event -> {
                rotate();
                event.consume();
            });

            setOnMousePressed(event -> {
                if (event.getButton() == MouseButton.PRIMARY) {
                    mouseX = event.getSceneX();
                    mouseY = event.getSceneY();
                    setFitHeight(PIECE_IMAGE_SIZE);
                    setFitWidth(PIECE_IMAGE_SIZE);
                    check();
                    event.consume();
                }
            });


            setOnMouseDragged(event -> {
                if (checktop() && event.getButton() == MouseButton.PRIMARY) {
                    toFront();
                    double movementX = event.getSceneX() - mouseX;
                    double movementY = event.getSceneY() - mouseY;
                    setLayoutX(getLayoutX() + movementX);
                    setLayoutY(getLayoutY() + movementY);
                    mouseX = event.getSceneX();
                    mouseY = event.getSceneY();

                    event.consume();
                }

            });
            setOnMouseReleased(event -> {
                if (checktop() && event.getButton() == MouseButton.PRIMARY) {
                    snapToGrid();
                    if (Masks.length() == 24) {
                        Text a = new Text(350, 100, "YOU WIN!");
                        a.setFont(new Font(60));
                        win.getChildren().add(a);
                    }
                    System.out.println(Masks);
                    event.consume();
                }

            });


        }

        private boolean checkpos() {
            int x = ((int) getLayoutX() - 80) / SQUARE_SIZE;
            int y = ((int) getLayoutY() - 80) / SQUARE_SIZE;
            if (x >= 0 && x <= 10 && y >= 0 && y <= 5) {
                return true;
            } else return false;
        }

        private boolean checktop() {
            if (maskstate[mask - 'A'] != null) {
                return StepsGame.isPlacementSequenceValid(Masks.replace(maskstate[mask - 'A'], "") + maskstate[mask - 'A']);
            }
            if (maskstate[mask - 'A'] == null) {
                return true;
            } else return false;
        }

        private void rotate() {
            if (maskstate[mask - 'A'] == null) {
                setRotate((getRotate() + 90) % 360);
            }
        }

        private void check() {

            if (maskstate[mask - 'A'] != null) {
                Masks = Masks.replace(maskstate[mask - 'A'], "");
            }
        }

        private void flip() {
            if (maskstate[mask - 'A'] == null) {
                count[mask - 'A'] += 1;
                if (count[mask - 'A'] == 1) {
                    setImage(new Image(Board.class.getResource(URI_BASE + mask + 'E' + ".png").toString()));
                } else {
                    setImage(new Image(Board.class.getResource(URI_BASE + mask + 'A' + ".png").toString()));
                    count[mask - 'A'] = 0;
                }
            }
        }

        private void setPosition() {
            int x = ((int) getLayoutX() - 80) / SQUARE_SIZE;
            int y = ((int) getLayoutY() - 80) / SQUARE_SIZE;
            char pos = ' ';
            if ((x + y * 10) <= 24) {
                pos = (char) (x + (y * 10) + 'A');
            } else {
                pos = (char) ((x - 5) + ((y - 2) * 10) + 'a');
            }
            char rotate = (char) ('A' + (int) (getRotate() / 90));
            String val = "";
            val += mask;
            if (count[mask - 'A'] == 0) {
                val += rotate;
            } else {
                val += (char) (rotate + 4);
            }
            val += pos;
            maskstate[mask - 'A'] = val;

        }

        private void snapToGrid() {
            setLayoutX((int) (getLayoutX() / 60) * 60.0 + 20);
            setLayoutY((int) (getLayoutY() / 60) * 60.0 + 20);
            setPosition();
            if (checkpos() && StepsGame.isPlacementSequenceValid(maskstate[mask - 'A'])) {
                if (StepsGame.isPlacementSequenceValid(Masks + maskstate[mask - 'A'])) {
                    Masks += maskstate[mask - 'A'];
                    setPosition();
                } else {
                    snapToHome();
                }

            } else {
                maskstate[mask - 'A'] = null;
                snapToHome();
            }
        }

        private void snapToHome() {
            maskstate[mask - 'A'] = null;
            setLayoutX(homeX);
            setLayoutY(homeY);
            setFitHeight(120);
            setFitWidth(120);
            setRotate(0);
        }


    }


    private void makeMasks(char a) {
        masks.getChildren().add(new DraggableFXMask(a));
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        board.setLayoutX(200);
        board.setLayoutY(200);
        root.getChildren().add(win);
        root.getChildren().add(btm);
        root.getChildren().add(board);
        root.getChildren().add(masks);
        root.getChildren().add(square);
        makeBoard();
        primaryStage.setTitle("StepsGame Board");
        primaryStage.setScene(scene);
        primaryStage.show();

        class Botton {
            String a;

             Botton(String a) {

                 this.a = a;
                 System.out.println(a);

                 Button easyBtn = new Button();
                easyBtn.setLayoutX(40);
                easyBtn.setLayoutY(200+(a.charAt(0)-'A')*100);
                easyBtn.setText(a);
                btm.getChildren().add(easyBtn);
                square.toBack();
                 easyBtn.setOnAction(new EventHandler<ActionEvent>() {
           @Override
                public void handle(ActionEvent event) {

               if (a.charAt(0)=='A'){z = 0;}
               if (a.charAt(0)=='B'){z = 1;}
               if (a.charAt(0)=='C'){z = 2;}
                    win.getChildren().clear();
                    square.getChildren().clear();
                    masks.getChildren().clear();

                    int random = rand.nextInt(3);
                    int s = 0;
                    String a = "";
                    Set<String> b = new HashSet<>();
                    String[] d = {"A", "B", "C", "D", "E", "F", "G", "H"};
                    Set<String> c = new HashSet<>(Arrays.asList(d));
                    for (int i = 0; i < starterStarts(random).length() / 3; i++) {
                        String storage = starterStarts(random).substring(s, s + 3);
                        s += 3;
                        square.getChildren().add(new Viewer.Square(storage));
                        Masks = starterStarts(random);
                        square.setLayoutX(80);
                        square.setLayoutY(80);

                        b.add("" + starterStarts(random).charAt(i * 3));
                    }
                    c.removeAll(b);
                    for (String i : c) {
                        makeMasks(i.charAt(0));
                    }
                }

                 });}


        }
        String[] ac = {"A Level","B Level","C Level"};
            for(String i:ac){
                Botton a = new Botton(i);
        }

    }

    // FIXME Task 7: Implement a basic playable Steps Game in JavaFX that only allows pieces to be placed in valid places

    // FIXME Task 8: Implement starting placements
    Random rand = new Random();

    String  [][] ran = {{"DFOAALHHnGAkFGQCAiBBg", "BGKFCNCHSAHQHFnEBvGAi", "HBLADgBHnCGODAiGEl"},
            {"BHFFCLHBNAGl", "BGKEGOCGQAGl", "GDLADgHAiEFFCGc"},
            {"DFOGGQEDI", "EGOCGQGGS", "CGOGGQEDI"}};

    public String finalString;

    public String starterStarts(int i){
        finalString = ran[z][i];
        return finalString;
    }





    // FIXME Task 10: Implement hints

    // FIXME Task 11: Generate interesting starting placements


}