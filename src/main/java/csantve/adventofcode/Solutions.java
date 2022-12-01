package csantve.adventofcode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Solutions {
    private static final ClassLoader classloader = Thread.currentThread().getContextClassLoader();

    public void day2() {
        System.out.println("=== DAY 2 SOLUTION ===");
        try (InputStream is = getResource("day2.txt")) {
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String line = br.readLine();
            System.out.println(line);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void day1() {
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

    private InputStream getResource(String path) throws IOException {
        InputStream is = classloader.getResourceAsStream(path);
        if (is == null) throw new IOException();
        return is;
    }
}
