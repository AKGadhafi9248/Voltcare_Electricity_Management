package com.example.myproject.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.example.myproject.model.Consumer;
import com.example.myproject.model.Feedback;
import com.example.myproject.model.PaymentHistory;
import com.example.myproject.model.paymentModel;
import com.example.myproject.model.paymentRequest;
import com.example.myproject.model.Bill;
import com.example.myproject.model.Compliant;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;



@Service

public class DatabaseUtil {



  private static JdbcTemplate jdbcTemplate;



  @Autowired

  public void setJdbsTemplate(JdbcTemplate jdbcTemplate) {

    DatabaseUtil.jdbcTemplate = jdbcTemplate;

  }

  private static boolean tableExists(DatabaseMetaData metaData, String tableName) throws SQLException {

	  try (ResultSet resultSet = metaData.getTables(null, null, tableName.toUpperCase(), null)) {

	    return resultSet.next();

	  }

	}

  public static void createTables() {



	  try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {



	    DatabaseMetaData metaData = connection.getMetaData();



	    // Check if 'consumer' table exists

	    if (!tableExists(metaData, "consumer")) {

	      String createConsumerTableSQL = "CREATE TABLE consumer (" +

	          "consumer_number BIGINT PRIMARY KEY, " +

	          "bill_number INT, " +

	          "title VARCHAR(255), " +

	          "name VARCHAR(255), " +

	          "email VARCHAR(255), " +

	          "mobile VARCHAR(20), " +

	          "category VARCHAR(255), " +

	          "user_id VARCHAR(255), " +

	          "password VARCHAR(255))";

	      jdbcTemplate.execute(createConsumerTableSQL);

	      System.out.println("Consumer table created.");

	    } else {

	      System.out.println("Consumer table already exists.");

	    }



	    // Check if 'bill' table exists

	    if (!tableExists(metaData, "bill")) {

	      String createBillsTableSQL = "CREATE TABLE bill (" +

	          "bill_id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY, " +

	          "consumer_number BIGINT, " +

	          "bill_description VARCHAR(255), " +

	          "bill_amount INT, " +

	          "bill_date DATE, " +

	          "FOREIGN KEY (consumer_number) REFERENCES consumer(consumer_number))";

	      jdbcTemplate.execute(createBillsTableSQL);

	      System.out.println("Bill table created.");

	    } else {

	      System.out.println("Bill table already exists.");

	    }



	    // Check if 'payments' table exists

	    if (!tableExists(metaData, "bill_history")) {



	    	  String createBillHistoryTableSQL = "CREATE TABLE bill_history (" +

	    	    "history_id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY, " +

	    	    "consumer_number BIGINT, " +

	    	    "bill_description VARCHAR(255), " +

	    	    "bill_amount INT, " +

	    	    "bill_date DATE, " +

	    	    "payment_date TIMESTAMP, " +

	    	    "FOREIGN KEY (consumer_number) REFERENCES consumer(consumer_number))";



	    	  jdbcTemplate.execute(createBillHistoryTableSQL);

	    	  System.out.println("Bill history table created.");

	    	} else {

	    	  System.out.println("Bill history table already exists.");

	    	}


	    if (!tableExists(metaData, "feedbackTable")) {


	    	String createFeedbackTableSQL = "CREATE TABLE feedbackTable (" +

  "feedback_id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY, " +

  "complaint_id INT, " +

  "command_description VARCHAR(500), " +

  "rating INT CHECK (rating BETWEEN 1 AND 5), " +

  "feedback_status VARCHAR(20) DEFAULT 'inactive', " +

  "FOREIGN KEY (complaint_id) REFERENCES complaintTable(complaint_id)" +

")";



	    	  jdbcTemplate.execute(createFeedbackTableSQL);



	    	  System.out.println("Feedback table created.");



	    	} else {



	    	  System.out.println("Feedback table already exists.");



	    	}
	    // Check if 'complaint' table exists

	    if (!tableExists(metaData, "complaintTable")) {

	      String createComplaintTableSQL = "CREATE TABLE complaintTable (" +

	          "complaint_id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY, " +

	          "consumer_number BIGINT, " +

	          "complaint_type VARCHAR(255), " +

	          "category VARCHAR(255), " +

	          "contact_person VARCHAR(255), " +

	          "landmark VARCHAR(255), " +

	          "problem_description VARCHAR(255), " +

	          "mobile VARCHAR(20), " +

	          "address VARCHAR(255), " +

	          "complaint_date DATE, " +
	          
	          "status VARCHAR(50) DEFAULT 'Active', " +

	          "FOREIGN KEY (consumer_number) REFERENCES consumer(consumer_number))";

	      jdbcTemplate.execute(createComplaintTableSQL);

	      System.out.println("Complaint table created.");

	    } else {

	      System.out.println("Complaint table already exists.");

	    }



	  } catch (SQLException e) {

	    e.printStackTrace();

	  }
	  



	}
  public static void insertComplaint(Compliant complaint) {
	  DatabaseUtil.createTables();

	  String insertSQL = "INSERT INTO complaintTable (consumer_number, complaint_type, category, contact_person, landmark, problem_description, mobile, address, complaint_date) " +

	      "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";



	  jdbcTemplate.update(insertSQL, complaint.getConsumerNumber(), complaint.getComplaintType(),

	      complaint.getCategory(), complaint.getContactPerson(), complaint.getLandmark(),

	      complaint.getProblemDescription(), complaint.getMobile(), complaint.getAddress(),

	      java.sql.Date.valueOf(complaint.getComplaintDate()));

	}



