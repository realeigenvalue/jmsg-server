package com.realeigenvalue.jmsg_server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class Server {
	private ServerModel model;
	private ServerView view;
	private ArrayList<Socket> clients;
	public Server() {
		model = new ServerModel();
		while(true) {
			String[] ret = ServerView.startDialog();
			model.setIpAdress(ret[0]);
			if((model.setPortNumber(ret[1])) == true) {
				break;
			}
		}
		view = new ServerView();
		view.getClientLabel().setText("Number of Clients: 0");
		clients = new ArrayList<Socket>();
		ServerSocket server_socket = null;
		try {
			server_socket = new ServerSocket(model.getPortNumber(), 50, InetAddress.getByName(model.getIpAdress()));
		} catch (UnknownHostException e) {
		} catch (IOException e) {
		}
		while(true) {
			try {
				Socket client_socket = server_socket.accept();
				clients.add(client_socket);
				new ClientThread(client_socket).start();
			} catch(Exception e) {
			}
		}
	}
	private class ClientThread extends Thread {
		private Socket socket;
		private PrintWriter output;
		private BufferedReader input;
		public ClientThread(Socket socket) throws IOException {
			this.socket = socket;
			output = new PrintWriter (this.socket.getOutputStream(), true);
			input = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
			model.getClients().incrementAndGet();
			view.getClientLabel().setText("Number of Clients: " + model.getClients().intValue());
		}
		public void run() {
			String line;
			try {
				while((line = input.readLine()) != null) {
					syncUpdate(line); //client stays connected
				}
			} catch (IOException e) {
				model.getClients().decrementAndGet();
				view.getClientLabel().setText("Number of Clients: " + model.getClients().intValue());
			}
		}
	}
	public synchronized void syncUpdate(String message) {
		model.getFeed().add(message);
		view.getFeedArea().append(message + "\n");
		for(Socket client : clients) {
			try {
				PrintWriter output = new PrintWriter (client.getOutputStream(), true);
				output.println(message);
			} catch(Exception e) {
			}
		}
	}
}