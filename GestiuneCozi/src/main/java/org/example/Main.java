package org.example;

import GUI.InterfataGrafica;
import Model.Client;
import Model.Server;
import ModelLogic.ManagerDeSimulare;
import ModelLogic.Programator;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args)  {
        ManagerDeSimulare manager = new ManagerDeSimulare(35,10,2,6,30);
        Thread t = new Thread(manager);
        t.start();
        InterfataGrafica interfata=new InterfataGrafica(manager);
        Thread simulare = new Thread(interfata);
        simulare.start();
    }
}
