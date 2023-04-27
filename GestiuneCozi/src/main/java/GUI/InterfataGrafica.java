package GUI;

import Model.Client;
import Model.Server;
import ModelLogic.ManagerDeSimulare;
import ModelLogic.Programator;

import javax.swing.*;
import java.awt.*;
import java.util.List;

    public class InterfataGrafica extends JFrame implements Runnable {

        private ManagerDeSimulare manager;
        private JLabel timpCurentLabel;
        private JTextArea clientiArea;
        private JTextArea servereArea;

        public InterfataGrafica(ManagerDeSimulare manager) {
            super("Simulare");
            this.manager = manager;
            this.timpCurentLabel = new JLabel();
            this.clientiArea = new JTextArea();
            this.servereArea = new JTextArea();
            initUI();
        }

        private void initUI() {
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

            panel.add(new JLabel("Timp curent: "));
            panel.add(timpCurentLabel);

            panel.add(new JLabel("Clienți: "));
            JScrollPane clientiScrollPane = new JScrollPane(clientiArea);
            clientiScrollPane.setPreferredSize(new Dimension(400, 200));
            panel.add(clientiScrollPane);

            panel.add(new JLabel("Statusul cozilor: "));
            JScrollPane servereScrollPane = new JScrollPane(servereArea);
            servereScrollPane.setPreferredSize(new Dimension(400, 200));
            panel.add(servereScrollPane);

            add(panel);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(800, 600);
            setLocationRelativeTo(null);
            setVisible(true);
        }

        @Override
        public void run() {
            int timpulCurent = 0;

            while (timpulCurent <= manager.timpLimita) {

                List<Client> clientiGenerati = manager.getClientiGenerati();
                Programator programator = manager.getProgramator();
                List<Server> servere = programator.getServere();

                timpCurentLabel.setText(String.valueOf(timpulCurent));

                StringBuilder clientiBuilder = new StringBuilder();
                for (Client client : clientiGenerati) {
                    clientiBuilder.append(client.toString()).append("\n");
                }
                clientiArea.setText(clientiBuilder.toString());

                StringBuilder servereBuilder = new StringBuilder();
                for (Server server : servere) {
                    servereBuilder.append(server.toString()).append("\n");
                }
                servereArea.setText(servereBuilder.toString());

                try {
                    Thread.sleep(1000);
                    timpulCurent++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

