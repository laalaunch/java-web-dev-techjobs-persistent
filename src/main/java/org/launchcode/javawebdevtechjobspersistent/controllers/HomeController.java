package org.launchcode.javawebdevtechjobspersistent.controllers;

import org.launchcode.javawebdevtechjobspersistent.models.Employer;
import org.launchcode.javawebdevtechjobspersistent.models.Job;
import org.launchcode.javawebdevtechjobspersistent.models.Skill;
import org.launchcode.javawebdevtechjobspersistent.models.data.EmployerRepository;
import org.launchcode.javawebdevtechjobspersistent.models.data.JobRepository;
import org.launchcode.javawebdevtechjobspersistent.models.data.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * Created by LaunchCode
 */
@Controller
public class HomeController {

    @Autowired
    private EmployerRepository employerRepository;

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private JobRepository jobRepository;

    @RequestMapping("")
    public String index(Model model) {

        model.addAttribute("title", "My Jobs");
        model.addAttribute("jobs", jobRepository.findAll());

        return "index";
    }

    @GetMapping("add")
    public String displayAddJobForm(Model model) {
        model.addAttribute("title", "Add Job");
        model.addAttribute(new Job());
        model.addAttribute("employers", employerRepository.findAll());
        model.addAttribute("skills", skillRepository.findAll());
        model.addAttribute("jobs", jobRepository.findAll());
        return "add";
    }
    @RequestMapping(value="/add",method=RequestMethod.POST,params="employerId")
public String processAddJobWithoutSkills(@ModelAttribute @Valid Job newJob,
                                         @RequestParam Integer employerId,
                                         Errors errors, Model model){
        model.addAttribute("Error", "Must select a Skill");
        model.addAttribute("employers", employerRepository.findAll());
        model.addAttribute("skills", skillRepository.findAll());
        model.addAttribute("title", "Add Job");
        return "add";
    }

@RequestMapping(value="/add",method=RequestMethod.POST,params={"employerId","skills"})
   // @PostMapping("add")
    public String processAddJobForm(@ModelAttribute @Valid Job newJob,
                                    @RequestParam Integer employerId,
                                    @RequestParam List<Integer> skills,
                                    Errors errors, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Job");
            return "add";
        }

        if (employerId != null || !skills.isEmpty()) {
            Optional<Employer> emp = employerRepository.findById(employerId);
                if (emp.isPresent()) {
                    Employer employer = emp.get();
                    newJob.setEmployer(employer);

        }
            List<Skill> skillObjs = (List<Skill>) skillRepository.findAllById(skills);
            if (!skillObjs.isEmpty()) {
                newJob.setSkills(skillObjs);
        }
            jobRepository.save(newJob);
            return "redirect:/view/" + newJob.getId();
        }
            return "redirect:../add";
    }

    @GetMapping("view/{jobId}")
    public String displayViewJob(Model model, @PathVariable int jobId) {
        Optional<Job> tempJob = jobRepository.findById(jobId);
        Job result = tempJob.get();
        model.addAttribute(result);
        return "view";
    }


}
