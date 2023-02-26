package pl.bdygasinski.repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import pl.bdygasinski.annotation.ClientRepo;
import pl.bdygasinski.exception.repository.ClientNotFoundRepositoryException;
import pl.bdygasinski.model.Client;

import java.util.List;
import java.util.Objects;

@ApplicationScoped
@ClientRepo
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
    public Client findById(Long id) throws ClientNotFoundRepositoryException {
        try {
            Client client = entityManager.find(Client.class, id);
            Objects.requireNonNull(client);
            return client;
        } catch (NullPointerException e) {
            throw new ClientNotFoundRepositoryException("Client not found", e.getCause());
        }
    }

    @Override
    public List<Client> getAll() {
        return entityManager.createNamedQuery(Client.GET_ALL, Client.class)
                .getResultList();
    }
}
