package com.github.eclirc.core.listener;

import java.io.IOException;

import com.github.eclirc.core.IRCManager;
import com.github.eclirc.core.commands.CommandServer;
import com.github.eclirc.core.exception.IRCException;

/**
 * Ouvinte do socket conectado ao servidor
 * @author <a href="mailto:alessi.jonas@gmail.com">Jonas Alessi</a>
 *
 */
public class IRCReceiver extends Thread {

	private CommandServer serverIRC;
	private IRCManager irc;

	public IRCReceiver(CommandServer serverIRC) {
		super(serverIRC.getHost());
		irc = new IRCManager(serverIRC);
		this.serverIRC = serverIRC;
	}

	public void run() {
		String line;
		try {
			while (!this.isInterrupted()) {
				line = serverIRC.readIRC();
				if (line == null) {
					continue;
				}
				if (irc.isPing(line)) {
					continue;
				}
				irc.process(line);
				try {
					Thread.sleep(300);
				} catch (InterruptedException e) {
					throw new IRCException(e,"error.timeout");
				}
			}
			serverIRC.disconect();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (IRCException e) {
			e.printStackTrace();
		}
	}

}
