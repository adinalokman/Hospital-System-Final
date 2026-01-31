package com.hospital.hms_app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    @Autowired 
    private PatientRepository patientRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    // PAGE 1: LANDING PAGE (Welcome) 
    @GetMapping("/")
    public String landingPage() {
        return "index"; // will open the index.html file
    }

    // PAGE 2: REGISTER PAGE for new user
    @GetMapping("/register-page")
    public String showRegisterPage() {
        return "register"; // open the file register.html
    }

    // PAGE 3: DASHBOARD 
    @GetMapping("/dashboard")
    public String showDashboard(Model model) {
        // send the patient and appoinment data to the dashboard
        model.addAttribute("patients", patientRepository.findAll());
        model.addAttribute("appointments", appointmentRepository.findAll());
        return "dashboard"; // open the dashboard.html file
    }

    // 1. UPDATE: change function 'addPatient' to edit ID 
    @PostMapping("/add")
    public String addPatient(
            @RequestParam(required = false) Long id, // if there is ID, that's mean we try to edit
            @RequestParam String name, 
            @RequestParam String email, 
            @RequestParam String medicalCondition,
            @RequestParam String phoneNumber,
            @RequestParam String address,     
            @RequestParam String bloodType,   
            @RequestParam String medicalHistory 
    ) {
        // store data in object
        Patient patient = new Patient(name, email, medicalCondition, phoneNumber, address, bloodType, medicalHistory);
        
        // Note: If the ID exist, we set the ID so that the base know we update and not INSERT
        if (id != null) {
            patient.setId(id);
        }
        
        patientRepository.save(patient);
        return "redirect:/dashboard";
    }

    // 2. NEW: function to open EDIT page
    @GetMapping("/edit/{id}")
    public String showEditForm(@org.springframework.web.bind.annotation.PathVariable Long id, Model model) {
        // find patient based on ID
        Patient existingPatient = patientRepository.findById(id).orElse(null);
        
        // send the patient data to edit
        model.addAttribute("patient", existingPatient);
        
        return "edit_patient"; 
    }

    // BOOK APPOINTMENT 
    @PostMapping("/book")
    public String bookAppointment(
            @RequestParam String patientName,
            @RequestParam String doctorName,
            @RequestParam String appointmentDate,
            @RequestParam String appointmentTime
    ) {
        Appointment newAppointment = new Appointment(patientName, doctorName, appointmentDate, appointmentTime, "Confirmed");
        appointmentRepository.save(newAppointment);
        return "redirect:/dashboard";
    }

    // DELETE / CANCEL 
    @PostMapping("/delete")
    public String deletePatient(@RequestParam Long id) {
        patientRepository.deleteById(id);
        return "redirect:/dashboard";
    }

    @PostMapping("/cancel")
    public String cancelAppointment(@RequestParam Long id) {
        appointmentRepository.deleteById(id);
        return "redirect:/dashboard";
    }
}