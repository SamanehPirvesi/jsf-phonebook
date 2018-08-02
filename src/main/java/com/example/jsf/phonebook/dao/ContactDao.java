package com.example.jsf.phonebook.dao;


import javax.faces.bean.ManagedBean;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.example.jsf.phonebook.model.Contact;
import com.example.jsf.phonebook.utility.HibernateUtil;

@ManagedBean(name="cDao", eager=true)
public class ContactDao {

	// 1- Create

	public boolean createContact(Contact c) {
		boolean res = false;
		Session session = HibernateUtil.openSession();
		Transaction tx = null;
		try {
			tx = session.getTransaction();
			tx.begin();

			session.persist(c);

			tx.commit();
			res = true;
		} catch (Exception ex) {

			tx.rollback();

		} finally {
			session.close();
		}

		return res;

	}
	public Contact getContactById(long id) {
		Contact c= null;
		Session session = HibernateUtil.openSession();
		Transaction tx = null;
		try {
			tx = session.getTransaction();
			tx.begin();

			c=session.get(Contact.class, id);

			tx.commit();
			
		} catch (Exception ex) {

			tx.rollback();

		} finally {
			session.close();
		}

		return c;

	}
	public Contact getContactByName(String name) {
		Contact c = null;
		Session session = HibernateUtil.openSession();
		Transaction tx = null;
		try {
			tx = session.getTransaction();
			tx.begin();

			
			Query<Contact> query=session.createQuery("from Contact where name=:var",Contact.class);
			query.setParameter("var", name);
             c=(Contact) query.uniqueResult();
			tx.commit();
			
		} catch (Exception ex) {

			tx.rollback();

		} finally {
			session.close();
		}

		return c;

	}
	public boolean updateContact(Contact c ){
		boolean res = false;
		Session session = HibernateUtil.openSession();
		Transaction tx = null;
		try {
			tx = session.getTransaction();
			tx.begin();

			session.update(c);

			tx.commit();
			res = true;
		} catch (Exception ex) {

			tx.rollback();

		} finally {
			session.close();
		}

		return res;

	}
	public boolean updateContactById(long id , String name) {
		boolean res = false;
		Session session = HibernateUtil.openSession();
		Transaction tx = null;
		try {
			tx = session.getTransaction();
			tx.begin();

			Query query=session.createQuery("UPDATE Contact SET name=:firstname WHERE contact_id=:idnumber");
			query.setParameter("firstname",name);
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
	public boolean updateContactTellNumberById(long id , String tell) {
		boolean res = false;
		Session session = HibernateUtil.openSession();
		Transaction tx = null;
		try {
			tx = session.getTransaction();
			tx.begin();

			Query query=session.createQuery("UPDATE Contact SET tellnumber=:tellnumber WHERE contact_id=:idnumber");
			query.setParameter("tellnumber",tell);
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
	public boolean deleteContact(Contact c) {
		boolean res = false;
		Session session = HibernateUtil.openSession();
		Transaction tx = null;
		try {
			tx = session.getTransaction();
			tx.begin();

			session.delete(c);

			tx.commit();
			res = true;
		} catch (Exception ex) {

			tx.rollback();

		} finally {
			session.close();
		}

		return res;

	}
	public boolean deleteContactByName(String name) {
		boolean res = false;
		Session session = HibernateUtil.openSession();
		Transaction tx = null;
		try {
			tx = session.getTransaction();
			tx.begin();

			Query query=session.createQuery("DELETE FROM Contact WHERE name=:contactname");
			query.setString("contactname",name);
			
		query.executeUpdate();
			res = true;
		} catch (Exception ex) {

			tx.rollback();

		} finally {
			session.close();
		}

		return res;

	}
}
