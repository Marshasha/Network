
package ch.hevs.bankservice;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

import ch.hevs.businessobject.Account;
import ch.hevs.businessobject.Client;

//@stateful
@Stateless    //staless >> la classe ne permet qu'un seul appel
// @TransactionAttribute(value = TransactionAttributeType.NOT_SUPPORTED)
public class BankBean implements Bank {
	
//	@PersistenceContext(name = "networkPU", type = PersistenceContextType.EXTENDED)
	@PersistenceContext(name = "networkPU", type = PersistenceContextType.TRANSACTION)
	private EntityManager em;

	public Account getAccount(String accountDescription, String lastnameOwner) {
		Query query = em.createQuery("FROM Account a WHERE a.description=:description AND a.owner.lastname=:lastname");
		query.setParameter("description", accountDescription);
		query.setParameter("lastname", lastnameOwner);
		
		return (Account) query.getSingleResult();
	}
	
	public List<Account> getAccountListFromClientLastname(String lastname) {
		return (List<Account>) em.createQuery("SELECT c.accounts FROM Client c where c.lastname=:lastname").setParameter("lastname", lastname).getResultList();
	}

	public void transfer(Account srcAccount, Account destAccount, int amount) {
		
//		em.persist(srcAccount);  >> objet déjà gérer (récup de la DB) >> il faut merge() (ou alors, mais non recommandé, il faut Stateful et prolonger le cycle de vie de l'em)
//		em.persist(destAccount); >> objet déjà gérer (récup de la DB) >> il faut merge() (ou alors, mais non recommandé, il faut Stateful et prolonger le cycle de vie de l'em)
		em.merge(srcAccount);
		em.merge(destAccount);
		srcAccount.debit(amount);
		destAccount.credit(amount);
//		em.flush();  >> va avec la prolongation de la durée de vie pour vider le cache (permet la synchronisation avec la DB)
	}

	public List<Client> getClients() {
		return em.createQuery("FROM Client").getResultList();
	}
	
	public Client getClient(long clientid) {
		return (Client) em.createQuery("FROM Client c where c.id=:id").setParameter("id", clientid).getSingleResult();
	}
}
