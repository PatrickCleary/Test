package tictactoe;
//This class simnulates a generic cell of a tictactoe board
public class TttCell{
    //character variable represents player who owns the cell
    private char xo;
    private boolean isoccupied;
    public TttCell(boolean b, char c){
        isoccupied=b;
        xo=c;
    }
    public boolean isOccupied(){
        return isoccupied;
    }
    public char getXO(){
        return xo;
    }    
    public void setOccupied(boolean b)throws Exception{
        if(isoccupied){
            throw new Exception("Cell Occupied");
        }
        isoccupied=b;
    }    
    public void setXO(char c){
        xo=c;
    }
    
}