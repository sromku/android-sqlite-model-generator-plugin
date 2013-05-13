package com.sromku.generator.wizard;

import org.eclipse.jface.wizard.Wizard;

public class GeneratorWizard extends Wizard
{
	protected DetailsPage one;
	private String rootOutputPackage;
	private String rootPackage;

	public GeneratorWizard(String fullPath, String fileName)
	{
		super();
		setWindowTitle("Android SQLite Model Generator");
		setNeedsProgressMonitor(true);

		rootOutputPackage = fullPath.substring(0, fullPath.indexOf(fileName));
	}

	@Override
	public void addPages()
	{
		one = new DetailsPage(rootOutputPackage);
		addPage(one);
	}

	@Override
	public boolean performFinish()
	{
		rootOutputPackage = one.getRootPackage();

		rootPackage = rootOutputPackage.substring(rootOutputPackage.indexOf("src"), rootOutputPackage.length());
		rootPackage = rootPackage.replace("/", ".");

		if (rootPackage.endsWith("."))
		{
			rootPackage = rootPackage.substring(0, rootPackage.length() - 1);
		}
		
		// remove src.
		rootPackage = rootPackage.substring(4, rootPackage.length());

		return true;
	}

	public String getRootOutputPackage()
	{
		return rootOutputPackage;
	}

	public String getPackage()
	{
		return rootPackage;
	}
}
