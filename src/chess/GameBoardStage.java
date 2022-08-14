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
import java.util.HashMap;

public class GameBoardStage {
	private Scene scene;
	private Stage stage;
	private Group root;
	private Canvas canvas;
	private GraphicsContext gc;

	private GridPane map;
	private String[][] gameBoard;
	private ArrayList<Element> pieceCells;
	private Element activeCell;
	private Boolean hasActive;
	private HashMap<Integer, String> chessNotation;

	// Turn-Based System Attribute
	private int player_turn;
	// public final static String PLAYER_ONE = "P1";
	// public final static String PLAYER_TWO = "P2";

	// GUI and GridPane Size Specifications
    public final static int MAX_CELLS = 64;
	public final static int MAP_NUM_ROWS = 8;
	public final static int MAP_NUM_COLS = 8;
	public final static int MAP_WIDTH = 700;
	public final static int MAP_HEIGHT = 700;
	public final static int CELL_WIDTH = 60;
	public final static int CELL_HEIGHT = 70;
    public final static int WINDOW_WIDTH = 605;
	public final static int WINDOW_HEIGHT = 550;

    public final Image bg = new Image( // Background image
	"images/board.png",
	605,
	550,
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

		this.hasActive = false;
		this.initChessNotation();
		this.player_turn = 0;
	}

		// getter method for hasActive attribute
		Boolean getHasActive(){
			return this.hasActive;
		}

		// getter method for activeCell attribute
		Element getActiveCell(){
			return this.activeCell;
		}

		// getter method for pieceCells attribute
		ArrayList<Element> getPieceCells(){
			return this.pieceCells;
		}

		// getter method for chessNotation attribute
		HashMap<Integer, String> getChessNotation(){
			return this.chessNotation;
		}

		// getter method for player_turn attribute
		int getPlayerTurn(){
			return this.player_turn;
		}


		// setter method for hasActive attribute
		void setHasActive(Boolean bool){
			this.hasActive = bool;
		}

		// setter method for activeCell attribute
		void setActiveCell(Element active){
			this.activeCell = active;
		}

		// increment setter for player_turn
		void incrementTurn(){
			this.player_turn++;
		}

		// method to add the stage elements
		public void setStage(Stage stage) {
			this.stage = stage;
			//draw the background to the canvas at location x=0, y=0
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

		// method to print the gameboard
		void printGameBoard(){
			for(int i=0;i<GameBoardStage.MAP_NUM_ROWS;i++){
				System.out.println(Arrays.toString(this.gameBoard[i])); //print final board content
			}
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
			this.printGameBoard();
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
		this.map.setLayoutX(GameBoardStage.WINDOW_WIDTH*0.0175);
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

	// method to initialize the chess notations based on map coordinates
	private void initChessNotation(){
		this.chessNotation = new HashMap<Integer, String>();

		this.chessNotation.put(0, "A8");
		this.chessNotation.put(1, "B8");
		this.chessNotation.put(2, "C8");
		this.chessNotation.put(3, "D8");
		this.chessNotation.put(4, "E8");
		this.chessNotation.put(5, "F8");
		this.chessNotation.put(6, "G8");
		this.chessNotation.put(7, "H8");

		this.chessNotation.put(10, "A7");
		this.chessNotation.put(11, "B7");
		this.chessNotation.put(12, "C7");
		this.chessNotation.put(13, "D7");
		this.chessNotation.put(14, "E7");
		this.chessNotation.put(15, "F7");
		this.chessNotation.put(16, "G7");
		this.chessNotation.put(17, "H7");

		this.chessNotation.put(20, "A6");
		this.chessNotation.put(21, "B6");
		this.chessNotation.put(22, "C6");
		this.chessNotation.put(23, "D6");
		this.chessNotation.put(24, "E6");
		this.chessNotation.put(25, "F6");
		this.chessNotation.put(26, "G6");
		this.chessNotation.put(27, "H6");
		
		this.chessNotation.put(30, "A5");
		this.chessNotation.put(31, "B5");
		this.chessNotation.put(32, "C5");
		this.chessNotation.put(33, "D5");
		this.chessNotation.put(34, "E5");
		this.chessNotation.put(35, "F5");
		this.chessNotation.put(36, "G5");
		this.chessNotation.put(37, "H5");

		this.chessNotation.put(40, "A4");
		this.chessNotation.put(41, "B4");
		this.chessNotation.put(42, "C4");
		this.chessNotation.put(43, "D4");
		this.chessNotation.put(44, "E4");
		this.chessNotation.put(45, "F4");
		this.chessNotation.put(46, "G4");
		this.chessNotation.put(47, "H4");

		this.chessNotation.put(50, "A3");
		this.chessNotation.put(51, "B3");
		this.chessNotation.put(52, "C3");
		this.chessNotation.put(53, "D3");
		this.chessNotation.put(54, "E3");
		this.chessNotation.put(55, "F3");
		this.chessNotation.put(56, "G3");
		this.chessNotation.put(57, "H3");

		this.chessNotation.put(60, "A2");
		this.chessNotation.put(61, "B2");
		this.chessNotation.put(62, "C2");
		this.chessNotation.put(63, "D2");
		this.chessNotation.put(64, "E2");
		this.chessNotation.put(65, "F2");
		this.chessNotation.put(66, "G2");
		this.chessNotation.put(67, "H2");

		this.chessNotation.put(70, "A1");
		this.chessNotation.put(71, "B1");
		this.chessNotation.put(72, "C1");
		this.chessNotation.put(73, "D1");
		this.chessNotation.put(74, "E1");
		this.chessNotation.put(75, "F1");
		this.chessNotation.put(76, "G1");
		this.chessNotation.put(77, "H1");
	}

	// method to update gameboard piece placements
	void setGameBoardPiece(Element prev, Element next){
		// 'prev' pertains to the active piece clicked earlier by the player
		// 'next' pertains to the piece being clicked by the player

		// 'prev' coordinates
		int activeCoords = prev.getRow() * 10 + prev.getCol();
		// 'next' coordinates
		int nextCoords = next.getRow() * 10 + next.getCol();

		for(int row=0;row<GameBoardStage.MAP_NUM_ROWS;row++){
			for(int col=0;col<GameBoardStage.MAP_NUM_COLS;col++){
				// coordinates are checked using this formula
				int check = row*10+col;

				// Updating the previous tile coordinate
				if(check == activeCoords){
					this.gameBoard[row][col] = Element.CLEARED_TYPE;
				}

				// Updating the next tile with the active piece type
				if(check == nextCoords){
					this.gameBoard[row][col] = next.getType();
				}

			}
		}
	}
}