package br.com.restful.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import br.com.restful.bean.Cliente;
import br.com.restful.bean.Mensagem;

public class ChatDAO {

	private static List<Mensagem> mensagens;

	public ChatDAO(){
		if (mensagens == null)
			mensagens = new ArrayList<Mensagem>();
	}

	public boolean enviaMensagem(Cliente cliente, String msg) {
		Mensagem m = new Mensagem(cliente, msg);
		m.setId(getPrimaryKey(mensagens));
		boolean result = mensagens.add(m);
		return result;
	}

	private int getPrimaryKey(List<Mensagem> mensagens2) {
		int major = 0;
		for (Mensagem mensagem : mensagens2) {
			if (major <= mensagem.getId())
				major = mensagem.getId();
		}
		major ++;
		return major;
	}

	public List<Mensagem> getMensagens() {
		return mensagens;
	}

	public void setMensagens(List<Mensagem> mensagens) {
		ChatDAO.mensagens = mensagens;
	}
	
	public List<Mensagem> getMensagens(int idLastMsg) {
		List<Mensagem> result = new ArrayList<Mensagem>();
		System.out.println("Mensagens no sistema: "+mensagens);
		for (Mensagem mensagem : mensagens) {
			if (mensagem.getId() > idLastMsg){
				System.out.println(mensagem.getId()+">"+idLastMsg);
				result.add(mensagem);
			}
		}
		
		return result;
	}
}
