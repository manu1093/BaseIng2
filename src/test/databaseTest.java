package test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.sun.security.ntlm.Client;

import database.Database;
import database.beans.Cliente;

public class databaseTest {
private Database db=Database.getInstance();
	@Test
	public void testGetProdotto() {
		assertEquals("notebook samsung R522", db.getProdotto("ag349512").getNome());
	}

	@Test
	public void testProdottiACaso() {
		assertTrue(db.prodottiACaso(2).size()==2);;
	}

	@Test
	public void testProdottiPreferitiIntCliente() {
		assertTrue(db.prodottiPreferiti(2, (Cliente) db.login("franco89", "ciaociao")).size()==2);
	}

	@Test
	public void testProdottoAction() {
		assertTrue(db.prodottoAction(1, "azione").get(0).getCategoria().equals("azione"));
	}

	@Test
	public void testLogin() {
		assertTrue(db.login("franco89", "ciaociao") instanceof Cliente);
	}

	@Test
	public void testListaUtentiAbilitati() {
		assertTrue(db.listaUtentiAbilitati().size()>0);
	}

}
