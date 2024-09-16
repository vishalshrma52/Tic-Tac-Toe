import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class TicTacToe
{
   JFrame fr=new JFrame("Game");
   JLabel la=new JLabel(new ImageIcon(getClass().getResource("images/t2.jpg")));
   JPanel [] pa=new JPanel[3];
   JButton [] bt=new JButton[9];
   JLabel msg=new JLabel("Your turn");
   JButton reset=new JButton("Reset");
   ImageIcon icon1=new ImageIcon(getClass().getResource("images/user1.png"));
   ImageIcon icon2=new ImageIcon(getClass().getResource("images/user2.png"));
   int player=1,count=0;
   boolean winnerFound=false;

   public TicTacToe()
   {
       createFrame();
   }
 
   private void createFrame()
   {
       fr.setSize(500,640);
       fr.setLocationRelativeTo(null);
       fr.setResizable(false);
       fr.setDefaultCloseOperation(3);
       fr.add(la);
       addPanels();
       fr.setVisible(true);
   }
   
   private void addPanels()
   {
       la.setLayout(null);
       for(int i=0;i<3;i++)
       {
          pa[i]=new JPanel();
          la.add(pa[i]);
       }
       pa[0].setBounds(50,20,400,40);
       pa[1].setBounds(50,90,400,400);
       pa[2].setBounds(50,520,400,40);

       addButtons();
   }
  
   private void addButtons()
   {
       pa[1].setLayout(new GridLayout(3,3));
       CrossListener listener=new CrossListener();
       for(int i=0;i<9;i++)
       {
           bt[i]=new JButton();
           bt[i].addActionListener(listener);
           pa[1].add(bt[i]);
           bt[i].setBackground(Color.blue);
       }
       addOthers();
   }
   
   private void addOthers()
   {
       pa[0].add(msg);
       msg.setFont(new Font("lucida calligraphy",1,25));
       msg.setForeground(Color.blue);
       pa[2].add(reset);
       pa[2].setOpaque(false);
       reset.setFont(new Font("arial",0,18));
       reset.setBackground(Color.blue);
       reset.setForeground(Color.green);
       reset.addActionListener(new ResetListener());
       reset.setEnabled(false);
   }
 
   class CrossListener implements ActionListener 
   {
      public void actionPerformed(ActionEvent evt)
      {
          JButton bb=(JButton)evt.getSource();
          if(player==1)//when first player click
          {
             bb.setIcon(icon1);
             bb.setEnabled(false);
             msg.setText("Opponent Turn...");
             msg.setForeground(Color.magenta);
             player=2;
          }
          else if(player==2)//when second player click
          {
             bb.setIcon(icon2);
             bb.setEnabled(false);
             msg.setText("Your Turn...");
             msg.setForeground(Color.blue);
             player=1;
          }
          findWinner();
          count++;
          if(count==9 && !winnerFound)
          {
             msg.setText("Game Over...");
             msg.setForeground(Color.red); 
             JOptionPane.showMessageDialog(fr,"oops ! it is a tie");
             reset.setEnabled(true);
             
          }
      }
     
      private void findWinner()
      {
         if(bt[0].getIcon()==icon1 && bt[1].getIcon()==icon1 && bt[2].getIcon()==icon1)
               gameOver(0,1,2,"You");
         else if(bt[0].getIcon()==icon2 && bt[1].getIcon()==icon2 && bt[2].getIcon()==icon2)
                    gameOver(0,1,2,"Opponent");
         if(bt[3].getIcon()==icon1 && bt[4].getIcon()==icon1 && bt[5].getIcon()==icon1)
               gameOver(3,4,5,"You");
         else if(bt[3].getIcon()==icon2 && bt[4].getIcon()==icon2 && bt[5].getIcon()==icon2)
                    gameOver(3,4,5,"Opponent");
         if(bt[6].getIcon()==icon1 && bt[7].getIcon()==icon1 && bt[8].getIcon()==icon1)
               gameOver(6,7,8,"You");
         else if(bt[6].getIcon()==icon2 && bt[7].getIcon()==icon2 && bt[8].getIcon()==icon2)
                    gameOver(6,7,8,"Opponent");
         if(bt[0].getIcon()==icon1 && bt[3].getIcon()==icon1 && bt[6].getIcon()==icon1)
               gameOver(0,3,6,"You");
         else if(bt[0].getIcon()==icon2 && bt[3].getIcon()==icon2 && bt[6].getIcon()==icon2)
                    gameOver(0,3,6,"Opponent");
         if(bt[1].getIcon()==icon1 && bt[4].getIcon()==icon1 && bt[7].getIcon()==icon1)
               gameOver(1,4,7,"You");
         else if(bt[1].getIcon()==icon2 && bt[4].getIcon()==icon2 && bt[7].getIcon()==icon2)
                    gameOver(1,4,7,"Opponent");
         if(bt[2].getIcon()==icon1 && bt[5].getIcon()==icon1 && bt[8].getIcon()==icon1)
               gameOver(2,5,8,"You");
         else if(bt[2].getIcon()==icon2 && bt[5].getIcon()==icon2 && bt[8].getIcon()==icon2)
                    gameOver(2,5,8,"Opponent");
         if(bt[0].getIcon()==icon1 && bt[4].getIcon()==icon1 && bt[8].getIcon()==icon1)
               gameOver(0,4,8,"You");
         else if(bt[0].getIcon()==icon2 && bt[4].getIcon()==icon2 && bt[8].getIcon()==icon2)
                    gameOver(0,4,8,"Opponent");
         if(bt[2].getIcon()==icon1 && bt[4].getIcon()==icon1 && bt[6].getIcon()==icon1)
               gameOver(2,4,6,"You");
         else if(bt[2].getIcon()==icon2 && bt[4].getIcon()==icon2 && bt[6].getIcon()==icon2)
                    gameOver(2,4,6,"Opponent");
         
         
      }
      private void gameOver(int i,int j,int k,String str)
      {  
          reset.setEnabled(true);
          winnerFound=true;
          bt[i].setBackground(Color.green);
          bt[j].setBackground(Color.green);
          bt[k].setBackground(Color.green);
          msg.setText("Game Over...");
          for(int c=0;c<9;c++)
          {
              bt[c].setEnabled(false);
          }
          JOptionPane.showMessageDialog(fr,str+" Won...");
          
      }
   }

   class ResetListener implements ActionListener
   {
      public void actionPerformed(ActionEvent evt)
      {
        for(int c=0;c<9;c++)
        {
           bt[c].setEnabled(true);
           bt[c].setBackground(Color.blue);
           bt[c].setIcon(null);
        }
        player=1;
        msg.setText("Your Turn...");
        msg.setForeground(Color.blue);
        winnerFound=false;
        count=0;
        reset.setEnabled(false);
      }
   }

   public static void main(String[] args)
   {
       new TicTacToe();
   }
}