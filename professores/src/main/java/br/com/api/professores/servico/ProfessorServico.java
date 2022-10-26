package br.com.api.professores.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.api.professores.modelo.ProfessorModelo;
import br.com.api.professores.modelo.RespostaModelo;
import br.com.api.professores.repositorio.ProfessorRepositorio;

@Service
public class ProfessorServico {

    @Autowired
    private ProfessorRepositorio pr;

    @Autowired
    private RespostaModelo rm;

    // Método de listar todos os professores
    public Iterable<ProfessorModelo> listar() {
        return pr.findAll();
    }

    // Método para cadastrar e alterar produtos
    public ResponseEntity<?> cadastrarAlterar(ProfessorModelo pm, String acao) {
        if (pm.getNome().equals("")) {
            rm.setMsg("Nome do professor é obrigatório!");
            return new ResponseEntity<RespostaModelo>(rm, HttpStatus.BAD_REQUEST);
        } else if (pm.getCpf().equals("")) {
            rm.setMsg("O cpf é obrigatório");
            return new ResponseEntity<RespostaModelo>(rm, HttpStatus.BAD_REQUEST);
        } else if (pm.getData_nascimento().equals("")) {
            rm.setMsg("A data de nascimento é obrigatório");
            return new ResponseEntity<RespostaModelo>(rm, HttpStatus.BAD_REQUEST);
        } else if (pm.getSexo().equals("")) {
            rm.setMsg("O sexo é obrigatório");
            return new ResponseEntity<RespostaModelo>(rm, HttpStatus.BAD_REQUEST);
        } else if (pm.getNome().length() > 100) {
            rm.setMsg("Limite de 100 caracteres ultrapassados");
            return new ResponseEntity<RespostaModelo>(rm, HttpStatus.BAD_REQUEST);
        } else if (pm.getCpf().length() != 11) {
            rm.setMsg("CPF inválido");
            return new ResponseEntity<RespostaModelo>(rm, HttpStatus.BAD_REQUEST);
        } else {
            if (acao.equals("cadastrar")) {
                return new ResponseEntity<ProfessorModelo>(pr.save(pm), HttpStatus.CREATED);
            } else {
                return new ResponseEntity<ProfessorModelo>(pr.save(pm), HttpStatus.OK);
            }
        }
    }

    // Método para remover professores
    public ResponseEntity<RespostaModelo> remover(long id) {
        pr.deleteById(id);
        rm.setMsg("O professor foi removido com sucesso!");
        return new ResponseEntity<RespostaModelo>(rm, HttpStatus.OK);
    }

}
