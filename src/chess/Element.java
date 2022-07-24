package chess;

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

    // The images are stored as constant values
    public final static int IMAGE_SIZE = 60;

    //Black Chess Pieces
    public final static Image B_KING_IMAGE = new Image("images/black_king.png",GameBoardStage.CELL_WIDTH,GameBoardStage.CELL_WIDTH,false,false);
    public final static Image B_QUEEN_IMAGE = new Image("images/black_queen.png",GameBoardStage.CELL_WIDTH,GameBoardStage.CELL_WIDTH,false,false);
    public final static Image B_ROOK_IMAGE = new Image("images/black_rook.png",GameBoardStage.CELL_WIDTH,GameBoardStage.CELL_WIDTH,false,false);
    public final static Image B_BISHOP_IMAGE = new Image("images/black_bishop.png",GameBoardStage.CELL_WIDTH,GameBoardStage.CELL_WIDTH,false,false);
    public final static Image B_KNIGHT_IMAGE = new Image("images/black_knight.png",GameBoardStage.CELL_WIDTH,GameBoardStage.CELL_WIDTH,false,false);
    public final static Image B_PAWN_IMAGE = new Image("images/black_pawn.png",GameBoardStage.CELL_WIDTH,GameBoardStage.CELL_WIDTH,false,false);

    //White Chess Pieces
    public final static Image W_KING_IMAGE = new Image("images/white_king.png",GameBoardStage.CELL_WIDTH,GameBoardStage.CELL_WIDTH,false,false);
    public final static Image W_QUEEN_IMAGE = new Image("images/white_queen.png",GameBoardStage.CELL_WIDTH,GameBoardStage.CELL_WIDTH,false,false);
    public final static Image W_ROOK_IMAGE = new Image("images/white_rook.png",GameBoardStage.CELL_WIDTH,GameBoardStage.CELL_WIDTH,false,false);
    public final static Image W_BISHOP_IMAGE = new Image("images/white_bishop.png",GameBoardStage.CELL_WIDTH,GameBoardStage.CELL_WIDTH,false,false);
    public final static Image W_KNIGHT_IMAGE = new Image("images/white_knight.png",GameBoardStage.CELL_WIDTH,GameBoardStage.CELL_WIDTH,false,false);
    public final static Image W_PAWN_IMAGE = new Image("images/white_pawn.png",GameBoardStage.CELL_WIDTH,GameBoardStage.CELL_WIDTH,false,false);

    public final static String B_KING_TYPE = "b_king";
    public final static String B_QUEEN_TYPE = "b_queen";
    public final static String B_ROOK_TYPE = "b_rook";
    public final static String B_BISHOP_TYPE = "b_bishop";
    public final static String B_KNIGHT_TYPE = "b_knight";
    public final static String B_PAWN_TYPE = "b_pawn";

    public final static String W_KING_TYPE = "w_king";
    public final static String W_QUEEN_TYPE = "w_queen";
    public final static String W_ROOK_TYPE = "w_rook";
    public final static String W_BISHOP_TYPE = "w_bishop";
    public final static String W_KNIGHT_TYPE = "w_knight";
    public final static String W_PAWN_TYPE = "w_pawn";

	public Element(String type, GameBoardStage gameStage) { // Note: an ELEMENT has an imgView (to set up an Image)
		this.type = type;
		this.gameStage = gameStage;

		// load image depending on the type
		switch(this.type) { // This determines which picture to use, depending on the element to be added on the layout
			case Element.B_KING_TYPE: this.img = Element.B_KING_IMAGE; break;
            case Element.B_QUEEN_TYPE: this.img = Element.B_QUEEN_IMAGE; break;
            case Element.B_ROOK_TYPE: this.img = Element.B_ROOK_IMAGE; break;
            case Element.B_BISHOP_TYPE: this.img = Element.B_BISHOP_IMAGE; break;
            case Element.B_KNIGHT_TYPE: this.img = Element.B_KNIGHT_IMAGE; break;
            case Element.B_PAWN_TYPE: this.img = Element.B_PAWN_IMAGE; break;

            case Element.W_KING_TYPE: this.img = Element.W_KING_IMAGE; break;
            case Element.W_QUEEN_TYPE: this.img = Element.W_QUEEN_IMAGE; break;
            case Element.W_ROOK_TYPE: this.img = Element.W_ROOK_IMAGE; break;
            case Element.W_BISHOP_TYPE: this.img = Element.W_BISHOP_IMAGE; break;
            case Element.W_KNIGHT_TYPE: this.img = Element.W_KNIGHT_IMAGE; break;
            case Element.W_PAWN_TYPE: this.img = Element.W_PAWN_IMAGE; break;

		}

		// this.setImageView();
		// this.setMouseHandler();
	}

    protected void loadImage(String filename,int width, int height){
		try{
			this.img = new Image(filename,width,height,false,false);
		} catch(Exception e){}
	}


	String getType(){
		return this.type;
	}

	int getRow() {
		return this.row;
	}

	int getCol() {
		return this.col;
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

	// private void setMouseHandler(){
	// 	Element element = this; // Notice that no parameters, but we still got the element
	// 	this.imgView.setOnMouseClicked(new EventHandler<MouseEvent>(){ //Notice that the ImageView element is the one that interacts
	// 		public void handle(MouseEvent e) {
    //             switch(element.getType()) {
	//                 case Element.FLAG_TYPE: 		// if flag, set flagClicked to true
	// 							                	System.out.println("FLAG clicked!");
	// 							    	            gameStage.setFlagClicked(true);
	// 							    	            break;
	//                 case Element.FLAG_OFF_TYPE: 	// if flagOff, set flagClicked back to false
	//                 								System.out.println("FLAG OFF clicked!");
	//                 								gameStage.setFlagClicked(false);
	//                 								break;
	//     			case Element.LAND_TYPE:	// Check if the Flag is toggled for that land
	// 		    									System.out.println("LAND clicked!");
	// 							    				if(!gameStage.isFlagClicked()) {
	// 							    					if(!gameStage.isBomb(element)){	// if not a bomb, clear image
	// 							    						int bombCount = gameStage.checkBombs(element);
	// 							    						setImage(element, bombCount);
	// 							    						element.setType(CLEARED_TYPE);
	// 							    						gameStage.setSafeCells(element); // save the cleared cell's index to an array
	// 							    						// also check if the number of safe cells has been reached already
	// 								    					if(GameStage.safeCells.size() == GameStage.MAX_SAFE_CELLS) gameStage.setGameOver(1);
	// 							    					}
	// 							    					else {
	// 							    						changeImage(element,Element.BOMB_IMAGE); // if bomb, change image to bomb
	// 							    						gameStage.setGameOver(0);
	// 							    					}
	// 							    	            } else {
	// 							    	            	changeImage(element,Element.FLAG_IMAGE);	// if flag was clicked before hand, change image to flag
	// 							    	            	element.setType(LAND_FLAG_TYPE);			// change type to landToFlag
	// 							    	            	//gameStage.setFlagClicked(false);	    	// reset flagClicked to false
	// 							    	            	gameStage.checkFlags(element);
	// 							    	            }
	// 							    				break;
	//     			case Element.LAND_FLAG_TYPE:
	// 							    				changeImage(element,Element.LAND_IMAGE);		// if flag is clicked, change image back to land
	// 						    	            	element.setType(LAND_TYPE);
	// 						    	            	gameStage.unCheckFlags(element);				// decrement flagCells if the cell returns back to land
	// 						    	            	break;
	//     			case Element.CLEARED_TYPE:
	//     											// do nothing if cell was cleared
	//     											break;
    //             }
	// 		}	//end of handle()
	// 	});
	// }

	private void clearImage(Element element) {
		imgView.setImage(null);
	}

	private void changeImage(Element element, Image image) {
		this.imgView.setImage(image);

	}
}
