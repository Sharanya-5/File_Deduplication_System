import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.Scanner;

public class BackUpFile {
    private final List<Pair> Duplicate;

    BackUpFile(List<Pair> Duplicate) {
        this.Duplicate = Duplicate;

    }
    public void backupFiles(){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the Directory path to backup the file:");
        String target=sc.nextLine();

        File targetFile=new File(target);
        if(!targetFile.exists() || target.isBlank()){
            targetFile.mkdir();
        }
        boolean keepFirst=true;
        for(Pair pair:Duplicate){
            if(keepFirst){
                keepFirst=false;
                continue;
            }
            File srcFile=new File(pair.getFilePath());
            System.out.println(srcFile.getAbsolutePath());
            Path src=srcFile.toPath();
            Path dst= Paths.get(target,src.getFileName().toString());

            try{
                Files.move(src,dst, StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Backup Successful!!!File Copied to"+dst.toAbsolutePath());
            } catch (IOException e) {
                System.out.println("Backup Failed!!! Error:"+e.getMessage());
            }


        }
    }


}
