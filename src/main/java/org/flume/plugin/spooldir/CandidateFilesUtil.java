package org.flume.plugin.spooldir;

import java.util.Map;

import com.google.common.collect.Maps;

import java.util.concurrent.locks.Lock;  
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CandidateFilesUtil {
	 private static Map<String,Boolean> history = Maps.newConcurrentMap();
	 
	 public static synchronized void add(String path){
		 history.put(path, true);
	 }
	 
	 public static synchronized boolean exist(String path){
		 return history.containsKey(path);
	 }
	 
	 private static ReadWriteLock lock = new ReentrantReadWriteLock();  
	 public static Lock lock(){
		 Lock _lock =  lock.writeLock();
		 _lock.lock();
		 return _lock;
	 }
	 
	 public static void release(Lock _lock){
		 _lock.unlock();
	 }
}
