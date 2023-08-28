package hr.apisit.thread;

import hr.apisit.domain.Owner;

import java.util.List;

public class PrintOwnerThread implements Runnable {

    List<Owner> ownerList;

    public PrintOwnerThread(List<Owner> ownerList) {
        this.ownerList = ownerList;
    }

    @Override
    public void run() {

        while (true) {
            try {
                System.out.println("--------------------------------");
                for (Owner o : ownerList) {
                    System.out.println(o.getIme() + ", " + o.getPrezime() + ", " + o.getDatumRodenja() + ", " + o.getOib());
                }
                System.out.println("--------------------------------");
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
