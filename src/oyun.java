
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.JPanel;
import javax.swing.Timer;

class Ground{
    
    private int X;
    private BufferedImage imgtmp;
    
    public Ground(int X,BufferedImage imgtmp){
        this.X=X;
        this.imgtmp=imgtmp;
    }

    public int getX() {
        return X;
    }

    public void setX(int X) {
        this.X = X;
    }

    public BufferedImage getImgtmp() {
        return imgtmp;
    }

    public void setImgtmp(BufferedImage imgtmp) {
        this.imgtmp = imgtmp;
    }

}

class Tubes {
    
    private int height;
    private int revHeight;
    private int X;
    private BufferedImage imgtmp;
    private BufferedImage imgtmpReverse;

    public Tubes(int height, int X, BufferedImage imgtmp,BufferedImage imgtmpReverse) {
        this.height = height;
        this.revHeight = 400-height;
        this.X = X;
        this.imgtmp = imgtmp;
        this.imgtmpReverse=imgtmpReverse;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getRevHeight() {
        return revHeight;
    }

    public void setRevHeight(int revHeight) {
        this.revHeight = revHeight;
    }

    public int getX() {
        return X;
    }

    public void setX(int X) {
        this.X = X;
    }

    public BufferedImage getImgtmp() {
        return imgtmp;
    }

    public void setImgtmp(BufferedImage imgtmp) {
        this.imgtmp = imgtmp;
    }

    public BufferedImage getImgtmpReverse() {
        return imgtmpReverse;
    }

    public void setImgtmpReverse(BufferedImage imgtmpReverse) {
        this.imgtmpReverse = imgtmpReverse;
    }
    
}

public class oyun extends JPanel implements ActionListener,KeyListener{
    //Timers
    Timer timer=new Timer(5, this);
    
    //Images
    private BufferedImage imgBird;
    private BufferedImage imgBackgroundDayLight;
    private BufferedImage imgBackgroundNight;
    private BufferedImage imgGround;
    private BufferedImage imgTube;
    private BufferedImage imgTubeReverse;
    private BufferedImage imgZero;
    private BufferedImage imgOne;
    private BufferedImage imgTwo;
    private BufferedImage imgThree;
    private BufferedImage imgFour;
    private BufferedImage imgFive;
    private BufferedImage imgSix;
    private BufferedImage imgSeven;
    private BufferedImage imgEight;
    private BufferedImage imgNine;
    private BufferedImage imgGameOver;
    private BufferedImage imgGetReady;
    private BufferedImage imgLetterB;
    private BufferedImage imgLetterE;
    private BufferedImage imgLetterS;
    private BufferedImage imgLetterT;
    private BufferedImage imgLetterP;
    private BufferedImage imgLetterA;
    private BufferedImage imgLetterC;
    private BufferedImage imgLetterR;
    private BufferedImage imgStartButton;
    
    //ArrayLists
    private ArrayList<Ground> grounds=new ArrayList<Ground>();
    private ArrayList<Tubes> tubes=new ArrayList<Tubes>();
    private ArrayList<Integer> digits=new ArrayList<Integer>();
    private ArrayList<Integer> digitsBest=new ArrayList<Integer>();
    
    //Tools
    private int dirGround=-2;
    private boolean hasTube=false;
    private Random rand=new Random();
    private int birdY=240;
    private int birdDir=5;
    private boolean startGame=false;
    private boolean stopGame=false;
    private boolean pressSpace=false;
    private int time=0;
    private int counter=0;
    private boolean lightOrNight=false;
    private boolean getStart=false;
    
