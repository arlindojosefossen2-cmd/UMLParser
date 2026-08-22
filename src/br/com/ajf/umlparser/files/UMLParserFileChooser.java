package br.com.ajf.umlparser.files;

import javax.swing.*;
import java.io.File;

/**
 * The Class UMLParserFileChooser.
 */
public final class UMLParserFileChooser
{
    
    /**
     * Instantiates a new UML parser file chooser.
     */
    public UMLParserFileChooser()
    {
    
    }
    
    /**
     * Show open java file dialog.
     *
     * @return the string
     */
    public String showOpenJavaFileDialog()
    {
        return new UMLParserFileChooser().shoWOpenDialogAndChooseAFile();
    }
    
    /**
     * Sho W open dialog and choose A file.
     *
     * @return the string
     */
    private String shoWOpenDialogAndChooseAFile()
    {
        final JFileChooser jfc = new JFileChooser(System.getProperty("user.dir"));
        
        jfc.setAcceptAllFileFilterUsed(false);
        jfc.setFileFilter(new GenericFilter("Java File",new String[]{"java"}));
        jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        
        final int chooser = jfc.showOpenDialog(null);
        
        return processFile(jfc,chooser);
    }
    
    /**
     * Process file.
     *
     * @param jfc the jfc
     * @param chooser the chooser
     * @return the string
     */
    private String processFile(JFileChooser jfc, int chooser)
    {
        if(chooser != JFileChooser.APPROVE_OPTION)
        {
            return null;
        }
        
        final File file = jfc.getSelectedFile();
        final String absolutePath = file.getAbsolutePath().replace(File.separator,".");
        final String[] src = absolutePath.split("src");
        final String subString = src[src.length-1].substring(1);
        
        return subString.substring(0, subString.length() - 5);
    }
}