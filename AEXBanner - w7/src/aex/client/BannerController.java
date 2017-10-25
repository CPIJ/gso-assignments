package aex.client;

import aex.service.StockExchange;
import aex.shared.IFund;
import aex.shared.IStockExchange;
import fontyspublisher.IRemotePropertyListener;
import fontyspublisher.IRemotePublisherForListener;
import java.beans.PropertyChangeEvent;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class BannerController extends UnicastRemoteObject implements IRemotePropertyListener {

    private IStockExchange stockExchange;
    private Timer pollingTimer;
    private Registry registry;
    private AEXBanner banner;

    private static final int PORT = 1337;
    private static final String STOCK_EXCHANGE = "StockExchange";
    private static final String STOCK_EXCHANGE_PUBLISHER = "StockExchangePublisher";
    private static final int UPDATE_TIME = 2000;
    private static final int STARTUP_TIME = 0;

    private IRemotePublisherForListener publisher;

    public BannerController(AEXBanner banner) throws RemoteException {
        this.pollingTimer = new Timer();
        this.banner = banner;

        try {
            registry = LocateRegistry.getRegistry(PORT);
        } catch (RemoteException ex) {
            System.err.println("Couldn't connect to remote " + ex.getMessage());
        }

        try {
            publisher = (IRemotePublisherForListener) registry.lookup(STOCK_EXCHANGE_PUBLISHER);
            publisher.subscribeRemoteListener(this, STOCK_EXCHANGE);
            //this.stockExchange = (IStockExchange) registry.lookup(STOCK_EXCHANGE);
        } catch (RemoteException ex) {
            System.err.println("Couldn't lookup exchanges from remote " + ex.getMessage());
        } catch (NotBoundException ex) {
            System.err.println("Exchanges aren't bound in remote " + ex.getMessage());
        }

        /*
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
        }, STARTUP_TIME, UPDATE_TIME);*/
    }

    public void stop() {
        pollingTimer.cancel();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) throws RemoteException {
        List<IFund> stockExchange = (List<IFund>) evt.getNewValue();
        StringBuilder sb = new StringBuilder();
        System.out.println("aex.client.BannerController.propertyChange()");
        stockExchange
                .forEach((fund) -> {
                    sb.append(fund.toString());
                });

        banner.setExchanges(sb.toString());
    }
}
