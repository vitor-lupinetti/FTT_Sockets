/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ftt_sockets;

import business.GerenciaImpressora;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import threads.ThreadCliente;


/**
 *
 * @author Dell
 */
public class FTT_Sockets {

    private static ServerSocket server;
    private static int port = 11340;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        server = new ServerSocket(port);
        System.out.println("****serviço de impressão****\n\nPorta do servidor:" + server.getLocalPort());
        GerenciaImpressora.getInstance().ativar();
        boolean ativo = true;
        try{
            while(ativo){
                Socket socket = server.accept();
                ThreadCliente client = new ThreadCliente(socket);
                client.start();
                if(!client.isAlive())
                    ativo = false;
            }
        }
        finally{
            GerenciaImpressora.getInstance().desativar();
            System.out.println("****serviço finalizado*****");
        }
    }
    
}
