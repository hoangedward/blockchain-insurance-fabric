package main;
 
import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
 
public class Main3 {
    
    final static int MAX_DEPTH = 10;
    
    final static FileFilter FILE_FILTER = new FileFilter() {
        
        @Override
        public boolean accept(File pathname) {
            if(pathname.getName().equals(".git")
                    || pathname.getName().endsWith(".png")
                    || pathname.getName().endsWith(".svg")) {
                return false;
            }
            return true;
        }
    };
 
    public static void main(String[] args) {
        final int depth = 0;
        
        final String path = "D:\\Hyperledger\\Project\\build-blockchain-insurance-app";
        File file = new File(path);
        List<File> listFiles = sortByFolder(file.listFiles(FILE_FILTER));
        printName(listFiles, depth);
    }
 
    private static void printName(List<File> listFiles, int depth) {
        if(depth > MAX_DEPTH) {
            return;
        }
        for (File file : listFiles) {
            System.out.println(printTab(depth, file.isDirectory()) + file.getName());
            if(file.isDirectory()) {
                printName(sortByFolder(file.listFiles(FILE_FILTER)), depth + 1);
            }
        }
    }
 
    private static String printTab(int depth, boolean isFolder) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < depth; i++) {
            sb.append("  ");
        }
        if(isFolder) {
            sb.append("+");
        }
        return sb.toString();
    }
    
 
    private static List<File> sortByFolder(File[] files) {
        List<File> list = Arrays.asList(files);
        list.sort(new Comparator<File>() {
 
            @Override
            public int compare(File o1, File o2) {
                int file1 = o1.isDirectory() ? 0 : 1;
                int file2 = o2.isDirectory() ? 0 : 1;
                return file1 - file2;
            }
        });
        return list;
    }
    
}