package com.example.employeemanager.controler;


import com.example.employeemanager.dto.EmployeeDTO;
import com.example.employeemanager.entity.Employee;
import com.example.employeemanager.repository.EmployeeRepository;
import com.example.employeemanager.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class EmployeeController {

  private EmployeeService employeeservice;
    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeService employeeservice,
                              EmployeeRepository employeeRepository) {
        this.employeeservice = employeeservice;
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("/home")
    public String getString(Model model) {
        model.addAttribute("employees", employeeRepository.findAll());
        return "main/home";

    }

    @GetMapping("/ajouter")
    public String GetAjout(){
        return "main/ajouter";
    }

    @PostMapping("/ajouter")
    public String ajouter(@ModelAttribute EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setName(employeeDTO.getNom());
        employee.setPrenom(employeeDTO.getPrenom());
        employee.setPoste(employeeDTO.getPoste());
        employee.setNum(employeeDTO.getNum());
        employee.setEmail(employeeDTO.getEmail());
        employee.setSalaire(employeeDTO.getSalaire());

        employeeservice.ajouter(employee);

        return "redirect:/home";

    }


}
