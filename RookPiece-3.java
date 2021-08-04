package newChess;

import newChess.ChessGame.Side;

/**
 * @author Isha Das
 *this is a rook piece class of the European chess game and Xiangqi 
 */
public class RookPiece extends EuropeanChessPiece {


	// Flag to track if the piece has moved
	private boolean firstMove = true;


	/*
	 * This is the RookPiece chess piece constructor
	 */
	public RookPiece(ChessBoard chessBoard, Side side) {
		this.setLabel("R");
		this.setChessBoard(chessBoard);
		this.setSide(side);
	}

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
			if(this.isLegalVerticalNonCaptureMove(row, column, true, 0) || 
					this.isLegalHorizontalNonCaptureMove(row, column, true, 0))
			{
				legalMove = true;
				this.moveDone();
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
			//this gets the side and checks to see whether it is the alteranating option through all the combinations possible
			if( (this.getSide() == ChessGame.Side.NORTH || this.getSide() == ChessGame.Side.EAST) 
					&& (cp.getSide() == ChessGame.Side.SOUTH || cp.getSide() == ChessGame.Side.WEST) 
					|| (this.getSide() == ChessGame.Side.SOUTH || this.getSide() == ChessGame.Side.WEST)
					&& (cp.getSide() == ChessGame.Side.NORTH || cp.getSide() == ChessGame.Side.EAST) )
			{
				if( (this.getRow() == row) && (this.getColumn() > column) 
						&& this.isLegalHorizontalNonCaptureMove(row, column + 1 , true, 0) )
				{
					cb.removePiece(row, column);
					this.movePiece(row, column);
					legalMove = true;
					this.moveDone();
				}
				else if( (this.getRow() == row) && (this.getColumn() < column) 
						&& this.isLegalHorizontalNonCaptureMove(row, column - 1 , true, 0) )
				{
					cb.removePiece(row, column);
					this.movePiece(row, column);
					legalMove = true;
					this.moveDone();
				}
				else if( (this.getRow() < row) && (this.getColumn() == column) 
						&& this.isLegalVerticalNonCaptureMove(row - 1, column, true, 0) )
				{
					cb.removePiece(row, column);
					this.movePiece(row, column);
					legalMove = true;
					this.moveDone();
				}
				else if( (this.getRow() > row) && (this.getColumn() == column) 
						&& this.isLegalVerticalNonCaptureMove(row + 1, column, true, 0) )
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
		// Set the firstMove flag to false after a move
		this.firstMove = false;
	}

}
