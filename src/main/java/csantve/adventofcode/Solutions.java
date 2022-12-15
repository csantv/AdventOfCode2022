package csantve.adventofcode;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Solutions {
    private static final ClassLoader classloader = Thread.currentThread().getContextClassLoader();

    public void day5() {
        System.out.println("=== DAY 5 SOLUTION ===");

        try (InputStream is = getResource("day5.txt")) {
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String line;
            // build stacks
            List<Stack<String>> stacks = null;
            while ((line = br.readLine()) != null) {
                if (line.charAt(1) == '1') break;
                if (stacks == null) {
                    stacks = Stream.generate(Stack<String>::new).limit(Math.ceilDiv(line.length(), 4)).toList();
                }
                for (int i = 0; i < line.length(); i += 4) {
                    String sub = line.substring(i, i + 3);
                    if (sub.contains("[")) {
                        stacks.get(i / 4).add(0, String.valueOf(sub.charAt(1)));
                    }
                }
            }
            if (stacks == null) return;
            br.readLine();
            while ((line = br.readLine()) != null) {
                Pattern p = Pattern.compile("[0-9]+");
                Matcher m = p.matcher(line);
                List<Integer> orders = new ArrayList<>();
                while (m.find()) {
                    orders.add(Integer.parseInt(m.group()));
                }
                Stack<String> to = stacks.get(orders.get(2) - 1), from = stacks.get(orders.get(1) - 1);
//                for (int i = 0; i < orders.get(0); i++) {
//                    to.push(from.pop());
//                }
                var sub = from.subList(from.size() - orders.get(0), from.size());
                to.addAll(sub);
                sub.clear();
            }
            // get crate in each top of stack
            StringBuilder res = new StringBuilder();
            for (Stack<String> stack : stacks) {
                res.append(stack.peek());
            }
            System.out.println(res);
        } catch (IOException ignore) {}
    }

    public void day4() {
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

    public void day3() {
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

    public void day2() {
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
        if (is == null) throw new IOException(String.format("File %s not found.", path));
        return is;
    }
}
