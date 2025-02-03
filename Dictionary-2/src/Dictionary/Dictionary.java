package Dictionary;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Dictionary {
    public JPanel dPanel;
    public JButton FINDButton;
    public JButton IMPORTButton;
    public JButton EXPORTButton;
    public JTextField TextOriginalWord;
    public JTextField TextFreqWord1;
    public JTextField TextFreqWord2;
    public JTextField TextFreqWord3;
    public JButton ADDButton;
    public JTextField TextNewWord;
    public JTextField TextFilePath;
    public JButton MODIFYButton;
    public JButton REMOVEButton;
    public JButton CLEARButton;
    public JTextArea TextArea;

    public static ArrayList<word> findFreq(ArrayList<word> freqList, String strCompare) {

        ArrayList<word> fList = new ArrayList<>();

        for (word i : freqList) {
            if (i.a.contains(strCompare)) {
                fList.add(i);
            }
        }

        fList.sort((a1,a2) -> a1.frequency - a2.frequency);
        Collections.reverse(fList);
        return new ArrayList<>(fList);
    }

    public static void checkFileExist(String fileToCheck) throws FileNotFoundError {
        if (!new File(fileToCheck).isFile()) {
            throw new FileNotFoundError("\"" + fileToCheck + "\"" + " is not found");
        }
    }

    int i = -1;
    //int l = 0;

    String a, b;
    int frequency;

    /**
     * This is my Dictionary constructor.
     * This constructor creates an ArrayList of objects called "words".
     * Each of these objects, if they are a valid word, have a corresponding definition and frequency.
     * There are 7 action listeners for each button.
     * Each button has a different purpose and all correspond to an input text from one of the text fields.
     */
    public Dictionary() {

        ArrayList<word> dictionary = new ArrayList<word>();


        /* Button to find EnterWord's word in dictionary
          */
        FINDButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //System.out.println("there are " + dictionary.size() + " words in the dictionary");

                /*for (int k = 0; k< dictionary.size(); k++) {
                    System.out.println("\nThe word is: " + dictionary.get(k).a + "\nThe definition is: " + dictionary.get(k).b + "\n\n");
                }*/

                String A;
                String A1, A2;

                String x = TextNewWord.getText();
                ArrayList<word> frequencyWords = findFreq(dictionary, x);

                JTextField[] freqWordFields = {TextFreqWord1, TextFreqWord2, TextFreqWord3};

                //System.out.println("there are " + dictionary.size() + " words in the dictionary");

                try {
                    if (dictionary.isEmpty()) {
                        throw new WordNotFoundError("WordNotFoundError"); // Invalid Word Exception
                    }
                }
                catch(WordNotFoundError ex) {
                    //System.out.println("Exception caught: " + ex.getMessage());
                    TextArea.setText("No Word Matched.");
                }

                /** The following for loop checks if the word entered is a substring or is the same string
                as a word in the dictionary. If so, the word is added to an arraylist of freq words */
                for (int j = 0; j < dictionary.size(); j++) {

                    word dictWord = dictionary.get(j);
                    A = dictWord.a;

                    if (A.contains(x)) {
                        //frequencyWords.add(dictWord);
                        dictWord.frequency++;


                        // this for loop is to remove duplicate frequency words
                        for (int i = 0; i < frequencyWords.size(); i++) {
                            for (int k = j + 1; k < frequencyWords.size(); k++) {

                                A1 = frequencyWords.get(j).a;
                                A2 = frequencyWords.get(k).a;


                                if (A1.equals(A2)) { // HAVE TO CREATE AN ERROR THROW HERE
                                    frequencyWords.remove(k);
                                    TextArea.setText("");
                                }

                            }
                        }

                        /**/
                        //System.out.println("word: " + A + ". meaning: " + dictWord.b + ". frequency: " + dictWord.frequency + "\n");
                        /**/
                        //System.out.println("size of frequency words: " + frequencyWords.size());
                    }
                }

                /**the following code checks if the searched word is a substring or a word
                 in the frequency list */
                boolean matchFound = false;

                for (int t=0; t<frequencyWords.size(); t++) {
                        if (frequencyWords.get(t).a.contains(x)) {

                            matchFound = true;
                            break;
                        }
                }


                try {
                    if (matchFound) {
                        TextArea.setText("");
                    } else {
                        throw new WordNotFoundError("WordNotFoundError"); // Invalid Word Exception
                    }
                }
                catch(WordNotFoundError ex) {
//                    System.out.println("Exception caught: " + ex.getMessage());
                    TextArea.setText("No Word Matched.");
                }


                /** The following code sets the frequency text fields */
                for (int i=0; i < frequencyWords.size() && i < freqWordFields.length; i++) {
                    //System.out.println("there are " + dictionary.size() + " words in the dictionary");
                    if(x.isEmpty() || x.equals(" ") || !frequencyWords.get(i).a.contains(x)) {
                        freqWordFields[i].setText("");
                    }
                    else {
                        freqWordFields[i].setText(frequencyWords.get(i).a);
                    }
                }

                for (int j = frequencyWords.size(); j < freqWordFields.length; j++) {
                    freqWordFields[j].setText("");
                }

            }
        });


        /* Button for adding EnterWord's word to dictionary
          */
        ADDButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                i++;

                String x = TextNewWord.getText();
                String y = TextArea.getText();

                String A1, A2;

                word newWord = new word(x, y, 0);
                if (x.isEmpty() || y.isEmpty() ) {
                    TextArea.setText("Error: enter a word AND definition. Press clear and try again."); // HAVE TO CREATE AN ERROR THROW HERE
                }

                if (x.matches("[a-zA-Z]+")) {
                    dictionary.add(i, newWord);
                }
                else {
                    i--;
                    throw new InvalidWordError("InvalidWord"); // Invalid Word Exception
                }

