public class Call {
    private String callerName;
    private String issue;

    public Call(String callerName, String issue) {
        this.callerName = callerName;
        this.issue = issue;
    }

    // This toString() method makes it easy to print the call details
    @Override
    public String toString() {
        return "Call from: " + callerName + " (Issue: " + issue + ")";
    }
}