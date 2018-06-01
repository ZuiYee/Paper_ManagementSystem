package View;

import mongo.MongodbData;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;






public class MainView {
	public static void main(String[] args) {
		View v = new View();
		
	}
}

class View extends JFrame {
	
	private int Max_Page_Num;
	public int PageNum = 1;
	DefaultTableModel myTableModel = null;
	//����Ĭ��
	public static String[] column = {"����ѧԺ", "��������", "��������", "�ڿ�����", "��������", "��", "��", "ҳ��", "��¼����", "��¼��������" }; 
	public static JTable Table;
	private JTextField condition;
	private JLabel PageNumJLabel;
	private String param ;
	private String type;
	private MongodbData mg;
	private String[][] result;
	private JLabel currPageNumJLabel;
	
	public View() {
		Init_View();
	}
	
	public void Init_View() {
		type = "year";
		mg = new MongodbData();
		JFrame frame = new JFrame("���Ĳ�ѯϵͳ");
		frame.setDefaultLookAndFeelDecorated(true);
		frame.setSize(600, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//����Ŀ�
		JPanel JPanelNorth = new JPanel();
		//������
		JLabel JLabelText = new JLabel("����:");
		JPanelNorth.add(JLabelText);
		//��������ı�
		condition = new JTextField(20);
		condition.addKeyListener((KeyListener) new FindListener());
		JPanelNorth.add(condition);
		//���ҿ�
		//����ѡ���
		JComboBox combo = new JComboBox();
		combo.addItem("author");
		combo.addItem("title");
		combo.addItem("periodical");
		combo.addItem("year");
		combo.addItem("stage");
		combo.addItem("volume");
		combo.addItem("leaf");
		combo.addItem("dol");
		combo.addItem("dol_catalog");
		type = combo.getSelectedItem().toString();//Ĭ��author
		JPanelNorth.add(combo);
		JButton JButtonFind = new JButton("��ѯ");
		//����������
		JButtonFind.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) {
            	type = combo.getSelectedItem().toString();
                find();
            }  
        });  
		JButtonFind.addKeyListener((KeyListener) new FindListener());
		JPanelNorth.add(JButtonFind);
		//�����Ϣ
		JButton JButtonAdd = new JButton("���");
		JButtonAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {  
                new AddView();  
            }  
        }); 
		JPanelNorth.add(JButtonAdd);
		frame.add(JPanelNorth);
		//���Ŀ�
		JPanel JPanelCenter = new JPanel();
		JPanelCenter.setLayout(new GridLayout(1,1));
		//�������
		
		DefaultTableModel myTableModel = new DefaultTableModel(result, column);
		Table = new JTable(myTableModel);
		DefaultTableCellRenderer cr = new DefaultTableCellRenderer();
		cr.setHorizontalAlignment(JLabel.CENTER);
		Table.setDefaultRenderer(Object.class, cr);
		initJTable(Table, result);
		JScrollPane ScrollPane = new JScrollPane(Table);
		JPanelCenter.add(ScrollPane);
		
		
		frame.add(JPanelCenter);
		//����Ŀ�
		JPanel JPanelSouth = new JPanel();
		JPanelSouth.setLayout(new GridLayout(1,5));
		//��ҳ�鿴��ť  
		//��ҳ
		JButton JButtonFirst = new JButton("��ҳ");
		JButtonFirst.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {  
                PageNum = 1;  
                String[][] result_1 = list(result, PageNum);
                initJTable(Table, result_1); 
                currPageNumJLabel.setText(PageNum + "/" + Max_Page_Num);
            }  
        });  
		
		//��ǰ��ҳ
		JButton JButtonPre = new JButton("ǰһҳ");
		JButtonPre.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {  
                PageNum--;  
                if (PageNum <= 0) {  
                    PageNum = 1;  
                }  
                String[][] result_2 = list(result, PageNum);
                initJTable(Table, result_2); 
                currPageNumJLabel.setText(PageNum + "/" + Max_Page_Num);
            }  
        });  
		//���ҳ
		JButton JButtonNext = new JButton("��һҳ");
		JButtonNext.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {  
                PageNum++;  
                if (PageNum > Max_Page_Num) {  
                    PageNum = Max_Page_Num;  
                }  
                String[][] result_3 = list(result, PageNum);
                initJTable(Table, result_3); 
                currPageNumJLabel.setText(PageNum + "/" + Max_Page_Num); 
            }  
        });  
		//���һҳ
		JButton JButtonLast = new JButton("ĩҳ");
		JButtonLast.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {  
                PageNum = Max_Page_Num;  
                String[][] result_4 = list(result, PageNum);
                initJTable(Table, result_4); 
                currPageNumJLabel.setText(PageNum + "/" + Max_Page_Num);
            }  
        });  
		
		currPageNumJLabel = new JLabel(PageNum + "/" + Max_Page_Num);
		currPageNumJLabel.setHorizontalAlignment(JLabel.CENTER);
		
		JPanelSouth.add(JButtonFirst);
		JPanelSouth.add(JButtonPre);
		JPanelSouth.add(currPageNumJLabel);
		JPanelSouth.add(JButtonNext);
		JPanelSouth.add(JButtonLast);
		frame.add(JPanelSouth);
		frame.setVisible(true);
		
		
		frame.add(JPanelNorth, BorderLayout.NORTH);
		frame.add(JPanelCenter, BorderLayout.CENTER);
		frame.add(JPanelSouth, BorderLayout.SOUTH);
		//���ò���
		/*
		setBounds(800, 400, 810, 400);  
        setResizable(false);  
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);  
        setVisible(true);
        */
		
	}
        public static void initJTable(JTable Table, String[][] result_r) {
        	((DefaultTableModel)Table.getModel()).setDataVector(result_r, column);
        	Table.setRowHeight(20);
        	TableColumn firsetColumn = Table.getColumnModel().getColumn(0);
        	firsetColumn.setPreferredWidth(90);
        	firsetColumn.setMaxWidth(120);
        	firsetColumn.setMinWidth(60);
        	TableColumn secondColumn = Table.getColumnModel().getColumn(1);
        	secondColumn.setPreferredWidth(90);
        	secondColumn.setMaxWidth(120);
        	secondColumn.setMinWidth(60);
        	TableColumn thirdColumn = Table.getColumnModel().getColumn(2);
        	thirdColumn.setPreferredWidth(90);
        	thirdColumn.setMaxWidth(120);
        	thirdColumn.setMinWidth(60);
        	TableColumn fourthColumn = Table.getColumnModel().getColumn(3);
        	fourthColumn.setPreferredWidth(90);
        	fourthColumn.setMaxWidth(120);
        	fourthColumn.setMinWidth(60);
        	TableColumn fifthColumn = Table.getColumnModel().getColumn(4);
        	fifthColumn.setPreferredWidth(90);
        	fifthColumn.setMaxWidth(120);
        	fifthColumn.setMinWidth(60);
        	TableColumn sixthColumn = Table.getColumnModel().getColumn(5);
        	sixthColumn.setPreferredWidth(30);
        	sixthColumn.setMaxWidth(30);
        	sixthColumn.setMinWidth(30);
        	TableColumn seventhColumn = Table.getColumnModel().getColumn(6);  
            seventhColumn.setPreferredWidth(30);  
            seventhColumn.setMaxWidth(30);  
            seventhColumn.setMinWidth(30);
            TableColumn eighthColumn = Table.getColumnModel().getColumn(7);  
            eighthColumn.setPreferredWidth(60);  
            eighthColumn.setMaxWidth(90);  
            eighthColumn.setMinWidth(30);
            TableColumn ninthColumn = Table.getColumnModel().getColumn(8);  
            ninthColumn.setPreferredWidth(90);  
            ninthColumn.setMaxWidth(120);  
            ninthColumn.setMinWidth(60);
            TableColumn tenthColumn = Table.getColumnModel().getColumn(9);  
            tenthColumn.setPreferredWidth(180);  
            tenthColumn.setMaxWidth(210);  
            tenthColumn.setMinWidth(150);
        }
        private class FindListener extends KeyAdapter {  
            public void keyPressed(KeyEvent e) {  
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {  
                    find();  
                }  
            }  
        }  
    	
    	
    	private void find() {  
            PageNum = 1;
    		param = condition.getText();
    		 if ("".equals(param) || param == null) {
    			 JOptionPane.showMessageDialog(null, "�������ѯ����", "����Ϊ��", JOptionPane.ERROR_MESSAGE); 
    			 return;  
    	        }
            result = mg.get_query(type, param); 
    		Max_Page_Num = mg.count()/10;
            condition.setText(param);
            currPageNumJLabel.setText(PageNum + "/" + Max_Page_Num);
            initJTable(View.Table, list(result, PageNum-1));
        }  
    	private String[][] list(String r[][],int Num){
    		String[][] str = new String[2000][2000];
    		int beginNum = Num*10;
    		for(int i=0;i<10;i++)
    		{
    			for(int j=0;j<10;j++)
    			{
    				str[i][j] = r[beginNum][j];
    			}
    			beginNum++;
    		}
			return str;
    	}
	
}
