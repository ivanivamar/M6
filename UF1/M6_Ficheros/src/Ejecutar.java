
public class Ejecutar {

    public static void main(String[] args) {
        Runtime r = Runtime.getRuntime();
        String comando1 = "NOTEPAD";
        Process p;
        try {
            p = r.exec(comando1);
            comando1 = "CALC";
            p = r.exec(comando1);
        } catch (Exception e) {
            System.out.println("Error en: " + comando1);
            e.printStackTrace();
        }
    }
}
