public class Main {
    public static void main(String[] args) {

        final int productionCarTimeout = 5000;

        final Market market = new Market();

        new Thread(null, market::sellCar, "Покупатель 1").start();
        new Thread(null, market::sellCar, "Покупатель 2").start();
        new Thread(null, market::sellCar, "Покупатель 3").start();

        for (int i = 0; i < market.getTargetSalesCount(); i++) {
            try {
                new Thread(null, market::deliverCar, "Honda").start();
                Thread.sleep(productionCarTimeout);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }


    }
}
