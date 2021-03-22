import etoshapovalov.arive.render.AriveRenderable;

import java.awt.*;
import java.awt.image.BufferedImage;

public class AriveTestFragment extends AriveRenderable {
    public AriveTestFragment(){
        frameDuration = 30;
    }
    @Override
    protected BufferedImage getNextFrame() {
        BufferedImage bufferedImage = new BufferedImage(1920,1080,BufferedImage.TYPE_INT_ARGB);
        bufferedImage.getGraphics().setColor(Color.red);
        bufferedImage.getGraphics().drawRect(0,0,30,30);
        bufferedImage.getGraphics().setColor(Color.blue);
        bufferedImage.getGraphics().drawRect(30,0,30,30);
        bufferedImage.getGraphics().setColor(Color.green);
        bufferedImage.getGraphics().drawRect(60,0,30,30);

        return bufferedImage;
    }
}
