import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        HashMap<String, Folder> folderMap = new HashMap<>();

        for (int i = 0; i < n + m; i++) {
            st = new StringTokenizer(br.readLine());

            String p = st.nextToken();
            String f = st.nextToken();
            String c = st.nextToken();

            if (folderMap.get(p) == null) {
                folderMap.put(p, new Folder());
            }

            Folder parentFolder = folderMap.get(p);

            if ("1".equals(c)) {
                Folder folder = folderMap.getOrDefault(f, new Folder());
                parentFolder.addFolder(f, folder);
                folderMap.put(f, folder);
            } else {
                parentFolder.addFile(f);
            }
        }

        int q = Integer.parseInt(br.readLine());

        for (int i = 0; i < q; i++) {
            String query = br.readLine();

            int index = query.lastIndexOf("/");
            String s = query.substring(index + 1);

            Folder folder = folderMap.get(s);

            FileInfo info = folder.getFileInfo();

            sb
                    .append(info.files.size())
                    .append(" ")
                    .append(info.fileCount)
                    .append("\n");
        }

        System.out.print(sb);
    }

    static class Folder {

        HashMap<String, Folder> folders;
        HashSet<String> files;
        FileInfo cachedFileInfo;

        public Folder() {
            folders = new HashMap<>();
            files = new HashSet<>();
        }

        public void addFile(String file) {
            files.add(file);
        }

        public void addFolder(String name, Folder folder) {
            folders.put(name, folder);
        }

        public FileInfo getFileInfo() {
            if (cachedFileInfo != null) {
                return cachedFileInfo;
            }

            HashSet<String> tempFiles = new HashSet<>(files);
            int fileCount = files.size();

            for (Folder folder : folders.values()) {
                FileInfo info = folder.getFileInfo();
                tempFiles.addAll(info.files);
                fileCount += info.fileCount;
            }

            return cachedFileInfo = new FileInfo(tempFiles, fileCount);
        }
    }

    static class FileInfo {

        HashSet<String> files;
        int fileCount;

        public FileInfo(HashSet<String> files, int fileCount) {
            this.files = files;
            this.fileCount = fileCount;
        }
    }
}