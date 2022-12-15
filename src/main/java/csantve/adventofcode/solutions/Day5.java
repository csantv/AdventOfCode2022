package csantve.adventofcode.solutions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Day5 extends Day {
    @Override
    public void solve() {
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
}
