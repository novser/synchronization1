public class Main {
    public static void main(String[] args) {

        final Market market = new Market();

        new Thread(null, market::sellCar, "Покупатель 1").start();
        new Thread(null, market::sellCar, "Покупатель 2").start();
        new Thread(null, market::sellCar, "Покупатель 3").start();

        new Thread(null, market::deliverCar, "Honda").start();
    }
}
