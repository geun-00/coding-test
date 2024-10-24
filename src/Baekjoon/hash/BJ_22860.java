package Baekjoon.hash;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/22860">백준 22860번 - 해시 : 폴더 정리 (small)</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-22860%EB%B2%88-%ED%8F%B4%EB%8D%94-%EC%A0%95%EB%A6%ACsmall">velog</a>
 *
 * @since 2024-10-24
 */
public class BJ_22860 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        //폴더 이름, 폴더 정보
        HashMap<String, Folder> folderMap = new HashMap<>();

        for (int i = 0; i < n + m; i++) {
            st = new StringTokenizer(br.readLine());

            String p = st.nextToken(); //상위 폴더의 이름
            String f = st.nextToken(); //폴더 또는 파일의 이름
            String c = st.nextToken(); //폴더/파일 여부(1=폴더, 0=파일)

            //폴더가 없으면 빈 폴더 새로 생성
            if (folderMap.get(p) == null) {
                folderMap.put(p, new Folder());
            }

            //상위 폴더 가져오기
            Folder parentFolder = folderMap.get(p);

            //상위 폴더에 하위 폴더 추가
            if ("1".equals(c)) {

                //예제처럼 main 폴더 먼저 저장하기 편하게 오지 않을 수 있다.
                //기존에 생성된 폴더라면 해당 폴더를 연결해야 정상적인 계층 관계가 형성된다.
                Folder folder = folderMap.getOrDefault(f, new Folder());

                parentFolder.addFolder(f, folder); //상위 폴더에 하위 폴더 추가
                folderMap.put(f, folder);          //폴더 정보 추가
            }
            //상위 폴더에 하위 파일 추가
            else {
                parentFolder.addFile(f);
            }
        }

        int q = Integer.parseInt(br.readLine());

        for (int i = 0; i < q; i++) {
            String query = br.readLine();

            //성능 최적화
            int index = query.lastIndexOf("/");
            String s = query.substring(index + 1);

            Folder folder = folderMap.get(s);

            //성능 개선 필요
//            String[] split = query.split("/");
//            for (int j = 1; j < split.length; j++) {
//                folder = folder.folders.get(split[j]);
//            }

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

        HashSet<String> files;              //현재 폴더가 보유 중인 파일
        HashMap<String, Folder> folders;    //현재 폴더가 보유 중인 폴더
        FileInfo cachedFileInfo;            //성능 최적화를 위한 캐시 데이터

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

            HashSet<String> currentFiles = new HashSet<>(files); //현재 폴더가 보유 중인 파일
            int fileCount = files.size();                        //현재 폴더가 보유 중인 파일의 개수

            //자신의 하위 폴더 탐색
            for (Folder folder : folders.values()) {

                FileInfo info = folder.getFileInfo(); //재귀로 가장 하위 폴더 정보부터 가져오기

                //현재 폴더 파일 목록에 하위 폴더의 파일 목록 추가(Set으로 중복 제거)
                currentFiles.addAll(info.files);
                //현재 폴더 파일 개수에 하위 폴더 파일 개수 추가(파일의 총 개수 계산)
                fileCount += info.fileCount;
            }

            //파일 정보 반환하면서 캐시 데이터 저장
            return cachedFileInfo = new FileInfo(currentFiles, fileCount);
        }
    }

    static class FileInfo {

        HashSet<String> files; //현재 폴더와 하위 폴더를 포함한 폴더들의 파일 목록
        int fileCount;         //현재 폴더와 하위 폴더를 포함한 폴더들의 파일 총 개수

        public FileInfo(HashSet<String> files, int fileCount) {
            this.files = files;
            this.fileCount = fileCount;
        }
    }
}