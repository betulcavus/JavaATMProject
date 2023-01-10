package atmproject;

import java.text.DecimalFormat;
import java.util.Scanner;

public class Account {

    private int accountNumber;//hesap numarasi
    private int pinNumber;//sifre
    private double checkingBalance;//vadesiz hesap bakiyesi
    private double savingBalance;//vadeli hesap bakiyesi

    DecimalFormat moneyFormat = new DecimalFormat("'$'###,##0.00");

    Scanner input = new Scanner(System.in);
    
    public int getAccountNumber() {
        return accountNumber;
    }
    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }
    public int getPinNumber() {
        return pinNumber;
    }
    public void setPinNumber(int pinNumber) {
        this.pinNumber = pinNumber;
    }
    public double getCheckingBalance() {
        return checkingBalance;
    }
    public void setCheckingBalance(double checkingBalance) {
        this.checkingBalance = checkingBalance;
    }
    public double getSavingBalance() {
        return savingBalance;
    }
    public void setSavingBalance(double savingBalance) {
        this.savingBalance = savingBalance;
    }
    public Scanner getInput() {
        return input;
    }
    public void setInput(Scanner input) {
        this.input = input;
    }

    //para cekme  => checkingHesabindaParaCekmeIslemindenSonraKalanMiktar
    private double calculateCheckingBalanceAfterWithdraw(double amount){

        checkingBalance =  checkingBalance - amount;

        return checkingBalance;
    }

    //para yatirma => checkingHesabindaParaYatirmaIslemindenSonraKalanMiktar
    private double calculateCheckingBalanceAfterDeposit(double amount){

        checkingBalance =  checkingBalance + amount;

        return checkingBalance;
    }

    //para cekme => savingHesabindaParaCekmeIslemindenSonraKalanMiktar
    private double calculateSavingBalanceAfterWithdraw(double amount){

        savingBalance = savingBalance - amount;

        return savingBalance;
    }

    //para yatirma => savingHesabindaParaYatirmaIslemindenSonraKalanMiktar
    private double calculateSavingBalanceAfterDeposit(double amount){

        savingBalance =  savingBalance + amount;

        return  savingBalance;
    }

    //son bakiyeyi goster methodu
    public void displayCurrentAmount(double balance){
        System.out.println("hesabinizda bulunan bakiye: "+ moneyFormat.format(balance));
    }

    //Musteri ile para cekmek icin etkilesime gec: checking hesap
    public void getCheckingWithdraw(){
        displayCurrentAmount(checkingBalance);
        System.out.println("Cekmek istediginiz miktari giriniz:");
        double amount = input.nextDouble();
        if(amount <= 0) {
            System.out.println("Sifir veya eksi rakamlar gecersizdir!");
            getCheckingWithdraw(); //recursive method-methodu tekrar cagirma
        }else if(amount <= checkingBalance){
            calculateCheckingBalanceAfterWithdraw(amount);
            displayCurrentAmount(checkingBalance);
        }else
            System.out.println("Yetersiz bakiye");
    }

    //Musteri ile para yatirmak icin etkilesime gec: checking hesap
    public void getCheckingDeposit(){
        displayCurrentAmount(checkingBalance);
        System.out.println("Yatirmak istediginiz miktari giriniz:");
        double amount = input.nextDouble();
        if(amount <= 0){
            System.out.println("Sifir veya eksi rakamlar gecersizdir!");
            getCheckingDeposit();
        }else {
            calculateCheckingBalanceAfterDeposit(amount);
            System.out.println();
            displayCurrentAmount(checkingBalance);
        }
    }

    //Musteri ile para cekmek icin etkilesime gec: saving hesap
    public void getSavingWithdraw() {
        displayCurrentAmount(savingBalance);
        System.out.println("Cekmek istediginiz mikatri giriniz:");
        double amount = input.nextDouble();
        if(amount <= 0){
            System.out.println("Sifir veya eksi rakamlar gecersizdir!");
            getSavingWithdraw();
        }else if(amount <= savingBalance){
            calculateSavingBalanceAfterWithdraw(amount);
            System.out.println();
            displayCurrentAmount(savingBalance);
        }else {
            System.out.println("Yetersiz bakiye");
        }
    }

    //Musteri ile para yatirmak icin etkilesime gec: saving hesap
    public void getSavingDeposit(){
        displayCurrentAmount(savingBalance);
        System.out.println("Yatirmak istediginiz meblayi giriniz:");
        double amount = input.nextDouble();
        if(amount <= 0 ){
            System.out.println("Sifir veya eksi rakamlar gecersizdir!");
            getSavingDeposit();
        }else{
            calculateSavingBalanceAfterDeposit(amount);
            displayCurrentAmount(savingBalance);
        }
    }

}

/*
        checking account => vadesiz hesap
        saving account => vadeli hesap
        para cekme : withdraw
        para yatirma : deposit
        amount : miktar
        double checkingBalance; checking icin bakiye
        double savingBalance;  saving bakiye
        int accountNumber = hesap numarasi
        int pinNumber =  sifre

        checkingBalance = 100
        double paraIstiyorum = 50
                             100                 50
        checkingBalance = checkingBalance  - paraIstiyorum;
        return checkingBalance;

        savingBalance = 1000
        amount = 2000
            2000         1000
        if(amount <= savingBalance){
        }

        double checkingBalance;//vadesiz hesap
        double savingBalance;//vadeli hesap
        checking hesaptan para cekiniz
        checking hesaba para yatiriniz
        saving hesaptan para cekiniz
        saving hesaba para yatiriniz
        checkingIslemleri(filtreleme): checking hesaptan para cekiniz
        checking hesaba para yatirma islemleri(filtreleme):
        checking hesaba para yatiriniz
        savingIslemleri(filtreleme):saving hesaptan para cekiniz
        saving hesaba para yatirma islemleri(filtreleme):
        saving hesaba para yatiriniz
 */