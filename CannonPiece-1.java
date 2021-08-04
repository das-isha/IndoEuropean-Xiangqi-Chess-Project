package newChess;
import newChess.ChessGame.Side;

/**
 * @author Isha Das
 * this is a cannon piece class of the Xiangqi game
 */
public class CannonPiece extends XiangqiPiece {
	/*
	 * This is the CannonPiece chess piece constructor
	 */
	public CannonPiece(ChessBoard chessBoard, Side side) {
		this.setLabel("C");
		this.setChessBoard(chessBoard);
		this.setSide(side);
	}
	/**
	 * This is a checks if it is a legal non capture move for the piece
	 * has the same method as Rook
	 * @param row is the row that the piece is moved to
	 * @param column is the column that the piece is moved to
	 * @return boolean that tells us if it is legal
	 */ 
	public boolean isLegalNonCaptureMove(int row, int column){
		
		int freeSquare = 0;
		boolean legalMove = false; 
		
		if(Math.abs(column - getColumn()) > 0 && Math.abs(row - getRow()) > 0){
			legalMove =  false;
		}
		else{
			if(Math.abs(column - getColumn()) > 0){
				if(column > getColumn()){
					for(int i = getColumn() + 1; i <= column; i++){
						if(getChessBoard().getPiece(getRow(),i) == null){
							freeSquare++;
						}
					}
					if(freeSquare== Math.abs(column - getColumn())){
						legalMove =  true;
					}
					else{
						legalMove =  false;
					}
				}
				else{
					for(int i = getColumn() - 1; i >= column; i--){
						if(getChessBoard().getPiece(getRow(),i) == null){
							freeSquare++;
						}
					}
					if(freeSquare== Math.abs(column - getColumn())){
						legalMove =  true;
					}
					else{
						legalMove =  false;
					}
				}
			}
			else{
				if(row > getRow()){
					for(int i = getRow() + 1; i <= row; i++){
						if(getChessBoard().getPiece(i,getColumn()) == null){
							freeSquare++;
						}
					}
					if(freeSquare== Math.abs(row - getRow())){
						legalMove =  true;
					}
					else{
						legalMove =  false;
					}
				}
				else{
					for(int i = getRow() - 1; i >= row; i--){
						if(getChessBoard().getPiece(i,getColumn()) == null){
							freeSquare++;
						}
					}
					if(freeSquare== Math.abs(row - getRow())){
						legalMove =  true;
					}
					else{
						legalMove =  false;
					}
				}
			}
			
		}
		return legalMove;
	}

	/**
	 * This is a checks if it is a legal capture move for the piece and overrides the default
	 * @param row is the row that the piece is moved to
	 * @param column is the column that the piece is moved to
	 * @return boolean that tells us if it is legal
	 */ 
	@Override  
	public boolean isLegalCaptureMove(int row, int column){
		
		int occupiedSquare = 0; 
		boolean legalMove = false; 

		if(this.getSide() == (this.getChessBoard().getPiece(row,column)).getSide()){
			legalMove = false;
		}
		else{
			if(Math.abs(column - getColumn()) > 0 && Math.abs(row - getRow()) > 0){
				legalMove = false;
			}
			else{
				if(Math.abs(column - getColumn()) > 0){
					if(column > getColumn()){
						for(int i = getColumn() + 1; i < column; i++){
							if(getChessBoard().getPiece(getRow(),i) != null){
								occupiedSquare += 1;
							}
						}
						if(occupiedSquare == 1){
							legalMove = true;
						}
						else{
							legalMove = false;
						}
					}
					else{
						for(int i = getColumn() - 1; i > column; i--){
							if(getChessBoard().getPiece(getRow(),i) != null){
								occupiedSquare += 1;
							}
						}
						if(occupiedSquare == 1){
							legalMove = true;
						}
						else{
							legalMove = false;
						}
					}
				}
				else{
					if(row > getRow()){
						for(int i = getRow() + 1; i < row; i++){
							if((getChessBoard().getPiece(i,getColumn())) != null){
								occupiedSquare += 1;
							}
						}
						if(occupiedSquare == 1){
							legalMove = true;
						}
						else{
							legalMove = false;
						}
					}
					else{
						for(int i = getRow() - 1; i > row; i--){
							if(getChessBoard().getPiece(i,getColumn()) != null){
								occupiedSquare += 1;
							}
						}
						if(occupiedSquare ==  1){
							legalMove = true;
						}
						else{
							legalMove = false;
						}
					}
				}
			}
		}
		return legalMove ; 
	}

	/**
	 * This overrides the isLegalMove in ChessPiece
	 * @param row is the row that the piece is moved to
	 * @param column is the column that the piece is moved to
	 * @return boolean that tells us if it is legal
	 */ 
	public boolean isLegalMove(int row, int column){
		//if there is a piece on square it is trying to get to return true
		if((this.getChessBoard().getPiece(row,column)) == null){
			return true;
		}
		else{
			return false;
		}

	} 

	@Override
	public void moveDone() {
		// TODO Auto-generated method stub

	}

}