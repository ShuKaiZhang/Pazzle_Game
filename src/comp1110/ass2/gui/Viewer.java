package comp1110.ass2.gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
//import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.paint.Color;


/**
 * A very simple viewer for piece placements in the steps game.
 *
 * NOTE: This class is separate from your main game class.  This
 * class does not play a game, it just illustrates various piece
 * placements.
 */
public class Viewer extends Application {

    /* board layout */
    private static final int SQUARE_SIZE = 60;
    private static final int PIECE_IMAGE_SIZE = (int) ((3*SQUARE_SIZE)*1.33);
    private static final int VIEWER_WIDTH = 750;
    private static final int VIEWER_HEIGHT = 500;

    private static final String URI_BASE = "assets/";

    private final Group root = new Group();
    private final Group controls = new Group();
    private final Group square = new Group();
    TextField textField;

    class Square extends ImageView {

        Square(String id) {
            if(id.charAt(1)=='A'||id.charAt(1)=='E'){
                setImage(new Image(Viewer.class.getResource(URI_BASE + id.charAt(0)+'A' + ".png").toString()));
                setRotate(0);
            }
            if(id.charAt(1)=='B'){
                setImage(new Image(Viewer.class.getResource(URI_BASE + id.charAt(0)+'A' + ".png").toString()));
                setRotate(90);
            }
            if(id.charAt(1)=='C') {
                setImage(new Image(Viewer.class.getResource(URI_BASE + id.charAt(0) + 'A' + ".png").toString()));
                setRotate(90);
            }
            if(id.charAt(1)=='D'){
                setImage(new Image(Viewer.class.getResource(URI_BASE + id.charAt(0)+'A' + ".png").toString()));
                setRotate(90);
            }
            if(id.charAt(1)=='E'){
                setImage(new Image(Viewer.class.getResource(URI_BASE + id.charAt(0)+'E' + ".png").toString()));
                setRotate(90);
            }
            if(id.charAt(1)=='F'){
                setImage(new Image(Viewer.class.getResource(URI_BASE + id.charAt(0)+'E' + ".png").toString()));
                setRotate(90);
            }
            if(id.charAt(1)=='G'){
                setImage(new Image(Viewer.class.getResource(URI_BASE + id.charAt(0)+'E' + ".png").toString()));
                setRotate(90);
            }
            if(id.charAt(1)=='H'){
                setImage(new Image(Viewer.class.getResource(URI_BASE + id.charAt(0)+'E' + ".png").toString()));
                setRotate(90);
            }

            int a = (int)id.charAt(2);

            setFitHeight(PIECE_IMAGE_SIZE);
            setFitWidth(PIECE_IMAGE_SIZE);

            if (a<=89) {
                setLayoutX(((a - 65) % 10)  * SQUARE_SIZE);
                setLayoutY(((a - 65) / 10)  * SQUARE_SIZE);
            }
            if (a>=97) {
                setLayoutX(((a - 97+25) % 10)  * SQUARE_SIZE);
                setLayoutY(((a - 97+25) / 10)  * SQUARE_SIZE);
            }


            // setImage(new Image(Viewer.class.getResource(URI_BASE + id + ".png").toString()));
        }

    }

    /**
     * Draw a placement in the window, removing any previously drawn one
     *
     * @param placement  A valid placement string
     */
    void makePlacement(String placement) {
        square.getChildren().clear();
        int s = 0;
        // FIXME Task 4: implement the simple placement viewer
        for(int i =0;i<placement.length()/3;i++) {
            String storage = placement.substring(s,s+3);
            s+=3;
            square.getChildren().add(new Square(storage));
        }
        for (int i = 0; i < 15; i++) {
            Circle r = new Circle ();
            r.setCenterX(SQUARE_SIZE*(i%5)*2+2*SQUARE_SIZE);
            r.setCenterY(SQUARE_SIZE*(i/5)*2+2*SQUARE_SIZE);
            r.setRadius(SQUARE_SIZE/3);
            square.getChildren().add(r);
            r.setFill(Color.GRAY);
        }
        for (int i = 0; i < 10; i++) {
            Circle r = new Circle ();
            r.setCenterX(SQUARE_SIZE*(i%5)*2+3*SQUARE_SIZE);
            r.setCenterY(SQUARE_SIZE*(i/5)*2+3*SQUARE_SIZE);
            r.setRadius(SQUARE_SIZE/3);
            square.getChildren().add(r);
            r.setFill(Color.GRAY);
        }

        square.toBack();
    }


    /**
     * Create a basic text field for input and a refresh button.
     */
    private void makeControls() {
        Label label1 = new Label("Placement:");
        textField = new TextField ();
        textField.setPrefWidth(300);
        Button button = new Button("Refresh");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                makePlacement(textField.getText());
                textField.clear();
            }
        });
        HBox hb = new HBox();
        hb.getChildren().addAll(label1, textField, button);
        hb.setSpacing(10);
        hb.setLayoutX(130);
        hb.setLayoutY(VIEWER_HEIGHT - 50);
        controls.getChildren().add(hb);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("StepsGame Viewer");
        Scene scene = new Scene(root, VIEWER_WIDTH, VIEWER_HEIGHT);

        root.getChildren().add(controls);
        root.getChildren().add(square);

        makeControls();

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}