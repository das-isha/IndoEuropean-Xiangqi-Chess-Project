package newChess;
import javax.swing.Icon;
import newChess.ChessGame.Side;

/**
 * @author Isha Das
 * abstract class for all the European chess piece
 */
public abstract class EuropeanChessPiece implements ChessPiece {

	private ChessBoard chessBoard = null;
	private ChessGame.Side side = null;
	private int currentRow;
	private int currentColumn;
	private String pieceLabel = "";
	private Icon icon = null;
	
	@Override
	public Side getSide() {
		// TODO Auto-generated method stub
		return side;
	}

	@Override
	public String getLabel() {
		// TODO Auto-generated method stub
		return pieceLabel;
	}

	@Override
	public Icon getIcon() {
		// TODO Auto-generated method stub
		return icon;
	}

	@Override
	public void setLocation(int row, int col) {
		// TODO Auto-generated method stub
		currentRow = row;
		currentColumn = col;
	}

	@Override
	public boolean isLegalMove(int toRow, int toColumn) {
		// TODO Auto-generated method stub
		return ( isLegalCaptureMove(toRow, toColumn) || this.isLegalNonCaptureMove(toRow, toColumn) );
	}
	
	//returns the chessboard
	public ChessBoard getChessBoard() {
	   return this.chessBoard;
	}
	
	//sets the chess board
	public void setChessBoard(ChessBoard chessBoard) {
		this.chessBoard = chessBoard;
	}
	
	//gets the row of the board
	public int getRow() {
		return currentRow;
	}
	
	//gets the columns of the board
	public int getColumn() {
		return currentColumn;		
	}
	
	//sets the side aka the players 
	public void setSide(ChessGame.Side side) {
		this.side = side;
	}

	public abstract boolean isLegalNonCaptureMove (int row, int column);
	
	
	public abstract boolean isLegalCaptureMove (int row, int column);
	
	
	public abstract void moveDone();
	
	
	public void setLabel(String label) {
		this.pieceLabel = label;
	}
	
	
	public void setIcon(Icon icon) {
		this.icon = icon;
	}
	
	public boolean isLegalVerticalNonCaptureMove (int row, int column, boolean backwardAllowed, int maxSteps) {
		// Boolean variable to store if the move to the new row and column is lagal
		boolean legalMove = false;
		boolean foundPiece = false;
		
		//Create a ChessBoard and assign is the ChessBoard attached to the Piece
		ChessBoard cb = this.getChessBoard();
		
		if(!backwardAllowed && maxSteps > 0)
		{
			if(this.getSide() == ChessGame.Side.NORTH || this.getSide() == ChessGame.Side.EAST)
			{
				int i = 0;
				do
				{
					++i;
					
					if( (this.getColumn() == column) && (cb.hasPiece(this.getRow() + i, column)) )
					{
						foundPiece = true;
					}
					
				} while (!foundPiece && i < maxSteps && this.getRow()+i < row);
				
				if(this.getRow()+i == row)
				{
					this.movePiece(row, column);
					legalMove = true;
				}
				else
				{ 
					legalMove = false;
				}
				
			}
			else if(this.getSide() == ChessGame.Side.SOUTH || this.getSide() == ChessGame.Side.WEST)
			{	
				int i = 0;
				do
				{
					i++;
					if( (this.getColumn() == column) && (cb.hasPiece(this.getRow()-i, column)) )
					{
						foundPiece = true;
					}
					
				} while (!foundPiece && i < maxSteps && this.getRow()-i > row );
				
				if(this.getRow()-i == row)
				{
					this.movePiece(row, column);
					legalMove = true;
				}
				else
				{
					legalMove = false;
				}
		
			}
		
		}
		else if(backwardAllowed && maxSteps == 0)
		{
			if(this.getRow() < row)
			{
				int i = 0;
				do
				{
					++i;
					
					if( (this.getColumn() == column) && (cb.hasPiece(this.getRow() + i, column)) )
					{
						foundPiece = true;
					}
					
				} while (!foundPiece && this.getRow()+i < row);
				if(this.getRow()+i == row && this.getColumn() == column)
				{
					this.movePiece(row, column);
					legalMove = true;
				}
				else
				{ 
					legalMove = false;
				}
			}
			if(this.getRow() > row)
			{
				int i = 0;
				do
				{
					++i;
					
					if( (this.getColumn() == column) && (cb.hasPiece(this.getRow() - i, column)) )
					{
						foundPiece = true;
					}
					
				} while (!foundPiece && this.getRow()-i > row);
				if(this.getRow()-i == row && this.getColumn() == column)
				{
					this.movePiece(row, column);
					legalMove = true;
				}
				else
				{ 
					legalMove = false;
				}
			}
			
		}
		
		return legalMove;
	}
	
