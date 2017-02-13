package ua.spalah.bank.commands;

import ua.spalah.bank.io.sockets.IO;
import ua.spalah.bank.services.BankReportService;

/**
 * Created by root on 12.01.2017.
 */
public class GetBankInfoCommand extends AbstractCommand {

    private BankReportService bankReportService;

    public GetBankInfoCommand(BankReportService bankReportService, IO io) {
        super(io);
        this.bankReportService = bankReportService;
    }

    @Override
    public void execute() {
        write("Current bank info:");
        write("Number of clients: " + bankReportService.getNumberOfClients(BankServerCommander.currentBank));
        write("Number of accounts: " + bankReportService.getNumberOfAccounts(BankServerCommander.currentBank));
        write("Total accounts sum: " + bankReportService.getTotalAccountSum(BankServerCommander.currentBank));
        write("Bank credit sum: " + bankReportService.getBankCreditSum(BankServerCommander.currentBank));
    }

    @Override
    public String getCommandInfo() {
        return "Get Bank Info";
    }
}
