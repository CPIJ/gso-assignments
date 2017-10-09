package aex.client;

import aex.service.IStockExchange;
import aex.service.MockStockExchange;
import java.util.Timer;
import java.util.TimerTask;

public class BannerController {

    private AEXBanner banner;
    private IStockExchange effectenbeurs;
    private Timer pollingTimer;

    public BannerController(AEXBanner banner2) {

        this.banner = banner2;
        this.effectenbeurs = new MockStockExchange();
        pollingTimer = new Timer();
        
        pollingTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                StringBuilder sb = new StringBuilder();
                sb.append("");
                effectenbeurs
                        .getStocks()
                        .forEach((fund) -> {
                    sb.append(fund.toString());
                });
                banner2.setExchanges(sb.toString());
            }
        }, 0, 2000);
       
    }
    
    public void stop() {
        pollingTimer.cancel();
    }
}
