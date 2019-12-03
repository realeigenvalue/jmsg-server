package com.realeigenvalue.jmsg_server;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

public class ServerView extends JFrame {
	private JLabel clientLabel;
	private JTextArea feedArea;
	public ServerView() {
		clientLabel = new JLabel("Number of Clients: 100");
		feedArea = new JTextArea(10, 64);
		feedArea.setEditable(false);
		JScrollPane feedScrollPane = new JScrollPane(feedArea);
		feedScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		JPanel mainPanel = new JPanel();
		mainPanel.add(clientLabel);
		mainPanel.add(feedScrollPane);
		this.setContentPane(mainPanel);
		this.pack();
		this.setTitle("jmsg-server");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	public JLabel getClientLabel() {
		return clientLabel;
	}
	public JTextArea getFeedArea() {
		return feedArea;
	}
	public static String[] startDialog() {
		JTextField ipAddress = new JTextField();
		JTextField portNumber = new JTextField();
		Object[] message = {
				"IP:", ipAddress,
				"PORT:", portNumber
		};
		Object[] options = {"START"};	
		int option = JOptionPane.showOptionDialog(null, message, "jmsg-server", 
				                                  JOptionPane.PLAIN_MESSAGE, 
				                                  JOptionPane.QUESTION_MESSAGE, 
				                                  null, options, options[0]);
		if(option == JOptionPane.CLOSED_OPTION) {
			System.exit(0);
		}
		String[] ret = {ipAddress.getText(), portNumber.getText()};
		return ret;
	}
}