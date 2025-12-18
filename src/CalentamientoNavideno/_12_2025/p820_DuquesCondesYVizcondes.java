package CalentamientoNavideno._12_2025;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author santi
 * @date 18/12/2025
 */
public class p820_DuquesCondesYVizcondes {

    static class Posicion {
        int x;
        int y;

        Posicion(int x, int y) {
            this.x =x;
            this.y =y;
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        while (scan.hasNext()) {

            int dimX = scan.nextInt();
            int dimY = scan.nextInt();
            scan.nextLine();

            //Definir matriz
            int[][] matriz = new int[dimY][dimX];

            //Cargar matriz
            for (int y=0; y<dimY; y++) {
                String fila = scan.nextLine();
                for (int x=0; x<dimX; x++) {
                    //El codigo del carácter lo ponemos en negativo
                    matriz[y][x] = fila.charAt(x)*-1;
                }
            }

            //Definir la lista de posiciones pendientes de explorar
            List<Posicion> listaPendientes = new ArrayList<>();

            //Añadir la posición inicial
            if (matriz[0][0] == ('.' * -1)) {
                listaPendientes.add( new Posicion(0,0));
                matriz[0][0]= 1;
            }

            //Explorar todos los caminos posibles hasta que no queden más
            while (listaPendientes.size() > 0) {
                Posicion p = listaPendientes.remove(0);

                //Fila superior
                if (p.y-1 >=0) {
                    if (p.x-1 >=0 && matriz[p.y-1][p.x-1] == '.'*-1) { matriz[p.y-1][p.x-1] = matriz[p.y][p.x]+1; listaPendientes.add( new Posicion( p.x-1, p.y-1)); }
                    if ( matriz[p.y-1][p.x] == '.'*-1) { matriz[p.y-1][p.x] = matriz[p.y][p.x]+1; listaPendientes.add( new Posicion(  p.x,p.y-1)); }
                    if (p.x+1 <dimX && matriz[p.y-1][p.x+1] == '.'*-1) { matriz[p.y-1][p.x+1] = matriz[p.y][p.x]+1; listaPendientes.add( new Posicion( p.x+1,p.y-1)); }
                }
                //Misma fila
                if (p.x-1 >=0 && matriz[p.y][p.x-1] == '.'*-1) { matriz[p.y][p.x-1] = matriz[p.y][p.x]+1; listaPendientes.add( new Posicion( p.x-1,p.y)); }
                if (p.x+1 <dimX && matriz[p.y][p.x+1] == '.'*-1) { matriz[p.y][p.x+1] = matriz[p.y][p.x]+1; listaPendientes.add( new Posicion( p.x+1, p.y)); }

                //Fila inferior
                if (p.y+1 < dimY) {
                    if (p.x-1 >=0 && matriz[p.y+1][p.x-1] == '.'*-1) { matriz[p.y+1][p.x-1] = matriz[p.y][p.x]+1; listaPendientes.add( new Posicion(  p.x-1, p.y+1)); }
                    if ( matriz[p.y+1][p.x] == '.'*-1) { matriz[p.y+1][p.x] = matriz[p.y][p.x]+1; listaPendientes.add( new Posicion(  p.x, p.y+1)); }
                    if (p.x+1 <dimX && matriz[p.y+1][p.x+1] == '.'*-1) { matriz[p.y+1][p.x+1] = matriz[p.y][p.x]+1; listaPendientes.add( new Posicion(  p.x+1, p.y+1)); }
                }

            }


            //Al salir, el resultado está en la posicion dimX, dimY de la matriz
            if (matriz[dimY-1][dimX-1] >= 0 ) {
                System.out.println(matriz[dimY-1][dimX-1]);
            } else {
                System.out.println("IMPOSIBLE");
            }
        }
    }

}
