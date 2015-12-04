package teste;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.ws.rs.PathParam;

import br.com.restful.bean.Cliente;
import br.com.restful.bean.Mensagem;
import br.com.restful.controller.ChatController;

public class Teste {
	private static ChatController cc;
	private static List<Mensagem> mensagens;
	private static List<Cliente> clientes;

	public Teste(){
		if (mensagens == null)
			mensagens = new ArrayList<Mensagem>();

		if (clientes == null)
			clientes = new ArrayList<Cliente>();

		if (cc == null){
			cc = new ChatController();
			cc.getChatDAO().setMensagens(mensagens);
			cc.getClienteDAO().setClientes(clientes);
			System.out.println("Endereço da lista de clientes: "+clientes);
		}
	}	
	
	public String setConnection(String userName, String password){
		System.out.println("Name: "+userName+"\n"+"password: "+password);

		if (cc.addCliente(userName, password))
			return userName+" conected.";
		else	
			return "Error ao tentar conectar cliente.";
	}

	public String sendMsg(String userName, @PathParam("password") String password, String msg){

		if (cc.enviaMensagem(new Cliente(userName, password), msg))
			return "Mensagem enviada com sucesso.";
		else
			return "Não foi possível enviar a mensagem, tente novamente.";
	}
	
	public List<Mensagem> getMsg(String userName, String password, int i){
		if (cc.hasCliente(new Cliente(userName, password)))
			return cc.getMsgLs(i);
		
		return new ArrayList<Mensagem>();
	}
	
	public static ChatController getCc() {
		return cc;
	}

	public static void setCc(ChatController cc) {
		Teste.cc = cc;
	}

	public static List<Mensagem> getLsMsgs() {
		return mensagens;
	}

	public static void setMensagens(List<Mensagem> mensagens) {
		Teste.mensagens = mensagens;
	}

	public static List<Cliente> getClientes() {
		return clientes;
	}

	public static void setClientes(List<Cliente> clientes) {
		Teste.clientes = clientes;
	}

		public static void main (String[] args){
			Teste t = new Teste();
			System.out.println(t.setConnection("Lucas", "1234")+"\n");
			System.out.println(t.sendMsg("Lucas", "1234", "qualquer coisa"));
			
			System.out.println(t.sendMsg("Lucas", "sss", "nao era pra ser enviada"));
			
			System.out.println(t.setConnection("saitama", "xxxx")+"\n");
			System.out.println(t.sendMsg("saitama", "xxxx", "mensagem do saitama"));
			
			//Date d = t.getLsMsgs().get(1).getHora();
			System.out.println(t.sendMsg("saitama", "xxxx", "mensagem do jiraya"));
			System.out.println("imprimindo as mensagens");
			System.out.println(t.getLsMsgs());
			GregorianCalendar g = new GregorianCalendar();
			for (Mensagem m : t.getLsMsgs()) {
				g.setTime(m.getHora());
				System.out.println(g.get(g.SECOND)+", "+g.get(g.MILLISECOND));
			}
			
			System.out.println("\nAqui\n"+t.getMsg("Lucas", "1234", 0));
			System.out.println("Teste de push.");
		}
	}
