package seedu.address.logic.commands.results;

public class ExitCommandResult extends CommandResult {

    public ExitCommandResult(String feedbackToUser) {
        super(feedbackToUser);
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
