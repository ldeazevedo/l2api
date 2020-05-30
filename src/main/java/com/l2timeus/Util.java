package com.l2timeus;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Util {

    public static String readFile(String fileName) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append("<br>");
                line = br.readLine();
            }
            return sb.toString();
        } catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }
}
