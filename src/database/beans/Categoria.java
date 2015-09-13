package database.beans;

import java.util.HashMap;

public class Categoria implements Bean{
	private String nome;
	private String descrizione;

    public Categoria(String nome, String descrizione) {
        this.nome = nome;
        this.descrizione = descrizione;
    }
   public Categoria(){
    nome="";
    descrizione="";
   }     
	
	public void setNome(String s)
	{
		nome=s;
	}
	public void setDescrizione(String s)
	{
		descrizione=s;
	}
	public String getNome()
	{
		return nome;
	}
	public String getDescrizione()
	{
		return descrizione;
	}

    @Override
    public HashMap<String, String> toSpecial() {
       HashMap <String,String>m=new HashMap<String,String>();
       m.put("nome", nome);
       m.put("descrizione", descrizione);
     
       return m;
    }
	

}
