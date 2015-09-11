package grafica;

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

public class Plot {
	private int h;
	private int w;
	private JFreeChart torta;
	private HashMap<String,Float> mp;
	private String titolo;

	public int getHeight() {
		return h;
	}
	public int getWidth()
	{
		return w;
	}
	public void setHeight(int h)
	{
		this.h=h;
	}
	public void setWidth(int w)
	{
		this.w=w;
	}
	public void setHashMap(HashMap<String,Float> mp)
	{
		this.mp=mp;
	}
	public void setTitolo(String titolo)
	{
		this.titolo=titolo;
	}
	
	public void setTorta() {
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
	public JFreeChart getTorta()
	{
		return torta;
	}
	public void setIsto() {
		Iterator it=mp.entrySet().iterator();
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		while (it.hasNext())
		{
			Map.Entry<String, Float> map=(Map.Entry)it.next();
			dataset.addValue(map.getValue(), map.getKey(), this.titolo);
		}
		
		torta = ChartFactory.createBarChart(titolo, "utenti", titolo, dataset);

				
	}
	public JFreeChart getIsto()
	{
		return torta;
	}
}
