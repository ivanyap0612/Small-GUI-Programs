import javax.swing.*;
import javax.swing.event.*;
import javax.sound.midi.*;
import java.awt.*;
import java.awt.event.*;


public class MPiano implements KeyListener, ChangeListener{
boolean startPlaying = false;   
int keyPrs;
Synthesizer synthesizer;
MidiChannel[] channel;
    
    MPiano(){
    //create JFrame object
    JFrame f;
    f = new JFrame("Mini Piano");
    //create JLabel object
    JLabel label;
    label = new JLabel("C   D    E    F   G    A   B    C   D    E    F   G    A   B    C");
    JButton[] black;
    black = new JButton[13];
    JButton[] white;
    white = new  JButton[15];
    //create JLayeredPane object
    JLayeredPane panel;
    panel = new JLayeredPane();
    f.add(panel);
    label.setFont(new Font("Verdana", Font.PLAIN, 20));
    label.setForeground(Color.red);
    Dimension size = label.getPreferredSize();
    label.setBounds(15, 105, size.width, size.height);
    panel.add(label);

        for (int j = 0; j < 15; j++) {
            //create white key of piano
            white[j] = new JButton();
            white[j].setSize(39, 151); 
            white[j].setLocation(j * 39, 0);
            white[j].setBackground(Color.WHITE);
            panel.add(white[j], 0, -1);
            white[j].setName("wk" + Integer.toString(j));
            white[j].addKeyListener(this);
            white[j].addChangeListener(this);
        }

        try{ 
            synthesizer=MidiSystem.getSynthesizer();
            synthesizer.open();
            channel = synthesizer.getChannels();
        }
        catch(MidiUnavailableException ex){ 
            JOptionPane.showMessageDialog(null,"Not able to open the MIDI.");
        }

        for (int j = 0; j < 13; j++) {
            
        if (j==2 || j ==6 || j ==9)
        continue;
        //create black key of piano
        black[j] = new JButton();
        black[j].setSize(29, 89);
        black[j].setBackground(Color.BLACK);
        black[j].setLocation(24 + j * 39, 0);
        panel.add(black[j], 1, -1);
        black[j].setName("bk" + Integer.toString(j));
        black[j].addKeyListener(this);
        black[j].addChangeListener(this);      
        }
    
    f.setSize(600, 190);
    f.setVisible(true);
    f.setResizable(false);
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   
    }


    public void stateChanged(ChangeEvent ev){
     JButton tmp;
     tmp = (JButton)ev.getSource();
     String buttonName;
     buttonName = tmp.getName();
        if(tmp.getModel().isPressed()){
         startPlaying = true;
         System.out.println(buttonName + " is pressed.");
         switch(buttonName){
             case "wk0":
             keyPrs = 72;
             channel[0].noteOn(keyPrs,68);
             break;
             
             case "bk0":
             keyPrs = 73;
             channel[0].noteOn(keyPrs,68);
             break;
             
             case "wk1":
             keyPrs = 74;
             channel[0].noteOn(keyPrs,68);
             break;
             
             case "bk1":
             keyPrs = 75;
             channel[0].noteOn(keyPrs,68);
             break;
             
             case "wk2":
             keyPrs = 76;
             channel[0].noteOn(keyPrs,68);
             break;
             
             case "wk3":
             keyPrs = 77;
             channel[0].noteOn(keyPrs,68);
             break;
             
             case "bk3":
             keyPrs = 78;
             channel[0].noteOn(keyPrs,68);
             break;
             
             case "wk4":
             keyPrs = 79;
             channel[0].noteOn(keyPrs,68);
             break;
             
             case "bk4":
             keyPrs = 80;
             channel[0].noteOn(keyPrs,68);
             break;
             
             case "wk5":
             keyPrs = 81;
             channel[0].noteOn(keyPrs,68);
             break;
             
             case "bk5":
             keyPrs = 82;
             channel[0].noteOn(keyPrs,68);
             break;
             
             case "wk6":
             keyPrs = 83;
             channel[0].noteOn(keyPrs,68);
             break;
             
             case "wk7":
             keyPrs = 84;
             channel[0].noteOn(keyPrs,68);
             break;
             
             case "bk7":
             keyPrs = 85;
             channel[0].noteOn(keyPrs,68);
             break;
             
             case "wk8":
             keyPrs = 86;
             channel[0].noteOn(keyPrs,68);
             break;
             
             case "bk8":
             keyPrs = 87;
             channel[0].noteOn(keyPrs,68);
             break;
             
             case "wk9":
             keyPrs = 88;
             channel[0].noteOn(keyPrs,68);
             break;
             
             case "wk10":
             keyPrs = 89;
             channel[0].noteOn(keyPrs,68);
             break;
             
             case "bk10":
             keyPrs = 90;
             channel[0].noteOn(keyPrs,68);
             break;
             
             case "wk11":
             keyPrs = 91;
             channel[0].noteOn(keyPrs,68);
             break;
             
             case "bk11":
             keyPrs = 92;
             channel[0].noteOn(keyPrs,68);
             break;
             
             case "wk12":
             keyPrs = 93;
             channel[0].noteOn(keyPrs,68);
             break;
             
             case "bk12":
             keyPrs = 94;
             channel[0].noteOn(keyPrs,68);
             break;
             
             case "wk13":
             keyPrs = 95;
             channel[0].noteOn(keyPrs,68);
             break;
             
             case "wk14":
             keyPrs = 96;
             channel[0].noteOn(keyPrs,68);
             break;
             
             default:
             break;
            }
        }

        else{
            if(startPlaying)
            channel[0].noteOff(keyPrs);
            startPlaying = false;
        }
    }
    
