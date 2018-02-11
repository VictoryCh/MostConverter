package most.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class RecMiss {

	private String recmost;
	private String recmis;
	private Integer byte_;
	private Integer Nfile;

	public RecMiss() {
		this.recmost = new String("");
		this.recmis = new String("");
		this.byte_ = new Integer(1);
		this.Nfile = new Integer(1);
	}

//	@XmlAttribute(name = "recmost", required = true)
	@XmlElement(name = "recmost")
	public String getRecmost() {
		return recmost;
	}

	public void setRecmost(String recmost) {
		this.recmost = recmost;
	}
	@XmlElement (name = "recmis")
	public String getRecmis() {
		return recmis;
	}

	public void setRecmis(String recmis) {
		this.recmis = recmis;
	}
	@XmlElement (name = "byte_")
	public Integer getByte_() {
		return byte_;
	}

	public void setByte_(Integer byte_) {
		this.byte_ = byte_;
	}
	@XmlElement (name = "Nfile")
	public Integer getNfile() {
		return Nfile;
	}

	public void setNfile(Integer nfile) {
		Nfile = nfile;
	}
}
