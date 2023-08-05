package com.example.employeemanager.controler;


import com.example.employeemanager.dto.EmployeeDTO;
import com.example.employeemanager.entity.Employee;
import com.example.employeemanager.repository.EmployeeRepository;
import com.example.employeemanager.service.EmployeeService;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Optional;

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
    public String getHome(Model model) {
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

        if (employeeRepository.existsEmployeeByNameAndAndPrenom(employee.getName(),employee.getPrenom())){
            return "redirect:/home";
        }

        employeeservice.ajouter(employee);
        return "redirect:/home";

    }

    @GetMapping("/modifier/{id}")
    public String GetModifier(@PathVariable("id") Long id, Model model){
        Optional<Employee> employeeOptional = employeeservice.GetIdByEmployee(id);

        if (employeeOptional.isPresent()) {
            Employee employee = employeeOptional.get();
            model.addAttribute("employee",employee);
            return "main/modifier";
        } else {
            return "redirect:home";
        }

    }

    @PostMapping("/modifier/{id}")
    public String modifier(@PathVariable("id") Long id, @ModelAttribute EmployeeDTO employeeDTO) {
        Optional<Employee> employeeOptional = employeeservice.GetIdByEmployee(id);

        if (employeeOptional.isPresent()) {
            Employee employee = employeeOptional.get();
            employee.setName(employeeDTO.getNom());
            employee.setPrenom(employeeDTO.getPrenom());
            employee.setPoste(employeeDTO.getPoste());
            employee.setEmail(employeeDTO.getEmail());
            employee.setNum(employeeDTO.getNum());
            employee.setSalaire(employeeDTO.getSalaire());

            employeeservice.modifier(employee);

            return "redirect:/home";

        } else {
            return "redirect:/home";
        }
    }

    @GetMapping("/supp/{id}")
    public String Getsupp(@PathVariable("id") Long id) {
        employeeservice.supp(id);
        return "redirect:/home";
    }

    @GetMapping("/uploadFile")
    public String Getupload(Model model){
        model.addAttribute("title","Upload File");
        return "main/UploadFile";
    }

    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file){
        try{
            InputStream inputStream = file.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                        .setHeader(
                            "nom",
                            "prenom",
                            "poste",
                            "email",
                            "numero",
                            "salaire"
                        )
                    .setIgnoreHeaderCase(true)
                    .setSkipHeaderRecord(true)
                    .setTrim(true)
                    .build();

            CSVParser csvRecords = new CSVParser(bufferedReader,csvFormat);
            for(CSVRecord csvRecord : csvRecords){
                String name = csvRecord.get("nom");
                String prenom = csvRecord.get("prenom");
                String poste = csvRecord.get("poste");
                String email = csvRecord.get("email");
                String num = csvRecord.get("numero");
                String salaire = csvRecord.get("salaire");

                employeeRepository.save(new Employee(name,prenom,poste,num,email,salaire));
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        return "redirect:/home";
    }


}
