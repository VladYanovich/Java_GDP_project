public class Narodowy extends Wskaznik {
double DW;
double depreciation;
double indirect_taxes;
double subsidies;

public Narodowy(double consumption, double investments, double goverspend, double external_balance, double DW){
    this.consumption = consumption;
    this.investments = investments;
    this.goverspend = goverspend;
    this.external_balance = external_balance;
    this.DW = DW;
}

double WyprowadzeniePNB(){
    return consumption + investments + goverspend + external_balance + DW;
}

double WyprowadzenieN(double depreciation){
    return consumption + investments + goverspend + external_balance - depreciation;
}


double WyprowadzenieN(double depreciation, double indirect_taxes, double subsidies){
    return consumption + investments + goverspend + external_balance - depreciation - indirect_taxes + subsidies;
}

}
