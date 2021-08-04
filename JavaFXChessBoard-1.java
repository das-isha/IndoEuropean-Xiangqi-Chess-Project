package newChess;
import java.util.List;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;


/**
 * @author Isha Das
 *this is a javafxchess board class that implements the chessboard interface
 */
public class JavaFXChessBoard extends Application implements ChessBoard {
	private GridPane pane;
	private Button[][] squares;                   		// the squares of the board
	private ChessPiece[][] pieces;                 		// stores the pieces
	private ChessGame gameRules;                   		// global rules for this particular game
	private JavaFXChessBoardDisplay fxBoardDisplay;     // rules for how to draw the chess board
	private int numRows = 0;
	private int numColumns = 0;
	

	/**
	 * Returns the rules of the game.
	 * @return the rules of the game
	 */
	@Override
	public ChessGame getGameRules() {
		return gameRules;
	}

	/**
	 * Changes the rules of the game
	 * @param newRules the new rules for the game
	 */
	public void setGameRules(ChessGame newRules) {
		this.gameRules = newRules;
	}
	
	/**
	 *  Adds a piece to the board at the desired location.  Any piece currently
	 *  at that location is lost.
	 *  @param piece   the piece to add
	 *  @param row     the row for the piece
	 *  @param column  the column for the piece
	 */
	@Override
	public void addPiece(ChessPiece piece, int row, int column) {
		// set the piece on the board, tell the piece where it is, and then use the display rules to display the square
		pieces[row][column] = piece;
		piece.setLocation(row, column);

		fxBoardDisplay.displayFilledSquare(squares[row][column], row, column, piece);
	}
	
	/**
	 *  Removes a piece from the board
	 *  @param row  	the row of the piece
	 *  @param column  	the column of the piece
	 *  @return  the piece removed of null if there was no piece at that square
	 */
	@Override
	public ChessPiece removePiece(int row, int column) {
		// remove the piece from the board, use the display rules to show an empty square
		ChessPiece save = pieces[row][column];
		pieces[row][column] = null;

		fxBoardDisplay.displayEmptySquare(squares[row][column], row, column);

		return save;
	}

	/**
	 *  Returns true if there is a piece at a specific location of the board.
	 *  @param row   	the row to examine
	 *  @param column   the column to examine
	 *  @return   true if there is a piece a this row and column and false
	 *            if the square is empty
	 */
	@Override
	public boolean hasPiece(int row, int column) {
		return (pieces[row][column] != null);
	}

	/**
	 *  Returns the chess piece at a specific location on the board.
	 *  @param row   	the row for the piece
	 *  @param column   the column for the piece
	 *  @return      the piece at the row and column or null if there is no piece there.
	 */
	@Override
	public ChessPiece getPiece(int row, int column) {
		return pieces[row][column];
	}

