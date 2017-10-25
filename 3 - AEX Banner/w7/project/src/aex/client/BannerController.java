package aex.client;

import aex.shared.IStockExchange;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Timer;
import java.util.TimerTask;

public class BannerController {

    private IStockExchange stockExchange;
    private Timer pollingTimer;
    private Registry registry;

    private static final int PORT = 1234;
    private static final String STOCK_EXCHANGE = "StockExchange";
    private static final int UPDATE_TIME = 2000;
    private static final int STARTUP_TIME = 0;

    public BannerController(AEXBanner banner) {
        this.pollingTimer = new Timer();

        try {
            registry = LocateRegistry.getRegistry(PORT);
        } catch (RemoteException ex) {
            System.err.println("Couldn't connect to remote " + ex.getMessage());
        }

        try {
            this.stockExchange = (IStockExchange) registry.lookup(STOCK_EXCHANGE);
        } catch (RemoteException ex) {
            System.err.println("Couldn't lookup exchanges from remote " + ex.getMessage());
        } catch (NotBoundException ex) {
            System.err.println("Exchanges aren't bound in remote " + ex.getMessage());
        }

        pollingTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                StringBuilder sb = new StringBuilder();
                try {
                    stockExchange
                            .getExchanges()
                            .forEach((fund) -> {
                                sb.append(fund.toString());
                            });
                } catch (RemoteException ex) {
                    System.err.println("Couldn't retrieve data from remote " + ex.getMessage());
                }
                banner.setExchanges(sb.toString());
            }
        }, STARTUP_TIME, UPDATE_TIME);

    }

    public void stop() {
        pollingTimer.cancel();
    }
}
