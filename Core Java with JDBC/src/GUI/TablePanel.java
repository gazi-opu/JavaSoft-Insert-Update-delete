package GUI;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import model.Person;

public class TablePanel extends JPanel {
	private JTable table;
	private PersonTableModel tableModel;
	private JPopupMenu popup;
    private PersonTableListener personTablelistener;
	public TablePanel() {

		setBorder(BorderFactory.createEtchedBorder());
		
		tableModel = new PersonTableModel();
		table = new JTable(tableModel );
		popup = new JPopupMenu();
		
		JMenuItem removeItem = new JMenuItem("Delete Row");
		popup.add(removeItem);
		
		table.addMouseListener(new MouseAdapter() {
	    	@Override
			public void mousePressed(MouseEvent e) {
			
	    		int row = table.rowAtPoint(e.getPoint());
	    		//System.out.println(row);
	    		
	    		table.getSelectionModel().setSelectionInterval(row, row);
	    		
				if(e.getButton() == MouseEvent.BUTTON3);
				popup.show(table, e.getX(), e.getY());
				
			}
			
		});
		removeItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int row = table.getSelectedRow();
				
				 if (personTablelistener != null) {
					personTablelistener.rowDeleted(row);
					tableModel.fireTableRowsDeleted(row, row);
				}
				
				//System.out.println(row);
				
			}
		});
		
		setLayout(new BorderLayout());

		add(new JScrollPane(table), BorderLayout.CENTER);

	}

	public void setData(List<Person> db) {
		tableModel.setData(db);
	}

	public void refresh(){
		tableModel.fireTableDataChanged();
	}

	public void setPersonTableListener(PersonTableListener listener) {
		this.personTablelistener = listener;
		
	}
}
