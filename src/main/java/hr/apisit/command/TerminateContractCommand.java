package hr.apisit.command;

public class TerminateContractCommand implements Command{

    @Override
    public void execute() {
        System.out.println("Request for contract termiantino invoked...");
    }
}
