/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tqtk.XuLy;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import tqtk.Entity.SessionEntity;
import tqtk.XuLy.login.LayThongTinSession;
import static tqtk.XuLy.login.LayThongTinSession.Taoketnoi;
import static tqtk.XuLy.login.LayThongTinSession.Taoketnoi1;

/**
 *
 * @author Alex
 */
public class Worker extends Thread {
    
    List<String> list;
    Socket socket;
    SessionEntity ss;
    int servernum;
    
    public Worker(Socket socket, SessionEntity ss) {
        this.socket = socket;
        this.ss = ss;
    }
    
    @Override
    public void run() {
        try {
            Taoketnoi(ss, "10100", socket);
            Taoketnoi(ss, "11102", socket);
            while (true) {
                // lay phan thuong khoan
                Thread t1 = new Thread() {
                    public void run() {
                        try {
                            List<String> list = new ArrayList<>();
                            list.add("3");
                            list.add("101");
                            Taoketnoi1(ss, "62007", list, socket);
                        } catch (IOException ex) {
                            Logger.getLogger(LayThongTinSession.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (Exception ex) {
                            Logger.getLogger(LayThongTinSession.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                };
                t1.start();
                // bat dau khoan
                Thread t2 = new Thread() {
                    public void run() {
                        try {
                            List<String> list = new ArrayList<>();
                            list.add("3");
                            list.add("101");
                            list.add("0");
                            list.add("0");
                            Taoketnoi1(ss, "62006", list, socket);
                        } catch (IOException ex) {
                            Logger.getLogger(LayThongTinSession.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (Exception ex) {
                            Logger.getLogger(LayThongTinSession.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                };
                t2.start();
                
                Thread.sleep(2 * 60 * 1000);
            }
        } catch (IOException ex) {
            Logger.getLogger(LayThongTinSession.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(LayThongTinSession.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
