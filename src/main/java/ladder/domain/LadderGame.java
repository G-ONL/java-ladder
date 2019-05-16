package ladder.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LadderGame {

    private static final int RIGHT = 1;
    private static final int LEFT = 2;
    private static final int STRAIGHT = 0;
    private static final int MOVEMENT = 1;

    private final Ladder ladder;
    private final List<Player> players;
    private final String[] prizes;

    public LadderGame(Ladder ladder, List<Player> players, String[] prizes) {
        this.ladder = ladder;
        this.players = players;
        this.prizes = prizes;
    }

    public LadderGameResult start() {
        int[] result = new int[players.size()];
        for (int i = 0; i < players.size(); i++) {
            result[getUserResult(i)] = i;
        }
        Map<Player, String> nameToPrize = new HashMap<>();
        for (int i = 0; i < result.length; i++) {
            nameToPrize.put(players.get(result[i]), prizes[i]);
        }
        return new LadderGameResult(nameToPrize);
    }

    private int getUserResult(int index) {
        for (Line line : ladder.getLines()) {
            int[] points = line.getPoints();
            index += judgeLeftOrRightOrStraight(points[index]);
        }
        return index;
    }

    private int judgeLeftOrRightOrStraight(int point) {
        if (point == RIGHT) {
            return MOVEMENT;
        }
        if (point == LEFT) {
            return -MOVEMENT;
        }
        return STRAIGHT;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public String[] getPrizes() {
        return this.prizes;
    }

    public Ladder getLadder() {
        return this.ladder;
    }
}
