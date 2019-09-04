import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class Deadline extends Task {
    private String by;
    private Date date;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.date = getDate(by);
    }

    @Override
    public String toString() {
        return (getDate(by) == null) ? "[D]" + super.toString() + "(by: " + by + ")" : "[D]" + super.toString() + "(by: " + getDateString(date) + ")";
    }

    public Date getDate() {
        return date;
    }

    private String getDateString(Date date) {
        if (date == null)
            return by;
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        String pattern = by.length() > 11 ? "d'" + getDaySuffix(localDate.getDayOfMonth()) + "' 'of' MMMM yyyy, ha " : "d'" + getDaySuffix(localDate.getDayOfMonth()) + "' 'of' MMMM yyyy";
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        return formatter.format(date);
    }

    public String printInFile() {
        return this.isDone() ? "D|1|" + this.getDescription() + "|" + this.getDateString(date) : "D|0|" + this.getDescription() + "|" + this.getDateString(date);
    }
}