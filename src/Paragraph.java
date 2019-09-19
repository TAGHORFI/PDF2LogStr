
public class Paragraph {

	public String[] text;
	public String[] table;
	
	public Paragraph(String[] paragraph) {
		// TODO Auto-generated constructor stub
		text=paragraph;
	}

	public String[] getText()
	{
		return text;
	}
	
	public String[] getTable()
	{
		return table;
	}
	
	
	public void setText(String[] txt)
	{
		text=txt;
	}
	
	public void setTable(String[] tab)
	{
		table=tab;
	}

}
