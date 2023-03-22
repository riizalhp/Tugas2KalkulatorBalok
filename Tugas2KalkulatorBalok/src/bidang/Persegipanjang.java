/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bidang;

/**
 *
 * @author Lenovo
 */
public class Persegipanjang implements Bidang {
  public double panjang, lebar;
    
    public Persegipanjang(double panjang, double lebar){
        this.panjang = panjang;
        this.lebar = lebar;}

    @Override
    public double MenghitungLuas() {
        return (panjang*lebar);
    }

    @Override
    public double MenghitungKeliling() {
        return(2*(panjang+lebar));
    }

    
    
}
