package aex.service;

import java.rmi.RemoteException;

/**
 *
 * @author Ian
 */
public class Main {

    public static void main(String[] args) throws RemoteException {
        StockExchange stockExchange = new StockExchange();
        stockExchange.startBroadcast();
    }
}
