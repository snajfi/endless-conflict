package cz.endless.conflict.enums;


/**
 * Created by dobeji1 on 13.03.2018.
 */
public enum OperationDifficult {
    VERY_EASY(90),
    EASY(80),
    MEDIUM(65),
    DIFFICULT(45),
    VERY_DIFFICULT(25),
    IMPOSSIBLE(0);

    private int difficulty;

    OperationDifficult(int difficulty) {
        this.difficulty = difficulty;
    }

    public int getDifficulty() {
        return difficulty;
    }

}
