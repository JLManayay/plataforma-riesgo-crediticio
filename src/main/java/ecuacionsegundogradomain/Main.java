package ecuacionsegundogradomain;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        double a, b, c, discriminante;

        System.out.print("Ingrese a: ");
        a = sc.nextDouble();
        System.out.print("Ingrese b: ");
        b = sc.nextDouble();
        System.out.print("Ingrese c: ");
        c = sc.nextDouble();

        if (a == 0) {
            System.out.println("Error: a no puede ser 0.");
            return;
        }

        discriminante = (b * b) - (4 * a * c);

        if (discriminante > 0) {
            double x1 = (-b + Math.sqrt(discriminante)) / (2 * a);
            double x2 = (-b - Math.sqrt(discriminante)) / (2 * a);

            System.out.println("Dos raices reales diferentes:");
            System.out.println("x1 = " + x1);
            System.out.println("x2 = " + x2);
        } else if (discriminante == 0) {
            double x = (-b) / (2 * a);
            System.out.println("Una raiz real doble:");
            System.out.println("x = " + x);
        } else {
            System.out.println("No existen raices reales.");
        }
    }
}