    public oyun(){
        lightOrNight=rand.nextBoolean();
        try { 
            imgBird=ImageIO.read(new FileImageInputStream(new File("gameImages/bird.png")));
            imgBackgroundDayLight=ImageIO.read(new FileImageInputStream(new File("gameImages/daylightBack.png")));
            imgBackgroundNight=ImageIO.read(new FileImageInputStream(new File("gameImages/nightBack.png")));
            imgGround=ImageIO.read(new FileImageInputStream(new File("gameImages/ground.png")));
            grounds.add(new Ground(0, imgGround));
            imgTube=ImageIO.read(new FileImageInputStream(new File("gameImages/tube.png")));
            imgTubeReverse=ImageIO.read(new FileImageInputStream(new File("gameImages/tubeReverse.png")));
            tubes.add(new Tubes(getRandomHeight(), 800, imgTube, imgTubeReverse));
            imgZero=ImageIO.read(new FileImageInputStream(new File("gameImages/zero.png")));
            imgOne=ImageIO.read(new FileImageInputStream(new File("gameImages/one.png")));
            imgTwo=ImageIO.read(new FileImageInputStream(new File("gameImages/two.png")));
            imgThree=ImageIO.read(new FileImageInputStream(new File("gameImages/three.png")));
            imgFour=ImageIO.read(new FileImageInputStream(new File("gameImages/four.png")));
            imgFive=ImageIO.read(new FileImageInputStream(new File("gameImages/five.png")));
            imgSix=ImageIO.read(new FileImageInputStream(new File("gameImages/six.png")));
            imgSeven=ImageIO.read(new FileImageInputStream(new File("gameImages/seven.png")));
            imgEight=ImageIO.read(new FileImageInputStream(new File("gameImages/eight.png")));
            imgNine=ImageIO.read(new FileImageInputStream(new File("gameImages/nine.png")));
            imgGameOver=ImageIO.read(new FileImageInputStream(new File("gameImages/gameover.png")));
            imgGetReady=ImageIO.read(new FileImageInputStream(new File("gameImages/getready.png")));
            imgLetterB=ImageIO.read(new FileImageInputStream(new File("gameImages/letterB.png")));
            imgLetterE=ImageIO.read(new FileImageInputStream(new File("gameImages/letterE.png")));
            imgLetterS=ImageIO.read(new FileImageInputStream(new File("gameImages/letterS.png")));
            imgLetterT=ImageIO.read(new FileImageInputStream(new File("gameImages/letterT.png")));
            imgLetterP=ImageIO.read(new FileImageInputStream(new File("gameImages/letterP.png")));
            imgLetterA=ImageIO.read(new FileImageInputStream(new File("gameImages/letterA.png")));
            imgLetterC=ImageIO.read(new FileImageInputStream(new File("gameImages/letterC.png")));
            imgLetterR=ImageIO.read(new FileImageInputStream(new File("gameImages/letterR.png")));
            imgStartButton=ImageIO.read(new FileImageInputStream(new File("gameImages/startButton.png")));
            
        } catch (IOException ex) {
            Logger.getLogger(oyun.class.getName()).log(Level.SEVERE, null, ex);
        }
    timer.start();
 
}
    
    public boolean kontrolEt(){
        
        if(birdY>=505 && !stopGame){
            return true;
        }
        return false;
    }
    
    public boolean kontrolEt2(){
        
        for(Tubes t : tubes){
            int tmpY=765-imgGround.getHeight()-t.getHeight();
            if(new Rectangle(200,birdY,imgBird.getWidth()/7,imgBird.getHeight()/7).intersects(new Rectangle(t.getX()+12,tmpY+3,imgTube.getWidth()-40,t.getHeight()))){
                return true;
                
            }else if(new Rectangle(200,birdY,imgBird.getWidth()/7,imgBird.getHeight()/7).intersects(new Rectangle(t.getX()+12,-6,imgTube.getWidth()-40,t.getRevHeight()))){
                return true;
            } 
        } 
        return false;
    }
    
    public boolean kontrolEt3(){
        
        for(Tubes t : tubes){
            if(t.getX()==200){
                counter++;
                System.out.println(counter);
                return true;
            }
        }
        return false;
    }

