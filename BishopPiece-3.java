package newChess;
import newChess.ChessGame.Side;
/**
 * @author Isha Das
 *
 */


//the bishop piece is able to move any number of spaces in a straight diagonal and inherits european chess piece
public class BishopPiece extends EuropeanChessPiece{
	

	public BishopPiece(ChessBoard chessBoard, Side side) {
		this.setLabel("B");
		this.setChessBoard(chessBoard);
		this.setSide(side);
	}
	//checks to see whether it is legal to move this piece from its current position to the indicated position
	@Override
	public boolean isLegalNonCaptureMove(int row, int column) {
		// Boolean variable to store if the move to the new row and column is lagal
		boolean legalMove = false;
		
		//Create a ChessBoard and assign is the ChessBoard attached to the Piece
		ChessBoard cb = this.getChessBoard();
		
		if(cb.hasPiece(row, column))
		{
			legalMove = false;
		}
		else
		{
			if(this.isLegalDiagonalNonCaptureMove(row, column, true, 0))
			{
				legalMove = true;
				this.moveDone();
			}
		}
		
		return legalMove;		
	}
	//check to see if the capture is legal or not
	@Override
	public boolean isLegalCaptureMove(int row, int column) {
		// Boolean variable to store if the move to the new row and column is lagal
		boolean legalMove = false;
		
		//Create a ChessBoard and assign is the ChessBoard attached to the Piece
		ChessBoard cb = this.getChessBoard();
		
	
		if(cb.hasPiece(row, column))
		{
			ChessPiece cp = cb.getPiece(row, column);

			if( (this.getSide() == ChessGame.Side.NORTH || this.getSide() == ChessGame.Side.EAST) 
					&& (cp.getSide() == ChessGame.Side.SOUTH || cp.getSide() == ChessGame.Side.WEST) 
					|| (this.getSide() == ChessGame.Side.SOUTH || this.getSide() == ChessGame.Side.WEST)
					&& (cp.getSide() == ChessGame.Side.NORTH || cp.getSide() == ChessGame.Side.EAST) )
			{
				if( (this.getRow() > row) && (this.getColumn() > column) 
						&& this.isLegalDiagonalNonCaptureMove(row + 1, column + 1 , true, 0) )
				{
					cb.removePiece(row, column);
					this.movePiece(row, column);
					legalMove = true;
					this.moveDone();
				}
				else if( (this.getRow() > row) && (this.getColumn() < column) 
						&& this.isLegalDiagonalNonCaptureMove(row + 1, column - 1 , true, 0) )
				{
					cb.removePiece(row, column);
					this.movePiece(row, column);
					legalMove = true;
					this.moveDone();
				}
				else if( (this.getRow() < row) && (this.getColumn() < column) 
						&& this.isLegalDiagonalNonCaptureMove(row - 1, column -1, true, 0) )
				{
					cb.removePiece(row, column);
					this.movePiece(row, column);
					legalMove = true;
					this.moveDone();
				}
				else if( (this.getRow() < row) && (this.getColumn() > column) 
						&& this.isLegalDiagonalNonCaptureMove(row - 1, column + 1, true, 0) )
				{
					cb.removePiece(row, column);
					this.movePiece(row, column);
					legalMove = true;
					this.moveDone();
				}
			}
		}
	
		return legalMove;
	}

	@Override
	public void moveDone() {
		// Do nothing
		
	}

}