  // Method to insert a new Bill into Derby with billDate

  public static void insertBill(Bill bill) {

    String insertSQL = "INSERT INTO bill (consumer_number, bill_description, bill_amount, bill_date) VALUES (?, ?, ?, ?)";



    jdbcTemplate.update(insertSQL, bill.getConsumerNumber(), bill.getBillDescription(), bill.getBillAmount(), Date.valueOf(bill.getBillDate()));

  }



  // Method to get Bills for a specific Consumer from Derby

public static List<Bill> getBillsByConsumer(long consumerNumber) {

    String query = "SELECT * FROM bills WHERE consumer_number = ?";


    return jdbcTemplate.query(query, new Object[]{consumerNumber}, (rs, rowNum) -> {

      Bill bill = new Bill();

      bill.setBillId(rs.getInt("bill_id"));

      bill.setConsumerNumber(rs.getLong("consumer_number"));

      bill.setBillDescription(rs.getString("bill_description"));

      bill.setBillAmount(rs.getInt("bill_amount"));

      bill.setBillDate(rs.getDate("bill_date").toLocalDate()); // Convert SQL Date to LocalDate

      return bill;

    });

  }

  public static  List<Bill> getAllBills() {

	    String sql = "SELECT * FROM bill where ";

	    return jdbcTemplate.query(sql, (rs, rowNum) -> {

	      Bill bill = new Bill();

	      bill.setConsumerNumber(rs.getLong("consumer_number"));

	      bill.setBillDescription(rs.getString("bill_description"));

	      bill.setBillAmount(rs.getInt("bill_amount"));

	      bill.setBillDate(rs.getDate("bill_date").toLocalDate());

	      return bill;

	    });

	  }

  // Method to find a Consumer by their User ID

  public static Consumer findUserById(String userId) {

    String query = "SELECT * FROM consumer WHERE user_id = ?";



    return jdbcTemplate.queryForObject(query, (rs, rowNum) -> {

      Consumer consumer = new Consumer();

      consumer.setConsumerNumber(rs.getLong("consumer_number"));

      consumer.setBillNumber(rs.getInt("bill_number"));

      consumer.setTitle(rs.getString("title"));

      consumer.setName(rs.getString("name"));

      consumer.setEmail(rs.getString("email"));

      consumer.setMobile(rs.getString("mobile"));

      consumer.setCategory(rs.getString("category"));

      consumer.setUserId(rs.getString("user_id"));

      consumer.setPassword(rs.getString("password"));

      return consumer;

    }, userId);

  }
  public static Long findConsumerNumberById(String userId) {



	  String query = "SELECT consumer_number FROM consumer WHERE user_id = ?";



	  return jdbcTemplate.queryForObject(query, Long.class, userId);

	}


  // Method to get all Consumers from Derby

  public static List<Consumer> getAllConsumers() {

    String query = "SELECT * FROM consumer";

    return jdbcTemplate.query(query, (rs, rowNum) -> {

      Consumer consumer = new Consumer();

      consumer.setConsumerNumber(rs.getLong("consumer_number"));

      consumer.setBillNumber(rs.getInt("bill_number"));

      consumer.setTitle(rs.getString("title"));

      consumer.setName(rs.getString("name"));

      consumer.setEmail(rs.getString("email"));

      consumer.setMobile(rs.getString("mobile"));

      consumer.setCategory(rs.getString("category"));

      consumer.setUserId(rs.getString("user_id"));

      consumer.setPassword(rs.getString("password"));

      return consumer;

    });

  }



