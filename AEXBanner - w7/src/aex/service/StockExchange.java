package aex.service;

import aex.shared.IFund;
import aex.shared.IStockExchange;
import fontyspublisher.RemotePublisher;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ian
 */
public class StockExchange implements IStockExchange {

    private Random random = new Random();
    private RemotePublisher publisher;

    private static final int PORT = 1337;
    private static final String STOCK_EXCHANGE = "StockExchange";
    private static final String STOCK_EXCHANGE_PUBLISHER = "StockExchangePublisher";
    private static final int UPDATE_TIME = 2000;
    private static final int STARTUP_TIME = 0;

    public StockExchange() throws RemoteException {
        publisher = new RemotePublisher();
        publisher.registerProperty(STOCK_EXCHANGE);
        Registry registry = LocateRegistry.createRegistry(PORT);
        registry.rebind(STOCK_EXCHANGE_PUBLISHER, publisher);
        
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    getExchangesOnceAgain();
                } catch (RemoteException ex) {
                    Logger.getLogger(StockExchange.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        }, STARTUP_TIME, UPDATE_TIME);
    }

    private double randomizeExchange(double baseline) {
        return baseline + (random.nextDouble() * 5) - 2.5;
    }
    
    public void getExchangesOnceAgain() throws RemoteException {
        publisher.inform(STOCK_EXCHANGE, null, Arrays.asList(new Fund[]{
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
        }));
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
