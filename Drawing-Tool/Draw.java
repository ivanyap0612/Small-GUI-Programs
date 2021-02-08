import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import javax.swing.event.*;

public class Draw extends JFrame implements MouseMotionListener, MouseListener, ChangeListener
{
    JPanel canvas = new JPanel(){
                                    public void paintComponent(Graphics g)
                                    {
                                        if(image == null)
                                        {
                                            image = createImage(getSize().width,getSize().height);
                                            grpc = (Graphics2D)image.getGraphics();
                                            grpc.setColor(Color.WHITE);
                                            grpc.fillRect(0,0,getSize().width,getSize().height);
                                        }else
                                        {
                                            g.drawImage(image,0,0,null);
                                        }
                                    }
                                };
    
    Point newPoint = new Point();
    Point oldPoint = new Point();
    Color pen = new Color(230,230,250);
    Graphics2D grpc;
    Image image;
    JSlider penAdjust = new JSlider (JSlider.VERTICAL,0,40,8);
    int penSize =5;
    
    public Draw(){
        super("Painter");
        JPanel toolbar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        toolbar.add(new Label("Drag your mouse to start drawing"));
        toolbar.add(penAdjust);
        penAdjust.setMinorTickSpacing(1);
        penAdjust.setMajorTickSpacing(5);
        penAdjust.setPaintTicks(true);
        penAdjust.setPaintLabels(true);
        penAdjust.addChangeListener(this);
        this.add(canvas,BorderLayout.CENTER);
        this.add(toolbar,BorderLayout.NORTH);
        canvas.addMouseMotionListener(this);
        canvas.addMouseListener(this);
        
        setSize(2000,1200);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    public void mouseMoved (MouseEvent e){
        oldPoint = e.getPoint();
    }
    
    public void mouseDragged(MouseEvent e){
        grpc.setStroke(new BasicStroke(penSize,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));
        grpc.setColor(pen);
        newPoint = e.getPoint();
        grpc.drawLine(oldPoint.x,oldPoint.y,newPoint.x,newPoint.y);
        repaint();
        oldPoint = newPoint;
    }
    
    public void mouseClicked (MouseEvent e){
        if (e.getModifiers() == MouseEvent.BUTTON3_MASK)
        {
            pen = JColorChooser.showDialog(this, "Choose your pen colour", pen);
        }
    }
    
    public void mousePressed (MouseEvent e) {}
    public void mouseReleased (MouseEvent e) {}
    public void mouseEntered (MouseEvent e) {}
    public void mouseExited (MouseEvent e) {}
   
    public void stateChanged (ChangeEvent pen){
        JSlider temp = (JSlider)pen.getSource();
        if (!temp.getValueIsAdjusting())
        {
            penSize = (int)temp.getValue();
        }
    }
    
    public static void main(String[] a){
        new Draw();
    }
}