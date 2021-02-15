package sn.esp;


import esp.CompteInterf;

import java.rmi.Naming;

public class Client {
    public static void main(String[] args) {
        try
        {
            CompteInterf compte = (CompteInterf) Naming.lookup("rmi://localhost:1099/BOB_DIST");
            compte.deposer(3000);
            System.out.println("Solde = "+compte.solde());
            compte.retirer(1000);
            System.out.println("Nouveau solde = "+compte.solde());


        }
        catch (Exception ex)
        {
            System.out.println("impossible de se connecter");
            System.out.println(ex.getMessage());
        }
    }
}
