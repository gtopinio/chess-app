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
	public final static String NULL_COLOR = "NULL";

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

			case Element.CLEARED_TYPE: this.img = Element.NULL_IMAGE; this.color = Element.NULL_COLOR; break;

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

	void setColor(String color){
		this.color = color;
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
				System.out.println("========== STATUS ==========");
				System.out.println("Player clicked: " + chessNotation.get(coordinate));
				System.out.println("Type: " + element.getType());
				System.out.println("Color: " + element.getColor());
				System.out.println("============================");

				if(gameStage.getHasActive()){ // If there is an active piece or tile, set the next movement for the piece
					if(validPlacement(element)){
						// Need to update the following: gameBoard, the previous piece, and the next piece tile
						// The current element being clicked is the target tile
						// The target tile/piece's type, image, and color should be changed according to the active piece's attributes 
						element.setType(gameStage.getActiveCell().getType());
						changeImage(element, gameStage.getActiveCell().getImage());
						element.setColor(gameStage.getActiveCell().getColor());

						// Updating the gameboard coordinates status
						gameStage.setGameBoardPiece(gameStage.getActiveCell(), element);

						// Clearing the active piece
						Element cleared = new Element(Element.CLEARED_TYPE, gameStage);

						// Setting the new active piece to a cleared type and setting the gamestage's hasActive back to false
						gameStage.setActiveCell(cleared);
						gameStage.setHasActive(false);
						gameStage.clearActive();

						// Printing the active piece type and see if there is an active piece
						System.out.println("Active piece: " + gameStage.getActiveCell().getType());
						System.out.println("Is piece active: " + gameStage.getHasActive());
					}
				}
				else { // If there's no active piece, set the active piece

					// WHITE'S TURN
					if(gameStage.getPlayerTurn() % 2 == 0 && element.getColor() == Element.WHITE_COLOR){

						if(element.getType() == Element.CLEARED_TYPE){
							// If the piece being clicked is a cleared type, there should be no active piece yet
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
							element.setColor(Element.NULL_COLOR);
						}

						gameStage.incrementTurn();
					}

					// BLACK'S TURN
					else if(gameStage.getPlayerTurn() % 2 == 1 && element.getColor() == Element.BLACK_COLOR){
						
						if(element.getType() == Element.CLEARED_TYPE){
							// If the piece being clicked is a cleared type, there should be no active piece yet
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
							element.setColor(Element.NULL_COLOR);
						}

						gameStage.incrementTurn();
					}
					gameStage.showActive();
				}
				// printing the gameboard after every mouse click
				gameStage.printGameBoard();

				// updating menubar contents
				gameStage.updateColorTurn(gameStage.getPlayerTurn());
			}	//end of handle()
		});
	}

	// private void clearImage(Element element) {
	// 	imgView.setImage(null);
	// }

	// method to change image of an Element
	private void changeImage(Element element, Image image) {
		this.imgView.setImage(image);

	}

	// method to know if the chess piece placement is valid or not. It returns a boolean value.
	private boolean validPlacement(Element next){
		// active piece coordinates
		int activeCoords = gameStage.getActiveCell().getRow() * 10 + gameStage.getActiveCell().getCol();
		// next tile/piece coordinates
		int nextCoords = next.getRow() * 10 + next.getCol();
		String[][] gameboard = gameStage.getGameBoard();
		Boolean valid = false;

		switch (gameStage.getActiveCell().getType()) {
			// valid placements for WHITE PAWN
			case Element.W_PAWN_TYPE:
				// possible two-tile placements at initial stage of pawn
				if((activeCoords >= 60 && activeCoords <= 67) && ((activeCoords - nextCoords) == 10 || (activeCoords - nextCoords) == 20 )){
					valid = true;
				}
				// one forward tile movement for the pawn
				else if((activeCoords - nextCoords) == 10){
					valid = true;
				}
				// eating movement
				else if( (activeCoords - nextCoords == 11 || activeCoords - nextCoords == 9) && (next.getColor() == Element.BLACK_COLOR) ){
					valid = true;
				}
				else valid = false;
				
				break;
			
			// valid placements for BLACK PAWN
			case Element.B_PAWN_TYPE:
				// possible two-tile placements at initial stage of pawn
				if((activeCoords >= 10 && activeCoords <= 17) && ((activeCoords - nextCoords) == -10 || (activeCoords - nextCoords) == -20 )){
					valid = true;
				}
				// one forward tile movement for the pawn
				else if((activeCoords - nextCoords) == -10){
					valid = true;
				}
				// eating movement
				else if( (activeCoords - nextCoords == -11 || activeCoords - nextCoords == -9) && (next.getColor() == Element.WHITE_COLOR) ){
					valid = true;
				}
				else valid = false;
				
				break;
			
			case Element.W_ROOK_TYPE:
				// horizontal rook movement
				// need to know if there's a clear horizontal path between the active piece and next piece
				// first, we need to know if the rook and next tile is on the same horizontal space / row
				// then, we need to know if there's no SAME COLORED obstacle on that space
				// lastly, rook placement only works on OPPOSITE COLORS or EMPTY tile

				if(gameStage.getActiveCell().getRow() == next.getRow() && (gameStage.getActiveCell().getColor() != next.getColor())){
					
					// to move from the left horizontal space, we need to scan from the left of the active piece
					if(activeCoords > nextCoords){
						for(int col = gameStage.getActiveCell().getCol()-1; col >= next.getCol(); col--){
							// if there's a COLORED obstacle and scanning didn't finish until the next tile's column, it's invalid movement
							if(gameboard[next.getRow()][col].contains("W") && col != next.getCol() || gameboard[next.getRow()][col].contains("B") && col != next.getCol()){
								valid = false;
								break;
							}
							else{
								valid = true;
							}
						}
					}

					// to move from the right horizontal space, we need to scand from the right of the active piece
					else if(activeCoords < nextCoords){
						for(int col = gameStage.getActiveCell().getCol()+1; col <= next.getCol(); col++){
							// if there's a COLORED obstacle and scanning didn't finish until the next tile's column, it's invalid movement
							if(gameboard[next.getRow()][col].contains("W") && col != next.getCol() || gameboard[next.getRow()][col].contains("B") && col != next.getCol()){
								valid = false;
								break;
							}
							else{
								valid = true;
							}
						}
					}
				}

				// vertical movement of the rook
				else if(gameStage.getActiveCell().getCol() == next.getCol() && (gameStage.getActiveCell().getColor() != next.getColor())){
					// to move from the upper vertical space, we need to scan from the upper of the active piece
					if(activeCoords > nextCoords){
						for(int row = gameStage.getActiveCell().getRow()-1; row >= next.getRow(); row--){
							// if there's a COLORED obstacle and scanning didn't finish until the next tile's column, it's invalid movement
							if(gameboard[row][next.getCol()].contains("W") && row != next.getRow() || gameboard[row][next.getCol()].contains("B") && row != next.getRow()){
								valid = false;
								break;
							}
							else{
								valid = true;
							}
						}
					}

					// to move from the lower vertical space, we need to scan from the bottom of the active piece
					else if(activeCoords < nextCoords){
						for(int row = gameStage.getActiveCell().getRow()+1; row <= next.getRow(); row++){
							// if there's a COLORED obstacle and scanning didn't finish until the next tile's column, it's invalid movement
							if(gameboard[row][next.getCol()].contains("W") && row != next.getRow() || gameboard[row][next.getCol()].contains("B") && row != next.getRow()){
								valid = false;
								break;
							}
							else{
								valid = true;
							}
						}
					}
				}

				else valid = false;
				break;
			case Element.B_ROOK_TYPE:
				// horizontal rook movement
				// need to know if there's a clear horizontal path between the active piece and next piece
				// first, we need to know if the rook and next tile is on the same horizontal space / row
				// then, we need to know if there's no SAME COLORED obstacle on that space
				// lastly, rook placement only works on OPPOSITE COLORS or EMPTY tile
				

				if(gameStage.getActiveCell().getRow() == next.getRow() && (gameStage.getActiveCell().getColor() != next.getColor())){
					
					// to move from the left horizontal space, we need to scan from the left of the active piece
					if(activeCoords > nextCoords){
						for(int col = gameStage.getActiveCell().getCol()-1; col >= next.getCol(); col--){
							// if there's a COLORED obstacle and scanning didn't finish until the next tile's column, it's invalid movement
							if(gameboard[next.getRow()][col].contains("W") && col != next.getCol() || gameboard[next.getRow()][col].contains("B") && col != next.getCol()){
								valid = false;
								break;
							}
							else{
								valid = true;
							}
						}
					}

					// to move from the right horizontal space, we need to scand from the right of the active piece
					else if(activeCoords < nextCoords){
						for(int col = gameStage.getActiveCell().getCol()+1; col <= next.getCol(); col++){
							// if there's a COLORED obstacle and scanning didn't finish until the next tile's column, it's invalid movement
							if(gameboard[next.getRow()][col].contains("W") && col != next.getCol() || gameboard[next.getRow()][col].contains("B") && col != next.getCol()){
								valid = false;
								break;
							}
							else{
								valid = true;
							}
						}
					}
				}

				// vertical movement of the rook
				else if(gameStage.getActiveCell().getCol() == next.getCol() && (gameStage.getActiveCell().getColor() != next.getColor())){
					// to move from the upper vertical space, we need to scan from the upper of the active piece
					if(activeCoords > nextCoords){
						for(int row = gameStage.getActiveCell().getRow()-1; row >= next.getRow(); row--){
							// if there's a COLORED obstacle and scanning didn't finish until the next tile's column, it's invalid movement
							if(gameboard[row][next.getCol()].contains("W") && row != next.getRow() || gameboard[row][next.getCol()].contains("B") && row != next.getRow()){
								valid = false;
								break;
							}
							else{
								valid = true;
							}
						}
					}

					// to move from the lower vertical space, we need to scan from the bottom of the active piece
					else if(activeCoords < nextCoords){
						for(int row = gameStage.getActiveCell().getRow()+1; row <= next.getRow(); row++){
							// if there's a COLORED obstacle and scanning didn't finish until the next tile's column, it's invalid movement
							if(gameboard[row][next.getCol()].contains("W") && row != next.getRow() || gameboard[row][next.getCol()].contains("B") && row != next.getRow()){
								valid = false;
								break;
							}
							else{
								valid = true;
							}
						}
					}
				}

				else valid = false;
				break;
		}
		return valid;
	}
}
