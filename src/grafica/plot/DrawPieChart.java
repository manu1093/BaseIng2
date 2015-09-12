/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafica.plot;

import java.awt.BasicStroke;
import java.awt.Color;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletOutputStream;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author manu
 */
public class DrawPieChart implements Graph{
     private GenericGraph graph;   
    public DrawPieChart( HashMap<String, Float> mp, String titolo) {
       graph=new GenericGraph();       
       this.graph.setTitolo(titolo);
       this.graph.setHashMap(mp);
       
    }
    @Override
    public JFreeChart Draw() {
        this.graph.setTorta();
       return this.graph.getGraph();
            
    }
    
    
    
}
