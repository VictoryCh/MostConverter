package most.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import most.MainApp;

//@XmlRootElement(name = "nameForm")
//@XmlAccessorType(XmlAccessType.FIELD)
public class Form {
//	private Map<String,RecMiss> recmiss;
	
	@XmlAttribute(name = "name", required = true)
	public String form;

/*	public Form(){
		recmiss = new HashMap<String, RecMiss>();
	}
	
//	@XmlElement(name = "record")
	@XmlJavaTypeAdapter(MapAdapter.class)
	public Map<String,RecMiss> getRecmiss(){
		return recmiss;
	}
	public void setRecmiss(Map<String,RecMiss> map) {
		this.recmiss = map;
	}*/
	
	

	
	@XmlElement(name = "record")
	private List<RecMiss> recmiss;
	public List<RecMiss> getRecMiss(){
		return recmiss;
	}
	public void setMiss(List<RecMiss> recmiss) {
		this.recmiss = recmiss;
	}

}


/*class MapAdapter extends XmlAdapter<RecMiss[], Map<String, RecMiss>> {
    public RecMiss[] marshal(Map<String, RecMiss> arg0) throws Exception {
    	RecMiss[] mapElements = new RecMiss[arg0.size()];
        int i = 0;
        for (Map.Entry<String, RecMiss> entry : arg0.entrySet())
            mapElements[i++] = new RecMiss(entry.getKey(),entry.getValue().getRecmost());

        return mapElements;
    	Collection<RecMiss> recmissis = arg0.values();
    	mapElements = recmissis.toArray(new RecMiss[arg0.size()]);
        return mapElements;
    }

    public Map<String, RecMiss> unmarshal(RecMiss[] arg0) throws Exception {
        Map<String, RecMiss> r = new HashMap<String, RecMiss>(arg0.length);
        for (RecMiss mapelement : arg0)
            r.put(mapelement.getRecmis(), mapelement);
        return r;
    }
}*/

/*class RecMissis{
	@XmlElement(name = "record", type = RecMiss.class, required = true)
	RecMiss[] recmissis; 
}

class RecMissAdapter extends XmlAdapter<RecMissis, Map<String, RecMiss>> {
    @Override
    public Map<String, RecMiss> unmarshal(RecMissis recmissis) throws Exception {
    	Map<String, RecMiss> map = new HashMap<String, RecMiss>(recmissis.recmissis.length);
        for (RecMiss recmiss : recmissis.recmissis)
            map.put(recmiss.getRecmis(), recmiss);
        return map;	 
    }
 
    @Override
    public RecMissis marshal(Map<String, RecMiss> map) throws Exception {
    	RecMissis recMissList = new RecMissis();
    	Collection<RecMiss> recmissis = map.values();
    	recMissList.recmissis = recmissis.toArray(new RecMiss[recmissis.size()]);
        return recMissList;
    }
}*/
