package Model;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Server implements Runnable{
    private BlockingQueue<Client> clienti;
    private AtomicInteger perioadaAsteptare;
    private int id;

    public Server()
    {
        clienti=new LinkedBlockingQueue<>();
        perioadaAsteptare=new AtomicInteger(0);
    }
    public void adaugaClienti(Client clientNou)  {
            clienti.add(clientNou);
      //  System.out.println("am adaugat client");
       // System.out.println(this.toString());
        perioadaAsteptare.getAndAdd(clientNou.getTimpDeServiciu());
    }
    @Override
    public void run() {
        while(true){
            try{
                if(!clienti.isEmpty()) {
                    Client client = clienti.peek();
                    int timpProcesare = client.getTimpDeServiciu();
                   // System.out.println("Clientul " + client.toString() + " a ajuns in fata cozii. El asteapta " + client.getTimpDeServiciu());
                    Thread.sleep(timpProcesare * 1000);
                    perioadaAsteptare.addAndGet(-timpProcesare);
                    clienti.remove(client);
                }
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public BlockingQueue<Client> getClienti() {
        return clienti;
    }

    public void setClienti(BlockingQueue<Client> clienti) {
        this.clienti = clienti;
    }

    public AtomicInteger getPerioadaAsteptare() {
        return perioadaAsteptare;
    }

    public void setPerioadaAsteptare(AtomicInteger perioadaAsteptare) {
        this.perioadaAsteptare = perioadaAsteptare;
    }
    public void setId(int id){
        this.id=id;
    }
    public String toString()
    {
        return "Coada "+id+":"+clienti.toString()+"\n";
    }

    public int getId() {
        return id;
    }
}