	public boolean isLegalDiagonalNonCaptureMove (int row, int column, boolean backwardAllowed, int maxSteps) {
		// Boolean variable to store if the move to the new row and column is lagal
		boolean legalMove = false;
		boolean foundPiece = false;
		
		//Create a ChessBoard and assign is the ChessBoard attached to the Piece
		ChessBoard cb = this.getChessBoard();
		
		if(backwardAllowed && maxSteps == 0)
		{
			if(this.getRow() < row && this.getColumn() < column)
			{
				int i = 0;
				do
				{
					++i;
					
					if( cb.hasPiece(this.getRow() + i, this.getColumn() + i) )
					{
						foundPiece = true;
					}
					
				} while (!foundPiece && this.getRow()+i < row && this.getColumn()+i < column);
				if(this.getRow()+i == row && this.getColumn()+i == column)
				{
					this.movePiece(row, column);
					legalMove = true;
				}
				else
				{ 
					legalMove = false;
				}
			}
			if(this.getRow() < row && this.getColumn() > column)
			{
				int i = 0;
				do
				{
					++i;
					
					if( cb.hasPiece(this.getRow() + i, this.getColumn() - i) )
					{
						foundPiece = true;
					}
					
				} while (!foundPiece && this.getRow()+i < row && this.getColumn()-i > column);
				if(this.getRow()+i == row && this.getColumn()-i == column)
				{
					this.movePiece(row, column);
					legalMove = true;
				}
				else
				{ 
					legalMove = false;
				}
			}
			if(this.getRow() > row && this.getColumn() > column)
			{
				int i = 0;
				do
				{
					++i;
					
					if( cb.hasPiece(this.getRow() - i, this.getColumn() - i) )
					{
						foundPiece = true;
					}
					
				} while (!foundPiece && this.getRow()-i > row && this.getColumn()-i > column);
				if(this.getRow()-i == row && this.getColumn()-i == column)
				{
					this.movePiece(row, column);
					legalMove = true;
				}
				else
				{ 
					legalMove = false;
				}
			}
			if(this.getRow() > row && this.getColumn() < column)
			{
				int i = 0;
				do
				{
					++i;
					
					if( cb.hasPiece(this.getRow() - i, this.getColumn() + i) )
					{
						foundPiece = true;
					}
					
				} while (!foundPiece && this.getRow()-i > row && this.getColumn()+i < column);
				if(this.getRow()-i == row && this.getColumn()+i == column)
				{
					this.movePiece(row, column);
					legalMove = true;
				}
				else
				{ 
					legalMove = false;
				}
			}
			
		}
		
		return legalMove;
	}

	
	public boolean isLegalHorizontalNonCaptureMove (int row, int column, boolean backwardAllowed, int maxSteps) {
		// Boolean variable to store if the move to the new row and column is lagal
		boolean legalMove = false;
		boolean foundPiece = false;
		
		//Create a ChessBoard and assign is the ChessBoard attached to the Piece
		ChessBoard cb = this.getChessBoard();
		
		if(backwardAllowed && maxSteps > 0)
		{
			if(this.getSide() == ChessGame.Side.NORTH || this.getSide() == ChessGame.Side.EAST)
			{
				int i = 0;
				do
				{
					++i;
					
					if( (this.getRow() == row) && (cb.hasPiece(row, this.getColumn() + i)) )
					{
						foundPiece = true;
					}
					
				} while (!foundPiece && i < maxSteps && this.getColumn()+i < column);
				
				if(this.getColumn()+i == column)
				{
					this.movePiece(row, column);
					legalMove = true;
				}
				else
				{ 
					legalMove = false;
				}
				
			}
			else if(this.getSide() == ChessGame.Side.SOUTH || this.getSide() == ChessGame.Side.WEST)
			{	
				int i = 0;
				do
				{
					i++;
					if( (this.getRow() == row) && (cb.hasPiece(row, this.getColumn() - i)) )
					{
						foundPiece = true;
					}
					
				} while (!foundPiece && i < maxSteps && this.getColumn()-i > column );
				
				if(this.getColumn()-i == column)
				{
					this.movePiece(row, column);
					legalMove = true;
				}
				else
				{
					legalMove = false;
				}
		
			}
		
		}
		else if(backwardAllowed && maxSteps == 0)
		{
			if(this.getColumn() < column)
			{
				int i = 0;
				do
				{
					++i;
					
					if( (this.getRow() == row) && (cb.hasPiece(row,this.getColumn()+i )) )
					{
						foundPiece = true;
					}
					
				} while (!foundPiece && this.getColumn()+i < column);
				if(this.getColumn()+i == column && this.getRow() == row)
				{
					this.movePiece(row, column);
					legalMove = true;
				}
				else
				{ 
					legalMove = false;
				}
			}
			if(this.getColumn() > column)
			{
				int i = 0;
				do
				{
					++i;
					
					if( (this.getRow() == row) && (cb.hasPiece(row, this.getColumn() - i)) )
					{
						foundPiece = true;
					}
					
				} while (!foundPiece && this.getColumn()-i > column);
				if(this.getColumn()-i == column && this.getRow() == row)
				{
					this.movePiece(row, column);
					legalMove = true;
				}
				else
				{ 
					legalMove = false;
				}
			}
			
		}
		
		return legalMove;
	}
	
	public void movePiece(int row, int column) {
		
		this.getChessBoard().removePiece(this.getRow(), this.getColumn());
		this.getChessBoard().addPiece(this, row, column);	
	}
	
}
