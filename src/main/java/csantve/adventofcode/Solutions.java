package csantve.adventofcode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Solutions {
    private final ClassLoader classloader = Thread.currentThread().getContextClassLoader();

    public void day1() {
        System.out.println("=== DAY 1 SOLUTION ===");
        try (InputStream is = classloader.getResourceAsStream("day1/input.txt")) {
            assert is != null;
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
            System.out.println(calories.get(0)+calories.get(1)+calories.get(2));
        } catch (IOException ignore) {}
    }
}
