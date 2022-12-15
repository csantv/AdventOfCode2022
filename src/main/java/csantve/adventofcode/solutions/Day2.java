package csantve.adventofcode.solutions;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Objects;
import java.util.StringTokenizer;

public class Day2 extends Day {
    @Override
    public void solve() {
        // X, A -> rock
        // Y, B -> paper
        // Z, C -> scissor
        System.out.println("=== DAY 2 SOLUTION ===");
        Map<String, Long> pointMap = Map.ofEntries(
                Map.entry("X", 1L),
                Map.entry("Y", 2L),
                Map.entry("Z", 3L)
        );

        BiMap<String, String> decode = HashBiMap.create();
        decode.put("X", "A");
        decode.put("Y", "B");
        decode.put("Z", "C");

        BiMap<String, String> winsAgainst = HashBiMap.create();
        winsAgainst.put("X", "C");
        winsAgainst.put("Y", "A");
        winsAgainst.put("Z", "B");

        BiMap<String, String> lossesAgainst = HashBiMap.create();
        lossesAgainst.put("X", "B");
        lossesAgainst.put("Y", "C");
        lossesAgainst.put("Z", "A");

        // == BEGIN FIRST PART ==
        try (InputStream is = getResource("day2.txt")) {
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String line;
            long points = 0;
            // == FIRST PART ==
            while ((line = br.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(line, " ");
                String opponent = st.nextToken();
                String mine = st.nextToken();
                points += pointMap.get(mine);
                if (Objects.equals(decode.get(mine), opponent)) {
                    // draw
                    points += 3;
                } else if (Objects.equals(winsAgainst.get(mine), opponent)) {
                    // win
                    points += 6;
                }
            }
            System.out.println(points);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // == END FIRST PART ==

        // == BEGIN SECOND PART ==
        try (InputStream is = getResource("day2.txt")) {
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String line;
            long points = 0;
            while ((line = br.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(line, " ");
                String opponent = st.nextToken();
                String outcome = st.nextToken();
                String mine = decode.inverse().get(opponent);
                // X -> lose
                // Y -> draw
                // Z -> win
                if (Objects.equals(outcome, "X")) {
                    mine = lossesAgainst.inverse().get(opponent);
                } else if (Objects.equals(outcome, "Z")) {
                    mine = winsAgainst.inverse().get(opponent);
                    points += 6;
                } else {
                    points += 3;
                }
                points += pointMap.get(mine);
            }
            System.out.println(points);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // == END SECOND PART ==
    }
}
