import javax.swing.*;
import java.awt.*;

public class Teatro extends JFrame {
    JButton[][] teatro = new JButton[20][20];
    double valorIngresso = 80.00;
    double valorReserva = 32.00;

    public Teatro() {
        setTitle("Teatro");
        setSize(1100, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JPanel painelCadeiras = new JPanel(new GridLayout(20, 20, 2, 2));

        for (int i = 0; i < teatro.length; i++) {
            for (int j = 0; j < teatro[i].length; j++) {
                teatro[i][j] = new JButton("L");
                teatro[i][j].setBackground(Color.GREEN);
                teatro[i][j].setFont(new Font("Arial", Font.BOLD, 12));
                painelCadeiras.add(teatro[i][j]);
            }
        }

        this.add(painelCadeiras, BorderLayout.CENTER);

        JPanel painelBotoes = new JPanel(new GridLayout(6, 1, 5, 10));

        JLabel titulo = new JLabel("TEATRO", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 22));

        JButton reservar = new JButton("Reservar cadeira");
        reservar.addActionListener(e -> reservar_assentos());

        JButton comprar = new JButton("Comprar cadeira");
        comprar.addActionListener(e -> comprar_assentos());

        JButton cancelar = new JButton("Cancelar reserva");
        cancelar.addActionListener(e -> cancelar_reserva());

        JButton relatorio = new JButton("Relatório financeiro");
        relatorio.addActionListener(e -> relatorio());

        JButton sair = new JButton("Encerrar");
        sair.addActionListener(e -> System.exit(0));

        painelBotoes.add(titulo);
        painelBotoes.add(reservar);
        painelBotoes.add(comprar);
        painelBotoes.add(cancelar);
        painelBotoes.add(relatorio);
        painelBotoes.add(sair);

        this.add(painelBotoes, BorderLayout.EAST);

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
                teatro[linha - 1][coluna - 1].setBackground(Color.YELLOW);

                valorTotal += valorReserva;

                JOptionPane.showMessageDialog(null, "Cadeira reservada com sucesso!!");
            }
        }

        JOptionPane.showMessageDialog(null, "Total a pagar: R$ " + valorTotal);
    }

    public void comprar_assentos() {

        String resposta = JOptionPane.showInputDialog("Quantos assentos deseja comprar?");
        int quantidadeAssentos = Integer.parseInt(resposta);

        double valorTotal = 0;

        for (int i = 0; i < quantidadeAssentos; i++) {

            String respostaLinha = JOptionPane.showInputDialog("Informe a linha do assento (1-20)");
            int linha = Integer.parseInt(respostaLinha);

            String respostaColuna = JOptionPane.showInputDialog("Informe a coluna do assento (1-20)");
            int coluna = Integer.parseInt(respostaColuna);

            if (linha < 1 || linha > 20 || coluna < 1 || coluna > 20) {

                JOptionPane.showMessageDialog(null, "Assento inválido!");

            } else if (teatro[linha - 1][coluna - 1].getText().equals("R")) {

                JOptionPane.showMessageDialog(null, "Cadeira indisponível!");

            } else if (teatro[linha - 1][coluna - 1].getText().equals("V")) {

                JOptionPane.showMessageDialog(null, "Cadeira indisponível!");

            } else {

                teatro[linha - 1][coluna - 1].setText("V");
                teatro[linha - 1][coluna - 1].setBackground(Color.RED);

                valorTotal += valorIngresso;

                JOptionPane.showMessageDialog(null, "Compra realizada com sucesso!");
            }
        }

        JOptionPane.showMessageDialog(null, "Valor pago: R$ " + valorTotal);
    }

    public void cancelar_reserva() {

        String respostaLinha = JOptionPane.showInputDialog("Informe a linha do assento (1-20)");
        int linha = Integer.parseInt(respostaLinha);

        String respostaColuna = JOptionPane.showInputDialog("Informe a coluna do assento (1-20)");
        int coluna = Integer.parseInt(respostaColuna);

        if (linha < 1 || linha > 20 || coluna < 1 || coluna > 20) {

            JOptionPane.showMessageDialog(null, "Assento inválido!");

        } else if (teatro[linha - 1][coluna - 1].getText().equals("R")) {

            teatro[linha - 1][coluna - 1].setText("L");
            teatro[linha - 1][coluna - 1].setBackground(Color.GREEN);

            JOptionPane.showMessageDialog(null, "Reserva cancelada com sucesso!");

        } else {

            JOptionPane.showMessageDialog(null, "Este assento não possui reserva.");
        }
    }

    public void relatorio() {

        int livres = 0;
        int reservados = 0;
        int vendidos = 0;

        for (int i = 0; i < teatro.length; i++) {

            for (int j = 0; j < teatro[i].length; j++) {

                if (teatro[i][j].getText().equals("L")) {

                    livres++;

                } else if (teatro[i][j].getText().equals("R")) {

                    reservados++;

                } else if (teatro[i][j].getText().equals("V")) {

                    vendidos++;
                }
            }
        }

        JOptionPane.showMessageDialog(null,
                "Assentos livres: " + livres +
                        "\nAssentos reservados: " + reservados +
                        "\nAssentos vendidos: " + vendidos);
    }

    public static void main(String[] args) {
        new Teatro();
    }
}