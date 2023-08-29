package com.Alberto.demo.camion;


import com.Alberto.demo.chofer.Chofer;
import com.Alberto.demo.chofer.ChoferService;
import com.Alberto.demo.manytomany.AsignaChoferCamionService;
import com.Alberto.demo.manytomany.AsignarCaminionesAChofer;
import com.Alberto.demo.manytomany.AsignarCamionChoferRepo;
import com.Alberto.demo.user.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.swing.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.Optional;

@Controller
public class CamionController {
    @Autowired
    private ChoferService choferService;
    @Autowired
    private AsignaChoferCamionService asignaChoferCamionService;
    private final CamionService camionService;
    @Autowired
    public CamionController(CamionService camionService) {
        this.camionService = camionService;
    }


    @GetMapping("/camion")
    public String showChoferList(Model model, @Param("busqueda")String busqueda){

        List<Camion> listCamiones = camionService.listaBuscar(busqueda);
        model.addAttribute("busqueda",busqueda);
        model.addAttribute("listCamiones",listCamiones);

        return "camion";
    }
     @GetMapping("/camion_list")
    public String listCamiones (Model model) throws Exception {
         List<Chofer> listChoferes = choferService.listAll();
         List<Camion> listCam = camionService.listCamionNoUso(false);
         model.addAttribute("listCam",listCam);

         return "camion_list";
      }
   /* @GetMapping("/camion_list")
    public ResponseEntity<?>seach(@RequestParam boolean mat) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(camionService.listCamionNoUso(mat));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"error\":\"" + e.getMessage() + "\"}"));
        }
    }*/



    @GetMapping("/camion/new")
    public String showNewForm(Model model){
        //List<Chofer>listChoferes = choferService.listAll();
       // model.addAttribute("listChoferes", listChoferes);
        model.addAttribute("camion", new Camion());
        model.addAttribute("pageTitle", "Añadir Nuevo Camion");
        return  "camion_form";
    }

    @PostMapping("/camion/save")
    public String saveCamiones(Camion camion, RedirectAttributes ra){
        camionService.salvar(camion);
        ra.addFlashAttribute("message","El Camion se Inserto Correctamente");


        return "redirect:/camion";
    }
   /* @GetMapping("/camion/edit/{id}")
    public String showEditForm(@PathVariable("id")Integer id,Model model, RedirectAttributes ra){
        try{
            Camion camion = camionService.get(id);
            model.addAttribute("camion",camion);
            model.addAttribute("pageTitle", "Editar Camion (ID: " + id +")");

            return "camion_form";
        }catch (UserNotFoundException e){
            ra.addFlashAttribute("message",e.getMessage());
            return "redirect:/camion";
        }

    }*/
    @GetMapping("/camion/edit/{id}")

    public String mostrarFormularioEditar(@PathVariable("id")Integer id,Model model, RedirectAttributes ra){
       try {
           Camion camion = camionService.get(id);
           model.addAttribute("camion",camion );
       }catch (UserNotFoundException e){
           ra.addFlashAttribute("message",e.getMessage());
       }
        return "camion_editar";
    }
    @PostMapping("/camion/{id}")
    public String actualizar(@PathVariable("id") Integer id, @ModelAttribute("camion")Camion camion, Model model,
    RedirectAttributes ra){
        Camion camionExistente= new Camion();
        try {
            camionExistente = camionService.get(id);
            model.addAttribute("camion",camion);
            model.addAttribute("pageTitle", "Editar Camion (Matricula: "+" " + camionExistente.getMatricula() +" "+")");
            camionExistente.setId(id);
            camionExistente.setMatricula(camion.getMatricula());
            camionExistente.setCapacidad(camion.getCapacidad());
            camionExistente.setRuedas(camion.getRuedas());
            if(camionExistente.getKilometraje()>camion.getKilometraje()){
                ra.addFlashAttribute("message","El Camion con Matricula" +" "+ camionExistente.getMatricula() +" "+" y Kilometraje: "+" "+camionExistente.getKilometraje()+" "+" Kilometraje no puede ser menor al existente");
                return "redirect:/camion/edit/{id}";
            } else if (camionExistente.getKilometraje()<camion.getKilometraje()) {
                camionExistente.setKilometraje(camion.getKilometraje());
            }
            if(camionExistente.getCombustible().equalsIgnoreCase(camion.getCombustible())){
                camionExistente.setCombustible(camion.getCombustible());
                camionService.salvar(camionExistente);
                ra.addFlashAttribute("message","El Camion Matricula "+ camionExistente.getMatricula() +"se Actualizo Correctamente");
                return "redirect:/camion";
            }else
                ra.addFlashAttribute("message","El Camion con Matricula" +camionExistente.getMatricula() +" y tipo de combustible"+" "+ camionExistente.getCombustible()+" " +" Combustible No puede ser editado");
            return "redirect:/camion/edit/{id}";
        }catch (UserNotFoundException e){
            ra.addFlashAttribute("message",e.getMessage());
        }


        return "redirect:/camion";
    }
    @GetMapping("/camion/delete/{id}")
    public String deleteCamion(@PathVariable("id")Integer id,RedirectAttributes ra){
        try{
            Camion control= camionService.get(id);

            if(control.isUtilizado()){
                ra.addFlashAttribute("message2","El Camion con Matricula" +control.getMatricula()  +" no se puede eliminar: Se encuentra en uso");
                return "redirect:/camion";
            }
                camionService.delete(id);
                Camion camion = camionService.get(id);

                ra.addFlashAttribute("message", "El Camion con Matricula" + camion.getMatricula() + " ha sido eliminado exitósamente");

        }catch (UserNotFoundException e){
            ra.addFlashAttribute("message",e.getMessage());

        }
        return "redirect:/camion";
    }
    @GetMapping("/camion_list/asignar_camion/{id}")
    public String asignarCamion(@PathVariable("id")Integer id, RedirectAttributes ra){
        try {
           Camion camion = camionService.get(id);
           camionService.setCamionAuxiliar(camion);


            ra.addFlashAttribute("message","El camion matricula:"+ camion.getMatricula()+" ha sido seleccionado correctamente");



            return "redirect:/chofer_list";
        }catch (UserNotFoundException e){
            ra.addFlashAttribute("message",e.getMessage());
        }
        return "redirect:/camion_list";
    }
    @GetMapping("/chofer_list")
    public String listChoferes (Model model) throws Exception {
        List<Chofer> listChoferes = choferService.listAll();


        model.addAttribute("listChoferes",listChoferes);
        return "chofer_list";
    }
    @GetMapping("chofer_list/asignar_chofer/{id}")
    public String asignarChofer(@PathVariable("id")Integer id, RedirectAttributes ra){
        try {
            Camion camion = camionService.getCamionAuxiliar();

            Chofer chofer = choferService.get(id);
            AsignarCaminionesAChofer cc=new AsignarCaminionesAChofer(camion,chofer,LocalDate.now());
            camion.setUtilizado(true);
            camionService.salvar(camion);

            asignaChoferCamionService.salvar(cc);
            ra.addFlashAttribute("message","El camion matricula:"+ camion.getMatricula()+" ha sido seleccionado correctamente");



            return "redirect:/camion_list";


        }catch (UserNotFoundException e){
            ra.addFlashAttribute("message",e.getMessage());
        }
        return "redirect:/camion_list";
    }






}
