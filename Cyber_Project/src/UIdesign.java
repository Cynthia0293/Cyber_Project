import javax.swing.*;
import javax.swing.border.AbstractBorder;
import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

public interface UIdesign {
    public static final Color COLOR_TXT = new Color(30, 37, 51);
    public static final Color COLOR_BTN = new Color(164, 199, 196);
    public static final Font FONT_TXT = new Font("Lunch Type", Font.PLAIN, 14);
    public static final Font FONT_BTN = new Font("Lunch Type", Font.TRUETYPE_FONT, 14);
    public class RoundJTextField extends JTextField {
        protected void paintComponent(Graphics g) {
            if (!isOpaque() && getBorder() instanceof RoundedCornerBorder) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setPaint(getBackground());
                g2.fill(((RoundedCornerBorder) getBorder()).getBorderShape(
                        0, 0, getWidth() - 1, getHeight() - 1));
                g2.dispose();
            }
            super.paintComponent(g);
        }
        public void updateUI() {
            super.updateUI();
            setOpaque(false);
            setBorder(new RoundedCornerBorder());
        }
    }
    public class RoundJPSWField extends JPasswordField {
        protected void paintComponent(Graphics g) {
            if (!isOpaque() && getBorder() instanceof RoundedCornerBorder) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setPaint(getBackground());
                g2.fill(((RoundedCornerBorder) getBorder()).getBorderShape(
                        0, 0, getWidth() - 1, getHeight() - 1));
                g2.dispose();
            }
            super.paintComponent(g);
        }
        public void updateUI() {
            super.updateUI();
            setOpaque(false);
            setBorder(new RoundedCornerBorder());
        }
    }
    class RoundedCornerBorder extends AbstractBorder {
        private static final Color ALPHA_ZERO = new Color(0x0, true);
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            Shape border = getBorderShape(x, y, width - 1, height - 1);
            g2.setPaint(ALPHA_ZERO);
            Area corner = new Area(new Rectangle2D.Double(x, y, width, height));
            corner.subtract(new Area(border));
            g2.fill(corner);
            g2.setPaint(Color.WHITE);
            g2.draw(border);
            g2.dispose();
        }
        public Shape getBorderShape(int x, int y, int w, int h) {
            int r = h; //h / 2;
            return new RoundRectangle2D.Double(x, y, w, h, r, r);
        }
        public Insets getBorderInsets(Component c) {
            return new Insets(4, 8, 4, 8);
        }
        public Insets getBorderInsets(Component c, Insets insets) {
            insets.set(4, 8, 4, 8);
            return insets;
        }
    }

    public class RoundRectButton extends JButton
    {
        public RoundRectButton(String s)
        {
            super(s);
            setMargin(new Insets(0,0,0,0));
            setBorder(new RoundedCornerBorder());
            setContentAreaFilled(false);
            setFocusPainted(false);
        }
        protected void paintComponent(Graphics g) {
            if (!isOpaque() && getBorder() instanceof RoundedCornerBorder) {
                Graphics2D g2 = (Graphics2D) g.create();
                if (getModel().isArmed()) {
                    g2.setColor(COLOR_BTN);
                } else {
                    g2.setColor(getBackground());
                }
                g2.fill(((RoundedCornerBorder) getBorder()).getBorderShape(
                        0, 0, getWidth() - 1, getHeight() - 1));
                g2.dispose();
            }
            super.paintComponent(g);
        }
        public void updateUI() {
            super.updateUI();
            setOpaque(false);
            setBorder(new RoundedCornerBorder());
        }
    }

    public class jPanelGradient extends JPanel{
        protected void paintComponent(Graphics g){
            Graphics2D g2=(Graphics2D)g;
            int width=getWidth();
            int height=getHeight();

            Color co1=new Color(206, 223, 232);
            Color co2=new Color(224, 234, 202);
            GradientPaint gp=new GradientPaint(0,20,co1,0,height,co2);
            g2.setPaint(gp);
            g2.fillRect(0,0,width,height);
        }
    }

    public static ImageIcon createAutoAdjustIcon(Image image, boolean constrained) {
        ImageIcon icon = new ImageIcon(image) {
            public synchronized void paintIcon(java.awt.Component cmp, Graphics g, int x, int y) {
                Point startPoint = new Point(0, 0);//默认绘制起点
                Dimension cmpSize = cmp.getSize();//获取组件大小
                Dimension imgSize = new Dimension(getIconWidth(), getIconHeight());//获取图像大小

                if(constrained) {
                    double ratio = 1.0*imgSize.width/imgSize.height;
                    imgSize.width = (int) Math.min(cmpSize.width, ratio*cmpSize.height);
                    imgSize.height = (int) (imgSize.width/ratio);
                    startPoint.x = (int)
                            (cmp.getAlignmentX()*(cmpSize.width - imgSize.width));
                    startPoint.y = (int)
                            (cmp.getAlignmentY()*(cmpSize.height - imgSize.height));
                } else {
                    imgSize = cmpSize;
                }
                if(getImageObserver() == null) {
                    g.drawImage(getImage(), startPoint.x, startPoint.y,
                            imgSize.width, imgSize.height, cmp);
                } else {
                    g.drawImage(getImage(), startPoint.x, startPoint.y,
                            imgSize.width, imgSize.height, getImageObserver());
                }
            };
        };
        return icon;
    }
    public static ImageIcon createAutoAdjustIcon(String filename, boolean constrained) {
        return createAutoAdjustIcon(new ImageIcon(filename).getImage(), constrained);
    }
}

