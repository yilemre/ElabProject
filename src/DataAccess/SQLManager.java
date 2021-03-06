//Mainly everything by Marius
package DataAccess;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import logic.*;
import Exceptions.*;


public class SQLManager {

	private static SQLManager instance;
	private Connection c;

	public static SQLManager getInstance(){
		if (instance==null) instance = new SQLManager();
		return instance;
	}

	private SQLManager (){

		try{
			Class.forName("org.sqlite.JDBC");
			c= DriverManager.getConnection("jdbc:sqlite:ELab.db");
		}

		catch (Exception e){
			e.printStackTrace();
		}
	}

	//rights: 0 is a customer, 1 a member and 2 a "Lehrstuhl bez. Person" so 0 and 2 should only see ComponentManagement and 1 everything
	/**
	 * @param firstname
	 * @param surname
	 * @param street
	 * @param housenumber
	 * @param zipcode
	 * @param email
	 * @param datetime
	 * @param username
	 * @param password
	 * @param rights
	 * @return
	 * @throws SQLException
	 */
	public int insertPersonIntoDB (String firstname, String surname, String street, int housenumber, int zipcode, String email, String datetime, String username, String password, int rights) throws SQLException{
		int result=0;
		Statement stmt = c.createStatement();
		String sql ="INSERT INTO Persons (firstname, surname, street, housenumber, zipcode, email, timestamp, username, password, rights) VALUES ('"+firstname+"','"+surname+"','"+street+"',"+housenumber+","+zipcode+",'"+email+"','"+datetime+"','"+username+"','"+password+"',"+rights+");";
		stmt.executeUpdate(sql);
		ResultSet rs = stmt.executeQuery("SELECT last_insert_rowid() FROM Persons");
		rs.next();
		result = rs.getInt(1);
		rs.close();
		stmt.close();	
		return result;
	}
	
	/**
	 * @param username
	 * @return
	 * @throws SQLException
	 */
	public boolean isUsernameAvailable(String username) throws SQLException{
		boolean result = true;
		Statement stmt = c.createStatement();
		String sql = "SELECT * FROM Persons WHERE username='"+username+"';";
		ResultSet rs = stmt.executeQuery(sql);
		if(rs.next()) {
			result = false;
		}
		else {
			result = true;
		}
		return result;
	}

	/**
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public int deletePersonFromDB(int id) throws SQLException{
		Statement stmt = c.createStatement();
		String sql ="DELETE FROM Persons WHERE idPerson="+id+"";
		stmt.executeUpdate(sql);
		stmt.close();	
		return id;
	}
	
	/**
	 * @param idCustomer
	 * @return
	 * @throws SQLException
	 */
	public List<Integer> getBillIDsByCustomerID(int idCustomer) throws SQLException{
		List<Integer> result = new ArrayList<Integer>();
		Statement stmt = c.createStatement();
		String sql = "SELECT idBill FROM Bills WHERE idCustomer="+idCustomer;
		ResultSet rs = stmt.executeQuery(sql);
		while(rs.next()) {
			result.add(rs.getInt(1));
		}
		return result;
	}
	
	/**
	 * @param idCustomer
	 * @return
	 * @throws SQLException
	 */
	public List<Integer> getOrderIDsByCustomerID(int idCustomer) throws SQLException{
		List<Integer> result = new ArrayList<Integer>();
		Statement stmt = c.createStatement();
		String sql = "SELECT idOrder FROM Orders WHERE idCustomer="+idCustomer;
		ResultSet rs = stmt.executeQuery(sql);
		while(rs.next()) {
			result.add(rs.getInt(1));
		}
		return result;
	}
	
	/**
	 * @param idAdvisor
	 * @return
	 * @throws SQLException
	 */
	public List<Integer> getOrderIDsAdvisorID(int idAdvisor) throws SQLException{
		List<Integer> result = new ArrayList<Integer>();
		Statement stmt = c.createStatement();
		String sql = "SELECT idOrder FROM Orders WHERE idAdvisor="+idAdvisor;
		ResultSet rs = stmt.executeQuery(sql);
		while(rs.next()) {
			result.add(rs.getInt(1));
		}
		return result;
	}
	
	/**
	 * @param idAdvisor
	 * @return
	 * @throws SQLException
	 */
	public List<Integer> getBillIDsByAdvisorID(int idAdvisor) throws SQLException{
		List<Integer> result = new ArrayList<Integer>();
		Statement stmt = c.createStatement();
		String sql = "SELECT idBill FROM Bills WHERE idAdvisor="+idAdvisor;
		ResultSet rs = stmt.executeQuery(sql);
		while(rs.next()) {
			result.add(rs.getInt(1));
		}
		return result;
	}
	
	/**
	 * @param idSecondaryAdvisor
	 * @return
	 * @throws SQLException
	 */
	public List<Integer> getOrderIDsSecondaryAdvisorID(int idSecondaryAdvisor) throws SQLException{
		List<Integer> result = new ArrayList<Integer>();
		Statement stmt = c.createStatement();
		String sql = "SELECT idOrder FROM Orders WHERE idSecondaryAdvisor="+idSecondaryAdvisor;
		ResultSet rs = stmt.executeQuery(sql);
		while(rs.next()) {
			result.add(rs.getInt(1));
		}
		return result;
	}


	//Emre begin 
	/**
	 * @param id
	 * @param firstname
	 * @param surname
	 * @param street
	 * @param housenumber
	 * @param zipcode
	 * @param email
	 * @param t
	 * @param username
	 * @param password
	 * @param rights
	 * @throws SQLException
	 */
	public void modifyPerson(int id, String firstname, String surname, String street, int housenumber,
			int zipcode,String email, String t, String username, String password, int rights) throws SQLException {
		Statement stmt = c.createStatement(); 
		String sql = "UPDATE Persons SET firstname='"+firstname+"',surname='"+surname+"',street ='"+street+"', housenumber="+housenumber+", zipcode="+zipcode+", email='"+email+"',timestamp='"+t+"', username='"+username+"', password='"+password+"', rights="+rights+" WHERE idPerson="+id+";";
		stmt.executeUpdate(sql); 
		stmt.close();

	}
	
	/**
	 * @param id
	 * @return
	 * @throws SQLException
	 * @throws PersonWithSpecifiedIDNotInDBException
	 */
	public Person getPersonByID(int id) throws SQLException, PersonWithSpecifiedIDNotInDBException {
		Person result=null;
	    Statement stmt = c.createStatement(); 
	    String sql = "SELECT * FROM Persons WHERE idPerson="+id+";"; 
		ResultSet rs = stmt.executeQuery(sql);
		if (rs.next()) {
			result = new Person (rs.getInt("idPerson"),rs.getString("firstname"),rs.getString("surname"),rs.getString("street"),rs.getInt("housenumber"),rs.getInt("zipcode"),rs.getString("email"),rs.getString("timestamp"),rs.getString("username"),rs.getString("password"),rs.getInt("rights"));
			return result;
		}
		else {
			throw new PersonWithSpecifiedIDNotInDBException();
		}
		
	}
	
	/**
	 * @param username
	 * @param password
	 * @throws SQLException
	 */
	public void changePassword(String username, String password) throws SQLException {
	    Statement statement = c.createStatement(); 
	    String sql = "UPDATE Persons SET password= '"+password + "' WHERE username ='"+ username+"'; "; 
	    statement.executeUpdate(sql); 
	    statement.close();
	    
	}
	/**
	 * @param username
	 * @return
	 * @throws SQLException
	 */
	public int getPersonIDByUsername(String username) throws SQLException {
	    Statement stmt = c.createStatement(); 
	    String sql = "SELECT idPerson FROM Persons WHERE username='"+ username+"'"; 
	    return stmt.executeQuery(sql).getInt(1); 
	}
	
	/**
	 * @param personID
	 * @param partID
	 * @param amount
	 * @throws SQLException
	 */
	public void addToShoppingCard(int personID, int partID, int amount) throws SQLException {
	    Statement stmt = c.createStatement(); 
	    String sql = "INSERT INTO ShoppingCardParts (idPerson, idPart, amount) VALUES " + personID + ", " + partID + ", " + amount ; 
	    stmt.executeUpdate(sql); 
	    stmt.close();
	}
	
