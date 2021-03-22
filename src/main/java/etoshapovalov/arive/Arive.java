package etoshapovalov.arive;

public class Arive {
    private static boolean isInitialized = false;
    private static String ffmpegPath;

    public static String getFFmpegPath() {
        String localFFmpegPath = ffmpegPath + "/ffmpeg";
        if(System.getProperty("os.name").contains("win")){
            localFFmpegPath += ".exe";
        }
        return localFFmpegPath;
    }

    public static void initialize(String _ffmpegPath){
        ffmpegPath = _ffmpegPath;
        isInitialized = true;
    }
    public static void checkInitialization(){
        assert isInitialized : "Arive is not initialized! Call Arive#initialize() first!";
    }
}
