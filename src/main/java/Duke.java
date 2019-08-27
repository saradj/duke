import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String line = "____________________________________________________________";
        //System.out.println("Hello from\n" + logo);
        String[] tasks = new String[100];
        int nbTasks = 0;
        System.out.println("\t" + line);
        System.out.println("\t Hello! I'm Duke");
        System.out.println("\t What can I do for you?");
        System.out.println("\t" + line);
        Scanner scanner = new Scanner(System.in);  // Create a Scanner object
        String input = scanner.nextLine();  // Read user input
        while (!(input.equals("bye"))) { //Continue reading until user inputs bye
            System.out.println("\t" + line);
            if (input.equals("list")) {
                if (nbTasks == 0)
                    System.out.println("\t No tasks yet!");
                else {
                    for (int i = 1; i <= nbTasks; i++) // looping to print all the saved tasks
                        System.out.println("\t " + i + ". " + tasks[i - 1]);
                }
            } else {
                System.out.println("\t added: " + input);
                tasks[nbTasks++] = input;// Adding a task
            }
            System.out.println("\t" + line);
            input = scanner.nextLine();  // Read next user input
        }
        System.out.println("\t" + line);
        if (input.equals("bye"))
            System.out.println("\t Bye. Hope to see you again soon!");
        System.out.println("\t" + line);
    }
}
