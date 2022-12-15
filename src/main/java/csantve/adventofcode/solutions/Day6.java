package csantve.adventofcode.solutions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Day6 extends Day{
    @Override
    public void solve() {
        try (InputStream is = getResource("day6.txt")) {
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String line = br.readLine();
            System.out.println(line);
        } catch (IOException ignore) {}
    }
}
