package by.bsu.grabar.command.action;

import by.bsu.grabar.command.Command;

import java.util.HashMap;
import java.util.Map;

/**
 * The type Command manager.
 */
public class CommandManager {
    private static Map<Operation, Command> map = new HashMap<Operation, Command>();

    static {
        map.put(Operation.LOGIN, new LoginCommand());
        map.put(Operation.LOGOUT, new LogoutCommand());
        map.put(Operation.REGISTRATION, new RegistrationCommand());
        map.put(Operation.LANGUAGE, new LanguageCommand());
        map.put(Operation.SINGLEGAME, new SingleGameCommand());
        map.put(Operation.FORWARD, new ForwardCommand());
        map.put(Operation.ADDBALANCE, new ChangeBalanceCommand(Operation.ADDBALANCE.toString()));
        map.put(Operation.REDUCEBALANCE, new ChangeBalanceCommand(Operation.REDUCEBALANCE.toString()));
        map.put(Operation.SENDMESSAGE, new SendMessageCommand());
        map.put(Operation.GAMESTARTUPPARAM, new SetGameStartupParamCommand());
    }

    /**
     * Take command command.
     *
     * @param value the value
     * @return the command
     */
    public static Command takeCommand(String value){
        Operation operation = Operation.valueOf(value.toUpperCase());
        return map.get(operation);
    }


    private enum Operation{
        /**
         * Login operation.
         */
        LOGIN,
        /**
         * Logout operation.
         */
        LOGOUT,
        /**
         * Registration operation.
         */
        REGISTRATION,
        /**
         * Language operation.
         */
        LANGUAGE,
        /**
         * Singlegame operation.
         */
        SINGLEGAME,
        /**
         * Forward operation.
         */
        FORWARD,
        /**
         * Addbalance operation.
         */
        ADDBALANCE,
        /**
         * Reducebalance operation.
         */
        REDUCEBALANCE,
        /**
         * Sendmessage operation.
         */
        SENDMESSAGE,
        /**
         * Gamestartupparam operation.
         */
        GAMESTARTUPPARAM
    }
}
