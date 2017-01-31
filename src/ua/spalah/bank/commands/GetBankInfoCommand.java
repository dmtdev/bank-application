package ua.spalah.bank.commands;

import ua.spalah.bank.services.BankReportService;

/**
 * Created by root on 12.01.2017.
 */
public class GetBankInfoCommand implements Command {

    private BankReportService bankReportService;

    public GetBankInfoCommand(BankReportService bankReportService) {
        this.bankReportService = bankReportService;
    }

    @Override
    public void execute() {
        //System.out.println(BankServerCommander.currentBank.getAllClients().size());

        System.out.println("Current bank info:");

        BankServerCommander.serverAnswer += "Current bank info:\n";
        //System.out.println("Number of clients: " + bankReportService.getNumberOfClients(BankCommander.currentBank));

        BankServerCommander.serverAnswer += "Number of clients: " + bankReportService.getNumberOfClients(BankServerCommander.currentBank)+"\n";

        //System.out.println("Number of accounts: " + bankReportService.getNumberOfAccounts(BankCommander.currentBank));
        BankServerCommander.serverAnswer += "Number of accounts: " + bankReportService.getNumberOfAccounts(BankServerCommander.currentBank)+"\n";

        //System.out.println("Total accounts sum: "+bankReportService.getTotalAccountSum(BankCommander.currentBank));
        BankServerCommander.serverAnswer += "Total accounts sum: "+bankReportService.getTotalAccountSum(BankServerCommander.currentBank)+"\n";

        //System.out.println("Bank credit sum: "+bankReportService.getBankCreditSum(BankCommander.currentBank));
        BankServerCommander.serverAnswer += "Bank credit sum: "+bankReportService.getBankCreditSum(BankServerCommander.currentBank)+"\n";

        //bankReportService.getClientsSortedByName(BankCommander.currentBank);
    }


    @Override
    public String getCommandInfo() {
        return "Get Bank Info";
    }
}
