package threading;



import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// using locks
public class ConcurrencyLockExample implements Runnable{

    private Resource resource;
    private Lock lock;

    public ConcurrencyLockExample(Resource r){
        this.resource = r;
        this.lock = new ReentrantLock();
    }

    @Override
    public void run() {
        try {
            if(lock.tryLock(10, TimeUnit.SECONDS)){
                resource.doSomething();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally{
            //release lock
            lock.unlock();
        }
        resource.doLogging();
    }

}


// using synchronized
class SynchronizedLockExample implements Runnable{

    private Resource resource;

    public SynchronizedLockExample(Resource r){
        this.resource = r;
    }

    @Override
    public void run() {
        synchronized (resource) {
            resource.doSomething();
        }
        resource.doLogging();
    }
}



class Resource {

    public void doSomething(){
        //do some operation, DB read, write etc
    }

    public void doLogging(){
        //logging, no need for thread safety
    }
}



