package Model;

public class Client {
    private int ID;
    private int timpSosire;
    private int timpDeServiciu;
    private int IdServer;

    public Client(int ID, int timpSosire, int timpDeServiciu)
    {
        this.ID=ID;
        this.timpDeServiciu=timpDeServiciu;
        this.timpSosire=timpSosire;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getTimpSosire() {
        return timpSosire;
    }

    public void setTimpSosire(int timpSosire) {
        this.timpSosire = timpSosire;
    }

    public int getTimpDeServiciu() {
        return timpDeServiciu;
    }

    public void setTimpDeServiciu(int timpDeServiciu) {
        this.timpDeServiciu = timpDeServiciu;
    }

    public int getId() {
        return ID;
    }
    public String toString()
    {
        return "( "+ID +" , "+timpSosire+" , "+timpDeServiciu+")";
    }


    public int getIdServer() {
        return IdServer;
    }

    public void setIdServer(int idServer) {
        IdServer = idServer;
    }
}
