package chess;
/***********************************************************
* This is a Java program that simulates a Chess application
*
* @author Mark Genesis C. Topinio
* @created_date 2022-07-23 21:30
*
***********************************************************/

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application{

    public static void main(String[] args) {
		launch(args);
	}

    public void start(Stage stage){
        // Starting the Chess App
        GameBoardStage chessboard = new GameBoardStage();
        chessboard.setStage(stage);
	}
}
