package com.github.eclirc.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.ViewPart;

import com.github.eclirc.core.UserIRC;

import swing2swt.layout.BorderLayout;
/**
 * UI EclIRC
 * @author Jonas Alessi
 */
public class EclIRCView extends ViewPart {

	private static final String SAY = "Say ...";
	public static final String ID = "br.com.eclIRC.ui.EclIRCView"; //$NON-NLS-1$
	
	private TabFolder tabServers;
	private Text txtSay;
	private List listUser;
	private UserIRC user;
	
	public EclIRCView() {
	}

	@Override
	public void createPartControl(Composite composite) {
		composite.setLayout(new BorderLayout(2, 2));

		tabServers = new TabFolder(composite, SWT.NONE);
		tabServers.setLayoutData(BorderLayout.CENTER);
		
		createTabUser();

		listUser = new List(composite, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		listUser.setLayoutData(BorderLayout.WEST);
		
		txtSay = new Text(composite, SWT.BORDER | SWT.WRAP | SWT.V_SCROLL | SWT.MULTI);
		txtSay.setLayoutData(BorderLayout.SOUTH);
		txtSay.setText(SAY);
		txtSay.addFocusListener(new FocusListener() {
			public void focusLost(FocusEvent arg0) {
				txtSay.setText(SAY);
			}
			public void focusGained(FocusEvent arg0) {
				if (txtSay.getText().startsWith(SAY)) {
					txtSay.setText("");
				}
			}
		});
		
	}

	private void createTabUser() {
		final TabItem tbtmUser = new TabItem(tabServers, SWT.NONE);
		tbtmUser.setText(".. User ..");
		
		Composite contentUser = new Composite(tabServers, SWT.NONE);
		tbtmUser.setControl(contentUser);
		contentUser.setLayout(new GridLayout(2, false));
		
		Label lblNick = new Label(contentUser, SWT.NONE);
		lblNick.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNick.setText("Nick:");
		
		final Text txtNick = new Text(contentUser, SWT.BORDER);
		txtNick.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblRealname = new Label(contentUser, SWT.NONE);
		lblRealname.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblRealname.setText("Real name:");
		
		final Text txtRealName = new Text(contentUser, SWT.BORDER);
		txtRealName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblPassword = new Label(contentUser, SWT.NONE);
		lblPassword.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblPassword.setText("Password:");
		
		final Text txtPassword = new Text(contentUser, SWT.BORDER);
		txtPassword.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblUsername = new Label(contentUser, SWT.NONE);
		lblUsername.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblUsername.setText("Username:");
		
		final Text txtUsername = new Text(contentUser, SWT.BORDER);
		txtUsername.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(contentUser, SWT.NONE);
		
		Button btnOk = new Button(contentUser, SWT.NONE);
		btnOk.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent e) {
				user = new UserIRC(txtNick.getText(), txtRealName.getText(), txtPassword.getText(), txtUsername.getText());
				tbtmUser.dispose();
				createTabServer();
			}
		});
		GridData gd_btnOk = new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1);
		gd_btnOk.widthHint = 52;
		btnOk.setLayoutData(gd_btnOk);
		btnOk.setText("Ok");
	}

	private void createTabServer() {
		Composite contentTab = new Composite(tabServers, SWT.NONE);
		
		TabItem tabItem = new TabItem(tabServers, SWT.NONE);
		tabItem.setControl(contentTab);
		tabItem.setText("..Server..");
		
		GridLayout layoutContentTab = new GridLayout(2, false);
		layoutContentTab.marginHeight = 10;
		layoutContentTab.marginLeft = 10;
		contentTab.setLayout(layoutContentTab);
		
		Label lblHost = new Label(contentTab, SWT.NONE);
		lblHost.setText("Host:");
		
		Text txtHost = new Text(contentTab, SWT.BORDER);
		GridData gd_txtHost = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_txtHost.widthHint = 173;
		txtHost.setLayoutData(gd_txtHost);
		
		Label lblNewLabel = new Label(contentTab, SWT.NONE);
		lblNewLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel.setText("Port:");
		
		Text txtPort = new Text(contentTab, SWT.BORDER);
		txtPort.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		new Label(contentTab, SWT.NONE);
		new Label(contentTab, SWT.NONE);
		new Label(contentTab, SWT.NONE);
		
		Button btnConect = new Button(contentTab, SWT.NONE);
		btnConect.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
		btnConect.setText("Conect");
		
		contentTab.update();
	}

	@Override
	public void setFocus() {
		txtSay.setFocus();
	}
}