    public int getRandomHeight(){
        return rand.nextInt(250)+50;
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if(!lightOrNight){
            g.drawImage(imgBackgroundDayLight, 0, 0, this);
        }else{
            g.drawImage(imgBackgroundNight, 0, 0, this);
        }
        
        for(Ground gr : grounds){
            g.drawImage(imgGround, gr.getX(), 550, this);
        }

     /*   g.drawImage(imgTube,  100,765-imgGround.getHeight()-imgTube.getHeight()/3, imgTube.getWidth(), imgTube.getHeight()/3, this);
        g.drawImage(imgTubeReverse, 0, 0, imgTube.getWidth(), imgTube.getHeight()/3, this); */
     if(startGame){
        for(Tubes t : tubes){
            g.drawImage(imgTube, t.getX(), 765-imgGround.getHeight()-t.getHeight(),imgTube.getWidth(),t.getHeight(), this);
            g.drawImage(imgTubeReverse, t.getX(), 0,imgTube.getWidth(),t.getRevHeight(), this);
        } 
     }
        if(digits.size()==0 && startGame){
            g.drawImage(imgZero, 220, 100,imgZero.getWidth()/2,imgZero.getHeight()/2, this);
        }
        if(kontrolEt3()){
            digits.clear();
            int tmp=counter;
            tmp/=2;
         while(tmp!=0){
             
         digits.add(tmp%10);
         tmp/=10;
         }
        }
        int X=220;
        for (int i = digits.size()-1; i >=0; i--) {
            int tmp2=digits.get(i);
            switch(tmp2){
                case 0:
                    g.drawImage(imgZero, X, 100,imgZero.getWidth()/2,imgZero.getHeight()/2, this);
                    break;
                case 1:
                    g.drawImage(imgOne, X, 100,imgOne.getWidth()/2,imgOne.getHeight()/2, this);
                    break;
                case 2:
                    g.drawImage(imgTwo, X, 100,imgTwo.getWidth()/2,imgTwo.getHeight()/2, this);
                    break;
                case 3:
                    g.drawImage(imgThree, X, 100,imgThree.getWidth()/2,imgThree.getHeight()/2, this);
                    break;
                case 4:
                    g.drawImage(imgFour, X, 100,imgFour.getWidth()/2,imgFour.getHeight()/2, this);
                    break;
                case 5:
                    g.drawImage(imgFive, X, 100,imgFive.getWidth()/2,imgFive.getHeight()/2, this);
                    break;
                case 6:
                    g.drawImage(imgSix, X, 100,imgSix.getWidth()/2,imgSix.getHeight()/2, this);
                    break;
                case 7:
                    g.drawImage(imgSeven, X, 100,imgSeven.getWidth()/2,imgSeven.getHeight()/2, this);
                    break;
                case 8:
                    g.drawImage(imgEight, X, 100,imgEight.getWidth()/2,imgEight.getHeight()/2, this);
                    break;
                case 9:
                    g.drawImage(imgNine, X, 100,imgNine.getWidth()/2,imgNine.getHeight()/2, this);
                    break;    
            }
            X+=40;
        }
        
        //g.drawImage(imgZero, 220, 100,imgZero.getWidth()/2,imgZero.getHeight()/2, this);
        g.drawImage(imgBird, 200, birdY, imgBird.getWidth()/7,imgBird.getHeight()/7,this);
        if(!startGame && !stopGame){
            //word Of PRESS
            g.drawImage(imgGetReady, 140, 160,imgGetReady.getWidth()/2,imgGetReady.getHeight()/2, this);
            g.drawImage(imgLetterP, 80, 320, imgLetterP.getWidth(), imgLetterP.getWidth(), this);
            g.drawImage(imgLetterR, 110, 320, imgLetterR.getWidth(), imgLetterR.getWidth(), this);
            g.drawImage(imgLetterE, 140, 320, imgLetterE.getWidth(), imgLetterE.getWidth(), this);
            g.drawImage(imgLetterS, 170, 320, imgLetterS.getWidth(), imgLetterS.getWidth(), this);
            g.drawImage(imgLetterS, 200, 320, imgLetterS.getWidth(), imgLetterS.getWidth(), this);
            //word of SPACE
            g.drawImage(imgLetterS, 260, 320, imgLetterS.getWidth(), imgLetterS.getWidth(), this);
            g.drawImage(imgLetterP, 290, 320, imgLetterP.getWidth(), imgLetterP.getWidth(), this);
            g.drawImage(imgLetterA, 320, 320, imgLetterA.getWidth(), imgLetterA.getWidth(), this);
            g.drawImage(imgLetterC, 350, 320, imgLetterC.getWidth(), imgLetterC.getWidth(), this);
            g.drawImage(imgLetterE, 380, 320, imgLetterE.getWidth(), imgLetterE.getWidth(), this);
        }
        File file=new File("scores.bin");
        
        if(stopGame && file.exists()){
            g.drawImage(imgLetterB, 200, 300,imgLetterB.getWidth()/2,imgLetterB.getHeight()/2, this);
            g.drawImage(imgLetterE, 220, 300,imgLetterB.getWidth()/2,imgLetterB.getHeight()/2, this);
            g.drawImage(imgLetterS, 240, 300,imgLetterB.getWidth()/2,imgLetterB.getHeight()/2, this);
            g.drawImage(imgLetterT, 260, 300,imgLetterB.getWidth()/2,imgLetterB.getHeight()/2, this);
            g.drawImage(imgGameOver, 100, 200,imgGameOver.getWidth()/3,imgGameOver.getHeight()/3, this); 
            g.setColor(Color.BLACK);
            int score=getScore(file);
            
            digitsBest.clear();
            int tmp=score;
            
             while(tmp!=0){
            digitsBest.add(tmp%10);
            tmp/=10;
            }
            if(digitsBest.size()==0){
            g.drawImage(imgZero, 280, 300,imgZero.getWidth()/4,imgZero.getHeight()/4, this);
        }
             X=280;
            for (int i = digitsBest.size()-1; i >=0; i--) {
                int tmp2=digitsBest.get(i);
                switch(tmp2){
                case 0:
                    g.drawImage(imgZero, X, 300,imgZero.getWidth()/4,imgZero.getHeight()/4, this);
                    break;
                case 1:
                    g.drawImage(imgOne, X, 300,imgOne.getWidth()/4,imgOne.getHeight()/4, this);
                    break;
                case 2:
                    g.drawImage(imgTwo, X, 300,imgTwo.getWidth()/4,imgTwo.getHeight()/4, this);
                    break;
                case 3:
                    g.drawImage(imgThree, X, 300,imgThree.getWidth()/4,imgThree.getHeight()/4, this);
                    break;
                case 4:
                    g.drawImage(imgFour, X, 300,imgFour.getWidth()/4,imgFour.getHeight()/4, this);
                    break;
                case 5:
                    g.drawImage(imgFive, X, 300,imgFive.getWidth()/4,imgFive.getHeight()/4, this);
                    break;
                case 6:
                    g.drawImage(imgSix, X, 300,imgSix.getWidth()/4,imgSix.getHeight()/4, this);
                    break;
                case 7:
                    g.drawImage(imgSeven, X, 300,imgSeven.getWidth()/4,imgSeven.getHeight()/4, this);
                    break;
                case 8:
                    g.drawImage(imgEight, X, 300,imgEight.getWidth()/4,imgEight.getHeight()/4, this);
                    break;
                case 9:
                    g.drawImage(imgNine, X, 300,imgNine.getWidth()/4,imgNine.getHeight()/4, this);
                    break;    
                }
                X+=20;
            }
            
        }
        if(getStart){
            g.drawImage(imgStartButton, 175, 400,imgStartButton.getWidth()/3,imgStartButton.getHeight()/3, this);
        }
    }