	//Emre end

	/**
	 * @return
	 * @throws SQLException
	 */
	public List<Person> getPersons() throws SQLException {
		List<Person> result = new ArrayList<Person>();

		Statement stmt = c.createStatement();
		String sql = "SELECT * FROM Persons";
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()){
			Person temp = new Person (rs.getInt("idPerson"),rs.getString("firstname"),rs.getString("surname"),rs.getString("street"),rs.getInt("housenumber"),rs.getInt("zipcode"),rs.getString("email"),rs.getString("timestamp"),rs.getString("username"),rs.getString("password"),rs.getInt("rights"));

			result.add(temp);			
		}

		return result;
	}
	
	/**
	 * @param firstname
	 * @return
	 * @throws SQLException
	 * @throws FirstnameNotInDBException
	 */
	public List<Person> getPersonsByFirstname(String firstname) throws SQLException, FirstnameNotInDBException {
		List<Person> result = new ArrayList<Person>();
		Statement stmt = c.createStatement();
		String sql = "SELECT * FROM Persons WHERE firstname LIKE'"+firstname+"'";
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()){
			Person temp = new Person (rs.getInt("idPerson"),rs.getString("firstname"),rs.getString("surname"),rs.getString("street"),rs.getInt("housenumber"),rs.getInt("zipcode"),rs.getString("email"),rs.getString("timestamp"),rs.getString("username"),rs.getString("password"),rs.getInt("rights"));

			result.add(temp);			
		}
		if (result.isEmpty()) throw new FirstnameNotInDBException();
		return result;
	}
	
	/**
	 * @param surname
	 * @return
	 * @throws SQLException
	 * @throws LastnameNotInDBException
	 */
	public List<Person> getPersonsByLastname(String surname) throws SQLException, LastnameNotInDBException {
		List<Person> result = new ArrayList<Person>();

		Statement stmt = c.createStatement();
		String sql = "SELECT * FROM Persons WHERE surname LIKE'"+surname+"'";
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()){
			Person temp = new Person (rs.getInt("idPerson"),rs.getString("firstname"),rs.getString("surname"),rs.getString("street"),rs.getInt("housenumber"),rs.getInt("zipcode"),rs.getString("email"),rs.getString("timestamp"),rs.getString("username"),rs.getString("password"),rs.getInt("rights"));

			result.add(temp);			
		}
		if (result.isEmpty()) throw new LastnameNotInDBException();
		return result;
	}
	
	//Maybe you could consider throwing an ambiguousPersonException but that can't happen
	/**
	 * @param username
	 * @return
	 * @throws SQLException
	 * @throws UsernameNotInDBException
	 */
	public Person getPersonByUsername(String username) throws SQLException, UsernameNotInDBException {
		Person result;
		Statement stmt = c.createStatement();
		String sql = "SELECT * FROM Persons WHERE username LIKE'"+username+"'";
		ResultSet rs = stmt.executeQuery(sql);
		if (rs.next()) {
			result = new Person (rs.getInt("idPerson"),rs.getString("firstname"),rs.getString("surname"),rs.getString("street"),rs.getInt("housenumber"),rs.getInt("zipcode"),rs.getString("email"),rs.getString("timestamp"),rs.getString("username"),rs.getString("password"),rs.getInt("rights"));
			return result;
		}
		else {
			throw new UsernameNotInDBException();
		}
		
	}
	
	/**
	 * @param rights
	 * @return
	 * @throws SQLException
	 * @throws PersonStatusNotInDBException
	 */
	public List<Person> getPersonsByRights(int rights) throws SQLException, PersonStatusNotInDBException {
		List<Person> result = new ArrayList<Person>();
		Statement stmt = c.createStatement();
		String sql = "SELECT * FROM Persons WHERE rights="+rights+"";
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()){
			Person temp = new Person (rs.getInt("idPerson"),rs.getString("firstname"),rs.getString("surname"),rs.getString("street"),rs.getInt("housenumber"),rs.getInt("zipcode"),rs.getString("email"),rs.getString("timestamp"),rs.getString("username"),rs.getString("password"),rs.getInt("rights"));

			result.add(temp);			
		}
		if (result.isEmpty()) throw new PersonStatusNotInDBException();
		return result;
	}

	/**
	 * @param name
	 * @param note
	 * @return
	 * @throws SQLException
	 */
	public int addCategoryToDB(String name, String note) throws SQLException {
		int result=0;
		Statement stmt = c.createStatement();
		String sql ="INSERT INTO Categorys (name, note) VALUES ('"+name+"','"+note+"');";
		stmt.executeUpdate(sql);
		ResultSet rs = stmt.executeQuery("SELECT last_insert_rowid() FROM Categorys");
		rs.next();
		result = rs.getInt(1);
		rs.close();
		stmt.close();	
		return result;
	}

	/**
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public int deleteCategoryFromDB(int id) throws SQLException {
		Statement stmt = c.createStatement();
		String sql ="DELETE FROM Categorys WHERE idCategory="+id+";";
		stmt.executeUpdate(sql);
		stmt.close();	
		return id;
	}
	//Emre begin 
	/**
	 * @param id
	 * @param newName
	 * @param newNote
	 * @throws SQLException
	 */
	public void modifyCategory(int id, String newName, String newNote) throws SQLException {
	    Statement stmt = c.createStatement(); 
	    stmt.executeUpdate("UPDATE Categorys SET name='"+ newName+"' , note='"+ newNote+"' WHERE idCategory ="+id+";");
	    stmt.close();
		}
	
	/**
	 * @return
	 * @throws SQLException
	 * @throws CategoryNotInDBException
	 */
	public List<Category> getCategories () throws SQLException, CategoryNotInDBException {
	    List<Category> category = new ArrayList<Category>();
	    Statement stmt = c.createStatement(); 
	    ResultSet rs = stmt.executeQuery("SELECT * FROM Categorys"); 
	    while(rs.next()) {
		Category temp = new Category(rs.getInt("idCategory"), rs.getString("name"), rs.getString("note")); 
		category.add(temp);
	    }
	    return category; 
	}
	
	/**
	 * @return
	 * @throws SQLException
	 */
	public Category[] getCategoryArray () throws SQLException {
	    int count=0; 
	    Statement stmt = c.createStatement(); 
	    ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM Categorys"); 
	    rs.next(); 
	    count = rs.getInt(1); 
	    stmt.close();
	    Category[] c = new Category[count]; 
	    rs = stmt.executeQuery("SELECT * FROM Categorys"); 
	    int x = 0;
	    while(rs.next()) {
		Category a = new Category(rs.getInt("idCategory"), rs.getString("name"), rs.getString("note")); 
		c[x] = a; 
		x+=1; 
	    }
	    return c;
	    
	}
	//Emre end 

	/**
	 * @param articlenumber
	 * @param productlink
	 * @param name
	 * @param price
	 * @param storing
	 * @param plannedAmount
	 * @param orderedAmount
	 * @param storageLocation
	 * @param category
	 * @return
	 * @throws SQLException
	 */
	public int addPartIntoDB(String articlenumber, String productlink, String name, double price, int storing, int plannedAmount, int orderedAmount, String storageLocation, int category) throws SQLException {
		int result=0;
		Statement stmt = c.createStatement();
		String sql ="INSERT INTO Parts (articlenumber, productlink, name, price, storing, plannedAmount, orderedAmount, storageLocation, idCategory) VALUES ('"+articlenumber+"','"+productlink+"','"+name+"',"+price+","+storing+","+plannedAmount+","+orderedAmount+",'"+storageLocation+"',"+ category+")";
		stmt.executeUpdate(sql);
		ResultSet rs = stmt.executeQuery("SELECT last_insert_rowid() FROM Parts");
		rs.next();
		result = rs.getInt(1);
		rs.close();
		stmt.close();	
		return result;
	}

	/**
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public int deletePartFromDB(int id) throws SQLException{
		Statement stmt = c.createStatement();
		String sql ="DELETE FROM Parts WHERE idPart="+id+";";
		stmt.executeUpdate(sql);
		stmt.close();	
		return id;
	}
	//Emre begin 
	/**
	 * @param id
	 * @param articlenumber
	 * @param productlink
	 * @param name
	 * @param price
	 * @param storing
	 * @param plannedAmount
	 * @param orderedAmount
	 * @param storageLocation
	 * @param idCategory
	 * @throws SQLException
	 */
	public void modifyPart (int id, String articlenumber, String productlink, String name, double price, int storing, 
		int plannedAmount, int orderedAmount, String storageLocation, int idCategory) throws SQLException {
	    	Statement stmt = c.createStatement(); 
	    	stmt.executeUpdate("UPDATE Parts SET articlenumber = '"+ articlenumber+"', productlink='"+productlink+"',name='"+name+"', price="+
	    	price+", storing="+ storing+", plannedAmount="+ plannedAmount+", orderedAmount="+ orderedAmount+",storageLocation='"+storageLocation+"',idCategory="+idCategory+" WHERE idPart= "+ id);
    		stmt.close();
	} 
	
	/**
	 * @return
	 * @throws SQLException
	 */
	public List<Component> getComponents() throws SQLException {
	    List<Component> result = new ArrayList<Component>(); 
	    Statement stmt = c.createStatement(); 
	    ResultSet rs = stmt.executeQuery("SELECT * FROM Parts");
	    
	    while (rs.next()) {
		Component c = new Component(rs.getInt("idPart"),rs.getString("articlenumber"), rs.getString("name"), rs.getString("productlink"), rs.getDouble("price"), rs.getInt("storing"), rs.getInt("plannedAmount"), rs.getInt("orderedAmount"), rs.getString("storageLocation"),rs.getInt("idCategory"));
		result.add(c); 
	    }
	    return result; 
	}
	
	
	/**
	 * @param articlenumber
	 * @return
	 * @throws SQLException
	 * @throws ArticlenumberNotInDBException
	 */
	public List<Component> getComponentsByArticlenumber(String articlenumber) throws SQLException, ArticlenumberNotInDBException {
	    List<Component> result = new ArrayList<Component>(); 
	    Statement stmt = c.createStatement(); 
	    ResultSet rs = stmt.executeQuery("SELECT * FROM Parts WHERE articlenumber LIKE '%" + articlenumber + "%';"); 
	    
	    while (rs.next()) {
		Component c = new Component(rs.getInt("idPart"),rs.getString("articlenumber"), rs.getString("name"), rs.getString("productlink"), rs.getDouble("price"), rs.getInt("storing"), rs.getInt("plannedAmount"), rs.getInt("orderedAmount"), rs.getString("storageLocation"),rs.getInt("idCategory"));
		result.add(c); 
	    }
	    if (result.isEmpty()) throw new ArticlenumberNotInDBException();
	    return result; 
	}
	
	/**
	 * @param name
	 * @return
	 * @throws SQLException
	 * @throws ComponentNameNotInDBException
	 */
	public List<Component> getComponentsByName(String name) throws SQLException, ComponentNameNotInDBException {
	    List<Component> result = new ArrayList<Component>(); 
	    Statement stmt = c.createStatement(); 
	    ResultSet rs = stmt.executeQuery("SELECT * FROM Parts WHERE name LIKE '%" + name+ "%';"); 
	    
	    while (rs.next()) {
		Component c = new Component(rs.getInt("idPart"),rs.getString("articlenumber"), rs.getString("name"), rs.getString("productlink"), rs.getDouble("price"), rs.getInt("storing"), rs.getInt("plannedAmount"), rs.getInt("orderedAmount"), rs.getString("storageLocation"),rs.getInt("idCategory"));
		result.add(c); 
	    }
	    //if (result.isEmpty()) throw new ComponentNameNotInDBException();
	    return result; 
	}
	
	/**
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public List<ShoppingObject> getPartsByShoppingCard(int id) throws SQLException {
	    List<ShoppingObject> result = new ArrayList<ShoppingObject>(); 
	    Statement stmt = c.createStatement(); 
	    String sql = "SELECT idPart, articlenumber, name, amount, price FROM Parts NATURAL JOIN ShoppingCardParts WHERE idPerson ="+ id; 
	    ResultSet rs = stmt.executeQuery(sql); 
	    while (rs.next()) {
		ShoppingObject temp = new ShoppingObject(rs.getInt("idPart"), rs.getString("articlenumber"), rs.getString("name"),rs.getInt("amount"), rs.getDouble("price") );
		result.add(temp); 
	    }
	    return result; 
	}
	
	/**
	 * @param idPart
	 * @param minusValue
	 * @throws SQLException
	 */
	public void updatePartQuantityAfterShoppingMinus(int idPart, int minusValue) throws SQLException {
	    Statement stmt = c.createStatement(); 
	    String sql ="UPDATE Parts SET storing = storing -"+ minusValue + " WHERE idPart="+ idPart;
	    stmt.executeUpdate(sql); 
	    stmt.close();
	}
	
	/**
	 * @param idPart
	 * @param plusValue
	 * @throws SQLException
	 */
	public void updatePartQuantityAfterShoppingPlus(int idPart, int plusValue) throws SQLException {
	    Statement stmt = c.createStatement(); 
	    String sql ="UPDATE Parts SET storing = storing +"+ plusValue + " WHERE idPart="+ idPart;
	    stmt.executeUpdate(sql); 
	    stmt.close();
	}
	
	//You need to check whether or not there are already parts with ID x for Person Y in Card! If so: Increase amount and don't add new row!
	/**
	 * @param idPart
	 * @param idPerson
	 * @param amount
	 * @throws SQLException
	 */
	public void addPartToShoppingCard(int idPart, int idPerson, int amount) throws SQLException {
		Statement stmt = c.createStatement();
		String sql = "SELECT COUNT(*) FROM ShoppingCardParts WHERE idPerson ="+ idPerson +  " AND idPart =" + idPart; 
		//String sql ="INSERT INTO ShoppingCardParts (idPerson, idPart, amount) VALUES((SELECT idPerson FROM Persons WHERE idPerson="+idPerson+"), (SELECT idPart FROM Parts WHERE idPart="+idPart+"),"+amount+");";
		//stmt.executeUpdate(sql); 
		ResultSet rs = stmt.executeQuery(sql);
		if (rs.getInt(1)== 1) {
		    sql = "UPDATE ShoppingCardParts SET amount = amount + "+ amount + " WHERE idPart="+ idPart+ " AND idPerson ="+ idPerson; 
		    stmt.executeUpdate(sql); 
		} else {
		    sql = "INSERT INTO ShoppingCardParts (idPerson, idPart, amount) VALUES (" + idPerson + ", "+ idPart+ ", " + amount+ ")";
		    stmt.executeUpdate(sql); 
		}
		stmt.close(); 
	}
	
	/**
	 * @param idPart
	 * @param amount
	 * @return
	 * @throws SQLException
	 * @throws noMorePartsLeftException
	 */
	public boolean checkIfEnoughPartsAreAvailable(int idPart, int amount) throws SQLException, noMorePartsLeftException {
		boolean result = false;
		Statement stmt = c.createStatement();
		String sql = "SELECT * FROM Parts WHERE storing>="+amount+" AND idPart="+idPart;
		ResultSet rs = stmt.executeQuery(sql);
		if (rs.next()){
			result = true;			
		}
		else {
			result = false;
		}
		return result;
	}
	
	/**
	 * @param idPart
	 * @param idPerson
	 * @param minusValue
	 * @throws SQLException
	 */
	public void updateShoppingCardPartMinus(int idPart, int idPerson, int minusValue) throws SQLException {
	    Statement stmt = c.createStatement();
	    stmt.executeUpdate("UPDATE ShoppingCardParts SET amount = amount - "+ minusValue + " WHERE idPart="+ idPart +" AND idPerson=" +idPerson); 
	    ResultSet rs = stmt.executeQuery("SELECT amount FROM ShoppingCardParts WHERE idPart ="+ idPart+ " AND idPerson = "+ idPerson); 
	    if (rs.getInt(1) == 0) {
		stmt.executeUpdate("DELETE FROM ShoppingCardParts WHERE idPart = "+idPart+ " AND idPerson="+ idPerson); 
	    }
	    
	}
	/**
	 * @param idPart
	 * @param idPerson
	 * @throws SQLException
	 */
	public void payPartFromShoppingCard(int idPart, int idPerson) throws SQLException {
	    Statement stmt = c.createStatement(); 
	    stmt.executeUpdate("DELETE FROM ShoppingCardParts WHERE idPart = "+ idPart + " AND idPerson = " + idPerson); 
	    stmt.close();
	}
	
	//Emre end

	/**
	 * @param idPart
	 * @param idPerson
	 * @throws SQLException
	 */
	public void deletePartFromShoppingCard(int idPart, int idPerson) throws SQLException{
		Statement stmt = c.createStatement();
		String sql="DELETE FROM ShoppingCardParts WHERE idPart="+idPart+" AND idPerson="+idPerson+";";
		stmt.executeUpdate(sql);
		stmt.close();
	}



