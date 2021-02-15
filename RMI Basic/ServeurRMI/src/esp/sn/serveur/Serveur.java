package esp.sn.serveur;


import esp.sn.Impl.CompteImpl;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class Serveur {

    public static void main(String[] args) {
        try
        {
            CompteImpl compte = new CompteImpl();

            LocateRegistry.createRegistry(1099);
            Naming.rebind("rmi://localhost/BOB_DIST",compte);
            System.out.println("Serveur start!!!!");
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

}
