package com.sromku.generator.command;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.handlers.HandlerUtil;

import com.sromku.generator.Generator;
import com.sromku.generator.wizard.GeneratorWizard;

public class GenerateHandler extends AbstractHandler
{
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException
	{
		Shell shell = HandlerUtil.getActiveShell(event);
		ISelection sel = HandlerUtil.getActiveMenuSelection(event);

		IFile firstElement = null;
		if (sel instanceof TreeSelection)
		{
			TreeSelection selection = (TreeSelection) sel;
			firstElement = (IFile) selection.getFirstElement();
		}

		String fullPath = firstElement.getLocation().toPortableString();
		String fileName = firstElement.getName();
		GeneratorWizard generatorWizard = new GeneratorWizard(fullPath, fileName);
		WizardDialog wizardDialog = new WizardDialog(shell, generatorWizard);
		if (wizardDialog.open() == Window.OK)
		{
			String outputFolder = generatorWizard.getRootOutputPackage();
			String packageImport = generatorWizard.getPackage();

			Generator generator = new Generator();
			generator.generate(fullPath, outputFolder, packageImport);
		}
		else
		{
		}
		return null;
	}

}
