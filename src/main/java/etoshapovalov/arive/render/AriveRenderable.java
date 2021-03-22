package etoshapovalov.arive.render;

import etoshapovalov.arive.Arive;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class AriveRenderable {
    protected int frameOffset = 0;
    protected int zIndex = 0;
    protected int frameDuration = 0;

    private byte[] renderedVideo;
    private boolean isRendered = false;

    public byte[] getRenderedVideo() {
        assert isRendered : "Renderable needs to be rendered! You need to call AriveRenderable#render() first!";
        return renderedVideo;
    }

    public final void render() throws IOException {
        ProcessBuilder builder = new ProcessBuilder(Arive.getFFmpegPath(),"-f", "image2pipe", "-i", "-", "-r", "30", "-f", "mpegts", "-");
        builder.redirectErrorStream(true);
        Process process = builder.start();
        BufferedInputStream reader = new BufferedInputStream(process.getInputStream());
        for(int i = 0; i < frameDuration; i++){
            BufferedImage image = getNextFrame();

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(image, "png", baos);
            byte[] bytes = baos.toByteArray();

            process.getOutputStream().write(bytes);

            image.flush();
        }
        process.getOutputStream().close();


        List<Byte> videoList = new ArrayList<>();
        int c;
        while ((c=reader.read())!=-1){
            videoList.add((byte) c);
        }

        // Byte list to byte array
        renderedVideo = new byte[videoList.size()];
        for(int i = 0; i < videoList.size(); i++) {
            renderedVideo[i] = videoList.get(i);
        }

        isRendered = true;

    }
    protected abstract BufferedImage getNextFrame();

}
