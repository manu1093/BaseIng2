/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafica.plot;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jfree.chart.ChartUtilities;

/**
 *
 * @author manu
 */
public class InvokerGraph {
    private Graph graph;
    private int height;
    private int wight;
    public InvokerGraph(int height, int wight,Graph g){
        this.graph=g;
        this.height=height;
        this.wight=height;
    } 
    public void graph(OutputStream os) {
        try {
            ChartUtilities.writeChartAsPNG(os, graph.Draw(), this.wight, this.height);
        } catch (IOException ex) {
            Logger.getLogger(InvokerGraph.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
