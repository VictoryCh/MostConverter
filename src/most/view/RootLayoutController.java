package most.view;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import most.MainApp;
import most.model.Form;
import most.model.RecMiss;
import most.model.Setting;
import most.util.*;

public class RootLayoutController {

	private MainApp mainApp;
	
	private Map<String, Form> mapForms;
	private Map<String, RecMiss> allRec;
	private Requisites requisites;
	
	private CheckBox[] grCheck;
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnSetting;

    @FXML
    private Button btnRun;

    @FXML
    private TextField txtDir;
    
    @FXML
    private TextField txtDirf3;
    
    @FXML
    private TextField txtPeriod;
    
    @FXML
    private CheckBox chAll;
    
    @FXML
    private CheckBox chF13;
    
    @FXML
    private CheckBox chF2;
    
    @FXML
    private CheckBox chF3;
    
    @FXML
    private CheckBox chF41;
    
    @FXML
    private CheckBox chF5;
    
    @FXML
    private CheckBox chF62;
    
    @FXML
    private CheckBox chFPPL13;
    
    @FXML
    private CheckBox chFPPL41;
    
    @FXML
    private Button btnOpen;
    
    @FXML
    private Button btnOpen1;
    
    public void setMainApp(MainApp mainApp) {
    	this.mainApp = mainApp;
    }
    
//    public List<Form3.VDF302> vdf302 = null;
    public Map<String,List<HashMap<String,String>>> vdf301 = null;
    
