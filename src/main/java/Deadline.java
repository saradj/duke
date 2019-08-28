public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + by + ")";
    }

    @Override
    public String printInFile() {
        return this.isDone ? "D|1|" + this.getDescription() + "|" + this.by : "D|0|" + this.getDescription() + "|" + this.by;
    }
}
