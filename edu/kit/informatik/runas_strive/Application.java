package edu.kit.informatik.runas_strive;

import edu.kit.informatik.runas_strive.ui.Session;
import edu.kit.informatik.runas_strive.ui.resources.OutputMessage;

/**
 * Represents the application. Creates the needed instances and runs the
 * interactive command session.
 * 
 * @author uogok
 * @version 23
 */
public final class Application {

    private static final String ERROR_UTILITY_CLASS_INSTANTIATION = "Utility class cannot be instantiated.";

    private Application() {
        throw new AssertionError(ERROR_UTILITY_CLASS_INSTANTIATION);
    }

    /**
     * The entry point of the game Runa's Strive. It starts a Session
     * {@link Session#start()}.
     * 
     * @param args the parameters of the session which must be empty i.e no
     *             arguments are needed to run this program, if there are arguments
     *             then the program terminates after returning an error
     *             message{@link OutputMessage#ERROR_ENTERED_ARGUMENTS}.
     */
    public static void main(String[] args) {

        if (args.length != 0) {
            System.out.println(OutputMessage.ERROR_ENTERED_ARGUMENTS.toString());
            return;
        }
        Session session = new Session();
        session.start();
    }

}
