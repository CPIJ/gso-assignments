package aex.service;

import java.util.List;

/**
 *
 * @author Ian
 */
public interface IStockExchange {

    /**
     * 
     * @return Get a list of all stocks in the StockExchange
     */
    public List<IFund> getStocks();
}
