package comp1110.ass2.gui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;




public class Board extends Application {

    private static final int SQUARE_SIZE = 60;
    private static final int BOARD_HEIGHT = 700;
    private static final int BOARD_WIDTH = 933;
    private static final int PIECE_IMAGE_SIZE = (int) ((3*SQUARE_SIZE)*1.33);
    private static final String URI_BASE = "assets/";
    private final Group masks = new Group();
    private final Group board = new Group();
    private final Group root = new Group();
    char[] maskstate = new char[8];
    Scene scene = new Scene(root, BOARD_WIDTH, BOARD_HEIGHT);
    private void makeBoard() {
        for (int i = 0; i < 15; i++) {
            Circle r = new Circle ();
            r.setCenterX(SQUARE_SIZE*(i%5)*2);
            r.setCenterY(SQUARE_SIZE*(i/5)*2);
            r.setRadius(SQUARE_SIZE/3);
            board.getChildren().add(r);
            r.setFill(Color.GRAY);
        }
        for (int i = 0; i < 10; i++) {
            Circle r = new Circle ();
            r.setCenterX(SQUARE_SIZE*(i%5)*2+SQUARE_SIZE);
            r.setCenterY(SQUARE_SIZE*(i/5)*2+SQUARE_SIZE);
            r.setRadius(SQUARE_SIZE/3);
            board.getChildren().add(r);
            r.setFill(Color.GRAY);
        }
        board.toBack();
    }
    class FXMask extends ImageView {
        char mask;

        FXMask(char mask) {

            setImage(new Image(Board.class.getResource(URI_BASE + mask+'A' + ".png").toString()));
            this.mask = mask;

        }

    }


    class DraggableFXMask extends FXMask {
        int homeX, homeY;           // the position in the window where the mask should be when not on the board
        double mouseX, mouseY;      // the last known mouse positions (used when dragging)

        DraggableFXMask(char mask) {
            super(mask);

            homeX = ((mask-'A')%4*200+100);
            setLayoutX(homeX);
            homeY = ((mask-'A')/4*500+30);
            setLayoutY(homeY);
            setFitHeight(120);
            setFitWidth(120);

            setOnScroll(event -> {            // scroll to change orientation
                rotate();
                event.consume();
            });

            setOnMousePressed(event -> {      // mouse press indicates begin of drag
                mouseX = event.getSceneX();
                mouseY = event.getSceneY();
                setFitHeight(PIECE_IMAGE_SIZE);
                setFitWidth(PIECE_IMAGE_SIZE);

            });
            setOnMouseDragged(event -> {      // mouse is being dragged

                toFront();
                double movementX = event.getSceneX() - mouseX;
                double movementY = event.getSceneY() - mouseY;
                setLayoutX(getLayoutX() + movementX);
                setLayoutY(getLayoutY() + movementY);
                mouseX = event.getSceneX();
                mouseY = event.getSceneY();
                event.consume();
            });
            setOnMouseReleased(event -> {     // drag is complete
                snapToGrid();
            });



        }

        private void rotate() {

                setRotate((getRotate() + 90) % 360);

        }
        private void snapToGrid() {
            if (onBoard()) {
                setLayoutX((BOARD_WIDTH/2)+getLayoutX());
                setLayoutY((BOARD_HEIGHT/2)+getLayoutY());
            } else {
                snapToHome();
            }

        }
        private boolean onBoard() {
            return getLayoutX() > (200) && (getLayoutX() < (300))
                    && getLayoutY() > (200) && (getLayoutY() < (300));
        }
        private void snapToHome() {
            setLayoutX(homeX);
            setLayoutY(homeY);
            setRotate(0);
        }


    }

    private void makeMasks() {

            char piece[]={'A','B','C','D','E','F','G','H'};
            for (int i = 0;i<4;i++) {
                masks.getChildren().add(new DraggableFXMask((char)('A'+i)));

            }
            for (int i = 0;i<4;i++){
                masks.getChildren().add(new DraggableFXMask(((char)('E'+i))));
        }


    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        board.setLayoutX(200);
        board.setLayoutY(200);
        root.getChildren().add(board);
        root.getChildren().add(masks);
        makeBoard();
        makeMasks();


        primaryStage.setTitle("StepsGame Board");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
    // FIXME Task 7: Implement a basic playable Steps Game in JavaFX that only allows pieces to be placed in valid places

    // FIXME Task 8: Implement starting placements

    // FIXME Task 10: Implement hints

    // FIXME Task 11: Generate interesting starting placements


}
