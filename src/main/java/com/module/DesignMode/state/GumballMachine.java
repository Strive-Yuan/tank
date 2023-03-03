package com.module.DesignMode.state;

public class GumballMachine {
    //    final static int SOLD_OUT = 0; //售罄
//    final static int NO_QUARTER = 1;
//    final static int HAS_QUARTER = 2;
//    final static int SOLD = 3; //出售
//
//    int state = SOLD_OUT;
//    int count = 0;
    State soldOutState;
    State noQuarterState;
    State hasQuarterState;
    State soldState;
    int count = 0;
    State state = soldOutState;

    public GumballMachine(State soldOutState, State noQuarterState, State hasQuarterState, State soldState, int count) {
        this.soldOutState = soldOutState;
        this.noQuarterState = noQuarterState;
        this.hasQuarterState = hasQuarterState;
        this.soldState = soldState;
        this.count = count;
    }

    public void insertQuarter() {
        state.insertQuarter();
    }


    public void ejectQuarter() {
        state.ejectQuarter();
    }


    public void turnCrank() {
        state.turnCrank();
        state.dispense();
    }

    void setState(State state) {
        this.state = state;
    }

    void releaseBall() {
        System.out.println("A gumball comes rolling out the slot...");
        if (count != 0) {
            count = count - 1;
        }
    }

    public State getSoldOutState() {
        return soldOutState;
    }

    public State getNoQuarterState() {
        return noQuarterState;
    }

    public State getHasQuarterState() {
        return hasQuarterState;
    }

    public int getCount() {
        return count;
    }
}
