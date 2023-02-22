package pl.bdygasinski.manager;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.TransactionalException;
import pl.bdygasinski.dto.ClientInputDTO;
import pl.bdygasinski.dto.ClientOutputDTO;
import pl.bdygasinski.exception.manager.ClientNotFoundManagerException;
import pl.bdygasinski.exception.manager.ClientNotValidException;
import pl.bdygasinski.exception.repository.ClientNotFoundRepositoryException;
import pl.bdygasinski.model.Client;
import pl.bdygasinski.repository.ClientRepository;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class ClientManager {
    @Inject
    private ClientRepository repository;

    public ClientOutputDTO createClient(ClientInputDTO dto) throws ClientNotValidException {
        try {
            Client client = dto.createClient();
            repository.add(client);
            return new ClientOutputDTO(client);
        } catch (TransactionalException e) {
            throw new ClientNotValidException("Login is already in use", e.getCause());
        }

    }

    public List<ClientOutputDTO> getAll() {
        return repository.getAll().stream()
                .map(ClientOutputDTO::new)
                .collect(Collectors.toList());
    }

    public void deleteClientById(Long id) throws ClientNotFoundManagerException {
        try {
            Client client = repository.findById(id);
            repository.delete(client);
        } catch (ClientNotFoundRepositoryException e) {
            throw new ClientNotFoundManagerException(e.getMessage(), e.getCause());
        }
    }
}
