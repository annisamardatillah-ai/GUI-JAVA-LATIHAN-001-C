import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class KalkulatorGUI extends JFrame {   // JFrame = Top-level Container

    // Atomic components
    private JTextField tfAngka1, tfAngka2;
    private JLabel lblHasil;
    private JButton btnTambah, btnKurang, btnKali, btnBagi, btnReset;

    public KalkulatorGUI() {
        super("Kalkulator Sederhana - Latihan GUI Java");
        setSize(440, 320);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));      // Layout 1: BorderLayout

        // NORTH: judul (JPanel + FlowLayout)
        JPanel panelJudul = new JPanel(new FlowLayout(FlowLayout.CENTER)); // Layout 2: FlowLayout
        JLabel lblJudul = new JLabel("KALKULATOR SEDERHANA 001");
        lblJudul.setFont(new Font("SansSerif", Font.BOLD, 20));
        panelJudul.add(lblJudul);
        add(panelJudul, BorderLayout.NORTH);

        // CENTER: input (JPanel + GridLayout)
        JPanel panelInput = new JPanel(new GridLayout(2, 2, 10, 10));      // Layout 3: GridLayout
        panelInput.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
        tfAngka1 = new JTextField();
        tfAngka2 = new JTextField();
        panelInput.add(new JLabel("Angka 1 :"));
        panelInput.add(tfAngka1);
        panelInput.add(new JLabel("Angka 2 :"));
        panelInput.add(tfAngka2);
        add(panelInput, BorderLayout.CENTER);

        // SOUTH: tombol + hasil
        JPanel panelSelatan = new JPanel(new GridLayout(2, 1, 5, 5));
        JPanel panelTombol = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 5));
        btnTambah = new JButton("+");
        btnKurang = new JButton("-");
        btnKali   = new JButton("x");
        btnBagi   = new JButton("/");
        btnReset  = new JButton("C");
        panelTombol.add(btnTambah);
        panelTombol.add(btnKurang);
        panelTombol.add(btnKali);
        panelTombol.add(btnBagi);
        panelTombol.add(btnReset);

        lblHasil = new JLabel("Hasil : -", SwingConstants.CENTER);
        lblHasil.setFont(new Font("SansSerif", Font.BOLD, 16));

        panelSelatan.add(panelTombol);
        panelSelatan.add(lblHasil);
        add(panelSelatan, BorderLayout.SOUTH);

        // Event Listener 1: ActionListener (interface)
        TombolListener listener = new TombolListener();
        btnTambah.addActionListener(listener);
        btnKurang.addActionListener(listener);
        btnKali.addActionListener(listener);
        btnBagi.addActionListener(listener);
        btnReset.addActionListener(listener);

        // Event Listener 2: MouseAdapter (adapter class)
        btnReset.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnReset.setBackground(Color.ORANGE);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                btnReset.setBackground(null);
            }
        });

        // Event Listener 3: WindowAdapter (adapter class)
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int pilihan = JOptionPane.showConfirmDialog(
                        KalkulatorGUI.this,
                        "Yakin ingin keluar dari Aplikasi 001?",
                        "Konfirmasi Keluar",
                        JOptionPane.YES_NO_OPTION);
                if (pilihan == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });

        //background jadi pink
      Color pink = new Color(255, 192, 203);
      getContentPane().setBackground(pink);
      panelJudul.setBackground(pink);
      panelInput.setBackground(pink);
      panelTombol.setBackground(pink);
      panelSelatan.setBackground(pink);
    }

    private class TombolListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Object sumber = e.getSource();

            if (sumber == btnReset) {
                tfAngka1.setText("");
                tfAngka2.setText("");
                lblHasil.setText("Hasil : -");
                tfAngka1.requestFocus();
                return;
            }

            try {
                double a = Double.parseDouble(tfAngka1.getText().trim());
                double b = Double.parseDouble(tfAngka2.getText().trim());
                double hasil;

                if (sumber == btnTambah) {
                    hasil = a + b;
                } else if (sumber == btnKurang) {
                    hasil = a - b;
                } else if (sumber == btnKali) {
                    hasil = a * b;
                } else {
                    if (b == 0) {
                        JOptionPane.showMessageDialog(KalkulatorGUI.this,
                                "Pembagi tidak boleh nol!",
                                "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    hasil = a / b;
                }
                lblHasil.setText("Hasil : " + hasil);

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(KalkulatorGUI.this,
                        "Masukkan angka yang valid!",
                        "Input Tidak Valid", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new KalkulatorGUI().setVisible(true));
    }
}