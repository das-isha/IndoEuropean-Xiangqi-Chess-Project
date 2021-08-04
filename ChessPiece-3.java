package newChess;
import javax.swing.Icon;

/**
 * @author Isha Das
 * this is an interface representing a chess piece
 */
public interface ChessPiece {

	public ChessGame.Side getSide();

	public String getLabel();

	public Icon getIcon();

	public void setLocation(int row, int col);


	public boolean isLegalCaptureMove(int toRow, int toColumn);


	public boolean isLegalMove(int toRow, int toColumn);
}
