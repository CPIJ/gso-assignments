package aex.service;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author Ian
 */
public class Main {

    private static final int PORT = 1337;
    private static final String STOCK_EXCHANGE = "StockExchange";
    private static Registry registry;

    public static void main(String[] args) throws RemoteException {
        StockExchange se = new StockExchange();
    }
}
