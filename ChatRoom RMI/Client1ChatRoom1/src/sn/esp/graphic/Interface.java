package sn.esp.graphic;

import sn.esp.impl.ChatUserImpl;
import sn.esp.interf.ChatRoom;

import java.awt.*;
import java.awt.event.*;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import javax.swing.*;

public class Interface implements Serializable, Runnable{
    private final String title = "Logiciel de discussion en ligne";
    private String pseudo = null;

    private final JFrame window = new JFrame(this.title);
    private final JTextArea txtOutput = new JTextArea();
    private final JTextField txtMessage = new JTextField();
    private final JButton btnSend = new JButton("Envoyer");
    ChatUserImpl chatUserImpl;
    ChatRoom room;

    public Interface() {

        this.run();
        this.createIHM();
        this.requestPseudo();

    }

    private void setGraphe(Interface chatUser) {
    }

    public void createIHM() {
        // Assemblage des composants
        JPanel panel = (JPanel) this.window.getContentPane();
        JScrollPane sclPane = new JScrollPane(txtOutput);
        panel.add(sclPane, BorderLayout.CENTER);
        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.add(this.txtMessage, BorderLayout.CENTER);
        southPanel.add(this.btnSend, BorderLayout.EAST);
        panel.add(southPanel, BorderLayout.SOUTH);

        // Gestion des évènements
        window.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                window_windowClosing(e);
            }
        });
        btnSend.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnSend_actionPerformed(e);
            }
        });
        txtMessage.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent event) {
                if (event.getKeyChar() == '\n')
                    btnSend_actionPerformed(null);
            }
        });

        // Initialisation des attributs
        this.txtOutput.setBackground(new Color(220, 220, 220));
        this.txtOutput.setEditable(false);
        this.window.setSize(500, 400);
        this.window.setVisible(true);
        this.txtMessage.requestFocus();
    }

    public void requestPseudo() {
        this.pseudo = JOptionPane.showInputDialog(
                this.window, "Entrez votre pseudo : ",
                this.title, JOptionPane.OK_OPTION
        );
        if (this.pseudo == null) {
            System.exit(0);
        } else {
            try {
                room.subscribe(this.chatUserImpl, this.pseudo);
            } catch (RemoteException e) {
                System.out.println("Erreur subscribe = " + e.getMessage());
            }
        }
    }

    public void window_windowClosing(WindowEvent e) {
        System.exit(-1);
    }

    public void btnSend_actionPerformed(ActionEvent e) {
        if (!txtMessage.getText().equals("")) {
            this.txtOutput.append(this.pseudo.toUpperCase() + ":" + this.txtMessage.getText() + "\n");
            String message = txtMessage.getText();
            try {
                room.postMessage(this.pseudo, message);
            } catch (RemoteException remoteException) {
                System.out.println("Erreur envoie message = " + remoteException.getMessage());
            }
        }
        this.txtMessage.setText("");
        this.txtMessage.requestFocus();
    }

    public static void main(String[] args) {
        new Interface();
    }


    public void display(String message) {
        boolean isFound = message.indexOf(this.pseudo) !=-1? true: false;
        if (!isFound){
            this.txtOutput.append(message +" \n");
            this.txtOutput.moveCaretPosition(this.txtOutput.getText().length());
        }
    }

    @Override
    public void run() {
        try {
            chatUserImpl = new ChatUserImpl();
            chatUserImpl.setGraphe(this);
            room = (ChatRoom) Naming.lookup("rmi://localhost:1099/Chat");
        } catch (NotBoundException e) {
            System.out.println("Erreur not bound = " + e.getMessage());
        } catch (MalformedURLException e) {
            System.out.println("Erreur malformed url = " + e.getMessage());
        } catch (RemoteException e) {
            System.out.println("Erreur Remote Exception = " + e.getMessage());
        }
        System.out.println("Reussi");
    }
}