  // Method to check if a user ID already exists in Derby

  public static boolean isUserIdExist(String userId) {

    String query = "SELECT COUNT(*) FROM consumer WHERE user_id = ?";



    Integer count = jdbcTemplate.queryForObject(query, Integer.class, userId);

    return count != null && count > 0;

  }



  // Method to insert a new Consumer into Derby

  public static void insertConsumer(Consumer consumer) {

    String insertSQL = "INSERT INTO consumer (consumer_number, bill_number, title, name, email, mobile, category, user_id, password) " +

        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";



    jdbcTemplate.update(insertSQL, consumer.getConsumerNumber(), consumer.getBillNumber(), consumer.getTitle(), 

        consumer.getName(), consumer.getEmail(), consumer.getMobile(), consumer.getCategory(), 

        consumer.getUserId(), consumer.getPassword());

  }
  public static List<Bill> findBillsByConsumerNumber(Long consumerNumber) {

	  String query = "SELECT * FROM bill WHERE consumer_number = ?";

	  return jdbcTemplate.query(query, (rs, rowNum) -> {

	    Bill bill = new Bill();

	    bill.setBillId(rs.getInt("bill_id"));

	    bill.setConsumerNumber(rs.getLong("consumer_number"));

	    bill.setBillDescription(rs.getString("bill_description"));

	    bill.setBillAmount(rs.getInt("bill_amount"));

	    bill.setBillDate(rs.getDate("bill_date").toLocalDate());

	    return bill;

	  }, consumerNumber);

	}

  public static  List<paymentRequest> getAllPayments() {

	    String sql = "SELECT * FROM bill_history";

	    return jdbcTemplate.query(sql, (rs, rowNum) -> {

	      paymentRequest payment = new paymentRequest();

	      payment.setBillId(rs.getString("bill_id"));

	      payment.setAmount(rs.getString("amount"));

	      payment.setPaymentDate(rs.getString("payment_date"));

	      return payment;

	    });

	  }
  public static List<Compliant> getActiveComplaints() {

	    String sql = "SELECT * FROM complaintTable WHERE status = 'Active'";

	    RowMapper<Compliant> rowMapper = (rs, rowNum) -> {

	      Compliant complaint = new Compliant();

	      complaint.setComplaintId(rs.getInt("complaint_id"));

	      complaint.setConsumerNumber(rs.getLong("consumer_number"));

	      complaint.setComplaintType(rs.getString("complaint_type"));

	      complaint.setCategory(rs.getString("category"));

	      complaint.setContactPerson(rs.getString("contact_person"));

	      complaint.setLandmark(rs.getString("landmark"));

	      complaint.setProblemDescription(rs.getString("problem_description"));

	      complaint.setMobile(rs.getString("mobile"));

	      complaint.setAddress(rs.getString("address"));

	      complaint.setComplaintDate(rs.getDate("complaint_date").toLocalDate());

	      complaint.setStatus(rs.getString("status"));

	      return complaint;

	    };

	    return jdbcTemplate.query(sql, rowMapper);

	  }

