package pl.bdygasinski.manager;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import pl.bdygasinski.dto.ClientInputDTO;
import pl.bdygasinski.dto.ClientOutputDTO;
import pl.bdygasinski.model.Client;
import pl.bdygasinski.repository.ClientRepository;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class ClientManager {
    @Inject
    private ClientRepository repository;

    public ClientOutputDTO createClient(ClientInputDTO dto) {
        Client client = dto.createClient();
        repository.add(client);
        return new ClientOutputDTO(client);
    }

    public List<ClientOutputDTO> getAll() {
        return repository.getAll().stream()
                .map(ClientOutputDTO::new)
                .collect(Collectors.toList());
    }
}
