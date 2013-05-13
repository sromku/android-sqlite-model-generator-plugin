package com.sromku.generator.wizard;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class DetailsPage extends WizardPage
{
	private Text rootPackage;
	private Composite container;
	private String rootPackageText;

	protected DetailsPage(String rootPackage)
	{
		super("Android SQLite Model Generator");
		setTitle("Generator Parameters");
		setDescription("Input parameters for generating source java files");
		rootPackageText = rootPackage;
	}

	@Override
	public void createControl(Composite parent)
	{
		container = new Composite(parent, SWT.NULL);
		GridLayout layout = new GridLayout();
		container.setLayout(layout);
		layout.numColumns = 2;
		Label label1 = new Label(container, SWT.NULL);
		label1.setText("Output root package: ");

		rootPackage = new Text(container, SWT.BORDER | SWT.SINGLE);
		rootPackage.setText(rootPackageText);
		rootPackage.addKeyListener(new KeyListener()
		{

			@Override
			public void keyPressed(KeyEvent e)
			{
			}

			@Override
			public void keyReleased(KeyEvent e)
			{
				// if (!rootPackage.getText().isEmpty())
				// {
				// setPageComplete(true);
				// }
			}

		});
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		rootPackage.setLayoutData(gd);
		
		setControl(container);
		setPageComplete(true);
	}

	public String getRootPackage()
	{
		return rootPackage.getText();
	}

}
