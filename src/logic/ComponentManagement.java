//Emre
package logic;


import java.sql.SQLException;
import java.util.List;

import DataAccess.SQLManager;

public class ComponentManagement {
    
    public static void addCategory(String name, String note) throws SQLException {
	SQLManager.getInstance().addCategoryToDB(name, note); 
		}
    public static void deleteCategory(int id) throws SQLException{
        SQLManager.getInstance().deleteCategoryFromDB(id); 
    }
    public static void modifyCategory(int id, String newName, String newNote) throws SQLException {
	SQLManager.getInstance().modifyCategory(id, newName, newNote);    
    }
    
    public static Category[] getCategoryArray() throws SQLException {
	return SQLManager.getInstance().getCategoryArray(); 
    }
    
    public static List<Category> getCategories() throws SQLException {
   	return SQLManager.getInstance().getCategories(); 
       }
    
		
    public static void addPart(String articlenumber, String productlink, String name, double price, int storing, int plannedAmount, int orderedAmount, String storageLocation, int idCategory) throws SQLException {
	SQLManager.getInstance().addPartIntoDB(articlenumber, productlink, name, price, storing, plannedAmount, orderedAmount, storageLocation, idCategory);
    }
    public static List<Component> getComponents() throws SQLException {
	return SQLManager.getInstance().getComponents(); 
    }
    public static List<Component> getComponentByArticlenumber(String articlenumber) throws SQLException {
	return SQLManager.getInstance().getComponentsByArticlenumber(articlenumber); 
    }
    public static List<Component> getComponentByName(String name) throws SQLException {
	return SQLManager.getInstance().getComponentsByName(name); 
    }
    public static void deletePart(int id) throws SQLException {
	SQLManager.getInstance().deletePartFromDB(id); 
    }
    public static void modifyPart(int id, String articlenumber, String productlink, String name, double price, int storing, 
	    int plannedAmount, int orderedAmount, String storageLocation, int idCategory) throws SQLException {
		SQLManager.getInstance().modifyPart(id, articlenumber, productlink, name, price, storing, plannedAmount, orderedAmount, storageLocation, idCategory);
    }
		
		
		
}
