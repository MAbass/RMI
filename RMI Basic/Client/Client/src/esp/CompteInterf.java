package esp;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface CompteInterf extends Remote {
    public String getTitulaire() throws RemoteException;

    public float solde() throws RemoteException;

    public void deposer(float montant) throws RemoteException;

    public void retirer(float montant) throws RemoteException;

    public List historique() throws RemoteException;
}