import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.util.Arrays;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioSystem;
import java.io.*;

public class Mole extends JFrame implements ActionListener{
    Boolean player = new Boolean(true);
    JButton[] btn = new JButton[9];
    Object[] options = {"New game", "Quit"};
    int[] moles = new int[9];
    
    private Timer timer;
    private int gameTime=10000;
    private int whacked = 0;
    private int missed = 0;
    Boolean gameStarted = false;
    
    public Mole(){
        super("Whack-a-mole");
        JPanel topBoard = new JPanel(new BorderLayout());
        JLabel lbl = new JLabel(" < Click to start new game");
        topBoard.add(lbl,BorderLayout.CENTER);
        JButton startBtn = new JButton("Start");
        startBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                startBtn.setEnabled(false);
                gameStarted=true;
                timer.start();
            }
        });
        topBoard.add(startBtn,BorderLayout.WEST);
        JPanel gameBoard = new JPanel(new GridLayout(3,0));
        Arrays.fill(moles,0);
        for(int i = 0; i < 9 ; i++){
            btn[i] = new JButton();
            btn[i].setFocusable(false);
            btn[i].setBackground(new Color(104,69,5));
            gameBoard.add(btn[i]);
            btn[i].addActionListener(this);
        }
        this.setLayout(new BorderLayout());
        add(topBoard,BorderLayout.NORTH);
        add(gameBoard,BorderLayout.CENTER);
        setSize(500,500);
        setVisible(true);
        setResizable(false);
        timer = new Timer(600,new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(gameTime < 0) {
                    timer.stop();
                    gameOver();
                    startBtn.setEnabled(true);
                }
                else{
                    genMole();
                    gameTime -= 500;
                }
            }
        });
        setDefaultCloseOperation(EXIT_ON_CLOSE);  
    }
    public static void main(String[] args){
        new Mole();
    }
    public void actionPerformed(ActionEvent evt){
        JButton temp = (JButton)evt.getSource();
        if (moles[Arrays.asList(btn).indexOf(temp)]==1){
            clear();
            System.out.println("Whacked!");
            playSound("whack.wav");
            whacked++;
            missed--;
        }
        else{
            if(gameStarted){
            playSound("laugh.wav");}
        }
    }
    
    private void genMole(){
        Random rnd = new Random(System.currentTimeMillis());
        clear();
        int moleLoc = rnd.nextInt(9);
        moles[moleLoc] =1;
        btn[moleLoc].setIcon(loadImage("mole.png"));
        missed++;
    }
    private void clear(){
        for (int i=0;i<9;i++){
            moles[i]=0;
            btn[i].setIcon(null);
        }
    }
    private void gameOver(){
        clear();
        JOptionPane.showMessageDialog(this,"Hit "+whacked + " and missed " + missed + " times!","GameOver",JOptionPane.INFORMATION_MESSAGE);
        whacked = missed =0;
        gameTime=10000;
        gameStarted=false;
    }
    private ImageIcon loadImage(String path){
        Image image = new ImageIcon(this.getClass().getResource(path)).getImage();
        Image scaledImage = image.getScaledInstance(132, 132, java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }
    public void playSound(String soundName){
        try{
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(this.getClass().getResource(soundName));
            Clip clip = AudioSystem.getClip( );
            clip.open(audioInputStream);
            clip.start( );
        }
        catch(Exception ex){
            System.out.println("Error with playing sound.");
            ex.printStackTrace( );
        }
    }

}
        
        