	/**
	 * Returns true if a particular square is threatened by an opposing piece.
	 * @param row     the row of the square
	 * @param column  the column of the square
	 * @param piece   a piece of the game
	 * @return  true if the square can be attacked by a piece of an opposing side as the parameter piece
	 */
	@Override
	public boolean squareThreatened(int row, int column, ChessPiece piece) {
		for (int i = 0; i < squares.length; i++) {
			for (int j = 0; j < squares[i].length; j++) {
				if (hasPiece(i,j) && getPiece(i, j).getSide() != piece.getSide() &&
						getPiece(i, j).isLegalCaptureMove(row, column))
					return true;
			}
		}
		return false;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		//read the arguments using application parameter
		Application.Parameters parameters = getParameters();
		List<String> rawParams = parameters.getRaw();
		String gameText = rawParams.get(0);
		
		// Create appropriate display board and game rules based on the first argument
		if(gameText.equals("chess")) {
			fxBoardDisplay = new JavaFXEuropeanChessDisplay();
			gameRules = new EuropeanChess();
			primaryStage.setTitle("European Chess Board");
		}
		else if(gameText.equals("xiangqi")) {
			fxBoardDisplay = new JavaFXXiangqiDisplay();
			gameRules = new Xiangqi();
			primaryStage.setTitle("Xiangqi Board");
		}
		else 
			throw new IllegalArgumentException("Invalid first argument (valid values are chess or xiangqi not as typed " + gameText + " )");

		      
		numRows = gameRules.getNumRows();
		numColumns = gameRules.getNumColumns();
		
		pieces = new ChessPiece[numRows][numColumns];
		squares = new Button[numRows][numColumns];
		
		pane = new GridPane();
		
		//Setting size for the pane  
		pane.setMinSize(fxBoardDisplay.getSquareSize() * numColumns, fxBoardDisplay.getSquareSize() * numRows);
		//pane.setBackground(Color.LIGHTGRAY, CornerRadii.EMPTY, new Insets(20));

		//Set the GidPane so that node will fill to height and width 
        for (int row = 0 ; row < numRows ; row++ ){
            RowConstraints rc = new RowConstraints();
            rc.setFillHeight(true);
            rc.setVgrow(Priority.ALWAYS);
            pane.getRowConstraints().add(rc);
        }
        for (int col = 0 ; col < numColumns; col++ ) {
            ColumnConstraints cc = new ColumnConstraints();
            cc.setFillWidth(true);
            cc.setHgrow(Priority.ALWAYS);
            pane.getColumnConstraints().add(cc);
        }
        
        EventHandler<ActionEvent> buttonHandler = new EventHandler<ActionEvent>() {
    		private boolean firstPick = true;  // if true, we a selecting a piece
    		private int pieceRow;              // remember row of selected piece
    		private int pieceCol;              // remember column of selected piece

    		/** 
    		 * What we do when the user chooses the piece to move.
    		 * @param row the row of the chosen piece
    		 * @param col the column of the chosen piece
    		 */
    		private void processFirstSelection(int row, int col) {
    			if ((pieces[row][col] != null) && 
    					(getGameRules() == null || getGameRules().legalPieceToPlay(pieces[row][col], row, col))) {
    				/*
    				 * if this is the first pick and a square with a piece was picked,
    				 * remember the piece's location and highlight the square.
    				 */
    				pieceRow = row;
    				pieceCol = col;
    				fxBoardDisplay.highlightSquare(true, squares[row][col], row, col, pieces[row][col]);
    				firstPick = false;
    			}
    		}

    		/**
    		 * What we do when the user chooses the square to move the piece to.
    		 * @param row the row the piece will move to
    		 * @param col the column the piece will move to
    		 */
    		private void processSecondSelection(int row, int col) {
    			if (row == pieceRow && col == pieceCol)
    				return;

    			boolean moveMade = getGameRules().makeMove(pieces[pieceRow][pieceCol], row, col);

    			// if the move was made or if it was not made and the user select a new piece, then reset to choose a new move
    			if (moveMade || getGameRules().canChangeSelection(pieces[pieceRow][pieceCol], pieceRow, pieceCol)) {
    				fxBoardDisplay.highlightSquare(false, squares[pieceRow][pieceCol], pieceRow, pieceCol, pieces[pieceRow][pieceCol]);
    				firstPick = true;
    			}
    		}

        	/**
    		 *  Handle a button click.  The method alternates between selecting a piece
    		 *  and selecting any square.  After both are selected, the piece's 
    		 *  legalMove is called, and if the move is legal, the piece is moved.
    		 *  @param e   the event that triggered the method
    		 */
        	@Override
            public void handle(ActionEvent event) {
    			Button b = (Button)event.getSource();
    			int col = -1;
    			int row = -1;

    			// first find which button (board square) was clicked.
    			for (int i = 0; i < squares.length; i++) {
    				for (int j = 0; j < squares[i].length; j++) {
    					if (squares[i][j] == b) {
    						row = i;
    						col = j;
    					}
    				}
    			}

    			if (firstPick) {
    				processFirstSelection(row, col);
    			}
    			else {
    				processSecondSelection(row, col);
    			}
            }
            
            
        };
        
		// create the squares
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numColumns; j++) {
				squares[i][j] = new Button();
				squares[i][j].setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
				squares[i][j].setOnAction(buttonHandler);
				fxBoardDisplay.displayEmptySquare(squares[i][j], i, j);
				pane.add(squares[i][j],j,i);
				pieces[i][j] = null;
			}
		}
		
		

		gameRules.startGame(this);
		
		//create the scene, place it in primary stage and show the stage
		Scene scene = new Scene(pane);
		primaryStage.setScene(scene);
		primaryStage.show();

	}
	
	//launches the application
	public void launchApp(String[] args) {
		Application.launch(args);
	}
}
