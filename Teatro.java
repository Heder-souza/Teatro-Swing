import javax.swing.*;
import java.awt.*;

public class Teatro extends JFrame {
    JButton[][] teatro = new JButton[20][20];
    double valorIngresso = 80.00;
    double valorReserva = 32.00;

    public Teatro() {
        setTitle("Teatro");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout());

        JPanel painelCadeiras = new JPanel(new GridLayout(20, 20, 2, 2));

        for (int i = 0; i < teatro.length; i++) {
            for (int j = 0; j < teatro[i].length; j++) {
                teatro[i][j] = new JButton("L");
                teatro[i][j].setBackground(Color.GREEN);
                painelCadeiras.add(teatro[i][j]);
            }
        }

        this.add(painelCadeiras, BorderLayout.CENTER);

        JButton reservar = new JButton("Reservar cadeira");
        reservar.addActionListener(e -> reservar_assentos());

        this.add(reservar, BorderLayout.SOUTH);

        setVisible(true);

    }

    public void reservar_assentos() {
        String resposta = JOptionPane.showInputDialog("Quantos assentos deseja reservar?");
        int quantidadeAssentos = Integer.parseInt(resposta);

        double valorTotal = 0;

        for (int i = 0; i < quantidadeAssentos; i++) {
            String respostaLinha = JOptionPane.showInputDialog("Informe a linha do assento (1-20)");
            int linha = Integer.parseInt(respostaLinha);

            String respostaColuna = JOptionPane.showInputDialog("Informe a coluna do assento (1-20)");
            int coluna = Integer.parseInt(respostaColuna);

            if (linha < 1 || linha > 20 || coluna < 1 || coluna > 20) {
                JOptionPane.showMessageDialog(null, "Assento inválido fora do limite!");
            }

            else if (teatro[linha - 1][coluna - 1].getText().equals("R")) {
                JOptionPane.showMessageDialog(null, "Cadeira indisponivel!!");
            } else {
                teatro[linha - 1][coluna - 1].setText("R");
                teatro[linha - 1][coluna - 1].setBackground(Color.RED);

                valorTotal += valorReserva;

                JOptionPane.showMessageDialog(null, "Cadeira reservada com sucesso!!");
            }
        }

        JOptionPane.showMessageDialog(null, "Total a pagar: R$ " + valorTotal);
    }

    public static void main(String[] args) {
        new Teatro();
    }
}