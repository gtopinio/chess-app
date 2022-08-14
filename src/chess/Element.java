package chess;

import java.util.HashMap;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class Element {
    private String type;
	protected Image img;
	protected ImageView imgView;
	protected GameBoardStage gameStage;
	protected int row, col;
	protected String color;
	// protected String direction;

    // The images are stored as constant values
    public final static int IMAGE_SIZE = 60;

	// NULL Image
	public final static Image NULL_IMAGE = new Image("images/null_image.png",GameBoardStage.CELL_WIDTH,GameBoardStage.CELL_WIDTH,false,false);

    // Black Chess Pieces
    public final static Image B_KING_IMAGE = new Image("images/black_king.png",GameBoardStage.CELL_WIDTH,GameBoardStage.CELL_WIDTH,false,false);
    public final static Image B_QUEEN_IMAGE = new Image("images/black_queen.png",GameBoardStage.CELL_WIDTH,GameBoardStage.CELL_WIDTH,false,false);
    public final static Image B_ROOK_IMAGE = new Image("images/black_rook.png",GameBoardStage.CELL_WIDTH,GameBoardStage.CELL_WIDTH,false,false);
    public final static Image B_BISHOP_IMAGE = new Image("images/black_bishop.png",GameBoardStage.CELL_WIDTH,GameBoardStage.CELL_WIDTH,false,false);
    public final static Image B_KNIGHT_IMAGE = new Image("images/black_knight.png",GameBoardStage.CELL_WIDTH,GameBoardStage.CELL_WIDTH,false,false);
    public final static Image B_PAWN_IMAGE = new Image("images/black_pawn.png",GameBoardStage.CELL_WIDTH,GameBoardStage.CELL_WIDTH,false,false);

    // White Chess Pieces
    public final static Image W_KING_IMAGE = new Image("images/white_king.png",GameBoardStage.CELL_WIDTH,GameBoardStage.CELL_WIDTH,false,false);
    public final static Image W_QUEEN_IMAGE = new Image("images/white_queen.png",GameBoardStage.CELL_WIDTH,GameBoardStage.CELL_WIDTH,false,false);
    public final static Image W_ROOK_IMAGE = new Image("images/white_rook.png",GameBoardStage.CELL_WIDTH,GameBoardStage.CELL_WIDTH,false,false);
    public final static Image W_BISHOP_IMAGE = new Image("images/white_bishop.png",GameBoardStage.CELL_WIDTH,GameBoardStage.CELL_WIDTH,false,false);
    public final static Image W_KNIGHT_IMAGE = new Image("images/white_knight.png",GameBoardStage.CELL_WIDTH,GameBoardStage.CELL_WIDTH,false,false);
    public final static Image W_PAWN_IMAGE = new Image("images/white_pawn.png",GameBoardStage.CELL_WIDTH,GameBoardStage.CELL_WIDTH,false,false);

	// Black Piece Coordinate Names
    public final static String B_KING_TYPE = "BK";
    public final static String B_QUEEN_TYPE = "BQ";
    public final static String B_ROOK_TYPE = "BR";
    public final static String B_BISHOP_TYPE = "BB";
    public final static String B_KNIGHT_TYPE = "BN";
    public final static String B_PAWN_TYPE = "BP";

	// White Piece Coordinate Names
    public final static String W_KING_TYPE = "WK";
    public final static String W_QUEEN_TYPE = "WQ";
    public final static String W_ROOK_TYPE = "WR";
    public final static String W_BISHOP_TYPE = "WB";
    public final static String W_KNIGHT_TYPE = "WN";
    public final static String W_PAWN_TYPE = "WP";

	// NULL Piece Coordinate Name
	public final static String CLEARED_TYPE = "00";

	public final static String WHITE_COLOR = "WHITE";
	public final static String BLACK_COLOR = "BLACK";

	// public final static String UP_DIRECTION = "UP";
	// public final static String DOWN_DIRECTION = "DOWN";

	public Element(String type, GameBoardStage gameStage) { // Note: an ELEMENT has an imgView (to set up an Image)
		this.type = type;
		this.gameStage = gameStage;

		// load image depending on the type
		switch(this.type) { // This determines which picture to use, depending on the element to be added on the layout
			case Element.B_KING_TYPE: this.img = Element.B_KING_IMAGE; this.color = Element.BLACK_COLOR; break;
            case Element.B_QUEEN_TYPE: this.img = Element.B_QUEEN_IMAGE; this.color = Element.BLACK_COLOR; break;
            case Element.B_ROOK_TYPE: this.img = Element.B_ROOK_IMAGE; this.color = Element.BLACK_COLOR; break;
            case Element.B_BISHOP_TYPE: this.img = Element.B_BISHOP_IMAGE; this.color = Element.BLACK_COLOR; break;
            case Element.B_KNIGHT_TYPE: this.img = Element.B_KNIGHT_IMAGE; this.color = Element.BLACK_COLOR; break;
            case Element.B_PAWN_TYPE: this.img = Element.B_PAWN_IMAGE; this.color = Element.BLACK_COLOR; break;

            case Element.W_KING_TYPE: this.img = Element.W_KING_IMAGE; this.color = Element.WHITE_COLOR; break;
            case Element.W_QUEEN_TYPE: this.img = Element.W_QUEEN_IMAGE; this.color = Element.WHITE_COLOR; break;
            case Element.W_ROOK_TYPE: this.img = Element.W_ROOK_IMAGE; this.color = Element.WHITE_COLOR; break;
            case Element.W_BISHOP_TYPE: this.img = Element.W_BISHOP_IMAGE; this.color = Element.WHITE_COLOR; break;
            case Element.W_KNIGHT_TYPE: this.img = Element.W_KNIGHT_IMAGE; this.color = Element.WHITE_COLOR; break;
            case Element.W_PAWN_TYPE: this.img = Element.W_PAWN_IMAGE; this.color = Element.WHITE_COLOR; break;

			case Element.CLEARED_TYPE: this.img = Element.NULL_IMAGE; break;

		}

		this.setImageView();
		this.setMouseHandler();
	}

    protected void loadImage(String filename,int width, int height){
		try{
			this.img = new Image(filename,width,height,false,false);
		} catch(Exception e){}
	}


	String getType(){
		return this.type;
	}

	Image getImage(){
		return this.img;
	}

	int getRow() {
		return this.row;
	}

	int getCol() {
		return this.col;
	}

	String getColor(){
		return this.color;
	}


	protected ImageView getImageView(){
		return this.imgView;
	}

	void setType(String type){
		this.type = type;
	}

	void initRowCol(int i, int j) {
		this.row = i;
		this.col = j;
	}

	private void setImageView() { //Adjusting the properties of an image
		// initialize the image view of this element
		this.imgView = new ImageView();
		this.imgView.setImage(this.img);
		this.imgView.setLayoutX(0); // JavaFX method of setLayout for Image. Sets the X/Y positioning of the node
		this.imgView.setLayoutY(0);
		this.imgView.setPreserveRatio(true);

        this.imgView.setFitWidth(GameBoardStage.CELL_WIDTH);
        this.imgView.setFitHeight(GameBoardStage.CELL_HEIGHT);
	}

	private void setMouseHandler(){
		Element element = this; // Notice that no parameters, but we still got the element
		this.imgView.setOnMouseClicked(new EventHandler<MouseEvent>(){ //Notice that the ImageView element is the one that interacts
			public void handle(MouseEvent e) {

				// The chess notation can be retrieved for easier reading
				HashMap<Integer, String> chessNotation = gameStage.getChessNotation();
				int coordinate = element.getRow() * 10 + element.getCol(); // This is the map coordinate of the piece from the GridPane that has been clicked by the player

				// Prints out the clicked piece or tile + coordinates
				System.out.print("Player clicked:" + chessNotation.get(coordinate));
				System.out.println(" --- Type:"+ element.getType());

				if(gameStage.getHasActive()){ // If there is an active piece or tile, set the next movement for the piece
					if(element.getType() == Element.CLEARED_TYPE){
						// Need to update the following: gameBoard, the previous piece, and the next piece tile
						// The current element being clicked is the target tile
						// The target tile/piece's type and image should be changed according to the active piece's attributes 
						element.setType(gameStage.getActiveCell().getType());
						changeImage(element, gameStage.getActiveCell().getImage());

						// Updating the gameboard coordinates status
						gameStage.setGameBoardPiece(gameStage.getActiveCell(), element);

						// Clearing the active piece
						Element cleared = new Element(Element.CLEARED_TYPE, gameStage);

						// Setting the new active piece to a cleared type and setting the gamestage's hasActive back to false
						gameStage.setActiveCell(cleared);
						gameStage.setHasActive(false);

						// Printing the active piece type and see if there is an active piece
						System.out.println("Active piece: " + gameStage.getActiveCell().getType());
						System.out.println("Is piece active: " + gameStage.getHasActive());
					}
				}
				else { // If there's no active piece, set the active piece

					// WHITE'S TURN
					if(gameStage.getPlayerTurn() % 2 == 0 && element.getColor() == Element.WHITE_COLOR){

						if(element.getType() == Element.CLEARED_TYPE){
							// If the piece being clicked, there should be no active piece yet
							gameStage.setHasActive(false);
						}
						else {
							// Copying the type and gamestage of the clicked piece and declaring a new piece
							Element newActive = new Element(element.getType(), element.gameStage);
							newActive.initRowCol(element.getRow(), element.getCol());
	
							// Setting the new active piece and setting the gamestage's hasActive to true
							gameStage.setActiveCell(newActive);
							gameStage.setHasActive(true);
	
							// Printing the active piece type and see if there is an active piece
							System.out.println("Active piece: " + gameStage.getActiveCell().getType());
							System.out.println("Is piece active: " + gameStage.getHasActive());
	
							// Changing the piece type to a cleared type and changing the image to null
							element.setType(Element.CLEARED_TYPE);
							changeImage(element, Element.NULL_IMAGE);
						}

						gameStage.incrementTurn();
					}

					// BLACK'S TURN
					else if(gameStage.getPlayerTurn() % 2 == 1 && element.getColor() == Element.BLACK_COLOR){
						
						if(element.getType() == Element.CLEARED_TYPE){
							// If the piece being clicked, there should be no active piece yet
							gameStage.setHasActive(false);
						}
						else {
							// Copying the type and gamestage of the clicked piece and declaring a new piece
							Element newActive = new Element(element.getType(), element.gameStage);
							newActive.initRowCol(element.getRow(), element.getCol());
	
							// Setting the new active piece and setting the gamestage's hasActive to true
							gameStage.setActiveCell(newActive);
							gameStage.setHasActive(true);
	
							// Printing the active piece type and see if there is an active piece
							System.out.println("Active piece: " + gameStage.getActiveCell().getType());
							System.out.println("Is piece active: " + gameStage.getHasActive());
	
							// Changing the piece type to a cleared type and changing the image to null
							element.setType(Element.CLEARED_TYPE);
							changeImage(element, Element.NULL_IMAGE);
						}

						gameStage.incrementTurn();
					}




				}
				// printing the gameboard after every mouse click
				gameStage.printGameBoard();
			}	//end of handle()
		});
	}

	// private void clearImage(Element element) {
	// 	imgView.setImage(null);
	// }

	private void changeImage(Element element, Image image) {
		this.imgView.setImage(image);

	}
}
