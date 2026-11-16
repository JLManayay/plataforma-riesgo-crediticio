package ecuacionsegundogradomain;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

/**
 * Pruebas automatizadas del modulo piloto (Main.java, Version 5 del Anexo C).
 * Ejecuta Main con entradas simuladas y valida la salida en los tres casos:
 * dos raices reales, raiz doble y discriminante negativo, ademas del control de a = 0.
 */
public class EcuacionSegundoGradoTest {

    private static int fallos = 0;

    private static String ejecutar(String entrada) {
        InputStream inOriginal = System.in;
        PrintStream outOriginal = System.out;
        ByteArrayOutputStream salida = new ByteArrayOutputStream();
        try {
            System.setIn(new ByteArrayInputStream(entrada.getBytes()));
            System.setOut(new PrintStream(salida));
            Main.main(new String[0]);
        } finally {
            System.setIn(inOriginal);
            System.setOut(outOriginal);
        }
        return salida.toString();
    }

    private static void verificar(String caso, String entrada, String... esperados) {
        String salida = ejecutar(entrada);
        for (String esperado : esperados) {
            if (!salida.contains(esperado)) {
                System.out.println("[FALLA] " + caso + ": se esperaba \"" + esperado + "\"");
                fallos++;
                return;
            }
        }
        System.out.println("[OK] " + caso);
    }

    public static void main(String[] args) {
        verificar("Dos raices reales (a=1, b=-5, c=6)", "1\n-5\n6\n",
                "Dos raices reales diferentes", "x1 = 3.0", "x2 = 2.0");
        verificar("Raiz doble (a=1, b=2, c=1)", "1\n2\n1\n",
                "Una raiz real doble", "x = -1.0");
        verificar("Discriminante negativo (a=1, b=1, c=1)", "1\n1\n1\n",
                "No existen raices reales");
        verificar("Control de a = 0", "0\n2\n1\n",
                "Error: a no puede ser 0");

        assert fallos == 0 : fallos + " prueba(s) fallaron";
        if (fallos > 0) {
            System.exit(1);
        }
        System.out.println("Todas las pruebas pasaron correctamente.");
    }
}
