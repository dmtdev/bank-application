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
        System.out.println("Current bank info:");
        System.out.println("Number of clients: " + bankReportService.getNumberOfClients(BankCommander.currentBank));
        System.out.println("Number of accounts: " + bankReportService.getNumberOfAccounts(BankCommander.currentBank));
        System.out.println("Total accounts sum: "+bankReportService.getTotalAccountSum(BankCommander.currentBank));
        System.out.println("Bank credit sum: "+bankReportService.getBankCreditSum(BankCommander.currentBank));
    }


    @Override
    public String printCommandInfo() {
        return "Get Bank Info";
    }
}
