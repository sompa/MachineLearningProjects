 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Sompa
 */
public class Data {

    ArrayList<Map<String, Integer>> data;

    public static String classAttribute;

    public Data() {
        data = new ArrayList<Map<String, Integer>>();

    }

    void loadFile(String filename) throws FileNotFoundException {
        Scanner scr = new Scanner(new File(filename));
        String[] attributes = scr.nextLine().split("\\s");
        classAttribute = attributes[attributes.length - 1];
        while (scr.hasNext()) {
            Map<String, Integer> record = new HashMap<String, Integer>();
            for (String attribute : attributes) {
                record.put(attribute, scr.nextInt());
            }
            data.add(record);
        }
        scr.close();
    }

    public void test(Node root, String formatter) {
        int right = 0, wrong = 0;
        for (Map<String, Integer> record : data) {
            Node node = root;
            while (!node.isLeaf()) {
                if (record.get(node.getAttribute()) == 0) {
                    node = node.getLeftChild();
                } else {
                    node = node.getRightChild();
                }
            }
            if (record.get(classAttribute) == node.getValue()) {
                right++;
            } else {
                wrong++;
            }
        }
        System.out.println(String.format(formatter, right * 100.0 / (wrong + right)));
    }

    public void insert(Map<String, Integer> record) {
        data.add(record);
    }

    public Data[] split(String attribute) {
        Data[] splits = {new Data(), new Data()};
        for (Map<String, Integer> record : data) {
            splits[record.get(attribute)].insert(copyWithout(record, attribute));
        }
        return splits;
    }

    public double infoGain(String attribute) {
        double entropy = 0.0;
        double entropy0 = 0.0;
        double entropy1 = 0.0;
        double infoGain = 0.0;
        double[] counts = new double[4];
        for (Map<String, Integer> record : data) {
            counts[record.get(attribute) * 2 + record.get(classAttribute)]++;
        }
//        System.out.print("field " + attribute);
//        for (int i = 0; i < 4; i++) {
//            System.out.print(" " + counts[i]);
//        }

        entropy = -(counts[2] + counts[0]) / size()
                * log2((counts[2] + counts[0]) / size())
                - (counts[3] + counts[1]) / size()
                * log2((counts[3] + counts[1]) / size());
        entropy0 = -counts[0] / (counts[0] + counts[1]) * log2(counts[0] / (counts[0] + counts[1])) - counts[1] / (counts[0] + counts[1]) * log2(counts[1] / (counts[0] + counts[1]));
        entropy1 = -counts[2] / (counts[2] + counts[3]) * log2(counts[2] / (counts[2] + counts[3])) - counts[3] / (counts[2] + counts[3]) * log2(counts[3] / (counts[2] + counts[3]));
        infoGain = entropy - (counts[1] + counts[0]) / size() * entropy0 - (counts[3] + counts[2]) / size() * entropy1;
//        System.out.println(" " + entropy + " " + entropy0 + " " + entropy1 + " " + infoGain);
        return infoGain;
    }

    public void id3(Node node) {
        Map<String, Integer> record = data.get(0);
        String maxAttribute = "";
        double maxInfoGain = 0;
        int zeros = zeroCount();
        int ones = size() - zeros;
        if (record.keySet().size() == 1 || ones == 0 || zeros == 0) {
            node.setValue(ones > zeros ? 1 : 0);
        } else {
            for (String attribute : record.keySet()) {
                if (!attribute.equals(classAttribute)) {
                    double infoGain = this.infoGain(attribute);
                    if (infoGain > maxInfoGain) {
                        maxAttribute = attribute;
                        maxInfoGain = infoGain;
                    }
                }
            }
            if (maxInfoGain == 0.0) {
                node.setValue(ones > zeros ? 1 : 0);
            } else {
                Data[] datas = split(maxAttribute);
                node.setAttribute(maxAttribute);
                node.setLeftChild(new Node());
                node.setRightChild(new Node());
                datas[0].id3(node.getLeftChild());
                datas[1].id3(node.getRightChild());
            }
        }

    }

    private int zeroCount() {
        int count = 0;
        for (Map<String, Integer> record : data) {
            if (record.get(classAttribute) == 0) {
                count++;
            }
        }
        return count;
    }

    private double log2(double value) {
        if (value == 0) {
            return 0;
        }
        return Math.log(value) / Math.log(2);
    }

    public int size() {
        return data.size();
    }

    private Map<String, Integer> copyWithout(Map<String, Integer> record, String attribute) {
        Map<String, Integer> newRec = new HashMap<String, Integer>();
        for (String key : record.keySet()) {
            if (!key.equals(attribute)) {
                newRec.put(key, record.get(key));
            }
        }
        return newRec;
    }
}
