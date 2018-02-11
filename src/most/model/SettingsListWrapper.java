package most.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "nameForm")
public class SettingsListWrapper {

	private List<Setting> settings;

	@XmlElement(name = "record")
	public List<Setting> getSettings(){
		return settings;
	}

	public void setSettings(List<Setting> settings) {
		this.settings = settings;
	}
}
