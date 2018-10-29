package com.qust.zq.images;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MouseAndKeyEvent {  
	  
    private Frame f;  
    private Button but;  
    private TextField tf;  
      
    public MouseAndKeyEvent() {  
        init();  
    }  
  
    private void init(){  
        f=new Frame("me frame");  
        f.setBounds(300, 200, 600, 500);  
        f.setLayout(new FlowLayout());  
          
        tf=new TextField(20);  
        but=new Button("my Botton");  
          
        f.add(tf);  
        f.add(but);  
          
        event();  
          
        f.setVisible(true);  
    }  
  
    private void event(){  
        f.addWindowListener(new WindowAdapter() {  
            @Override  
            public void windowClosing(WindowEvent e) {  
                // TODO Auto-generated method stub  
                System.exit(0);  
            }  
          
        });  
        tf.addKeyListener(new KeyAdapter() {  
            public void keyPressed(KeyEvent e){  
                int code=e.getKeyCode();  
                if(!(code>=KeyEvent.VK_0 && code<=KeyEvent.VK_9)){  
                    System.out.println(code+"....非法的输入");  
                    e.consume();  //不执行加入文本框.  
                }  
                  
            }  
              
        });  
          
          
          
        but.addActionListener(new ActionListener() {  
              
            @Override  
            public void actionPerformed(ActionEvent e) {  
                // TODO Auto-generated method stub  
                System.out.println("actionPerformed 活动一次");  
            }  
        });  
          
        but.addMouseListener(new MouseAdapter() {  
            private int count=0;  
            private int clickCount=1;  
            public void mouseEntered(MouseEvent e){  
                System.out.println("鼠标进入到改组件"+count++);  
            }  
              
            public void mouseClicked(MouseEvent e){  
                if(e.getClickCount()==2){  
                    System.out.println("双击动作");  
                }else  
                    System.out.println("点击动作"+clickCount++);  
                  
            }  
              
        });  
        //添加 键盘事件  
        but.addKeyListener(new KeyAdapter() {  
          
            public void keyPressed(KeyEvent e){  
                System.out.println(e.getKeyChar()+"..."+e.getKeyCode());  
                System.out.println(KeyEvent.getKeyText(e.getKeyCode())+"..."+e.getKeyCode());  
                //enter  就退出  
                /*if(e.getKeyCode()==KeyEvent.VK_ENTER) 
                    System. 
                    exit(0);*/  
                  
                //ctrl + Enter 发送消息  
                if(e.isControlDown() && e.getKeyCode()==KeyEvent.VK_ENTER){  
                    System.out.println("我要发送消息!");  
                }  
                  
            }  
          
        });  
    }  
  
    public static void main(String[] args) {  
        // TODO Auto-generated method stub  
        new MouseAndKeyEvent();  
    }  
  
}  