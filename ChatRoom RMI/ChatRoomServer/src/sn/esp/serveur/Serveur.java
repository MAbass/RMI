package sn.esp.serveur;

import sn.esp.impl.ChatRoomImpl;

import java.rmi.Naming;

import java.rmi.registry.LocateRegistry;

public class Serveur {
    public static void main(String[] args) {
        try
        {
            ChatRoomImpl compte = new ChatRoomImpl("Chat");
            LocateRegistry.createRegistry(1099);
            Naming.rebind("rmi://localhost/Chat",compte);
            System.out.println("Serveur start!!!!");
        } catch (Exception e)
        {
            System.out.println(e.getMessage());

        }
    }
}
