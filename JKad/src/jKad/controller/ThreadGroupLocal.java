package jKad.controller;

import java.util.concurrent.ConcurrentHashMap;

public abstract class ThreadGroupLocal<T>
{
    private ConcurrentHashMap<ThreadGroup, T> map;
    
    public ThreadGroupLocal()
    {
        map = new ConcurrentHashMap<ThreadGroup, T>();
    }
    
    public T get()
    {
        T result = null;
        ThreadGroup group = Thread.currentThread().getThreadGroup();
        result = map.get(group);
        if(result == null)
        {
            result = initialValue();
            map.put(group, result);
        }
        return result;
    }
    
    public void set(T obj)
    {
        map.put(Thread.currentThread().getThreadGroup(), obj);
    }
    
    public abstract T initialValue();
}
