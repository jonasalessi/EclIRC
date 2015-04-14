package com.github.eclirc.core.commands;

import java.io.IOException;

import com.github.eclirc.core.exception.IRCException;

/**
 * Interface para comunicação com o servidor IRC
 * @author <a href="mailto:alessi.jonas@gmail.com">Jonas Alessi</a>
 *
 */
public interface CommandServer {

	/**
	 * Retornar o nome do host
	 * @return
	 */
	String getHost();
	
	/**
	 * Entrar em um canal
	 * @param channel
	 */
	void join(String channel) throws IRCException;

	/**
	 * Registra o servidor 
	 */
	void register() throws IRCException;
	
	/**
	 * Quando receber um ping deve retornar o pong
	 * @param ping
	 * @throws IRCException
	 */
	void doPong(String ping) throws IRCException ;

	/**
	 * Adicionar um usuário ao canal do servidor
	 * @param user Usuário
	 * @param channel Canal
	 */
	void addUserChannel(String user, String channel);
	
	/**
	 * Enviar uma mensagem privada no canal
	 * @param nickname
	 * @param message
	 */
	void privateMessage(String nickname, String message) throws IRCException;
	
	/**
	 * Enviar uma mensagem no canal
	 * @param message
	 * @param channel
	 */
	void message(String message, String channel) throws IRCException;
	
	/**
	 * Faz a leitura no servidor conectado
	 * @return String
	 */
	String readIRC() throws IOException;
	
	/**
	 * Sair do servidor
	 */
	void exit() throws IRCException;

	/**
	 * Fechar conexão com o socket
	 * @throws IOException
	 */
	void disconect() throws IOException;
	
	void exitChannel(String channel) throws IRCException;
}
