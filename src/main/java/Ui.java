import java.util.Scanner;

public class Ui {
    Scanner scanner;
    private static final String line = "____________________________________________________________";
    String input;
    public Ui(){
        scanner= new Scanner(System.in);
    }
    public void showWelcome(){
        System.out.println("\t" + line);
        System.out.println("\t Hello! I'm Duke");
        System.out.println("\t What can I do for you?");
        System.out.println("\t" + line);
    }
    public String readCommand(){
         input = scanner.nextLine();
         return input;
    }
    public void showLine(){
        System.out.println("\t "+ line);
    }
    public void showLoadingError(){
        System.out.println("\t ☹ OOPS!!! Error while loading the list from the hard disc");
    }
    public void showError(String e){
        System.out.println(e);
    }
    public void showTask(String task){
        System.out.println(task);
    }
    public void showMarkDone(String doneTask){
        System.out.println("\t Nice! I've marked this task as done:");
        System.out.println("\t " + doneTask);

    }
    public void showAddCommand(String command, int size){
        System.out.println("\t Got it. I've added this task: ");
        System.out.println("\t " + command);
        System.out.println(size == 1 ? "\t Now you have 1 task in the list." : "\t Now you have " + size + " tasks in the list.");
    }
    public void showRemovedTask(String removed, int size){
        System.out.println("\t Noted. I've removed this task:");
        System.out.println("\t " + removed);
        System.out.println(size == 1 ? "\t Now you have " + size + " task in the list." : "\t Now you have " +size + " tasks in the list.");

    }}
