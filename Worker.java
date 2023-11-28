package Cog_Factory;

public class Worker {
    
    private String names;
    private int cph;
    private int totalCogsProduced;
    private int totalWaste;
    private int cogsInProgress;


    public Worker(String names, int cph){
        this.names = names;
        this.cph = cph;        
        this.totalCogsProduced = 0;
        this.totalWaste = 0;
        this.cogsInProgress = 0;
    }

    public String getNames(){
        return this.names;
    }

    public int getCph(){
        return this.cph;
    }

    public int getTotalCogsProduced(){
        return this.totalCogsProduced;
    }

    public int getTotalWaste(){
        return this.totalWaste;
    }

    public int getCogsInProgress(){
        return this.cogsInProgress;
    }

    public void reset(){
        this.totalCogsProduced = 0;
        this.totalWaste = 0;
        this.cogsInProgress = 0;
    }

    public boolean isBusy(){
        return cogsInProgress > 0;
    }

    public void assignOrder(int orderSize){
        cogsInProgress = orderSize;
    }

    public void work(){
        
        // Perform an hour of work
        cogsInProgress -= cph;
        int wasted = 0;
        if(cogsInProgress< 0)
        {
            wasted =  -cogsInProgress;
            cogsInProgress = 0;
            totalWaste += wasted;
        }
        totalCogsProduced += cph-wasted;
        
    }

    public int compareTo(int cph){
        return this.cph - cph;
    }

    public String toString(){
        return "Worker: " + this.names + " CPH: " + this.cph + " Total Cogs Produced: " + this.totalCogsProduced + " Total Waste: " + this.totalWaste + " Cogs In Progress: " + this.cogsInProgress;
    }

}
