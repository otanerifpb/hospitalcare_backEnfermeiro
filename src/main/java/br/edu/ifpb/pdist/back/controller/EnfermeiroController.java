package br.edu.ifpb.pdist.back.controller;

import br.edu.ifpb.pdist.back.model.Enfermeiro;
import br.edu.ifpb.pdist.back.repository.EnfermeiroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@RestController
//@CrossOrigin(origins = "*")
//@CrossOrigin(origins = "https://hospitalview-8171b04dfdb4.herokuapp.com")
@RequestMapping("/enfermeiro")
public class EnfermeiroController {

    @Autowired
    private EnfermeiroRepository enfermeiroRepository;
   
    // Ativa o menu Enfermeiro na barra de navegação
    // @ModelAttribute("menu")
    // public String activeMenu(){
    //     return "enfermeiro";
    // }

    // Rota para acessar a lista pelo menu
    @RequestMapping(method = RequestMethod.GET)
    public List<Enfermeiro> listAll(ModelAndView mav) {
        List<Enfermeiro> opEnfermeiros = enfermeiroRepository.findAll();
        return opEnfermeiros;
    } 

    // Rota para acessar a lista ao usar o REDIRECT
    @RequestMapping("/")
    public List<Enfermeiro> listAll(Model model) {
       return enfermeiroRepository.findAll();
    }

    // Rota para acessar o formulário
    @RequestMapping("/formEnfermeiro")
    public ModelAndView getFormEnfermeiro(Enfermeiro enfermeiro, ModelAndView mav) {
        mav.addObject("enfermeiro", enfermeiro);
        mav.setViewName("enfermeiro/formEnfermeiro");
        return mav;
    }

    // Rota para cadastrar um Enfermeiro no Sitema
    @RequestMapping(value="/save", method = RequestMethod.POST)
    public ResponseEntity<Enfermeiro> save(@RequestBody Enfermeiro enfermeiro, RedirectAttributes redAttrs) {
        Optional<Enfermeiro> OpEnfermeiro = enfermeiroRepository.findByCoren(enfermeiro.getCoren());
        if (!OpEnfermeiro.isPresent()){
            Enfermeiro novoEnfermeiro = enfermeiroRepository.save(enfermeiro);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoEnfermeiro);
        }
        return null;
   }

    // Rota para preencer os dados do formunlário de atualização com dados do banco 
    @RequestMapping("/{id}")
    public Enfermeiro getEnfermeiroById(@PathVariable(value = "id") Integer id, ModelAndView mav) {
        Optional<Enfermeiro> opEnfermeiro = enfermeiroRepository.findById(id);
        if (opEnfermeiro.isPresent()) {
            Enfermeiro enfermeiro = opEnfermeiro.get();
            return enfermeiro;
        } 
        return null;
    }
    
    // Rota para atualizar um Enfermeiro na lista pelo formUpEnfermeiro
    @RequestMapping(value="/update", method = RequestMethod.POST)
    public ResponseEntity<Enfermeiro> update(@RequestBody Enfermeiro enfermeiro, RedirectAttributes redAttrs) {
        Enfermeiro upDateEnfermeiro = enfermeiroRepository.save(enfermeiro);
        return ResponseEntity.status(HttpStatus.OK).body(upDateEnfermeiro);
    }

    // Rota para deletar um Enfermeiro da lista
    @RequestMapping("/delete/{id}")
    public void excluirEnfermeiro(@PathVariable(value = "id") Integer id) {
        Optional<Enfermeiro> OpEnfermeiro = enfermeiroRepository.findById(id);
        if (OpEnfermeiro.isPresent()){
            Enfermeiro enfermeiro = OpEnfermeiro.get();
            enfermeiroRepository.delete(enfermeiro);
        }
    }

}
