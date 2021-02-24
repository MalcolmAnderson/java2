package controllers;

import javafx.application.Platform;
import javafx.scene.control.cell.PropertyValueFactory;

public class MainScreen_Controller {

    public void LoadInventory() {
        System.out.println("LoadInventory Doesn't Do Anything Yet.");
    }


//    public void LoadInventory(Inventory inv){
//        this.inv = inv;
//
//        partsTableView.setItems(inv.getAllParts());
//        partIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
//        partNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
//        partInventoryLevelColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
//        partPricePerUnitColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
//
//        productsTableView.setItems(inv.getAllProducts());
//        productIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
//        productNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
//        productInventoryLevelColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
//        productPricePerUnitColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
//    }

    public void OnButtonClicked_Exit(){
        Platform.exit();
    }

}