    @Override
    public void repaint() {
        super.repaint();
    }
    
    public void standBy(){
        if(time<30){
          time++;
        birdY-=1;  
        }else if(time>=30){
            time++;
            birdY+=1;
            if(time>=60){
                time=0;
            }
        }
    }
    public boolean fallingBird(){
        if(birdY!=-40){
            birdY+=7;
            return false;
        }else{
            return true;
        }
    }
    
    public void getGameAgain(){
        lightOrNight=rand.nextBoolean();
        birdY=240;
            birdDir=5;
            counter=0;
            time=0;
            startGame=false;
            stopGame=false;
            standBy();
            tubes.clear();
            digits.clear();
            hasTube=false;
            getStart=false;
            tubes.add(new Tubes(getRandomHeight(), 800, imgTube, imgTubeReverse));
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        repaint();
        if(!stopGame){
           for(Ground g : grounds){
            g.setX(g.getX()+dirGround);
            if(g.getX()<=-81){
                grounds.add(new Ground(0, imgGround));
                grounds.remove(g);
            }
        } 
        }
        
        if(startGame){
            if(!stopGame){
             for(int t=0;t<tubes.size();t++){
            tubes.get(t).setX(tubes.get(t).getX()+dirGround);
            if(tubes.get(t).getX()==300){
                hasTube=true;
            }else if(tubes.get(t).getX()==-80){
                tubes.remove(t);
            }   
            }
            
        }
        if(hasTube){
            tubes.add(new Tubes(getRandomHeight(), 530, imgTube, imgTubeReverse));
            hasTube=false;
        }
            if(pressSpace && !stopGame){
                time++;
                birdY-=birdDir;
                if(time<=10){
                    birdDir=5;
                }else if(time<=15){
                    birdDir=3;
                }else if(time<=20){
                    birdDir=2;
                }else if(time<=25){ 
                    birdDir=1;
                }else if(time<=28){
                    birdDir=0;
                }else if(time==29){
                    pressSpace=false;
                    time=0;
                    birdDir=3;
                }
            }else if(!stopGame){
                time++;
                birdY+=birdDir;
                if(time>=30){
                    birdDir=6;
                }else{
                    birdDir=5;
                }
            }
        }else{
            standBy();
        }
        if(kontrolEt()){
            if(!stopGame){
                fallingBird();
            }
            stopGame=true;
        }
        if(kontrolEt2() && !stopGame){
            if(!stopGame){
                fallingBird();
            }
            stopGame=true;
        }
        if(stopGame){
            if(birdY<700){
                birdY+=8;
            }else{
                File file=new File("scores.bin");
                if(file.exists()){
                    try(Scanner scan=new Scanner(new FileReader(file))){
                        String tmp=scan.nextLine();
                        int score=Integer.parseInt(tmp);
                        if(score<counter/2){
                            saveScore();
                        }
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(oyun.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }else{
                   saveScore(); 
                }
                getStart=true;
            }
        }
        kontrolEt3();
        
        addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e) {
        if(e.getX()>=175 && e.getX()<=310 && e.getY()>=400 && e.getY()<=450 && getStart){
                getGameAgain();
            }
    }
        });
    }
    public void saveScore(){
        try(FileWriter out=new FileWriter("scores.bin")){
                    String tmp=Integer.toString(counter/2);
                    out.write(tmp+"\n");
                    
                } catch (IOException ex) {
                    Logger.getLogger(oyun.class.getName()).log(Level.SEVERE, null, ex);
                }
    }
    
    public int getScore(File file){
        try(Scanner scan=new Scanner(new FileReader(file))){
            
            String tmp=scan.nextLine();
            int tmp2=Integer.parseInt(tmp);
            
            return tmp2;     
        } catch (FileNotFoundException ex) {
            Logger.getLogger(oyun.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    @Override
    public void keyTyped(KeyEvent arg0) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int c=e.getKeyCode();
        
        if(c==KeyEvent.VK_SPACE && !stopGame){
            getStart=false;
            startGame=true;
            stopGame=false;
            pressSpace=true;
            time=0;
        }else if(c==KeyEvent.VK_SPACE && getStart){
            getGameAgain();
        }
        
    }

    @Override
    public void keyReleased(KeyEvent arg0) {

    }

    
    
}
