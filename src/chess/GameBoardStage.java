package chess;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.util.ArrayList;

public class GameBoardStage {
	private Scene scene;
	private Stage stage;
	private Group root;
	private Canvas canvas;
	private GraphicsContext gc;

	private GridPane map;
	private int[][] gameBoard;
	// private ArrayList<Element> pieceCells;

    public final static int MAX_CELLS = 64;
	public final static int MAP_NUM_ROWS = 8;
	public final static int MAP_NUM_COLS = 8;
	public final static int MAP_WIDTH = 700;
	public final static int MAP_HEIGHT = 700;
	public final static int CELL_WIDTH = 65;
	public final static int CELL_HEIGHT = 65;
    public final static int WINDOW_WIDTH = 600;
	public final static int WINDOW_HEIGHT = 600;

    public final Image bg = new Image( // Background image
	"images/board.png",
	600,
	600,
	false,
	false);

	// Constructor
	public GameBoardStage() {
		this.root = new Group();
		this.scene = new Scene(root, GameBoardStage.WINDOW_WIDTH,GameBoardStage.WINDOW_HEIGHT, Color.ANTIQUEWHITE);
		this.canvas = new Canvas(GameBoardStage.WINDOW_WIDTH,GameBoardStage.WINDOW_HEIGHT);
		this.gc = canvas.getGraphicsContext2D();

		this.map = new GridPane(); // Main layout for the game
		// this.pieceCells = new ArrayList<Element>(); //List to hold the pattern of the Grid Pane
		this.gameBoard = new int[GameBoardStage.MAP_NUM_ROWS][GameBoardStage.MAP_NUM_COLS];
	}

		//method to add the stage elements
		public void setStage(Stage stage) {
			this.stage = stage;
			//draw the background to the canvas at location x=0, y=90
			this.gc.drawImage( this.bg, 0, 0); // background of the GridPane
	
			// this.initGameBoard();
			// this.createMap();
	
			//set stage elements here
			this.root.getChildren().add(this.canvas);
			this.root.getChildren().add(this.map);

			this.stage.setTitle("Chess");
			this.stage.setScene(this.scene);
			this.stage.show();
		}

		//method to initialize game board
		// private void initGameBoard(){
		// 	for(int i=0;i<GameBoardStage.MAP_NUM_ROWS;i++){
		// 		for(int j=0;j<GameBoardStage.MAP_NUM_COLS;j++){
		// 			int check = i*10+j;
		// 			switch (check) {
		// 				case value:
							
		// 					break;
					
		// 				default:
		// 					break;
		// 			}
		// 		}
		// 	}
		// }
}