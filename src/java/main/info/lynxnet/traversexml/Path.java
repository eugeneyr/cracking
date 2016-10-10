package info.lynxnet.traversexml;

import java.util.ArrayList;
import java.util.List;

public class Path {
    private String path;

    static class PathInfo {
        boolean absolute;
        List<String> components = new ArrayList<String>();

        public PathInfo(String path) {
            if (path != null && !path.isEmpty()) {
                this.absolute = path.startsWith("/");
                for (String s : path.split("/")) {
                    if (s.isEmpty()) {
                        continue;
                    }
                    if (s.equals("..")) {
                        if (!this.components.isEmpty()) {
                            System.out.println("Changing to the parent: " + this.components.get(this.components.size() - 1));
                            this.components.remove(this.components.size() - 1);
                        }
                        continue;
                    }
                    if (!s.matches("[a-zA-Z]+")) {
                        throw new IllegalArgumentException(String.format("Not from my ABC: %s", s));
                    }
                    this.components.add(s);
                }
            }
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            if (this.absolute) {
                sb.append("/");
            }
            sb.append(String.join("/", this.components));
            return sb.toString();
        }
    }

    public Path(String path) {
        this.path = new PathInfo(path).toString();
    }

    public String getPath() {
        return path;
    }

    public Path cd(String newPath) {
        PathInfo newPathInfo = new PathInfo(newPath);
        if (newPathInfo.absolute) {
            this.path = newPathInfo.toString();
            return this;
        }
        String p = this.path + (this.path.isEmpty() ? "" : "/") + newPath;
        newPathInfo = new PathInfo(p);
        if (newPathInfo.absolute && !newPathInfo.components.isEmpty() && newPathInfo.components.get(0).equals("..")) {
            throw new IllegalArgumentException("Cannot cd higher than the root directory");
        }
        this.path = newPathInfo.toString();
        return this;
    }

    public static void main(String[] args) {
        Path path = new Path("/a/b/c/d");
        System.out.println(path.cd("/x/../y").getPath());

    }
}