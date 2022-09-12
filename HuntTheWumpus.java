/**
 * Author: Hannah Soria
 * Date: 5/4/2022
 * File: Hunter.java
 * Section lab C, Lecture A
 * Project 10: Hunt the Wumpus
 * CS231 Spring 2022
 *
 original Template created by Bruce A. Maxwell and Stephanie R Taylor
 */

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;
import java.awt.Graphics2D;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.event.MouseInputAdapter;

public class HuntTheWumpus {

     // These four fields are as in the LandscapeDisplay class (though 
    // they are now all private)
    private JFrame win;
    private LandscapePanel canvas;    
    private Landscape scape; 
    private int scale;
    private Hunter hunter;
    private Wumpus wumpus;

    /** fields related to demo of mouse interaction **/
    // Unless you have a good reason to report the mouse position in
    // HuntTheWumpus, you should remove these fields and all the
    // code that affects them.
    // There here to demonstrate how you would add a message to the bottom
    // of the window. For HuntTheWumpus, you may want to use it to indicate
    // that the Hunter is armed or close to the Wumpus, or dead.
    JLabel fieldX; // Label field 1, displays the X location of the mouse 
    JLabel fieldY; // Label field 2, displays the Y location of the mouse 

    // controls whether the game is playing or exiting
    private enum PlayState { PLAY, STOP }
    private PlayState state;

