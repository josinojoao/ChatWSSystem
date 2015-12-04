package br.com.restful.controller;

import java.util.Date;
import java.util.List;

import br.com.restful.bean.Cliente;
import br.com.restful.bean.Mensagem;
import br.com.restful.dao.ChatDAO;
import br.com.restful.dao.ClienteDAO;

/**
 * 
 * Classe responsável por ser o controlador entre o resource Aluno e a camada DAO
 *
 * @return boolean conectado
 * @author Vitor Lucas <lukassgm@gmail.com.br>
 * @since 23/06/2015
 * @version 1.0
 */
public class ChatController {
	private ClienteDAO clienteDAO;
	private ChatDAO chatDAO;

	public ChatController(){
		clienteDAO = new ClienteDAO();
		chatDAO = new ChatDAO();
	}

	public boolean addCliente(String userName, String password) {
		return clienteDAO.add(userName, password);

	}

	public boolean enviaMensagem(Cliente cliente, String msg) {
		if (clienteDAO.has(cliente)){
			return chatDAO.enviaMensagem(cliente, msg);
		}
		else{
			return false;
		}

	}
	
	public List<Mensagem> getMsgLs(int i){
		return chatDAO.getMensagens(i);
	}
	
	public boolean hasCliente(Cliente cliente ){
		return clienteDAO.has(cliente);
	}

	public ClienteDAO getClienteDAO() {
		return clienteDAO;
	}

	public void setClienteDAO(ClienteDAO clienteDAO) {
		this.clienteDAO = clienteDAO;
	}

	public ChatDAO getChatDAO() {
		return chatDAO;
	}

	public void setChatDAO(ChatDAO chatDAO) {
		this.chatDAO = chatDAO;
	}

}
