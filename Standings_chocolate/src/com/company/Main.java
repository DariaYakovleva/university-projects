package com.company;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.*;
import java.io.*;
import java.net.URL;

public class Main {
    static int[] curProb = new int[1000];
    static int[] prevProb = new int[1000];
    static String[] names = new String[1000];
    static int TEAM_COUNT = 200;
    static void init() throws FileNotFoundException {
        File current = new File("current.txt");
        PrintWriter out = new PrintWriter(current);
        for (int i = 0; i < 1000; i++) {
            out.println(i + ":0");
        }
        out.close();
    }

    static void getCurrent() throws IOException {
        for (int i = 0; i < 1000; i++) prevProb[i] = 0;
        Reader current = new FileReader("current.txt");
        BufferedReader in = new BufferedReader(current);
        while (true) {
            String cur = in.readLine();
            if (cur == null) break;
            int team = Integer.parseInt(cur.substring(0, cur.indexOf(":")));
            int probs = Integer.parseInt(cur.substring(cur.indexOf(":") + 1));
            prevProb[team] = probs;
        }
        in.close();
        current.close();
    }

    static void setCurrent() throws FileNotFoundException {
        File current = new File("current.txt");
        PrintWriter out = new PrintWriter(current);
        File res = new File("result.txt");
        PrintWriter res2 = new PrintWriter(res);
        File sum = new File("sum.txt");
        PrintWriter sum2 = new PrintWriter(sum);
        for (int i = 0; i < 1000; i++) {
            if (curProb[i] + prevProb[i] > 0) {
                out.println(i + ":" + (curProb[i] + prevProb[i]));
                if (curProb[i] > 0) res2.println(i + " " + names[i] + " :" + curProb[i]);
                sum2.println(i + " " + names[i] + " :" + (curProb[i] + prevProb[i]));
            }
        }
        out.close();
        res2.close();
        sum2.close();
    }

    public static void main(String argv[]) throws FileNotFoundException {
        String path = "http://neerc.ifmo.ru/school/russia-team/standings.xml";
//        init();
        try {
            getCurrent();
            for (int i = 0; i < 1000; i++) curProb[i] = 0;
            File fXmlFile = new File(path);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse((new URL(path)).openConnection().getInputStream());
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("session");
            int len = nList.getLength();
            if (len < TEAM_COUNT) throw new Exception("bad xml file:(");
            for (int temp = 0; temp < len; temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    String count = eElement.getAttribute("alias");
                    if (count.charAt(0) == 'S') {
                        String name = eElement.getAttribute("party");
                        int cnt = Integer.parseInt(eElement.getAttribute("solved"));
                        int team = Integer.parseInt(count.substring(1));
                        curProb[team] = cnt - prevProb[team];
                        names[team] = name;
//                        System.out.println(team + " " + prevProb[team] + " " + curProb[team]);
                    }
                }
            }
            setCurrent();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}