    /**
     * Creates the main window
     * @param scape the Landscape that will hold the objects we display
     * @param scale the size of each grid element
     **/		 
    public HuntTheWumpus() {
        
        // The game should begin in the play state.
        this.state = PlayState.PLAY; 
        
        // Create the elements of the Landscape and the game.
        this.scale = 64; // determines the size of the grid
        this.scape = new Landscape(scale*10,scale*7);
        
        //creates all the vertices
        Vertex v1 = new Vertex(2,2,false);
        v1.setVisible( true );
        v1.setCost( 0 );
        Vertex v2 = new Vertex( 3, 2 , false);
        v2.setVisible( true );
        v2.setCost( 1 );
        Vertex v3 = new Vertex( 4, 2 , false);
        v3.setVisible( true );
        v3.setCost( 2 );
        Vertex v4 = new Vertex( 5, 2 , false);
        v4.setVisible( true );
        v4.setCost( 3 );
        Vertex v5 = new Vertex( 2, 3 ,false);
        v5.setVisible( true );
        v5.setCost( 0 );
        Vertex v6 = new Vertex( 3, 3 , false);
        v6.setVisible( true );
        v6.setCost( 1 );
        Vertex v7 = new Vertex( 4, 3 , false);
        v7.setVisible( true );
        v7.setCost( 2 );
        Vertex v8 = new Vertex( 5, 3 , false);
        v8.setVisible( true );
        v8.setCost(3);
        Vertex v9 = new Vertex( 2, 4 ,false);
        v9.setVisible( true );
        v9.setCost( 0 );
        Vertex v10 = new Vertex( 3, 4 , false);
        v10.setVisible( true );
        v10.setCost( 1 );
        Vertex v11 = new Vertex( 4, 4 , false);
        v11.setVisible( true );
        v11.setCost( 2 );
        Vertex v12 = new Vertex( 5, 4 , false);
        v12.setVisible( true );
        v12.setCost(3);
        Vertex v13 = new Vertex( 2, 5 ,false);
        v13.setVisible( true );
        v13.setCost( 0 );
        Vertex v14 = new Vertex( 3, 5 , false);
        v14.setVisible( true );
        v14.setCost( 1 );
        Vertex v15 = new Vertex( 4, 5 , false);
        v15.setVisible( true );
        v15.setCost( 2 );
        Vertex v16 = new Vertex( 5, 5 , false);
        v16.setVisible( true );
        v16.setCost(3);
        v4.setCost( 3 );

        //connects all the vertices
        v1.connect( v2, Vertex.Direction.EAST );
        v2.connect( v1, Vertex.Direction.WEST );
        v2.connect( v3, Vertex.Direction.EAST );
        v3.connect( v2, Vertex.Direction.WEST );
        v3.connect( v4, Vertex.Direction.EAST );
        v4.connect( v3, Vertex.Direction.WEST );
        v5.connect( v6, Vertex.Direction.EAST );
        v6.connect( v5, Vertex.Direction.WEST );
        v6.connect( v7, Vertex.Direction.EAST );
        v7.connect( v6, Vertex.Direction.WEST );
        v7.connect( v8, Vertex.Direction.EAST );
        v8.connect( v7, Vertex.Direction.WEST );
        v8.connect( v9, Vertex.Direction.EAST );
        v9.connect( v8, Vertex.Direction.WEST );
        v9.connect( v10, Vertex.Direction.EAST );
        v10.connect( v9, Vertex.Direction.WEST );
        v10.connect( v11, Vertex.Direction.EAST );
        v11.connect( v10, Vertex.Direction.WEST );
        v11.connect( v12, Vertex.Direction.EAST );
        v12.connect( v11, Vertex.Direction.WEST );
        v12.connect( v13, Vertex.Direction.EAST );
        v13.connect( v12, Vertex.Direction.WEST );
        v13.connect( v14, Vertex.Direction.EAST );
        v14.connect( v13, Vertex.Direction.WEST );
        v14.connect( v15, Vertex.Direction.WEST );
        v15.connect( v14, Vertex.Direction.EAST );
        v15.connect( v16, Vertex.Direction.WEST );
        v16.connect( v15, Vertex.Direction.EAST );
        v1.connect( v5, Vertex.Direction.SOUTH );
        v5.connect( v1, Vertex.Direction.NORTH );
        v5.connect( v9, Vertex.Direction.SOUTH );
        v9.connect( v5, Vertex.Direction.NORTH );
        v9.connect( v13, Vertex.Direction.SOUTH );
        v13.connect( v9, Vertex.Direction.NORTH );
        v2.connect( v6, Vertex.Direction.SOUTH );
        v6.connect( v2, Vertex.Direction.NORTH );
        v6.connect( v10, Vertex.Direction.SOUTH );
        v10.connect( v6, Vertex.Direction.NORTH ); 
        v10.connect( v14, Vertex.Direction.SOUTH );
        v14.connect( v10, Vertex.Direction.NORTH );
        v3.connect( v7, Vertex.Direction.SOUTH );
        v7.connect( v3, Vertex.Direction.NORTH );
        v7.connect( v11, Vertex.Direction.SOUTH );
        v11.connect( v7, Vertex.Direction.NORTH );
        v11.connect( v15, Vertex.Direction.SOUTH );
        v15.connect( v11, Vertex.Direction.NORTH );
        v4.connect( v8, Vertex.Direction.SOUTH );
        v8.connect( v4, Vertex.Direction.NORTH );
        v8.connect( v12, Vertex.Direction.SOUTH );
        v12.connect( v8, Vertex.Direction.NORTH ); 
        v12.connect( v16, Vertex.Direction.SOUTH );
        v16.connect( v12, Vertex.Direction.NORTH );

        //add the vertices to the scape
        this.scape.addBackgroundAgent( v1 );
        this.scape.addBackgroundAgent( v2 );
        this.scape.addBackgroundAgent( v3 );
        this.scape.addBackgroundAgent( v4 );  
        this.scape.addBackgroundAgent( v5 );
        this.scape.addBackgroundAgent( v6 );
        this.scape.addBackgroundAgent( v7 );
        this.scape.addBackgroundAgent( v8 );  
        this.scape.addBackgroundAgent( v9 );
        this.scape.addBackgroundAgent( v10 );
        this.scape.addBackgroundAgent( v11 );
        this.scape.addBackgroundAgent( v12 );  
        this.scape.addBackgroundAgent( v13 );
        this.scape.addBackgroundAgent( v14 );
        this.scape.addBackgroundAgent( v15 );
        this.scape.addBackgroundAgent( v16 );  

        ArrayList <Vertex> vertices = new ArrayList<Vertex>();
        vertices.add(v1);
        vertices.add(v2);
        vertices.add(v3);
        vertices.add(v4);
        vertices.add(v5);
        vertices.add(v6);
        vertices.add(v7);
        vertices.add(v8);
        vertices.add(v9);
        vertices.add(v10);
        vertices.add(v11);
        vertices.add(v12);
        vertices.add(v13);
        vertices.add(v14);
        vertices.add(v15);
        vertices.add(v16);
        
        //random for the wumpus
        Random rand = new Random();
        Vertex randomVertex = vertices.get(rand.nextInt(vertices.size()));
        //creates the hunter and wumpus and add to the scape
        Hunter theHunter = new Hunter(v1);
        hunter = theHunter;
        this.scape.setHunter(theHunter);
        Wumpus theWumpus = new Wumpus(randomVertex);
        wumpus = theWumpus;
        this.scape.setWumpus(theWumpus);
        this.scape.addBackgroundAgent(theHunter.getLocation());
        this.scape.addBackgroundAgent(theWumpus.getHome());

        // Make the main window
        this.win = new JFrame("Hunt the Wumpus");
        win.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);

