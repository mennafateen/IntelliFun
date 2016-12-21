import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class main {
    static Scanner in = new Scanner(System.in);

    public static void saveData() throws FileNotFoundException {
        Database.saveAccounts();
        Database.saveGames();
    }

    public static void displayHomePage() throws FileNotFoundException {
        int option = -1;
        System.out.println("Welcome to IntelliFun Website!\nWhat do you want to do?");

        while(!(option <= 3 && option >= 1)) {
            System.out.println("1. Login");
            System.out.println("2. Sign up");
            System.out.println("3. Exit");
            option = in.nextInt();
            in.nextLine();
            if (option == 1) Account.login(in);
            else if (option == 2) Account.signup(in);
            else if (option == 3) {
                saveData();
                System.exit(0);
            } else
                System.out.println("Please choose a valid number.");
        }
    }

    public static void displayPlayGamePage() throws FileNotFoundException {
        Database.listGames();
        int numOfGames = Database.games.size();

        int option = -1;
        while(option > numOfGames || option < 1) {
            System.out.println("Choose a game to play: ");
            option = in.nextInt();
            if (option <= numOfGames && option >= 1) {
                int s = Game.playGame(Database.games.get(option - 1).name, in);
                Game.updateScore(s);
            }
            else
                System.out.println("Please choose a valid number.");
        }
    }

    public static void displayStudentInterface() throws FileNotFoundException {
        int option = -1;
        while(option != 1 && option != 2) {
            System.out.println("1. Play Game");
            System.out.println("2. Exit");
            option = in.nextInt();
            in.nextLine();
            if (option == 1)
                displayPlayGamePage();
            else if (option == 2) {
                saveData();
                System.exit(0);
            }else
                System.out.println("Please choose a valid number.");
        }
    }

    public static void displayTeacherInterface() throws IOException {
        int option = -1;

        while(!(option <= 3 && option >= 1)) {
            System.out.println("1. Create a game");
            System.out.println("2. Play a game");
            System.out.println("3. Exit");
            option = in.nextInt();
            in.nextLine();
            if (option == 1)
                Game.createGame(in);
            else if (option == 2)
                displayPlayGamePage();
            else if (option == 3) {
                saveData();
                System.exit(0);
            } else
                System.out.println("Please choose a valid option.");
        }
    }

    public static void main(String[] args) throws IOException {

        Database.loadAccounts();
        Database.loadGames();

        displayHomePage();

        while (true) {
            if (Account.currentAccount.userType.equals("Student")) {
                displayStudentInterface();
            } else if (Account.currentAccount.userType.equals("Teacher")) {
                displayTeacherInterface();
            }
            System.out.println();
        }

    }

}
