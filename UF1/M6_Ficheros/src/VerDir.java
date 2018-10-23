
import Engine.*;
import static Engine.Engine.scan;
import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class VerDir {

    public static void main(String[] args) {
        System.out.println(Engine.Colors.BLUE + "Ficheros en el directorio actual: " + Engine.Colors.RESET);
        try {
            System.out.println("Write path:");
            String path = scan.next();

            System.out.println("Actual path: " + path);
            Files.walk(Paths.get(path))
                    .filter(Files::isRegularFile)
                    .forEach(System.out::println);
            System.out.println("Scan terminated");
            /*File f = new File("C:\\Windows");   // �.� Directorio actual �..� Directorio padre   �c:\\temp�  nombre directorio
            File[] archivos = f.listFiles();
            for (int i = 0; i < archivos.length; i++) {

                System.out.println(archivos[i].getName());
                if (archivos[i].isFile() == false) {
                    System.out.print("/");
                    File f2 = new File("C:\\Windows/" + archivos[i].getName());
                    File[] archivos2 = f2.listFiles();
                    for (int e = 0; e < archivos2.length; e++) {
                        System.out.println(archivos2[e].getName());
                    }
                    System.out.println("---------------------");
                }
            }*/
        } catch (AccessDeniedException e) {
            System.out.println(Engine.Colors.RED + "Access denied. Closing..." + Engine.Colors.RESET);
        } catch (IOException e) {
            System.out.println(Engine.Colors.RED + "Access denied. Closing..." + Engine.Colors.RESET);
        } catch (Exception ex) {
            System.out.println(Engine.Colors.RED + "Access denied. Closing..." + Engine.Colors.RESET);
        }
    }
}
