package controller.command;

import model.DAO.ClientDAO;
import model.entity.Client;
import model.service.CheckClient;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();

        String name = request.getParameter("Login");
        String pass = request.getParameter("Password");

        if (name == null || name.equals("") || pass == null || pass.equals("")) {
            return "redirect:/login.jsp";
        }
        /**
         * check user in DB
         */
        if (!CheckClient.isValidClient(name, pass)) {
            System.out.println("no such client");
            return "redirect:/index.jsp";
        }
        /**
         * set User to Session
         */
        Client client = (Client) session.getAttribute("client");
        if (client == null) {
            client = ClientDAO.getClient(name);
            session.setAttribute("role", client.getRole_id());
            session.setAttribute("client", client);

            System.out.println("role in LoginCommand: "+client.getRole_id());
            System.out.println("client in LoginCommand: "+client.getLogin());
            /**
             * check if User is Banned
             */
            if (client.getStatus().equals("BANNED")) {
                session.setAttribute("role", 0);
                session.setAttribute("client", null);
                return "redirect:/index.jsp";
            }
        }

        /**
         * All checks passed, send to Page
         */
        System.out.println("ROLE IN LoginCommand(): "+(int)session.getAttribute("role"));
        if((int)session.getAttribute("role")==3){
            return "/WEB-INF/admin/welcomeAdmin.jsp";
        }
        if((int)session.getAttribute("role")==2){
            return "/WEB-INF/manager/welcomeManager.jsp";
        }
            return "/WEB-INF/client/menu.jsp";
    }
}
