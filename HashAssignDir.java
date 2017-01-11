import java.io.*;
import java.util.Scanner;

public class HashAssignDir {

    public static int getPartition(String key, int numReduceTasks) {
        return (key.hashCode() & Integer.MAX_VALUE) % numReduceTasks;

    }

    public static void main (String[] args) throws FileNotFoundException {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the dir path: ");
        String file = scanner.next();
        System.out.print("Enter the number of reducers: ");
        int rnum = Integer.parseInt(scanner.next());

        // File file = new File(filepath);
        File dir = new File(file);
        File[] directoryListing = dir.listFiles();

        if (directoryListing != null) {
            for (File child : directoryListing) {
                try(BufferedReader br = new BufferedReader(new FileReader(child))) {
                    String key;
                    int hashvalue;
                    int[] assignment = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

                    for (String line; (line = br.readLine()) != null; ) {
                        // process the line.
                        if (line.contains(" ")) {
                            String[] splits = line.split(" ");
                            key = splits[0];
                            // key = line.substring(0,10);
                            // System.out.println("Key is: " + key);
                            hashvalue = getPartition(key, rnum);
                            assignment[hashvalue] += 1;
                            // System.out.println("Hashed to reducer: " + hashvalue);
                        }
                    }
                    for (int element : assignment) {
                        System.out.print(element + ",");
                    }
                } catch (IOException ie) {
                    ie.printStackTrace();
                }
            }
            // line is not visible here.
        }

        System.out.println("Finished!");
    }

}