//Nico begin reworked by Marius

	//Nico begin	

	/*type: 0 for "3D-Prints", 1 for "circuit board", 2 for "other"
	every order is going to be inserted with the status 0 which is "accepted"!
	Values for all the other status:
		0 accepted
		1 made
		2 costs calculated
		3 picked up
		4 billed
		5 waiting for material
		6 production interrupted
		7 bill generated
	*/
	/**
	 * @param title
	 * @param type
	 * @param projectedCosts
	 * @param realCosts
	 * @param idCustomer
	 * @param idAdvisor
	 * @param idSecondaryAdvisor
	 * @param fileName
	 * @param fileLocation
	 * @param note
	 * @return
	 * @throws SQLException
	 */
	public int insertOrderIntoDB (String title, int type, double projectedCosts, double realCosts, int idCustomer, int idAdvisor, int idSecondaryAdvisor, String fileName, String fileLocation, String note) throws SQLException{
		int result=0;
		Statement stmt = c.createStatement();
		String sql ="INSERT INTO Orders (titel, type, projectedCosts, realCosts, idCustomer, idAdvisor, idSecondaryAdvisor, fileName, fileLocation, note) VALUES ('"+title+"', "+type+", "+projectedCosts+","+realCosts+",(SELECT idPerson FROM Persons WHERE idPerson="+idCustomer+"), (SELECT idPerson FROM Persons WHERE idPerson="+idAdvisor+"), (SELECT idPerson FROM Persons WHERE idPerson="+idSecondaryAdvisor+"), '"+fileName+"','"+fileLocation+"', '"+note+"');";
		stmt.executeUpdate(sql);
		ResultSet rs = stmt.executeQuery("SELECT last_insert_rowid() FROM Orders");
		rs.next();
		result = rs.getInt(1);
		rs.close();
		stmt.close();
		return result;
	}
	
	/**
	 * @param idOrder
	 * @param status
	 * @param datetime
	 * @return
	 * @throws SQLException
	 */
	public int insertOrderStatusIntoDB(int idOrder, int status, String datetime) throws SQLException {
		int result = 0;
		Statement stmt = c.createStatement();
		String sql ="INSERT INTO OrderStatus (idOrder, status, timestamp) VALUES ("+idOrder+ ", "+status+", '"+datetime+"');";
		stmt.executeUpdate(sql);
		ResultSet rs = stmt.executeQuery("SELECT last_insert_rowid() FROM OrderStatus");
		rs.next();
		result = rs.getInt(1);
		rs.close();
		stmt.close();	
		return result;
	}

	/**
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public int deleteOrderFromDB(int id) throws SQLException{
		Statement stmt = c.createStatement();
		String sql ="DELETE FROM Orders WHERE idOrder="+id;
		stmt.executeUpdate(sql);
		stmt.close();
		return id;
	}
	
	/**
	 * @param idOrder
	 * @return
	 * @throws SQLException
	 */
	public boolean isBillCreatedForOrder(int idOrder) throws SQLException{
		boolean result = false;
		Statement stmt = c.createStatement();
		String sql = "SELECT idBill FROM Bills WHERE idOrder="+idOrder;
		ResultSet rs = stmt.executeQuery(sql);
		if(rs.next()) {
			result = true;
		}
		else {
			result = false;
		}
		return result;
	}

	/**
	 * @param id
	 * @param title
	 * @param type
	 * @param projectedCosts
	 * @param realCosts
	 * @param idCustomer
	 * @param idAdvisor
	 * @param idSecondaryAdvisor
	 * @param fileName
	 * @param fileLocation
	 * @param note
	 * @throws SQLException
	 */
	public void modifyOrder(int id, String title, int type, double projectedCosts, double realCosts, int idCustomer, int idAdvisor, int idSecondaryAdvisor, String fileName, String fileLocation, String note) throws SQLException {
		Statement stmt = c.createStatement();
		String sql ="UPDATE Orders SET titel='"+title+"',type="+type+",projectedCosts="+projectedCosts+" ,realCosts="+realCosts+" ,idCustomer="+idCustomer+" ,idAdvisor="+idAdvisor+" ,idSecondaryAdvisor ="+idSecondaryAdvisor+",fileName='"+fileName+"',fileLocation='"+fileLocation+"',note='"+note+"' WHERE idOrder="+id+";";
		stmt.executeUpdate(sql);
		stmt.close();
	}
	
	//i think we dont need this method 'cause we don't actually overwrite a row in this table!
	/**
	 * @param id
	 * @param status
	 * @throws SQLException
	 */
	public void changeOrderStatus(int id, int status) throws SQLException{
		Statement stmt = c.createStatement(); 
		String sql = "UPDATE OrderStatus SET status='"+status+"' WHERE idOrder="+id;
		stmt.executeUpdate(sql); 
		stmt.close();
	}
	//Emre+
	/**
	 * @param id
	 * @return
	 * @throws SQLException
	 * @throws OrderNotInDBException
	 */
	public Order getOrderByID(int id) throws SQLException, OrderNotInDBException {
	    Statement stmt= c.createStatement(); 
	    Order result=null;
	    String sql = "SELECT idOrder, titel, type, projectedCosts, realCosts, idCustomer, idAdvisor, idSecondaryAdvisor, fileName, fileLocation, note, MAX(status) as status, timestamp FROM Orders NATURAL JOIN OrderStatus GROUP BY idOrder HAVING idOrder="+id+";";
	    ResultSet rs= stmt.executeQuery(sql);
	    if(rs.next()) {
	    	result = new Order (rs.getInt("idOrder"),rs.getString("titel"),rs.getInt("type"),rs.getDouble("projectedCosts"),rs.getDouble("realCosts"),rs.getInt("idCustomer"),rs.getInt("idAdvisor"),rs.getInt("idSecondaryAdvisor"),rs.getString("fileName"),rs.getString("fileLocation"),rs.getString("note"),rs.getInt("status"), rs.getString("timestamp"));
	    }
	    else {
	    	throw new OrderNotInDBException();
	    }
	    return result;
	}
	//Emre- 

	// search methods
	/**
	 * @param titel
	 * @return
	 * @throws SQLException
	 * @throws OrderTitleNotInDBException
	 */
	public List<Order> getOrdersByTitle(String titel) throws SQLException, OrderTitleNotInDBException {
		List<Order> result = new ArrayList<Order>();
		Statement stmt = c.createStatement();
		String sql = "SELECT idOrder, titel, type, projectedCosts, realCosts, idCustomer, idAdvisor, idSecondaryAdvisor, fileName, fileLocation, note, MAX(status) as status, timestamp FROM Orders NATURAL JOIN OrderStatus GROUP BY idOrder HAVING titel LIKE'%"+titel+"%';";
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()){
			Order temp = new Order (rs.getInt("idOrder"),rs.getString("titel"),rs.getInt("type"),rs.getDouble("projectedCosts"),rs.getDouble("realCosts"),rs.getInt("idCustomer"),rs.getInt("idAdvisor"),rs.getInt("idSecondaryAdvisor"),rs.getString("fileName"),rs.getString("fileLocation"),rs.getString("note"),rs.getInt("status"), rs.getString("timestamp"));
			result.add(temp);			
		}
		
		rs.close();
		stmt.close();
		if (result.isEmpty()) throw new OrderTitleNotInDBException();
		return result;
	}

	/**
	 * @param type
	 * @return
	 * @throws SQLException
	 * @throws OrderTypeNotInDBException
	 */
	public List<Order> getOrdersByType(int type) throws SQLException, OrderTypeNotInDBException {
		List<Order> result = new ArrayList<Order>();
		Statement stmt = c.createStatement();
		String sql = "SELECT idOrder, titel, type, projectedCosts, realCosts, idCustomer, idAdvisor, idSecondaryAdvisor, fileName, fileLocation, note, MAX(status) as status, timestamp FROM Orders NATURAL JOIN OrderStatus GROUP BY idOrder HAVING type="+type+";";
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()){
			Order temp = new Order (rs.getInt("idOrder"),rs.getString("titel"),rs.getInt("type"),rs.getDouble("projectedCosts"),rs.getDouble("realCosts"),rs.getInt("idCustomer"),rs.getInt("idAdvisor"),rs.getInt("idSecondaryAdvisor"),rs.getString("fileName"),rs.getString("fileLocation"),rs.getString("note"),rs.getInt("status"), rs.getString("timestamp"));
			result.add(temp);
		}

		rs.close();
		stmt.close();
		if (result.isEmpty()) throw new OrderTypeNotInDBException();
		return result;
	}
	
	/**
	 * @return
	 * @throws SQLException
	 */
	public List<Order> getOrders() throws SQLException {
		List<Order> result = new ArrayList<Order>();
		Statement stmt = c.createStatement();
		String sql = "SELECT idOrder, titel, type, projectedCosts, realCosts, idCustomer, idAdvisor, idSecondaryAdvisor, fileName, fileLocation, note, MAX(status) as status, timestamp FROM Orders NATURAL JOIN OrderStatus GROUP BY idOrder";
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()){
			Order temp = new Order (rs.getInt("idOrder"),rs.getString("titel"),rs.getInt("type"),rs.getDouble("projectedCosts"),rs.getDouble("realCosts"),rs.getInt("idCustomer"),rs.getInt("idAdvisor"),rs.getInt("idSecondaryAdvisor"),rs.getString("fileName"),rs.getString("fileLocation"),rs.getString("note"), rs.getInt("status"), rs.getString("timestamp"));

			result.add(temp);			
		}

		return result;
	}
	
	/**
	 * @return
	 * @throws SQLException
	 */
	public List<Order> getOrdersWhereBillisNotCreatedYet() throws SQLException {
		List<Order> result = new ArrayList<Order>();
		Statement stmt = c.createStatement();
		String sql = "SELECT * FROM (SELECT idOrder, titel, type, projectedCosts, realCosts, idCustomer, idAdvisor, idSecondaryAdvisor, fileName, fileLocation, note, MAX(status) as status, timestamp FROM Orders NATURAL JOIN OrderStatus GROUP BY idOrder) WHERE status!=7";
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()){
			Order temp = new Order (rs.getInt("idOrder"),rs.getString("titel"),rs.getInt("type"),rs.getDouble("projectedCosts"),rs.getDouble("realCosts"),rs.getInt("idCustomer"),rs.getInt("idAdvisor"),rs.getInt("idSecondaryAdvisor"),rs.getString("fileName"),rs.getString("fileLocation"),rs.getString("note"), rs.getInt("status"), rs.getString("timestamp"));

			result.add(temp);			
		}

		return result;
	}
	
	/**
	 * @param title
	 * @return
	 * @throws SQLException
	 * @throws OrderNoBillWithThisTitleNotInDBException
	 */
	public List<Order> getOrdersWhereBillisNotCreatedYetByTitle(String title) throws SQLException, OrderNoBillWithThisTitleNotInDBException {
		List<Order> result = new ArrayList<Order>();
		Statement stmt = c.createStatement();
		String sql = "SELECT * FROM (SELECT idOrder, titel, type, projectedCosts, realCosts, idCustomer, idAdvisor, idSecondaryAdvisor, fileName, fileLocation, note, MAX(status) as status, timestamp FROM Orders NATURAL JOIN OrderStatus GROUP BY idOrder) WHERE status!=7 AND titel LIKE '%"+title+"%';";
		ResultSet rs = stmt.executeQuery(sql);
		if(rs.next()) {
			Order temp = new Order (rs.getInt("idOrder"),rs.getString("titel"),rs.getInt("type"),rs.getDouble("projectedCosts"),rs.getDouble("realCosts"),rs.getInt("idCustomer"),rs.getInt("idAdvisor"),rs.getInt("idSecondaryAdvisor"),rs.getString("fileName"),rs.getString("fileLocation"),rs.getString("note"), rs.getInt("status"), rs.getString("timestamp"));
			result.add(temp);
			while(rs.next()) {
				Order temp1 = new Order (rs.getInt("idOrder"),rs.getString("titel"),rs.getInt("type"),rs.getDouble("projectedCosts"),rs.getDouble("realCosts"),rs.getInt("idCustomer"),rs.getInt("idAdvisor"),rs.getInt("idSecondaryAdvisor"),rs.getString("fileName"),rs.getString("fileLocation"),rs.getString("note"), rs.getInt("status"), rs.getString("timestamp"));
				result.add(temp1);	
			}
		}
		else {
			throw new OrderNoBillWithThisTitleNotInDBException();
		}
		return result;
	}
	
	/**
	 * @param type
	 * @return
	 * @throws SQLException
	 * @throws OrderNoBillWithThisTypeNotInDBException
	 */
	public List<Order> getOrdersWhereBillisNotCreatedYetByType(int type) throws SQLException, OrderNoBillWithThisTypeNotInDBException {
		List<Order> result = new ArrayList<Order>();
		Statement stmt = c.createStatement();
		String sql = "SELECT * FROM (SELECT idOrder, titel, type, projectedCosts, realCosts, idCustomer, idAdvisor, idSecondaryAdvisor, fileName, fileLocation, note, MAX(status) as status, timestamp FROM Orders NATURAL JOIN OrderStatus GROUP BY idOrder) WHERE status!=7 AND type="+type+";";
		ResultSet rs = stmt.executeQuery(sql);
		if(rs.next()) {
			Order temp = new Order (rs.getInt("idOrder"),rs.getString("titel"),rs.getInt("type"),rs.getDouble("projectedCosts"),rs.getDouble("realCosts"),rs.getInt("idCustomer"),rs.getInt("idAdvisor"),rs.getInt("idSecondaryAdvisor"),rs.getString("fileName"),rs.getString("fileLocation"),rs.getString("note"), rs.getInt("status"), rs.getString("timestamp"));
				result.add(temp);
			while (rs.next()){
				Order temp1 = new Order (rs.getInt("idOrder"),rs.getString("titel"),rs.getInt("type"),rs.getDouble("projectedCosts"),rs.getDouble("realCosts"),rs.getInt("idCustomer"),rs.getInt("idAdvisor"),rs.getInt("idSecondaryAdvisor"),rs.getString("fileName"),rs.getString("fileLocation"),rs.getString("note"), rs.getInt("status"), rs.getString("timestamp"));
					result.add(temp1);			
			}
		}
		else {
			throw new OrderNoBillWithThisTypeNotInDBException();
			}
		return result;
	}
	
	/**
	 * @param status
	 * @return
	 * @throws SQLException
	 * @throws OrderNoBillWithThisStatusNotInDBException
	 */
	public List<Order> getOrdersWhereBillisNotCreatedYetByStatus(int status) throws SQLException, OrderNoBillWithThisStatusNotInDBException {
		List<Order> result = new ArrayList<Order>();
		Statement stmt = c.createStatement();
		String sql = "SELECT * FROM (SELECT idOrder, titel, type, projectedCosts, realCosts, idCustomer, idAdvisor, idSecondaryAdvisor, fileName, fileLocation, note, MAX(status) as status, timestamp FROM Orders NATURAL JOIN OrderStatus GROUP BY idOrder) WHERE status="+status+";";
		ResultSet rs = stmt.executeQuery(sql);
		if(rs.next()) {
			Order temp = new Order (rs.getInt("idOrder"),rs.getString("titel"),rs.getInt("type"),rs.getDouble("projectedCosts"),rs.getDouble("realCosts"),rs.getInt("idCustomer"),rs.getInt("idAdvisor"),rs.getInt("idSecondaryAdvisor"),rs.getString("fileName"),rs.getString("fileLocation"),rs.getString("note"), rs.getInt("status"), rs.getString("timestamp"));
			result.add(temp);
			while (rs.next()){
				Order temp1 = new Order (rs.getInt("idOrder"),rs.getString("titel"),rs.getInt("type"),rs.getDouble("projectedCosts"),rs.getDouble("realCosts"),rs.getInt("idCustomer"),rs.getInt("idAdvisor"),rs.getInt("idSecondaryAdvisor"),rs.getString("fileName"),rs.getString("fileLocation"),rs.getString("note"), rs.getInt("status"), rs.getString("timestamp"));
				result.add(temp1);			
			}
		}
		else {
			throw new OrderNoBillWithThisStatusNotInDBException();
			}
		return result;
	}
	
	//
	/**
	 * @param status
	 * @return
	 * @throws SQLException
	 * @throws OrderStatusNotInDBException
	 */
	public List<Order> getOrdersByStatus(int status) throws SQLException, OrderStatusNotInDBException {
		List<Order> result = new ArrayList<Order>();
		Statement stmt = c.createStatement();
		String sql = "SELECT idOrder, titel, type, projectedCosts, realCosts, idCustomer, idAdvisor, idSecondaryAdvisor, fileName, fileLocation, note, MAX(status) as status, timestamp FROM Orders NATURAL JOIN OrderStatus GROUP BY idOrder HAVING status="+status+";";
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()){
			Order temp = new Order (rs.getInt("idOrder"),rs.getString("titel"),rs.getInt("type"),rs.getDouble("projectedCosts"),rs.getDouble("realCosts"),rs.getInt("idCustomer"),rs.getInt("idAdvisor"),rs.getInt("idSecondaryAdvisor"),rs.getString("fileName"),rs.getString("fileLocation"),rs.getString("note"), rs.getInt("status"), rs.getString("timestamp"));
			result.add(temp);			
		}
		
		rs.close();
		stmt.close();
		if (result.isEmpty()) throw new OrderStatusNotInDBException();
		return result;
	}
	
	/**
	 * @return
	 * @throws SQLException
	 */
	public List<Person> getCustomerArray () throws SQLException {
	   List<Person> result = new ArrayList<Person>();
	   Statement stmt = c.createStatement();
	   String sql = "SELECT * FROM Persons WHERE rights !="+1+";";
	   ResultSet rs = stmt.executeQuery(sql);
	   while(rs.next()){
		   	Person temp = new Person(rs.getInt("idPerson"),rs.getString("firstname"),rs.getString("surname"),rs.getString("street"),rs.getInt("housenumber"),rs.getInt("zipcode"),rs.getString("email"),rs.getString("timestamp"),rs.getString("username"),rs.getString("password"),rs.getInt("rights"));
			result.add(temp);	   
	   }
	   return result; 
	}
	
	/**
	 * @return
	 * @throws SQLException
	 */
	public List<Person> getAdvisorArray () throws SQLException {
		List<Person> result = new ArrayList<Person>();
		Statement stmt = c.createStatement();
		String sql = "SELECT * FROM Persons WHERE rights="+1+";";
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()){
			Person temp = new Person(rs.getInt("idPerson"),rs.getString("firstname"),rs.getString("surname"),rs.getString("street"),rs.getInt("housenumber"),rs.getInt("zipcode"),rs.getString("email"),rs.getString("timestamp"),rs.getString("username"),rs.getString("password"),rs.getInt("rights"));
			result.add(temp);
		}
		return result;
	}
	
	/**
	 * @return
	 * @throws SQLException
	 */
	public List<Person> getSecondaryAdvisorArray () throws SQLException {
	    List<Person> result = new ArrayList<Person>();
	    Statement stmt = c.createStatement();
	    String sql = "SELECT * FROM Persons WHERE rights="+1+";";
	    ResultSet rs = stmt.executeQuery(sql);
	    while (rs.next()){
	    	Person temp = new Person(rs.getInt("idPerson"),rs.getString("firstname"),rs.getString("surname"),rs.getString("street"),rs.getInt("housenumber"),rs.getInt("zipcode"),rs.getString("email"),rs.getString("timestamp"),rs.getString("username"),rs.getString("password"),rs.getInt("rights"));
	    	result.add(temp);
	    }
	    return result;
	}
	
	//Financial SQL by Nico
	
	/**
	 * @param actualAmount
	 * @param debitAmount
	 * @param name
	 * @param type
	 * @param costCentreNumber
	 * @return
	 * @throws SQLException
	 */
	public int addRegistertoDB(double actualAmount, double debitAmount, String name, int type, String costCentreNumber) throws SQLException{
		int result = 0;
		Statement stmt = c.createStatement();
		String sql ="INSERT INTO Registers (debitAmount, actualAmount, name, type, costCentreNumber) VALUES ('"+debitAmount+"','"+actualAmount+"','"+name+"','"+type+"', '"+costCentreNumber+"')";
		stmt.executeUpdate(sql);
		ResultSet rs = stmt.executeQuery("SELECT last_insert_rowid() FROM Registers");
		rs.next();
		result = rs.getInt(1);
		rs.close();
		stmt.close();
		return result;
	}
		
	/**
	 * @param id
	 * @param actualAmount
	 * @param debitAmount
	 * @param name
	 * @param type
	 * @param costCentreNumber
	 * @throws SQLException
	 */
	public void modifyRegister(int id, double actualAmount, double debitAmount, String name, int type, String costCentreNumber) throws SQLException{
		Statement stmt = c.createStatement();
		stmt.executeUpdate("UPDATE Registers SET debitAmount = "+debitAmount+", actualAmount = "+actualAmount+", name ='"+name+"', type ="+type+", costCentreNumber='"+costCentreNumber+"' WHERE idRegister="+id);
		stmt.close();
	}
	
	public List<CashRegister> getRegisterArray () throws SQLException {
		   List<CashRegister> result = new ArrayList<CashRegister>();
		   Statement stmt = c.createStatement();
		   String sql = "SELECT * FROM Registers";
		   ResultSet rs = stmt.executeQuery(sql);
		   while(rs.next()){
			   	CashRegister temp = new CashRegister(rs.getInt("idRegister"), rs.getDouble("debitAmount"), rs.getDouble("actualAmount"), rs.getString("name"), rs.getInt("type"), rs.getString("costCentreNumber"));
				result.add(temp);	   
		   }
		   return result; 
		}
	
	/**
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public int deleteCashRegisterFromDB(int id) throws SQLException{
		Statement stmt = c.createStatement();
		String sql ="DELETE FROM Registers WHERE idRegister="+id;
		stmt.executeUpdate(sql);
		stmt.close();
		return id;
	}
	
	/**
	 * @param actualAmount
	 * @param debitAmount
	 * @param name
	 * @param idRegister
	 * @return
	 * @throws SQLException
	 */
	public int addPottoDB(double actualAmount, double debitAmount, String name, int idRegister) throws SQLException{
		int result = 0;
		Statement stmt = c.createStatement();
		String sql ="INSERT INTO Pots (debitAmount, actualAmount, name, idRegister) VALUES ("+debitAmount+","+actualAmount+",'"+name+"',"+idRegister+")";
		stmt.executeUpdate(sql);
		ResultSet rs = stmt.executeQuery("SELECT last_insert_rowid() FROM Registers");
		rs.next();
		result = rs.getInt(1);
		rs.close();
		stmt.close();
		return result;
	}
		
	/**
	 * @param id
	 * @param actualAmount
	 * @param debitAmount
	 * @param name
	 * @param idRegister
	 * @throws SQLException
	 */
	public void modifyPot(int id, double actualAmount, double debitAmount, String name, int idRegister) throws SQLException{
		Statement stmt = c.createStatement();
		String sql = "UPDATE Pots SET debitAmount = "+debitAmount+", actualAmount = "+actualAmount+", name ='"+name+"', idRegister ="+idRegister+" WHERE idPots="+id; 
		stmt.executeUpdate(sql); 
		stmt.close();
	}
	
	/**
	 * @return
	 * @throws SQLException
	 */
	public List<Pot> getPotArray () throws SQLException {
		   List<Pot> result = new ArrayList<Pot>();
		   Statement stmt = c.createStatement();
		   String sql = "SELECT * FROM Pots";
		   ResultSet rs = stmt.executeQuery(sql);
		   while(rs.next()){
			   	Pot temp = new Pot(rs.getInt("idPots"), rs.getString("name"), rs.getDouble("debitAmount"), rs.getDouble("actualAmount"), rs.getInt("idRegister"));
				result.add(temp);	   
		   }
		   return result; 
		}
	//Emre+
	/**
	 * @param idRegister
	 * @return
	 * @throws SQLException
	 */
	public List<Pot> getPotArrayByCashRegisterID(int idRegister) throws SQLException {
	    List<Pot> result = new ArrayList<Pot>(); 
	    Statement stmt = c.createStatement(); 
	    String sql = "SELECT * FROM Pots WHERE idRegister= "+ idRegister; 
	    ResultSet rs = stmt.executeQuery(sql);
	    while(rs.next()) {
		Pot temp = new Pot(rs.getInt("idPots"), rs.getString("name"), rs.getDouble("debitAmount"), rs.getDouble("actualAmount"), rs.getInt("idRegister")); 
		result.add(temp); 
	    }
	    return result; 
	    
	}
	
	/**
	 * @param idPot
	 * @return
	 * @throws SQLException
	 * @throws PotNotInDBException
	 */
	public Pot getPotByID(int idPot) throws SQLException, PotNotInDBException {
	    Pot result = null; 
	    Statement stmt = c.createStatement(); 
	    String sql = "SELECT * FROM Pots WHERE idPot= "+idPot; 
	    ResultSet rs = stmt.executeQuery(sql);
	    if(rs.next()) {
	    result = new Pot(rs.getInt("idPots"), rs.getString("name"), rs.getDouble("debitAmount"), rs.getDouble("actualAmount"), rs.getInt("idRegister")); 
	    }
	    else {
	    	throw new PotNotInDBException();
	    }
	    return result; 
	}
	
	/**
	 * @param id
	 * @throws SQLException
	 */
	public void deletePotByChashRegisterID(int id ) throws SQLException {
	    Statement stmt = c.createStatement(); 
	    String sql = "DELETE FROM Pots WHERE idRegister="+ id;
	    stmt.executeUpdate(sql); 
	    stmt.close();
	        
	}
	
	//Emre-
	/**
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public int deletePotFromDB(int id) throws SQLException{
		Statement stmt = c.createStatement();
		String sql ="DELETE FROM Pots WHERE idPots="+id+";";
		stmt.executeUpdate(sql);
		stmt.close();
		return id;
	}
	
	/**
	 * @param idOrder
	 * @param idPot
	 * @param idRegister
	 * @param idCustomer
	 * @param idAdvisor
	 * @param name
	 * @param payKind
	 * @param amount
	 * @return
	 * @throws SQLException
	 */
	public int addBilltoDB(int idOrder, int idPot, int idRegister, int idCustomer, int idAdvisor, String name, int payKind, double amount) throws SQLException {
		int result=0;
		Statement stmt = c.createStatement();
		String sql ="INSERT INTO Bills (idOrder, idPot, idRegister, idCustomer, idAdvisor, name, methodOfPayment, figure) VALUES ("+idOrder+", "+idPot+", "+idRegister+", "+idCustomer+", "+idAdvisor+", '"+name+"', "+payKind+", "+amount+");";
		stmt.executeUpdate(sql);
		ResultSet rs = stmt.executeQuery("SELECT last_insert_rowid() FROM Bills");
		rs.next();
		result = rs.getInt(1);
		rs.close();
		stmt.close();	
		return result;
	}
	
	/**
	 * @param idBill
	 * @param status
	 * @param datetime
	 * @return
	 * @throws SQLException
	 */
	public int addBillStatustoDB(int idBill, int status, String datetime) throws SQLException {
		int result = 0;
		Statement stmt = c.createStatement();
		String sql ="INSERT INTO BillStatus (idBill, status, timestamp) VALUES ("+idBill+ ", "+status+", '"+datetime+"');";
		stmt.executeUpdate(sql);
		ResultSet rs = stmt.executeQuery("SELECT last_insert_rowid() FROM BillStatus");
		rs.next();
		result = rs.getInt(1);
		rs.close();
		stmt.close();	
		return result;
	}
	
	/**
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public int deleteBillFromDB(int id) throws SQLException{
		Statement stmt = c.createStatement();
		String sql ="DELETE FROM Bills WHERE idBill="+id;
		String sql2 ="DELETE FROM BillStatus WHERE idBill="+id;
		stmt.executeUpdate(sql);
		stmt.executeUpdate(sql2);
		stmt.close();
		return id;
	}
	
	/**
	 * @param id
	 * @param idOrder
	 * @param idPot
	 * @param idRegister
	 * @param name
	 * @param methodOfPayment
	 * @param amount
	 * @throws SQLException
	 */
	public void modifyBill(int id, int idOrder, int idPot, int idRegister, String name, int methodOfPayment, double amount) throws SQLException {
		Statement stmt = c.createStatement();
		String sql = "UPDATE Bills SET idOrder="+idOrder+", idPot="+idPot+", idRegister="+idRegister+", name='"+name+"' , methodOfPayment="+methodOfPayment+" , figure="+amount+" WHERE idBill="+id+";";
		stmt.executeUpdate(sql);
		stmt.close();
	}
	
	/**
	 * @param id
	 * @param status
	 * @throws SQLException
	 */
	public void changeBillStatus(int id, int status) throws SQLException{
		Statement stmt = c.createStatement(); 
		String sql = "UPDATE BillStatus SET status='"+status+"' WHERE idBill="+id;
		stmt.executeUpdate(sql); 
		stmt.close();
	}
	
	/**
	 * @return
	 * @throws SQLException
	 */
	public List<Bill> getBills() throws SQLException {
		List<Bill> result = new ArrayList<Bill>();
		Statement stmt = c.createStatement();
		String sql = "SELECT idBill, idOrder, idPot, idRegister, idCustomer, idAdvisor, name, methodOfPayment, figure, MAX(status) as status, timestamp FROM Bills NATURAL JOIN BillStatus GROUP BY idBill";
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()){
			Bill temp = new Bill (rs.getInt("idBill"),rs.getInt("idOrder"),rs.getInt("idPot"), rs.getInt("idCustomer"), rs.getInt("idAdvisor"), rs.getInt("idRegister"), rs.getString("name"), rs.getInt("methodOfPayment"),rs.getDouble("figure"),rs.getInt("status"), rs.getString("timestamp"));
			result.add(temp);			
		}

		return result;
	}
	
	/**
	 * @param name
	 * @return
	 * @throws SQLException
	 * @throws BillTitleNotInDBException
	 */
	public List<Bill> getBillByName(String name) throws SQLException, BillTitleNotInDBException {
		List<Bill> result = new ArrayList<Bill>();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT idBill, idOrder, idPot, idCustomer, idAdvisor, idRegister, name, methodOfPayment, figure, MAX(status) as status, timestamp FROM Bills NATURAL JOIN BillStatus GROUP BY idBill HAVING name LIKE '%"+name+"%'");
		while (rs.next()){
			Bill temp = new Bill (rs.getInt("idBill"),rs.getInt("idOrder"),rs.getInt("idPot"), rs.getInt("idCustomer"), rs.getInt("idAdvisor"), rs.getInt("idRegister"), rs.getString("name"), rs.getInt("methodOfPayment"),rs.getDouble("figure"),rs.getInt("status"), rs.getString("timestamp"));
			result.add(temp);			
		}

		rs.close();
		stmt.close();
		if (result.isEmpty()) throw new BillTitleNotInDBException();
		return result;
	}
	
	/**
	 * @param id
	 * @return
	 * @throws SQLException
	 * @throws BillIDNotInDBException
	 */
	public Bill getBillByID(int id) throws SQLException, BillIDNotInDBException {
		Bill result = null;
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT idBill, idOrder, idPot, idCustomer, idAdvisor, idRegister, name, methodOfPayment, figure, MAX(status) as status, timestamp FROM Bills NATURAL JOIN BillStatus GROUP BY idBill HAVING idBill="+id+";");
		while (rs.next()){
			result = new Bill (rs.getInt("idBill"),rs.getInt("idOrder"),rs.getInt("idPot"), rs.getInt("idCustomer"), rs.getInt("idAdvisor"), rs.getInt("idRegister"), rs.getString("name"), rs.getInt("methodOfPayment"),rs.getDouble("figure"),rs.getInt("status"), rs.getString("timestamp"));			
		}

		rs.close();
		stmt.close();
		if (result == null) throw new BillIDNotInDBException();
		return result;
	}
	
	/**
	 * @param status
	 * @return
	 * @throws SQLException
	 * @throws BillStatusNotInDBException
	 */
	public List<Bill> getBillsByStatus(int status) throws SQLException, BillStatusNotInDBException {
		List<Bill> result = new ArrayList<Bill>();
		Statement stmt = c.createStatement();
		String sql = "SELECT idBill, idOrder, idPot, idRegister, idCustomer, idAdvisor, name, methodOfPayment, figure, MAX(status) as status, timestamp FROM Bills NATURAL JOIN BillStatus GROUP BY idBill HAVING status LIKE "+status+";";
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()){
			Bill temp = new Bill (rs.getInt("idBill"),rs.getInt("idOrder"),rs.getInt("idPot"), rs.getInt("idCustomer"), rs.getInt("idAdvisor"), rs.getInt("idRegister"), rs.getString("name"), rs.getInt("methodOfPayment"),rs.getDouble("figure"),rs.getInt("status"), rs.getString("timestamp"));
			result.add(temp);			
		}
		
		rs.close();
		stmt.close();
		if (result.isEmpty()) throw new BillStatusNotInDBException();
		return result;
	}
	
	/**
	 * @param idOrder
	 * @return
	 * @throws SQLException
	 */
	public Bill getBillByOrderID(int idOrder) throws SQLException {
		Bill result = null;
		Statement stmt = c.createStatement();
		String sql = "SELECT idBill, idOrder, idPot, idRegister, idCustomer, idAdvisor, name, methodOfPayment, figure, MAX(status) as status, timestamp FROM Bills NATURAL JOIN BillStatus GROUP BY idBill HAVING idOrder="+idOrder+";";
		ResultSet rs = stmt.executeQuery(sql);
		while(rs.next()) {
			Bill temp = new Bill (rs.getInt("idBill"),rs.getInt("idOrder"),rs.getInt("idPot"), rs.getInt("idCustomer"), rs.getInt("idAdvisor"), rs.getInt("idRegister"), rs.getString("name"), rs.getInt("methodOfPayment"),rs.getDouble("figure"),rs.getInt("status"), rs.getString("timestamp"));
			result = temp;
		}
		rs.close();
		stmt.close();
		return result;
	}
	
	/**
	 * @param date
	 * @return
	 * @throws SQLException
	 * @throws BillDateNotInDBException
	 */
	public List<Bill> getBillsByDate(String date) throws SQLException, BillDateNotInDBException {
		List<Bill> result = new ArrayList<Bill>();
		Statement stmt = c.createStatement();
		String sql = "SELECT idBill, idOrder, idPot, idRegister, idCustomer, idAdvisor, name, methodOfPayment, figure, MAX(status) as status, timestamp FROM Bills NATURAL JOIN BillStatus GROUP BY idBill HAVING timestamp LIKE '%"+date+"%';";
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()){
			Bill temp = new Bill (rs.getInt("idBill"),rs.getInt("idOrder"),rs.getInt("idPot"), rs.getInt("idCustomer"), rs.getInt("idAdvisor"), rs.getInt("idRegister"), rs.getString("name"), rs.getInt("methodOfPayment"),rs.getDouble("figure"),rs.getInt("status"), rs.getString("timestamp"));
			result.add(temp);			
		}
		
		rs.close();
		stmt.close();
		if (result.isEmpty()) throw new BillDateNotInDBException();
		return result;
	}
	// Nico End*/
	
	/**
	 * @param idBill
	 * @return
	 * @throws SQLException
	 * @throws CantGenerateBillinformationException
	 */
	public Billinformation getBillinformationByID(int idBill) throws SQLException, CantGenerateBillinformationException {
		Billinformation result = null;
		Statement stmt = c.createStatement();
		String sql = "SELECT * FROM Bills JOIN Persons ON Bills.idCustomer=Persons.idPerson WHERE idBill="+idBill+";";
		ResultSet rs = stmt.executeQuery(sql);
		if (rs.next()){
			result = new Billinformation (rs.getInt("idBill"),rs.getInt("idOrder"),rs.getInt("idPot"), rs.getInt("idCustomer"), rs.getInt("idAdvisor"), rs.getInt("idRegister"), rs.getString("name"), rs.getInt("methodOfPayment"),rs.getDouble("figure"),rs.getInt("status"), rs.getString("timestamp"), rs.getString("firstname"), rs.getString("surname"), rs.getString("street"), rs.getInt("housenumber"), rs.getInt("zipcode"), rs.getString("email"));			
		}
		else {
			throw new CantGenerateBillinformationException();
		}
		return result;
	}
	
	/**
	 * @param idPot
	 * @param amount
	 * @throws SQLException
	 */
	public void updateActualAmountPotByAmount(int idPot, double amount) throws SQLException{
		Statement stmt = c.createStatement();
		String sql = "UPDATE Pots SET actualAmount=actualAmount+"+amount+" WHERE idPots="+idPot;
		stmt.executeUpdate(sql); 
		stmt.close();
	}
	
	/**
	 * @param idPot
	 * @param amount
	 * @throws SQLException
	 */
	public void updateTargetAmountPotByAmount(int idPot, double amount) throws SQLException{
		Statement stmt = c.createStatement();
		String sql = "UPDATE Pots SET debitAmount=debitAmount+"+amount+" WHERE idPots="+idPot;
		stmt.executeUpdate(sql); 
		stmt.close();
	}
	
	/**
	 * @param idRegister
	 * @return
	 * @throws SQLException
	 */
	public boolean isRegisterReferenced(int idRegister) throws SQLException{
		boolean result = false;
		Statement stmt = c.createStatement();
		String sql = "SELECT * FROM Pots WHERE idRegister="+idRegister+";";
		ResultSet rs = stmt.executeQuery(sql);
		if (rs.next()){
			result = true;			
		}
		else {
			result = false;
		}
		return result;
	}
	
	/**
	 * @param idPot
	 * @return
	 * @throws SQLException
	 */
	public boolean isPotReferenced(int idPot) throws SQLException{
		boolean result = false;
		Statement stmt = c.createStatement();
		String sql = "SELECT * FROM Bills WHERE idPot="+idPot+";";
		ResultSet rs = stmt.executeQuery(sql);
		if (rs.next()){
			result = true;			
		}
		else {
			result = false;
		}
		return result;
	}	
	
	/**
	 * @param id
	 * @throws SQLException
	 */
	public void setSIBRegisterViaPots(int id) throws SQLException{
		Statement stmt = c.createStatement();
		String sql = "SELECT sum(debitAmount), sum(actualAmount) FROM Pots GROUP BY idRegister HAVING idRegister="+id+";";
		ResultSet rs = stmt.executeQuery(sql);
		
		if (rs.next()) {
			Statement updateStatement = c.createStatement();
			String update = "UPDATE Registers SET debitAmount="+rs.getDouble(1)+" WHERE idRegister="+id+";";
			String update2 = "UPDATE Registers SET actualAmount="+rs.getDouble(2)+" WHERE idRegister="+id+";";
			updateStatement.executeUpdate(update);
			updateStatement.executeUpdate(update2);
		}
	}
	
}