package seedu.address.logic.commands.results;

public class HelpCommandResult extends CommandResult {

    public HelpCommandResult(String feedbackToUser) {
        super(feedbackToUser);
    }

    @Override
    public boolean isShowHelp() {
        return true;
    }
}
