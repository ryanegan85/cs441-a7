package edu.binghamton.cs.cs441_a7;

import java.util.ArrayList;
import java.util.Collections;

public class HiScoreEntry {

    public ArrayList<String> names;
    public ArrayList<String> scores;

    public HiScoreEntry() {
        names = new ArrayList<>();
        scores = new ArrayList<>();
    }

    public HiScoreEntry(ArrayList<String> newNames, ArrayList<String> newScores) {
        if(newNames.size() == newScores.size()) {
            names = newNames;
            scores = newScores;
        }
    }

    public void putNewHiScore(String newName, String newScore) {
        names.add(newName);
        scores.add(newScore);
    }

    public void sortScores() {
        boolean sorted = false;
        ArrayList<Integer> scoresInt = new ArrayList<>();
        for(int i=0; i<scores.size(); i++) {
            scoresInt.add(Integer.parseInt(scores.get(i)));
        }

        int temp;
        String tempString;
        while(!sorted) {
            sorted = true;
            for(int i=0; i<scoresInt.size()-1; i++) {
                if(scoresInt.get(i) > scoresInt.get(i+1)) {
                    temp = scoresInt.get(i);
                    tempString = names.get(i);

                    scoresInt.set(i, scoresInt.get(i+1));
                    names.set(i, names.get(i+1));

                    scoresInt.set(i+1, temp);
                    names.set(i+1, tempString);

                    sorted = false;
                }
            }
        }

        for(int i=0; i<scores.size(); i++) {
            scores.set(i, Integer.toString(scoresInt.get(i)));
        }

        Collections.reverse(names);
        Collections.reverse(scores);
    }

    public String getName(int position) {
        return names.get(position);
    }

    public String getScore(int position) {
        return scores.get(position);
    }

    public int getSize() {
        return names.size();
    }
}
