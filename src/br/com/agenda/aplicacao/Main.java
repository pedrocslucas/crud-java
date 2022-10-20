package br.com.agenda.aplicacao;

import br.com.agenda.dao.ContatoDAO;
import br.com.agenda.model.Contato;
import java.util.Date;

/*MVC
    Model
    View
    Controller
*/

public class Main {

    public static void main(String[] args) {
        
        ContatoDAO contatoDao = new ContatoDAO();
        
        Contato contato = new Contato();
        /*
        contato.setNome("Josué Silva");
        contato.setIdade(32);
        contato.setDataCadastro(new Date());
        */
        
        //contatoDao.save(contato);
        
        //Listagem dos dados
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
        System.out.println("Lista de Contatos:");
        for(Contato cont : contatoDao.getContatos()){
            System.out.println("Id: "+cont.getId());
            System.out.println("Nome: "+cont.getNome());
            System.out.println("Idade: "+cont.getIdade());
            System.out.println("Data de Registro: "+cont.getDataCadastro());
            System.out.println("----------------------");
        }
        
        //Atualizando os dados
        //contato.setId(2);//É referente ao número do id que está no sistema
        contatoDao.update(contato);
        
        //Deletando os dados
        int id = 2;
        contatoDao.deleteById(id);
    }
    
}
