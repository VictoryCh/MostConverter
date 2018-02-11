package most;
	
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import most.model.Form;
import most.model.FormListWrapper;
import most.model.RecMiss;
import most.model.Setting;
import most.model.SettingsListWrapper;
import most.view.RootLayoutController;

public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    
    private ObservableList<Setting> settingsData = FXCollections.observableArrayList();

    private ObservableMap<String, Form> forms = FXCollections.observableHashMap();

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Most Converter");

        initRootLayout();

//        showPersonOverview();
    }

    /**
     * Инициализирует корневой макет.
     */
    public void initRootLayout() {
        try {
            // Загружаем корневой макет из fxml файла.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Отображаем сцену, содержащую корневой макет.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
            
            RootLayoutController controller = loader.getController();
            controller.setMainApp(this);
            
            //Загружаем информацию о реквизитах из МИССИС
            File file = new File(System.getProperty("user.dir") + "/Dictionary/recmiss.xml");
            if (file != null) {
    			LoadFormsFromFile(file);
    		}
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Возвращает главную сцену.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public ObservableList<Setting> getSettingsData() {
		return settingsData;
	}

	public ObservableMap<String,Form> getFormsData() {
		return forms;
	}
	public static void main(String[] args) {
        launch(args);
    }
    
    
    public void LoadSettingsFromFile(File file) {
    	try {
			JAXBContext context = JAXBContext.newInstance(SettingsListWrapper.class);
			Unmarshaller um = context.createUnmarshaller();
			
			SettingsListWrapper wrapper = (SettingsListWrapper) um.unmarshal(file);
			
			settingsData.clear();
			settingsData.addAll(wrapper.getSettings());
			
			
		} catch (JAXBException e) {
			e.printStackTrace();
		}
    }
    
    public void LoadFormsFromFile(File file) {
    	JAXBContext context;
		try {
			context = JAXBContext.newInstance(FormListWrapper.class);
			Unmarshaller um = context.createUnmarshaller();
			
			FormListWrapper wrapper = (FormListWrapper) um.unmarshal(file);
			
			forms.clear();
			//формируем файл соответствия реквизитов
			wrapper.getForms().forEach((Form form) -> forms.put(form.form, form));
			
			
			
		} catch (JAXBException e) {
			e.printStackTrace();
		}

    }
}


