        // make the main drawing canvas (this is the usual
        // LandscapePanel we have been using). and put it in the window
        this.canvas = new LandscapePanel( this.scape.getWidth(),
					                                        this.scape.getHeight() );
        this.win.add( this.canvas, BorderLayout.CENTER );
        this.win.pack();

        // make the labels and a button and put them into the frame
        // below the canvas.
        this.fieldX = new JLabel("X");
        this.fieldY = new JLabel("Y");
        JButton quit = new JButton("Quit");
        //JButton restart = new JButton("Restart");
        JPanel panel = new JPanel( new FlowLayout(FlowLayout.RIGHT));
        panel.add( this.fieldX );
        panel.add( this.fieldY );
        panel.add( quit );
        //panel.add(restart);
        this.win.add( panel, BorderLayout.SOUTH);
        this.win.pack();

        // Add key and button controls.
        // We are binding the key control object to the entire window.
        // That means that if a key is pressed while the window is
        // in focus, then control.keyTyped will be executed.
        // Because we are binding quit (the button) to control, control.actionPerformed will
        // be called if the quit button is pressed. If you make more than one button,
        // then the same function will be called. Use an if-statement in the function
        // to determine which button was pressed (check out Control.actionPerformed and
        // this advice should make sense)
        Control control = new Control();
        this.win.addKeyListener(control);
        this.win.setFocusable(true);
        this.win.requestFocus();
        quit.addActionListener( control );
        //restart.addActionListener(control);

        // for mouse control
        // Make a MouseControl object and then bind it to the canvas
        // (the part that displays the Landscape). When the mouse
        // enters, exits, moves, or clicks in the canvas, the appropriate
        // method will be called.
        MouseControl mc = new MouseControl();
        this.canvas.addMouseListener( mc );
        this.canvas.addMouseMotionListener( mc );

