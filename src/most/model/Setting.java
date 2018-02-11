package most.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import most.MainApp;

public class Setting {
	private MainApp mainApp;
	
	
	private StringProperty type;
	private IntegerProperty count;
	private IntegerProperty len;
	private StringProperty recmost;
	private StringProperty name;
/*	private StringProperty recmis;
	private IntegerProperty byte_;
	private IntegerProperty Nfile;*/
	
	public Setting() {
		this.type = new SimpleStringProperty("type");
		this.count = new SimpleIntegerProperty(1);
		this.len = new SimpleIntegerProperty(1);
		this.recmost = new SimpleStringProperty("recmost");
		this.name = new SimpleStringProperty("name");
/*		this.recmis = new SimpleStringProperty("recmis");
		this.byte_ = new SimpleIntegerProperty(1);
		this.Nfile = new SimpleIntegerProperty(1);*/
	}
	
	public String getType() {
		return type.get();
	}

	public void setType(String type) {
		this.type.set(type);
	}
	
	public StringProperty typeProperty() {
		return type;
	}

	public Integer getCount() {
		return count.get();
	}

	public void setCount(Integer count) {
		this.count.set(count);
	}
	
	public IntegerProperty countProperty(){
		return count;
	}

	public Integer getLen() {
		return len.get();
	}

	public void setLen(Integer len) {
		this.len.set(len);
	}
	
	public IntegerProperty lenProperty() {
		return len;
	}

	public String getRecmost() {
		return recmost.get();
	}

	public void setRecmost(String recmost) {
		this.recmost.set(recmost);
	}
	
	public StringProperty RecmostProperty(){
		return recmost;
	}
	
	public String getName() {
		return name.get();
	}

	public void setName(String name) {
		this.name.set(name);
	}

	public StringProperty nameProperty() {
		return name;
	}
	
	/*public String getRecmis() {
		return recmis.get();
	}

	public void setRecmis(String recmis) {
		this.recmis.set(recmis);
	}
	
	public StringProperty recmisProperty() {
		return recmis;
	}

	public Integer getByte_() {
		return byte_.get();
	}

	public void setByte_(Integer byte_) {
		this.byte_.set(byte_);
	}
	
	public IntegerProperty byteProperty() {
		return byte_;
	}

	public Integer getNfile() {
		return Nfile.get();
	}

	public void setNfile(Integer nfile) {
		this.Nfile.set(nfile);
	}
	
	public IntegerProperty nfileProperty() {
		return Nfile;
	}*/

	private void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
	
}
