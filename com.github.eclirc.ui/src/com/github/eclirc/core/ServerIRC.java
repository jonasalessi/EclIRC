package com.github.eclirc.core;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.github.eclirc.core.commands.CommandServer;
import com.github.eclirc.core.exception.IRCException;
import com.github.eclirc.core.listener.IRCReceiver;
import com.github.eclirc.core.listener.IRCStream;
import com.github.eclirc.core.utils.CommandConstants;
import com.github.eclirc.core.utils.IRCUtils;

/**
 * Classe que representa um servidor do IRC
 * @author <a href="mailto:alessi.jonas@gmail.com">Jonas Alessi</a>
 */
public class ServerIRC extends IRCStream implements CommandServer {

	private String host;
	private int port;
	private Map<String, ChannelIRC> channel;
	private UserIRC user;
	private IRCReceiver receiver;

	public ServerIRC(String host, int port, UserIRC user) {
		this.host = host;
		this.port = port;
		this.user = user;
		this.channel = new HashMap<String, ChannelIRC>();
	}

	/**
	 * Conecta com o servidor
	 * @param bridge Se for preciso ter uma classe ponte para a comunicação com o ouvinte
	 * @throws IRCException
	 */
	public void connect(CommandServer bridge) throws IRCException {
		super.connect(getHost(), getPort());
		receiver = new IRCReceiver(bridge);
		receiver.start();
		register();
	}

	/**
	 * Conecta com o servidor
	 * @throws IRCException
	 */
	public void connect() throws IRCException {
		connect(this);
	}

	public String getHost() {
		return host;
	}

	public Map<String, ChannelIRC> getChannels() {
		return channel;
	}

	public UserIRC getUser() {
		return user;
	}

	public int getPort() {
		return port;
	}

	public void join(String channel) throws IRCException {
		ChannelIRC ch = new ChannelIRC(channel, this);
		this.channel.put(channel, ch);
		write(CommandConstants.JOIN + IRCUtils.SPACE_BLANK + channel);
	}

	public void register() throws IRCException {
		if (user.getPassword() != null) {
			write(CommandConstants.PASS + IRCUtils.SPACE_BLANK + user.getPassword());
		}
		write(CommandConstants.NICK + IRCUtils.SPACE_BLANK + user.getNick());
		write(CommandConstants.USER + IRCUtils.SPACE_BLANK + user.getUserName() + " "
				+ getLocalAddress() + " " + getHost() + " :"
				+ user.getRealName());
	}

	public void doPong(String ping) throws IRCException {
		write(CommandConstants.PONG + ping);
	}

	public void privateMessage(String nickname, String message) throws IRCException {
		write(CommandConstants.PRIVMSG + IRCUtils.SPACE_BLANK + nickname + IRCUtils.POINTS + message);		
	}

	public void message(String message, String channel) throws IRCException {
		write(CommandConstants.PRIVMSG + IRCUtils.SPACE_BLANK + channel + IRCUtils.POINTS + message);		
	}

	public void addUserChannel(String user, String channel) {
		ChannelIRC ch = this.channel.get(channel);
		if (ch != null) {
			ch.addUserChannel(user);
		}
	}

	public void exit() throws IRCException {
		write(CommandConstants.QUIT + IRCUtils.SPACE_BLANK + host);
		receiver.interrupt();
	}
	
	public void exitChannel(String channel) throws IRCException {
		write(CommandConstants.QUIT + IRCUtils.SPACE_BLANK + channel);
	}
	
	public void disconect() throws IOException {
		super.close();
	}
}
