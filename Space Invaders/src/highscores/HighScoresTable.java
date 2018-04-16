package highscores;

import java.io.FileNotFoundException;
import java.io.File;
import java.io.Serializable;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 204225148
 * @version 4.0
 * @since
 */
public class HighScoresTable implements Serializable {

    /**
     * Version 1 of the object.
     */
    private static final long    serialVersionUID = 1L;
    private ArrayList<ScoreInfo> sTable;
    private int                  size;

    /**
     * This method Create an empty high-scores table with the specified size.
     *
     * @param sizeOfTable
     *            is the size of the high score table. The size means that the
     *            table holds up to size top scores.
     */
    public HighScoresTable(int sizeOfTable) {
        this.sTable = new ArrayList<ScoreInfo>();
        this.size = sizeOfTable;
    }

    /**
     * This method Adds a high-score.
     *
     * @param score
     *            is the high score to be added to the table.
     */
    public void add(ScoreInfo score) {
        int rank = this.getRank(score.getScore());

        // If the score is higher enough.
        if (rank != -1) {
            int index = 0;
            ScoreInfo temp = score;
            List<ScoreInfo> copy = new ArrayList<ScoreInfo>(this.sTable);

            for (ScoreInfo scoreInfo : copy) {
                if (rank == index) {
                    this.sTable.set(index, score);
                    temp = scoreInfo;
                } else if (rank < index) {
                    this.sTable.set(index, temp);
                    temp = scoreInfo;
                }
                index++;
            }

            if (this.size > index) {
                this.sTable.add(index, temp);
            }
        }
    }

    /**
     * This method Returns table max size.
     *
     * @return is the size of the high scores table.
     */
    public int size() {
        return this.size;
    }

    /**
     * This method Returns the current high scores. The list is sorted such that
     * the highest scores come first.
     *
     * @return the list of the high scores.
     */
    public List<ScoreInfo> getHighScores() {
        return this.sTable;
    }

    /**
     * This method returns the rank of the current score: where will it be on
     * the list if added - Rank 1 means the score will be highest on the list.
     * Rank `size` means the score will be lowest. Rank > `size` means the score
     * is too low and will not be added to the list.
     *
     * @param score
     *            is the integer score that will be added to the table.
     * @return the place that the new score will be added to.
     */
    public int getRank(int score) {
        for (int i = 0; i < this.sTable.size(); i++) {
            if (this.sTable.get(i).getScore() < score) {
                return i;
            }
        }
        if (this.sTable.size() < this.size()) {
            return this.sTable.size();
        }
        return -1;
    }

    /**
     * This method Clears the table.
     */
    public void clear() {
        this.sTable.removeAll(this.sTable);
    }

    /**
     * This method Loads table data from file. Current table data is cleared.
     *
     * @param filename
     *            is the file to be loaded.
     * @throws IOException
     *             is the possible exception.
     */
    public void load(File filename) throws IOException {
        this.clear();
        HighScoresTable hl = HighScoresTable.loadFromFile(filename);
        this.size = hl.size;
        this.sTable = (ArrayList<ScoreInfo>) hl.getHighScores();
    }

    /**
     * This method Read a table from file and return it. If the file does not
     * exist, or there is a problem with reading it, an empty table is returned.
     *
     * @param filename
     *            is the file that is readed.
     * @return the high score table that is readed from the file.
     */
    public static HighScoresTable loadFromFile(File filename) {
        HighScoresTable hl = null;
        ObjectInputStream objectInputStream = null;
        try {
            objectInputStream = new ObjectInputStream(
                    new FileInputStream(filename));
            hl = (HighScoresTable) objectInputStream.readObject();
        } catch (FileNotFoundException e) {
            // Can't find file to open
            System.err.println("Unable to find file: " + filename);
            return null;
        } catch (ClassNotFoundException e) {
            // The class in the stream is unknown to the JVM
            System.err.println(
                    "Unable to find class for object in file: " + filename);
            return null;
        } catch (IOException e) {
            System.err.println("Failed reading object");
            return null;
        } finally {
            try {
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
            } catch (IOException e) {
                System.err.println("Failed closing file: " + filename);
            }
        }
        return hl;
    }

    /**
     * This method Saves table data to the specified file.
     *
     * @param filename
     *            is the file that is saved.
     * @throws IOException
     *             is the possible exception.
     */
    public void save(File filename) throws IOException {
        ObjectOutputStream objectOutputStream = null;
        try {
            // check if exists - and delete it.
            if (filename.exists()) {
                filename.delete();
            }
            objectOutputStream = new ObjectOutputStream(
                    new FileOutputStream(filename));
            objectOutputStream.writeObject(this);
        } catch (IOException e) {
            System.err.println("Failed saving object");
            e.printStackTrace(System.err);
        } finally {
            try {
                if (objectOutputStream != null) {
                    objectOutputStream.close();
                }
            } catch (IOException e) {
                System.err.println("Failed closing file: " + filename);
            }
        }
    }
}