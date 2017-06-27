package com.example;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class SearchGreendao {

    public static void main(String[]args){

        Schema schema = new Schema(1000,"com.example.administrator.koudaiwanzi.home.entity.greendao");
        addNote(schema);
        try {
            new DaoGenerator().generateAll(schema,"./app/src/main/java");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void addNote(Schema schema) {

        Entity entity = schema.addEntity("HistoryEntity");
        entity.addIdProperty().autoincrement().primaryKey();
        entity.addStringProperty("content");
        entity.addStringProperty("url");

    }

}
