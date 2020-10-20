package roulette.casino;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CasinoStore {
    private List<Player> players;

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public void init() {
        players = new ArrayList<>();

        URL url = getClass().getClassLoader().getResource("players.txt");
        List<String> lines = null;
        File file = null;

        try {
            file = new File(url.toURI());
            lines = Files.readAllLines(file.toPath());

            for (String name : lines) {
                players.add(new Player(name));
            }
        } catch (URISyntaxException e) {
        } catch (IOException e) {
        }
    }

    public Optional<Player> findPlayerByName(String name) {
        return players.stream().filter(player -> player.getName().equalsIgnoreCase(name)).findFirst();
    }
}
