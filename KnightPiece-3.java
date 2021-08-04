package newChess;
import newChess.ChessGame.Side;

/**
 * @author Isha Das
 *this is a knight piece class of the European game
 */
public class KnightPiece extends EuropeanChessPiece {
	

	public KnightPiece(ChessBoard chessBoard, Side side) {
		this.setLabel("N");
		this.setChessBoard(chessBoard);
		this.setSide(side);
	}

	@Override
	public boolean isLegalNonCaptureMove(int row, int column) {
		// TODO Auto-generated method stub
		boolean legalMove = false;
		
		ChessBoard cb = this.getChessBoard();
		
		if(cb.hasPiece(row, column))
		{
			legalMove = false;
		}
		else
		{
			if( (Math.abs(this.getRow() - row) == 1  && Math.abs(this.getColumn() - column) == 2) 
					|| (Math.abs(this.getRow() - row) == 2 && Math.abs(this.getColumn() - column) == 1) ) 
			{
				this.movePiece(row, column);
				legalMove = true;
			}
			
		}
		return legalMove;
	}

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
				if( (Math.abs(this.getRow() - row) == 1  && Math.abs(this.getColumn() - column) == 2) 
						|| (Math.abs(this.getRow() - row) == 2 && Math.abs(this.getColumn() - column) == 1) ) 
				{
					cb.removePiece(row, column);
					this.movePiece(row, column);
					legalMove = true;
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
