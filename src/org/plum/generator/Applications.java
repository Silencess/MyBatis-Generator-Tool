package org.plum.generator;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.dnd.DropTargetAdapter;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.FileTransfer;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;
import org.mybatis.generator.exception.InvalidConfigurationException;

public class Applications {
	private static Text driverFile_text;
	private static Text txtRoot;
	private static Text text_4;
	private static Combo combo;
	private static Text txtComexample;
	private static Text txtSrcmainjava;
	private static Text text;
	private static Text text_2;
	private static Text txtSrcmainresoure;
	private static Text text_5;
	private static Text txtSrcmainjava_1;
	private static Text text_7;
	private static Text text_log;
	private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd HH:mm");
	private static List list;
	private static Button btnCheckButton;
	
	static String defaultModelType = null;
	static String targetRuntime = null;
	static String javaTypeResolver = null;
	static String suppressAllComments = null;
	private static Combo combo_2;
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		Display display = Display.getDefault();
		Shell shlMybatisgenerator = new Shell(SWT.CLOSE | SWT.MIN | SWT.TITLE);
		shlMybatisgenerator.setImage(SWTResourceManager.getImage(Applications.class, "/org/plum/icon/icon.jpg"));
		shlMybatisgenerator.setSize(1105, 767);
		shlMybatisgenerator.setText("MybatisGenerator");
		shlMybatisgenerator.setLayout(null);
		
		Label label_6 = new Label(shlMybatisgenerator, SWT.NONE);
		label_6.setImage(SWTResourceManager.getImage(Applications.class, "/org/plum/icon/mybatis-logo.png"));
		label_6.setBounds(93, 10, 369, 116);
		
		Group group_db = new Group(shlMybatisgenerator, SWT.NONE);
		group_db.setText("数据库连接配置");
		group_db.setBounds(591, 10, 488, 150);
		
		Label label = new Label(group_db, SWT.NONE);
		label.setBounds(10, 28, 61, 17);
		label.setText("数据库驱动");
		
		driverFile_text = new Text(group_db, SWT.BORDER);
		driverFile_text.setToolTipText("选择或拖拽文件到此处");
		driverFile_text.setBounds(77, 25, 312, 23);
		
