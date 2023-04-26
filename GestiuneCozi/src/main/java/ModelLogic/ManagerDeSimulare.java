package ModelLogic;

import Model.Client;
import Model.Server;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ManagerDeSimulare implements Runnable {
    public int timpLimita ;
    public int timpProcesareMax;
    public int timpProcesareMin;
    public int numarServere ;
    public int numarClienti;
    private Programator programator;
    private List<Client> clientiGenerati;

    public ManagerDeSimulare(int timpLimita, int timpProcesareMax, int timpProcesareMin, int numarServere, int numarClienti) {
        this.timpLimita=timpLimita;
        this.timpProcesareMax=timpProcesareMax;
        this.timpProcesareMin=timpProcesareMin;
        this.numarServere=numarServere;
        this.numarClienti=numarClienti;
        programator = new Programator(numarServere, numarClienti / numarServere);
        generareNClientiAleator();
    }

    private void generareNClientiAleator() {
        clientiGenerati = new ArrayList<>();
        for (int i = 0; i < numarClienti; i++) {
            int timpSosire = (int) (Math.random() * (timpLimita - 1)) + 1;
            int timpProcesare = (int) (Math.random() * (timpProcesareMax - timpProcesareMin)) + timpProcesareMin;
            Client client = new Client(i + 1, timpSosire, timpProcesare);
            clientiGenerati.add(client);
        }
        Collections.sort(clientiGenerati, Comparator.comparingInt(Client::getTimpSosire));
    }

    public List<Client> getClientiGenerati() {
        return clientiGenerati;
    }

    public Programator getProgramator() {
        return programator;
    }

    public void run() {

        int timpulCurent = 0;

        try{
            FileWriter file= new FileWriter("log.txt");

        while (timpulCurent <= timpLimita) {
            List<Client> clientiDeTrimis = new ArrayList<>();
            for (Client client : clientiGenerati) {
                if (client.getTimpSosire() == timpulCurent) {
                    clientiDeTrimis.add(client);
                }
            }
            for (Client client : clientiDeTrimis) {
                programator.expediereClient(client);
                clientiGenerati.remove(client);
            }

            System.out.println(timpulCurent);
                file.write("Statusul cozilor la timpul " + timpulCurent + ":"+"\n");
                file.write("Clienti:" + clientiGenerati.toString()+"\n");
                for (Server server : programator.getServere()) {
                    file.write(server.toString());
                }
                Thread.sleep(1000);
                timpulCurent++;
            }
        file.close();
        } catch (InterruptedException m) {
            m.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

