package info.lynxnet.traversexml;

import java.util.ArrayList;
import java.util.HashMap;

public class Dispatcher {
    private HashMap<Integer, Worker> workers = new HashMap<Integer, Worker>();

    public Iterable<Worker> getWorkers() {
        return this.workers.values();
    }

    public Worker acquireWorker(int id) {
        Worker w = this.workers.getOrDefault(id, null);
        if (w == null) {
            w = new Worker(id);
            this.workers.put(id, w);
        }

        return w;
    }

    public void releaseWorker(int id) {
        Worker w = this.workers.getOrDefault(id, null);
        if (w == null)
            throw new IllegalArgumentException();

        w.dispose();
        this.workers.remove(id);
    }

    public class Worker {
        private ArrayList<String> workItems = new ArrayList<String>();

        private int id;

        public int getId() {
            return this.id;
        }

        public Iterable<String> getWorkItems() {
            return this.workItems;
        }

        public Worker(int id) {
            this.id = id;
        }

        public void doWork(String work) {
            if (this.workItems == null)
                throw new IllegalStateException(this.getClass().getName());

            this.workItems.add(work);
        }

        public void dispose() {
            this.workItems = null;
        }
    }

    public static void main(String[] args) {
        Dispatcher d = new Dispatcher();

        d.acquireWorker(1).doWork("Work11");
        d.acquireWorker(2).doWork("Work21");
        System.out.println(String.join(", ", d.acquireWorker(2).getWorkItems()));
        d.releaseWorker(2);
        d.acquireWorker(1).doWork("Work12");
        System.out.println(String.join(", ", d.acquireWorker(1).getWorkItems()));
        d.releaseWorker(1);
    }
}