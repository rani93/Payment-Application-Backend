package com.cn.cnpayment.dal;

import com.cn.cnpayment.exception.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.cn.cnpayment.entity.Payment;

import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class PaymentDALImpl implements PaymentDAL {

    @Autowired
    private EntityManager entityManager;

    @Override
    @Transactional
    public Payment getById(int id) {
        Session session = entityManager.unwrap(Session.class);
        Payment payment = session.get(Payment.class, id);
//        if (payment == null) {
//            throw new NotFoundException("Payment with id " + id + " not found.");
//        }
        return payment;
    }

    @Override
    public List<Payment> getAllPayments() {
//        Session session = entityManager.unwrap(Session.class);
//        return session.createQuery("FROM Payment", Payment.class).getResultList();
    	
    	Session session = entityManager.unwrap(Session.class);
		List<Payment> allPayments= session.createQuery(
				"SELECT p FROM Payment p", Payment.class).getResultList();
		return allPayments;
    }

    @Override
    public List<Payment> getByPaymentType(String paymentType) {
//        Session session = entityManager.unwrap(Session.class);
//        return session.createQuery(
//                "FROM Payment p WHERE lower(p.paymentType) = :paymentType", Payment.class)
//                .setParameter("paymentType", paymentType.toLowerCase())
//                .getResultList();
    	
    	List<Payment> allPayments=getAllPayments();
		List<Payment> paymentsByPaymentType = new ArrayList<>();
		for(Payment payment : allPayments)
		{
			if(payment.getPaymentType().equalsIgnoreCase(paymentType))
			{
				paymentsByPaymentType.add(payment);
			}
		}
		return paymentsByPaymentType;
    }

    @Override
    public List<Payment> getByPaymentDescription(String keyword) {
//        Session session = entityManager.unwrap(Session.class);
//        return session.createQuery(
//                "FROM Payment p WHERE p.description LIKE :keyword", Payment.class)
//                .setParameter("keyword", "%" + keyword + "%")
//                .getResultList();
    	List<Payment> allPayments=getAllPayments();
		List<Payment> paymentsByDescription = new ArrayList<>();
		for(Payment payment : allPayments)
		{
			if(payment.getDescription().contains(keyword))
			{
				paymentsByDescription.add(payment);
			}
		}
		return paymentsByDescription;
    }

    @Override
    public void addPayment(Payment payment) {
        Session session = entityManager.unwrap(Session.class);
//        session.persist(payment);
//        session.merge(payment);
        session.save(payment);
		
    }

    @Override
    public void delete(int paymentId) {
        Session session = entityManager.unwrap(Session.class);
        Payment payment = session.get(Payment.class, paymentId);
//        if (payment != null) {
//            session.remove(payment);
//        }
        session.delete(payment);
    }

    @Override
    public void update(Payment updatePayment) {
        Session session = entityManager.unwrap(Session.class);
        Payment existingPayment = session.get(Payment.class, updatePayment.getId());
//        if (existingPayment != null) {
            existingPayment.setDescription(updatePayment.getDescription());
            existingPayment.setPaymentType(updatePayment.getPaymentType());
            session.merge(existingPayment);
//        }
    }

    @Override
    public void updateDescription(int paymentId, String description) {
        Session session = entityManager.unwrap(Session.class);
        Payment existingPayment = session.get(Payment.class, paymentId);
//        if (existingPayment != null) {
            existingPayment.setDescription(description);
//            session.merge(existingPayment);
//        }
            session.update(existingPayment);
    }
}
