import java.io.File;
import java.util.List;

public class DeleteFile {
    private final List<Pair> duplicates;

    DeleteFile(List<Pair> duplicates) {
        this.duplicates = duplicates;
    }

    public void deletingFile() {
        boolean keepSafe = true;
        for (Pair pair : duplicates) {
            File file = new File(pair.getFilePath());
            if (keepSafe) {
                keepSafe = false;
                continue;
            }
            if (file.delete()) {
                System.out.println("File deleted Successfully\n");
            } else {
                System.out.println("File could not be deleted\n");
            }
        }
    }
}

