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
import java.util.Arrays;

public class GameBoardStage {
	private Scene scene;
	private Stage stage;
	private Group root;
	private Canvas canvas;
	private GraphicsContext gc;

	private GridPane map;
	private String[][] gameBoard;
	private ArrayList<Element> pieceCells;

	// GUI and GridPane Size Specifications
    public final static int MAX_CELLS = 64;
	public final static int MAP_NUM_ROWS = 8;
	public final static int MAP_NUM_COLS = 8;
	public final static int MAP_WIDTH = 700;
	public final static int MAP_HEIGHT = 700;
	public final static int CELL_WIDTH = 60;
	public final static int CELL_HEIGHT = 70;
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
		this.pieceCells = new ArrayList<Element>(); //List to hold the pattern of the Grid Pane
		this.gameBoard = new String[GameBoardStage.MAP_NUM_ROWS][GameBoardStage.MAP_NUM_COLS];
	}

		//method to add the stage elements
		public void setStage(Stage stage) {
			this.stage = stage;
			//draw the background to the canvas at location x=0, y=90
			this.gc.drawImage( this.bg, 0, 0); // background of the GridPane
	
			this.initGameBoard();
			this.createMap();
	
			//set stage elements here
			this.root.getChildren().add(this.canvas);
			this.root.getChildren().add(this.map);

			this.stage.setTitle("Chess");
			this.stage.setScene(this.scene);
			this.stage.show();
		}

		// method to initialize game board
		private void initGameBoard(){
			for(int i=0;i<GameBoardStage.MAP_NUM_ROWS;i++){
				for(int j=0;j<GameBoardStage.MAP_NUM_COLS;j++){
					int check = i*10+j;
					if(check == 0 || check == 7){
						gameBoard[i][j] = Element.B_ROOK_TYPE; // Black Rook
					}
						
					else if(check == 1 || check == 6){
						gameBoard[i][j] = Element.B_KNIGHT_TYPE; // Black Knight
					}

					else if(check == 2 || check == 5){
						gameBoard[i][j] = Element.B_BISHOP_TYPE; // Black Bishop
					}

					else if(check == 3){
						gameBoard[i][j] = Element.B_QUEEN_TYPE; // Black Queen
					}

					else if(check == 4){
						gameBoard[i][j] = Element.B_KING_TYPE; // Black King
					}

					else if(check >= 10 && check <= 17){
						gameBoard[i][j] = Element.B_PAWN_TYPE; // Black Pawn
					}

					else if(check >= 20 && check <= 57){
						gameBoard[i][j] = Element.CLEARED_TYPE; // Cleared Types
					}

					else if(check >= 60 && check <= 67){
						gameBoard[i][j] = Element.W_PAWN_TYPE; // White Pawn
					}

					else if(check == 70 || check == 77){
						gameBoard[i][j] = Element.W_ROOK_TYPE; // White Rook
					}

					else if(check == 71 || check == 76){
						gameBoard[i][j] = Element.W_KNIGHT_TYPE; // White Knight
					}

					else if(check == 72 || check == 75){
						gameBoard[i][j] = Element.W_BISHOP_TYPE; // White Bishop
					}

					else if(check == 73){
						gameBoard[i][j] = Element.W_QUEEN_TYPE; // White Queen
					}

					else if(check == 74){
						gameBoard[i][j] = Element.W_KING_TYPE; // White King
					}

				}
			}
			for(int i=0;i<GameBoardStage.MAP_NUM_ROWS;i++){
				System.out.println(Arrays.toString(this.gameBoard[i]));//print final board content
			}
		}

		private void createMap(){ // method to create 8x8 = 64 land cells

			// Instantiate chess elements while also passing the GameBoardStage
			
			for(int i=0;i<GameBoardStage.MAP_NUM_ROWS;i++){
				for(int j=0;j<GameBoardStage.MAP_NUM_COLS;j++){
	
					int check = i*10+j;
					if(check == 0 || check == 7){
						Element b_rook = new Element(Element.B_ROOK_TYPE, this);
						this.instantiateChessPiece(b_rook, i, j);
					}
						
					else if(check == 1 || check == 6){
						Element b_knight = new Element(Element.B_KNIGHT_TYPE, this);
						this.instantiateChessPiece(b_knight, i, j);
					}

					else if(check == 2 || check == 5){
						Element b_bishop = new Element(Element.B_BISHOP_TYPE, this);
						this.instantiateChessPiece(b_bishop, i, j);
					}

					else if(check == 3){
						Element b_queen = new Element(Element.B_QUEEN_TYPE, this);
						this.instantiateChessPiece(b_queen, i, j);
					}

					else if(check == 4){
						Element b_king = new Element(Element.B_KING_TYPE, this);
						this.instantiateChessPiece(b_king, i, j);
					}

					else if(check >= 10 && check <= 17){
						Element b_pawn = new Element(Element.B_PAWN_TYPE,this);
						this.instantiateChessPiece(b_pawn, i, j);
					}

					else if(check >= 20 && check <= 57){
						Element cleared = new Element(Element.CLEARED_TYPE, this);
						this.instantiateChessPiece(cleared, i, j);
					}

					else if(check >= 60 && check <= 67){
						Element w_pawn = new Element(Element.W_PAWN_TYPE,this);
						this.instantiateChessPiece(w_pawn, i, j);
					}

					else if(check == 70 || check == 77){
						Element w_rook = new Element(Element.W_ROOK_TYPE, this);
						this.instantiateChessPiece(w_rook, i, j);
					}

					else if(check == 71 || check == 76){
						Element w_knight = new Element(Element.W_KNIGHT_TYPE, this);
						this.instantiateChessPiece(w_knight, i, j);
					}

					else if(check == 72 || check == 75){
						Element w_bishop = new Element(Element.W_BISHOP_TYPE, this);
						this.instantiateChessPiece(w_bishop, i, j);
					}

					else if(check == 73){
						Element w_queen = new Element(Element.W_QUEEN_TYPE, this);
						this.instantiateChessPiece(w_queen, i, j);
					}

					else if(check == 74){
						Element w_king = new Element(Element.W_KING_TYPE, this);
						this.instantiateChessPiece(w_king, i, j);
					}

				}
			}
	
			this.setGridPaneProperties();
			this.setGridPaneContents();
		}

	// method to set the initial chess piece coordinates on the board
	private void instantiateChessPiece(Element piece, int i, int j){
		piece.initRowCol(i, j);
		this.pieceCells.add(piece);
	}

	// method to set size and location of the grid pane
	private void setGridPaneProperties(){
		this.map.setPrefSize(GameBoardStage.MAP_WIDTH, GameBoardStage.MAP_HEIGHT);
		//set the map to x and y location; add border color to see the size of the gridpane/map
//	    this.map.setStyle("-fx-border-color: red ;");
		this.map.setLayoutX(GameBoardStage.WINDOW_WIDTH*0.010);
	    this.map.setLayoutY(GameBoardStage.WINDOW_WIDTH*0.013);
	    this.map.setVgap(8.3);
	    this.map.setHgap(15);
	}

	// method to add row and column constraints of the grid pane
	private void setGridPaneContents(){

		 //loop that will set the constraints of the elements in the grid pane
	     int counter=0;
	     for(int row=0;row<GameBoardStage.MAP_NUM_ROWS;row++){
	    	 for(int col=0;col<GameBoardStage.MAP_NUM_COLS;col++){
	    		 // map each land's constraints
	    		 GridPane.setConstraints(pieceCells.get(counter).getImageView(),col,row);
	    		 counter++;
	    	 }
	     }

	   //loop to add each land element to the gridpane/map
	     for(Element piece: pieceCells){
	    	 this.map.getChildren().add(piece.getImageView());
	     }
	}
}