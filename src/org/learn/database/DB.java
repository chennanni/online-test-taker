package org.learn.database;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.learn.data.Option;
import org.learn.data.Question;
import org.learn.data.Test;
import org.learn.data.User;

public class DB {
	
	private static DB instance;
	private static SessionFactory sessionFactory;
	private static ServiceRegistry serviceRegistry;
	
	private DB() {
		createSessionFactory();
	}
	
	private void createSessionFactory() {
		try{
	         Configuration configuration = new Configuration();
	         configuration.configure();
	         serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
	                 configuration.getProperties()).build();
	         sessionFactory = configuration.buildSessionFactory(serviceRegistry); 
	      }catch (Throwable ex) { 
	         System.err.println("Failed to create sessionFactory object." + ex);
	         throw new ExceptionInInitializerError(ex); 
	      }
	}
	
	private void closeSessionFactory() {
	        sessionFactory.close();
	}
	
	public static DB getInstance() {
		if (instance == null) {
			instance = new DB();
		}
		return instance;
	}
	
	public boolean login (String email, String password) {
		boolean actionComplete = false;
		Session session=sessionFactory.openSession();
		Transaction transaction = null;
        try {
        	transaction = session.beginTransaction();
        	String queryString = "FROM User u "+
        						 "WHERE u.email = :email "+
        						 "AND u.password = :password";
        	Query query = session.createQuery(queryString);
        	query.setParameter("email", email);
        	query.setParameter("password", password);
			if (query.list().isEmpty())
				actionComplete = false;
			else
				actionComplete = true;
			transaction.commit();
        } catch (HibernateException e) {
        	if (transaction != null) transaction.rollback();
        	e.printStackTrace();
        	actionComplete = false;
        } finally {
        	session.close(); 
        }
        return actionComplete;
	}
	
	public boolean signup(String email, String password) {
		boolean actionComplete = false;
		Session session=sessionFactory.openSession();
		Transaction transaction = null;
        try {
        	transaction = session.beginTransaction();
        	String queryString = "FROM User u "+
        						 "WHERE u.email = :email ";
        	Query query = session.createQuery(queryString);
        	query.setParameter("email", email);
			if (query.list().isEmpty()){
				// create an entry
				User user = new User();
				user.setEmail(email);
				user.setPassword(password);
				session.save(user);
				actionComplete = true;
			}
			else
				actionComplete = false;
			transaction.commit();
        } catch (HibernateException e) {
        	if (transaction != null) transaction.rollback();
        	e.printStackTrace();
        	actionComplete = false;
        } finally {
        	session.close(); 
        }
        return actionComplete;
	}
	
	public boolean listAllUser() {
		boolean actionComplete = false;
		Session session=sessionFactory.openSession();
		Transaction transaction = null;
        try {
        	transaction = session.beginTransaction();
        	String queryString = "FROM User";
        	Query query = session.createQuery(queryString);
        	List<?> userList = query.list();
        	for (Iterator<?> iterator = userList.iterator(); iterator.hasNext();){
			     User u = (User) iterator.next(); 
			     System.out.println("email: " + u.getEmail());
			}
			transaction.commit();
        } catch (HibernateException e) {
        	if (transaction != null) transaction.rollback();
        	e.printStackTrace();
        	actionComplete = false;
        } finally {
        	session.close(); 
        }
        return actionComplete;
	}
	
	public List<Option> getOptions(String testName, int qId) {
		Session session=sessionFactory.openSession();
		Transaction transaction = null;
		Test test = null;
		Question question = null;
		List<Option> options = new ArrayList<Option>();
		try {
			transaction = session.beginTransaction();			
			String queryString = "FROM Test t WHERE t.name = :testName";
        	Query query = session.createQuery(queryString);
        	query.setParameter("testName", testName);
			List<?> testList = query.list();
			if (testList.size()==1) {
				test = (Test)testList.get(0);
				List<Question> questionList = test.getQuestions();
				for (int i=0; i<questionList.size(); i++) {
					if (questionList.get(i).getId() == qId) {
						question = questionList.get(i);
						// get all options
						List<Option> copy = question.getOptions();
						for (int j=0; j<copy.size(); j++) {
							options.add(copy.get(j));
						}
						break;
					}
				}
			} else {
				System.out.println("test found err...");
				//0 or 2 more cases found
			}
			transaction.commit();
		} catch(HibernateException e) {
			if (transaction != null) transaction.rollback();
        	e.printStackTrace();
		} finally {
			session.close();
		}
		return options;
	}
	
	public List<Option> getFirstQuestionOptions(String testName) {
		Session session=sessionFactory.openSession();
		Transaction transaction = null;
		Test test = null;
		Question question = null;
		List<Option> options = new ArrayList<Option>();
		try {
			transaction = session.beginTransaction();			
			String queryString = "FROM Test t WHERE t.name = :testName";
        	Query query = session.createQuery(queryString);
        	query.setParameter("testName", testName);
			List<?> testList = query.list();
			if (testList.size()==1) {
				test = (Test)testList.get(0);
				if (test.getQuestions()!=null && test.getQuestions().size()>=1){
					question = test.getQuestions().get(0);
					List<Option> copy = question.getOptions();
					for (int i=0; i<copy.size(); i++) {
						options.add(copy.get(i));
					}
				}
				else
					System.out.println("question found err...");
			} else {
				System.out.println("test found err...");
				//0 or 2 more cases found
			}
			transaction.commit();
		} catch(HibernateException e) {
			if (transaction != null) transaction.rollback();
        	e.printStackTrace();
		} finally {
			session.close();
		}
		return options;
	}
	
	public Question getNextQuestion(String testName, int qId) {
		Session session=sessionFactory.openSession();
		Transaction transaction = null;
		Test test = null;
		Question question = null;
		try {
			transaction = session.beginTransaction();			
			String queryString = "FROM Test t WHERE t.name = :testName";
        	Query query = session.createQuery(queryString);
        	query.setParameter("testName", testName);
			List<?> testList = query.list();
			if (testList.size()==1) {
				test = (Test)testList.get(0);
				List<Question> questionList = test.getQuestions();
				for (int i=0; i<questionList.size(); i++) {
					if (questionList.get(i).getId() == qId) {
						question = questionList.get(i);
						break;
					}
				}
			} else {
				System.out.println("test found err...");
				//0 or 2 more cases found
			}
			transaction.commit();
		} catch(HibernateException e) {
			if (transaction != null) transaction.rollback();
        	e.printStackTrace();
		} finally {
			session.close();
		}
		return question;
	}
	
	public Question getFirstQuestion(String testName) {
		Session session=sessionFactory.openSession();
		Transaction transaction = null;
		Test test = null;
		Question question = null;
		try {
			transaction = session.beginTransaction();			
			String queryString = "FROM Test t WHERE t.name = :testName";
        	Query query = session.createQuery(queryString);
        	query.setParameter("testName", testName);
			List<?> testList = query.list();
			if (testList.size()==1) {
				test = (Test)testList.get(0);
				if (test.getQuestions()!=null && test.getQuestions().size()>=1)
					question = test.getQuestions().get(0);
				else
					System.out.println("question found err...");
			} else {
				System.out.println("test found err...");
				//0 or 2 more cases found
			}
			transaction.commit();
		} catch(HibernateException e) {
			if (transaction != null) transaction.rollback();
        	e.printStackTrace();
		} finally {
			session.close();
		}
		return question;
	}
	
