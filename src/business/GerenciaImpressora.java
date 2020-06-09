/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import threads.ThreadImpressora;

/**
 *
 * @author Dell
 */
public class GerenciaImpressora {
    
    ConcurrentLinkedQueue<String> mensagens;
    private static GerenciaImpressora instance;
    List<ThreadImpressora> impressoras;
    
    
    private GerenciaImpressora(){
        mensagens = new ConcurrentLinkedQueue<>();
        impressoras = new ArrayList<>();
    }
    
   
    public static synchronized GerenciaImpressora getInstance() {
        if (instance == null) {
            instance = new GerenciaImpressora();
        }
        return instance;
    }
    
    public void adcionaMensagem(String msg){
        mensagens.add(msg);
    }
    
    public String removeMensagem(){
        if(mensagens.isEmpty() == false)
            return mensagens.poll();
        return null;
    }
    
    public void ativar() {
        ThreadImpressora impressora;
        for (int i = 0; i < 5; i++) {
            impressora = new ThreadImpressora();
            impressora.start();
            impressoras.add(impressora);
        } 
    }

    public void desativar() {
        if (impressoras != null) {
            for (ThreadImpressora impressora : impressoras) {
                impressora.setStatus(false);
                try {
                    impressora.join(2000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ThreadImpressora.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (impressora.isAlive()) {
                    impressora.interrupt();
                }
            }
        }
    }
}
