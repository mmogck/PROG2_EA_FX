package control.gamemanagement;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * ThreadController Description:
 *
 * The thread controller handles all threads in this application. You can simple
 * add or remove threads by name.
 *
 * @author Felix Busch
 */
public class ThreadController
{

    private int maxThreads;
    private int currentThreadCount;
    private HashMap threadMap;

    public ThreadController(int maxThreads)
    {
        this.maxThreads = maxThreads;
        this.threadMap = new HashMap();
        this.currentThreadCount = 0;
    }

    /**
     * This method starts a new thread in the ThreadController. First we check
     * if it's allowed to add a new thread.
     *
     * You need to specify a name as a string for the new thread. Thats means
     * that you're also able to remove the thread by name.
     *
     * @return: return code 0=Failure, 1=Success
     */
    public int addThread(String threadName, Runnable thread)
    {
        if (this.currentThreadCount == this.maxThreads)
        {
            return 0;
        }

        threadMap.put(threadName, thread);
        this.currentThreadCount++;
        thread.run();

        return 1;
    }

    /**
     * This method removes a running thread in the ThreadController. We iterate
     * through the hashmap and search for the thread name. The hashmap is our
     * data structure that maps a string to a thread.
     *
     * If the thread is active, then we terminte the thread and remove it from
     * the hashmap.
     *
     * @return: return code 0=Failure, 1=Success
     */
    public int removeThread(String threadName)
    {
        if (this.currentThreadCount == 0)
        {
            return 0;
        }

        Set threadSet = threadMap.entrySet();
        Iterator it = threadSet.iterator();

        while (it.hasNext())
        {
            Map.Entry entry = (Map.Entry) it.next();
            if (entry.getKey().toString().contains(threadName))
            {
                Thread foundThread = (Thread) entry.getValue();
                if (foundThread.isAlive())
                {
                    foundThread.interrupt();
                }

                threadMap.remove(threadName);
                this.currentThreadCount -= 1;
                return 1;
            }
        }

        return 0;
    }
}
