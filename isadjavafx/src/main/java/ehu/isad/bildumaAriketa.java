package ehu.isad;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class bildumaAriketa extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Ikusi Bildumak");

        ComboBox comboBox = new ComboBox();
        ImageView imageView = new ImageView();


        //Bilduma a=new Bilduma("Hiria", List.of(
          //      new Argazkia("Bilbao","bilbao.jpg"),
            //    new Argazkia("Madrid","madrid.jpg"),
          //      new Argazkia("Barcelona","barcelona.jpg")));

        //Bilduma b=new Bilduma("Logos", List.of(
          //      new Argazkia("Android","android.jpg"),
            //    new Argazkia("Apple","apple.jpg"),
              //  new Argazkia("windows","windows.jpg")));

//        Bilduma c=new Bilduma("Eguraldia", List.of(
  //              new Argazkia("Eguzkia","eguzkia.jpg"),
    //            new Argazkia("Lainoak","lainoak.jpg"),
      //          new Argazkia("Euria","euria.jpg")));

        //comboBox.getItems().addAll(List.of(
          //      a,b,c
            //    ));

        List<String> bildumak =
                List.of("Abereak", "Landareak", "Frutak");

        ObservableList<Bilduma> bildumaList =
                FXCollections.observableArrayList();

        bildumak.forEach((elem) -> {
            bildumaList.add(new Bilduma(elem));
        });

        comboBox.setItems(bildumaList);

        Map<String, List<Argazkia>> bildumaMap = new HashMap<>();

        bildumaMap.put("Abereak", List.of(
                new Argazkia("Elefantea", "elefantea.jpg"),
                new Argazkia("Txakurra", "txakurra.jpg"),
                new Argazkia("Untxia", "untxia.jpeg")
        ));

        bildumaMap.put("Landareak", List.of(
                new Argazkia("Eguzkilorea", "eguzkilorea.jpeg"),
                new Argazkia("Larrosa", "larrosa.jpeg"),
                new Argazkia("Tulipa", "tulipa.png")
        ));

        bildumaMap.put("Frutak", List.of(
                new Argazkia("Madaria", "madaria.jpeg"),
                new Argazkia("Meloia", "meloia.jpeg"),
                new Argazkia("Sagarra", "sagarra.png")
        ));




        ObservableList<Argazkia> argazkiList =
                FXCollections.observableArrayList();



        ListView listViewOfArgazki = new ListView<>();

        comboBox.setEditable(false);

        comboBox.setOnAction(e -> {
            listViewOfArgazki.getItems().clear();
            Bilduma now=(Bilduma)comboBox.getValue();
            argazkiList.clear();
            argazkiList.addAll(bildumaMap.get(now.getIzena()));
            listViewOfArgazki.getItems().addAll(argazkiList);
        });

        comboBox.getSelectionModel().selectFirst();
        //listView.setOnMouseClicked(e -> {
          //  System.out.println(listView.getSelectionModel().getSelectedItem().toString());
        //});


        listViewOfArgazki.getSelectionModel().selectedItemProperty().addListener(  (observable, oldValue, newValue) -> {
            if (observable.getValue() == null) return;

           String fitx = ((Argazkia)observable.getValue()).getFitx();
           // System.out.println(fitx);

            try {
                imageView.setImage(lortuIrudia(fitx /* 48x48 */));
            } catch (IOException e) {
                e.printStackTrace();
            }

        });





        VBox vbox = new VBox(comboBox,listViewOfArgazki,imageView);
        Scene scene = new Scene(vbox, 200, 120);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private Image lortuIrudia(String fitx) throws IOException{
        InputStream is=getClass().getResourceAsStream("/" + fitx);
        //INPUT STREAM-a null da getResourcesAsStream metodoa resources karpetan es delako sartzen.
        //Interneten soluzioak bilatu ditut baina errorea ez da desagertzen.

        //if (is==null){
        //    System.out.println("wrong");
        //}


        BufferedImage reader = ImageIO.read(is);
        return SwingFXUtils.toFXImage(reader,null);
    }
}