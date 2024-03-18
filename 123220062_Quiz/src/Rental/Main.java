
package Rental;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {
    private String vehicleType;
    private String customerName;
    private String phoneNumber;
    private String selectedVehicle;
    private double rentalPrice;
    private int rentalDays;

    // Frame 1: Pilihan Kendaraan
    private JPanel panel1;
    private JButton motorButton;
    private JButton mobilButton;

    // Frame 2/3: Detail Penyewaan
    private JPanel panel2;
    private JTextField nameField;
    private JTextField phoneField;
    private JRadioButton[] vehicleButtons;
    private ButtonGroup vehicleGroup;
    private JTextField daysField;
    private JButton saveButton;

    // Frame 4: Detail dan Total Harga
    private JPanel panel3;
    private JLabel nameLabel;
    private JLabel phoneLabel;
    private JLabel vehicleLabel;
    private JLabel daysLabel;
    private JLabel totalPriceLabel;
    private JButton finishButton;

    public Main() {
        setTitle("Aplikasi Rental Kendaraan");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Frame 1
        panel1 = new JPanel(new FlowLayout());
        motorButton = new JButton("Motor");
        mobilButton = new JButton("Mobil");
        panel1.add(motorButton);
        panel1.add(mobilButton);

        // Frame 2/3
        panel2 = new JPanel(new GridLayout(6, 1));
        nameField = new JTextField(10);
        phoneField = new JTextField(10);
        panel2.add(new JLabel("Nama Penyewa:"));
        panel2.add(nameField);
        panel2.add(new JLabel("Nomor Telepon:"));
        panel2.add(phoneField);
        panel2.add(new JLabel("Pilih Kendaraan:"));
        vehicleButtons = new JRadioButton[3];
        vehicleGroup = new ButtonGroup();
        JPanel vehiclePanel = new JPanel(new FlowLayout());
        for (int i = 0; i < vehicleButtons.length; i++) {
            vehicleButtons[i] = new JRadioButton();
            vehiclePanel.add(vehicleButtons[i]);
            vehicleGroup.add(vehicleButtons[i]);
        }
        panel2.add(vehiclePanel);
        panel2.add(new JLabel("Jumlah Hari Sewa:"));
        daysField = new JTextField(5);
        panel2.add(daysField);
        saveButton = new JButton("Simpan");
        panel2.add(saveButton);

        // Frame 4
        panel3 = new JPanel(new GridLayout(6, 1));
        nameLabel = new JLabel();
        phoneLabel = new JLabel();
        vehicleLabel = new JLabel();
        daysLabel = new JLabel();
        totalPriceLabel = new JLabel();
        panel3.add(nameLabel);
        panel3.add(phoneLabel);
        panel3.add(vehicleLabel);
        panel3.add(daysLabel);
        panel3.add(totalPriceLabel);
        finishButton = new JButton("Selesai");
        panel3.add(finishButton);

        // Add action listeners
        motorButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                vehicleType = "Motor";
                showDetailFrame();
            }
        });

        mobilButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                vehicleType = "Mobil";
                showDetailFrame();
            }
        });

        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                customerName = nameField.getText();
                phoneNumber = phoneField.getText();
                for (int i = 0; i < vehicleButtons.length; i++) {
                    if (vehicleButtons[i].isSelected()) {
                        selectedVehicle = "Kendaraan " + (i + 1);
                        // Contoh harga sewa per hari, dapat diganti sesuai kebutuhan
                        rentalPrice = (i + 1) * 100000; // Misal: 100 ribu per hari
                        break;
                    }
                }
                rentalDays = Integer.parseInt(daysField.getText());
                showSummaryFrame();
            }
        });

        finishButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Menutup aplikasi
            }
        });

        // Menampilkan frame pertama
        add(panel1);
        setVisible(true);
    }

    private void showDetailFrame() {
        getContentPane().removeAll();
        add(panel2);
        revalidate();
        repaint();
    }

    private void showSummaryFrame() {
        getContentPane().removeAll();
        nameLabel.setText("Nama Penyewa: " + customerName);
        phoneLabel.setText("Nomor Telepon: " + phoneNumber);
        vehicleLabel.setText("Jenis Kendaraan: " + selectedVehicle + ", Harga Sewa per Hari: Rp" + rentalPrice);
        daysLabel.setText("Jumlah Hari Sewa: " + rentalDays);
        totalPriceLabel.setText("Total Harga Sewa: Rp" + (rentalDays * rentalPrice));
        add(panel3);
        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Main();
            }
        });
    }
}
