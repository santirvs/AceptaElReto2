package CalentamientoNavideno;

import java.util.Scanner;

/**
 *
 * @author santi
 * @date 17/12/2025
 */
public class p364_EspionajeEnNavidad {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String linea = scan.nextLine();
        while (!linea.equals("FIN")) {
            for (int pos=0; pos < linea.length(); pos++) {
                char c = linea.charAt(pos);
                if (c=='Z') c='A';
                else if (c!=' ') c++;
                System.out.print(c);
            }
            System.out.println();
            linea = scan.nextLine();
        }

    }

}
