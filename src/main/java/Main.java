public class Main {

    public static void main(String[] args) throws InterruptedException {

        //поток который будет выполняться каждую секунду
        Thread out1 = new Thread(new Runnable() {
            public void run() {
                System.out.println("1sec");

            }
        });
        //поток который будет выполняться каждые 5 секунд
        Thread out2 = new Thread(new Runnable() {
            public void run() {
                System.out.println("5sec");

            }
        });

        //поток таймера
        Timer timer = new Timer(out1,out2);
        Thread timerThread = new Thread(timer);
        timerThread.start();

    }
}

class Timer implements Runnable{

    Thread out1;
    Thread out2;
    long saveTime = System.currentTimeMillis();
    int t=0;

    public Timer(Thread out1, Thread out2){
        this.out1 = out1;
        this.out2 = out2;
    }

    public void run() {
        while (true){
            long curTime = System.currentTimeMillis();
            if(curTime-saveTime>1000){
                saveTime = curTime;
                out1.run();
                t++;
                if(t==5){
                    out2.run();
                    t = 0;
                }
            }
        }
    }
}

