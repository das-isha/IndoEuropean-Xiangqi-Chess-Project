package newChess;
import newChess.ChessGame.Side;

/**
 * @author Isha Das
 *this is a horse piece class of the Xiangqi game
 */
public class HorsePiece extends XiangqiPiece {

	/*
	 * This is the HorsePiece chess piece constructor
	 */
	public HorsePiece(ChessBoard chessBoard, Side side) {
		this.setLabel("H");
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
			if( isLegalHorseMove(row,column) ) 
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
			if( isLegalHorseMove(row,column) ) 
			{
				cb.removePiece(row, column);
				this.movePiece(row, column);
				legalMove = true;
			}
		}

		return legalMove;
	}
	/**
	 * This method checks if movement is in a legal L shape
	 * @param row is the row the piece is to be moved to 
	 * @param column is the column the piece is to be moved to
	 * @return a boolean representing if the move is legal
	 */
	public boolean isLegalLMove(int row, int column){
		//checks for all possible L combinations//
		if((column - getColumn() == 1) && (row - getRow() == 2)){
			return true;
		}
		else if(column - getColumn() == 1 && row - getRow() == -2){
			return true;
		}

		else if((column - getColumn() == -1) && ((row - getRow() == 2))){
			return true;
		}
		else if((column - getColumn() == -1) && ((row - getRow() == -2))){
			return true;
		}

		else if((column - getColumn() == 2) && ((row - getRow() == -1))){
			return true;
		}

		else if((column - getColumn() == 2) && ((row - getRow() == 1))){
			return true;
		}

		else if((column - getColumn() == -2) && ((row - getRow() == -1))){ 
			return true;
		}
		else if((column - getColumn() == -2) && ((row - getRow() == 1))){ 
			return true;
		}
		else{
			return false;
		}
	}

	/**
	 * This checks if Knight has any pieces in its path
	 * @param row is the row the piece is to be moved to 
	 * @param column is the column the piece is to be moved to
	 * @return a boolean representing if the path is clear
	 */
	public boolean clearPath(int row, int column){

		//checks the path for all possible movements//
		if(column - getColumn() == 2){
			if((getChessBoard().getPiece(getRow(), getColumn() + 1)) == null){
				return true;
			}
			else{
				return false;
			}  
		}

		else if(column - getColumn() == -2){
			if((getChessBoard().getPiece(getRow(), getColumn() - 1)) == null){
				return true;
			}
			else{
				return false;
			}  
		}

		else if( row - getRow() == 2){
			if((getChessBoard().getPiece(getRow() + 1, getColumn()) == null)) {
				return true;
			}
			else{
				return false;
			}
		}
		else if(row - getRow() == -2){
			if((getChessBoard().getPiece(getRow() - 1, getColumn()) == null)) {
				return true;
			}
			else{
				return false;
			}
		}
		else{
			return false;
		}
	}
	/**
	 *  Checks if it is legal move or not
	 *  @param row  the row of the piece
	 *  @param col  the column of the piece
	 *  @return  true if it is a legal move
	 */
	public boolean isLegalHorseMove(int row, int column) {

		boolean legalMove = false;
		//checks if L shape move//
		if(isLegalLMove(row, column)){
			//checks if path is clear//
			if(clearPath(row,column)){
				legalMove = true;
			}
			else{
				legalMove = false;
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
