package com.aldis.clientRest.repository.Service;



import com.aldis.clientRest.app.UserRest;
import com.aldis.clientRest.dto.ClientDTO;
import com.aldis.clientRest.dto.ClientListDTO;
import com.aldis.clientRest.entity.Client;
import com.aldis.clientRest.helper.UtilHelper;
import com.aldis.clientRest.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ClientService {

    private ClientRepository repository;
    private UserRest userRest;

    public ClientService(ClientRepository repository, UserRest userRest) {
        this.repository = repository;
        this.userRest = userRest;
    }

    public List<ClientListDTO> getAll(String token) throws AuthenticationException {
        userRest.authenticate(token);
        return repository.findAll().stream().map(item->{
            ClientListDTO clientListDTO = new ClientListDTO();
            clientListDTO.setId(item.getId());
            clientListDTO.setName(item.getName());
            clientListDTO.setLastName(item.getLastName());
            clientListDTO.setBirthDate(item.getBirthDate());
            clientListDTO.setAge(item.getAge());
            clientListDTO.setDateTeacherRetirement(UtilHelper.calculateDateTeacherRetirement(item.getBirthDate()));
            return clientListDTO;
        }).toList();
    }

    public Client create(String token,ClientDTO clientDTO) throws AuthenticationException {
        userRest.authenticate(token);

        if(!UtilHelper.isValidAge(clientDTO.getBirthDate(),clientDTO.getAge())){
            throw new UnsupportedOperationException("invalid date or age");
        }

        Optional<Client> optional = repository.findByNameAndLastNameAndAge(clientDTO.getName(), clientDTO.getLastName(), clientDTO.getAge());
        if (optional.isPresent()) {
            throw new UnsupportedOperationException("Customer already exists with the same name, surname and year of age ");
        }

        Client client = new Client();
        client.setName(clientDTO.getName());
        client.setLastName(clientDTO.getLastName());
        client.setAge(clientDTO.getAge());
        client.setBirthDate(clientDTO.getBirthDate());
        client.setCreatedAt(LocalDateTime.now());
        return repository.save(client);
    }

    public Map<String, Object> getMetrics(String token) throws AuthenticationException {
        userRest.authenticate(token);
        return repository.getMetrics();
    }
}
