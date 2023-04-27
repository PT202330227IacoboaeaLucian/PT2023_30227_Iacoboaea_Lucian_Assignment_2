package ModelLogic;

import Model.Client;
import Model.Server;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Programator {
    private List<Server> servere;
    private int maxNrServere;
    private int maxClientiPerServer;

    public Programator(int maxNrServere,int maxClientiPerServer)
    {
        this.maxNrServere=maxNrServere;
        this.maxClientiPerServer=maxClientiPerServer;
        this.servere=new ArrayList<>();
        for(int i=0;i<maxNrServere; i++)
        {
            Server server= new Server();
            server.setId(i+1);
            server.setPerioadaAsteptare(new AtomicInteger(0));
            Thread serverThread= new Thread(server);
            serverThread.start();
            servere.add(server);
        }
    }

    public void expediereClient(Client t)
    {
        int timpAsteptare = Integer.MAX_VALUE;
        Server coadaLibera = null;
        for (Server server : servere) {
            int wt = server.getPerioadaAsteptare().intValue();
            if (wt < timpAsteptare && server.getClienti().size() < maxClientiPerServer) {
                timpAsteptare = wt;
                coadaLibera = server;
            }
        }
        if (coadaLibera != null) {
            coadaLibera.adaugaClienti(t);

        }
    }
    public List<Server> getServere() {
        return servere;
    }
    public String toString()
    {
        return "Coada: " +servere + "Max nr cozi: " + maxNrServere+"Max nr clienti per coada:"+maxClientiPerServer;
    }
}
