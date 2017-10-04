package comp1110.ass2.gui;


import comp1110.ass2.StepsGame;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Board extends Application {


    private static final int SQUARE_SIZE = 60;
    private static final int BOARD_HEIGHT = 700;
    private static final int BOARD_WIDTH = 933;
    private static final int PIECE_IMAGE_SIZE = (int) ((3*SQUARE_SIZE)*1.33);
    private static final String URI_BASE = "assets/";
    private final Group masks = new Group();
    private final Group board = new Group();
    private final Group root = new Group();
    String [] maskstate = new String[8];
    String Masks = "";
    int count = 0;
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
        int homeX, homeY;
        double mouseX, mouseY;

        DraggableFXMask(char mask) {
            super(mask);

            homeX = ((mask-'A')%4*200+100);
            setLayoutX(homeX);
            homeY = ((mask-'A')/4*500+30);
            setLayoutY(homeY);
            setFitHeight(120);
            setFitWidth(120);


            setOnMouseClicked(event -> {
                if (event.getButton() == MouseButton.SECONDARY)
                        {   flip();
                            event.consume();
                        }
                    });

            setOnScroll(event -> {
                rotate();
                event.consume();
            });

            setOnMousePressed(event -> {
                if (event.getButton() == MouseButton.PRIMARY){
                mouseX = event.getSceneX();
                mouseY = event.getSceneY();
                setFitHeight(PIECE_IMAGE_SIZE);
                setFitWidth(PIECE_IMAGE_SIZE);
                check();
                event.consume();}
            });


            setOnMouseDragged(event -> {
                if (checktop()){
                if (maskstate[mask - 'A'] != null) {
                    String z = (Masks.replace(maskstate[mask - 'A'], "")+maskstate[mask - 'A']); }
                toFront();
                double movementX = event.getSceneX() - mouseX;
                double movementY = event.getSceneY() - mouseY;
                setLayoutX(getLayoutX() + movementX);
                setLayoutY(getLayoutY() + movementY);
                mouseX = event.getSceneX();
                mouseY = event.getSceneY();

                event.consume();}

            });
            setOnMouseReleased(event -> {
                if (checktop()){
                snapToGrid();
                event.consume();}

            });



        }
        private boolean checktop() {
            if (maskstate[mask - 'A'] != null) {
                return StepsGame.isPlacementSequenceValid(Masks.replace(maskstate[mask - 'A'], "")+maskstate[mask - 'A']);
            }if (maskstate[mask - 'A'] == null){return true;}
            else return false;
        }

        private void rotate() {
            if (maskstate[mask - 'A'] == null) {
            setRotate((getRotate() + 90) % 360);}
        }
        private void check() {
            if (maskstate[mask-'A'] != null){
                Masks=Masks.replace(maskstate[mask-'A'],"");
            }
        }

        private void flip() {
            if (maskstate[mask - 'A'] == null) {
            count +=1;
            if (count%2==1){
            setImage(new Image(Board.class.getResource(URI_BASE + mask+'E' + ".png").toString()));}
            else {setImage(new Image(Board.class.getResource(URI_BASE + mask+'A' + ".png").toString()));}}
        }
        private void setPosition() {
            int x = ((int)getLayoutX()-80)/SQUARE_SIZE;
            int y = ((int)getLayoutY()-80)/SQUARE_SIZE;
            char pos =' ';
            if((x+y*10)<=24){
            pos = (char)(x+(y*10)+'A');
            }else{pos = (char)((x-5)+((y-2)*10)+'a');}
            char rotate = (char)('A'+ (int)(getRotate() / 90));
            String val="";
            val += mask;
            if (count%2==0){val+= rotate;}
            else{val+= (char)(rotate+4);}
            val += pos;
            maskstate [mask-'A'] = val;

        }

        private void snapToGrid() {
                setLayoutX((int)(getLayoutX()/60)*60.0+20);
                setLayoutY((int)(getLayoutY()/60)*60.0+20);
                setPosition();
                if (StepsGame.isPlacementSequenceValid(maskstate[mask-'A'])){
                    if(StepsGame.isPlacementSequenceValid(Masks+maskstate[mask-'A'])){
                        Masks +=maskstate[mask-'A'];setPosition();
                    }else {snapToHome();}

                }
                else {
                    maskstate [mask-'A'] = null;
                    snapToHome();
                }
        }

        private void snapToHome() {
            setLayoutX(homeX);
            setLayoutY(homeY);
            setFitHeight(120);
            setFitWidth(120);
            setRotate(0);
        }


    }


    private void makeMasks() {


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
