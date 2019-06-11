package rs.raf.view.frames;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import rs.raf.model.DBFile;
import rs.raf.model.UIAbstractFile;
import rs.raf.model.UIFileField;
import rs.raf.view.panels.FileView;

@SuppressWarnings("serial")
public class AvgView extends JPanel{

	private JComboBox<String> numericFields=new JComboBox<>();
	
	private ArrayList<JRadioButton> allFields=new ArrayList<>();
	
	
	
	public AvgView(String title,final ArrayList<UIFileField> fields) {
		super();

		setLayout(new GridLayout(fields.size()+1,1));
		ArrayList<JPanel> boxes=new ArrayList<JPanel>(); 
		
		for(int i=0;i<fields.size();i++) {
			if(fields.get(i).isNumeric())
			numericFields.addItem(fields.get(i).getFieldName());
		}
		
		boxes.add(new JPanel(new FlowLayout(FlowLayout.LEFT)));
		boxes.get(0).add(numericFields);
		boxes.add(new JPanel(new FlowLayout(FlowLayout.LEFT)));
		boxes.get(1).add(new JLabel("group by"));
		
		 add(boxes.get(0));
	 	 add(boxes.get(1));
		
		for(int i=0;i<fields.size();i++) {
		boxes.add(new JPanel(new FlowLayout(FlowLayout.LEFT)));
		JRadioButton btn=new JRadioButton(fields.get(i).getFieldName());
		btn.setName(fields.get(i).getFieldName());
		allFields.add(btn);
		boxes.get(i+2).add(btn);
		add(boxes.get(i+2));
		}			
		
		
		Box boxC=new Box(BoxLayout.X_AXIS);
		JButton btnOK=new JButton("start");
		btnOK.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				
					String arg1=numericFields.getSelectedItem().toString();
					ArrayList<String>orderBy=new ArrayList<>();
					
					for(int i=0;i<allFields.size();i++) {
						if(allFields.get(i).isSelected())
						orderBy.add(allFields.get(i).getName());
						
					}
					
					FileView fv = ((FileView) MainFrame.getInstance().getRightPanel().getTopPanel().getSelectedComponent());
					UIAbstractFile ui=fv.getFile();
					try {
						((DBFile) ui).avg(arg1, orderBy);
					} catch (SQLException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					
			
				setVisible(false);
			}
			
		});
		add(btnOK);
		boxC.add(btnOK);
		add(boxC);
		
		
	 
	}


}

