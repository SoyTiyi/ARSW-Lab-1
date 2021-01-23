package edu.eci.arsw.threads;

import java.util.LinkedList;

import edu.eci.arsw.spamkeywordsdatasource.HostBlacklistsDataSourceFacade;


public class BlackListThread extends Thread {
	private String ipadress;
	private int ocurrencesCount;
	private int checkedListCount;
	private int alarmCount;
	private int firstServer;
	private int lastServer;
	private HostBlacklistsDataSourceFacade skds;

	/*
	 * This LinkedList have the servers number when the ipaddres appers
	 * */
	private LinkedList<Integer> servers = new LinkedList<>();

	public LinkedList<Integer> getServers() {
		return servers;
	}

	public void setServers(LinkedList<Integer> servers){
		this.servers = servers;
	}

	public int getOcurrencesCount(){
		return ocurrencesCount;
	}

	public void setOcurrencesCount(int ocurrencesCount){
		this.ocurrencesCount = ocurrencesCount;
	}

	public int getCheckedListCount(){
		return checkedListCount;
	}

	public BlackListThread() {

	}

	public BlackListThread(String ipadress,int ocurrencesCount,int checkedListCount,int alarmCount,int firstServer,int lastServer, HostBlacklistsDataSourceFacade skds) {
		this.ipadress = ipadress;
		this.ocurrencesCount = ocurrencesCount;
		this.checkedListCount = checkedListCount;
		this.alarmCount = alarmCount;
		this.firstServer = firstServer;
		this.lastServer = lastServer;
		this.skds = skds;
	}

	public void run(){
		for(int i=firstServer; i<lastServer && ocurrencesCount < alarmCount ; i++){
			if(skds.isInBlackListServer(i, ipadress)){
				servers.add(i);
				ocurrencesCount++;
			}	
		}		
	}
}
