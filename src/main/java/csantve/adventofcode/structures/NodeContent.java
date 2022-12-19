package csantve.adventofcode.structures;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class NodeContent {
    private NodeType type;
    private Long size = 0L;
    private String name;

    public NodeContent(String name) {
        this.name = name;
        this.type = NodeType.FOLDER;
    }

    public NodeContent(String name, Long size) {
        this.name = name;
        this.size = size;
        this.type = NodeType.FILE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NodeContent that = (NodeContent) o;

        return getName().equals(that.getName());
    }

    @Override
    public int hashCode() {
        return getName().hashCode();
    }
}
