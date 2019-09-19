import java.util.ArrayList;


public class LogicalDocument {
	
	public ArrayList<String[]> title;
	public ArrayList<String[]> subtitle;
	public ArrayList<Paragraph> head = new ArrayList<Paragraph>();
	public ArrayList<Paragraph> resume = new ArrayList<Paragraph>();
	public ArrayList<Section> sectionseq = new ArrayList<Section>(); 
	
	public ArrayList<String[]> getTitle()
	{
		return title;
	}
	public ArrayList<String[]> getSubTitle()
	{
		return subtitle;
	}
	
	public Paragraph getParagraphfromHead(int n)
	{
		return head.get(n);
	}
	public Paragraph getParagraphfromResume(int n)
	{
		return resume.get(n);
	}
	public ArrayList<Section> getSectionSequencefromBody()
	{
		return sectionseq;
	}
	public ArrayList<Paragraph> getParagraphSequencefromHead()
	{
		return head;
	}
	public ArrayList<Paragraph> getParagraphSequencefromResume()
	{
		return resume;
	}
	public Section getSectionfromBody(int n)
	{
		return sectionseq.get(n);
	}
	
	
	public void setTitle(ArrayList<String[]> t)

	{
		title=t;
	}
	public void setSubTitle(ArrayList<String[]> st)

	{
		subtitle=st;
	}
	public void setSectionSeq(ArrayList<Section> ss)
	{
		sectionseq = ss;
	}
	public void addParagraphtoHead(Paragraph s)
	{
		if(!head.contains(s))
		head.add(s);
	}
	public void addParagraphtoResume(Paragraph s)
	{
		if(!resume.contains(s))
		resume.add(s);
	}
	public void addSectiontoBody(Section s)
	{
		sectionseq.add(s);
	}
}
