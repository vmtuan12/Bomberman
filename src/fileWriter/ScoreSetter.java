package fileWriter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class ScoreSetter extends fileWriter.FileWriter {
    private List<String> scoreList;

    public ScoreSetter() {
        textPath = "score/highscore.txt";
        try {
            scanner = new Scanner(new File(textPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setScore(String name, int score) {
        scoreList = new ArrayList<>();
        while (scanner.hasNext()) {
            scoreList.add(scanner.nextLine());
        }
        scoreList.add(name + " - " + score);
        Collections.sort(scoreList,new StringComparator());
        while (scoreList.size() > 3) {
            scoreList.remove(scoreList.size() - 1);
        }
        try {
            writer = new BufferedWriter(new FileWriter(textPath));
            for (int i = 0; i < scoreList.size(); i++) {
                writer.write(scoreList.get(i));
                if(i != scoreList.size() - 1) writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class StringComparator implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            int score1 = Integer.parseInt(o1.substring(o1.indexOf('-') + 2));
            int score2 = Integer.parseInt(o2.substring(o2.indexOf('-') + 2));
            if(score1 != score2) return score2 - score1;
            else {
                return o2.compareTo(o1);
            }
        }
    }
}
