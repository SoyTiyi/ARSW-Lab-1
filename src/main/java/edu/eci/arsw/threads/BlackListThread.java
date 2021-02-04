package edu.eci.arsw.threads;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

import edu.eci.arsw.spamkeywordsdatasource.HostBlacklistsDataSourceFacade;

public class BlackListThread extends Thread {
	private String ipadress;
	private AtomicInteger ocurrencesCount;
	private AtomicInteger checkedListCount;
	private int alarmCount;
	private int firstServer;
	private int lastServer;
	private HostBlacklistsDataSourceFacade skds;
	private boolean stop;

	/*
	 * This LinkedList have the servers number when the ipaddres appers
	 */
	private LinkedList<Integer> servers = new LinkedList<>();

	public LinkedList<Integer> getServers() {
		return servers;
	}

	public void setServers(LinkedList<Integer> servers) {
		this.servers = servers;
	}

	public AtomicInteger getOcurrencesCount() {
		return ocurrencesCount;
	}

	public void setOcurrencesCount(AtomicInteger ocurrencesCount) {
		this.ocurrencesCount = ocurrencesCount;
	}

	public AtomicInteger getCheckedListCount() {
		return checkedListCount;
	}

	public BlackListThread() {

	}

	public BlackListThread(String ipadress, AtomicInteger ocurrencesCount, AtomicInteger checkedListCount,
			int alarmCount, int firstServer, int lastServer, HostBlacklistsDataSourceFacade skds) {
		this.ipadress = ipadress;
		this.ocurrencesCount = ocurrencesCount;
		this.checkedListCount = checkedListCount;
		this.alarmCount = alarmCount;
		this.firstServer = firstServer;
		this.lastServer = lastServer;
		this.skds = skds;
	}

	public void run() {
		for (int i = firstServer; i < lastServer && ocurrencesCount.get() < alarmCount; i++) {
			checkedListCount.incrementAndGet();
			if (skds.isInBlackListServer(i, ipadress)) {
				servers.add(i);
				ocurrencesCount.incrementAndGet();	
			}
		}
	}
}
