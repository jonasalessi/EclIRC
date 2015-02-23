package com.github.eclirc.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.ViewPart;

import swing2swt.layout.BorderLayout;
/**
 * UI EclIRC
 * @author Jonas Alessi
 *
 */
public class EclIRCView extends ViewPart {

	private static final String SAY = "Say ...";
	public static final String ID = "br.com.eclIRC.ui.EclIRCView"; //$NON-NLS-1$
	
	private TabFolder tabFolder;
	private Text text;
	private List listUser;
	
	public EclIRCView() {
	}

	@Override
	public void createPartControl(Composite composite) {
		composite.setLayout(new BorderLayout(2, 2));

		tabFolder = new TabFolder(composite, SWT.NONE);
		tabFolder.setLayoutData(BorderLayout.CENTER);
		
		TabItem tabItem = new TabItem(tabFolder, SWT.NONE);
		tabItem.setText("..Server..");
		
		listUser = new List(composite, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		listUser.setLayoutData(BorderLayout.WEST);
		
		text = new Text(composite, SWT.BORDER | SWT.WRAP | SWT.V_SCROLL | SWT.MULTI);
		text.setLayoutData(BorderLayout.SOUTH);
		text.setText(SAY);
		text.addFocusListener(new FocusListener() {
			public void focusLost(FocusEvent arg0) {
				text.setText(SAY);
			}
			public void focusGained(FocusEvent arg0) {
				if (text.getText().startsWith(SAY)) {
					text.setText("");
				}
			}
		});
		
	}

	@Override
	public void setFocus() {
		text.setFocus();
	}
	

}
