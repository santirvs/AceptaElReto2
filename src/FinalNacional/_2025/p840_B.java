package FinalNacional._2025;


import java.util.Scanner;

public class p840_B {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int valor = sc.nextInt();

        while (valor != 0) {
            int num= sc.nextInt();
            while (num != 0) {
                valor *= num;
                num=sc.nextInt();
            }
            System.out.println(valor);
            valor = sc.nextInt();
        }

    }

}