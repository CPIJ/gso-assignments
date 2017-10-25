package aex.service;

import aex.shared.IFund;

/**
 *
 * @author Ian
 */
public final class Fund implements IFund {
    
    private final String name;
    private final double exchange;
    
    public Fund(String name, double exchange){
        this.name = name;
        this.exchange = exchange;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public double getExchange() {
        return this.exchange;
    }

    @Override
    public String toString() {
        return this.name + ": " + String.format("%.2f", this.exchange) + "     ";
    }

}
