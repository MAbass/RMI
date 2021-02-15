package esp.sn.Impl;


import esp.CompteInterf;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class CompteImpl extends UnicastRemoteObject implements CompteInterf {
    private String nom;
    private float solde;

    public CompteImpl() throws RemoteException {
    }

    @Override
    public String getTitulaire() throws RemoteException {
        return null;
    }

    @Override
    public float solde() throws RemoteException {
        return this.solde;
    }

    @Override
    public void deposer(float montant) throws RemoteException {
        this.solde = montant;

    }

    @Override
    public void retirer(float montant) throws RemoteException {

        this.solde = this.solde - montant;

    }

    @Override
    public List historique() throws RemoteException {

        return null;
    }
}