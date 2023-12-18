package br.edu.ifpb.pdist.back.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifpb.pdist.back.model.Recepcionista;
import br.edu.ifpb.pdist.back.repository.RecepcionistaRepository;

@RestController
//@CrossOrigin(origins = "https://hospitalview-8171b04dfdb4.herokuapp.com")
@RequestMapping("/recepcionista")
public class RecepcionistaController {
    
    @Autowired
    private RecepcionistaRepository recepcionistaRepository;
    
     // Ativa o menu Recepcionista na barra de navegação
    // @ModelAttribute("menu")
    // public String activeMenu(){
    //     return "recepcionista";
    // }

    // Rota para acessar a lista pelo menu
    @RequestMapping(method = RequestMethod.GET)
    public List<Recepcionista> listAll(ModelAndView mav) {
        List<Recepcionista> opRecepcionistas = recepcionistaRepository.findAll();
        return opRecepcionistas;
    } 

    // Rota para acessar a lista ao usar o REDIRECT
    @RequestMapping("/")
    public List<Recepcionista> listAll(Model model) {
       return recepcionistaRepository.findAll();
    }

    // Rota para acessar o formunário
    // @RequestMapping("/formRecepcionista")
    // public ModelAndView getFormRecepcionista(Recepcionista recepcionista, ModelAndView mav) {
    //     mav.addObject("recepcionista", recepcionista);
    //     mav.setViewName("recepcionista/formRecepcionista");
    //     return mav;
    // }

    // Rota para cadastrar um Recepcionista no Sistema
    @RequestMapping(value="/save", method = RequestMethod.POST)
    public ResponseEntity<Recepcionista> save(@RequestBody Recepcionista recepcionista, RedirectAttributes redAttrs) {
        Optional<Recepcionista> OpRecepcionista = recepcionistaRepository.findByMatricula(recepcionista.getMatricula());
        if (!OpRecepcionista.isPresent()){       
            Recepcionista novoRecepcionista = recepcionistaRepository.save(recepcionista);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoRecepcionista); 
        }
        return null;
   }

    // Rota para preencer os dados do formunlário de atualização com dados do banco 
    @RequestMapping("/{id}")
    public Recepcionista getRecepcionistaById(@PathVariable(value = "id") Integer id, ModelAndView mav) {
        Optional<Recepcionista> opRecepcionista = recepcionistaRepository.findById(id);
        if (opRecepcionista.isPresent()) {
            Recepcionista recepcionista = opRecepcionista.get();
            return recepcionista;
        } 
        return null;
    }
    
    // Rota para atualizar um Recepcionista na lista pelo formUpRecepcionista
    @RequestMapping(value="/update", method = RequestMethod.POST)
    public ResponseEntity<Recepcionista> update(@RequestBody Recepcionista recepcionista, RedirectAttributes redAttrs) {
        Recepcionista upDateRecepcionista = recepcionistaRepository.save(recepcionista);
        return ResponseEntity.status(HttpStatus.OK).body(upDateRecepcionista); 
    }

    // Rota para deletar um Recepcionista da lista
    @RequestMapping("/delete/{id}")
    public void excluirrecepcionista(@PathVariable(value = "id") Integer id) {
        Optional<Recepcionista> OpRecepcionista = recepcionistaRepository.findById(id);
        if (OpRecepcionista.isPresent()){
            Recepcionista recepcionista = OpRecepcionista.get();
            recepcionistaRepository.delete(recepcionista);
        }
    }
}
