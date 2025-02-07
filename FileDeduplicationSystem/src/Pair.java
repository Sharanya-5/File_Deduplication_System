public class Pair{
    private final String FileName;
    private final String FilePath;

    Pair(String FileName, String FilePath){
        this.FileName = FileName;
        this.FilePath = FilePath;
    }
    public String getFileName(){
        return FileName;
    }
    public String  getFilePath(){
        return FilePath;
    }
}
