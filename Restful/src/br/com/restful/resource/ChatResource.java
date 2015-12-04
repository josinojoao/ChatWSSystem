package br.com.restful.resource;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.restful.bean.Cliente;
import br.com.restful.bean.Mensagem;
import br.com.restful.controller.ChatController;

/**
 * 
 * Classe responsável por conter os metodos REST de acesso ao webservice
 *
 * @author Vitor, Pedro e Icaro
 * @since 23/06/2015
 * @version 1.0
 */
@Path("/chat")
public class ChatResource { 
	private static ChatController cc;
	
	public ChatResource(){
		if (cc == null){
			cc = new ChatController();
		}
	}

	/**
	 * Método responsável por fazer a conexão de um cliente ao servidor.
	 *
	 * @return String conectado
	 * @author Vitor, Pedro e Icaro
	 * @since 23/06/2015
	 * @version 1.0
	 */
	@GET
	@Path("{username}/{password}")
	@Produces("application/json")
	public String setConnection(@PathParam("username") String userName, @PathParam("password") String password){
		return cc.addCliente(userName, password)+"";
	}

	@GET
	@Path("sendmessage/{username}/{password}/{msg}")
	@Produces("application/json")
	public String sendMsg(@PathParam("username") String userName, @PathParam("password") String password, @PathParam("msg") String msg){
		return cc.enviaMensagem(new Cliente(userName, password), msg)+"";
	}
	
	@GET
	@Path("getmessage/{username}/{password}/{idmsg}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Mensagem> getLsMsg(@PathParam("username") String userName, @PathParam("password") String password, @PathParam("idmsg") int idMsg){
		if (cc.hasCliente(new Cliente(userName, password)))
			return cc.getMsgLs(idMsg);
		
		return new ArrayList<Mensagem>();
	}
	
	@GET
	@Path("getmessage/{idlastmsg}")
	@Produces({MediaType.APPLICATION_JSON})
	public List<Mensagem> getMsg(@PathParam("idlastmsg") int idLastMsg){
		return cc.getMsgLs(idLastMsg);
	}
	
}
