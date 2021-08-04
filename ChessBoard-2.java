package newChess;

/**
 * @author Isha Das
 * This class is an interface represneting chessboard 
 */
public interface ChessBoard {

	public ChessGame getGameRules();

	public void addPiece(ChessPiece piece, int row, int column);

	public ChessPiece removePiece(int row, int column);

	public boolean hasPiece(int row, int column);

	public ChessPiece getPiece(int row, int column);

	public boolean squareThreatened(int row, int column, ChessPiece piece);

}
