package org.example;

import Model.Client;
import Model.Server;
import ModelLogic.ManagerDeSimulare;
import ModelLogic.Programator;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args)  {
        ManagerDeSimulare manager = new ManagerDeSimulare(30,10,2,5,50);
        Thread t = new Thread(manager);
        t.start();

    }
}
