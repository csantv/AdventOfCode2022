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
            for (int i = 0; i < line.length() - 4; i++) {
                String sub = line.substring(i, i + 4);
                if (hasUniqueCharacters(sub)) {
                    System.out.println(i + 4);
                    break;
                }
            }
            for (int i = 0; i < line.length() - 14; i++) {
                String sub = line.substring(i, i + 14);
                if (hasUniqueCharacters(sub)) {
                    System.out.println(i + 14);
                    break;
                }
            }
        } catch (IOException ignore) {}
    }

    private boolean hasUniqueCharacters(String string) {
        for (int i = 0; i < string.length(); i++) {
            for (int j = i + 1; j < string.length(); j++) {
                if (string.charAt(i) == string.charAt(j)) return false;
            }
        }
        return true;
    }
}
