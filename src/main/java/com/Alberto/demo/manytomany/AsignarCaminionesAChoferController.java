package com.Alberto.demo.manytomany;

import com.Alberto.demo.camion.Camion;
import com.Alberto.demo.camion.CamionService;
import com.Alberto.demo.chofer.Chofer;
import com.Alberto.demo.chofer.ChoferService;
import com.Alberto.demo.user.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Controller
public class AsignarCaminionesAChoferController {
    @Autowired
    private ChoferService choferService;
    @Autowired
    private CamionService camionService;
    private final AsignaChoferCamionService camion_choferService;

    @Autowired
    public AsignarCaminionesAChoferController(AsignaChoferCamionService camion_choferService) {
        this.camion_choferService = camion_choferService;
    }

    @GetMapping("/asignarcaminionesachofer")
    public String listaChofer_Camion(Model model){

            List<AsignarCaminionesAChofer> listVariada = camion_choferService.listadoPorFecha();
            model.addAttribute("listVariada", listVariada);
            return "asignarcaminionesachofer";

    }

    @GetMapping("/terminar_uso/{id}")
    public String terminar_usoCamion(@PathVariable("id")Integer id, RedirectAttributes ra){
       try {
           AsignarCaminionesAChofer camion_Chofer = camion_choferService.get(id);
           camion_Chofer.setFecha_termino(LocalDate.now());
           camion_choferService.salvar(camion_Chofer);
           Chofer chofer=choferService.get(camion_Chofer.getChofer().getId());
           Camion camion= camionService.get(camion_Chofer.getCamion().getId());
           camion.setUtilizado(false);
           camionService.salvar(camion);
           ra.addFlashAttribute("message","El camion matricula:"+ camion.getMatricula()+" ha terminado su asignacion al chofer"+ chofer.getNombre());
           return "redirect:/asignarcaminionesachofer";




       }catch (UserNotFoundException e){

           ra.addFlashAttribute("message","No se encontro ");
       }
        return "redirect:/asignarcaminionesachofer";
    }





}
