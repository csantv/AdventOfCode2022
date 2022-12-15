package csantve.adventofcode.solutions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Day1 extends Day {
    @Override
    public void solve() {
        System.out.println("=== DAY 1 SOLUTION ===");
        try (InputStream is = getResource("day1.txt")) {
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            ArrayList<Long> calories = new ArrayList<>();
            String line;
            long count = 0L;
            while ((line = br.readLine()) != null) {
                if (line.equals("")) {
                    calories.add(count);
                    count = 0L;
                    continue;
                }
                count += Long.parseLong(line);
            }
            long max = Collections.max(calories);
            System.out.println(max);

            calories.sort(Collections.reverseOrder());
            System.out.println(calories.get(0) + calories.get(1) + calories.get(2));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
