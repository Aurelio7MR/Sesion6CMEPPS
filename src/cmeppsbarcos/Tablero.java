/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cmeppsbarcos;

import java.util.ArrayList;

/**
 *
 * @author usuario
 */
public class Tablero {

    private int[][] Casillas;
    private ArrayList<Barco> Barcos;

    public Tablero(ArrayList<Barco> b) {
        Casillas = new int[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Casillas[i][j] = 0;
            }
        }
        Barcos = b;
    }

    public int getCasilla(int x, int y) {
        return Casillas[x][y];
    }

    public boolean ponerBarco(int x, int y, boolean esV, int tam, int id) {
        boolean posValida = true;
        int i = 0;

        if (esV) {
            while (posValida && i < tam) {
                if (Casillas[x + i][y] != 0) {
                    posValida = false;
                } else {
                    i++;
                }
            }

            if (posValida) {
                for (int j = 0; j < tam; j++) {
                    Casillas[x + j][y] = id;
                }
            }
        } else {
            while (posValida && i < tam) {
                if (Casillas[x][y + i] != 0) {
                    posValida = false;
                } else {
                    i++;
                }
            }

            if (posValida) {
                for (int j = 0; j < tam; j++) {
                    Casillas[x][y + j] = id;
                }
            }
        }
        return posValida;
    }

    public boolean Disparar(int x, int y) {
        if (Casillas[x][y] != 0) {
            int id = Casillas[x][y];
            int contador = 0;
            Casillas[x][y] = 0;
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    if (Casillas[i][j] == id) {
                        contador++;
                    }
                }
            }

            if (contador > 0) {
                System.out.println("Tocado");
            } else {
                boolean encontrado = false;
                int i = 0;
                while (!encontrado && i < Barcos.size()) {
                    if (id == Barcos.get(i).getID()) {
                        encontrado = true;
                    } else {
                        i++;
                    }
                }
                Barcos.remove(i);
                System.out.println("Hundido");
            }
            return true;
        } else {
            System.out.println("Agua");
        }
        return false;
    }

    public boolean barcosEliminados() {
        if (Barcos.size() == 0) {
            return true;
        }
        return false;
    }
}
