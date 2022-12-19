package csantve.adventofcode.solutions;

import csantve.adventofcode.structures.Node;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Day7 extends Day {
    @Override
    public void solve() {
        try (InputStream is = getResource("day7.txt")) {
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String line;
            Node root = new Node("/");
            while ((line = br.readLine()) != null) {
                var arr = line.split(" ");
                if (line.charAt(0) == '$') {
                    if (Objects.equals(arr[1], "cd")) {
                        root = root.traverse(arr[2]);
                    }
                } else {
                    Node node;
                    if (Objects.equals(arr[0], "dir")) {
                        node = new Node(arr[1]);
                    } else {
                        node = new Node(arr[1], Long.valueOf(arr[0]));
                    }
                    root.addChild(node);
                }
            }
            root = root.traverse("/");
            List<Node> small = new ArrayList<>();
            root.calculateSizes(root, small);
            Long sizes = 0L;
            for (var node: small) {
                sizes += node.getContent().getSize();
            }
            System.out.println(sizes);
        } catch (IOException ignore) {}
    }
}
