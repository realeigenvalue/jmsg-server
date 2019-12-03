package com.realeigenvalue.jmsg_server;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ServerModel {
	private String ipAdress;
	private int portNumber;
	private AtomicInteger clients; //thread safe integer
	private List<String> feed; //thread safe List<String> => ArrayList<String>
	public ServerModel() {
		this.ipAdress = "";
		this.portNumber = -1;
		this.clients = new AtomicInteger();
		feed = Collections.synchronizedList(new ArrayList<String>());
	}
	public String getIpAdress() {
		return ipAdress;
	}
	public void setIpAdress(String ipAdress) {
		this.ipAdress = ipAdress;
	}
	public int getPortNumber() {
		return portNumber;
	}
	public boolean setPortNumber(String portNumber) {
		try {
			this.portNumber = Integer.parseInt(portNumber);
			return true;
		} catch(Exception e) {
			this.portNumber = -1;
			return false;
		}
	}
	public AtomicInteger getClients() {
		return clients;
	}
	public List<String> getFeed() {
		return feed;
	}
}