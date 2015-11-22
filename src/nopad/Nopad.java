/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nopad;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.print.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.undo.UndoManager;
import javax.swing.undo.UndoableEdit;


/**
 *
 * @author acer
 */
public class Nopad extends JFrame implements Printable{
    //private static int counter;
   static Nopad nopad1;
     static String Title;
    JTextArea ta1;
    UndoManager m;
    JComboBox list;
    int im,is;
 public void set(String t){super.setTitle(t);}
    public void set(){super.setTitle(Title);}
 Nopad(){
     //super.setLayout(new FlowLayout());
     this.setTitle("UNTITLED");
     this.setIconImage(null);
     JPanel panel=new JPanel();  
     panel.setVisible(true);
     this.add(panel);
     JMenuBar menubarr=new JMenuBar();
     setJMenuBar(menubarr);
     menubarr.setBackground(Color.yellow);
     menubarr.setBorderPainted(true);
     //Menus
     JMenu File=new JMenu("<html><body><Font color='red' size='5'><b><i>F</i></b>ile</font></body></html>");
     menubarr.add(File);
     JMenu Edit=new JMenu("<html><body><Font color='cyan' size='5'><b><i>E</i></b>dit</font></body></html>");
     menubarr.add(Edit);
     JMenu Format=new JMenu("<html><body><Font color='green' size='5'><b><i>F</i></b>ormat</font></body></html>");
     menubarr.add(Format);
     JMenu Help=new JMenu("<html><body><Font color='blue' size='5'><b><i>H</i></b>elp</font></body></html>");
     menubarr.add(Help);
   
     //MenuItems
     JMenuItem New=new JMenuItem("<html><body><Font color='red'>New</font></body></html>");
     JMenuItem Open=new JMenuItem("<html><body><Font color='red'>Open</font></body></html>");
     JMenuItem Save=new JMenuItem("<html><body><Font color='red'>Save</font></body></html>");
     JMenuItem Find_Replace=new JMenuItem("<html><body><Font color='red'>Find and Replace</font></body></html>");
     JMenuItem Print=new JMenuItem("<html><body><Font color='red'>Print</font></body></html>");
     
     JMenuItem Exit=new JMenuItem("<html><body><Font color='red'>Exit</font></body></html>");
     JMenuItem Undo=new JMenuItem("<html><body><Font color='cyan'>Undo</font></body></html>");
     JMenuItem Redo=new JMenuItem("<html><body><Font color='cyan'>Redo</font></body></html>");
     JMenuItem Cut=new JMenuItem("<html><body><Font color='cyan'>Cut</font></body></html>");
     JMenuItem Copy=new JMenuItem("<html><body><Font color='cyan'>Copy</font></body></html>");
     JMenuItem Paste=new JMenuItem("<html><body><Font color='cyan'>Paste</font></body></html>");
     JMenuItem Word_Wrap=new JMenuItem("<html><body><Font color='green'>Word Wrap</font></body></html>");
     JMenuItem font=new JMenuItem("<html><body><Font color='green'>Font</font></body></html>");
     JMenuItem Hel=new JMenuItem("<html><body><Font color='blue'>About       </font></body></html>");
    //Adding Menuitems to menus
     File.add(New);
     File.add(Open);
     File.add(Save);
     File.add(Find_Replace);
     File.add(Print);
     File.add(Exit);
     Edit.add(Undo);
     Edit.add(Redo);
     Edit.add(Cut);
     Edit.add(Copy);
     Edit.add(Paste);
     Format.add(Word_Wrap);
     Format.add(font);
     Help.add(Hel);
     //Adding textarea
     JPanel npanel=new JPanel();
     this.add(npanel);
     ta1=new JTextArea(50,50);
     ta1.setEditable(true);
     ta1.setBackground(Color.black);
     ta1.setForeground(Color.white);
     ta1.setCaretColor(Color.white);
     m=new UndoManager();
     ta1.getDocument().addUndoableEditListener(m);
     npanel.add(ta1);
     JScrollPane pane=new JScrollPane(ta1);
     this.setContentPane(pane);
     File.setMnemonic(KeyEvent.VK_F);
     Edit.setMnemonic(KeyEvent.VK_E);
     Format.setMnemonic(KeyEvent.VK_O);
     Help.setMnemonic(KeyEvent.VK_H);
KeyStroke NEW=KeyStroke.getKeyStroke(KeyEvent.VK_N,KeyEvent.CTRL_DOWN_MASK);
New.setAccelerator(NEW);
KeyStroke OPEN=KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK);
Open.setAccelerator(OPEN);
KeyStroke SAVE=KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK);
Save.setAccelerator(SAVE);
KeyStroke PRINT=KeyStroke.getKeyStroke(KeyEvent.VK_P,KeyEvent.CTRL_DOWN_MASK);
Print.setAccelerator(PRINT);
KeyStroke FIND=KeyStroke.getKeyStroke(KeyEvent.VK_F, KeyEvent.CTRL_DOWN_MASK);
Find_Replace.setAccelerator(FIND);
KeyStroke EXIT=KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.CTRL_DOWN_MASK);
Exit.setAccelerator(EXIT);
KeyStroke UNDO=KeyStroke.getKeyStroke(KeyEvent.VK_T, KeyEvent.CTRL_DOWN_MASK);
Undo.setAccelerator(UNDO);
KeyStroke REDO=KeyStroke.getKeyStroke(KeyEvent.VK_Y,KeyEvent.CTRL_DOWN_MASK);
Redo.setAccelerator(REDO);
     //addListeners
     New.addActionListener(new ActionListener(){
       @Override
        public void actionPerformed(ActionEvent e){
         ta1.setText("");
         set("UNTITLED");
         }
      });
      
      Save.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e){
          JFileChooser f=new JFileChooser();
          f.showSaveDialog(f);
          //File fff=f.getSelectedFile();
        Title=(f.getSelectedFile()).getName();
                set();
               String ss= ta1.getText();
               byte [] a=ss.getBytes();
               FileOutputStream op;
            String path=(f.getSelectedFile()).getPath();
            try {
                //String name=(f.getSelectedFile()).getName();
                op = new FileOutputStream(path);
              op.write(a);
              op.flush();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Nopad.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Nopad.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
      });
      Open.addActionListener(new ActionListener(){
          @Override
          public void actionPerformed(ActionEvent e){
            JFileChooser f=new JFileChooser();
            f.showOpenDialog(f);
            File ff=f.getSelectedFile();
              try {  
                  FileReader r=new FileReader(ff);
                  
                  int c; c = r.read();
                  
                  
                  while(c!=-1){ta1.append(Character.toString((char)c));c=r.read();}
              r.close();} catch (FileNotFoundException ex) {
                  Logger.getLogger(Nopad.class.getName()).log(Level.SEVERE, null, ex);
              } catch (IOException ex) {
                  Logger.getLogger(Nopad.class.getName()).log(Level.SEVERE, null, ex);
              }
          }
      
      });
      Exit.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e){
          System.exit(0);
        }
      });
      Print.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e){
          PrinterJob job=PrinterJob.getPrinterJob();
          job.setPrintable(nopad1);
          boolean doPrint=job.printDialog();
          if(doPrint){try {
              job.print();
             
              } catch (PrinterException ex) {
                  Logger.getLogger(Nopad.class.getName()).log(Level.SEVERE, null, ex);
              }
}
        }
      });
      /////////////////////////////////////////////////////////////////////////////////////////
      Find_Replace.addActionListener(new ActionListener(){
      
        @Override
        public void actionPerformed(ActionEvent e){
           JFrame frame=new JFrame("Find and Replace");
       
           Dimension d=new Dimension(300,300);
            frame.setSize(250,250);
           frame.setMaximumSize(d);
           JPanel pane=new JPanel();
           frame.add(pane);
           frame.setVisible(true);
           pane.setVisible(true);
          
           JLabel find=new JLabel("Find");
           pane.add(find);
           frame.setLayout(null);
           pane.setBounds(10, 20, 50, 20);
           JPanel pane2=new JPanel();
           frame.add(pane2);
           JTextField tf1=new JTextField("Type text here");
           pane2.add(tf1);
           tf1.setSize(500,20);
           tf1.setLocation(50, 20);
           pane2.setBounds(80,20,100,30);
           JPanel bPane=new JPanel();
           frame.add(bPane);
            JLabel Replace =new JLabel("Replace");
           JPanel pane3=new JPanel();
           frame.add(pane3);
           JTextField tf3=new JTextField("Replace with");
           pane3.add(Replace);
           pane3.setBounds(10,50,50,20);
           JPanel pane4=new JPanel();
           pane4.add(tf3);
           frame.add(pane4);
           tf3.setSize(501,30);
           tf3.setLocation(50,50);
           pane4.setBounds(-122,50,501,20);
         //  bPane.setVisible(true);
           JButton button=new JButton("Replace");
           
           bPane.add(button);
          
           button.setSize(50,25);
          
           bPane.setBounds(75,120,100,60);
           button.setLocation(77,120);
           Font f1=new Font("Times New Roman",Font.PLAIN,45);
           ta1.setFont(f1);
           frame.repaint();
           button.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
              String ta=ta1.getText();
              String tf11=tf1.getText();
              String tf33=tf3.getText();
            String s=  ta.replaceAll(tf11, tf33);
            ta1.setText(s);
            }
           });
        }
      });
      
      Undo.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e){
            if(m.canUndo())
            m.undo();
               
        }
      });
       Redo.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e){
            if(m.canRedo())
            m.redo();
        }
      });
       
      Cut.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e){
          ta1.cut();
        }
      });
      Copy.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e){
          ta1.copy();
        }
        
      });
      Paste.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e){
          ta1.paste();
        }
      });
      Hel.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e){
           JOptionPane.showMessageDialog(null, "Nopad is the property of Mr.Mohit Sehgal."+"\n"+"In case of any problem please contact me at:sehgal.mohit06@gmail.com");
        }
        
     });
      Word_Wrap.addActionListener(new ActionListener(){
           @Override
           public void actionPerformed(ActionEvent e){
               if(ta1.getLineWrap()==false)
               {ta1.setLineWrap(true);
               Word_Wrap.setBackground(Color.cyan);}
               else
               {ta1.setLineWrap(false);
                        Word_Wrap.setBackground(null);}
           }
        });
      font.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e){
           JFrame frame=new JFrame();
           frame.setVisible(true);
           JPanel p=new JPanel();
           frame.add(p);
           frame.setSize(900,600);
           frame.setLayout(null);
           JLabel BOI=new JLabel("Bold or Italic");
           JPanel k=new JPanel();
           frame.add(k);
           k.add(BOI);
           String [] style={"BOLD","Italic","BOLD"+" "+"Italic","Plain"};
          list=new JComboBox(style);
        p.add(list);
        p.setVisible(true);
        p.setBounds(20,20,500,50);
        
        list.addItemListener(new ItemListener(){
       
               @Override
               public void itemStateChanged(ItemEvent e) {
                   //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
               
                  im= list.getSelectedIndex();//JOptionPane.showMessageDialog(null,im);
                
            
               }
             
        
        });
         JButton b=new JButton("Apply");
         JPanel pp=new JPanel();
         frame.add(pp);
         pp.add(b);
         b.setSize(30,30);
         pp.setBounds(300,500,500,500);
         JTextField tf1=new JTextField("45");
         JPanel p1=new JPanel();
         frame.add(p1);
         p1.add(tf1);
         p1.setVisible(true);
         tf1.setSize(50,50);
         p1.setBounds(0,0,100,66);
         String size=tf1.getText();
         is=Integer.parseInt(size);
         JButton t=new JButton("Cancel");
         JPanel op=new JPanel();
         frame.add(op);
         pp.add(t);
         
         repaint();
              b.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    //JOptionPane.showMessageDialog(null,im);
                  if(style[im].equals("BOLD")){
                    Font f=new Font("Serif",Font.BOLD,is);
                    
                    ta1.setFont(f);//JOptionPane.showMessageDialog(null,"Message");
                  }
                  if(style[im].equals("Italic")){
                    Font f=new Font("Serif",Font.ITALIC,is); ta1.setFont(f);
                  }
                  if(style[im].equals("BOLD Italic")){
                    Font f=new Font("Serif",Font.BOLD,is);
                    Font ff=new Font("Serif",Font.ITALIC,is);    ta1.setFont(f); ta1.setFont(ff);           
                  }
                  if(style[im].equals("Plain")){
                    Font f=new Font("Serif",Font.ITALIC,is); ta1.setFont(f);
                  }
                }
                 
              });
        
        }
      });
 }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        nopad1=new Nopad();
      
       nopad1.setVisible(true);
       nopad1.setSize(300, 300);
       nopad1.setDefaultCloseOperation(EXIT_ON_CLOSE);
      
      
    }

    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   
}
