package nl.captcha.gimpy;

import java.awt.image.BufferedImage;
import nl.jhlabs.image.ShadowFilter;

import static nl.captcha.util.ImageUtil.applyFilter;

/**
 * Twists text and adds a dark drop-shadow.
 * 
 * @author <a href="mailto:james.childers@gmail.com">James Childers</a>
 * 
 */
public class DropShadowGimpyRenderer implements GimpyRenderer {
	private static final int DEFAULT_RADIUS = 3;
	private static final int DEFAULT_OPACITY = 75;
	
	private final int _radius;
	private final int _opacity;
	
	public DropShadowGimpyRenderer() {
		this(DEFAULT_RADIUS, DEFAULT_OPACITY);
	}
	
	public DropShadowGimpyRenderer(int radius, int opacity) {
		_radius = radius;
		_opacity = opacity;
	}

    public void gimp(BufferedImage image) {
        ShadowFilter sFilter = new ShadowFilter();
        sFilter.setRadius(_radius);
        sFilter.setOpacity(_opacity);
        
        BufferedImage buffer = sFilter.filter(image, null);
        applyFilter(buffer, null);
    }
}