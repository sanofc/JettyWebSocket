package com.sanofc;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/WebSocketEndpoint")
public class WebSocketEndpoint {

	static Set<Session> sessions = Collections.synchronizedSet(new HashSet());

	@OnOpen
	public void onOpen(Session session) {
		System.out.println("WebSocket opened:" + session.getId());
		sessions.add(session);
	}

	@OnMessage
	public void onMessage(String message) {
		System.out.println("WebSocket received message:" + message);
		for (Session session : sessions) {
			try {
				session.getBasicRemote().sendText(message);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@OnClose
	public void onClose(CloseReason reason) {
		System.out.println("Closing a WebSocket due to "
				+ reason.getReasonPhrase());
	}

}