        // The last thing to do is make it all visible.
        this.win.setVisible( true );
    }

    private class LandscapePanel extends JPanel {
		
        /**
         * Creates the drawing canvas
         * @param height the height of the panel in pixels
         * @param width the width of the panel in pixels
         **/
        public LandscapePanel(int height, int width) {
            super();
            this.setPreferredSize( new Dimension( width, height ) );
            this.setBackground(Color.black);
        }

        /**
         * Method overridden from JComponent that is responsible for
         * drawing components on the screen.  The supplied Graphics
         * object is used to draw.
         * 
         * @param g		the Graphics object used for drawing
         */
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            scape.draw( g, scale );
            //draws the title and instructions
            Graphics2D g2d = (Graphics2D) g;
            Font font = new Font("Monospaced", Font.PLAIN, 40);
            g2d.setFont(font);
            g2d.setColor(Color.white);
            g2d.drawString("HUNT THE WUMPUS", 40, 70);
            Font font2 = new Font("Monospaced", Font.ITALIC, 15);
            g2d.setFont(font2);
            g2d.setColor(Color.WHITE);
            g2d.drawString("WELCOME HUNTER", 30, 420 );
            g2d.drawString("Find the wumpus and shoot it!", 30, 440 );
            g2d.drawString("To move use the 'w,a,s,d' keys, ", 30, 460 );
            g2d.drawString("if the room of the hunter lights up... ", 30, 480 );
            g2d.drawString("the wumpus is near!", 30, 500 );
            g2d.drawString("To arm/disarm click spacebar", 30, 520 );
            g2d.drawString( "Once you're armed use the 'w,a,s,d' to shoot.", 30, 540 );
            g2d.drawString("Miss and continue, hit the wumpus and win,", 30, 560 );
            g2d.drawString("or run into the wumpus and you die!!!", 30, 580 );
            g2d.drawString("BEWARE: everytime you begin a new hunt...", 30, 600 );
            g2d.drawString("the wumpus will have moved", 30, 620 );
        }
    } // end class LandscapePanel

    // This is the class where you define functions that are 
    // executed when certain mouse events take place.
    private class MouseControl extends MouseInputAdapter {
        public void mouseMoved(MouseEvent e) {
            fieldX.setText( "" + e.getPoint().x );
            fieldY.setText( "" + e.getPoint().y );
        }

        public void mouseDragged(MouseEvent e) {
            fieldX.setText( "" + e.getPoint().x );
            fieldY.setText( "" + e.getPoint().y );
        }
        
        public void mousePressed(MouseEvent e) {
            System.out.println( "Pressed: " + e.getClickCount() );
        }

        public void mouseReleased(MouseEvent e) {
            System.out.println( "Released: " + e.getClickCount());
        }

        public void mouseEntered(MouseEvent e) {
            System.out.println( "Entered: " + e.getPoint() );
        }

        public void mouseExited(MouseEvent e) {
            System.out.println( "Exited: " + e.getPoint() );
        }

        public void mouseClicked(MouseEvent e) {
    	    System.out.println( "Clicked: " + e.getClickCount() );
        }
    } // end class MouseControl

    private class Control extends KeyAdapter implements ActionListener {

        //return the hunter
        public Hunter getHunter(){
            return hunter;
        }

        //return the wumpus
        public Wumpus getWumpus(){
            return wumpus;
        }
 
        public void keyTyped(KeyEvent e) {

            Vertex cv = hunter.getLocation();
            System.out.println( "Key Pressed: " + e.getKeyChar() );
            if( ("" + e.getKeyChar()).equalsIgnoreCase("q") ) {
                state = PlayState.STOP;
            }

            //key is pressed
            if (("" + e.getKeyChar()).equalsIgnoreCase("w")){
                //hunter is unarmed
                if( getHunter().getArmed()== -1){
                    //hunter moves north
                    getHunter().move(Vertex.Direction.NORTH);
                    getHunter().getLocation().setVisible(true);
                    //hunter goes into wumpus home unarmed
                    if(getHunter().getLocation() == getWumpus().getHome()){
                        System.out.println("the wumpus ate you!");
                        state = PlayState.STOP;
                    }
                } else {
                    //hunter is armed
                    while(cv.getNeighbor(cv.getX(), cv.getY()-1)!= null){
                        cv = cv.getNeighbor(cv.getX(), cv.getY()-1);
                        // if this vertex matches the Wumpus' location, then the wumpus has been killed
                        if (cv == getWumpus().getHome() ){
                            System.out.println("The wumpus has been killed!");
                            wumpus.setVisible(true);
                        //otherwise the hunter misses
                        } else if (cv != getWumpus().getHome() ){
                            System.out.println("miss!!");
                            getHunter().setArmed(-1);
                        }
                    }
                }
            }

            //key is pressed
            if (("" + e.getKeyChar()).equalsIgnoreCase("a")){
                //hunter is unarmed
                if( getHunter().getArmed()== -1){
                    //hunter moves east
                    getHunter().move(Vertex.Direction.EAST);
                    getHunter().getLocation().setVisible(true);
                    //hunter goes into wumpus home unarmed
                    if(getHunter().getLocation() == getWumpus().getHome()){
                        System.out.println("the wumpus ate you!");
                        state = PlayState.STOP;
                    }
                } else {
                //hunter is armed
                    while(cv.getNeighbor(cv.getX()-1, cv.getY())!= null){
                        cv = cv.getNeighbor(cv.getX()-1, cv.getY());
                        // if this vertex matches the Wumpus' location, then the wumpus has been killed
                        if (cv == getWumpus().getHome() ){
                            System.out.println("The wumpus has been killed!");
                            wumpus.setVisible(true);
                        //otherwise the hunter misses
                        } else if (cv != getWumpus().getHome() ){
                            System.out.println("miss!!");
                            getHunter().setArmed(-1);
                        }
                    }
                }
            }

            //key is pressed
            if (("" + e.getKeyChar()).equalsIgnoreCase("d")){
                //hunter is unarmed
                if( getHunter().getArmed()== -1){
                    //hunter moves west
                    getHunter().move(Vertex.Direction.WEST);
                    getHunter().getLocation().setVisible(true);
                    //hunter goes into wumpus home unarmed
                    if(getHunter().getLocation() == getWumpus().getHome()){
                        System.out.println("the wumpus ate you!");
                        state = PlayState.STOP;
                    }
                } else {
                //hunter is armed
                    while(cv.getNeighbor(cv.getX()+1, cv.getY())!= null){
                        cv = cv.getNeighbor(cv.getX()+1, cv.getY());
                        // if this vertex matches the Wumpus' location, then the wumpus has been killed
                        if (cv == getWumpus().getHome() ){
                            System.out.println("The wumpus has been killed!");
                            wumpus.setVisible(true);
                        //otherwise the hunter misses
                        } else if (cv != getWumpus().getHome() ){
                            System.out.println("miss!!");
                            getHunter().setArmed(-1);
                        }
                    }
                }
            }
            
            //key is pressed
            if (("" + e.getKeyChar()).equalsIgnoreCase("s")){
                //hunter is unarmed
                if( getHunter().getArmed()== -1){
                    //hunter moves south
                    getHunter().move(Vertex.Direction.SOUTH);
                    getHunter().getLocation().setVisible(true);
                    //hunter goes into wumpus home unarmed
                    if(getHunter().getLocation() == getWumpus().getHome()){
                        System.out.println("the wumpus ate you!");
                        state = PlayState.STOP;
                    }
                //hunter is armed
                } else {
                    System.out.println("the hunter is armed");
                    while(cv.getNeighbor(cv.getX(), cv.getY()+1)!= null){
                        cv = cv.getNeighbor(cv.getX(), cv.getY()+1);
                        // if this vertex matches the Wumpus' location, then the wumpus has been killed
                        if (cv == getWumpus().getHome() ){
                            System.out.println("The wumpus has been killed!!!");
                            wumpus.setVisible(true);
                        //otherwise the hunter misses
                        } else if (cv != getWumpus().getHome() ){
                            System.out.println("miss!!");
                            getHunter().setArmed(-1);
                        }
                    }    
                }
            }

            //the space button arms and disarms the wumpus
            if( ("" + e.getKeyChar()).equalsIgnoreCase(" ")){
                if(getHunter().getArmed()== -1){
                    getHunter().setArmed(1);
                    System.out.println("the hunter is armed");
                } else{
                    getHunter().setArmed(-1);
                    System.out.println("the hunter is unarmed");
                }
            }

        }

        public void actionPerformed(ActionEvent event) {
            // If the Quit button was pressed
            if( event.getActionCommand().equalsIgnoreCase("Quit") ) {
		        System.out.println("Quit button clicked");
                state = PlayState.STOP;
            }
            if (event.getActionCommand().equalsIgnoreCase("restart")){
                System.out.println("new game");
                repaint();
            }
        }
    } // end class Control

    public void repaint() {
    	this.win.repaint();
    }

    public void dispose() {
	    this.win.dispose();
    }

    public static void main(String[] argv) throws InterruptedException {
        HuntTheWumpus w = new HuntTheWumpus();
        while (w.state == PlayState.PLAY) {
            w.repaint();
            Thread.sleep(33);
        }
        System.out.println("Disposing window");
        w.dispose();
    }
	
} // end class InteractiveLandscapeDisplay
