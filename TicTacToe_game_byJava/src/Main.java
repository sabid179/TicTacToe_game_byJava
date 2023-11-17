import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class Main extends JFrame{
    public static void main(String[] args) {
        createAndExecuteGame();
    }
    public static void createAndExecuteGame() {
        Main newGame = new Main();
        newGame.goOn();
    }
    public void goOn() {
        ImageIcon logo = new ImageIcon(Objects.requireNonNull(getClass().getResource("img.png")));
        setIconImage(logo.getImage());
        setSize(270, 390);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container cnt = getContentPane();
        cnt.setLayout(null);
        cnt.setBackground(Color.WHITE);
        Color color = new Color(9, 167, 179);

        JLabel titleBar = new JLabel();
        titleBar.setText("Tic Tac Toe");
        titleBar.setBounds(0, 0, 270, 40);
        titleBar.setFont(new Font("Monospaced",Font.BOLD, 25));
        titleBar.setHorizontalAlignment(SwingConstants.CENTER);
        titleBar.setForeground(Color.WHITE);
        titleBar.setOpaque(true);
        titleBar.setBackground(color);
        cnt.add(titleBar);

        JLabel moveIndicator = new JLabel();
        moveIndicator.setText(" :  Move of \"X\"");
        moveIndicator.setBounds(0, 45, 170, 20);
        moveIndicator.setFont(new Font("Monospaced",Font.BOLD, 15));
        moveIndicator.setHorizontalAlignment(SwingConstants.LEFT);
        moveIndicator.setForeground(Color.BLACK);
        cnt.add(moveIndicator);

        JLabel output = new JLabel();
        output.setText("Pending game...");
        output.setBounds(0, 300, 270, 60);
        output.setHorizontalAlignment(SwingConstants.CENTER);
        output.setVerticalAlignment(SwingConstants.CENTER);
        output.setFont(new Font("Monospaced", Font.BOLD, 25));
        output.setForeground(Color.WHITE);
        output.setOpaque(true);
        output.setBackground(Color.decode("#09a7b3"));
        cnt.add(output);

        JButton[][] buttons = new JButton[3][3];
        final int[] move = {1};
        final int[] win = {0};
        final int[] moveCount = {0};
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setText("");
                buttons[i][j].setBounds(23 + (j * 70), 75 + (i * 70), 70, 70);
                buttons[i][j].setCursor(new Cursor(Cursor.HAND_CURSOR));
                buttons[i][j].setBackground(Color.WHITE);
                buttons[i][j].setFont(new Font("Monospaced", Font.BOLD, 40));
                buttons[i][j].setHorizontalAlignment(SwingConstants.CENTER);
                buttons[i][j].setVerticalAlignment(SwingConstants.CENTER);
                cnt.add(buttons[i][j]);
                buttons[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        for (int k = 0; k < 3; k++) {
                            for (int l = 0; l < 3; l++) {
                                if (e.getSource() == buttons[k][l] && buttons[k][l].getText().isEmpty() && win[0] != 1) {
                                    if (move[0] == 1) {
                                        buttons[k][l].setText("X");
                                        moveCount[0]++;
                                        buttons[k][l].setForeground(Color.RED);
                                        move[0] = 0;
                                        moveIndicator.setText(" :  Move of \"O\"");
                                    } else if (move[0] == 0) {
                                        buttons[k][l].setText("O");
                                        moveCount[0]++;
                                        buttons[k][l].setForeground(Color.GREEN);
                                        move[0] = 1;
                                        moveIndicator.setText(" :  Move of \"X\"");
                                    }
                                    if (checkWinnings(buttons) == 'X') {
                                        output.setText("X won!");
                                        output.setForeground(Color.YELLOW);
                                        win[0] = 1;
                                    } else if (checkWinnings(buttons) == 'O') {
                                        output.setText("O won!");
                                        output.setForeground(Color.YELLOW);
                                        win[0] = 1;
                                    } else if (moveCount[0] == 9) {
                                        output.setText("Match draw!");
                                        output.setForeground(Color.YELLOW);
                                        win[0] = 2;
                                    } else continue;
                                } else if (e.getSource() == buttons[k][l] && win[0] == 1) {
                                    output.setText(((move[0] == 1)? "O" : "X") + " won already!");
                                    output.setForeground(Color.YELLOW);
                                }
                            }
                        }
                     }
                });
            }
        }
        JButton resetButton = new JButton();
        resetButton.setText("RESET");
        resetButton.setBounds(160, 45, 80, 20);
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        buttons[i][j].setText("");
                        move[0] = 1;
                        moveIndicator.setText(" :  Move of \"X\"");
                        output.setText("Pending game...");
                        output.setForeground(Color.WHITE);
                        moveCount[0] = 0;
                        win[0] = 0;
                    }
                }
            }
        });
        cnt.add(resetButton);
        setVisible(true);
    }
    public char checkWinnings(JButton[][] buttons) {
        if ((buttons[0][0].getText().equals("X") && buttons[0][1].getText().equals("X") && buttons[0][2].getText().equals("X"))
                || (buttons[1][0].getText().equals("X") && buttons[1][1].getText().equals("X") && buttons[1][2].getText().equals("X"))
                || (buttons[2][0].getText().equals("X") && buttons[2][1].getText().equals("X") && buttons[2][2].getText().equals("X"))
                || (buttons[0][0].getText().equals("X") && buttons[1][0].getText().equals("X") && buttons[2][0].getText().equals("X"))
                || (buttons[0][1].getText().equals("X") && buttons[1][1].getText().equals("X") && buttons[2][1].getText().equals("X"))
                || (buttons[0][2].getText().equals("X") && buttons[1][2].getText().equals("X") && buttons[2][2].getText().equals("X"))
                || (buttons[0][0].getText().equals("X") && buttons[1][1].getText().equals("X") && buttons[2][2].getText().equals("X"))
                || (buttons[0][2].getText().equals("X") && buttons[1][1].getText().equals("X") && buttons[2][0].getText().equals("X"))) {
            return 'X';
        } else if ((buttons[0][0].getText().equals("O") && buttons[0][1].getText().equals("O") && buttons[0][2].getText().equals("O"))
                || (buttons[1][0].getText().equals("O") && buttons[1][1].getText().equals("O") && buttons[1][2].getText().equals("O"))
                || (buttons[2][0].getText().equals("O") && buttons[2][1].getText().equals("O") && buttons[2][2].getText().equals("O"))
                || (buttons[0][0].getText().equals("O") && buttons[1][0].getText().equals("O") && buttons[2][0].getText().equals("O"))
                || (buttons[0][1].getText().equals("O") && buttons[1][1].getText().equals("O") && buttons[2][1].getText().equals("O"))
                || (buttons[0][2].getText().equals("O") && buttons[1][2].getText().equals("O") && buttons[2][2].getText().equals("O"))
                || (buttons[0][0].getText().equals("O") && buttons[1][1].getText().equals("O") && buttons[2][2].getText().equals("O"))
                || (buttons[0][2].getText().equals("O") && buttons[1][1].getText().equals("O") && buttons[2][0].getText().equals("O"))) {
            return 'O';
        }else return '0';
    }
}