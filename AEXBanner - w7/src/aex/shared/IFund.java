package aex.shared;

import java.io.Serializable;

/**
 *
 * @author Ian
 */
public interface IFund extends Serializable{

    /**
     * 
     * @return Returns the name of the fund
     */
    public String getName();

    /**
     * 
     * @return Returns the exchange rate of this fund 
     */
    public double getExchange();
}
