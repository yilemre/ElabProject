//Author: Nico

package logic;

public class Order {
	
	private int id;
	private String title;
	private int type;
	private double forecastedCosts;
	private double realCosts;
	private int idCustomer;
	private int idAdvisor;
	private int idSecondaryAdvisor;
	private String notes;
	private String fileName;
	private String fileLocation;
	private int status;
	
	public Order(int id, String title, int type, double forecastedCosts, double realCosts, int idCustomer, int idAdvisor, int idSecondaryAdvisor, String notes, String fileName, String fileLocation) {
		this.id = id;
		this.title = title;
		this.type = type;
		this.forecastedCosts = forecastedCosts;
		this.realCosts = realCosts;
		this.idCustomer = idCustomer;
		this.idAdvisor = idAdvisor;
		this.idSecondaryAdvisor = idSecondaryAdvisor;
		this.notes = notes;
		this.fileName = fileName;
		this.fileLocation = fileLocation;
		this.status = 1;
	}
	
	public Order(){
		
	}

	public int getOrderId() {
		return id;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public double getForecastedCosts() {
		return forecastedCosts;
	}

	public void setForecastedCosts(double forecastedCosts) {
		this.forecastedCosts = forecastedCosts;
	}

	public double getRealCosts() {
		return realCosts;
	}

	public void setRealCosts(double realCosts) {
		this.realCosts = realCosts;
	}

	public int getIdCustomer() {
		return idCustomer;
	}

	public void setIdCustomer(int idCustomer) {
		this.idCustomer = idCustomer;
	}

	public int getIdAdvisor() {
		return idAdvisor;
	}

	public void setIdAdvisor(int idAdvisor) {
		this.idAdvisor = idAdvisor;
	}

	public int getIdSecondaryAdvisor() {
		return idSecondaryAdvisor;
	}

	public void setIdSecondaryAdvisor(int idSecondaryAdvisor) {
		this.idSecondaryAdvisor = idSecondaryAdvisor;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileLocation() {
		return fileLocation;
	}

	public void setFileLocation(String fileLocation) {
		this.fileLocation = fileLocation;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
