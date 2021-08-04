package newChess;
import javax.swing.JOptionPane;

import newChess.ChessGame.Side;

/**
 * @author Isha Das
 *this is a pawn piece class of the European game
 */
public class PawnPiece extends EuropeanChessPiece {
	
	// Flag to track if the piece has moved
	private boolean firstMove = true;

	public PawnPiece(ChessBoard chessBoard, Side side) {
		this.setLabel("P");
		this.setChessBoard(chessBoard);
		this.setSide(side);
	}
	
	
	@Override
	public boolean isLegalNonCaptureMove(int row, int column)
	{
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
			if(firstMove)
			{
				if(this.isLegalVerticalNonCaptureMove(row, column, false, 2))
				{
					legalMove = true;
					this.moveDone();
				}
			}
			else
			{
				if(this.isLegalVerticalNonCaptureMove(row, column, false, 1))
				{
					legalMove = true;
					this.moveDone();
				}
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

			if(this.getSide() == ChessGame.Side.NORTH || this.getSide() == ChessGame.Side.EAST)
			{
				if(cp.getSide() == ChessGame.Side.SOUTH || cp.getSide() == ChessGame.Side.WEST)
				{
					if( (Math.abs(this.getColumn() - column) == 1) && ( (row - this.getRow()) == 1 )  )
					{
						this.movePiece(row, column);
						legalMove = true;
					}
				}
			}
			else if(this.getSide() == ChessGame.Side.SOUTH || this.getSide() == ChessGame.Side.WEST)
			{
				if(cp.getSide() == ChessGame.Side.NORTH || cp.getSide() == ChessGame.Side.EAST)
				{
					if( (Math.abs(this.getColumn() - column) == 1) && ( (this.getRow() - row) == 1 )  )
					{
						this.movePiece(row, column);
						legalMove = true;
					}
				}
			}
		}
	
		return legalMove;
	}

	@Override
	public void moveDone() {
		// Set the firstMove flag to false after a move
		this.firstMove = false;
		
		// Check if the Pawn reached the end for first player and swap it with selcted piece
		if( ( (this.getSide() == ChessGame.Side.NORTH || this.getSide() == ChessGame.Side.EAST) 
				&& (this.getRow() == (this.getChessBoard().getGameRules().getNumRows() - 1)) ) 
				|| ( (this.getSide() == ChessGame.Side.SOUTH || this.getSide() == ChessGame.Side.WEST) 
				&& (this.getRow() == 0) ) )
		{
			// Ask user to pick a new piece for swap
			switch (readInputChar())
			{
				case 'N':
					this.getChessBoard().removePiece(this.getRow(), this.getColumn());
					
					KnightPiece n = new KnightPiece(this.getChessBoard(), this.getSide());
					this.getChessBoard().addPiece(n, this.getRow(), this.getColumn());
					break;
					
				case 'R':
					this.getChessBoard().removePiece(this.getRow(), this.getColumn());
					
					RookPiece r = new RookPiece(this.getChessBoard(), this.getSide());
					this.getChessBoard().addPiece(r, this.getRow(), this.getColumn());
					break;

				case 'B':
					this.getChessBoard().removePiece(this.getRow(), this.getColumn());
					
					BishopPiece b = new BishopPiece(this.getChessBoard(), this.getSide());
					this.getChessBoard().addPiece(b, this.getRow(), this.getColumn());
					break;

				case 'Q':
					this.getChessBoard().removePiece(this.getRow(), this.getColumn());
					
					QueenPiece q = new QueenPiece(this.getChessBoard(), this.getSide());
					this.getChessBoard().addPiece(q, this.getRow(), this.getColumn());
					break;
						
			}
		}
		
	}
	
	//Helper method to return a character that will read the input and catch any exceptions using JOptionPane
	private static char readInputChar() {
		
		String inputString = "";
		do
		{
			try
			{
				inputString = JOptionPane.showInputDialog("Upgrade Pawn, enter N, R, B or Q");
			}
			catch(Exception e)
			{
				inputString = JOptionPane.showInputDialog("Upgrade Pawn, enter N, R, B or Q");
			}
		}while(!(inputString.equals("N") ||  inputString.equals("R") || inputString.equals("B") || inputString.equals("Q")));
    
		return inputString.charAt(0);
	}
}
