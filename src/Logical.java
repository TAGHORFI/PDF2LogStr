import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;


public class Logical {
	public   ArrayList<Section> sequencesections = new ArrayList<Section>();
 


	 public Document parse(String path) throws DocumentException {
		 	//parsing xml 
		    SAXReader reader = new SAXReader();
	        File file = new File(path);
	        Document document = reader.read(file);
	        System.out.println("Parsing ...."+file);
	        System.out.println("---------------------------");
	        return document;
	    }
	 
		@SuppressWarnings("unused")
		public void bar(Document document, String path) throws DocumentException, IOException {

			String filename = document.getName();
		 	System.out.println(path);
		 	String[] nom = filename.split("/");			
	        ArrayList<String []> data = new ArrayList<String[]>();

	        LogicalDocument doc= new LogicalDocument();
	        
			Element root = document.getRootElement();
	        List<Element> list =  root.elements("bloc");
	        for (Element element : list) {
	        	String[] ligne = new String[7];
				ligne[0] = element.attributeValue("top");
        		ligne[1] = element.attributeValue("left");
        		ligne[2] = element.attributeValue("width");
        		ligne[3] = element.attributeValue("height");
        		ligne[4] = element.attributeValue("font");
        		ligne[5] = element.attributeValue("page");
        		ligne[6] = element.getStringValue();

        		data.add(ligne);
	        }
	        Collections.sort(data, new Sortbyroll());
	        
	        Integer mostfrequentheight = null;
    		String[] line = new String[6];
    		ArrayList<String> sequence = new ArrayList<String>();
	        for(int i=0; i<data.size(); i++)
	        {
	        		line = data.get(i);
		        	if(Integer.valueOf(line[2])>=100)

	    			sequence.add(line[3]);
	        		
	        }
	        
	        Map<String, Integer> stringsCount = new HashMap<>();
	        for(String s: sequence)
	        {
	          Integer c = stringsCount.get(s);
	          if(c == null) c = new Integer(0);
	          c++;
	          stringsCount.put(s,c);
	        }
	        Map.Entry<String,Integer> mostRepeated = null;
	        for(Map.Entry<String, Integer> e: stringsCount.entrySet())
	        {
	            if(mostRepeated == null || mostRepeated.getValue()<e.getValue())
	                mostRepeated = e;
	        }
	        if(mostRepeated != null)
	        {
	        	mostfrequentheight = Integer.valueOf(mostRepeated.getKey());
	            System.out.println("Most common height: " + mostfrequentheight);
	        }
	        
	        Integer totalwidth = 0;
	        int z;
	        for(z=0; z<data.size(); z++)
	        {
	        	

	        	totalwidth = totalwidth + Integer.valueOf(data.get(z)[2]);
	        		
	        }
	        Integer mostfrequentwidth = totalwidth / (z+1);
	        System.out.println("average width: " + mostfrequentwidth);

	        
	        Integer mostfrequentleft = null;
	        Integer firstparaleft = null;
	        for(int k=0; k<data.size(); k++)
	        {
	        	if(Integer.valueOf(data.get(k)[3]) == mostfrequentheight)
	        	{
	        		
	        		firstparaleft = Integer.valueOf(data.get(k)[1]);
	        		break;
	        	}
	        }
	        System.out.println("first left: " + firstparaleft);
	        
    		ArrayList<String> sequenceleft = new ArrayList<String>();
	        for(int j=0; j<data.size(); j++)
	        {
	        	if(Integer.valueOf(data.get(j)[3]) == mostfrequentheight && Integer.valueOf(data.get(j)[2]) > 100)
	        	{
	        		sequenceleft.add(data.get(j)[1]);

	        	}
	        }
	        
	        Map<String, Integer> stringsCountleft = new HashMap<>();
	        for(String s: sequenceleft)
	        {
	          Integer c = stringsCountleft.get(s);
	          if(c == null) c = new Integer(0);
	          c++;
	          stringsCountleft.put(s,c);
	        }
	        Map.Entry<String,Integer> mostRepeatedleft = null;
	        for(Map.Entry<String, Integer> e: stringsCountleft.entrySet())
	        {
	            if(mostRepeatedleft == null || mostRepeatedleft.getValue()<e.getValue())
	                mostRepeatedleft = e;
	        }
	        if(mostRepeatedleft != null)
	        {
	        	mostfrequentleft = Integer.valueOf(mostRepeatedleft.getKey());
	            System.out.println("Most common left: " + mostfrequentleft);
	        }
	        Integer mostfrequentpolice = null;

	        ArrayList<String> sequencepolice = new ArrayList<String>();
	        for(int j=0; j<data.size(); j++)
	        {
	        	if(Integer.valueOf(data.get(j)[3]) == mostfrequentheight && Integer.valueOf(data.get(j)[2]) > 300)
	        	{
	        		sequencepolice.add(data.get(j)[4]);

	        	}
	        }
	        
	        Map<String, Integer> stringsCountpolice = new HashMap<>();
	        for(String s: sequencepolice)
	        {
	          Integer c = stringsCountpolice.get(s);
	          if(c == null) c = new Integer(0);
	          c++;
	          stringsCountpolice.put(s,c);
	        }
	        Map.Entry<String,Integer> mostRepeatedpolice = null;
	        for(Map.Entry<String, Integer> e: stringsCountpolice.entrySet())
	        {
	            if(mostRepeatedpolice == null || mostRepeatedpolice.getValue()<e.getValue())
	                mostRepeatedpolice = e;
	        }
	        if(mostRepeatedpolice != null)
	        {
	        	mostfrequentpolice = Integer.valueOf(mostRepeatedpolice.getKey());
	            System.out.println("Most common police: " + mostfrequentpolice);
	        }

	       
	        
	        int i=0;
	        line = data.get(i);
	        
	        while ((!Integer.valueOf(line[3]).equals(mostfrequentheight) && !Integer.valueOf(line[3]).equals(mostfrequentheight + 1)  && !Integer.valueOf(line[3]).equals(mostfrequentheight - 1)) || i == 0)
	        {
	        	i++;
		        line = data.get(i);
	        }
	        int highestTop=(int) (((Integer.valueOf(data.get(0)[0]) + Integer.valueOf(data.get(i+1)[0])) / Math.sqrt(Integer.valueOf(data.get(0)[0]) + Integer.valueOf(data.get(i+1)[0])))) * 10;
	     
            System.out.println("Highest top: " + highestTop);

	        
	        
	        i=0;
	        line = data.get(i);
	        while ((!Integer.valueOf(line[3]).equals(mostfrequentheight) && !Integer.valueOf(line[3]).equals(mostfrequentheight + 1)  && !Integer.valueOf(line[3]).equals(mostfrequentheight - 1)) || i == 0)
	        {

	        	if(!Integer.valueOf(line[1]).equals(mostfrequentleft) && !line[6].contains("•"))
	        	{
	        		if(doc.getTitle()==null && Integer.valueOf(line[0]) <= highestTop && Integer.valueOf(line[2]) > 50)
	        		{
	        			ArrayList<String[]> titre=new ArrayList<String[]>();
	        			titre.add(line);
	        			while(Integer.valueOf(line[3]).equals(Integer.valueOf(data.get(i+1)[3])))
	        			{
	        				titre.add(data.get(i+1));
	        				i++;
	        			}
	        			doc.setTitle(titre);
	        		}
	        		if(doc.getTitle()!=null && Integer.valueOf(line[0]) <= highestTop && Integer.valueOf(line[2]) > 50 && Integer.valueOf(doc.getTitle().get(0)[3]) < Integer.valueOf(line[3]))
	        		{
	        			doc.title.clear();
	        			ArrayList<String[]> titre=new ArrayList<String[]>();
	        			titre.add(line);
	        			while(Integer.valueOf(line[3]).equals(Integer.valueOf(data.get(i+1)[3])))
	        			{
	        				titre.add(data.get(i+1));
	        				i++;
	        			}
	        			doc.setTitle(titre);
	        		}
	        		
	        		if(doc.getTitle()!=null && Integer.valueOf(doc.getTitle().get(0)[3]) > Integer.valueOf(line[3]) && Integer.valueOf(line[0]) < highestTop)
	        		{
	        			if(doc.getSubTitle() == null)
	        			{
	        				ArrayList<String[]> soustitre=new ArrayList<String[]>();
		        			soustitre.add(line);
		        			while(Integer.valueOf(line[3]).equals(Integer.valueOf(data.get(i+1)[3])))
		        			{
		        				soustitre.add(data.get(i+1));
		        				i++;
		        			}
		        			doc.setSubTitle(soustitre);
	        			}
	        			else
	        			{
	        				if(Integer.valueOf(doc.getSubTitle().get(0)[3]) < Integer.valueOf(line[3]) && Integer.valueOf(line[0]) < highestTop)
	        				{
	        					ArrayList<String[]> soustitre=new ArrayList<String[]>();
			        			soustitre.add(line);
			        			while(Integer.valueOf(line[3]).equals(Integer.valueOf(data.get(i+1)[3])))
			        			{
			        				soustitre.add(data.get(i+1));
			        				i++;
			        			}
			        			doc.setSubTitle(soustitre);
	        				}
	        				
	        			}
	        		}
	        	}
	        	i++;
		        line = data.get(i);

	        }
	        
	        i=0;
	        line = data.get(i);
	        
	        while ((!Integer.valueOf(line[3]).equals(mostfrequentheight) && !Integer.valueOf(line[3]).equals(mostfrequentheight + 1)  && !Integer.valueOf(line[3]).equals(mostfrequentheight - 1)) || i == 0)
		    {

		        if(!Integer.valueOf(line[1]).equals(mostfrequentleft) && !line[6].contains("•"))
		        {
		        	if(doc.getTitle()==null && Integer.valueOf(line[2]) > 50)
		        	{
		        		ArrayList<String[]> titre=new ArrayList<String[]>();
		        		titre.add(line);
		        		while(Integer.valueOf(line[3]).equals(Integer.valueOf(data.get(i+1)[3])))
		        		{
		        			titre.add(data.get(i+1));
		        			i++;
		        		}
		        		doc.setTitle(titre);
		        			
		        	}
	        
		        }
		        i++;
			    line = data.get(i);
		     }
	        
	        ArrayList<String []> datat = new ArrayList<String[]>();

	        for(int k=0; k<data.size(); k++)
	        {
	        	if(Integer.valueOf(data.get(k)[1]) < 500 )
	        	{
	        		datat.add(data.get(k));
	        	}
	        }
	        
	        ArrayList<Integer> CaptionHeights = new ArrayList<Integer>();
	        Integer current = mostfrequentheight;
	        Integer Titleheight=Integer.valueOf(doc.getTitle().get(0)[3]);
	        System.out.println("titleheight " + Titleheight);
	        while(current != null)
	        {
	          Integer mfs= mostfrequentbefore(datat, current);
	       	  System.out.println("current " + mfs);
	        	if(!CaptionHeights.contains(mfs) && mfs != null)
	        	{
	       		CaptionHeights.add(mfs);
	        	}
		      current = mfs;

	       }
	        if(CaptionHeights.get(CaptionHeights.size()-1).equals(Integer.valueOf(doc.getTitle().get(0)[3])))
	        	CaptionHeights.remove(CaptionHeights.size()-1);
	        if(doc.getSubTitle()!=null && CaptionHeights.get(CaptionHeights.size()-1).equals(Integer.valueOf(doc.getSubTitle().get(0)[3])))
	        	CaptionHeights.remove(CaptionHeights.size()-1);
	        
	        System.out.println("titre =" );
	        
	        for(int r=0; r<doc.getTitle().size(); r++)
	        {
	        	 System.out.println(doc.getTitle().get(r)[6]);
	        }
	        
	        if(doc.getSubTitle()!=null)System.out.println("subtitre =" + doc.getSubTitle().get(0)[6]);
	        

	        System.out.println("list of most common hight before paragraph: " + CaptionHeights);
	        
	        i=0;
	        line = data.get(i);


	          while(!Integer.valueOf(line[3]).equals(Integer.valueOf(doc.getTitle().get(0)[3])))
	          {
	        		doc.addParagraphtoHead(new Paragraph(line));
	        		i++;
				    line = data.get(i);
	         }
	          while(Integer.valueOf(line[3]).equals(Integer.valueOf(doc.getTitle().get(0)[3])))
	          {
	        	
	        		i++;
				    line = data.get(i);
	         }
	          if(doc.getSubTitle()!=null)
	          {
	        	  while(!Integer.valueOf(line[3]).equals(Integer.valueOf(doc.getSubTitle().get(0)[3])) && Integer.valueOf(line[2]) < highestTop)
		          {
		        		
		        		
		        		doc.addParagraphtoHead(new Paragraph(line));
		        		i++;
					    line = data.get(i);
		         }
	        	  while(Integer.valueOf(line[3]).equals(Integer.valueOf(doc.getSubTitle().get(0)[3])))
		          {
		        	
		        		i++;
					    line = data.get(i);
		         }
	        	  
	          }
	          
	          while (!Integer.valueOf(line[3]).equals(Integer.valueOf(CaptionHeights.get(CaptionHeights.size() - 1))))
	          {
	              doc.addParagraphtoResume(new Paragraph(line));
	        	  i++;
	        	  line = data.get(i);
	          }
	        
	        
	        ArrayList<Integer> HeadCaptionHeights = new ArrayList<Integer>();

	        
	        while(!CaptionHeights.contains(Integer.valueOf(data.get(i-1)[3])) && Integer.valueOf(data.get(i-1)[3]) > mostfrequentheight && !HeadCaptionHeights.contains(Integer.valueOf(data.get(i-1)[3])) && Integer.valueOf(data.get(i-1)[1]) < (Integer.valueOf(data.get(i)[1]) + 50) && i > 1)
		       {
	        	
		    	   HeadCaptionHeights.add(Integer.valueOf(data.get(i-1)[3]));
		    	   i--;
			       line = data.get(i);
	        	
		       }
	        System.out.println("list of most common hight before head paragraph: " + HeadCaptionHeights);
	        
	         
	        
	         
	          

//	          System.out.println(line[6]);
//	          System.out.println("------------");
//	          if(doc.head != null)
//	          {for(int r=0; r<doc.head.size(); r++)
//		        {
//		        	 System.out.println("Head :  -------" + doc.getParagraphfromHead(r).getText()[6]);
//		       }}
	          
	          
//	          if(doc.resume != null)
//	          {for(int r=0; r<doc.resume.size(); r++)
//		        {
//		       	 System.out.println("Abstract :  -------" +doc.getParagraphfromResume(r).getText()[6]);
//		       }}
	          
	          Document newdocument = DocumentHelper.createDocument();
		      Element newroot = newdocument.addElement( "pdf2xml" ); 
		      newroot.addAttribute("producer", "Tayeb Ghorfi");
		        
		      Element wdocument = newroot.addElement("document");
		        
		      Element header = wdocument.addElement("head");
		      for(int r=0; r<doc.head.size(); r++)
		      { 
		        	Element bloc = header.addElement("bloc");
					bloc.addAttribute("top", doc.getParagraphfromHead(r).getText()[0]);
					bloc.addAttribute("left", doc.getParagraphfromHead(r).getText()[1]);
					bloc.addAttribute("width", doc.getParagraphfromHead(r).getText()[2]);
					bloc.addAttribute("height", doc.getParagraphfromHead(r).getText()[3]);
					bloc.addAttribute("font", doc.getParagraphfromHead(r).getText()[4]);
					bloc.addText(doc.getParagraphfromHead(r).getText()[6]);
		        }
		        
		        
		      Element title = wdocument.addElement("title");
		      for(int r=0; r<doc.getTitle().size(); r++)
		      {  
					Element bloc = title.addElement("bloc");
					bloc.addAttribute("top", doc.getTitle().get(r)[0]);
					bloc.addAttribute("left", doc.getTitle().get(r)[1]);
					bloc.addAttribute("width", doc.getTitle().get(r)[2]);
					bloc.addAttribute("height", doc.getTitle().get(r)[3]);
					bloc.addAttribute("font", doc.getTitle().get(r)[4]);
					bloc.addText(doc.getTitle().get(r)[6]);
		      }
		      Element subtitle = wdocument.addElement("subtitle");
		      if(doc.getSubTitle()!=null)
		        {
		         for(int r=0; r<doc.getSubTitle().size(); r++)
		        	{  
		        		Element bloc = subtitle.addElement("bloc");
		        		bloc.addAttribute("top", doc.getSubTitle().get(r)[0]);
		        		bloc.addAttribute("left", doc.getSubTitle().get(r)[1]);
						bloc.addAttribute("width", doc.getSubTitle().get(r)[2]);
						bloc.addAttribute("height", doc.getSubTitle().get(r)[3]);
						bloc.addAttribute("font", doc.getSubTitle().get(r)[4]);
						bloc.addText(doc.getSubTitle().get(r)[6]);
		        	}
		          }
		        
		    Element resume = wdocument.addElement("abstract");
		    for(int r=0; r<doc.resume.size(); r++)
		    { 
		        	Element bloc = resume.addElement("bloc");
					bloc.addAttribute("top", doc.getParagraphfromResume(r).getText()[0]);
					bloc.addAttribute("left", doc.getParagraphfromResume(r).getText()[1]);
					bloc.addAttribute("width", doc.getParagraphfromResume(r).getText()[2]);
					bloc.addAttribute("height", doc.getParagraphfromResume(r).getText()[3]);
					bloc.addAttribute("font", doc.getParagraphfromResume(r).getText()[4]);
					bloc.addText(doc.getParagraphfromResume(r).getText()[6]);
		      }
		           
    


		        
		        
	       
		   
		   
		   
		   
		   
		   Element body = wdocument.addElement("body");
		   int l = i;
		   if(Integer.valueOf(data.get(l)[3]).equals(CaptionHeights.get(CaptionHeights.size()-1))  && Integer.valueOf(data.get(l)[2]) < 500)
		    {

		    	int currentpos=CaptionHeights.size()-1;
		    	Section sec=new Section(data.get(l));
		    	Element subsection = body.addElement("section");
	        	Element caption = subsection.addElement("caption");
	        	Element bloccaption = caption.addElement("bloc");
	        	bloccaption.addAttribute("top", data.get(l)[0]);
	        	bloccaption.addAttribute("left", data.get(l)[1]);
	        	bloccaption.addAttribute("width", data.get(l)[2]);
	        	bloccaption.addAttribute("height", data.get(l)[3]);
	        	bloccaption.addAttribute("font", data.get(l)[4]);
	        	bloccaption.addText(data.get(l)[6]);
		    	System.out.println(sec.getCaption()[6]);
				System.out.println("--------------");
		    	sequencesections.add(sec);
				getSubSection(subsection, sequencesections, data, CaptionHeights, l+1, currentpos, mostfrequentheight, mostfrequentpolice, highestTop);
				doc.setSectionSeq(sequencesections);

		      }		  
	      // writeSubSection(doc.getSectionSequencefromBody(), body);
	       //creating output xml model
	       
	        
	        OutputFormat format = OutputFormat.createPrettyPrint();
	        XMLWriter writer;
	        String outpath= path.replace("blocs","logical/");
	        if(!new File(outpath).exists())
	        {
	            new File(outpath).mkdirs();
	        }
	         
	         FileOutputStream fos = new FileOutputStream(outpath+nom[nom.length-1]);
	         //writer = new XMLWriter( System.out, format );
	         writer = new XMLWriter( fos, format );

	         writer.write( newdocument );
	       
		}
		
		
		//Take as input a section and find corresponding subsections
//		public void writeSubSection(ArrayList<Section> secseq, Element section)
//		
//		{
//			for(int r=0; r<secseq.size(); r++)
//			   { 
//					Element subsection = section.addElement("section");
//		        	Element caption = subsection.addElement("caption");
//		        	Element bloccaption = caption.addElement("bloc");
//		        	bloccaption.addAttribute("top", secseq.get(r).getCaption()[0]);
//		        	bloccaption.addAttribute("left", secseq.get(r).getCaption()[1]);
//		        	bloccaption.addAttribute("width", secseq.get(r).getCaption()[2]);
//		        	bloccaption.addAttribute("height", secseq.get(r).getCaption()[3]);
//		        	bloccaption.addAttribute("font", secseq.get(r).getCaption()[4]);
//		        	bloccaption.addText(secseq.get(r).getCaption()[6]);
////		        	System.out.println(secseq.get(r).getCaption()[6]);
//		        	Element paragraph = subsection.addElement("paragraph");
//		        	if(secseq.get(r).getParagraphSequence() != null)
//		        	{
//		        	for(int t=0; t<secseq.get(r).getParagraphSequence().size();t++)
//		        	{
//		        		Element blocparagraph = paragraph.addElement("bloc");
//		        		blocparagraph.addAttribute("top", secseq.get(r).getParagraph(t).getText()[0]);
//		        		blocparagraph.addAttribute("left", secseq.get(r).getParagraph(t).getText()[1]);
//		        		blocparagraph.addAttribute("width", secseq.get(r).getParagraph(t).getText()[2]);
//		        		blocparagraph.addAttribute("height", secseq.get(r).getParagraph(t).getText()[3]);
//		        		blocparagraph.addAttribute("font", secseq.get(r).getParagraph(t).getText()[4]);
//		        		blocparagraph.addText(secseq.get(r).getParagraph(t).getText()[6]);
//		        	}
//		        	
//		        	}
//		        	
//		        	
//		        	if( secseq.get(r).getSectionSequence()!= null )
//		        		writeSubSection(secseq.get(r).getSectionSequence(), subsection);
//
//			   }
//			
//			
//			
//		}
		
		
		// find the most frequent height before a bloc having a certain height
		public Integer mostfrequentbefore ( ArrayList<String []> datat, Integer mostfrequentheight)
		{
			
			
			Integer mostfrequentheightbis = null;
	        ArrayList<String> sequenceheightbis = new ArrayList<String>();
	        for(int j=1; j<datat.size(); j++)
	        {
	        	if(Integer.valueOf(datat.get(j)[3]) == mostfrequentheight && Integer.valueOf(datat.get(j-1)[2]) > 50  && Integer.valueOf(datat.get(j-1)[2]) < 500 && Integer.valueOf(datat.get(j-1)[3]) > mostfrequentheight)
	        	{
	        		sequenceheightbis.add(datat.get(j-1)[3]);

	        	}
	        	else if(Integer.valueOf(datat.get(j)[3]) == mostfrequentheight && Integer.valueOf(datat.get(j-1)[3]) < mostfrequentheight && Integer.valueOf(datat.get(j-2)[3]) > mostfrequentheight && Integer.valueOf(datat.get(j-2)[2]) > 50  && Integer.valueOf(datat.get(j-2)[2]) < 500)
	        	{
	        		sequenceheightbis.add(datat.get(j-2)[3]);

	        	}
	        }
	        
	        Map<String, Integer> stringsCountheightbis = new HashMap<>();
	        for(String s: sequenceheightbis)
	        {
	          Integer c = stringsCountheightbis.get(s);
	          if(c == null) c = new Integer(0);
	          c++;
	          stringsCountheightbis.put(s,c);
	        }
	        Map.Entry<String,Integer> mostRepeatedheightbis = null;
	        for(Map.Entry<String, Integer> e: stringsCountheightbis.entrySet())
	        {
	            if(mostRepeatedheightbis == null || mostRepeatedheightbis.getValue()<e.getValue())
	                mostRepeatedheightbis = e;
	        }
	        if(mostRepeatedheightbis != null)
	        {
	        	mostfrequentheightbis = Integer.valueOf(mostRepeatedheightbis.getKey());
	            System.out.println("Most common hight before: " + mostfrequentheightbis);
	        }
			
				
			
			return mostfrequentheightbis;
			
		}
		public void getSubSection(Element section, ArrayList<Section> secseq, ArrayList<String []> data, ArrayList<Integer> captions, int i, int c, Integer mfh, Integer mfp, Integer ht)
		{
			System.out.println("entrée de fonction");

			ArrayList<Paragraph> paraseq = new ArrayList<Paragraph>();

			ArrayList<Section> secseq1 = new ArrayList<Section>();
			
		//	System.out.println(secseq.get(secseq.size()-1).getCaption()[3]);
		    for(int j=i; j< data.size(); j++)
		    {
		    	

				//System.out.println(captions.get(c));
				//System.out.println("......"+data.get(j)[3]+"......");

		    	if(Integer.valueOf(data.get(j)[3]).equals(mfh) && Integer.valueOf(data.get(j)[2]) > 200)
		    	{
		    		Paragraph p=new Paragraph(data.get(j));
					//System.out.println(p.getText()[6]+"tttttttttt");
					paraseq.add(p);

		    	}
		    	else if(!Integer.valueOf(data.get(j)[3]).equals(mfh) && !captions.contains(Integer.valueOf(data.get(j)[3])) && !Integer.valueOf(data.get(j)[4]).equals(mfp))	    	
		    	{
		    		if(Integer.valueOf(data.get(j)[0]) < ht)
		    		{
		        		Element header = section.getDocument().getRootElement().element("document").element("head");
		        		Element bloc = header.addElement("bloc");
						bloc.addAttribute("top", data.get(j)[0]);
						bloc.addAttribute("left", data.get(j)[1]);
						bloc.addAttribute("width", data.get(j)[2]);
						bloc.addAttribute("height", data.get(j)[3]);
						bloc.addAttribute("font", data.get(j)[4]);
						bloc.addText(data.get(j)[6]);
				        paraseq.clear();
		    		}
		    		else
		    		{
		        		Element resume = section.getDocument().getRootElement().element("document").element("abstract");
		        		Element bloc = resume.addElement("bloc");
						bloc.addAttribute("top", data.get(j)[0]);
						bloc.addAttribute("left", data.get(j)[1]);
						bloc.addAttribute("width", data.get(j)[2]);
						bloc.addAttribute("height", data.get(j)[3]);
						bloc.addAttribute("font", data.get(j)[4]);
						bloc.addText(data.get(j)[6]);
				        paraseq.clear();
		    		}
		    	}
		    	else if(c >= 1 && Integer.valueOf(data.get(j)[3]).equals(captions.get(c)) && Integer.valueOf(data.get(j)[2]) > 50  && Integer.valueOf(data.get(j)[2]) < 400)
		    	{
		    		System.out.println(data.get(j)[6]+"*******");
			    	//System.out.println(secseq.get(secseq.size()-1).getSection( secseq.get(secseq.size()-1).getSectionSequence().size()-1).getCaption()[6]);
					//System.out.println(paraseq.get(paraseq.size()-1).getText()[6]);
					//System.out.println(secseq.get(secseq.size()-1).getSection(secseq.get(secseq.size()-1).getSectionSequence().size()-1).getCaption()[6]);
			        List<Element> list =  section.getParent().elements().get(section.getParent().elements().size()-1).elements("section");
			      
			        	if(!list.isEmpty())
			        	{	Element paragraph = list.get(list.size()-1).addElement("paragraph");
				        	if(paraseq != null)
				        	{
	    		    	    	//System.out.println("afine");
	    		    	    	//System.out.println(list.get(list.size()-1).element("caption").element("bloc").getText());


				        		for(int t=0; t<paraseq.size();t++)
				        		{
				        			Element blocparagraph = paragraph.addElement("bloc");
					        		blocparagraph.addAttribute("top", paraseq.get(t).getText()[0]);
					        		blocparagraph.addAttribute("left", paraseq.get(t).getText()[1]);
					        		blocparagraph.addAttribute("width", paraseq.get(t).getText()[2]);
					        		blocparagraph.addAttribute("height", paraseq.get(t).getText()[3]);
					        		blocparagraph.addAttribute("font", paraseq.get(t).getText()[4]);
					        		blocparagraph.addText(paraseq.get(t).getText()[6]);
				        		}

			        	
			        

					
			        } }
			        	else
			        	{
			        		Element paragraph = section.addElement("paragraph");
				        	if(paraseq != null)
				        	{
				        		for(int t=0; t<paraseq.size();t++)
				        		{
				        			Element blocparagraph = paragraph.addElement("bloc");
					        		blocparagraph.addAttribute("top", paraseq.get(t).getText()[0]);
					        		blocparagraph.addAttribute("left", paraseq.get(t).getText()[1]);
					        		blocparagraph.addAttribute("width", paraseq.get(t).getText()[2]);
					        		blocparagraph.addAttribute("height", paraseq.get(t).getText()[3]);
					        		blocparagraph.addAttribute("font", paraseq.get(t).getText()[4]);
					        		blocparagraph.addText(paraseq.get(t).getText()[6]);
				        		}

			        	
			        

					
			        }
			        	}
			        paraseq.clear();
				    Element parentsection = section.getParent();
				    
				    Element subsection = parentsection.addElement("section");
		        	Element caption = subsection.addElement("caption");
		        	Element bloccaption = caption.addElement("bloc");
		        	bloccaption.addAttribute("top", data.get(j)[0]);
		        	bloccaption.addAttribute("left", data.get(j)[1]);
		        	bloccaption.addAttribute("width", data.get(j)[2]);
		        	bloccaption.addAttribute("height", data.get(j)[3]);
		        	bloccaption.addAttribute("font", data.get(j)[4]);
		        	bloccaption.addText(data.get(j)[6]);
		    		Section sec=new Section(data.get(j));
					secseq.add(sec);
					//getSubSection(section, secseq, data, captions, j+1, c, mfh, mfp, ht);
					secseq1.clear();   
		    	}
		    	
		    	else if(captions.size() == 1 && Integer.valueOf(data.get(j)[3]).equals(captions.get(c)) && Integer.valueOf(data.get(j)[2]) > 50  && Integer.valueOf(data.get(j)[2]) < 400)
		    	{
		    		System.out.println(data.get(j)[6]+"*-*-*-*");
			    	//System.out.println(secseq.get(secseq.size()-1).getSection( secseq.get(secseq.size()-1).getSectionSequence().size()-1).getCaption()[6]);
					//System.out.println(paraseq.get(paraseq.size()-1).getText()[6]);
					//System.out.println(secseq.get(secseq.size()-1).getSection(secseq.get(secseq.size()-1).getSectionSequence().size()-1).getCaption()[6]);
			        List<Element> list =  section.getParent().elements("section");
			      
			        	if(!list.isEmpty())
			        	{	Element paragraph = list.get(list.size()-1).addElement("paragraph");
			        		System.out.println("afine");
			        		System.out.println(list.get(list.size()-1).element("caption").element("bloc").getText());
				        	if(paraseq != null)
				        	{
				        		for(int t=0; t<paraseq.size();t++)
				        		{
				        			Element blocparagraph = paragraph.addElement("bloc");
					        		blocparagraph.addAttribute("top", paraseq.get(t).getText()[0]);
					        		blocparagraph.addAttribute("left", paraseq.get(t).getText()[1]);
					        		blocparagraph.addAttribute("width", paraseq.get(t).getText()[2]);
					        		blocparagraph.addAttribute("height", paraseq.get(t).getText()[3]);
					        		blocparagraph.addAttribute("font", paraseq.get(t).getText()[4]);
					        		blocparagraph.addText(paraseq.get(t).getText()[6]);
				        		}

			        	
			        

					
			        } }
			        	else
			        	{
			        		Element paragraph = section.addElement("paragraph");
				        	if(paraseq != null)
				        	{
				        		for(int t=0; t<paraseq.size();t++)
				        		{
				        			Element blocparagraph = paragraph.addElement("bloc");
					        		blocparagraph.addAttribute("top", paraseq.get(t).getText()[0]);
					        		blocparagraph.addAttribute("left", paraseq.get(t).getText()[1]);
					        		blocparagraph.addAttribute("width", paraseq.get(t).getText()[2]);
					        		blocparagraph.addAttribute("height", paraseq.get(t).getText()[3]);
					        		blocparagraph.addAttribute("font", paraseq.get(t).getText()[4]);
					        		blocparagraph.addText(paraseq.get(t).getText()[6]);
				        		}

			        	
			        

					
			        }
			        	}
			        paraseq.clear();
				    Element parentsection = section.getParent();
				    
				    Element subsection = parentsection.addElement("section");
		        	Element caption = subsection.addElement("caption");
		        	Element bloccaption = caption.addElement("bloc");
		        	bloccaption.addAttribute("top", data.get(j)[0]);
		        	bloccaption.addAttribute("left", data.get(j)[1]);
		        	bloccaption.addAttribute("width", data.get(j)[2]);
		        	bloccaption.addAttribute("height", data.get(j)[3]);
		        	bloccaption.addAttribute("font", data.get(j)[4]);
		        	bloccaption.addText(data.get(j)[6]);
		    		Section sec=new Section(data.get(j));
					secseq.add(sec);
					//getSubSection(section, secseq, data, captions, j+1, c, mfh, mfp, ht);
					secseq1.clear();   
		    	}

		    	
		    	else if(c >= 1 && Integer.valueOf(data.get(j)[3]).equals(captions.get(c-1)) && Integer.valueOf(data.get(j)[2]) > 50  && Integer.valueOf(data.get(j)[2]) < 400)
		    	{

		    	   System.out.println(data.get(j)[6]+"///////////");
					//System.out.println(paraseq.get(paraseq.size()-1).getText()[6]);

		    	    if(secseq1.isEmpty())
		    	    {	   
		    	    	System.out.println("vide");
 
		    	    	List<Element> list =  section.getParent().elements("section");
		    	    	
		    	    		if(list.get(list.size()-1).element("caption").element("bloc").getText().equals(secseq.get(secseq.size()-1).getCaption()[6]))
		    	    		{
		    	    			Element paragraph = list.get(list.size()-1).addElement("paragraph");
		    	    			if(paraseq != null)
		    	    			{
		    	    				for(int t=0; t<paraseq.size();t++)
		    	    				{
		    	    					Element blocparagraph = paragraph.addElement("bloc");
		    	    					blocparagraph.addAttribute("top", paraseq.get(t).getText()[0]);
		    	    					blocparagraph.addAttribute("left", paraseq.get(t).getText()[1]);
					        			blocparagraph.addAttribute("width", paraseq.get(t).getText()[2]);
					        			blocparagraph.addAttribute("height", paraseq.get(t).getText()[3]);
					        			blocparagraph.addAttribute("font", paraseq.get(t).getText()[4]);
					        			blocparagraph.addText(paraseq.get(t).getText()[6]);
		    	    				}

		    	    			}
		    	    
		    	    		 }
		    	    	
		    	    	secseq.get(secseq.size()-1).setParagraphSeq(paraseq);
						//System.out.println(secseq.get(secseq.size()-1).getParagraph(0).getText()[6]);
     
		    	    }
		    	    else if (!secseq1.isEmpty())
		    	    { 
		    	    	System.out.println("pas vide");
		    	    	
		    	    
		    	    	List<Element> list =  section.getParent().elements().get(section.getParent().elements().size()-1).elements("section");
		    	    	
		    	    	
		    	    			Element paragraph = list.get(list.size()-1).addElement("paragraph");
		    	    			if(paraseq != null)
		    	    			{
		    		    	    	//System.out.println("afine");
		    		    	    	//System.out.println(list.get(list.size()-1).element("caption").element("bloc").getText());

		    	    				for(int t=0; t<paraseq.size();t++)
		    	    				{
		    	    					Element blocparagraph = paragraph.addElement("bloc");
		    	    					blocparagraph.addAttribute("top", paraseq.get(t).getText()[0]);
		    	    					blocparagraph.addAttribute("left", paraseq.get(t).getText()[1]);
		    	    					blocparagraph.addAttribute("width", paraseq.get(t).getText()[2]);
		    	    					blocparagraph.addAttribute("height", paraseq.get(t).getText()[3]);
		    	    					blocparagraph.addAttribute("font", paraseq.get(t).getText()[4]);
		    	    					blocparagraph.addText(paraseq.get(t).getText()[6]);
		    	    				}

		    	    			}
		    	    		
		    	    	
		    	    }
			    	   //System.out.println(secseq.get(secseq.size()-1).getSection(secseq.get(secseq.size()-1).getSectionSequence().size()-1).getCaption()[6]);
					//secseq.get(secseq.size()-1).getSection(secseq.get(secseq.size()-1).getSectionSequence().size()-1).setParagraphSeq(paraseq);
					
		    	    paraseq.clear();
		    	   // System.out.println(section.getParent().elements().get(section.getParent().elements().size()-1).element("caption").element("bloc").getText());
		    	    Element subsection = section.getParent().elements().get(section.getParent().elements().size()-1).addElement("section");
		        	Element caption = subsection.addElement("caption");
		        	Element bloccaption = caption.addElement("bloc");
		        	bloccaption.addAttribute("top", data.get(j)[0]);
		        	bloccaption.addAttribute("left", data.get(j)[1]);
		        	bloccaption.addAttribute("width", data.get(j)[2]);
		        	bloccaption.addAttribute("height", data.get(j)[3]);
		        	bloccaption.addAttribute("font", data.get(j)[4]);
		        	bloccaption.addText(data.get(j)[6]);
		    		Section sec=new Section(data.get(j));
					secseq1.add(sec);
					getSubSection(subsection, secseq1, data, captions, j+1, c-1, mfh, mfp, ht);
					secseq.get(secseq.size()-1).setSectionSeq(secseq1);
				    

				//	System.out.println(secseq.get(secseq.size()-1).getCaption()[3]);

		    	}
		    	
		    	
		    		
		    			
		    }
		    
		    
			System.out.println("sortie de fonction");


		}
	/**
	 * @param args
	 * @throws DocumentException 
	 * @throws IOException 
	 */
	public static void main(String[] args) throws DocumentException, IOException {
		// TODO Auto-generated method stub
		Convert.main(args);
		String dossier = args[0]+"/blocs/";
		Logical logi=new Logical();

		// reading input folder
		File repertoire = new File(dossier);
        String liste[] = repertoire.list();      
      
        if (liste.length > 0) {         
            for (int i = 0; i < liste.length; i++) {
                System.out.println(liste[i]);
                Document doc = logi.parse(dossier+"/"+liste[i]);
    			logi.bar(doc, dossier);

            }
        } else {
            System.err.println("Nom de repertoire invalide");
        }
                
	}

}
