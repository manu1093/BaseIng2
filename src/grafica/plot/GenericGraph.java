package grafica.plot;

import java.awt.BasicStroke;
import java.awt.Color;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.XYSeries;

public class GenericGraph {

    public GenericGraph() {
    }
    
	
	private JFreeChart torta;
	private HashMap<String,Float> mp;
	private String titolo;

	
	 void setHashMap(HashMap<String,Float> mp)
	{
		this.mp=mp;
	}
	 void setTitolo(String titolo)
	{
		this.titolo=titolo;
	}
	
	 void setTorta() {
		Iterator it=mp.entrySet().iterator();
		DefaultPieDataset dataset = new DefaultPieDataset();
		while (it.hasNext())
		{
			Map.Entry<String, Float> map=(Map.Entry)it.next();
			dataset.setValue(map.getKey(), map.getValue());
		}
		boolean legend = true;
		boolean tooltips = false;
		boolean urls = false;

		torta = ChartFactory.createPieChart(titolo, dataset, legend, tooltips, urls);

		torta.setBorderPaint(Color.GREEN);
		torta.setBorderStroke(new BasicStroke(5.0f));
		torta.setBorderVisible(false);		
	}
	 JFreeChart getGraph()
	{
		return torta;
	}
	 void setIsto() {
		Iterator it=mp.entrySet().iterator();
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		while (it.hasNext())
		{
			Map.Entry<String, Float> map=(Map.Entry)it.next();
			dataset.addValue(map.getValue(), map.getKey(), this.titolo);
		}
		
		torta = ChartFactory.createBarChart(titolo, "utenti", titolo, dataset);

				
	}
	 

    
}
