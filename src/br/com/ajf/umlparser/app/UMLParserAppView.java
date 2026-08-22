package br.com.ajf.umlparser.app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import br.com.ajf.umlparser.files.JMapFileChooser;
import br.com.ajf.umlparser.files.ShowSaveDialog;
import br.com.ajf.umlparser.files.UMLParserFileChooser;
import br.com.ajf.umlparser.files.UMLParserSaveLabel;
import br.com.ajf.umlparser.images.UMLParserImageLabelPackage;
import br.com.ajf.umlparser.images.UMLParserLabel;
import br.com.ajf.umlparser.parsers.IUMLParser;
import br.com.ajf.umlparser.parsers.UMLParser;
import br.com.ajf.umlparser.utils.UMLParserInfo;
import br.com.ajf.umlparser.utils.UMLParserToolTip;

/**
 * The Class UMLParserAppView.
 */
@UMLParserToolTip(Author = "AJF",
                  Date = "Today: 21/32/43",
                  Version = "1.0",
                  Since = "1.0",
                  Package = "br.com.ajf.umlparser.app",
                  Description = "Launch class using labels.")
public final class UMLParserAppView
{
    /** The canvas. */
    private JLabel canvas;
    
    /** The window. */
    private JFrame window;
    
    /** The pk list. */
    private final List<JLabel> pkList = new LinkedList<>();
   
    /**
     * Instantiates a new UML parser app view.
     */
    public UMLParserAppView()
    {
    
    }
    
