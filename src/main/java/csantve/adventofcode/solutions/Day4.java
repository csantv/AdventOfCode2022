package csantve.adventofcode.solutions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day4 extends Day {
    @Override
    public void solve() {
        System.out.println("=== DAY 4 SOLUTION ===");

        try (InputStream is = getResource("day4.txt")) {
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String line;
            long repeats = 0;
            long repeats2 = 0;
            while ((line = br.readLine()) != null) {
                var groups = line.split(",");
                var g1 = IntStream.rangeClosed(Integer.parseInt(groups[0].split("-")[0]),
                        Integer.parseInt(groups[0].split("-")[1])).boxed().collect(Collectors.toSet());
                var g2 = IntStream.rangeClosed(Integer.parseInt(groups[1].split("-")[0]),
                        Integer.parseInt(groups[1].split("-")[1])).boxed().collect(Collectors.toSet());
                if (g1.containsAll(g2) || g2.containsAll(g1)) repeats++;
                if (!Collections.disjoint(g1, g2)) repeats2++;
            }
            System.out.println(repeats);
            System.out.println(repeats2);
        } catch (IOException ignore) {}
    }
}
