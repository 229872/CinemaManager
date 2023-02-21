package pl.bdygasinski.repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.hibernate.cfg.NotYetImplementedException;
import pl.bdygasinski.model.Client;

import java.util.List;

@ApplicationScoped
public class ClientRepository implements Repository<Client> {

    @PersistenceContext(unitName = "postgres")
    private EntityManager entityManager;

    @Transactional
    @Override
    public void add(Client entity) {
        entityManager.persist(entity);
    }

    @Transactional
    @Override
    public void delete(Client entity) {
        if (!entityManager.contains(entity)) {
            entity = entityManager.merge(entity);
        }
        entityManager.remove(entity);
    }

    @Transactional
    @Override
    public Client update(Client entity) {
        return entityManager.merge(entity);
    }

    @Override
    public Client findById(Long id) {
        return entityManager.find(Client.class, id);
    }

    @Override
    public List<Client> getAll() {
        throw new NotYetImplementedException();
    }
}
