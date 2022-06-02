public class Dealer {

    private Market market;
    private final int productionCarTimeout = 3000;
    private final int sellingTimeout = 1000;

    public Dealer(Market market) {
        this.market = market;
    }

    public synchronized void produceCars() {
        String producerName = Thread.currentThread().getName();
        try {
            for (int i = 0; i < market.getTargetSalesCount(); i++) {
                System.out.println("Производитель " + producerName + " начинает делать автомобиль");
                Thread.sleep(productionCarTimeout);
                market.getCars().add(new Car());
                System.out.println("Производитель " + producerName + " выпустил и доставил новое авто");
                notify();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public synchronized Car sellCar() {
        String consumerName = Thread.currentThread().getName();
        try {
            System.out.println(consumerName + " зашел в автосалон");
            while (market.getCars().size() == 0) {
                System.out.println("Машин нет. " + consumerName + " ждет");
                wait();
            }
            Thread.sleep(sellingTimeout);
            System.out.println(consumerName + " доволен покупкой");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return market.getCars().remove(0);
    }
}
