package gestion.incident.web;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import gestion.incident.service.VehiculeService;
import gestion.incident.service.dto.VehiculeDto;
/**
* Controlleur REST pour gérer les véhicules.
*/
@RestController
@RequestMapping("/api")
public class VehiculeResource {
private final VehiculeService vehiculeService;
@Autowired
public VehiculeResource(VehiculeService vehiculeService) {
this.vehiculeService = vehiculeService;
}
// Implémentations des méthodes @RequestMapping Get et Post
/**
* GET /vehicules : Get véhicule par imatriculation.
*/
@RequestMapping(value = "/vehicules/{immatriculation}", method =RequestMethod.GET)
@ResponseStatus(HttpStatus.OK)
public @ResponseBody VehiculeDto getVehiculeImmat(@PathVariable String immatriculation) {
	VehiculeDto vehiculeDTO =this.vehiculeService.getVehiculeParImmatriculation(immatriculation);
return vehiculeDTO;
}
@CrossOrigin
@RequestMapping(value = "/vehicules", method =RequestMethod.GET)
@ResponseStatus(HttpStatus.OK)
public @ResponseBody List<VehiculeDto> getVehicules() {
	List<VehiculeDto> vehiculeDTO =this.vehiculeService.getTousLesVehicules();
return vehiculeDTO;
}
@RequestMapping(value = "/vehicules", method = RequestMethod.POST)
@ResponseStatus(HttpStatus.CREATED)
public @ResponseBody VehiculeDto creerVehicule(@RequestBody VehiculeDto
vehiculeVM) {
   vehiculeService.creerVehicule(vehiculeVM);
System.out.println("# Véhicule créé: " +
vehiculeVM.getCodeInterne() + ", " + vehiculeVM.getImmatriculation());
return vehiculeVM;
}
}