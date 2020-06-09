/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

import business.GerenciaImpressora;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dell
 */
public class ThreadImpressora extends Thread {
     private boolean status;

    @Override
    public void run() {
        setStatus(true);
        while (status) {
            try {
                String mensagemImpressao = GerenciaImpressora.getInstance().removeMensagem(); 
                if (mensagemImpressao != null) {
                    imprimir(mensagemImpressao);
                }
                Thread.sleep(1);
            } catch (InterruptedException ex) {
                Logger.getLogger(ThreadImpressora.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void setStatus(boolean value) {
        status = value;
    }

    private void imprimir(String msg) throws InterruptedException {
        System.out.println("Imprimindo: " + msg);
        Thread.sleep(1000);
    }
}
