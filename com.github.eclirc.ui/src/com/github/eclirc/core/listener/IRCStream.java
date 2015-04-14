package com.github.eclirc.core.listener;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import com.github.eclirc.core.exception.IRCException;
import com.github.eclirc.core.utils.IRCUtils;

public abstract class IRCStream {

	private Socket socket;
	private BufferedWriter writer;
	private BufferedReader reader;

	protected final void connect(String host, int port) throws IRCException {
		try {
			socket = new Socket(host, port);
			socket.setSoTimeout(60000 * 15);
			writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
		} catch (UnknownHostException e) {
			throw new IRCException(e, "server.notFound");
		} catch (IOException e) {
			throw new IRCException(e, "server.errorIO");
		}
	}
	public final void write(String content) throws IRCException {
		System.out.println("[DEBUG] " + content);
		try {
			writer.write(content + IRCUtils.END_LINE);
			writer.flush();
		} catch (IOException e) {
			throw new IRCException(e, "server.error.write.IO");
		}
	}

	public final String readIRC() throws IOException {
		return reader.readLine();
	}

	protected InetAddress getLocalAddress() {
		return socket.getLocalAddress();
	}
	
	protected void close() throws IOException {
		socket.close();
	}
}
