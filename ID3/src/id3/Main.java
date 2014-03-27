/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id3;

import java.io.FileNotFoundException;

/**
 *
 * @author Sompa
 */
public class Main {

    Node root;
    Data test, train;

    public Main() {
        root = new Node();
        test = new Data();
        train = new Data();
    }

    public void test(Data data, String formatter) {
        data.test(root, formatter);
    }

    public void id3() {
        train.id3(root);
    }

    public void process(String trainFilename, String testFilename) throws FileNotFoundException {
            train.loadFile(trainFilename);
            test.loadFile(testFilename);
            id3();
            root.print("");
            train.test(root, "Accuracy on training set (" + train.size() + " instances): %f%%\n");
            test.test(root, "Accuracy on test set (" + test.size() + " instances): %f%%\n");
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        // TODO code application logic here
        Main m = new Main();
        m.process(args[0], args[1]);
    }
}
