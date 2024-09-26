import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

public class BlurredBall extends JPanel {

    private BufferedImage ballImage;

    public BlurredBall() {
        // Create an image with a single ball
        ballImage = new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = ballImage.createGraphics();

        // Fill the image with a white background
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, ballImage.getWidth(), ballImage.getHeight());

        // Draw a red ball in the center
        g2d.setColor(Color.RED);
        g2d.fillOval(350, 250, 100, 100); // Ball of 100x100 pixels

        g2d.dispose();

        // Apply a strong blur to the ball image
        applyStrongBlur();
    }

    // Method to apply a strong blur to the ball image
    private void applyStrongBlur() {
        // Create a larger and stronger blur kernel (7x7)
        float[] blurMatrix = new float[10000];
        for (int i = 0; i < 10000; i++)
            blurMatrix[i] = 1.0f / 10000.0f;

        // Normalize the kernel
        float sum = 0.0f;
        for (float value : blurMatrix) {
            sum += value;
        }
        for (int i = 0; i < blurMatrix.length; i++) {
            blurMatrix[i] /= sum; // Normalize the kernel values
        }

        // Apply convolution to create the blur effect
        ConvolveOp blurOp = new ConvolveOp(new Kernel(100, 100, blurMatrix), ConvolveOp.EDGE_NO_OP, null);

        // Create a new image to store the blurred result
        BufferedImage blurredImage = new BufferedImage(ballImage.getWidth(), ballImage.getHeight(),
                ballImage.getType());

        // Apply the blur multiple times to increase the intensity
        for (int i = 0; i < 1; i++) { // Apply blur 5 times
            blurOp.filter(ballImage, blurredImage);
            // Copy the blurred image back to the original for further blurring
            Graphics2D g2d = ballImage.createGraphics();
            g2d.drawImage(blurredImage, 0, 0, null);
            g2d.dispose();
        }

        // Replace the original image with the final blurred image
        ballImage = blurredImage;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the blurred image
        g.drawImage(ballImage, 0, 0, null);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Aggressive Blurred Ball");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.add(new BlurredBall());
        frame.setVisible(true);
    }
}