//                catch(InvalidWordError ex) {
//                    System.out.println("Exception caught: " + ex.getMessage());
//                    TextArea.setText("Error: Invalid word");
//                    i--;
//                }

                if (i>-1) {

                    for (int j = 0; j < dictionary.size(); j++) {
                        for (int k = j+1; k < dictionary.size(); k++) {

                            A1 = dictionary.get(j).a;
                            A2 = dictionary.get(k).a;


                            if (A1.equals(A2)) {
                                dictionary.remove(i);
                                //System.out.println("dictionary size: " + dictionary.size());
                                i--;
                                throw new WordDuplicatedError("DuplicateWordException"); // Invalid Duplicate Exception
                            }

//                            catch (WordDuplicatedError ex){
//                                System.out.println("Exception caught: " + ex.getMessage());
//                                TextArea.setText("Error: duplicated word. Press clear and try again.");
//                            }

                        }
                    }

                }

            }
        });


        /* File Buttons
          */
        IMPORTButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String filePath = TextFilePath.getText();

                ArrayList <String> linesGroup1  = new ArrayList<String>();
                ArrayList <String> linesGroup2  = new ArrayList<String>();

                String A1, A2;

                try {

                    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                        String line;
                        int lineNumber = 1;

                        while ((line = reader.readLine()) != null) {
                            if (lineNumber % 3 == 1) {
                                // Lines 1, 4, 7, 10, 13, 16, ...
                                linesGroup1.add(line);
                            } else if (lineNumber % 3 == 2) {
                                // Lines 2, 5, 8, 11, 14, 17, ...
                                linesGroup2.add(line);
                            }

                            lineNumber++;
                        }
                    }
                    catch (IOException FileNotFoundError) {
//                    System.out.println("Exception caught: " + FileNotFoundError.getMessage());
//                    TextArea.setText("Error: File Not Found.");
                    }

                    checkFileExist(filePath);
                }
                catch (FileNotFoundError ex){
                    System.out.println("Exception caught: FileNotFoundError");
                    TextArea.setText("Error: File Not Found.");
                }


                for (int p=0; p<linesGroup1.size(); p++) {
                    i++;
                    word newWord = new word(linesGroup1.get(p), linesGroup2.get(p), 0);
                    dictionary.add(i, newWord);
                }

                for (int j = 0; j < dictionary.size(); j++) {
                    for (int k = j+1; k < dictionary.size(); k++) {

                        A1 = dictionary.get(j).a;
                        A2 = dictionary.get(k).a;

                        try {
                            if (A1.equals(A2)) {
                                dictionary.remove(i);
                                //System.out.println("dictionary size: " + dictionary.size());
                                i--;
                                throw new WordDuplicatedError("DuplicateWordException"); // Invalid Duplicate Exception
                            }
                        }
                        catch (WordDuplicatedError ex){
                            System.out.println("Exception caught: " + ex.getMessage());
                            TextArea.setText("Error: duplicated word.");
                        }

                    }
                }
            }
        });

        EXPORTButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String filePath = TextFilePath.getText();

                try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {

                    ArrayList<word> fList = new ArrayList<>();

                    for (word i : dictionary) {
                        fList.add(i);
                    }

                    fList.sort((a1,a2) -> a1.frequency - a2.frequency);
                    Collections.reverse(fList);



                    for (int u=0; u< fList.size(); u++) {
                        writer.write(fList.get(u).a);
                        writer.newLine(); // Move to the next line
                        writer.write("" + fList.get(u).frequency);
                        writer.newLine();
                        writer.write(fList.get(u).b);
                        writer.newLine();
                        writer.write("");
                        writer.newLine();
                    }


                } catch (IOException FileNotFoundError) {
//                    System.out.println("Exception caught: " + FileNotFoundError.getMessage());
//                    TextArea.setText("Error: File Not Found.");
                }

            }
        });


        /* Button to clear all displayed text
          */
        CLEARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                TextNewWord.setText("");
                TextOriginalWord.setText("");
                TextFreqWord1.setText("");
                TextFreqWord1.setText("");
                TextFreqWord2.setText("");
                TextFreqWord3.setText("");
                TextFilePath.setText("");
                TextArea.setText("");

            }
        });

        /* Button to delete original word and replace with new word
          */
        MODIFYButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String dictWord;

                String oldWord = TextOriginalWord.getText();
                String newWord = TextNewWord.getText();

                if (i == -1) {
                    TextArea.setText("Error: No words in dictionary to modify. Press clear and add a word before modifying"); // HAVE TO CREATE AN ERROR THROW HERE
                } else if (oldWord.isEmpty() || newWord.isEmpty()){
                    TextArea.setText("Error: enter the original word AND the new word."); // HAVE TO CREATE AN ERROR THROW HERE
                }
                else {

                    if (i > -1) {

                        for (int j = 0; j < dictionary.size(); j++) {

                            dictWord = dictionary.get(j).a;

                            try {
                                if (newWord.equals(dictWord)) {
                                    throw new WordDuplicatedError("DuplicateWordException"); // Invalid Duplicate Exception
                                }
                            }
                            catch (WordDuplicatedError ex) {
                                System.out.println("Exception caught: " + ex.getMessage());
                                TextArea.setText("Error: duplicated word.");
                                break;
                            }


                            if (oldWord.equals(dictWord)) {
                                dictionary.get(j).a = newWord;
                                TextArea.setText("");
                                break;

                            }
                            else {
                                TextArea.setText("Error: enter an original word that is in the current dictionary"); // HAVE TO CREATE AN ERROR THROW HERE
                            }
                        }

                    }

                }
            }
        });

        /* Button to remove entered word from dictionary
          */
        REMOVEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String removeWord = TextNewWord.getText();
                String dictWord;

                if (TextNewWord.getText().isEmpty()) {
                    TextArea.setText("Error: type a word to remove in the New Word text field"); // HAVE TO CREATE AN ERROR THROW HERE
                    throw new WordNotFoundError("WordNotFoundError");
                }

                if (dictionary.isEmpty()) {
                    TextArea.setText("Error: there are no words in the dictionary to remove"); // HAVE TO CREATE AN ERROR THROW HERE
                    //System.out.println(i);
                    throw new WordNotFoundError("WordNotFoundError");
                }

                if (i>-1) {

                    for (int j = 0; j < dictionary.size(); j++) {

                        dictWord = dictionary.get(j).a;


                        if (removeWord.equals(dictWord)) {
                            dictionary.remove(j);
                            i--;
                            TextArea.setText("");
                        } else {
                            //TextArea.setText("Error: no such word in the dictionary to remove");
                            TextArea.setText("No Word Matched.");
                            throw new WordNotFoundError("WordNotFoundError");
                        }

//                        catch(WordNotFoundError ex) {
//                            System.out.println("Exception caught: " + ex.getMessage());
//                            TextArea.setText("No Word Matched.");
//                        }

                    }

                }

            }
        });
    }

    /** Main method that creates the dictionary and displays the GUI
      */
    public static void main(String[] args) {

        JFrame frame = new JFrame("Dictionary");
        Dictionary dictionary = new Dictionary();
        frame.setContentPane(dictionary.dPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(550,600);
        frame.setVisible(true);

    }

}


