public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    public String printInFile() {
        return this.isDone() ? "T|1|" + this.getDescription() : "T|0|" + this.getDescription();
    }
}