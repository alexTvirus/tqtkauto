/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tqtk.XuLy.login;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import tqtk.Utils.Util;
import java.net.CookieManager;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import tqtk.Entity.SessionEntity;

/**
 *
 * @author Alex
 */
public class LayThongTinSession {

    public static SessionEntity getSessionEntity(String user, String pass, int id) throws Exception {
        CookieManager msCookieManager = new CookieManager();
        Util.setCookie(msCookieManager);
        String urlWeb = "http://tamquoctruyenky.vn/auth/login";
        String html = Util.getPageSource(urlWeb);
        String token = Util.getToken(html);
        String refer = Util.getRefer(html);

        html = Util.dangNhap(urlWeb, user, pass, token, refer);
        urlWeb = "http://app.slg.vn/tamquoctruyenky/slg?server=" + id;
        html = Util.getThongTinFrame(urlWeb);

        String address = Util.getFrameString(html);
        html = Util.getThongTinPort(address);

        String ip = Util.getInfoSocket(html, "ip");
        String ports = Util.getInfoSocket(html, "ports");
        String sessionKey = Util.getInfoSocket(html, "sessionKey");
        String userID = Util.getInfoSocket(html, "userID");

        SessionEntity ss = new SessionEntity();
        ss.setIp(ip);
        ss.setPorts(Integer.parseInt(ports));
        ss.setSessionKey(sessionKey);
        ss.setUserId(userID);
        return ss;
    }

    public void LayThongTinSession(int id) throws Exception {
        SessionEntity ss = getSessionEntity("hacklslol10@yahoo.com", "123456", 22);
        SessionEntity ss1 = getSessionEntity("hacklslol11@yahoo.com", "123456", 22);
        Thread t1 = new Thread() {
            public void run() {
                try {
//                socket.setTcpNoDelay(true);

                    Socket socket = new Socket();
                    socket.connect(new InetSocketAddress(InetAddress.getByName(ss.getIp()), ss.getPorts()), 7000);
                    // neu da ket noi truoc roi thi ko can chay 2 dong nay
                    Taoketnoi(ss, "10100", socket);
                    Taoketnoi(ss, "11102", socket);

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

                } catch (IOException ex) {
                    Logger.getLogger(LayThongTinSession.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(LayThongTinSession.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        t1.start();

        Thread t2 = new Thread() {
            public void run() {
                try {

                    Socket socket = new Socket();
                    socket.connect(new InetSocketAddress(InetAddress.getByName(ss1.getIp()), ss1.getPorts()), 7000);
                    // neu da ket noi truoc roi thi ko can chay 2 dong nay
                    Taoketnoi(ss1, "10100", socket);
                    Taoketnoi(ss1, "11102", socket);
                    Thread t1 = new Thread() {
                        public void run() {
                            try {
                                List<String> list = new ArrayList<>();
                                list.add("3");
                                list.add("101");
                                Taoketnoi1(ss1, "62007", list, socket);
                            } catch (IOException ex) {
                                Logger.getLogger(LayThongTinSession.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (Exception ex) {
                                Logger.getLogger(LayThongTinSession.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    };
                    t1.start();

                    Thread t2 = new Thread() {
                        public void run() {
                            try {
                                List<String> list = new ArrayList<>();
                                list.add("3");
                                list.add("101");
                                list.add("0");
                                list.add("0");
                                Taoketnoi1(ss1, "62006", list, socket);
                            } catch (IOException ex) {
                                Logger.getLogger(LayThongTinSession.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (Exception ex) {
                                Logger.getLogger(LayThongTinSession.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    };
                    t2.start();
                } catch (IOException ex) {
                    Logger.getLogger(LayThongTinSession.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(LayThongTinSession.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        t2.start();

        int x = 1;

    }

    public static void Taoketnoi(SessionEntity ss, String code, Socket socket) throws UnknownHostException, IOException {

        BufferedWriter wr = null;

        try {
            String message = Util.TaoMsg(code, new ArrayList<String>(), ss);

            wr = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF8"));
            wr.write(message);
            wr.flush();

            if ((socket.getInputStream().read()) == -1) {
                System.out.print("loi");
            }
//            in.close();
        } catch (Exception e) {
            e.getMessage();
        }

    }

    public static void Taoketnoi1(SessionEntity ss, String code, List<String> list, Socket socket) throws UnknownHostException, IOException {

        BufferedWriter wr = null;
        try {
            String message = Util.TaoMsg(code, list, ss);
            wr = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF8"));
            wr.write(message);
            wr.flush();
            int c;
            if ((socket.getInputStream().read()) == -1) {
                System.out.print("loi1");
            } else {
                System.out.print("ok");
            }

//            InputStream ins = socket.getInputStream();
//            while ((c = ins.read()) != -1) {
//                System.out.print((char) c);
//            }
//            wr.close();
//            in.close();
        } catch (Exception e) {
            e.getMessage();
        }

    }
}