    /**
     * Creates the.
     *
     * @param windowTitle the window title
     */
    public void create(String windowTitle)
    {
        window = new JFrame(windowTitle);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(576,576);
        
        window.setIconImage(UMLParserInfo.ICON.getImage());
        
        final JMenuBar menuBar = new JMenuBar();
        
        JMenu menu = new JMenu("Menu");
        JMenuItem item = new JMenuItem(new AbstractAction("Open a Java File")
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                openJavaFile();
            }
        });
        item.setToolTipText("Just Open a Java File in The Project.");
        menu.add(item);
        
        item = new JMenuItem(new AbstractAction("Open a Package")
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                openPackage();
            }
        });
        item.setToolTipText("Just Open a Package in the Project.");
        menu.add(item);
        
        item = new JMenuItem(new AbstractAction("Clear UMLS")
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                clear();
            }
        });
        
        item.setToolTipText("Just Open a Package in the Project.");
        menu.add(item);
        
        item = new JMenuItem(new AbstractAction("Save the First UML as PNG")
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                saveUMLASPNG();
            }
        });
        
        item.setToolTipText("Just save First UML that you open in the Project.");
        menu.add(item);
        
        item = new JMenuItem(new AbstractAction("Save the All UMLS as PNG")
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                saveAllUMLASPNG();
            }
        });
        
        item.setToolTipText("Just save All UMLS that you open in the Project.");
        menu.add(item);
        
        
        item = new JMenuItem(new AbstractAction("Save the First Package UML as PNG")
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                savePackageUMLASPNG();
            }
        });
        
        item.setToolTipText("Just save First Package UML that you open in the Project.");
        menu.add(item);
        
        menuBar.add(menu);
        
        menu = new JMenu("Help");
        item = new JMenuItem(new AbstractAction("About")
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                aboutTheAuthor();
            }
        });
        item.setToolTipText("Just About the Author: email,name,etc...");
        menu.add(item);
        menuBar.add(menu);
        
        final JLabel jLabelTitle = new JLabel(new ImageIcon(this.getClass().getResource("/title.png")));
        
        canvas = new JLabel(new ImageIcon(this.getClass().getResource("/background.png")));
        canvas.setLayout(null);
        
        final JPanel container = new JPanel();
        container.setLayout(new BorderLayout());
        
        final JPanel jsContainer = new JPanel();
        jsContainer.setLayout(new BorderLayout());
        
        jsContainer.add(menuBar,BorderLayout.NORTH);
        jsContainer.add(jLabelTitle,BorderLayout.CENTER);
        
        container.add(canvas,BorderLayout.SOUTH);
        
        final JScrollPane jScrollPane = new JScrollPane(container);
        
        jScrollPane.setWheelScrollingEnabled(true);
        jScrollPane.setEnabled(true);
        window.getContentPane().setLayout(new BorderLayout());
        
        window.add(jsContainer,BorderLayout.NORTH);
        window.add(jScrollPane,BorderLayout.CENTER);
        
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
    
    /**
     * Save all UMLASPNG.
     */
    protected void saveAllUMLASPNG()
	{
    	final File file = ShowSaveDialog.showSaveDialog(window);
    	
    	if(file != null)
    	{
    		try
    		{
    			new UMLParserSaveLabel().saveAllLabels(file,canvas);
    		}
    		catch(IOException e)
    		{
    			UMLParserInfo.show("ERROR", e.getMessage());
    		}
    	}
	}

	/**
     * Save package UMLASPNG.
     */
    protected void savePackageUMLASPNG()
	{
    	final File file = ShowSaveDialog.showSaveDialog(window);
		
		if(file != null)
		{
			try
			{
				new UMLParserSaveLabel().saveLabels(file, pkList);
			} 
			catch (IOException e)
			{
				UMLParserInfo.show("ERROR", e.getMessage());
			}
		}
	}

	/**
     * Save the First UML AS PNG.
     */
    protected void saveUMLASPNG()
	{
		final File file = ShowSaveDialog.showSaveDialog(window);
		
		if(file != null)
		{
			try
			{
				new UMLParserSaveLabel().saveLabel(file, canvas);
			} 
			catch (IOException e)
			{
				UMLParserInfo.show("ERROR", e.getMessage());
			}
		}
	}

	/**
     * Clear.
     */
    private void clear()
    {
        canvas.removeAll();
        canvas.repaint();
        pkList.clear();
    }
    
    /**
     * About the author.
     */
    private void aboutTheAuthor()
    {
        UMLParserInfo.show("Info",
                """
                           Author: A.J.F. BadEnterprise S/A.
                           Email: ArlindoJoseFossen2@gmail.com
                           That's a Simple Uml Parser for Java Files.
                           I made this code using reflection and
                           All code is free for Utilization and Modification.
                                    ***tanks by all***  \s""");
    }
    
    /**
     * Open package.
     */
    private void openPackage()
    {
        final JMapFileChooser jMap = new JMapFileChooser();
        jMap.show();
        final Map<String,List<String>> map = jMap.getMap();
        final IUMLParser parser = new UMLParser();
        
        int x = 8;
        int y = 8;
        int maxHeight = 0;
        
        if(map == null || map.isEmpty())
        {
        	return;
        }
        
        for(String key : map.keySet())
        {
            final List<String> classNames = map.get(key);
            
            if(classNames.isEmpty())
            {
            	return;
            }
            
            final UMLParserImageLabelPackage img = new UMLParserImageLabelPackage(key,classNames,parser, window);
            canvas.add(img.getCanvas());
            pkList.add(img.getCanvas());
            
            img.getCanvas().setBounds(x,y,img.getCanvas().getWidth(),img.getCanvas().getHeight());
            
            maxHeight = Math.max(img.getCanvas().getHeight(),maxHeight);
            x += img.getCanvas().getWidth();
            
            if(x + img.getCanvas().getWidth() >= canvas.getWidth())
            {
                x = 8;
                y += (int) (maxHeight * 0.75 + 8);
            }
        }
        
        canvas.repaint();
    }
    
    /**
     * Open java file.
     */
    private void openJavaFile()
    {
        final IUMLParser parser = new UMLParser();
        final String showOpenJavaFileDialog = new UMLParserFileChooser().showOpenJavaFileDialog();
        
        if(showOpenJavaFileDialog == null || showOpenJavaFileDialog.isEmpty())
        {
        	return;
        }
        
		final UMLParserLabel img = new UMLParserLabel(parser,showOpenJavaFileDialog, window);
        
        final String packageName = img.getPackageName();
        final JLabel jLabel = new JLabel(packageName);
        jLabel.setForeground(Color.WHITE);
        jLabel.setBackground(Color.RED);
        jLabel.setBorder(BorderFactory.createLineBorder(Color.CYAN, 4, true));
        jLabel.setBounds(8, 8, jLabel.getText().length()*8, 20);
        canvas.add(jLabel);
        jLabel.setLocation(8, 8);
        
        final JLabel imgLabel = (JLabel) img.getComponent();
        imgLabel.setLocation(8,48);
       
        canvas.add(img.getToolTipLabel(),0);
        canvas.add(imgLabel,1);
        canvas.repaint();
    }
    

    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(String[] args)
    {
        new UMLParserAppView().create("UML_Parser_Using_JLabel-1.0-ByAJF");
    }
}