package aex.client;

import aex.shared.IFund;
import fontyspublisher.IRemotePropertyListener;
import fontyspublisher.IRemotePublisherForListener;
import java.beans.PropertyChangeEvent;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BannerController extends UnicastRemoteObject implements IRemotePropertyListener {

    private Registry registry;
    private AEXBanner banner;
    private IRemotePublisherForListener publisher;

    private static final int PORT = 1337;
    private static final String STOCK_EXCHANGE = "StockExchange";
    private static final String STOCK_EXCHANGE_PUBLISHER = "StockExchangePublisher";

    public BannerController(AEXBanner banner) throws RemoteException {
        this.banner = banner;

        try {
            registry = LocateRegistry.getRegistry(PORT);
        } catch (RemoteException ex) {
            System.err.println("Couldn't connect to remote " + ex.getMessage());
        }

        try {
            publisher = (IRemotePublisherForListener) registry.lookup(STOCK_EXCHANGE_PUBLISHER);
            publisher.subscribeRemoteListener(this, STOCK_EXCHANGE);
        } catch (RemoteException ex) {
            System.err.println("Couldn't lookup exchanges from remote " + ex.getMessage());
        } catch (NotBoundException ex) {
            System.err.println("Exchanges aren't bound in remote " + ex.getMessage());
        }
    }

    public void stop() {
        try {
            publisher.unsubscribeRemoteListener(this, STOCK_EXCHANGE);
        } catch (RemoteException ex) {
            Logger.getLogger(BannerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) throws RemoteException {
        List<IFund> stockExchange = (List<IFund>) evt.getNewValue();

        StringBuilder sb = new StringBuilder();
        stockExchange.forEach(fund -> sb.append(fund.toString()));

        banner.setExchanges(sb.toString());
    }
}
