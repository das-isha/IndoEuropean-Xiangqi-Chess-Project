package newChess;
import newChess.ChessGame.Side;

/**
 * @author Isha Das
 *this is a elephant piece class of the Xiangqi game
 */
public class ElephantPiece extends XiangqiPiece {


	public ElephantPiece(ChessBoard chessBoard, Side side) {
		this.setLabel("E");
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
			if( isLegalElephantMove(row,column) ) 
			{
				this.movePiece(row, column);
				legalMove = true;
			}
		}
		return legalMove;
	}

	@Override
	public boolean isLegalCaptureMove(int row, int column) {
		// Boolean variable to store if the move to the new row and column is legal
		boolean legalMove = false;

		//Create a ChessBoard and assign is the ChessBoard attached to the Piece
		ChessBoard cb = this.getChessBoard();


		if(cb.hasPiece(row, column))
		{
			if( isLegalElephantMove(row,column) ) 
			{
				cb.removePiece(row, column);
				this.movePiece(row, column);
				legalMove = true;
			}
		}

		return legalMove;
	}

	/**
	 * This is a method that overrides the isHorizontal method in ChessPiece to move two squares
	 * @param row is the row that the piece is moved to
	 * @param column is the column that the piece is moved to
	 * @return boolean that tells us if it was a horizontal move of two squares
	 */
	@Override
	public boolean isHorizontal(int row, int column){
		//performs a check to see if piece is moved horizontally by two blocks
		if((row - this.getRow()) == 2 || (row - this.getRow()) == -2){
			return true;
		}
		else{
			return false;
		}
	}

	/**
	 * This is a method that overrides the isVertical method in ChessPiece to move two squares
	 * @param row is the row that the piece is moved to
	 * @param column is the column that the piece is moved to
	 * @return boolean that tells us if it was a hvertical move of two squares
	 */
	public boolean isVertical(int row, int column){
		//checks if piece is moved vertically by 2 squares//
		if((column - this.getColumn()) == 2 || (column - this.getColumn()) == -2){
			return true;
		}
		else{
			return false;
		}
	}

	/**
	 * This is a checks if there is no piece diagonal to it 
	 * @param row is the row that the piece is moved to
	 * @param column is the column that the piece is moved to
	 * @return boolean that tells us if it is clear to move over
	 */
	public boolean diagonalPiece(int row, int column){
		//variable that stores whether the north right diagonal is clear//
		boolean northRight = true;
		//variable that stores whether the north left diagonal is clear//
		boolean northLeft = true;
		//variable that stores whether the south right diagonal is clear//
		boolean southRight = true;
		//variable that stores whether the south left diagonal is clear//
		boolean southLeft = true;

		//perform a check on the top right diagonal
		if(this.getSide() == Side.NORTH){
			if(column > this.getColumn()){
				if((this.getChessBoard().getPiece(row - 1, column - 1) == null) && (this.getChessBoard().getPiece(row +1, column - 1) == null)){
					northRight = true;
				}
				else{
					northRight = false;
				}
			}
			//perform a check on the bottom right diagonal
			else{
				if((this.getChessBoard().getPiece(row +1, column +1) == null) && (this.getChessBoard().getPiece(row -1, column+1) == null)){
					northLeft = true;
				}
				else{
					northLeft = false;
				}
			}

			//perform a check for if both diagonals on the upper side are clear..
			if(northRight == true && northLeft == true){
				return true;
			}
			else{
				return false;
			}
		}
		else{
			//perform a check for the bottom right diagonal
			if(column > this.getColumn()){
				if((this.getChessBoard().getPiece(row +1, column - 1) == null) && (this.getChessBoard().getPiece(row - 1, column - 1) == null)){
					southRight = true;
				}
				else{
					southRight = false;
				}
			}
			//perform a check for the left right diagonal
			else{
				if((this.getChessBoard().getPiece(row + 1, column + 1) == null) && (this.getChessBoard().getPiece(row -1, column + 1) == null)){
					southLeft = true;
				}
				else{
					southLeft = false;
				}
			}

			//performs a check to see if both the diagonals on the lower side are empty
			if(southRight == true && southLeft == true){
				return true;
			}
			else{
				return false;
			}
		}
	}


	/**
	 *  Checks if it is legal move or not
	 *  @param row  the row of the piece
	 *  @param col  the column of the piece
	 *  @return  true if it is a legal move
	 */
	public boolean isLegalElephantMove(int row, int column) {

		boolean legalMove = false;
		//Create a ChessBoard and assign is the ChessBoard attached to the Piece

		//checks if diagonal is clear//
		if(diagonalPiece(row,column)){
			if(getSide() == Side.NORTH){
				//checks if piece is within bounds//
				if(row > 4){
					legalMove = false;
				}
				else{
					//checks if it is a legal move for the piece//
					if(isVertical(row,column) && isHorizontal(row,column)){
						legalMove = true;
					}
					else{
						legalMove = false;
					}
				}
			}
			else{
				//perfroms a check to ensure that the row is less than five and within the bounds
				if(row < 5){
					legalMove = false;
				}
				else{
					if(isVertical(row,column) && isHorizontal(row,column)){
						legalMove = true;
					}
					else{
						legalMove = false;
					}
				}
			}
		}
		else{
			legalMove = false;
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