package sn.esp.impl;

import sn.esp.interf.ChatRoom;
import sn.esp.interf.ChatUser;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Set;

public class ChatRoomImpl extends UnicastRemoteObject implements ChatRoom, Serializable {

    HashMap<ChatUser, String> chatUserStringHashMap;

    String name;

    public ChatRoomImpl(String name) throws RemoteException {
        chatUserStringHashMap  = new HashMap<>();
        this.name = name;
    }

    @Override
    public void subscribe(ChatUser user, String pseudo) throws RemoteException {
        if (!chatUserStringHashMap.containsKey(pseudo)) {
            chatUserStringHashMap.put(user, pseudo);
            this.postMessage(pseudo, "Connecté");
        }

    }

    @Override
    public void unsubscribe(String pseudo) throws RemoteException {
        if (!chatUserStringHashMap.containsKey(pseudo)) {
            chatUserStringHashMap.remove(pseudo);
            this.postMessage(pseudo, "Deconnecté");
        }
    }

    @Override
    public void postMessage(String pseudo, String message) throws RemoteException {
        String messageEntier = pseudo+" : "+message;
        System.out.println("--->"+messageEntier);
        Set<ChatUser> listUser = chatUserStringHashMap.keySet();
        for (ChatUser user:listUser){
            user.displayMessage(messageEntier);
        }


    }
}
