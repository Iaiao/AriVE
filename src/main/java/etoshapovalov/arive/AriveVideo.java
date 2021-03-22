package etoshapovalov.arive;

import etoshapovalov.arive.render.AriveRenderable;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AriveVideo {
    private String rootPath;

    //TODO: Replace with AriveFragment
    public List<AriveRenderable> fragments = new ArrayList<>();
    public AriveVideo(String rootPath){
        new File(rootPath).mkdir();
        this.rootPath = rootPath;
    }
    public void render() throws IOException, InterruptedException {

        for(AriveRenderable fragment : fragments){
            ProcessBuilder builder = new ProcessBuilder(Arive.getFFmpegPath(),"-f","mpegts","-i", "-", "-c:v", "libx264", rootPath+"/out.mp4");
            builder.redirectErrorStream(true);
            builder.redirectOutput(ProcessBuilder.Redirect.INHERIT);

            Process process = builder.start();

            fragment.render();
            process.getOutputStream().write(fragment.getRenderedVideo());

            process.getOutputStream().close();
            process.waitFor();
        }

    }
}
