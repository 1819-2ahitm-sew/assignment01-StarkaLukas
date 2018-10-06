import java.lang.reflect.Array;
import java.util.Scanner;

public class Starka_Taschenrechner {
    public static void main(String[] args) {

        //Variablendeklaration
        Scanner scanner = new Scanner(System.in);
        String[] cache = new String[214748369];
        String calculation = "";
        int i = 0;
        char operator = '0';
        double solution = 0.0;
        boolean pn = false;
        boolean firstTime = true;

        System.out.printf("Calculator%n==========%n%nEingabe: ");
        cache[i] = scanner.nextLine();
        calculation = cache[i];

        while (!calculation.equals("quit")) {
            if ((correctFormat(calculation)) || (calculation.equals("p")) || (calculation.equals("n"))) {
                if (calculation.equals("p")) {
                    if (i >= 1) {
                        pn = true;
                        if (firstTime) {
                            i -= 2;
                            firstTime = false;
                        }
                        else{
                            i = i - 1;
                        }
                        calculation = cache[i];
                        operator = operator(calculation);
                        solution = calculation(calculation, operator);
                        printCalculation(solution, calculation, i, pn);
                    } else {
                        System.out.printf("Die Aktion %s kann hier nicht ausgeführt werden%nEingabe: ", calculation);
                    }
                }else if (calculation.equals("n")) {
                    pn = true;
                    i += 1;
                    calculation = cache[i];
                    if (calculation != null) {
                        operator = operator(calculation);
                        solution = calculation(calculation, operator);
                        printCalculation(solution, calculation, i, pn);
                    }
                    else{
                        System.out.printf("Die Aktion n kann hier nicht ausgeführt werden%nEingabe: ");
                    }
                } else {
                    operator = operator(calculation);
                    solution = calculation(calculation, operator);
                    printCalculation(solution, calculation, i, pn);
                    i++;
                    System.out.printf("Eingabe: ");
                }

            } else {
                System.out.printf("Ungültige Eingabe! Bitte geben Sie die Rechnung im Format <Gleitkommazahl>_<Operator>_<Gleitkommazahl> ein: ");
            }
            calculation = scanner.nextLine();
            if (correctFormat(calculation)) {
                cache[i] = calculation;
            }
            pn = false;
        }

    }

    private static void printCalculation(double solution,String calculation,int i, boolean pn) {

        System.out.printf("Berechnung");
        if (pn){
            System.out.printf(" %d",i += 1);
        }
        System.out.printf(": %s = ", calculation);


        if (solution - (int) solution == 0) {
            System.out.printf("%.0f%n", solution);
        }
        else{
            System.out.printf("%f%n", solution);
        }
    }

    private static double calculation(String calculation, char operator) {
        double solution = 0;

        String[] part = calculation.split(" ");

        double number1 = Integer.parseInt(part[0]);
        double number2 = Integer.parseInt(part[2]);

        switch (operator) {
            case '+':
                solution = number1 + number2;
                break;
            case '-':
                solution = number1 - number2;
                break;
            case '*':
                solution = number1 * number2;
                break;
            case '/':
                solution = number1 / number2;
                break;
        }

        return solution;
    }

    private static char operator(String calculation) {
        char operator = ' ';
        char actuallLetter;

        for (int i = 0; i < calculation.length(); i++) {
            actuallLetter = calculation.charAt(i);
            if ((actuallLetter == '+') || (actuallLetter == '-') || (actuallLetter == '*') || (actuallLetter == '/')) {
                operator = actuallLetter;
            }
        }
        return operator;
    }

    private static boolean correctFormat(String calculation) {
        boolean correctFormat = false;
        char actuallLetter;
        int count = 0;
        int j = 0;
        int k = 0;

        for (int i = 0; i < calculation.length(); i++) {
            actuallLetter = calculation.charAt(i);
            if ((actuallLetter == '+') || (actuallLetter == '-') || (actuallLetter == '*') || (actuallLetter == '/')) {
                j = i - 1;
                k = i + 1;

                if ((calculation.charAt(j) == ' ') && (calculation.charAt(k) == ' ')) {
                    correctFormat = true;
                }

            } else if (actuallLetter == ' ') {
                count++;
            }
        }
        if (count != 2) {
            correctFormat = false;
        }

        return correctFormat;
    }
}
