package com.example.jsf.phonebook.dao;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.example.jsf.phonebook.model.Contact;
import com.example.jsf.phonebook.model.User;
import com.example.jsf.phonebook.utility.HibernateUtil;

@ManagedBean(name ="uDao", eager = true)
public class UserDao implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * use for adding data in user table in the DB
	 * 
	 * @param u
	 * @return
	 */
	public boolean createUser(User u) {
		boolean res = false;
		Session session = HibernateUtil.openSession();
		Transaction tx = null;
		try {
			tx = session.getTransaction();
			tx.begin();
			session.persist(u);
			tx.commit();
			res = true;
		} catch (Exception ex) {
			tx.rollback();

		} finally {
			session.close();
		}
		return res;
	}

	/**
	 * use for read user by id from DB
	 * 
	 * @param id
	 * @return
	 */
	public User getUserById(long id) {
		User u = null;
		Session session = HibernateUtil.openSession();
		Transaction tx = null;
		try {
			tx = session.getTransaction();
			tx.begin();
			u = session.get(User.class, id);
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
		} finally {
			session.close();
		}
		return u;
	}

	public User getUserByUserName(String username) {
		User u = null;
		Session session = HibernateUtil.openSession();
		Transaction tx = null;
		try {
			tx = session.getTransaction();
			tx.begin();
			Query<User> query = session.createQuery("from User where userName=:username", User.class);
			query.setParameter("username", username);
			u = (User) query.uniqueResult();
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
		} finally {
			session.close();
		}
		return u;
	}

	/**
	 * return list of all user
	 * 
	 * @return
	 */

	public List<User> getAllUser() {
		List<User> list = null;
		Session session = HibernateUtil.openSession();
		Transaction tx = null;
		try {
			tx = session.getTransaction();
			tx.begin();
			Query<User> query = session.createQuery("from User ", User.class);
			list = query.getResultList();
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
		} finally {
			session.close();
		}
		return list;
	}

	public List<Contact> getListOfContact(long id) {

		List<Contact> list = null;
		Session session = HibernateUtil.openSession();
		Transaction tx = null;
		try {
			tx = session.getTransaction();
			tx.begin();
			Query<Contact> query = session.createQuery("from Contact where user_userId=:id ", Contact.class);
			query.setParameter("id", id);
			list = query.getResultList();
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
		} finally {
			session.close();
		}
		return list;
	}

	public boolean updateUser(User u) {
		boolean res = false;
		Session session = HibernateUtil.openSession();
		Transaction tx = null;
		try {
			tx = session.getTransaction();
			tx.begin();
			session.update(u);
			tx.commit();
			res = true;
		} catch (Exception ex) {
			tx.rollback();
		} finally {
			session.close();
		}
		return res;
	}

	/**
	 * white out need to read from DB only with find by id and after that update our
	 * data
	 * 
	 * @param id
	 * @param name
	 * @return
	 */

	public boolean updateUserById(long id, String name) {
		boolean res = false;
		Session session = HibernateUtil.openSession();
		Transaction tx = null;
		try {
			tx = session.getTransaction();
			tx.begin();
			Query query = session.createQuery("UPDATE User SET user_name=:username WHERE user_id=:idnumber");
			query.setParameter("username", name);
			query.setParameter("idnumber", id);
			query.executeUpdate();
			res = true;
		} catch (Exception ex) {
			tx.rollback();
		} finally {
			session.close();
		}
		return res;
	}

	/**
	 * take the user name and update the passwod
	 * 
	 * @param id
	 * @param name
	 * @return
	 */
	public boolean updatePassworldByUsername(String username, String password) {
		boolean res = false;
		Session session = HibernateUtil.openSession();
		Transaction tx = null;
		try {
			tx = session.getTransaction();
			tx.begin();
			Query query = session.createQuery("UPDATE User SET password=:password WHERE userName=:username");
			query.setParameter("password", password);
			query.setParameter("username", username);
			query.executeUpdate();
			res = true;
		} catch (Exception ex) {
			tx.rollback();
		} finally {
			session.close();
		}
		return res;
	}

	public boolean deleteUser(User u) {
		boolean res = false;
		Session session = HibernateUtil.openSession();
		Transaction tx = null;
		try {
			tx = session.getTransaction();
			tx.begin();
			session.delete(u);
			tx.commit();
			res = true;
		} catch (Exception ex) {
			tx.rollback();
		} finally {
			session.close();
		}
		return res;
	}

	public void fillUserBean(User ufb, User ub) {
		// for (Contact c:ub.getListContact()) {
		// System.out.println("namr"+c.getTellnumber());
		// }
		ub.setUserId(ufb.getUserId());
		ub.setListContact(ufb.getListContact());

	}

	public void fillListOfCntactForUserBean(User ub) {
		User u = getUserById(ub.getUserId());
		ub.setListContact(u.getListContact());
	}
}
