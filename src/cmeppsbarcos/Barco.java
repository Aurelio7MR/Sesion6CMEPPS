/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cmeppsbarcos;

/**
 *
 * @author usuario
 */
public class Barco {

    private int Tamano;
    private int id;

    public Barco(int t, int i) {
        Tamano = t;
        id = i;
    }

    public int getTamano() {
        return Tamano;
    }

    public int getID() {
        return id;
    }

}
