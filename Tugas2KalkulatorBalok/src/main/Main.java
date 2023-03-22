/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;
import ruang.Balok;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class Main extends JFrame implements ActionListener {
    
    //Deklarasi Label
    JLabel lTitle,lPanjang,lLebar,lTinggi,lHasil,lLuas,lKeliling,lVolume,lLuasPermukaan;
    //Deklarasi Teks
    JTextField Panjang, Lebar,Tinggi;
    //Deklarasi Button
    JButton hitung,reset;
    
    /**
     *
     */
    public Main(){

        lTitle = new JLabel("Cuboid Calculator");
        lPanjang = new JLabel("Length");
        lLebar = new JLabel("Width");
        lTinggi = new JLabel("Height");
        lHasil = new JLabel("Result");
        lLuas = new JLabel();
        lKeliling = new JLabel();
        lVolume = new JLabel();
        lLuasPermukaan = new JLabel();
        
        lTitle.setBounds(220,30,200,30);
        lTitle.setFont(new Font(null, Font.BOLD, 16));
        
        lPanjang.setBounds(80, 110, 100, 40);
        lPanjang.setFont(new Font(null, Font.BOLD, 16));

        lLebar.setBounds(80, 175, 100, 40);
        lLebar.setFont(new Font(null, Font.BOLD, 16));
        
        lTinggi.setBounds(80, 240, 100, 40);
        lTinggi.setFont(new Font(null, Font.BOLD, 16));
        
        lHasil.setBounds(270, 275, 100, 40);
        lHasil.setFont(new Font(null, Font.BOLD, 16));

        lLuas.setBounds(80, 325, 400, 40);
        lLuas.setFont(new Font(null, Font.BOLD, 14));
        
        lKeliling.setBounds(80, 370,400, 40);
        lKeliling.setFont(new Font(null, Font.BOLD, 14));

        lVolume.setBounds(80, 413, 400, 40);
        lVolume.setFont(new Font(null, Font.BOLD, 14));

        lLuasPermukaan.setBounds(80, 455, 400, 40);
        lLuasPermukaan.setFont(new Font(null, Font.BOLD, 14));
        
        //Pengaturan Teks
        Panjang = new JTextField();
        Lebar = new JTextField();
        Tinggi = new JTextField();

        Panjang.setBounds(200,120,240,25);
        Lebar.setBounds(200,183,240,25);
        Tinggi.setBounds(200,250,240,25);
        
        //Button
        hitung= new JButton("Hitung");
        reset= new JButton("Reset");

        hitung.setBounds(185,540,100,23);
        reset.setBounds(295,540,100,23);
        
        
        //Setting JFrame
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        setSize(570,610);
        setLocationRelativeTo(null);
        setLayout(null);

        add(lTitle);
        add(lPanjang);
        add(lLebar);
        add(lTinggi);
        add(lLuas);
        add(lKeliling);
        add(lVolume);
        add(lLuasPermukaan);
        add(Panjang);
        add(Lebar);
        add(Tinggi);
        add(lHasil);
        add(hitung);
        add(reset);

        //Event Handling (Button)
        hitung.addActionListener(this);
        reset.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == hitung){
            try {
                double length =  Double.parseDouble(Panjang.getText());
                double width =  Double.parseDouble(Lebar.getText());
                double heigth =  Double.parseDouble(Tinggi.getText());
                
                Balok balok = new Balok (length, width, heigth);

                lLuas.setText(String.format("Square area      : %.2f",balok.MenghitungLuas()));
                lKeliling.setText(String.format("Square circumstance  : %.2f", balok.MenghitungKeliling()));
                lLuasPermukaan.setText(String.format("Cuboi surface area    : %.2f", balok.MenghitungLuasPermukaan()));
                lVolume.setText(String.format("Cuboid Volume                     : %.2f", balok.MenghitungVolume()));
                
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(null, nfe.getMessage(), "Message", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e){
                JOptionPane.showMessageDialog(null, "Terjadi Error, Masukkan Input Yang Benar", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        
        if(ae.getSource() == reset){
            Panjang.setText(null);
            Lebar.setText(null);
            Tinggi.setText(null);
            lLuas.setText(null);
            lKeliling.setText(null);
            lVolume.setText(null);
            lLuasPermukaan.setText(null);
        }

    }
    
    public static void main(String[] args) {
        new Main();

    }
}
