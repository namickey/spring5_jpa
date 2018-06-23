package demo.controller.bar;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import demo.entity.NameRepo;
import demo.entity.NameT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class RegistBarController {

    @Autowired
    NameRepo nameRepo;

    @RequestMapping(value = "/bar/regist/index", method = RequestMethod.GET)
    public String index(Model model){
        System.out.println("RegistBarController:index:start");
        model.addAttribute("registBarForm", new RegistBarForm());
        return "bar/regist";
    }

    @RequestMapping(value = "/bar/regist/regist", method = RequestMethod.POST)
    public String regist(@Valid @ModelAttribute("registBarForm")RegistBarForm registBarForm, BindingResult result){
        System.out.println("●RegistBarController:regist:start");

        List<NameT> list = nameRepo.findAll();

        if(list == null){
            System.out.println("list is null.");
        } else {
            System.out.println(list.size());
        }

        for (NameT a : list) {
            System.out.println(a);
        }



        if(result.hasErrors()){
            return "bar/regist";
        }
        return "redirect:index";
    }
}