  public static Compliant getComplaintById(int id) {

	  String sql = "SELECT * FROM complaintTable WHERE complaint_id = ?";

	   

	  // Query the database and map the result to a Complaint object

	  return jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) -> {

	    Compliant complaint = new Compliant();

	    complaint.setComplaintId(rs.getInt("complaint_id"));

	    complaint.setConsumerNumber(rs.getLong("consumer_number"));

	    complaint.setComplaintType(rs.getString("complaint_type"));

	    complaint.setCategory(rs.getString("category"));

	    complaint.setContactPerson(rs.getString("contact_person"));

	    complaint.setLandmark(rs.getString("landmark"));

	    complaint.setProblemDescription(rs.getString("problem_description"));

	    complaint.setMobile(rs.getString("mobile"));

	    complaint.setAddress(rs.getString("address"));

	    complaint.setComplaintDate(rs.getDate("complaint_date").toLocalDate());

	    complaint.setStatus(rs.getString("status"));

	    return complaint;

	  });

	}

	  // Update complaint status

	  public static void updateComplaintStatus(int complaintId, String status) {

	    String sql = "UPDATE complaintTable SET status = ? WHERE complaint_id = ?";

	    jdbcTemplate.update(sql, status, complaintId);

	  }
	  public static List<Compliant> getAllComplaints(Long consumerNumber) {

		    String sql = "SELECT * FROM complaintTable where consumer_number = ?";

		  
		    return jdbcTemplate.query(sql, (rs, rowNum) -> {

		    	Compliant complaint = new Compliant();

			      complaint.setComplaintId(rs.getInt("complaint_id"));

			      complaint.setConsumerNumber(rs.getLong("consumer_number"));

			      complaint.setComplaintType(rs.getString("complaint_type"));

			      complaint.setCategory(rs.getString("category"));

			      complaint.setContactPerson(rs.getString("contact_person"));

			      complaint.setLandmark(rs.getString("landmark"));

			      complaint.setProblemDescription(rs.getString("problem_description"));

			      complaint.setMobile(rs.getString("mobile"));

			      complaint.setAddress(rs.getString("address"));

			      complaint.setComplaintDate(rs.getDate("complaint_date").toLocalDate());

			      complaint.setStatus(rs.getString("status"));

			      return complaint;

			  }, consumerNumber);

		  }
	 
	  public static List<Compliant> getComplaints() {

		    String sql = "SELECT * FROM complaintTable";



		    return jdbcTemplate.query(sql, new ComplaintRowMapper());

		  }
	  private static class ComplaintRowMapper implements RowMapper<Compliant> {

		    @Override

		    public Compliant mapRow(ResultSet rs, int rowNum) throws SQLException {

		      Compliant complaint = new Compliant();

		      complaint.setComplaintId(rs.getInt("complaint_id"));

		      complaint.setConsumerNumber(rs.getLong("consumer_number"));

		      complaint.setComplaintType(rs.getString("complaint_type"));

		      complaint.setCategory(rs.getString("category"));

		      complaint.setContactPerson(rs.getString("contact_person"));

		      complaint.setLandmark(rs.getString("landmark"));

		      complaint.setProblemDescription(rs.getString("problem_description"));

		      complaint.setMobile(rs.getString("mobile"));

		      complaint.setAddress(rs.getString("address"));

		      complaint.setComplaintDate(rs.getDate("complaint_date").toLocalDate());

		      complaint.setStatus(rs.getString("status"));

		      return complaint;

		    }

		  }
	  public static boolean isConsumerNumberExist(String consumerNumber) {

		    String sql = "SELECT COUNT(*) FROM consumer WHERE consumer_number = ?";

		    Integer count = jdbcTemplate.queryForObject(sql, new Object[]{consumerNumber}, Integer.class);

		    return count != null && count > 0;

		  }

	  public static Integer calculateTotalAmount(List<Integer> billIds) {

		    String sql = "SELECT SUM(bill_amount) FROM bill WHERE id IN (?)";

		    return jdbcTemplate.queryForObject(sql, new Object[] { billIds.toArray() }, Integer.class);

		  }



		  // Method to update bill history and delete from bill table

	  public static void updateBillHistory(List<Integer> billIds) {
		  createTables();	

		  // Build the IN clause with placeholders for each billId

		  String inClause = String.join(",", billIds.stream().map(id -> "?").toList());



		  String sqlInsert = "INSERT INTO bill_history (consumer_number, bill_date, bill_description, bill_amount, payment_date) " +

		            "SELECT consumer_number, bill_date, bill_description, bill_amount, CURRENT_TIMESTAMP " +

		            "FROM bill WHERE bill_id IN (" + inClause + ")";



		  // Convert List<Integer> to Object[] for jdbcTemplate

		  Object[] params = billIds.toArray();



		  jdbcTemplate.update(sqlInsert, params);



		  String sqlDelete = "DELETE FROM bill WHERE bill_id IN (" + inClause + ")";

		  jdbcTemplate.update(sqlDelete, params);

		}
	  public static List<PaymentHistory> getBillHistory(Long number) {

		  // SQL query to fetch payment history

		  String sql = "SELECT consumer_number, bill_date, bill_description, bill_amount, payment_date FROM bill_history where consumer_number = ?";

		  return jdbcTemplate.query(sql, (rs, rowNum) -> {

			  PaymentHistory paymentHistory = new PaymentHistory();

		      paymentHistory.setConsumerNumber(rs.getLong("consumer_number"));

		      paymentHistory.setBillDate(rs.getDate("bill_date"));

		      paymentHistory.setBillDescription(rs.getString("bill_description"));

		      paymentHistory.setBillAmount(rs.getBigDecimal("bill_amount"));

		      paymentHistory.setPaymentDate(rs.getTimestamp("payment_date"));

			      return paymentHistory;

			  }, number);

		  // RowMapper to map each row of the result set to a PaymentHistory object

		 

		}


		/*
		 * public static void submitFeedback(int complaintId, String commandDescription,
		 * int rating) {
		 * 
		 * String sql =
		 * "INSERT INTO feedbackTable (complaint_id, command_description, rating, feedback_status) VALUES (?, ?, ?, 'inactive')"
		 * ;
		 * 
		 * 
		 * 
		 * // Assuming jdbcTemplate is a static or accessible instance
		 * 
		 * jdbcTemplate.update(sql, complaintId, commandDescription, rating);
		 * 
		 * 
		 * 
		 * System.out.println("Feedback submitted for Complaint ID: " + complaintId);
		 * 
		 * }
		 */


