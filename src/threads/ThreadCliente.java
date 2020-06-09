/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

import business.GerenciaImpressora;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 *
 * @author Dell
 */
public class ThreadCliente extends Thread {
    private Socket _client;
    public ThreadCliente (Socket client){
        _client = client;
    }
    
    @Override
     public void run() {
        try ( InputStream stream = _client.getInputStream()) {
            boolean ativo = true;
            while (_client.isConnected()) {
                if (stream.available() != 0) {
                    byte[] dados = new byte[stream.available()];
                    stream.read(dados);
                    if (!new String(dados).equals("sair")) {
                        GerenciaImpressora.getInstance().adcionaMensagem(new String(dados));
                    } else {
                        _client.close();
                    }

                }
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
