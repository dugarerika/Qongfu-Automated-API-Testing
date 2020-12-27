package exeptions;

public class IncorrectDataDrivenValues extends Exception {
	private static final long serialVersionUID = 1L;

    public IncorrectDataDrivenValues(String errorMessage) {
        super(errorMessage);
    }
}