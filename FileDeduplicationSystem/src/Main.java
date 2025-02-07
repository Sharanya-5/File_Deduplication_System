import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class Main{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the path of the folder:");
        String fPath = sc.nextLine();
        File Dir = new File(fPath);

        if ((!Dir.exists()) || (!Dir.isDirectory())) {

            System.out.println("\nOOPS!!The folder does not exist");
            return;
        }

        HashMap<String, List<Pair>> mp = new HashMap<>();
        System.out.println("\n------------------SCANNING DUPLICATES FILES IN THE FOLDER-----------------------\n");
        scanFolder(mp, Dir);

        boolean flag = false;
        for (List<Pair> list : mp.values()) {
            if (list.size() > 1) {
                flag = true;
                System.out.println("Duplicate files found in the Directory:\n");
                for (Pair pair : list) {
                    System.out.println(pair.getFileName() +" found Absolute Path: " + pair.getFilePath());

                }
                System.out.println("Want to delete or backup the file??\n" +
                        "\t\t\tEnter 1 to delete the file\n" +
                        "\t\t\tEnter 2 to backup the file");
                switch (sc.nextInt()) {
                    case 1:
                        DeleteFile delete = new DeleteFile(list);
                        delete.deletingFile();
                        break;
                    case 2:
                        BackUpFile backup=new BackUpFile(list);
                        backup.backupFiles();
                        break;
                    default:
                        System.out.println("\nInvalid Option!!!");
                }
            }

        }
        if (!flag) {
            System.out.println("No Duplicate files Found in the Directory/Folder.");
        }
    }
    private static void scanFolder(HashMap<String, List<Pair>> mp, File dir) {
        File[] files=dir.listFiles();
        try {
            for(File file:files) {
                if(file!=null){
                    if(file.isDirectory()) {
                        scanFolder(mp,file);
                    }
                    else {
                        String fileHash=fileHashing(file);
                        Pair pair=new Pair(file.getName(),file.getAbsolutePath());
                        if(!mp.containsKey(fileHash)) {
                            mp.put(fileHash, new ArrayList<>());
                        }
                        mp.get(fileHash).add(pair);
                    }
                }

            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }


    private static String fileHashing(File f) {
        MessageDigest dgst=null;
        try{
            dgst=MessageDigest.getInstance("SHA-256");
            FileInputStream fis=new FileInputStream(f);
            BufferedInputStream bis=new BufferedInputStream(fis);

            byte[] buffer=new byte[1024];
            int len;
            while((len=bis.read(buffer))!=-1)
                    dgst.update(buffer,0,len);

            return byteConvToHex(dgst.digest());

        } catch (IOException e ) {
            throw new RuntimeException(e);
        }
        catch(NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private static String byteConvToHex(byte[] digest) {
        StringBuilder hexString=new StringBuilder();
        for (byte b : digest) {
            hexString.append(String.format("%02X", b));
        }
        return hexString.toString();
    }
}