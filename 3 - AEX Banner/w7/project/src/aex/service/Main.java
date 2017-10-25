package aex.service;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author Ian
 */
public class Main {

    private static final int PORT = 1234;
    private static final String STOCK_EXCHANGE = "StockExchange";
    private static Registry registry;

    public static void main(String[] args) {
        try {
            registry = LocateRegistry.createRegistry(PORT);
        } catch (RemoteException ex) {
            System.err.println("Couldn't create the registry " + ex.getMessage());
        }

        try {
            registry.rebind(STOCK_EXCHANGE, new StockExchange());
        } catch (RemoteException ex) {
            System.err.println("Couldn't (re)bind the object " + ex.getMessage());
        }
    }
}
