package aex.shared;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author Ian
 */
public interface IStockExchange extends Remote{

    /**
     * 
     * @return Get a list of all stocks in the StockExchange
     */
    public List<IFund> getExchanges() throws RemoteException;
}
