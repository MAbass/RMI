package sn.esp.impl;



import sn.esp.graphic.Interface;
import sn.esp.interf.ChatUser;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ChatUserImpl extends UnicastRemoteObject implements ChatUser {
    private Interface anInterface;
    public ChatUserImpl() throws RemoteException {
    }

    public void setGraphe(Interface anInterface) {
        this.anInterface = anInterface;
    }

    @Override
    public void displayMessage(String message) throws RemoteException {
        anInterface.display(message);

    }
}
