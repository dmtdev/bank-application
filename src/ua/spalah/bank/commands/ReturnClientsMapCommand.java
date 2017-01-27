package ua.spalah.bank.commands;

import ua.spalah.bank.model.Client;
import ua.spalah.bank.services.BankReportService;

/**
 * Created by dd on 22.01.2017.
 */
public class ReturnClientsMapCommand implements Command {
    private BankReportService bankReportService;

    public ReturnClientsMapCommand(BankReportService bankReportService) {
        this.bankReportService = bankReportService;
    }

    @Override
    public void execute() {
//        for (Client c : BankCommander.currentBank.getClients() ) {
//            //System.out.println(c.getCity()+" "+c.toString());
//        }
//        bankReportService.getClientsByCity(BankCommander.currentBank);
    }

    @Override
    public String getCommandInfo() {
        return "Clients map";
    }
}
