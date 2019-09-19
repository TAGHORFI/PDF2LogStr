import java.util.ArrayList;


public class Section {
	
	public String[] caption;
	public ArrayList<Paragraph> paragraphseq;
	public ArrayList<Section> sectionseq;

	public Section(String[] titre) {
		// TODO Auto-generated constructor stub
		caption=titre;
	}
	
	public String[] getCaption()
	{
		return caption;
	}
	public ArrayList<Section> getSectionSequence()
	{
		return sectionseq;
	}
	public Section getSection(int n)
	{
		return sectionseq.get(n);
	}
	public ArrayList<Paragraph> getParagraphSequence()
	{
		return paragraphseq;
	}
	
	public Paragraph getParagraph(int n)
	{
		return paragraphseq.get(n);
	}
	
	public void setCaption(String[] cap)
	{
		caption=cap;
	}
	public void addParagraph(Paragraph p)
	{
		paragraphseq.add(p);
	}
	public void setParagraphSeq(ArrayList<Paragraph> ps)
	{
		paragraphseq = ps;
	}
	public void setSectionSeq(ArrayList<Section> ss)
	{
		sectionseq = ss;
	}
	public void addSection(Section s)
	{
		sectionseq.add(s);
	}

}