    @FXML
    void initialize() {
    	txtDir.setText(System.getProperty("user.dir") + "/input/");
    	txtDirf3.setText(System.getProperty("user.dir") + "/DataBase/");
    	
    	Calendar cl = Calendar.getInstance();
    	cl.add(Calendar.MONTH, -1);
    	Date dt = cl.getTime();
    	SimpleDateFormat fmt = new SimpleDateFormat("YYMM");
    	
    	txtPeriod.setText(fmt.format(dt));
    	
    	grCheck = new CheckBox[] {chF13, chF2, chF3, chF41, chF5, chF62, chFPPL13, chFPPL41};
    	
    	chAll.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				Arrays.stream(grCheck).forEach(ch->ch.setSelected(newValue));				
			}
            });
    }

    private void handleOpen() {
    	FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        File file = fileChooser.showOpenDialog(mainApp.getPrimaryStage());

        if (file != null) {
            mainApp.LoadSettingsFromFile(file);
        }
    }
    
    @FXML
    private void RunOnAction() throws IOException{
    	mapForms = mainApp.getFormsData(); //получили все соответствия реквизитов из МИССИС
    	allRec = new HashMap<String, RecMiss>();
    	mapForms.forEach( (k,v)-> v.getRecMiss().forEach((RecMiss rec)-> allRec.put(rec.getRecmost(), rec)));
    	requisites = new Requisites(mapForms);
    	
    	
    	
    	Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Information");
	    alert.setHeaderText(null);
	    
    	for (int i=0; i<grCheck.length; i++) {
    		
			
    		if (grCheck[i].isSelected()) {
    			long startTime = System.currentTimeMillis();
    			
    			if ((grCheck[i].getText().equals("F13")||grCheck[i].getText().equals("FPPL13")||grCheck[i].getText().equals("F3")) && vdf301==null) {
//	    			vdf302 = new Form3(txtDirf3.getText(),txtPeriod.getText().substring(0,2)).getVDF302();
	    			vdf301 = new Form3(txtDirf3.getText(),txtPeriod.getText().substring(0,2)).getVDF301();
    			}
    			CreateSDF(grCheck[i].getText());
    			
    			
    			
    			long timeSpent = System.currentTimeMillis() - startTime;
    		    alert.setContentText("Done!\nTime: " + timeSpent/1000);
    		    alert.showAndWait();
    		}
    	}
 
    }
    
    @FXML
    private void OpenOnAction() {
    	DirectoryChooser chooser = new DirectoryChooser();
    	
        File file = chooser.showDialog(mainApp.getPrimaryStage());
        if (file != null) {
            txtDir.setText(file.getAbsolutePath());
        }
    }
    
    @FXML
    private void Open1OnAction() {
    	DirectoryChooser chooser = new DirectoryChooser();
    	
        File file = chooser.showDialog(mainApp.getPrimaryStage());
        if (file != null) {
            txtDirf3.setText(file.getAbsolutePath());
        }
    }
    
    public void CreateSDF(String name) throws IOException {
    	String urlFile = System.getProperty("user.dir");
    	BufferedReader reader = null;
    	BufferedWriter writer = null;
    	
    	boolean flagF3 = false;

    	File file = new File(urlFile + "/Dictionary/" + name +".xml");

		if (file != null) {
			mainApp.LoadSettingsFromFile(file);
		}
		
    	List<Setting> lsSetData = mainApp.getSettingsData();  	

//===================================================================
    	
       	try {

    			File fout = new File(urlFile + "/output/");
    			
    			if (!fout.exists()) {
    				fout.mkdirs();
    			}
    			fout = new File(urlFile + "/output/"+ name +"-20"+ txtPeriod.getText() +"-1114.sdf");
    				    
    			if (System.getProperty("os.name").toUpperCase().contains("LINUX"))
    				Runtime.getRuntime().exec("chmod 777 " + urlFile + "/output/"+ name +"-20"+ txtPeriod.getText() +"-1114.sdf");
    			
    			writer = new BufferedWriter(
    					new OutputStreamWriter(new FileOutputStream(fout), Charset.forName("ISO8859_5")));

    			//==========================================
    			if (!name.equals("F3")) {
    				if(name.equals("F13") || name.equals("FPPL13")) flagF3=true;
	    			reader = new BufferedReader(
	    					new InputStreamReader(new FileInputStream(urlFile + "/input/"+name+".TXT"), Charset.forName("cp866")));
	    			
	    			String line;
	    			String reg = "\\d{3}\\/[^\\/]+\\/";   			
	    			Pattern pattern = Pattern.compile(reg);
	    			
	    			while ((line = reader.readLine()) != null) {
	    				Map<String, String> ud = new HashMap<String, String>(); //данные из формата ввода для одного УД
	    				List<String> list = new ArrayList<String>();
	
	    				Matcher m = pattern.matcher(line);
	    				while (m.find()) {
	    					String[] s = m.group().split("/");
	    					if (!s[0].equals("520"))
	    						list.add(m.group());
//	    					System.out.println(m.group());
	    				}
	    				
//	    				list.forEach(System.out::println);
	    				
	    	    		ud = list.stream()
	    	        				.map(s -> s.split("/"))
	    	        				.collect(Collectors.toMap(x -> x[0], x->x[1]));
	    	    		
	    	    		if (ud.get("002").equals("4")) ud.put("002", "1");
	    	    		
	    	    		//Формируем строку для моста
	    	    		StringBuffer str = new StringBuffer();
	    	    		
	    	    		Map<String, String> f3 = null;
	    	    		if(flagF3) {
	    	    			int index;
	    	    			String numUD = ud.get("003");
	    	    			if (vdf301.containsKey(numUD)) {
	    	    				index = vdf301.get(numUD).size();
	    	    				f3 = vdf301.get(ud.get("003")).get(index-1);
	    	    			} else f3=null; 							
	    	    			
	    	    		}
	    	    		
	    	    		for(int i=0; i<lsSetData.size(); i++) {
	    	    			
	    	    			if (lsSetData.get(i).getRecmost().equals("Р[3-6]")) {
	    	    				lsSetData.get(i).setRecmost("Р[1-11-2]");
	    	    			}
	    	    			
	    	    			RecMiss recmiss = allRec.get(lsSetData.get(i).getRecmost());
	    	    			//=====================================================
	    	    			if(flagF3 && requisites.isForm3(lsSetData.get(i).getRecmost())) {
	    	    				
	    	    				if (f3!=null&&f3.containsKey(recmiss.getRecmis())) {
	    	    					str.append(requisites.ModifRec(lsSetData.get(i),recmiss,f3.get(recmiss.getRecmis())));
	    	    				} else {
	    	    					str.append(requisites.ModifRec(lsSetData.get(i),recmiss,null));
	    	    				}
	    	    			}else 
	    	    				str.append(requisites.ModifRec(lsSetData.get(i),recmiss,ud.get(recmiss.getRecmis()))); 			
	    	    		}
	    	    		writer.write(str + "\r\n");
	        		}
	    			flagF3 = false;
    			} else {
    				List<Map<String,String>> listf3 = new Form3(txtDirf3.getText(),txtPeriod.getText().substring(0,2)).getListF3(vdf301);
    				System.out.println("Start!");
    				for (int i=0; i<listf3.size(); i++) {
	    	    		//Формируем строку для моста
	    	    		StringBuffer str = new StringBuffer();
	    	    		    	    		
	    	    		for(int j=0; j<lsSetData.size(); j++) {
	    	    			RecMiss recmiss = allRec.get(lsSetData.get(j).getRecmost());
    	    				str.append(requisites.ModifRec(lsSetData.get(j),recmiss,listf3.get(i).get(recmiss.getRecmis()))); 			
	    	    		}    	    		
	    	    		writer.write(str + "\r\n");
	        		}
    			}
        	} catch (IOException e) {
        		
    			e.printStackTrace();
    		} finally {
    			writer.flush();
    			writer.close();
    			if (reader != null) reader.close();
    		}
    }
}
