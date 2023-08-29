package com.Alberto.demo.chofer;

import com.Alberto.demo.user.User;
import com.Alberto.demo.user.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChoferService {

    private final ChoferRepository choferRepository;
    @Autowired
    public ChoferService(ChoferRepository choferRepository) {
        this.choferRepository = choferRepository;
    }

    public List<Chofer> listAll(){
        return (List<Chofer>)choferRepository.findAll();
    }

    public void salvar( Chofer chofer) {
        choferRepository.save(chofer);
    }
    public Chofer get(Integer id) throws UserNotFoundException {

        Optional<Chofer> result = choferRepository.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        throw new UserNotFoundException("No se Encontro al chofer especificado"+id);

    }
    public void delete(Integer id) throws UserNotFoundException{
        Long count= choferRepository.countById(id);
        if(count==null || count==0){
            throw new UserNotFoundException("No se Encontro al chofer especificado"+id);
        }
        choferRepository.deleteById(id);
    }


}
