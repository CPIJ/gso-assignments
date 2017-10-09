package aex.service;

import aex.shared.IFund;
import aex.shared.IStockExchange;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Ian
 */
public class StockExchange extends UnicastRemoteObject implements IStockExchange {

    private Random random = new Random();

    public StockExchange() throws RemoteException {

    }

    private double randomizeExchange(double baseline) {
        return baseline + (random.nextDouble() * 5) - 2.5;
    }

    @Override
    public List<IFund> getExchanges() {
        return Arrays.asList(new Fund[]{
            new Fund("2Aalberts", randomizeExchange(41)),
            new Fund("2ABN AMRO", randomizeExchange(25)),
            new Fund("2Aegon", randomizeExchange(4)),
            new Fund("2ASML", randomizeExchange(144)),
            new Fund("2Heineken", randomizeExchange(52)),
            new Fund("2ING", randomizeExchange(1000)),
            new Fund("2KPN", randomizeExchange(3)),
            new Fund("2Philips", randomizeExchange(2.5)),
            new Fund("2Randstad", randomizeExchange(100)),
            new Fund("2Shell", randomizeExchange(50)),
            new Fund("2Unilever", randomizeExchange(100))
        });
    }

}
