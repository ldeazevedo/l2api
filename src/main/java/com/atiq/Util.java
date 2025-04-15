package com.atiq;

import java.io.BufferedReader;
import java.io.FileReader;

public class Util {

    private Util() {
    }

    public static String readFile(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line).append("<br>");
                line = br.readLine();
            }
            return sb.toString();
        } catch (Exception ex) {
            return "";
        }
    }
}