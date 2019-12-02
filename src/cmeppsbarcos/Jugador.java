/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cmeppsbarcos;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author usuario
 */
public class Jugador {

    private String Nombre;
    private Tablero TJugador;
    private Tablero TRival;
    private ArrayList<Barco> Barcos;
    private ArrayList<String> Disparos;

    public Jugador(String n, Tablero tj, ArrayList<Barco> b) {
        Nombre = n;
        TJugador = tj;
        Barcos = b;
        Disparos = new ArrayList<String>();
    }

    public void ColocarBarcos() {
        Scanner sc = new Scanner(System.in);
        String s;
        int x, y;
        boolean valido;
        for (int i = 0; i < Barcos.size(); i++) {
            do {
                System.out.println("Coloca el barco " + (i + 1) + " de tamaño " + Barcos.get(i).getTamano());
                System.out.println("Escribe v si quieres que su orientacion sea vertical: ");
                s = sc.next();
                System.out.println("Indica posicion inicial coordenada X: ");
                x = sc.nextInt();
                System.out.println("Indica posicion inicial coordenada Y: ");
                y = sc.nextInt();

                if (s.compareTo("v") == 0) {
                    valido = TJugador.ponerBarco(x, y, true, Barcos.get(i).getTamano(), Barcos.get(i).getID());

                } else {
                    valido = TJugador.ponerBarco(x, y, false, Barcos.get(i).getTamano(), Barcos.get(i).getID());
                }

                if (valido) {
                    System.out.println("Barco colocado con exito.");
                } else {
                    System.out.println("Barco no colocado, prueba de nuevo.");
                }

            } while (!valido);
        }
    }

    public boolean Disparar(int x, int y) {
        boolean repetido = false;
        int i = 0;
        while (i < Disparos.size() && !repetido) {
            if (Disparos.get(i).substring(0, 1).compareTo(String.valueOf(x)) == 0 && Disparos.get(i).substring(1).compareTo(String.valueOf(y)) == 0) {
                repetido = true;
            }
            i++;
        }
        if (!repetido) {
            Disparos.add(String.valueOf(x) + String.valueOf(y));
            return TRival.Disparar(x, y);

        } else {
            System.out.println("Disparo repetido");
        }
        return false;
    }

    public Tablero getTableroJugador() {
        return TJugador;
    }

    public Tablero getTableroRival() {
        return TRival;
    }

    public void asignarTableroRival(Tablero tr) {
        TRival = tr;
    }

    public ArrayList<String> getDisparos() {
        return Disparos;
    }

    public boolean comprobarVictoria() {
        return TRival.barcosEliminados();
    }
}
