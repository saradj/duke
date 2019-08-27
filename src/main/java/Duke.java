import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Scanner scanner = new Scanner(System.in);  // Create a Scanner object
        String line = scanner.nextLine();  // Read user input
        while(!line.equals("bye")){
            System.out.println(" ____________________________________________________________");
            System.out.println(line);
            System.out.println(" ____________________________________________________________");
            line = scanner.nextLine();  // Read next user input
        }
        System.out.println(" ____________________________________________________________");
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println(" ____________________________________________________________");
    }
}
