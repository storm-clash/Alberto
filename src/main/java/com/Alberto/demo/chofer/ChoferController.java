package com.Alberto.demo.chofer;

import com.Alberto.demo.camion.Camion;
import com.Alberto.demo.camion.CamionService;
import com.Alberto.demo.manytomany.AsignaChoferCamionService;
import com.Alberto.demo.manytomany.AsignarCaminionesAChofer;
import com.Alberto.demo.user.User;
import com.Alberto.demo.user.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;

@Controller
public class ChoferController {
    @Autowired
    private CamionService camionService;
    @Autowired
    private AsignaChoferCamionService asignaChoferCamionService;
    private final ChoferService choferService;
    @Autowired
    public ChoferController(ChoferService choferService) {
        this.choferService = choferService;
    }


    @GetMapping("/chofer")
    public String showChoferList(Model model){
        List<Chofer> listChoferes = choferService.listAll();
        model.addAttribute("listChoferes",listChoferes);

        return "chofer";
    }
    @GetMapping("/chofer/new")
    public String showNewForm(Model model){
        model.addAttribute("chofer", new Chofer());
        model.addAttribute("pageTitle", "Añadir Nuevo Chofer");
        return  "chofer_form";
    }

    @PostMapping("/chofer/save")
    public String saveChoferes(Chofer chofer, RedirectAttributes ra,Model model){
        model.addAttribute("chofer",chofer);
        double salarioRestado= 12000*0.21;

        if(salarioRestado>= chofer.getSalario()){
            model.addAttribute("message", "El Chofer No puede tener salario menor a"+ salarioRestado);

            return "chofer_form";
        }
        choferService.salvar(chofer);
        ra.addFlashAttribute("message","El Chofer se Inserto Correctamente");


        return "redirect:/chofer";
    }
    @GetMapping("/chofer/edit/{id}")
    public String showEditForm(@PathVariable("id")Integer id, Model model, RedirectAttributes ra){
        try{
            Chofer chofer = choferService.get(id);
            model.addAttribute("chofer",chofer);
            model.addAttribute("pageTitle", "Editar Chofer (ID: " + id +")");

            return "chofer_form";
        }catch (UserNotFoundException e){
            ra.addFlashAttribute("message",e.getMessage());
            return "redirect:/chofer";
        }

    }
    @GetMapping("/chofer/delete/{id}")
    public String deleteChofer(@PathVariable("id")Integer id,RedirectAttributes ra){
        try{
           Camion camion=new Camion();
            List<AsignarCaminionesAChofer> mix=asignaChoferCamionService.listadoporChofer(id);
           for(AsignarCaminionesAChofer lista:mix){
              if(lista.getChofer().getId()==id && lista.getFecha_termino()==null){

               lista.setFecha_termino(LocalDate.now());
               asignaChoferCamionService.salvar(lista);
               camion=camionService.get(lista.getCamion().getId());
               camion.setUtilizado(false);
               camionService.salvar(camion);



           }
       }


            choferService.delete(id);
            ra.addFlashAttribute("message","El Chofer con ID" +id +" ha sido eliminado");



        }catch (UserNotFoundException e){
            ra.addFlashAttribute("message",e.getMessage());

        }
        return "redirect:/chofer";
    }

}
