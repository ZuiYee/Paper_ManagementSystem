package View;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import mongo.MongodbData;



public class AddView extends JFrame{
	
	
	public AddView() {
		init();
	}
	
	private void init() {
		JFrame addFrame = new JFrame("添加成员");
		addFrame.setSize(340, 400);
		addFrame.setDefaultLookAndFeelDecorated(true);
		JPanel panel = new JPanel();
		addFrame.add(panel);
		this.placeComponents(panel);
		addFrame.setVisible(true);
		
	}
	
	private void placeComponents(JPanel panel) {
		panel.setLayout(null);
		JLabel address = new JLabel("所属学院:");
		address.setBounds(10,20,80,25);
		panel.add(address);
		JTextField addresstext = new JTextField();
		addresstext.setBounds(100,20,165,25);
		panel.add(addresstext);
		
		JLabel author = new JLabel("作者名单:");
		author.setBounds(10,50,80,25);
		panel.add(author);
		JTextField authortext = new JTextField();
		authortext.setBounds(100,50,165,25);
		panel.add(authortext);
		
		JLabel title = new JLabel("论文名称:");
		title.setBounds(10,80,80,25);
		panel.add(title);
		JTextField titletext = new JTextField();
		titletext.setBounds(100,80,165,25);
		panel.add(titletext);
		
		JLabel periodical = new JLabel("期刊名称:");
		periodical.setBounds(10,110,80,25);
		panel.add(periodical);
		JTextField periodicaltext = new JTextField();
		periodicaltext.setBounds(100,110,165,25);
		panel.add(periodicaltext);
		
		JLabel year = new JLabel("发表年月:");
		year.setBounds(10,140,80,25);
		panel.add(year);
		JTextField yeartext = new JTextField();
		yeartext.setBounds(100,140,165,25);
		panel.add(yeartext);
		
		JLabel stage = new JLabel("卷:");
		stage.setBounds(10,170,80,25);
		panel.add(stage);
		JTextField stagetext = new JTextField();
		stagetext.setBounds(100,170,165,25);
		panel.add(stagetext);
		
		
		JLabel volume = new JLabel("期:");
		volume.setBounds(10,200,80,25);
		panel.add(volume);
		JTextField volumetext = new JTextField();
		volumetext.setBounds(100,200,165,25);
		panel.add(volumetext);
		
		JLabel leaf = new JLabel("页码:");
		leaf.setBounds(10,230,80,25);
		panel.add(leaf);
		JTextField leaftext = new JTextField();
		leaftext.setBounds(100,230,165,25);
		panel.add(leaftext);
		
		JLabel dol = new JLabel("收录索引:");
		dol.setBounds(10,260,80,25);
		panel.add(dol);
		JTextField doltext = new JTextField();
		doltext.setBounds(100,260,165,25);
		panel.add(doltext);
		
		
		JLabel dol_catalog = new JLabel("收录索引分区:");
		dol_catalog.setBounds(10,290,80,25);
		panel.add(dol_catalog);
		JTextField dol_catalogtext = new JTextField();
		dol_catalogtext.setBounds(100,290,165,25);
		panel.add(dol_catalogtext);
		
		JButton summitButton = new JButton("login");
		summitButton.setBounds(180, 320, 80, 25);
        panel.add(summitButton);
        summitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ("".equals(address.getText()) || address.getText() == null) {
	    			 JOptionPane.showMessageDialog(null, "请输入所属学院", "所属学院输入为空", JOptionPane.ERROR_MESSAGE); 
	    			 return;  
	    	        }
				
				if ("".equals(authortext.getText()) || authortext.getText() == null) {
	    			 JOptionPane.showMessageDialog(null, "请输入作者", "作者输入为空", JOptionPane.ERROR_MESSAGE); 
	    			 return;  
	    	        }
				
				if ("".equals(titletext.getText()) || titletext.getText() == null) {
	    			 JOptionPane.showMessageDialog(null, "请输入论文题目", "论文题目输入为空", JOptionPane.ERROR_MESSAGE); 
	    			 return;  
	    	        }
				
				if ("".equals(periodical.getText()) || periodical.getText() == null) {
	    			 JOptionPane.showMessageDialog(null, "请输入发表期刊", "发表期刊输入为空", JOptionPane.ERROR_MESSAGE); 
	    			 return;  
	    	        }
				
				if ("".equals(year.getText()) || year.getText() == null) {
	    			 JOptionPane.showMessageDialog(null, "请输入发表年月", "发表年月输入为空", JOptionPane.ERROR_MESSAGE); 
	    			 return;  
	    	        }
				if ("".equals(stage.getText()) || stage.getText() == null) {
	    			 JOptionPane.showMessageDialog(null, "请输入卷", "卷输入为空", JOptionPane.ERROR_MESSAGE); 
	    			 return;  
	    	        }
				if ("".equals(volume.getText()) || volume.getText() == null) {
	    			 JOptionPane.showMessageDialog(null, "请输入期", "期输入为空", JOptionPane.ERROR_MESSAGE); 
	    			 return;  
	    	        }
				
				if ("".equals(leaf.getText()) || leaf.getText() == null) {
	    			 JOptionPane.showMessageDialog(null, "请输入页", "页输入为空", JOptionPane.ERROR_MESSAGE); 
	    			 return;  
	    	        }
				
				if ("".equals(dol.getText()) || dol.getText() == null) {
	    			 JOptionPane.showMessageDialog(null, "请输入收录索引", "收录索引输入为空", JOptionPane.ERROR_MESSAGE); 
	    			 return;  
	    	        }
				
				if ("".equals(dol_catalog.getText()) || dol_catalog.getText() == null) {
	    			 JOptionPane.showMessageDialog(null, "请输入收录索引分区", "收录索引分区输入为空", JOptionPane.ERROR_MESSAGE); 
	    			 return;  
	    	        }
				
				MongodbData mg = new MongodbData();
				mg.Insert(address.getText(), authortext.getText(), 
						titletext.getText(), periodical.getText(),
						year.getText(), stage.getText(), volume.getText(),
						leaf.getText(), dol.getText(), dol_catalog.getText());
				Object[] options = { "是", "取消" }; 
				JOptionPane.showOptionDialog(null, "点击以继续", "提示", 
				JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, 
				null, options, options[0]); 
			}
        });
		
	}
}
