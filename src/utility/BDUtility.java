/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utility;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import javax.imageio.ImageIO;
import javax.swing.*;

public class BDUtility {

    public static void setImage(JFrame frame, String imagePath, int newWidth,
            int newHeight) {
        try {
            // Load the original image
            BufferedImage originalImage = ImageIO.read(BDUtility.class.getResourceAsStream(imagePath));

            // Create a resized BufferedImage
            BufferedImage resizedImage = new BufferedImage(newWidth, newHeight,
                    BufferedImage.TYPE_INT_RGB);
            resizedImage.createGraphics().drawImage(originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH),
                    0, 0, null);
           

            // Create an ImageIcon from the resized image
            ImageIcon backgroundImage = new ImageIcon(resizedImage);

            // Set the image to a JLabel
            JLabel backgroundLabel = new JLabel(backgroundImage);
            backgroundLabel.setBounds(0, 0, newWidth, newHeight);

            // Set the label to the frame
            //frame.getContentPane().removeAll(); // Clear any existing components
            frame.getContentPane().add(backgroundLabel, BorderLayout.CENTER);
           // frame.setSize(newWidth, newHeight); // Resize frame
            frame.validate();
           // frame.repaint();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static HashMap<String, JFrame> formsMap = new HashMap<>();

    public static void openForm(String FormName, JFrame formInstance) {
        JFrame existingForm = formsMap.get(FormName);
        if (existingForm == null || !existingForm.isVisible()) {
            formsMap.put(FormName, formInstance);
            formInstance.setVisible(true);
        } else {
            existingForm.toFront();
        }
    }

    public static String getPath(String finalPath) {
        String projectPath = System.getProperty("user.dir");
        return projectPath + "\\src\\" + finalPath;
    }

    public static String getFileExtension(String fileName) {
        int lastDotIndex = fileName.lastIndexOf(".");
        if (lastDotIndex != -1) {
            return fileName.substring(lastDotIndex + 1);
        }
        return "";
    }

    public static BufferedImage scaleImage(BufferedImage originalImage, BufferedImage selectedImage) {
        int width = selectedImage.getWidth();
        int height = selectedImage.getHeight();
        BufferedImage scaledImage = new BufferedImage(width, height, originalImage.getType());
        scaledImage.createGraphics().drawImage(originalImage.getScaledInstance(width,height, Image.SCALE_SMOOTH), 0, 0 ,null);
        return scaledImage;
    }

}
