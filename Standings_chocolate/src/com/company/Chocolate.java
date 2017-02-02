package com.company;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static com.company.Main.prevProb;

/**
 * Created by Daria on 11.12.2016.
 */
public class Chocolate {

    private Map<Integer, Integer> problemsNow;
    private Map<Integer, Integer> problemsWas;
    private Map<Integer, String> names;

    private final String STANDING = "standing.txt";
    private final String DIFFERENCE = "diff.txt";
    private final String PATH = "http://neerc.ifmo.ru/school/russia-team/standings.xml";
    private int TEAM_COUNT = 1000;

    public Chocolate() {
        problemsNow = new HashMap<>();
        problemsWas = new HashMap<>();
        names = new HashMap<>();
    }

    void downloadStanding() throws Exception {
        downloadCurrentProbs();
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse((new URL(PATH)).openConnection().getInputStream());
        doc.getDocumentElement().normalize();
        PrintWriter stand = new PrintWriter(new File(STANDING));
        NodeList nList = doc.getElementsByTagName("session");
        int len = nList.getLength();
        System.out.println("XML file length:" + len);
        if (len < 200) throw new Exception("bad xml file:(");
        for (int temp = 0; temp < len; temp++) {
            Node nNode = nList.item(temp);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                String count = eElement.getAttribute("alias");
                if (count.charAt(0) == 'S') {
                    String teamName = eElement.getAttribute("party");
                    int teamSolved = Integer.parseInt(eElement.getAttribute("solved"));
                    int teamNum = Integer.parseInt(count.substring(1));
                    names.put(teamNum, teamName);
                    problemsNow.put(teamNum, teamSolved);
                    stand.println(teamNum + ":" + teamSolved);
                }
            }
        }
        stand.close();
    }


    private void downloadCurrentProbs() throws IOException {
        BufferedReader stand = new BufferedReader(new FileReader(STANDING));
        while (true) {
            String cur = stand.readLine();
            if (cur == null) break;
            int team = Integer.parseInt(cur.substring(0, cur.indexOf(":")));
            int probs = Integer.parseInt(cur.substring(cur.indexOf(":") + 1));
            problemsWas.put(team, probs);
        }
        stand.close();
    }

    void countDifference() throws FileNotFoundException {
        Map<Integer, Integer> res = new HashMap<>();
        for (Integer team: problemsWas.keySet()) {
            int prev = problemsWas.get(team);
            int cur = problemsNow.get(team);
            if (cur - prev > 0) res.put(team, cur - prev);
        }
        for (Integer team: problemsNow.keySet()) {
            if (!problemsWas.containsKey(team)) {
                res.put(team, problemsNow.get(team));
            }
        }
        PrintWriter diff = new PrintWriter(new File(DIFFERENCE));
        for (int team = 0; team < TEAM_COUNT; team++) {
            if (res.containsKey(team)) {
                diff.println(team + ":" + res.get(team) + ", " + names.get(team));
            }
        }
        diff.close();
    }
}
