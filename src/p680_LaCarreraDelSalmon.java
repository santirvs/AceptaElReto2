import java.util.Scanner;

/**
 *
 * @author santi
 * @since 27/12/2025
 *
 *
 * v1.- Recuperado un envío del 19/03/2024 - RTE
 *
 * v2.- Veo la similitud con el problema de "Caracoles del pozo".
 *      Lo copio del JO-EL y lo adapto.   -> AC
 *
 *
 */
public class p680_LaCarreraDelSalmon {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        //Comptador de casos
        int casos = scan.nextInt();

        //Bucle de casos
        for (int i = 0; i < casos; i++) {
            int distancia, remonta, desciende;
            int numDias = 0;
            boolean haLlegado = false;
            distancia = scan.nextInt();
            remonta = scan.nextInt();
            desciende = scan.nextInt();
            if (desciende >= remonta && remonta < distancia) {
                // Si desciende más de lo que remonta y no llega a salir el primer dia
                System.out.println("IMPOSIBLE");
            } else {
                // Remonta más de lo que desciende. Hacer bucle o calcular directo
                while (haLlegado != true) {
                    //Restar lo que remonta a la distancia
                    distancia -= remonta;
                    numDias++;
                    //Ha llegado al final?
                    if (distancia <= 0) {
                        haLlegado = true;
                    } else {
                        //NO--> Descansa. La distancia se incrementa ya que desciende
                        distancia += desciende;
                    }
                }
                System.out.println(numDias);
            }
        }
        scan.close();
    }

}