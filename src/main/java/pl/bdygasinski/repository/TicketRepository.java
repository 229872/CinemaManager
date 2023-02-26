package pl.bdygasinski.repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;
import pl.bdygasinski.annotation.TicketRepo;
import pl.bdygasinski.exception.repository.EntityNotFoundException;
import pl.bdygasinski.exception.repository.TicketNotFoundRepositoryException;
import pl.bdygasinski.model.Ticket;

import java.util.List;

@ApplicationScoped
@TicketRepo
public class TicketRepository implements Repository<Ticket> {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public void add(Ticket entity) {
        entityManager.persist(entity);
    }

    @Transactional
    @Override
    public void delete(Ticket entity) {
        if (!entityManager.contains(entity)) {
            entity = entityManager.merge(entity);
        }
        entityManager.remove(entity);
    }

    @Transactional
    @Override
    public Ticket update(Ticket entity) {
        return entityManager.merge(entity);
    }

    @Override
    public Ticket findById(Long id) throws TicketNotFoundRepositoryException {
        try {
            return entityManager.createNamedQuery(Ticket.FIND_BY_ID, Ticket.class)
                    .setParameter("id", id)
                    .getSingleResult();

        } catch (PersistenceException e) {
            throw new TicketNotFoundRepositoryException("Ticket not found", e.getCause());
        }

    }

    @Override
    public List<Ticket> getAll() {
        return entityManager.createNamedQuery(Ticket.GET_ALL, Ticket.class)
                .getResultList();
    }
}
