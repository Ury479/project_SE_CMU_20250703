package se233.chapter1.view;

public class InventoryPane extends ScrollPane{
    private BasedEquipment[] equipmentArray;
    public InventoryPane(){}
    private Pane getDetailsPane(){
        Pane inventoryInforPane = new HBox(10);
         inventoryInfoPane.setBorder(null);
         inventoryInfoPane.setPadding(new Insert(25,25,25,25));
         if(equipmentArray != null){
            ImageView[] imageViewList = new ImageView[equipmentArray.length];
            for(int i = 0,i < equipmentArray.length;i++){
                imageViewList[i] = new imageViewList();
                imageViewList[i].setImage(new Image(Launcher.class.getResource(equipmentArray[i].getImagepath()).toString()))
            }
            inventoryInforPane.getChildren().addAll(imageViewList);
         }
         return inventoryInforPane;
    }
    public void drawPane(BasedEquipment[] equipmentArray){
        this.equipmemntArray = equipmentArray;
        Pane inventoryInfo = getDetailsPane();
        this.setStyle(("-fx-background-color:Red;");
        this.setContent(inventoryInfor);
    }
}
