package org.plum.generator;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class SelectFileAdapter implements SelectionListener {
	
	private Text text;
	private Shell parent;
	
	public SelectFileAdapter(Shell parent,Text text) {
		this.parent = parent;
		this.text = text;
	}

	@Override
	public void widgetSelected(SelectionEvent paramSelectionEvent) {
		DirectoryDialog filedlg=new DirectoryDialog(parent);
		filedlg.setText("选择保存位置");
		filedlg.setFilterPath("SystemDrive");
		filedlg.setMessage("请选择文件的保存位置，逆向工程生成的文件将会保存在此处。");
		String directory = filedlg.open();
		if(null!=directory) {
			text.setText(directory);
		}
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent paramSelectionEvent) {
	}

}
