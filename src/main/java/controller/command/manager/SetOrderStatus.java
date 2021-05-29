package controller.command.manager;

import controller.command.Command;
import model.DAO.OrderDAO;

import javax.servlet.http.HttpServletRequest;

public class SetOrderStatus implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        String reason = request.getParameter("orderStatus");
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        OrderDAO.setOrderStatus(orderId,reason);

        return "/managerOrders";
    }
}