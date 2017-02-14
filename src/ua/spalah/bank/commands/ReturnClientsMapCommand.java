package ua.spalah.bank.commands;

import ua.spalah.bank.io.sockets.IO;
import ua.spalah.bank.services.BankReportService;

/**
 * Created by dd on 22.01.2017.
 */
public class ReturnClientsMapCommand extends AbstractCommand  {
    private BankReportService bankReportService;

    public ReturnClientsMapCommand(BankReportService bankReportService,IO io) {
        super(io);
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
