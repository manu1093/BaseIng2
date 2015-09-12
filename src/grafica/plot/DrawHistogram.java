/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafica.plot;

import java.util.HashMap;
import org.jfree.chart.JFreeChart;

/**
 *
 * @author manu
 */
public class DrawHistogram implements Graph{

    private GenericGraph graph;   
    
    public DrawHistogram( HashMap<String, Float> mp, String titolo) {
       graph=new GenericGraph();       
       this.graph.setTitolo(titolo);
       this.graph.setHashMap(mp);
      
    }
    @Override
    public JFreeChart Draw() {
       this.graph.setIsto();
       return this.graph.getGraph();
    }
    
}
