package newChess;

/**
 * @author Isha Das
 * This is European chess class that implements the chess game ineterface
 */
public class EuropeanChess implements ChessGame {

	// Stores previous side
	private ChessGame.Side prevSide;
	private int numRows = 8;
	private int numColums = 8;
	
	@Override
	public boolean legalPieceToPlay(ChessPiece piece, int row, int column) {
		// Boolean variable to store if the move to the new row and column is lagal
		boolean legalMove = false;
		
		if(this.prevSide != null)
		{
			if( (this.prevSide == ChessGame.Side.NORTH || this.prevSide == ChessGame.Side.EAST) 
					&& (piece.getSide() == ChessGame.Side.SOUTH || piece.getSide() == ChessGame.Side.WEST) 
					|| (this.prevSide == ChessGame.Side.SOUTH || this.prevSide == ChessGame.Side.WEST)
					&& (piece.getSide() == ChessGame.Side.NORTH || piece.getSide() == ChessGame.Side.EAST) )
			{
				this.prevSide = piece.getSide();
				legalMove = true;
			}
		}
		else
		{
			this.prevSide = piece.getSide();
			legalMove = true;
		}
		return legalMove;
	}

	@Override
	public boolean makeMove(ChessPiece piece, int toRow, int toColumn) {
		// TODO Auto-generated method stub
		return piece.isLegalMove(toRow, toColumn);
	}


	@Override
	public boolean canChangeSelection(ChessPiece piece) {
		return piece.getSide() != prevSide;
	}

	@Override
	public int getNumRows() {
		// TODO Auto-generated method stub
		return numRows;
	}

	@Override
	public int getNumColumns() {
		// TODO Auto-generated method stub
		return numColums;
	}

	//
	@Override
	public void startGame(ChessBoard board) {
		
		// Set players side
		ChessGame.Side player1 = ChessGame.Side.NORTH;
		ChessGame.Side player2 = ChessGame.Side.SOUTH;

		//sets the pawn pieces on both players sides
		for(int i = 0; i < 8; i++) { 
			board.addPiece(new PawnPiece(board, player1), 1, i); 
		}
		
		for(int i = 0; i < 8; i++) { 
			board.addPiece(new PawnPiece(board, player2), 6, i); 
		}
		
		//sets the pawn pieces on both players sides
		board.addPiece(new QueenPiece(board, player1), 0, 3);
		board.addPiece(new QueenPiece(board, player2), 7, 3);
		
		//sets the king pieces on both players sides
		board.addPiece(new KingPiece(board, player1), 0, 4); 
		board.addPiece(new KingPiece(board, player2), 7, 4);
		
		//sets the knight pieces on both players sides
		board.addPiece(new KnightPiece(board, player1), 0, 1); 
		board.addPiece(new KnightPiece(board, player1), 0, 6); 
		board.addPiece(new KnightPiece(board, player2), 7, 1); 
		board.addPiece(new KnightPiece(board, player2), 7, 6); 
		
		//sets the rooks pieces on both players sides
		board.addPiece(new RookPiece(board, player1), 0, 0);
		board.addPiece(new RookPiece(board, player1), 0, 7);
		board.addPiece(new RookPiece(board, player2), 7, 0);
		board.addPiece(new RookPiece(board, player2), 7, 7);
		
		//sets the bishop pieces on both players sides
		board.addPiece(new BishopPiece(board, player1), 0, 2);
		board.addPiece(new BishopPiece(board, player1), 0, 5);
		board.addPiece(new BishopPiece(board, player2), 7, 2); 
		board.addPiece(new BishopPiece(board, player2), 7, 5);
	
	}
}
