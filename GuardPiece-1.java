package newChess;
import newChess.ChessGame.Side;

/**
 * @author Isha Das
 *this is a guard piece class of the Xiangqi game
 */
public class GuardPiece extends XiangqiPiece {

	/*
	 * This is the Guard Piece chess piece constructor
	 */
	public GuardPiece(ChessBoard chessBoard, Side side) {
		this.setLabel("G");
		this.setChessBoard(chessBoard);
		this.setSide(side);
	}
	//this is checking if the guard made a legal move not involving a capture
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
			if( isLegalGuardMove(row,column) ) 
			{
				this.movePiece(row, column);
				legalMove = true;
			}
		}
		return legalMove;
	}
	//this is checking if the guard made a legal move involving a capture
	@Override
	public boolean isLegalCaptureMove(int row, int column) {
		// Boolean variable to store if the move to the new row and column is lagal
		boolean legalMove = false;

		//Create a ChessBoard and assign is the ChessBoard attached to the Piece
		ChessBoard cb = this.getChessBoard();


		if(cb.hasPiece(row, column))
		{
			if( isLegalGuardMove(row,column) ) 
			{
				cb.removePiece(row, column);
				this.movePiece(row, column);
				legalMove = true;
			}
		}

		return legalMove;
	}


	/**
	 *  Checks if it is legal move or not
	 *  @param row  the row of the piece
	 *  @param col  the column of the piece
	 *  @return  true if it is a legal move
	 */
	private boolean isLegalGuardMove(int row, int column) {

		boolean legalMove = false;
		//Create a ChessBoard and assign is the ChessBoard attached to the Piece

		if(getSide() == Side.NORTH){
			//makes sure guard is within palace
			if(column < 3 || column > 5 || row >2){
				legalMove = false;
			}
			else{
				//checks if a diagonal move is made
				if((isVertical(row,column)) && (isHorizontal(row,column))){
					legalMove = true;
				}
				else{
					legalMove = false;
				}
			}
		}
		else{
			if(column < 3 || column > 5 || row < 7){
				legalMove = false;
			}
			else{
				if((isVertical(row,column)) && (isHorizontal(row,column))){
					legalMove = true;
				}
				else{
					legalMove = false;
				}
			}
		}



		return legalMove;
	}

	/**
	 *This method that does nothing but represents that the move was successfully completed
	 */
	@Override
	public void moveDone() {
		// TODO Auto-generated method stub

	}



}