    public void keyPressed(KeyEvent ev){    
    char a;
    a = ev.getKeyChar();
    System.out.println(a + " is pressed.");
       if(!startPlaying){
        startPlaying =true;
        switch(a){
            case 'q':
             keyPrs = 72;
             channel[0].noteOn(keyPrs,68);
             break;
             
             case '2':
             keyPrs = 73;
             channel[0].noteOn(keyPrs,68);
             break;
             
             case 'w':
             keyPrs = 74;
             channel[0].noteOn(keyPrs,68);
             break;
             
             case '3':
             keyPrs = 75;
             channel[0].noteOn(keyPrs,68);
             break;
             
             case 'e':
             keyPrs = 76;
             channel[0].noteOn(keyPrs,68);
             break;
             
             case 'r':
             keyPrs = 77;
             channel[0].noteOn(keyPrs,68);
             break;
             
             case '5':
             keyPrs = 78;
             channel[0].noteOn(keyPrs,68);
             break;
             
             case 't':
             keyPrs = 79;
             channel[0].noteOn(keyPrs,68);
             break;
             
             case '6':
             keyPrs = 80;
             channel[0].noteOn(keyPrs,68);
             break;
             
             case 'y':
             keyPrs = 81;
             channel[0].noteOn(keyPrs,68);
             break;
             
             case '7':
             keyPrs = 82;
             channel[0].noteOn(keyPrs,68);
             break;
             
             case 'u':
             keyPrs = 83;
             channel[0].noteOn(keyPrs,68);
             break;
             
             case 'z':
             keyPrs = 84;
             channel[0].noteOn(keyPrs,68);
             break;
             
             case 's':
             keyPrs = 85;
             channel[0].noteOn(keyPrs,68);
             break;
             
             case 'x':
             keyPrs = 86;
             channel[0].noteOn(keyPrs,68);
             break;
             
             case 'd':
             keyPrs = 87;
             channel[0].noteOn(keyPrs,68);
             break;
             
             case 'c':
             keyPrs = 88;
             channel[0].noteOn(keyPrs,68);
             break;
             
             case 'v':
             keyPrs = 89;
             channel[0].noteOn(keyPrs,68);
             break;
             
             case 'g':
             keyPrs = 90;
             channel[0].noteOn(keyPrs,68);
             break;
             
             case 'b':
             keyPrs = 91;
             channel[0].noteOn(keyPrs,68);
             break;
             
             case 'h':
             keyPrs = 92;
             channel[0].noteOn(keyPrs,68);
             break;
             
             case 'n':
             keyPrs = 93;
             channel[0].noteOn(keyPrs,68);
             break;
             
             case 'j':
             keyPrs = 94;
             channel[0].noteOn(keyPrs,68);
             break;
             
             case 'm':
             keyPrs = 95;
             channel[0].noteOn(keyPrs,68);
             break;
             
             case ',':
             keyPrs = 96;
             channel[0].noteOn(keyPrs,68);
             break;
             
             default:
             break;
        }
       }
    }
    public void keyReleased(KeyEvent ev){
        channel[0].noteOff(keyPrs);
        startPlaying = false;
    }
    public void keyTyped(KeyEvent ev){}
    public static void main(String[] args) {
    new MPiano();
    }
}