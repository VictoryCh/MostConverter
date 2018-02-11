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


@XmlRootElement(name = "recmiss")
public class FormListWrapper {
	
	private List<Form> forms;

	@XmlElement(name = "nameForm")
	public List<Form> getForms(){
		return forms;
	}

	public void setForms(List<Form> forms) {
		this.forms = forms;
	}
}

