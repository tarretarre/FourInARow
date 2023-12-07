import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameBoard {
    private JFrame frame = new JFrame("Four in a row");
    private JLabel messageLabel = new JLabel("");

    private JLabel[] board = new JLabel[42];
    public GameBoard(){
        messageLabel.setBackground(Color.lightGray);
        frame.getContentPane().add(messageLabel, "South");

        JPanel boardPanel = new JPanel();
        boardPanel.setBackground(Color.black);
        boardPanel.setLayout(new GridLayout(6, 7, 2, 2));
        for (int i = 0; i < board.length; i++) {
            final int j = i;
            board[i] = new JLabel();
            board[i].setBackground(Color.white);
            //board[i].addMouseListener(this);

            boardPanel.add(board[i]);
        }
        frame.getContentPane().add(boardPanel, "Center");
    }
}
