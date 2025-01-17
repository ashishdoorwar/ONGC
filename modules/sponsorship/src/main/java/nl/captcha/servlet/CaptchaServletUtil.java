package nl.captcha.servlet;

import nl.captcha.Captcha;
import nl.captcha.audio.Sample;

import com.liferay.portal.kernel.util.PortalUtil;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.portlet.PortletRequest;
import javax.portlet.PortletSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioSystem;

public final class CaptchaServletUtil {

    public static void writeImage(HttpServletResponse response, BufferedImage bi) {
        response.setHeader("Cache-Control", "private,no-cache,no-store");
        response.setContentType("image/png");	// PNGs allow for transparency. JPGs do not.
        try {
            writeImage(response.getOutputStream(), bi);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeImage(OutputStream os, BufferedImage bi) {
    	try {
			ImageIO.write(bi, "png", os);
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    public static void writeAudio(OutputStream os, Sample sample) {
       try {
          ByteArrayOutputStream baos = new ByteArrayOutputStream(1024);
          AudioSystem.write(sample.getAudioInputStream(), AudioFileFormat.Type.WAVE, baos);
          os.write(baos.toByteArray());
          os.flush();
          os.close();
       } catch (IOException e) {
           e.printStackTrace();
       }
   }

    public static void writeAudio(HttpServletResponse response, Sample sample) {
       response.setHeader("Cache-Control", "private,no-cache,no-store");
       response.setContentType("audio/wave");

       try {
           // Convert to BAOS so we can set the content-length header
           ByteArrayOutputStream baos = new ByteArrayOutputStream(1024);
           AudioSystem.write(sample.getAudioInputStream(),
                   AudioFileFormat.Type.WAVE, baos);
           response.setContentLength(baos.size());

           OutputStream os = response.getOutputStream();
           os.write(baos.toByteArray());
           os.flush();
           os.close();
       } catch (IOException e) {
           e.printStackTrace();
       }
   }
    
    
    public static boolean checkCaptcha(String answer,PortletRequest req) {
    	boolean flag=false;
    	try { 
    	PortletSession pss=req.getPortletSession();
    	if(pss!=null && pss.getAttribute("ongc.captchaObj")!=null) {
    		Captcha cap=(Captcha)pss.getAttribute("ongc.captchaObj");
    		flag=cap.isCorrect(answer);
    	}else {
    		System.out.println(pss+"   >>    "+pss.getAttribute("ongc.captchaObj"));
    	}
    	}catch (Exception e) {
			e.printStackTrace();
		}
    	return flag;
    }
}