		Button button_1 = new Button(group_db, SWT.NONE);
		button_1.setBounds(395, 23, 80, 27);
		button_1.setText("选择驱动包");
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog filedlg=new FileDialog(shlMybatisgenerator,SWT.OPEN);
				//设置文件对话框的标题
				filedlg.setText("选择驱动包");
				filedlg.setFilterNames (new String [] {"数据库驱动(*.jar)"});
				filedlg.setFilterExtensions (new String [] {"*.jar"});
				//打开文件对话框，返回选中文件的绝对路径
				String filePath = filedlg.open();
				if(filePath!=null) {
					driverFile_text.setText(filePath);
				}
			}
		});
		
		Label label_1 = new Label(group_db, SWT.NONE);
		label_1.setBounds(10, 85, 61, 17);
		label_1.setText("驱   动   类");
		
		combo = new Combo(group_db, SWT.NONE);
		combo.setToolTipText("下拉选择常用驱动类，或者手动输入一个驱动类。");
		combo.setItems(new String[] {"com.mysql.jdbc.Driver", "oracle.jdbc.OracleDriver"});
		combo.setBounds(77, 82, 398, 25);
		combo.select(0);
		
		Label label_2 = new Label(group_db, SWT.NONE);
		label_2.setBounds(10, 56, 61, 17);
		label_2.setText("连 接 地 址");
		
		combo_2 = new Combo(group_db, SWT.NONE);
		combo_2.setItems(new String[] {"jdbc:mysql://192.168.122.1:3306/cms", "jdbc:oracle:thin:@172.16.8.202:1521:test"});
		combo_2.setBounds(77, 54, 398, 25);
		combo_2.select(0);
		combo_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(combo_2.getSelectionIndex()==0) {
					combo.select(0);
				}else {
					combo.select(1);
				}
			}
		});
		
		Label lblNewLabel = new Label(group_db, SWT.NONE);
		lblNewLabel.setBounds(10, 120, 61, 17);
		lblNewLabel.setText("帐   户   名");
		
		txtRoot = new Text(group_db, SWT.BORDER);
		txtRoot.setText("root");
		txtRoot.setBounds(77, 117, 88, 23);
		
		Label lblNewLabel_1 = new Label(group_db, SWT.NONE);
		lblNewLabel_1.setBounds(194, 120, 31, 17);
		lblNewLabel_1.setText("密码");
		
		text_4 = new Text(group_db, SWT.BORDER);
		text_4.setText("1234");
		text_4.setBounds(249, 117, 80, 23);
		
		Button button = new Button(group_db, SWT.NONE);
		button.setBounds(381, 111, 80, 27);
		button.setText("连接测试");
		
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String filePath = driverFile_text.getText();
				String driver = combo.getText();
				String url = combo_2.getText();
				String user = txtRoot.getText();
				String pwd = text_4.getText();
				if(filePath.equals("")) {
					MessageDialog.openError(shlMybatisgenerator,"错误","请选择驱动文件！");
					return;
				}else {
					File file = new File(filePath);
					if(file.isDirectory()||!file.exists()||!filePath.endsWith(".jar")) {
						MessageDialog.openError(shlMybatisgenerator,"错误","请检查驱动文件地址！");
						return;
					}
					ExtClasspathLoader.add2Path(file);
				}
				if(driver.equals("")) {
					MessageDialog.openError(shlMybatisgenerator,"错误","请填写驱动类！");
					return;
				}
				if(url.equals("")) {
					MessageDialog.openError(shlMybatisgenerator,"错误","请填写连接地址！");
					return;
				}
				if(user.equals("")) {
					MessageDialog.openError(shlMybatisgenerator,"错误","请输入数据库帐户名！");
					return;
				}
				if(pwd.equals("")) {
					MessageDialog.openError(shlMybatisgenerator,"错误","请输入数据库密码！");
					return;
				}
				String testResult = DBTest.test(driver, url, user, pwd);
				if(testResult.equals("success")) {
					MessageDialog.openInformation(shlMybatisgenerator,"恭喜","连接成功！");
				}else {
					MessageDialog.openError(shlMybatisgenerator,"错误",testResult);
				}
			}
		});
		
		DropTarget dropTarget = new DropTarget(shlMybatisgenerator, DND.DROP_COPY);
		dropTarget.setTransfer(FileTransfer.getInstance());
		dropTarget.addDropListener(new DropTargetAdapter() {
			@Override
			public void drop(DropTargetEvent event) {
				if (FileTransfer.getInstance().isSupportedType(event.currentDataType)) {
					String[] files = (String[]) event.data;
					if (files != null && files.length > 0) {
						String filePath=files[0];
						if(filePath.endsWith(".jar")) {
							driverFile_text.setText(filePath);
						}else {
							event.detail = DND.DROP_NONE;
						}
					}
				}
			}
		});
		
		Group grpContext = new Group(shlMybatisgenerator, SWT.NONE);
		grpContext.setText("Context属性");
		grpContext.setBounds(10, 145, 534, 97);
		
		Composite group1 = new Composite(grpContext, SWT.NONE);
		group1.setBounds(10, 39, 488, 17);
		
		Label lblNewLabel_2 = new Label(group1, SWT.NONE);
		lblNewLabel_2.setBounds(0, 0, 122, 17);
		lblNewLabel_2.setToolTipText("指定生成对象的样式");
		lblNewLabel_2.setText("defaultModelType ：");
		
		Button btnRadioButton = new Button(group1, SWT.RADIO);
		btnRadioButton.setBounds(140, 0, 97, 17);
		btnRadioButton.setSelection(true);
		btnRadioButton.setToolTipText("类似hierarchical");
		btnRadioButton.setText("conditional");
		
		Button btnRadioButton_1 = new Button(group1, SWT.RADIO);
		btnRadioButton_1.setBounds(266, 0, 97, 17);
		btnRadioButton_1.setToolTipText("所有内容（主键，blob）等全部生成在一个对象中");
		btnRadioButton_1.setText("flat");
		
		Button btnRadioButton_2 = new Button(group1, SWT.RADIO);
		btnRadioButton_2.setBounds(391, 0, 97, 17);
		btnRadioButton_2.setToolTipText("主键生成一个XXKey对象(key class)，Blob等单独生成一个对象，其他简单属性在一个对象中(record class)");
		btnRadioButton_2.setText("hierarchical");
		
		Composite group2 = new Composite(grpContext, SWT.NONE);
		group2.setBounds(10, 70, 388, 17);
		
		Label lblNewLabel_3 = new Label(group2, SWT.NONE);
		lblNewLabel_3.setBounds(0, 0, 122, 17);
		lblNewLabel_3.setText("targetRuntime：");
		
		Button btnMybatis = new Button(group2, SWT.RADIO);
		btnMybatis.setBounds(140, 0, 97, 17);
		btnMybatis.setSelection(true);
		btnMybatis.setToolTipText("默认的值，生成基于MyBatis3.x以上版本的内容，包括XXXBySample");
		btnMybatis.setText("MyBatis3");
		
		Button btnMybatissimple = new Button(group2, SWT.RADIO);
		btnMybatissimple.setBounds(266, 0, 113, 17);
		btnMybatissimple.setToolTipText("类似MyBatis3，只是不生成XXXBySample");
		btnMybatissimple.setText("MyBatis3Simple");
		
		Group grpJavatyperesolver = new Group(shlMybatisgenerator, SWT.NONE);
		grpJavatyperesolver.setText("javaTypeResolver");
		grpJavatyperesolver.setBounds(10, 260, 260, 54);
		
		Label lblForcebigdecimals = new Label(grpJavatyperesolver, SWT.NONE);
		lblForcebigdecimals.setToolTipText("用于处理DB中的类型到Java中的类型，默认使用JavaTypeResolverDefaultImpl");
		lblForcebigdecimals.setBounds(10, 27, 106, 17);
		lblForcebigdecimals.setText("forceBigDecimals");
		
		Button btnTrue = new Button(grpJavatyperesolver, SWT.RADIO);
		btnTrue.setToolTipText("把JDBC DECIMAL和NUMERIC 类型解析为java.math.BigDecimal");
		btnTrue.setBounds(126, 27, 44, 17);
		btnTrue.setText("true");
		
		Button btnFalse = new Button(grpJavatyperesolver, SWT.RADIO);
		btnFalse.setSelection(true);
		btnFalse.setToolTipText("默认值，把JDBC DECIMAL 和 NUMERIC 类型解析为 Integer");
		btnFalse.setBounds(200, 27, 53, 17);
		btnFalse.setText("false");
		
		Group grpCommentgenerator = new Group(shlMybatisgenerator, SWT.NONE);
		grpCommentgenerator.setText("commentGenerator");
		grpCommentgenerator.setBounds(292, 260, 260, 54);
		
		Label label_13 = new Label(grpCommentgenerator, SWT.NONE);
		label_13.setToolTipText("");
		label_13.setText("是否去除注释");
		label_13.setBounds(10, 27, 106, 17);
		
		Button button_11 = new Button(grpCommentgenerator, SWT.RADIO);
		button_11.setSelection(true);
		button_11.setToolTipText("不生成注释");
		button_11.setText("true");
		button_11.setBounds(125, 27, 44, 17);
		
		Button button_12 = new Button(grpCommentgenerator, SWT.RADIO);
		button_12.setToolTipText("生成注释");
		button_12.setText("false");
		button_12.setBounds(189, 27, 53, 17);
		
		Group grpJavamodelgenerator = new Group(shlMybatisgenerator, SWT.NONE);
		grpJavamodelgenerator.setToolTipText("java模型创建器");
		grpJavamodelgenerator.setText("javaModelGenerator");
		grpJavamodelgenerator.setBounds(10, 336, 534, 187);
		
		Label lblTargetpackage = new Label(grpJavamodelgenerator, SWT.NONE);
		lblTargetpackage.setToolTipText("生成的类要放的包，真实的包受enableSubPackages属性控制");
		lblTargetpackage.setBounds(10, 41, 84, 17);
		lblTargetpackage.setText("targetPackage");
		
		txtComexample = new Text(grpJavamodelgenerator, SWT.BORDER);
		txtComexample.setText("com.example");
		txtComexample.setToolTipText("");
		txtComexample.setBounds(95, 38, 137, 23);
		
		Label lblTargetproject = new Label(grpJavamodelgenerator, SWT.NONE);
		lblTargetproject.setToolTipText("目标项目，指定一个存在的目录下，生成的内容会放到指定目录中，如果目录不存在，MBG不会自动建目录");
		lblTargetproject.setBounds(238, 41, 79, 17);
		lblTargetproject.setText("targetProject");
		
		txtSrcmainjava = new Text(grpJavamodelgenerator, SWT.BORDER);
		txtSrcmainjava.setText("D:\\src\\main\\domain");
		txtSrcmainjava.setBounds(323, 38, 133, 23);
		
		Button button_2 = new Button(grpJavamodelgenerator, SWT.NONE);
		button_2.setBounds(463, 36, 61, 27);
		button_2.setText("选择目录");
		button_2.addSelectionListener(new SelectFileAdapter(shlMybatisgenerator, txtSrcmainjava));
		
		Composite composite = new Composite(grpJavamodelgenerator, SWT.NONE);
		composite.setBounds(10, 78, 238, 17);
		
		Label lblConstructorbased = new Label(composite, SWT.NONE);
		lblConstructorbased.setBounds(0, 0, 102, 17);
		lblConstructorbased.setToolTipText("是否自动为每一个生成的类创建一个构造方法，构造方法包含了所有的field；而不是使用setter；");
		lblConstructorbased.setText("constructorBased");
		
		Button btnTrue_1 = new Button(composite, SWT.RADIO);
		btnTrue_1.setBounds(128, 0, 44, 17);
		btnTrue_1.setText("true");
		
		Button btnFalse_1 = new Button(composite, SWT.RADIO);
		btnFalse_1.setBounds(178, 0, 60, 17);
		btnFalse_1.setSelection(true);
		btnFalse_1.setText("false");
		
		Composite composite_1 = new Composite(grpJavamodelgenerator, SWT.NONE);
		composite_1.setBounds(10, 111, 238, 17);
		
		Label lblEnablesubpackages = new Label(composite_1, SWT.NONE);
		lblEnablesubpackages.setBounds(0, 0, 121, 17);
		lblEnablesubpackages.setToolTipText("在targetPackage的基础上，根据数据库的schema再生成一层package，最终生成的类放在这个package下，默认为false");
		lblEnablesubpackages.setText("enableSubPackages");
		
		Button btnRadioButton_3 = new Button(composite_1, SWT.RADIO);
		btnRadioButton_3.setBounds(129, 0, 44, 17);
		btnRadioButton_3.setText("true");
		
		Button btnRadioButton_4 = new Button(composite_1, SWT.RADIO);
		btnRadioButton_4.setBounds(175, 0, 48, 17);
		btnRadioButton_4.setSelection(true);
		btnRadioButton_4.setText("false");
		
		Composite composite_2 = new Composite(grpJavamodelgenerator, SWT.NONE);
		composite_2.setBounds(254, 78, 183, 17);
		
		Label lblNewLabel_4 = new Label(composite_2, SWT.NONE);
		lblNewLabel_4.setBounds(0, 0, 73, 17);
		lblNewLabel_4.setToolTipText("是否创建一个不可变的类，如果为true，那么MBG会创建一个没有setter方法的类，取而代之的是类似constructorBased的类");
		lblNewLabel_4.setText("immutable");
		
		Button btnTrue_2 = new Button(composite_2, SWT.RADIO);
		btnTrue_2.setBounds(79, 0, 44, 17);
		btnTrue_2.setText("true");
		
		Button btnFalse_2 = new Button(composite_2, SWT.RADIO);
		btnFalse_2.setBounds(129, 0, 60, 17);
		btnFalse_2.setSelection(true);
		btnFalse_2.setText("false");
		
		
		Composite composite_3 = new Composite(grpJavamodelgenerator, SWT.NONE);
		composite_3.setBounds(254, 111, 183, 17);
		
		Label lblTrimstrings = new Label(composite_3, SWT.NONE);
		lblTrimstrings.setBounds(0, 0, 73, 17);
		lblTrimstrings.setToolTipText("设置是否在getter方法中，对String类型字段调用trim()方法");
		lblTrimstrings.setText("trimStrings");
		
		Button btnTrue_3 = new Button(composite_3, SWT.RADIO);
		btnTrue_3.setBounds(80, 0, 44, 17);
		btnTrue_3.setSelection(true);
		btnTrue_3.setText("true");
		
		Button btnFalse_3 = new Button(composite_3, SWT.RADIO);
		btnFalse_3.setBounds(130, 0, 60, 17);
		btnFalse_3.setText("false");
		
		btnCheckButton = new Button(grpJavamodelgenerator, SWT.CHECK);
		btnCheckButton.setBounds(10, 148, 105, 17);
		btnCheckButton.setText("是否继承于父类");
		
		Label lblRootclass = new Label(grpJavamodelgenerator, SWT.NONE);
		lblRootclass.setBounds(162, 148, 40, 17);
		lblRootclass.setText("父类");
		
		text = new Text(grpJavamodelgenerator, SWT.BORDER);
		text.setEnabled(false);
		text.setToolTipText("设置一个根对象，如果设置了这个根对象，那么生成的keyClass或者recordClass会继承这个类；在Table的rootClass属性中可以覆盖该选项。 \r\n注意：如果在key class或者record class中有root class相同的属性，MBG就不会重新生成这些属性了，包括：属性名相同，类型相同，有相同的getter/setter方法；\r\n\r\n");
		text.setBounds(208, 145, 229, 23);
		
		btnCheckButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(btnCheckButton.getSelection()) {
					text.setEnabled(true);
				}else {
					text.setText("");
					text.setEnabled(false);
				}
			}
		});
		
		Group grpSqlmapgenerator = new Group(shlMybatisgenerator, SWT.NONE);
		grpSqlmapgenerator.setToolTipText("生成SQL map的XML文件生成器，\r\n注意，在Mybatis3之后，我们可以使用mapper.xml文件+Mapper接口（或者不用mapper接口）， 或者只使用Mapper接口+Annotation，所以，如果javaClientGenerator配置中配置了需要生成XML的话，这个元素就必须配置");
		grpSqlmapgenerator.setText("sqlMapGenerator");
		grpSqlmapgenerator.setBounds(10, 537, 534, 84);
		
		Label label_3 = new Label(grpSqlmapgenerator, SWT.NONE);
		label_3.setToolTipText("生成的类要放的包，真实的包受enableSubPackages属性控制");
		label_3.setText("targetPackage");
		label_3.setBounds(10, 31, 86, 17);
		
		text_2 = new Text(grpSqlmapgenerator, SWT.BORDER);
		text_2.setToolTipText("");
		text_2.setText("com.example");
		text_2.setBounds(96, 28, 137, 23);
		
		Label label_4 = new Label(grpSqlmapgenerator, SWT.NONE);
		label_4.setToolTipText("目标项目，指定一个存在的目录下，生成的内容会放到指定目录中，如果目录不存在，MBG不会自动建目录");
		label_4.setText("targetProject");
		label_4.setBounds(239, 31, 86, 17);
		
		txtSrcmainresoure = new Text(grpSqlmapgenerator, SWT.BORDER);
		txtSrcmainresoure.setText("D:\\src\\main\\dao");
		txtSrcmainresoure.setBounds(325, 28, 131, 23);
		
		Button button_3 = new Button(grpSqlmapgenerator, SWT.NONE);
		button_3.setText("选择目录");
		button_3.setBounds(462, 26, 60, 27);
		button_3.addSelectionListener(new SelectFileAdapter(shlMybatisgenerator, txtSrcmainresoure));
		
		Composite composite_4 = new Composite(grpSqlmapgenerator, SWT.NONE);
		composite_4.setBounds(10, 60, 316, 17);
		
		Label label_5 = new Label(composite_4, SWT.NONE);
		label_5.setToolTipText("在targetPackage的基础上，根据数据库的schema再生成一层package，最终生成的类放在这个package下，默认为false");
		label_5.setText("enableSubPackages");
		label_5.setBounds(0, 0, 144, 17);
		
		Button button_4 = new Button(composite_4, SWT.RADIO);
		button_4.setText("true");
		button_4.setBounds(144, 0, 60, 17);
		
		Button button_5 = new Button(composite_4, SWT.RADIO);
		button_5.setText("false");
		button_5.setSelection(true);
		button_5.setBounds(219, 0, 97, 17);
		
		Group grpJavaclientgenerator = new Group(shlMybatisgenerator, SWT.NONE);
		grpJavaclientgenerator.setToolTipText("生成Mapper接口");
		grpJavaclientgenerator.setText("javaClientGenerator");
		grpJavaclientgenerator.setBounds(10, 634, 534, 84);
		
		Label label_7 = new Label(grpJavaclientgenerator, SWT.NONE);
		label_7.setToolTipText("生成的类要放的包，真实的包受enableSubPackages属性控制");
		label_7.setText("targetPackage");
		label_7.setBounds(10, 31, 86, 17);
		
		text_5 = new Text(grpJavaclientgenerator, SWT.BORDER);
		text_5.setToolTipText("");
		text_5.setText("com.example");
		text_5.setBounds(96, 28, 137, 23);
		
		Label label_8 = new Label(grpJavaclientgenerator, SWT.NONE);
		label_8.setToolTipText("目标项目，指定一个存在的目录下，生成的内容会放到指定目录中，如果目录不存在，MBG不会自动建目录");
		label_8.setText("targetProject");
		label_8.setBounds(239, 31, 86, 17);
		
		txtSrcmainjava_1 = new Text(grpJavaclientgenerator, SWT.BORDER);
		txtSrcmainjava_1.setText("D:\\src\\main\\dao");
		txtSrcmainjava_1.setBounds(325, 28, 131, 23);
		
		Button button_6 = new Button(grpJavaclientgenerator, SWT.NONE);
		button_6.setText("选择目录");
		button_6.setBounds(462, 26, 60, 27);
		button_6.addSelectionListener(new SelectFileAdapter(shlMybatisgenerator, txtSrcmainjava_1));
		
		Composite composite_5 = new Composite(grpJavaclientgenerator, SWT.NONE);
		composite_5.setBounds(10, 60, 217, 17);
		
		Label label_9 = new Label(composite_5, SWT.NONE);
		label_9.setToolTipText("在targetPackage的基础上，根据数据库的schema再生成一层package，最终生成的类放在这个package下，默认为false");
		label_9.setText("enableSubPackages");
		label_9.setBounds(0, 0, 116, 17);
		
		Button button_7 = new Button(composite_5, SWT.RADIO);
		button_7.setText("true");
		button_7.setBounds(122, 0, 41, 17);
		
		Button button_8 = new Button(composite_5, SWT.RADIO);
		button_8.setText("false");
		button_8.setSelection(true);
		button_8.setBounds(169, 0, 48, 17);
		
		Label lblType = new Label(grpJavaclientgenerator, SWT.NONE);
		lblType.setToolTipText("选择怎么生成mapper接口（在MyBatis3/MyBatis3Simple下）");
		lblType.setBounds(243, 60, 68, 17);
		lblType.setText("type");
		
		Combo combo_1 = new Combo(grpJavaclientgenerator, SWT.READ_ONLY);
		combo_1.setToolTipText("1，ANNOTATEDMAPPER：会生成使用Mapper接口+Annotation的方式创建（SQL生成在annotation中），不会生成对应的XML；\r\n2，MIXEDMAPPER：使用混合配置，会生成Mapper接口，并适当添加合适的Annotation，但是XML会生成在XML中；\r\n3，XMLMAPPER：会生成Mapper接口，接口完全依赖XML；");
		combo_1.setItems(new String[] {"ANNOTATEDMAPPER", "MIXEDMAPPER", "XMLMAPPER"});
		combo_1.setBounds(325, 59, 197, 25);
		combo_1.select(2);
		
		Label label_10 = new Label(shlMybatisgenerator, SWT.SEPARATOR | SWT.VERTICAL);
		label_10.setBounds(555, 0, 30, 728);
		
		Group grpTable = new Group(shlMybatisgenerator, SWT.NONE);
		grpTable.setText("Table");
		grpTable.setBounds(591, 183, 488, 535);
		grpTable.setLayout(null);
		
		Label label_11 = new Label(grpTable, SWT.NONE);
		label_11.setBounds(210, 32, 55, 17);
		label_11.setText("表    名：");
		
		text_7 = new Text(grpTable, SWT.BORDER);
		text_7.setBounds(271, 29, 188, 23);
		
		Button button_9 = new Button(grpTable, SWT.NONE);
		button_9.setBounds(225, 69, 80, 27);
		button_9.setText("添加");
		
		Button button_10 = new Button(grpTable, SWT.NONE);
		button_10.setBounds(358, 69, 80, 27);
		button_10.setText("删除");
		
		list = new List(grpTable, SWT.BORDER | SWT.V_SCROLL);
		list.setItems(new String[] {});
		list.setBounds(10, 24, 174, 117);
		list.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		
		Button button_generator = new Button(grpTable, SWT.NONE);
		button_generator.setBounds(225, 114, 213, 27);
		button_generator.setImage(null);
		button_generator.setText("开始生成");
		
		Label label_12 = new Label(grpTable, SWT.NONE);
		label_12.setBounds(10, 159, 61, 17);
		label_12.setText("日志记录");
		
		text_log = new Text(grpTable, SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
		text_log.setLocation(10, 189);
		text_log.setSize(451, 300);
		text_log.setEditable(false);
		text_log.setForeground(SWTResourceManager.getColor(SWT.COLOR_LINK_FOREGROUND));
		
		Link link = new Link(grpTable, SWT.NONE);
		link.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 13, SWT.NORMAL));
		link.setBounds(265, 495, 213, 30);
		link.setText("Powered By <a>Plum</a>  2019.7");
		link.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler https://www.cnblogs.com/plumsq/p/11222057.html");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		
		//添加表名的监听
		button_9.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String tableName = text_7.getText();
				if(tableName.equals("")) {
					MessageDialog.openError(shlMybatisgenerator,"错误","请输入表名再进行添加！");
					return;
				}
				String[] items = list.getItems();
				for(String item:items) {
					if(item.equals(tableName)) {
						MessageDialog.openError(shlMybatisgenerator,"错误","已经添加过此表！");
						return;
					}
				}
				if(null!=tableName) {
					list.add(tableName);
				}
			}
		});
		//删除某个表名
		button_10.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int selectionIndex = list.getSelectionIndex();
				if(selectionIndex!=-1) {
					list.remove(selectionIndex);
				}else {
					MessageDialog.openError(shlMybatisgenerator,"错误","请从左侧列表选中一个表名再进行删除。");
				}
			}
		});
		
		//生成
		button_generator.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				//清空日志记录
				text_log.setText("");
				//检查必填项
				if(!validate()) {
					log("校验失败，请检查上述信息是否填写.");
					return;
				}
				log("开始生成,请稍后。。。");
				//Context属性
				
				Control[] tabList = group1.getChildren();
				for(Control control:tabList) {
					if(control instanceof Button) {
						Button b = (Button) control;
						if(b.getSelection()) {
							defaultModelType=b.getText();
							break;
						}
					}
				}
				tabList = group2.getChildren();
				for(Control control:tabList) {
					if(control instanceof Button) {
						Button b = (Button) control;
						if(b.getSelection()) {
							targetRuntime=b.getText();
							break;
						}
					}
				}
				
				//javaTypeResolver
				
				tabList = grpJavatyperesolver.getChildren();
				for(Control control:tabList) {
					if(control instanceof Button) {
						Button b = (Button) control;
						if(b.getSelection()) {
							javaTypeResolver=b.getText();
							break;
						}
					}
				}
				
				//commentGenerator
				tabList = grpCommentgenerator.getChildren();
				for(Control control:tabList) {
					if(control instanceof Button) {
						Button b = (Button) control;
						if(b.getSelection()) {
							suppressAllComments=b.getText();
							break;
						}
					}
				}
				
				//javaModelGenerator
				Map<String,String> javaModelMap = new HashMap<String,String>();
				javaModelMap.put("targetPackage",txtComexample.getText());
				javaModelMap.put("targetProject",txtSrcmainjava.getText());
				if(btnCheckButton.getSelection()) {
					javaModelMap.put("rootClass",text.getText());
				}

				tabList = composite.getChildren();
				for(Control control:tabList) {
					if(control instanceof Button) {
						Button b = (Button) control;
						if(b.getSelection()) {
							javaModelMap.put("constructorBased",b.getText());
							break;
						}
					}
				}
				tabList = composite_2.getChildren();
				for(Control control:tabList) {
					if(control instanceof Button) {
						Button b = (Button) control;
						if(b.getSelection()) {
							javaModelMap.put("immutable",b.getText());
							break;
						}
					}
				}
				tabList = composite_3.getChildren();
				for(Control control:tabList) {
					if(control instanceof Button) {
						Button b = (Button) control;
						if(b.getSelection()) {
							javaModelMap.put("trimStrings",b.getText());
							break;
						}
					}
				}
				tabList = composite_1.getChildren();
				for(Control control:tabList) {
					if(control instanceof Button) {
						Button b = (Button) control;
						if(b.getSelection()) {
							javaModelMap.put("enableSubPackages",b.getText());
							break;
						}
					}
				}

				//sqlMapGenerator
				Map<String,String> sqlMap = new HashMap<String,String>();
				sqlMap.put("targetPackage",text_2.getText());
				sqlMap.put("targetProject",txtSrcmainresoure.getText());
				tabList = composite_4.getChildren();
				for(Control control:tabList) {
					if(control instanceof Button) {
						Button b = (Button) control;
						if(b.getSelection()) {
							sqlMap.put("enableSubPackages",b.getText());
							break;
						}
					}
				}

				//javaClientGenerator
				Map<String,String> javaClientMap = new HashMap<String,String>();
				javaClientMap.put("targetPackage",text_5.getText());
				javaClientMap.put("targetProject",txtSrcmainjava_1.getText());
				javaClientMap.put("type",combo_1.getText());
				tabList = composite_4.getChildren();
				for(Control control:tabList) {
					if(control instanceof Button) {
						Button b = (Button) control;
						if(b.getSelection()) {
							javaClientMap.put("enableSubPackages",b.getText());
							break;
						}
					}
				}

				//jdbc
				Map<String,String> jdbcMap = new HashMap<String,String>();
				jdbcMap.put("connectionURL",combo_2.getText());
				jdbcMap.put("driverClass",combo.getText());
				jdbcMap.put("userId",txtRoot.getText());
				jdbcMap.put("password",text_4.getText());

				//table
				String[] tablename = list.getItems();
				//驱动jar包
				String text2 = driverFile_text.getText();
				
				// 此处代码直接在监听器方法中，是UI线程
		        new Thread() {
		            public void run() {
		            	//开始生成
		            	GeneratorHelper generatorHelp = new GeneratorHelper();
		            	//添加jar驱动包
		            	generatorHelp.setClasspathEntry(text2);
		            	//添加配置项
		            	generatorHelp.createContext(defaultModelType, targetRuntime);
		            	generatorHelp.createCommentGeneratorConfiguration(suppressAllComments);
		            	generatorHelp.createJavaClientGenerator(javaClientMap);
		            	generatorHelp.createJavaModelGenerator(javaModelMap);
		            	generatorHelp.createJavaTypeResolverConfiguration(javaTypeResolver);
		            	generatorHelp.createJDBCConnectionConfiguration(jdbcMap);
		            	generatorHelp.createSqlMapGenerator(sqlMap);
		            	generatorHelp.createTableConfiguration(tablename);
		            	
		            	try {
		            		generatorHelp.generator();
		            	} catch (SQLException | IOException | InterruptedException | InvalidConfigurationException e1) {
		            		// 非UI线程访问UI
		                    display.syncExec(new Runnable() {
		                        @Override
		                        public void run() {
		                            // 这段代码实际上会被放在UI线程中执行
		                            log(e1.getMessage());
		                            log("生成失败，请查看上述错误日志。");
		                        }
		                    });
		            	}
		            	// 非UI线程访问UI
	                    display.syncExec(new Runnable() {
	                        @Override
	                        public void run() {
	                            // 这段代码实际上会被放在UI线程中执行
	                            log("生成成功！");
	                        }
	                    });
		            }
		        }.start();
			}
		});

		shlMybatisgenerator.open();
		shlMybatisgenerator.layout();
		while (!shlMybatisgenerator.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
	public static boolean validate() {
		log("开始校验必填项...");
		if(txtComexample.getText().equals("")) {
			log("javaModelGenerator--->targetPackage");
			return false;
		}
		String test = txtSrcmainjava.getText();
		File file= null;
		if(test.equals("")) {
			log("javaModelGenerator--->targetProject");
			return false;
		}else {
			file = new File(test);
			if(!file.exists()||file.isDirectory()) {
				file.mkdirs();
			}
		}
		if(btnCheckButton.getSelection()&&text.getText().equals("")) {
			log("javaModelGenerator--->父类");
			return false;
		}
		if(text_2.getText().equals("")) {
			log("sqlMapGenerator--->targetPackage");
			return false;
		}
		
		test = txtSrcmainresoure.getText();
		if(test.equals("")) {
			log("sqlMapGenerator--->targetProject");
			return false;
		}else {
			file = new File(test);
			if(!file.exists()||file.isDirectory()) {
				file.mkdirs();
			}
		}
		if(text_5.getText().equals("")) {
			log("javaClientGenerator--->targetPackage");
			return false;
		}
		
		test = txtSrcmainjava_1.getText();
		if(test.equals("")) {
			log("javaClientGenerator--->targetProject");
			return false;
		}else {
			file = new File(test);
			if(!file.exists()||file.isDirectory()) {
				file.mkdirs();
			}
		}
		
		if(driverFile_text.getText().equals("")) {
			log("数据库连接配置--->数据库驱动文件地址");
			return false;
		}
		if(combo_2.getText().equals("")) {
			log("数据库连接配置--->连接地址");
			return false;
		}
		if(combo.getText().equals("")) {
			log("数据库连接配置--->驱动类");
			return false;
		}
		if(txtRoot.getText().equals("")) {
			log("数据库连接配置--->帐户名");
			return false;
		}
		if(text_4.getText().equals("")) {
			log("数据库连接配置--->密码");
			return false;
		}
		if(list.getItems().length<1) {
			log("Table--->请添加要生成的表名。");
			return false;
		}
		log("检查完成。。。");
		return true;
	}
	
	public static void log(String log) {
		String text = text_log.getText()+simpleDateFormat.format(new Date())+"--->"+log+System.getProperty("line.separator");
		text_log.setText(text);
	}
}
