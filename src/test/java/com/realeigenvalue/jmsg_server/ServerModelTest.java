package com.realeigenvalue.jmsg_server;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class ServerModelTest {
	@Test
	public void testServerModel() {
		ServerModel sm = new ServerModel();
		assertEquals("", sm.getIpAdress());
		assertEquals(-1, sm.getPortNumber());
		assertEquals(0, sm.getClients().get());
	}
	@Test
	public void testGetIpAdress() {
		ServerModel sm = new ServerModel();
		assertEquals("", sm.getIpAdress());
		sm.setIpAdress("127.0.0.1");
		assertEquals("127.0.0.1", sm.getIpAdress());
	}
	@Test
	public void testSetIpAdress() {
		ServerModel sm = new ServerModel();
		assertEquals("", sm.getIpAdress());
		sm.setIpAdress("127.0.0.1");
		assertEquals("127.0.0.1", sm.getIpAdress());
	}
	@Test
	public void testGetPortNumber() {
		ServerModel sm = new ServerModel();
		assertEquals(-1, sm.getPortNumber());
		sm.setPortNumber("1024");
		assertEquals(1024, sm.getPortNumber());
		sm.setPortNumber("nonsense");
		assertEquals(-1, sm.getPortNumber());
	}
	@Test
	public void testSetPortNumber() {
		ServerModel sm = new ServerModel();
		assertEquals(-1, sm.getPortNumber());
		sm.setPortNumber("1024");
		assertEquals(1024, sm.getPortNumber());
		sm.setPortNumber("nonsense");
		assertEquals(-1, sm.getPortNumber());
	}
}