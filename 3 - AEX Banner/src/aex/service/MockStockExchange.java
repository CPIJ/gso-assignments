package aex.service;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Ian
 */
public class MockStockExchange implements IStockExchange {
    
    private Random random = new Random();
    
    private double randomizeExchange(double baseline) {
        return baseline + (random.nextDouble() * 5) - 2.5;
    }

    @Override
    public List<IFund> getStocks() {
        return Arrays.asList(new Fund[]{
            new Fund("Aalberts", randomizeExchange(41)),
            new Fund("ABN AMRO", randomizeExchange(25)),
            new Fund("Aegon", randomizeExchange(4)),
            new Fund("ASML", randomizeExchange(144)),
            new Fund("Heineken", randomizeExchange(52)),
            new Fund("ING", randomizeExchange(1000)),
            new Fund("KPN", randomizeExchange(3)),
            new Fund("Philips", randomizeExchange(2.5)),
            new Fund("Randstad", randomizeExchange(100)),
            new Fund("Shell", randomizeExchange(50)),
            new Fund("Unilever", randomizeExchange(100))
        });
    }

}
