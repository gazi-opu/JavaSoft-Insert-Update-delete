package databaseConnection;
import java.sql.SQLException;

import model.AgeCategory;
import model.Database;
import model.EmploymentCategory;
import model.Gender;
import model.Person;


public class TestDatabase {

	public static void main(String[] args) {
		System.out.println("Running database test");
		
		Database db = new Database();
		
		try {
			db.connect();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		db.addPerson(new Person("Joe", "Builder", AgeCategory.senior, EmploymentCategory.selfEmployed, "14785", true, Gender.male));
		db.addPerson(new Person("HackSon", "Developer", AgeCategory.adult, EmploymentCategory.selfEmployed, "1235", true, Gender.male));
		
		try {
			db.save();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		db.disconnect();
		

	}

}
