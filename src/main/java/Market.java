import java.util.ArrayList;
import java.util.List;

public class Market {

    private final int targetSalesCount = 10;
    private Dealer dealer = new Dealer(this);
    private List<Car> cars = new ArrayList<>(targetSalesCount);

    public List<Car> getCars() {
        return cars;
    }

    public int getTargetSalesCount() {
        return targetSalesCount;
    }

    public void sellCar() {
       dealer.sellCar();
    }

    public void deliverCar () {
        dealer.produceCars();
    }
}
