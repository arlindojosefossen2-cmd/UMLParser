package br.com.ajf.umlparser.files;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * The Class JMapFileChooser.
 */
public class JMapFileChooser
{
    
    /** The Constant FILTER. */
    private static final String FILTER = ".java";
    
    /** The Constant SRC_PATH_SPLIT. */
    private static final String SRC_PATH_SPLIT = "src";
    
    /** The Constant ONE. */
    private static final int ONE = 1;
    
    /** The map. */
    private final Map<String, List<String>> map = new HashMap<>();
    
    /**
     * Instantiates a new j map file chooser.
     */
    public JMapFileChooser()
    {
    
    }
    
    /**
     * Show.
     */
    public void show()
    {
        process(ShowOpenDialog.select());
    }
    
    /**
     * Process.
     *
     * @param file the file
     */
    private void process(File file)
    {
        if(file == null)
        {
            return;
        }
        
        final File[] files = file.listFiles();
        
        if(files != null)
        {
            for (File f : files)
            {
                process(f);
                
                if(filter(f))
                {
                    processMap(f);
                }
            }
        }
    }
    
    /**
     * Process map.
     *
     * @param f the f
     */
    private void processMap(File f)
    {
        final String subString = getSubSrcString(f);
        final String[] pk = subString.split("\\.");
        final StringBuilder aux = new StringBuilder();
        
        for (int i = 0; i < pk.length-2; i++)
        {
            aux.append(i == pk.length - 2 ? pk[i] : pk[i] + ".");
        }
      
        if(map.containsKey(aux.toString()))
        {
            final List<String> list = map.get(aux.toString());
            list.addAll(processList(f,new LinkedList<>()));
            map.replace(aux.toString(),list);
        }
        else
        {
            map.put(aux.toString(),processList(f,new LinkedList<>()));
        }
    }
    
    /**
     * Process list.
     *
     * @param f the f
     * @param list the list
     * @return the list
     */
    private List<String> processList(File f, LinkedList<String> list)
    {
        final String subString = getSubSrcString(f);
        final String pk = subString.substring(0, subString.length()-5);
        list.add(pk);
        return list;
    }
    
    /**
     * Gets the sub src string.
     *
     * @param f the f
     * @return the sub src string
     */
    private static String getSubSrcString(File f)
    {
        final String absolutePath = f.getAbsolutePath().replace("\\",".");
        final String[] sp = absolutePath.split(SRC_PATH_SPLIT);
        return sp[sp.length - ONE].substring(ONE);
    }
    
    /**
     * Filter.
     *
     * @param f the f
     * @return true, if successful
     */
    private boolean filter(File f)
    {
        return f.getAbsolutePath().contains(FILTER);
    }
    
    /**
     * Gets the map.
     *
     * @return the map
     */
    public Map<String, List<String>> getMap()
    {
        return map;
    }
}