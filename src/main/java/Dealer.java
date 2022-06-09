public class Dealer {

    private Market market;
    private final int sellingTimeout = 1000;
    private final int productionCarTimeout = 5000;
    private int soldCars;

    public Dealer(Market market) {
        this.market = market;
    }

    public void produceCars() {
        String producerName = Thread.currentThread().getName();
        try {
            for (int i = 0; i < market.getTargetSalesCount(); i++) {
                System.out.println("Производитель " + producerName + " начинает делать автомобиль");
                Thread.sleep(productionCarTimeout);
                synchronized (this) {
                    market.getCars().add(new Car());
                    System.out.println("Производитель " + producerName + " выпустил и доставил новое авто");
                    notify();
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void sellCar() {
        String consumerName = Thread.currentThread().getName();

        try {
            synchronized (this) {

                while (market.getCars().size() == 0 || soldCars < market.getTargetSalesCount()) {
                    System.out.println(consumerName + " зашел в автосалон");
                    if (soldCars >= market.getTargetSalesCount()) {
                        notifyAll();
                        System.out.println("Авто закончились, " + consumerName + " вышел из автосалона");
                        return;
                    } else {
                        System.out.println("Машин нет. " + consumerName + " ждет");
                        wait();
                        if (market.getCars().size() == 0) {
                            continue;
                        } else {
                            Thread.sleep(sellingTimeout);
                            System.out.println(consumerName + " доволен покупкой");
                            market.getCars().remove(0);
                            soldCars++;
                        }
                    }
                }

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}

