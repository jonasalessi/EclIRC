package com.github.eclirc.core;

import com.github.eclirc.core.commands.CommandServer;
import com.github.eclirc.core.exception.IRCException;
import com.github.eclirc.core.utils.CommandConstants;

/**
 * Classe manager do IRC, contendo as regras para identificação dos tipos de mensagens
 * @author <a href="mailto:alessi.jonas@gmail.com">Jonas Alessi</a>
 *
 */
public class IRCManager {

	private CommandServer command;

	public IRCManager(CommandServer command) {
		this.command = command;
	}

	public boolean isPing(String content) throws IRCException {
		if (content.startsWith(CommandConstants.PING)) {
			command.doPong(content.split(":")[1]);
			return true;
		}
		return false;
	}

	public void process(String line) {
		System.out.println(line);
	}
	
}
