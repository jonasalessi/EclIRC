package com.github.eclirc.core;

import java.util.HashMap;
import java.util.Map;

public class ChannelIRC {

	private String nome;
	private ServerIRC servidor;
	private Map<String, UserIRC> usuarioCanal;
	
	public ChannelIRC(String nome, ServerIRC servidor) {
		this.nome = nome;
		this.servidor = servidor;
		this.usuarioCanal = new HashMap<String, UserIRC>();
	}

	public void addUserChannel(String user) {
		usuarioCanal.put(user, new UserIRC(user, null, null, null));
	}

}
