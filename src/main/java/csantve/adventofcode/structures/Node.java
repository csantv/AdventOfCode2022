package csantve.adventofcode.structures;

import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter @Setter
public class Node {
    private Node parent = null;

    private Map<String, Node> children = null;

    private NodeContent content;

    public Node(String name) {
        this.content = new NodeContent(name);
        this.children = new HashMap<>();
    }

    public Node(String name, Long size) {
        this.content = new NodeContent(name, size);
    }

    public void addChild(Node node) {
        if (children.containsKey(node.getContent().getName())) return;
        node.setParent(this);
        children.put(node.getContent().getName(), node);
    }

    public Node traverse(String name) {
        if (Objects.equals(name, "..")) return this.parent;
        if (Objects.equals(name, "/")) {
            Node traverser = this;
            while (traverser.getParent() != null) traverser = traverser.getParent();
            return traverser;
        }
        return this.children.get(name);
    }

    public void calculateSizes(Node node, List<Node> small) {
        if (node.getChildren() == null) return;
        for (var n: node.getChildren().entrySet()) {
            calculateSizes(n.getValue(), small);
        }
        Long size = 0L;
        for (var n: node.getChildren().entrySet()) {
            size += n.getValue().getContent().getSize();
        }
        node.getContent().setSize(size);
        if (size < 100000) small.add(node);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Node node = (Node) o;

        return getContent().equals(node.getContent());
    }

    @Override
    public int hashCode() {
        return getContent().hashCode();
    }

    @Override
    public String toString() {
        return String.format("Node(%s, %s)", content.getType().name(), content.getName());
    }
}
