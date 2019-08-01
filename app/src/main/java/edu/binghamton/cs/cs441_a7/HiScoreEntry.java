package edu.binghamton.cs.cs441_a7;

import java.util.ArrayList;

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
