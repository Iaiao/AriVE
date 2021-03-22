import etoshapovalov.arive.Arive;
import etoshapovalov.arive.AriveVideo;

import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException, InterruptedException {
        Arive.initialize("C:\\Users\\andry\\Documents\\ffmpeg");
        AriveVideo video = new AriveVideo("./TEST");
        video.fragments.add(new AriveTestFragment());
        video.render();
    }
}
