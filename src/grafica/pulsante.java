package grafica;

public class pulsante {
	private String label;
	private String pagina;
	private String attributi;
	private String metodo="GET";
	
	public void setMetodo(String metodo)
	{
		this.metodo=metodo;
	}
	public void setLabel(String label)
	{
		this.label=label;
	}
	public void setPagina(String pagina)
	{
		this.pagina=pagina;
	}
	public void setAttributi(String attributi)
	{
		this.attributi=attributi;
	}
	public String getIndirizzo()
	{
		return ""+pagina+"?"+attributi;
	}
	public String getBottone()
	{
		return "<FORM METHOD="+metodo+" ACTION=\"/BaseIng2/"+this.getIndirizzo()+"\">\n<INPUT TYPE=\"submit\" value=\""+label+"\">\n</FORM>";
	}
	
	
	
}
