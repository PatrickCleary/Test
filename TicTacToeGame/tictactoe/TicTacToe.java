//Ryan Cleary
package tictactoe;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
//Class creates a JPanel and an array of buttons and an array of cell objects. 
//The buttons are for playing the game and the cell objects are for keeping track of the status of the game
public class TicTacToe extends JPanel implements ActionListener{
    JButton quit=new JButton ("Quit");
    //keeps track of whose turn it is
    int alternate=0;
    JButton buttons[][]=new JButton[3][3];
    TttCell cells[][]=new TttCell[3][3];
    //creates a new JPanel to place the buttons in a grid within the layout of the main JPanel
    JPanel inner=new JPanel();
    String winner;
    JLabel label = new JLabel("Winner: ");
    JLabel gamewinner = new JLabel("");
    JLabel message = new JLabel("");
    JButton reset = new JButton ("reset");
    JPanel NorthPanel = new JPanel();
    public TicTacToe() {
    	this.setPreferredSize(new Dimension(500, 500));
        setLayout(new BorderLayout());
        initializeCells();
        initializebuttons();
        add(quit,BorderLayout.SOUTH);
        quit.addActionListener(this);
        add(inner,BorderLayout.CENTER);
        NorthPanel.add(message);
        NorthPanel.add(reset);
        
        NorthPanel.add(label);
        NorthPanel.add(gamewinner);
        
        reset.addActionListener(this);
        add(NorthPanel,BorderLayout.NORTH);
        
    }
    //method creates a 2D array of JButtons with blank text fields and 
    //whose action listener is this class they are then added to the inner JPanel
    public void initializebuttons(){
        inner.setLayout(new GridLayout(3,3));
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                buttons[i][j]=new JButton("");
                buttons[i][j].addActionListener(this);
                inner.add(buttons[i][j]);
            }
        }
    }
    //Creates a 2D array of cell objects which are initially set to unoccupied
    //the owner of the cell is set to a because it needs a value 
    public void initializeCells(){
        char a='a';
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                cells[i][j]=new TttCell(false,a); 
                a++;
            }
        }
    }
    //the board is reset to play a second round
    public void resetButtons(){
        message.setText("");
        gamewinner.setText("");
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                buttons[i][j].setEnabled(true);
                buttons[i][j].setText("");
                initializeCells();
            }
        }
    }
    //two for loops find the JButton array index that matches the button that was clicked on
    //if the button clicked on is not occupied then the button becomes occupied and its text is changed to the player who clicked on it 
    //then the turn counter is incremented
    @Override
    public void actionPerformed(ActionEvent e){
        message.setText("");
        JButton buttonClicked=(JButton)e.getSource();
        if(buttonClicked==reset){
            resetButtons();
        }
        if(buttonClicked==quit){
            System.exit(0);
        }
        for (int i=0;i<3;i++) {
            for (int j=0;j<3;j++) {
                if( buttons[i][j]==buttonClicked){  
                    try{
                    if(alternate%2==0){
                        cells[i][j].setOccupied(true);
                        buttonClicked.setText("X");
                        cells[i][j].setXO('X');
                        alternate++;
                        Win();
                    } 
                        else{
                            cells[i][j].setOccupied(true);
                            buttonClicked.setText("O");
                            cells[i][j].setXO('O');
                            alternate++;
                            Win();
                        }
                    }
                    catch(Exception ex){
                        message.setText("Cell Occupied");
                    }
                }
            }
        }
    }
    public void Win(){
        if(Draw()){
            gamewinner.setText("Draw");
            for(int i=0;i<3;i++){
                for(int j=0;j<3;j++){
                    buttons[i][j].setEnabled(false);
                }
            }
        }
        if(checkForWin()){
            gamewinner.setText(winner);
            for(int i=0;i<3;i++){
                for(int j=0;j<3;j++){
                    buttons[i][j].setEnabled(false);
                }
            }
        }
    }
    public boolean Draw(){
        int x=0;
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(cells[i][j].isOccupied()){
                    x++;
                }
            }
        }
        return (x==9);
     }
    public boolean checkForWin(){
        //horizontal win check
        if( checkAdjacent(0,0,0,1)&&checkAdjacent(0,1,0,2))  
                
                 //no need to put " == true" because the default check is for true
            return true;
        else if(checkAdjacent(1,0,1,1)&&checkAdjacent(1,1,1,2) )
            return true;
        else if ( checkAdjacent(2,0,2,1) && checkAdjacent(2,1,2,2))
            return true;
        //vertical win check
        else if ( checkAdjacent(0,0,1,0) && checkAdjacent(1,0,2,0))
            return true;  
        else if ( checkAdjacent(0,1,1,1) && checkAdjacent(1,1,2,1))
            return true;
        else if ( checkAdjacent(0,2,1,2) && checkAdjacent(1,2,2,2))
            return true;
        //diagonal win check
        else if ( checkAdjacent(0,0,1,1) && checkAdjacent(1,1,2,2))
            return true;  
        else if ( checkAdjacent(0,2,1,1) && checkAdjacent(1,1,2,0))
            return true;
        else 
            return false;
    }
    public boolean checkAdjacent(int a, int b, int c, int d){
            char y=cells[a][b].getXO();
            char z=cells[c][d].getXO();
            winner=String.valueOf(z);
            return y==z;
        }
}