/*
 * public static void updateFeedbackStatus(int complaintId, String status) {
 * 
 * createTables();
 * 
 * String sql =
 * "UPDATE feedbackTable SET feedback_status = ? WHERE complaint_id = ?";
 * 
 * jdbcTemplate.update(sql, status, complaintId);
 * 
 * System.out.println("Feedback status updated to " + status +
 * " for Complaint ID: " + complaintId);
 * 
 * }
 */
	  public static List<Feedback> getAllFeedbacks() {

		  // Fetch feedbacks from the database

		  String sql = "SELECT * FROM feedbackTable";

		  List<Feedback> feedbackList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Feedback.class));



		  // Loop through the feedbacks and add consumer number

		  for (Feedback feedback : feedbackList) {

		    String consumerNumber = getConsumerNumberByComplaintId(feedback.getComplaintId());

		    feedback.setConsumerNumber(consumerNumber);

		  }



		  return feedbackList;

		}



		  public static List<Feedback> searchFeedbacks(String search) {

		    String sql = "SELECT * FROM feedbackTable WHERE " +

		           "CAST(complaint_id AS VARCHAR) LIKE ? OR " +

		           "CAST(consumer_number AS VARCHAR) LIKE ? OR " +

		           "CAST(rating AS VARCHAR) LIKE ? OR " +

		           "command_description LIKE ?";

		    String wildcardSearch = "%" + search + "%";

		    return jdbcTemplate.query(sql, new Object[]{wildcardSearch, wildcardSearch, wildcardSearch, wildcardSearch},

		        new BeanPropertyRowMapper<>(Feedback.class));

		  }
		  public static List<Feedback> getActiveFeedbacksForConsumer(Long consumerNumber) {

			    String sql = "SELECT  f.feedback_id, f.complaint_id, f.command_description, f.rating, f.feedback_status FROM feedbackTable f " +

			           "JOIN complaintTable c ON f.complaint_id = c.complaint_id " +

			           "WHERE c.consumer_number = ? AND f.feedback_status = 'active'";
			    
			    

			    return jdbcTemplate.query(sql, new Object[]{consumerNumber}, new BeanPropertyRowMapper<>(Feedback.class));

			  }



			  public static void submitFeedback(int feedbackId, String commandDescription, int rating) {

			    String sql = "UPDATE feedbackTable SET command_description = ?, rating = ?, feedback_status = 'inactive' WHERE feedback_id = ?";

			    jdbcTemplate.update(sql, commandDescription, rating, feedbackId);

			  }

			  public static void insertFeedback(int complaintId, String commandDescription, int rating) {

				  // Ensure rating is between 1 and 5

				  if (rating < 1 || rating > 5) {

				    throw new IllegalArgumentException("Rating must be between 1 and 5.");

				  }



				  String sql = "INSERT INTO feedbackTable (complaint_id, command_description, rating, feedback_status) " +

				         "VALUES (?, ?, ?, 'inactive')";

				  jdbcTemplate.update(sql, complaintId, commandDescription, rating);

				}


				public static void updateFeedbackStatusByComplaintId(int complaintId, String status) {

				  String sql = "UPDATE feedbackTable SET feedback_status = ? WHERE complaint_id = ?";

				  jdbcTemplate.update(sql, status, complaintId);

				}
				public static boolean feedbackExistsForComplaint(int complaintId) {

					  String sql = "SELECT COUNT(*) FROM feedbackTable WHERE complaint_id = ?";

					  Integer count = jdbcTemplate.queryForObject(sql, new Object[]{complaintId}, Integer.class);

					  return count != null && count > 0;

					}
				private static String getConsumerNumberByComplaintId(int complaintId) {

					  // Query the consumerTable to get the consumer number by complaint_id

					  String sql = "SELECT c.consumer_number FROM consumer c " +

					         "JOIN complaintTable cm ON c.consumer_number = cm.consumer_number " +

					         "WHERE cm.complaint_id = ?";

					   

					  return jdbcTemplate.queryForObject(sql, new Object[]{complaintId}, String.class);

					}
}