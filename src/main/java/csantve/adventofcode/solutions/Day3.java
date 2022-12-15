package csantve.adventofcode.solutions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Day3 extends Day {
    @Override
    public void solve() {
        System.out.println("=== DAY 3 SOLUTION ===");

        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        String priorities = alphabet + alphabet.toUpperCase();

        try (InputStream is = getResource("day3.txt")) {
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String line;
            long prio = 0;
            while ((line = br.readLine()) != null) {
                String first = line.substring(0, line.length() / 2);
                String second = line.substring(line.length() / 2);
                for (String ch: first.split("")) {
                    if (second.contains(ch)) {
                        prio += priorities.indexOf(ch) + 1;
                        break;
                    }
                }
            }
            System.out.println(prio);
        } catch (IOException ignore) {}

        try (InputStream is = getResource("day3.txt")) {
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String line1;
            long prio = 0;
            while ((line1 = br.readLine()) != null) {
                String line2 = br.readLine();
                String line3 = br.readLine();
                for (String ch: line1.split("")) {
                    if (line2.contains(ch) && line3.contains(ch)) {
                        prio += priorities.indexOf(ch) + 1;
                        break;
                    }
                }
            }
            System.out.println(prio);
        } catch (IOException ignore) {}
    }
}
