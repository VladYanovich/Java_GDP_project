public class PKB extends Wskaznik {



    public PKB(double consumption){
        this.consumption = consumption;
    }
    public PKB(double consumption, double investments){
        this.consumption = consumption;
        this.investments = investments;
    }

    double Wyprowadzenie1F(){
        return consumption + investments;
    }

    double Wyprowadzenie(double goverspend){
        return consumption + investments + goverspend;
    }

    double Wyprowadzenie(double goverspend, double external_balance){
        return consumption + investments + goverspend + external_balance;
    }



}
