package sample.model;

import java.util.ArrayList;

/**
 * Created by Benjamin on 28.06.2016.
 */
public class Tournament {
    private ArrayList<Player> players = new ArrayList<>();

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }
}