//	public Question nextQuestion(Question currentQuestion) {
//		
//	}
	
	public Test getTest(String testName) {
		Session session=sessionFactory.openSession();
		Transaction transaction = null;
		Test test = null;
		try {
			transaction = session.beginTransaction();			
			String queryString = "FROM Test t WHERE t.name = :testName";
        	Query query = session.createQuery(queryString);
        	query.setParameter("testName", testName);
			List<?> testList = query.list();
			if (testList.size()==1) {
				test = (Test)testList.get(0);
			} else {
				System.out.println("test found err...");
				//0 or 2 more cases found
			}
			transaction.commit();
		} catch(HibernateException e) {
			if (transaction != null) transaction.rollback();
        	e.printStackTrace();
		} finally {
			session.close();
		}
		return test;
	}
	/**
	 * invoke once to initialize tables in the database
	 */
	public void init() {
		Session session=sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			
			// Q1
			Question question = new Question();
			question.setDescription("2+2=?");
			question.setAnswer("Ans: 4");
			question.setAnswerExplanation("This is explanation...");
			question.setNextQuestionId(2);
			Option option1 = new Option("Ans: 4");
			option1.setQuestion(question);
			//question.getOptions().add(option1);
			Option option2 = new Option("Ans: 5");
			option2.setQuestion(question);
			//question.getOptions().add(option2);
			Option option3 = new Option("Ans: 6");
			option3.setQuestion(question);
			//question.getOptions().add(option3);
			
			// Q2
			Question question2 = new Question();
			question2.setDescription("3+5=?");
			question2.setAnswer("Ans: 8");
			question2.setAnswerExplanation("This is explanation...");
			question2.setNextQuestionId(3);
			Option option1_2 = new Option("Ans: 6");
			option1_2.setQuestion(question2);
			Option option2_2 = new Option("Ans: 8");
			option2_2.setQuestion(question2);
			Option option3_2 = new Option("Ans: 10");
			option3_2.setQuestion(question2);

			// Test
			Test test = new Test();
			test.setName("Java Test");
			test.getQuestions().add(question);
			test.getQuestions().add(question2);
			
			session.persist(option1);
			session.persist(option2);
			session.persist(option3);
			session.persist(option1_2);
			session.persist(option2_2);
			session.persist(option3_2);
			session.persist(question);
			session.persist(question2);
			session.persist(test);
			
			transaction.commit();
			
		} catch(HibernateException e) {
			if (transaction != null) transaction.rollback();
        	e.printStackTrace();
		} finally {
			session.close();
			closeSessionFactory();
		}
	}
	
	public String getAnswer(String testName, int qId) {
		Session session=sessionFactory.openSession();
		Transaction transaction = null;
		Test test = null;
		Question question = null;
		String answer = null;
		try {
			transaction = session.beginTransaction();			
			String queryString = "FROM Test t WHERE t.name = :testName";
        	Query query = session.createQuery(queryString);
        	query.setParameter("testName", testName);
			List<?> testList = query.list();
			if (testList.size()==1) {
				test = (Test)testList.get(0);
				List<Question> questionList = test.getQuestions();
				for (int i=0; i<questionList.size(); i++) {
					if (questionList.get(i).getId() == qId) {
						question = questionList.get(i);
						answer = question.getAnswer();
						break;
					}
				}
			} else {
				System.out.println("answer found err...");
				//0 or 2 more cases found
			}
			transaction.commit();
		} catch(HibernateException e) {
			if (transaction != null) transaction.rollback();
        	e.printStackTrace();
		} finally {
			session.close();
		}
		return answer;
	}
	
	public String getFirstQuestionAnswer(String testName) {
		Session session=sessionFactory.openSession();
		Transaction transaction = null;
		Test test = null;
		Question question = null;
		String answer = null;
		try {
			transaction = session.beginTransaction();			
			String queryString = "FROM Test t WHERE t.name = :testName";
        	Query query = session.createQuery(queryString);
        	query.setParameter("testName", testName);
			List<?> testList = query.list();
			if (testList.size()==1) {
				test = (Test)testList.get(0);
				if (test.getQuestions()!=null && test.getQuestions().size()>=1){
					question = test.getQuestions().get(0);
					answer = question.getAnswer();
				}
				else
					System.out.println("question found err...");
			} else {
				System.out.println("test found err...");
				//0 or 2 more cases found
			}
			transaction.commit();
		} catch(HibernateException e) {
			if (transaction != null) transaction.rollback();
        	e.printStackTrace();
		} finally {
			session.close();
		}
		return answer;
	}
}
