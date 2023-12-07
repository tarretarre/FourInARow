import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameBoard extends JFrame implements MouseListener{
    private JFrame frame = new JFrame("Four in a row");
    private JLabel messageLabel = new JLabel("");
    private Square[] board = new Square[42];
    private Square currentSquare = new Square();
    public GameBoard(){

    }
    public void drawGameBoard(){
        messageLabel.setBackground(Color.lightGray);
        frame.getContentPane().add(messageLabel, "South");

        JPanel boardPanel = new JPanel();
        boardPanel.setBackground(Color.black);
        boardPanel.setLayout(new GridLayout(6, 7, 2, 2));
        for (int i = 0; i < board.length; i++) {
            board[i] = new Square();
            board[i].addMouseListener(this);
            boardPanel.add(board[i]);
        }
        frame.getContentPane().add(boardPanel, "Center");


        frame.setTitle("Four in a row");
        frame.setSize(400, 400);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setResizable(true);
    }

    class Square extends JPanel {
        JLabel label = new JLabel();

        public Square() {
            setBackground(Color.white);
            add(label);
        }

        public void setText(String s) {
            label.setText(s);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public static void main(String[] args) {
        GameBoard g = new GameBoard();
        g.drawGameBoard();
    }
}
