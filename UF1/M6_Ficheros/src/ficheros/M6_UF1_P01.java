/**
 * Nombre: Ivan Ivanov
 *
 * Fecha: 28/09/2018
 */
package ficheros;

import Engine.Engine;
import static Engine.Engine.Colors.*;
import static Engine.Engine.scan;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileTime;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class M6_UF1_P01 {

    static String separator, folderPath, filename;

    public static void main(String[] args) throws IOException {
        String[] menuOptions = {
            "Create folder",
            "Rename folder",
            "Create file",
            "Check file info",
            "Rename file",
            "Delete folder"
        };
        String option;
        String filename, text = null;
        separator = File.separator;
        do {
            Engine.showMenu(menuOptions);
            option = scan.nextLine();

            switch (option) {
                case "1":
                    // Create folder:
                    System.out.println("Folder name:");
                    String folderName = scan.nextLine();
                    try {
                        createDir(folderName);
                    } catch (Exception e) {
                        System.out.println("Failed to create the folder (folder already exists).");
                    }
                    break;

                case "2":
                    System.out.println("Enter full path to the folder:");
                    String dirPath = scan.nextLine();
                    try {
                        renameDir(dirPath);
                    } catch (Exception e) {
                        System.out.println("Failed to create the folder (folder already exists).");
                    }
                    break;

                case "3":

                    // Create file:
                    System.out.println("Enter full path to the folder:");
                    dirPath = scan.nextLine();
                    System.out.println("File name:");
                    filename = scan.nextLine();
                    System.out.println("File text:");
                    text = scan.nextLine();

                    filename = dirPath + separator + filename + ".txt";
                     {
                        try {
                            // Call the method
                            createFile2(filename, text);
                        } catch (Exception e) {
                            System.out.println("Failed to create file.");
                        }
                    }
                    break;

                case "4":
                    System.out.println("Enter full path to the file:");
                    filename = scan.nextLine();
                    String filePath = filename + ".txt";
                    filePath = scan.nextLine();
                    checkPermissions(filePath);
                    break;

                case "5":
                    System.out.println("Enter full path to the file:");
                    filePath = scan.nextLine();
                    String filePath2 = filePath + ".txt";
                    try {
                        renameFile(filePath2);
                    } catch (Exception e) {
                        System.out.println("Failed to create the folder (folder already exists).");
                    }
                    break;

                case "6":
                    System.out.println("Enter full path to the folder:");
                    dirPath = scan.nextLine();
                    File f = new File(dirPath);
                    try {
                        deleteDir(f);
                    } catch (Exception e) {
                        System.out.println("Failed to create the folder (folder already exists).");
                    }
                    break;

                case "0":
                    System.out.println("Closing...");
                    System.exit(0);
                    break;
            }
        } while (option != "0");
    }

    private static void createDir(String name) {
        // Variables:
        String path = "C:\\Temp";
        separator = File.separator;
        folderPath = path + separator + name;
        File carpeta = new File(folderPath);
        if (!carpeta.exists()) {
            carpeta.mkdir();
        } else {
            System.out.println(RED + "The folder already exists." + RESET);
        }
    }

    private static void renameDir(String dirPath) {
        File dir = new File(dirPath);
        if (!dir.isDirectory()) {
            System.err.println("There is no directory at given path");
        } else {
            System.out.println("Enter new name of directory(Only Name and Not Path).");
            String newDirName = scan.nextLine();
            File newDir = new File(dir.getParent() + separator + newDirName);
            dir.renameTo(newDir);
        }
    }

    public static void createFile2(String fileName, String text) throws IOException {
        // 1 - Open file:
        FileWriter fw = new FileWriter(fileName);
        BufferedWriter bw = new BufferedWriter(fw);
        // 2 - Write on file:
        bw.write(text);
        // 3 - Close file:
        bw.flush(); // Clean memory.
        bw.close();
    }

    private static void checkPermissions(String filePath) throws IOException {
        File f = new File(filePath);
        Path path = Paths.get(filePath);

        if (f.canWrite()) {
            System.out.println("Writeable: yes");
        } else {
            System.out.println("Writeable: no");
        }
        if (f.canRead()) {
            System.out.println("Readable: yes");
        } else {
            System.out.println("Readable: no");
        }
        if (f.isHidden()) {
            System.out.println("Hidden: yes");
        } else {
            System.out.println("Hidden: no");
        }

        long size = f.getTotalSpace();
        System.out.println("Size: ");
        System.out.println(size + " bytes");
        FileTime fileTime;
        fileTime = Files.getLastModifiedTime(path);
        System.out.println("Last modified date: ");
        printFileTime(fileTime);

    }

    private static void printFileTime(FileTime fileTime) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy - hh:mm:ss");
        System.out.println(dateFormat.format(fileTime.toMillis()));
    }

    private static void renameFile(String filePath2) {
        File file = new File(filePath2);
        if (!file.isFile()) {
            System.err.println("There is no file at given path.");
        } else {
            System.out.println("Enter new name of file(Only Name and Not Path).");
            String newFileName = scan.nextLine();
            File newFile = new File(file.getParent() + separator + newFileName);
            file.renameTo(newFile);
        }
    }

    private static void deleteDir(File f) throws Exception {
        try {
            if (f.isDirectory()) {
                for (File f2 : f.listFiles()) {
                    deleteDir(f2);
                }
            }
            if (!f.delete()) {
                throw new Exception("Delete command returned false for file: " + f);
            }
        } catch (Exception e) {
            throw new Exception("Failed to delete the folder: " + f, e);
        }
    }
}
