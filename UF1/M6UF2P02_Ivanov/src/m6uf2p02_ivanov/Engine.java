/*
* @author: Iván Ivanov Marín
* @date: 07/02/2018
 */
package m6uf2p02_ivanov;

// Libs:
import java.util.Scanner;

public class Engine {

    public static Scanner scan = new Scanner(System.in);
    //Scan to get input

    public static enum Colors {
        RESET {
            public String toString() {
                return "\u001B[0m";
            }
        },
        BLACK {
            public String toString() {
                return "\u001B[30m";
            }
        },
        RED {
            public String toString() {
                return "\u001B[31m";
            }
        },
        GREEN {
            public String toString() {
                return "\u001B[32m";
            }
        },
        YELLOW {
            public String toString() {
                return "\u001B[33m";
            }
        },
        BLUE {
            public String toString() {
                return "\u001B[34m";
            }
        },
        PURPLE {
            public String toString() {
                return "\u001B[35m";
            }
        },
        CYAN {
            public String toString() {
                return "\u001B[36m";
            }
        },
        WHITE {
            public String toString() {
                return "\u001B[37m";
            }
        },
    };

    //Print Menu 
    public static void showMenu(String[] menuOptions) {
        System.out.println("\n");
        int count = 0;
        for (String s : menuOptions) {
            count++;
            System.out.println(count + ". " + s);
        }
        System.out.println("0. Exit program");
        System.out.println("Choose an option");
    }

    /*
        Get a numeric input from console, and ensure that a number is given.
     */
    public static double readNum() {
        double temp = 0;
        boolean check = false;
        while (!check) {
            try {
                temp = Integer.parseInt(scan.nextLine());
                check = true;
            } catch (NumberFormatException e) {
                //e.printStackTrace();
                System.out.print("\nPlease input a valid number: \t");
                check = false;
            }
        }
        return temp;
    }

    public static double dcFormat(double num) {
        int factor = (int) Math.pow(10, num);
        return (Math.round(num * factor) / factor);
    }
}
