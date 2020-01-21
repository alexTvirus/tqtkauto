/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tqtk;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import tqtk.Entity.SessionEntity;
import tqtk.XuLy.Worker;
import tqtk.XuLy.login.LayThongTinSession;

/**
 *
 * @author Alex
 */
public class Tqtk {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try {
            List<SessionEntity> ss = new ArrayList<>();
//            ss.add(LayThongTinSession.getSessionEntity("hacklslol2@yahoo.com", "lisatthu35", 22));
//            ss.add(LayThongTinSession.getSessionEntity("congngu1990@gmail.com", "123456", 22));
//            ss.add(LayThongTinSession.getSessionEntity("hacklslol6@yahoo.com", "123456", 22));
//            ss.add(LayThongTinSession.getSessionEntity("hacklslol3@yahoo.com", "lisatthu35", 22));
//            ss.add(LayThongTinSession.getSessionEntity("hacklslol7@yahoo.com", "123456", 22));
            ss.add(LayThongTinSession.getSessionEntity("hacklslol9@yahoo.com", "123456", 22));
            ss.add(LayThongTinSession.getSessionEntity("hacklslol8@yahoo.com", "123456", 22));

            final ScheduledExecutorService executor = Executors.newScheduledThreadPool(5);
            for (int i = 0; i < ss.size(); ++i) {
                Socket socket = new Socket();
                socket.connect(new InetSocketAddress(InetAddress.getByName(ss.get(i).getIp()), ss.get(i).getPorts()), 7000);
                Runnable worker = new Worker(socket, ss.get(i));
                executor.schedule(worker, 300L, TimeUnit.MILLISECONDS);
            }
            executor.shutdown();
            while (!executor.isTerminated()) {
            }
            System.out.println("tqtk.Tqtk.main()");
        } catch (Exception ex) {
            Logger.getLogger(Tqtk.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
