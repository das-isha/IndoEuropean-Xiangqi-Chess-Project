package newChess;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.geometry.Insets;

/**
 * @author Isha Das
 *this is a fx xiangqi display that implements the fx chess board display interface
 */
public class JavaFXXiangqiDisplay implements JavaFXChessBoardDisplay {
	
	/** The primary color of the checkerboard */
	public static Color lightGreyColor = Color.LIGHTGRAY;
	
	/** The secondary color of the checkerboard */
	public static Color darkGreyColor = Color.GRAY;

	/* The color of the SOUTH player */
	public static Color southPlayerColor = Color.YELLOW;

	/* The color of the NORTH player */
	public static Color northPlayerColor = Color.LIGHTGREEN;

	/* The color of the EAST player */
	public static Color eastPlayerColor = Color.WHITE;
	
	/* The color of the WEST player */
	public static Color westPlayerColor = Color.SALMON;

	/** The color used to highlight a square */
	public static String highlightColor = "-fx-background-color: blue";

	/**
	 * Display a square that has no piece on it.  Will produce a red/black checkerboard.
	 * @param button the button that is used for the chessboard square
	 * @param row    the row of this square on the board
	 * @param column the column of this square on the board
	 */
	@Override
	public void displayEmptySquare(Button button, int row, int column) {
		Color pieceColor = lightGreyColor;
		if (row < 3 || row > 6) {
			if(column < 3 || column > 5) {
				pieceColor = lightGreyColor;
			}
			else {
				pieceColor = darkGreyColor;
			}
		} else {
			pieceColor = lightGreyColor;
		}
			
		button.setBackground(new Background(
				new BackgroundFill(pieceColor, CornerRadii.EMPTY, new Insets(1,1,1,1))));
		button.setText(null);
	}

	/**
	 * Display a square that has a piece on it.
	 * @param button the button that is used for the chessboard square
	 * @param row    the row of this square on the board
	 * @param column the column of this square on the board
	 * @param piece  the piece that is on this square
	 */
	@Override
	public void displayFilledSquare(Button button, int row, int column, ChessPiece piece) {
		Color pieceColor;

		switch (piece.getSide()) {
		case NORTH:   pieceColor = northPlayerColor;
		break;
		case SOUTH:   pieceColor = southPlayerColor;
		break;
		case EAST:    pieceColor = eastPlayerColor;
		break;
		default:      pieceColor = westPlayerColor;
		}

		button.setBackground(new Background(
				new BackgroundFill(pieceColor, new CornerRadii(10), new Insets(7,7,7,7))));

		button.setText(piece.getLabel());
		button.setStyle("-fx-font-weight: bold");
	}

	/**
	 * Highlight a square of the board.
	 * @param highlight  do we want the highlight on (true) or off (false)?
	 * @param button     the button that is used for the chessboard square
	 * @param row        the row of this square on the board
	 * @param column     the column of this square on the board
	 * @param piece      the piece (if any) that is on this square
	 */
	@Override
	public void highlightSquare(boolean highlight, Button button, int row, int column, ChessPiece piece) {
		if (highlight) {
			button.setStyle(highlightColor);
		}
		else if (piece == null)
			displayEmptySquare(button, row, column);
		else
			displayFilledSquare(button, row, column, piece);
	}
}
