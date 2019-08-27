import java.util.ArrayList;
import java.util.List;
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
        List<Task> tasks = new ArrayList<>();
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
                if (tasks.isEmpty())
                    System.out.println("\t No tasks yet!");
                else {
                    System.out.println("\t Here are the tasks in your list:");
                    for (int i = 1; i <= tasks.size(); i++) { // looping to print all the saved tasks
                        System.out.println("\t" + i + ".[" + tasks.get(i - 1).getStatusIcon() + "] " + tasks.get(i - 1).getDescription());
                    }
                }
            } else {
                if (input.isBlank())
                    System.out.println("Please enter a valid task");
                else if (input.length() > 5 && input.substring(0, 4).equals("done")) {
                    int taskNb = Integer.parseInt(input.substring(5));
                    System.out.println("\t Nice! I've marked this task as done:");
                    if (taskNb < tasks.size() && taskNb > 0) {
                        tasks.get(taskNb - 1).markAsDone();
                        System.out.println("\t [\u2713] " + tasks.get(taskNb - 1).getDescription());
                    } else System.out.println("Enter a valid task number");
                } else {
                    System.out.println("\t added: " + input);
                    tasks.add(new Task(input));// Adding a task
                }
            }
            System.out.println("\t" + line);
            input = scanner.nextLine();  // Read next user input
        }
        System.out.println("\t" + line);
        System.out.println("\t Bye. Hope to see you again soon!");
        System.out.println("\t" + line);
    }
}
