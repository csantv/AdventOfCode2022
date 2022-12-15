package csantve.adventofcode.solutions;

import java.io.IOException;
import java.io.InputStream;

public abstract class Day {
    private static final ClassLoader classloader = Thread.currentThread().getContextClassLoader();

    public abstract void solve();

    protected InputStream getResource(String path) throws IOException {
        InputStream is = classloader.getResourceAsStream(path);
        if (is == null) throw new IOException(String.format("File %s not found.", path));
        return is;
    }
}
