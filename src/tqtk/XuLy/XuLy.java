/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tqtk.XuLy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

/**
 *
 * @author Alex
 */
public class XuLy {

    public String handler(OutputStream out, InputStream in, String msg) throws UnsupportedEncodingException, IOException {
        BufferedWriter wr = null;
        wr = new BufferedWriter(new OutputStreamWriter(out));
        wr.write(msg);
        wr.flush();
        BufferedReader ins = new BufferedReader(new InputStreamReader(in, "UTF-8"));
        StringBuffer response = new StringBuffer();
        String inputLine;
        while ((inputLine = ins.readLine()) != null) {
            response.append(inputLine);
        }
        return response.toString();
    }
}
