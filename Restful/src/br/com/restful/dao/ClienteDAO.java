package br.com.restful.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.restful.bean.Cliente;

public class ClienteDAO {
	
	private static List<Cliente> clientes;
	
	public ClienteDAO() {
		if (clientes == null)
			clientes = new ArrayList<Cliente>();
	}
	
	public boolean add(String userName, String password) {
		Cliente c = new Cliente(userName, password);
		boolean result = clientes.add(c);
		imprime();
		return result;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		ClienteDAO.clientes = clientes;
	}

	public boolean has(Cliente cliente) {
		for (Cliente c : clientes) {
			if (c.getUser().equals(cliente.getUser())){
				if (c.getPassword().equals(cliente.getPassword()))
						return true;
			}
		}
		return false;
	}

	private void imprime() {
		for (Cliente cliente : clientes) {
			System.out.println(cliente);
		}
	}
	
}
