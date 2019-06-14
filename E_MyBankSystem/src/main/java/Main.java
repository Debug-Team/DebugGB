import zmq.BankSystemMQ;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Thread threadForD = new Thread(){
            @Override
            public void run() {
                BankSystemMQ.initMQServerForD();
            }
        };
        threadForD.start();
        Thread threadForA = new Thread(){
            @Override
            public void run() {
//                System.out.println("a");
                BankSystemMQ.initMQServerForA();
            }
        };
        threadForA.start();
    }
}
