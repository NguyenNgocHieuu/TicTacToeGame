package tic_tac_toe;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class TicTacToeGame extends JFrame{
	private JButton[][] buttons;
    private boolean playerX;

    public TicTacToeGame() {
        super("Tic Tac Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);
        setLayout(new GridLayout(3, 3));

        buttons = new JButton[3][3];
        playerX = true;

        // khoi tao cac o vuong va gan su kien 
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col] = new JButton();
                buttons[row][col].setFont(new Font(Font.SANS_SERIF, Font.BOLD, 50));
                buttons[row][col].addActionListener(e -> handleButtonClick(e));
                add(buttons[row][col]);
            }
        }
    }

    // xu li su kien khi nhan vao cac o vuong
    private void handleButtonClick(ActionEvent e) {
        JButton button = (JButton) e.getSource();

        if (button.getText().isEmpty()) {
            if (playerX) {
                button.setText("X");
            } else {
                button.setText("O");
            }

            button.setEnabled(false);
            playerX = !playerX;

            // kiem tra xem ai là nguoi chien thang
            if (checkWin()) {
                String winner = playerX ? "O" : "X";
                JOptionPane.showMessageDialog(null, "Player " + winner + " wins!");
                resetGame();
            } else if (checkDraw()) {
                JOptionPane.showMessageDialog(null, "It's a draw!");
                resetGame();
            }
        }
    }

    // kiem tra xem nguoi choi hien tai da thang hay chua
    private boolean checkWin() {
        String[][] board = new String[3][3];

        // lay thong tin tu cac o vuong
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col] = buttons[row][col].getText();
            }
        }

        // kiem tra cac dong va cot
        for (int i = 0; i < 3; i++) {
            if (board[i][0].equals(board[i][1]) && board[i][1].equals(board[i][2]) && !board[i][0].isEmpty()) {
                return true;
            }
            if (board[0][i].equals(board[1][i]) && board[1][i].equals(board[2][i]) && !board[0][i].isEmpty()) {
                return true;
            }
        }

        // kiem tra cac duong cheo
        if (board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2]) && !board[0][0].isEmpty()) {
            return true;
        }
        if (board[0][2].equals(board[1][1]) && board[1][1].equals(board[2][0]) && !board[0][2].isEmpty()) {
            return true;
        }

        return false;
    }

    // kiem tra tro choi co hoa hay ko
    private boolean checkDraw() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (buttons[row][col].getText().isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    // reset lai tro choi
    private void resetGame() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col].setText("");
                buttons[row][col].setEnabled(true);
            }
        }
        playerX = true;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new TicTacToeGame().setVisible(true);
            }
        });
    }
}