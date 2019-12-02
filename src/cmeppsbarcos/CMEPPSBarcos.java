/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cmeppsbarcos;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author usuario
 */
public class CMEPPSBarcos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Barco b1 = new Barco(2, 1);
        Barco b2 = new Barco(2, 2);
        /*Barco b3 = new Barco(2,3);
        Barco b4 = new Barco(2,4);
        Barco b5 = new Barco(3,5);
        Barco b6 = new Barco(3,6);
        Barco b7 = new Barco(3,7);
        Barco b8 = new Barco(4,8);
        Barco b9 = new Barco(4,9);
        Barco b10 = new Barco(5,10);*/

        ArrayList<Barco> barcos = new ArrayList<Barco>();
        barcos.add(b1);
        barcos.add(b2);
        /*barcos.add(b3);
        barcos.add(b4);
        barcos.add(b5);
        barcos.add(b6);
        barcos.add(b7);
        barcos.add(b8);
        barcos.add(b9);
        barcos.add(b10);*/

        Tablero tj1 = new Tablero(barcos);
        Tablero tj2 = new Tablero(barcos);

        Jugador j1 = new Jugador("Aurelio", tj1, barcos);
        Jugador j2 = new Jugador("Rival", tj2, barcos);
        System.out.println("\nJugador 1 coloca sus barcos");
        j1.ColocarBarcos();
        System.out.println("\nJugador 2 coloca sus barcos");
        j2.ColocarBarcos();

        j1.asignarTableroRival(j2.getTableroJugador());
        j2.asignarTableroRival(j1.getTableroJugador());

        Scanner sc = new Scanner(System.in);
        int x, y;
        boolean hayGanador = false;
        boolean disparo;

        Random ran = new Random();
        ran.setSeed(System.currentTimeMillis());

        while (!hayGanador) {

            do {
                System.out.print("Tablero jugador 1");
                System.out.print("\n    0   1   2   3   4   5   6   7   8   9  ");
                System.out.print("\n  + - + - + - + - + - + - + - + - + - + - +");
                for (int i = 0; i < 10; i++) {
                    System.out.print("\n" + i + " | ");
                    for (int j = 0; j < 10; j++) {
                        System.out.print(j1.getTableroJugador().getCasilla(i, j) + " | ");
                    }
                    System.out.print("\n  + - + - + - + - + - + - + - + - + - + - +");
                }

                System.out.println("\n\nTurno del jugador 1 para disparar");
                System.out.println("Tus disparos son: ");
                for (int i = 0; i < j1.getDisparos().size(); i++) {
                    System.out.print(j1.getDisparos().get(i) + " ");
                }
                boolean FueraLimites = false;
                do {
                    System.out.println("\nIntroduzca coordenada X: ");
                    x = sc.nextInt();
                    System.out.println("Introduzca coordenada Y: ");
                    y = sc.nextInt();

                    if (x < 0 || x > 9 || y < 0 || x > 9) {
                        FueraLimites = true;
                        System.out.println("Coordenadas fuera de rango.Vuelva a introducir las coordenadas.");
                    }
                } while (FueraLimites);
                disparo = j1.Disparar(x, y);
                if (disparo) {
                    hayGanador = j1.comprobarVictoria();
                }

            } while (disparo && !hayGanador);

            if (hayGanador) {
                System.out.println("Felicidades jugador 1. Has ganado!");
                break;
            }

            do {
                System.out.println("\n\nTurno del jugador 2 para disparar");
                x = ran.nextInt(10);
                y = ran.nextInt(10);
                disparo = j2.Disparar(x, y);
                if (disparo) {
                    hayGanador = j2.comprobarVictoria();
                }
            } while (disparo && !hayGanador);

            if (hayGanador) {
                System.out.println("Felicidades jugador 2. Has ganado!");
                break;
            }
        }
    }

}
