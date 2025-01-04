package cs106;

import static sbcc.Core.*;
import static org.apache.commons.lang3.StringUtils.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.*;

public class BasicSpellChecker implements SpellChecker {
    Dictionary dictionary = new BasicDictionary();
    StringBuilder document = new StringBuilder();
    Matcher m = Pattern.compile("\\b[\\w']+\\b").matcher(document);

    String current;


    @Override
    public void importDictionary(String filename) throws Exception {
        dictionary.importFile(filename);
    }

    @Override
    public void loadDictionary(String filename) throws Exception {
        dictionary.load(filename);
    }

    @Override
    public void saveDictionary(String filename) throws Exception {
        dictionary.save(filename);
    }

    @Override
    public void loadDocument(String filename) throws Exception {
        document.setLength(0);
        document.append(readFile(filename));
    }


    @Override
    public void saveDocument(String filename) throws Exception {
        writeFile(filename, getText());
    }

    @Override
    public String getText() {
        return document.toString();
    }

    @Override
    public String[] spellCheck(boolean continueFromPrevious) {


        if (!continueFromPrevious)
            m.reset();

        while (m.find()) {
            var word = m.group();
            var location = m.start() + "";
            String[] result = dictionary.find(word);

            if (result != null) {
                return new String[]{word, location, result[0], result[1]};
            }
        }
        return null;
    }


    @Override
    public void addWordToDictionary(String word) {
        dictionary.add(word);
    }

    @Override
    public void replaceText(int startIndex, int endIndex, String replacementText) {
        document.replace(startIndex, endIndex, replacementText);
    }
}
