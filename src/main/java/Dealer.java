public class Dealer {

    private Market market;
    private final int sellingTimeout = 1000;
    private final int productionCarTimeout = 5000;

    public Dealer(Market market) {
        this.market = market;
    }

    public void produceCars() {
        String producerName = Thread.currentThread().getName();
        try {
            for (int i = 0; i < market.getTargetSalesCount(); i++) {
                synchronized (this) {
                    System.out.println("Производитель " + producerName + " начинает делать автомобиль");
                    market.getCars().add(new Car());
                    System.out.println("Производитель " + producerName + " выпустил и доставил новое авто");
                    notifyAll();
                }
                Thread.sleep(productionCarTimeout);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void sellCar() {
        String consumerName = Thread.currentThread().getName();
        synchronized (this) {
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
            market.getCars().remove(0);
        }
    }
}
