public class Main {
    public static void main(String[] args) {

        final Market market = new Market();

        for (int i = 0; i < market.getTargetSalesCount(); i++) {
            new Thread(null, market::sellCar, "Покупатель " + (i + 1)).start();
            try {
                Thread.sleep(2000);
            } catch (Exception e) {

            }
        }

        new Thread(null, market::deliverCar, "Honda").start();
    }
}
