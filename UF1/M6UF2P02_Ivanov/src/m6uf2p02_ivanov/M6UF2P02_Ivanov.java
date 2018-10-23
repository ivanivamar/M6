package m6uf2p02_ivanov;

import java.io.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static m6uf2p02_ivanov.Engine.scan;
import static m6uf2p02_ivanov.Engine.showMenu;

public class M6UF2P02_Ivanov {

    static File txtFile = new File("C:\\Temp\\factura.txt");

    public static void main(String[] args) throws IOException {
        String[] menuOptions = {
                "Read file",
                "Parse to CSV"
        };
        String option;
        do {
            showMenu(menuOptions);
            option = scan.nextLine();

            switch (option) {
                case "1":
                    readFile(txtFile);
                    break;

                case "2":
                    test();
                    //parseToCSV(txtFile);
                    break;
            }
        } while (option != "0");
    }

    private static void test() throws FileNotFoundException {
        Pattern p = Pattern.compile(
                "^([\\d.]+)\\s+(\\d+)\\s+([\\d.]+)\\s+(.+?)\\s+\\((\\d+)\\)(?:\\s+\\{([^{}]+))?"
        );
        Matcher m = p.matcher("");
        Scanner sc = new Scanner(new File("C:\\Temp\\factura.txt"));
        while (sc.hasNextLine())
        {
            String s = sc.nextLine();
            if (m.reset(s).find())
            {
                System.out.printf("%s %8s %6s%n%s (%s) %s%n%n",
                        m.group(1), m.group(2), m.group(3), m.group(4), m.group(5),
                        m.start(6) != -1 ? m.group(6) : "");
            }
        }
    }

    /*private static void parseToCSV(File txtFile) throws IOException {
        // 1 - Open file:
        final Path path = Paths.get("C:\\Temp\\");
        final Path txt = path.resolve("factura.txt");
        final Path csv = path.resolve("factura.csv");
        try (
                final Stream<String> lines = Files.lines(txt);
                final PrintWriter pw = new PrintWriter(Files.newBufferedWriter(csv, StandardOpenOption.CREATE_NEW))) {
            lines.map((line) -> line.split("\\|")).
                    map((line) -> Stream.of(line).collect(Collectors.joining(","))).
                    forEach(pw::println);
        }
    }*/

    private static void readFile(File file) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(txtFile));
        String st;
        while ((st = br.readLine()) != null)
            System.out.println(st);
    }
}
