package brickBraker;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
  JFrame frame=new JFrame("brickBreaker");
  frame.setBounds( 10,10,700,600);
  frame.setResizable(false);
  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

  Gameplay gameplay=new Gameplay();
  frame.add(gameplay);
  
  frame.setVisible(true);
	}

}
