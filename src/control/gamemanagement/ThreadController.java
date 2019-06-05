package control.gamemanagement;

import java.util.Map;
import java.util.Set;
import java.util.Iterator;
import java.util.HashMap;

/*
 * ThreadController
 * Description:
 *
 * The thread controller handles all threads in this application.
 * You can simple add or remove threads by name.
*/

public class ThreadController
{
    private int maxThreads;
    private int currentThreadCount;
    HashMap threadMap;

    public ThreadController(int maxThreads)
    {
        this.maxThreads = maxThreads;
        this.threadMap = new HashMap();
        this.currentThreadCount = 0;
    }

    public int addThread(String threadName, Runnable thread)
    {
        if (this.currentThreadCount == this.maxThreads)
            return 0;

        threadMap.put(threadName, thread);
        this.currentThreadCount++;
        thread.run();

        return 1;
    }

    public int removeThread(String threadName)
    {
        if (this.currentThreadCount == 0)
            return 0;

        Set threadSet = threadMap.entrySet();
        Iterator it = threadSet.iterator();

        while (it.hasNext())
        {
            Map.Entry entry = (Map.Entry)it.next();
            if (entry.getKey().toString().contains(threadName))
            {
                Thread foundThread = (Thread)entry.getValue();
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